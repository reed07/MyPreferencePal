package com.google.ads.mediation.inmobi;

import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ImpressionBeaconAsyncTask extends AsyncTask {
    private static final String TAG = "ImpressionBeaconTask";
    private static final long retryDelay = 20;

    /* access modifiers changed from: protected */
    public Object doInBackground(Object[] objArr) {
        for (String replace : (String[]) objArr) {
            try {
                URL url = new URL(replace.replace("\\/", "/").replace("$TS", new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss.SS", Locale.US).format(new Date())));
                int responseCode = ((HttpURLConnection) url.openConnection()).getResponseCode();
                for (int i = 1; i <= 3 && responseCode != 200; i++) {
                    Log.d(TAG, "Impression Beacon failed");
                    Thread.sleep(retryDelay);
                    responseCode = ((HttpURLConnection) url.openConnection()).getResponseCode();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
        }
        return null;
    }
}
