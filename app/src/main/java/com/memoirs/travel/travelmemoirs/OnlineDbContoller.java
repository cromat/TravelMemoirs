package com.memoirs.travel.travelmemoirs;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mat on 22.10.2015..
 */
public class OnlineDbContoller extends Application{

    public static final String TAG = OnlineDbContoller.class.getSimpleName();

    private RequestQueue mRequestQueue;

    private static OnlineDbContoller mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized OnlineDbContoller getInstance() {
        return mInstance;
    }

    /**
     * Dohvaćanje zahtjeva za spajanje na internet iz reda
     */
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    /**
     * Dodaje novi zahtjev za spajanje na internet u red čekanja
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    /**
     * Prekida trenutne zahtjeve za spajanje
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
