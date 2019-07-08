package com.google.android.exoplayer2.source.hls.offline;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.offline.DownloadAction;
import com.google.android.exoplayer2.offline.DownloadAction.Deserializer;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.SegmentDownloadAction;
import com.google.android.exoplayer2.offline.StreamKey;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public final class HlsDownloadAction extends SegmentDownloadAction {
    public static final Deserializer DESERIALIZER = new SegmentDownloadActionDeserializer(TYPE, 1) {
        /* access modifiers changed from: protected */
        public StreamKey readKey(int i, DataInputStream dataInputStream) throws IOException {
            if (i > 0) {
                return super.readKey(i, dataInputStream);
            }
            return new StreamKey(dataInputStream.readInt(), dataInputStream.readInt());
        }

        /* access modifiers changed from: protected */
        public DownloadAction createDownloadAction(Uri uri, boolean z, byte[] bArr, List<StreamKey> list) {
            return new HlsDownloadAction(uri, z, bArr, list);
        }
    };
    private static final String TYPE = "hls";
    private static final int VERSION = 1;

    public static HlsDownloadAction createDownloadAction(Uri uri, @Nullable byte[] bArr, List<StreamKey> list) {
        return new HlsDownloadAction(uri, false, bArr, list);
    }

    public static HlsDownloadAction createRemoveAction(Uri uri, @Nullable byte[] bArr) {
        return new HlsDownloadAction(uri, true, bArr, Collections.emptyList());
    }

    @Deprecated
    public HlsDownloadAction(Uri uri, boolean z, @Nullable byte[] bArr, List<StreamKey> list) {
        super(TYPE, 1, uri, z, bArr, list);
    }

    public HlsDownloader createDownloader(DownloaderConstructorHelper downloaderConstructorHelper) {
        return new HlsDownloader(this.uri, this.keys, downloaderConstructorHelper);
    }
}
