package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import android.widget.Toast;
import com.mopub.common.AdReport;
import com.mopub.common.util.DateAndTime;
import com.mopub.common.util.Intents;
import com.mopub.exceptions.IntentNotResolvableException;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AdAlertReporter {
    private final Context mContext;
    private final String mDateString = new SimpleDateFormat("M/d/yy hh:mm:ss a z", Locale.US).format(DateAndTime.now());
    private Intent mEmailIntent;
    private String mParameters;
    private String mResponse;
    private final View mView;

    public AdAlertReporter(Context context, View view, @Nullable AdReport adReport) {
        this.mView = view;
        this.mContext = context;
        initEmailIntent();
        String convertBitmapInWEBPToBase64EncodedString = convertBitmapInWEBPToBase64EncodedString(takeScreenShot());
        this.mParameters = "";
        this.mResponse = "";
        if (adReport != null) {
            this.mParameters = adReport.toString();
            this.mResponse = adReport.getResponseString();
        }
        addEmailSubject();
        addEmailBody(this.mParameters, this.mResponse, convertBitmapInWEBPToBase64EncodedString);
    }

    public void send() {
        try {
            Intents.startActivity(this.mContext, this.mEmailIntent);
        } catch (IntentNotResolvableException unused) {
            Toast.makeText(this.mContext, "No email client available", 0).show();
        }
    }

    private void initEmailIntent() {
        this.mEmailIntent = new Intent("android.intent.action.SENDTO");
        this.mEmailIntent.setData(Uri.parse("mailto:creative-review@mopub.com"));
    }

    private Bitmap takeScreenShot() {
        View view = this.mView;
        if (view == null || view.getRootView() == null) {
            return null;
        }
        View rootView = this.mView.getRootView();
        boolean isDrawingCacheEnabled = rootView.isDrawingCacheEnabled();
        rootView.setDrawingCacheEnabled(true);
        Bitmap drawingCache = rootView.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(drawingCache);
        rootView.setDrawingCacheEnabled(isDrawingCacheEnabled);
        return createBitmap;
    }

    private String convertBitmapInWEBPToBase64EncodedString(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, 25, byteArrayOutputStream);
                return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private void addEmailSubject() {
        StringBuilder sb = new StringBuilder();
        sb.append("New creative violation report - ");
        sb.append(this.mDateString);
        this.mEmailIntent.putExtra("android.intent.extra.SUBJECT", sb.toString());
    }

    private void addEmailBody(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            sb.append(strArr[i]);
            if (i != strArr.length - 1) {
                sb.append("\n=================\n");
            }
        }
        this.mEmailIntent.putExtra("android.intent.extra.TEXT", sb.toString());
    }
}
