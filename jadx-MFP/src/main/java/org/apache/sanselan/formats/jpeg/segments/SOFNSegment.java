package org.apache.sanselan.formats.jpeg.segments;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import org.apache.sanselan.ImageReadException;

public class SOFNSegment extends Segment {
    public final int height;
    public final int numberOfComponents;
    public final int precision;
    public final int width;

    public SOFNSegment(int i, byte[] bArr) throws ImageReadException, IOException {
        this(i, bArr.length, new ByteArrayInputStream(bArr));
    }

    public SOFNSegment(int i, int i2, InputStream inputStream) throws ImageReadException, IOException {
        super(i, i2);
        if (getDebug()) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("SOF0Segment marker_length: ");
            sb.append(i2);
            printStream.println(sb.toString());
        }
        this.precision = readByte("Data_precision", inputStream, "Not a Valid JPEG File");
        this.height = read2Bytes("Image_height", inputStream, "Not a Valid JPEG File");
        this.width = read2Bytes("Image_Width", inputStream, "Not a Valid JPEG File");
        this.numberOfComponents = readByte("Number_of_components", inputStream, "Not a Valid JPEG File");
        skipBytes(inputStream, i2 - 6, "Not a Valid JPEG File: SOF0 Segment");
        if (getDebug()) {
            System.out.println("");
        }
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("SOFN (SOF");
        sb.append(this.marker - 65472);
        sb.append(") (");
        sb.append(getSegmentType());
        sb.append(")");
        return sb.toString();
    }
}
