package io.opencensus.common;

final class AutoValue_Duration extends Duration {
    private final int nanos;
    private final long seconds;

    AutoValue_Duration(long j, int i) {
        this.seconds = j;
        this.nanos = i;
    }

    public long getSeconds() {
        return this.seconds;
    }

    public int getNanos() {
        return this.nanos;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Duration{seconds=");
        sb.append(this.seconds);
        sb.append(", nanos=");
        sb.append(this.nanos);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Duration)) {
            return false;
        }
        Duration duration = (Duration) obj;
        if (!(this.seconds == duration.getSeconds() && this.nanos == duration.getNanos())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long j = (long) 1000003;
        long j2 = this.seconds;
        return this.nanos ^ (((int) (j ^ (j2 ^ (j2 >>> 32)))) * 1000003);
    }
}
