package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.facebook.ads.internal.w.b.j;
import com.facebook.ads.internal.w.b.x;
import com.facebook.ads.internal.w.c.b;
import com.facebook.ads.internal.w.c.c;

public class AdOptionsView extends LinearLayout {
    private static final int a = ((int) (x.b * 23.0f));
    private static final int b = ((int) (x.b * 4.0f));
    private final ImageView c;
    private final ImageView d;

    public enum Orientation {
        HORIZONTAL,
        VERTICAL
    }

    public AdOptionsView(Context context, NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout) {
        this(context, nativeAdBase, nativeAdLayout, Orientation.HORIZONTAL, 23);
    }

    public AdOptionsView(Context context, final NativeAdBase nativeAdBase, @Nullable NativeAdLayout nativeAdLayout, Orientation orientation, int i) {
        super(context);
        this.c = new ImageView(context);
        this.c.setScaleType(ScaleType.FIT_CENTER);
        ImageView imageView = this.c;
        int i2 = b;
        imageView.setPadding(i2, i2, i2, i2);
        this.c.setImageBitmap(c.a(b.INFO_ICON));
        this.d = new ImageView(context);
        this.d.setScaleType(ScaleType.FIT_CENTER);
        ImageView imageView2 = this.d;
        int i3 = b;
        imageView2.setPadding(i3, i3, i3, i3);
        this.d.setImageBitmap(c.a(b.AD_CHOICES_ICON));
        setOrientation(orientation == Orientation.HORIZONTAL ? 0 : 1);
        setIconColor(-10459280);
        int max = Math.max(a, (int) (x.b * ((float) i)));
        LayoutParams layoutParams = new LayoutParams(max, max);
        addView(this.c, layoutParams);
        addView(this.d, layoutParams);
        nativeAdBase.f().a(nativeAdLayout);
        if (!nativeAdBase.isAdLoaded() || nativeAdBase.g().g()) {
            setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    nativeAdBase.f().y();
                }
            });
            j.a(this, j.INTERNAL_AD_OPTIONS_VIEW);
            return;
        }
        setVisibility(8);
    }

    public void setIconColor(int i) {
        this.c.setColorFilter(i);
        this.d.setColorFilter(i);
    }

    public void setLayoutParams(ViewGroup.LayoutParams layoutParams) {
        layoutParams.width = -2;
        layoutParams.height = -2;
        super.setLayoutParams(layoutParams);
    }
}
