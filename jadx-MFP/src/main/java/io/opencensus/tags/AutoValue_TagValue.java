package io.opencensus.tags;

final class AutoValue_TagValue extends TagValue {
    private final String asString;

    AutoValue_TagValue(String str) {
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
        sb.append("TagValue{asString=");
        sb.append(this.asString);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TagValue)) {
            return false;
        }
        return this.asString.equals(((TagValue) obj).asString());
    }

    public int hashCode() {
        return this.asString.hashCode() ^ 1000003;
    }
}
