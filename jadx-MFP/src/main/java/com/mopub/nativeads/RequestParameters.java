package com.mopub.nativeads;

import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.MoPub;
import java.util.EnumSet;

public class RequestParameters {
    @Nullable
    private final EnumSet<NativeAdAsset> mDesiredAssets;
    @Nullable
    private final String mKeywords;
    @Nullable
    private final Location mLocation;
    @Nullable
    private final String mUserDataKeywords;

    public static final class Builder {
        /* access modifiers changed from: private */
        public EnumSet<NativeAdAsset> desiredAssets;
        /* access modifiers changed from: private */
        public String keywords;
        /* access modifiers changed from: private */
        public Location location;
        /* access modifiers changed from: private */
        public String userDatakeywords;

        @NonNull
        public final Builder keywords(String str) {
            this.keywords = str;
            return this;
        }

        @NonNull
        public final Builder userDataKeywords(String str) {
            if (!MoPub.canCollectPersonalInformation()) {
                str = null;
            }
            this.userDatakeywords = str;
            return this;
        }

        @NonNull
        public final Builder location(Location location2) {
            if (!MoPub.canCollectPersonalInformation()) {
                location2 = null;
            }
            this.location = location2;
            return this;
        }

        @NonNull
        public final Builder desiredAssets(EnumSet<NativeAdAsset> enumSet) {
            this.desiredAssets = EnumSet.copyOf(enumSet);
            return this;
        }

        @NonNull
        public final RequestParameters build() {
            return new RequestParameters(this);
        }
    }

    public enum NativeAdAsset {
        TITLE("title"),
        TEXT("text"),
        ICON_IMAGE("iconimage"),
        MAIN_IMAGE("mainimage"),
        CALL_TO_ACTION_TEXT("ctatext"),
        STAR_RATING("starrating");
        
        private final String mAssetName;

        private NativeAdAsset(String str) {
            this.mAssetName = str;
        }

        @NonNull
        public String toString() {
            return this.mAssetName;
        }
    }

    private RequestParameters(@NonNull Builder builder) {
        this.mKeywords = builder.keywords;
        this.mDesiredAssets = builder.desiredAssets;
        boolean canCollectPersonalInformation = MoPub.canCollectPersonalInformation();
        Location location = null;
        this.mUserDataKeywords = canCollectPersonalInformation ? builder.userDatakeywords : null;
        if (canCollectPersonalInformation) {
            location = builder.location;
        }
        this.mLocation = location;
    }

    @Nullable
    public final String getKeywords() {
        return this.mKeywords;
    }

    @Nullable
    public final String getUserDataKeywords() {
        if (!MoPub.canCollectPersonalInformation()) {
            return null;
        }
        return this.mUserDataKeywords;
    }

    @Nullable
    public final Location getLocation() {
        return this.mLocation;
    }

    public final String getDesiredAssets() {
        String str = "";
        EnumSet<NativeAdAsset> enumSet = this.mDesiredAssets;
        return enumSet != null ? TextUtils.join(",", enumSet.toArray()) : str;
    }
}
