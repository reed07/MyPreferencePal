package com.google.android.exoplayer2.source.smoothstreaming.offline;

import android.net.Uri;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.SegmentDownloader;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest.StreamElement;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsUtil;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.StreamKey;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.upstream.ParsingLoadable.Parser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class SsDownloader extends SegmentDownloader<SsManifest, StreamKey> {
    public SsDownloader(Uri uri, List<StreamKey> list, DownloaderConstructorHelper downloaderConstructorHelper) {
        super(SsUtil.fixManifestUri(uri), list, downloaderConstructorHelper);
    }

    /* access modifiers changed from: protected */
    public SsManifest getManifest(DataSource dataSource, Uri uri) throws IOException {
        ParsingLoadable parsingLoadable = new ParsingLoadable(dataSource, uri, 4, (Parser<? extends T>) new SsManifestParser<Object>());
        parsingLoadable.load();
        return (SsManifest) parsingLoadable.getResult();
    }

    /* access modifiers changed from: protected */
    public List<Segment> getSegments(DataSource dataSource, SsManifest ssManifest, boolean z) {
        StreamElement[] streamElementArr;
        ArrayList arrayList = new ArrayList();
        for (StreamElement streamElement : ssManifest.streamElements) {
            for (int i = 0; i < streamElement.formats.length; i++) {
                for (int i2 = 0; i2 < streamElement.chunkCount; i2++) {
                    arrayList.add(new Segment(streamElement.getStartTimeUs(i2), new DataSpec(streamElement.buildRequestUri(i, i2))));
                }
            }
        }
        return arrayList;
    }
}
