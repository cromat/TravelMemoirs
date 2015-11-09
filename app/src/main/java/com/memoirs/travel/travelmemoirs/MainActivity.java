package com.memoirs.travel.travelmemoirs;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.multidex.MultiDex;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;



import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        MultiDex.install(this);
    }
//Test objekt

    private String memoirTitle = "Naslov Videa";
    private String memoirDescription = "Ovo je jedan zanimljivi video iz Bosne.";
    private float memoirRating = 4.5f;
    private Memoir.MemoirLocation memoirLocation = new Memoir.MemoirLocation("split", "croatia");
    private Date memoirDate = new Date(1995, 5, 12);


    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //test gumb za login screen
    private Button stiskanac;

    //Inicijalizacija varijabli
    private RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private ArrayList<Memoir> mMemoirsList;
    MemoirsAdapter mAdapter;
    private ListView mDrawerList;
    private ArrayAdapter<String> mDrawerAdapter;
    private DrawerLayout mDrawerLayout;

    private GoogleApiClient mGoogleApiClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Pridruživanje postojećih kontrola varijablama
        mRecyclerView = (RecyclerView) findViewById(R.id.memoirs_recycler_view);
        mMemoirsList = new ArrayList<>();

        //Gumb za login screen
        stiskanac = (Button) findViewById(R.id.stiskanac);

        //test
        String memoirThumbnail = "dada";
        Memoir memoir = new Memoir(memoirThumbnail, memoirTitle, memoirDescription, memoirRating, memoirLocation, memoirDate);
        mMemoirsList.add(memoir);

        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MemoirsAdapter(mMemoirsList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyItemInserted(mMemoirsList.size());


        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        //klik za login screen
       stiskanac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateMemoir.class));
            }
        });

        addDrawerItems();

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case (0): //filter za memoare ulogiranog korisnika

                    case (1):
                        startActivity(new Intent(getApplicationContext(), CreateMemoir.class));
                        break;

                    case (2): //filter za memoare po datumu

                    case (3): //Slikaj i spremi na odredeno mjesto u memoriji

                    case (4): //drafts

                    case (5):
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        break;
                    case (6):
                        //slozit ovo jos, mozda ce radit ako se pocetni activity stavi login
                        signOutFromGplus();
                }
            }
        });
    }

    private void addDrawerItems() {
        String[] osArray = {"My Memoirs", "Create Memoir", "Recent Memoirs", "Take a Photo/Video", "Drafts", "Settings", "Log Out"};
        mDrawerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mDrawerAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_MENU) {

            View drawerView = findViewById(R.id.navList); // child drawer view

            if (!mDrawerLayout.isDrawerOpen(drawerView)) {
                mDrawerLayout.openDrawer(drawerView);
            } else if (mDrawerLayout.isDrawerOpen(drawerView)) {
                mDrawerLayout.closeDrawer(drawerView);
            }
            return true;
        }

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            View drawerView = findViewById(R.id.navList);
            if (mDrawerLayout.isDrawerOpen(drawerView)) {
                mDrawerLayout.closeDrawer(drawerView);
            } else finish();

            return true;
        }
            return super.onKeyDown(keyCode, event);
        }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private void signOutFromGplus() {
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
            mGoogleApiClient.connect();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
    }


}




