package com.myfitnesspal.shared.model.v1;

public class Timezone {
    private String timezone_identifier;
    private String timezone_name;

    public String getTimezone_name() {
        return this.timezone_name;
    }

    public void setTimezone_name(String str) {
        this.timezone_name = str;
    }

    public String getTimezone_identifier() {
        return this.timezone_identifier;
    }

    public void setTimezone_identifier(String str) {
        this.timezone_identifier = str;
    }

    public int hashCode() {
        return this.timezone_identifier.hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof Timezone)) {
            return false;
        }
        Timezone timezone = (Timezone) obj;
        if (this.timezone_name.equals(timezone.timezone_name) && this.timezone_identifier.equals(timezone.timezone_identifier)) {
            z = true;
        }
        return z;
    }

    public String toString() {
        return getTimezone_name();
    }
}
