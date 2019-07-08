package org.apache.sanselan.formats.tiff.fieldtypes;

import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.util.Debug;

public class FieldTypeFloat extends FieldType {
    public FieldTypeFloat() {
        super(11, 4, "Float");
    }

    public Object getSimpleValue(TiffField tiffField) {
        if (tiffField.length == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            sb.append(" (");
            sb.append(tiffField.tagInfo.name);
            sb.append(")");
            return new Float(convertByteArrayToFloat(sb.toString(), tiffField.valueOffsetBytes, tiffField.byteOrder));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.name);
        sb2.append(" (");
        sb2.append(tiffField.tagInfo.name);
        sb2.append(")");
        return convertByteArrayToFloatArray(sb2.toString(), getRawBytes(tiffField), 0, tiffField.length, tiffField.byteOrder);
    }

    public byte[] writeData(Object obj, int i) throws ImageWriteException {
        if (obj instanceof Float) {
            return convertFloatToByteArray(((Float) obj).floatValue(), i);
        }
        if (obj instanceof float[]) {
            return convertFloatArrayToByteArray((float[]) obj, i);
        }
        if (obj instanceof Float[]) {
            Float[] fArr = (Float[]) obj;
            float[] fArr2 = new float[fArr.length];
            for (int i2 = 0; i2 < fArr2.length; i2++) {
                fArr2[i2] = fArr[i2].floatValue();
            }
            return convertFloatArrayToByteArray(fArr2, i);
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
