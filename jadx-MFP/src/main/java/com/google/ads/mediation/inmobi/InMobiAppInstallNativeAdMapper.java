package com.google.ads.mediation.inmobi;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeAdMapper;
import com.google.android.gms.ads.mediation.NativeAppInstallAdMapper;
import com.inmobi.ads.InMobiNative;
import com.mopub.mobileads.dfp.adapters.DownloadDrawablesAsync;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

class InMobiAppInstallNativeAdMapper extends NativeAppInstallAdMapper {
    private String[] impressionTrackers;
    /* access modifiers changed from: private */
    public final InMobiAdapter inMobiAdapter;
    private final InMobiNative inMobiNative;
    private final Boolean isOnlyURL;
    private final HashMap<String, String> landingUrlMap = new HashMap<>();
    /* access modifiers changed from: private */
    public final MediationNativeListener mediationNativeListener;

    public void trackView(View view) {
    }

    public void untrackView(View view) {
    }

    InMobiAppInstallNativeAdMapper(InMobiAdapter inMobiAdapter2, InMobiNative inMobiNative2, Boolean bool, MediationNativeListener mediationNativeListener2) {
        this.inMobiAdapter = inMobiAdapter2;
        this.inMobiNative = inMobiNative2;
        this.isOnlyURL = bool;
        this.mediationNativeListener = mediationNativeListener2;
    }

