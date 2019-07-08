package com.google.android.exoplayer2.audio;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.audio.AudioProcessor.UnhandledFormatException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public final class SonicAudioProcessor implements AudioProcessor {
    private static final float CLOSE_THRESHOLD = 0.01f;
    public static final float MAXIMUM_PITCH = 8.0f;
    public static final float MAXIMUM_SPEED = 8.0f;
    public static final float MINIMUM_PITCH = 0.1f;
    public static final float MINIMUM_SPEED = 0.1f;
    private static final int MIN_BYTES_FOR_SPEEDUP_CALCULATION = 1024;
    public static final int SAMPLE_RATE_NO_CHANGE = -1;
    private ByteBuffer buffer = EMPTY_BUFFER;
    private int channelCount = -1;
    private long inputBytes;
    private boolean inputEnded;
    private ByteBuffer outputBuffer = EMPTY_BUFFER;
    private long outputBytes;
    private int outputSampleRateHz = -1;
    private int pendingOutputSampleRateHz = -1;
    private float pitch = 1.0f;
    private int sampleRateHz = -1;
    private ShortBuffer shortBuffer = this.buffer.asShortBuffer();
    @Nullable
    private Sonic sonic;
    private float speed = 1.0f;

    public int getOutputEncoding() {
        return 2;
    }

    public float setSpeed(float f) {
        float constrainValue = Util.constrainValue(f, 0.1f, 8.0f);
        if (this.speed != constrainValue) {
            this.speed = constrainValue;
            this.sonic = null;
        }
        flush();
        return constrainValue;
    }

    public float setPitch(float f) {
        float constrainValue = Util.constrainValue(f, 0.1f, 8.0f);
        if (this.pitch != constrainValue) {
            this.pitch = constrainValue;
            this.sonic = null;
        }
        flush();
        return constrainValue;
    }

    public void setOutputSampleRateHz(int i) {
        this.pendingOutputSampleRateHz = i;
    }

    public long scaleDurationForSpeedup(long j) {
        long j2;
        long j3 = this.outputBytes;
        if (j3 < 1024) {
            return (long) (((double) this.speed) * ((double) j));
        }
        int i = this.outputSampleRateHz;
        int i2 = this.sampleRateHz;
        if (i == i2) {
            j2 = Util.scaleLargeTimestamp(j, this.inputBytes, j3);
        } else {
            j2 = Util.scaleLargeTimestamp(j, this.inputBytes * ((long) i), j3 * ((long) i2));
        }
        return j2;
    }

    public boolean configure(int i, int i2, int i3) throws UnhandledFormatException {
        if (i3 == 2) {
            int i4 = this.pendingOutputSampleRateHz;
            if (i4 == -1) {
                i4 = i;
            }
            if (this.sampleRateHz == i && this.channelCount == i2 && this.outputSampleRateHz == i4) {
                return false;
            }
            this.sampleRateHz = i;
            this.channelCount = i2;
            this.outputSampleRateHz = i4;
            this.sonic = null;
            return true;
        }
        throw new UnhandledFormatException(i, i2, i3);
    }

    public boolean isActive() {
        return this.sampleRateHz != -1 && (Math.abs(this.speed - 1.0f) >= CLOSE_THRESHOLD || Math.abs(this.pitch - 1.0f) >= CLOSE_THRESHOLD || this.outputSampleRateHz != this.sampleRateHz);
    }

    public int getOutputChannelCount() {
        return this.channelCount;
    }

    public int getOutputSampleRateHz() {
        return this.outputSampleRateHz;
    }

    public void queueInput(ByteBuffer byteBuffer) {
        Assertions.checkState(this.sonic != null);
        if (byteBuffer.hasRemaining()) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.inputBytes += (long) remaining;
            this.sonic.queueInput(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
        int framesAvailable = this.sonic.getFramesAvailable() * this.channelCount * 2;
        if (framesAvailable > 0) {
            if (this.buffer.capacity() < framesAvailable) {
                this.buffer = ByteBuffer.allocateDirect(framesAvailable).order(ByteOrder.nativeOrder());
                this.shortBuffer = this.buffer.asShortBuffer();
            } else {
                this.buffer.clear();
                this.shortBuffer.clear();
            }
            this.sonic.getOutput(this.shortBuffer);
            this.outputBytes += (long) framesAvailable;
            this.buffer.limit(framesAvailable);
            this.outputBuffer = this.buffer;
        }
    }

    public void queueEndOfStream() {
        Assertions.checkState(this.sonic != null);
        this.sonic.queueEndOfStream();
        this.inputEnded = true;
    }

    public ByteBuffer getOutput() {
        ByteBuffer byteBuffer = this.outputBuffer;
        this.outputBuffer = EMPTY_BUFFER;
        return byteBuffer;
    }

    public boolean isEnded() {
        if (this.inputEnded) {
            Sonic sonic2 = this.sonic;
            if (sonic2 == null || sonic2.getFramesAvailable() == 0) {
                return true;
            }
        }
        return false;
    }

    public void flush() {
        if (isActive()) {
            Sonic sonic2 = this.sonic;
            if (sonic2 == null) {
                Sonic sonic3 = new Sonic(this.sampleRateHz, this.channelCount, this.speed, this.pitch, this.outputSampleRateHz);
                this.sonic = sonic3;
            } else {
                sonic2.flush();
            }
        }
        this.outputBuffer = EMPTY_BUFFER;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }

    public void reset() {
        this.speed = 1.0f;
        this.pitch = 1.0f;
        this.channelCount = -1;
        this.sampleRateHz = -1;
        this.outputSampleRateHz = -1;
        this.buffer = EMPTY_BUFFER;
        this.shortBuffer = this.buffer.asShortBuffer();
        this.outputBuffer = EMPTY_BUFFER;
        this.pendingOutputSampleRateHz = -1;
        this.sonic = null;
        this.inputBytes = 0;
        this.outputBytes = 0;
        this.inputEnded = false;
    }
}
