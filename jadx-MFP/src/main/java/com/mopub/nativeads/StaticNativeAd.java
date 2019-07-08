package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import java.util.HashMap;
import java.util.Map;

public abstract class StaticNativeAd extends BaseNativeAd implements ClickInterface, ImpressionInterface {
    @Nullable
    private String mCallToAction;
    @Nullable
    private String mClickDestinationUrl;
    @NonNull
    private final Map<String, Object> mExtras = new HashMap();
    @Nullable
    private String mIconImageUrl;
    private int mImpressionMinPercentageViewed = 50;
    private int mImpressionMinTimeViewed = 1000;
    private Integer mImpressionMinVisiblePx = null;
    private boolean mImpressionRecorded;
    @Nullable
    private String mMainImageUrl;
    @Nullable
    private String mPrivacyInformationIconClickThroughUrl;
    @Nullable
    private String mPrivacyInformationIconImageUrl;
    @Nullable
    private Double mStarRating;
    @Nullable
    private String mText;
    @Nullable
    private String mTitle;

    public void clear(@NonNull View view) {
    }

    public void handleClick(@NonNull View view) {
    }

    public void prepare(@NonNull View view) {
    }

    public void recordImpression(@NonNull View view) {
    }

    @Nullable
    public final String getTitle() {
        return this.mTitle;
    }

    @Nullable
    public final String getText() {
        return this.mText;
    }

    @Nullable
    public final String getMainImageUrl() {
        return this.mMainImageUrl;
    }

    @Nullable
    public final String getIconImageUrl() {
        return this.mIconImageUrl;
    }

    @Nullable
    public final String getCallToAction() {
        return this.mCallToAction;
    }

    @Nullable
    public final Double getStarRating() {
        return this.mStarRating;
    }

    @Nullable
    public final String getPrivacyInformationIconClickThroughUrl() {
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

    @NonNull
    public final Map<String, Object> getExtras() {
        return new HashMap(this.mExtras);
    }

    @Nullable
    public final String getClickDestinationUrl() {
        return this.mClickDestinationUrl;
    }

    public final void setMainImageUrl(@Nullable String str) {
        this.mMainImageUrl = str;
    }

    public final void setIconImageUrl(@Nullable String str) {
        this.mIconImageUrl = str;
    }

    public final void setClickDestinationUrl(@Nullable String str) {
        this.mClickDestinationUrl = str;
    }

    public final void setCallToAction(@Nullable String str) {
        this.mCallToAction = str;
    }

    public final void setTitle(@Nullable String str) {
        this.mTitle = str;
    }

    public final void setText(@Nullable String str) {
        this.mText = str;
    }

    public final void setStarRating(@Nullable Double d) {
        if (d == null) {
            this.mStarRating = null;
        } else if (d.doubleValue() < 0.0d || d.doubleValue() > 5.0d) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring attempt to set invalid star rating (");
            sb.append(d);
            sb.append("). Must be between ");
            sb.append(0.0d);
            sb.append(" and ");
            sb.append(5.0d);
            sb.append(".");
            MoPubLog.d(sb.toString());
        } else {
            this.mStarRating = d;
        }
    }

    public final void setPrivacyInformationIconClickThroughUrl(@Nullable String str) {
        this.mPrivacyInformationIconClickThroughUrl = str;
    }

    public final void setPrivacyInformationIconImageUrl(@Nullable String str) {
        this.mPrivacyInformationIconImageUrl = str;
    }

    public final void addExtra(@NonNull String str, @Nullable Object obj) {
        if (NoThrow.checkNotNull(str, "addExtra key is not allowed to be null")) {
            this.mExtras.put(str, obj);
        }
    }

    public final void setImpressionMinTimeViewed(int i) {
        if (i > 0) {
            this.mImpressionMinTimeViewed = i;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Ignoring non-positive impressionMinTimeViewed: ");
        sb.append(i);
        MoPubLog.d(sb.toString());
    }

    public final void setImpressionMinPercentageViewed(int i) {
        if (i < 0 || i > 100) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring impressionMinTimeViewed that's not a percent [0, 100]: ");
            sb.append(i);
            MoPubLog.d(sb.toString());
            return;
        }
        this.mImpressionMinPercentageViewed = i;
    }

    public final void setImpressionMinVisiblePx(@Nullable Integer num) {
        if (num == null || num.intValue() <= 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Ignoring null or non-positive impressionMinVisiblePx: ");
            sb.append(num);
            MoPubLog.d(sb.toString());
            return;
        }
        this.mImpressionMinVisiblePx = num;
    }

    public void destroy() {
        invalidate();
    }

    public final int getImpressionMinPercentageViewed() {
        return this.mImpressionMinPercentageViewed;
    }

    public final int getImpressionMinTimeViewed() {
        return this.mImpressionMinTimeViewed;
    }

    public final Integer getImpressionMinVisiblePx() {
        return this.mImpressionMinVisiblePx;
    }

    public final boolean isImpressionRecorded() {
        return this.mImpressionRecorded;
    }

    public final void setImpressionRecorded() {
        this.mImpressionRecorded = true;
    }
}
