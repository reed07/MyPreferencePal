package com.facebook.ads.internal.view.e;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class b extends RelativeLayout {
    private final Paint a = new Paint();
    private final RectF b;

    public b(Context context, String str) {
        super(context);
        float f = context.getResources().getDisplayMetrics().density;
        TextView textView = new TextView(context);
        textView.setTextColor(-16777216);
        textView.setTextSize(16.0f);
        textView.setText(str);
        textView.setTypeface(Typeface.defaultFromStyle(1));
        setGravity(17);
        int i = (int) (f * 6.0f);
        textView.setPadding(i, i, i, i);
        addView(textView);
        this.a.setStyle(Style.FILL);
        this.a.setColor(-1);
        this.b = new RectF();
        x.a((View) this, 0);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        float f = getContext().getResources().getDisplayMetrics().density;
        this.b.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) getWidth(), (float) getHeight());
        float f2 = f * 10.0f;
        canvas.drawRoundRect(this.b, f2, f2, this.a);
        super.onDraw(canvas);
    }
}
