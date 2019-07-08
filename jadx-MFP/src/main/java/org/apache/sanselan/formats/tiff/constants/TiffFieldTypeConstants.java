package org.apache.sanselan.formats.tiff.constants;

import org.apache.sanselan.SanselanConstants;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldType;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeASCII;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeByte;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeDouble;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeFloat;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeLong;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeRational;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeShort;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldTypeUnknown;

public interface TiffFieldTypeConstants extends SanselanConstants {
    public static final FieldType[] FIELD_TYPES;
    public static final FieldType[] FIELD_TYPE_ANY = FIELD_TYPES;
    public static final FieldTypeASCII FIELD_TYPE_ASCII = new FieldTypeASCII(2, "ASCII");
    public static final FieldTypeByte FIELD_TYPE_BYTE = new FieldTypeByte(1, "Byte");
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_ANY = FIELD_TYPE_ANY;
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_ASCII;
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_BYTE;
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_BYTE_OR_SHORT;
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_LONG;
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_LONG_OR_SHORT;
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_RATIONAL;
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_SHORT;
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_SHORT_OR_LONG;
    public static final FieldType[] FIELD_TYPE_DESCRIPTION_UNKNOWN = null;
    public static final FieldType FIELD_TYPE_DOUBLE = new FieldTypeDouble();
    public static final FieldType FIELD_TYPE_FLOAT = new FieldTypeFloat();
    public static final FieldTypeLong FIELD_TYPE_LONG = new FieldTypeLong(4, "Long");
    public static final FieldTypeRational FIELD_TYPE_RATIONAL = new FieldTypeRational(5, "Rational");
    public static final FieldType FIELD_TYPE_SBYTE = new FieldTypeByte(6, "SByte");
    public static final FieldTypeShort FIELD_TYPE_SHORT = new FieldTypeShort(3, "Short");
    public static final FieldType FIELD_TYPE_SLONG = new FieldTypeLong(9, "SLong");
    public static final FieldType FIELD_TYPE_SRATIONAL = new FieldTypeRational(10, "SRational");
    public static final FieldType FIELD_TYPE_SSHORT = new FieldTypeShort(8, "SShort");
    public static final FieldType FIELD_TYPE_UNDEFINED = new FieldTypeByte(7, "Undefined");
    public static final FieldType FIELD_TYPE_UNKNOWN = new FieldTypeUnknown();

    static {
        FieldTypeByte fieldTypeByte = FIELD_TYPE_BYTE;
        FieldTypeASCII fieldTypeASCII = FIELD_TYPE_ASCII;
        FieldTypeShort fieldTypeShort = FIELD_TYPE_SHORT;
        FieldTypeLong fieldTypeLong = FIELD_TYPE_LONG;
        FieldTypeRational fieldTypeRational = FIELD_TYPE_RATIONAL;
        FIELD_TYPES = new FieldType[]{fieldTypeByte, fieldTypeASCII, fieldTypeShort, fieldTypeLong, fieldTypeRational, FIELD_TYPE_SBYTE, FIELD_TYPE_UNDEFINED, FIELD_TYPE_SSHORT, FIELD_TYPE_SLONG, FIELD_TYPE_SRATIONAL, FIELD_TYPE_FLOAT, FIELD_TYPE_DOUBLE};
        FIELD_TYPE_DESCRIPTION_LONG = new FieldType[]{fieldTypeLong};
        FIELD_TYPE_DESCRIPTION_SHORT = new FieldType[]{fieldTypeShort};
        FIELD_TYPE_DESCRIPTION_SHORT_OR_LONG = new FieldType[]{fieldTypeShort, fieldTypeLong};
        FIELD_TYPE_DESCRIPTION_ASCII = new FieldType[]{fieldTypeASCII};
        FIELD_TYPE_DESCRIPTION_LONG_OR_SHORT = new FieldType[]{fieldTypeShort, fieldTypeLong};
        FIELD_TYPE_DESCRIPTION_RATIONAL = new FieldType[]{fieldTypeRational};
        FIELD_TYPE_DESCRIPTION_BYTE_OR_SHORT = new FieldType[]{fieldTypeShort, fieldTypeByte};
        FIELD_TYPE_DESCRIPTION_BYTE = new FieldType[]{fieldTypeByte};
    }
}
