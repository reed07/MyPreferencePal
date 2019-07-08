package com.google.android.exoplayer2.extractor.mp4;

import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.GaplessInfoHolder;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekMap.SeekPoints;
import com.google.android.exoplayer2.extractor.SeekPoint;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;

public final class Mp4Extractor implements Extractor, SeekMap {
    private static final int BRAND_QUICKTIME = Util.getIntegerCodeForString("qt  ");
    public static final ExtractorsFactory FACTORY = $$Lambda$Mp4Extractor$quy71uYOGsneho91FZy1d2UGE1Q.INSTANCE;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 1;
    private static final long MAXIMUM_READ_AHEAD_BYTES_STREAM = 10485760;
    private static final long RELOAD_MINIMUM_SEEK_DISTANCE = 262144;
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_SAMPLE = 2;
    private long[][] accumulatedSampleSizes;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private final ArrayDeque<ContainerAtom> containerAtoms;
    private long durationUs;
    private ExtractorOutput extractorOutput;
    private int firstVideoTrackIndex;
    private final int flags;
    private boolean isQuickTime;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleTrackIndex;
    private Mp4Track[] tracks;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    private static final class Mp4Track {
        public int sampleIndex;
        public final TrackSampleTable sampleTable;
        public final Track track;
        public final TrackOutput trackOutput;

        public Mp4Track(Track track2, TrackSampleTable trackSampleTable, TrackOutput trackOutput2) {
            this.track = track2;
            this.sampleTable = trackSampleTable;
            this.trackOutput = trackOutput2;
        }
    }

    public boolean isSeekable() {
        return true;
    }

