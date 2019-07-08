package com.brightcove.player.edge;

import android.util.Log;

public abstract class ErrorListener {
    private final String TAG = getClass().getSimpleName();

    public void onError(String str) {
        Log.e(this.TAG, str);
    }
}
