package com.google.android.exoplayer2.extractor.ts;

import android.support.annotation.Nullable;
import android.util.Pair;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.TrackIdGenerator;
import com.google.android.exoplayer2.util.CodecSpecificDataUtil;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableBitArray;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;

public final class LatmReader implements ElementaryStreamReader {
    private static final int INITIAL_BUFFER_SIZE = 1024;
    private static final int STATE_FINDING_SYNC_1 = 0;
    private static final int STATE_FINDING_SYNC_2 = 1;
    private static final int STATE_READING_HEADER = 2;
    private static final int STATE_READING_SAMPLE = 3;
    private static final int SYNC_BYTE_FIRST = 86;
    private static final int SYNC_BYTE_SECOND = 224;
    private int audioMuxVersionA;
    private int bytesRead;
    private int channelCount;
    private Format format;
    private String formatId;
    private int frameLengthType;
    private final String language;
    private int numSubframes;
    private long otherDataLenBits;
    private boolean otherDataPresent;
    private TrackOutput output;
    private final ParsableBitArray sampleBitArray = new ParsableBitArray(this.sampleDataBuffer.data);
    private final ParsableByteArray sampleDataBuffer = new ParsableByteArray((int) INITIAL_BUFFER_SIZE);
    private long sampleDurationUs;
    private int sampleRateHz;
    private int sampleSize;
    private int secondHeaderByte;
    private int state;
    private boolean streamMuxRead;
    private long timeUs;

    public void packetFinished() {
    }

    public LatmReader(@Nullable String str) {
        this.language = str;
    }

    public void seek() {
        this.state = 0;
        this.streamMuxRead = false;
    }

    public void createTracks(ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator) {
        trackIdGenerator.generateNewId();
        this.output = extractorOutput.track(trackIdGenerator.getTrackId(), 1);
        this.formatId = trackIdGenerator.getFormatId();
    }

    public void packetStarted(long j, int i) {
        this.timeUs = j;
    }

    public void consume(ParsableByteArray parsableByteArray) throws ParserException {
        while (parsableByteArray.bytesLeft() > 0) {
            switch (this.state) {
                case 0:
                    if (parsableByteArray.readUnsignedByte() != 86) {
                        break;
                    } else {
                        this.state = 1;
                        break;
                    }
                case 1:
                    int readUnsignedByte = parsableByteArray.readUnsignedByte();
                    if ((readUnsignedByte & 224) != 224) {
                        if (readUnsignedByte == 86) {
                            break;
                        } else {
                            this.state = 0;
                            break;
                        }
                    } else {
                        this.secondHeaderByte = readUnsignedByte;
                        this.state = 2;
                        break;
                    }
                case 2:
                    this.sampleSize = ((this.secondHeaderByte & -225) << 8) | parsableByteArray.readUnsignedByte();
                    if (this.sampleSize > this.sampleDataBuffer.data.length) {
                        resetBufferForSize(this.sampleSize);
                    }
                    this.bytesRead = 0;
                    this.state = 3;
                    break;
                case 3:
                    int min = Math.min(parsableByteArray.bytesLeft(), this.sampleSize - this.bytesRead);
                    parsableByteArray.readBytes(this.sampleBitArray.data, this.bytesRead, min);
                    this.bytesRead += min;
                    if (this.bytesRead != this.sampleSize) {
                        break;
                    } else {
                        this.sampleBitArray.setPosition(0);
                        parseAudioMuxElement(this.sampleBitArray);
                        this.state = 0;
                        break;
                    }
                default:
                    throw new IllegalStateException();
            }
        }
    }

    private void parseAudioMuxElement(ParsableBitArray parsableBitArray) throws ParserException {
        if (!parsableBitArray.readBit()) {
            this.streamMuxRead = true;
            parseStreamMuxConfig(parsableBitArray);
        } else if (!this.streamMuxRead) {
            return;
        }
        if (this.audioMuxVersionA != 0) {
            throw new ParserException();
        } else if (this.numSubframes == 0) {
            parsePayloadMux(parsableBitArray, parsePayloadLengthInfo(parsableBitArray));
            if (this.otherDataPresent) {
                parsableBitArray.skipBits((int) this.otherDataLenBits);
            }
        } else {
            throw new ParserException();
        }
    }

