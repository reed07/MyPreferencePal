package com.google.ads.mediation.inmobi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class ImageDownloaderAsyncTask extends AsyncTask<Object, Void, HashMap<String, Drawable>> {
    private static final long DRAWABLE_FUTURE_TIMEOUT_SECONDS = 10;
    static final String KEY_ICON = "icon_key";
    static final String KEY_IMAGE = "image_key";
    private final DrawableDownloadListener mListener;
    private final InMobiMemoryCache memoryCache = new InMobiMemoryCache();

    interface DrawableDownloadListener {
        void onDownloadFailure();

        void onDownloadSuccess(HashMap<String, Drawable> hashMap);
    }

    public ImageDownloaderAsyncTask(DrawableDownloadListener drawableDownloadListener) {
        this.mListener = drawableDownloadListener;
    }

    /* access modifiers changed from: protected */
    public HashMap<String, Drawable> doInBackground(Object... objArr) {
        Drawable drawable;
        Drawable drawable2;
        HashMap hashMap = objArr[0];
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        try {
            if (this.memoryCache.get(String.valueOf(hashMap.get("image_key"))) != null) {
                drawable = this.memoryCache.get(String.valueOf(hashMap.get("image_key")));
            } else {
                drawable = (Drawable) getDrawableFuture((URL) hashMap.get("image_key"), newCachedThreadPool).get(DRAWABLE_FUTURE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
                this.memoryCache.put(String.valueOf(hashMap.get("image_key")), drawable);
            }
            if (this.memoryCache.get(String.valueOf(hashMap.get("icon_key"))) != null) {
                drawable2 = this.memoryCache.get(String.valueOf(hashMap.get("icon_key")));
            } else {
                Drawable drawable3 = (Drawable) getDrawableFuture((URL) hashMap.get("icon_key"), newCachedThreadPool).get(DRAWABLE_FUTURE_TIMEOUT_SECONDS, TimeUnit.SECONDS);
                this.memoryCache.put(String.valueOf(hashMap.get("icon_key")), drawable3);
                drawable2 = drawable3;
            }
            HashMap<String, Drawable> hashMap2 = new HashMap<>();
            hashMap2.put("image_key", drawable);
            hashMap2.put("icon_key", drawable2);
            return hashMap2;
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Future<Drawable> getDrawableFuture(final URL url, ExecutorService executorService) {
        return executorService.submit(new Callable<Drawable>() {
            public Drawable call() throws Exception {
                Bitmap decodeStream = BitmapFactory.decodeStream(url.openStream());
                decodeStream.setDensity(160);
                return new BitmapDrawable(Resources.getSystem(), decodeStream);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(HashMap<String, Drawable> hashMap) {
        super.onPostExecute(hashMap);
        if (hashMap != null) {
            this.mListener.onDownloadSuccess(hashMap);
        } else {
            this.mListener.onDownloadFailure();
        }
    }
}
