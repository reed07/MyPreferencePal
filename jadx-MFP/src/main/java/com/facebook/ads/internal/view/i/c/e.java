package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.view.i.a.c;
import com.facebook.ads.internal.view.i.b.b;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import java.util.HashMap;

public class e extends c {
    /* access modifiers changed from: private */
    public final String a;
    private final TextView b = new TextView(getContext());
    /* access modifiers changed from: private */
    public final com.facebook.ads.internal.s.c c;
    /* access modifiers changed from: private */
    public final String d;
    private final Paint e;
    private final RectF f;

    public e(Context context, String str, com.facebook.ads.internal.s.c cVar, String str2, String str3) {
        super(context);
        this.a = str;
        this.c = cVar;
        this.d = str2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        this.b.setTextColor(-3355444);
        this.b.setTextSize(16.0f);
        this.b.setPadding((int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f), (int) (displayMetrics.density * 6.0f), (int) (displayMetrics.density * 4.0f));
        this.e = new Paint();
        this.e.setStyle(Style.FILL);
        this.e.setColor(-16777216);
        this.e.setAlpha(RequestCodes.PROGRESS_PHOTOS_GALLERY);
        this.f = new RectF();
        x.a((View) this, 0);
        this.b.setText(str3);
        addView(this.b, new LayoutParams(-2, -2));
    }

    /* access modifiers changed from: protected */
    public void a() {
        super.a();
        this.b.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (e.this.getVideoView() != null) {
                    Uri parse = Uri.parse(e.this.a);
                    e.this.getVideoView().getEventBus().a(new b(parse));
                    com.facebook.ads.internal.a.b a2 = com.facebook.ads.internal.a.c.a(e.this.getContext(), e.this.c, e.this.d, parse, new HashMap());
                    if (a2 != null) {
                        a2.a();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void b() {
        this.b.setOnClickListener(null);
        super.b();
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.f.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) getWidth(), (float) getHeight());
        canvas.drawRoundRect(this.f, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, this.e);
        super.onDraw(canvas);
    }
}
