package com.facebook.ads.internal.d;

import android.os.Bundle;
import com.facebook.ads.internal.w.b.r;
import com.myfitnesspal.shared.constants.Constants.Challenges;

public class d implements r<Bundle> {
    private c a;
    private final c b;
    private final b c;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;

    public d(b bVar) {
        this.c = bVar;
        this.b = new c(bVar.b);
        this.a = new c(bVar.b);
    }

    public d(b bVar, Bundle bundle) {
        this.c = bVar;
        this.b = (c) bundle.getSerializable("testStats");
        this.a = (c) bundle.getSerializable("viewableStats");
        this.d = bundle.getBoolean(Challenges.CHALLENGE_STATUS_ENDED);
        this.e = bundle.getBoolean("passed");
        this.f = bundle.getBoolean("complete");
    }

    private void b() {
        this.f = true;
        this.d = true;
        this.c.a(this.f, this.e, this.e ? this.a : this.b);
    }

    public void a() {
        if (!this.d) {
            this.a.b();
        }
    }

    public void a(double d2, double d3) {
        if (!this.d) {
            this.b.a(d2, d3);
            this.a.a(d2, d3);
            double h = this.c.e ? this.a.c().h() : this.a.c().g();
            if (this.c.c < 0.0d || this.b.c().f() <= this.c.c || h != 0.0d) {
                if (h >= this.c.d) {
                    this.e = true;
                    b();
                }
                return;
            }
            b();
        }
    }

    public Bundle g() {
        Bundle bundle = new Bundle();
        bundle.putSerializable("viewableStats", this.a);
        bundle.putSerializable("testStats", this.b);
        bundle.putBoolean(Challenges.CHALLENGE_STATUS_ENDED, this.d);
        bundle.putBoolean("passed", this.e);
        bundle.putBoolean("complete", this.f);
        return bundle;
    }
}
