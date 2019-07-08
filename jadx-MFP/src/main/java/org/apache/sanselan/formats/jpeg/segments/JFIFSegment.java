package org.apache.sanselan.formats.jpeg.segments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.formats.jpeg.JpegConstants;

public class JFIFSegment extends Segment implements JpegConstants {
    public final int densityUnits;
    public final int jfifMajorVersion;
    public final int jfifMinorVersion;
    public final int thumbnailSize;
    public final int xDensity;
    public final int xThumbnail;
    public final int yDensity;
    public final int yThumbnail;

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("JFIF (");
        sb.append(getSegmentType());
        sb.append(")");
        return sb.toString();
    }

    public JFIFSegment(int i, byte[] bArr) throws ImageReadException, IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public JFIFSegment(int i, int i2, InputStream inputStream) throws ImageReadException, IOException {
        super(i, i2);
        byte[] readBytes = readBytes(inputStream, JFIF0_SIGNATURE.length);
        if (compareByteArrays(readBytes, JFIF0_SIGNATURE) || compareByteArrays(readBytes, JFIF0_SIGNATURE_ALTERNATIVE)) {
            this.jfifMajorVersion = readByte("JFIF_major_version", inputStream, "Not a Valid JPEG File");
            this.jfifMinorVersion = readByte("JFIF_minor_version", inputStream, "Not a Valid JPEG File");
            this.densityUnits = readByte("density_units", inputStream, "Not a Valid JPEG File");
            this.xDensity = read2Bytes("x_density", inputStream, "Not a Valid JPEG File");
            this.yDensity = read2Bytes("y_density", inputStream, "Not a Valid JPEG File");
            this.xThumbnail = readByte("x_thumbnail", inputStream, "Not a Valid JPEG File");
            this.yThumbnail = readByte("y_thumbnail", inputStream, "Not a Valid JPEG File");
            this.thumbnailSize = this.xThumbnail * this.yThumbnail;
            int i3 = this.thumbnailSize;
            if (i3 > 0) {
                skipBytes(inputStream, i3, "Not a Valid JPEG File: missing thumbnail");
            }
            if (getDebug()) {
                System.out.println("");
                return;
            }
            return;
        }
        throw new ImageReadException("Not a Valid JPEG File: missing JFIF string");
    }
}
