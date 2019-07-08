package io.opencensus.trace;

import javax.annotation.Nullable;

final class AutoValue_EndSpanOptions extends EndSpanOptions {
    private final boolean sampleToLocalSpanStore;
    private final Status status;

    static final class Builder extends io.opencensus.trace.EndSpanOptions.Builder {
        private Boolean sampleToLocalSpanStore;
        private Status status;

        Builder() {
        }

        public io.opencensus.trace.EndSpanOptions.Builder setSampleToLocalSpanStore(boolean z) {
            this.sampleToLocalSpanStore = Boolean.valueOf(z);
            return this;
        }

        public io.opencensus.trace.EndSpanOptions.Builder setStatus(@Nullable Status status2) {
            this.status = status2;
            return this;
        }

        public EndSpanOptions build() {
            String str = "";
            if (this.sampleToLocalSpanStore == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" sampleToLocalSpanStore");
                str = sb.toString();
            }
            if (str.isEmpty()) {
                return new AutoValue_EndSpanOptions(this.sampleToLocalSpanStore.booleanValue(), this.status);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Missing required properties:");
            sb2.append(str);
            throw new IllegalStateException(sb2.toString());
        }
    }

    private AutoValue_EndSpanOptions(boolean z, @Nullable Status status2) {
        this.sampleToLocalSpanStore = z;
        this.status = status2;
    }

    public boolean getSampleToLocalSpanStore() {
        return this.sampleToLocalSpanStore;
    }

    @Nullable
    public Status getStatus() {
        return this.status;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EndSpanOptions{sampleToLocalSpanStore=");
        sb.append(this.sampleToLocalSpanStore);
        sb.append(", status=");
        sb.append(this.status);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof EndSpanOptions)) {
            return false;
        }
        EndSpanOptions endSpanOptions = (EndSpanOptions) obj;
        if (this.sampleToLocalSpanStore == endSpanOptions.getSampleToLocalSpanStore()) {
            Status status2 = this.status;
            if (status2 != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int i = ((this.sampleToLocalSpanStore ? 1231 : 1237) ^ 1000003) * 1000003;
        Status status2 = this.status;
        return i ^ (status2 == null ? 0 : status2.hashCode());
    }
}
