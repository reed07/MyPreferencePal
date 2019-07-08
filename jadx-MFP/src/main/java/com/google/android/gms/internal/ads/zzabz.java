package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzabz extends zzaex implements zzacg {
    private Bundle mExtras;
    private final Object mLock = new Object();
    private String zzdaw;
    private List<zzabr> zzdax;
    private String zzday;
    private zzadb zzdaz;
    private String zzdba;
    private double zzdbb;
    private String zzdbc;
    private String zzdbd;
    @Nullable
    private zzabm zzdbe;
    @Nullable
    private zzyp zzdbf;
    @Nullable
    private View zzdbg;
    @Nullable
    private IObjectWrapper zzdbh;
    @Nullable
    private String zzdbi;
    /* access modifiers changed from: private */
    public zzacd zzdbj;
    private String zzdbm;
    private List<Object> zzdbs;
    private boolean zzdbt;

    public zzabz(String str, List<zzabr> list, String str2, zzadb zzadb, String str3, String str4, double d, String str5, String str6, @Nullable zzabm zzabm, zzyp zzyp, View view, IObjectWrapper iObjectWrapper, String str7, Bundle bundle) {
        this.zzdaw = str;
        this.zzdax = list;
        this.zzdbs = null;
        this.zzdbt = false;
        this.zzday = str2;
        this.zzdaz = zzadb;
        this.zzdba = str3;
        this.zzdbm = str4;
        this.zzdbb = d;
        this.zzdbc = str5;
        this.zzdbd = str6;
        this.zzdbe = zzabm;
        this.zzdbf = zzyp;
        this.zzdbg = view;
        this.zzdbh = iObjectWrapper;
        this.zzdbi = str7;
        this.mExtras = bundle;
    }

    public final String getCustomTemplateId() {
        return "";
    }

    public final List getMuteThisAdReasons() {
        return null;
    }

    public final boolean isCustomMuteThisAdEnabled() {
        return false;
    }

    public final void zza(zzyh zzyh) {
    }

    public final void zza(zzyl zzyl) {
    }

    public final String zzrv() {
        return "6";
    }

    public final String getHeadline() {
        return this.zzdaw;
    }

    public final List getImages() {
        return this.zzdax;
    }

    public final String getBody() {
        return this.zzday;
    }

    public final zzadb zzsb() {
        return this.zzdaz;
    }

    public final String getCallToAction() {
        return this.zzdba;
    }

    public final String getAdvertiser() {
        return this.zzdbm;
    }

    public final double getStarRating() {
        return this.zzdbb;
    }

    public final String getStore() {
        return this.zzdbc;
    }

    public final String getPrice() {
        return this.zzdbd;
    }

    public final zzyp getVideoController() {
        return this.zzdbf;
    }

    public final IObjectWrapper zzsc() {
        return ObjectWrapper.wrap(this.zzdbj);
    }

    public final void zzb(zzacd zzacd) {
        synchronized (this.mLock) {
            this.zzdbj = zzacd;
        }
    }

    public final zzabm zzrw() {
        return this.zzdbe;
    }

    public final View zzrx() {
        return this.zzdbg;
    }

    public final IObjectWrapper zzsd() {
        return this.zzdbh;
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        return this.zzdbi;
    }

    public final Bundle getExtras() {
        return this.mExtras;
    }

    public final zzacx zzse() {
        return this.zzdbe;
    }

    public final void destroy() {
        zzayh.zzelc.post(new zzaca(this));
    }

    public final void performClick(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzdbj == null) {
                zzaxz.e("#001 Attempt to perform click before app native ad initialized.");
            } else {
                this.zzdbj.performClick(bundle);
            }
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzdbj == null) {
                zzaxz.e("#002 Attempt to record impression before native ad initialized.");
                return false;
            }
            boolean recordImpression = this.zzdbj.recordImpression(bundle);
            return recordImpression;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        synchronized (this.mLock) {
            if (this.zzdbj == null) {
                zzaxz.e("#003 Attempt to report touch event before native ad initialized.");
            } else {
                this.zzdbj.reportTouchEvent(bundle);
            }
        }
    }

    public final void zza(zzaet zzaet) {
        this.zzdbj.zza(zzaet);
    }

    public final void zzsi() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                this.zzdbj.zzsi();
            }
        }
    }

    public final void recordCustomClickGesture() {
        synchronized (this.mLock) {
            if (this.zzdbj != null) {
                this.zzdbj.recordCustomClickGesture();
            }
        }
    }

    public final void cancelUnconfirmedClick() {
        this.zzdbj.cancelUnconfirmedClick();
    }
}
