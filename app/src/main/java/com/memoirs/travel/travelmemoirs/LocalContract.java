package com.memoirs.travel.travelmemoirs;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by mat on 20.10.2015..
 */
public class LocalContract {

    public static final String CONTENT_AUTHORITY = "com.memoirs.travelmemoirs";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_DRAFT = "draft";


    //Unutarnja klasa koja sadrzi atribute SQLite baze
    public static final class DraftEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_DRAFT).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRAFT;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_DRAFT;

        //Za tablicu draftova
        public static final String TABLE_NAME_DRAFTS = "drafts";

        public static final String COLUMN_THUMBNAIL = "thumbnail";

        public static final String COLUMN_TITLE = "title";

        public static final String COLUMN_DESCRIPTION = "description";

        public static final String COLUMN_CITY = "city";

        public static final String COLUMN_COUNTRY = "country";

        //Za tablicu slika od draftova

        public static final String TABLE_NAME_DRAFT_PICTURES = "pictures";

        public static final String COLUMN_PICTURE_PATH = "path";

        public static final String COLUMN_DRAFT_ID = "id_draft";

        public static Uri buildMemoirUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
