package io.opencensus.stats;

import java.util.List;

final class AutoValue_BucketBoundaries extends BucketBoundaries {
    private final List<Double> boundaries;

    AutoValue_BucketBoundaries(List<Double> list) {
        if (list != null) {
            this.boundaries = list;
            return;
        }
        throw new NullPointerException("Null boundaries");
    }

    public List<Double> getBoundaries() {
        return this.boundaries;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BucketBoundaries{boundaries=");
        sb.append(this.boundaries);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BucketBoundaries)) {
            return false;
        }
        return this.boundaries.equals(((BucketBoundaries) obj).getBoundaries());
    }

    public int hashCode() {
        return this.boundaries.hashCode() ^ 1000003;
    }
}
