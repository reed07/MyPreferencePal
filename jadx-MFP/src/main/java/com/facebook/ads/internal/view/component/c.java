package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.support.v4.graphics.ColorUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.b.h;
import com.facebook.ads.internal.w.b.x;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.Locale;

public class c extends Button {
    public static final int a = ((int) (x.b * 16.0f));
    private static final int b = ((int) (x.b * 4.0f));
    private final Paint c;
    private final RectF d;
    private final boolean e;

    public c(Context context, boolean z, boolean z2, h hVar) {
        int i;
        int i2;
        super(context);
        this.e = z;
        x.a((TextView) this, false, 16);
        setGravity(17);
        int i3 = a;
        setPadding(i3, i3, i3, i3);
        if (hVar != null) {
            setTextColor(hVar.f(z2));
            i2 = hVar.e(z2);
            i = ColorUtils.blendARGB(i2, -16777216, 0.1f);
        } else {
            setBackgroundColor(0);
            setTextColor(0);
            i2 = 0;
            i = 0;
        }
        this.c = new Paint();
        setButtonColor(i2);
        this.d = new RectF();
        if (!z) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            stateListDrawable.addState(new int[]{16842919}, new ColorDrawable(i));
            stateListDrawable.addState(new int[0], new ColorDrawable(i2));
            x.a((View) this, (Drawable) stateListDrawable);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.e) {
            this.d.set(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, (float) getWidth(), (float) getHeight());
            RectF rectF = this.d;
            int i = b;
            canvas.drawRoundRect(rectF, (float) i, (float) i, this.c);
        }
        super.onDraw(canvas);
    }

    public void setButtonColor(int i) {
        this.c.setStyle(Style.FILL);
        this.c.setColor(i);
    }

    public void setText(String str) {
        super.setText(str.toUpperCase(Locale.US));
    }
}
