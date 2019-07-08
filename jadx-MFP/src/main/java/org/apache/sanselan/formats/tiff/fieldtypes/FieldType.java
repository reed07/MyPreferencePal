package org.apache.sanselan.formats.tiff.fieldtypes;

import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryFileFunctions;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;

public abstract class FieldType extends BinaryFileFunctions implements TiffConstants {
    public final int length;
    public final String name;
    public final int type;

    public abstract Object getSimpleValue(TiffField tiffField) throws ImageReadException;

    public abstract byte[] writeData(Object obj, int i) throws ImageWriteException;

    public FieldType(int i, int i2, String str) {
        this.type = i;
        this.length = i2;
        this.name = str;
    }

    public boolean isLocalValue(TiffField tiffField) {
        int i = this.length;
        return i > 0 && i * tiffField.length <= 4;
    }

    public static final byte[] getStubLocalValue() {
        return new byte[4];
    }

    public final byte[] getRawBytes(TiffField tiffField) {
        if (!isLocalValue(tiffField)) {
            return tiffField.oversizeValue;
        }
        int i = this.length * tiffField.length;
        byte[] bArr = new byte[i];
        System.arraycopy(tiffField.valueOffsetBytes, 0, bArr, 0, i);
        return bArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(getClass().getName());
        sb.append(". type: ");
        sb.append(this.type);
        sb.append(", name: ");
        sb.append(this.name);
        sb.append(", length: ");
        sb.append(this.length);
        sb.append("]");
        return sb.toString();
    }
}
