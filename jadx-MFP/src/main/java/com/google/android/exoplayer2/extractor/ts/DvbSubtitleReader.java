package com.google.android.exoplayer2.extractor.ts;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ExtractorOutput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.DvbSubtitleInfo;
import com.google.android.exoplayer2.extractor.ts.TsPayloadReader.TrackIdGenerator;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.util.Collections;
import java.util.List;

public final class DvbSubtitleReader implements ElementaryStreamReader {
    private int bytesToCheck;
    private final TrackOutput[] outputs;
    private int sampleBytesWritten;
    private long sampleTimeUs;
    private final List<DvbSubtitleInfo> subtitleInfos;
    private boolean writingSample;

    public DvbSubtitleReader(List<DvbSubtitleInfo> list) {
        this.subtitleInfos = list;
        this.outputs = new TrackOutput[list.size()];
    }

    public void seek() {
        this.writingSample = false;
    }

    public void createTracks(ExtractorOutput extractorOutput, TrackIdGenerator trackIdGenerator) {
        for (int i = 0; i < this.outputs.length; i++) {
            DvbSubtitleInfo dvbSubtitleInfo = (DvbSubtitleInfo) this.subtitleInfos.get(i);
            trackIdGenerator.generateNewId();
            TrackOutput track = extractorOutput.track(trackIdGenerator.getTrackId(), 3);
            track.format(Format.createImageSampleFormat(trackIdGenerator.getFormatId(), MimeTypes.APPLICATION_DVBSUBS, null, -1, 0, Collections.singletonList(dvbSubtitleInfo.initializationData), dvbSubtitleInfo.language, null));
            this.outputs[i] = track;
        }
    }

    public void packetStarted(long j, int i) {
        if ((i & 4) != 0) {
            this.writingSample = true;
            this.sampleTimeUs = j;
            this.sampleBytesWritten = 0;
            this.bytesToCheck = 2;
        }
    }

    public void packetFinished() {
        if (this.writingSample) {
            for (TrackOutput sampleMetadata : this.outputs) {
                sampleMetadata.sampleMetadata(this.sampleTimeUs, 1, this.sampleBytesWritten, 0, null);
            }
            this.writingSample = false;
        }
    }

    public void consume(ParsableByteArray parsableByteArray) {
        TrackOutput[] trackOutputArr;
        if (this.writingSample && (this.bytesToCheck != 2 || checkNextByte(parsableByteArray, 32))) {
            if (this.bytesToCheck != 1 || checkNextByte(parsableByteArray, 0)) {
                int position = parsableByteArray.getPosition();
                int bytesLeft = parsableByteArray.bytesLeft();
                for (TrackOutput trackOutput : this.outputs) {
                    parsableByteArray.setPosition(position);
                    trackOutput.sampleData(parsableByteArray, bytesLeft);
                }
                this.sampleBytesWritten += bytesLeft;
            }
        }
    }

    private boolean checkNextByte(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.bytesLeft() == 0) {
            return false;
        }
        if (parsableByteArray.readUnsignedByte() != i) {
            this.writingSample = false;
        }
        this.bytesToCheck--;
        return this.writingSample;
    }
}
