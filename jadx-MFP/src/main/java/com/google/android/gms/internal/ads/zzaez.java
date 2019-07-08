package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.MuteThisAdListener;
import com.google.android.gms.ads.MuteThisAdReason;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd.AdChoicesInfo;
import com.google.android.gms.ads.formats.NativeAd.Image;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd.UnconfirmedClickListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzark
public final class zzaez extends UnifiedNativeAd {
    private final zzaew zzdeb;
    private final List<Image> zzdec = new ArrayList();
    private final zzade zzded;
    private final VideoController zzdee = new VideoController();
    private final AdChoicesInfo zzdef;
    private final List<MuteThisAdReason> zzdeg = new ArrayList();

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0051 A[Catch:{ RemoteException -> 0x005c }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0027 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzaez(com.google.android.gms.internal.ads.zzaew r5) {
        /*
            r4 = this;
            r4.<init>()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4.zzdec = r0
            com.google.android.gms.ads.VideoController r0 = new com.google.android.gms.ads.VideoController
            r0.<init>()
            r4.zzdee = r0
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r4.zzdeg = r0
            r4.zzdeb = r5
            r5 = 0
            com.google.android.gms.internal.ads.zzaew r0 = r4.zzdeb     // Catch:{ RemoteException -> 0x005c }
            java.util.List r0 = r0.getImages()     // Catch:{ RemoteException -> 0x005c }
            if (r0 == 0) goto L_0x0062
            java.util.Iterator r0 = r0.iterator()     // Catch:{ RemoteException -> 0x005c }
        L_0x0027:
            boolean r1 = r0.hasNext()     // Catch:{ RemoteException -> 0x005c }
            if (r1 == 0) goto L_0x0062
            java.lang.Object r1 = r0.next()     // Catch:{ RemoteException -> 0x005c }
            boolean r2 = r1 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x005c }
            if (r2 == 0) goto L_0x004e
            android.os.IBinder r1 = (android.os.IBinder) r1     // Catch:{ RemoteException -> 0x005c }
            if (r1 == 0) goto L_0x004e
            java.lang.String r2 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r2 = r1.queryLocalInterface(r2)     // Catch:{ RemoteException -> 0x005c }
            boolean r3 = r2 instanceof com.google.android.gms.internal.ads.zzadb     // Catch:{ RemoteException -> 0x005c }
            if (r3 == 0) goto L_0x0047
            r1 = r2
            com.google.android.gms.internal.ads.zzadb r1 = (com.google.android.gms.internal.ads.zzadb) r1     // Catch:{ RemoteException -> 0x005c }
            goto L_0x004f
        L_0x0047:
            com.google.android.gms.internal.ads.zzadd r2 = new com.google.android.gms.internal.ads.zzadd     // Catch:{ RemoteException -> 0x005c }
            r2.<init>(r1)     // Catch:{ RemoteException -> 0x005c }
            r1 = r2
            goto L_0x004f
        L_0x004e:
            r1 = r5
        L_0x004f:
            if (r1 == 0) goto L_0x0027
            java.util.List<com.google.android.gms.ads.formats.NativeAd$Image> r2 = r4.zzdec     // Catch:{ RemoteException -> 0x005c }
            com.google.android.gms.internal.ads.zzade r3 = new com.google.android.gms.internal.ads.zzade     // Catch:{ RemoteException -> 0x005c }
            r3.<init>(r1)     // Catch:{ RemoteException -> 0x005c }
            r2.add(r3)     // Catch:{ RemoteException -> 0x005c }
            goto L_0x0027
        L_0x005c:
            r0 = move-exception
            java.lang.String r1 = ""
            com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0)
        L_0x0062:
            com.google.android.gms.internal.ads.zzaew r0 = r4.zzdeb     // Catch:{ RemoteException -> 0x0091 }
            java.util.List r0 = r0.getMuteThisAdReasons()     // Catch:{ RemoteException -> 0x0091 }
            if (r0 == 0) goto L_0x0097
            java.util.Iterator r0 = r0.iterator()     // Catch:{ RemoteException -> 0x0091 }
        L_0x006e:
            boolean r1 = r0.hasNext()     // Catch:{ RemoteException -> 0x0091 }
            if (r1 == 0) goto L_0x0097
            java.lang.Object r1 = r0.next()     // Catch:{ RemoteException -> 0x0091 }
            boolean r2 = r1 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x0091 }
            if (r2 == 0) goto L_0x0083
            android.os.IBinder r1 = (android.os.IBinder) r1     // Catch:{ RemoteException -> 0x0091 }
            com.google.android.gms.internal.ads.zzyl r1 = com.google.android.gms.internal.ads.zzym.zzf(r1)     // Catch:{ RemoteException -> 0x0091 }
            goto L_0x0084
        L_0x0083:
            r1 = r5
        L_0x0084:
            if (r1 == 0) goto L_0x006e
            java.util.List<com.google.android.gms.ads.MuteThisAdReason> r2 = r4.zzdeg     // Catch:{ RemoteException -> 0x0091 }
            com.google.android.gms.internal.ads.zzyo r3 = new com.google.android.gms.internal.ads.zzyo     // Catch:{ RemoteException -> 0x0091 }
            r3.<init>(r1)     // Catch:{ RemoteException -> 0x0091 }
            r2.add(r3)     // Catch:{ RemoteException -> 0x0091 }
            goto L_0x006e
        L_0x0091:
            r0 = move-exception
            java.lang.String r1 = ""
            com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0)
        L_0x0097:
            com.google.android.gms.internal.ads.zzaew r0 = r4.zzdeb     // Catch:{ RemoteException -> 0x00a7 }
            com.google.android.gms.internal.ads.zzadb r0 = r0.zzsb()     // Catch:{ RemoteException -> 0x00a7 }
            if (r0 == 0) goto L_0x00a5
            com.google.android.gms.internal.ads.zzade r1 = new com.google.android.gms.internal.ads.zzade     // Catch:{ RemoteException -> 0x00a7 }
            r1.<init>(r0)     // Catch:{ RemoteException -> 0x00a7 }
            goto L_0x00ae
        L_0x00a5:
            r1 = r5
            goto L_0x00ae
        L_0x00a7:
            r0 = move-exception
            java.lang.String r1 = ""
            com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0)
            r1 = r5
        L_0x00ae:
            r4.zzded = r1
            com.google.android.gms.internal.ads.zzaew r0 = r4.zzdeb     // Catch:{ RemoteException -> 0x00c5 }
            com.google.android.gms.internal.ads.zzacx r0 = r0.zzse()     // Catch:{ RemoteException -> 0x00c5 }
            if (r0 == 0) goto L_0x00cb
            com.google.android.gms.internal.ads.zzada r0 = new com.google.android.gms.internal.ads.zzada     // Catch:{ RemoteException -> 0x00c5 }
            com.google.android.gms.internal.ads.zzaew r1 = r4.zzdeb     // Catch:{ RemoteException -> 0x00c5 }
            com.google.android.gms.internal.ads.zzacx r1 = r1.zzse()     // Catch:{ RemoteException -> 0x00c5 }
            r0.<init>(r1)     // Catch:{ RemoteException -> 0x00c5 }
            r5 = r0
            goto L_0x00cb
        L_0x00c5:
            r0 = move-exception
            java.lang.String r1 = ""
            com.google.android.gms.internal.ads.zzbbd.zzb(r1, r0)
        L_0x00cb:
            r4.zzdef = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaez.<init>(com.google.android.gms.internal.ads.zzaew):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: zzsc */
    public final IObjectWrapper zzhy() {
        try {
            return this.zzdeb.zzsc();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final Object zzic() {
        try {
            IObjectWrapper zzsd = this.zzdeb.zzsd();
            if (zzsd != null) {
                return ObjectWrapper.unwrap(zzsd);
            }
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
        return null;
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzdeb.performClick(bundle);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzdeb.recordImpression(bundle);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzdeb.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final String getHeadline() {
        try {
            return this.zzdeb.getHeadline();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final List<Image> getImages() {
        return this.zzdec;
    }

    public final String getBody() {
        try {
            return this.zzdeb.getBody();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final Image getIcon() {
        return this.zzded;
    }

    public final String getCallToAction() {
        try {
            return this.zzdeb.getCallToAction();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final Double getStarRating() {
        try {
            double starRating = this.zzdeb.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final String getStore() {
        try {
            return this.zzdeb.getStore();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final String getPrice() {
        try {
            return this.zzdeb.getPrice();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzdeb.getVideoController() != null) {
                this.zzdee.zza(this.zzdeb.getVideoController());
            }
        } catch (RemoteException e) {
            zzbbd.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzdee;
    }

    public final AdChoicesInfo getAdChoicesInfo() {
        return this.zzdef;
    }

    public final String getMediationAdapterClassName() {
        try {
            return this.zzdeb.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            Bundle extras = this.zzdeb.getExtras();
            if (extras != null) {
                return extras;
            }
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
        return new Bundle();
    }

    public final void enableCustomClickGesture() {
        try {
            this.zzdeb.zzsi();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final void recordCustomClickGesture() {
        try {
            this.zzdeb.recordCustomClickGesture();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final List<MuteThisAdReason> getMuteThisAdReasons() {
        return this.zzdeg;
    }

    public final boolean isCustomMuteThisAdEnabled() {
        try {
            return this.zzdeb.isCustomMuteThisAdEnabled();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return false;
        }
    }

    public final void destroy() {
        try {
            this.zzdeb.destroy();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final void setUnconfirmedClickListener(UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zzdeb.zza((zzaet) new zzafj(unconfirmedClickListener));
        } catch (RemoteException e) {
            zzbbd.zzb("Failed to setUnconfirmedClickListener", e);
        }
    }

    public final void muteThisAd(MuteThisAdReason muteThisAdReason) {
        try {
            if (!isCustomMuteThisAdEnabled()) {
                zzbbd.e("Ad is not custom mute enabled");
            } else if (muteThisAdReason == null) {
                this.zzdeb.zza((zzyl) null);
            } else if (muteThisAdReason instanceof zzyo) {
                this.zzdeb.zza(((zzyo) muteThisAdReason).zzqe());
            } else {
                zzbbd.e("Use mute reason from UnifiedNativeAd.getMuteThisAdReasons() or null");
            }
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final void setMuteThisAdListener(MuteThisAdListener muteThisAdListener) {
        try {
            this.zzdeb.zza((zzyh) new zzyk(muteThisAdListener));
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
        }
    }

    public final void cancelUnconfirmedClick() {
        try {
            this.zzdeb.cancelUnconfirmedClick();
        } catch (RemoteException e) {
            zzbbd.zzb("Failed to cancelUnconfirmedClick", e);
        }
    }

    public final String getAdvertiser() {
        try {
            return this.zzdeb.getAdvertiser();
        } catch (RemoteException e) {
            zzbbd.zzb("", e);
            return null;
        }
    }
}
