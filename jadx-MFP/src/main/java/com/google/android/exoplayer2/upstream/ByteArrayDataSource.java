package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;

public final class ByteArrayDataSource extends BaseDataSource {
    private int bytesRemaining;
    private final byte[] data;
    private boolean opened;
    private int readPosition;
    @Nullable
    private Uri uri;

    public ByteArrayDataSource(byte[] bArr) {
        boolean z = false;
        super(false);
        Assertions.checkNotNull(bArr);
        if (bArr.length > 0) {
            z = true;
        }
        Assertions.checkArgument(z);
        this.data = bArr;
    }

    public long open(DataSpec dataSpec) throws IOException {
        this.uri = dataSpec.uri;
        transferInitializing(dataSpec);
        this.readPosition = (int) dataSpec.position;
        this.bytesRemaining = (int) (dataSpec.length == -1 ? ((long) this.data.length) - dataSpec.position : dataSpec.length);
        int i = this.bytesRemaining;
        if (i <= 0 || this.readPosition + i > this.data.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("Unsatisfiable range: [");
            sb.append(this.readPosition);
            sb.append(", ");
            sb.append(dataSpec.length);
            sb.append("], length: ");
            sb.append(this.data.length);
            throw new IOException(sb.toString());
        }
        this.opened = true;
        transferStarted(dataSpec);
        return (long) this.bytesRemaining;
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        int i3 = this.bytesRemaining;
        if (i3 == 0) {
            return -1;
        }
        int min = Math.min(i2, i3);
        System.arraycopy(this.data, this.readPosition, bArr, i, min);
        this.readPosition += min;
        this.bytesRemaining -= min;
        bytesTransferred(min);
        return min;
    }

    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    public void close() throws IOException {
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
        this.uri = null;
    }
}
