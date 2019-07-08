package com.google.android.exoplayer2.offline;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.source.TrackGroupArray;
import java.util.List;

public final class ProgressiveDownloadHelper extends DownloadHelper {
    @Nullable
    private final String customCacheKey;
    private final Uri uri;

    public int getPeriodCount() {
        return 1;
    }

    /* access modifiers changed from: protected */
    public void prepareInternal() {
    }

    public ProgressiveDownloadHelper(Uri uri2) {
        this(uri2, null);
    }

    public ProgressiveDownloadHelper(Uri uri2, @Nullable String str) {
        this.uri = uri2;
        this.customCacheKey = str;
    }

    public TrackGroupArray getTrackGroups(int i) {
        return TrackGroupArray.EMPTY;
    }

    public ProgressiveDownloadAction getDownloadAction(@Nullable byte[] bArr, List<TrackKey> list) {
        return ProgressiveDownloadAction.createDownloadAction(this.uri, bArr, this.customCacheKey);
    }

    public ProgressiveDownloadAction getRemoveAction(@Nullable byte[] bArr) {
        return ProgressiveDownloadAction.createRemoveAction(this.uri, bArr, this.customCacheKey);
    }
}
