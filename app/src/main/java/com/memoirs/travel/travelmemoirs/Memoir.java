package com.memoirs.travel.travelmemoirs;

import android.graphics.Bitmap;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mat on 19.10.2015..
 */
public class Memoir implements Serializable{
    private Bitmap memoirThumbnail;
    private String memoirTitle;
    private String memoirDescription;
    private float memoirRating;
    private MemoirLocation memoirLocation;
    private Date memoirDate;

    //Konstruktori klase Memoir

    public Memoir(){
        super();
        this.memoirThumbnail = null;
        this.memoirTitle = "NONE";
        this.memoirDescription = "NONE";
        this.memoirRating = 0;
        this.memoirLocation = new MemoirLocation();
        this.memoirDate = null;
    }

    public Memoir(Memoir memoir){
        super();
        this.memoirThumbnail = memoir.memoirThumbnail;
        this.memoirTitle = memoir.memoirTitle;
        this.memoirDescription = memoir.memoirDescription;
        this.memoirRating = memoir.memoirRating;
        this.memoirLocation = memoir.getMemoirLocation();
        this.memoirDate = memoir.memoirDate;
    }

    public Memoir(Bitmap memoirThumbnail, String memoirTitle, String memoirDescription,
                  float memoirRating, String city, String country,Date date){
        super();
        this.memoirThumbnail = memoirThumbnail;
        this.memoirTitle = memoirTitle;
        this.memoirDescription = memoirDescription;
        this.memoirRating = memoirRating;
        this.memoirLocation = new MemoirLocation(city, country);
        this.memoirDate =  date;
    }

    public Memoir(Bitmap memoirThumbnail, String memoirTitle, String memoirDescription,
                  float memoirRating, Memoir.MemoirLocation location,Date date){
        super();
        this.memoirThumbnail = memoirThumbnail;
        this.memoirTitle = memoirTitle;
        this.memoirDescription = memoirDescription;
        this.memoirRating = memoirRating;
        this.memoirLocation = new MemoirLocation(location);
        this.memoirDate =  date;
    }

    //Getteri i setteri klase memoir

    public Date getMemoirDate() {
        return memoirDate;
    }

    public void setMemoirDate(Date memoirDate) {
        this.memoirDate = memoirDate;
    }

    public Bitmap getMemoirThumbnail() {
        return memoirThumbnail;
    }

    public void setMemoirThumbnail(Bitmap memoirThumbnail) {
        this.memoirThumbnail = memoirThumbnail;
    }

    public String getMemoirDescription() {
        return memoirDescription;
    }

    public void setMemoirDescription(String memoirDescription) {
        this.memoirDescription = memoirDescription;
    }

    public float getMemoirRating() {
        return memoirRating;
    }

    public void setMemoirRating(float memoirRating) {
        this.memoirRating = memoirRating;
    }

    public MemoirLocation getMemoirLocation() {
        return memoirLocation;
    }

    public void setMemoirLocation(MemoirLocation memoirLocation) {
        this.memoirLocation = memoirLocation;
    }

    public String getMemoirTitle() {
        return memoirTitle;
    }

    public void setMemoirTitle(String memoirTitle) {
        this.memoirTitle = memoirTitle;
    }

    //Deklaracija klase MemoirLocation

    public static class MemoirLocation{
        private String city;
        private String country;

        //Konstruktori klase MemoirLocation

        public MemoirLocation(){
            super();
            this.city = "NONE";
            this.country = "NONE";
        }

        public MemoirLocation(String city, String country){
            super();
            this.city = city.substring(0,1).toUpperCase() + city.substring(1).toLowerCase();
            this.country = country.substring(0,1).toUpperCase() + country.substring(1).toLowerCase();
        }

        public MemoirLocation(MemoirLocation location){
            super();
            this.city = location.city.substring(0,1).toUpperCase() + location.city.substring(1).toLowerCase();
            this.country = location.country.substring(0,1).toUpperCase() + location.country.substring(1).toLowerCase();
        }

        //Getteri i setteri klase MemoirLocation

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }
    }
}
