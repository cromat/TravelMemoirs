package com.memoirs.travel.travelmemoirs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import static org.bytedeco.javacpp.opencv_imgcodecs.*;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.AndroidFrameConverter;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.OpenCVFrameConverter;

import java.io.File;
import java.util.ArrayList;

public class CreateMemoir extends Activity implements View.OnClickListener{


    private LinearLayout lnrImages;
    private Button btnAddPhots;
    private Button btnSaveImages;
    private ArrayList<String> imagesPathList;
    private Bitmap yourbitmap;
    private Bitmap resized;
    private FFmpegFrameRecorder recorder;
    private final int PICK_IMAGE_MULTIPLE =1;

    opencv_core.IplImage[] img;

    private int imageWidth = 320;
    private int imageHeight = 240;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_memoir);
        lnrImages = (LinearLayout)findViewById(R.id.lnrImages);
        btnAddPhots = (Button)findViewById(R.id.btnAddPhots);
        btnSaveImages = (Button)findViewById(R.id.btnSaveImages);
        btnAddPhots.setOnClickListener(this);
        btnSaveImages.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddPhots:
                Intent intent = new Intent(CreateMemoir.this,CustomPhotoGalleryActivity.class);
                startActivityForResult(intent,PICK_IMAGE_MULTIPLE);
                break;
            case R.id.btnSaveImages:

                File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
                        "JCDIMG");


                File folder = mediaStorageDir;
                File[] listOfFiles = folder.listFiles();
                if(listOfFiles.length>0)
                {
                    img = new opencv_core.IplImage[listOfFiles.length];

                    for (int j = 0; j < listOfFiles.length; j++) {
                        String files="";
                        if (listOfFiles[j].isFile())
                        {
                            files = listOfFiles[j].getName();
                        }
                        String[] tokens = files.split("\\.(?=[^\\.]+$)");
                        String name=tokens[0];

                        System.out.println(" j " + name);

                        img[j]=cvLoadImage("/sdcard/DCIM/JCDIMG/"+name+".jpg");
                    }
                }

                FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("/sdcard/DCIM/MyImages/test.mp4",800,480);
                OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();

                try {
                    recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
                    recorder.setFormat("mp4");
                    recorder.setFrameRate(2);
                    recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
                    recorder.start();

                    int x = 0;
                    int y = 0;
                    for (int i=0; i<img.length; i++)
                    {
                        recorder.record(converterToMat.convert(img[i]));

                        //publishProgress((int) ((i / (float) img.length) * 100));

                    }

                    recorder.stop();
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                if(imagesPathList !=null){
                    if(imagesPathList.size()>1) {
                        Toast.makeText(CreateMemoir.this, imagesPathList.size() + " no of images are selected", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(CreateMemoir.this, imagesPathList.size() + " no of image are selected", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(CreateMemoir.this," no images are selected", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if(requestCode == PICK_IMAGE_MULTIPLE){
                imagesPathList = new ArrayList<String>();
                String[] imagesPath = data.getStringExtra("data").split("\\|");
                try{
                    lnrImages.removeAllViews();
                }catch (Throwable e){
                    e.printStackTrace();
                }



                /*for (int i=0;i<imagesPath.length;i++){
                    imagesPathList.add(imagesPath[i]);
                    yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);
                    ImageView imageView = new ImageView(this);
                    imageView.setImageBitmap(yourbitmap);
                    imageView.setAdjustViewBounds(true);
                    lnrImages.addView(imageView);
                }*/
            }
        }

    }

}
