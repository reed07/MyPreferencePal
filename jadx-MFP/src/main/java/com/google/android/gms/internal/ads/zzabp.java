package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzabp extends RelativeLayout {
    private static final float[] zzdao = {5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f};
    @Nullable
    private AnimationDrawable zzdap;

    public zzabp(Context context, zzabm zzabm, LayoutParams layoutParams) {
        super(context);
        Preconditions.checkNotNull(zzabm);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(zzdao, null, null));
        shapeDrawable.getPaint().setColor(zzabm.getBackgroundColor());
        setLayoutParams(layoutParams);
        zzbv.zzlh().setBackground(this, shapeDrawable);
        LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        if (!TextUtils.isEmpty(zzabm.getText())) {
            LayoutParams layoutParams3 = new LayoutParams(-2, -2);
            TextView textView = new TextView(context);
            textView.setLayoutParams(layoutParams3);
            textView.setId(1195835393);
            textView.setTypeface(Typeface.DEFAULT);
            textView.setText(zzabm.getText());
            textView.setTextColor(zzabm.getTextColor());
            textView.setTextSize((float) zzabm.getTextSize());
            zzwu.zzpv();
            int zza = zzbat.zza(context, 4);
            zzwu.zzpv();
            textView.setPadding(zza, 0, zzbat.zza(context, 4), 0);
            addView(textView);
            layoutParams2.addRule(1, textView.getId());
        }
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams2);
        imageView.setId(1195835394);
        List<zzabr> zzrp = zzabm.zzrp();
        if (zzrp != null && zzrp.size() > 1) {
            this.zzdap = new AnimationDrawable();
            for (zzabr zzsa : zzrp) {
                try {
                    this.zzdap.addFrame((Drawable) ObjectWrapper.unwrap(zzsa.zzsa()), zzabm.zzrq());
                } catch (Exception e) {
                    zzaxz.zzb("Error while getting drawable.", e);
                }
            }
            zzbv.zzlh().setBackground(imageView, this.zzdap);
        } else if (zzrp.size() == 1) {
            try {
                imageView.setImageDrawable((Drawable) ObjectWrapper.unwrap(((zzabr) zzrp.get(0)).zzsa()));
            } catch (Exception e2) {
                zzaxz.zzb("Error while getting drawable.", e2);
            }
        }
        addView(imageView);
    }

    public final void onAttachedToWindow() {
        AnimationDrawable animationDrawable = this.zzdap;
        if (animationDrawable != null) {
            animationDrawable.start();
        }
        super.onAttachedToWindow();
    }
}
