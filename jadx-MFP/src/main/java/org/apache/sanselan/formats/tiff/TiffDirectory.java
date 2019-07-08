package org.apache.sanselan.formats.tiff;

import com.facebook.internal.AnalyticsEvents;
import java.util.ArrayList;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.formats.tiff.constants.TagInfo;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

public class TiffDirectory extends TiffElement implements TiffConstants {
    public final ArrayList entries;
    private JpegImageData jpegImageData = null;
    public final int nextDirectoryOffset;
    public final int type;

    public final class ImageDataElement extends TiffElement {
        public ImageDataElement(int i, int i2) {
            super(i, i2);
        }
    }

    public static final String description(int i) {
        switch (i) {
            case -4:
                return "Interoperability";
            case -3:
                return "Gps";
            case -2:
                return "Exif";
            case -1:
                return AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
            case 0:
                return "Root";
            case 1:
                return "Sub";
            case 2:
                return "Thumbnail";
            default:
                return "Bad Type";
        }
    }

    public String description() {
        return description(this.type);
    }

    public TiffDirectory(int i, ArrayList arrayList, int i2, int i3) {
        super(i2, (arrayList.size() * 12) + 2 + 4);
        this.type = i;
        this.entries = arrayList;
        this.nextDirectoryOffset = i3;
    }

    public ArrayList getDirectoryEntrys() {
        return new ArrayList(this.entries);
    }

    public boolean hasJpegImageData() throws ImageReadException {
        return findField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT) != null;
    }

    public TiffField findField(TagInfo tagInfo) throws ImageReadException {
        return findField(tagInfo, false);
    }

    public TiffField findField(TagInfo tagInfo, boolean z) throws ImageReadException {
        if (this.entries == null) {
            return null;
        }
        for (int i = 0; i < this.entries.size(); i++) {
            TiffField tiffField = (TiffField) this.entries.get(i);
            if (tiffField.tag == tagInfo.tag) {
                return tiffField;
            }
        }
        if (!z) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Missing expected field: ");
        sb.append(tagInfo.getDescription());
        throw new ImageReadException(sb.toString());
    }

    public ImageDataElement getJpegRawImageDataElement() throws ImageReadException {
        TiffField findField = findField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT);
        TiffField findField2 = findField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        if (findField != null && findField2 != null) {
            return new ImageDataElement(findField.getIntArrayValue()[0], findField2.getIntArrayValue()[0]);
        }
        throw new ImageReadException("Couldn't find image data.");
    }

    public void setJpegImageData(JpegImageData jpegImageData2) {
        this.jpegImageData = jpegImageData2;
    }

    public JpegImageData getJpegImageData() {
        return this.jpegImageData;
    }
}
