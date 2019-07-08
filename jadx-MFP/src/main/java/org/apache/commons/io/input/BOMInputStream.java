package org.apache.commons.io.input;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import org.apache.commons.io.ByteOrderMark;

public class BOMInputStream extends ProxyInputStream {
    private static final Comparator<ByteOrderMark> ByteOrderMarkLengthComparator = new Comparator<ByteOrderMark>() {
        public int compare(ByteOrderMark byteOrderMark, ByteOrderMark byteOrderMark2) {
            int length = byteOrderMark.length();
            int length2 = byteOrderMark2.length();
            if (length > length2) {
                return -1;
            }
            return length2 > length ? 1 : 0;
        }
    };
    private final List<ByteOrderMark> boms;
    private ByteOrderMark byteOrderMark;
    private int fbIndex;
    private int fbLength;
    private int[] firstBytes;
    private final boolean include;
    private int markFbIndex;
    private boolean markedAtStart;

    public ByteOrderMark getBOM() throws IOException {
        if (this.firstBytes == null) {
            this.fbLength = 0;
            this.firstBytes = new int[((ByteOrderMark) this.boms.get(0)).length()];
            int i = 0;
            while (true) {
                int[] iArr = this.firstBytes;
                if (i >= iArr.length) {
                    break;
                }
                iArr[i] = this.in.read();
                this.fbLength++;
                if (this.firstBytes[i] < 0) {
                    break;
                }
                i++;
            }
            this.byteOrderMark = find();
            ByteOrderMark byteOrderMark2 = this.byteOrderMark;
            if (byteOrderMark2 != null && !this.include) {
                if (byteOrderMark2.length() < this.firstBytes.length) {
                    this.fbIndex = this.byteOrderMark.length();
                } else {
                    this.fbLength = 0;
                }
            }
        }
        return this.byteOrderMark;
    }

    private int readFirstBytes() throws IOException {
        getBOM();
        int i = this.fbIndex;
        if (i >= this.fbLength) {
            return -1;
        }
        int[] iArr = this.firstBytes;
        this.fbIndex = i + 1;
        return iArr[i];
    }

    private ByteOrderMark find() {
        for (ByteOrderMark byteOrderMark2 : this.boms) {
            if (matches(byteOrderMark2)) {
                return byteOrderMark2;
            }
        }
        return null;
    }

    private boolean matches(ByteOrderMark byteOrderMark2) {
        for (int i = 0; i < byteOrderMark2.length(); i++) {
            if (byteOrderMark2.get(i) != this.firstBytes[i]) {
                return false;
            }
        }
        return true;
    }

    public int read() throws IOException {
        int readFirstBytes = readFirstBytes();
        return readFirstBytes >= 0 ? readFirstBytes : this.in.read();
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        int i4 = 0;
        while (i2 > 0 && i3 >= 0) {
            i3 = readFirstBytes();
            if (i3 >= 0) {
                int i5 = i + 1;
                bArr[i] = (byte) (i3 & 255);
                i2--;
                i4++;
                i = i5;
            }
        }
        int read = this.in.read(bArr, i, i2);
        if (read >= 0) {
            return i4 + read;
        }
        if (i4 > 0) {
            return i4;
        }
        return -1;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public synchronized void mark(int i) {
        this.markFbIndex = this.fbIndex;
        this.markedAtStart = this.firstBytes == null;
        this.in.mark(i);
    }

    public synchronized void reset() throws IOException {
        this.fbIndex = this.markFbIndex;
        if (this.markedAtStart) {
            this.firstBytes = null;
        }
        this.in.reset();
    }

    public long skip(long j) throws IOException {
        while (j > 0 && readFirstBytes() >= 0) {
            j--;
        }
        return this.in.skip(j);
    }
}
