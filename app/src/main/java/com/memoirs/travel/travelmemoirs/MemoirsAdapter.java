package com.memoirs.travel.travelmemoirs;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

/**
 * Created by mat on 19.10.2015..
 */

//Adapter koji spaja listu memoara sa RecyclerView listom koja se prikazuje

public class MemoirsAdapter extends RecyclerView.Adapter<MemoirsAdapter.MemoirsViewHolder>{

    private static final String LOG_TAG = MemoirsAdapter.class.getSimpleName();

    private static List<Memoir> mMemoirs;

    public MemoirsAdapter(List<Memoir> list){
        this.mMemoirs = list;
    }

    @Override
    public int getItemCount() {
        return mMemoirs.size();
    }

    @Override
    public MemoirsViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_memoir_item, parent, false);
        return new MemoirsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MemoirsViewHolder memoirsViewHolder, int i) {
        //TODO: Download thumbnaila
        memoirsViewHolder.memoirItemTitle.setText(mMemoirs.get(i).getMemoirTitle());
        memoirsViewHolder.memoirItemDescription.setText(mMemoirs.get(i).getMemoirDescription());
        memoirsViewHolder.memoirItemRating.setRating(mMemoirs.get(i).getMemoirRating());
        memoirsViewHolder.memoirItemLocation.setText(mMemoirs.get(i).getMemoirLocation().getCity()
                + ", " + mMemoirs.get(i).getMemoirLocation().getCountry());
        memoirsViewHolder.memoirItemDate.setText(mMemoirs.get(i).getMemoirDate().toString());
    }

    //Klasa za prikaz pojedinog memoara u RecyclerView listi

    public static class MemoirsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView memoirItemThumbnail;
        private TextView memoirItemTitle;
        private TextView memoirItemDescription;
        private RatingBar memoirItemRating;
        private TextView memoirItemLocation;
        private TextView memoirItemDate;
        private Context context;

        public MemoirsViewHolder(View view){
            super(view);

            memoirItemThumbnail = (ImageView)view.findViewById(R.id.memoir_item_thumbnail);
            memoirItemTitle = (TextView)view.findViewById(R.id.memoir_item_title);
            memoirItemDescription = (TextView)view.findViewById(R.id.memoir_item_description);
            memoirItemRating = (RatingBar)view.findViewById(R.id.memoir_item_rating);
            memoirItemLocation = (TextView)view.findViewById(R.id.memoir_item_location);
            memoirItemDate = (TextView)view.findViewById(R.id.memoir_item_date);
            context = itemView.getContext();
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), MemoirDetails.class);
            intent.putExtra("memoir",mMemoirs.get(getPosition()));
            context.startActivity(intent);
        }
    }
}
