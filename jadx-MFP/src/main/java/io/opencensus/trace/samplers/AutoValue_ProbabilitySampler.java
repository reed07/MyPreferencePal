package io.opencensus.trace.samplers;

final class AutoValue_ProbabilitySampler extends ProbabilitySampler {
    private final long idUpperBound;
    private final double probability;

    AutoValue_ProbabilitySampler(double d, long j) {
        this.probability = d;
        this.idUpperBound = j;
    }

    /* access modifiers changed from: 0000 */
    public double getProbability() {
        return this.probability;
    }

    /* access modifiers changed from: 0000 */
    public long getIdUpperBound() {
        return this.idUpperBound;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProbabilitySampler{probability=");
        sb.append(this.probability);
        sb.append(", idUpperBound=");
        sb.append(this.idUpperBound);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ProbabilitySampler)) {
            return false;
        }
        ProbabilitySampler probabilitySampler = (ProbabilitySampler) obj;
        if (!(Double.doubleToLongBits(this.probability) == Double.doubleToLongBits(probabilitySampler.getProbability()) && this.idUpperBound == probabilitySampler.getIdUpperBound())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        long doubleToLongBits = (long) (((int) (((long) 1000003) ^ ((Double.doubleToLongBits(this.probability) >>> 32) ^ Double.doubleToLongBits(this.probability)))) * 1000003);
        long j = this.idUpperBound;
        return (int) (doubleToLongBits ^ (j ^ (j >>> 32)));
    }
}
