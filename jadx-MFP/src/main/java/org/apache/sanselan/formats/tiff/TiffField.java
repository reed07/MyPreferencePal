package org.apache.sanselan.formats.tiff;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.common.byteSources.ByteSource;
import org.apache.sanselan.formats.tiff.constants.TagInfo;
import org.apache.sanselan.formats.tiff.constants.TiffConstants;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldType;

public class TiffField implements TiffConstants {
    private static final Map ALL_TAG_MAP = makeTagMap(ALL_TAGS, true, "All");
    private static final Map EXIF_TAG_MAP = makeTagMap(ALL_EXIF_TAGS, true, "EXIF");
    private static final Map GPS_TAG_MAP = makeTagMap(ALL_GPS_TAGS, false, "GPS");
    private static final Map TIFF_TAG_MAP = makeTagMap(ALL_TIFF_TAGS, false, "TIFF");
    public final int byteOrder;
    public final int directoryType;
    public final FieldType fieldType;
    public final int length;
    public byte[] oversizeValue = null;
    private int sortHint = -1;
    public final int tag;
    public final TagInfo tagInfo;
    public final int type;
    public final int valueOffset;
    public final byte[] valueOffsetBytes;

    public final class OversizeValueElement extends TiffElement {
        public OversizeValueElement(int i, int i2) {
            super(i, i2);
        }
    }

    public TiffField(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6) {
        this.tag = i;
        this.directoryType = i2;
        this.type = i3;
        this.length = i4;
        this.valueOffset = i5;
        this.valueOffsetBytes = bArr;
        this.byteOrder = i6;
        this.fieldType = getFieldType(i3);
        this.tagInfo = getTag(i2, i);
    }

    public TiffElement getOversizeValueElement() {
        if (this.fieldType.isLocalValue(this)) {
            return null;
        }
        return new OversizeValueElement(this.valueOffset, this.oversizeValue.length);
    }

    public void setOversizeValue(byte[] bArr) {
        this.oversizeValue = bArr;
    }

    private static FieldType getFieldType(int i) {
        for (FieldType fieldType2 : FIELD_TYPES) {
            if (fieldType2.type == i) {
                return fieldType2;
            }
        }
        return FIELD_TYPE_UNKNOWN;
    }

    private static TagInfo getTag(int i, int i2, List list) {
        if (list.size() < 1) {
            return null;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            TagInfo tagInfo2 = (TagInfo) list.get(i3);
            if (tagInfo2.directoryType != EXIF_DIRECTORY_UNKNOWN) {
                if (i == -2 && tagInfo2.directoryType == EXIF_DIRECTORY_EXIF_IFD) {
                    return tagInfo2;
                }
                if (i == -4 && tagInfo2.directoryType == EXIF_DIRECTORY_INTEROP_IFD) {
                    return tagInfo2;
                }
                if (i == -3 && tagInfo2.directoryType == EXIF_DIRECTORY_GPS) {
                    return tagInfo2;
                }
                if (i == -5 && tagInfo2.directoryType == EXIF_DIRECTORY_MAKER_NOTES) {
                    return tagInfo2;
                }
                if (i == 0 && tagInfo2.directoryType == TIFF_DIRECTORY_IFD0) {
                    return tagInfo2;
                }
                if (i == 1 && tagInfo2.directoryType == TIFF_DIRECTORY_IFD1) {
                    return tagInfo2;
                }
                if (i == 2 && tagInfo2.directoryType == TIFF_DIRECTORY_IFD2) {
                    return tagInfo2;
                }
                if (i == 3 && tagInfo2.directoryType == TIFF_DIRECTORY_IFD3) {
                    return tagInfo2;
                }
            }
        }
        for (int i4 = 0; i4 < list.size(); i4++) {
            TagInfo tagInfo3 = (TagInfo) list.get(i4);
            if (tagInfo3.directoryType != EXIF_DIRECTORY_UNKNOWN) {
                if (i >= 0 && tagInfo3.directoryType.isImageDirectory()) {
                    return tagInfo3;
                }
                if (i < 0 && !tagInfo3.directoryType.isImageDirectory()) {
                    return tagInfo3;
                }
            }
        }
        for (int i5 = 0; i5 < list.size(); i5++) {
            TagInfo tagInfo4 = (TagInfo) list.get(i5);
            if (tagInfo4.directoryType == EXIF_DIRECTORY_UNKNOWN) {
                return tagInfo4;
            }
        }
        return TIFF_TAG_UNKNOWN;
    }

