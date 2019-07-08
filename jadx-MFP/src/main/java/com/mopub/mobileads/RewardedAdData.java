package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

class RewardedAdData {
    @NonNull
    private final Map<String, Set<MoPubReward>> mAdUnitToAvailableRewardsMap = new TreeMap();
    @NonNull
    private final Map<String, String> mAdUnitToCustomDataMap = new TreeMap();
    @NonNull
    private final Map<String, CustomEventRewardedAd> mAdUnitToCustomEventMap = new TreeMap();
    @NonNull
    private final Map<String, MoPubReward> mAdUnitToRewardMap = new TreeMap();
    @NonNull
    private final Map<String, String> mAdUnitToServerCompletionUrlMap = new TreeMap();
    @Nullable
    private String mCurrentlyShowingAdUnitId;
    @NonNull
    private final Map<TwoPartKey, Set<String>> mCustomEventToMoPubIdMap = new HashMap();
    @NonNull
    private final Map<Class<? extends CustomEventRewardedAd>, MoPubReward> mCustomEventToRewardMap = new HashMap();
    @Nullable
    private String mCustomerId;

    private static class TwoPartKey extends Pair<Class<? extends CustomEventRewardedAd>, String> {
        @NonNull
        final String adNetworkId;
        @NonNull
        final Class<? extends CustomEventRewardedAd> customEventClass;

        public TwoPartKey(@NonNull Class<? extends CustomEventRewardedAd> cls, @NonNull String str) {
            super(cls, str);
            this.customEventClass = cls;
            this.adNetworkId = str;
        }
    }

