package com.bumptech.glide.util.pool;

import android.support.annotation.NonNull;

public abstract class StateVerifier {

    private static class DefaultStateVerifier extends StateVerifier {
        private volatile boolean isReleased;

        DefaultStateVerifier() {
            super();
        }

        public void throwIfRecycled() {
            if (this.isReleased) {
                throw new IllegalStateException("Already released");
            }
        }

        public void setRecycled(boolean z) {
            this.isReleased = z;
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract void setRecycled(boolean z);

    public abstract void throwIfRecycled();

    @NonNull
    public static StateVerifier newInstance() {
        return new DefaultStateVerifier();
    }

    private StateVerifier() {
    }
}
