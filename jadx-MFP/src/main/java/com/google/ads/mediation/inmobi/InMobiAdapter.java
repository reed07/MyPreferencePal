package com.google.ads.mediation.inmobi;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.facebook.appevents.AppEventsConstants;
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
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdListener;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiBanner.AnimationType;
import com.inmobi.ads.InMobiBanner.BannerAdListener;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.InMobiInterstitial.InterstitialAdListener2;
import com.inmobi.ads.InMobiNative;
import com.inmobi.ads.InMobiNative.NativeAdListener;
import com.inmobi.sdk.InMobiSdk;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class InMobiAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter, MediationRewardedVideoAdAdapter {
    /* access modifiers changed from: private */
    public static ConcurrentHashMap<Integer, InMobiNative> STATIC_MAP = new ConcurrentHashMap<>(10, 0.9f, 10);
    private static Boolean disableHardwareFlag = Boolean.valueOf(false);
    private static Boolean isAppInitialized = Boolean.valueOf(false);
    static String url = "";
    /* access modifiers changed from: private */
    public NativeMediationAdRequest _nativeMedAdReq;
    private InMobiInterstitial adInterstitial;
    private InMobiInterstitial adRewarded;
    /* access modifiers changed from: private */
    public MediationBannerListener bannerListener;
    /* access modifiers changed from: private */
    public MediationInterstitialListener interstitialListener;
    /* access modifiers changed from: private */
    public Boolean isOnlyUrl = Boolean.valueOf(false);
    /* access modifiers changed from: private */
    public String key = "";
    private NativeAdListener mInMobiNativeAdListener;
    /* access modifiers changed from: private */
    public MediationNativeListener nativeListener;
    /* access modifiers changed from: private */
    public MediationRewardedVideoAdListener rewardedVideoAdListener;
    /* access modifiers changed from: private */
    public final InMobiAdapter self = this;
    /* access modifiers changed from: private */
    public String value = "";
    private FrameLayout wrappedAdView;

    /* renamed from: com.google.ads.mediation.inmobi.InMobiAdapter$5 reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode = new int[StatusCode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode[] r0 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode = r0
                int[] r0 = $SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.INTERNAL_ERROR     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.REQUEST_INVALID     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = $SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode     // Catch:{ NoSuchFieldError -> 0x002a }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.NETWORK_UNREACHABLE     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = $SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.inmobi.ads.InMobiAdRequestStatus$StatusCode r1 = com.inmobi.ads.InMobiAdRequestStatus.StatusCode.NO_FILL     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.ads.mediation.inmobi.InMobiAdapter.AnonymousClass5.<clinit>():void");
        }
    }

    public void onDestroy() {
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void showInterstitial() {
        if (this.adInterstitial.isReady()) {
            Log.d("InMobiAdapter", "Ad is ready to show");
            this.adInterstitial.show();
        }
    }

    public View getBannerView() {
        return this.wrappedAdView;
    }

    public static void disableHardwareAcceleration() {
        disableHardwareFlag = Boolean.valueOf(true);
    }

    public void requestBannerAd(Context context, MediationBannerListener mediationBannerListener, Bundle bundle, AdSize adSize, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        InMobiBanner inMobiBanner;
        if (!isAppInitialized.booleanValue() && bundle != null) {
            Log.d("InMobiAdapter", bundle.getString("accountid"));
            Log.d("InMobiAdapter", bundle.getString("placementid"));
            InMobiSdk.init(context, bundle.getString("accountid"));
            isAppInitialized = Boolean.valueOf(true);
        }
        if (VERSION.SDK_INT < 14) {
            mediationBannerListener.onAdFailedToLoad(this, 1);
            return;
        }
        this.bannerListener = mediationBannerListener;
        LayoutParams layoutParams = new LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context));
        if (context instanceof Activity) {
            inMobiBanner = new InMobiBanner((Activity) context, Long.parseLong(bundle.getString("placementid")));
        } else {
            inMobiBanner = new InMobiBanner(context, Long.parseLong(bundle.getString("placementid")));
        }
        inMobiBanner.setEnableAutoRefresh(false);
        inMobiBanner.setAnimationType(AnimationType.ANIMATION_OFF);
        if (mediationAdRequest.getKeywords() != null) {
            inMobiBanner.setKeywords(TextUtils.join(", ", mediationAdRequest.getKeywords()));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("tp", "c_admob");
        isTaggedForChildDirectedTreatment(mediationAdRequest, hashMap);
        inMobiBanner.setExtras(hashMap);
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        inMobiBanner.setListener((BannerAdListener) new BannerAdListener() {
            public void onUserLeftApplication(InMobiBanner inMobiBanner) {
                Log.d("InMobiAdapter", "onUserLeftApplication");
                InMobiAdapter.this.bannerListener.onAdLeftApplication(InMobiAdapter.this);
            }

            public void onAdRewardActionCompleted(InMobiBanner inMobiBanner, Map<Object, Object> map) {
                Log.d("InMobiAdapter", "InMobi Banner onRewardActionCompleted.");
                if (map != null) {
                    for (Object obj : map.keySet()) {
                        String obj2 = obj.toString();
                        String obj3 = map.get(obj2).toString();
                        StringBuilder sb = new StringBuilder();
                        sb.append(obj2);
                        sb.append(":");
                        sb.append(obj3);
                        Log.d("Rewards: ", sb.toString());
                    }
                }
            }

            public void onAdLoadSucceeded(InMobiBanner inMobiBanner) {
                System.out.println("onLoadSucceeded");
                Log.d("InMobiAdapter", "onAdLoadSucceeded");
                InMobiAdapter.this.bannerListener.onAdLoaded(InMobiAdapter.this);
            }

            public void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass5.$SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiAdapter.this.bannerListener.onAdFailedToLoad(InMobiAdapter.this, 0);
                        break;
                    case 2:
                        InMobiAdapter.this.bannerListener.onAdFailedToLoad(InMobiAdapter.this, 1);
                        break;
                    case 3:
                        InMobiAdapter.this.bannerListener.onAdFailedToLoad(InMobiAdapter.this, 2);
                        break;
                    case 4:
                        InMobiAdapter.this.bannerListener.onAdFailedToLoad(InMobiAdapter.this, 3);
                        break;
                    default:
                        InMobiAdapter.this.bannerListener.onAdFailedToLoad(InMobiAdapter.this, 0);
                        break;
                }
                Log.d("InMobiBanner", inMobiAdRequestStatus.getMessage());
            }

            public void onAdDisplayed(InMobiBanner inMobiBanner) {
                Log.d("InMobiAdapter", "onAdDismissed");
                InMobiAdapter.this.bannerListener.onAdOpened(InMobiAdapter.this);
            }

            public void onAdDismissed(InMobiBanner inMobiBanner) {
                Log.d("InMobiAdapter", "onAdDismissed");
                InMobiAdapter.this.bannerListener.onAdClosed(InMobiAdapter.this);
            }

            public void onAdInteraction(InMobiBanner inMobiBanner, Map<Object, Object> map) {
                Log.d("onBannerInteraction", "onBannerInteraction called");
                InMobiAdapter.this.bannerListener.onAdClicked(InMobiAdapter.this);
            }
        });
        if (disableHardwareFlag.booleanValue()) {
            inMobiBanner.disableHardwareAcceleration();
        }
        this.wrappedAdView = new FrameLayout(context);
        this.wrappedAdView.setLayoutParams(layoutParams);
        inMobiBanner.setLayoutParams(new LinearLayout.LayoutParams(adSize.getWidthInPixels(context), adSize.getHeightInPixels(context)));
        this.wrappedAdView.addView(inMobiBanner);
        InMobiAdapterUtils.buildAdRequest(mediationAdRequest, bundle2);
        inMobiBanner.load();
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        if (!isAppInitialized.booleanValue()) {
            InMobiSdk.init(context, bundle.getString("accountid"));
            isAppInitialized = Boolean.valueOf(true);
        }
        if (VERSION.SDK_INT < 14) {
            mediationInterstitialListener.onAdFailedToLoad(this, 1);
            return;
        }
        this.interstitialListener = mediationInterstitialListener;
        this.adInterstitial = new InMobiInterstitial(context, Long.parseLong(bundle.getString("placementid")), (InterstitialAdListener2) new InterstitialAdListener2() {
            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "onUserLeftApplication");
                InMobiAdapter.this.interstitialListener.onAdLeftApplication(InMobiAdapter.this);
            }

            public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d("InMobiAdapter", "InMobi Interstitial onRewardActionCompleted.");
                if (map != null) {
                    for (Object obj : map.keySet()) {
                        String obj2 = obj.toString();
                        String obj3 = map.get(obj2).toString();
                        StringBuilder sb = new StringBuilder();
                        sb.append(obj2);
                        sb.append(":");
                        sb.append(obj3);
                        Log.d("Rewards: ", sb.toString());
                    }
                }
            }

            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "Ad Display failed.");
            }

            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "Ad Will Display.");
            }

            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "onAdLoadSucceeded");
                InMobiAdapter.this.interstitialListener.onAdLoaded(InMobiAdapter.this);
            }

            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass5.$SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiAdapter.this.interstitialListener.onAdFailedToLoad(InMobiAdapter.this, 0);
                        break;
                    case 2:
                        InMobiAdapter.this.interstitialListener.onAdFailedToLoad(InMobiAdapter.this, 1);
                        break;
                    case 3:
                        InMobiAdapter.this.interstitialListener.onAdFailedToLoad(InMobiAdapter.this, 2);
                        break;
                    case 4:
                        InMobiAdapter.this.interstitialListener.onAdFailedToLoad(InMobiAdapter.this, 3);
                        break;
                    default:
                        InMobiAdapter.this.interstitialListener.onAdFailedToLoad(InMobiAdapter.this, 0);
                        break;
                }
                Log.d("InMobiAdapter", "onAdLoadFailed");
            }

            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "InMobi Ad server responded with an Ad.");
            }

            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "onAdDisplayed");
                InMobiAdapter.this.interstitialListener.onAdOpened(InMobiAdapter.this);
            }

            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "onAdDismissed");
                InMobiAdapter.this.interstitialListener.onAdClosed(InMobiAdapter.this);
            }

            public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d("InMobiAdapter", "InterstitialInteraction");
                InMobiAdapter.this.interstitialListener.onAdClicked(InMobiAdapter.this);
            }
        });
        if (mediationAdRequest.getKeywords() != null) {
            this.adInterstitial.setKeywords(TextUtils.join(", ", mediationAdRequest.getKeywords()));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("tp", "c_admob");
        isTaggedForChildDirectedTreatment(mediationAdRequest, hashMap);
        this.adInterstitial.setExtras(hashMap);
        if (disableHardwareFlag.booleanValue()) {
            this.adInterstitial.disableHardwareAcceleration();
        }
        InMobiAdapterUtils.buildAdRequest(mediationAdRequest, bundle2);
        this.adInterstitial.load();
    }

    private void isTaggedForChildDirectedTreatment(MediationAdRequest mediationAdRequest, HashMap<String, String> hashMap) {
        if (mediationAdRequest.taggedForChildDirectedTreatment() == 1) {
            hashMap.put("coppa", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        } else {
            hashMap.put("coppa", "0");
        }
    }

    public void initialize(Context context, MediationAdRequest mediationAdRequest, String str, MediationRewardedVideoAdListener mediationRewardedVideoAdListener, Bundle bundle, Bundle bundle2) {
        Log.d("InMobiAdapter", "initialize called from InMobiAdapter.");
        this.rewardedVideoAdListener = mediationRewardedVideoAdListener;
        String string = bundle.getString("accountid");
        if (!isAppInitialized.booleanValue()) {
            InMobiSdk.init(context, string);
            isAppInitialized = Boolean.valueOf(true);
        }
        this.adRewarded = new InMobiInterstitial(context, Long.parseLong(bundle.getString("placementid")), (InterstitialAdListener2) new InterstitialAdListener2() {
            public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d("InMobiAdapter", "InMobi RewardedVideo onRewardActionCompleted.");
                if (map != null) {
                    for (Object obj : map.keySet()) {
                        InMobiAdapter.this.key = obj.toString();
                        InMobiAdapter inMobiAdapter = InMobiAdapter.this;
                        inMobiAdapter.value = map.get(inMobiAdapter.key).toString();
                        StringBuilder sb = new StringBuilder();
                        sb.append(InMobiAdapter.this.key);
                        sb.append(":");
                        sb.append(InMobiAdapter.this.value);
                        Log.d("Rewards: ", sb.toString());
                    }
                }
                InMobiAdapter.this.rewardedVideoAdListener.onRewarded(InMobiAdapter.this, new RewardItem() {
                    public String getType() {
                        return InMobiAdapter.this.key;
                    }

                    public int getAmount() {
                        if (InMobiAdapter.this.value == null || "".equalsIgnoreCase(InMobiAdapter.this.value)) {
                            return 0;
                        }
                        try {
                            return Integer.parseInt(InMobiAdapter.this.value);
                        } catch (NumberFormatException e) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Reward value should be of type integer:");
                            sb.append(e.getMessage());
                            Log.e("InMobiAdapter", sb.toString());
                            e.printStackTrace();
                            return 0;
                        }
                    }
                });
            }

            public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "Ad Display failed.");
            }

            public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "Ad Will Display.");
            }

            public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "onAdDisplayed");
                InMobiAdapter.this.rewardedVideoAdListener.onAdOpened(InMobiAdapter.this);
                InMobiAdapter.this.rewardedVideoAdListener.onVideoStarted(InMobiAdapter.this);
            }

            public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "onAdDismissed");
                InMobiAdapter.this.rewardedVideoAdListener.onAdClosed(InMobiAdapter.this);
            }

            public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {
                Log.d("InMobiAdapter", "onInterstitialInteraction called");
                InMobiAdapter.this.rewardedVideoAdListener.onAdClicked(InMobiAdapter.this);
            }

            public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "onAdLoadSucceeded");
                InMobiAdapter.this.rewardedVideoAdListener.onAdLoaded(InMobiAdapter.this);
            }

            public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass5.$SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiAdapter.this.rewardedVideoAdListener.onAdFailedToLoad(InMobiAdapter.this, 0);
                        break;
                    case 2:
                        InMobiAdapter.this.rewardedVideoAdListener.onAdFailedToLoad(InMobiAdapter.this, 1);
                        break;
                    case 3:
                        InMobiAdapter.this.rewardedVideoAdListener.onAdFailedToLoad(InMobiAdapter.this, 2);
                        break;
                    case 4:
                        InMobiAdapter.this.rewardedVideoAdListener.onAdFailedToLoad(InMobiAdapter.this, 3);
                        break;
                    default:
                        InMobiAdapter.this.rewardedVideoAdListener.onAdFailedToLoad(InMobiAdapter.this, 0);
                        break;
                }
                Log.d("InMobiAdapter", "onAdLoadFailed");
            }

            public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "InMobi Ad server responded with an Ad.");
            }

            public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {
                Log.d("InMobiAdapter", "onUserLeftApplication");
                InMobiAdapter.this.rewardedVideoAdListener.onAdLeftApplication(InMobiAdapter.this);
            }
        });
        this.rewardedVideoAdListener.onInitializationSucceeded(this);
        if (mediationAdRequest.getKeywords() != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("keyword is present:");
            sb.append(mediationAdRequest.getKeywords().toString());
            Log.d("InMobiAdapter", sb.toString());
            this.adRewarded.setKeywords(TextUtils.join(", ", mediationAdRequest.getKeywords()));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("tp", "c_admob");
        isTaggedForChildDirectedTreatment(mediationAdRequest, hashMap);
        this.adRewarded.setExtras(hashMap);
        if (disableHardwareFlag.booleanValue()) {
            this.adRewarded.disableHardwareAcceleration();
        }
        InMobiAdapterUtils.buildAdRequest(mediationAdRequest, bundle2);
    }

    public void loadAd(MediationAdRequest mediationAdRequest, Bundle bundle, Bundle bundle2) {
        InMobiInterstitial inMobiInterstitial = this.adRewarded;
        if (inMobiInterstitial != null) {
            inMobiInterstitial.load();
        }
    }

    public void showVideo() {
        if (this.adRewarded.isReady()) {
            this.adRewarded.show();
        }
    }

    public boolean isInitialized() {
        return isAppInitialized.booleanValue();
    }

    public void requestNativeAd(Context context, MediationNativeListener mediationNativeListener, Bundle bundle, final NativeMediationAdRequest nativeMediationAdRequest, Bundle bundle2) {
        this._nativeMedAdReq = nativeMediationAdRequest;
        boolean z = true;
        if (!isAppInitialized.booleanValue() && bundle != null) {
            InMobiSdk.init(context, bundle.getString("accountid"));
            isAppInitialized = Boolean.valueOf(true);
        }
        if (VERSION.SDK_INT < 14) {
            mediationNativeListener.onAdFailedToLoad(this, 1);
            return;
        }
        this.nativeListener = mediationNativeListener;
        if (!nativeMediationAdRequest.isAppInstallAdRequested() || !nativeMediationAdRequest.isContentAdRequested()) {
            z = false;
        }
        if (!Boolean.valueOf(z).booleanValue()) {
            this.nativeListener.onAdFailedToLoad(this, 3);
            return;
        }
        this.mInMobiNativeAdListener = new NativeAdListener() {
            public void onAdFullScreenWillDisplay(InMobiNative inMobiNative) {
            }

            public void onAdStatusChanged(@NonNull InMobiNative inMobiNative) {
            }

            public void onMediaPlaybackComplete(@NonNull InMobiNative inMobiNative) {
            }

            public void onAdLoadSucceeded(InMobiNative inMobiNative) {
                System.out.println(" [ InMobi Native Ad ] : onAdLoadSucceeded ");
                Log.d("InMobiAdapter", "onAdLoadSucceeded");
                if (inMobiNative != null) {
                    NativeAdOptions nativeAdOptions = InMobiAdapter.this.self._nativeMedAdReq.getNativeAdOptions();
                    if (nativeAdOptions != null) {
                        InMobiAdapter.this.isOnlyUrl = Boolean.valueOf(nativeAdOptions.shouldReturnUrlsForImageAssets());
                    }
                    InMobiAdapter inMobiAdapter = InMobiAdapter.this;
                    new InMobiAppInstallNativeAdMapper(inMobiAdapter, inMobiNative, inMobiAdapter.isOnlyUrl, InMobiAdapter.this.nativeListener).mapAppInstallAd();
                    InMobiAdapter.STATIC_MAP.remove(Integer.valueOf(nativeMediationAdRequest.hashCode()));
                }
            }

            public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
                switch (AnonymousClass5.$SwitchMap$com$inmobi$ads$InMobiAdRequestStatus$StatusCode[inMobiAdRequestStatus.getStatusCode().ordinal()]) {
                    case 1:
                        InMobiAdapter.this.nativeListener.onAdFailedToLoad(InMobiAdapter.this, 0);
                        break;
                    case 2:
                        InMobiAdapter.this.nativeListener.onAdFailedToLoad(InMobiAdapter.this, 1);
                        break;
                    case 3:
                        InMobiAdapter.this.nativeListener.onAdFailedToLoad(InMobiAdapter.this, 2);
                        break;
                    case 4:
                        InMobiAdapter.this.nativeListener.onAdFailedToLoad(InMobiAdapter.this, 3);
                        break;
                    default:
                        InMobiAdapter.this.nativeListener.onAdFailedToLoad(InMobiAdapter.this, 0);
                        break;
                }
                Log.d(" InMobiNativeAd ", inMobiAdRequestStatus.getMessage());
                InMobiAdapter.STATIC_MAP.remove(Integer.valueOf(nativeMediationAdRequest.hashCode()));
            }

            public void onAdFullScreenDismissed(InMobiNative inMobiNative) {
                Log.d("InMobiAdapter", "onAdDismissed");
                InMobiAdapter.this.nativeListener.onAdClosed(InMobiAdapter.this);
            }

            public void onAdFullScreenDisplayed(InMobiNative inMobiNative) {
                Log.d("InMobiAdapter", "onAdDisplayed");
                InMobiAdapter.this.nativeListener.onAdOpened(InMobiAdapter.this);
            }

            public void onUserWillLeaveApplication(InMobiNative inMobiNative) {
                Log.d("InMobiAdapter", "onUserLeftApplication");
                InMobiAdapter.this.nativeListener.onAdLeftApplication(InMobiAdapter.this);
            }

            public void onAdImpressed(@NonNull InMobiNative inMobiNative) {
                Log.d("InMobiAdapter", "onAdImpressed");
                InMobiAdapter.this.nativeListener.onAdImpression(InMobiAdapter.this);
            }

            public void onAdClicked(@NonNull InMobiNative inMobiNative) {
                Log.d("InMobiAdapter", "onAdClicked");
                InMobiAdapter.this.nativeListener.onAdClicked(InMobiAdapter.this);
            }
        };
        InMobiNative inMobiNative = new InMobiNative(context, Long.parseLong(bundle.getString("placementid")), this.mInMobiNativeAdListener);
        STATIC_MAP.putIfAbsent(Integer.valueOf(nativeMediationAdRequest.hashCode()), inMobiNative);
        Set keywords = nativeMediationAdRequest.getKeywords();
        if (keywords != null) {
            inMobiNative.setKeywords(TextUtils.join(", ", keywords));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("tp", "c_admob");
        isTaggedForChildDirectedTreatment(nativeMediationAdRequest, hashMap);
        inMobiNative.setExtras(hashMap);
        InMobiAdapterUtils.buildAdRequest(nativeMediationAdRequest, bundle2);
        inMobiNative.load();
    }
}
