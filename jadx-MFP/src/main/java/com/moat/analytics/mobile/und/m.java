package com.moat.analytics.mobile.und;

import android.util.Log;

class m extends Exception {
    m() {
    }

    static void a(Exception exc) {
        if (w.a().b) {
            Log.e("MoatException", Log.getStackTraceString(exc));
        }
    }
}
