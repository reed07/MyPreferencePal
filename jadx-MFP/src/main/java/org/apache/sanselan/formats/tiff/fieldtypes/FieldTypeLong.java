package org.apache.sanselan.formats.tiff.fieldtypes;

import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.util.Debug;

public class FieldTypeLong extends FieldType {
    public FieldTypeLong(int i, String str) {
        super(i, 4, str);
    }

    public Object getSimpleValue(TiffField tiffField) {
        if (tiffField.length == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            sb.append(" (");
            sb.append(tiffField.tagInfo.name);
            sb.append(")");
            return new Integer(convertByteArrayToInt(sb.toString(), tiffField.valueOffsetBytes, tiffField.byteOrder));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.name);
        sb2.append(" (");
        sb2.append(tiffField.tagInfo.name);
        sb2.append(")");
        return convertByteArrayToIntArray(sb2.toString(), getRawBytes(tiffField), 0, tiffField.length, tiffField.byteOrder);
    }

    public byte[] writeData(Object obj, int i) throws ImageWriteException {
        if (obj instanceof Integer) {
            return convertIntArrayToByteArray(new int[]{((Integer) obj).intValue()}, i);
        } else if (obj instanceof int[]) {
            return convertIntArrayToByteArray((int[]) obj, i);
        } else {
            if (obj instanceof Integer[]) {
                Integer[] numArr = (Integer[]) obj;
                int[] iArr = new int[numArr.length];
                for (int i2 = 0; i2 < iArr.length; i2++) {
                    iArr[i2] = numArr[i2].intValue();
                }
                return convertIntArrayToByteArray(iArr, i);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid data: ");
            sb.append(obj);
            sb.append(" (");
            sb.append(Debug.getType(obj));
            sb.append(")");
            throw new ImageWriteException(sb.toString());
        }
    }
}
