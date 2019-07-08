package com.google.android.exoplayer2.source.smoothstreaming.offline;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.offline.DownloadAction;
import com.google.android.exoplayer2.offline.DownloadAction.Deserializer;
import com.google.android.exoplayer2.offline.DownloaderConstructorHelper;
import com.google.android.exoplayer2.offline.SegmentDownloadAction;
import com.google.android.exoplayer2.source.smoothstreaming.manifest.StreamKey;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

public final class SsDownloadAction extends SegmentDownloadAction<StreamKey> {
    public static final Deserializer DESERIALIZER = new SegmentDownloadActionDeserializer<StreamKey>(TYPE, 0) {
        /* access modifiers changed from: protected */
        public StreamKey readKey(DataInputStream dataInputStream) throws IOException {
            return new StreamKey(dataInputStream.readInt(), dataInputStream.readInt());
        }

        /* access modifiers changed from: protected */
        public DownloadAction createDownloadAction(Uri uri, boolean z, byte[] bArr, List<StreamKey> list) {
            return new SsDownloadAction(uri, z, bArr, list);
        }
    };
    private static final String TYPE = "ss";
    private static final int VERSION = 0;

    public SsDownloadAction(Uri uri, boolean z, @Nullable byte[] bArr, List<StreamKey> list) {
        super(TYPE, 0, uri, z, bArr, list);
    }

    /* access modifiers changed from: protected */
    public SsDownloader createDownloader(DownloaderConstructorHelper downloaderConstructorHelper) {
        return new SsDownloader(this.uri, this.keys, downloaderConstructorHelper);
    }

    /* access modifiers changed from: protected */
    public void writeKey(DataOutputStream dataOutputStream, StreamKey streamKey) throws IOException {
        dataOutputStream.writeInt(streamKey.streamElementIndex);
        dataOutputStream.writeInt(streamKey.trackIndex);
    }
}
