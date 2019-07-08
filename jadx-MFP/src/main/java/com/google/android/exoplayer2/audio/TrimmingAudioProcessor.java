package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor.UnhandledFormatException;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class TrimmingAudioProcessor implements AudioProcessor {
    private static final int OUTPUT_ENCODING = 2;
    private ByteBuffer buffer = EMPTY_BUFFER;
    private int bytesPerFrame;
    private int channelCount = -1;
    private byte[] endBuffer = Util.EMPTY_BYTE_ARRAY;
    private int endBufferSize;
    private boolean inputEnded;
    private boolean isActive;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    private int pendingTrimStartBytes;
    private boolean receivedInputSinceConfigure;
    private int sampleRateHz = -1;
    private int trimEndFrames;
    private int trimStartFrames;
    private long trimmedFrameCount;

    public int getOutputEncoding() {
        return 2;
    }

    public void setTrimFrameCount(int i, int i2) {
        this.trimStartFrames = i;
        this.trimEndFrames = i2;
    }

    public void resetTrimmedFrameCount() {
        this.trimmedFrameCount = 0;
    }

    public long getTrimmedFrameCount() {
        return this.trimmedFrameCount;
    }

    public boolean configure(int i, int i2, int i3) throws UnhandledFormatException {
        if (i3 == 2) {
            int i4 = this.endBufferSize;
            if (i4 > 0) {
                this.trimmedFrameCount += (long) (i4 / this.bytesPerFrame);
            }
            this.channelCount = i2;
            this.sampleRateHz = i;
            this.bytesPerFrame = Util.getPcmFrameSize(2, i2);
            int i5 = this.trimEndFrames;
            int i6 = this.bytesPerFrame;
            this.endBuffer = new byte[(i5 * i6)];
            this.endBufferSize = 0;
            int i7 = this.trimStartFrames;
            this.pendingTrimStartBytes = i6 * i7;
            boolean z = this.isActive;
            this.isActive = (i7 == 0 && i5 == 0) ? false : true;
            this.receivedInputSinceConfigure = false;
            if (z != this.isActive) {
                return true;
            }
            return false;
        }
        throw new UnhandledFormatException(i, i2, i3);
    }

    public boolean isActive() {
        return this.isActive;
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i = limit - position;
        if (i != 0) {
            this.receivedInputSinceConfigure = true;
            int min = Math.min(i, this.pendingTrimStartBytes);
            this.trimmedFrameCount += (long) (min / this.bytesPerFrame);
            this.pendingTrimStartBytes -= min;
            byteBuffer.position(position + min);
            if (this.pendingTrimStartBytes <= 0) {
                int i2 = i - min;
                int length = (this.endBufferSize + i2) - this.endBuffer.length;
                if (this.buffer.capacity() < length) {
                    this.buffer = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
                } else {
                    this.buffer.clear();
                }
                int constrainValue = Util.constrainValue(length, 0, this.endBufferSize);
                this.buffer.put(this.endBuffer, 0, constrainValue);
                int constrainValue2 = Util.constrainValue(length - constrainValue, 0, i2);
                byteBuffer.limit(byteBuffer.position() + constrainValue2);
                this.buffer.put(byteBuffer);
                byteBuffer.limit(limit);
                int i3 = i2 - constrainValue2;
                this.endBufferSize -= constrainValue;
                byte[] bArr = this.endBuffer;
                System.arraycopy(bArr, constrainValue, bArr, 0, this.endBufferSize);
                byteBuffer.get(this.endBuffer, this.endBufferSize, i3);
                this.endBufferSize += i3;
                this.buffer.flip();
                this.outputBuffer = this.buffer;
            }
        }
    }

    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        if (this.inputEnded && this.endBufferSize > 0 && byteBuffer == EMPTY_BUFFER) {
            int capacity = this.buffer.capacity();
            int i = this.endBufferSize;
            if (capacity < i) {
                this.buffer = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
            } else {
                this.buffer.clear();
            }
            this.buffer.put(this.endBuffer, 0, this.endBufferSize);
            this.endBufferSize = 0;
            this.buffer.flip();
            byteBuffer = this.buffer;
        }
        this.outputBuffer = EMPTY_BUFFER;
        return byteBuffer;
    }

    public boolean isEnded() {
        return this.inputEnded && this.endBufferSize == 0 && this.outputBuffer == EMPTY_BUFFER;
    }

    public void flush() {
        this.outputBuffer = EMPTY_BUFFER;
        this.inputEnded = false;
        if (this.receivedInputSinceConfigure) {
            this.pendingTrimStartBytes = 0;
        }
        this.endBufferSize = 0;
    }

    public void reset() {
        flush();
        this.buffer = EMPTY_BUFFER;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.endBuffer = Util.EMPTY_BYTE_ARRAY;
    }
}
