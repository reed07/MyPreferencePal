package com.mopub.common;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.util.MoPubCollections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SdkConfiguration {
    @NonNull
    private final String mAdUnitId;
    @NonNull
    private final List<Class<? extends MoPubAdvancedBidder>> mAdvancedBidders;
    @NonNull
    private final MediationSettings[] mMediationSettings;
    @Nullable
    private final List<String> mNetworksToInit;

    public static class Builder {
        @NonNull
        private String adUnitId;
        @NonNull
        private final List<Class<? extends MoPubAdvancedBidder>> advancedBidders = new ArrayList();
        @NonNull
        private MediationSettings[] mediationSettings = new MediationSettings[0];
        @Nullable
        private List<String> networksToInit;

        public Builder(@NonNull String str) {
            this.adUnitId = str;
        }

        public Builder withAdvancedBidder(@NonNull Class<? extends MoPubAdvancedBidder> cls) {
            Preconditions.checkNotNull(cls);
            this.advancedBidders.add(cls);
            return this;
        }

        public Builder withAdvancedBidders(@NonNull Collection<Class<? extends MoPubAdvancedBidder>> collection) {
            NoThrow.checkNotNull(collection);
            MoPubCollections.addAllNonNull((Collection<? super T>) this.advancedBidders, collection);
            return this;
        }

        public Builder withMediationSettings(@NonNull MediationSettings... mediationSettingsArr) {
            Preconditions.checkNotNull(mediationSettingsArr);
            this.mediationSettings = mediationSettingsArr;
            return this;
        }

        public Builder withNetworksToInit(@Nullable List<String> list) {
            if (list == null) {
                return this;
            }
            this.networksToInit = new ArrayList();
            MoPubCollections.addAllNonNull((Collection<? super T>) this.networksToInit, (Collection<T>) list);
            return this;
        }

        public SdkConfiguration build() {
            SdkConfiguration sdkConfiguration = new SdkConfiguration(this.adUnitId, this.advancedBidders, this.mediationSettings, this.networksToInit);
            return sdkConfiguration;
        }
    }

    private SdkConfiguration(@NonNull String str, @NonNull List<Class<? extends MoPubAdvancedBidder>> list, @NonNull MediationSettings[] mediationSettingsArr, @Nullable List<String> list2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(list);
        this.mAdUnitId = str;
        this.mAdvancedBidders = list;
        this.mMediationSettings = mediationSettingsArr;
        this.mNetworksToInit = list2;
    }

    @NonNull
    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    @NonNull
    public List<Class<? extends MoPubAdvancedBidder>> getAdvancedBidders() {
        return Collections.unmodifiableList(this.mAdvancedBidders);
    }

    @NonNull
    public MediationSettings[] getMediationSettings() {
        MediationSettings[] mediationSettingsArr = this.mMediationSettings;
        return (MediationSettings[]) Arrays.copyOf(mediationSettingsArr, mediationSettingsArr.length);
    }

    @Nullable
    public List<String> getNetworksToInit() {
        List<String> list = this.mNetworksToInit;
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }
}
