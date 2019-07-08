package com.facebook.ads.internal.view.e;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.view.i.b.b;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.HashMap;

public class a extends RelativeLayout {
    /* access modifiers changed from: private */
    public final w a;
    /* access modifiers changed from: private */
    public final String b;
    /* access modifiers changed from: private */
    public com.facebook.ads.internal.view.i.a c;
    private final Paint d = new Paint();
    private final RectF e;

    public a(Context context, w wVar, String str, String str2, int i, com.facebook.ads.internal.view.i.a aVar, final c cVar, final String str3) {
        super(context);
        this.a = wVar;
        this.b = str;
        this.c = aVar;
        TextView textView = new TextView(context);
        textView.setTextColor(-1);
        textView.setTextSize(16.0f);
        textView.setText(str2);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        setGravity(17);
        addView(textView);
        this.d.setStyle(Style.FILL);
        this.d.setColor(i);
        this.e = new RectF();
        x.a((View) this, 0);
        setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str;
                String str2;
                try {
                    Uri parse = Uri.parse(a.this.b);
                    a.this.c.getEventBus().a(new b(parse));
                    HashMap hashMap = new HashMap();
                    hashMap.put("touch", k.a(a.this.a.e()));
                    com.facebook.ads.internal.a.b a2 = com.facebook.ads.internal.a.c.a(a.this.getContext(), cVar, str3, parse, hashMap);
                    if (a2 != null) {
                        a2.a();
                    }
                } catch (ActivityNotFoundException e) {
                    e = e;
                    str2 = String.valueOf(a.class);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error while opening ");
                    sb.append(a.this.b);
                    str = sb.toString();
                    Log.e(str2, str, e);
                } catch (Exception e2) {
                    e = e2;
                    str2 = String.valueOf(a.class);
                    str = "Error executing action";
                    Log.e(str2, str, e);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.e.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) getWidth(), (float) getHeight());
        float f2 = f * 10.0f;
        canvas.drawRoundRect(this.e, f2, f2, this.d);
        super.onDraw(canvas);
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.a.a(motionEvent, getRootView(), this);
        return super.onInterceptTouchEvent(motionEvent);
    }
}
