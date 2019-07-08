package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.SdkConfiguration;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ReflectionTarget;
import com.mopub.mobileads.MoPubRewardedVideoManager.RequestParameters;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MoPubRewardedVideos {
    @ReflectionTarget
    private static void initializeRewardedVideo(@NonNull Activity activity, MediationSettings... mediationSettingsArr) {
        Preconditions.checkNotNull(activity);
        MoPubRewardedVideoManager.init(activity, mediationSettingsArr);
    }

    @ReflectionTarget
    private static void initializeRewardedVideo(@NonNull Activity activity, @NonNull List<Class<? extends CustomEventRewardedVideo>> list, MediationSettings... mediationSettingsArr) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(list);
        MoPubRewardedVideoManager.init(activity, mediationSettingsArr);
        MoPubRewardedVideoManager.initNetworks(activity, list);
    }

    @ReflectionTarget
    private static void initializeRewardedVideo(@NonNull Activity activity, @NonNull SdkConfiguration sdkConfiguration) {
        Preconditions.checkNotNull(activity);
        Preconditions.checkNotNull(sdkConfiguration);
        List<String> networksToInit = sdkConfiguration.getNetworksToInit();
        ArrayList arrayList = new ArrayList();
        if (networksToInit != null) {
            for (String str : networksToInit) {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        arrayList.add(Class.forName(str).asSubclass(CustomEventRewardedVideo.class));
                    } catch (ClassNotFoundException unused) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Ignoring unknown class name ");
                        sb.append(str);
                        MoPubLog.w(sb.toString());
                    } catch (ClassCastException unused2) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Unable to cast ");
                        sb2.append(str);
                        sb2.append(" to Class<? extends CustomEventRewardedVideo>.");
                        MoPubLog.w(sb2.toString());
                    }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            initializeRewardedVideo(activity, arrayList, sdkConfiguration.getMediationSettings());
        } else {
            initializeRewardedVideo(activity, sdkConfiguration.getMediationSettings());
        }
    }

    @ReflectionTarget
    public static void setRewardedVideoListener(@Nullable MoPubRewardedVideoListener moPubRewardedVideoListener) {
        MoPubRewardedVideoManager.setVideoListener(moPubRewardedVideoListener);
    }

    @ReflectionTarget
    public static void loadRewardedVideo(@NonNull String str, @Nullable MediationSettings... mediationSettingsArr) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager.loadVideo(str, (RequestParameters) null, mediationSettingsArr);
    }

    @ReflectionTarget
    public static void loadRewardedVideo(@NonNull String str, @Nullable RequestParameters requestParameters, @Nullable MediationSettings... mediationSettingsArr) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager.loadVideo(str, requestParameters, mediationSettingsArr);
    }

    @ReflectionTarget
    public static boolean hasRewardedVideo(@NonNull String str) {
        Preconditions.checkNotNull(str);
        return MoPubRewardedVideoManager.hasVideo(str);
    }

    @ReflectionTarget
    public static void showRewardedVideo(@NonNull String str) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager.showVideo(str);
    }

    @ReflectionTarget
    public static void showRewardedVideo(@NonNull String str, @Nullable String str2) {
        Preconditions.checkNotNull(str);
        MoPubRewardedVideoManager.showVideo(str, str2);
    }

    @ReflectionTarget
    public static Set<MoPubReward> getAvailableRewards(@NonNull String str) {
        Preconditions.checkNotNull(str);
        return MoPubRewardedVideoManager.getAvailableRewards(str);
    }

    @ReflectionTarget
    public static void selectReward(@NonNull String str, @NonNull MoPubReward moPubReward) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(moPubReward);
        MoPubRewardedVideoManager.selectReward(str, moPubReward);
    }
}
