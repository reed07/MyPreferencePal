package org.apache.sanselan.formats.tiff.fieldtypes;

import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.formats.tiff.TiffField;

public class FieldTypeASCII extends FieldType {
    public FieldTypeASCII(int i, String str) {
        super(i, 1, str);
    }

    public Object getSimpleValue(TiffField tiffField) {
        byte[] rawBytes = getRawBytes(tiffField);
        return new String(rawBytes, 0, rawBytes.length - 1);
    }

    public byte[] writeData(Object obj, int i) throws ImageWriteException {
        byte[] bArr;
        if (obj instanceof byte[]) {
            bArr = (byte[]) obj;
        } else if (obj instanceof String) {
            bArr = ((String) obj).getBytes();
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown data type: ");
            sb.append(obj);
            throw new ImageWriteException(sb.toString());
        }
        byte[] bArr2 = new byte[(bArr.length + 1)];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        return bArr2;
    }
}