    public void release() {
    }

    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new Mp4Extractor()};
    }

    public Mp4Extractor() {
        this(0);
    }

    public Mp4Extractor(int i) {
        this.flags = i;
        this.atomHeader = new ParsableByteArray(16);
        this.containerAtoms = new ArrayDeque<>();
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleTrackIndex = -1;
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return Sniffer.sniffUnfragmented(extractorInput);
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.containerAtoms.clear();
        this.atomHeaderBytesRead = 0;
        this.sampleTrackIndex = -1;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        if (j == 0) {
            enterReadingAtomHeaderState();
        } else if (this.tracks != null) {
            updateSampleIndices(j2);
        }
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        while (true) {
            switch (this.parserState) {
                case 0:
                    if (readAtomHeader(extractorInput)) {
                        break;
                    } else {
                        return -1;
                    }
                case 1:
                    if (!readAtomPayload(extractorInput, positionHolder)) {
                        break;
                    } else {
                        return 1;
                    }
                case 2:
                    return readSample(extractorInput, positionHolder);
                default:
                    throw new IllegalStateException();
            }
        }
    }

    public long getDurationUs() {
        return this.durationUs;
    }

    public SeekPoints getSeekPoints(long j) {
        long j2;
        long j3;
        long j4;
        long j5;
        Mp4Track[] mp4TrackArr = this.tracks;
        if (mp4TrackArr.length == 0) {
            return new SeekPoints(SeekPoint.START);
        }
        int i = this.firstVideoTrackIndex;
        if (i != -1) {
            TrackSampleTable trackSampleTable = mp4TrackArr[i].sampleTable;
            int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j);
            if (synchronizationSampleIndex == -1) {
                return new SeekPoints(SeekPoint.START);
            }
            long j6 = trackSampleTable.timestampsUs[synchronizationSampleIndex];
            j2 = trackSampleTable.offsets[synchronizationSampleIndex];
            if (j6 < j && synchronizationSampleIndex < trackSampleTable.sampleCount - 1) {
                int indexOfLaterOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j);
                if (!(indexOfLaterOrEqualSynchronizationSample == -1 || indexOfLaterOrEqualSynchronizationSample == synchronizationSampleIndex)) {
                    j4 = trackSampleTable.timestampsUs[indexOfLaterOrEqualSynchronizationSample];
                    j5 = trackSampleTable.offsets[indexOfLaterOrEqualSynchronizationSample];
                    j3 = j5;
                    j = j6;
                }
            }
            j5 = -1;
            j4 = -9223372036854775807L;
            j3 = j5;
            j = j6;
        } else {
            j2 = Long.MAX_VALUE;
            j3 = -1;
            j4 = -9223372036854775807L;
        }
        int i2 = 0;
        while (true) {
            Mp4Track[] mp4TrackArr2 = this.tracks;
            if (i2 >= mp4TrackArr2.length) {
                break;
            }
            if (i2 != this.firstVideoTrackIndex) {
                TrackSampleTable trackSampleTable2 = mp4TrackArr2[i2].sampleTable;
                long maybeAdjustSeekOffset = maybeAdjustSeekOffset(trackSampleTable2, j, j2);
                if (j4 != -9223372036854775807L) {
                    j3 = maybeAdjustSeekOffset(trackSampleTable2, j4, j3);
                    j2 = maybeAdjustSeekOffset;
                } else {
                    j2 = maybeAdjustSeekOffset;
                }
            }
            i2++;
        }
        SeekPoint seekPoint = new SeekPoint(j, j2);
        if (j4 == -9223372036854775807L) {
            return new SeekPoints(seekPoint);
        }
        return new SeekPoints(seekPoint, new SeekPoint(j4, j3));
    }

    private void enterReadingAtomHeaderState() {
        this.parserState = 0;
        this.atomHeaderBytesRead = 0;
    }

    private boolean readAtomHeader(ExtractorInput extractorInput) throws IOException, InterruptedException {
        if (this.atomHeaderBytesRead == 0) {
            if (!extractorInput.readFully(this.atomHeader.data, 0, 8, true)) {
                return false;
            }
            this.atomHeaderBytesRead = 8;
            this.atomHeader.setPosition(0);
            this.atomSize = this.atomHeader.readUnsignedInt();
            this.atomType = this.atomHeader.readInt();
        }
        long j = this.atomSize;
        if (j == 1) {
            extractorInput.readFully(this.atomHeader.data, 8, 8);
            this.atomHeaderBytesRead += 8;
            this.atomSize = this.atomHeader.readUnsignedLongToLong();
        } else if (j == 0) {
            long length = extractorInput.getLength();
            if (length == -1 && !this.containerAtoms.isEmpty()) {
                length = ((ContainerAtom) this.containerAtoms.peek()).endPosition;
            }
            if (length != -1) {
                this.atomSize = (length - extractorInput.getPosition()) + ((long) this.atomHeaderBytesRead);
            }
        }
        if (this.atomSize >= ((long) this.atomHeaderBytesRead)) {
            if (shouldParseContainerAtom(this.atomType)) {
                long position = (extractorInput.getPosition() + this.atomSize) - ((long) this.atomHeaderBytesRead);
                this.containerAtoms.push(new ContainerAtom(this.atomType, position));
                if (this.atomSize == ((long) this.atomHeaderBytesRead)) {
                    processAtomEnded(position);
                } else {
                    enterReadingAtomHeaderState();
                }
            } else if (shouldParseLeafAtom(this.atomType)) {
                Assertions.checkState(this.atomHeaderBytesRead == 8);
                Assertions.checkState(this.atomSize <= 2147483647L);
                this.atomData = new ParsableByteArray((int) this.atomSize);
                System.arraycopy(this.atomHeader.data, 0, this.atomData.data, 0, 8);
                this.parserState = 1;
            } else {
                this.atomData = null;
                this.parserState = 1;
            }
            return true;
        }
        throw new ParserException("Atom size less than header length (unsupported).");
    }

    private boolean readAtomPayload(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        boolean z;
        long j = this.atomSize - ((long) this.atomHeaderBytesRead);
        long position = extractorInput.getPosition() + j;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.data, this.atomHeaderBytesRead, (int) j);
            if (this.atomType == Atom.TYPE_ftyp) {
                this.isQuickTime = processFtypAtom(this.atomData);
            } else if (!this.containerAtoms.isEmpty()) {
                ((ContainerAtom) this.containerAtoms.peek()).add(new LeafAtom(this.atomType, this.atomData));
            }
        } else if (j < RELOAD_MINIMUM_SEEK_DISTANCE) {
            extractorInput.skipFully((int) j);
        } else {
            positionHolder.position = extractorInput.getPosition() + j;
            z = true;
            processAtomEnded(position);
            if (z || this.parserState == 2) {
                return false;
            }
            return true;
        }
        z = false;
        processAtomEnded(position);
        if (z) {
        }
        return false;
    }

    private void processAtomEnded(long j) throws ParserException {
        while (!this.containerAtoms.isEmpty() && ((ContainerAtom) this.containerAtoms.peek()).endPosition == j) {
            ContainerAtom containerAtom = (ContainerAtom) this.containerAtoms.pop();
            if (containerAtom.type == Atom.TYPE_moov) {
                processMoovAtom(containerAtom);
                this.containerAtoms.clear();
                this.parserState = 2;
            } else if (!this.containerAtoms.isEmpty()) {
                ((ContainerAtom) this.containerAtoms.peek()).add(containerAtom);
            }
        }
        if (this.parserState != 2) {
            enterReadingAtomHeaderState();
        }
    }

    private void processMoovAtom(ContainerAtom containerAtom) throws ParserException {
        Metadata metadata;
        ContainerAtom containerAtom2 = containerAtom;
        ArrayList arrayList = new ArrayList();
        GaplessInfoHolder gaplessInfoHolder = new GaplessInfoHolder();
        LeafAtom leafAtomOfType = containerAtom2.getLeafAtomOfType(Atom.TYPE_udta);
        Metadata metadata2 = null;
        if (leafAtomOfType != null) {
            metadata = AtomParsers.parseUdta(leafAtomOfType, this.isQuickTime);
            if (metadata != null) {
                gaplessInfoHolder.setFromMetadata(metadata);
            }
        } else {
            metadata = null;
        }
        ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(Atom.TYPE_meta);
        if (containerAtomOfType != null) {
            metadata2 = AtomParsers.parseMdtaFromMeta(containerAtomOfType);
        }
        boolean z = true;
        if ((this.flags & 1) == 0) {
            z = false;
        }
        ArrayList trackSampleTables = getTrackSampleTables(containerAtom2, gaplessInfoHolder, z);
        int size = trackSampleTables.size();
        int i = -1;
        long j = -9223372036854775807L;
        for (int i2 = 0; i2 < size; i2++) {
            TrackSampleTable trackSampleTable = (TrackSampleTable) trackSampleTables.get(i2);
            Track track = trackSampleTable.track;
            Mp4Track mp4Track = new Mp4Track(track, trackSampleTable, this.extractorOutput.track(i2, track.type));
            mp4Track.trackOutput.format(MetadataUtil.getFormatWithMetadata(track.type, track.format.copyWithMaxInputSize(trackSampleTable.maximumSize + 30), metadata, metadata2, gaplessInfoHolder));
            j = Math.max(j, track.durationUs != -9223372036854775807L ? track.durationUs : trackSampleTable.durationUs);
            if (track.type == 2) {
                if (i == -1) {
                    i = arrayList.size();
                }
            }
            arrayList.add(mp4Track);
        }
        this.firstVideoTrackIndex = i;
        this.durationUs = j;
        this.tracks = (Mp4Track[]) arrayList.toArray(new Mp4Track[arrayList.size()]);
        this.accumulatedSampleSizes = calculateAccumulatedSampleSizes(this.tracks);
        this.extractorOutput.endTracks();
        this.extractorOutput.seekMap(this);
    }

    private ArrayList<TrackSampleTable> getTrackSampleTables(ContainerAtom containerAtom, GaplessInfoHolder gaplessInfoHolder, boolean z) throws ParserException {
        ArrayList<TrackSampleTable> arrayList = new ArrayList<>();
        for (int i = 0; i < containerAtom.containerChildren.size(); i++) {
            ContainerAtom containerAtom2 = (ContainerAtom) containerAtom.containerChildren.get(i);
            if (containerAtom2.type == Atom.TYPE_trak) {
                Track parseTrak = AtomParsers.parseTrak(containerAtom2, containerAtom.getLeafAtomOfType(Atom.TYPE_mvhd), -9223372036854775807L, null, z, this.isQuickTime);
                if (parseTrak != null) {
                    TrackSampleTable parseStbl = AtomParsers.parseStbl(parseTrak, containerAtom2.getContainerAtomOfType(Atom.TYPE_mdia).getContainerAtomOfType(Atom.TYPE_minf).getContainerAtomOfType(Atom.TYPE_stbl), gaplessInfoHolder);
                    if (parseStbl.sampleCount != 0) {
                        arrayList.add(parseStbl);
                    }
                }
            }
        }
        return arrayList;
    }

    private int readSample(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        int i;
        long position = extractorInput.getPosition();
        if (this.sampleTrackIndex == -1) {
            this.sampleTrackIndex = getTrackIndexOfNextReadSample(position);
            if (this.sampleTrackIndex == -1) {
                return -1;
            }
        }
        Mp4Track mp4Track = this.tracks[this.sampleTrackIndex];
        TrackOutput trackOutput = mp4Track.trackOutput;
        int i2 = mp4Track.sampleIndex;
        long j = mp4Track.sampleTable.offsets[i2];
        int i3 = mp4Track.sampleTable.sizes[i2];
        long j2 = (j - position) + ((long) this.sampleBytesWritten);
        if (j2 < 0 || j2 >= RELOAD_MINIMUM_SEEK_DISTANCE) {
            positionHolder.position = j;
            return 1;
        }
        if (mp4Track.track.sampleTransformation == 1) {
            j2 += 8;
            i3 -= 8;
        }
        extractorInput.skipFully((int) j2);
        if (mp4Track.track.nalUnitLengthFieldLength != 0) {
            byte[] bArr = this.nalLength.data;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i4 = mp4Track.track.nalUnitLengthFieldLength;
            int i5 = 4 - mp4Track.track.nalUnitLengthFieldLength;
            while (this.sampleBytesWritten < i3) {
                int i6 = this.sampleCurrentNalBytesRemaining;
                if (i6 == 0) {
                    extractorInput.readFully(this.nalLength.data, i5, i4);
                    this.nalLength.setPosition(0);
                    this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                    this.nalStartCode.setPosition(0);
                    trackOutput.sampleData(this.nalStartCode, 4);
                    this.sampleBytesWritten += 4;
                    i3 += i5;
                } else {
                    int sampleData = trackOutput.sampleData(extractorInput, i6, false);
                    this.sampleBytesWritten += sampleData;
                    this.sampleCurrentNalBytesRemaining -= sampleData;
                }
            }
            i = i3;
        } else {
            while (true) {
                int i7 = this.sampleBytesWritten;
                if (i7 >= i3) {
                    break;
                }
                int sampleData2 = trackOutput.sampleData(extractorInput, i3 - i7, false);
                this.sampleBytesWritten += sampleData2;
                this.sampleCurrentNalBytesRemaining -= sampleData2;
            }
            i = i3;
        }
        trackOutput.sampleMetadata(mp4Track.sampleTable.timestampsUs[i2], mp4Track.sampleTable.flags[i2], i, 0, null);
        mp4Track.sampleIndex++;
        this.sampleTrackIndex = -1;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        return 0;
    }

    private int getTrackIndexOfNextReadSample(long j) {
        int i = 0;
        long j2 = Long.MAX_VALUE;
        boolean z = true;
        long j3 = Long.MAX_VALUE;
        int i2 = -1;
        int i3 = -1;
        boolean z2 = true;
        long j4 = Long.MAX_VALUE;
        while (true) {
            Mp4Track[] mp4TrackArr = this.tracks;
            if (i >= mp4TrackArr.length) {
                break;
            }
            Mp4Track mp4Track = mp4TrackArr[i];
            int i4 = mp4Track.sampleIndex;
            if (i4 != mp4Track.sampleTable.sampleCount) {
                long j5 = mp4Track.sampleTable.offsets[i4];
                long j6 = this.accumulatedSampleSizes[i][i4];
                long j7 = j5 - j;
                boolean z3 = j7 < 0 || j7 >= RELOAD_MINIMUM_SEEK_DISTANCE;
                if ((!z3 && z2) || (z3 == z2 && j7 < j4)) {
                    z2 = z3;
                    i3 = i;
                    j4 = j7;
                    j3 = j6;
                }
                if (j6 < j2) {
                    z = z3;
                    i2 = i;
                    j2 = j6;
                }
            }
            i++;
        }
        return (j2 == Long.MAX_VALUE || !z || j3 < j2 + MAXIMUM_READ_AHEAD_BYTES_STREAM) ? i3 : i2;
    }

    private void updateSampleIndices(long j) {
        Mp4Track[] mp4TrackArr;
        for (Mp4Track mp4Track : this.tracks) {
            TrackSampleTable trackSampleTable = mp4Track.sampleTable;
            int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j);
            if (indexOfEarlierOrEqualSynchronizationSample == -1) {
                indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j);
            }
            mp4Track.sampleIndex = indexOfEarlierOrEqualSynchronizationSample;
        }
    }

    private static long[][] calculateAccumulatedSampleSizes(Mp4Track[] mp4TrackArr) {
        long[][] jArr = new long[mp4TrackArr.length][];
        int[] iArr = new int[mp4TrackArr.length];
        long[] jArr2 = new long[mp4TrackArr.length];
        boolean[] zArr = new boolean[mp4TrackArr.length];
        for (int i = 0; i < mp4TrackArr.length; i++) {
            jArr[i] = new long[mp4TrackArr[i].sampleTable.sampleCount];
            jArr2[i] = mp4TrackArr[i].sampleTable.timestampsUs[0];
        }
        long j = 0;
        int i2 = 0;
        while (i2 < mp4TrackArr.length) {
            int i3 = -1;
            long j2 = Long.MAX_VALUE;
            for (int i4 = 0; i4 < mp4TrackArr.length; i4++) {
                if (!zArr[i4] && jArr2[i4] <= j2) {
                    j2 = jArr2[i4];
                    i3 = i4;
                }
            }
            int i5 = iArr[i3];
            jArr[i3][i5] = j;
            j += (long) mp4TrackArr[i3].sampleTable.sizes[i5];
            int i6 = i5 + 1;
            iArr[i3] = i6;
            if (i6 < jArr[i3].length) {
                jArr2[i3] = mp4TrackArr[i3].sampleTable.timestampsUs[i6];
            } else {
                zArr[i3] = true;
                i2++;
            }
        }
        return jArr;
    }

    private static long maybeAdjustSeekOffset(TrackSampleTable trackSampleTable, long j, long j2) {
        int synchronizationSampleIndex = getSynchronizationSampleIndex(trackSampleTable, j);
        if (synchronizationSampleIndex == -1) {
            return j2;
        }
        return Math.min(trackSampleTable.offsets[synchronizationSampleIndex], j2);
    }

    private static int getSynchronizationSampleIndex(TrackSampleTable trackSampleTable, long j) {
        int indexOfEarlierOrEqualSynchronizationSample = trackSampleTable.getIndexOfEarlierOrEqualSynchronizationSample(j);
        return indexOfEarlierOrEqualSynchronizationSample == -1 ? trackSampleTable.getIndexOfLaterOrEqualSynchronizationSample(j) : indexOfEarlierOrEqualSynchronizationSample;
    }

    private static boolean processFtypAtom(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        if (parsableByteArray.readInt() == BRAND_QUICKTIME) {
            return true;
        }
        parsableByteArray.skipBytes(4);
        while (parsableByteArray.bytesLeft() > 0) {
            if (parsableByteArray.readInt() == BRAND_QUICKTIME) {
                return true;
            }
        }
        return false;
    }

    private static boolean shouldParseLeafAtom(int i) {
        return i == Atom.TYPE_mdhd || i == Atom.TYPE_mvhd || i == Atom.TYPE_hdlr || i == Atom.TYPE_stsd || i == Atom.TYPE_stts || i == Atom.TYPE_stss || i == Atom.TYPE_ctts || i == Atom.TYPE_elst || i == Atom.TYPE_stsc || i == Atom.TYPE_stsz || i == Atom.TYPE_stz2 || i == Atom.TYPE_stco || i == Atom.TYPE_co64 || i == Atom.TYPE_tkhd || i == Atom.TYPE_ftyp || i == Atom.TYPE_udta || i == Atom.TYPE_keys || i == Atom.TYPE_ilst;
    }

    private static boolean shouldParseContainerAtom(int i) {
        return i == Atom.TYPE_moov || i == Atom.TYPE_trak || i == Atom.TYPE_mdia || i == Atom.TYPE_minf || i == Atom.TYPE_stbl || i == Atom.TYPE_edts || i == Atom.TYPE_meta;
    }
}
