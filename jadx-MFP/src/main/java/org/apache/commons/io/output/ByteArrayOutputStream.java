package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ByteArrayOutputStream extends OutputStream {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private final List<byte[]> buffers;
    private int count;
    private byte[] currentBuffer;
    private int currentBufferIndex;
    private int filledBufferSum;

    public void close() throws IOException {
    }

    public ByteArrayOutputStream() {
        this(1024);
    }

    public ByteArrayOutputStream(int i) {
        this.buffers = new ArrayList();
        if (i >= 0) {
            synchronized (this) {
                needNewBuffer(i);
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Negative initial size: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    private void needNewBuffer(int i) {
        if (this.currentBufferIndex < this.buffers.size() - 1) {
            this.filledBufferSum += this.currentBuffer.length;
            this.currentBufferIndex++;
            this.currentBuffer = (byte[]) this.buffers.get(this.currentBufferIndex);
            return;
        }
        byte[] bArr = this.currentBuffer;
        if (bArr == null) {
            this.filledBufferSum = 0;
        } else {
            i = Math.max(bArr.length << 1, i - this.filledBufferSum);
            this.filledBufferSum += this.currentBuffer.length;
        }
        this.currentBufferIndex++;
        this.currentBuffer = new byte[i];
        this.buffers.add(this.currentBuffer);
    }

    public void write(byte[] bArr, int i, int i2) {
        if (i >= 0 && i <= bArr.length && i2 >= 0) {
            int i3 = i + i2;
            if (i3 <= bArr.length && i3 >= 0) {
                if (i2 != 0) {
                    synchronized (this) {
                        int i4 = this.count + i2;
                        int i5 = this.count - this.filledBufferSum;
                        while (i2 > 0) {
                            int min = Math.min(i2, this.currentBuffer.length - i5);
                            System.arraycopy(bArr, i3 - i2, this.currentBuffer, i5, min);
                            i2 -= min;
                            if (i2 > 0) {
                                needNewBuffer(i4);
                                i5 = 0;
                            }
                        }
                        this.count = i4;
                    }
                    return;
                }
                return;
            }
        }
        throw new IndexOutOfBoundsException();
    }

    public synchronized void write(int i) {
        int i2 = this.count - this.filledBufferSum;
        if (i2 == this.currentBuffer.length) {
            needNewBuffer(this.count + 1);
            i2 = 0;
        }
        this.currentBuffer[i2] = (byte) i;
        this.count++;
    }

    public synchronized void writeTo(OutputStream outputStream) throws IOException {
        int i = this.count;
        for (byte[] bArr : this.buffers) {
            int min = Math.min(bArr.length, i);
            outputStream.write(bArr, 0, min);
            i -= min;
            if (i == 0) {
                break;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002c, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized byte[] toByteArray() {
        /*
            r7 = this;
            monitor-enter(r7)
            int r0 = r7.count     // Catch:{ all -> 0x002d }
            if (r0 != 0) goto L_0x0009
            byte[] r0 = EMPTY_BYTE_ARRAY     // Catch:{ all -> 0x002d }
            monitor-exit(r7)
            return r0
        L_0x0009:
            byte[] r1 = new byte[r0]     // Catch:{ all -> 0x002d }
            java.util.List<byte[]> r2 = r7.buffers     // Catch:{ all -> 0x002d }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x002d }
            r3 = 0
            r4 = 0
        L_0x0013:
            boolean r5 = r2.hasNext()     // Catch:{ all -> 0x002d }
            if (r5 == 0) goto L_0x002b
            java.lang.Object r5 = r2.next()     // Catch:{ all -> 0x002d }
            byte[] r5 = (byte[]) r5     // Catch:{ all -> 0x002d }
            int r6 = r5.length     // Catch:{ all -> 0x002d }
            int r6 = java.lang.Math.min(r6, r0)     // Catch:{ all -> 0x002d }
            java.lang.System.arraycopy(r5, r3, r1, r4, r6)     // Catch:{ all -> 0x002d }
            int r4 = r4 + r6
            int r0 = r0 - r6
            if (r0 != 0) goto L_0x0013
        L_0x002b:
            monitor-exit(r7)
            return r1
        L_0x002d:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.output.ByteArrayOutputStream.toByteArray():byte[]");
    }

    public String toString() {
        return new String(toByteArray());
    }
}
