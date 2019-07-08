package com.brightcove.player.config;

public class AllocatorConfig {
    private final int individualAllocationSize;
    private final int initialAllocationCount;
    private final boolean trimOnReset;

    public static class Builder {
        private int individualAllocationSize = 65536;
        private int initialAllocationCount = 0;
        private boolean trimOnReset = true;

        public Builder setTrimOnReset(boolean z) {
            this.trimOnReset = z;
            return this;
        }

        public Builder setIndividualAllocationSize(int i) {
            if (i >= 0) {
                this.individualAllocationSize = i;
            }
            return this;
        }

        public Builder setInitialAllocationCount(int i) {
            if (i >= 0) {
                this.initialAllocationCount = i;
            }
            return this;
        }

        public AllocatorConfig build() {
            return new AllocatorConfig(this.trimOnReset, this.individualAllocationSize, this.initialAllocationCount);
        }
    }

    private AllocatorConfig(boolean z, int i, int i2) {
        this.trimOnReset = z;
        this.individualAllocationSize = i;
        this.initialAllocationCount = i2;
    }

    public boolean isTrimOnReset() {
        return this.trimOnReset;
    }

    public int getIndividualAllocationSize() {
        return this.individualAllocationSize;
    }

    public int getInitialAllocationCount() {
        return this.initialAllocationCount;
    }
}
