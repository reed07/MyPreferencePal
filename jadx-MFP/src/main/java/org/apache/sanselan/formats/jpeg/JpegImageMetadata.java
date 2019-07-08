package org.apache.sanselan.formats.jpeg;

import org.apache.sanselan.common.IImageMetadata;
import org.apache.sanselan.formats.tiff.TiffImageMetadata;

public class JpegImageMetadata implements IImageMetadata {
    private static final String newline = System.getProperty("line.separator");
    private final TiffImageMetadata exif;

    public JpegImageMetadata(Object obj, TiffImageMetadata tiffImageMetadata) {
        this.exif = tiffImageMetadata;
    }

    public TiffImageMetadata getExif() {
        return this.exif;
    }

    public String toString() {
        return toString(null);
    }

    public String toString(String str) {
        if (str == null) {
            str = "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (this.exif == null) {
            stringBuffer.append("No Exif metadata.");
        } else {
            stringBuffer.append("Exif metadata:");
            stringBuffer.append(newline);
            stringBuffer.append(this.exif.toString("\t"));
        }
        stringBuffer.append(newline);
        stringBuffer.append(str);
        stringBuffer.append("No Photoshop (IPTC) metadata.");
        return stringBuffer.toString();
    }
}
