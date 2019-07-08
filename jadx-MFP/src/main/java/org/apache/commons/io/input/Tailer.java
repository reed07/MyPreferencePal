package org.apache.commons.io.input;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Tailer implements Runnable {
    private final long delayMillis;
    private final boolean end;
    private final File file;
    private final byte[] inbuf;
    private final TailerListener listener;
    private final boolean reOpen;
    private volatile boolean run;

    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        r11.listener.fileNotFound();
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:1:0x0005 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x0068 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:46:0x0092 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x0016 */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x001d A[SYNTHETIC, Splitter:B:11:0x001d] */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023 A[SYNTHETIC, Splitter:B:13:0x0023] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
            r11 = this;
            r0 = 0
            r2 = 0
            r3 = r0
            r5 = r3
        L_0x0005:
            boolean r7 = r11.run     // Catch:{ Exception -> 0x00aa }
            if (r7 == 0) goto L_0x0038
            if (r2 != 0) goto L_0x0038
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x0016 }
            java.io.File r8 = r11.file     // Catch:{ FileNotFoundException -> 0x0016 }
            java.lang.String r9 = "r"
            r7.<init>(r8, r9)     // Catch:{ FileNotFoundException -> 0x0016 }
            r2 = r7
            goto L_0x001b
        L_0x0016:
            org.apache.commons.io.input.TailerListener r7 = r11.listener     // Catch:{ Exception -> 0x00aa }
            r7.fileNotFound()     // Catch:{ Exception -> 0x00aa }
        L_0x001b:
            if (r2 != 0) goto L_0x0023
            long r7 = r11.delayMillis     // Catch:{ InterruptedException -> 0x0005 }
            java.lang.Thread.sleep(r7)     // Catch:{ InterruptedException -> 0x0005 }
            goto L_0x0005
        L_0x0023:
            boolean r3 = r11.end     // Catch:{ Exception -> 0x00aa }
            if (r3 == 0) goto L_0x002f
            java.io.File r3 = r11.file     // Catch:{ Exception -> 0x00aa }
            long r3 = r3.length()     // Catch:{ Exception -> 0x00aa }
            r5 = r3
            goto L_0x0030
        L_0x002f:
            r5 = r0
        L_0x0030:
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00aa }
            r2.seek(r5)     // Catch:{ Exception -> 0x00aa }
            goto L_0x0005
        L_0x0038:
            boolean r7 = r11.run     // Catch:{ Exception -> 0x00aa }
            if (r7 == 0) goto L_0x00b0
            java.io.File r7 = r11.file     // Catch:{ Exception -> 0x00aa }
            boolean r7 = org.apache.commons.io.FileUtils.isFileNewer(r7, r3)     // Catch:{ Exception -> 0x00aa }
            java.io.File r8 = r11.file     // Catch:{ Exception -> 0x00aa }
            long r8 = r8.length()     // Catch:{ Exception -> 0x00aa }
            int r10 = (r8 > r5 ? 1 : (r8 == r5 ? 0 : -1))
            if (r10 >= 0) goto L_0x006e
            org.apache.commons.io.input.TailerListener r7 = r11.listener     // Catch:{ Exception -> 0x00aa }
            r7.fileRotated()     // Catch:{ Exception -> 0x00aa }
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ FileNotFoundException -> 0x0068 }
            java.io.File r8 = r11.file     // Catch:{ FileNotFoundException -> 0x0068 }
            java.lang.String r9 = "r"
            r7.<init>(r8, r9)     // Catch:{ FileNotFoundException -> 0x0068 }
            org.apache.commons.io.IOUtils.closeQuietly(r2)     // Catch:{ FileNotFoundException -> 0x0066 }
            r5 = r0
            r2 = r7
            goto L_0x0038
        L_0x0060:
            r0 = move-exception
            r2 = r7
            goto L_0x00b4
        L_0x0063:
            r0 = move-exception
            r2 = r7
            goto L_0x00ab
        L_0x0066:
            r5 = r0
            r2 = r7
        L_0x0068:
            org.apache.commons.io.input.TailerListener r7 = r11.listener     // Catch:{ Exception -> 0x00aa }
            r7.fileNotFound()     // Catch:{ Exception -> 0x00aa }
            goto L_0x0038
        L_0x006e:
            if (r10 <= 0) goto L_0x0079
            long r5 = r11.readLines(r2)     // Catch:{ Exception -> 0x00aa }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00aa }
            goto L_0x0086
        L_0x0079:
            if (r7 == 0) goto L_0x0086
            r2.seek(r0)     // Catch:{ Exception -> 0x00aa }
            long r5 = r11.readLines(r2)     // Catch:{ Exception -> 0x00aa }
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x00aa }
        L_0x0086:
            boolean r7 = r11.reOpen     // Catch:{ Exception -> 0x00aa }
            if (r7 == 0) goto L_0x008d
            org.apache.commons.io.IOUtils.closeQuietly(r2)     // Catch:{ Exception -> 0x00aa }
        L_0x008d:
            long r7 = r11.delayMillis     // Catch:{ InterruptedException -> 0x0092 }
            java.lang.Thread.sleep(r7)     // Catch:{ InterruptedException -> 0x0092 }
        L_0x0092:
            boolean r7 = r11.run     // Catch:{ Exception -> 0x00aa }
            if (r7 == 0) goto L_0x0038
            boolean r7 = r11.reOpen     // Catch:{ Exception -> 0x00aa }
            if (r7 == 0) goto L_0x0038
            java.io.RandomAccessFile r7 = new java.io.RandomAccessFile     // Catch:{ Exception -> 0x00aa }
            java.io.File r8 = r11.file     // Catch:{ Exception -> 0x00aa }
            java.lang.String r9 = "r"
            r7.<init>(r8, r9)     // Catch:{ Exception -> 0x00aa }
            r7.seek(r5)     // Catch:{ Exception -> 0x0063, all -> 0x0060 }
            r2 = r7
            goto L_0x0038
        L_0x00a8:
            r0 = move-exception
            goto L_0x00b4
        L_0x00aa:
            r0 = move-exception
        L_0x00ab:
            org.apache.commons.io.input.TailerListener r1 = r11.listener     // Catch:{ all -> 0x00a8 }
            r1.handle(r0)     // Catch:{ all -> 0x00a8 }
        L_0x00b0:
            org.apache.commons.io.IOUtils.closeQuietly(r2)
            return
        L_0x00b4:
            org.apache.commons.io.IOUtils.closeQuietly(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.Tailer.run():void");
    }

    private long readLines(RandomAccessFile randomAccessFile) throws IOException {
        StringBuilder sb = new StringBuilder();
        long filePointer = randomAccessFile.getFilePointer();
        long j = filePointer;
        boolean z = false;
        while (this.run) {
            int read = randomAccessFile.read(this.inbuf);
            if (read == -1) {
                break;
            }
            long j2 = filePointer;
            for (int i = 0; i < read; i++) {
                byte b = this.inbuf[i];
                if (b == 10) {
                    this.listener.handle(sb.toString());
                    sb.setLength(0);
                    j2 = ((long) i) + j + 1;
                    z = false;
                } else if (b != 13) {
                    if (z) {
                        this.listener.handle(sb.toString());
                        sb.setLength(0);
                        j2 = ((long) i) + j + 1;
                        z = false;
                    }
                    sb.append((char) b);
                } else {
                    if (z) {
                        sb.append(13);
                    }
                    z = true;
                }
            }
            j = randomAccessFile.getFilePointer();
            filePointer = j2;
        }
        randomAccessFile.seek(filePointer);
        return filePointer;
    }
}
