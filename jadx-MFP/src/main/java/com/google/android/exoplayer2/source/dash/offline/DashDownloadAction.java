package com.google.android.exoplayer2.source.dash.offline;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.offline.DownloadAction;
import com.google.android.exoplayer2.offline.DownloadAction.Deserializer;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.SegmentDownloadAction;
import com.google.android.exoplayer2.offline.StreamKey;
import java.util.Collections;
import java.util.List;

public final class DashDownloadAction extends SegmentDownloadAction {
    public static final Deserializer DESERIALIZER = new SegmentDownloadActionDeserializer(TYPE, 0) {
        /* access modifiers changed from: protected */
        public DownloadAction createDownloadAction(Uri uri, boolean z, byte[] bArr, List<StreamKey> list) {
            return new DashDownloadAction(uri, z, bArr, list);
        }
    };
    private static final String TYPE = "dash";
    private static final int VERSION = 0;

    public static DashDownloadAction createDownloadAction(Uri uri, @Nullable byte[] bArr, List<StreamKey> list) {
        return new DashDownloadAction(uri, false, bArr, list);
    }

    public static DashDownloadAction createRemoveAction(Uri uri, @Nullable byte[] bArr) {
        return new DashDownloadAction(uri, true, bArr, Collections.emptyList());
    }

    @Deprecated
    public DashDownloadAction(Uri uri, boolean z, @Nullable byte[] bArr, List<StreamKey> list) {
        super(TYPE, 0, uri, z, bArr, list);
    }

    public DashDownloader createDownloader(DownloaderConstructorHelper downloaderConstructorHelper) {
        return new DashDownloader(this.uri, this.keys, downloaderConstructorHelper);
    }
}
