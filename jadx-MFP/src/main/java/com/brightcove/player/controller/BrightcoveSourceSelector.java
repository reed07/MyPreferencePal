package com.brightcove.player.controller;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;
import com.brightcove.player.C;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.model.Source;
import com.brightcove.player.model.SourceCollection;
import com.brightcove.player.model.Video;
import com.brightcove.player.util.ErrorUtil;
import com.mopub.common.Constants;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BrightcoveSourceSelector implements SourceSelector {
    private final Integer DEFAULT_BIT_RATE;
    private boolean preferHls;

    private int getMinimumHLSVersion() {
        return 14;
    }

    public BrightcoveSourceSelector() {
        this.preferHls = VERSION.SDK_INT >= getMinimumHLSVersion();
        this.DEFAULT_BIT_RATE = Integer.valueOf(C.DASH_ROLE_SUB_FLAG);
    }

    public boolean isPreferHls() {
        return this.preferHls;
    }

    public void setPreferHls(boolean z) {
        this.preferHls = z;
    }

    @NonNull
    public Source selectSource(Video video) throws NoSourceFoundException {
        Source source;
        if (video != null) {
            Map sourceCollections = video.getSourceCollections();
            if (sourceCollections == null || sourceCollections.size() == 0) {
                throw new NoSourceFoundException();
            }
            Source source2 = null;
            if (this.preferHls) {
                SourceCollection sourceCollection = (SourceCollection) sourceCollections.get(DeliveryType.HLS);
                source = sourceCollection == null ? null : selectSource(sourceCollection.getSources());
            } else {
                source = null;
            }
            if (source == null && sourceCollections.containsKey(DeliveryType.MP4)) {
                Pair splitSourceCollectionByHTTPS = splitSourceCollectionByHTTPS((SourceCollection) sourceCollections.get(DeliveryType.MP4));
                source = findBestSourceByBitRate((SourceCollection) (!((SourceCollection) splitSourceCollectionByHTTPS.first).getSources().isEmpty() ? splitSourceCollectionByHTTPS.first : splitSourceCollectionByHTTPS.second), this.DEFAULT_BIT_RATE);
            }
            if (source == null) {
                SourceCollection sourceCollection2 = (SourceCollection) sourceCollections.get(DeliveryType.UNKNOWN);
                if (sourceCollection2 != null) {
                    source2 = selectSource(sourceCollection2.getSources());
                }
                source = source2;
            }
            if (source != null && source.getUrl() != null) {
                return source;
            }
            throw new NoSourceFoundException();
        }
        throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.VIDEO_REQUIRED));
    }

    @NonNull
    private Pair<SourceCollection, SourceCollection> splitSourceCollectionByHTTPS(@Nullable SourceCollection sourceCollection) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Set<Source> sources = sourceCollection != null ? sourceCollection.getSources() : null;
        if (sources != null && !sources.isEmpty()) {
            for (Source source : sources) {
                if (!(source == null || source.getUrl() == null)) {
                    if (source.getUrl().startsWith(Constants.HTTPS)) {
                        hashSet.add(source);
                    } else {
                        hashSet2.add(source);
                    }
                }
            }
        }
        return Pair.create(new SourceCollection((Set<Source>) hashSet, DeliveryType.MP4), new SourceCollection((Set<Source>) hashSet2, DeliveryType.MP4));
    }

    public static Source findBestSourceByBitRate(SourceCollection sourceCollection, Integer num) {
        if (sourceCollection.getSources() == null || sourceCollection.getSources().size() == 0) {
            return null;
        }
        Source source = (Source) sourceCollection.getSources().iterator().next();
        int i = Integer.MAX_VALUE;
        for (Source source2 : sourceCollection.getSources()) {
            if (source2.getBitRate() != null && source2.getBitRate().intValue() > 0) {
                int abs = Math.abs(source2.getBitRate().intValue() - num.intValue());
                if (abs <= i) {
                    source = source2;
                    i = abs;
                }
            }
        }
        return source;
    }

    @Nullable
    public static Source selectSource(@Nullable Set<Source> set) {
        Source source = null;
        if (set != null && !set.isEmpty()) {
            for (Source source2 : set) {
                if (source == null || source2.getUrl().startsWith(Constants.HTTPS)) {
                    source = source2;
                }
            }
        }
        return source;
    }
}
