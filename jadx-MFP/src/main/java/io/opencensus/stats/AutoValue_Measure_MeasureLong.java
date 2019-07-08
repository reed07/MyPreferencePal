package io.opencensus.stats;

import io.opencensus.stats.Measure.MeasureLong;

final class AutoValue_Measure_MeasureLong extends MeasureLong {
    private final String description;
    private final String name;
    private final String unit;

    AutoValue_Measure_MeasureLong(String str, String str2, String str3) {
        if (str != null) {
            this.name = str;
            if (str2 != null) {
                this.description = str2;
                if (str3 != null) {
                    this.unit = str3;
                    return;
                }
                throw new NullPointerException("Null unit");
            }
            throw new NullPointerException("Null description");
        }
        throw new NullPointerException("Null name");
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getUnit() {
        return this.unit;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MeasureLong{name=");
        sb.append(this.name);
        sb.append(", description=");
        sb.append(this.description);
        sb.append(", unit=");
        sb.append(this.unit);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MeasureLong)) {
            return false;
        }
        MeasureLong measureLong = (MeasureLong) obj;
        if (!this.name.equals(measureLong.getName()) || !this.description.equals(measureLong.getDescription()) || !this.unit.equals(measureLong.getUnit())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode()) * 1000003) ^ this.unit.hashCode();
    }
}
