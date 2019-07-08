package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.nativeads.NativeVideoController.Listener;
import java.util.HashMap;
import java.util.Map;

public abstract class VideoNativeAd extends BaseNativeAd implements Listener {
    @Nullable
    private String mCallToAction;
    @Nullable
    private String mClickDestinationUrl;
    @NonNull
    private final Map<String, Object> mExtras = new HashMap();
    @Nullable
    private String mIconImageUrl;
    @Nullable
    private String mMainImageUrl;
    @Nullable
    private String mPrivacyInformationIconClickThroughUrl;
    @Nullable
    private String mPrivacyInformationIconImageUrl;
    @Nullable
    private String mText;
    @Nullable
    private String mTitle;
    @Nullable
    private String mVastVideo;

    public void clear(@NonNull View view) {
    }

    public void destroy() {
    }

    public void prepare(@NonNull View view) {
    }

    public void render(@NonNull MediaLayout mediaLayout) {
    }

    @Nullable
    public String getTitle() {
        return this.mTitle;
    }

    @Nullable
    public String getText() {
        return this.mText;
    }

    @Nullable
    public String getMainImageUrl() {
        return this.mMainImageUrl;
    }

    @Nullable
    public String getIconImageUrl() {
        return this.mIconImageUrl;
    }

    @Nullable
    public String getClickDestinationUrl() {
        return this.mClickDestinationUrl;
    }

    @Nullable
    public String getVastVideo() {
        return this.mVastVideo;
    }

    @Nullable
    public String getCallToAction() {
        return this.mCallToAction;
    }

    @Nullable
    public String getPrivacyInformationIconClickThroughUrl() {
        return this.mPrivacyInformationIconClickThroughUrl;
    }

    @Nullable
    public String getPrivacyInformationIconImageUrl() {
        return this.mPrivacyInformationIconImageUrl;
    }

    @Nullable
    public final Object getExtra(@NonNull String str) {
        if (!NoThrow.checkNotNull(str, "getExtra key is not allowed to be null")) {
            return null;
        }
        return this.mExtras.get(str);
    }

    public final Map<String, Object> getExtras() {
        return this.mExtras;
    }

    public void setTitle(@Nullable String str) {
        this.mTitle = str;
    }

    public void setText(@Nullable String str) {
        this.mText = str;
    }

    public void setMainImageUrl(@Nullable String str) {
        this.mMainImageUrl = str;
    }

    public void setIconImageUrl(@Nullable String str) {
        this.mIconImageUrl = str;
    }

    public void setClickDestinationUrl(@Nullable String str) {
        this.mClickDestinationUrl = str;
    }

    public void setVastVideo(String str) {
        this.mVastVideo = str;
    }

    public void setCallToAction(@Nullable String str) {
        this.mCallToAction = str;
    }

    public void setPrivacyInformationIconClickThroughUrl(@Nullable String str) {
        this.mPrivacyInformationIconClickThroughUrl = str;
    }

    public void setPrivacyInformationIconImageUrl(@Nullable String str) {
        this.mPrivacyInformationIconImageUrl = str;
    }

    public final void addExtra(@NonNull String str, @Nullable Object obj) {
        if (NoThrow.checkNotNull(str, "addExtra key is not allowed to be null")) {
            this.mExtras.put(str, obj);
        }
    }
}
