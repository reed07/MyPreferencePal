package com.airbnb.lottie.manager;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable.Callback;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import com.airbnb.lottie.ImageAssetDelegate;
import com.airbnb.lottie.LottieImageAsset;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ImageAssetManager {
    private static final Object bitmapHashLock = new Object();
    private final Context context;
    @Nullable
    private ImageAssetDelegate delegate;
    private final Map<String, LottieImageAsset> imageAssets;
    private String imagesFolder;

    public ImageAssetManager(Callback callback, String str, ImageAssetDelegate imageAssetDelegate, Map<String, LottieImageAsset> map) {
        this.imagesFolder = str;
        if (!TextUtils.isEmpty(str)) {
            String str2 = this.imagesFolder;
            if (str2.charAt(str2.length() - 1) != '/') {
                StringBuilder sb = new StringBuilder();
                sb.append(this.imagesFolder);
                sb.append('/');
                this.imagesFolder = sb.toString();
            }
        }
        if (!(callback instanceof View)) {
            Log.w("LOTTIE", "LottieDrawable must be inside of a view for images to work.");
            this.imageAssets = new HashMap();
            this.context = null;
            return;
        }
        this.context = ((View) callback).getContext();
        this.imageAssets = map;
        setDelegate(imageAssetDelegate);
    }

    public void setDelegate(@Nullable ImageAssetDelegate imageAssetDelegate) {
        this.delegate = imageAssetDelegate;
    }

    @Nullable
    public Bitmap bitmapForId(String str) {
        LottieImageAsset lottieImageAsset = (LottieImageAsset) this.imageAssets.get(str);
        if (lottieImageAsset == null) {
            return null;
        }
        Bitmap bitmap = lottieImageAsset.getBitmap();
        if (bitmap != null) {
            return bitmap;
        }
        ImageAssetDelegate imageAssetDelegate = this.delegate;
        if (imageAssetDelegate != null) {
            Bitmap fetchBitmap = imageAssetDelegate.fetchBitmap(lottieImageAsset);
            if (fetchBitmap != null) {
                putBitmap(str, fetchBitmap);
            }
            return fetchBitmap;
        }
        String fileName = lottieImageAsset.getFileName();
        Options options = new Options();
        options.inScaled = true;
        options.inDensity = 160;
        if (!fileName.startsWith("data:") || fileName.indexOf("base64,") <= 0) {
            try {
                if (!TextUtils.isEmpty(this.imagesFolder)) {
                    AssetManager assets = this.context.getAssets();
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.imagesFolder);
                    sb.append(fileName);
                    return putBitmap(str, BitmapFactory.decodeStream(assets.open(sb.toString()), null, options));
                }
                throw new IllegalStateException("You must set an images folder before loading an image. Set it with LottieComposition#setImagesFolder or LottieDrawable#setImagesFolder");
            } catch (IOException e) {
                Log.w("LOTTIE", "Unable to open asset.", e);
                return null;
            }
        } else {
            try {
                byte[] decode = Base64.decode(fileName.substring(fileName.indexOf(44) + 1), 0);
                return putBitmap(str, BitmapFactory.decodeByteArray(decode, 0, decode.length, options));
            } catch (IllegalArgumentException e2) {
                Log.w("LOTTIE", "data URL did not have correct base64 format.", e2);
                return null;
            }
        }
    }

    public void recycleBitmaps() {
        synchronized (bitmapHashLock) {
            for (Entry value : this.imageAssets.entrySet()) {
                LottieImageAsset lottieImageAsset = (LottieImageAsset) value.getValue();
                Bitmap bitmap = lottieImageAsset.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                    lottieImageAsset.setBitmap(null);
                }
            }
        }
    }

    public boolean hasSameContext(Context context2) {
        return (context2 == null && this.context == null) || this.context.equals(context2);
    }

    private Bitmap putBitmap(String str, @Nullable Bitmap bitmap) {
        synchronized (bitmapHashLock) {
            ((LottieImageAsset) this.imageAssets.get(str)).setBitmap(bitmap);
        }
        return bitmap;
    }
}
