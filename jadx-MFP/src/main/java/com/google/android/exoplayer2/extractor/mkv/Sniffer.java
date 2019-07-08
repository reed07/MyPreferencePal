package com.google.android.exoplayer2.extractor.mkv;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.io.IOException;

final class Sniffer {
    private static final int ID_EBML = 440786851;
    private static final int SEARCH_LENGTH = 1024;
    private int peekLength;
    private final ParsableByteArray scratch = new ParsableByteArray(8);

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        ExtractorInput extractorInput2 = extractorInput;
        long length = extractorInput.getLength();
        long j = 1024;
        int i = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        if (i != 0 && length <= 1024) {
            j = length;
        }
        int i2 = (int) j;
        extractorInput2.peekFully(this.scratch.data, 0, 4);
        long readUnsignedInt = this.scratch.readUnsignedInt();
        this.peekLength = 4;
        while (true) {
            boolean z = true;
            if (readUnsignedInt != 440786851) {
                int i3 = this.peekLength + 1;
                this.peekLength = i3;
                if (i3 == i2) {
                    return false;
                }
                extractorInput2.peekFully(this.scratch.data, 0, 1);
                readUnsignedInt = ((readUnsignedInt << 8) & -256) | ((long) (this.scratch.data[0] & 255));
            } else {
                long readUint = readUint(extractorInput);
                long j2 = (long) this.peekLength;
                if (readUint == Long.MIN_VALUE || (i != 0 && j2 + readUint >= length)) {
                    return false;
                }
                while (true) {
                    int i4 = this.peekLength;
                    long j3 = j2 + readUint;
                    if (((long) i4) >= j3) {
                        if (((long) i4) != j3) {
                            z = false;
                        }
                        return z;
                    } else if (readUint(extractorInput) == Long.MIN_VALUE) {
                        return false;
                    } else {
                        long readUint2 = readUint(extractorInput);
                        int i5 = (readUint2 > 0 ? 1 : (readUint2 == 0 ? 0 : -1));
                        if (i5 < 0 || readUint2 > 2147483647L) {
                            return false;
                        }
                        if (i5 != 0) {
                            int i6 = (int) readUint2;
                            extractorInput2.advancePeekPosition(i6);
                            this.peekLength += i6;
                        }
                    }
                }
                return false;
            }
        }
    }

    private long readUint(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i = 0;
        extractorInput.peekFully(this.scratch.data, 0, 1);
        byte b = this.scratch.data[0] & 255;
        if (b == 0) {
            return Long.MIN_VALUE;
        }
        int i2 = 128;
        int i3 = 0;
        while ((b & i2) == 0) {
            i2 >>= 1;
            i3++;
        }
        int i4 = b & (~i2);
        extractorInput.peekFully(this.scratch.data, 1, i3);
        while (i < i3) {
            i++;
            i4 = (this.scratch.data[i] & 255) + (i4 << 8);
        }
        this.peekLength += i3 + 1;
        return (long) i4;
    }
}
