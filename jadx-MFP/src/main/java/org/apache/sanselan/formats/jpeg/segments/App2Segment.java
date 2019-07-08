package org.apache.sanselan.formats.jpeg.segments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.formats.jpeg.JpegImageParser;

public class App2Segment extends APPNSegment implements Comparable {
    public final int cur_marker;
    public final byte[] icc_bytes;
    public final int num_markers;

    public App2Segment(int i, byte[] bArr) throws ImageReadException, IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public App2Segment(int i, int i2, InputStream inputStream) throws ImageReadException, IOException {
        super(i, i2, inputStream);
        if (startsWith(this.bytes, JpegImageParser.icc_profile_label)) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.bytes);
            readAndVerifyBytes(byteArrayInputStream, JpegImageParser.icc_profile_label, "Not a Valid App2 Segment: missing ICC Profile label");
            this.cur_marker = readByte("cur_marker", byteArrayInputStream, "Not a valid App2 Marker");
            this.num_markers = readByte("num_markers", byteArrayInputStream, "Not a valid App2 Marker");
            this.icc_bytes = readByteArray("App2 Data", (i2 - JpegImageParser.icc_profile_label.length) - 2, byteArrayInputStream, "Invalid App2 Segment: insufficient data");
            return;
        }
        this.cur_marker = -1;
        this.num_markers = -1;
        this.icc_bytes = null;
    }

    public int compareTo(Object obj) {
        return this.cur_marker - ((App2Segment) obj).cur_marker;
    }
}
