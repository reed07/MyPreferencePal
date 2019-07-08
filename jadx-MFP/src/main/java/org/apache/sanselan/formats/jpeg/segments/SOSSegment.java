package org.apache.sanselan.formats.jpeg.segments;

public class SOSSegment extends Segment {
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("SOS (");
        sb.append(getSegmentType());
        sb.append(")");
        return sb.toString();
    }
}
