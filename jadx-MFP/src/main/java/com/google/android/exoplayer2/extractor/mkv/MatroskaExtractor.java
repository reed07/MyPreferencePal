package com.google.android.exoplayer2.extractor.mkv;

import android.support.annotation.Nullable;
import android.util.Pair;
import android.util.SparseArray;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.audio.Ac3Util;
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
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.LongArray;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.NalUnitUtil;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public final class MatroskaExtractor implements Extractor {
    private static final int BLOCK_STATE_DATA = 2;
    private static final int BLOCK_STATE_HEADER = 1;
    private static final int BLOCK_STATE_START = 0;
    private static final String CODEC_ID_AAC = "A_AAC";
    private static final String CODEC_ID_AC3 = "A_AC3";
    private static final String CODEC_ID_ACM = "A_MS/ACM";
    private static final String CODEC_ID_ASS = "S_TEXT/ASS";
    private static final String CODEC_ID_DTS = "A_DTS";
    private static final String CODEC_ID_DTS_EXPRESS = "A_DTS/EXPRESS";
    private static final String CODEC_ID_DTS_LOSSLESS = "A_DTS/LOSSLESS";
    private static final String CODEC_ID_DVBSUB = "S_DVBSUB";
    private static final String CODEC_ID_E_AC3 = "A_EAC3";
    private static final String CODEC_ID_FLAC = "A_FLAC";
    private static final String CODEC_ID_FOURCC = "V_MS/VFW/FOURCC";
    private static final String CODEC_ID_H264 = "V_MPEG4/ISO/AVC";
    private static final String CODEC_ID_H265 = "V_MPEGH/ISO/HEVC";
    private static final String CODEC_ID_MP2 = "A_MPEG/L2";
    private static final String CODEC_ID_MP3 = "A_MPEG/L3";
    private static final String CODEC_ID_MPEG2 = "V_MPEG2";
    private static final String CODEC_ID_MPEG4_AP = "V_MPEG4/ISO/AP";
    private static final String CODEC_ID_MPEG4_ASP = "V_MPEG4/ISO/ASP";
    private static final String CODEC_ID_MPEG4_SP = "V_MPEG4/ISO/SP";
    private static final String CODEC_ID_OPUS = "A_OPUS";
    private static final String CODEC_ID_PCM_INT_LIT = "A_PCM/INT/LIT";
    private static final String CODEC_ID_PGS = "S_HDMV/PGS";
    private static final String CODEC_ID_SUBRIP = "S_TEXT/UTF8";
    private static final String CODEC_ID_THEORA = "V_THEORA";
    private static final String CODEC_ID_TRUEHD = "A_TRUEHD";
    private static final String CODEC_ID_VOBSUB = "S_VOBSUB";
    private static final String CODEC_ID_VORBIS = "A_VORBIS";
    private static final String CODEC_ID_VP8 = "V_VP8";
    private static final String CODEC_ID_VP9 = "V_VP9";
    private static final String DOC_TYPE_MATROSKA = "matroska";
    private static final String DOC_TYPE_WEBM = "webm";
    private static final int ENCRYPTION_IV_SIZE = 8;
    public static final ExtractorsFactory FACTORY = $$Lambda$MatroskaExtractor$jNXW0tyYIOPE6N2jicocV6rRvBs.INSTANCE;
    public static final int FLAG_DISABLE_SEEK_FOR_CUES = 1;
    private static final int FOURCC_COMPRESSION_DIVX = 1482049860;
    private static final int FOURCC_COMPRESSION_VC1 = 826496599;
    private static final int ID_AUDIO = 225;
    private static final int ID_AUDIO_BIT_DEPTH = 25188;
    private static final int ID_BLOCK = 161;
    private static final int ID_BLOCK_DURATION = 155;
    private static final int ID_BLOCK_GROUP = 160;
    private static final int ID_CHANNELS = 159;
    private static final int ID_CLUSTER = 524531317;
    private static final int ID_CODEC_DELAY = 22186;
    private static final int ID_CODEC_ID = 134;
    private static final int ID_CODEC_PRIVATE = 25506;
    private static final int ID_COLOUR = 21936;
    private static final int ID_COLOUR_PRIMARIES = 21947;
    private static final int ID_COLOUR_RANGE = 21945;
    private static final int ID_COLOUR_TRANSFER = 21946;
    private static final int ID_CONTENT_COMPRESSION = 20532;
    private static final int ID_CONTENT_COMPRESSION_ALGORITHM = 16980;
    private static final int ID_CONTENT_COMPRESSION_SETTINGS = 16981;
    private static final int ID_CONTENT_ENCODING = 25152;
    private static final int ID_CONTENT_ENCODINGS = 28032;
    private static final int ID_CONTENT_ENCODING_ORDER = 20529;
    private static final int ID_CONTENT_ENCODING_SCOPE = 20530;
    private static final int ID_CONTENT_ENCRYPTION = 20533;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS = 18407;
    private static final int ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE = 18408;
    private static final int ID_CONTENT_ENCRYPTION_ALGORITHM = 18401;
    private static final int ID_CONTENT_ENCRYPTION_KEY_ID = 18402;
    private static final int ID_CUES = 475249515;
    private static final int ID_CUE_CLUSTER_POSITION = 241;
    private static final int ID_CUE_POINT = 187;
    private static final int ID_CUE_TIME = 179;
    private static final int ID_CUE_TRACK_POSITIONS = 183;
    private static final int ID_DEFAULT_DURATION = 2352003;
    private static final int ID_DISPLAY_HEIGHT = 21690;
    private static final int ID_DISPLAY_UNIT = 21682;
    private static final int ID_DISPLAY_WIDTH = 21680;
    private static final int ID_DOC_TYPE = 17026;
    private static final int ID_DOC_TYPE_READ_VERSION = 17029;
    private static final int ID_DURATION = 17545;
    private static final int ID_EBML = 440786851;
    private static final int ID_EBML_READ_VERSION = 17143;
    private static final int ID_FLAG_DEFAULT = 136;
    private static final int ID_FLAG_FORCED = 21930;
    private static final int ID_INFO = 357149030;
    private static final int ID_LANGUAGE = 2274716;
    private static final int ID_LUMNINANCE_MAX = 21977;
    private static final int ID_LUMNINANCE_MIN = 21978;
    private static final int ID_MASTERING_METADATA = 21968;
    private static final int ID_MAX_CLL = 21948;
    private static final int ID_MAX_FALL = 21949;
    private static final int ID_NAME = 21358;
    private static final int ID_PIXEL_HEIGHT = 186;
    private static final int ID_PIXEL_WIDTH = 176;
    private static final int ID_PRIMARY_B_CHROMATICITY_X = 21973;
    private static final int ID_PRIMARY_B_CHROMATICITY_Y = 21974;
    private static final int ID_PRIMARY_G_CHROMATICITY_X = 21971;
    private static final int ID_PRIMARY_G_CHROMATICITY_Y = 21972;
    private static final int ID_PRIMARY_R_CHROMATICITY_X = 21969;
    private static final int ID_PRIMARY_R_CHROMATICITY_Y = 21970;
    private static final int ID_PROJECTION = 30320;
    private static final int ID_PROJECTION_POSE_PITCH = 30324;
    private static final int ID_PROJECTION_POSE_ROLL = 30325;
    private static final int ID_PROJECTION_POSE_YAW = 30323;
    private static final int ID_PROJECTION_PRIVATE = 30322;
    private static final int ID_PROJECTION_TYPE = 30321;
    private static final int ID_REFERENCE_BLOCK = 251;
    private static final int ID_SAMPLING_FREQUENCY = 181;
    private static final int ID_SEEK = 19899;
    private static final int ID_SEEK_HEAD = 290298740;
    private static final int ID_SEEK_ID = 21419;
    private static final int ID_SEEK_POSITION = 21420;
    private static final int ID_SEEK_PRE_ROLL = 22203;
    private static final int ID_SEGMENT = 408125543;
    private static final int ID_SEGMENT_INFO = 357149030;
    private static final int ID_SIMPLE_BLOCK = 163;
    private static final int ID_STEREO_MODE = 21432;
    private static final int ID_TIMECODE_SCALE = 2807729;
    private static final int ID_TIME_CODE = 231;
    private static final int ID_TRACKS = 374648427;
    private static final int ID_TRACK_ENTRY = 174;
    private static final int ID_TRACK_NUMBER = 215;
    private static final int ID_TRACK_TYPE = 131;
    private static final int ID_VIDEO = 224;
    private static final int ID_WHITE_POINT_CHROMATICITY_X = 21975;
    private static final int ID_WHITE_POINT_CHROMATICITY_Y = 21976;
    private static final int LACING_EBML = 3;
    private static final int LACING_FIXED_SIZE = 2;
    private static final int LACING_NONE = 0;
    private static final int LACING_XIPH = 1;
    private static final int OPUS_MAX_INPUT_SIZE = 5760;
    /* access modifiers changed from: private */
    public static final byte[] SSA_DIALOGUE_FORMAT = Util.getUtf8Bytes("Format: Start, End, ReadOrder, Layer, Style, Name, MarginL, MarginR, MarginV, Effect, Text");
    private static final byte[] SSA_PREFIX = {68, 105, 97, 108, 111, 103, 117, 101, 58, 32, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 58, 48, 48, 58, 48, 48, 58, 48, 48, 44};
    private static final int SSA_PREFIX_END_TIMECODE_OFFSET = 21;
    private static final byte[] SSA_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final String SSA_TIMECODE_FORMAT = "%01d:%02d:%02d:%02d";
    private static final long SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR = 10000;
    private static final byte[] SUBRIP_PREFIX = {Framer.STDOUT_FRAME_PREFIX, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, Framer.STDIN_FRAME_PREFIX, Framer.STDIN_FRAME_PREFIX, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final int SUBRIP_PREFIX_END_TIMECODE_OFFSET = 19;
    private static final byte[] SUBRIP_TIMECODE_EMPTY = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    private static final String SUBRIP_TIMECODE_FORMAT = "%02d:%02d:%02d,%03d";
    private static final long SUBRIP_TIMECODE_LAST_VALUE_SCALING_FACTOR = 1000;
    private static final String TAG = "MatroskaExtractor";
    private static final int TRACK_TYPE_AUDIO = 2;
    private static final int UNSET_ENTRY_ID = -1;
    private static final int VORBIS_MAX_INPUT_SIZE = 8192;
    private static final int WAVE_FORMAT_EXTENSIBLE = 65534;
    private static final int WAVE_FORMAT_PCM = 1;
    private static final int WAVE_FORMAT_SIZE = 18;
    /* access modifiers changed from: private */
    public static final UUID WAVE_SUBFORMAT_PCM = new UUID(72057594037932032L, -9223371306706625679L);
    private long blockDurationUs;
    private int blockFlags;
    private int blockLacingSampleCount;
    private int blockLacingSampleIndex;
    private int[] blockLacingSampleSizes;
    private int blockState;
    private long blockTimeUs;
    private int blockTrackNumber;
    private int blockTrackNumberLength;
    private long clusterTimecodeUs;
    private LongArray cueClusterPositions;
    private LongArray cueTimesUs;
    private long cuesContentPosition;
    private Track currentTrack;
    private long durationTimecode;
    private long durationUs;
    private final ParsableByteArray encryptionInitializationVector;
    private final ParsableByteArray encryptionSubsampleData;
    private ByteBuffer encryptionSubsampleDataBuffer;
    private ExtractorOutput extractorOutput;
    private final ParsableByteArray nalLength;
    private final ParsableByteArray nalStartCode;
    private final EbmlReader reader;
    private int sampleBytesRead;
    private int sampleBytesWritten;
    private int sampleCurrentNalBytesRemaining;
    private boolean sampleEncodingHandled;
    private boolean sampleInitializationVectorRead;
    private int samplePartitionCount;
    private boolean samplePartitionCountRead;
    private boolean sampleRead;
    private boolean sampleSeenReferenceBlock;
    private byte sampleSignalByte;
    private boolean sampleSignalByteRead;
    private final ParsableByteArray sampleStrippedBytes;
    private final ParsableByteArray scratch;
    private int seekEntryId;
    private final ParsableByteArray seekEntryIdBytes;
    private long seekEntryPosition;
    private boolean seekForCues;
    private final boolean seekForCuesEnabled;
    private long seekPositionAfterBuildingCues;
    private boolean seenClusterPositionForCurrentCuePoint;
    private long segmentContentPosition;
    private long segmentContentSize;
    private boolean sentSeekMap;
    private final ParsableByteArray subtitleSample;
    private long timecodeScale;
    private final SparseArray<Track> tracks;
    private final VarintReader varintReader;
    private final ParsableByteArray vorbisNumPageSamples;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    private final class InnerEbmlReaderOutput implements EbmlReaderOutput {
        public int getElementType(int i) {
            switch (i) {
                case 131:
                case 136:
                case MatroskaExtractor.ID_BLOCK_DURATION /*155*/:
                case 159:
                case MatroskaExtractor.ID_PIXEL_WIDTH /*176*/:
                case 179:
                case 186:
                case MatroskaExtractor.ID_TRACK_NUMBER /*215*/:
                case MatroskaExtractor.ID_TIME_CODE /*231*/:
                case MatroskaExtractor.ID_CUE_CLUSTER_POSITION /*241*/:
                case MatroskaExtractor.ID_REFERENCE_BLOCK /*251*/:
                case MatroskaExtractor.ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
                case MatroskaExtractor.ID_DOC_TYPE_READ_VERSION /*17029*/:
                case MatroskaExtractor.ID_EBML_READ_VERSION /*17143*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
                case MatroskaExtractor.ID_CONTENT_ENCODING_ORDER /*20529*/:
                case MatroskaExtractor.ID_CONTENT_ENCODING_SCOPE /*20530*/:
                case MatroskaExtractor.ID_SEEK_POSITION /*21420*/:
                case MatroskaExtractor.ID_STEREO_MODE /*21432*/:
                case MatroskaExtractor.ID_DISPLAY_WIDTH /*21680*/:
                case MatroskaExtractor.ID_DISPLAY_UNIT /*21682*/:
                case MatroskaExtractor.ID_DISPLAY_HEIGHT /*21690*/:
                case MatroskaExtractor.ID_FLAG_FORCED /*21930*/:
                case MatroskaExtractor.ID_COLOUR_RANGE /*21945*/:
                case MatroskaExtractor.ID_COLOUR_TRANSFER /*21946*/:
                case MatroskaExtractor.ID_COLOUR_PRIMARIES /*21947*/:
                case MatroskaExtractor.ID_MAX_CLL /*21948*/:
                case MatroskaExtractor.ID_MAX_FALL /*21949*/:
                case MatroskaExtractor.ID_CODEC_DELAY /*22186*/:
                case MatroskaExtractor.ID_SEEK_PRE_ROLL /*22203*/:
                case MatroskaExtractor.ID_AUDIO_BIT_DEPTH /*25188*/:
                case MatroskaExtractor.ID_PROJECTION_TYPE /*30321*/:
                case MatroskaExtractor.ID_DEFAULT_DURATION /*2352003*/:
                case MatroskaExtractor.ID_TIMECODE_SCALE /*2807729*/:
                    return 2;
                case 134:
                case 17026:
                case MatroskaExtractor.ID_NAME /*21358*/:
                case MatroskaExtractor.ID_LANGUAGE /*2274716*/:
                    return 3;
                case MatroskaExtractor.ID_BLOCK_GROUP /*160*/:
                case 174:
                case 183:
                case 187:
                case 224:
                case MatroskaExtractor.ID_AUDIO /*225*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION_AES_SETTINGS /*18407*/:
                case MatroskaExtractor.ID_SEEK /*19899*/:
                case MatroskaExtractor.ID_CONTENT_COMPRESSION /*20532*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION /*20533*/:
                case MatroskaExtractor.ID_COLOUR /*21936*/:
                case MatroskaExtractor.ID_MASTERING_METADATA /*21968*/:
                case MatroskaExtractor.ID_CONTENT_ENCODING /*25152*/:
                case MatroskaExtractor.ID_CONTENT_ENCODINGS /*28032*/:
                case MatroskaExtractor.ID_PROJECTION /*30320*/:
                case MatroskaExtractor.ID_SEEK_HEAD /*290298740*/:
                case 357149030:
                case MatroskaExtractor.ID_TRACKS /*374648427*/:
                case MatroskaExtractor.ID_SEGMENT /*408125543*/:
                case MatroskaExtractor.ID_EBML /*440786851*/:
                case MatroskaExtractor.ID_CUES /*475249515*/:
                case MatroskaExtractor.ID_CLUSTER /*524531317*/:
                    return 1;
                case MatroskaExtractor.ID_BLOCK /*161*/:
                case MatroskaExtractor.ID_SIMPLE_BLOCK /*163*/:
                case MatroskaExtractor.ID_CONTENT_COMPRESSION_SETTINGS /*16981*/:
                case MatroskaExtractor.ID_CONTENT_ENCRYPTION_KEY_ID /*18402*/:
                case MatroskaExtractor.ID_SEEK_ID /*21419*/:
                case MatroskaExtractor.ID_CODEC_PRIVATE /*25506*/:
                case MatroskaExtractor.ID_PROJECTION_PRIVATE /*30322*/:
                    return 4;
                case MatroskaExtractor.ID_SAMPLING_FREQUENCY /*181*/:
                case MatroskaExtractor.ID_DURATION /*17545*/:
                case MatroskaExtractor.ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
                case MatroskaExtractor.ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
                case MatroskaExtractor.ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
                case MatroskaExtractor.ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
                case MatroskaExtractor.ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
                case MatroskaExtractor.ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
                case MatroskaExtractor.ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
                case MatroskaExtractor.ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
                case MatroskaExtractor.ID_LUMNINANCE_MAX /*21977*/:
                case MatroskaExtractor.ID_LUMNINANCE_MIN /*21978*/:
                case MatroskaExtractor.ID_PROJECTION_POSE_YAW /*30323*/:
                case MatroskaExtractor.ID_PROJECTION_POSE_PITCH /*30324*/:
                case MatroskaExtractor.ID_PROJECTION_POSE_ROLL /*30325*/:
                    return 5;
                default:
                    return 0;
            }
        }

        public boolean isLevel1Element(int i) {
            return i == 357149030 || i == MatroskaExtractor.ID_CLUSTER || i == MatroskaExtractor.ID_CUES || i == MatroskaExtractor.ID_TRACKS;
        }

        private InnerEbmlReaderOutput() {
        }

        public void startMasterElement(int i, long j, long j2) throws ParserException {
            MatroskaExtractor.this.startMasterElement(i, j, j2);
        }

        public void endMasterElement(int i) throws ParserException {
            MatroskaExtractor.this.endMasterElement(i);
        }

        public void integerElement(int i, long j) throws ParserException {
            MatroskaExtractor.this.integerElement(i, j);
        }

        public void floatElement(int i, double d) throws ParserException {
            MatroskaExtractor.this.floatElement(i, d);
        }

        public void stringElement(int i, String str) throws ParserException {
            MatroskaExtractor.this.stringElement(i, str);
        }

        public void binaryElement(int i, int i2, ExtractorInput extractorInput) throws IOException, InterruptedException {
            MatroskaExtractor.this.binaryElement(i, i2, extractorInput);
        }
    }

    private static final class Track {
        private static final int DEFAULT_MAX_CLL = 1000;
        private static final int DEFAULT_MAX_FALL = 200;
        private static final int DISPLAY_UNIT_PIXELS = 0;
        private static final int MAX_CHROMATICITY = 50000;
        public int audioBitDepth;
        public int channelCount;
        public long codecDelayNs;
        public String codecId;
        public byte[] codecPrivate;
        public int colorRange;
        public int colorSpace;
        public int colorTransfer;
        public CryptoData cryptoData;
        public int defaultSampleDurationNs;
        public int displayHeight;
        public int displayUnit;
        public int displayWidth;
        public DrmInitData drmInitData;
        public boolean flagDefault;
        public boolean flagForced;
        public boolean hasColorInfo;
        public boolean hasContentEncryption;
        public int height;
        /* access modifiers changed from: private */
        public String language;
        public int maxContentLuminance;
        public int maxFrameAverageLuminance;
        public float maxMasteringLuminance;
        public float minMasteringLuminance;
        public int nalUnitLengthFieldLength;
        public String name;
        public int number;
        public TrackOutput output;
        public float primaryBChromaticityX;
        public float primaryBChromaticityY;
        public float primaryGChromaticityX;
        public float primaryGChromaticityY;
        public float primaryRChromaticityX;
        public float primaryRChromaticityY;
        public byte[] projectionData;
        public float projectionPosePitch;
        public float projectionPoseRoll;
        public float projectionPoseYaw;
        public int projectionType;
        public int sampleRate;
        public byte[] sampleStrippedBytes;
        public long seekPreRollNs;
        public int stereoMode;
        @Nullable
        public TrueHdSampleRechunker trueHdSampleRechunker;
        public int type;
        public float whitePointChromaticityX;
        public float whitePointChromaticityY;
        public int width;

        private Track() {
            this.width = -1;
            this.height = -1;
            this.displayWidth = -1;
            this.displayHeight = -1;
            this.displayUnit = 0;
            this.projectionType = -1;
            this.projectionPoseYaw = BitmapDescriptorFactory.HUE_RED;
            this.projectionPosePitch = BitmapDescriptorFactory.HUE_RED;
            this.projectionPoseRoll = BitmapDescriptorFactory.HUE_RED;
            this.projectionData = null;
            this.stereoMode = -1;
            this.hasColorInfo = false;
            this.colorSpace = -1;
            this.colorTransfer = -1;
            this.colorRange = -1;
            this.maxContentLuminance = 1000;
            this.maxFrameAverageLuminance = 200;
            this.primaryRChromaticityX = -1.0f;
            this.primaryRChromaticityY = -1.0f;
            this.primaryGChromaticityX = -1.0f;
            this.primaryGChromaticityY = -1.0f;
            this.primaryBChromaticityX = -1.0f;
            this.primaryBChromaticityY = -1.0f;
            this.whitePointChromaticityX = -1.0f;
            this.whitePointChromaticityY = -1.0f;
            this.maxMasteringLuminance = -1.0f;
            this.minMasteringLuminance = -1.0f;
            this.channelCount = 1;
            this.audioBitDepth = -1;
            this.sampleRate = 8000;
            this.codecDelayNs = 0;
            this.seekPreRollNs = 0;
            this.flagDefault = true;
            this.language = "eng";
        }

        /* JADX WARNING: Removed duplicated region for block: B:159:0x03fd  */
        /* JADX WARNING: Removed duplicated region for block: B:160:0x040f  */
        /* JADX WARNING: Removed duplicated region for block: B:163:0x0421  */
        /* JADX WARNING: Removed duplicated region for block: B:164:0x0423  */
        /* JADX WARNING: Removed duplicated region for block: B:181:0x0466  */
        /* JADX WARNING: Removed duplicated region for block: B:182:0x0469  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput r27, int r28) throws com.google.android.exoplayer2.ParserException {
            /*
                r26 = this;
                r0 = r26
                java.lang.String r1 = r0.codecId
                int r2 = r1.hashCode()
                r3 = 4
                r4 = 8
                r5 = 1
                r6 = 2
                r7 = 0
                r8 = 3
                r9 = -1
                switch(r2) {
                    case -2095576542: goto L_0x0156;
                    case -2095575984: goto L_0x014c;
                    case -1985379776: goto L_0x0141;
                    case -1784763192: goto L_0x0136;
                    case -1730367663: goto L_0x012b;
                    case -1482641358: goto L_0x0120;
                    case -1482641357: goto L_0x0115;
                    case -1373388978: goto L_0x010a;
                    case -933872740: goto L_0x00ff;
                    case -538363189: goto L_0x00f4;
                    case -538363109: goto L_0x00e9;
                    case -425012669: goto L_0x00dd;
                    case -356037306: goto L_0x00d1;
                    case 62923557: goto L_0x00c5;
                    case 62923603: goto L_0x00b9;
                    case 62927045: goto L_0x00ad;
                    case 82338133: goto L_0x00a2;
                    case 82338134: goto L_0x0097;
                    case 99146302: goto L_0x008b;
                    case 444813526: goto L_0x007f;
                    case 542569478: goto L_0x0073;
                    case 725957860: goto L_0x0067;
                    case 738597099: goto L_0x005b;
                    case 855502857: goto L_0x0050;
                    case 1422270023: goto L_0x0044;
                    case 1809237540: goto L_0x0039;
                    case 1950749482: goto L_0x002d;
                    case 1950789798: goto L_0x0021;
                    case 1951062397: goto L_0x0015;
                    default: goto L_0x0013;
                }
            L_0x0013:
                goto L_0x0160
            L_0x0015:
                java.lang.String r2 = "A_OPUS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 11
                goto L_0x0161
            L_0x0021:
                java.lang.String r2 = "A_FLAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 21
                goto L_0x0161
            L_0x002d:
                java.lang.String r2 = "A_EAC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 16
                goto L_0x0161
            L_0x0039:
                java.lang.String r2 = "V_MPEG2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 2
                goto L_0x0161
            L_0x0044:
                java.lang.String r2 = "S_TEXT/UTF8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 24
                goto L_0x0161
            L_0x0050:
                java.lang.String r2 = "V_MPEGH/ISO/HEVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 7
                goto L_0x0161
            L_0x005b:
                java.lang.String r2 = "S_TEXT/ASS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 25
                goto L_0x0161
            L_0x0067:
                java.lang.String r2 = "A_PCM/INT/LIT"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 23
                goto L_0x0161
            L_0x0073:
                java.lang.String r2 = "A_DTS/EXPRESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 19
                goto L_0x0161
            L_0x007f:
                java.lang.String r2 = "V_THEORA"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 9
                goto L_0x0161
            L_0x008b:
                java.lang.String r2 = "S_HDMV/PGS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 27
                goto L_0x0161
            L_0x0097:
                java.lang.String r2 = "V_VP9"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 1
                goto L_0x0161
            L_0x00a2:
                java.lang.String r2 = "V_VP8"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 0
                goto L_0x0161
            L_0x00ad:
                java.lang.String r2 = "A_DTS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 18
                goto L_0x0161
            L_0x00b9:
                java.lang.String r2 = "A_AC3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 15
                goto L_0x0161
            L_0x00c5:
                java.lang.String r2 = "A_AAC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 12
                goto L_0x0161
            L_0x00d1:
                java.lang.String r2 = "A_DTS/LOSSLESS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 20
                goto L_0x0161
            L_0x00dd:
                java.lang.String r2 = "S_VOBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 26
                goto L_0x0161
            L_0x00e9:
                java.lang.String r2 = "V_MPEG4/ISO/AVC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 6
                goto L_0x0161
            L_0x00f4:
                java.lang.String r2 = "V_MPEG4/ISO/ASP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 4
                goto L_0x0161
            L_0x00ff:
                java.lang.String r2 = "S_DVBSUB"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 28
                goto L_0x0161
            L_0x010a:
                java.lang.String r2 = "V_MS/VFW/FOURCC"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 8
                goto L_0x0161
            L_0x0115:
                java.lang.String r2 = "A_MPEG/L3"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 14
                goto L_0x0161
            L_0x0120:
                java.lang.String r2 = "A_MPEG/L2"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 13
                goto L_0x0161
            L_0x012b:
                java.lang.String r2 = "A_VORBIS"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 10
                goto L_0x0161
            L_0x0136:
                java.lang.String r2 = "A_TRUEHD"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 17
                goto L_0x0161
            L_0x0141:
                java.lang.String r2 = "A_MS/ACM"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 22
                goto L_0x0161
            L_0x014c:
                java.lang.String r2 = "V_MPEG4/ISO/SP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 3
                goto L_0x0161
            L_0x0156:
                java.lang.String r2 = "V_MPEG4/ISO/AP"
                boolean r1 = r1.equals(r2)
                if (r1 == 0) goto L_0x0160
                r1 = 5
                goto L_0x0161
            L_0x0160:
                r1 = -1
            L_0x0161:
                r2 = 4096(0x1000, float:5.74E-42)
                r10 = 0
                switch(r1) {
                    case 0: goto L_0x038c;
                    case 1: goto L_0x0384;
                    case 2: goto L_0x037c;
                    case 3: goto L_0x036b;
                    case 4: goto L_0x036b;
                    case 5: goto L_0x036b;
                    case 6: goto L_0x0352;
                    case 7: goto L_0x0339;
                    case 8: goto L_0x031f;
                    case 9: goto L_0x0316;
                    case 10: goto L_0x0304;
                    case 11: goto L_0x02bc;
                    case 12: goto L_0x02ae;
                    case 13: goto L_0x02a4;
                    case 14: goto L_0x029a;
                    case 15: goto L_0x0291;
                    case 16: goto L_0x0288;
                    case 17: goto L_0x0278;
                    case 18: goto L_0x026f;
                    case 19: goto L_0x026f;
                    case 20: goto L_0x0266;
                    case 21: goto L_0x0258;
                    case 22: goto L_0x01f2;
                    case 23: goto L_0x01b8;
                    case 24: goto L_0x01af;
                    case 25: goto L_0x01a6;
                    case 26: goto L_0x0198;
                    case 27: goto L_0x018f;
                    case 28: goto L_0x016f;
                    default: goto L_0x0167;
                }
            L_0x0167:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Unrecognized codec identifier."
                r1.<init>(r2)
                throw r1
            L_0x016f:
                java.lang.String r1 = "application/dvbsubs"
                byte[] r2 = new byte[r3]
                byte[] r3 = r0.codecPrivate
                byte r4 = r3[r7]
                r2[r7] = r4
                byte r4 = r3[r5]
                r2[r5] = r4
                byte r4 = r3[r6]
                r2[r6] = r4
                byte r3 = r3[r8]
                r2[r8] = r3
                java.util.List r2 = java.util.Collections.singletonList(r2)
                r12 = r1
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x018f:
                java.lang.String r1 = "application/pgs"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x0198:
                java.lang.String r1 = "application/vobsub"
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
                r12 = r1
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x01a6:
                java.lang.String r1 = "text/x-ssa"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x01af:
                java.lang.String r1 = "application/x-subrip"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x01b8:
                java.lang.String r1 = "audio/raw"
                int r2 = r0.audioBitDepth
                int r2 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r2)
                if (r2 != 0) goto L_0x01eb
                java.lang.String r1 = "audio/x-unknown"
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Unsupported PCM bit depth: "
                r3.append(r4)
                int r4 = r0.audioBitDepth
                r3.append(r4)
                java.lang.String r4 = ". Setting mimeType to "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                com.google.android.exoplayer2.util.Log.w(r2, r3)
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x01eb:
                r12 = r1
                r18 = r2
                r2 = r10
                r15 = -1
                goto L_0x0393
            L_0x01f2:
                java.lang.String r1 = "audio/raw"
                com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r2.<init>(r3)
                boolean r2 = parseMsAcmCodecPrivate(r2)
                if (r2 == 0) goto L_0x0239
                int r2 = r0.audioBitDepth
                int r2 = com.google.android.exoplayer2.util.Util.getPcmEncoding(r2)
                if (r2 != 0) goto L_0x0232
                java.lang.String r1 = "audio/x-unknown"
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Unsupported PCM bit depth: "
                r3.append(r4)
                int r4 = r0.audioBitDepth
                r3.append(r4)
                java.lang.String r4 = ". Setting mimeType to "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                com.google.android.exoplayer2.util.Log.w(r2, r3)
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x0232:
                r12 = r1
                r18 = r2
                r2 = r10
                r15 = -1
                goto L_0x0393
            L_0x0239:
                java.lang.String r1 = "audio/x-unknown"
                java.lang.String r2 = "MatroskaExtractor"
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r4 = "Non-PCM MS/ACM is unsupported. Setting mimeType to "
                r3.append(r4)
                r3.append(r1)
                java.lang.String r3 = r3.toString()
                com.google.android.exoplayer2.util.Log.w(r2, r3)
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x0258:
                java.lang.String r1 = "audio/flac"
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
                r12 = r1
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x0266:
                java.lang.String r1 = "audio/vnd.dts.hd"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x026f:
                java.lang.String r1 = "audio/vnd.dts"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x0278:
                java.lang.String r1 = "audio/true-hd"
                com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker r2 = new com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$TrueHdSampleRechunker
                r2.<init>()
                r0.trueHdSampleRechunker = r2
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x0288:
                java.lang.String r1 = "audio/eac3"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x0291:
                java.lang.String r1 = "audio/ac3"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x029a:
                java.lang.String r1 = "audio/mpeg"
                r12 = r1
                r2 = r10
                r15 = 4096(0x1000, float:5.74E-42)
                r18 = -1
                goto L_0x0393
            L_0x02a4:
                java.lang.String r1 = "audio/mpeg-L2"
                r12 = r1
                r2 = r10
                r15 = 4096(0x1000, float:5.74E-42)
                r18 = -1
                goto L_0x0393
            L_0x02ae:
                java.lang.String r1 = "audio/mp4a-latm"
                byte[] r2 = r0.codecPrivate
                java.util.List r2 = java.util.Collections.singletonList(r2)
                r12 = r1
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x02bc:
                java.lang.String r1 = "audio/opus"
                r2 = 5760(0x1680, float:8.071E-42)
                java.util.ArrayList r3 = new java.util.ArrayList
                r3.<init>(r8)
                byte[] r11 = r0.codecPrivate
                r3.add(r11)
                java.nio.ByteBuffer r11 = java.nio.ByteBuffer.allocate(r4)
                java.nio.ByteOrder r12 = java.nio.ByteOrder.nativeOrder()
                java.nio.ByteBuffer r11 = r11.order(r12)
                long r12 = r0.codecDelayNs
                java.nio.ByteBuffer r11 = r11.putLong(r12)
                byte[] r11 = r11.array()
                r3.add(r11)
                java.nio.ByteBuffer r4 = java.nio.ByteBuffer.allocate(r4)
                java.nio.ByteOrder r11 = java.nio.ByteOrder.nativeOrder()
                java.nio.ByteBuffer r4 = r4.order(r11)
                long r11 = r0.seekPreRollNs
                java.nio.ByteBuffer r4 = r4.putLong(r11)
                byte[] r4 = r4.array()
                r3.add(r4)
                r12 = r1
                r2 = r3
                r15 = 5760(0x1680, float:8.071E-42)
                r18 = -1
                goto L_0x0393
            L_0x0304:
                java.lang.String r1 = "audio/vorbis"
                r2 = 8192(0x2000, float:1.14794E-41)
                byte[] r3 = r0.codecPrivate
                java.util.List r3 = parseVorbisCodecPrivate(r3)
                r12 = r1
                r2 = r3
                r15 = 8192(0x2000, float:1.14794E-41)
                r18 = -1
                goto L_0x0393
            L_0x0316:
                java.lang.String r1 = "video/x-unknown"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x031f:
                com.google.android.exoplayer2.util.ParsableByteArray r1 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r2 = r0.codecPrivate
                r1.<init>(r2)
                android.util.Pair r1 = parseFourCcPrivate(r1)
                java.lang.Object r2 = r1.first
                java.lang.String r2 = (java.lang.String) r2
                java.lang.Object r1 = r1.second
                java.util.List r1 = (java.util.List) r1
                r12 = r2
                r15 = -1
                r18 = -1
                r2 = r1
                goto L_0x0393
            L_0x0339:
                java.lang.String r1 = "video/hevc"
                com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r2.<init>(r3)
                com.google.android.exoplayer2.video.HevcConfig r2 = com.google.android.exoplayer2.video.HevcConfig.parse(r2)
                java.util.List<byte[]> r3 = r2.initializationData
                int r2 = r2.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r2
                r12 = r1
                r2 = r3
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x0352:
                java.lang.String r1 = "video/avc"
                com.google.android.exoplayer2.util.ParsableByteArray r2 = new com.google.android.exoplayer2.util.ParsableByteArray
                byte[] r3 = r0.codecPrivate
                r2.<init>(r3)
                com.google.android.exoplayer2.video.AvcConfig r2 = com.google.android.exoplayer2.video.AvcConfig.parse(r2)
                java.util.List<byte[]> r3 = r2.initializationData
                int r2 = r2.nalUnitLengthFieldLength
                r0.nalUnitLengthFieldLength = r2
                r12 = r1
                r2 = r3
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x036b:
                java.lang.String r1 = "video/mp4v-es"
                byte[] r2 = r0.codecPrivate
                if (r2 != 0) goto L_0x0373
                r2 = r10
                goto L_0x0377
            L_0x0373:
                java.util.List r2 = java.util.Collections.singletonList(r2)
            L_0x0377:
                r12 = r1
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x037c:
                java.lang.String r1 = "video/mpeg2"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x0384:
                java.lang.String r1 = "video/x-vnd.on2.vp9"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
                goto L_0x0393
            L_0x038c:
                java.lang.String r1 = "video/x-vnd.on2.vp8"
                r12 = r1
                r2 = r10
                r15 = -1
                r18 = -1
            L_0x0393:
                boolean r1 = r0.flagDefault
                r1 = r1 | r7
                boolean r3 = r0.flagForced
                if (r3 == 0) goto L_0x039c
                r3 = 2
                goto L_0x039d
            L_0x039c:
                r3 = 0
            L_0x039d:
                r1 = r1 | r3
                boolean r3 = com.google.android.exoplayer2.util.MimeTypes.isAudio(r12)
                if (r3 == 0) goto L_0x03c5
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                int r3 = r0.channelCount
                int r4 = r0.sampleRate
                com.google.android.exoplayer2.drm.DrmInitData r6 = r0.drmInitData
                java.lang.String r7 = r0.language
                r16 = r3
                r17 = r4
                r19 = r2
                r20 = r6
                r21 = r1
                r22 = r7
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createAudioSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22)
                r8 = 1
                goto L_0x0544
            L_0x03c5:
                boolean r3 = com.google.android.exoplayer2.util.MimeTypes.isVideo(r12)
                if (r3 == 0) goto L_0x04c2
                int r1 = r0.displayUnit
                if (r1 != 0) goto L_0x03df
                int r1 = r0.displayWidth
                if (r1 != r9) goto L_0x03d5
                int r1 = r0.width
            L_0x03d5:
                r0.displayWidth = r1
                int r1 = r0.displayHeight
                if (r1 != r9) goto L_0x03dd
                int r1 = r0.height
            L_0x03dd:
                r0.displayHeight = r1
            L_0x03df:
                r1 = -1082130432(0xffffffffbf800000, float:-1.0)
                int r3 = r0.displayWidth
                if (r3 == r9) goto L_0x03f7
                int r4 = r0.displayHeight
                if (r4 == r9) goto L_0x03f7
                int r1 = r0.height
                int r1 = r1 * r3
                float r1 = (float) r1
                int r3 = r0.width
                int r3 = r3 * r4
                float r3 = (float) r3
                float r1 = r1 / r3
                r21 = r1
                goto L_0x03f9
            L_0x03f7:
                r21 = -1082130432(0xffffffffbf800000, float:-1.0)
            L_0x03f9:
                boolean r1 = r0.hasColorInfo
                if (r1 == 0) goto L_0x040f
                byte[] r1 = r26.getHdrStaticInfo()
                com.google.android.exoplayer2.video.ColorInfo r10 = new com.google.android.exoplayer2.video.ColorInfo
                int r3 = r0.colorSpace
                int r4 = r0.colorRange
                int r5 = r0.colorTransfer
                r10.<init>(r3, r4, r5, r1)
                r24 = r10
                goto L_0x0411
            L_0x040f:
                r24 = r10
            L_0x0411:
                java.lang.String r1 = "htc_video_rotA-000"
                java.lang.String r3 = r0.name
                boolean r1 = r1.equals(r3)
                r3 = 270(0x10e, float:3.78E-43)
                r4 = 180(0xb4, float:2.52E-43)
                r5 = 90
                if (r1 == 0) goto L_0x0423
                r9 = 0
                goto L_0x0449
            L_0x0423:
                java.lang.String r1 = "htc_video_rotA-090"
                java.lang.String r8 = r0.name
                boolean r1 = r1.equals(r8)
                if (r1 == 0) goto L_0x0430
                r9 = 90
                goto L_0x0449
            L_0x0430:
                java.lang.String r1 = "htc_video_rotA-180"
                java.lang.String r8 = r0.name
                boolean r1 = r1.equals(r8)
                if (r1 == 0) goto L_0x043d
                r9 = 180(0xb4, float:2.52E-43)
                goto L_0x0449
            L_0x043d:
                java.lang.String r1 = "htc_video_rotA-270"
                java.lang.String r8 = r0.name
                boolean r1 = r1.equals(r8)
                if (r1 == 0) goto L_0x0449
                r9 = 270(0x10e, float:3.78E-43)
            L_0x0449:
                int r1 = r0.projectionType
                if (r1 != 0) goto L_0x049b
                float r1 = r0.projectionPoseYaw
                r8 = 0
                int r1 = java.lang.Float.compare(r1, r8)
                if (r1 != 0) goto L_0x049b
                float r1 = r0.projectionPosePitch
                int r1 = java.lang.Float.compare(r1, r8)
                if (r1 != 0) goto L_0x049b
                float r1 = r0.projectionPoseRoll
                int r1 = java.lang.Float.compare(r1, r8)
                if (r1 != 0) goto L_0x0469
                r20 = 0
                goto L_0x049d
            L_0x0469:
                float r1 = r0.projectionPosePitch
                r7 = 1119092736(0x42b40000, float:90.0)
                int r1 = java.lang.Float.compare(r1, r7)
                if (r1 != 0) goto L_0x0476
                r20 = 90
                goto L_0x049d
            L_0x0476:
                float r1 = r0.projectionPosePitch
                r5 = -1020002304(0xffffffffc3340000, float:-180.0)
                int r1 = java.lang.Float.compare(r1, r5)
                if (r1 == 0) goto L_0x0498
                float r1 = r0.projectionPosePitch
                r5 = 1127481344(0x43340000, float:180.0)
                int r1 = java.lang.Float.compare(r1, r5)
                if (r1 != 0) goto L_0x048b
                goto L_0x0498
            L_0x048b:
                float r1 = r0.projectionPosePitch
                r4 = -1028390912(0xffffffffc2b40000, float:-90.0)
                int r1 = java.lang.Float.compare(r1, r4)
                if (r1 != 0) goto L_0x049b
                r20 = 270(0x10e, float:3.78E-43)
                goto L_0x049d
            L_0x0498:
                r20 = 180(0xb4, float:2.52E-43)
                goto L_0x049d
            L_0x049b:
                r20 = r9
            L_0x049d:
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                int r1 = r0.width
                int r3 = r0.height
                r18 = -1082130432(0xffffffffbf800000, float:-1.0)
                byte[] r4 = r0.projectionData
                int r5 = r0.stereoMode
                com.google.android.exoplayer2.drm.DrmInitData r7 = r0.drmInitData
                r16 = r1
                r17 = r3
                r19 = r2
                r22 = r4
                r23 = r5
                r25 = r7
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createVideoSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
                r8 = 2
                goto L_0x0544
            L_0x04c2:
                java.lang.String r3 = "application/x-subrip"
                boolean r3 = r3.equals(r12)
                if (r3 == 0) goto L_0x04d8
                java.lang.String r2 = java.lang.Integer.toString(r28)
                java.lang.String r3 = r0.language
                com.google.android.exoplayer2.drm.DrmInitData r4 = r0.drmInitData
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(r2, r12, r1, r3, r4)
                goto L_0x0544
            L_0x04d8:
                java.lang.String r3 = "text/x-ssa"
                boolean r3 = r3.equals(r12)
                if (r3 == 0) goto L_0x050e
                java.util.ArrayList r2 = new java.util.ArrayList
                r2.<init>(r6)
                byte[] r3 = com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.SSA_DIALOGUE_FORMAT
                r2.add(r3)
                byte[] r3 = r0.codecPrivate
                r2.add(r3)
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                java.lang.String r3 = r0.language
                r17 = -1
                com.google.android.exoplayer2.drm.DrmInitData r4 = r0.drmInitData
                r19 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r15 = r1
                r16 = r3
                r18 = r4
                r21 = r2
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createTextSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18, r19, r21)
                goto L_0x0544
            L_0x050e:
                java.lang.String r3 = "application/vobsub"
                boolean r3 = r3.equals(r12)
                if (r3 != 0) goto L_0x052f
                java.lang.String r3 = "application/pgs"
                boolean r3 = r3.equals(r12)
                if (r3 != 0) goto L_0x052f
                java.lang.String r3 = "application/dvbsubs"
                boolean r3 = r3.equals(r12)
                if (r3 == 0) goto L_0x0527
                goto L_0x052f
            L_0x0527:
                com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
                java.lang.String r2 = "Unexpected MIME type."
                r1.<init>(r2)
                throw r1
            L_0x052f:
                java.lang.String r11 = java.lang.Integer.toString(r28)
                r13 = 0
                r14 = -1
                java.lang.String r3 = r0.language
                com.google.android.exoplayer2.drm.DrmInitData r4 = r0.drmInitData
                r15 = r1
                r16 = r2
                r17 = r3
                r18 = r4
                com.google.android.exoplayer2.Format r1 = com.google.android.exoplayer2.Format.createImageSampleFormat(r11, r12, r13, r14, r15, r16, r17, r18)
            L_0x0544:
                int r2 = r0.number
                r3 = r27
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r3.track(r2, r8)
                r0.output = r2
                com.google.android.exoplayer2.extractor.TrackOutput r2 = r0.output
                r2.format(r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track.initializeOutput(com.google.android.exoplayer2.extractor.ExtractorOutput, int):void");
        }

        public void outputPendingSampleMetadata() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.outputPendingSampleMetadata(this);
            }
        }

        public void reset() {
            TrueHdSampleRechunker trueHdSampleRechunker2 = this.trueHdSampleRechunker;
            if (trueHdSampleRechunker2 != null) {
                trueHdSampleRechunker2.reset();
            }
        }

        private byte[] getHdrStaticInfo() {
            if (this.primaryRChromaticityX == -1.0f || this.primaryRChromaticityY == -1.0f || this.primaryGChromaticityX == -1.0f || this.primaryGChromaticityY == -1.0f || this.primaryBChromaticityX == -1.0f || this.primaryBChromaticityY == -1.0f || this.whitePointChromaticityX == -1.0f || this.whitePointChromaticityY == -1.0f || this.maxMasteringLuminance == -1.0f || this.minMasteringLuminance == -1.0f) {
                return null;
            }
            byte[] bArr = new byte[25];
            ByteBuffer wrap = ByteBuffer.wrap(bArr);
            wrap.put(0);
            wrap.putShort((short) ((int) ((this.primaryRChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryRChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryGChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.primaryBChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityX * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) ((this.whitePointChromaticityY * 50000.0f) + 0.5f)));
            wrap.putShort((short) ((int) (this.maxMasteringLuminance + 0.5f)));
            wrap.putShort((short) ((int) (this.minMasteringLuminance + 0.5f)));
            wrap.putShort((short) this.maxContentLuminance);
            wrap.putShort((short) this.maxFrameAverageLuminance);
            return bArr;
        }

        private static Pair<String, List<byte[]>> parseFourCcPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                parsableByteArray.skipBytes(16);
                long readLittleEndianUnsignedInt = parsableByteArray.readLittleEndianUnsignedInt();
                if (readLittleEndianUnsignedInt == 1482049860) {
                    return new Pair<>(MimeTypes.VIDEO_H263, null);
                }
                if (readLittleEndianUnsignedInt == 826496599) {
                    byte[] bArr = parsableByteArray.data;
                    for (int position = parsableByteArray.getPosition() + 20; position < bArr.length - 4; position++) {
                        if (bArr[position] == 0 && bArr[position + 1] == 0 && bArr[position + 2] == 1 && bArr[position + 3] == 15) {
                            return new Pair<>(MimeTypes.VIDEO_VC1, Collections.singletonList(Arrays.copyOfRange(bArr, position, bArr.length)));
                        }
                    }
                    throw new ParserException("Failed to find FourCC VC1 initialization data");
                }
                Log.w(MatroskaExtractor.TAG, "Unknown FourCC. Setting mimeType to video/x-unknown");
                return new Pair<>(MimeTypes.VIDEO_UNKNOWN, null);
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing FourCC private data");
            }
        }

        private static List<byte[]> parseVorbisCodecPrivate(byte[] bArr) throws ParserException {
            try {
                if (bArr[0] == 2) {
                    int i = 1;
                    int i2 = 0;
                    while (bArr[i] == -1) {
                        i2 += 255;
                        i++;
                    }
                    int i3 = i + 1;
                    int i4 = i2 + bArr[i];
                    int i5 = 0;
                    while (bArr[i3] == -1) {
                        i5 += 255;
                        i3++;
                    }
                    int i6 = i3 + 1;
                    int i7 = i5 + bArr[i3];
                    if (bArr[i6] == 1) {
                        byte[] bArr2 = new byte[i4];
                        System.arraycopy(bArr, i6, bArr2, 0, i4);
                        int i8 = i6 + i4;
                        if (bArr[i8] == 3) {
                            int i9 = i8 + i7;
                            if (bArr[i9] == 5) {
                                byte[] bArr3 = new byte[(bArr.length - i9)];
                                System.arraycopy(bArr, i9, bArr3, 0, bArr.length - i9);
                                ArrayList arrayList = new ArrayList(2);
                                arrayList.add(bArr2);
                                arrayList.add(bArr3);
                                return arrayList;
                            }
                            throw new ParserException("Error parsing vorbis codec private");
                        }
                        throw new ParserException("Error parsing vorbis codec private");
                    }
                    throw new ParserException("Error parsing vorbis codec private");
                }
                throw new ParserException("Error parsing vorbis codec private");
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing vorbis codec private");
            }
        }

        private static boolean parseMsAcmCodecPrivate(ParsableByteArray parsableByteArray) throws ParserException {
            try {
                int readLittleEndianUnsignedShort = parsableByteArray.readLittleEndianUnsignedShort();
                boolean z = true;
                if (readLittleEndianUnsignedShort == 1) {
                    return true;
                }
                if (readLittleEndianUnsignedShort != MatroskaExtractor.WAVE_FORMAT_EXTENSIBLE) {
                    return false;
                }
                parsableByteArray.setPosition(24);
                if (!(parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getMostSignificantBits() && parsableByteArray.readLong() == MatroskaExtractor.WAVE_SUBFORMAT_PCM.getLeastSignificantBits())) {
                    z = false;
                }
                return z;
            } catch (ArrayIndexOutOfBoundsException unused) {
                throw new ParserException("Error parsing MS/ACM codec private");
            }
        }
    }

    private static final class TrueHdSampleRechunker {
        private int blockFlags;
        private int chunkSize;
        private boolean foundSyncframe;
        private int sampleCount;
        private final byte[] syncframePrefix = new byte[10];
        private long timeUs;

        public void reset() {
            this.foundSyncframe = false;
        }

        public void startSample(ExtractorInput extractorInput, int i, int i2) throws IOException, InterruptedException {
            if (!this.foundSyncframe) {
                extractorInput.peekFully(this.syncframePrefix, 0, 10);
                extractorInput.resetPeekPosition();
                if (Ac3Util.parseTrueHdSyncframeAudioSampleCount(this.syncframePrefix) != 0) {
                    this.foundSyncframe = true;
                    this.sampleCount = 0;
                } else {
                    return;
                }
            }
            if (this.sampleCount == 0) {
                this.blockFlags = i;
                this.chunkSize = 0;
            }
            this.chunkSize += i2;
        }

        public void sampleMetadata(Track track, long j) {
            if (this.foundSyncframe) {
                int i = this.sampleCount;
                this.sampleCount = i + 1;
                if (i == 0) {
                    this.timeUs = j;
                }
                if (this.sampleCount >= 16) {
                    track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                    this.sampleCount = 0;
                }
            }
        }

        public void outputPendingSampleMetadata(Track track) {
            if (this.foundSyncframe && this.sampleCount > 0) {
                track.output.sampleMetadata(this.timeUs, this.blockFlags, this.chunkSize, 0, track.cryptoData);
                this.sampleCount = 0;
            }
        }
    }

    public void release() {
    }

    static /* synthetic */ Extractor[] lambda$static$0() {
        return new Extractor[]{new MatroskaExtractor()};
    }

    public MatroskaExtractor() {
        this(0);
    }

    public MatroskaExtractor(int i) {
        this(new DefaultEbmlReader(), i);
    }

    MatroskaExtractor(EbmlReader ebmlReader, int i) {
        this.segmentContentPosition = -1;
        this.timecodeScale = -9223372036854775807L;
        this.durationTimecode = -9223372036854775807L;
        this.durationUs = -9223372036854775807L;
        this.cuesContentPosition = -1;
        this.seekPositionAfterBuildingCues = -1;
        this.clusterTimecodeUs = -9223372036854775807L;
        this.reader = ebmlReader;
        this.reader.init(new InnerEbmlReaderOutput());
        boolean z = true;
        if ((i & 1) != 0) {
            z = false;
        }
        this.seekForCuesEnabled = z;
        this.varintReader = new VarintReader();
        this.tracks = new SparseArray<>();
        this.scratch = new ParsableByteArray(4);
        this.vorbisNumPageSamples = new ParsableByteArray(ByteBuffer.allocate(4).putInt(-1).array());
        this.seekEntryIdBytes = new ParsableByteArray(4);
        this.nalStartCode = new ParsableByteArray(NalUnitUtil.NAL_START_CODE);
        this.nalLength = new ParsableByteArray(4);
        this.sampleStrippedBytes = new ParsableByteArray();
        this.subtitleSample = new ParsableByteArray();
        this.encryptionInitializationVector = new ParsableByteArray(8);
        this.encryptionSubsampleData = new ParsableByteArray();
    }

    public boolean sniff(ExtractorInput extractorInput) throws IOException, InterruptedException {
        return new Sniffer().sniff(extractorInput);
    }

    public void init(ExtractorOutput extractorOutput2) {
        this.extractorOutput = extractorOutput2;
    }

    public void seek(long j, long j2) {
        this.clusterTimecodeUs = -9223372036854775807L;
        this.blockState = 0;
        this.reader.reset();
        this.varintReader.reset();
        resetSample();
        for (int i = 0; i < this.tracks.size(); i++) {
            ((Track) this.tracks.valueAt(i)).reset();
        }
    }

    public int read(ExtractorInput extractorInput, PositionHolder positionHolder) throws IOException, InterruptedException {
        this.sampleRead = false;
        boolean z = true;
        while (z && !this.sampleRead) {
            z = this.reader.read(extractorInput);
            if (z && maybeSeekForCues(positionHolder, extractorInput.getPosition())) {
                return 1;
            }
        }
        if (z) {
            return 0;
        }
        for (int i = 0; i < this.tracks.size(); i++) {
            ((Track) this.tracks.valueAt(i)).outputPendingSampleMetadata();
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public void startMasterElement(int i, long j, long j2) throws ParserException {
        if (i == ID_BLOCK_GROUP) {
            this.sampleSeenReferenceBlock = false;
        } else if (i == 174) {
            this.currentTrack = new Track();
        } else if (i == 187) {
            this.seenClusterPositionForCurrentCuePoint = false;
        } else if (i == ID_SEEK) {
            this.seekEntryId = -1;
            this.seekEntryPosition = -1;
        } else if (i == ID_CONTENT_ENCRYPTION) {
            this.currentTrack.hasContentEncryption = true;
        } else if (i == ID_MASTERING_METADATA) {
            this.currentTrack.hasColorInfo = true;
        } else if (i == ID_CONTENT_ENCODING) {
        } else {
            if (i == ID_SEGMENT) {
                long j3 = this.segmentContentPosition;
                if (j3 == -1 || j3 == j) {
                    this.segmentContentPosition = j;
                    this.segmentContentSize = j2;
                    return;
                }
                throw new ParserException("Multiple Segment elements not supported");
            } else if (i == ID_CUES) {
                this.cueTimesUs = new LongArray();
                this.cueClusterPositions = new LongArray();
            } else if (i != ID_CLUSTER || this.sentSeekMap) {
            } else {
                if (!this.seekForCuesEnabled || this.cuesContentPosition == -1) {
                    this.extractorOutput.seekMap(new Unseekable(this.durationUs));
                    this.sentSeekMap = true;
                    return;
                }
                this.seekForCues = true;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void endMasterElement(int i) throws ParserException {
        if (i != ID_BLOCK_GROUP) {
            if (i == 174) {
                if (isCodecSupported(this.currentTrack.codecId)) {
                    Track track = this.currentTrack;
                    track.initializeOutput(this.extractorOutput, track.number);
                    this.tracks.put(this.currentTrack.number, this.currentTrack);
                }
                this.currentTrack = null;
            } else if (i == ID_SEEK) {
                int i2 = this.seekEntryId;
                if (i2 != -1) {
                    long j = this.seekEntryPosition;
                    if (j != -1) {
                        if (i2 == ID_CUES) {
                            this.cuesContentPosition = j;
                        }
                    }
                }
                throw new ParserException("Mandatory element SeekID or SeekPosition not found");
            } else if (i != ID_CONTENT_ENCODING) {
                if (i != ID_CONTENT_ENCODINGS) {
                    if (i == 357149030) {
                        if (this.timecodeScale == -9223372036854775807L) {
                            this.timecodeScale = 1000000;
                        }
                        long j2 = this.durationTimecode;
                        if (j2 != -9223372036854775807L) {
                            this.durationUs = scaleTimecodeToUs(j2);
                        }
                    } else if (i != ID_TRACKS) {
                        if (i == ID_CUES && !this.sentSeekMap) {
                            this.extractorOutput.seekMap(buildSeekMap());
                            this.sentSeekMap = true;
                        }
                    } else if (this.tracks.size() != 0) {
                        this.extractorOutput.endTracks();
                    } else {
                        throw new ParserException("No valid tracks were found");
                    }
                } else if (this.currentTrack.hasContentEncryption && this.currentTrack.sampleStrippedBytes != null) {
                    throw new ParserException("Combining encryption and compression is not supported");
                }
            } else if (this.currentTrack.hasContentEncryption) {
                if (this.currentTrack.cryptoData != null) {
                    this.currentTrack.drmInitData = new DrmInitData(new SchemeData(C.UUID_NIL, MimeTypes.VIDEO_WEBM, this.currentTrack.cryptoData.encryptionKey));
                } else {
                    throw new ParserException("Encrypted Track found but ContentEncKeyID was not found");
                }
            }
        } else if (this.blockState == 2) {
            if (!this.sampleSeenReferenceBlock) {
                this.blockFlags |= 1;
            }
            commitSampleToOutput((Track) this.tracks.get(this.blockTrackNumber), this.blockTimeUs);
            this.blockState = 0;
        }
    }

    /* access modifiers changed from: 0000 */
    public void integerElement(int i, long j) throws ParserException {
        boolean z = false;
        switch (i) {
            case 131:
                this.currentTrack.type = (int) j;
                return;
            case 136:
                Track track = this.currentTrack;
                if (j == 1) {
                    z = true;
                }
                track.flagDefault = z;
                return;
            case ID_BLOCK_DURATION /*155*/:
                this.blockDurationUs = scaleTimecodeToUs(j);
                return;
            case 159:
                this.currentTrack.channelCount = (int) j;
                return;
            case ID_PIXEL_WIDTH /*176*/:
                this.currentTrack.width = (int) j;
                return;
            case 179:
                this.cueTimesUs.add(scaleTimecodeToUs(j));
                return;
            case 186:
                this.currentTrack.height = (int) j;
                return;
            case ID_TRACK_NUMBER /*215*/:
                this.currentTrack.number = (int) j;
                return;
            case ID_TIME_CODE /*231*/:
                this.clusterTimecodeUs = scaleTimecodeToUs(j);
                return;
            case ID_CUE_CLUSTER_POSITION /*241*/:
                if (!this.seenClusterPositionForCurrentCuePoint) {
                    this.cueClusterPositions.add(j);
                    this.seenClusterPositionForCurrentCuePoint = true;
                    return;
                }
                return;
            case ID_REFERENCE_BLOCK /*251*/:
                this.sampleSeenReferenceBlock = true;
                return;
            case ID_CONTENT_COMPRESSION_ALGORITHM /*16980*/:
                if (j != 3) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("ContentCompAlgo ");
                    sb.append(j);
                    sb.append(" not supported");
                    throw new ParserException(sb.toString());
                }
                return;
            case ID_DOC_TYPE_READ_VERSION /*17029*/:
                if (j < 1 || j > 2) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("DocTypeReadVersion ");
                    sb2.append(j);
                    sb2.append(" not supported");
                    throw new ParserException(sb2.toString());
                }
                return;
            case ID_EBML_READ_VERSION /*17143*/:
                if (j != 1) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("EBMLReadVersion ");
                    sb3.append(j);
                    sb3.append(" not supported");
                    throw new ParserException(sb3.toString());
                }
                return;
            case ID_CONTENT_ENCRYPTION_ALGORITHM /*18401*/:
                if (j != 5) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("ContentEncAlgo ");
                    sb4.append(j);
                    sb4.append(" not supported");
                    throw new ParserException(sb4.toString());
                }
                return;
            case ID_CONTENT_ENCRYPTION_AES_SETTINGS_CIPHER_MODE /*18408*/:
                if (j != 1) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("AESSettingsCipherMode ");
                    sb5.append(j);
                    sb5.append(" not supported");
                    throw new ParserException(sb5.toString());
                }
                return;
            case ID_CONTENT_ENCODING_ORDER /*20529*/:
                if (j != 0) {
                    StringBuilder sb6 = new StringBuilder();
                    sb6.append("ContentEncodingOrder ");
                    sb6.append(j);
                    sb6.append(" not supported");
                    throw new ParserException(sb6.toString());
                }
                return;
            case ID_CONTENT_ENCODING_SCOPE /*20530*/:
                if (j != 1) {
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("ContentEncodingScope ");
                    sb7.append(j);
                    sb7.append(" not supported");
                    throw new ParserException(sb7.toString());
                }
                return;
            case ID_SEEK_POSITION /*21420*/:
                this.seekEntryPosition = j + this.segmentContentPosition;
                return;
            case ID_STEREO_MODE /*21432*/:
                int i2 = (int) j;
                if (i2 == 3) {
                    this.currentTrack.stereoMode = 1;
                    return;
                } else if (i2 != 15) {
                    switch (i2) {
                        case 0:
                            this.currentTrack.stereoMode = 0;
                            return;
                        case 1:
                            this.currentTrack.stereoMode = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    this.currentTrack.stereoMode = 3;
                    return;
                }
            case ID_DISPLAY_WIDTH /*21680*/:
                this.currentTrack.displayWidth = (int) j;
                return;
            case ID_DISPLAY_UNIT /*21682*/:
                this.currentTrack.displayUnit = (int) j;
                return;
            case ID_DISPLAY_HEIGHT /*21690*/:
                this.currentTrack.displayHeight = (int) j;
                return;
            case ID_FLAG_FORCED /*21930*/:
                Track track2 = this.currentTrack;
                if (j == 1) {
                    z = true;
                }
                track2.flagForced = z;
                return;
            case ID_COLOUR_RANGE /*21945*/:
                switch ((int) j) {
                    case 1:
                        this.currentTrack.colorRange = 2;
                        return;
                    case 2:
                        this.currentTrack.colorRange = 1;
                        return;
                    default:
                        return;
                }
            case ID_COLOUR_TRANSFER /*21946*/:
                int i3 = (int) j;
                if (i3 != 1) {
                    if (i3 == 16) {
                        this.currentTrack.colorTransfer = 6;
                        return;
                    } else if (i3 != 18) {
                        switch (i3) {
                            case 6:
                            case 7:
                                break;
                            default:
                                return;
                        }
                    } else {
                        this.currentTrack.colorTransfer = 7;
                        return;
                    }
                }
                this.currentTrack.colorTransfer = 3;
                return;
            case ID_COLOUR_PRIMARIES /*21947*/:
                Track track3 = this.currentTrack;
                track3.hasColorInfo = true;
                int i4 = (int) j;
                if (i4 == 1) {
                    track3.colorSpace = 1;
                    return;
                } else if (i4 != 9) {
                    switch (i4) {
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            track3.colorSpace = 2;
                            return;
                        default:
                            return;
                    }
                } else {
                    track3.colorSpace = 6;
                    return;
                }
            case ID_MAX_CLL /*21948*/:
                this.currentTrack.maxContentLuminance = (int) j;
                return;
            case ID_MAX_FALL /*21949*/:
                this.currentTrack.maxFrameAverageLuminance = (int) j;
                return;
            case ID_CODEC_DELAY /*22186*/:
                this.currentTrack.codecDelayNs = j;
                return;
            case ID_SEEK_PRE_ROLL /*22203*/:
                this.currentTrack.seekPreRollNs = j;
                return;
            case ID_AUDIO_BIT_DEPTH /*25188*/:
                this.currentTrack.audioBitDepth = (int) j;
                return;
            case ID_PROJECTION_TYPE /*30321*/:
                switch ((int) j) {
                    case 0:
                        this.currentTrack.projectionType = 0;
                        return;
                    case 1:
                        this.currentTrack.projectionType = 1;
                        return;
                    case 2:
                        this.currentTrack.projectionType = 2;
                        return;
                    case 3:
                        this.currentTrack.projectionType = 3;
                        return;
                    default:
                        return;
                }
            case ID_DEFAULT_DURATION /*2352003*/:
                this.currentTrack.defaultSampleDurationNs = (int) j;
                return;
            case ID_TIMECODE_SCALE /*2807729*/:
                this.timecodeScale = j;
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: 0000 */
    public void floatElement(int i, double d) {
        if (i == ID_SAMPLING_FREQUENCY) {
            this.currentTrack.sampleRate = (int) d;
        } else if (i != ID_DURATION) {
            switch (i) {
                case ID_PRIMARY_R_CHROMATICITY_X /*21969*/:
                    this.currentTrack.primaryRChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_R_CHROMATICITY_Y /*21970*/:
                    this.currentTrack.primaryRChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_X /*21971*/:
                    this.currentTrack.primaryGChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_G_CHROMATICITY_Y /*21972*/:
                    this.currentTrack.primaryGChromaticityY = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_X /*21973*/:
                    this.currentTrack.primaryBChromaticityX = (float) d;
                    return;
                case ID_PRIMARY_B_CHROMATICITY_Y /*21974*/:
                    this.currentTrack.primaryBChromaticityY = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_X /*21975*/:
                    this.currentTrack.whitePointChromaticityX = (float) d;
                    return;
                case ID_WHITE_POINT_CHROMATICITY_Y /*21976*/:
                    this.currentTrack.whitePointChromaticityY = (float) d;
                    return;
                case ID_LUMNINANCE_MAX /*21977*/:
                    this.currentTrack.maxMasteringLuminance = (float) d;
                    return;
                case ID_LUMNINANCE_MIN /*21978*/:
                    this.currentTrack.minMasteringLuminance = (float) d;
                    return;
                default:
                    switch (i) {
                        case ID_PROJECTION_POSE_YAW /*30323*/:
                            this.currentTrack.projectionPoseYaw = (float) d;
                            return;
                        case ID_PROJECTION_POSE_PITCH /*30324*/:
                            this.currentTrack.projectionPosePitch = (float) d;
                            return;
                        case ID_PROJECTION_POSE_ROLL /*30325*/:
                            this.currentTrack.projectionPoseRoll = (float) d;
                            return;
                        default:
                            return;
                    }
            }
        } else {
            this.durationTimecode = (long) d;
        }
    }

    /* access modifiers changed from: 0000 */
    public void stringElement(int i, String str) throws ParserException {
        if (i == 134) {
            this.currentTrack.codecId = str;
        } else if (i != 17026) {
            if (i == ID_NAME) {
                this.currentTrack.name = str;
            } else if (i == ID_LANGUAGE) {
                this.currentTrack.language = str;
            }
        } else if (!DOC_TYPE_WEBM.equals(str) && !DOC_TYPE_MATROSKA.equals(str)) {
            StringBuilder sb = new StringBuilder();
            sb.append("DocType ");
            sb.append(str);
            sb.append(" not supported");
            throw new ParserException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0205, code lost:
        throw new com.google.android.exoplayer2.ParserException("EBML lacing sample size out of range.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void binaryElement(int r20, int r21, com.google.android.exoplayer2.extractor.ExtractorInput r22) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            r2 = r21
            r3 = r22
            r4 = 161(0xa1, float:2.26E-43)
            r5 = 163(0xa3, float:2.28E-43)
            r6 = 4
            r7 = 0
            r8 = 1
            if (r1 == r4) goto L_0x0094
            if (r1 == r5) goto L_0x0094
            r4 = 16981(0x4255, float:2.3795E-41)
            if (r1 == r4) goto L_0x0087
            r4 = 18402(0x47e2, float:2.5787E-41)
            if (r1 == r4) goto L_0x0077
            r4 = 21419(0x53ab, float:3.0014E-41)
            if (r1 == r4) goto L_0x0058
            r4 = 25506(0x63a2, float:3.5742E-41)
            if (r1 == r4) goto L_0x004b
            r4 = 30322(0x7672, float:4.249E-41)
            if (r1 != r4) goto L_0x0034
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.projectionData = r4
            byte[] r1 = r1.projectionData
            r3.readFully(r1, r7, r2)
            goto L_0x02bf
        L_0x0034:
            com.google.android.exoplayer2.ParserException r2 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Unexpected id: "
            r3.append(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L_0x004b:
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.codecPrivate = r4
            byte[] r1 = r1.codecPrivate
            r3.readFully(r1, r7, r2)
            goto L_0x02bf
        L_0x0058:
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            byte[] r1 = r1.data
            java.util.Arrays.fill(r1, r7)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            byte[] r1 = r1.data
            int r6 = r6 - r2
            r3.readFully(r1, r6, r2)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            r1.setPosition(r7)
            com.google.android.exoplayer2.util.ParsableByteArray r1 = r0.seekEntryIdBytes
            long r1 = r1.readUnsignedInt()
            int r2 = (int) r1
            r0.seekEntryId = r2
            goto L_0x02bf
        L_0x0077:
            byte[] r1 = new byte[r2]
            r3.readFully(r1, r7, r2)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r2 = r0.currentTrack
            com.google.android.exoplayer2.extractor.TrackOutput$CryptoData r3 = new com.google.android.exoplayer2.extractor.TrackOutput$CryptoData
            r3.<init>(r8, r1, r7, r7)
            r2.cryptoData = r3
            goto L_0x02bf
        L_0x0087:
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r1 = r0.currentTrack
            byte[] r4 = new byte[r2]
            r1.sampleStrippedBytes = r4
            byte[] r1 = r1.sampleStrippedBytes
            r3.readFully(r1, r7, r2)
            goto L_0x02bf
        L_0x0094:
            int r4 = r0.blockState
            r9 = 8
            if (r4 != 0) goto L_0x00b9
            com.google.android.exoplayer2.extractor.mkv.VarintReader r4 = r0.varintReader
            long r10 = r4.readUnsignedVarint(r3, r7, r8, r9)
            int r4 = (int) r10
            r0.blockTrackNumber = r4
            com.google.android.exoplayer2.extractor.mkv.VarintReader r4 = r0.varintReader
            int r4 = r4.getLastLength()
            r0.blockTrackNumberLength = r4
            r10 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0.blockDurationUs = r10
            r0.blockState = r8
            com.google.android.exoplayer2.util.ParsableByteArray r4 = r0.scratch
            r4.reset()
        L_0x00b9:
            android.util.SparseArray<com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track> r4 = r0.tracks
            int r10 = r0.blockTrackNumber
            java.lang.Object r4 = r4.get(r10)
            com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor$Track r4 = (com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.Track) r4
            if (r4 != 0) goto L_0x00cf
            int r1 = r0.blockTrackNumberLength
            int r1 = r2 - r1
            r3.skipFully(r1)
            r0.blockState = r7
            return
        L_0x00cf:
            int r10 = r0.blockState
            if (r10 != r8) goto L_0x028c
            r10 = 3
            r0.readScratch(r3, r10)
            com.google.android.exoplayer2.util.ParsableByteArray r11 = r0.scratch
            byte[] r11 = r11.data
            r12 = 2
            byte r11 = r11[r12]
            r11 = r11 & 6
            int r11 = r11 >> r8
            r13 = 255(0xff, float:3.57E-43)
            if (r11 != 0) goto L_0x00f9
            r0.blockLacingSampleCount = r8
            int[] r6 = r0.blockLacingSampleSizes
            int[] r6 = ensureArrayCapacity(r6, r8)
            r0.blockLacingSampleSizes = r6
            int[] r6 = r0.blockLacingSampleSizes
            int r11 = r0.blockTrackNumberLength
            int r2 = r2 - r11
            int r2 = r2 - r10
            r6[r7] = r2
            goto L_0x0219
        L_0x00f9:
            if (r1 != r5) goto L_0x0284
            r0.readScratch(r3, r6)
            com.google.android.exoplayer2.util.ParsableByteArray r14 = r0.scratch
            byte[] r14 = r14.data
            byte r14 = r14[r10]
            r14 = r14 & r13
            int r14 = r14 + r8
            r0.blockLacingSampleCount = r14
            int[] r14 = r0.blockLacingSampleSizes
            int r15 = r0.blockLacingSampleCount
            int[] r14 = ensureArrayCapacity(r14, r15)
            r0.blockLacingSampleSizes = r14
            if (r11 != r12) goto L_0x0122
            int r10 = r0.blockTrackNumberLength
            int r2 = r2 - r10
            int r2 = r2 - r6
            int r6 = r0.blockLacingSampleCount
            int r2 = r2 / r6
            int[] r10 = r0.blockLacingSampleSizes
            java.util.Arrays.fill(r10, r7, r6, r2)
            goto L_0x0219
        L_0x0122:
            if (r11 != r8) goto L_0x015a
            r6 = 0
            r10 = 4
            r11 = 0
        L_0x0127:
            int r14 = r0.blockLacingSampleCount
            int r15 = r14 + -1
            if (r6 >= r15) goto L_0x014e
            int[] r14 = r0.blockLacingSampleSizes
            r14[r6] = r7
        L_0x0131:
            int r10 = r10 + r8
            r0.readScratch(r3, r10)
            com.google.android.exoplayer2.util.ParsableByteArray r14 = r0.scratch
            byte[] r14 = r14.data
            int r15 = r10 + -1
            byte r14 = r14[r15]
            r14 = r14 & r13
            int[] r15 = r0.blockLacingSampleSizes
            r16 = r15[r6]
            int r16 = r16 + r14
            r15[r6] = r16
            if (r14 == r13) goto L_0x0131
            r14 = r15[r6]
            int r11 = r11 + r14
            int r6 = r6 + 1
            goto L_0x0127
        L_0x014e:
            int[] r6 = r0.blockLacingSampleSizes
            int r14 = r14 - r8
            int r15 = r0.blockTrackNumberLength
            int r2 = r2 - r15
            int r2 = r2 - r10
            int r2 = r2 - r11
            r6[r14] = r2
            goto L_0x0219
        L_0x015a:
            if (r11 != r10) goto L_0x026d
            r6 = 0
            r10 = 4
            r11 = 0
        L_0x015f:
            int r14 = r0.blockLacingSampleCount
            int r15 = r14 + -1
            if (r6 >= r15) goto L_0x020e
            int[] r14 = r0.blockLacingSampleSizes
            r14[r6] = r7
            int r10 = r10 + 1
            r0.readScratch(r3, r10)
            com.google.android.exoplayer2.util.ParsableByteArray r14 = r0.scratch
            byte[] r14 = r14.data
            int r15 = r10 + -1
            byte r14 = r14[r15]
            if (r14 == 0) goto L_0x0206
            r16 = 0
            r14 = 0
        L_0x017b:
            if (r14 >= r9) goto L_0x01d1
            int r18 = 7 - r14
            int r5 = r8 << r18
            com.google.android.exoplayer2.util.ParsableByteArray r12 = r0.scratch
            byte[] r12 = r12.data
            byte r12 = r12[r15]
            r12 = r12 & r5
            if (r12 == 0) goto L_0x01c7
            int r10 = r10 + r14
            r0.readScratch(r3, r10)
            com.google.android.exoplayer2.util.ParsableByteArray r12 = r0.scratch
            byte[] r12 = r12.data
            int r16 = r15 + 1
            byte r12 = r12[r15]
            r12 = r12 & r13
            int r5 = ~r5
            r5 = r5 & r12
            long r7 = (long) r5
            r5 = r16
            r16 = r7
        L_0x019e:
            if (r5 >= r10) goto L_0x01b4
            long r7 = r16 << r9
            com.google.android.exoplayer2.util.ParsableByteArray r15 = r0.scratch
            byte[] r15 = r15.data
            int r16 = r5 + 1
            byte r5 = r15[r5]
            r5 = r5 & r13
            long r12 = (long) r5
            long r7 = r7 | r12
            r5 = r16
            r13 = 255(0xff, float:3.57E-43)
            r16 = r7
            goto L_0x019e
        L_0x01b4:
            if (r6 <= 0) goto L_0x01c4
            int r14 = r14 * 7
            int r14 = r14 + 6
            r7 = 1
            long r12 = r7 << r14
            long r12 = r12 - r7
            long r16 = r16 - r12
            r7 = r16
            goto L_0x01d3
        L_0x01c4:
            r7 = r16
            goto L_0x01d3
        L_0x01c7:
            int r14 = r14 + 1
            r5 = 163(0xa3, float:2.28E-43)
            r7 = 0
            r8 = 1
            r12 = 2
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x017b
        L_0x01d1:
            r7 = r16
        L_0x01d3:
            r12 = -2147483648(0xffffffff80000000, double:NaN)
            int r5 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r5 < 0) goto L_0x01fe
            r12 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r5 = (r7 > r12 ? 1 : (r7 == r12 ? 0 : -1))
            if (r5 > 0) goto L_0x01fe
            int r5 = (int) r7
            int[] r7 = r0.blockLacingSampleSizes
            if (r6 != 0) goto L_0x01e7
            goto L_0x01ec
        L_0x01e7:
            int r8 = r6 + -1
            r8 = r7[r8]
            int r5 = r5 + r8
        L_0x01ec:
            r7[r6] = r5
            int[] r5 = r0.blockLacingSampleSizes
            r5 = r5[r6]
            int r11 = r11 + r5
            int r6 = r6 + 1
            r5 = 163(0xa3, float:2.28E-43)
            r7 = 0
            r8 = 1
            r12 = 2
            r13 = 255(0xff, float:3.57E-43)
            goto L_0x015f
        L_0x01fe:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "EBML lacing sample size out of range."
            r1.<init>(r2)
            throw r1
        L_0x0206:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "No valid varint length mask found"
            r1.<init>(r2)
            throw r1
        L_0x020e:
            int[] r5 = r0.blockLacingSampleSizes
            r6 = 1
            int r14 = r14 - r6
            int r6 = r0.blockTrackNumberLength
            int r2 = r2 - r6
            int r2 = r2 - r10
            int r2 = r2 - r11
            r5[r14] = r2
        L_0x0219:
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.scratch
            byte[] r2 = r2.data
            r5 = 0
            byte r2 = r2[r5]
            int r2 = r2 << r9
            com.google.android.exoplayer2.util.ParsableByteArray r5 = r0.scratch
            byte[] r5 = r5.data
            r6 = 1
            byte r5 = r5[r6]
            r6 = 255(0xff, float:3.57E-43)
            r5 = r5 & r6
            r2 = r2 | r5
            long r5 = r0.clusterTimecodeUs
            long r7 = (long) r2
            long r7 = r0.scaleTimecodeToUs(r7)
            long r5 = r5 + r7
            r0.blockTimeUs = r5
            com.google.android.exoplayer2.util.ParsableByteArray r2 = r0.scratch
            byte[] r2 = r2.data
            r5 = 2
            byte r2 = r2[r5]
            r2 = r2 & r9
            if (r2 != r9) goto L_0x0242
            r2 = 1
            goto L_0x0243
        L_0x0242:
            r2 = 0
        L_0x0243:
            int r6 = r4.type
            if (r6 == r5) goto L_0x0259
            r6 = 163(0xa3, float:2.28E-43)
            if (r1 != r6) goto L_0x0257
            com.google.android.exoplayer2.util.ParsableByteArray r6 = r0.scratch
            byte[] r6 = r6.data
            byte r6 = r6[r5]
            r5 = 128(0x80, float:1.794E-43)
            r6 = r6 & r5
            if (r6 != r5) goto L_0x0257
            goto L_0x0259
        L_0x0257:
            r5 = 0
            goto L_0x025a
        L_0x0259:
            r5 = 1
        L_0x025a:
            if (r2 == 0) goto L_0x025f
            r7 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x0260
        L_0x025f:
            r7 = 0
        L_0x0260:
            r2 = r5 | r7
            r0.blockFlags = r2
            r2 = 2
            r0.blockState = r2
            r2 = 0
            r0.blockLacingSampleIndex = r2
            r2 = 163(0xa3, float:2.28E-43)
            goto L_0x028e
        L_0x026d:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Unexpected lacing value: "
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L_0x0284:
            com.google.android.exoplayer2.ParserException r1 = new com.google.android.exoplayer2.ParserException
            java.lang.String r2 = "Lacing only supported in SimpleBlocks."
            r1.<init>(r2)
            throw r1
        L_0x028c:
            r2 = 163(0xa3, float:2.28E-43)
        L_0x028e:
            if (r1 != r2) goto L_0x02b7
        L_0x0290:
            int r1 = r0.blockLacingSampleIndex
            int r2 = r0.blockLacingSampleCount
            if (r1 >= r2) goto L_0x02b3
            int[] r2 = r0.blockLacingSampleSizes
            r1 = r2[r1]
            r0.writeSampleData(r3, r4, r1)
            long r1 = r0.blockTimeUs
            int r5 = r0.blockLacingSampleIndex
            int r6 = r4.defaultSampleDurationNs
            int r5 = r5 * r6
            int r5 = r5 / 1000
            long r5 = (long) r5
            long r1 = r1 + r5
            r0.commitSampleToOutput(r4, r1)
            int r1 = r0.blockLacingSampleIndex
            r2 = 1
            int r1 = r1 + r2
            r0.blockLacingSampleIndex = r1
            goto L_0x0290
        L_0x02b3:
            r1 = 0
            r0.blockState = r1
            goto L_0x02bf
        L_0x02b7:
            r1 = 0
            int[] r2 = r0.blockLacingSampleSizes
            r1 = r2[r1]
            r0.writeSampleData(r3, r4, r1)
        L_0x02bf:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor.binaryElement(int, int, com.google.android.exoplayer2.extractor.ExtractorInput):void");
    }

    private void commitSampleToOutput(Track track, long j) {
        Track track2 = track;
        if (track2.trueHdSampleRechunker != null) {
            track2.trueHdSampleRechunker.sampleMetadata(track2, j);
        } else {
            long j2 = j;
            if (CODEC_ID_SUBRIP.equals(track2.codecId)) {
                commitSubtitleSample(track, SUBRIP_TIMECODE_FORMAT, 19, 1000, SUBRIP_TIMECODE_EMPTY);
            } else if (CODEC_ID_ASS.equals(track2.codecId)) {
                commitSubtitleSample(track, SSA_TIMECODE_FORMAT, 21, SSA_TIMECODE_LAST_VALUE_SCALING_FACTOR, SSA_TIMECODE_EMPTY);
            }
            track2.output.sampleMetadata(j, this.blockFlags, this.sampleBytesWritten, 0, track2.cryptoData);
        }
        this.sampleRead = true;
        resetSample();
    }

    private void resetSample() {
        this.sampleBytesRead = 0;
        this.sampleBytesWritten = 0;
        this.sampleCurrentNalBytesRemaining = 0;
        this.sampleEncodingHandled = false;
        this.sampleSignalByteRead = false;
        this.samplePartitionCountRead = false;
        this.samplePartitionCount = 0;
        this.sampleSignalByte = 0;
        this.sampleInitializationVectorRead = false;
        this.sampleStrippedBytes.reset();
    }

    private void readScratch(ExtractorInput extractorInput, int i) throws IOException, InterruptedException {
        if (this.scratch.limit() < i) {
            if (this.scratch.capacity() < i) {
                ParsableByteArray parsableByteArray = this.scratch;
                parsableByteArray.reset(Arrays.copyOf(parsableByteArray.data, Math.max(this.scratch.data.length * 2, i)), this.scratch.limit());
            }
            extractorInput.readFully(this.scratch.data, this.scratch.limit(), i - this.scratch.limit());
            this.scratch.setLimit(i);
        }
    }

    private void writeSampleData(ExtractorInput extractorInput, Track track, int i) throws IOException, InterruptedException {
        int i2;
        if (CODEC_ID_SUBRIP.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SUBRIP_PREFIX, i);
        } else if (CODEC_ID_ASS.equals(track.codecId)) {
            writeSubtitleSampleData(extractorInput, SSA_PREFIX, i);
        } else {
            TrackOutput trackOutput = track.output;
            boolean z = true;
            if (!this.sampleEncodingHandled) {
                if (track.hasContentEncryption) {
                    this.blockFlags &= -1073741825;
                    int i3 = 128;
                    if (!this.sampleSignalByteRead) {
                        extractorInput.readFully(this.scratch.data, 0, 1);
                        this.sampleBytesRead++;
                        if ((this.scratch.data[0] & 128) != 128) {
                            this.sampleSignalByte = this.scratch.data[0];
                            this.sampleSignalByteRead = true;
                        } else {
                            throw new ParserException("Extension bit is set in signal byte");
                        }
                    }
                    if ((this.sampleSignalByte & 1) == 1) {
                        boolean z2 = (this.sampleSignalByte & 2) == 2;
                        this.blockFlags |= 1073741824;
                        if (!this.sampleInitializationVectorRead) {
                            extractorInput.readFully(this.encryptionInitializationVector.data, 0, 8);
                            this.sampleBytesRead += 8;
                            this.sampleInitializationVectorRead = true;
                            byte[] bArr = this.scratch.data;
                            if (!z2) {
                                i3 = 0;
                            }
                            bArr[0] = (byte) (i3 | 8);
                            this.scratch.setPosition(0);
                            trackOutput.sampleData(this.scratch, 1);
                            this.sampleBytesWritten++;
                            this.encryptionInitializationVector.setPosition(0);
                            trackOutput.sampleData(this.encryptionInitializationVector, 8);
                            this.sampleBytesWritten += 8;
                        }
                        if (z2) {
                            if (!this.samplePartitionCountRead) {
                                extractorInput.readFully(this.scratch.data, 0, 1);
                                this.sampleBytesRead++;
                                this.scratch.setPosition(0);
                                this.samplePartitionCount = this.scratch.readUnsignedByte();
                                this.samplePartitionCountRead = true;
                            }
                            int i4 = this.samplePartitionCount * 4;
                            this.scratch.reset(i4);
                            extractorInput.readFully(this.scratch.data, 0, i4);
                            this.sampleBytesRead += i4;
                            short s = (short) ((this.samplePartitionCount / 2) + 1);
                            int i5 = (s * 6) + 2;
                            ByteBuffer byteBuffer = this.encryptionSubsampleDataBuffer;
                            if (byteBuffer == null || byteBuffer.capacity() < i5) {
                                this.encryptionSubsampleDataBuffer = ByteBuffer.allocate(i5);
                            }
                            this.encryptionSubsampleDataBuffer.position(0);
                            this.encryptionSubsampleDataBuffer.putShort(s);
                            int i6 = 0;
                            int i7 = 0;
                            while (true) {
                                i2 = this.samplePartitionCount;
                                if (i6 >= i2) {
                                    break;
                                }
                                int readUnsignedIntToInt = this.scratch.readUnsignedIntToInt();
                                if (i6 % 2 == 0) {
                                    this.encryptionSubsampleDataBuffer.putShort((short) (readUnsignedIntToInt - i7));
                                } else {
                                    this.encryptionSubsampleDataBuffer.putInt(readUnsignedIntToInt - i7);
                                }
                                i6++;
                                i7 = readUnsignedIntToInt;
                            }
                            int i8 = (i - this.sampleBytesRead) - i7;
                            if (i2 % 2 == 1) {
                                this.encryptionSubsampleDataBuffer.putInt(i8);
                            } else {
                                this.encryptionSubsampleDataBuffer.putShort((short) i8);
                                this.encryptionSubsampleDataBuffer.putInt(0);
                            }
                            this.encryptionSubsampleData.reset(this.encryptionSubsampleDataBuffer.array(), i5);
                            trackOutput.sampleData(this.encryptionSubsampleData, i5);
                            this.sampleBytesWritten += i5;
                        }
                    }
                } else if (track.sampleStrippedBytes != null) {
                    this.sampleStrippedBytes.reset(track.sampleStrippedBytes, track.sampleStrippedBytes.length);
                }
                this.sampleEncodingHandled = true;
            }
            int limit = i + this.sampleStrippedBytes.limit();
            if (!CODEC_ID_H264.equals(track.codecId) && !CODEC_ID_H265.equals(track.codecId)) {
                if (track.trueHdSampleRechunker != null) {
                    if (this.sampleStrippedBytes.limit() != 0) {
                        z = false;
                    }
                    Assertions.checkState(z);
                    track.trueHdSampleRechunker.startSample(extractorInput, this.blockFlags, limit);
                }
                while (true) {
                    int i9 = this.sampleBytesRead;
                    if (i9 >= limit) {
                        break;
                    }
                    readToOutput(extractorInput, trackOutput, limit - i9);
                }
            } else {
                byte[] bArr2 = this.nalLength.data;
                bArr2[0] = 0;
                bArr2[1] = 0;
                bArr2[2] = 0;
                int i10 = track.nalUnitLengthFieldLength;
                int i11 = 4 - track.nalUnitLengthFieldLength;
                while (this.sampleBytesRead < limit) {
                    int i12 = this.sampleCurrentNalBytesRemaining;
                    if (i12 == 0) {
                        readToTarget(extractorInput, bArr2, i11, i10);
                        this.nalLength.setPosition(0);
                        this.sampleCurrentNalBytesRemaining = this.nalLength.readUnsignedIntToInt();
                        this.nalStartCode.setPosition(0);
                        trackOutput.sampleData(this.nalStartCode, 4);
                        this.sampleBytesWritten += 4;
                    } else {
                        this.sampleCurrentNalBytesRemaining = i12 - readToOutput(extractorInput, trackOutput, i12);
                    }
                }
            }
            if (CODEC_ID_VORBIS.equals(track.codecId)) {
                this.vorbisNumPageSamples.setPosition(0);
                trackOutput.sampleData(this.vorbisNumPageSamples, 4);
                this.sampleBytesWritten += 4;
            }
        }
    }

    private void writeSubtitleSampleData(ExtractorInput extractorInput, byte[] bArr, int i) throws IOException, InterruptedException {
        int length = bArr.length + i;
        if (this.subtitleSample.capacity() < length) {
            this.subtitleSample.data = Arrays.copyOf(bArr, length + i);
        } else {
            System.arraycopy(bArr, 0, this.subtitleSample.data, 0, bArr.length);
        }
        extractorInput.readFully(this.subtitleSample.data, bArr.length, i);
        this.subtitleSample.reset(length);
    }

    private void commitSubtitleSample(Track track, String str, int i, long j, byte[] bArr) {
        setSampleDuration(this.subtitleSample.data, this.blockDurationUs, str, i, j, bArr);
        TrackOutput trackOutput = track.output;
        ParsableByteArray parsableByteArray = this.subtitleSample;
        trackOutput.sampleData(parsableByteArray, parsableByteArray.limit());
        this.sampleBytesWritten += this.subtitleSample.limit();
    }

    private static void setSampleDuration(byte[] bArr, long j, String str, int i, long j2, byte[] bArr2) {
        byte[] bArr3;
        byte[] bArr4;
        if (j == -9223372036854775807L) {
            bArr4 = bArr2;
            bArr3 = bArr4;
        } else {
            int i2 = (int) (j / 3600000000L);
            long j3 = j - (((long) (i2 * 3600)) * 1000000);
            int i3 = (int) (j3 / 60000000);
            long j4 = j3 - (((long) (i3 * 60)) * 1000000);
            int i4 = (int) (j4 / 1000000);
            int i5 = (int) ((j4 - (((long) i4) * 1000000)) / j2);
            Object[] objArr = {Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)};
            String str2 = str;
            bArr3 = Util.getUtf8Bytes(String.format(Locale.US, str, objArr));
            bArr4 = bArr2;
        }
        byte[] bArr5 = bArr;
        int i6 = i;
        System.arraycopy(bArr3, 0, bArr, i, bArr4.length);
    }

    private void readToTarget(ExtractorInput extractorInput, byte[] bArr, int i, int i2) throws IOException, InterruptedException {
        int min = Math.min(i2, this.sampleStrippedBytes.bytesLeft());
        extractorInput.readFully(bArr, i + min, i2 - min);
        if (min > 0) {
            this.sampleStrippedBytes.readBytes(bArr, i, min);
        }
        this.sampleBytesRead += i2;
    }

    private int readToOutput(ExtractorInput extractorInput, TrackOutput trackOutput, int i) throws IOException, InterruptedException {
        int i2;
        int bytesLeft = this.sampleStrippedBytes.bytesLeft();
        if (bytesLeft > 0) {
            i2 = Math.min(i, bytesLeft);
            trackOutput.sampleData(this.sampleStrippedBytes, i2);
        } else {
            i2 = trackOutput.sampleData(extractorInput, i, false);
        }
        this.sampleBytesRead += i2;
        this.sampleBytesWritten += i2;
        return i2;
    }

    private SeekMap buildSeekMap() {
        if (!(this.segmentContentPosition == -1 || this.durationUs == -9223372036854775807L)) {
            LongArray longArray = this.cueTimesUs;
            if (!(longArray == null || longArray.size() == 0)) {
                LongArray longArray2 = this.cueClusterPositions;
                if (longArray2 != null && longArray2.size() == this.cueTimesUs.size()) {
                    int size = this.cueTimesUs.size();
                    int[] iArr = new int[size];
                    long[] jArr = new long[size];
                    long[] jArr2 = new long[size];
                    long[] jArr3 = new long[size];
                    int i = 0;
                    for (int i2 = 0; i2 < size; i2++) {
                        jArr3[i2] = this.cueTimesUs.get(i2);
                        jArr[i2] = this.segmentContentPosition + this.cueClusterPositions.get(i2);
                    }
                    while (true) {
                        int i3 = size - 1;
                        if (i < i3) {
                            int i4 = i + 1;
                            iArr[i] = (int) (jArr[i4] - jArr[i]);
                            jArr2[i] = jArr3[i4] - jArr3[i];
                            i = i4;
                        } else {
                            iArr[i3] = (int) ((this.segmentContentPosition + this.segmentContentSize) - jArr[i3]);
                            jArr2[i3] = this.durationUs - jArr3[i3];
                            this.cueTimesUs = null;
                            this.cueClusterPositions = null;
                            return new ChunkIndex(iArr, jArr, jArr2, jArr3);
                        }
                    }
                }
            }
        }
        this.cueTimesUs = null;
        this.cueClusterPositions = null;
        return new Unseekable(this.durationUs);
    }

    private boolean maybeSeekForCues(PositionHolder positionHolder, long j) {
        if (this.seekForCues) {
            this.seekPositionAfterBuildingCues = j;
            positionHolder.position = this.cuesContentPosition;
            this.seekForCues = false;
            return true;
        }
        if (this.sentSeekMap) {
            long j2 = this.seekPositionAfterBuildingCues;
            if (j2 != -1) {
                positionHolder.position = j2;
                this.seekPositionAfterBuildingCues = -1;
                return true;
            }
        }
        return false;
    }

    private long scaleTimecodeToUs(long j) throws ParserException {
        long j2 = this.timecodeScale;
        if (j2 != -9223372036854775807L) {
            return Util.scaleLargeTimestamp(j, j2, 1000);
        }
        throw new ParserException("Can't scale timecode prior to timecodeScale being set.");
    }

    private static boolean isCodecSupported(String str) {
        return CODEC_ID_VP8.equals(str) || CODEC_ID_VP9.equals(str) || CODEC_ID_MPEG2.equals(str) || CODEC_ID_MPEG4_SP.equals(str) || CODEC_ID_MPEG4_ASP.equals(str) || CODEC_ID_MPEG4_AP.equals(str) || CODEC_ID_H264.equals(str) || CODEC_ID_H265.equals(str) || CODEC_ID_FOURCC.equals(str) || CODEC_ID_THEORA.equals(str) || CODEC_ID_OPUS.equals(str) || CODEC_ID_VORBIS.equals(str) || CODEC_ID_AAC.equals(str) || CODEC_ID_MP2.equals(str) || CODEC_ID_MP3.equals(str) || CODEC_ID_AC3.equals(str) || CODEC_ID_E_AC3.equals(str) || CODEC_ID_TRUEHD.equals(str) || CODEC_ID_DTS.equals(str) || CODEC_ID_DTS_EXPRESS.equals(str) || CODEC_ID_DTS_LOSSLESS.equals(str) || CODEC_ID_FLAC.equals(str) || CODEC_ID_ACM.equals(str) || CODEC_ID_PCM_INT_LIT.equals(str) || CODEC_ID_SUBRIP.equals(str) || CODEC_ID_ASS.equals(str) || CODEC_ID_VOBSUB.equals(str) || CODEC_ID_PGS.equals(str) || CODEC_ID_DVBSUB.equals(str);
    }

    private static int[] ensureArrayCapacity(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length * 2, i)];
    }
}
