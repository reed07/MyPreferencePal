package com.twitter.sdk.android.core.services.params;

public class Geocode {
    public final Distance distance;
    public final double latitude;
    public final double longitude;
    public final int radius;

    public enum Distance {
        MILES("mi"),
        KILOMETERS("km");
        
        public final String identifier;

        private Distance(String str) {
            this.identifier = str;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.latitude);
        sb.append(",");
        sb.append(this.longitude);
        sb.append(",");
        sb.append(this.radius);
        sb.append(this.distance.identifier);
        return sb.toString();
    }
}
