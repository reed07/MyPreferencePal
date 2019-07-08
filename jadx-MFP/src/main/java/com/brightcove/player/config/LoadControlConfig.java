package com.brightcove.player.config;

import android.support.annotation.NonNull;
import com.google.android.exoplayer2.DefaultLoadControl;

public class LoadControlConfig {
    private final AllocatorConfig allocatorConfig;
    private final int bufferForPlaybackAfterRebufferMs;
    private final int bufferForPlaybackMs;
    private final int maxBufferMs;
    private final int minBufferMs;
    private final boolean prioritizeTimeOverSizeThresholds;
    private final int targetBufferBytes;

    public static class Builder {
        private AllocatorConfig allocatorConfig = new com.brightcove.player.config.AllocatorConfig.Builder().build();
        private int bufferForPlaybackAfterRebufferMs = 5000;
        private int bufferForPlaybackMs = 2500;
        private int maxBufferMs = DefaultLoadControl.DEFAULT_MAX_BUFFER_MS;
        private int minBufferMs = 15000;
        private boolean prioritizeTimeOverSizeThresholds = true;
        private int targetBufferBytes = -1;

        public Builder setMinBufferMs(int i) {
            this.minBufferMs = i;
            return this;
        }

        public Builder setMaxBufferMs(int i) {
            this.maxBufferMs = i;
            return this;
        }

        public Builder setBufferForPlaybackMs(int i) {
            this.bufferForPlaybackMs = i;
            return this;
        }

        public Builder setBufferForPlaybackAfterRebufferMs(int i) {
            this.bufferForPlaybackAfterRebufferMs = i;
            return this;
        }

        public Builder setTargetBufferBytes(int i) {
            this.targetBufferBytes = i;
            return this;
        }

        public Builder setPrioritizeTimeOverSizeThresholds(boolean z) {
            this.prioritizeTimeOverSizeThresholds = z;
            return this;
        }

        public Builder setAllocatorConfig(AllocatorConfig allocatorConfig2) {
            if (allocatorConfig2 != null) {
                this.allocatorConfig = allocatorConfig2;
                return this;
            }
            throw new IllegalArgumentException("AllocatorConfig must not be null");
        }

        public LoadControlConfig build() {
            LoadControlConfig loadControlConfig = new LoadControlConfig(this.minBufferMs, this.maxBufferMs, this.bufferForPlaybackMs, this.bufferForPlaybackAfterRebufferMs, this.targetBufferBytes, this.prioritizeTimeOverSizeThresholds, this.allocatorConfig);
            return loadControlConfig;
        }
    }

    private LoadControlConfig(int i, int i2, int i3, int i4, int i5, boolean z, AllocatorConfig allocatorConfig2) {
        this.minBufferMs = i;
        this.maxBufferMs = i2;
        this.bufferForPlaybackMs = i3;
        this.bufferForPlaybackAfterRebufferMs = i4;
        this.targetBufferBytes = i5;
        this.prioritizeTimeOverSizeThresholds = z;
        this.allocatorConfig = allocatorConfig2;
    }

    public int getMinBufferMs() {
        return this.minBufferMs;
    }

    public int getMaxBufferMs() {
        return this.maxBufferMs;
    }

    public int getBufferForPlaybackMs() {
        return this.bufferForPlaybackMs;
    }

    public int getBufferForPlaybackAfterRebufferMs() {
        return this.bufferForPlaybackAfterRebufferMs;
    }

    public int getTargetBufferBytes() {
        return this.targetBufferBytes;
    }

    public boolean isPrioritizeTimeOverSizeThresholds() {
        return this.prioritizeTimeOverSizeThresholds;
    }

    @NonNull
    public AllocatorConfig getAllocatorConfig() {
        return this.allocatorConfig;
    }
}
