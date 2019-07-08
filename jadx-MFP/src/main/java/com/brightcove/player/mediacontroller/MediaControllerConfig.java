package com.brightcove.player.mediacontroller;

import android.view.View.OnTouchListener;

public final class MediaControllerConfig {
    private final int initialDuration;
    private final int initialPlayheadPosition;
    private final int layoutId;
    private final OnTouchListener onTouchListener;
    private final boolean showControlsOnCreation;

    public static class Builder {
        /* access modifiers changed from: private */
        public int initialDuration = -1;
        /* access modifiers changed from: private */
        public int initialPlayheadPosition = 1;
        /* access modifiers changed from: private */
        public int layoutId = -1;
        /* access modifiers changed from: private */
        public OnTouchListener onTouchListener = null;
        /* access modifiers changed from: private */
        public boolean showControlsOnCreation = true;

        public Builder setLayoutId(int i) {
            this.layoutId = i;
            return this;
        }

        public Builder setOnTouchListener(OnTouchListener onTouchListener2) {
            this.onTouchListener = onTouchListener2;
            return this;
        }

        public Builder setInitialDuration(int i) {
            this.initialDuration = i;
            return this;
        }

        public Builder setInitialPlayheadPosition(int i) {
            this.initialPlayheadPosition = i;
            return this;
        }

        public Builder setShowControlsOnCreation(boolean z) {
            this.showControlsOnCreation = z;
            return this;
        }

        public MediaControllerConfig build() {
            return new MediaControllerConfig(this);
        }
    }

    private MediaControllerConfig(Builder builder) {
        this.layoutId = builder.layoutId;
        this.onTouchListener = builder.onTouchListener;
        this.initialDuration = builder.initialDuration;
        this.initialPlayheadPosition = builder.initialPlayheadPosition;
        this.showControlsOnCreation = builder.showControlsOnCreation;
    }

    public int getLayoutId() {
        return this.layoutId;
    }

    public OnTouchListener getOnTouchListener() {
        return this.onTouchListener;
    }

    public int getInitialDuration() {
        return this.initialDuration;
    }

    public int getInitialPlayheadPosition() {
        return this.initialPlayheadPosition;
    }

    public boolean isShowControlsOnCreation() {
        return this.showControlsOnCreation;
    }
}
