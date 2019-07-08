package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Numbers;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.NativeImageHelper.ImageListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class MoPubCustomEventNative extends CustomEventNative {
    private MoPubStaticNativeAd moPubStaticNativeAd;

    static class MoPubStaticNativeAd extends StaticNativeAd {
        @NonNull
        private final Context mContext;
        /* access modifiers changed from: private */
        @NonNull
        public final CustomEventNativeListener mCustomEventNativeListener;
        @NonNull
        private final ImpressionTracker mImpressionTracker;
        @NonNull
        private final JSONObject mJsonObject;
        @NonNull
        private final NativeClickHandler mNativeClickHandler;

        enum Parameter {
            IMPRESSION_TRACKER("imptracker", true),
            CLICK_TRACKER("clktracker", true),
            TITLE("title", false),
            TEXT("text", false),
            MAIN_IMAGE("mainimage", false),
            ICON_IMAGE("iconimage", false),
            CLICK_DESTINATION("clk", false),
            FALLBACK("fallback", false),
            CALL_TO_ACTION("ctatext", false),
            STAR_RATING("starrating", false),
            PRIVACY_INFORMATION_ICON_IMAGE_URL("privacyicon", false),
            PRIVACY_INFORMATION_ICON_CLICKTHROUGH_URL("privacyclkurl", false);
            
            @NonNull
            @VisibleForTesting
            static final Set<String> requiredKeys = null;
            @NonNull
            final String name;
            final boolean required;

            static {
                int i;
                Parameter[] values;
                requiredKeys = new HashSet();
                for (Parameter parameter : values()) {
                    if (parameter.required) {
                        requiredKeys.add(parameter.name);
                    }
                }
            }

            private Parameter(String str, boolean z) {
                this.name = str;
                this.required = z;
            }

            @Nullable
            static Parameter from(@NonNull String str) {
                Parameter[] values;
                for (Parameter parameter : values()) {
                    if (parameter.name.equals(str)) {
                        return parameter;
                    }
                }
                return null;
            }
        }

        MoPubStaticNativeAd(@NonNull Context context, @NonNull JSONObject jSONObject, @NonNull ImpressionTracker impressionTracker, @NonNull NativeClickHandler nativeClickHandler, @NonNull CustomEventNativeListener customEventNativeListener) {
            this.mJsonObject = jSONObject;
            this.mContext = context.getApplicationContext();
            this.mImpressionTracker = impressionTracker;
            this.mNativeClickHandler = nativeClickHandler;
            this.mCustomEventNativeListener = customEventNativeListener;
        }

        /* access modifiers changed from: 0000 */
        public void loadAd() throws IllegalArgumentException {
            if (containsRequiredKeys(this.mJsonObject)) {
                Iterator keys = this.mJsonObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    Parameter from = Parameter.from(str);
                    if (from != null) {
                        try {
                            addInstanceVariable(from, this.mJsonObject.opt(str));
                        } catch (ClassCastException unused) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("JSONObject key (");
                            sb.append(str);
                            sb.append(") contained unexpected value.");
                            throw new IllegalArgumentException(sb.toString());
                        }
                    } else {
                        addExtra(str, this.mJsonObject.opt(str));
                    }
                }
                if (TextUtils.isEmpty(getPrivacyInformationIconClickThroughUrl())) {
                    setPrivacyInformationIconClickThroughUrl("https://www.mopub.com/optout");
                }
                NativeImageHelper.preCacheImages(this.mContext, getAllImageUrls(), new ImageListener() {
                    public void onImagesCached() {
                        if (!MoPubStaticNativeAd.this.isInvalidated()) {
                            MoPubStaticNativeAd.this.mCustomEventNativeListener.onNativeAdLoaded(MoPubStaticNativeAd.this);
                        }
                    }

                    public void onImagesFailedToCache(NativeErrorCode nativeErrorCode) {
                        if (!MoPubStaticNativeAd.this.isInvalidated()) {
                            MoPubStaticNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(nativeErrorCode);
                        }
                    }
                });
                return;
            }
            throw new IllegalArgumentException("JSONObject did not contain required keys.");
        }

        private boolean containsRequiredKeys(@NonNull JSONObject jSONObject) {
            HashSet hashSet = new HashSet();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                hashSet.add(keys.next());
            }
            return hashSet.containsAll(Parameter.requiredKeys);
        }

        private void addInstanceVariable(@NonNull Parameter parameter, @Nullable Object obj) throws ClassCastException {
            try {
                switch (parameter) {
                    case MAIN_IMAGE:
                        setMainImageUrl((String) obj);
                        return;
                    case ICON_IMAGE:
                        setIconImageUrl((String) obj);
                        return;
                    case IMPRESSION_TRACKER:
                        addImpressionTrackers(obj);
                        return;
                    case CLICK_DESTINATION:
                        setClickDestinationUrl((String) obj);
                        return;
                    case CLICK_TRACKER:
                        parseClickTrackers(obj);
                        return;
                    case CALL_TO_ACTION:
                        setCallToAction((String) obj);
                        return;
                    case TITLE:
                        setTitle((String) obj);
                        return;
                    case TEXT:
                        setText((String) obj);
                        return;
                    case STAR_RATING:
                        setStarRating(Numbers.parseDouble(obj));
                        return;
                    case PRIVACY_INFORMATION_ICON_IMAGE_URL:
                        setPrivacyInformationIconImageUrl((String) obj);
                        return;
                    case PRIVACY_INFORMATION_ICON_CLICKTHROUGH_URL:
                        setPrivacyInformationIconClickThroughUrl((String) obj);
                        return;
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unable to add JSON key to internal mapping: ");
                        sb.append(parameter.name);
                        MoPubLog.d(sb.toString());
                        return;
                }
            } catch (ClassCastException e) {
                if (!parameter.required) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Ignoring class cast exception for optional key: ");
                    sb2.append(parameter.name);
                    MoPubLog.d(sb2.toString());
                    return;
                }
                throw e;
            }
        }

        private void parseClickTrackers(@NonNull Object obj) {
            if (obj instanceof JSONArray) {
                addClickTrackers(obj);
            } else {
                addClickTracker((String) obj);
            }
        }

        private boolean isImageKey(@Nullable String str) {
            return str != null && str.toLowerCase(Locale.US).endsWith("image");
        }

        /* access modifiers changed from: 0000 */
        @NonNull
        public List<String> getExtrasImageUrls() {
            ArrayList arrayList = new ArrayList(getExtras().size());
            for (Entry entry : getExtras().entrySet()) {
                if (isImageKey((String) entry.getKey()) && (entry.getValue() instanceof String)) {
                    arrayList.add((String) entry.getValue());
                }
            }
            return arrayList;
        }

        /* access modifiers changed from: 0000 */
        @NonNull
        public List<String> getAllImageUrls() {
            ArrayList arrayList = new ArrayList();
            if (!TextUtils.isEmpty(getMainImageUrl())) {
                arrayList.add(getMainImageUrl());
            }
            if (!TextUtils.isEmpty(getIconImageUrl())) {
                arrayList.add(getIconImageUrl());
            }
            if (!TextUtils.isEmpty(getPrivacyInformationIconImageUrl())) {
                arrayList.add(getPrivacyInformationIconImageUrl());
            }
            arrayList.addAll(getExtrasImageUrls());
            return arrayList;
        }

        public void prepare(@NonNull View view) {
            this.mImpressionTracker.addView(view, this);
            this.mNativeClickHandler.setOnClickListener(view, (ClickInterface) this);
        }

        public void clear(@NonNull View view) {
            this.mImpressionTracker.removeView(view);
            this.mNativeClickHandler.clearOnClickListener(view);
        }

        public void destroy() {
            this.mImpressionTracker.destroy();
            super.destroy();
        }

        public void recordImpression(@NonNull View view) {
            notifyAdImpressed();
        }

        public void handleClick(@Nullable View view) {
            notifyAdClicked();
            this.mNativeClickHandler.openClickDestinationUrl(getClickDestinationUrl(), view);
        }
    }

    /* access modifiers changed from: protected */
    public void loadNativeAd(@NonNull Context context, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) {
        MoPubStaticNativeAd moPubStaticNativeAd2 = this.moPubStaticNativeAd;
        if (moPubStaticNativeAd2 == null || moPubStaticNativeAd2.isInvalidated()) {
            Object obj = map.get(DataKeys.JSON_BODY_KEY);
            if (!(obj instanceof JSONObject)) {
                customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
                return;
            }
            MoPubStaticNativeAd moPubStaticNativeAd3 = new MoPubStaticNativeAd(context, (JSONObject) obj, new ImpressionTracker(context), new NativeClickHandler(context), customEventNativeListener);
            this.moPubStaticNativeAd = moPubStaticNativeAd3;
            if (map2.containsKey(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT)) {
                try {
                    this.moPubStaticNativeAd.setImpressionMinPercentageViewed(Integer.parseInt((String) map2.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT)));
                } catch (NumberFormatException unused) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unable to format min visible percent: ");
                    sb.append((String) map2.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT));
                    MoPubLog.d(sb.toString());
                }
            }
            if (map2.containsKey(DataKeys.IMPRESSION_VISIBLE_MS)) {
                try {
                    this.moPubStaticNativeAd.setImpressionMinTimeViewed(Integer.parseInt((String) map2.get(DataKeys.IMPRESSION_VISIBLE_MS)));
                } catch (NumberFormatException unused2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Unable to format min time: ");
                    sb2.append((String) map2.get(DataKeys.IMPRESSION_VISIBLE_MS));
                    MoPubLog.d(sb2.toString());
                }
            }
            if (map2.containsKey(DataKeys.IMPRESSION_MIN_VISIBLE_PX)) {
                try {
                    this.moPubStaticNativeAd.setImpressionMinVisiblePx(Integer.valueOf(Integer.parseInt((String) map2.get(DataKeys.IMPRESSION_MIN_VISIBLE_PX))));
                } catch (NumberFormatException unused3) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Unable to format min visible px: ");
                    sb3.append((String) map2.get(DataKeys.IMPRESSION_MIN_VISIBLE_PX));
                    MoPubLog.d(sb3.toString());
                }
            }
            try {
                this.moPubStaticNativeAd.loadAd();
            } catch (IllegalArgumentException unused4) {
                customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onInvalidate() {
        MoPubStaticNativeAd moPubStaticNativeAd2 = this.moPubStaticNativeAd;
        if (moPubStaticNativeAd2 != null) {
            moPubStaticNativeAd2.invalidate();
        }
    }
}
