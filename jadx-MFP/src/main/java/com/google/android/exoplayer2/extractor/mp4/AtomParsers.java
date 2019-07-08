package com.google.android.exoplayer2.extractor.mp4;

import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.mp4.FixedSampleSizeRechunker.Results;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata.Entry;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final class AtomParsers {
    private static final int MAX_GAPLESS_TRIM_SIZE_SAMPLES = 3;
    private static final String TAG = "AtomParsers";
    private static final int TYPE_clcp = Util.getIntegerCodeForString("clcp");
    private static final int TYPE_mdta = Util.getIntegerCodeForString("mdta");
    private static final int TYPE_meta = Util.getIntegerCodeForString("meta");
    private static final int TYPE_sbtl = Util.getIntegerCodeForString("sbtl");
    private static final int TYPE_soun = Util.getIntegerCodeForString("soun");
    private static final int TYPE_subt = Util.getIntegerCodeForString("subt");
    private static final int TYPE_text = Util.getIntegerCodeForString("text");
    private static final int TYPE_vide = Util.getIntegerCodeForString("vide");
    private static final byte[] opusMagic = Util.getUtf8Bytes("OpusHead");

    private static final class ChunkIterator {
        private final ParsableByteArray chunkOffsets;
        private final boolean chunkOffsetsAreLongs;
        public int index;
        public final int length;
        private int nextSamplesPerChunkChangeIndex;
        public int numSamples;
        public long offset;
        private int remainingSamplesPerChunkChanges;
        private final ParsableByteArray stsc;

        public ChunkIterator(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, boolean z) {
            this.stsc = parsableByteArray;
            this.chunkOffsets = parsableByteArray2;
            this.chunkOffsetsAreLongs = z;
            parsableByteArray2.setPosition(12);
            this.length = parsableByteArray2.readUnsignedIntToInt();
            parsableByteArray.setPosition(12);
            this.remainingSamplesPerChunkChanges = parsableByteArray.readUnsignedIntToInt();
            boolean z2 = true;
            if (parsableByteArray.readInt() != 1) {
                z2 = false;
            }
            Assertions.checkState(z2, "first_chunk must be 1");
            this.index = -1;
        }

        public boolean moveNext() {
            long j;
            int i = this.index + 1;
            this.index = i;
            if (i == this.length) {
                return false;
            }
            if (this.chunkOffsetsAreLongs) {
                j = this.chunkOffsets.readUnsignedLongToLong();
            } else {
                j = this.chunkOffsets.readUnsignedInt();
            }
            this.offset = j;
            if (this.index == this.nextSamplesPerChunkChangeIndex) {
                this.numSamples = this.stsc.readUnsignedIntToInt();
                this.stsc.skipBytes(4);
                int i2 = this.remainingSamplesPerChunkChanges - 1;
                this.remainingSamplesPerChunkChanges = i2;
                this.nextSamplesPerChunkChangeIndex = i2 > 0 ? this.stsc.readUnsignedIntToInt() - 1 : -1;
            }
            return true;
        }
    }

    private interface SampleSizeBox {
        int getSampleCount();

        boolean isFixedSampleSize();

        int readNextSampleSize();
    }

    private static final class StsdData {
        public static final int STSD_HEADER_SIZE = 8;
        public Format format;
        public int nalUnitLengthFieldLength;
        public int requiredSampleTransformation = 0;
        public final TrackEncryptionBox[] trackEncryptionBoxes;

        public StsdData(int i) {
            this.trackEncryptionBoxes = new TrackEncryptionBox[i];
        }
    }

    static final class StszSampleSizeBox implements SampleSizeBox {
        private final ParsableByteArray data;
        private final int fixedSampleSize = this.data.readUnsignedIntToInt();
        private final int sampleCount = this.data.readUnsignedIntToInt();

        public StszSampleSizeBox(LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            int i = this.fixedSampleSize;
            return i == 0 ? this.data.readUnsignedIntToInt() : i;
        }

        public boolean isFixedSampleSize() {
            return this.fixedSampleSize != 0;
        }
    }

    static final class Stz2SampleSizeBox implements SampleSizeBox {
        private int currentByte;
        private final ParsableByteArray data;
        private final int fieldSize = (this.data.readUnsignedIntToInt() & 255);
        private final int sampleCount = this.data.readUnsignedIntToInt();
        private int sampleIndex;

        public boolean isFixedSampleSize() {
            return false;
        }

        public Stz2SampleSizeBox(LeafAtom leafAtom) {
            this.data = leafAtom.data;
            this.data.setPosition(12);
        }

        public int getSampleCount() {
            return this.sampleCount;
        }

        public int readNextSampleSize() {
            int i = this.fieldSize;
            if (i == 8) {
                return this.data.readUnsignedByte();
            }
            if (i == 16) {
                return this.data.readUnsignedShort();
            }
            int i2 = this.sampleIndex;
            this.sampleIndex = i2 + 1;
            if (i2 % 2 != 0) {
                return this.currentByte & 15;
            }
            this.currentByte = this.data.readUnsignedByte();
            return (this.currentByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
        }
    }

    private static final class TkhdData {
        /* access modifiers changed from: private */
        public final long duration;
        /* access modifiers changed from: private */
        public final int id;
        /* access modifiers changed from: private */
        public final int rotationDegrees;

        public TkhdData(int i, long j, int i2) {
            this.id = i;
            this.duration = j;
            this.rotationDegrees = i2;
        }
    }

    public static Track parseTrak(ContainerAtom containerAtom, LeafAtom leafAtom, long j, DrmInitData drmInitData, boolean z, boolean z2) throws ParserException {
        long j2;
        LeafAtom leafAtom2;
        long j3;
        long[] jArr;
        long[] jArr2;
        Track track;
        ContainerAtom containerAtom2 = containerAtom;
        ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia);
        int trackTypeForHdlr = getTrackTypeForHdlr(parseHdlr(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_hdlr).data));
        if (trackTypeForHdlr == -1) {
            return null;
        }
        TkhdData parseTkhd = parseTkhd(containerAtom2.getLeafAtomOfType(Atom.TYPE_tkhd).data);
        if (j == -9223372036854775807L) {
            j2 = parseTkhd.duration;
            leafAtom2 = leafAtom;
        } else {
            leafAtom2 = leafAtom;
            j2 = j;
        }
        long parseMvhd = parseMvhd(leafAtom2.data);
        if (j2 == -9223372036854775807L) {
            j3 = -9223372036854775807L;
        } else {
            j3 = Util.scaleLargeTimestamp(j2, 1000000, parseMvhd);
        }
        ContainerAtom containerAtomOfType2 = containerAtomOfType.getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl);
        Pair parseMdhd = parseMdhd(containerAtomOfType.getLeafAtomOfType(Atom.TYPE_mdhd).data);
        StsdData parseStsd = parseStsd(containerAtomOfType2.getLeafAtomOfType(Atom.TYPE_stsd).data, parseTkhd.id, parseTkhd.rotationDegrees, (String) parseMdhd.second, drmInitData, z2);
        if (!z) {
            Pair parseEdts = parseEdts(containerAtom2.getContainerAtomOfType(Atom.TYPE_edts));
            jArr = (long[]) parseEdts.second;
            jArr2 = (long[]) parseEdts.first;
        } else {
            jArr2 = null;
            jArr = null;
        }
        if (parseStsd.format == null) {
            track = null;
        } else {
            int access$100 = parseTkhd.id;
            long longValue = ((Long) parseMdhd.first).longValue();
            Format format = parseStsd.format;
            int i = parseStsd.requiredSampleTransformation;
            TrackEncryptionBox[] trackEncryptionBoxArr = parseStsd.trackEncryptionBoxes;
            int i2 = parseStsd.nalUnitLengthFieldLength;
            track = new Track(access$100, trackTypeForHdlr, longValue, parseMvhd, j3, format, i, trackEncryptionBoxArr, i2, jArr2, jArr);
        }
        return track;
    }

    public static TrackSampleTable parseStbl(Track track, ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder) throws ParserException {
        SampleSizeBox sampleSizeBox;
        boolean z;
        int i;
        int i2;
        long j;
        int[] iArr;
        long[] jArr;
        int i3;
        int[] iArr2;
        long[] jArr2;
        int i4;
        int i5;
        int[] iArr3;
        long[] jArr3;
        int[] iArr4;
        int[] iArr5;
        int i6;
        boolean z2;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z3;
        int i11;
        int i12;
        int i13;
        int i14;
        SampleSizeBox sampleSizeBox2;
        Track track2 = track;
        ContainerAtom containerAtom2 = containerAtom;
        GaplessInfoHolder gaplessInfoHolder2 = gaplessInfoHolder;
        LeafAtom leafAtomOfType = containerAtom2.getLeafAtomOfType(Atom.TYPE_stsz);
        if (leafAtomOfType != null) {
            sampleSizeBox = new StszSampleSizeBox(leafAtomOfType);
        } else {
            LeafAtom leafAtomOfType2 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stz2);
            if (leafAtomOfType2 != null) {
                sampleSizeBox = new Stz2SampleSizeBox(leafAtomOfType2);
            } else {
                throw new ParserException("Track has no sample table size information");
            }
        }
        int sampleCount = sampleSizeBox.getSampleCount();
        if (sampleCount == 0) {
            TrackSampleTable trackSampleTable = new TrackSampleTable(track, new long[0], new int[0], 0, new long[0], new int[0], -9223372036854775807L);
            return trackSampleTable;
        }
        LeafAtom leafAtomOfType3 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stco);
        if (leafAtomOfType3 == null) {
            leafAtomOfType3 = containerAtom2.getLeafAtomOfType(Atom.TYPE_co64);
            z = true;
        } else {
            z = false;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType3.data;
        ParsableByteArray parsableByteArray2 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stsc).data;
        ParsableByteArray parsableByteArray3 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stts).data;
        LeafAtom leafAtomOfType4 = containerAtom2.getLeafAtomOfType(Atom.TYPE_stss);
        ParsableByteArray parsableByteArray4 = leafAtomOfType4 != null ? leafAtomOfType4.data : null;
        LeafAtom leafAtomOfType5 = containerAtom2.getLeafAtomOfType(Atom.TYPE_ctts);
        ParsableByteArray parsableByteArray5 = leafAtomOfType5 != null ? leafAtomOfType5.data : null;
        ChunkIterator chunkIterator = new ChunkIterator(parsableByteArray2, parsableByteArray, z);
        parsableByteArray3.setPosition(12);
        int readUnsignedIntToInt = parsableByteArray3.readUnsignedIntToInt() - 1;
        int readUnsignedIntToInt2 = parsableByteArray3.readUnsignedIntToInt();
        int readUnsignedIntToInt3 = parsableByteArray3.readUnsignedIntToInt();
        if (parsableByteArray5 != null) {
            parsableByteArray5.setPosition(12);
            i = parsableByteArray5.readUnsignedIntToInt();
        } else {
            i = 0;
        }
        int i15 = -1;
        if (parsableByteArray4 != null) {
            parsableByteArray4.setPosition(12);
            i2 = parsableByteArray4.readUnsignedIntToInt();
            if (i2 > 0) {
                i15 = parsableByteArray4.readUnsignedIntToInt() - 1;
            } else {
                parsableByteArray4 = null;
            }
        } else {
            i2 = 0;
        }
        if (!(sampleSizeBox.isFixedSampleSize() && MimeTypes.AUDIO_RAW.equals(track2.format.sampleMimeType) && readUnsignedIntToInt == 0 && i == 0 && i2 == 0)) {
            long[] jArr4 = new long[sampleCount];
            int[] iArr6 = new int[sampleCount];
            long[] jArr5 = new long[sampleCount];
            int i16 = i2;
            iArr = new int[sampleCount];
            int i17 = readUnsignedIntToInt;
            ParsableByteArray parsableByteArray6 = parsableByteArray3;
            int i18 = readUnsignedIntToInt3;
            long j2 = 0;
            long j3 = 0;
            int i19 = 0;
            int i20 = 0;
            int i21 = 0;
            int i22 = 0;
            int i23 = i16;
            int i24 = i;
            int i25 = readUnsignedIntToInt2;
            int i26 = i15;
            int i27 = 0;
            while (true) {
                if (i20 >= sampleCount) {
                    i7 = i17;
                    i8 = sampleCount;
                    i9 = i27;
                    i10 = i22;
                    break;
                }
                long j4 = j3;
                boolean z4 = true;
                while (i27 == 0) {
                    z4 = chunkIterator.moveNext();
                    if (!z4) {
                        break;
                    }
                    int i28 = i17;
                    int i29 = sampleCount;
                    j4 = chunkIterator.offset;
                    i27 = chunkIterator.numSamples;
                    sampleCount = i29;
                    i17 = i28;
                }
                i7 = i17;
                int i30 = sampleCount;
                if (!z4) {
                    Log.w(TAG, "Unexpected end of chunk data");
                    jArr4 = Arrays.copyOf(jArr4, i20);
                    iArr6 = Arrays.copyOf(iArr6, i20);
                    jArr5 = Arrays.copyOf(jArr5, i20);
                    iArr = Arrays.copyOf(iArr, i20);
                    i8 = i20;
                    i9 = i27;
                    i10 = i22;
                    break;
                }
                if (parsableByteArray5 != null) {
                    int i31 = i24;
                    while (i21 == 0 && i31 > 0) {
                        i21 = parsableByteArray5.readUnsignedIntToInt();
                        i22 = parsableByteArray5.readInt();
                        i31--;
                    }
                    i21--;
                    i13 = i31;
                    i14 = i22;
                } else {
                    i13 = i24;
                    i14 = i22;
                }
                jArr4[i20] = j4;
                iArr6[i20] = sampleSizeBox.readNextSampleSize();
                i24 = i13;
                if (iArr6[i20] > i19) {
                    i19 = iArr6[i20];
                    sampleSizeBox2 = sampleSizeBox;
                } else {
                    sampleSizeBox2 = sampleSizeBox;
                }
                jArr5[i20] = j2 + ((long) i14);
                iArr[i20] = parsableByteArray4 == null ? 1 : 0;
                if (i20 == i26) {
                    iArr[i20] = 1;
                    i23--;
                    if (i23 > 0) {
                        i26 = parsableByteArray4.readUnsignedIntToInt() - 1;
                    }
                }
                j2 += (long) i18;
                i25--;
                if (i25 == 0 && i7 > 0) {
                    i25 = parsableByteArray6.readUnsignedIntToInt();
                    i7--;
                    i18 = parsableByteArray6.readInt();
                }
                i27--;
                i20++;
                i22 = i14;
                i17 = i7;
                sampleSizeBox = sampleSizeBox2;
                sampleCount = i30;
                j3 = j4 + ((long) iArr6[i20]);
            }
            j = j2 + ((long) i10);
            int i32 = i24;
            while (true) {
                if (i32 <= 0) {
                    z3 = true;
                    break;
                } else if (parsableByteArray5.readUnsignedIntToInt() != 0) {
                    z3 = false;
                    break;
                } else {
                    parsableByteArray5.readInt();
                    i32--;
                }
            }
            if (i23 == 0 && i25 == 0 && i9 == 0 && i7 == 0) {
                i12 = i21;
                if (i12 == 0 && z3) {
                    i11 = i19;
                    track2 = track;
                    jArr2 = jArr4;
                    jArr = jArr5;
                    i3 = i11;
                    iArr2 = iArr6;
                    i4 = i8;
                }
            } else {
                i12 = i21;
            }
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Inconsistent stbl box for track ");
            i11 = i19;
            track2 = track;
            sb.append(track2.id);
            sb.append(": remainingSynchronizationSamples ");
            sb.append(i23);
            sb.append(", remainingSamplesAtTimestampDelta ");
            sb.append(i25);
            sb.append(", remainingSamplesInChunk ");
            sb.append(i9);
            sb.append(", remainingTimestampDeltaChanges ");
            sb.append(i7);
            sb.append(", remainingSamplesAtTimestampOffset ");
            sb.append(i12);
            sb.append(!z3 ? ", ctts invalid" : "");
            Log.w(str, sb.toString());
            jArr2 = jArr4;
            jArr = jArr5;
            i3 = i11;
            iArr2 = iArr6;
            i4 = i8;
        } else {
            int i33 = sampleCount;
            long[] jArr6 = new long[chunkIterator.length];
            int[] iArr7 = new int[chunkIterator.length];
            while (chunkIterator.moveNext()) {
                jArr6[chunkIterator.index] = chunkIterator.offset;
                iArr7[chunkIterator.index] = chunkIterator.numSamples;
            }
            Results rechunk = FixedSampleSizeRechunker.rechunk(Util.getPcmFrameSize(track2.format.pcmEncoding, track2.format.channelCount), jArr6, iArr7, (long) readUnsignedIntToInt3);
            jArr2 = rechunk.offsets;
            iArr2 = rechunk.sizes;
            i3 = rechunk.maximumSize;
            jArr = rechunk.timestamps;
            iArr = rechunk.flags;
            j = rechunk.duration;
            i4 = i33;
        }
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(j, 1000000, track2.timescale);
        if (track2.editListDurations == null || gaplessInfoHolder.hasGaplessInfo()) {
            long[] jArr7 = jArr2;
            int[] iArr8 = iArr2;
            int i34 = i3;
            int[] iArr9 = iArr;
            long[] jArr8 = jArr;
            Util.scaleLargeTimestampsInPlace(jArr8, 1000000, track2.timescale);
            TrackSampleTable trackSampleTable2 = new TrackSampleTable(track, jArr7, iArr8, i34, jArr8, iArr9, scaleLargeTimestamp);
            return trackSampleTable2;
        }
        if (track2.editListDurations.length == 1 && track2.type == 1 && jArr.length >= 2) {
            long j5 = track2.editListMediaTimes[0];
            long scaleLargeTimestamp2 = j5 + Util.scaleLargeTimestamp(track2.editListDurations[0], track2.timescale, track2.movieTimescale);
            iArr3 = iArr2;
            i5 = i3;
            if (canApplyEditWithGaplessInfo(jArr, j, j5, scaleLargeTimestamp2)) {
                long j6 = j - scaleLargeTimestamp2;
                long scaleLargeTimestamp3 = Util.scaleLargeTimestamp(j5 - jArr[0], (long) track2.format.sampleRate, track2.timescale);
                long scaleLargeTimestamp4 = Util.scaleLargeTimestamp(j6, (long) track2.format.sampleRate, track2.timescale);
                if (!(scaleLargeTimestamp3 == 0 && scaleLargeTimestamp4 == 0) && scaleLargeTimestamp3 <= 2147483647L && scaleLargeTimestamp4 <= 2147483647L) {
                    int i35 = (int) scaleLargeTimestamp3;
                    GaplessInfoHolder gaplessInfoHolder3 = gaplessInfoHolder;
                    gaplessInfoHolder3.encoderDelay = i35;
                    gaplessInfoHolder3.encoderPadding = (int) scaleLargeTimestamp4;
                    Util.scaleLargeTimestampsInPlace(jArr, 1000000, track2.timescale);
                    TrackSampleTable trackSampleTable3 = new TrackSampleTable(track, jArr2, iArr3, i5, jArr, iArr, Util.scaleLargeTimestamp(track2.editListDurations[0], 1000000, track2.movieTimescale));
                    return trackSampleTable3;
                }
            }
        } else {
            iArr3 = iArr2;
            i5 = i3;
        }
        if (track2.editListDurations.length == 1 && track2.editListDurations[0] == 0) {
            long j7 = track2.editListMediaTimes[0];
            for (int i36 = 0; i36 < jArr.length; i36++) {
                jArr[i36] = Util.scaleLargeTimestamp(jArr[i36] - j7, 1000000, track2.timescale);
            }
            TrackSampleTable trackSampleTable4 = new TrackSampleTable(track, jArr2, iArr3, i5, jArr, iArr, Util.scaleLargeTimestamp(j - j7, 1000000, track2.timescale));
            return trackSampleTable4;
        }
        boolean z5 = track2.type == 1;
        int[] iArr10 = new int[track2.editListDurations.length];
        int[] iArr11 = new int[track2.editListDurations.length];
        int i37 = 0;
        boolean z6 = false;
        int i38 = 0;
        int i39 = 0;
        while (i37 < track2.editListDurations.length) {
            long j8 = track2.editListMediaTimes[i37];
            if (j8 != -1) {
                int i40 = i39;
                boolean z7 = z6;
                int i41 = i38;
                long scaleLargeTimestamp5 = Util.scaleLargeTimestamp(track2.editListDurations[i37], track2.timescale, track2.movieTimescale);
                iArr10[i37] = Util.binarySearchCeil(jArr, j8, true, true);
                iArr11[i37] = Util.binarySearchCeil(jArr, j8 + scaleLargeTimestamp5, z5, false);
                while (iArr10[i37] < iArr11[i37] && (iArr[iArr10[i37]] & 1) == 0) {
                    iArr10[i37] = iArr10[i37] + 1;
                }
                i38 = i41 + (iArr11[i37] - iArr10[i37]);
                z2 = z7 | (i40 != iArr10[i37]);
                i6 = iArr11[i37];
            } else {
                int i42 = i38;
                i6 = i39;
                z2 = z6;
            }
            i37++;
            z6 = z2;
            i39 = i6;
        }
        boolean z8 = z6;
        int i43 = 0;
        boolean z9 = true;
        if (i38 == i4) {
            z9 = false;
        }
        boolean z10 = z8 | z9;
        long[] jArr9 = z10 ? new long[i38] : jArr2;
        int[] iArr12 = z10 ? new int[i38] : iArr3;
        int i44 = z10 ? 0 : i5;
        int[] iArr13 = z10 ? new int[i38] : iArr;
        long[] jArr10 = new long[i38];
        int i45 = i44;
        long j9 = 0;
        int i46 = 0;
        while (i43 < track2.editListDurations.length) {
            long j10 = track2.editListMediaTimes[i43];
            int i47 = iArr10[i43];
            int[] iArr14 = iArr10;
            int i48 = iArr11[i43];
            if (z10) {
                iArr4 = iArr11;
                int i49 = i48 - i47;
                System.arraycopy(jArr2, i47, jArr9, i46, i49);
                jArr3 = jArr2;
                iArr5 = iArr3;
                System.arraycopy(iArr5, i47, iArr12, i46, i49);
                System.arraycopy(iArr, i47, iArr13, i46, i49);
            } else {
                jArr3 = jArr2;
                iArr4 = iArr11;
                iArr5 = iArr3;
            }
            int i50 = i45;
            while (i47 < i48) {
                long[] jArr11 = jArr9;
                int i51 = i48;
                long[] jArr12 = jArr;
                int[] iArr15 = iArr;
                jArr10[i46] = Util.scaleLargeTimestamp(j9, 1000000, track2.movieTimescale) + Util.scaleLargeTimestamp(jArr[i47] - j10, 1000000, track2.timescale);
                if (z10 && iArr12[i46] > i50) {
                    i50 = iArr5[i47];
                }
                i46++;
                i47++;
                i48 = i51;
                jArr9 = jArr11;
                jArr = jArr12;
                iArr = iArr15;
            }
            int[] iArr16 = iArr;
            j9 += track2.editListDurations[i43];
            i43++;
            i45 = i50;
            jArr9 = jArr9;
            jArr = jArr;
            iArr10 = iArr14;
            iArr11 = iArr4;
            iArr3 = iArr5;
            jArr2 = jArr3;
        }
        TrackSampleTable trackSampleTable5 = new TrackSampleTable(track, jArr9, iArr12, i45, jArr10, iArr13, Util.scaleLargeTimestamp(j9, 1000000, track2.movieTimescale));
        return trackSampleTable5;
    }

    @Nullable
    public static Metadata parseUdta(LeafAtom leafAtom, boolean z) {
        if (z) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtom.data;
        parsableByteArray.setPosition(8);
        while (parsableByteArray.bytesLeft() >= 8) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_meta) {
                parsableByteArray.setPosition(position);
                return parseUdtaMeta(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    @Nullable
    public static Metadata parseMdtaFromMeta(ContainerAtom containerAtom) {
        LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_hdlr);
        LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_keys);
        LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_ilst);
        Metadata metadata = null;
        if (leafAtomOfType == null || leafAtomOfType2 == null || leafAtomOfType3 == null || parseHdlr(leafAtomOfType.data) != TYPE_mdta) {
            return null;
        }
        ParsableByteArray parsableByteArray = leafAtomOfType2.data;
        parsableByteArray.setPosition(12);
        int readInt = parsableByteArray.readInt();
        String[] strArr = new String[readInt];
        for (int i = 0; i < readInt; i++) {
            int readInt2 = parsableByteArray.readInt();
            parsableByteArray.skipBytes(4);
            strArr[i] = parsableByteArray.readString(readInt2 - 8);
        }
        ParsableByteArray parsableByteArray2 = leafAtomOfType3.data;
        parsableByteArray2.setPosition(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray2.bytesLeft() > 8) {
            int position = parsableByteArray2.getPosition();
            int readInt3 = parsableByteArray2.readInt();
            int readInt4 = parsableByteArray2.readInt() - 1;
            if (readInt4 < 0 || readInt4 >= strArr.length) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Skipped metadata with unknown key index: ");
                sb.append(readInt4);
                Log.w(str, sb.toString());
            } else {
                MdtaMetadataEntry parseMdtaMetadataEntryFromIlst = MetadataUtil.parseMdtaMetadataEntryFromIlst(parsableByteArray2, position + readInt3, strArr[readInt4]);
                if (parseMdtaMetadataEntryFromIlst != null) {
                    arrayList.add(parseMdtaMetadataEntryFromIlst);
                }
            }
            parsableByteArray2.setPosition(position + readInt3);
        }
        if (!arrayList.isEmpty()) {
            metadata = new Metadata((List<? extends Entry>) arrayList);
        }
        return metadata;
    }

    @Nullable
    private static Metadata parseUdtaMeta(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(12);
        while (parsableByteArray.getPosition() < i) {
            int position = parsableByteArray.getPosition();
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_ilst) {
                parsableByteArray.setPosition(position);
                return parseIlst(parsableByteArray, position + readInt);
            }
            parsableByteArray.setPosition(position + readInt);
        }
        return null;
    }

    @Nullable
    private static Metadata parseIlst(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.skipBytes(8);
        ArrayList arrayList = new ArrayList();
        while (parsableByteArray.getPosition() < i) {
            Entry parseIlstElement = MetadataUtil.parseIlstElement(parsableByteArray);
            if (parseIlstElement != null) {
                arrayList.add(parseIlstElement);
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return new Metadata((List<? extends Entry>) arrayList);
    }

    private static long parseMvhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        if (Atom.parseFullAtomVersion(parsableByteArray.readInt()) != 0) {
            i = 16;
        }
        parsableByteArray.skipBytes(i);
        return parsableByteArray.readUnsignedInt();
    }

    private static TkhdData parseTkhd(ParsableByteArray parsableByteArray) {
        boolean z;
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        int readInt = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int position = parsableByteArray.getPosition();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= i) {
                z = true;
                break;
            } else if (parsableByteArray.data[position + i3] != -1) {
                z = false;
                break;
            } else {
                i3++;
            }
        }
        long j = -9223372036854775807L;
        if (z) {
            parsableByteArray.skipBytes(i);
        } else {
            long readUnsignedInt = parseFullAtomVersion == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
            if (readUnsignedInt != 0) {
                j = readUnsignedInt;
            }
        }
        parsableByteArray.skipBytes(16);
        int readInt2 = parsableByteArray.readInt();
        int readInt3 = parsableByteArray.readInt();
        parsableByteArray.skipBytes(4);
        int readInt4 = parsableByteArray.readInt();
        int readInt5 = parsableByteArray.readInt();
        if (readInt2 == 0 && readInt3 == 65536 && readInt4 == -65536 && readInt5 == 0) {
            i2 = 90;
        } else if (readInt2 == 0 && readInt3 == -65536 && readInt4 == 65536 && readInt5 == 0) {
            i2 = 270;
        } else if (readInt2 == -65536 && readInt3 == 0 && readInt4 == 0 && readInt5 == -65536) {
            i2 = 180;
        }
        return new TkhdData(readInt, j, i2);
    }

    private static int parseHdlr(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(16);
        return parsableByteArray.readInt();
    }

    private static int getTrackTypeForHdlr(int i) {
        if (i == TYPE_soun) {
            return 1;
        }
        if (i == TYPE_vide) {
            return 2;
        }
        if (i == TYPE_text || i == TYPE_sbtl || i == TYPE_subt || i == TYPE_clcp) {
            return 3;
        }
        return i == TYPE_meta ? 4 : -1;
    }

    private static Pair<Long, String> parseMdhd(ParsableByteArray parsableByteArray) {
        int i = 8;
        parsableByteArray.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray.skipBytes(parseFullAtomVersion == 0 ? 8 : 16);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            i = 4;
        }
        parsableByteArray.skipBytes(i);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        sb.append((char) (((readUnsignedShort >> 10) & 31) + 96));
        sb.append((char) (((readUnsignedShort >> 5) & 31) + 96));
        sb.append((char) ((readUnsignedShort & 31) + 96));
        return Pair.create(Long.valueOf(readUnsignedInt), sb.toString());
    }

    private static StsdData parseStsd(ParsableByteArray parsableByteArray, int i, int i2, String str, DrmInitData drmInitData, boolean z) throws ParserException {
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(12);
        int readInt = parsableByteArray.readInt();
        StsdData stsdData = new StsdData(readInt);
        for (int i3 = 0; i3 < readInt; i3++) {
            int position = parsableByteArray.getPosition();
            int readInt2 = parsableByteArray.readInt();
            Assertions.checkArgument(readInt2 > 0, "childAtomSize should be positive");
            int readInt3 = parsableByteArray.readInt();
            if (readInt3 == Atom.TYPE_avc1 || readInt3 == Atom.TYPE_avc3 || readInt3 == Atom.TYPE_encv || readInt3 == Atom.TYPE_mp4v || readInt3 == Atom.TYPE_hvc1 || readInt3 == Atom.TYPE_hev1 || readInt3 == Atom.TYPE_s263 || readInt3 == Atom.TYPE_vp08 || readInt3 == Atom.TYPE_vp09) {
                parseVideoSampleEntry(parsableByteArray, readInt3, position, readInt2, i, i2, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_mp4a || readInt3 == Atom.TYPE_enca || readInt3 == Atom.TYPE_ac_3 || readInt3 == Atom.TYPE_ec_3 || readInt3 == Atom.TYPE_dtsc || readInt3 == Atom.TYPE_dtse || readInt3 == Atom.TYPE_dtsh || readInt3 == Atom.TYPE_dtsl || readInt3 == Atom.TYPE_samr || readInt3 == Atom.TYPE_sawb || readInt3 == Atom.TYPE_lpcm || readInt3 == Atom.TYPE_sowt || readInt3 == Atom.TYPE__mp3 || readInt3 == Atom.TYPE_alac || readInt3 == Atom.TYPE_alaw || readInt3 == Atom.TYPE_ulaw || readInt3 == Atom.TYPE_Opus || readInt3 == Atom.TYPE_fLaC) {
                parseAudioSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, z, drmInitData, stsdData, i3);
            } else if (readInt3 == Atom.TYPE_TTML || readInt3 == Atom.TYPE_tx3g || readInt3 == Atom.TYPE_wvtt || readInt3 == Atom.TYPE_stpp || readInt3 == Atom.TYPE_c608) {
                parseTextSampleEntry(parsableByteArray, readInt3, position, readInt2, i, str, stsdData);
            } else if (readInt3 == Atom.TYPE_camm) {
                stsdData.format = Format.createSampleFormat(Integer.toString(i), MimeTypes.APPLICATION_CAMERA_MOTION, null, -1, null);
            }
            parsableByteArray2.setPosition(position + readInt2);
        }
        return stsdData;
    }

    private static void parseTextSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, StsdData stsdData) throws ParserException {
        List list;
        long j;
        String str2;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i5 = i;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i2 + 8 + 8);
        if (i5 == Atom.TYPE_TTML) {
            str2 = MimeTypes.APPLICATION_TTML;
            list = null;
            j = Long.MAX_VALUE;
        } else if (i5 == Atom.TYPE_tx3g) {
            String str3 = MimeTypes.APPLICATION_TX3G;
            int i6 = (i3 - 8) - 8;
            byte[] bArr = new byte[i6];
            parsableByteArray2.readBytes(bArr, 0, i6);
            str2 = str3;
            list = Collections.singletonList(bArr);
            j = Long.MAX_VALUE;
        } else if (i5 == Atom.TYPE_wvtt) {
            str2 = MimeTypes.APPLICATION_MP4VTT;
            list = null;
            j = Long.MAX_VALUE;
        } else if (i5 == Atom.TYPE_stpp) {
            str2 = MimeTypes.APPLICATION_TTML;
            list = null;
            j = 0;
        } else if (i5 == Atom.TYPE_c608) {
            String str4 = MimeTypes.APPLICATION_MP4CEA608;
            stsdData2.requiredSampleTransformation = 1;
            str2 = str4;
            list = null;
            j = Long.MAX_VALUE;
        } else {
            throw new IllegalStateException();
        }
        stsdData2.format = Format.createTextSampleFormat(Integer.toString(i4), str2, null, -1, 0, str, -1, null, j, list);
    }

    /* JADX WARNING: Removed duplicated region for block: B:75:0x014c A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x014d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray r21, int r22, int r23, int r24, int r25, int r26, com.google.android.exoplayer2.drm.DrmInitData r27, com.google.android.exoplayer2.extractor.mp4.AtomParsers.StsdData r28, int r29) throws com.google.android.exoplayer2.ParserException {
        /*
            r0 = r21
            r1 = r23
            r2 = r24
            r3 = r27
            r4 = r28
            int r5 = r1 + 8
            int r5 = r5 + 8
            r0.setPosition(r5)
            r5 = 16
            r0.skipBytes(r5)
            int r11 = r21.readUnsignedShort()
            int r12 = r21.readUnsignedShort()
            r5 = 50
            r0.skipBytes(r5)
            int r5 = r21.getPosition()
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_encv
            r7 = 0
            r8 = r22
            if (r8 != r6) goto L_0x0058
            android.util.Pair r6 = parseSampleEntryEncryptionData(r0, r1, r2)
            if (r6 == 0) goto L_0x0052
            java.lang.Object r8 = r6.first
            java.lang.Integer r8 = (java.lang.Integer) r8
            int r8 = r8.intValue()
            if (r3 != 0) goto L_0x0040
            r3 = r7
            goto L_0x004a
        L_0x0040:
            java.lang.Object r9 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r9 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r9
            java.lang.String r9 = r9.schemeType
            com.google.android.exoplayer2.drm.DrmInitData r3 = r3.copyWithSchemeType(r9)
        L_0x004a:
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox[] r9 = r4.trackEncryptionBoxes
            java.lang.Object r6 = r6.second
            com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox r6 = (com.google.android.exoplayer2.extractor.mp4.TrackEncryptionBox) r6
            r9[r29] = r6
        L_0x0052:
            r0.setPosition(r5)
            r20 = r3
            goto L_0x005a
        L_0x0058:
            r20 = r3
        L_0x005a:
            r3 = -1
            r9 = 1065353216(0x3f800000, float:1.0)
            r14 = r7
            r17 = r14
            r3 = 0
            r16 = 1065353216(0x3f800000, float:1.0)
            r18 = -1
        L_0x0065:
            int r9 = r5 - r1
            if (r9 >= r2) goto L_0x014a
            r0.setPosition(r5)
            int r9 = r21.getPosition()
            int r10 = r21.readInt()
            if (r10 != 0) goto L_0x007f
            int r13 = r21.getPosition()
            int r13 = r13 - r1
            if (r13 != r2) goto L_0x007f
            goto L_0x014a
        L_0x007f:
            if (r10 <= 0) goto L_0x0083
            r15 = 1
            goto L_0x0084
        L_0x0083:
            r15 = 0
        L_0x0084:
            java.lang.String r6 = "childAtomSize should be positive"
            com.google.android.exoplayer2.util.Assertions.checkArgument(r15, r6)
            int r6 = r21.readInt()
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_avcC
            r13 = 3
            if (r6 != r15) goto L_0x00b3
            if (r7 != 0) goto L_0x0096
            r6 = 1
            goto L_0x0097
        L_0x0096:
            r6 = 0
        L_0x0097:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            java.lang.String r7 = "video/avc"
            int r9 = r9 + 8
            r0.setPosition(r9)
            com.google.android.exoplayer2.video.AvcConfig r6 = com.google.android.exoplayer2.video.AvcConfig.parse(r21)
            java.util.List<byte[]> r14 = r6.initializationData
            int r9 = r6.nalUnitLengthFieldLength
            r4.nalUnitLengthFieldLength = r9
            if (r3 != 0) goto L_0x0147
            float r6 = r6.pixelWidthAspectRatio
            r16 = r6
            goto L_0x0147
        L_0x00b3:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_hvcC
            if (r6 != r15) goto L_0x00d2
            if (r7 != 0) goto L_0x00bb
            r6 = 1
            goto L_0x00bc
        L_0x00bb:
            r6 = 0
        L_0x00bc:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            java.lang.String r7 = "video/hevc"
            int r9 = r9 + 8
            r0.setPosition(r9)
            com.google.android.exoplayer2.video.HevcConfig r6 = com.google.android.exoplayer2.video.HevcConfig.parse(r21)
            java.util.List<byte[]> r14 = r6.initializationData
            int r6 = r6.nalUnitLengthFieldLength
            r4.nalUnitLengthFieldLength = r6
            goto L_0x0147
        L_0x00d2:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vpcC
            if (r6 != r15) goto L_0x00e9
            if (r7 != 0) goto L_0x00da
            r6 = 1
            goto L_0x00db
        L_0x00da:
            r6 = 0
        L_0x00db:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            int r6 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_vp08
            if (r8 != r6) goto L_0x00e5
            java.lang.String r6 = "video/x-vnd.on2.vp8"
            goto L_0x00e7
        L_0x00e5:
            java.lang.String r6 = "video/x-vnd.on2.vp9"
        L_0x00e7:
            r7 = r6
            goto L_0x0147
        L_0x00e9:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_d263
            if (r6 != r15) goto L_0x00f8
            if (r7 != 0) goto L_0x00f1
            r6 = 1
            goto L_0x00f2
        L_0x00f1:
            r6 = 0
        L_0x00f2:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            java.lang.String r7 = "video/3gpp"
            goto L_0x0147
        L_0x00f8:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_esds
            if (r6 != r15) goto L_0x0113
            if (r7 != 0) goto L_0x0100
            r6 = 1
            goto L_0x0101
        L_0x0100:
            r6 = 0
        L_0x0101:
            com.google.android.exoplayer2.util.Assertions.checkState(r6)
            android.util.Pair r6 = parseEsdsFromParent(r0, r9)
            java.lang.Object r7 = r6.first
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r6 = r6.second
            java.util.List r14 = java.util.Collections.singletonList(r6)
            goto L_0x0147
        L_0x0113:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_pasp
            if (r6 != r15) goto L_0x011d
            float r16 = parsePaspFromParent(r0, r9)
            r3 = 1
            goto L_0x0147
        L_0x011d:
            int r15 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_sv3d
            if (r6 != r15) goto L_0x0126
            byte[] r17 = parseProjFromParent(r0, r9, r10)
            goto L_0x0147
        L_0x0126:
            int r9 = com.google.android.exoplayer2.extractor.mp4.Atom.TYPE_st3d
            if (r6 != r9) goto L_0x0147
            int r6 = r21.readUnsignedByte()
            r0.skipBytes(r13)
            if (r6 != 0) goto L_0x0147
            int r6 = r21.readUnsignedByte()
            switch(r6) {
                case 0: goto L_0x0145;
                case 1: goto L_0x0142;
                case 2: goto L_0x013e;
                case 3: goto L_0x013b;
                default: goto L_0x013a;
            }
        L_0x013a:
            goto L_0x0147
        L_0x013b:
            r18 = 3
            goto L_0x0147
        L_0x013e:
            r6 = 2
            r18 = 2
            goto L_0x0147
        L_0x0142:
            r18 = 1
            goto L_0x0147
        L_0x0145:
            r18 = 0
        L_0x0147:
            int r5 = r5 + r10
            goto L_0x0065
        L_0x014a:
            if (r7 != 0) goto L_0x014d
            return
        L_0x014d:
            java.lang.String r6 = java.lang.Integer.toString(r25)
            r8 = 0
            r9 = -1
            r10 = -1
            r13 = -1082130432(0xffffffffbf800000, float:-1.0)
            r19 = 0
            r15 = r26
            com.google.android.exoplayer2.Format r0 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20)
            r4.format = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mp4.AtomParsers.parseVideoSampleEntry(com.google.android.exoplayer2.util.ParsableByteArray, int, int, int, int, int, com.google.android.exoplayer2.drm.DrmInitData, com.google.android.exoplayer2.extractor.mp4.AtomParsers$StsdData, int):void");
    }

    private static Pair<long[], long[]> parseEdts(ContainerAtom containerAtom) {
        if (containerAtom != null) {
            LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_elst);
            if (leafAtomOfType != null) {
                ParsableByteArray parsableByteArray = leafAtomOfType.data;
                parsableByteArray.setPosition(8);
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                long[] jArr = new long[readUnsignedIntToInt];
                long[] jArr2 = new long[readUnsignedIntToInt];
                int i = 0;
                while (i < readUnsignedIntToInt) {
                    jArr[i] = parseFullAtomVersion == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
                    jArr2[i] = parseFullAtomVersion == 1 ? parsableByteArray.readLong() : (long) parsableByteArray.readInt();
                    if (parsableByteArray.readShort() == 1) {
                        parsableByteArray.skipBytes(2);
                        i++;
                    } else {
                        throw new IllegalArgumentException("Unsupported media rate.");
                    }
                }
                return Pair.create(jArr, jArr2);
            }
        }
        return Pair.create(null, null);
    }

    private static float parsePaspFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8);
        return ((float) parsableByteArray.readUnsignedIntToInt()) / ((float) parsableByteArray.readUnsignedIntToInt());
    }

    private static void parseAudioSampleEntry(ParsableByteArray parsableByteArray, int i, int i2, int i3, int i4, String str, boolean z, DrmInitData drmInitData, StsdData stsdData, int i5) throws ParserException {
        int i6;
        int i7;
        int i8;
        DrmInitData drmInitData2;
        DrmInitData drmInitData3;
        int i9;
        int i10;
        int i11;
        String str2;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        int i12 = i2;
        int i13 = i3;
        String str3 = str;
        DrmInitData drmInitData4 = drmInitData;
        StsdData stsdData2 = stsdData;
        parsableByteArray2.setPosition(i12 + 8 + 8);
        if (z) {
            i6 = parsableByteArray.readUnsignedShort();
            parsableByteArray2.skipBytes(6);
        } else {
            parsableByteArray2.skipBytes(8);
            i6 = 0;
        }
        if (i6 == 0 || i6 == 1) {
            i7 = parsableByteArray.readUnsignedShort();
            parsableByteArray2.skipBytes(6);
            i8 = parsableByteArray.readUnsignedFixedPoint1616();
            if (i6 == 1) {
                parsableByteArray2.skipBytes(16);
            }
        } else if (i6 == 2) {
            parsableByteArray2.skipBytes(16);
            int round = (int) Math.round(parsableByteArray.readDouble());
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            parsableByteArray2.skipBytes(20);
            i7 = readUnsignedIntToInt;
            i8 = round;
        } else {
            return;
        }
        int position = parsableByteArray.getPosition();
        List list = null;
        int i14 = i;
        if (i14 == Atom.TYPE_enca) {
            Pair parseSampleEntryEncryptionData = parseSampleEntryEncryptionData(parsableByteArray2, i12, i13);
            if (parseSampleEntryEncryptionData != null) {
                i14 = ((Integer) parseSampleEntryEncryptionData.first).intValue();
                if (drmInitData4 == null) {
                    drmInitData4 = null;
                } else {
                    drmInitData4 = drmInitData4.copyWithSchemeType(((TrackEncryptionBox) parseSampleEntryEncryptionData.second).schemeType);
                }
                stsdData2.trackEncryptionBoxes[i5] = (TrackEncryptionBox) parseSampleEntryEncryptionData.second;
            }
            parsableByteArray2.setPosition(position);
            drmInitData2 = drmInitData4;
        } else {
            drmInitData2 = drmInitData4;
        }
        String str4 = i14 == Atom.TYPE_ac_3 ? MimeTypes.AUDIO_AC3 : i14 == Atom.TYPE_ec_3 ? MimeTypes.AUDIO_E_AC3 : i14 == Atom.TYPE_dtsc ? MimeTypes.AUDIO_DTS : (i14 == Atom.TYPE_dtsh || i14 == Atom.TYPE_dtsl) ? MimeTypes.AUDIO_DTS_HD : i14 == Atom.TYPE_dtse ? MimeTypes.AUDIO_DTS_EXPRESS : i14 == Atom.TYPE_samr ? MimeTypes.AUDIO_AMR_NB : i14 == Atom.TYPE_sawb ? MimeTypes.AUDIO_AMR_WB : (i14 == Atom.TYPE_lpcm || i14 == Atom.TYPE_sowt) ? MimeTypes.AUDIO_RAW : i14 == Atom.TYPE__mp3 ? MimeTypes.AUDIO_MPEG : i14 == Atom.TYPE_alac ? MimeTypes.AUDIO_ALAC : i14 == Atom.TYPE_alaw ? MimeTypes.AUDIO_ALAW : i14 == Atom.TYPE_ulaw ? MimeTypes.AUDIO_MLAW : i14 == Atom.TYPE_Opus ? MimeTypes.AUDIO_OPUS : i14 == Atom.TYPE_fLaC ? MimeTypes.AUDIO_FLAC : null;
        String str5 = str4;
        int i15 = i8;
        int i16 = position;
        int i17 = i7;
        byte[] bArr = null;
        while (i16 - i12 < i13) {
            parsableByteArray2.setPosition(i16);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_esds || (z && readInt2 == Atom.TYPE_wave)) {
                i10 = readInt;
                String str6 = str5;
                i9 = i16;
                drmInitData3 = drmInitData2;
                if (readInt2 == Atom.TYPE_esds) {
                    i11 = i9;
                } else {
                    i11 = findEsdsPosition(parsableByteArray2, i9, i10);
                }
                if (i11 != -1) {
                    Pair parseEsdsFromParent = parseEsdsFromParent(parsableByteArray2, i11);
                    str5 = (String) parseEsdsFromParent.first;
                    bArr = (byte[]) parseEsdsFromParent.second;
                    if (MimeTypes.AUDIO_AAC.equals(str5)) {
                        Pair parseAacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(bArr);
                        i15 = ((Integer) parseAacAudioSpecificConfig.first).intValue();
                        i17 = ((Integer) parseAacAudioSpecificConfig.second).intValue();
                    }
                } else {
                    str5 = str6;
                }
            } else {
                if (readInt2 == Atom.TYPE_dac3) {
                    parsableByteArray2.setPosition(i16 + 8);
                    stsdData2.format = Ac3Util.parseAc3AnnexFFormat(parsableByteArray2, Integer.toString(i4), str3, drmInitData2);
                    i10 = readInt;
                    str2 = str5;
                    i9 = i16;
                    drmInitData3 = drmInitData2;
                } else if (readInt2 == Atom.TYPE_dec3) {
                    parsableByteArray2.setPosition(i16 + 8);
                    stsdData2.format = Ac3Util.parseEAc3AnnexFFormat(parsableByteArray2, Integer.toString(i4), str3, drmInitData2);
                    i10 = readInt;
                    str2 = str5;
                    i9 = i16;
                    drmInitData3 = drmInitData2;
                } else if (readInt2 == Atom.TYPE_ddts) {
                    int i18 = readInt;
                    str2 = str5;
                    int i19 = i16;
                    drmInitData3 = drmInitData2;
                    stsdData2.format = Format.createAudioSampleFormat(Integer.toString(i4), str5, null, -1, -1, i17, i15, null, drmInitData3, 0, str);
                    i10 = i18;
                    i9 = i19;
                } else {
                    int i20 = readInt;
                    str2 = str5;
                    int i21 = i16;
                    drmInitData3 = drmInitData2;
                    if (readInt2 == Atom.TYPE_alac) {
                        i10 = i20;
                        byte[] bArr2 = new byte[i10];
                        i9 = i21;
                        parsableByteArray2.setPosition(i9);
                        parsableByteArray2.readBytes(bArr2, 0, i10);
                        bArr = bArr2;
                        str5 = str2;
                    } else {
                        i10 = i20;
                        i9 = i21;
                        if (readInt2 == Atom.TYPE_dOps) {
                            int i22 = i10 - 8;
                            byte[] bArr3 = opusMagic;
                            byte[] bArr4 = new byte[(bArr3.length + i22)];
                            System.arraycopy(bArr3, 0, bArr4, 0, bArr3.length);
                            parsableByteArray2.setPosition(i9 + 8);
                            parsableByteArray2.readBytes(bArr4, opusMagic.length, i22);
                            bArr = bArr4;
                            str5 = str2;
                        } else if (i10 == Atom.TYPE_dfLa) {
                            int i23 = i10 - 12;
                            byte[] bArr5 = new byte[i23];
                            parsableByteArray2.setPosition(i9 + 12);
                            parsableByteArray2.readBytes(bArr5, 0, i23);
                            bArr = bArr5;
                            str5 = str2;
                        }
                    }
                }
                str5 = str2;
            }
            i16 = i9 + i10;
            drmInitData2 = drmInitData3;
            i12 = i2;
        }
        String str7 = str5;
        DrmInitData drmInitData5 = drmInitData2;
        if (stsdData2.format == null) {
            String str8 = str7;
            if (str8 != null) {
                int i24 = MimeTypes.AUDIO_RAW.equals(str8) ? 2 : -1;
                String num = Integer.toString(i4);
                if (bArr != null) {
                    list = Collections.singletonList(bArr);
                }
                stsdData2.format = Format.createAudioSampleFormat(num, str8, null, -1, -1, i17, i15, i24, list, drmInitData5, 0, str);
            }
        }
    }

    private static int findEsdsPosition(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_esds) {
                return position;
            }
            position += readInt;
        }
        return -1;
    }

    private static Pair<String, byte[]> parseEsdsFromParent(ParsableByteArray parsableByteArray, int i) {
        parsableByteArray.setPosition(i + 8 + 4);
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        parsableByteArray.skipBytes(2);
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            parsableByteArray.skipBytes(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            parsableByteArray.skipBytes(2);
        }
        parsableByteArray.skipBytes(1);
        parseExpandableClassSize(parsableByteArray);
        String mimeTypeFromMp4ObjectType = MimeTypes.getMimeTypeFromMp4ObjectType(parsableByteArray.readUnsignedByte());
        if (MimeTypes.AUDIO_MPEG.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS.equals(mimeTypeFromMp4ObjectType) || MimeTypes.AUDIO_DTS_HD.equals(mimeTypeFromMp4ObjectType)) {
            return Pair.create(mimeTypeFromMp4ObjectType, null);
        }
        parsableByteArray.skipBytes(12);
        parsableByteArray.skipBytes(1);
        int parseExpandableClassSize = parseExpandableClassSize(parsableByteArray);
        byte[] bArr = new byte[parseExpandableClassSize];
        parsableByteArray.readBytes(bArr, 0, parseExpandableClassSize);
        return Pair.create(mimeTypeFromMp4ObjectType, bArr);
    }

    private static Pair<Integer, TrackEncryptionBox> parseSampleEntryEncryptionData(ParsableByteArray parsableByteArray, int i, int i2) {
        int position = parsableByteArray.getPosition();
        while (position - i < i2) {
            parsableByteArray.setPosition(position);
            int readInt = parsableByteArray.readInt();
            Assertions.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (parsableByteArray.readInt() == Atom.TYPE_sinf) {
                Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent = parseCommonEncryptionSinfFromParent(parsableByteArray, position, readInt);
                if (parseCommonEncryptionSinfFromParent != null) {
                    return parseCommonEncryptionSinfFromParent;
                }
            }
            position += readInt;
        }
        return null;
    }

    static Pair<Integer, TrackEncryptionBox> parseCommonEncryptionSinfFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        String str = null;
        Object obj = null;
        int i4 = -1;
        int i5 = 0;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            int readInt2 = parsableByteArray.readInt();
            if (readInt2 == Atom.TYPE_frma) {
                obj = Integer.valueOf(parsableByteArray.readInt());
            } else if (readInt2 == Atom.TYPE_schm) {
                parsableByteArray.skipBytes(4);
                str = parsableByteArray.readString(4);
            } else if (readInt2 == Atom.TYPE_schi) {
                i4 = i3;
                i5 = readInt;
            }
            i3 += readInt;
        }
        if (!C.CENC_TYPE_cenc.equals(str) && !C.CENC_TYPE_cbc1.equals(str) && !C.CENC_TYPE_cens.equals(str) && !C.CENC_TYPE_cbcs.equals(str)) {
            return null;
        }
        boolean z = true;
        Assertions.checkArgument(obj != null, "frma atom is mandatory");
        Assertions.checkArgument(i4 != -1, "schi atom is mandatory");
        TrackEncryptionBox parseSchiFromParent = parseSchiFromParent(parsableByteArray, i4, i5, str);
        if (parseSchiFromParent == null) {
            z = false;
        }
        Assertions.checkArgument(z, "tenc atom is mandatory");
        return Pair.create(obj, parseSchiFromParent);
    }

    private static TrackEncryptionBox parseSchiFromParent(ParsableByteArray parsableByteArray, int i, int i2, String str) {
        int i3;
        int i4;
        byte[] bArr;
        int i5 = i + 8;
        while (i5 - i < i2) {
            parsableByteArray.setPosition(i5);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_tenc) {
                int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
                parsableByteArray.skipBytes(1);
                if (parseFullAtomVersion == 0) {
                    parsableByteArray.skipBytes(1);
                    i4 = 0;
                    i3 = 0;
                } else {
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    i3 = readUnsignedByte & 15;
                    i4 = (readUnsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                }
                boolean z = parsableByteArray.readUnsignedByte() == 1;
                int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                byte[] bArr2 = new byte[16];
                parsableByteArray.readBytes(bArr2, 0, bArr2.length);
                if (!z || readUnsignedByte2 != 0) {
                    bArr = null;
                } else {
                    int readUnsignedByte3 = parsableByteArray.readUnsignedByte();
                    byte[] bArr3 = new byte[readUnsignedByte3];
                    parsableByteArray.readBytes(bArr3, 0, readUnsignedByte3);
                    bArr = bArr3;
                }
                TrackEncryptionBox trackEncryptionBox = new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i4, i3, bArr);
                return trackEncryptionBox;
            }
            i5 += readInt;
        }
        return null;
    }

    private static byte[] parseProjFromParent(ParsableByteArray parsableByteArray, int i, int i2) {
        int i3 = i + 8;
        while (i3 - i < i2) {
            parsableByteArray.setPosition(i3);
            int readInt = parsableByteArray.readInt();
            if (parsableByteArray.readInt() == Atom.TYPE_proj) {
                return Arrays.copyOfRange(parsableByteArray.data, i3, readInt + i3);
            }
            i3 += readInt;
        }
        return null;
    }

    private static int parseExpandableClassSize(ParsableByteArray parsableByteArray) {
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int i = readUnsignedByte & Statements.GetOwnedFoodIdsDateDescending;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = parsableByteArray.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & Statements.GetOwnedFoodIdsDateDescending);
        }
        return i;
    }

    private static boolean canApplyEditWithGaplessInfo(long[] jArr, long j, long j2, long j3) {
        int length = jArr.length - 1;
        int constrainValue = Util.constrainValue(3, 0, length);
        int constrainValue2 = Util.constrainValue(jArr.length - 3, 0, length);
        if (jArr[0] > j2 || j2 >= jArr[constrainValue] || jArr[constrainValue2] >= j3 || j3 > j) {
            return false;
        }
        return true;
    }

    private AtomParsers() {
    }
}
