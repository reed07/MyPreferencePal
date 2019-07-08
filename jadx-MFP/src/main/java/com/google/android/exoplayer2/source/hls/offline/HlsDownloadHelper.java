package com.google.android.exoplayer2.source.hls.offline;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.offline.DownloadHelper;
import com.google.android.exoplayer2.offline.StreamKey;
import com.google.android.exoplayer2.offline.TrackKey;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist.HlsUrl;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistParser;
import com.google.android.exoplayer2.upstream.DataSource.Factory;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class HlsDownloadHelper extends DownloadHelper {
    private final Factory manifestDataSourceFactory;
    private HlsPlaylist playlist;
    private int[] renditionGroups;
    private final Uri uri;

    public HlsDownloadHelper(Uri uri2, Factory factory) {
        this.uri = uri2;
        this.manifestDataSourceFactory = factory;
    }

    /* access modifiers changed from: protected */
    public void prepareInternal() throws IOException {
        this.playlist = (HlsPlaylist) ParsingLoadable.load(this.manifestDataSourceFactory.createDataSource(), new HlsPlaylistParser(), this.uri, 4);
    }

    public HlsPlaylist getPlaylist() {
        Assertions.checkNotNull(this.playlist);
        return this.playlist;
    }

    public int getPeriodCount() {
        Assertions.checkNotNull(this.playlist);
        return 1;
    }

    public TrackGroupArray getTrackGroups(int i) {
        int i2;
        Assertions.checkNotNull(this.playlist);
        HlsPlaylist hlsPlaylist = this.playlist;
        int i3 = 0;
        if (hlsPlaylist instanceof HlsMediaPlaylist) {
            this.renditionGroups = new int[0];
            return TrackGroupArray.EMPTY;
        }
        HlsMasterPlaylist hlsMasterPlaylist = (HlsMasterPlaylist) hlsPlaylist;
        TrackGroup[] trackGroupArr = new TrackGroup[3];
        this.renditionGroups = new int[3];
        if (!hlsMasterPlaylist.variants.isEmpty()) {
            this.renditionGroups[0] = 0;
            trackGroupArr[0] = new TrackGroup(toFormats(hlsMasterPlaylist.variants));
            i3 = 1;
        }
        if (!hlsMasterPlaylist.audios.isEmpty()) {
            this.renditionGroups[i3] = 1;
            i2 = i3 + 1;
            trackGroupArr[i3] = new TrackGroup(toFormats(hlsMasterPlaylist.audios));
        } else {
            i2 = i3;
        }
        if (!hlsMasterPlaylist.subtitles.isEmpty()) {
            this.renditionGroups[i2] = 2;
            int i4 = i2 + 1;
            trackGroupArr[i2] = new TrackGroup(toFormats(hlsMasterPlaylist.subtitles));
            i2 = i4;
        }
        return new TrackGroupArray((TrackGroup[]) Arrays.copyOf(trackGroupArr, i2));
    }

    public HlsDownloadAction getDownloadAction(@Nullable byte[] bArr, List<TrackKey> list) {
        Assertions.checkNotNull(this.renditionGroups);
        return HlsDownloadAction.createDownloadAction(this.uri, bArr, toStreamKeys(list, this.renditionGroups));
    }

    public HlsDownloadAction getRemoveAction(@Nullable byte[] bArr) {
        return HlsDownloadAction.createRemoveAction(this.uri, bArr);
    }

    private static Format[] toFormats(List<HlsUrl> list) {
        Format[] formatArr = new Format[list.size()];
        for (int i = 0; i < list.size(); i++) {
            formatArr[i] = ((HlsUrl) list.get(i)).format;
        }
        return formatArr;
    }

    private static List<StreamKey> toStreamKeys(List<TrackKey> list, int[] iArr) {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            TrackKey trackKey = (TrackKey) list.get(i);
            arrayList.add(new StreamKey(iArr[trackKey.groupIndex], trackKey.trackIndex));
        }
        return arrayList;
    }
}