    private void parseStreamMuxConfig(ParsableBitArray parsableBitArray) throws ParserException {
        boolean readBit;
        ParsableBitArray parsableBitArray2 = parsableBitArray;
        int readBits = parsableBitArray2.readBits(1);
        this.audioMuxVersionA = readBits == 1 ? parsableBitArray2.readBits(1) : 0;
        if (this.audioMuxVersionA == 0) {
            if (readBits == 1) {
                latmGetValue(parsableBitArray);
            }
            if (parsableBitArray.readBit()) {
                this.numSubframes = parsableBitArray2.readBits(6);
                int readBits2 = parsableBitArray2.readBits(4);
                int readBits3 = parsableBitArray2.readBits(3);
                if (readBits2 == 0 && readBits3 == 0) {
                    if (readBits == 0) {
                        int position = parsableBitArray.getPosition();
                        int parseAudioSpecificConfig = parseAudioSpecificConfig(parsableBitArray);
                        parsableBitArray2.setPosition(position);
                        byte[] bArr = new byte[((parseAudioSpecificConfig + 7) / 8)];
                        parsableBitArray2.readBits(bArr, 0, parseAudioSpecificConfig);
                        Format createAudioSampleFormat = Format.createAudioSampleFormat(this.formatId, MimeTypes.AUDIO_AAC, null, -1, -1, this.channelCount, this.sampleRateHz, Collections.singletonList(bArr), null, 0, this.language);
                        if (!createAudioSampleFormat.equals(this.format)) {
                            this.format = createAudioSampleFormat;
                            this.sampleDurationUs = 1024000000 / ((long) createAudioSampleFormat.sampleRate);
                            this.output.format(createAudioSampleFormat);
                        }
                    } else {
                        parsableBitArray2.skipBits(((int) latmGetValue(parsableBitArray)) - parseAudioSpecificConfig(parsableBitArray));
                    }
                    parseFrameLength(parsableBitArray);
                    this.otherDataPresent = parsableBitArray.readBit();
                    this.otherDataLenBits = 0;
                    if (this.otherDataPresent) {
                        if (readBits == 1) {
                            this.otherDataLenBits = latmGetValue(parsableBitArray);
                        } else {
                            do {
                                readBit = parsableBitArray.readBit();
                                this.otherDataLenBits = (this.otherDataLenBits << 8) + ((long) parsableBitArray2.readBits(8));
                            } while (readBit);
                        }
                    }
                    if (parsableBitArray.readBit()) {
                        parsableBitArray2.skipBits(8);
                        return;
                    }
                    return;
                }
                throw new ParserException();
            }
            throw new ParserException();
        }
        throw new ParserException();
    }

    private void parseFrameLength(ParsableBitArray parsableBitArray) {
        this.frameLengthType = parsableBitArray.readBits(3);
        switch (this.frameLengthType) {
            case 0:
                parsableBitArray.skipBits(8);
                return;
            case 1:
                parsableBitArray.skipBits(9);
                return;
            case 3:
            case 4:
            case 5:
                parsableBitArray.skipBits(6);
                return;
            case 6:
            case 7:
                parsableBitArray.skipBits(1);
                return;
            default:
                throw new IllegalStateException();
        }
    }

    private int parseAudioSpecificConfig(ParsableBitArray parsableBitArray) throws ParserException {
        int bitsLeft = parsableBitArray.bitsLeft();
        Pair parseAacAudioSpecificConfig = CodecSpecificDataUtil.parseAacAudioSpecificConfig(parsableBitArray, true);
        this.sampleRateHz = ((Integer) parseAacAudioSpecificConfig.first).intValue();
        this.channelCount = ((Integer) parseAacAudioSpecificConfig.second).intValue();
        return bitsLeft - parsableBitArray.bitsLeft();
    }

    private int parsePayloadLengthInfo(ParsableBitArray parsableBitArray) throws ParserException {
        int readBits;
        if (this.frameLengthType == 0) {
            int i = 0;
            do {
                readBits = parsableBitArray.readBits(8);
                i += readBits;
            } while (readBits == 255);
            return i;
        }
        throw new ParserException();
    }

    private void parsePayloadMux(ParsableBitArray parsableBitArray, int i) {
        int position = parsableBitArray.getPosition();
        if ((position & 7) == 0) {
            this.sampleDataBuffer.setPosition(position >> 3);
        } else {
            parsableBitArray.readBits(this.sampleDataBuffer.data, 0, i * 8);
            this.sampleDataBuffer.setPosition(0);
        }
        this.output.sampleData(this.sampleDataBuffer, i);
        this.output.sampleMetadata(this.timeUs, 1, i, 0, null);
        this.timeUs += this.sampleDurationUs;
    }

    private void resetBufferForSize(int i) {
        this.sampleDataBuffer.reset(i);
        this.sampleBitArray.reset(this.sampleDataBuffer.data);
    }

    private static long latmGetValue(ParsableBitArray parsableBitArray) {
        return (long) parsableBitArray.readBits((parsableBitArray.readBits(2) + 1) * 8);
    }
}
