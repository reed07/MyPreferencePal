package com.google.ads.interactivemedia.v3.internal;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.google.ads.interactivemedia.v3.api.player.VideoProgressUpdate;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IMASDK */
public abstract class aeg implements Callback {
    private final long a;
    private boolean b;
    private final aeh c;
    private List<aei> d;

    aeg(long j) {
        this(null, j);
    }

    public abstract VideoProgressUpdate a();

    private aeg(aeh aeh, long j) {
        this.b = false;
        this.d = new ArrayList(1);
        this.a = j;
        this.c = new aeh(new Handler(this));
    }

    public final void a(aei aei) {
        this.d.add(aei);
    }

    public final void b(aei aei) {
        this.d.remove(aei);
    }

    public final void b() {
        if (!this.b) {
            this.b = true;
            this.c.b(0);
        }
    }

    public final void c() {
        if (this.b) {
            this.b = false;
            this.c.c(2);
        }
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
            case 1:
                VideoProgressUpdate a2 = a();
                for (aei a3 : this.d) {
                    a3.a(a2);
                }
                this.c.a(1, this.a);
                break;
            case 2:
                this.c.a(1);
                break;
        }
        return true;
    }
}
