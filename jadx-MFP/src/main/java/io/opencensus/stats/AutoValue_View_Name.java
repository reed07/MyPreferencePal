package io.opencensus.stats;

import io.opencensus.stats.View.Name;

final class AutoValue_View_Name extends Name {
    private final String asString;

    AutoValue_View_Name(String str) {
        if (str != null) {
            this.asString = str;
            return;
        }
        throw new NullPointerException("Null asString");
    }

    public String asString() {
        return this.asString;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name{asString=");
        sb.append(this.asString);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Name)) {
            return false;
        }
        return this.asString.equals(((Name) obj).asString());
    }

    public int hashCode() {
        return this.asString.hashCode() ^ 1000003;
    }
}
