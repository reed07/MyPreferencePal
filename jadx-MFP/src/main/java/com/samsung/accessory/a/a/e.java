package com.samsung.accessory.a.a;

import android.content.Context;

final class e {
    String a;
    int b;
    int c;

    public e(Context context, String str, int i) {
        if (context != null) {
            context.getApplicationContext();
            this.a = str;
            this.b = i;
            this.c = 2097152;
            return;
        }
        throw new RuntimeException("Failed to configure the Pool!");
    }
}
