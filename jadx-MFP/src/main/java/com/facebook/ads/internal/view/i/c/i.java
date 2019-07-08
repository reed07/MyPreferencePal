package com.facebook.ads.internal.view.i.c;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a.c;
import com.facebook.ads.internal.view.i.b.o;
import com.facebook.ads.internal.w.b.x;
import java.util.concurrent.atomic.AtomicBoolean;

public class i extends c {
    /* access modifiers changed from: private */
    public final a a;
    /* access modifiers changed from: private */
    public final int b;
    /* access modifiers changed from: private */
    public final String c;
    /* access modifiers changed from: private */
    public final String d;
    /* access modifiers changed from: private */
    public final AtomicBoolean e;
    private final f<o> f = new f<o>() {
        public Class<o> a() {
            return o.class;
        }

        public void a(o oVar) {
            if (!i.this.e.get() && i.this.getVideoView() != null) {
                int c = i.this.b - (i.this.getVideoView().getCurrentPositionInMillis() / 1000);
                if (c > 0) {
                    a f = i.this.a;
                    StringBuilder sb = new StringBuilder();
                    sb.append(i.this.c);
                    sb.append(' ');
                    sb.append(c);
                    f.setText(sb.toString());
                    return;
                }
                i.this.a.setText(i.this.d);
                i.this.e.set(true);
            }
        }
    };

    private static class a extends TextView {
        private final Paint a = new Paint();
        private final Paint b;
        private final RectF c;

        public a(Context context) {
            super(context);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            x.a((View) this, 0);
            setTextColor(-3355444);
            setPadding((int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f), (int) (displayMetrics.density * 9.0f), (int) (displayMetrics.density * 5.0f));
            setTextSize(18.0f);
            this.a.setStyle(Style.STROKE);
            this.a.setColor(-10066330);
            this.a.setStrokeWidth(1.0f);
            this.a.setAntiAlias(true);
            this.b = new Paint();
            this.b.setStyle(Style.FILL);
            this.b.setColor(-1895825408);
            this.c = new RectF();
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            if (getText().length() != 0) {
                int width = getWidth();
                int height = getHeight();
                float f = (float) 0;
                this.c.set(f, f, (float) width, (float) height);
                canvas.drawRoundRect(this.c, 6.0f, 6.0f, this.b);
                float f2 = (float) 2;
                this.c.set(f2, f2, (float) (width - 2), (float) (height - 2));
                canvas.drawRoundRect(this.c, 6.0f, 6.0f, this.a);
                super.onDraw(canvas);
            }
        }
    }

    public i(Context context, int i, String str, String str2) {
        super(context);
        this.b = i;
        this.c = str;
        this.d = str2;
        this.e = new AtomicBoolean(false);
        this.a = new a(context);
        a aVar = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(this.c);
        sb.append(' ');
        sb.append(i);
        aVar.setText(sb.toString());
        addView(this.a, new LayoutParams(-2, -2));
    }

    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.f);
        }
        this.a.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (!i.this.e.get()) {
                    Log.i("SkipPlugin", "User clicked skip before the ads is allowed to skip.");
                } else if (i.this.getVideoView() != null) {
                    i.this.getVideoView().f();
                }
            }
        });
    }

    public void b() {
        if (getVideoView() != null) {
            this.a.setOnClickListener(null);
            getVideoView().getEventBus().b(this.f);
        }
        super.b();
    }
}
