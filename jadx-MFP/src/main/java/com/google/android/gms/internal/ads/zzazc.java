package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.WindowManager.BadTokenException;
import com.google.ads.AdRequest;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import java.util.ArrayList;
import java.util.List;

@zzark
public final class zzazc {
    private Handler handler;
    private final Context mContext;
    private int state;
    private String zzboa;
    private String zzbuk;
    private final float zzdqe;
    @Nullable
    private String zzebe;
    private String zzelo;
    private float zzelp;
    private float zzelq;
    private float zzelr;
    private int zzels;
    private float zzelt;
    private float zzelu;
    private float zzelv;
    private float zzelw;
    private Runnable zzelx;

    public zzazc(Context context) {
        this.state = 0;
        this.zzelx = new zzazd(this);
        this.mContext = context;
        this.zzdqe = context.getResources().getDisplayMetrics().density;
        this.zzels = ViewConfiguration.get(this.mContext).getScaledTouchSlop();
        zzbv.zzlv().zzaak();
        this.handler = zzbv.zzlv().getHandler();
    }

    public zzazc(Context context, String str) {
        this(context);
        this.zzelo = str;
    }

    public final void zze(MotionEvent motionEvent) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcwd)).booleanValue()) {
            int actionMasked = motionEvent.getActionMasked();
            int historySize = motionEvent.getHistorySize();
            int pointerCount = motionEvent.getPointerCount();
            if (actionMasked == 0) {
                this.state = 0;
                this.zzelt = motionEvent.getX();
                this.zzelu = motionEvent.getY();
                return;
            }
            int i = this.state;
            if (i != -1) {
                boolean z = true;
                if (i == 0 && actionMasked == 5) {
                    this.state = 5;
                    this.zzelv = motionEvent.getX(1);
                    this.zzelw = motionEvent.getY(1);
                    this.handler.postDelayed(this.zzelx, ((Long) zzwu.zzpz().zzd(zzaan.zzcwe)).longValue());
                    return;
                } else if (this.state == 5) {
                    if (pointerCount == 2) {
                        if (actionMasked == 2) {
                            boolean z2 = false;
                            for (int i2 = 0; i2 < historySize; i2++) {
                                if (!zza(motionEvent.getHistoricalX(0, i2), motionEvent.getHistoricalY(0, i2), motionEvent.getHistoricalX(1, i2), motionEvent.getHistoricalY(1, i2))) {
                                    z2 = true;
                                }
                            }
                            if (zza(motionEvent.getX(), motionEvent.getY(), motionEvent.getX(1), motionEvent.getY(1))) {
                                z = z2;
                            }
                        } else {
                            z = false;
                        }
                    }
                    if (z) {
                        this.state = -1;
                        this.handler.removeCallbacks(this.zzelx);
                    }
                }
            }
            return;
        }
        int historySize2 = motionEvent.getHistorySize();
        for (int i3 = 0; i3 < historySize2; i3++) {
            zza(motionEvent.getActionMasked(), motionEvent.getHistoricalX(0, i3), motionEvent.getHistoricalY(0, i3));
        }
        zza(motionEvent.getActionMasked(), motionEvent.getX(), motionEvent.getY());
    }

    private final boolean zza(float f, float f2, float f3, float f4) {
        return Math.abs(this.zzelt - f) < ((float) this.zzels) && Math.abs(this.zzelu - f2) < ((float) this.zzels) && Math.abs(this.zzelv - f3) < ((float) this.zzels) && Math.abs(this.zzelw - f4) < ((float) this.zzels);
    }

    @VisibleForTesting
    private final void zza(int i, float f, float f2) {
        if (i == 0) {
            this.state = 0;
            this.zzelp = f;
            this.zzelq = f2;
            this.zzelr = f2;
            return;
        }
        int i2 = this.state;
        if (i2 != -1) {
            if (i == 2) {
                if (f2 > this.zzelq) {
                    this.zzelq = f2;
                } else if (f2 < this.zzelr) {
                    this.zzelr = f2;
                }
                if (this.zzelq - this.zzelr > this.zzdqe * 30.0f) {
                    this.state = -1;
                    return;
                }
                int i3 = this.state;
                if (i3 == 0 || i3 == 2) {
                    if (f - this.zzelp >= this.zzdqe * 50.0f) {
                        this.zzelp = f;
                        this.state++;
                    }
                } else if ((i3 == 1 || i3 == 3) && f - this.zzelp <= this.zzdqe * -50.0f) {
                    this.zzelp = f;
                    this.state++;
                }
                int i4 = this.state;
                if (i4 == 1 || i4 == 3) {
                    if (f > this.zzelp) {
                        this.zzelp = f;
                    }
                } else if (i4 == 2 && f < this.zzelp) {
                    this.zzelp = f;
                }
            } else if (i == 1 && i2 == 4) {
                showDialog();
            }
        }
    }

    public final void showDialog() {
        try {
            if (!(this.mContext instanceof Activity)) {
                zzaxz.zzen("Can not create dialog without Activity Context");
                return;
            }
            String str = !TextUtils.isEmpty(zzbv.zzlp().zzaag()) ? "Creative Preview (Enabled)" : "Creative Preview";
            String str2 = zzbv.zzlp().zzaah() ? "Troubleshooting (Enabled)" : Screens.TROUBLESHOOTING;
            ArrayList arrayList = new ArrayList();
            int zza = zza((List<String>) arrayList, "Ad Information", true);
            int zza2 = zza((List<String>) arrayList, str, true);
            int zza3 = zza((List<String>) arrayList, str2, true);
            Builder builder = new Builder(this.mContext, zzbv.zzlh().zzaab());
            builder.setTitle("Select a Debug Mode").setItems((CharSequence[]) arrayList.toArray(new String[0]), new zzaze(this, zza, zza2, zza3));
            builder.create().show();
        } catch (BadTokenException e) {
            String str3 = "";
            if (zzaxz.zzza()) {
                Log.v(AdRequest.LOGTAG, str3, e);
            }
        }
    }

    public final void setAdUnitId(String str) {
        this.zzboa = str;
    }

    public final void zzee(String str) {
        this.zzbuk = str;
    }

    public final void zzef(String str) {
        this.zzelo = str;
    }

    public final void zzeg(String str) {
        this.zzebe = str;
    }

    private static int zza(List<String> list, String str, boolean z) {
        list.add(str);
        return list.size() - 1;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzaad() {
        zzbv.zzlp().zza(this.mContext, this.zzboa, this.zzbuk, this.zzebe);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzaae() {
        zzbv.zzlp().zzf(this.mContext, this.zzboa, this.zzbuk);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(String str, DialogInterface dialogInterface, int i) {
        zzbv.zzlf();
        zzayh.zza(this.mContext, Intent.createChooser(new Intent("android.intent.action.SEND").setType("text/plain").putExtra("android.intent.extra.TEXT", str), "Share via"));
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x006e, code lost:
        if (android.text.TextUtils.isEmpty(r1) == false) goto L_0x0073;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ void zza(int r1, int r2, int r3, android.content.DialogInterface r4, int r5) {
        /*
            r0 = this;
            if (r5 != r1) goto L_0x009b
            android.content.Context r1 = r0.mContext
            boolean r1 = r1 instanceof android.app.Activity
            if (r1 != 0) goto L_0x000e
            java.lang.String r1 = "Can not create dialog without Activity Context"
            com.google.android.gms.internal.ads.zzaxz.zzen(r1)
            return
        L_0x000e:
            java.lang.String r1 = r0.zzelo
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0071
            java.lang.String r2 = "\\+"
            java.lang.String r3 = "%20"
            java.lang.String r1 = r1.replaceAll(r2, r3)
            android.net.Uri$Builder r2 = new android.net.Uri$Builder
            r2.<init>()
            android.net.Uri$Builder r1 = r2.encodedQuery(r1)
            android.net.Uri r1 = r1.build()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            com.google.android.gms.ads.internal.zzbv.zzlf()
            java.util.Map r1 = com.google.android.gms.internal.ads.zzayh.zzg(r1)
            java.util.Set r3 = r1.keySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x003f:
            boolean r4 = r3.hasNext()
            if (r4 == 0) goto L_0x0062
            java.lang.Object r4 = r3.next()
            java.lang.String r4 = (java.lang.String) r4
            r2.append(r4)
            java.lang.String r5 = " = "
            r2.append(r5)
            java.lang.Object r4 = r1.get(r4)
            java.lang.String r4 = (java.lang.String) r4
            r2.append(r4)
            java.lang.String r4 = "\n\n"
            r2.append(r4)
            goto L_0x003f
        L_0x0062:
            java.lang.String r1 = r2.toString()
            java.lang.String r1 = r1.trim()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0071
            goto L_0x0073
        L_0x0071:
            java.lang.String r1 = "No debug information"
        L_0x0073:
            android.app.AlertDialog$Builder r2 = new android.app.AlertDialog$Builder
            android.content.Context r3 = r0.mContext
            r2.<init>(r3)
            r2.setMessage(r1)
            java.lang.String r3 = "Ad Information"
            r2.setTitle(r3)
            java.lang.String r3 = "Share"
            com.google.android.gms.internal.ads.zzazf r4 = new com.google.android.gms.internal.ads.zzazf
            r4.<init>(r0, r1)
            r2.setPositiveButton(r3, r4)
            java.lang.String r1 = "Close"
            android.content.DialogInterface$OnClickListener r3 = com.google.android.gms.internal.ads.zzazg.zzemc
            r2.setNegativeButton(r1, r3)
            android.app.AlertDialog r1 = r2.create()
            r1.show()
            return
        L_0x009b:
            if (r5 != r2) goto L_0x00ab
            java.lang.String r1 = "Debug mode [Creative Preview] selected."
            com.google.android.gms.internal.ads.zzaxz.zzdn(r1)
            com.google.android.gms.internal.ads.zzazh r1 = new com.google.android.gms.internal.ads.zzazh
            r1.<init>(r0)
            com.google.android.gms.internal.ads.zzayf.zzc(r1)
            return
        L_0x00ab:
            if (r5 != r3) goto L_0x00ba
            java.lang.String r1 = "Debug mode [Troubleshooting] selected."
            com.google.android.gms.internal.ads.zzaxz.zzdn(r1)
            com.google.android.gms.internal.ads.zzazi r1 = new com.google.android.gms.internal.ads.zzazi
            r1.<init>(r0)
            com.google.android.gms.internal.ads.zzayf.zzc(r1)
        L_0x00ba:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzazc.zza(int, int, int, android.content.DialogInterface, int):void");
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzaaf() {
        this.state = 4;
        showDialog();
    }
}
