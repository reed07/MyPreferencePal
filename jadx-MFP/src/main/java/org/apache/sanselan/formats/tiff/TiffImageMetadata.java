package org.apache.sanselan.formats.tiff;

import java.util.ArrayList;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.IImageMetadata.IImageMetadataItem;
import org.apache.sanselan.common.ImageMetadata;
import org.apache.sanselan.common.RationalNumber;
import org.apache.sanselan.formats.tiff.constants.TagInfo;
import org.apache.sanselan.formats.tiff.constants.TagInfo.Offset;
import org.apache.sanselan.formats.tiff.constants.TiffDirectoryConstants;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldType;
import org.apache.sanselan.formats.tiff.write.TiffOutputDirectory;
import org.apache.sanselan.formats.tiff.write.TiffOutputField;
import org.apache.sanselan.formats.tiff.write.TiffOutputSet;

public class TiffImageMetadata extends ImageMetadata implements TiffDirectoryConstants {
    public final TiffContents contents;

    public static class Directory extends ImageMetadata implements IImageMetadataItem {
        private final TiffDirectory directory;
        public final int type;

        public Directory(TiffDirectory tiffDirectory) {
            this.type = tiffDirectory.type;
            this.directory = tiffDirectory;
        }

        public void add(TiffField tiffField) {
            add(new Item(tiffField));
        }

        public JpegImageData getJpegImageData() {
            return this.directory.getJpegImageData();
        }

        public String toString(String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(str != null ? str : "");
            sb.append(this.directory.description());
            sb.append(": ");
            sb.append(getJpegImageData() != null ? " (jpegImageData)" : "");
            sb.append("\n");
            sb.append(super.toString(str));
            sb.append("\n");
            return sb.toString();
        }

        public TiffOutputDirectory getOutputDirectory(int i) throws ImageWriteException {
            try {
                TiffOutputDirectory tiffOutputDirectory = new TiffOutputDirectory(this.type);
                ArrayList items = getItems();
                for (int i2 = 0; i2 < items.size(); i2++) {
                    TiffField tiffField = ((Item) items.get(i2)).getTiffField();
                    if (tiffOutputDirectory.findField(tiffField.tag) == null) {
                        if (!(tiffField.tagInfo instanceof Offset)) {
                            TagInfo tagInfo = tiffField.tagInfo;
                            FieldType fieldType = tiffField.fieldType;
                            TiffOutputField tiffOutputField = new TiffOutputField(tiffField.tag, tagInfo, fieldType, tiffField.length, tagInfo.encodeValue(fieldType, tiffField.getValue(), i));
                            tiffOutputField.setSortHint(tiffField.getSortHint());
                            tiffOutputDirectory.add(tiffOutputField);
                        }
                    }
                }
                tiffOutputDirectory.setJpegImageData(getJpegImageData());
                return tiffOutputDirectory;
            } catch (ImageReadException e) {
                throw new ImageWriteException(e.getMessage(), e);
            }
        }
    }

    public static class GPSInfo {
        public final RationalNumber latitudeDegrees;
        public final RationalNumber latitudeMinutes;
        public final String latitudeRef;
        public final RationalNumber latitudeSeconds;
        public final RationalNumber longitudeDegrees;
        public final RationalNumber longitudeMinutes;
        public final String longitudeRef;
        public final RationalNumber longitudeSeconds;

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("[GPS. ");
            StringBuilder sb = new StringBuilder();
            sb.append("Latitude: ");
            sb.append(this.latitudeDegrees.toDisplayString());
            sb.append(" degrees, ");
            sb.append(this.latitudeMinutes.toDisplayString());
            sb.append(" minutes, ");
            sb.append(this.latitudeSeconds.toDisplayString());
            sb.append(" seconds ");
            sb.append(this.latitudeRef);
            stringBuffer.append(sb.toString());
            StringBuilder sb2 = new StringBuilder();
            sb2.append(", Longitude: ");
            sb2.append(this.longitudeDegrees.toDisplayString());
            sb2.append(" degrees, ");
            sb2.append(this.longitudeMinutes.toDisplayString());
            sb2.append(" minutes, ");
            sb2.append(this.longitudeSeconds.toDisplayString());
            sb2.append(" seconds ");
            sb2.append(this.longitudeRef);
            stringBuffer.append(sb2.toString());
            stringBuffer.append("]");
            return stringBuffer.toString();
        }
    }

    public static class Item extends org.apache.sanselan.common.ImageMetadata.Item {
        private final TiffField entry;

        public Item(TiffField tiffField) {
            super(tiffField.getTagName(), tiffField.getValueDescription());
            this.entry = tiffField;
        }

        public TiffField getTiffField() {
            return this.entry;
        }
    }

    public TiffImageMetadata(TiffContents tiffContents) {
        this.contents = tiffContents;
    }

    public ArrayList getDirectories() {
        return super.getItems();
    }

    public ArrayList getItems() {
        ArrayList arrayList = new ArrayList();
        ArrayList items = super.getItems();
        for (int i = 0; i < items.size(); i++) {
            arrayList.addAll(((Directory) items.get(i)).getItems());
        }
        return arrayList;
    }

    public TiffOutputSet getOutputSet() throws ImageWriteException {
        int i = this.contents.header.byteOrder;
        TiffOutputSet tiffOutputSet = new TiffOutputSet(i);
        ArrayList directories = getDirectories();
        for (int i2 = 0; i2 < directories.size(); i2++) {
            Directory directory = (Directory) directories.get(i2);
            if (tiffOutputSet.findDirectory(directory.type) == null) {
                tiffOutputSet.addDirectory(directory.getOutputDirectory(i));
            }
        }
        return tiffOutputSet;
    }
}
