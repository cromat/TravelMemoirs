package com.memoirs.travel.travelmemoirs;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    private Bitmap memoirThumbnail = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.android);
    private String memoirTitle = "Naslov";
    private String memoirDescription = "opisg sdgasg sdg asg sg s";
    private float memoirRating = 3;
    private Memoir.MemoirLocation memoirLocation = new Memoir.MemoirLocation("split","croatia");
    private Date memoirDate = new Date(1995,5,12);
    Memoir memoir = new Memoir(memoirThumbnail,memoirTitle,memoirDescription,memoirRating,memoirLocation,memoirDate);

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;

    //Inicijalizacija varijabli
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<Memoir> mMemoirsList;
    MemoirsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pridruživanje postojećih kontrola varijablama
        mRecyclerView = (RecyclerView)findViewById(R.id.memoirs_recycler_view);
        mMemoirsList = new ArrayList<>();

        //test
        mMemoirsList.add(memoir);

        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MemoirsAdapter(mMemoirsList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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