    /* access modifiers changed from: 0000 */
    public void mapAppInstallAd() {
        final Double d;
        final Double d2;
        try {
            if (this.inMobiNative.getCustomAdContent() != null) {
                JSONObject customAdContent = this.inMobiNative.getCustomAdContent();
                setHeadline((String) InMobiAdapterUtils.mandatoryChecking(this.inMobiNative.getAdTitle(), "title"));
                setBody((String) InMobiAdapterUtils.mandatoryChecking(this.inMobiNative.getAdDescription(), "description"));
                setCallToAction((String) InMobiAdapterUtils.mandatoryChecking(this.inMobiNative.getAdCtaText(), InMobiNetworkValues.CTA));
                String str = (String) InMobiAdapterUtils.mandatoryChecking(this.inMobiNative.getAdLandingPageUrl(), InMobiNetworkValues.LANDING_URL);
                Bundle bundle = new Bundle();
                bundle.putString(InMobiNetworkValues.LANDING_URL, str);
                setExtras(bundle);
                this.landingUrlMap.put(InMobiNetworkValues.LANDING_URL, str);
                HashMap hashMap = new HashMap();
                JSONObject jSONObject = (JSONObject) InMobiAdapterUtils.mandatoryChecking(customAdContent.getJSONObject(InMobiNetworkValues.ICON), InMobiNetworkValues.ICON);
                URL url = new URL(jSONObject.getString("url"));
                final Uri parse = Uri.parse(url.toURI().toString());
                if (jSONObject.has(InMobiNetworkValues.ASPECT_RATIO)) {
                    d = Double.valueOf(Double.parseDouble(jSONObject.getString(InMobiNetworkValues.ASPECT_RATIO)));
                } else {
                    Double valueOf = Double.valueOf(Double.parseDouble(jSONObject.getString("width")));
                    Double valueOf2 = Double.valueOf(Double.parseDouble(jSONObject.getString("height")));
                    if (valueOf.doubleValue() <= 0.0d || valueOf2.doubleValue() <= 0.0d) {
                        d = Double.valueOf(0.0d);
                    } else {
                        d = Double.valueOf(valueOf.doubleValue() / valueOf2.doubleValue());
                    }
                }
                if (!this.isOnlyURL.booleanValue()) {
                    hashMap.put(DownloadDrawablesAsync.KEY_ICON, url);
                } else {
                    setIcon(new InMobiNativeMappedImage(null, parse, d.doubleValue()));
                }
                JSONObject jSONObject2 = (JSONObject) InMobiAdapterUtils.mandatoryChecking(customAdContent.getJSONObject("screenshots"), "screenshots");
                URL url2 = new URL(jSONObject2.getString("url"));
                final Uri parse2 = Uri.parse(url2.toURI().toString());
                if (jSONObject2.has(InMobiNetworkValues.ASPECT_RATIO)) {
                    d2 = Double.valueOf(Double.parseDouble(jSONObject2.getString(InMobiNetworkValues.ASPECT_RATIO)));
                } else {
                    Double valueOf3 = Double.valueOf(Double.parseDouble(jSONObject2.getString("width")));
                    Double valueOf4 = Double.valueOf(Double.parseDouble(jSONObject2.getString("height")));
                    if (valueOf3.doubleValue() <= 0.0d || valueOf4.doubleValue() <= 0.0d) {
                        d2 = Double.valueOf(0.0d);
                    } else {
                        d2 = Double.valueOf(valueOf3.doubleValue() / valueOf4.doubleValue());
                    }
                }
                if (!this.isOnlyURL.booleanValue()) {
                    hashMap.put(DownloadDrawablesAsync.KEY_IMAGE, url2);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new InMobiNativeMappedImage(null, parse2, d2.doubleValue()));
                    setImages(arrayList);
                }
                this.impressionTrackers = customAdContent.getString(InMobiNetworkValues.IMPRESSION_TRACKERS).substring(2, customAdContent.getString(InMobiNetworkValues.IMPRESSION_TRACKERS).length() - 2).split("\",\"");
                try {
                    if (customAdContent.has(InMobiNetworkValues.RATING)) {
                        setStarRating(Double.parseDouble(customAdContent.getString(InMobiNetworkValues.RATING)));
                    }
                    if (customAdContent.has(InMobiNetworkValues.PACKAGE_NAME)) {
                        setStore("Google Play");
                    } else {
                        setStore("Others");
                    }
                    if (customAdContent.has("price")) {
                        setPrice(customAdContent.getString("price"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (!this.isOnlyURL.booleanValue()) {
                    AnonymousClass1 r6 = new DrawableDownloadListener() {
                        public void onDownloadSuccess(HashMap<String, Drawable> hashMap) {
                            Drawable drawable = (Drawable) hashMap.get(DownloadDrawablesAsync.KEY_ICON);
                            InMobiAppInstallNativeAdMapper.this.setIcon(new InMobiNativeMappedImage(drawable, parse, d.doubleValue()));
                            Drawable drawable2 = (Drawable) hashMap.get(DownloadDrawablesAsync.KEY_IMAGE);
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(new InMobiNativeMappedImage(drawable2, parse2, d2.doubleValue()));
                            InMobiAppInstallNativeAdMapper.this.setImages(arrayList);
                            if (drawable == null && drawable2 == null) {
                                InMobiAppInstallNativeAdMapper.this.mediationNativeListener.onAdFailedToLoad(InMobiAppInstallNativeAdMapper.this.inMobiAdapter, 2);
                            } else {
                                InMobiAppInstallNativeAdMapper.this.mediationNativeListener.onAdLoaded((MediationNativeAdapter) InMobiAppInstallNativeAdMapper.this.inMobiAdapter, (NativeAdMapper) InMobiAppInstallNativeAdMapper.this);
                            }
                        }

                        public void onDownloadFailure() {
                            InMobiAppInstallNativeAdMapper.this.mediationNativeListener.onAdFailedToLoad(InMobiAppInstallNativeAdMapper.this.inMobiAdapter, 3);
                        }
                    };
                    new ImageDownloaderAsyncTask(r6).execute(new Object[]{hashMap});
                } else {
                    this.mediationNativeListener.onAdLoaded((MediationNativeAdapter) this.inMobiAdapter, (NativeAdMapper) this);
                }
                setOverrideClickHandling(false);
                setOverrideImpressionRecording(false);
                return;
            }
            this.mediationNativeListener.onAdFailedToLoad(this.inMobiAdapter, 3);
        } catch (MandatoryParamException | MalformedURLException | URISyntaxException | JSONException e2) {
            e2.printStackTrace();
            this.mediationNativeListener.onAdFailedToLoad(this.inMobiAdapter, 3);
        }
    }

    public void recordImpression() {
        new ImpressionBeaconAsyncTask().execute(this.impressionTrackers);
    }

    public void handleClick(View view) {
        this.inMobiNative.reportAdClickAndOpenLandingPage();
    }
}
