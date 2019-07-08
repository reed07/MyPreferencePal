package com.google.ads.mediation.inmobi;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.google.android.gms.ads.formats.NativeAd.Image;

class InMobiNativeMappedImage extends Image {
    private final Drawable inMobiDrawable;
    private final double inMobiScale;
    private final Uri inmobiImageUri;

    public InMobiNativeMappedImage(Drawable drawable, Uri uri, double d) {
        this.inMobiDrawable = drawable;
        this.inmobiImageUri = uri;
        this.inMobiScale = d;
    }

    public Drawable getDrawable() {
        return this.inMobiDrawable;
    }

    public Uri getUri() {
        return this.inmobiImageUri;
    }

    public double getScale() {
        return this.inMobiScale;
    }
}
