package io.opencensus.stats;

import io.opencensus.stats.Aggregation.Distribution;

final class AutoValue_Aggregation_Distribution extends Distribution {
    private final BucketBoundaries bucketBoundaries;

    AutoValue_Aggregation_Distribution(BucketBoundaries bucketBoundaries2) {
        if (bucketBoundaries2 != null) {
            this.bucketBoundaries = bucketBoundaries2;
            return;
        }
        throw new NullPointerException("Null bucketBoundaries");
    }

    public BucketBoundaries getBucketBoundaries() {
        return this.bucketBoundaries;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Distribution{bucketBoundaries=");
        sb.append(this.bucketBoundaries);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Distribution)) {
            return false;
        }
        return this.bucketBoundaries.equals(((Distribution) obj).getBucketBoundaries());
    }

    public int hashCode() {
        return this.bucketBoundaries.hashCode() ^ 1000003;
    }
}
