package org.apache.sanselan.formats.jpeg.segments;

import java.io.IOException;
import java.io.InputStream;
import org.apache.sanselan.ImageReadException;

public abstract class GenericSegment extends Segment {
    public final byte[] bytes;

    public GenericSegment(int i, int i2, InputStream inputStream) throws ImageReadException, IOException {
        super(i, i2);
        this.bytes = readByteArray("Segment Data", i2, inputStream, "Invalid Segment: insufficient data");
    }

    public GenericSegment(int i, byte[] bArr) throws ImageReadException, IOException {
        super(i, bArr.length);
        this.bytes = bArr;
    }
}
