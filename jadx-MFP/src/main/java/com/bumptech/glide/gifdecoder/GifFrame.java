package com.bumptech.glide.gifdecoder;

import android.support.annotation.ColorInt;

class GifFrame {
    int bufferFrameStart;
    int delay;
    int dispose;
    int ih;
    boolean interlace;
    int iw;
    int ix;
    int iy;
    @ColorInt
    int[] lct;
    int transIndex;
    boolean transparency;

    GifFrame() {
    }
}
