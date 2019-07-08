package org.apache.commons.io.input;

import java.io.Reader;
import java.io.Serializable;

public class CharSequenceReader extends Reader implements Serializable {
    private final CharSequence charSequence;
    private int idx;
    private int mark;

    public boolean markSupported() {
        return true;
    }

    public void close() {
        this.idx = 0;
        this.mark = 0;
    }

    public void mark(int i) {
        this.mark = this.idx;
    }

    public int read() {
        if (this.idx >= this.charSequence.length()) {
            return -1;
        }
        CharSequence charSequence2 = this.charSequence;
        int i = this.idx;
        this.idx = i + 1;
        return charSequence2.charAt(i);
    }

    public int read(char[] cArr, int i, int i2) {
        if (this.idx >= this.charSequence.length()) {
            return -1;
        }
        if (cArr == null) {
            throw new NullPointerException("Character array is missing");
        } else if (i2 < 0 || i < 0 || i + i2 > cArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append("Array Size=");
            sb.append(cArr.length);
            sb.append(", offset=");
            sb.append(i);
            sb.append(", length=");
            sb.append(i2);
            throw new IndexOutOfBoundsException(sb.toString());
        } else {
            int i3 = 0;
            for (int i4 = 0; i4 < i2; i4++) {
                int read = read();
                if (read == -1) {
                    return i3;
                }
                cArr[i + i4] = (char) read;
                i3++;
            }
            return i3;
        }
    }

    public void reset() {
        this.idx = this.mark;
    }

    public long skip(long j) {
        if (j < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Number of characters to skip is less than zero: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        } else if (this.idx >= this.charSequence.length()) {
            return -1;
        } else {
            int min = (int) Math.min((long) this.charSequence.length(), ((long) this.idx) + j);
            int i = min - this.idx;
            this.idx = min;
            return (long) i;
        }
    }

    public String toString() {
        return this.charSequence.toString();
    }
}
