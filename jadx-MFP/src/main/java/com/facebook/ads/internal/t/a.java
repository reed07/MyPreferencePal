package com.facebook.ads.internal.t;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.view.component.d;
import com.facebook.ads.internal.view.component.h;
import com.facebook.ads.internal.view.h.c;
import com.facebook.ads.internal.w.b.x;
import java.util.ArrayList;

public class a extends FrameLayout implements c {
    private static final int a = ((int) (x.b * 110.0f));
    private final j b;
    private final e c;
    private final AdOptionsView d;
    private ArrayList<View> e = new ArrayList<>();

    public a(Context context, e eVar, AdOptionsView adOptionsView, @Nullable MediaView mediaView, MediaView mediaView2, k kVar, j jVar) {
        super(context);
        this.b = jVar;
        this.c = eVar;
        this.d = adOptionsView;
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        switch (kVar) {
            case HEIGHT_400:
                h hVar = new h(getContext(), this.c, this.b);
                hVar.setLayoutParams(new LayoutParams(-1, a));
                linearLayout.addView(hVar);
                break;
            case HEIGHT_300:
                break;
        }
        RelativeLayout relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(new LayoutParams(-1, (int) (x.b * 180.0f)));
        x.a((View) relativeLayout, this.b.b());
        relativeLayout.addView(mediaView);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (int) (x.b * 180.0f));
        layoutParams.addRule(13, -1);
        mediaView.setLayoutParams(layoutParams);
        linearLayout.addView(relativeLayout);
        this.e.add(mediaView);
        d dVar = new d(getContext(), this.c, this.b, mediaView2, this.d, kVar == k.HEIGHT_300 || kVar == k.HEIGHT_120, a(kVar));
        dVar.setLayoutParams(new LayoutParams(-1, (int) (((float) a(kVar)) * x.b)));
        linearLayout.addView(dVar);
        this.e.add(dVar.getIconView());
        this.e.add(dVar.getCallToActionView());
        addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
    }

    private static int a(k kVar) {
        switch (kVar) {
            case HEIGHT_400:
                return (kVar.b() - 180) / 2;
            case HEIGHT_300:
                return kVar.b() - 180;
            case HEIGHT_50:
            case HEIGHT_100:
            case HEIGHT_120:
                return kVar.b();
            default:
                return 0;
        }
    }

    public void a() {
        this.c.z();
    }

    public View getView() {
        return this;
    }

    public ArrayList<View> getViewsForInteraction() {
        return this.e;
    }
}
