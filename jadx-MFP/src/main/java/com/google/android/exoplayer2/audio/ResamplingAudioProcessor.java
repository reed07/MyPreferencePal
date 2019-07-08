package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor.UnhandledFormatException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

final class ResamplingAudioProcessor implements AudioProcessor {
    private ByteBuffer buffer = EMPTY_BUFFER;
    private int channelCount = -1;
    private int encoding = 0;
    private boolean inputEnded;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    private int sampleRateHz = -1;

    public int getOutputEncoding() {
        return 2;
    }

    public boolean configure(int i, int i2, int i3) throws UnhandledFormatException {
        if (i3 != 3 && i3 != 2 && i3 != Integer.MIN_VALUE && i3 != 1073741824) {
            throw new UnhandledFormatException(i, i2, i3);
        } else if (this.sampleRateHz == i && this.channelCount == i2 && this.encoding == i3) {
            return false;
        } else {
            this.sampleRateHz = i;
            this.channelCount = i2;
            this.encoding = i3;
            return true;
        }
    }

    public boolean isActive() {
        int i = this.encoding;
        return (i == 0 || i == 2) ? false : true;
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputSampleRateHz() {
        return this.sampleRateHz;
    }

    public void queueInput(ByteBuffer byteBuffer) {
        int i;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i2 = limit - position;
        int i3 = this.encoding;
        if (i3 == Integer.MIN_VALUE) {
            i = (i2 / 3) * 2;
        } else if (i3 == 3) {
            i = i2 * 2;
        } else if (i3 == 1073741824) {
            i = i2 / 2;
        } else {
            throw new IllegalStateException();
        }
        if (this.buffer.capacity() < i) {
            this.buffer = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.buffer.clear();
        }
        int i4 = this.encoding;
        if (i4 == Integer.MIN_VALUE) {
            while (position < limit) {
                this.buffer.put(byteBuffer.get(position + 1));
                this.buffer.put(byteBuffer.get(position + 2));
                position += 3;
            }
        } else if (i4 == 3) {
            while (position < limit) {
                this.buffer.put(0);
                this.buffer.put((byte) ((byteBuffer.get(position) & 255) - 128));
                position++;
            }
        } else if (i4 == 1073741824) {
            while (position < limit) {
                this.buffer.put(byteBuffer.get(position + 2));
                this.buffer.put(byteBuffer.get(position + 3));
                position += 4;
            }
        } else {
            throw new IllegalStateException();
        }
        byteBuffer.position(byteBuffer.limit());
        this.buffer.flip();
        this.outputBuffer = this.buffer;
    }

    public void queueEndOfStream() {
        this.inputEnded = true;
    }

    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = EMPTY_BUFFER;
        return byteBuffer;
    }

    public boolean isEnded() {
        return this.inputEnded && this.outputBuffer == EMPTY_BUFFER;
    }

    public void flush() {
        this.outputBuffer = EMPTY_BUFFER;
        this.inputEnded = false;
    }

    public void reset() {
        flush();
        this.sampleRateHz = -1;
        this.channelCount = -1;
        this.encoding = 0;
        this.buffer = EMPTY_BUFFER;
    }
}
