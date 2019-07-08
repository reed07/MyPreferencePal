package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;

public abstract class BaseNativeAd {
    @NonNull
    private final Set<String> mClickTrackers = new HashSet();
    @NonNull
    private final Set<String> mImpressionTrackers = new HashSet();
    private boolean mInvalidated = false;
    @Nullable
    private NativeEventListener mNativeEventListener;

    public interface NativeEventListener {
        void onAdClicked();

        void onAdImpressed();
    }

    public abstract void clear(@NonNull View view);

    public abstract void destroy();

    public abstract void prepare(@NonNull View view);

    protected BaseNativeAd() {
    }

    public void setNativeEventListener(@Nullable NativeEventListener nativeEventListener) {
        this.mNativeEventListener = nativeEventListener;
    }

    /* access modifiers changed from: protected */
    public final void notifyAdImpressed() {
        NativeEventListener nativeEventListener = this.mNativeEventListener;
        if (nativeEventListener != null) {
            nativeEventListener.onAdImpressed();
        }
    }

    /* access modifiers changed from: protected */
    public final void notifyAdClicked() {
        NativeEventListener nativeEventListener = this.mNativeEventListener;
        if (nativeEventListener != null) {
            nativeEventListener.onAdClicked();
        }
    }

    /* access modifiers changed from: protected */
    public final void addImpressionTrackers(Object obj) throws ClassCastException {
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    addImpressionTracker(jSONArray.getString(i));
                } catch (JSONException unused) {
                    MoPubLog.d("Unable to parse impression trackers.");
                }
            }
            return;
        }
        throw new ClassCastException("Expected impression trackers of type JSONArray.");
    }

    /* access modifiers changed from: protected */
    public final void addClickTrackers(Object obj) throws ClassCastException {
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = (JSONArray) obj;
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    addClickTracker(jSONArray.getString(i));
                } catch (JSONException unused) {
                    MoPubLog.d("Unable to parse click trackers.");
                }
            }
            return;
        }
        throw new ClassCastException("Expected click trackers of type JSONArray.");
    }

    public final void addImpressionTracker(@NonNull String str) {
        if (NoThrow.checkNotNull(str, "impressionTracker url is not allowed to be null")) {
            this.mImpressionTrackers.add(str);
        }
    }

    public final void addClickTracker(@NonNull String str) {
        if (NoThrow.checkNotNull(str, "clickTracker url is not allowed to be null")) {
            this.mClickTrackers.add(str);
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Set<String> getImpressionTrackers() {
        return new HashSet(this.mImpressionTrackers);
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public Set<String> getClickTrackers() {
        return new HashSet(this.mClickTrackers);
    }

    public void invalidate() {
        this.mInvalidated = true;
    }

    public boolean isInvalidated() {
        return this.mInvalidated;
    }
}