    private static TagInfo getTag(int i, int i2) {
        List list = (List) EXIF_TAG_MAP.get(new Integer(i2));
        if (list == null) {
            return TIFF_TAG_UNKNOWN;
        }
        return getTag(i, i2, list);
    }

    private int getValueLengthInBytes() {
        return this.fieldType.length * this.length;
    }

    public void fillInValue(ByteSource byteSource) throws ImageReadException, IOException {
        if (!this.fieldType.isLocalValue(this)) {
            setOversizeValue(byteSource.getBlock(this.valueOffset, getValueLengthInBytes()));
        }
    }

    public String getValueDescription() {
        try {
            return getValueDescription(getValue());
        } catch (ImageReadException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid value: ");
            sb.append(e.getMessage());
            return sb.toString();
        }
    }

    private String getValueDescription(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Number) {
            return obj.toString();
        }
        if (obj instanceof String) {
            StringBuilder sb = new StringBuilder();
            sb.append("'");
            sb.append(obj.toString().trim());
            sb.append("'");
            return sb.toString();
        } else if (obj instanceof Date) {
            return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format((Date) obj);
        } else {
            int i = 0;
            if (obj instanceof Object[]) {
                Object[] objArr = (Object[]) obj;
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    if (i >= objArr.length) {
                        break;
                    }
                    Object obj2 = objArr[i];
                    if (i > 50) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("... (");
                        sb2.append(objArr.length);
                        sb2.append(")");
                        stringBuffer.append(sb2.toString());
                        break;
                    }
                    if (i > 0) {
                        stringBuffer.append(", ");
                    }
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("");
                    sb3.append(obj2);
                    stringBuffer.append(sb3.toString());
                    i++;
                }
                return stringBuffer.toString();
            } else if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                StringBuffer stringBuffer2 = new StringBuffer();
                while (true) {
                    if (i >= iArr.length) {
                        break;
                    }
                    int i2 = iArr[i];
                    if (i > 50) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("... (");
                        sb4.append(iArr.length);
                        sb4.append(")");
                        stringBuffer2.append(sb4.toString());
                        break;
                    }
                    if (i > 0) {
                        stringBuffer2.append(", ");
                    }
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("");
                    sb5.append(i2);
                    stringBuffer2.append(sb5.toString());
                    i++;
                }
                return stringBuffer2.toString();
            } else if (obj instanceof long[]) {
                long[] jArr = (long[]) obj;
                StringBuffer stringBuffer3 = new StringBuffer();
                while (true) {
                    if (i >= jArr.length) {
                        break;
                    }
                    long j = jArr[i];
                    if (i > 50) {
                        StringBuilder sb6 = new StringBuilder();
                        sb6.append("... (");
                        sb6.append(jArr.length);
                        sb6.append(")");
                        stringBuffer3.append(sb6.toString());
                        break;
                    }
                    if (i > 0) {
                        stringBuffer3.append(", ");
                    }
                    StringBuilder sb7 = new StringBuilder();
                    sb7.append("");
                    sb7.append(j);
                    stringBuffer3.append(sb7.toString());
                    i++;
                }
                return stringBuffer3.toString();
            } else if (obj instanceof double[]) {
                double[] dArr = (double[]) obj;
                StringBuffer stringBuffer4 = new StringBuffer();
                while (true) {
                    if (i >= dArr.length) {
                        break;
                    }
                    double d = dArr[i];
                    if (i > 50) {
                        StringBuilder sb8 = new StringBuilder();
                        sb8.append("... (");
                        sb8.append(dArr.length);
                        sb8.append(")");
                        stringBuffer4.append(sb8.toString());
                        break;
                    }
                    if (i > 0) {
                        stringBuffer4.append(", ");
                    }
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append("");
                    sb9.append(d);
                    stringBuffer4.append(sb9.toString());
                    i++;
                }
                return stringBuffer4.toString();
            } else if (obj instanceof byte[]) {
                byte[] bArr = (byte[]) obj;
                StringBuffer stringBuffer5 = new StringBuffer();
                while (true) {
                    if (i >= bArr.length) {
                        break;
                    }
                    byte b = bArr[i];
                    if (i > 50) {
                        StringBuilder sb10 = new StringBuilder();
                        sb10.append("... (");
                        sb10.append(bArr.length);
                        sb10.append(")");
                        stringBuffer5.append(sb10.toString());
                        break;
                    }
                    if (i > 0) {
                        stringBuffer5.append(", ");
                    }
                    StringBuilder sb11 = new StringBuilder();
                    sb11.append("");
                    sb11.append(b);
                    stringBuffer5.append(sb11.toString());
                    i++;
                }
                return stringBuffer5.toString();
            } else if (obj instanceof char[]) {
                char[] cArr = (char[]) obj;
                StringBuffer stringBuffer6 = new StringBuffer();
                while (true) {
                    if (i >= cArr.length) {
                        break;
                    }
                    char c = cArr[i];
                    if (i > 50) {
                        StringBuilder sb12 = new StringBuilder();
                        sb12.append("... (");
                        sb12.append(cArr.length);
                        sb12.append(")");
                        stringBuffer6.append(sb12.toString());
                        break;
                    }
                    if (i > 0) {
                        stringBuffer6.append(", ");
                    }
                    StringBuilder sb13 = new StringBuilder();
                    sb13.append("");
                    sb13.append(c);
                    stringBuffer6.append(sb13.toString());
                    i++;
                }
                return stringBuffer6.toString();
            } else if (obj instanceof float[]) {
                float[] fArr = (float[]) obj;
                StringBuffer stringBuffer7 = new StringBuffer();
                while (true) {
                    if (i >= fArr.length) {
                        break;
                    }
                    float f = fArr[i];
                    if (i > 50) {
                        StringBuilder sb14 = new StringBuilder();
                        sb14.append("... (");
                        sb14.append(fArr.length);
                        sb14.append(")");
                        stringBuffer7.append(sb14.toString());
                        break;
                    }
                    if (i > 0) {
                        stringBuffer7.append(", ");
                    }
                    StringBuilder sb15 = new StringBuilder();
                    sb15.append("");
                    sb15.append(f);
                    stringBuffer7.append(sb15.toString());
                    i++;
                }
                return stringBuffer7.toString();
            } else {
                StringBuilder sb16 = new StringBuilder();
                sb16.append("Unknown: ");
                sb16.append(obj.getClass().getName());
                return sb16.toString();
            }
        }
    }

    public String getDescriptionWithoutValue() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.tag);
        sb.append(" (0x");
        sb.append(Integer.toHexString(this.tag));
        sb.append(": ");
        sb.append(this.tagInfo.name);
        sb.append("): ");
        return sb.toString();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder();
        sb.append(this.tag);
        sb.append(" (0x");
        sb.append(Integer.toHexString(this.tag));
        sb.append(": ");
        sb.append(this.tagInfo.name);
        sb.append("): ");
        stringBuffer.append(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(getValueDescription());
        sb2.append(" (");
        sb2.append(this.length);
        sb2.append(" ");
        sb2.append(this.fieldType.name);
        sb2.append(")");
        stringBuffer.append(sb2.toString());
        return stringBuffer.toString();
    }

    public String getTagName() {
        if (this.tagInfo != TIFF_TAG_UNKNOWN) {
            return this.tagInfo.name;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.tagInfo.name);
        sb.append(" (0x");
        sb.append(Integer.toHexString(this.tag));
        sb.append(")");
        return sb.toString();
    }

    public Object getValue() throws ImageReadException {
        return this.tagInfo.getValue(this);
    }

    private static final Map makeTagMap(TagInfo[] tagInfoArr, boolean z, String str) {
        Hashtable hashtable = new Hashtable();
        for (TagInfo tagInfo2 : tagInfoArr) {
            Integer num = new Integer(tagInfo2.tag);
            List list = (List) hashtable.get(num);
            if (list == null) {
                list = new ArrayList();
                hashtable.put(num, list);
            }
            list.add(tagInfo2);
        }
        return hashtable;
    }

    public int[] getIntArrayValue() throws ImageReadException {
        Object value = getValue();
        int i = 0;
        if (value instanceof Number) {
            return new int[]{((Number) value).intValue()};
        } else if (value instanceof Number[]) {
            Number[] numberArr = (Number[]) value;
            int[] iArr = new int[numberArr.length];
            while (i < numberArr.length) {
                iArr[i] = numberArr[i].intValue();
                i++;
            }
            return iArr;
        } else if (value instanceof int[]) {
            int[] iArr2 = (int[]) value;
            int[] iArr3 = new int[iArr2.length];
            while (i < iArr2.length) {
                iArr3[i] = iArr2[i];
                i++;
            }
            return iArr3;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Unknown value: ");
            sb.append(value);
            sb.append(" for: ");
            sb.append(this.tagInfo.getDescription());
            throw new ImageReadException(sb.toString());
        }
    }

    public int getSortHint() {
        return this.sortHint;
    }

    public void setSortHint(int i) {
        this.sortHint = i;
    }
}
