package io.opencensus.trace.config;

import io.opencensus.trace.Sampler;

final class AutoValue_TraceParams extends TraceParams {
    private final int maxNumberOfAnnotations;
    private final int maxNumberOfAttributes;
    private final int maxNumberOfLinks;
    private final int maxNumberOfMessageEvents;
    private final Sampler sampler;

    static final class Builder extends io.opencensus.trace.config.TraceParams.Builder {
        private Integer maxNumberOfAnnotations;
        private Integer maxNumberOfAttributes;
        private Integer maxNumberOfLinks;
        private Integer maxNumberOfMessageEvents;
        private Sampler sampler;

        Builder() {
        }

        public io.opencensus.trace.config.TraceParams.Builder setSampler(Sampler sampler2) {
            if (sampler2 != null) {
                this.sampler = sampler2;
                return this;
            }
            throw new NullPointerException("Null sampler");
        }

        public io.opencensus.trace.config.TraceParams.Builder setMaxNumberOfAttributes(int i) {
            this.maxNumberOfAttributes = Integer.valueOf(i);
            return this;
        }

        public io.opencensus.trace.config.TraceParams.Builder setMaxNumberOfAnnotations(int i) {
            this.maxNumberOfAnnotations = Integer.valueOf(i);
            return this;
        }

        public io.opencensus.trace.config.TraceParams.Builder setMaxNumberOfMessageEvents(int i) {
            this.maxNumberOfMessageEvents = Integer.valueOf(i);
            return this;
        }

        public io.opencensus.trace.config.TraceParams.Builder setMaxNumberOfLinks(int i) {
            this.maxNumberOfLinks = Integer.valueOf(i);
            return this;
        }

        /* access modifiers changed from: 0000 */
        public TraceParams autoBuild() {
            String str = "";
            if (this.sampler == null) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(" sampler");
                str = sb.toString();
            }
            if (this.maxNumberOfAttributes == null) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append(str);
                sb2.append(" maxNumberOfAttributes");
                str = sb2.toString();
            }
            if (this.maxNumberOfAnnotations == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(" maxNumberOfAnnotations");
                str = sb3.toString();
            }
            if (this.maxNumberOfMessageEvents == null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(str);
                sb4.append(" maxNumberOfMessageEvents");
                str = sb4.toString();
            }
            if (this.maxNumberOfLinks == null) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str);
                sb5.append(" maxNumberOfLinks");
                str = sb5.toString();
            }
            if (str.isEmpty()) {
                AutoValue_TraceParams autoValue_TraceParams = new AutoValue_TraceParams(this.sampler, this.maxNumberOfAttributes.intValue(), this.maxNumberOfAnnotations.intValue(), this.maxNumberOfMessageEvents.intValue(), this.maxNumberOfLinks.intValue());
                return autoValue_TraceParams;
            }
            StringBuilder sb6 = new StringBuilder();
            sb6.append("Missing required properties:");
            sb6.append(str);
            throw new IllegalStateException(sb6.toString());
        }
    }

    private AutoValue_TraceParams(Sampler sampler2, int i, int i2, int i3, int i4) {
        this.sampler = sampler2;
        this.maxNumberOfAttributes = i;
        this.maxNumberOfAnnotations = i2;
        this.maxNumberOfMessageEvents = i3;
        this.maxNumberOfLinks = i4;
    }

    public Sampler getSampler() {
        return this.sampler;
    }

    public int getMaxNumberOfAttributes() {
        return this.maxNumberOfAttributes;
    }

    public int getMaxNumberOfAnnotations() {
        return this.maxNumberOfAnnotations;
    }

    public int getMaxNumberOfMessageEvents() {
        return this.maxNumberOfMessageEvents;
    }

    public int getMaxNumberOfLinks() {
        return this.maxNumberOfLinks;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TraceParams{sampler=");
        sb.append(this.sampler);
        sb.append(", maxNumberOfAttributes=");
        sb.append(this.maxNumberOfAttributes);
        sb.append(", maxNumberOfAnnotations=");
        sb.append(this.maxNumberOfAnnotations);
        sb.append(", maxNumberOfMessageEvents=");
        sb.append(this.maxNumberOfMessageEvents);
        sb.append(", maxNumberOfLinks=");
        sb.append(this.maxNumberOfLinks);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TraceParams)) {
            return false;
        }
        TraceParams traceParams = (TraceParams) obj;
        if (!(this.sampler.equals(traceParams.getSampler()) && this.maxNumberOfAttributes == traceParams.getMaxNumberOfAttributes() && this.maxNumberOfAnnotations == traceParams.getMaxNumberOfAnnotations() && this.maxNumberOfMessageEvents == traceParams.getMaxNumberOfMessageEvents() && this.maxNumberOfLinks == traceParams.getMaxNumberOfLinks())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((((this.sampler.hashCode() ^ 1000003) * 1000003) ^ this.maxNumberOfAttributes) * 1000003) ^ this.maxNumberOfAnnotations) * 1000003) ^ this.maxNumberOfMessageEvents) * 1000003) ^ this.maxNumberOfLinks;
    }
}
