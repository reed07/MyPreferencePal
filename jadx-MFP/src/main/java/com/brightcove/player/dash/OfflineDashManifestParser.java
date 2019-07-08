package com.brightcove.player.dash;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.brightcove.player.edge.OfflineStoreManager;
import com.brightcove.player.util.MediaSourceUtil;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.DashManifestParser;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class OfflineDashManifestParser implements Parser<DashManifest> {
    private final OfflineStoreManager storeManager;
    private final DashManifestParser wrappedManifestParser;

    public OfflineDashManifestParser(@NonNull DashManifestParser dashManifestParser, Context context) {
        this.wrappedManifestParser = dashManifestParser;
        this.storeManager = OfflineStoreManager.getInstance(context);
    }

    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        DashManifest parse = this.wrappedManifestParser.parse(uri, inputStream);
        ArrayList createOfflineFilter = createOfflineFilter(parse);
        return !createOfflineFilter.isEmpty() ? parse.copy((List<StreamKey>) createOfflineFilter) : parse;
    }

    private ArrayList<StreamKey> createOfflineFilter(DashManifest dashManifest) {
        ArrayList<StreamKey> arrayList = new ArrayList<>();
        int periodCount = dashManifest.getPeriodCount();
        for (int i = 0; i < periodCount; i++) {
            int i2 = 0;
            for (AdaptationSet adaptationSet : dashManifest.getPeriod(i).adaptationSets) {
                int i3 = 0;
                for (Representation findInitializationUri : adaptationSet.representations) {
                    String uri = MediaSourceUtil.findInitializationUri(findInitializationUri).toString();
                    if (!TextUtils.isEmpty(uri) && this.storeManager.findOfflineAssetUri(Uri.parse(uri)) != null) {
                        arrayList.add(new StreamKey(i, i2, i3));
                    }
                    i3++;
                }
                i2++;
            }
        }
        return arrayList;
    }
}
