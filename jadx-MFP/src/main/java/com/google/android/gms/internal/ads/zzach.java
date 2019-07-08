package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdAssetNames;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public class zzach implements zzacd {
    private final Context mContext;
    private final Object mLock = new Object();
    @Nullable
    private final zzbbi zzbob;
    @Nullable
    private zzawv zzbor;
    @Nullable
    private final zzaqp zzbqa;
    @Nullable
    private String zzbqb;
    private final zzace zzdby;
    private boolean zzdcb = false;
    private final zzabn zzdcc;
    @Nullable
    private final JSONObject zzdcd;
    @Nullable
    private final zzacf zzdce;
    private final zzcu zzdcf;
    @VisibleForTesting
    boolean zzdcg;
    @VisibleForTesting
    boolean zzdch;
    @Nullable
    private zzacw zzdci;
    private long zzdcj = 0;
    private long zzdck = 0;
    private WeakReference<View> zzdcl = null;

    public zzach(Context context, zzace zzace, @Nullable zzaqp zzaqp, zzcu zzcu, @Nullable JSONObject jSONObject, @Nullable zzacf zzacf, @Nullable zzbbi zzbbi, @Nullable String str) {
        this.mContext = context;
        this.zzdby = zzace;
        this.zzbqa = zzaqp;
        this.zzdcf = zzcu;
        this.zzdcd = jSONObject;
        this.zzdce = zzacf;
        this.zzbob = zzbbi;
        this.zzbqb = str;
        this.zzdcc = new zzabn(this.zzbqa);
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
    }

    @Nullable
    public View zza(OnClickListener onClickListener, boolean z) {
        zzabm zzrw = this.zzdce.zzrw();
        if (zzrw == null) {
            return null;
        }
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        if (!z) {
            int zzrr = zzrw.zzrr();
            if (zzrr != 0) {
                switch (zzrr) {
                    case 2:
                        layoutParams.addRule(12);
                        layoutParams.addRule(11);
                        break;
                    case 3:
                        layoutParams.addRule(12);
                        layoutParams.addRule(9);
                        break;
                    default:
                        layoutParams.addRule(10);
                        layoutParams.addRule(11);
                        break;
                }
            } else {
                layoutParams.addRule(10);
                layoutParams.addRule(9);
            }
        }
        zzabp zzabp = new zzabp(this.mContext, zzrw, layoutParams);
        zzabp.setOnClickListener(onClickListener);
        zzabp.setContentDescription((CharSequence) zzwu.zzpz().zzd(zzaan.zzcuj));
        return zzabp;
    }

    public boolean zzsj() {
        zzabm zzrw = this.zzdce.zzrw();
        return zzrw != null && zzrw.zzrs();
    }

    public boolean zzsl() {
        JSONObject jSONObject = this.zzdcd;
        return jSONObject != null && jSONObject.optBoolean("allow_pub_owned_ad_view", false);
    }

    public boolean zzsk() {
        JSONObject jSONObject = this.zzdcd;
        return jSONObject != null && jSONObject.optBoolean("allow_custom_click_gesture", false);
    }

    private final boolean zzbs(String str) {
        JSONObject jSONObject = this.zzdcd;
        JSONObject optJSONObject = jSONObject == null ? null : jSONObject.optJSONObject("allow_pub_event_reporting");
        if (optJSONObject == null) {
            return false;
        }
        return optJSONObject.optBoolean(str, false);
    }

    public final void zza(View view, String str, @Nullable Bundle bundle, Map<String, WeakReference<View>> map, View view2, boolean z) {
        JSONObject jSONObject;
        View view3 = view2;
        JSONObject zza = zza(map, view3);
        JSONObject zzp = zzp(view3);
        JSONObject zzq = zzq(view2);
        JSONObject zzr = zzr(view3);
        JSONObject jSONObject2 = null;
        try {
            Bundle bundle2 = bundle;
            JSONObject zza2 = zzbv.zzlf().zza(bundle, (JSONObject) null);
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("click_point", zza2);
                String str2 = str;
            } catch (Exception e) {
                e = e;
                String str3 = str;
                jSONObject2 = jSONObject3;
                zzaxz.zzb("Error occurred while grabbing click signals.", e);
                jSONObject = jSONObject2;
                zza(view, zzp, zza, zzq, zzr, str, jSONObject, null, z);
            }
            try {
                jSONObject3.put("asset_id", str);
                jSONObject = jSONObject3;
            } catch (Exception e2) {
                e = e2;
                jSONObject2 = jSONObject3;
                zzaxz.zzb("Error occurred while grabbing click signals.", e);
                jSONObject = jSONObject2;
                zza(view, zzp, zza, zzq, zzr, str, jSONObject, null, z);
            }
        } catch (Exception e3) {
            e = e3;
            String str4 = str;
            zzaxz.zzb("Error occurred while grabbing click signals.", e);
            jSONObject = jSONObject2;
            zza(view, zzp, zza, zzq, zzr, str, jSONObject, null, z);
        }
        zza(view, zzp, zza, zzq, zzr, str, jSONObject, null, z);
    }

    public void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (map != null) {
            synchronized (map) {
                for (Entry entry : map.entrySet()) {
                    if (view.equals((View) ((WeakReference) entry.getValue()).get())) {
                        zza(view, (String) entry.getKey(), bundle, map, view2, false);
                        return;
                    }
                }
            }
        }
        if ("6".equals(this.zzdce.zzrv())) {
            zza(view, "3099", bundle, map, view2, false);
        } else if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(this.zzdce.zzrv())) {
            zza(view, "2099", bundle, map, view2, false);
        } else {
            if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzdce.zzrv())) {
                zza(view, "1099", bundle, map, view2, false);
            }
        }
    }

    public final void performClick(Bundle bundle) {
        if (bundle == null) {
            zzaxz.zzdn("Click data is null. No click is reported.");
        } else if (!zzbs("click_reporting")) {
            zzaxz.e("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
        } else {
            zza(null, null, null, null, null, bundle.getBundle("click_signal").getString("asset_id"), null, zzbv.zzlf().zza(bundle, (JSONObject) null), false);
        }
    }

    private final void zza(View view, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, JSONObject jSONObject6, boolean z) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.zzdcd);
            if (jSONObject2 != null) {
                jSONObject7.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject7.put("ad_view_signal", jSONObject);
            }
            if (jSONObject5 != null) {
                jSONObject7.put("click_signal", jSONObject5);
            }
            if (jSONObject3 != null) {
                jSONObject7.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject7.put("lock_screen_signal", jSONObject4);
            }
            JSONObject jSONObject8 = new JSONObject();
            long currentTimeMillis = zzbv.zzlm().currentTimeMillis();
            jSONObject8.put("time_from_last_touch_down", currentTimeMillis - this.zzdck);
            jSONObject8.put("time_from_last_touch", currentTimeMillis - this.zzdcj);
            jSONObject7.put("touch_signal", jSONObject8);
            JSONObject jSONObject9 = new JSONObject();
            jSONObject9.put("asset_id", str);
            jSONObject9.put(MessengerShareContentUtility.ATTACHMENT_TEMPLATE_TYPE, this.zzdce.zzrv());
            zzbv.zzlh();
            jSONObject9.put("is_privileged_process", zzayp.zzaaa());
            boolean z2 = false;
            if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuo)).booleanValue() && this.zzdcc.zzrt() != null && this.zzdcd.optBoolean("custom_one_point_five_click_enabled", false)) {
                jSONObject9.put("custom_one_point_five_click_eligible", true);
            }
            jSONObject9.put("timestamp", currentTimeMillis);
            jSONObject9.put("has_custom_click_handler", this.zzdby.zzar(this.zzdce.getCustomTemplateId()) != null);
            if (this.zzdcb && zzsk()) {
                jSONObject9.put("custom_click_gesture_eligible", true);
            }
            if (z) {
                jSONObject9.put("is_custom_click_gesture", true);
            }
            String str2 = "has_custom_click_handler";
            if (this.zzdby.zzar(this.zzdce.getCustomTemplateId()) != null) {
                z2 = true;
            }
            jSONObject7.put(str2, z2);
            try {
                JSONObject optJSONObject = this.zzdcd.optJSONObject("tracking_urls_and_actions");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                jSONObject9.put("click_signals", this.zzdcf.zzab().zza(this.mContext, optJSONObject.optString("click_string"), view));
            } catch (Exception e) {
                zzaxz.zzb("Exception obtaining click signals", e);
            }
            jSONObject7.put("click", jSONObject9);
            if (jSONObject6 != null) {
                jSONObject7.put("provided_signals", jSONObject6);
            }
            jSONObject7.put("ads_id", this.zzbqb);
            zzbbo.zza(this.zzbqa.zzi(jSONObject7), "NativeAdEngineImpl.performClick");
        } catch (JSONException e2) {
            zzaxz.zzb("Unable to create click JSON.", e2);
        }
    }

    public void zzd(MotionEvent motionEvent) {
        this.zzdcj = zzbv.zzlm().currentTimeMillis();
        if (motionEvent.getAction() == 0) {
            this.zzdck = zzbv.zzlm().currentTimeMillis();
        }
        this.zzdcf.zza(motionEvent);
    }

    public final void reportTouchEvent(Bundle bundle) {
        if (bundle == null) {
            zzaxz.zzdn("Touch event data is null. No touch event is reported.");
        } else if (!zzbs("touch_reporting")) {
            zzaxz.e("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
        } else {
            this.zzdcf.zzab().zza((int) bundle.getFloat(AvidJSONUtil.KEY_X), (int) bundle.getFloat("y"), bundle.getInt("duration_ms"));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x005d, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzc(android.view.View r4, java.util.Map<java.lang.String, java.lang.ref.WeakReference<android.view.View>> r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mLock
            monitor-enter(r0)
            boolean r1 = r3.zzdcg     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0009:
            boolean r1 = zzn(r4)     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x0014
            r3.zza(r4, r5)     // Catch:{ all -> 0x005e }
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0014:
            com.google.android.gms.internal.ads.zzaac<java.lang.Boolean> r1 = com.google.android.gms.internal.ads.zzaan.zzcun     // Catch:{ all -> 0x005e }
            com.google.android.gms.internal.ads.zzaak r2 = com.google.android.gms.internal.ads.zzwu.zzpz()     // Catch:{ all -> 0x005e }
            java.lang.Object r1 = r2.zzd(r1)     // Catch:{ all -> 0x005e }
            java.lang.Boolean r1 = (java.lang.Boolean) r1     // Catch:{ all -> 0x005e }
            boolean r1 = r1.booleanValue()     // Catch:{ all -> 0x005e }
            if (r1 == 0) goto L_0x005c
            if (r5 == 0) goto L_0x005c
            monitor-enter(r5)     // Catch:{ all -> 0x005e }
            java.util.Set r1 = r5.entrySet()     // Catch:{ all -> 0x0059 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0059 }
        L_0x0031:
            boolean r2 = r1.hasNext()     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r1.next()     // Catch:{ all -> 0x0059 }
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2     // Catch:{ all -> 0x0059 }
            java.lang.Object r2 = r2.getValue()     // Catch:{ all -> 0x0059 }
            java.lang.ref.WeakReference r2 = (java.lang.ref.WeakReference) r2     // Catch:{ all -> 0x0059 }
            java.lang.Object r2 = r2.get()     // Catch:{ all -> 0x0059 }
            android.view.View r2 = (android.view.View) r2     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0031
            boolean r2 = zzn(r2)     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x0031
            r3.zza(r4, r5)     // Catch:{ all -> 0x0059 }
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x0057:
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            goto L_0x005c
        L_0x0059:
            r4 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x0059 }
            throw r4     // Catch:{ all -> 0x005e }
        L_0x005c:
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            return
        L_0x005e:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x005e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzach.zzc(android.view.View, java.util.Map):void");
    }

    private static boolean zzn(View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), null);
    }

    public void zzjl() {
        this.zzdby.zzjl();
    }

    public void zza(View view, Map<String, WeakReference<View>> map) {
        zza(zzp(view), zza(map, view), zzq(view), zzr(view), (JSONObject) null);
    }

    public final boolean recordImpression(Bundle bundle) {
        if (!zzbs("impression_reporting")) {
            zzaxz.e("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
            return false;
        }
        return zza((JSONObject) null, (JSONObject) null, (JSONObject) null, (JSONObject) null, zzbv.zzlf().zza(bundle, (JSONObject) null));
    }

    private final boolean zza(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (this.zzdcg) {
            return true;
        }
        this.zzdcg = true;
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zzdcd);
            jSONObject6.put("ads_id", this.zzbqb);
            if (jSONObject2 != null) {
                jSONObject6.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject6.put("ad_view_signal", jSONObject);
            }
            if (jSONObject3 != null) {
                jSONObject6.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject6.put("lock_screen_signal", jSONObject4);
            }
            if (jSONObject5 != null) {
                jSONObject6.put("provided_signals", jSONObject5);
            }
            zzbbo.zza(this.zzbqa.zzj(jSONObject6), "NativeAdEngineImpl.recordImpression");
            this.zzdby.zza((zzacd) this);
            this.zzdby.zziq();
            zzjl();
            return true;
        } catch (JSONException e) {
            zzaxz.zzb("Unable to create impression JSON.", e);
            return false;
        }
    }

    public void zzsm() {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (!this.zzdch) {
            this.zzdch = true;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ad", this.zzdcd);
                jSONObject.put("ads_id", this.zzbqb);
                zzbbo.zza(this.zzbqa.zzk(jSONObject), "NativeAdEngineImpl.recordDownloadedImpression");
            } catch (JSONException e) {
                zzbbd.zzb("", e);
            }
        }
    }

    public final View zzss() {
        WeakReference<View> weakReference = this.zzdcl;
        if (weakReference != null) {
            return (View) weakReference.get();
        }
        return null;
    }

    public final void zzm(View view) {
        this.zzdcl = new WeakReference<>(view);
    }

    public final void zzj(View view) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzctk)).booleanValue()) {
            zzcu zzcu = this.zzdcf;
            if (zzcu != null) {
                zzcq zzab = zzcu.zzab();
                if (zzab != null) {
                    zzab.zzb(view);
                }
            }
        }
    }

    public void zza(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        view.setOnTouchListener(onTouchListener);
        view.setClickable(true);
        view.setOnClickListener(onClickListener);
        if (map != null) {
            synchronized (map) {
                for (Entry value : map.entrySet()) {
                    View view2 = (View) ((WeakReference) value.getValue()).get();
                    if (view2 != null) {
                        view2.setOnTouchListener(onTouchListener);
                        view2.setClickable(true);
                        view2.setOnClickListener(onClickListener);
                    }
                }
            }
        }
        if (map2 != null) {
            synchronized (map2) {
                for (Entry value2 : map2.entrySet()) {
                    View view3 = (View) ((WeakReference) value2.getValue()).get();
                    if (view3 != null) {
                        view3.setOnTouchListener(onTouchListener);
                    }
                }
            }
        }
    }

    public zzbgg zzsp() throws zzbgq {
        JSONObject jSONObject = this.zzdcd;
        if (jSONObject == null || jSONObject.optJSONObject("overlay") == null) {
            return null;
        }
        zzbv.zzlg();
        Context context = this.mContext;
        zzwf zzg = zzwf.zzg(context);
        zzbgg zza = zzbgm.zza(context, zzbht.zzb(zzg), zzg.zzckk, false, false, this.zzdcf, this.zzbob, null, null, null, zzum.zzoi());
        if (zza != null) {
            zza.getView().setVisibility(8);
            new zzacj(zza).zza(this.zzbqa);
        }
        return zza;
    }

    public final void zzf(Map<String, WeakReference<View>> map) {
        if (this.zzdce.zzrx() != null) {
            boolean z = true;
            if (InternalAvidAdSessionContext.AVID_API_LEVEL.equals(this.zzdce.zzrv())) {
                zzayb zzyq = zzbv.zzlj().zzyq();
                String adUnitId = this.zzdby.getAdUnitId();
                String zzrv = this.zzdce.zzrv();
                if (map == null || (!map.containsKey(NativeAppInstallAd.ASSET_MEDIA_VIDEO) && !map.containsKey(UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO))) {
                    z = false;
                }
                zzyq.zzb(adUnitId, zzrv, z);
            } else if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzdce.zzrv())) {
                zzayb zzyq2 = zzbv.zzlj().zzyq();
                String adUnitId2 = this.zzdby.getAdUnitId();
                String zzrv2 = this.zzdce.zzrv();
                if (map == null || (!map.containsKey(NativeContentAd.ASSET_MEDIA_VIDEO) && !map.containsKey(UnifiedNativeAdAssetNames.ASSET_MEDIA_VIDEO))) {
                    z = false;
                }
                zzyq2.zzb(adUnitId2, zzrv2, z);
            }
        }
    }

    public final boolean zzb(View view, zzacb zzacb) {
        FrameLayout.LayoutParams layoutParams;
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcum)).booleanValue()) {
            layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        } else {
            layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        }
        View zzrx = this.zzdce.zzrx();
        if (zzrx == null) {
            return false;
        }
        ViewParent parent = zzrx.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(zzrx);
        }
        FrameLayout frameLayout = (FrameLayout) view;
        frameLayout.removeAllViews();
        frameLayout.addView(zzrx, layoutParams);
        this.zzdby.zza(zzacb);
        return true;
    }

    public final void zzi(View view) {
        this.zzdby.zzi(view);
    }

    public final void zza(View view, zzacb zzacb) {
        if (!zzb(view, zzacb)) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            ((FrameLayout) view).removeAllViews();
            zzacf zzacf = this.zzdce;
            if (zzacf instanceof zzacg) {
                zzacg zzacg = (zzacg) zzacf;
                if (zzacg.getImages() != null && zzacg.getImages().size() > 0) {
                    Object obj = zzacg.getImages().get(0);
                    zzadb zzj = obj instanceof IBinder ? zzadc.zzj((IBinder) obj) : null;
                    if (zzj != null) {
                        try {
                            IObjectWrapper zzsa = zzj.zzsa();
                            if (zzsa != null) {
                                Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzsa);
                                ImageView imageView = new ImageView(this.mContext);
                                imageView.setImageDrawable(drawable);
                                imageView.setScaleType(ScaleType.CENTER_INSIDE);
                                ((FrameLayout) view).addView(imageView, layoutParams);
                            }
                        } catch (RemoteException unused) {
                            zzaxz.zzeo("Could not get drawable from image");
                        }
                    }
                }
            }
        }
    }

    public void zza(zzaet zzaet) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuo)).booleanValue()) {
            if (!this.zzdcd.optBoolean("custom_one_point_five_click_enabled", false)) {
                zzaxz.zzeo("setUnconfirmedClickListener: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
            } else {
                this.zzdcc.zza(zzaet);
            }
        }
    }

    public void cancelUnconfirmedClick() {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuo)).booleanValue() && this.zzdcd.optBoolean("custom_one_point_five_click_enabled", false)) {
            this.zzdcc.cancelUnconfirmedClick();
        }
    }

    public void setClickConfirmingView(View view) {
        if (((Boolean) zzwu.zzpz().zzd(zzaan.zzcuo)).booleanValue()) {
            if (!this.zzdcd.optBoolean("custom_one_point_five_click_enabled", false)) {
                zzaxz.zzeo("setClickConfirmingView: Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
                return;
            }
            zzabn zzabn = this.zzdcc;
            if (view != null) {
                view.setOnClickListener(zzabn);
                view.setClickable(true);
                zzabn.zzdam = new WeakReference<>(view);
            }
        }
    }

    public void zzsi() {
        this.zzdcb = true;
    }

    public void recordCustomClickGesture() {
        if (this.zzdci == null) {
            zzaxz.zzdn("Ad should be associated with an ad view before calling recordCustomClickGesture()");
        } else if (!this.zzdcb) {
            zzaxz.zzdn("Custom click reporting failed. enableCustomClickGesture is not set.");
        } else if (!zzsk()) {
            zzaxz.zzdn("Custom click reporting failed. Ad unit id not whitelisted.");
        } else {
            zza(this.zzdci.zzsw(), "3099", null, this.zzdci.zzsv(), this.zzdci.zzsw(), true);
        }
    }

    public final void zza(zzacw zzacw) {
        this.zzdci = zzacw;
    }

    @Nullable
    public final zzacw zzst() {
        return this.zzdci;
    }

    public void zzsr() {
        this.zzdby.zzjn();
    }

    public void zzjm() {
        this.zzdby.zzjm();
    }

    public void zzsq() {
        this.zzbqa.zzug();
    }

    public final Context getContext() {
        return this.mContext;
    }

    @VisibleForTesting
    private static int[] zzo(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    @VisibleForTesting
    private final int zzck(int i) {
        zzwu.zzpv();
        return zzbat.zzb(this.mContext, i);
    }

    private final JSONObject zzb(Rect rect) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("width", zzck(rect.right - rect.left));
        jSONObject.put("height", zzck(rect.bottom - rect.top));
        jSONObject.put(AvidJSONUtil.KEY_X, zzck(rect.left));
        jSONObject.put("y", zzck(rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private final JSONObject zzp(View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (view == null) {
            return jSONObject2;
        }
        try {
            int[] zzo = zzo(view);
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("width", zzck(view.getMeasuredWidth()));
            jSONObject3.put("height", zzck(view.getMeasuredHeight()));
            jSONObject3.put(AvidJSONUtil.KEY_X, zzck(zzo[0]));
            jSONObject3.put("y", zzck(zzo[1]));
            jSONObject3.put("relative_to", "window");
            jSONObject2.put("frame", jSONObject3);
            Rect rect = new Rect();
            if (view.getGlobalVisibleRect(rect)) {
                jSONObject = zzb(rect);
            } else {
                jSONObject = new JSONObject();
                jSONObject.put("width", 0);
                jSONObject.put("height", 0);
                jSONObject.put(AvidJSONUtil.KEY_X, zzck(zzo[0]));
                jSONObject.put("y", zzck(zzo[1]));
                jSONObject.put("relative_to", "window");
            }
            jSONObject2.put("visible_bounds", jSONObject);
        } catch (Exception unused) {
            zzaxz.zzeo("Unable to get native ad view bounding box");
        }
        return jSONObject2;
    }

    private static JSONObject zzq(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        try {
            zzbv.zzlf();
            jSONObject.put("contained_in_scroll_view", zzayh.zzy(view) != -1);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private final JSONObject zzr(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view == null) {
            return jSONObject;
        }
        String str = "can_show_on_lock_screen";
        try {
            zzbv.zzlf();
            jSONObject.put(str, zzayh.zzx(view));
            zzbv.zzlf();
            jSONObject.put("is_keyguard_locked", zzayh.zzau(this.mContext));
        } catch (JSONException unused) {
            zzaxz.zzeo("Unable to get lock screen information");
        }
        return jSONObject;
    }

    private final JSONObject zza(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        if (map == null || view == null) {
            return jSONObject2;
        }
        int[] zzo = zzo(view);
        synchronized (map) {
            for (Entry entry : map.entrySet()) {
                View view2 = (View) ((WeakReference) entry.getValue()).get();
                if (view2 != null) {
                    int[] zzo2 = zzo(view2);
                    JSONObject jSONObject3 = new JSONObject();
                    JSONObject jSONObject4 = new JSONObject();
                    try {
                        jSONObject4.put("width", zzck(view2.getMeasuredWidth()));
                        jSONObject4.put("height", zzck(view2.getMeasuredHeight()));
                        jSONObject4.put(AvidJSONUtil.KEY_X, zzck(zzo2[0] - zzo[0]));
                        jSONObject4.put("y", zzck(zzo2[1] - zzo[1]));
                        jSONObject4.put("relative_to", "ad_view");
                        jSONObject3.put("frame", jSONObject4);
                        Rect rect = new Rect();
                        if (view2.getLocalVisibleRect(rect)) {
                            jSONObject = zzb(rect);
                        } else {
                            JSONObject jSONObject5 = new JSONObject();
                            jSONObject5.put("width", 0);
                            jSONObject5.put("height", 0);
                            jSONObject5.put(AvidJSONUtil.KEY_X, zzck(zzo2[0] - zzo[0]));
                            jSONObject5.put("y", zzck(zzo2[1] - zzo[1]));
                            jSONObject5.put("relative_to", "ad_view");
                            jSONObject = jSONObject5;
                        }
                        jSONObject3.put("visible_bounds", jSONObject);
                        if (view2 instanceof TextView) {
                            TextView textView = (TextView) view2;
                            jSONObject3.put("text_color", textView.getCurrentTextColor());
                            jSONObject3.put("font_size", (double) textView.getTextSize());
                            jSONObject3.put("text", textView.getText());
                        }
                        jSONObject2.put((String) entry.getKey(), jSONObject3);
                    } catch (JSONException unused) {
                        zzaxz.zzeo("Unable to get asset views information");
                    }
                }
            }
        }
        return jSONObject2;
    }

    @Nullable
    public final zzawv zzsu() {
        if (!zzbv.zzmf().zzv(this.mContext)) {
            return null;
        }
        if (this.zzbor == null) {
            this.zzbor = new zzawv(this.mContext, this.zzdby.getAdUnitId());
        }
        return this.zzbor;
    }
}
