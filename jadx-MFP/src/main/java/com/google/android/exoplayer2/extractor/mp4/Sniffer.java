package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

final class Sniffer {
    private static final int[] COMPATIBLE_BRANDS = {Util.getIntegerCodeForString("isom"), Util.getIntegerCodeForString("iso2"), Util.getIntegerCodeForString("iso3"), Util.getIntegerCodeForString("iso4"), Util.getIntegerCodeForString("iso5"), Util.getIntegerCodeForString("iso6"), Util.getIntegerCodeForString("avc1"), Util.getIntegerCodeForString("hvc1"), Util.getIntegerCodeForString("hev1"), Util.getIntegerCodeForString("mp41"), Util.getIntegerCodeForString("mp42"), Util.getIntegerCodeForString("3g2a"), Util.getIntegerCodeForString("3g2b"), Util.getIntegerCodeForString("3gr6"), Util.getIntegerCodeForString("3gs6"), Util.getIntegerCodeForString("3ge6"), Util.getIntegerCodeForString("3gg6"), Util.getIntegerCodeForString("M4V "), Util.getIntegerCodeForString("M4A "), Util.getIntegerCodeForString("f4v "), Util.getIntegerCodeForString("kddi"), Util.getIntegerCodeForString("M4VP"), Util.getIntegerCodeForString("qt  "), Util.getIntegerCodeForString("MSNV")};
    private static final int SEARCH_LENGTH = 4096;

    public static boolean sniffFragmented(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return sniffInternal(extractorInput, true);
    }

    public static boolean sniffUnfragmented(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return sniffInternal(extractorInput, false);
    }

    private static boolean sniffInternal(ExtractorInput extractorInput, boolean z) throws IOException, InterruptedException {
        boolean z2;
        boolean z3;
        boolean z4;
        ExtractorInput extractorInput2 = extractorInput;
        long length = extractorInput.getLength();
        long j = 4096;
        long j2 = -1;
        int i = (length > -1 ? 1 : (length == -1 ? 0 : -1));
        if (i != 0 && length <= 4096) {
            j = length;
        }
        int i2 = (int) j;
        ParsableByteArray parsableByteArray = new ParsableByteArray(64);
        boolean z5 = false;
        int i3 = i2;
        int i4 = 0;
        boolean z6 = false;
        while (true) {
            if (i4 >= i3) {
                z2 = false;
                break;
            }
            parsableByteArray.reset(8);
            extractorInput2.peekFully(parsableByteArray.data, z5 ? 1 : 0, 8);
            long readUnsignedInt = parsableByteArray.readUnsignedInt();
            int readInt = parsableByteArray.readInt();
            int i5 = 16;
            if (readUnsignedInt == 1) {
                extractorInput2.peekFully(parsableByteArray.data, 8, 8);
                parsableByteArray.setLimit(16);
                readUnsignedInt = parsableByteArray.readLong();
            } else {
                if (readUnsignedInt == 0) {
                    long length2 = extractorInput.getLength();
                    if (length2 != j2) {
                        readUnsignedInt = (length2 - extractorInput.getPeekPosition()) + ((long) 8);
                        i5 = 8;
                    }
                }
                i5 = 8;
            }
            if (i != 0 && ((long) i4) + readUnsignedInt > length) {
                return z5;
            }
            long j3 = (long) i5;
            if (readUnsignedInt < j3) {
                return z5;
            }
            i4 += i5;
            if (readInt == Atom.TYPE_moov) {
                i3 += (int) readUnsignedInt;
                if (i == 0 || ((long) i3) <= length) {
                    j2 = -1;
                } else {
                    i3 = (int) length;
                    j2 = -1;
                }
            } else if (readInt == Atom.TYPE_moof || readInt == Atom.TYPE_mvex) {
                z2 = false;
                z3 = true;
            } else {
                long j4 = readUnsignedInt;
                int i6 = i3;
                if ((((long) i4) + readUnsignedInt) - j3 >= ((long) i6)) {
                    z2 = false;
                    break;
                }
                int i7 = (int) (j4 - j3);
                i4 += i7;
                if (readInt == Atom.TYPE_ftyp) {
                    if (i7 < 8) {
                        return false;
                    }
                    parsableByteArray.reset(i7);
                    extractorInput2.peekFully(parsableByteArray.data, 0, i7);
                    int i8 = i7 / 4;
                    int i9 = 0;
                    while (true) {
                        if (i9 >= i8) {
                            z4 = z6;
                            break;
                        }
                        z4 = true;
                        if (i9 == 1) {
                            parsableByteArray.skipBytes(4);
                        } else if (isCompatibleBrand(parsableByteArray.readInt())) {
                            break;
                        }
                        i9++;
                    }
                    if (!z4) {
                        return false;
                    }
                    z6 = z4;
                } else if (i7 != 0) {
                    extractorInput2.advancePeekPosition(i7);
                }
                i3 = i6;
                j2 = -1;
                z5 = false;
            }
        }
        z3 = false;
        if (z6 && z == z3) {
            z2 = true;
        }
        return z2;
    }

    private static boolean isCompatibleBrand(int i) {
        if ((i >>> 8) == Util.getIntegerCodeForString("3gp")) {
            return true;
        }
        for (int i2 : COMPATIBLE_BRANDS) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    private Sniffer() {
    }
}
