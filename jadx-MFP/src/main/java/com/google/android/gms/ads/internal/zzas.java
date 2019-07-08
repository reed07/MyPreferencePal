package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.appevents.AppEventsConstants;
import com.google.ads.mediation.inmobi.InMobiNetworkValues;
import com.google.android.gms.ads.internal.gmsg.zzu;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzabs;
import com.google.android.gms.internal.ads.zzabu;
import com.google.android.gms.internal.ads.zzadb;
import com.google.android.gms.internal.ads.zzadc;
import com.google.android.gms.internal.ads.zzakx;
import com.google.android.gms.internal.ads.zzals;
import com.google.android.gms.internal.ads.zzalv;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzaxf;
import com.google.android.gms.internal.ads.zzaxz;
import com.google.android.gms.internal.ads.zzbgg;
import com.google.android.gms.internal.ads.zzbho;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.myfitnesspal.shared.db.table.ImagesTable;
import java.io.ByteArrayOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzark
@ParametersAreNonnullByDefault
public final class zzas {
    @Nullable
    public static View zze(@Nullable zzaxf zzaxf) {
        if (zzaxf == null) {
            zzaxz.e("AdState is null");
            return null;
        } else if (zzf(zzaxf) && zzaxf.zzdrv != null) {
            return zzaxf.zzdrv.getView();
        } else {
            try {
                IObjectWrapper zzut = zzaxf.zzdnc != null ? zzaxf.zzdnc.zzut() : null;
                if (zzut != null) {
                    return (View) ObjectWrapper.unwrap(zzut);
                }
                zzaxz.zzeo("View in mediation adapter is null.");
                return null;
            } catch (RemoteException e) {
                zzaxz.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zza(zzbgg zzbgg, zzakx zzakx, CountDownLatch countDownLatch) {
        zzbgg zzbgg2 = zzbgg;
        zzakx zzakx2 = zzakx;
        CountDownLatch countDownLatch2 = countDownLatch;
        boolean z = false;
        try {
            View view = zzbgg.getView();
            if (view == null) {
                zzaxz.zzeo("AdWebView is null");
            } else {
                view.setVisibility(4);
                List<String> list = zzakx2.zzdnb.zzdll;
                if (list != null) {
                    if (!list.isEmpty()) {
                        zzbgg2.zza("/nativeExpressAssetsLoaded", (zzu<? super zzbgg>) new zzav<Object>(countDownLatch2));
                        zzbgg2.zza("/nativeExpressAssetsLoadingFailed", (zzu<? super zzbgg>) new zzaw<Object>(countDownLatch2));
                        zzals zzuu = zzakx2.zzdnc.zzuu();
                        zzalv zzuv = zzakx2.zzdnc.zzuv();
                        View view2 = null;
                        if (list.contains(InternalAvidAdSessionContext.AVID_API_LEVEL) && zzuu != null) {
                            String headline = zzuu.getHeadline();
                            List images = zzuu.getImages();
                            String body = zzuu.getBody();
                            zzadb zzsb = zzuu.zzsb();
                            String callToAction = zzuu.getCallToAction();
                            double starRating = zzuu.getStarRating();
                            String store = zzuu.getStore();
                            String price = zzuu.getPrice();
                            Bundle extras = zzuu.getExtras();
                            if (zzuu.zzvc() != null) {
                                view2 = (View) ObjectWrapper.unwrap(zzuu.zzvc());
                            }
                            zzabs zzabs = new zzabs(headline, images, body, zzsb, callToAction, starRating, store, price, null, extras, null, view2, zzuu.zzsd(), null);
                            zzbgg.zzadl().zza((zzbho) new zzat(zzabs, zzakx2.zzdnb.zzdlk, zzbgg2));
                        } else if (!list.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES) || zzuv == null) {
                            zzaxz.zzeo("No matching template id and mapper");
                        } else {
                            String headline2 = zzuv.getHeadline();
                            List images2 = zzuv.getImages();
                            String body2 = zzuv.getBody();
                            zzadb zzsf = zzuv.zzsf();
                            String callToAction2 = zzuv.getCallToAction();
                            String advertiser = zzuv.getAdvertiser();
                            Bundle extras2 = zzuv.getExtras();
                            if (zzuv.zzvc() != null) {
                                view2 = (View) ObjectWrapper.unwrap(zzuv.zzvc());
                            }
                            zzabu zzabu = new zzabu(headline2, images2, body2, zzsf, callToAction2, advertiser, null, extras2, null, view2, zzuv.zzsd(), null);
                            zzbgg.zzadl().zza((zzbho) new zzau(zzabu, zzakx2.zzdnb.zzdlk, zzbgg2));
                        }
                        String str = zzakx2.zzdnb.zzdli;
                        String str2 = zzakx2.zzdnb.zzdlj;
                        if (str2 != null) {
                            zzbgg.loadDataWithBaseURL(str2, str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
                        } else {
                            zzbgg2.loadData(str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
                        }
                        z = true;
                    }
                }
                zzaxz.zzeo("No template ids present in mediation response");
            }
        } catch (RemoteException e) {
            zzaxz.zzc("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    public static boolean zzf(@Nullable zzaxf zzaxf) {
        return (zzaxf == null || !zzaxf.zzdyd || zzaxf.zzdnb == null || zzaxf.zzdnb.zzdli == null) ? false : true;
    }

    @VisibleForTesting
    static zzu<zzbgg> zza(@Nullable zzals zzals, @Nullable zzalv zzalv, zzab zzab) {
        return new zzax(zzals, zzab, zzalv);
    }

    /* access modifiers changed from: private */
    public static void zzd(zzbgg zzbgg) {
        OnClickListener onClickListener = zzbgg.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(zzbgg.getView());
        }
    }

    private static JSONObject zza(@Nullable Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if ("image".equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, zza((Bitmap) obj));
                    } else {
                        zzaxz.zzeo("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    zzaxz.zzeo("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    @VisibleForTesting
    private static String zza(@Nullable zzadb zzadb) {
        if (zzadb == null) {
            zzaxz.zzeo("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = zzadb.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException unused) {
            zzaxz.zzeo("Unable to get image uri. Trying data uri next");
        }
        return zzb(zzadb);
    }

    private static String zzb(zzadb zzadb) {
        try {
            IObjectWrapper zzsa = zzadb.zzsa();
            if (zzsa == null) {
                zzaxz.zzeo("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzsa);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzaxz.zzeo("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException unused) {
            zzaxz.zzeo("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    private static String zza(@Nullable Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzaxz.zzeo("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        String valueOf2 = String.valueOf(encodeToString);
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    @Nullable
    private static zzadb zzg(Object obj) {
        if (obj instanceof IBinder) {
            return zzadc.zzj((IBinder) obj);
        }
        return null;
    }

    static final /* synthetic */ void zza(zzabu zzabu, String str, zzbgg zzbgg, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzabu.getHeadline());
            jSONObject.put("body", zzabu.getBody());
            jSONObject.put("call_to_action", zzabu.getCallToAction());
            jSONObject.put("advertiser", zzabu.getAdvertiser());
            jSONObject.put("logo", zza(zzabu.zzsf()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzabu.getImages();
            if (images != null) {
                for (Object zzg : images) {
                    jSONArray.put(zza(zzg(zzg)));
                }
            }
            jSONObject.put(ImagesTable.TABLE_NAME, jSONArray);
            jSONObject.put("extras", zza(zzabu.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            zzbgg.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzaxz.zzc("Exception occurred when loading assets", e);
        }
    }

    static final /* synthetic */ void zza(zzabs zzabs, String str, zzbgg zzbgg, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", zzabs.getHeadline());
            jSONObject.put("body", zzabs.getBody());
            jSONObject.put("call_to_action", zzabs.getCallToAction());
            jSONObject.put("price", zzabs.getPrice());
            jSONObject.put("star_rating", String.valueOf(zzabs.getStarRating()));
            jSONObject.put("store", zzabs.getStore());
            jSONObject.put(InMobiNetworkValues.ICON, zza(zzabs.zzsb()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = zzabs.getImages();
            if (images != null) {
                for (Object zzg : images) {
                    jSONArray.put(zza(zzg(zzg)));
                }
            }
            jSONObject.put(ImagesTable.TABLE_NAME, jSONArray);
            jSONObject.put("extras", zza(zzabs.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", InternalAvidAdSessionContext.AVID_API_LEVEL);
            zzbgg.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (JSONException e) {
            zzaxz.zzc("Exception occurred when loading assets", e);
        }
    }
}
