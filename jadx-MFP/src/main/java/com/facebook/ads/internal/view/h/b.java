package com.facebook.ads.internal.view.h;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.MediaView;
import com.facebook.ads.internal.t.e;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.t.k;
import com.facebook.ads.internal.w.b.x;
import java.util.ArrayList;

public class b extends LinearLayout implements c {
    private static final int a = ((int) (x.b * 6.0f));
    private final e b;
    private final ArrayList<View> c = new ArrayList<>();

    public b(Context context, e eVar, j jVar, k kVar, MediaView mediaView, AdOptionsView adOptionsView) {
        super(context);
        this.b = eVar;
        setOrientation(0);
        int b2 = (int) (x.b * ((float) kVar.b()));
        a aVar = new a(getContext(), this.b, jVar, adOptionsView);
        int i = a;
        aVar.setPadding(i, i, i, i);
        Button button = new Button(getContext());
        jVar.c((TextView) button);
        button.setText(eVar.a("call_to_action"));
        addView(mediaView, new LayoutParams(b2, b2));
        LayoutParams layoutParams = new LayoutParams(0, -2);
        layoutParams.weight = 1.0f;
        layoutParams.gravity = 16;
        addView(aVar, layoutParams);
        addView(button, new LayoutParams(b2 * 2, b2));
        this.c.add(mediaView);
        this.c.add(button);
    }

    public void a() {
        this.b.z();
    }

    public View getView() {
        return this;
    }

    public ArrayList<View> getViewsForInteraction() {
        return this.c;
    }
}
