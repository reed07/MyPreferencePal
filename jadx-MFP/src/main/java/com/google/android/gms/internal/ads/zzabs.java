package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzabs extends zzadq implements zzacg {
    private Bundle mExtras;
    private Object mLock = new Object();
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

    public zzabs(String str, List<zzabr> list, String str2, zzadb zzadb, String str3, double d, String str4, String str5, @Nullable zzabm zzabm, Bundle bundle, zzyp zzyp, View view, IObjectWrapper iObjectWrapper, String str6) {
        this.zzdaw = str;
        this.zzdax = list;
        this.zzday = str2;
        this.zzdaz = zzadb;
        this.zzdba = str3;
        this.zzdbb = d;
        this.zzdbc = str4;
        this.zzdbd = str5;
        this.zzdbe = zzabm;
        this.mExtras = bundle;
        this.zzdbf = zzyp;
        this.zzdbg = view;
        this.zzdbh = iObjectWrapper;
        this.zzdbi = str6;
    }

    public final String getCustomTemplateId() {
        return "";
    }

    public final String zzrv() {
        return InternalAvidAdSessionContext.AVID_API_LEVEL;
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

    public final Bundle getExtras() {
        return this.mExtras;
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

    public final zzacx zzse() {
        return this.zzdbe;
    }

    public final void destroy() {
        zzayh.zzelc.post(new zzabt(this));
        this.zzdaw = null;
        this.zzdax = null;
        this.zzday = null;
        this.zzdaz = null;
        this.zzdba = null;
        this.zzdbb = 0.0d;
        this.zzdbc = null;
        this.zzdbd = null;
        this.zzdbe = null;
        this.mExtras = null;
        this.mLock = null;
        this.zzdbf = null;
        this.zzdbg = null;
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
}
