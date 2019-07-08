package com.google.android.gms.tagmanager;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.RawRes;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@VisibleForTesting
public class TagManager {
    private static TagManager zzbgs;
    private final DataLayer zzazr;
    private final zzal zzbet;
    private final zza zzbgp;
    private final zzfm zzbgq;
    private final ConcurrentMap<String, zzv> zzbgr;
    private final Context zzri;

    @VisibleForTesting
    public interface zza {
        zzy zza(Context context, TagManager tagManager, Looper looper, String str, int i, zzal zzal);
    }

    @VisibleForTesting
    private TagManager(Context context, zza zza2, DataLayer dataLayer, zzfm zzfm) {
        if (context != null) {
            this.zzri = context.getApplicationContext();
            this.zzbgq = zzfm;
            this.zzbgp = zza2;
            this.zzbgr = new ConcurrentHashMap();
            this.zzazr = dataLayer;
            this.zzazr.zza((zzb) new zzga(this));
            this.zzazr.zza((zzb) new zzg(this.zzri));
            this.zzbet = new zzal();
            this.zzri.registerComponentCallbacks(new zzgc(this));
            zza.zzo(this.zzri);
            return;
        }
        throw new NullPointerException("context cannot be null");
    }

    @RequiresPermission
    public static TagManager getInstance(Context context) {
        TagManager tagManager;
        synchronized (TagManager.class) {
            if (zzbgs == null) {
                if (context != null) {
                    zzbgs = new TagManager(context, new zzgb(), new DataLayer(new zzat(context)), zzfn.zzqe());
                } else {
                    zzdi.e("TagManager.getInstance requires non-null context.");
                    throw new NullPointerException();
                }
            }
            tagManager = zzbgs;
        }
        return tagManager;
    }

    public DataLayer getDataLayer() {
        return this.zzazr;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i) {
        zzy zza2 = this.zzbgp.zza(this.zzri, this, null, str, i, this.zzbet);
        zza2.zznt();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerDefaultOnly(String str, @RawRes int i, Handler handler) {
        zzy zza2 = this.zzbgp.zza(this.zzri, this, handler.getLooper(), str, i, this.zzbet);
        zza2.zznt();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i) {
        zzy zza2 = this.zzbgp.zza(this.zzri, this, null, str, i, this.zzbet);
        zza2.zznu();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferNonDefault(String str, @RawRes int i, Handler handler) {
        zzy zza2 = this.zzbgp.zza(this.zzri, this, handler.getLooper(), str, i, this.zzbet);
        zza2.zznu();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i) {
        zzy zza2 = this.zzbgp.zza(this.zzri, this, null, str, i, this.zzbet);
        zza2.zznv();
        return zza2;
    }

    public PendingResult<ContainerHolder> loadContainerPreferFresh(String str, @RawRes int i, Handler handler) {
        zzy zza2 = this.zzbgp.zza(this.zzri, this, handler.getLooper(), str, i, this.zzbet);
        zza2.zznv();
        return zza2;
    }

    public void dispatch() {
        this.zzbgq.dispatch();
    }

    public void setVerboseLoggingEnabled(boolean z) {
        zzdi.setLogLevel(z ? 2 : 5);
    }

    /* access modifiers changed from: 0000 */
    public final synchronized boolean zzb(Uri uri) {
        zzeh zzpm = zzeh.zzpm();
        if (!zzpm.zzb(uri)) {
            return false;
        }
        String containerId = zzpm.getContainerId();
        switch (zzgd.zzbgu[zzpm.zzpn().ordinal()]) {
            case 1:
                zzv zzv = (zzv) this.zzbgr.get(containerId);
                if (zzv != null) {
                    zzv.zzdf(null);
                    zzv.refresh();
                    break;
                }
                break;
            case 2:
            case 3:
                for (String str : this.zzbgr.keySet()) {
                    zzv zzv2 = (zzv) this.zzbgr.get(str);
                    if (str.equals(containerId)) {
                        zzv2.zzdf(zzpm.zzpo());
                        zzv2.refresh();
                    } else if (zzv2.zznq() != null) {
                        zzv2.zzdf(null);
                        zzv2.refresh();
                    }
                }
                break;
        }
        return true;
    }

    @VisibleForTesting
    public final int zza(zzv zzv) {
        this.zzbgr.put(zzv.getContainerId(), zzv);
        return this.zzbgr.size();
    }

    @VisibleForTesting
    public final boolean zzb(zzv zzv) {
        return this.zzbgr.remove(zzv.getContainerId()) != null;
    }

    /* access modifiers changed from: private */
    public final void zzeb(String str) {
        for (zzv zzde : this.zzbgr.values()) {
            zzde.zzde(str);
        }
    }
}
