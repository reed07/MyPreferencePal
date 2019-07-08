package io.opencensus.tags;

final class AutoValue_TagKey extends TagKey {
    private final String name;

    AutoValue_TagKey(String str) {
        if (str != null) {
            this.name = str;
            return;
        }
        throw new NullPointerException("Null name");
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TagKey{name=");
        sb.append(this.name);
        sb.append("}");
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TagKey)) {
            return false;
        }
        return this.name.equals(((TagKey) obj).getName());
    }

    public int hashCode() {
        return this.name.hashCode() ^ 1000003;
    }
}
