package org.apache.sanselan.formats.tiff.write;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryOutputStream;
import org.apache.sanselan.formats.tiff.JpegImageData;
import org.apache.sanselan.formats.tiff.TiffDirectory;
import org.apache.sanselan.formats.tiff.constants.TagInfo;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldType;
import org.apache.sanselan.formats.tiff.write.TiffOutputItem.Value;

public final class TiffOutputDirectory extends TiffOutputItem implements TiffConstants {
    private final ArrayList fields = new ArrayList();
    private JpegImageData jpegImageData = null;
    private TiffOutputDirectory nextDirectory = null;
    public final int type;

    public void setNextDirectory(TiffOutputDirectory tiffOutputDirectory) {
        this.nextDirectory = tiffOutputDirectory;
    }

    public TiffOutputDirectory(int i) {
        this.type = i;
    }

    public void add(TiffOutputField tiffOutputField) {
        this.fields.add(tiffOutputField);
    }

    public ArrayList getFields() {
        return new ArrayList(this.fields);
    }

    public TiffOutputField findField(TagInfo tagInfo) {
        return findField(tagInfo.tag);
    }

    public TiffOutputField findField(int i) {
        for (int i2 = 0; i2 < this.fields.size(); i2++) {
            TiffOutputField tiffOutputField = (TiffOutputField) this.fields.get(i2);
            if (tiffOutputField.tag == i) {
                return tiffOutputField;
            }
        }
        return null;
    }

    public void sortFields() {
        Collections.sort(this.fields, new Comparator() {
            public int compare(Object obj, Object obj2) {
                TiffOutputField tiffOutputField = (TiffOutputField) obj;
                TiffOutputField tiffOutputField2 = (TiffOutputField) obj2;
                if (tiffOutputField.tag != tiffOutputField2.tag) {
                    return tiffOutputField.tag - tiffOutputField2.tag;
                }
                return tiffOutputField.getSortHint() - tiffOutputField2.getSortHint();
            }
        });
    }

    public String description() {
        return TiffDirectory.description(this.type);
    }

    public void writeItem(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException {
        binaryOutputStream.write2Bytes(this.fields.size());
        for (int i = 0; i < this.fields.size(); i++) {
            ((TiffOutputField) this.fields.get(i)).writeField(binaryOutputStream);
        }
        TiffOutputDirectory tiffOutputDirectory = this.nextDirectory;
        int offset = tiffOutputDirectory != null ? tiffOutputDirectory.getOffset() : 0;
        if (offset == -1) {
            binaryOutputStream.write4Bytes(0);
        } else {
            binaryOutputStream.write4Bytes(offset);
        }
    }

    public void setJpegImageData(JpegImageData jpegImageData2) {
        this.jpegImageData = jpegImageData2;
    }

    public int getItemLength() {
        return (this.fields.size() * 12) + 2 + 4;
    }

    private void removeFieldIfPresent(TagInfo tagInfo) {
        TiffOutputField findField = findField(tagInfo);
        if (findField != null) {
            this.fields.remove(findField);
        }
    }

    /* access modifiers changed from: protected */
    public List getOutputItems(TiffOutputSummary tiffOutputSummary) throws ImageWriteException {
        TiffOutputField tiffOutputField;
        removeFieldIfPresent(TIFF_TAG_JPEG_INTERCHANGE_FORMAT);
        removeFieldIfPresent(TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH);
        if (this.jpegImageData != null) {
            tiffOutputField = new TiffOutputField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT, FIELD_TYPE_LONG, 1, FieldType.getStubLocalValue());
            add(tiffOutputField);
            add(new TiffOutputField(TIFF_TAG_JPEG_INTERCHANGE_FORMAT_LENGTH, FIELD_TYPE_LONG, 1, FIELD_TYPE_LONG.writeData(new int[]{this.jpegImageData.length}, tiffOutputSummary.byteOrder)));
        } else {
            tiffOutputField = null;
        }
        removeFieldIfPresent(TIFF_TAG_STRIP_OFFSETS);
        removeFieldIfPresent(TIFF_TAG_STRIP_BYTE_COUNTS);
        removeFieldIfPresent(TIFF_TAG_TILE_OFFSETS);
        removeFieldIfPresent(TIFF_TAG_TILE_BYTE_COUNTS);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this);
        sortFields();
        for (int i = 0; i < this.fields.size(); i++) {
            TiffOutputField tiffOutputField2 = (TiffOutputField) this.fields.get(i);
            if (!tiffOutputField2.isLocalValue()) {
                arrayList.add(tiffOutputField2.getSeperateValue());
            }
        }
        JpegImageData jpegImageData2 = this.jpegImageData;
        if (jpegImageData2 != null) {
            Value value = new Value("JPEG image data", jpegImageData2.data);
            arrayList.add(value);
            tiffOutputSummary.add(value, tiffOutputField);
        }
        return arrayList;
    }
}
