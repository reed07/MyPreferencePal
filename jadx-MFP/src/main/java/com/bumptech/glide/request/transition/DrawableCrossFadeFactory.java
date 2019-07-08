package com.bumptech.glide.request.transition;

import android.graphics.drawable.Drawable;
import com.bumptech.glide.load.DataSource;
import com.google.logging.type.LogSeverity;

public class DrawableCrossFadeFactory implements TransitionFactory<Drawable> {
    private final int duration;
    private final boolean isCrossFadeEnabled;
    private DrawableCrossFadeTransition resourceTransition;

    public static class Builder {
        private final int durationMillis;

        public Builder() {
            this(LogSeverity.NOTICE_VALUE);
        }

        public Builder(int i) {
            this.durationMillis = i;
        }
    }

    public Transition<Drawable> build(DataSource dataSource, boolean z) {
        return dataSource == DataSource.MEMORY_CACHE ? NoTransition.get() : getResourceTransition();
    }

    private Transition<Drawable> getResourceTransition() {
        if (this.resourceTransition == null) {
            this.resourceTransition = new DrawableCrossFadeTransition(this.duration, this.isCrossFadeEnabled);
        }
        return this.resourceTransition;
    }
}
