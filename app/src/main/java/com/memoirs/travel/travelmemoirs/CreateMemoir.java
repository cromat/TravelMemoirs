package com.memoirs.travel.travelmemoirs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacv.AndroidFrameConverter;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import static org.bytedeco.javacpp.opencv_imgcodecs.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

import paul.arian.fileselector.FileSelectionActivity;

public class CreateMemoir extends Activity implements View.OnClickListener{

    private LinearLayout lnrImages;
    private Button btnAddPhots;
    private Button btnSaveImages;
    private ArrayList<String> imagesPathList;
    private Bitmap yourbitmap;
    private Bitmap resized;
    private FFmpegFrameRecorder recorder;
    private final int PICK_IMAGE_MULTIPLE =1;
    private String [] files_paths;

    //C:\Users\mat\.gradle\caches\modules-2\files-2.1\org.bytedeco.javacpp-presets\opencv\3.0.0-1.1\a5adaff32a0abfaf188453c088a8b352e3098fd7\opencv-3.0.0-1.1-sources.jar!\org\bytedeco\javacpp\helper\opencv_core.java

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
                Intent intent = new Intent(CreateMemoir.this,FileSelectionActivity.class);
                startActivityForResult(intent,0);



                break;
            case R.id.btnSaveImages:
                /*new Thread(new Runnable() {
                    public void run() {*/

               // opencv_core.IplImage img = cvLoadImage(Environment.getExternalStorageState() + "/DCIM/JCDIMG/android.jpg");
                FFmpegFrameRecorder recorder = new FFmpegFrameRecorder("storage/emulated/0/DCIM/bananko.mp4",200,150);

                try {
                    recorder.setVideoCodec(avcodec.AV_CODEC_ID_MPEG4);
                    recorder.setFrameRate(20);
                    recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
                    recorder.start();

                    for (int i=0;i<files_paths.length;i++)
                    {
                        Bitmap bitmap = BitmapFactory.decodeFile(files_paths[i]);
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG,20,out);
                        Bitmap compressed = BitmapFactory.decodeStream(new ByteArrayInputStream(out.toByteArray()));

                        AndroidFrameConverter afc = new AndroidFrameConverter();
                        Frame frame = afc.convert(compressed);

                            for (int j=0;j<20;j++)
                                recorder.record(frame);
                    }
                    recorder.stop();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                   /* }
                }).start();*/

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0 && resultCode == RESULT_OK){
            ArrayList<File> Files = (ArrayList<File>) data.getSerializableExtra(FileSelectionActivity.FILES_TO_UPLOAD); //file array list
            files_paths = new String[Files.size()]; //string array
            int i = 0;

            for(File file : Files){
                //String fileName = file.getName();
                String uri = file.getAbsolutePath();
                System.out.println(uri);
                files_paths[i] = uri.toString(); //storing the selected file's paths to string array files_paths
                i++;
            }
        }else{
        }

    }





}
