package com.mopub.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.MoPub.BrowserAgent;
import com.mopub.common.Preconditions;
import com.mopub.common.util.DateAndTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class AdResponse implements Serializable {
    private static final long serialVersionUID = 1;
    @Nullable
    private final Integer mAdTimeoutDelayMillis;
    @Nullable
    private final String mAdType;
    @Nullable
    private final String mAdUnitId;
    @NonNull
    private final List<String> mAfterLoadFailUrls;
    @NonNull
    private final List<String> mAfterLoadSuccessUrls;
    @NonNull
    private final List<String> mAfterLoadUrls;
    @Nullable
    private final String mBeforeLoadUrl;
    @Nullable
    private final BrowserAgent mBrowserAgent;
    @Nullable
    private final String mClickTrackingUrl;
    @Nullable
    private final String mCustomEventClassName;
    @Nullable
    private final String mDspCreativeId;
    @Nullable
    private final String mFailoverUrl;
    @Nullable
    private final String mFullAdType;
    @Nullable
    private final Integer mHeight;
    @NonNull
    private final List<String> mImpressionTrackingUrls;
    @Nullable
    private final JSONObject mJsonBody;
    @Nullable
    private final String mNetworkType;
    @Nullable
    private final Integer mRefreshTimeMillis;
    @Nullable
    private final String mRequestId;
    @Nullable
    private final String mResponseBody;
    @Nullable
    private final String mRewardedCurrencies;
    @Nullable
    private final Integer mRewardedDuration;
    @Nullable
    private final String mRewardedVideoCompletionUrl;
    @Nullable
    private final String mRewardedVideoCurrencyAmount;
    @Nullable
    private final String mRewardedVideoCurrencyName;
    @NonNull
    private final Map<String, String> mServerExtras;
    private final boolean mShouldRewardOnClick;
    private final long mTimestamp;
    @Nullable
    private final Integer mWidth;

    public static class Builder {
        /* access modifiers changed from: private */
        public Integer adTimeoutDelayMillis;
        /* access modifiers changed from: private */
        public String adType;
        /* access modifiers changed from: private */
        public String adUnitId;
        /* access modifiers changed from: private */
        public List<String> afterLoadFailUrls = new ArrayList();
        /* access modifiers changed from: private */
        public List<String> afterLoadSuccessUrls = new ArrayList();
        /* access modifiers changed from: private */
        public List<String> afterLoadUrls = new ArrayList();
        /* access modifiers changed from: private */
        public String beforeLoadUrl;
        /* access modifiers changed from: private */
        public BrowserAgent browserAgent;
        /* access modifiers changed from: private */
        public String clickTrackingUrl;
        /* access modifiers changed from: private */
        public String customEventClassName;
        /* access modifiers changed from: private */
        public String dspCreativeId;
        /* access modifiers changed from: private */
        public String failoverUrl;
        /* access modifiers changed from: private */
        public String fullAdType;
        /* access modifiers changed from: private */
        public Integer height;
        /* access modifiers changed from: private */
        public List<String> impressionTrackingUrls = new ArrayList();
        /* access modifiers changed from: private */
        public JSONObject jsonBody;
        /* access modifiers changed from: private */
        public String networkType;
        /* access modifiers changed from: private */
        public Integer refreshTimeMillis;
        /* access modifiers changed from: private */
        public String requestId;
        /* access modifiers changed from: private */
        public String responseBody;
        /* access modifiers changed from: private */
        public String rewardedCurrencies;
        /* access modifiers changed from: private */
        public Integer rewardedDuration;
        /* access modifiers changed from: private */
        public String rewardedVideoCompletionUrl;
        /* access modifiers changed from: private */
        public String rewardedVideoCurrencyAmount;
        /* access modifiers changed from: private */
        public String rewardedVideoCurrencyName;
        /* access modifiers changed from: private */
        public Map<String, String> serverExtras = new TreeMap();
        /* access modifiers changed from: private */
        public boolean shouldRewardOnClick;
        /* access modifiers changed from: private */
        public Integer width;

        public Builder setAdType(@Nullable String str) {
            this.adType = str;
            return this;
        }

        public Builder setAdUnitId(@Nullable String str) {
            this.adUnitId = str;
            return this;
        }

        public Builder setFullAdType(@Nullable String str) {
            this.fullAdType = str;
            return this;
        }

        public Builder setNetworkType(@Nullable String str) {
            this.networkType = str;
            return this;
        }

        public Builder setRewardedVideoCurrencyName(@Nullable String str) {
            this.rewardedVideoCurrencyName = str;
            return this;
        }

        public Builder setRewardedVideoCurrencyAmount(@Nullable String str) {
            this.rewardedVideoCurrencyAmount = str;
            return this;
        }

        public Builder setRewardedCurrencies(@Nullable String str) {
            this.rewardedCurrencies = str;
            return this;
        }

        public Builder setRewardedVideoCompletionUrl(@Nullable String str) {
            this.rewardedVideoCompletionUrl = str;
            return this;
        }

        public Builder setRewardedDuration(@Nullable Integer num) {
            this.rewardedDuration = num;
            return this;
        }

        public Builder setShouldRewardOnClick(boolean z) {
            this.shouldRewardOnClick = z;
            return this;
        }

        public Builder setClickTrackingUrl(@Nullable String str) {
            this.clickTrackingUrl = str;
            return this;
        }

        public Builder setImpressionTrackingUrls(@NonNull List<String> list) {
            Preconditions.checkNotNull(list);
            this.impressionTrackingUrls = list;
            return this;
        }

        public Builder setFailoverUrl(@Nullable String str) {
            this.failoverUrl = str;
            return this;
        }

        public Builder setBeforeLoadUrl(@Nullable String str) {
            this.beforeLoadUrl = str;
            return this;
        }

        public Builder setAfterLoadUrls(@NonNull List<String> list) {
            Preconditions.checkNotNull(list);
            this.afterLoadUrls = list;
            return this;
        }

        public Builder setAfterLoadSuccessUrls(@NonNull List<String> list) {
            Preconditions.checkNotNull(list);
            this.afterLoadSuccessUrls = list;
            return this;
        }

        public Builder setAfterLoadFailUrls(@NonNull List<String> list) {
            Preconditions.checkNotNull(list);
            this.afterLoadFailUrls = list;
            return this;
        }

        public Builder setRequestId(@Nullable String str) {
            this.requestId = str;
            return this;
        }

        public Builder setDimensions(@Nullable Integer num, @Nullable Integer num2) {
            this.width = num;
            this.height = num2;
            return this;
        }

        public Builder setAdTimeoutDelayMilliseconds(@Nullable Integer num) {
            this.adTimeoutDelayMillis = num;
            return this;
        }

        public Builder setRefreshTimeMilliseconds(@Nullable Integer num) {
            this.refreshTimeMillis = num;
            return this;
        }

        public Builder setDspCreativeId(@Nullable String str) {
            this.dspCreativeId = str;
            return this;
        }

        public Builder setResponseBody(@Nullable String str) {
            this.responseBody = str;
            return this;
        }

        public Builder setJsonBody(@Nullable JSONObject jSONObject) {
            this.jsonBody = jSONObject;
            return this;
        }

        public Builder setCustomEventClassName(@Nullable String str) {
            this.customEventClassName = str;
            return this;
        }

        public Builder setBrowserAgent(@Nullable BrowserAgent browserAgent2) {
            this.browserAgent = browserAgent2;
            return this;
        }

        public Builder setServerExtras(@Nullable Map<String, String> map) {
            if (map == null) {
                this.serverExtras = new TreeMap();
            } else {
                this.serverExtras = new TreeMap(map);
            }
            return this;
        }

        public AdResponse build() {
            return new AdResponse(this);
        }
    }

    private AdResponse(@NonNull Builder builder) {
        this.mAdType = builder.adType;
        this.mAdUnitId = builder.adUnitId;
        this.mFullAdType = builder.fullAdType;
        this.mNetworkType = builder.networkType;
        this.mRewardedVideoCurrencyName = builder.rewardedVideoCurrencyName;
        this.mRewardedVideoCurrencyAmount = builder.rewardedVideoCurrencyAmount;
        this.mRewardedCurrencies = builder.rewardedCurrencies;
        this.mRewardedVideoCompletionUrl = builder.rewardedVideoCompletionUrl;
        this.mRewardedDuration = builder.rewardedDuration;
        this.mShouldRewardOnClick = builder.shouldRewardOnClick;
        this.mClickTrackingUrl = builder.clickTrackingUrl;
        this.mImpressionTrackingUrls = builder.impressionTrackingUrls;
        this.mFailoverUrl = builder.failoverUrl;
        this.mBeforeLoadUrl = builder.beforeLoadUrl;
        this.mAfterLoadUrls = builder.afterLoadUrls;
        this.mAfterLoadSuccessUrls = builder.afterLoadSuccessUrls;
        this.mAfterLoadFailUrls = builder.afterLoadFailUrls;
        this.mRequestId = builder.requestId;
        this.mWidth = builder.width;
        this.mHeight = builder.height;
        this.mAdTimeoutDelayMillis = builder.adTimeoutDelayMillis;
        this.mRefreshTimeMillis = builder.refreshTimeMillis;
        this.mDspCreativeId = builder.dspCreativeId;
        this.mResponseBody = builder.responseBody;
        this.mJsonBody = builder.jsonBody;
        this.mCustomEventClassName = builder.customEventClassName;
        this.mBrowserAgent = builder.browserAgent;
        this.mServerExtras = builder.serverExtras;
        this.mTimestamp = DateAndTime.now().getTime();
    }

    public boolean hasJson() {
        return this.mJsonBody != null;
    }

    @Nullable
    public JSONObject getJsonBody() {
        return this.mJsonBody;
    }

    @Nullable
    public String getStringBody() {
        return this.mResponseBody;
    }

    @Nullable
    public String getAdType() {
        return this.mAdType;
    }

    @Nullable
    public String getFullAdType() {
        return this.mFullAdType;
    }

    @Nullable
    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    @Nullable
    public String getNetworkType() {
        return this.mNetworkType;
    }

    @Nullable
    public String getRewardedVideoCurrencyName() {
        return this.mRewardedVideoCurrencyName;
    }

    @Nullable
    public String getRewardedVideoCurrencyAmount() {
        return this.mRewardedVideoCurrencyAmount;
    }

    @Nullable
    public String getRewardedCurrencies() {
        return this.mRewardedCurrencies;
    }

    @Nullable
    public String getRewardedVideoCompletionUrl() {
        return this.mRewardedVideoCompletionUrl;
    }

    @Nullable
    public Integer getRewardedDuration() {
        return this.mRewardedDuration;
    }

    public boolean shouldRewardOnClick() {
        return this.mShouldRewardOnClick;
    }

    @Nullable
    public String getClickTrackingUrl() {
        return this.mClickTrackingUrl;
    }

    @NonNull
    public List<String> getImpressionTrackingUrls() {
        return this.mImpressionTrackingUrls;
    }

    @Nullable
    @Deprecated
    public String getFailoverUrl() {
        return this.mFailoverUrl;
    }

    @Nullable
    public String getBeforeLoadUrl() {
        return this.mBeforeLoadUrl;
    }

    @NonNull
    public List<String> getAfterLoadUrls() {
        return this.mAfterLoadUrls;
    }

    @NonNull
    public List<String> getAfterLoadSuccessUrls() {
        return this.mAfterLoadSuccessUrls;
    }

    @NonNull
    public List<String> getAfterLoadFailUrls() {
        return this.mAfterLoadFailUrls;
    }

    @Nullable
    public String getRequestId() {
        return this.mRequestId;
    }

    @Nullable
    public Integer getWidth() {
        return this.mWidth;
    }

    @Nullable
    public Integer getHeight() {
        return this.mHeight;
    }

    @NonNull
    public Integer getAdTimeoutMillis(int i) {
        Integer num = this.mAdTimeoutDelayMillis;
        if (num == null || num.intValue() < 1000) {
            return Integer.valueOf(i);
        }
        return this.mAdTimeoutDelayMillis;
    }

    @Nullable
    public Integer getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }

    @Nullable
    public String getDspCreativeId() {
        return this.mDspCreativeId;
    }

    @Nullable
    public String getCustomEventClassName() {
        return this.mCustomEventClassName;
    }

    @Nullable
    public BrowserAgent getBrowserAgent() {
        return this.mBrowserAgent;
    }

    @NonNull
    public Map<String, String> getServerExtras() {
        return new TreeMap(this.mServerExtras);
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public Builder toBuilder() {
        return new Builder().setAdType(this.mAdType).setNetworkType(this.mNetworkType).setRewardedVideoCurrencyName(this.mRewardedVideoCurrencyName).setRewardedVideoCurrencyAmount(this.mRewardedVideoCurrencyAmount).setRewardedCurrencies(this.mRewardedCurrencies).setRewardedVideoCompletionUrl(this.mRewardedVideoCompletionUrl).setRewardedDuration(this.mRewardedDuration).setShouldRewardOnClick(this.mShouldRewardOnClick).setClickTrackingUrl(this.mClickTrackingUrl).setImpressionTrackingUrls(this.mImpressionTrackingUrls).setFailoverUrl(this.mFailoverUrl).setBeforeLoadUrl(this.mBeforeLoadUrl).setAfterLoadUrls(this.mAfterLoadUrls).setAfterLoadSuccessUrls(this.mAfterLoadSuccessUrls).setAfterLoadFailUrls(this.mAfterLoadFailUrls).setDimensions(this.mWidth, this.mHeight).setAdTimeoutDelayMilliseconds(this.mAdTimeoutDelayMillis).setRefreshTimeMilliseconds(this.mRefreshTimeMillis).setDspCreativeId(this.mDspCreativeId).setResponseBody(this.mResponseBody).setJsonBody(this.mJsonBody).setCustomEventClassName(this.mCustomEventClassName).setBrowserAgent(this.mBrowserAgent).setServerExtras(this.mServerExtras);
    }
}