    RewardedAdData() {
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public CustomEventRewardedAd getCustomEvent(@Nullable String str) {
        return (CustomEventRewardedAd) this.mAdUnitToCustomEventMap.get(str);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public MoPubReward getMoPubReward(@Nullable String str) {
        return (MoPubReward) this.mAdUnitToRewardMap.get(str);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getCustomData(@Nullable String str) {
        return (String) this.mAdUnitToCustomDataMap.get(str);
    }

    /* access modifiers changed from: 0000 */
    public void addAvailableReward(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        Preconditions.checkNotNull(str);
        if (str2 == null || str3 == null) {
            MoPubLog.e(String.format(Locale.US, "Currency name and amount cannot be null: name = %s, amount = %s", new Object[]{str2, str3}));
            return;
        }
        try {
            int parseInt = Integer.parseInt(str3);
            if (parseInt < 0) {
                MoPubLog.e(String.format(Locale.US, "Currency amount cannot be negative: %s", new Object[]{str3}));
                return;
            }
            if (this.mAdUnitToAvailableRewardsMap.containsKey(str)) {
                ((Set) this.mAdUnitToAvailableRewardsMap.get(str)).add(MoPubReward.success(str2, parseInt));
            } else {
                HashSet hashSet = new HashSet();
                hashSet.add(MoPubReward.success(str2, parseInt));
                this.mAdUnitToAvailableRewardsMap.put(str, hashSet);
            }
        } catch (NumberFormatException unused) {
            MoPubLog.e(String.format(Locale.US, "Currency amount must be an integer: %s", new Object[]{str3}));
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Set<MoPubReward> getAvailableRewards(@NonNull String str) {
        Preconditions.checkNotNull(str);
        Set<MoPubReward> set = (Set) this.mAdUnitToAvailableRewardsMap.get(str);
        return set == null ? Collections.emptySet() : set;
    }

    /* access modifiers changed from: 0000 */
    public void selectReward(@NonNull String str, @NonNull MoPubReward moPubReward) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(moPubReward);
        Set set = (Set) this.mAdUnitToAvailableRewardsMap.get(str);
        if (set == null || set.isEmpty()) {
            MoPubLog.e(String.format(Locale.US, "AdUnit %s does not have any rewards.", new Object[]{str}));
        } else if (!set.contains(moPubReward)) {
            MoPubLog.e(String.format(Locale.US, "Selected reward is invalid for AdUnit %s.", new Object[]{str}));
        } else {
            updateAdUnitRewardMapping(str, moPubReward.getLabel(), Integer.toString(moPubReward.getAmount()));
        }
    }

    /* access modifiers changed from: 0000 */
    public void resetAvailableRewards(@NonNull String str) {
        Preconditions.checkNotNull(str);
        Set set = (Set) this.mAdUnitToAvailableRewardsMap.get(str);
        if (set != null && !set.isEmpty()) {
            set.clear();
        }
    }

    /* access modifiers changed from: 0000 */
    public void resetSelectedReward(@NonNull String str) {
        Preconditions.checkNotNull(str);
        updateAdUnitRewardMapping(str, null, null);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getServerCompletionUrl(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return (String) this.mAdUnitToServerCompletionUrlMap.get(str);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public MoPubReward getLastShownMoPubReward(@NonNull Class<? extends CustomEventRewardedAd> cls) {
        return (MoPubReward) this.mCustomEventToRewardMap.get(cls);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Set<String> getMoPubIdsForAdNetwork(@NonNull Class<? extends CustomEventRewardedAd> cls, @Nullable String str) {
        Set<String> set;
        if (str == null) {
            HashSet hashSet = new HashSet();
            for (Entry entry : this.mCustomEventToMoPubIdMap.entrySet()) {
                if (cls == ((TwoPartKey) entry.getKey()).customEventClass) {
                    hashSet.addAll((Collection) entry.getValue());
                }
            }
            return hashSet;
        }
        TwoPartKey twoPartKey = new TwoPartKey(cls, str);
        if (this.mCustomEventToMoPubIdMap.containsKey(twoPartKey)) {
            set = (Set) this.mCustomEventToMoPubIdMap.get(twoPartKey);
        } else {
            set = Collections.emptySet();
        }
        return set;
    }

    /* access modifiers changed from: 0000 */
    public void updateAdUnitCustomEventMapping(@NonNull String str, @NonNull CustomEventRewardedAd customEventRewardedAd, @NonNull String str2) {
        this.mAdUnitToCustomEventMap.put(str, customEventRewardedAd);
        associateCustomEventWithMoPubId(customEventRewardedAd.getClass(), str2, str);
    }

    /* access modifiers changed from: 0000 */
    public void updateAdUnitRewardMapping(@NonNull String str, @Nullable String str2, @Nullable String str3) {
        Preconditions.checkNotNull(str);
        if (str2 == null || str3 == null) {
            this.mAdUnitToRewardMap.remove(str);
            return;
        }
        try {
            int parseInt = Integer.parseInt(str3);
            if (parseInt < 0) {
                MoPubLog.e(String.format(Locale.US, "Currency amount cannot be negative: %s", new Object[]{str3}));
                return;
            }
            this.mAdUnitToRewardMap.put(str, MoPubReward.success(str2, parseInt));
        } catch (NumberFormatException unused) {
            MoPubLog.e(String.format(Locale.US, "Currency amount must be an integer: %s", new Object[]{str3}));
        }
    }

    /* access modifiers changed from: 0000 */
    public void updateAdUnitToServerCompletionUrlMapping(@NonNull String str, @Nullable String str2) {
        Preconditions.checkNotNull(str);
        this.mAdUnitToServerCompletionUrlMap.put(str, str2);
    }

    /* access modifiers changed from: 0000 */
    public void updateCustomEventLastShownRewardMapping(@NonNull Class<? extends CustomEventRewardedAd> cls, @Nullable MoPubReward moPubReward) {
        Preconditions.checkNotNull(cls);
        this.mCustomEventToRewardMap.put(cls, moPubReward);
    }

    /* access modifiers changed from: 0000 */
    public void associateCustomEventWithMoPubId(@NonNull Class<? extends CustomEventRewardedAd> cls, @NonNull String str, @NonNull String str2) {
        TwoPartKey twoPartKey = new TwoPartKey(cls, str);
        Iterator it = this.mCustomEventToMoPubIdMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Entry entry = (Entry) it.next();
            if (!((TwoPartKey) entry.getKey()).equals(twoPartKey) && ((Set) entry.getValue()).contains(str2)) {
                ((Set) entry.getValue()).remove(str2);
                if (((Set) entry.getValue()).isEmpty()) {
                    it.remove();
                }
            }
        }
        Set set = (Set) this.mCustomEventToMoPubIdMap.get(twoPartKey);
        if (set == null) {
            set = new HashSet();
            this.mCustomEventToMoPubIdMap.put(twoPartKey, set);
        }
        set.add(str2);
    }

    /* access modifiers changed from: 0000 */
    public void setCurrentlyShowingAdUnitId(@Nullable String str) {
        this.mCurrentlyShowingAdUnitId = str;
    }

    /* access modifiers changed from: 0000 */
    public void updateAdUnitToCustomDataMapping(@NonNull String str, @Nullable String str2) {
        NoThrow.checkNotNull(str);
        this.mAdUnitToCustomDataMap.put(str, str2);
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getCurrentlyShowingAdUnitId() {
        return this.mCurrentlyShowingAdUnitId;
    }

    /* access modifiers changed from: 0000 */
    public void setCustomerId(@Nullable String str) {
        this.mCustomerId = str;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public String getCustomerId() {
        return this.mCustomerId;
    }
}
