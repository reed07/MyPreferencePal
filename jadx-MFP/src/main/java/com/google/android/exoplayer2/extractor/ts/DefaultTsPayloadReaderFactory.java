package com.google.android.exoplayer2.extractor.ts;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.EsInfo;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.Factory;
import com.google.android.exoplayer2.text.cea.Cea708InitializationData;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.common.primitives.SignedBytes;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class DefaultTsPayloadReaderFactory implements Factory {
    private static final int DESCRIPTOR_TAG_CAPTION_SERVICE = 134;
    public static final int FLAG_ALLOW_NON_IDR_KEYFRAMES = 1;
    public static final int FLAG_DETECT_ACCESS_UNITS = 8;
    public static final int FLAG_IGNORE_AAC_STREAM = 2;
    public static final int FLAG_IGNORE_H264_STREAM = 4;
    public static final int FLAG_IGNORE_HDMV_DTS_STREAM = 64;
    public static final int FLAG_IGNORE_SPLICE_INFO_STREAM = 16;
    public static final int FLAG_OVERRIDE_CAPTION_DESCRIPTORS = 32;
    private final List<Format> closedCaptionFormats;
    private final int flags;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Flags {
    }

    public DefaultTsPayloadReaderFactory() {
        this(0);
    }

    public DefaultTsPayloadReaderFactory(int i) {
        this(i, Collections.singletonList(Format.createTextSampleFormat(null, MimeTypes.APPLICATION_CEA608, 0, null)));
    }

    public DefaultTsPayloadReaderFactory(int i, List<Format> list) {
        this.flags = i;
        this.closedCaptionFormats = list;
    }

    public SparseArray<TsPayloadReader> createInitialPayloadReaders() {
        return new SparseArray<>();
    }

    public TsPayloadReader createPayloadReader(int i, EsInfo esInfo) {
        TsPayloadReader tsPayloadReader = null;
        switch (i) {
            case 2:
                return new PesReader(new H262Reader(buildUserDataReader(esInfo)));
            case 3:
            case 4:
                return new PesReader(new MpegAudioReader(esInfo.language));
            case 15:
                if (!isSet(2)) {
                    tsPayloadReader = new PesReader(new AdtsReader(false, esInfo.language));
                }
                return tsPayloadReader;
            case 17:
                if (!isSet(2)) {
                    tsPayloadReader = new PesReader(new LatmReader(esInfo.language));
                }
                return tsPayloadReader;
            case 21:
                return new PesReader(new Id3Reader());
            case 27:
                if (!isSet(4)) {
                    tsPayloadReader = new PesReader(new H264Reader(buildSeiReader(esInfo), isSet(1), isSet(8)));
                }
                return tsPayloadReader;
            case 36:
                return new PesReader(new H265Reader(buildSeiReader(esInfo)));
            case 89:
                return new PesReader(new DvbSubtitleReader(esInfo.dvbSubtitleInfos));
            case 129:
            case 135:
                return new PesReader(new Ac3Reader(esInfo.language));
            case 130:
                if (isSet(64)) {
                    return null;
                }
                break;
            case 134:
                if (!isSet(16)) {
                    tsPayloadReader = new SectionReader(new SpliceInfoSectionReader());
                }
                return tsPayloadReader;
            case 138:
                break;
            default:
                return null;
        }
        return new PesReader(new DtsReader(esInfo.language));
    }

    private SeiReader buildSeiReader(EsInfo esInfo) {
        return new SeiReader(getClosedCaptionFormats(esInfo));
    }

    private UserDataReader buildUserDataReader(EsInfo esInfo) {
        return new UserDataReader(getClosedCaptionFormats(esInfo));
    }

    private List<Format> getClosedCaptionFormats(EsInfo esInfo) {
        int i;
        String str;
        List list;
        if (isSet(32)) {
            return this.closedCaptionFormats;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(esInfo.descriptorBytes);
        List<Format> list2 = this.closedCaptionFormats;
        while (parsableByteArray.bytesLeft() > 0) {
            int position = parsableByteArray.getPosition() + parsableByteArray.readUnsignedByte();
            if (parsableByteArray.readUnsignedByte() == 134) {
                list2 = new ArrayList<>();
                int readUnsignedByte = parsableByteArray.readUnsignedByte() & 31;
                for (int i2 = 0; i2 < readUnsignedByte; i2++) {
                    String readString = parsableByteArray.readString(3);
                    int readUnsignedByte2 = parsableByteArray.readUnsignedByte();
                    boolean z = true;
                    boolean z2 = (readUnsignedByte2 & 128) != 0;
                    if (z2) {
                        i = readUnsignedByte2 & 63;
                        str = MimeTypes.APPLICATION_CEA708;
                    } else {
                        str = MimeTypes.APPLICATION_CEA608;
                        i = 1;
                    }
                    byte readUnsignedByte3 = (byte) parsableByteArray.readUnsignedByte();
                    parsableByteArray.skipBytes(1);
                    if (z2) {
                        if ((readUnsignedByte3 & SignedBytes.MAX_POWER_OF_TWO) == 0) {
                            z = false;
                        }
                        list = Cea708InitializationData.buildData(z);
                    } else {
                        list = null;
                    }
                    list2.add(Format.createTextSampleFormat(null, str, null, -1, 0, readString, i, null, Long.MAX_VALUE, list));
                }
            }
            parsableByteArray.setPosition(position);
        }
        return list2;
    }

    private boolean isSet(int i) {
        return (i & this.flags) != 0;
    }
}
