package com.brightcove.player.model;

public class RendererConfig {
    /* access modifiers changed from: private */
    public int bufferSegmentCount;
    /* access modifiers changed from: private */
    public int bufferSegmentSize;
    /* access modifiers changed from: private */
    public int httpConnectTimeoutMillis;
    /* access modifiers changed from: private */
    public int httpReadTimeoutMillis;
    /* access modifiers changed from: private */
    public boolean restrictHdContentToWidevineL1;

    public static final class Builder {
        private int bufferSegmentCount = -1;
        private int bufferSegmentSize = -1;
        private int httpConnectTimeoutMillis;
        private int httpReadTimeoutMillis;
        private boolean restrictHdContentToWidevineL1 = true;

        public Builder setHttpReadTimeoutMillis(int i) {
            this.httpReadTimeoutMillis = i;
            return this;
        }

        public Builder setHttpConnectTimeoutMillis(int i) {
            this.httpConnectTimeoutMillis = i;
            return this;
        }

        public Builder setBufferSegmentSize(int i) {
            this.bufferSegmentSize = i;
            return this;
        }

        public Builder setBufferSegmentCount(int i) {
            this.bufferSegmentCount = i;
            return this;
        }

        public Builder setRestrictHdContentToWidevineL1(boolean z) {
            this.restrictHdContentToWidevineL1 = z;
            return this;
        }

        public RendererConfig build() {
            RendererConfig rendererConfig = new RendererConfig();
            rendererConfig.httpReadTimeoutMillis = this.httpReadTimeoutMillis;
            rendererConfig.httpConnectTimeoutMillis = this.httpConnectTimeoutMillis;
            rendererConfig.restrictHdContentToWidevineL1 = this.restrictHdContentToWidevineL1;
            rendererConfig.bufferSegmentCount = this.bufferSegmentCount;
            rendererConfig.bufferSegmentSize = this.bufferSegmentSize;
            return rendererConfig;
        }
    }

    private RendererConfig() {
        this.bufferSegmentSize = -1;
        this.bufferSegmentCount = -1;
    }

    public int getHttpReadTimeoutMillis() {
        return this.httpReadTimeoutMillis;
    }

    public int getHttpConnectTimeoutMillis() {
        return this.httpConnectTimeoutMillis;
    }

    public int getBufferSegmentSize() {
        return this.bufferSegmentSize;
    }

    public int getBufferSegmentCount() {
        return this.bufferSegmentCount;
    }

    public boolean getRestrictHdContentToWidevineL1() {
        return this.restrictHdContentToWidevineL1;
    }
}
