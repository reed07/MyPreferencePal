package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzark
public final class zzaoj extends zzaok implements zzu<zzbgg> {
    private final Context mContext;
    private final WindowManager zzbuv;
    private DisplayMetrics zzbwk;
    private final zzbgg zzdin;
    private final zzzy zzdqd;
    private float zzdqe;
    private int zzdqf = -1;
    private int zzdqg = -1;
    private int zzdqh;
    private int zzdqi = -1;
    private int zzdqj = -1;
    private int zzdqk = -1;
    private int zzdql = -1;

    public zzaoj(zzbgg zzbgg, Context context, zzzy zzzy) {
        super(zzbgg);
        this.zzdin = zzbgg;
        this.mContext = context;
        this.zzdqd = zzzy;
        this.zzbuv = (WindowManager) context.getSystemService("window");
    }

    public final void zzj(int i, int i2) {
        int i3 = 0;
        if (this.mContext instanceof Activity) {
            i3 = zzbv.zzlf().zzi((Activity) this.mContext)[0];
        }
        if (this.zzdin.zzadj() == null || !this.zzdin.zzadj().zzafb()) {
            zzwu.zzpv();
            this.zzdqk = zzbat.zzb(this.mContext, this.zzdin.getWidth());
            zzwu.zzpv();
            this.zzdql = zzbat.zzb(this.mContext, this.zzdin.getHeight());
        }
        zzc(i, i2 - i3, this.zzdqk, this.zzdql);
        this.zzdin.zzadl().zzi(i, i2);
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        this.zzbwk = new DisplayMetrics();
        Display defaultDisplay = this.zzbuv.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zzbwk);
        this.zzdqe = this.zzbwk.density;
        this.zzdqh = defaultDisplay.getRotation();
        zzwu.zzpv();
        DisplayMetrics displayMetrics = this.zzbwk;
        this.zzdqf = zzbat.zzb(displayMetrics, displayMetrics.widthPixels);
        zzwu.zzpv();
        DisplayMetrics displayMetrics2 = this.zzbwk;
        this.zzdqg = zzbat.zzb(displayMetrics2, displayMetrics2.heightPixels);
        Activity zzabw = this.zzdin.zzabw();
        if (zzabw == null || zzabw.getWindow() == null) {
            this.zzdqi = this.zzdqf;
            this.zzdqj = this.zzdqg;
        } else {
            zzbv.zzlf();
            int[] zzg = zzayh.zzg(zzabw);
            zzwu.zzpv();
            this.zzdqi = zzbat.zzb(this.zzbwk, zzg[0]);
            zzwu.zzpv();
            this.zzdqj = zzbat.zzb(this.zzbwk, zzg[1]);
        }
        if (this.zzdin.zzadj().zzafb()) {
            this.zzdqk = this.zzdqf;
            this.zzdql = this.zzdqg;
        } else {
            this.zzdin.measure(0, 0);
        }
        zza(this.zzdqf, this.zzdqg, this.zzdqi, this.zzdqj, this.zzdqe, this.zzdqh);
        this.zzdin.zza("onDeviceFeaturesReceived", new zzaog(new zzaoi().zzz(this.zzdqd.zzqr()).zzy(this.zzdqd.zzqs()).zzaa(this.zzdqd.zzqu()).zzab(this.zzdqd.zzqt()).zzac(true)).zzvn());
        int[] iArr = new int[2];
        this.zzdin.getLocationOnScreen(iArr);
        zzwu.zzpv();
        int zzb = zzbat.zzb(this.mContext, iArr[0]);
        zzwu.zzpv();
        zzj(zzb, zzbat.zzb(this.mContext, iArr[1]));
        if (zzaxz.isLoggable(2)) {
            zzaxz.zzen("Dispatching Ready Event.");
        }
        zzdb(this.zzdin.zzabz().zzdp);
    }
}
