package org.apache.sanselan.formats.tiff.fieldtypes;

import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.RationalNumber;
import org.apache.sanselan.common.RationalNumberUtilities;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.util.Debug;

public class FieldTypeRational extends FieldType {
    public FieldTypeRational(int i, String str) {
        super(i, 8, str);
    }

    public Object getSimpleValue(TiffField tiffField) {
        if (tiffField.length == 1) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.name);
            sb.append(" (");
            sb.append(tiffField.tagInfo.name);
            sb.append(")");
            return convertByteArrayToRational(sb.toString(), tiffField.oversizeValue, tiffField.byteOrder);
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.name);
        sb2.append(" (");
        sb2.append(tiffField.tagInfo.name);
        sb2.append(")");
        return convertByteArrayToRationalArray(sb2.toString(), getRawBytes(tiffField), 0, tiffField.length, tiffField.byteOrder);
    }

    public byte[] writeData(Object obj, int i) throws ImageWriteException {
        if (obj instanceof RationalNumber) {
            return convertRationalToByteArray((RationalNumber) obj, i);
        }
        if (obj instanceof RationalNumber[]) {
            return convertRationalArrayToByteArray((RationalNumber[]) obj, i);
        }
        if (obj instanceof Number) {
            return convertRationalToByteArray(RationalNumberUtilities.getRationalNumber(((Number) obj).doubleValue()), i);
        }
        int i2 = 0;
        if (obj instanceof Number[]) {
            Number[] numberArr = (Number[]) obj;
            RationalNumber[] rationalNumberArr = new RationalNumber[numberArr.length];
            while (i2 < numberArr.length) {
                rationalNumberArr[i2] = RationalNumberUtilities.getRationalNumber(numberArr[i2].doubleValue());
                i2++;
            }
            return convertRationalArrayToByteArray(rationalNumberArr, i);
        } else if (obj instanceof double[]) {
            double[] dArr = (double[]) obj;
            RationalNumber[] rationalNumberArr2 = new RationalNumber[dArr.length];
            while (i2 < dArr.length) {
                rationalNumberArr2[i2] = RationalNumberUtilities.getRationalNumber(dArr[i2]);
                i2++;
            }
            return convertRationalArrayToByteArray(rationalNumberArr2, i);
        } else {
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
