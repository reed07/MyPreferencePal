package com.google.android.exoplayer2.metadata.id3;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class Id3Decoder implements MetadataDecoder {
    private static final int FRAME_FLAG_V3_HAS_GROUP_IDENTIFIER = 32;
    private static final int FRAME_FLAG_V3_IS_COMPRESSED = 128;
    private static final int FRAME_FLAG_V3_IS_ENCRYPTED = 64;
    private static final int FRAME_FLAG_V4_HAS_DATA_LENGTH = 1;
    private static final int FRAME_FLAG_V4_HAS_GROUP_IDENTIFIER = 64;
    private static final int FRAME_FLAG_V4_IS_COMPRESSED = 8;
    private static final int FRAME_FLAG_V4_IS_ENCRYPTED = 4;
    private static final int FRAME_FLAG_V4_IS_UNSYNCHRONIZED = 2;
    public static final int ID3_HEADER_LENGTH = 10;
    public static final int ID3_TAG = Util.getIntegerCodeForString("ID3");
    private static final int ID3_TEXT_ENCODING_ISO_8859_1 = 0;
    private static final int ID3_TEXT_ENCODING_UTF_16 = 1;
    private static final int ID3_TEXT_ENCODING_UTF_16BE = 2;
    private static final int ID3_TEXT_ENCODING_UTF_8 = 3;
    public static final FramePredicate NO_FRAMES_PREDICATE = $$Lambda$Id3Decoder$7M0gBIGKaTbyTVXWCb62bIHyc.INSTANCE;
    private static final String TAG = "Id3Decoder";
    @Nullable
    private final FramePredicate framePredicate;

    public interface FramePredicate {
        boolean evaluate(int i, int i2, int i3, int i4, int i5);
    }

    private static final class Id3Header {
        /* access modifiers changed from: private */
        public final int framesSize;
        /* access modifiers changed from: private */
        public final boolean isUnsynchronized;
        /* access modifiers changed from: private */
        public final int majorVersion;

        public Id3Header(int i, boolean z, int i2) {
            this.majorVersion = i;
            this.isUnsynchronized = z;
            this.framesSize = i2;
        }
    }

    private static int delimiterLength(int i) {
        return (i == 0 || i == 3) ? 1 : 2;
    }

    private static String getCharsetName(int i) {
        switch (i) {
            case 1:
                return C.UTF16_NAME;
            case 2:
                return "UTF-16BE";
            case 3:
                return "UTF-8";
            default:
                return "ISO-8859-1";
        }
    }

    static /* synthetic */ boolean lambda$static$0(int i, int i2, int i3, int i4, int i5) {
        return false;
    }

    public Id3Decoder() {
        this(null);
    }

    public Id3Decoder(@Nullable FramePredicate framePredicate2) {
        this.framePredicate = framePredicate2;
    }

    @Nullable
    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        return decode(byteBuffer.array(), byteBuffer.limit());
    }

    @Nullable
    public Metadata decode(byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        ParsableByteArray parsableByteArray = new ParsableByteArray(bArr, i);
        Id3Header decodeHeader = decodeHeader(parsableByteArray);
        if (decodeHeader == null) {
            return null;
        }
        int position = parsableByteArray.getPosition();
        int i2 = decodeHeader.majorVersion == 2 ? 6 : 10;
        int access$100 = decodeHeader.framesSize;
        if (decodeHeader.isUnsynchronized) {
            access$100 = removeUnsynchronization(parsableByteArray, decodeHeader.framesSize);
        }
        parsableByteArray.setLimit(position + access$100);
        boolean z = false;
        if (!validateFrames(parsableByteArray, decodeHeader.majorVersion, i2, false)) {
            if (decodeHeader.majorVersion != 4 || !validateFrames(parsableByteArray, 4, i2, true)) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to validate ID3 tag with majorVersion=");
                sb.append(decodeHeader.majorVersion);
                Log.w(str, sb.toString());
                return null;
            }
            z = true;
        }
        while (parsableByteArray.bytesLeft() >= i2) {
            Id3Frame decodeFrame = decodeFrame(decodeHeader.majorVersion, parsableByteArray, z, i2, this.framePredicate);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        return new Metadata((List<? extends Entry>) arrayList);
    }

    @Nullable
    private static Id3Header decodeHeader(ParsableByteArray parsableByteArray) {
        if (parsableByteArray.bytesLeft() < 10) {
            Log.w(TAG, "Data too short to be an ID3 tag");
            return null;
        }
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        if (readUnsignedInt24 != ID3_TAG) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Unexpected first three bytes of ID3 tag header: ");
            sb.append(readUnsignedInt24);
            Log.w(str, sb.toString());
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        boolean z = true;
        parsableByteArray.skipBytes(1);
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        int readSynchSafeInt = parsableByteArray.readSynchSafeInt();
        if (readUnsignedByte == 2) {
            if ((readUnsignedByte2 & 64) != 0) {
                Log.w(TAG, "Skipped ID3 tag with majorVersion=2 and undefined compression scheme");
                return null;
            }
        } else if (readUnsignedByte == 3) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readInt = parsableByteArray.readInt();
                parsableByteArray.skipBytes(readInt);
                readSynchSafeInt -= readInt + 4;
            }
        } else if (readUnsignedByte == 4) {
            if ((readUnsignedByte2 & 64) != 0) {
                int readSynchSafeInt2 = parsableByteArray.readSynchSafeInt();
                parsableByteArray.skipBytes(readSynchSafeInt2 - 4);
                readSynchSafeInt -= readSynchSafeInt2;
            }
            if ((readUnsignedByte2 & 16) != 0) {
                readSynchSafeInt -= 10;
            }
        } else {
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Skipped ID3 tag with unsupported majorVersion=");
            sb2.append(readUnsignedByte);
            Log.w(str2, sb2.toString());
            return null;
        }
        if (readUnsignedByte >= 4 || (readUnsignedByte2 & 128) == 0) {
            z = false;
        }
        return new Id3Header(readUnsignedByte, z, readSynchSafeInt);
    }

    private static boolean validateFrames(ParsableByteArray parsableByteArray, int i, int i2, boolean z) {
        int i3;
        long j;
        int i4;
        boolean z2;
        boolean z3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i5 = i;
        int position = parsableByteArray.getPosition();
        while (true) {
            try {
                int i6 = 1;
                if (parsableByteArray.bytesLeft() >= i2) {
                    if (i5 >= 3) {
                        i4 = parsableByteArray.readInt();
                        j = parsableByteArray.readUnsignedInt();
                        i3 = parsableByteArray.readUnsignedShort();
                    } else {
                        i4 = parsableByteArray.readUnsignedInt24();
                        j = (long) parsableByteArray.readUnsignedInt24();
                        i3 = 0;
                    }
                    if (i4 == 0 && j == 0 && i3 == 0) {
                        parsableByteArray2.setPosition(position);
                        return true;
                    }
                    if (i5 == 4 && !z) {
                        if ((8421504 & j) != 0) {
                            parsableByteArray2.setPosition(position);
                            return false;
                        }
                        j = (((j >> 24) & 255) << 21) | (j & 255) | (((j >> 8) & 255) << 7) | (((j >> 16) & 255) << 14);
                    }
                    if (i5 == 4) {
                        z3 = (i3 & 64) != 0;
                        z2 = (i3 & 1) != 0;
                    } else if (i5 == 3) {
                        z3 = (i3 & 32) != 0;
                        z2 = (i3 & 128) != 0;
                    } else {
                        z3 = false;
                        z2 = false;
                    }
                    if (!z3) {
                        i6 = 0;
                    }
                    if (z2) {
                        i6 += 4;
                    }
                    if (j < ((long) i6)) {
                        parsableByteArray2.setPosition(position);
                        return false;
                    } else if (((long) parsableByteArray.bytesLeft()) < j) {
                        return false;
                    } else {
                        parsableByteArray2.skipBytes((int) j);
                    }
                } else {
                    parsableByteArray2.setPosition(position);
                    return true;
                }
            } finally {
                parsableByteArray2.setPosition(position);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:131:0x0193, code lost:
        if (r13 == 67) goto L_0x0195;
     */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.exoplayer2.metadata.id3.Id3Frame decodeFrame(int r19, com.google.android.exoplayer2.util.ParsableByteArray r20, boolean r21, int r22, @android.support.annotation.Nullable com.google.android.exoplayer2.metadata.id3.Id3Decoder.FramePredicate r23) {
        /*
            r0 = r19
            r7 = r20
            int r8 = r20.readUnsignedByte()
            int r9 = r20.readUnsignedByte()
            int r10 = r20.readUnsignedByte()
            r11 = 3
            if (r0 < r11) goto L_0x0019
            int r1 = r20.readUnsignedByte()
            r13 = r1
            goto L_0x001a
        L_0x0019:
            r13 = 0
        L_0x001a:
            r14 = 4
            if (r0 != r14) goto L_0x003e
            int r1 = r20.readUnsignedIntToInt()
            if (r21 != 0) goto L_0x003c
            r2 = r1 & 255(0xff, float:3.57E-43)
            int r3 = r1 >> 8
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 7
            r2 = r2 | r3
            int r3 = r1 >> 16
            r3 = r3 & 255(0xff, float:3.57E-43)
            int r3 = r3 << 14
            r2 = r2 | r3
            int r1 = r1 >> 24
            r1 = r1 & 255(0xff, float:3.57E-43)
            int r1 = r1 << 21
            r1 = r1 | r2
            r15 = r1
            goto L_0x004b
        L_0x003c:
            r15 = r1
            goto L_0x004b
        L_0x003e:
            if (r0 != r11) goto L_0x0046
            int r1 = r20.readUnsignedIntToInt()
            r15 = r1
            goto L_0x004b
        L_0x0046:
            int r1 = r20.readUnsignedInt24()
            r15 = r1
        L_0x004b:
            if (r0 < r11) goto L_0x0053
            int r1 = r20.readUnsignedShort()
            r6 = r1
            goto L_0x0054
        L_0x0053:
            r6 = 0
        L_0x0054:
            r16 = 0
            if (r8 != 0) goto L_0x006a
            if (r9 != 0) goto L_0x006a
            if (r10 != 0) goto L_0x006a
            if (r13 != 0) goto L_0x006a
            if (r15 != 0) goto L_0x006a
            if (r6 != 0) goto L_0x006a
            int r0 = r20.limit()
            r7.setPosition(r0)
            return r16
        L_0x006a:
            int r1 = r20.getPosition()
            int r5 = r1 + r15
            int r1 = r20.limit()
            if (r5 <= r1) goto L_0x0085
            java.lang.String r0 = "Id3Decoder"
            java.lang.String r1 = "Frame size exceeds remaining tag data"
            com.google.android.exoplayer2.util.Log.w(r0, r1)
            int r0 = r20.limit()
            r7.setPosition(r0)
            return r16
        L_0x0085:
            if (r23 == 0) goto L_0x009b
            r1 = r23
            r2 = r19
            r3 = r8
            r4 = r9
            r12 = r5
            r5 = r10
            r14 = r6
            r6 = r13
            boolean r1 = r1.evaluate(r2, r3, r4, r5, r6)
            if (r1 != 0) goto L_0x009d
            r7.setPosition(r12)
            return r16
        L_0x009b:
            r12 = r5
            r14 = r6
        L_0x009d:
            r1 = 1
            if (r0 != r11) goto L_0x00bc
            r2 = r14 & 128(0x80, float:1.794E-43)
            if (r2 == 0) goto L_0x00a6
            r2 = 1
            goto L_0x00a7
        L_0x00a6:
            r2 = 0
        L_0x00a7:
            r3 = r14 & 64
            if (r3 == 0) goto L_0x00ad
            r3 = 1
            goto L_0x00ae
        L_0x00ad:
            r3 = 0
        L_0x00ae:
            r4 = r14 & 32
            if (r4 == 0) goto L_0x00b4
            r4 = 1
            goto L_0x00b5
        L_0x00b4:
            r4 = 0
        L_0x00b5:
            r17 = r2
            r5 = 0
            r2 = r4
            r4 = r17
            goto L_0x00f2
        L_0x00bc:
            r2 = 4
            if (r0 != r2) goto L_0x00ec
            r2 = r14 & 64
            if (r2 == 0) goto L_0x00c5
            r2 = 1
            goto L_0x00c6
        L_0x00c5:
            r2 = 0
        L_0x00c6:
            r3 = r14 & 8
            if (r3 == 0) goto L_0x00cc
            r3 = 1
            goto L_0x00cd
        L_0x00cc:
            r3 = 0
        L_0x00cd:
            r4 = r14 & 4
            if (r4 == 0) goto L_0x00d3
            r4 = 1
            goto L_0x00d4
        L_0x00d3:
            r4 = 0
        L_0x00d4:
            r5 = r14 & 2
            if (r5 == 0) goto L_0x00da
            r5 = 1
            goto L_0x00db
        L_0x00da:
            r5 = 0
        L_0x00db:
            r6 = r14 & 1
            if (r6 == 0) goto L_0x00e2
            r17 = 1
            goto L_0x00e4
        L_0x00e2:
            r17 = 0
        L_0x00e4:
            r18 = r17
            r17 = r3
            r3 = r4
            r4 = r18
            goto L_0x00f2
        L_0x00ec:
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r17 = 0
        L_0x00f2:
            if (r17 != 0) goto L_0x0230
            if (r3 == 0) goto L_0x00f8
            goto L_0x0230
        L_0x00f8:
            if (r2 == 0) goto L_0x00ff
            int r15 = r15 + -1
            r7.skipBytes(r1)
        L_0x00ff:
            if (r4 == 0) goto L_0x0107
            int r15 = r15 + -4
            r1 = 4
            r7.skipBytes(r1)
        L_0x0107:
            if (r5 == 0) goto L_0x010e
            int r1 = removeUnsynchronization(r7, r15)
            r15 = r1
        L_0x010e:
            r1 = 84
            r2 = 2
            r3 = 88
            if (r8 != r1) goto L_0x0123
            if (r9 != r3) goto L_0x0123
            if (r10 != r3) goto L_0x0123
            if (r0 == r2) goto L_0x011d
            if (r13 != r3) goto L_0x0123
        L_0x011d:
            com.google.android.exoplayer2.metadata.id3.TextInformationFrame r1 = decodeTxxxFrame(r7, r15)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x0123:
            if (r8 != r1) goto L_0x0132
            java.lang.String r1 = getFrameId(r0, r8, r9, r10, r13)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            com.google.android.exoplayer2.metadata.id3.TextInformationFrame r1 = decodeTextInformationFrame(r7, r15, r1)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x012f:
            r0 = move-exception
            goto L_0x022c
        L_0x0132:
            r4 = 87
            if (r8 != r4) goto L_0x0144
            if (r9 != r3) goto L_0x0144
            if (r10 != r3) goto L_0x0144
            if (r0 == r2) goto L_0x013e
            if (r13 != r3) goto L_0x0144
        L_0x013e:
            com.google.android.exoplayer2.metadata.id3.UrlLinkFrame r1 = decodeWxxxFrame(r7, r15)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x0144:
            r3 = 87
            if (r8 != r3) goto L_0x0152
            java.lang.String r1 = getFrameId(r0, r8, r9, r10, r13)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            com.google.android.exoplayer2.metadata.id3.UrlLinkFrame r1 = decodeUrlLinkFrame(r7, r15, r1)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x0152:
            r3 = 73
            r4 = 80
            if (r8 != r4) goto L_0x0168
            r5 = 82
            if (r9 != r5) goto L_0x0168
            if (r10 != r3) goto L_0x0168
            r5 = 86
            if (r13 != r5) goto L_0x0168
            com.google.android.exoplayer2.metadata.id3.PrivFrame r1 = decodePrivFrame(r7, r15)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x0168:
            r5 = 71
            r6 = 79
            if (r8 != r5) goto L_0x0180
            r5 = 69
            if (r9 != r5) goto L_0x0180
            if (r10 != r6) goto L_0x0180
            r5 = 66
            if (r13 == r5) goto L_0x017a
            if (r0 != r2) goto L_0x0180
        L_0x017a:
            com.google.android.exoplayer2.metadata.id3.GeobFrame r1 = decodeGeobFrame(r7, r15)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x0180:
            r5 = 67
            if (r0 != r2) goto L_0x018b
            if (r8 != r4) goto L_0x019b
            if (r9 != r3) goto L_0x019b
            if (r10 != r5) goto L_0x019b
            goto L_0x0195
        L_0x018b:
            r11 = 65
            if (r8 != r11) goto L_0x019b
            if (r9 != r4) goto L_0x019b
            if (r10 != r3) goto L_0x019b
            if (r13 != r5) goto L_0x019b
        L_0x0195:
            com.google.android.exoplayer2.metadata.id3.ApicFrame r1 = decodeApicFrame(r7, r15, r0)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x019b:
            r3 = 77
            if (r8 != r5) goto L_0x01ac
            if (r9 != r6) goto L_0x01ac
            if (r10 != r3) goto L_0x01ac
            if (r13 == r3) goto L_0x01a7
            if (r0 != r2) goto L_0x01ac
        L_0x01a7:
            com.google.android.exoplayer2.metadata.id3.CommentFrame r1 = decodeCommentFrame(r7, r15)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x01ac:
            if (r8 != r5) goto L_0x01c8
            r2 = 72
            if (r9 != r2) goto L_0x01c8
            r2 = 65
            if (r10 != r2) goto L_0x01c8
            if (r13 != r4) goto L_0x01c8
            r1 = r20
            r2 = r15
            r3 = r19
            r4 = r21
            r5 = r22
            r6 = r23
            com.google.android.exoplayer2.metadata.id3.ChapterFrame r1 = decodeChapterFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x01c8:
            if (r8 != r5) goto L_0x01e0
            if (r9 != r1) goto L_0x01e0
            if (r10 != r6) goto L_0x01e0
            if (r13 != r5) goto L_0x01e0
            r1 = r20
            r2 = r15
            r3 = r19
            r4 = r21
            r5 = r22
            r6 = r23
            com.google.android.exoplayer2.metadata.id3.ChapterTocFrame r1 = decodeChapterTOCFrame(r1, r2, r3, r4, r5, r6)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x01e0:
            if (r8 != r3) goto L_0x01f1
            r2 = 76
            if (r9 != r2) goto L_0x01f1
            r2 = 76
            if (r10 != r2) goto L_0x01f1
            if (r13 != r1) goto L_0x01f1
            com.google.android.exoplayer2.metadata.id3.MlltFrame r1 = decodeMlltFrame(r7, r15)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            goto L_0x01f9
        L_0x01f1:
            java.lang.String r1 = getFrameId(r0, r8, r9, r10, r13)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            com.google.android.exoplayer2.metadata.id3.BinaryFrame r1 = decodeBinaryFrame(r7, r15, r1)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
        L_0x01f9:
            if (r1 != 0) goto L_0x021d
            java.lang.String r2 = "Id3Decoder"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            r3.<init>()     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            java.lang.String r4 = "Failed to decode frame: id="
            r3.append(r4)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            java.lang.String r0 = getFrameId(r0, r8, r9, r10, r13)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            r3.append(r0)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            java.lang.String r0 = ", frameSize="
            r3.append(r0)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            r3.append(r15)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            java.lang.String r0 = r3.toString()     // Catch:{ UnsupportedEncodingException -> 0x0221 }
            com.google.android.exoplayer2.util.Log.w(r2, r0)     // Catch:{ UnsupportedEncodingException -> 0x0221 }
        L_0x021d:
            r7.setPosition(r12)
            return r1
        L_0x0221:
            java.lang.String r0 = "Id3Decoder"
            java.lang.String r1 = "Unsupported character encoding"
            com.google.android.exoplayer2.util.Log.w(r0, r1)     // Catch:{ all -> 0x012f }
            r7.setPosition(r12)
            return r16
        L_0x022c:
            r7.setPosition(r12)
            throw r0
        L_0x0230:
            java.lang.String r0 = "Id3Decoder"
            java.lang.String r1 = "Skipping unsupported compressed or encrypted frame"
            com.google.android.exoplayer2.util.Log.w(r0, r1)
            r7.setPosition(r12)
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.metadata.id3.Id3Decoder.decodeFrame(int, com.google.android.exoplayer2.util.ParsableByteArray, boolean, int, com.google.android.exoplayer2.metadata.id3.Id3Decoder$FramePredicate):com.google.android.exoplayer2.metadata.id3.Id3Frame");
    }

    @Nullable
    private static TextInformationFrame decodeTxxxFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfEos = indexOfEos(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        return new TextInformationFrame("TXXX", str, decodeStringIfValid(bArr, delimiterLength, indexOfEos(bArr, delimiterLength, readUnsignedByte), charsetName));
    }

    @Nullable
    private static TextInformationFrame decodeTextInformationFrame(ParsableByteArray parsableByteArray, int i, String str) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        return new TextInformationFrame(str, null, new String(bArr, 0, indexOfEos(bArr, 0, readUnsignedByte), charsetName));
    }

    @Nullable
    private static UrlLinkFrame decodeWxxxFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        if (i < 1) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfEos = indexOfEos(bArr, 0, readUnsignedByte);
        String str = new String(bArr, 0, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        return new UrlLinkFrame("WXXX", str, decodeStringIfValid(bArr, delimiterLength, indexOfZeroByte(bArr, delimiterLength), "ISO-8859-1"));
    }

    private static UrlLinkFrame decodeUrlLinkFrame(ParsableByteArray parsableByteArray, int i, String str) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new UrlLinkFrame(str, null, new String(bArr, 0, indexOfZeroByte(bArr, 0), "ISO-8859-1"));
    }

    private static PrivFrame decodePrivFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        return new PrivFrame(new String(bArr, 0, indexOfZeroByte, "ISO-8859-1"), copyOfRangeIfValid(bArr, indexOfZeroByte + 1, bArr.length));
    }

    private static GeobFrame decodeGeobFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i2 = i - 1;
        byte[] bArr = new byte[i2];
        parsableByteArray.readBytes(bArr, 0, i2);
        int indexOfZeroByte = indexOfZeroByte(bArr, 0);
        String str = new String(bArr, 0, indexOfZeroByte, "ISO-8859-1");
        int i3 = indexOfZeroByte + 1;
        int indexOfEos = indexOfEos(bArr, i3, readUnsignedByte);
        String decodeStringIfValid = decodeStringIfValid(bArr, i3, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        int indexOfEos2 = indexOfEos(bArr, delimiterLength, readUnsignedByte);
        return new GeobFrame(str, decodeStringIfValid, decodeStringIfValid(bArr, delimiterLength, indexOfEos2, charsetName), copyOfRangeIfValid(bArr, indexOfEos2 + delimiterLength(readUnsignedByte), bArr.length));
    }

    private static ApicFrame decodeApicFrame(ParsableByteArray parsableByteArray, int i, int i2) throws UnsupportedEncodingException {
        String str;
        int i3;
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        int i4 = i - 1;
        byte[] bArr = new byte[i4];
        parsableByteArray.readBytes(bArr, 0, i4);
        if (i2 == 2) {
            StringBuilder sb = new StringBuilder();
            sb.append("image/");
            sb.append(Util.toLowerInvariant(new String(bArr, 0, 3, "ISO-8859-1")));
            String sb2 = sb.toString();
            if ("image/jpg".equals(sb2)) {
                str = "image/jpeg";
                i3 = 2;
            } else {
                str = sb2;
                i3 = 2;
            }
        } else {
            i3 = indexOfZeroByte(bArr, 0);
            str = Util.toLowerInvariant(new String(bArr, 0, i3, "ISO-8859-1"));
            if (str.indexOf(47) == -1) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("image/");
                sb3.append(str);
                str = sb3.toString();
            }
        }
        byte b = bArr[i3 + 1] & 255;
        int i5 = i3 + 2;
        int indexOfEos = indexOfEos(bArr, i5, readUnsignedByte);
        return new ApicFrame(str, new String(bArr, i5, indexOfEos - i5, charsetName), b, copyOfRangeIfValid(bArr, indexOfEos + delimiterLength(readUnsignedByte), bArr.length));
    }

    @Nullable
    private static CommentFrame decodeCommentFrame(ParsableByteArray parsableByteArray, int i) throws UnsupportedEncodingException {
        if (i < 4) {
            return null;
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        String charsetName = getCharsetName(readUnsignedByte);
        byte[] bArr = new byte[3];
        parsableByteArray.readBytes(bArr, 0, 3);
        String str = new String(bArr, 0, 3);
        int i2 = i - 4;
        byte[] bArr2 = new byte[i2];
        parsableByteArray.readBytes(bArr2, 0, i2);
        int indexOfEos = indexOfEos(bArr2, 0, readUnsignedByte);
        String str2 = new String(bArr2, 0, indexOfEos, charsetName);
        int delimiterLength = indexOfEos + delimiterLength(readUnsignedByte);
        return new CommentFrame(str, str2, decodeStringIfValid(bArr2, delimiterLength, indexOfEos(bArr2, delimiterLength, readUnsignedByte), charsetName));
    }

    private static ChapterFrame decodeChapterFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, @Nullable FramePredicate framePredicate2) throws UnsupportedEncodingException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray2.data, position);
        String str = new String(parsableByteArray2.data, position, indexOfZeroByte - position, "ISO-8859-1");
        parsableByteArray.setPosition(indexOfZeroByte + 1);
        int readInt = parsableByteArray.readInt();
        int readInt2 = parsableByteArray.readInt();
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long j = readUnsignedInt == 4294967295L ? -1 : readUnsignedInt;
        long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
        long j2 = readUnsignedInt2 == 4294967295L ? -1 : readUnsignedInt2;
        ArrayList arrayList = new ArrayList();
        int i4 = position + i;
        while (parsableByteArray.getPosition() < i4) {
            Id3Frame decodeFrame = decodeFrame(i2, parsableByteArray, z, i3, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        Id3Frame[] id3FrameArr = new Id3Frame[arrayList.size()];
        arrayList.toArray(id3FrameArr);
        ChapterFrame chapterFrame = new ChapterFrame(str, readInt, readInt2, j, j2, id3FrameArr);
        return chapterFrame;
    }

    private static ChapterTocFrame decodeChapterTOCFrame(ParsableByteArray parsableByteArray, int i, int i2, boolean z, int i3, @Nullable FramePredicate framePredicate2) throws UnsupportedEncodingException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int position = parsableByteArray.getPosition();
        int indexOfZeroByte = indexOfZeroByte(parsableByteArray2.data, position);
        String str = new String(parsableByteArray2.data, position, indexOfZeroByte - position, "ISO-8859-1");
        parsableByteArray.setPosition(indexOfZeroByte + 1);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        boolean z2 = (readUnsignedByte & 2) != 0;
        boolean z3 = (readUnsignedByte & 1) != 0;
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        String[] strArr = new String[readUnsignedByte2];
        for (int i4 = 0; i4 < readUnsignedByte2; i4++) {
            int position2 = parsableByteArray.getPosition();
            int indexOfZeroByte2 = indexOfZeroByte(parsableByteArray2.data, position2);
            strArr[i4] = new String(parsableByteArray2.data, position2, indexOfZeroByte2 - position2, "ISO-8859-1");
            parsableByteArray.setPosition(indexOfZeroByte2 + 1);
        }
        ArrayList arrayList = new ArrayList();
        int i5 = position + i;
        while (parsableByteArray.getPosition() < i5) {
            Id3Frame decodeFrame = decodeFrame(i2, parsableByteArray, z, i3, framePredicate2);
            if (decodeFrame != null) {
                arrayList.add(decodeFrame);
            }
        }
        Id3Frame[] id3FrameArr = new Id3Frame[arrayList.size()];
        arrayList.toArray(id3FrameArr);
        ChapterTocFrame chapterTocFrame = new ChapterTocFrame(str, z2, z3, strArr, id3FrameArr);
        return chapterTocFrame;
    }

    private static MlltFrame decodeMlltFrame(ParsableByteArray parsableByteArray, int i) {
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int readUnsignedInt24 = parsableByteArray.readUnsignedInt24();
        int readUnsignedInt242 = parsableByteArray.readUnsignedInt24();
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
        ParsableBitArray parsableBitArray = new ParsableBitArray();
        parsableBitArray.reset(parsableByteArray);
        int i2 = ((i - 10) * 8) / (readUnsignedByte + readUnsignedByte2);
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            int readBits = parsableBitArray.readBits(readUnsignedByte);
            int readBits2 = parsableBitArray.readBits(readUnsignedByte2);
            iArr[i3] = readBits;
            iArr2[i3] = readBits2;
        }
        MlltFrame mlltFrame = new MlltFrame(readUnsignedShort, readUnsignedInt24, readUnsignedInt242, iArr, iArr2);
        return mlltFrame;
    }

    private static BinaryFrame decodeBinaryFrame(ParsableByteArray parsableByteArray, int i, String str) {
        byte[] bArr = new byte[i];
        parsableByteArray.readBytes(bArr, 0, i);
        return new BinaryFrame(str, bArr);
    }

    private static int removeUnsynchronization(ParsableByteArray parsableByteArray, int i) {
        byte[] bArr = parsableByteArray.data;
        int position = parsableByteArray.getPosition();
        while (true) {
            int i2 = position + 1;
            if (i2 >= i) {
                return i;
            }
            if ((bArr[position] & 255) == 255 && bArr[i2] == 0) {
                System.arraycopy(bArr, position + 2, bArr, i2, (i - position) - 2);
                i--;
            }
            position = i2;
        }
    }

    private static String getFrameId(int i, int i2, int i3, int i4, int i5) {
        if (i == 2) {
            return String.format(Locale.US, "%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
        }
        return String.format(Locale.US, "%c%c%c%c", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)});
    }

    private static int indexOfEos(byte[] bArr, int i, int i2) {
        int indexOfZeroByte = indexOfZeroByte(bArr, i);
        if (i2 == 0 || i2 == 3) {
            return indexOfZeroByte;
        }
        while (indexOfZeroByte < bArr.length - 1) {
            if (indexOfZeroByte % 2 == 0 && bArr[indexOfZeroByte + 1] == 0) {
                return indexOfZeroByte;
            }
            indexOfZeroByte = indexOfZeroByte(bArr, indexOfZeroByte + 1);
        }
        return bArr.length;
    }

    private static int indexOfZeroByte(byte[] bArr, int i) {
        while (i < bArr.length) {
            if (bArr[i] == 0) {
                return i;
            }
            i++;
        }
        return bArr.length;
    }

    private static byte[] copyOfRangeIfValid(byte[] bArr, int i, int i2) {
        if (i2 <= i) {
            return Util.EMPTY_BYTE_ARRAY;
        }
        return Arrays.copyOfRange(bArr, i, i2);
    }

    private static String decodeStringIfValid(byte[] bArr, int i, int i2, String str) throws UnsupportedEncodingException {
        return (i2 <= i || i2 > bArr.length) ? "" : new String(bArr, i, i2 - i, str);
    }
}
