package com.memoirs.travel.travelmemoirs;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mat on 23.10.2015..
 */
public class Draft implements Serializable {
    private int id;
    private String draftThumbnail;
    private String draftTitle;
    private String draftDescription;
    private DraftLocation draftLocation;

    //Konstruktori klase Draft

    public Draft() {
        super();
        this.draftThumbnail = null;
        this.draftTitle = "NONE";
        this.draftDescription = "NONE";
        this.draftLocation = new DraftLocation();
    }

    public Draft(Draft draft) {
        super();
        this.draftThumbnail = draft.draftThumbnail;
        this.draftTitle = draft.draftTitle;
        this.draftDescription = draft.draftDescription;
        this.draftLocation = draft.getDraftLocation();
    }

    public Draft(String draftThumbnail, String drafTitle, String draftDescription,
                  String city, String country) {
        super();
        this.draftThumbnail = draftThumbnail;
        this.draftTitle = drafTitle;
        this.draftDescription = draftDescription;
        this.draftLocation = new DraftLocation(city, country);
    }

    public Draft(String draftThumbnail, String drafTitle, String draftDescription,
                 DraftLocation location) {
        super();
        this.draftThumbnail = draftThumbnail;
        this.draftTitle = drafTitle;
        this.draftDescription = draftDescription;
        this.draftLocation = new DraftLocation(location);
    }

    //Getteri i setteri klase draft


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDraftThumbnail() {
        return draftThumbnail;
    }

    public void setDraftThumbnail(String draftThumbnail) {
        this.draftThumbnail = draftThumbnail;
    }

    public String getDraftTitle() {
        return draftTitle;
    }

    public void setDrafTitle(String draftTitle) {
        this.draftTitle = draftTitle;
    }

    public String getDrafDescription() {
        return draftDescription;
    }

    public void setDrafDescription(String draftDescription) {
        this.draftDescription = draftDescription;
    }

    public DraftLocation getDraftLocation() {
        return draftLocation;
    }

    public void setDraftLocation(DraftLocation draftLocation) {
        this.draftLocation = draftLocation;
    }

    public static class DraftLocation implements Serializable {
        private String city;
        private String country;

        //Konstruktori klase DraftLocation

        public DraftLocation() {
            super();
            this.city = "NONE";
            this.country = "NONE";
        }

        public DraftLocation(String city, String country) {
            super();
            this.city = city.substring(0, 1).toUpperCase() + city.substring(1).toLowerCase();
            this.country = country.substring(0, 1).toUpperCase() + country.substring(1).toLowerCase();
        }

        public DraftLocation(DraftLocation location) {
            super();
            this.city = location.city.substring(0, 1).toUpperCase() + location.city.substring(1).toLowerCase();
            this.country = location.country.substring(0, 1).toUpperCase() + location.country.substring(1).toLowerCase();
        }

        //Getteri i setteri klase DraftLocation

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
