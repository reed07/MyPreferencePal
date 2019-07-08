package com.google.android.exoplayer2.offline;

import android.net.Uri;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class DownloadAction {
    @Nullable
    private static Deserializer[] defaultDeserializers;
    public final byte[] data;
    public final boolean isRemoveAction;
    public final String type;
    public final Uri uri;
    public final int version;

    public static abstract class Deserializer {
        public final String type;
        public final int version;

        public abstract DownloadAction readFromStream(int i, DataInputStream dataInputStream) throws IOException;

        public Deserializer(String str, int i) {
            this.type = str;
            this.version = i;
        }
    }

    public abstract Downloader createDownloader(DownloaderConstructorHelper downloaderConstructorHelper);

    /* access modifiers changed from: protected */
    public abstract void writeToStream(DataOutputStream dataOutputStream) throws IOException;

    /* JADX WARNING: Can't wrap try/catch for region: R(23:8|9|10|11|12|13|14|(2:15|16)|19|21|22|23|(2:24|25)|28|30|31|32|33|34|37|38|39|40) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0043 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.google.android.exoplayer2.offline.DownloadAction.Deserializer[] getDefaultDeserializers() {
        /*
            java.lang.Class<com.google.android.exoplayer2.offline.DownloadAction> r0 = com.google.android.exoplayer2.offline.DownloadAction.class
            monitor-enter(r0)
            com.google.android.exoplayer2.offline.DownloadAction$Deserializer[] r1 = defaultDeserializers     // Catch:{ all -> 0x0055 }
            if (r1 == 0) goto L_0x000b
            com.google.android.exoplayer2.offline.DownloadAction$Deserializer[] r1 = defaultDeserializers     // Catch:{ all -> 0x0055 }
            monitor-exit(r0)
            return r1
        L_0x000b:
            r1 = 4
            com.google.android.exoplayer2.offline.DownloadAction$Deserializer[] r1 = new com.google.android.exoplayer2.offline.DownloadAction.Deserializer[r1]     // Catch:{ all -> 0x0055 }
            r2 = 0
            com.google.android.exoplayer2.offline.DownloadAction$Deserializer r3 = com.google.android.exoplayer2.offline.ProgressiveDownloadAction.DESERIALIZER     // Catch:{ all -> 0x0055 }
            r1[r2] = r3     // Catch:{ all -> 0x0055 }
            r2 = 1
            java.lang.String r3 = "com.google.android.exoplayer2.source.dash.offline.DashDownloadAction"
            java.lang.Class r3 = java.lang.Class.forName(r3)     // Catch:{ Exception -> 0x0022 }
            r4 = 2
            com.google.android.exoplayer2.offline.DownloadAction$Deserializer r3 = getDeserializer(r3)     // Catch:{ Exception -> 0x0023 }
            r1[r2] = r3     // Catch:{ Exception -> 0x0023 }
            goto L_0x0023
        L_0x0022:
            r4 = 1
        L_0x0023:
            java.lang.String r2 = "com.google.android.exoplayer2.source.hls.offline.HlsDownloadAction"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ Exception -> 0x0032 }
            int r3 = r4 + 1
            com.google.android.exoplayer2.offline.DownloadAction$Deserializer r2 = getDeserializer(r2)     // Catch:{ Exception -> 0x0033 }
            r1[r4] = r2     // Catch:{ Exception -> 0x0033 }
            goto L_0x0033
        L_0x0032:
            r3 = r4
        L_0x0033:
            java.lang.String r2 = "com.google.android.exoplayer2.source.smoothstreaming.offline.SsDownloadAction"
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ Exception -> 0x0042 }
            int r4 = r3 + 1
            com.google.android.exoplayer2.offline.DownloadAction$Deserializer r2 = getDeserializer(r2)     // Catch:{ Exception -> 0x0043 }
            r1[r3] = r2     // Catch:{ Exception -> 0x0043 }
            goto L_0x0043
        L_0x0042:
            r4 = r3
        L_0x0043:
            java.lang.Object r1 = com.google.android.exoplayer2.util.Assertions.checkNotNull(r1)     // Catch:{ all -> 0x0055 }
            java.lang.Object[] r1 = (java.lang.Object[]) r1     // Catch:{ all -> 0x0055 }
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r4)     // Catch:{ all -> 0x0055 }
            com.google.android.exoplayer2.offline.DownloadAction$Deserializer[] r1 = (com.google.android.exoplayer2.offline.DownloadAction.Deserializer[]) r1     // Catch:{ all -> 0x0055 }
            defaultDeserializers = r1     // Catch:{ all -> 0x0055 }
            com.google.android.exoplayer2.offline.DownloadAction$Deserializer[] r1 = defaultDeserializers     // Catch:{ all -> 0x0055 }
            monitor-exit(r0)
            return r1
        L_0x0055:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.offline.DownloadAction.getDefaultDeserializers():com.google.android.exoplayer2.offline.DownloadAction$Deserializer[]");
    }

    public static DownloadAction deserializeFromStream(Deserializer[] deserializerArr, InputStream inputStream) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        String readUTF = dataInputStream.readUTF();
        int readInt = dataInputStream.readInt();
        for (Deserializer deserializer : deserializerArr) {
            if (readUTF.equals(deserializer.type) && deserializer.version >= readInt) {
                return deserializer.readFromStream(readInt, dataInputStream);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("No deserializer found for:");
        sb.append(readUTF);
        sb.append(", ");
        sb.append(readInt);
        throw new DownloadException(sb.toString());
    }

    public static void serializeToStream(DownloadAction downloadAction, OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeUTF(downloadAction.type);
        dataOutputStream.writeInt(downloadAction.version);
        downloadAction.writeToStream(dataOutputStream);
        dataOutputStream.flush();
    }

    protected DownloadAction(String str, int i, Uri uri2, boolean z, @Nullable byte[] bArr) {
        this.type = str;
        this.version = i;
        this.uri = uri2;
        this.isRemoveAction = z;
        if (bArr == null) {
            bArr = Util.EMPTY_BYTE_ARRAY;
        }
        this.data = bArr;
    }

    public final byte[] toByteArray() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            serializeToStream(this, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException unused) {
            throw new IllegalStateException();
        }
    }

    public boolean isSameMedia(DownloadAction downloadAction) {
        return this.uri.equals(downloadAction.uri);
    }

    public List<StreamKey> getKeys() {
        return Collections.emptyList();
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = false;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DownloadAction downloadAction = (DownloadAction) obj;
        if (this.type.equals(downloadAction.type) && this.version == downloadAction.version && this.uri.equals(downloadAction.uri) && this.isRemoveAction == downloadAction.isRemoveAction && Arrays.equals(this.data, downloadAction.data)) {
            z = true;
        }
        return z;
    }

    public int hashCode() {
        return (((this.uri.hashCode() * 31) + (this.isRemoveAction ? 1 : 0)) * 31) + Arrays.hashCode(this.data);
    }

    private static Deserializer getDeserializer(Class<?> cls) throws NoSuchFieldException, IllegalAccessException {
        return (Deserializer) Assertions.checkNotNull(cls.getDeclaredField("DESERIALIZER").get(null));
    }
}
