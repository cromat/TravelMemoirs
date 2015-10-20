package com.memoirs.travel.travelmemoirs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.VideoView;


public class MemoirDetails extends ActionBarActivity {

    private static final String LOG_TAG = MemoirDetails.class.getSimpleName();

    //Inicijalizacija varijabli prikaza detalja memoara

    private VideoView memoirDetailVideo;
    private ImageView memoirDetailThumbnail;
    private RatingBar memoirDetailRating;
    private TextView memoirDetailDate;
    private TextView memoirDetailDescription;

    private Memoir memoir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memoir_details);

        //Povezivanje kontrola sa varijablama prikaza detalja memoara

        memoirDetailVideo = (VideoView)findViewById(R.id.memoir_detail_video);
        memoirDetailThumbnail = (ImageView)findViewById(R.id.memoir_detail_thumbnail);
        memoirDetailRating = (RatingBar)findViewById(R.id.memoir_detail_rating);
        memoirDetailDate = (TextView)findViewById(R.id.memoir_detail_date);
        memoirDetailDescription = (TextView)findViewById(R.id.memoir_detail_description);

        //TODO: RatingBar i VideoView onClickListener

        Intent intent = getIntent();
        memoir = (Memoir)intent.getSerializableExtra("memoir");
        memoirDetailThumbnail.setImageBitmap(memoir.getMemoirThumbnail());
        memoirDetailRating.setRating(memoir.getMemoirRating());
        memoirDetailDate.setText(memoir.getMemoirDate().toString());
        memoirDetailDescription.setText(memoir.getMemoirDescription());
        //TODO: set video
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_memoir_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
