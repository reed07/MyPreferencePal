package com.facebook.ads.internal.view.i;

import android.database.ContentObserver;
import android.os.Handler;

class e extends ContentObserver {
    private final c a;

    e(Handler handler, c cVar) {
        super(handler);
        this.a = cVar;
    }

    public boolean deliverSelfNotifications() {
        return false;
    }

    public void onChange(boolean z) {
        this.a.e();
    }
}
