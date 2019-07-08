package com.google.android.exoplayer2.source.smoothstreaming.offline;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.offline.DownloadHelper;
import com.google.android.exoplayer2.offline.TrackKey;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifest.StreamElement;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.SsManifestParser;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.StreamKey;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;

public final class SsDownloadHelper extends DownloadHelper {
    @MonotonicNonNull
    private SsManifest manifest;
    private final Factory manifestDataSourceFactory;
    private final Uri uri;

    public SsDownloadHelper(Uri uri2, Factory factory) {
        this.uri = uri2;
        this.manifestDataSourceFactory = factory;
    }

    /* access modifiers changed from: protected */
    public void prepareInternal() throws IOException {
        this.manifest = (SsManifest) ParsingLoadable.load(this.manifestDataSourceFactory.createDataSource(), new SsManifestParser(), this.uri);
    }

    public SsManifest getManifest() {
        Assertions.checkNotNull(this.manifest);
        return this.manifest;
    }

    public int getPeriodCount() {
        Assertions.checkNotNull(this.manifest);
        return 1;
    }

    public TrackGroupArray getTrackGroups(int i) {
        Assertions.checkNotNull(this.manifest);
        StreamElement[] streamElementArr = this.manifest.streamElements;
        TrackGroup[] trackGroupArr = new TrackGroup[streamElementArr.length];
        for (int i2 = 0; i2 < streamElementArr.length; i2++) {
            trackGroupArr[i2] = new TrackGroup(streamElementArr[i2].formats);
        }
        return new TrackGroupArray(trackGroupArr);
    }

    public SsDownloadAction getDownloadAction(@Nullable byte[] bArr, List<TrackKey> list) {
        return new SsDownloadAction(this.uri, false, bArr, toStreamKeys(list));
    }

    public SsDownloadAction getRemoveAction(@Nullable byte[] bArr) {
        return new SsDownloadAction(this.uri, true, bArr, Collections.emptyList());
    }

    private static List<StreamKey> toStreamKeys(List<TrackKey> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            TrackKey trackKey = (TrackKey) list.get(i);
            arrayList.add(new StreamKey(trackKey.groupIndex, trackKey.trackIndex));
        }
        return arrayList;
    }
}
