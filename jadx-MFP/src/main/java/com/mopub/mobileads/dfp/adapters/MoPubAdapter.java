package com.mopub.mobileads.dfp.adapters;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationBannerListener;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.MediationNativeListener;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import com.mopub.common.MoPub;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.SdkInitializationListener;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.BannerAdListener;
import com.mopub.nativeads.MoPubNative;
import com.mopub.nativeads.MoPubNative.MoPubNativeNetworkListener;
import com.mopub.nativeads.MoPubStaticNativeAdRenderer;
import com.mopub.nativeads.NativeAd.MoPubNativeEventListener;
import com.mopub.nativeads.NativeErrorCode;
import com.mopub.nativeads.RequestParameters;
import com.mopub.nativeads.RequestParameters.NativeAdAsset;
import com.mopub.nativeads.ViewBinder.Builder;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;

public class MoPubAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    public static final double DEFAULT_MOPUB_IMAGE_SCALE = 1.0d;
    public static final String TAG = "MoPubAdapter";
    /* access modifiers changed from: private */
    public AdSize mAdSize;
    private MoPubInterstitial mMoPubInterstitial;
    /* access modifiers changed from: private */
    public MoPubNativeEventListener mMoPubNativeEventListener;
    private MoPubView mMoPubView;
    /* access modifiers changed from: private */
    public int mPrivacyIconSize;
    /* access modifiers changed from: private */
    public int privacyIconPlacement;
    /* access modifiers changed from: private */
    public RequestParameters requestParameters;

    /* renamed from: com.mopub.mobileads.dfp.adapters.MoPubAdapter$4 reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$NativeErrorCode = new int[NativeErrorCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
        static {
            /*
                com.mopub.mobileads.MoPubErrorCode[] r0 = com.mopub.mobileads.MoPubErrorCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$mopub$mobileads$MoPubErrorCode = r0
                r0 = 1
                int[] r1 = $SwitchMap$com$mopub$mobileads$MoPubErrorCode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.mopub.mobileads.MoPubErrorCode r2 = com.mopub.mobileads.MoPubErrorCode.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                r1 = 2
                int[] r2 = $SwitchMap$com$mopub$mobileads$MoPubErrorCode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.mopub.mobileads.MoPubErrorCode r3 = com.mopub.mobileads.MoPubErrorCode.NETWORK_TIMEOUT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2[r3] = r1     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                r2 = 3
                int[] r3 = $SwitchMap$com$mopub$mobileads$MoPubErrorCode     // Catch:{ NoSuchFieldError -> 0x002a }
                com.mopub.mobileads.MoPubErrorCode r4 = com.mopub.mobileads.MoPubErrorCode.SERVER_ERROR     // Catch:{ NoSuchFieldError -> 0x002a }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                com.mopub.nativeads.NativeErrorCode[] r3 = com.mopub.nativeads.NativeErrorCode.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$mopub$nativeads$NativeErrorCode = r3
                int[] r3 = $SwitchMap$com$mopub$nativeads$NativeErrorCode     // Catch:{ NoSuchFieldError -> 0x003d }
                com.mopub.nativeads.NativeErrorCode r4 = com.mopub.nativeads.NativeErrorCode.EMPTY_AD_RESPONSE     // Catch:{ NoSuchFieldError -> 0x003d }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x003d }
                r3[r4] = r0     // Catch:{ NoSuchFieldError -> 0x003d }
            L_0x003d:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeErrorCode     // Catch:{ NoSuchFieldError -> 0x0047 }
                com.mopub.nativeads.NativeErrorCode r3 = com.mopub.nativeads.NativeErrorCode.INVALID_REQUEST_URL     // Catch:{ NoSuchFieldError -> 0x0047 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0047 }
                r0[r3] = r1     // Catch:{ NoSuchFieldError -> 0x0047 }
            L_0x0047:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeErrorCode     // Catch:{ NoSuchFieldError -> 0x0051 }
                com.mopub.nativeads.NativeErrorCode r1 = com.mopub.nativeads.NativeErrorCode.CONNECTION_ERROR     // Catch:{ NoSuchFieldError -> 0x0051 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0051 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0051 }
            L_0x0051:
                int[] r0 = $SwitchMap$com$mopub$nativeads$NativeErrorCode     // Catch:{ NoSuchFieldError -> 0x005c }
                com.mopub.nativeads.NativeErrorCode r1 = com.mopub.nativeads.NativeErrorCode.UNSPECIFIED     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.dfp.adapters.MoPubAdapter.AnonymousClass4.<clinit>():void");
        }
    }

    public static final class BundleBuilder {
        private int mPrivacyIconSizeDp;

        public BundleBuilder setPrivacyIconSize(int i) {
            this.mPrivacyIconSizeDp = i;
            return this;
        }

        public Bundle build() {
            Bundle bundle = new Bundle();
            bundle.putInt("privacy_icon_size_dp", this.mPrivacyIconSizeDp);
            return bundle;
        }
    }

    private class MBannerListener implements BannerAdListener {
        private MediationBannerListener mMediationBannerListener;

        public MBannerListener(MediationBannerListener mediationBannerListener) {
            this.mMediationBannerListener = mediationBannerListener;
        }

        public void onBannerClicked(MoPubView moPubView) {
            this.mMediationBannerListener.onAdClicked(MoPubAdapter.this);
            this.mMediationBannerListener.onAdLeftApplication(MoPubAdapter.this);
        }

        public void onBannerCollapsed(MoPubView moPubView) {
            this.mMediationBannerListener.onAdClosed(MoPubAdapter.this);
        }

        public void onBannerExpanded(MoPubView moPubView) {
            this.mMediationBannerListener.onAdOpened(MoPubAdapter.this);
        }

        public void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode) {
            try {
                switch (moPubErrorCode) {
                    case NO_FILL:
                        this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 3);
                        return;
                    case NETWORK_TIMEOUT:
                        this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 2);
                        return;
                    case SERVER_ERROR:
                        this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                        return;
                    default:
                        this.mMediationBannerListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                        return;
                }
            } catch (NoClassDefFoundError unused) {
            }
        }

        public void onBannerLoaded(MoPubView moPubView) {
            if (!(MoPubAdapter.this.mAdSize.getWidth() == moPubView.getAdWidth() && MoPubAdapter.this.mAdSize.getHeight() == moPubView.getAdHeight())) {
                Log.w(MoPubAdapter.TAG, "The banner ad size loaded does not match the request size. Update the ad size on your MoPub UI to match the request size.");
            }
            this.mMediationBannerListener.onAdLoaded(MoPubAdapter.this);
        }
    }

    private class mMediationInterstitialListener implements InterstitialAdListener {
        private MediationInterstitialListener mMediationInterstitialListener;

        public mMediationInterstitialListener(MediationInterstitialListener mediationInterstitialListener) {
            this.mMediationInterstitialListener = mediationInterstitialListener;
        }

        public void onInterstitialClicked(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdClicked(MoPubAdapter.this);
            this.mMediationInterstitialListener.onAdLeftApplication(MoPubAdapter.this);
        }

        public void onInterstitialDismissed(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdClosed(MoPubAdapter.this);
        }

        public void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode) {
            try {
                switch (moPubErrorCode) {
                    case NO_FILL:
                        this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 3);
                        return;
                    case NETWORK_TIMEOUT:
                        this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 2);
                        return;
                    case SERVER_ERROR:
                        this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                        return;
                    default:
                        this.mMediationInterstitialListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                        return;
                }
            } catch (NoClassDefFoundError unused) {
            }
        }

        public void onInterstitialLoaded(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdLoaded(MoPubAdapter.this);
        }

        public void onInterstitialShown(MoPubInterstitial moPubInterstitial) {
            this.mMediationInterstitialListener.onAdOpened(MoPubAdapter.this);
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onDestroy() {
        MoPubInterstitial moPubInterstitial = this.mMoPubInterstitial;
        if (moPubInterstitial != null) {
            moPubInterstitial.destroy();
            this.mMoPubInterstitial = null;
        }
        MoPubView moPubView = this.mMoPubView;
        if (moPubView != null) {
            moPubView.destroy();
            this.mMoPubView = null;
        }
    }

    public void requestNativeAd(final Context context, final MediationNativeListener mediationNativeListener, Bundle bundle, NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        String string = bundle.getString("adUnitId");
        NativeAdOptions nativeAdOptions = nativeMediationAdRequest.getNativeAdOptions();
        if (nativeAdOptions != null) {
            this.privacyIconPlacement = nativeAdOptions.getAdChoicesPlacement();
        } else {
            this.privacyIconPlacement = 1;
        }
        if (bundle2 != null) {
            int i = bundle2.getInt("privacy_icon_size_dp");
            if (i < 10) {
                this.mPrivacyIconSize = 10;
            } else if (i > 30) {
                this.mPrivacyIconSize = 30;
            } else {
                this.mPrivacyIconSize = i;
            }
        } else {
            this.mPrivacyIconSize = 20;
        }
        AnonymousClass1 r10 = new MoPubNativeNetworkListener() {
            /* JADX WARNING: Can't wrap try/catch for region: R(9:3|4|5|6|7|8|9|10|15) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0036 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onNativeLoad(com.mopub.nativeads.NativeAd r6) {
                /*
                    r5 = this;
                    com.mopub.mobileads.dfp.adapters.MoPubAdapter r0 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.this
                    com.mopub.nativeads.NativeAd$MoPubNativeEventListener r0 = r0.mMoPubNativeEventListener
                    r6.setMoPubNativeEventListener(r0)
                    com.mopub.nativeads.BaseNativeAd r6 = r6.getBaseNativeAd()
                    boolean r0 = r6 instanceof com.mopub.nativeads.StaticNativeAd
                    if (r0 == 0) goto L_0x0065
                    com.mopub.nativeads.StaticNativeAd r6 = (com.mopub.nativeads.StaticNativeAd) r6
                    r0 = 0
                    java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Exception -> 0x0057 }
                    r1.<init>()     // Catch:{ Exception -> 0x0057 }
                    java.lang.String r2 = "icon_key"
                    java.net.URL r3 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0036 }
                    java.lang.String r4 = r6.getIconImageUrl()     // Catch:{ MalformedURLException -> 0x0036 }
                    r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x0036 }
                    r1.put(r2, r3)     // Catch:{ MalformedURLException -> 0x0036 }
                    java.lang.String r2 = "image_key"
                    java.net.URL r3 = new java.net.URL     // Catch:{ MalformedURLException -> 0x0036 }
                    java.lang.String r4 = r6.getMainImageUrl()     // Catch:{ MalformedURLException -> 0x0036 }
                    r3.<init>(r4)     // Catch:{ MalformedURLException -> 0x0036 }
                    r1.put(r2, r3)     // Catch:{ MalformedURLException -> 0x0036 }
                    goto L_0x0044
                L_0x0036:
                    java.lang.String r2 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.TAG     // Catch:{ Exception -> 0x0057 }
                    java.lang.String r3 = "Invalid ad response received from MoPub. Image URLs are invalid"
                    android.util.Log.d(r2, r3)     // Catch:{ Exception -> 0x0057 }
                    com.google.android.gms.ads.mediation.MediationNativeListener r2 = r9     // Catch:{ Exception -> 0x0057 }
                    com.mopub.mobileads.dfp.adapters.MoPubAdapter r3 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.this     // Catch:{ Exception -> 0x0057 }
                    r2.onAdFailedToLoad(r3, r0)     // Catch:{ Exception -> 0x0057 }
                L_0x0044:
                    com.mopub.mobileads.dfp.adapters.DownloadDrawablesAsync r2 = new com.mopub.mobileads.dfp.adapters.DownloadDrawablesAsync     // Catch:{ Exception -> 0x0057 }
                    com.mopub.mobileads.dfp.adapters.MoPubAdapter$1$1 r3 = new com.mopub.mobileads.dfp.adapters.MoPubAdapter$1$1     // Catch:{ Exception -> 0x0057 }
                    r3.<init>(r6)     // Catch:{ Exception -> 0x0057 }
                    r2.<init>(r3)     // Catch:{ Exception -> 0x0057 }
                    r6 = 1
                    java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x0057 }
                    r6[r0] = r1     // Catch:{ Exception -> 0x0057 }
                    r2.execute(r6)     // Catch:{ Exception -> 0x0057 }
                    goto L_0x0065
                L_0x0057:
                    java.lang.String r6 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.TAG
                    java.lang.String r1 = "Exception constructing the native ad"
                    android.util.Log.d(r6, r1)
                    com.google.android.gms.ads.mediation.MediationNativeListener r6 = r9
                    com.mopub.mobileads.dfp.adapters.MoPubAdapter r1 = com.mopub.mobileads.dfp.adapters.MoPubAdapter.this
                    r6.onAdFailedToLoad(r1, r0)
                L_0x0065:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.dfp.adapters.MoPubAdapter.AnonymousClass1.onNativeLoad(com.mopub.nativeads.NativeAd):void");
            }

            public void onNativeFail(NativeErrorCode nativeErrorCode) {
                switch (AnonymousClass4.$SwitchMap$com$mopub$nativeads$NativeErrorCode[nativeErrorCode.ordinal()]) {
                    case 1:
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 3);
                        return;
                    case 2:
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                        return;
                    case 3:
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 1);
                        return;
                    case 4:
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                        return;
                    default:
                        mediationNativeListener.onAdFailedToLoad(MoPubAdapter.this, 0);
                        return;
                }
            }
        };
        if (string == null) {
            Log.d(TAG, "Ad unit id is invalid. So failing the request.");
            mediationNativeListener.onAdFailedToLoad(this, 1);
            return;
        }
        MoPubNative moPubNative = new MoPubNative(context, string, r10);
        moPubNative.registerAdRenderer(new MoPubStaticNativeAdRenderer(new Builder(0).build()));
        this.requestParameters = new RequestParameters.Builder().keywords(getKeywords(nativeMediationAdRequest, false)).userDataKeywords(getKeywords(nativeMediationAdRequest, true)).location(nativeMediationAdRequest.getLocation()).desiredAssets(EnumSet.of(NativeAdAsset.TITLE, NativeAdAsset.TEXT, NativeAdAsset.CALL_TO_ACTION_TEXT, NativeAdAsset.MAIN_IMAGE, NativeAdAsset.ICON_IMAGE)).build();
        if (MoPub.isSdkInitialized()) {
            moPubNative.makeRequest(this.requestParameters);
        } else {
            initializeMoPub(context, string, null, null, moPubNative);
        }
        this.mMoPubNativeEventListener = new MoPubNativeEventListener() {
            public void onImpression(View view) {
                mediationNativeListener.onAdImpression(MoPubAdapter.this);
                Log.d(MoPubAdapter.TAG, "onImpression");
            }

            public void onClick(View view) {
                mediationNativeListener.onAdClicked(MoPubAdapter.this);
                mediationNativeListener.onAdOpened(MoPubAdapter.this);
                mediationNativeListener.onAdLeftApplication(MoPubAdapter.this);
                Log.d(MoPubAdapter.TAG, "onClick");
            }
        };
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        String string = bundle.getString("adUnitId");
        this.mAdSize = adSize;
        this.mMoPubView = new MoPubView(context);
        this.mMoPubView.setBannerAdListener(new MBannerListener(mediationBannerListener));
        this.mMoPubView.setAdUnitId(string);
        if (mediationAdRequest.isTesting()) {
            this.mMoPubView.setTesting(true);
        }
        if (mediationAdRequest.getLocation() != null) {
            this.mMoPubView.setLocation(mediationAdRequest.getLocation());
        }
        this.mMoPubView.setKeywords(getKeywords(mediationAdRequest, false));
        this.mMoPubView.setUserDataKeywords(getKeywords(mediationAdRequest, true));
        if (MoPub.isSdkInitialized()) {
            this.mMoPubView.loadAd();
            return;
        }
        initializeMoPub(context, string, this.mMoPubView, null, null);
    }

    public View getBannerView() {
        return this.mMoPubView;
    }

    private String getKeywords(MediationAdRequest mediationAdRequest, boolean z) {
        Date birthday = mediationAdRequest.getBirthday();
        String str = "";
        if (birthday != null) {
            int age = getAge(birthday);
            StringBuilder sb = new StringBuilder();
            sb.append("m_age:");
            sb.append(Integer.toString(age));
            str = sb.toString();
        }
        int gender = mediationAdRequest.getGender();
        String str2 = "";
        if (gender != -1) {
            if (gender == 2) {
                str2 = "m_gender:f";
            } else if (gender == 1) {
                str2 = "m_gender:m";
            }
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("gmext");
        sb2.append(",");
        sb2.append(str);
        sb2.append(",");
        sb2.append(str2);
        if (z) {
            return keywordsContainPII(mediationAdRequest) ? sb2.toString() : "";
        }
        return keywordsContainPII(mediationAdRequest) ? "" : sb2.toString();
    }

    private boolean keywordsContainPII(MediationAdRequest mediationAdRequest) {
        return (mediationAdRequest.getBirthday() == null && mediationAdRequest.getGender() == -1 && mediationAdRequest.getLocation() == null) ? false : true;
    }

    private static int getAge(Date date) {
        return Calendar.getInstance().get(1) - Integer.parseInt((String) DateFormat.format("yyyy", date));
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        String string = bundle.getString("adUnitId");
        this.mMoPubInterstitial = new MoPubInterstitial((Activity) context, string);
        this.mMoPubInterstitial.setInterstitialAdListener(new mMediationInterstitialListener(mediationInterstitialListener));
        if (mediationAdRequest.isTesting()) {
            this.mMoPubInterstitial.setTesting(true);
        }
        this.mMoPubInterstitial.setKeywords(getKeywords(mediationAdRequest, false));
        this.mMoPubInterstitial.setKeywords(getKeywords(mediationAdRequest, true));
        if (MoPub.isSdkInitialized()) {
            this.mMoPubInterstitial.load();
            return;
        }
        initializeMoPub(context, string, null, this.mMoPubInterstitial, null);
    }

    public void showInterstitial() {
        if (this.mMoPubInterstitial.isReady()) {
            this.mMoPubInterstitial.show();
        } else {
            MoPubLog.i("Interstitial was not ready. Unable to load the interstitial");
        }
    }

    private void initializeMoPub(Context context, String str, MoPubView moPubView, MoPubInterstitial moPubInterstitial, MoPubNative moPubNative) {
        MoPub.initializeSdk(context, new SdkConfiguration.Builder(str).build(), initSdkListener(moPubView, moPubInterstitial, moPubNative));
    }

    private SdkInitializationListener initSdkListener(final MoPubView moPubView, final MoPubInterstitial moPubInterstitial, final MoPubNative moPubNative) {
        return new SdkInitializationListener() {
            public void onInitializationFinished() {
                MoPubLog.d("MoPub SDK initialized.");
                MoPubView moPubView = moPubView;
                if (moPubView != null) {
                    moPubView.loadAd();
                    return;
                }
                MoPubInterstitial moPubInterstitial = moPubInterstitial;
                if (moPubInterstitial != null) {
                    moPubInterstitial.load();
                } else if (moPubNative == null) {
                } else {
                    if (MoPubAdapter.this.requestParameters != null) {
                        moPubNative.makeRequest(MoPubAdapter.this.requestParameters);
                    } else {
                        moPubNative.makeRequest();
                    }
                }
            }
        };
    }
}
