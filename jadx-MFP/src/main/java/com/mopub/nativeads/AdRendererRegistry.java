package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Preconditions;
import java.util.ArrayList;
import java.util.Iterator;

public class AdRendererRegistry {
    @NonNull
    private final ArrayList<MoPubAdRenderer> mMoPubAdRenderers = new ArrayList<>();

    public void registerAdRenderer(@NonNull MoPubAdRenderer moPubAdRenderer) {
        this.mMoPubAdRenderers.add(moPubAdRenderer);
    }

    public int getAdRendererCount() {
        return this.mMoPubAdRenderers.size();
    }

    @NonNull
    public Iterable<MoPubAdRenderer> getRendererIterable() {
        return this.mMoPubAdRenderers;
    }

    public int getViewTypeForAd(@NonNull NativeAd nativeAd) {
        Preconditions.checkNotNull(nativeAd);
        for (int i = 0; i < this.mMoPubAdRenderers.size(); i++) {
            if (nativeAd.getMoPubAdRenderer() == this.mMoPubAdRenderers.get(i)) {
                return i + 1;
            }
        }
        return 0;
    }

    @Nullable
    public MoPubAdRenderer getRendererForAd(@NonNull BaseNativeAd baseNativeAd) {
        Preconditions.checkNotNull(baseNativeAd);
        Iterator it = this.mMoPubAdRenderers.iterator();
        while (it.hasNext()) {
            MoPubAdRenderer moPubAdRenderer = (MoPubAdRenderer) it.next();
            if (moPubAdRenderer.supports(baseNativeAd)) {
                return moPubAdRenderer;
            }
        }
        return null;
    }

    @Nullable
    public MoPubAdRenderer getRendererForViewType(int i) {
        try {
            return (MoPubAdRenderer) this.mMoPubAdRenderers.get(i - 1);
        } catch (IndexOutOfBoundsException unused) {
            return null;
        }
    }
}
