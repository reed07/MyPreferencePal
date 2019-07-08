package com.mopub.network;

import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.toolbox.ImageLoader;
import com.mopub.volley.toolbox.ImageLoader.ImageCache;
import com.mopub.volley.toolbox.ImageLoader.ImageContainer;
import com.mopub.volley.toolbox.ImageLoader.ImageListener;

public class MaxWidthImageLoader extends ImageLoader {
    private final int mMaxImageWidth;

    public MaxWidthImageLoader(RequestQueue requestQueue, Context context, ImageCache imageCache) {
        super(requestQueue, imageCache);
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        this.mMaxImageWidth = Math.min(point.x, point.y);
    }

    public ImageContainer get(String str, ImageListener imageListener) {
        return super.get(str, imageListener, this.mMaxImageWidth, 0);
    }
}
