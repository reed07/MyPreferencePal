package com.google.android.exoplayer2.extractor.mp4;

import android.support.annotation.Nullable;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmInitData.SchemeData;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.extractor.PositionHolder;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.SeekMap.Unseekable;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.TrackOutput.CryptoData;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.google.android.exoplayer2.text.cea.CeaUtil;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import com.google.common.base.Ascii;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public final class FragmentedMp4Extractor implements Extractor {
    private static final Format EMSG_FORMAT = Format.createSampleFormat(null, MimeTypes.APPLICATION_EMSG, Long.MAX_VALUE);
    public static final ExtractorsFactory FACTORY = $$Lambda$FragmentedMp4Extractor$i0zfpH_PcF0vytkdatCL0xeWFhQ.INSTANCE;
    public static final int FLAG_ENABLE_EMSG_TRACK = 4;
    private static final int FLAG_SIDELOADED = 8;
    public static final int FLAG_WORKAROUND_EVERY_VIDEO_FRAME_IS_SYNC_FRAME = 1;
    public static final int FLAG_WORKAROUND_IGNORE_EDIT_LISTS = 16;
    public static final int FLAG_WORKAROUND_IGNORE_TFDT_BOX = 2;
    private static final byte[] PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE = {-94, 57, 79, 82, 90, -101, 79, Ascii.DC4, -94, 68, 108, 66, 124, 100, -115, -12};
    private static final int SAMPLE_GROUP_TYPE_seig = Util.getIntegerCodeForString("seig");
    private static final int STATE_READING_ATOM_HEADER = 0;
    private static final int STATE_READING_ATOM_PAYLOAD = 1;
    private static final int STATE_READING_ENCRYPTION_DATA = 2;
    private static final int STATE_READING_SAMPLE_CONTINUE = 4;
    private static final int STATE_READING_SAMPLE_START = 3;
    private static final String TAG = "FragmentedMp4Extractor";
    @Nullable
    private final TrackOutput additionalEmsgTrackOutput;
    private ParsableByteArray atomData;
    private final ParsableByteArray atomHeader;
    private int atomHeaderBytesRead;
    private long atomSize;
    private int atomType;
    private TrackOutput[] cea608TrackOutputs;
    private final List<Format> closedCaptionFormats;
    private final ArrayDeque<ContainerAtom> containerAtoms;
    private TrackBundle currentTrackBundle;
    private long durationUs;
    private TrackOutput[] emsgTrackOutputs;
    private long endOfMdatPosition;
    private final byte[] extendedTypeScratch;
    private ExtractorOutput extractorOutput;
    private final int flags;
    private boolean haveOutputSeekMap;
    private final ParsableByteArray nalBuffer;
    private final ParsableByteArray nalPrefix;
    private final ParsableByteArray nalStartCode;
    private int parserState;
    private int pendingMetadataSampleBytes;
    private final ArrayDeque<MetadataSampleInfo> pendingMetadataSampleInfos;
    private long pendingSeekTimeUs;
    private boolean processSeiNalUnitPayload;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private int sampleSize;
    private long segmentIndexEarliestPresentationTimeUs;
    @Nullable
    private final DrmInitData sideloadedDrmInitData;
    @Nullable
    private final Track sideloadedTrack;
    @Nullable
    private final TimestampAdjuster timestampAdjuster;
    private final SparseArray<TrackBundle> trackBundles;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    private static final class MetadataSampleInfo {
        public final long presentationTimeDeltaUs;
        public final int size;

        public MetadataSampleInfo(long j, int i) {
            this.presentationTimeDeltaUs = j;
            this.size = i;
        }
    }

    private static final class TrackBundle {
        public int currentSampleInTrackRun;
        public int currentSampleIndex;
        public int currentTrackRunIndex;
        private final ParsableByteArray defaultInitializationVector = new ParsableByteArray();
        public DefaultSampleValues defaultSampleValues;
        private final ParsableByteArray encryptionSignalByte = new ParsableByteArray(1);
        public int firstSampleToOutputIndex;
        public final TrackFragment fragment = new TrackFragment();
        public final TrackOutput output;
        public Track track;

        public TrackBundle(TrackOutput trackOutput) {
            this.output = trackOutput;
        }

        public void init(Track track2, DefaultSampleValues defaultSampleValues2) {
            this.track = (Track) Assertions.checkNotNull(track2);
            this.defaultSampleValues = (DefaultSampleValues) Assertions.checkNotNull(defaultSampleValues2);
            this.output.format(track2.format);
            reset();
        }

        public void updateDrmInitData(DrmInitData drmInitData) {
            TrackEncryptionBox sampleDescriptionEncryptionBox = this.track.getSampleDescriptionEncryptionBox(this.fragment.header.sampleDescriptionIndex);
            this.output.format(this.track.format.copyWithDrmInitData(drmInitData.copyWithSchemeType(sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null)));
        }

        public void reset() {
            this.fragment.reset();
            this.currentSampleIndex = 0;
            this.currentTrackRunIndex = 0;
            this.currentSampleInTrackRun = 0;
            this.firstSampleToOutputIndex = 0;
        }

        public void seek(long j) {
            long usToMs = C.usToMs(j);
            int i = this.currentSampleIndex;
            while (i < this.fragment.sampleCount && this.fragment.getSamplePresentationTime(i) < usToMs) {
                if (this.fragment.sampleIsSyncFrameTable[i]) {
                    this.firstSampleToOutputIndex = i;
                }
                i++;
            }
        }

        public boolean next() {
            this.currentSampleIndex++;
            this.currentSampleInTrackRun++;
            int i = this.currentSampleInTrackRun;
            int[] iArr = this.fragment.trunLength;
            int i2 = this.currentTrackRunIndex;
            if (i != iArr[i2]) {
                return true;
            }
            this.currentTrackRunIndex = i2 + 1;
            this.currentSampleInTrackRun = 0;
            return false;
        }

        public int outputSampleEncryptionData() {
            ParsableByteArray parsableByteArray;
            int i;
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted == null) {
                return 0;
            }
            if (encryptionBoxIfEncrypted.perSampleIvSize != 0) {
                parsableByteArray = this.fragment.sampleEncryptionData;
                i = encryptionBoxIfEncrypted.perSampleIvSize;
            } else {
                byte[] bArr = encryptionBoxIfEncrypted.defaultInitializationVector;
                this.defaultInitializationVector.reset(bArr, bArr.length);
                parsableByteArray = this.defaultInitializationVector;
                i = bArr.length;
            }
            boolean sampleHasSubsampleEncryptionTable = this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex);
            this.encryptionSignalByte.data[0] = (byte) ((sampleHasSubsampleEncryptionTable ? 128 : 0) | i);
            this.encryptionSignalByte.setPosition(0);
            this.output.sampleData(this.encryptionSignalByte, 1);
            this.output.sampleData(parsableByteArray, i);
            if (!sampleHasSubsampleEncryptionTable) {
                return i + 1;
            }
            ParsableByteArray parsableByteArray2 = this.fragment.sampleEncryptionData;
            int readUnsignedShort = parsableByteArray2.readUnsignedShort();
            parsableByteArray2.skipBytes(-2);
            int i2 = (readUnsignedShort * 6) + 2;
            this.output.sampleData(parsableByteArray2, i2);
            return i + 1 + i2;
        }

        /* access modifiers changed from: private */
        public void skipSampleEncryptionData() {
            TrackEncryptionBox encryptionBoxIfEncrypted = getEncryptionBoxIfEncrypted();
            if (encryptionBoxIfEncrypted != null) {
                ParsableByteArray parsableByteArray = this.fragment.sampleEncryptionData;
                if (encryptionBoxIfEncrypted.perSampleIvSize != 0) {
                    parsableByteArray.skipBytes(encryptionBoxIfEncrypted.perSampleIvSize);
                }
                if (this.fragment.sampleHasSubsampleEncryptionTable(this.currentSampleIndex)) {
                    parsableByteArray.skipBytes(parsableByteArray.readUnsignedShort() * 6);
                }
            }
        }

        /* access modifiers changed from: private */
        public TrackEncryptionBox getEncryptionBoxIfEncrypted() {
            TrackEncryptionBox trackEncryptionBox;
            int i = this.fragment.header.sampleDescriptionIndex;
            if (this.fragment.trackEncryptionBox != null) {
                trackEncryptionBox = this.fragment.trackEncryptionBox;
            } else {
                trackEncryptionBox = this.track.getSampleDescriptionEncryptionBox(i);
            }
            if (trackEncryptionBox == null || !trackEncryptionBox.isEncrypted) {
                return null;
            }
            return trackEncryptionBox;
        }
    }

    public void release() {
    }

    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new FragmentedMp4Extractor()};
    }

    public FragmentedMp4Extractor() {
        this(0);
    }

    public FragmentedMp4Extractor(int i) {
        this(i, null);
    }

    public FragmentedMp4Extractor(int i, @Nullable TimestampAdjuster timestampAdjuster2) {
        this(i, timestampAdjuster2, null, null);
    }

    public FragmentedMp4Extractor(int i, @Nullable TimestampAdjuster timestampAdjuster2, @Nullable Track track, @Nullable DrmInitData drmInitData) {
        this(i, timestampAdjuster2, track, drmInitData, Collections.emptyList());
    }

    public FragmentedMp4Extractor(int i, @Nullable TimestampAdjuster timestampAdjuster2, @Nullable Track track, @Nullable DrmInitData drmInitData, List<Format> list) {
        this(i, timestampAdjuster2, track, drmInitData, list, null);
    }

    public FragmentedMp4Extractor(int i, @Nullable TimestampAdjuster timestampAdjuster2, @Nullable Track track, @Nullable DrmInitData drmInitData, List<Format> list, @Nullable TrackOutput trackOutput) {
        this.flags = i | (track != null ? 8 : 0);
        this.timestampAdjuster = timestampAdjuster2;
        this.sideloadedTrack = track;
        this.sideloadedDrmInitData = drmInitData;
        this.closedCaptionFormats = Collections.unmodifiableList(list);
        this.additionalEmsgTrackOutput = trackOutput;
        this.atomHeader = new ParsableByteArray(16);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalPrefix = new ParsableByteArray(5);
        this.nalBuffer = new ParsableByteArray();
        this.extendedTypeScratch = new byte[16];
        this.containerAtoms = new ArrayDeque<>();
        this.pendingMetadataSampleInfos = new ArrayDeque<>();
        this.trackBundles = new SparseArray<>();
        this.durationUs = -9223372036854775807L;
        this.pendingSeekTimeUs = -9223372036854775807L;
        this.segmentIndexEarliestPresentationTimeUs = -9223372036854775807L;
        enterReadingAtomHeaderState();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return Sniffer.sniffFragmented(extractorInput);
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
        Track track = this.sideloadedTrack;
        if (track != null) {
            TrackBundle trackBundle = new TrackBundle(extractorOutput2.track(0, track.type));
            trackBundle.init(this.sideloadedTrack, new DefaultSampleValues(0, 0, 0, 0));
            this.trackBundles.put(0, trackBundle);
            maybeInitExtraTracks();
            this.extractorOutput.endTracks();
        }
    }

    public void seek(long j, long j2) {
        int size = this.trackBundles.size();
        for (int i = 0; i < size; i++) {
            ((TrackBundle) this.trackBundles.valueAt(i)).reset();
        }
        this.pendingMetadataSampleInfos.clear();
        this.pendingMetadataSampleBytes = 0;
        this.pendingSeekTimeUs = j2;
        this.containerAtoms.clear();
        enterReadingAtomHeaderState();
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
                    readAtomPayload(extractorInput);
                    break;
                case 2:
                    readEncryptionData(extractorInput);
                    break;
                default:
                    if (!readSample(extractorInput)) {
                        break;
                    } else {
                        return 0;
                    }
            }
        }
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
            long position = extractorInput.getPosition() - ((long) this.atomHeaderBytesRead);
            if (this.atomType == Atom.TYPE_moof) {
                int size = this.trackBundles.size();
                for (int i = 0; i < size; i++) {
                    TrackFragment trackFragment = ((TrackBundle) this.trackBundles.valueAt(i)).fragment;
                    trackFragment.atomPosition = position;
                    trackFragment.auxiliaryDataPosition = position;
                    trackFragment.dataPosition = position;
                }
            }
            if (this.atomType == Atom.TYPE_mdat) {
                this.currentTrackBundle = null;
                this.endOfMdatPosition = this.atomSize + position;
                if (!this.haveOutputSeekMap) {
                    this.extractorOutput.seekMap(new Unseekable(this.durationUs, position));
                    this.haveOutputSeekMap = true;
                }
                this.parserState = 2;
                return true;
            }
            if (shouldParseContainerAtom(this.atomType)) {
                long position2 = (extractorInput.getPosition() + this.atomSize) - 8;
                this.containerAtoms.push(new ContainerAtom(this.atomType, position2));
                if (this.atomSize == ((long) this.atomHeaderBytesRead)) {
                    processAtomEnded(position2);
                } else {
                    enterReadingAtomHeaderState();
                }
            } else if (shouldParseLeafAtom(this.atomType)) {
                if (this.atomHeaderBytesRead == 8) {
                    long j2 = this.atomSize;
                    if (j2 <= 2147483647L) {
                        this.atomData = new ParsableByteArray((int) j2);
                        System.arraycopy(this.atomHeader.data, 0, this.atomData.data, 0, 8);
                        this.parserState = 1;
                    } else {
                        throw new ParserException("Leaf atom with length > 2147483647 (unsupported).");
                    }
                } else {
                    throw new ParserException("Leaf atom defines extended atom size (unsupported).");
                }
            } else if (this.atomSize <= 2147483647L) {
                this.atomData = null;
                this.parserState = 1;
            } else {
                throw new ParserException("Skipping atom with length > 2147483647 (unsupported).");
            }
            return true;
        }
        throw new ParserException("Atom size less than header length (unsupported).");
    }

    private void readAtomPayload(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int i = ((int) this.atomSize) - this.atomHeaderBytesRead;
        ParsableByteArray parsableByteArray = this.atomData;
        if (parsableByteArray != null) {
            extractorInput.readFully(parsableByteArray.data, 8, i);
            onLeafAtomRead(new LeafAtom(this.atomType, this.atomData), extractorInput.getPosition());
        } else {
            extractorInput.skipFully(i);
        }
        processAtomEnded(extractorInput.getPosition());
    }

    private void processAtomEnded(long j) throws ParserException {
        while (!this.containerAtoms.isEmpty() && ((ContainerAtom) this.containerAtoms.peek()).endPosition == j) {
            onContainerAtomRead((ContainerAtom) this.containerAtoms.pop());
        }
        enterReadingAtomHeaderState();
    }

    private void onLeafAtomRead(LeafAtom leafAtom, long j) throws ParserException {
        if (!this.containerAtoms.isEmpty()) {
            ((ContainerAtom) this.containerAtoms.peek()).add(leafAtom);
        } else if (leafAtom.type == Atom.TYPE_sidx) {
            Pair parseSidx = parseSidx(leafAtom.data, j);
            this.segmentIndexEarliestPresentationTimeUs = ((Long) parseSidx.first).longValue();
            this.extractorOutput.seekMap((SeekMap) parseSidx.second);
            this.haveOutputSeekMap = true;
        } else if (leafAtom.type == Atom.TYPE_emsg) {
            onEmsgLeafAtomRead(leafAtom.data);
        }
    }

    private void onContainerAtomRead(ContainerAtom containerAtom) throws ParserException {
        if (containerAtom.type == Atom.TYPE_moov) {
            onMoovContainerAtomRead(containerAtom);
        } else if (containerAtom.type == Atom.TYPE_moof) {
            onMoofContainerAtomRead(containerAtom);
        } else if (!this.containerAtoms.isEmpty()) {
            ((ContainerAtom) this.containerAtoms.peek()).add(containerAtom);
        }
    }

    private void onMoovContainerAtomRead(ContainerAtom containerAtom) throws ParserException {
        int i;
        int i2;
        ContainerAtom containerAtom2 = containerAtom;
        boolean z = true;
        int i3 = 0;
        Assertions.checkState(this.sideloadedTrack == null, "Unexpected moov box.");
        DrmInitData drmInitData = this.sideloadedDrmInitData;
        if (drmInitData == null) {
            drmInitData = getDrmInitDataFromAtoms(containerAtom2.leafChildren);
        }
        ContainerAtom containerAtomOfType = containerAtom2.getContainerAtomOfType(Atom.TYPE_mvex);
        SparseArray sparseArray = new SparseArray();
        int size = containerAtomOfType.leafChildren.size();
        long j = -9223372036854775807L;
        for (int i4 = 0; i4 < size; i4++) {
            LeafAtom leafAtom = (LeafAtom) containerAtomOfType.leafChildren.get(i4);
            if (leafAtom.type == Atom.TYPE_trex) {
                Pair parseTrex = parseTrex(leafAtom.data);
                sparseArray.put(((Integer) parseTrex.first).intValue(), parseTrex.second);
            } else if (leafAtom.type == Atom.TYPE_mehd) {
                j = parseMehd(leafAtom.data);
            }
        }
        SparseArray sparseArray2 = new SparseArray();
        int size2 = containerAtom2.containerChildren.size();
        int i5 = 0;
        while (i5 < size2) {
            ContainerAtom containerAtom3 = (ContainerAtom) containerAtom2.containerChildren.get(i5);
            if (containerAtom3.type == Atom.TYPE_trak) {
                i = i5;
                i2 = size2;
                Track parseTrak = AtomParsers.parseTrak(containerAtom3, containerAtom2.getLeafAtomOfType(Atom.TYPE_mvhd), j, drmInitData, (this.flags & 16) != 0, false);
                if (parseTrak != null) {
                    sparseArray2.put(parseTrak.id, parseTrak);
                }
            } else {
                i = i5;
                i2 = size2;
            }
            i5 = i + 1;
            size2 = i2;
        }
        int size3 = sparseArray2.size();
        if (this.trackBundles.size() == 0) {
            while (i3 < size3) {
                Track track = (Track) sparseArray2.valueAt(i3);
                TrackBundle trackBundle = new TrackBundle(this.extractorOutput.track(i3, track.type));
                trackBundle.init(track, getDefaultSampleValues(sparseArray, track.id));
                this.trackBundles.put(track.id, trackBundle);
                this.durationUs = Math.max(this.durationUs, track.durationUs);
                i3++;
            }
            maybeInitExtraTracks();
            this.extractorOutput.endTracks();
            return;
        }
        if (this.trackBundles.size() != size3) {
            z = false;
        }
        Assertions.checkState(z);
        while (i3 < size3) {
            Track track2 = (Track) sparseArray2.valueAt(i3);
            ((TrackBundle) this.trackBundles.get(track2.id)).init(track2, getDefaultSampleValues(sparseArray, track2.id));
            i3++;
        }
    }

    private DefaultSampleValues getDefaultSampleValues(SparseArray<DefaultSampleValues> sparseArray, int i) {
        if (sparseArray.size() == 1) {
            return (DefaultSampleValues) sparseArray.valueAt(0);
        }
        return (DefaultSampleValues) Assertions.checkNotNull(sparseArray.get(i));
    }

    private void onMoofContainerAtomRead(ContainerAtom containerAtom) throws ParserException {
        DrmInitData drmInitData;
        parseMoof(containerAtom, this.trackBundles, this.flags, this.extendedTypeScratch);
        if (this.sideloadedDrmInitData != null) {
            drmInitData = null;
        } else {
            drmInitData = getDrmInitDataFromAtoms(containerAtom.leafChildren);
        }
        if (drmInitData != null) {
            int size = this.trackBundles.size();
            for (int i = 0; i < size; i++) {
                ((TrackBundle) this.trackBundles.valueAt(i)).updateDrmInitData(drmInitData);
            }
        }
        if (this.pendingSeekTimeUs != -9223372036854775807L) {
            int size2 = this.trackBundles.size();
            for (int i2 = 0; i2 < size2; i2++) {
                ((TrackBundle) this.trackBundles.valueAt(i2)).seek(this.pendingSeekTimeUs);
            }
            this.pendingSeekTimeUs = -9223372036854775807L;
        }
    }

    private void maybeInitExtraTracks() {
        int i;
        if (this.emsgTrackOutputs == null) {
            this.emsgTrackOutputs = new TrackOutput[2];
            TrackOutput trackOutput = this.additionalEmsgTrackOutput;
            if (trackOutput != null) {
                this.emsgTrackOutputs[0] = trackOutput;
                i = 1;
            } else {
                i = 0;
            }
            if ((this.flags & 4) != 0) {
                int i2 = i + 1;
                this.emsgTrackOutputs[i] = this.extractorOutput.track(this.trackBundles.size(), 4);
                i = i2;
            }
            this.emsgTrackOutputs = (TrackOutput[]) Arrays.copyOf(this.emsgTrackOutputs, i);
            for (TrackOutput format : this.emsgTrackOutputs) {
                format.format(EMSG_FORMAT);
            }
        }
        if (this.cea608TrackOutputs == null) {
            this.cea608TrackOutputs = new TrackOutput[this.closedCaptionFormats.size()];
            for (int i3 = 0; i3 < this.cea608TrackOutputs.length; i3++) {
                TrackOutput track = this.extractorOutput.track(this.trackBundles.size() + 1 + i3, 3);
                track.format((Format) this.closedCaptionFormats.get(i3));
                this.cea608TrackOutputs[i3] = track;
            }
        }
    }

    private void onEmsgLeafAtomRead(ParsableByteArray parsableByteArray) {
        TrackOutput[] trackOutputArr;
        TrackOutput[] trackOutputArr2 = this.emsgTrackOutputs;
        if (trackOutputArr2 != null && trackOutputArr2.length != 0) {
            parsableByteArray.setPosition(12);
            int bytesLeft = parsableByteArray.bytesLeft();
            parsableByteArray.readNullTerminatedString();
            parsableByteArray.readNullTerminatedString();
            long scaleLargeTimestamp = Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), 1000000, parsableByteArray.readUnsignedInt());
            int position = parsableByteArray.getPosition();
            parsableByteArray.data[position - 4] = 0;
            parsableByteArray.data[position - 3] = 0;
            parsableByteArray.data[position - 2] = 0;
            parsableByteArray.data[position - 1] = 0;
            for (TrackOutput trackOutput : this.emsgTrackOutputs) {
                parsableByteArray.setPosition(12);
                trackOutput.sampleData(parsableByteArray, bytesLeft);
            }
            long j = this.segmentIndexEarliestPresentationTimeUs;
            if (j != -9223372036854775807L) {
                long j2 = j + scaleLargeTimestamp;
                TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
                long adjustSampleTimestamp = timestampAdjuster2 != null ? timestampAdjuster2.adjustSampleTimestamp(j2) : j2;
                for (TrackOutput sampleMetadata : this.emsgTrackOutputs) {
                    sampleMetadata.sampleMetadata(adjustSampleTimestamp, 1, bytesLeft, 0, null);
                }
            } else {
                this.pendingMetadataSampleInfos.addLast(new MetadataSampleInfo(scaleLargeTimestamp, bytesLeft));
                this.pendingMetadataSampleBytes += bytesLeft;
            }
        }
    }

    private static Pair<Integer, DefaultSampleValues> parseTrex(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(12);
        return Pair.create(Integer.valueOf(parsableByteArray.readInt()), new DefaultSampleValues(parsableByteArray.readUnsignedIntToInt() - 1, parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readUnsignedIntToInt(), parsableByteArray.readInt()));
    }

    private static long parseMehd(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 0 ? parsableByteArray.readUnsignedInt() : parsableByteArray.readUnsignedLongToLong();
    }

    private static void parseMoof(ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, int i, byte[] bArr) throws ParserException {
        int size = containerAtom.containerChildren.size();
        for (int i2 = 0; i2 < size; i2++) {
            ContainerAtom containerAtom2 = (ContainerAtom) containerAtom.containerChildren.get(i2);
            if (containerAtom2.type == Atom.TYPE_traf) {
                parseTraf(containerAtom2, sparseArray, i, bArr);
            }
        }
    }

    private static void parseTraf(ContainerAtom containerAtom, SparseArray<TrackBundle> sparseArray, int i, byte[] bArr) throws ParserException {
        TrackBundle parseTfhd = parseTfhd(containerAtom.getLeafAtomOfType(Atom.TYPE_tfhd).data, sparseArray);
        if (parseTfhd != null) {
            TrackFragment trackFragment = parseTfhd.fragment;
            long j = trackFragment.nextFragmentDecodeTime;
            parseTfhd.reset();
            if (containerAtom.getLeafAtomOfType(Atom.TYPE_tfdt) != null && (i & 2) == 0) {
                j = parseTfdt(containerAtom.getLeafAtomOfType(Atom.TYPE_tfdt).data);
            }
            parseTruns(containerAtom, parseTfhd, j, i);
            TrackEncryptionBox sampleDescriptionEncryptionBox = parseTfhd.track.getSampleDescriptionEncryptionBox(trackFragment.header.sampleDescriptionIndex);
            LeafAtom leafAtomOfType = containerAtom.getLeafAtomOfType(Atom.TYPE_saiz);
            if (leafAtomOfType != null) {
                parseSaiz(sampleDescriptionEncryptionBox, leafAtomOfType.data, trackFragment);
            }
            LeafAtom leafAtomOfType2 = containerAtom.getLeafAtomOfType(Atom.TYPE_saio);
            if (leafAtomOfType2 != null) {
                parseSaio(leafAtomOfType2.data, trackFragment);
            }
            LeafAtom leafAtomOfType3 = containerAtom.getLeafAtomOfType(Atom.TYPE_senc);
            if (leafAtomOfType3 != null) {
                parseSenc(leafAtomOfType3.data, trackFragment);
            }
            LeafAtom leafAtomOfType4 = containerAtom.getLeafAtomOfType(Atom.TYPE_sbgp);
            LeafAtom leafAtomOfType5 = containerAtom.getLeafAtomOfType(Atom.TYPE_sgpd);
            if (!(leafAtomOfType4 == null || leafAtomOfType5 == null)) {
                parseSgpd(leafAtomOfType4.data, leafAtomOfType5.data, sampleDescriptionEncryptionBox != null ? sampleDescriptionEncryptionBox.schemeType : null, trackFragment);
            }
            int size = containerAtom.leafChildren.size();
            for (int i2 = 0; i2 < size; i2++) {
                LeafAtom leafAtom = (LeafAtom) containerAtom.leafChildren.get(i2);
                if (leafAtom.type == Atom.TYPE_uuid) {
                    parseUuid(leafAtom.data, trackFragment, bArr);
                }
            }
        }
    }

    private static void parseTruns(ContainerAtom containerAtom, TrackBundle trackBundle, long j, int i) {
        List<LeafAtom> list = containerAtom.leafChildren;
        int size = list.size();
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < size; i4++) {
            LeafAtom leafAtom = (LeafAtom) list.get(i4);
            if (leafAtom.type == Atom.TYPE_trun) {
                ParsableByteArray parsableByteArray = leafAtom.data;
                parsableByteArray.setPosition(12);
                int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
                if (readUnsignedIntToInt > 0) {
                    i3 += readUnsignedIntToInt;
                    i2++;
                }
            }
        }
        trackBundle.currentTrackRunIndex = 0;
        trackBundle.currentSampleInTrackRun = 0;
        trackBundle.currentSampleIndex = 0;
        trackBundle.fragment.initTables(i2, i3);
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < size; i7++) {
            LeafAtom leafAtom2 = (LeafAtom) list.get(i7);
            if (leafAtom2.type == Atom.TYPE_trun) {
                int i8 = i5 + 1;
                i6 = parseTrun(trackBundle, i5, j, i, leafAtom2.data, i6);
                i5 = i8;
            }
        }
    }

    private static void parseSaiz(TrackEncryptionBox trackEncryptionBox, ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        int i;
        int i2 = trackEncryptionBox.perSampleIvSize;
        parsableByteArray.setPosition(8);
        boolean z = true;
        if ((Atom.parseFullAtomFlags(parsableByteArray.readInt()) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int readUnsignedByte = parsableByteArray.readUnsignedByte();
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (readUnsignedIntToInt == trackFragment.sampleCount) {
            if (readUnsignedByte == 0) {
                boolean[] zArr = trackFragment.sampleHasSubsampleEncryptionTable;
                i = 0;
                for (int i3 = 0; i3 < readUnsignedIntToInt; i3++) {
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    i += readUnsignedByte2;
                    zArr[i3] = readUnsignedByte2 > i2;
                }
            } else {
                if (readUnsignedByte <= i2) {
                    z = false;
                }
                i = (readUnsignedByte * readUnsignedIntToInt) + 0;
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, readUnsignedIntToInt, z);
            }
            trackFragment.initEncryptionData(i);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Length mismatch: ");
        sb.append(readUnsignedIntToInt);
        sb.append(", ");
        sb.append(trackFragment.sampleCount);
        throw new ParserException(sb.toString());
    }

    private static void parseSaio(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        long j;
        parsableByteArray.setPosition(8);
        int readInt = parsableByteArray.readInt();
        if ((Atom.parseFullAtomFlags(readInt) & 1) == 1) {
            parsableByteArray.skipBytes(8);
        }
        int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
        if (readUnsignedIntToInt == 1) {
            int parseFullAtomVersion = Atom.parseFullAtomVersion(readInt);
            long j2 = trackFragment.auxiliaryDataPosition;
            if (parseFullAtomVersion == 0) {
                j = parsableByteArray.readUnsignedInt();
            } else {
                j = parsableByteArray.readUnsignedLongToLong();
            }
            trackFragment.auxiliaryDataPosition = j2 + j;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Unexpected saio entry count: ");
        sb.append(readUnsignedIntToInt);
        throw new ParserException(sb.toString());
    }

    private static TrackBundle parseTfhd(ParsableByteArray parsableByteArray, SparseArray<TrackBundle> sparseArray) {
        parsableByteArray.setPosition(8);
        int parseFullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        TrackBundle trackBundle = getTrackBundle(sparseArray, parsableByteArray.readInt());
        if (trackBundle == null) {
            return null;
        }
        if ((parseFullAtomFlags & 1) != 0) {
            long readUnsignedLongToLong = parsableByteArray.readUnsignedLongToLong();
            trackBundle.fragment.dataPosition = readUnsignedLongToLong;
            trackBundle.fragment.auxiliaryDataPosition = readUnsignedLongToLong;
        }
        DefaultSampleValues defaultSampleValues = trackBundle.defaultSampleValues;
        trackBundle.fragment.header = new DefaultSampleValues((parseFullAtomFlags & 2) != 0 ? parsableByteArray.readUnsignedIntToInt() - 1 : defaultSampleValues.sampleDescriptionIndex, (parseFullAtomFlags & 8) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.duration, (parseFullAtomFlags & 16) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.size, (parseFullAtomFlags & 32) != 0 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.flags);
        return trackBundle;
    }

    @Nullable
    private static TrackBundle getTrackBundle(SparseArray<TrackBundle> sparseArray, int i) {
        if (sparseArray.size() == 1) {
            return (TrackBundle) sparseArray.valueAt(0);
        }
        return (TrackBundle) sparseArray.get(i);
    }

    private static long parseTfdt(ParsableByteArray parsableByteArray) {
        parsableByteArray.setPosition(8);
        return Atom.parseFullAtomVersion(parsableByteArray.readInt()) == 1 ? parsableByteArray.readUnsignedLongToLong() : parsableByteArray.readUnsignedInt();
    }

    private static int parseTrun(TrackBundle trackBundle, int i, long j, int i2, ParsableByteArray parsableByteArray, int i3) {
        boolean[] zArr;
        long j2;
        long[] jArr;
        boolean z;
        int i4;
        boolean z2;
        int i5;
        boolean z3;
        boolean z4;
        boolean z5;
        TrackBundle trackBundle2 = trackBundle;
        parsableByteArray.setPosition(8);
        int parseFullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        Track track = trackBundle2.track;
        TrackFragment trackFragment = trackBundle2.fragment;
        DefaultSampleValues defaultSampleValues = trackFragment.header;
        trackFragment.trunLength[i] = parsableByteArray.readUnsignedIntToInt();
        trackFragment.trunDataPosition[i] = trackFragment.dataPosition;
        if ((parseFullAtomFlags & 1) != 0) {
            long[] jArr2 = trackFragment.trunDataPosition;
            jArr2[i] = jArr2[i] + ((long) parsableByteArray.readInt());
        }
        boolean z6 = (parseFullAtomFlags & 4) != 0;
        int i6 = defaultSampleValues.flags;
        if (z6) {
            i6 = parsableByteArray.readUnsignedIntToInt();
        }
        boolean z7 = (parseFullAtomFlags & 256) != 0;
        boolean z8 = (parseFullAtomFlags & 512) != 0;
        boolean z9 = (parseFullAtomFlags & 1024) != 0;
        boolean z10 = (parseFullAtomFlags & 2048) != 0;
        long j3 = 0;
        if (track.editListDurations != null && track.editListDurations.length == 1 && track.editListDurations[0] == 0) {
            j3 = Util.scaleLargeTimestamp(track.editListMediaTimes[0], 1000, track.timescale);
        }
        int[] iArr = trackFragment.sampleSizeTable;
        int[] iArr2 = trackFragment.sampleCompositionTimeOffsetTable;
        long[] jArr3 = trackFragment.sampleDecodingTimeTable;
        boolean[] zArr2 = trackFragment.sampleIsSyncFrameTable;
        int i7 = i6;
        boolean z11 = track.type == 2 && (i2 & 1) != 0;
        int i8 = i3 + trackFragment.trunLength[i];
        long j4 = j3;
        boolean[] zArr3 = zArr2;
        long j5 = track.timescale;
        if (i > 0) {
            zArr = zArr3;
            jArr = jArr3;
            j2 = trackFragment.nextFragmentDecodeTime;
        } else {
            zArr = zArr3;
            jArr = jArr3;
            j2 = j;
        }
        long j6 = j2;
        int i9 = i3;
        while (i9 < i8) {
            int readUnsignedIntToInt = z7 ? parsableByteArray.readUnsignedIntToInt() : defaultSampleValues.duration;
            if (z8) {
                z = z7;
                i4 = parsableByteArray.readUnsignedIntToInt();
            } else {
                z = z7;
                i4 = defaultSampleValues.size;
            }
            if (i9 == 0 && z6) {
                z2 = z6;
                i5 = i7;
            } else if (z9) {
                z2 = z6;
                i5 = parsableByteArray.readInt();
            } else {
                z2 = z6;
                i5 = defaultSampleValues.flags;
            }
            if (z10) {
                z5 = z10;
                z4 = z8;
                z3 = z9;
                iArr2[i9] = (int) ((((long) parsableByteArray.readInt()) * 1000) / j5);
            } else {
                z5 = z10;
                z4 = z8;
                z3 = z9;
                iArr2[i9] = 0;
            }
            jArr[i9] = Util.scaleLargeTimestamp(j6, 1000, j5) - j4;
            iArr[i9] = i4;
            zArr[i9] = ((i5 >> 16) & 1) == 0 && (!z11 || i9 == 0);
            i9++;
            j6 += (long) readUnsignedIntToInt;
            z7 = z;
            z6 = z2;
            z10 = z5;
            z8 = z4;
            z9 = z3;
            i8 = i8;
        }
        int i10 = i8;
        trackFragment.nextFragmentDecodeTime = j6;
        return i10;
    }

    private static void parseUuid(ParsableByteArray parsableByteArray, TrackFragment trackFragment, byte[] bArr) throws ParserException {
        parsableByteArray.setPosition(8);
        parsableByteArray.readBytes(bArr, 0, 16);
        if (Arrays.equals(bArr, PIFF_SAMPLE_ENCRYPTION_BOX_EXTENDED_TYPE)) {
            parseSenc(parsableByteArray, 16, trackFragment);
        }
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, TrackFragment trackFragment) throws ParserException {
        parseSenc(parsableByteArray, 0, trackFragment);
    }

    private static void parseSenc(ParsableByteArray parsableByteArray, int i, TrackFragment trackFragment) throws ParserException {
        parsableByteArray.setPosition(i + 8);
        int parseFullAtomFlags = Atom.parseFullAtomFlags(parsableByteArray.readInt());
        if ((parseFullAtomFlags & 1) == 0) {
            boolean z = (parseFullAtomFlags & 2) != 0;
            int readUnsignedIntToInt = parsableByteArray.readUnsignedIntToInt();
            if (readUnsignedIntToInt == trackFragment.sampleCount) {
                Arrays.fill(trackFragment.sampleHasSubsampleEncryptionTable, 0, readUnsignedIntToInt, z);
                trackFragment.initEncryptionData(parsableByteArray.bytesLeft());
                trackFragment.fillEncryptionData(parsableByteArray);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Length mismatch: ");
            sb.append(readUnsignedIntToInt);
            sb.append(", ");
            sb.append(trackFragment.sampleCount);
            throw new ParserException(sb.toString());
        }
        throw new ParserException("Overriding TrackEncryptionBox parameters is unsupported.");
    }

    private static void parseSgpd(ParsableByteArray parsableByteArray, ParsableByteArray parsableByteArray2, String str, TrackFragment trackFragment) throws ParserException {
        byte[] bArr;
        parsableByteArray.setPosition(8);
        int readInt = parsableByteArray.readInt();
        if (parsableByteArray.readInt() == SAMPLE_GROUP_TYPE_seig) {
            if (Atom.parseFullAtomVersion(readInt) == 1) {
                parsableByteArray.skipBytes(4);
            }
            if (parsableByteArray.readInt() == 1) {
                parsableByteArray2.setPosition(8);
                int readInt2 = parsableByteArray2.readInt();
                if (parsableByteArray2.readInt() == SAMPLE_GROUP_TYPE_seig) {
                    int parseFullAtomVersion = Atom.parseFullAtomVersion(readInt2);
                    if (parseFullAtomVersion == 1) {
                        if (parsableByteArray2.readUnsignedInt() == 0) {
                            throw new ParserException("Variable length description in sgpd found (unsupported)");
                        }
                    } else if (parseFullAtomVersion >= 2) {
                        parsableByteArray2.skipBytes(4);
                    }
                    if (parsableByteArray2.readUnsignedInt() == 1) {
                        parsableByteArray2.skipBytes(1);
                        int readUnsignedByte = parsableByteArray2.readUnsignedByte();
                        int i = (readUnsignedByte & PsExtractor.VIDEO_STREAM_MASK) >> 4;
                        int i2 = readUnsignedByte & 15;
                        boolean z = parsableByteArray2.readUnsignedByte() == 1;
                        if (z) {
                            int readUnsignedByte2 = parsableByteArray2.readUnsignedByte();
                            byte[] bArr2 = new byte[16];
                            parsableByteArray2.readBytes(bArr2, 0, bArr2.length);
                            if (!z || readUnsignedByte2 != 0) {
                                bArr = null;
                            } else {
                                int readUnsignedByte3 = parsableByteArray2.readUnsignedByte();
                                byte[] bArr3 = new byte[readUnsignedByte3];
                                parsableByteArray2.readBytes(bArr3, 0, readUnsignedByte3);
                                bArr = bArr3;
                            }
                            trackFragment.definesEncryptionData = true;
                            TrackEncryptionBox trackEncryptionBox = new TrackEncryptionBox(z, str, readUnsignedByte2, bArr2, i, i2, bArr);
                            trackFragment.trackEncryptionBox = trackEncryptionBox;
                            return;
                        }
                        return;
                    }
                    throw new ParserException("Entry count in sgpd != 1 (unsupported).");
                }
                return;
            }
            throw new ParserException("Entry count in sbgp != 1 (unsupported).");
        }
    }

    private static Pair<Long, ChunkIndex> parseSidx(ParsableByteArray parsableByteArray, long j) throws ParserException {
        long j2;
        long j3;
        ParsableByteArray parsableByteArray2 = parsableByteArray;
        parsableByteArray2.setPosition(8);
        int parseFullAtomVersion = Atom.parseFullAtomVersion(parsableByteArray.readInt());
        parsableByteArray2.skipBytes(4);
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        if (parseFullAtomVersion == 0) {
            j3 = parsableByteArray.readUnsignedInt();
            j2 = j + parsableByteArray.readUnsignedInt();
        } else {
            j3 = parsableByteArray.readUnsignedLongToLong();
            j2 = j + parsableByteArray.readUnsignedLongToLong();
        }
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(j3, 1000000, readUnsignedInt);
        parsableByteArray2.skipBytes(2);
        int readUnsignedShort = parsableByteArray.readUnsignedShort();
        int[] iArr = new int[readUnsignedShort];
        long[] jArr = new long[readUnsignedShort];
        long[] jArr2 = new long[readUnsignedShort];
        long[] jArr3 = new long[readUnsignedShort];
        long j4 = j3;
        long j5 = scaleLargeTimestamp;
        int i = 0;
        while (i < readUnsignedShort) {
            int readInt = parsableByteArray.readInt();
            if ((readInt & Integer.MIN_VALUE) == 0) {
                long readUnsignedInt2 = parsableByteArray.readUnsignedInt();
                iArr[i] = readInt & Integer.MAX_VALUE;
                jArr[i] = j2;
                jArr3[i] = j5;
                j4 += readUnsignedInt2;
                long[] jArr4 = jArr2;
                long[] jArr5 = jArr3;
                int i2 = readUnsignedShort;
                int[] iArr2 = iArr;
                long[] jArr6 = jArr;
                j5 = Util.scaleLargeTimestamp(j4, 1000000, readUnsignedInt);
                jArr4[i] = j5 - jArr5[i];
                parsableByteArray2.skipBytes(4);
                j2 += (long) iArr2[i];
                i++;
                iArr = iArr2;
                jArr3 = jArr5;
                jArr2 = jArr4;
                jArr = jArr6;
                readUnsignedShort = i2;
            } else {
                throw new ParserException("Unhandled indirect reference");
            }
        }
        return Pair.create(Long.valueOf(scaleLargeTimestamp), new ChunkIndex(iArr, jArr, jArr2, jArr3));
    }

    private void readEncryptionData(ExtractorInput extractorInput) throws IOException, InterruptedException {
        int size = this.trackBundles.size();
        TrackBundle trackBundle = null;
        long j = Long.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            TrackFragment trackFragment = ((TrackBundle) this.trackBundles.valueAt(i)).fragment;
            if (trackFragment.sampleEncryptionDataNeedsFill && trackFragment.auxiliaryDataPosition < j) {
                long j2 = trackFragment.auxiliaryDataPosition;
                trackBundle = (TrackBundle) this.trackBundles.valueAt(i);
                j = j2;
            }
        }
        if (trackBundle == null) {
            this.parserState = 3;
            return;
        }
        int position = (int) (j - extractorInput.getPosition());
        if (position >= 0) {
            extractorInput.skipFully(position);
            trackBundle.fragment.fillEncryptionData(extractorInput);
            return;
        }
        throw new ParserException("Offset to encryption data was negative.");
    }

    private boolean readSample(ExtractorInput extractorInput) throws IOException, InterruptedException {
        CryptoData cryptoData;
        boolean z;
        int i;
        int i2;
        ExtractorInput extractorInput2 = extractorInput;
        int i3 = 4;
        int i4 = 1;
        int i5 = 0;
        if (this.parserState == 3) {
            if (this.currentTrackBundle == null) {
                TrackBundle nextFragmentRun = getNextFragmentRun(this.trackBundles);
                if (nextFragmentRun == null) {
                    int position = (int) (this.endOfMdatPosition - extractorInput.getPosition());
                    if (position >= 0) {
                        extractorInput2.skipFully(position);
                        enterReadingAtomHeaderState();
                        return false;
                    }
                    throw new ParserException("Offset to end of mdat was negative.");
                }
                int position2 = (int) (nextFragmentRun.fragment.trunDataPosition[nextFragmentRun.currentTrackRunIndex] - extractorInput.getPosition());
                if (position2 < 0) {
                    Log.w(TAG, "Ignoring negative offset to sample data.");
                    position2 = 0;
                }
                extractorInput2.skipFully(position2);
                this.currentTrackBundle = nextFragmentRun;
            }
            this.sampleSize = this.currentTrackBundle.fragment.sampleSizeTable[this.currentTrackBundle.currentSampleIndex];
            if (this.currentTrackBundle.currentSampleIndex < this.currentTrackBundle.firstSampleToOutputIndex) {
                extractorInput2.skipFully(this.sampleSize);
                this.currentTrackBundle.skipSampleEncryptionData();
                if (!this.currentTrackBundle.next()) {
                    this.currentTrackBundle = null;
                }
                this.parserState = 3;
                return true;
            }
            if (this.currentTrackBundle.track.sampleTransformation == 1) {
                this.sampleSize -= 8;
                extractorInput2.skipFully(8);
            }
            this.sampleBytesWritten = this.currentTrackBundle.outputSampleEncryptionData();
            this.sampleSize += this.sampleBytesWritten;
            this.parserState = 4;
            this.sampleCurrentNalBytesRemaining = 0;
        }
        TrackFragment trackFragment = this.currentTrackBundle.fragment;
        Track track = this.currentTrackBundle.track;
        TrackOutput trackOutput = this.currentTrackBundle.output;
        int i6 = this.currentTrackBundle.currentSampleIndex;
        long samplePresentationTime = trackFragment.getSamplePresentationTime(i6) * 1000;
        TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
        long adjustSampleTimestamp = timestampAdjuster2 != null ? timestampAdjuster2.adjustSampleTimestamp(samplePresentationTime) : samplePresentationTime;
        if (track.nalUnitLengthFieldLength == 0) {
            while (true) {
                int i7 = this.sampleBytesWritten;
                int i8 = this.sampleSize;
                if (i7 >= i8) {
                    break;
                }
                this.sampleBytesWritten += trackOutput.sampleData(extractorInput2, i8 - i7, false);
            }
        } else {
            byte[] bArr = this.nalPrefix.data;
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            int i9 = track.nalUnitLengthFieldLength + 1;
            int i10 = 4 - track.nalUnitLengthFieldLength;
            while (this.sampleBytesWritten < this.sampleSize) {
                int i11 = this.sampleCurrentNalBytesRemaining;
                if (i11 == 0) {
                    extractorInput2.readFully(bArr, i10, i9);
                    this.nalPrefix.setPosition(i5);
                    this.sampleCurrentNalBytesRemaining = this.nalPrefix.readUnsignedIntToInt() - i4;
                    this.nalStartCode.setPosition(i5);
                    trackOutput.sampleData(this.nalStartCode, i3);
                    trackOutput.sampleData(this.nalPrefix, i4);
                    this.processSeiNalUnitPayload = this.cea608TrackOutputs.length > 0 && NalUnitUtil.isNalUnitSei(track.format.sampleMimeType, bArr[i3]);
                    this.sampleBytesWritten += 5;
                    this.sampleSize += i10;
                } else {
                    if (this.processSeiNalUnitPayload) {
                        this.nalBuffer.reset(i11);
                        extractorInput2.readFully(this.nalBuffer.data, i5, this.sampleCurrentNalBytesRemaining);
                        trackOutput.sampleData(this.nalBuffer, this.sampleCurrentNalBytesRemaining);
                        i2 = this.sampleCurrentNalBytesRemaining;
                        int unescapeStream = NalUnitUtil.unescapeStream(this.nalBuffer.data, this.nalBuffer.limit());
                        this.nalBuffer.setPosition(MimeTypes.VIDEO_H265.equals(track.format.sampleMimeType) ? 1 : 0);
                        this.nalBuffer.setLimit(unescapeStream);
                        CeaUtil.consume(adjustSampleTimestamp, this.nalBuffer, this.cea608TrackOutputs);
                    } else {
                        i2 = trackOutput.sampleData(extractorInput2, i11, false);
                    }
                    this.sampleBytesWritten += i2;
                    this.sampleCurrentNalBytesRemaining -= i2;
                    i3 = 4;
                    i4 = 1;
                    i5 = 0;
                }
            }
        }
        boolean z2 = trackFragment.sampleIsSyncFrameTable[i6];
        TrackEncryptionBox access$100 = this.currentTrackBundle.getEncryptionBoxIfEncrypted();
        if (access$100 != null) {
            z = z2 | true;
            cryptoData = access$100.cryptoData;
        } else {
            z = z2;
            cryptoData = null;
        }
        long j = adjustSampleTimestamp;
        trackOutput.sampleMetadata(adjustSampleTimestamp, z ? 1 : 0, this.sampleSize, 0, cryptoData);
        outputPendingMetadataSamples(j);
        if (!this.currentTrackBundle.next()) {
            this.currentTrackBundle = null;
            i = 3;
        } else {
            i = 3;
        }
        this.parserState = i;
        return true;
    }

    private void outputPendingMetadataSamples(long j) {
        while (!this.pendingMetadataSampleInfos.isEmpty()) {
            MetadataSampleInfo metadataSampleInfo = (MetadataSampleInfo) this.pendingMetadataSampleInfos.removeFirst();
            this.pendingMetadataSampleBytes -= metadataSampleInfo.size;
            long j2 = metadataSampleInfo.presentationTimeDeltaUs + j;
            TimestampAdjuster timestampAdjuster2 = this.timestampAdjuster;
            if (timestampAdjuster2 != null) {
                j2 = timestampAdjuster2.adjustSampleTimestamp(j2);
            }
            for (TrackOutput sampleMetadata : this.emsgTrackOutputs) {
                sampleMetadata.sampleMetadata(j2, 1, metadataSampleInfo.size, this.pendingMetadataSampleBytes, null);
            }
        }
    }

    private static TrackBundle getNextFragmentRun(SparseArray<TrackBundle> sparseArray) {
        int size = sparseArray.size();
        TrackBundle trackBundle = null;
        long j = Long.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            TrackBundle trackBundle2 = (TrackBundle) sparseArray.valueAt(i);
            if (trackBundle2.currentTrackRunIndex != trackBundle2.fragment.trunCount) {
                long j2 = trackBundle2.fragment.trunDataPosition[trackBundle2.currentTrackRunIndex];
                if (j2 < j) {
                    trackBundle = trackBundle2;
                    j = j2;
                }
            }
        }
        return trackBundle;
    }

    private static DrmInitData getDrmInitDataFromAtoms(List<LeafAtom> list) {
        int size = list.size();
        ArrayList arrayList = null;
        for (int i = 0; i < size; i++) {
            LeafAtom leafAtom = (LeafAtom) list.get(i);
            if (leafAtom.type == Atom.TYPE_pssh) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                byte[] bArr = leafAtom.data.data;
                UUID parseUuid = PsshAtomUtil.parseUuid(bArr);
                if (parseUuid == null) {
                    Log.w(TAG, "Skipped pssh atom (failed to extract uuid)");
                } else {
                    arrayList.add(new SchemeData(parseUuid, MimeTypes.VIDEO_MP4, bArr));
                }
            }
        }
        if (arrayList == null) {
            return null;
        }
        return new DrmInitData((List<SchemeData>) arrayList);
    }

    private static boolean shouldParseLeafAtom(int i) {
        return i == Atom.TYPE_hdlr || i == Atom.TYPE_mdhd || i == Atom.TYPE_mvhd || i == Atom.TYPE_sidx || i == Atom.TYPE_stsd || i == Atom.TYPE_tfdt || i == Atom.TYPE_tfhd || i == Atom.TYPE_tkhd || i == Atom.TYPE_trex || i == Atom.TYPE_trun || i == Atom.TYPE_pssh || i == Atom.TYPE_saiz || i == Atom.TYPE_saio || i == Atom.TYPE_senc || i == Atom.TYPE_uuid || i == Atom.TYPE_sbgp || i == Atom.TYPE_sgpd || i == Atom.TYPE_elst || i == Atom.TYPE_mehd || i == Atom.TYPE_emsg;
    }

    private static boolean shouldParseContainerAtom(int i) {
        return i == Atom.TYPE_moov || i == Atom.TYPE_trak || i == Atom.TYPE_mdia || i == Atom.TYPE_minf || i == Atom.TYPE_stbl || i == Atom.TYPE_moof || i == Atom.TYPE_traf || i == Atom.TYPE_mvex || i == Atom.TYPE_edts;
    }
}
