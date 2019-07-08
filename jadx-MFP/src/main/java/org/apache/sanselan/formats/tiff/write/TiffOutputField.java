package org.apache.sanselan.formats.tiff.write;

import java.io.IOException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryOutputStream;
import org.apache.sanselan.formats.tiff.constants.TagInfo;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldType;
import org.apache.sanselan.formats.tiff.write.TiffOutputItem.Value;

public class TiffOutputField implements TiffConstants {
    private static final String newline = System.getProperty("line.separator");
    private byte[] bytes;
    public final int count;
    public final FieldType fieldType;
    private final Value separateValueItem;
    private int sortHint;
    public final int tag;
    public final TagInfo tagInfo;

    public TiffOutputField(TagInfo tagInfo2, FieldType fieldType2, int i, byte[] bArr) {
        this(tagInfo2.tag, tagInfo2, fieldType2, i, bArr);
    }

    public TiffOutputField(int i, TagInfo tagInfo2, FieldType fieldType2, int i2, byte[] bArr) {
        this.sortHint = -1;
        this.tag = i;
        this.tagInfo = tagInfo2;
        this.fieldType = fieldType2;
        this.count = i2;
        this.bytes = bArr;
        if (isLocalValue()) {
            this.separateValueItem = null;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Field Seperate value (");
        sb.append(tagInfo2.getDescription());
        sb.append(")");
        this.separateValueItem = new Value(sb.toString(), bArr);
    }

    protected static final TiffOutputField createOffsetField(TagInfo tagInfo2, int i) throws ImageWriteException {
        return new TiffOutputField(tagInfo2, FIELD_TYPE_LONG, 1, FIELD_TYPE_LONG.writeData(new int[]{0}, i));
    }

    /* access modifiers changed from: protected */
    public void writeField(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException {
        binaryOutputStream.write2Bytes(this.tag);
        binaryOutputStream.write2Bytes(this.fieldType.type);
        binaryOutputStream.write4Bytes(this.count);
        if (!isLocalValue()) {
            Value value = this.separateValueItem;
            if (value != null) {
                binaryOutputStream.write4Bytes(value.getOffset());
                return;
            }
            throw new ImageWriteException("Missing separate value item.");
        } else if (this.separateValueItem == null) {
            byte[] bArr = this.bytes;
            if (bArr.length <= 4) {
                binaryOutputStream.writeByteArray(bArr);
                int length = 4 - this.bytes.length;
                for (int i = 0; i < length; i++) {
                    binaryOutputStream.write(0);
                }
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Local value has invalid length: ");
            sb.append(this.bytes.length);
            throw new ImageWriteException(sb.toString());
        } else {
            throw new ImageWriteException("Unexpected separate value item.");
        }
    }

    /* access modifiers changed from: protected */
    public TiffOutputItem getSeperateValue() {
        return this.separateValueItem;
    }

    /* access modifiers changed from: protected */
    public boolean isLocalValue() {
        return this.bytes.length <= 4;
    }

    /* access modifiers changed from: protected */
    public void setData(byte[] bArr) throws ImageWriteException {
        if (this.bytes.length == bArr.length) {
            this.bytes = bArr;
            Value value = this.separateValueItem;
            if (value != null) {
                value.updateValue(bArr);
                return;
            }
            return;
        }
        throw new ImageWriteException("Cannot change size of value.");
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
        stringBuffer.append(this.tagInfo);
        stringBuffer.append(newline);
        stringBuffer.append(str);
        StringBuilder sb = new StringBuilder();
        sb.append("count: ");
        sb.append(this.count);
        stringBuffer.append(sb.toString());
        stringBuffer.append(newline);
        stringBuffer.append(str);
        stringBuffer.append(this.fieldType);
        stringBuffer.append(newline);
        return stringBuffer.toString();
    }

    public int getSortHint() {
        return this.sortHint;
    }

    public void setSortHint(int i) {
        this.sortHint = i;
    }
}
