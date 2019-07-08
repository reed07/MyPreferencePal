package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzbat;
import com.google.android.gms.internal.ads.zzwu;
import javax.annotation.Nullable;

@zzark
public final class zzo extends FrameLayout implements OnClickListener {
    private final ImageButton zzdse;
    private final zzw zzdsf;

    public zzo(Context context, zzp zzp, @Nullable zzw zzw) {
        super(context);
        this.zzdsf = zzw;
        setOnClickListener(this);
        this.zzdse = new ImageButton(context);
        this.zzdse.setImageResource(17301527);
        this.zzdse.setBackgroundColor(0);
        this.zzdse.setOnClickListener(this);
        ImageButton imageButton = this.zzdse;
        zzwu.zzpv();
        int zza = zzbat.zza(context, zzp.paddingLeft);
        zzwu.zzpv();
        int zza2 = zzbat.zza(context, 0);
        zzwu.zzpv();
        int zza3 = zzbat.zza(context, zzp.paddingRight);
        zzwu.zzpv();
        imageButton.setPadding(zza, zza2, zza3, zzbat.zza(context, zzp.paddingBottom));
        this.zzdse.setContentDescription("Interstitial close button");
        zzwu.zzpv();
        zzbat.zza(context, zzp.size);
        ImageButton imageButton2 = this.zzdse;
        zzwu.zzpv();
        int zza4 = zzbat.zza(context, zzp.size + zzp.paddingLeft + zzp.paddingRight);
        zzwu.zzpv();
        addView(imageButton2, new LayoutParams(zza4, zzbat.zza(context, zzp.size + zzp.paddingBottom), 17));
    }

    public final void onClick(View view) {
        zzw zzw = this.zzdsf;
        if (zzw != null) {
            zzw.zzvp();
        }
    }

    public final void zzaf(boolean z) {
        if (z) {
            this.zzdse.setVisibility(8);
        } else {
            this.zzdse.setVisibility(0);
        }
    }
}
