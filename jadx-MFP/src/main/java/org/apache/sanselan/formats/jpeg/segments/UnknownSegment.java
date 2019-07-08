package org.apache.sanselan.formats.jpeg.segments;

import java.io.IOException;
import org.apache.sanselan.ImageReadException;

public class UnknownSegment extends GenericSegment {
    public UnknownSegment(int i, byte[] bArr) throws ImageReadException, IOException {
        super(i, bArr);
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Unknown (");
        sb.append(getSegmentType());
        sb.append(")");
        return sb.toString();
    }
}
