package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzark
@ParametersAreNonnullByDefault
public final class zzabu extends zzadu implements zzacg {
    private Bundle mExtras;
    private Object mLock = new Object();
    private String zzdaw;
    private List<zzabr> zzdax;
    private String zzday;
    private String zzdba;
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
    private zzadb zzdbl;
    private String zzdbm;

    public zzabu(String str, List<zzabr> list, String str2, zzadb zzadb, String str3, String str4, @Nullable zzabm zzabm, Bundle bundle, zzyp zzyp, View view, IObjectWrapper iObjectWrapper, String str5) {
        this.zzdaw = str;
        this.zzdax = list;
        this.zzday = str2;
        this.zzdbl = zzadb;
        this.zzdba = str3;
        this.zzdbm = str4;
        this.zzdbe = zzabm;
        this.mExtras = bundle;
        this.zzdbf = zzyp;
        this.zzdbg = view;
        this.zzdbh = iObjectWrapper;
        this.zzdbi = str5;
    }

    public final String getCustomTemplateId() {
        return "";
    }

    public final String zzrv() {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES;
    }

    public final String getHeadline() {
        return this.zzdaw;
    }

    public final IObjectWrapper zzsd() {
        return this.zzdbh;
    }

    @Nullable
    public final String getMediationAdapterClassName() {
        return this.zzdbi;
    }

    public final List getImages() {
        return this.zzdax;
    }

    public final String getBody() {
        return this.zzday;
    }

    public final zzadb zzsf() {
        return this.zzdbl;
    }

    public final String getCallToAction() {
        return this.zzdba;
    }

    public final String getAdvertiser() {
        return this.zzdbm;
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

    public final zzacx zzse() {
        return this.zzdbe;
    }

    public final void destroy() {
        zzayh.zzelc.post(new zzabv(this));
        this.zzdaw = null;
        this.zzdax = null;
        this.zzday = null;
        this.zzdbl = null;
        this.zzdba = null;
        this.zzdbm = null;
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
