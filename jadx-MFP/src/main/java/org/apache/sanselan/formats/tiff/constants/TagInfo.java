package org.apache.sanselan.formats.tiff.constants;

import com.google.android.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.sanselan.ImageReadException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryFileFunctions;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.formats.tiff.constants.TiffDirectoryConstants.ExifDirectoryType;
import org.apache.sanselan.formats.tiff.fieldtypes.FieldType;
import org.apache.sanselan.util.Debug;

public class TagInfo implements TiffDirectoryConstants, TiffFieldTypeConstants {
    public final FieldType[] dataTypes;
    public final ExifDirectoryType directoryType;
    public final int length;
    public final String name;
    public final int tag;

    public static class Date extends TagInfo {
        private static final DateFormat DATE_FORMAT_1 = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        private static final DateFormat DATE_FORMAT_2 = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0016, code lost:
            return DATE_FORMAT_2.parse(r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
            org.apache.sanselan.util.Debug.debug((java.lang.Throwable) r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001b, code lost:
            return r3;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:4:0x0010 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Object getValue(org.apache.sanselan.formats.tiff.TiffField r3) throws org.apache.sanselan.ImageReadException {
            /*
                r2 = this;
                org.apache.sanselan.formats.tiff.fieldtypes.FieldType r0 = r3.fieldType
                java.lang.Object r3 = r0.getSimpleValue(r3)
                r0 = r3
                java.lang.String r0 = (java.lang.String) r0
                java.text.DateFormat r1 = DATE_FORMAT_1     // Catch:{ Exception -> 0x0010 }
                java.util.Date r3 = r1.parse(r0)     // Catch:{ Exception -> 0x0010 }
                return r3
            L_0x0010:
                java.text.DateFormat r1 = DATE_FORMAT_2     // Catch:{ Exception -> 0x0017 }
                java.util.Date r3 = r1.parse(r0)     // Catch:{ Exception -> 0x0017 }
                return r3
            L_0x0017:
                r0 = move-exception
                org.apache.sanselan.util.Debug.debug(r0)
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.sanselan.formats.tiff.constants.TagInfo.Date.getValue(org.apache.sanselan.formats.tiff.TiffField):java.lang.Object");
        }

        public byte[] encodeValue(FieldType fieldType, Object obj, int i) throws ImageWriteException {
            StringBuilder sb = new StringBuilder();
            sb.append("date encode value: ");
            sb.append(obj);
            sb.append(" (");
            sb.append(Debug.getType(obj));
            sb.append(")");
            throw new ImageWriteException(sb.toString());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[TagInfo. tag: ");
            sb.append(this.tag);
            sb.append(", name: ");
            sb.append(this.name);
            sb.append(" (data)");
            sb.append("]");
            return sb.toString();
        }
    }

    public static class Offset extends TagInfo {
        public Offset(String str, int i, FieldType[] fieldTypeArr, int i2, ExifDirectoryType exifDirectoryType) {
            super(str, i, fieldTypeArr, i2, exifDirectoryType);
        }

        public Offset(String str, int i, FieldType fieldType, int i2, ExifDirectoryType exifDirectoryType) {
            super(str, i, fieldType, i2, exifDirectoryType);
        }
    }

    public static final class Text extends TagInfo {
        private static final TextEncoding[] TEXT_ENCODINGS = {TEXT_ENCODING_ASCII, TEXT_ENCODING_JIS, TEXT_ENCODING_UNICODE, TEXT_ENCODING_UNDEFINED};
        private static final TextEncoding TEXT_ENCODING_ASCII = new TextEncoding(new byte[]{65, 83, 67, 73, 73, 0, 0, 0}, C.ASCII_NAME);
        private static final TextEncoding TEXT_ENCODING_JIS = new TextEncoding(new byte[]{74, 73, 83, 0, 0, 0, 0, 0}, "JIS");
        private static final TextEncoding TEXT_ENCODING_UNDEFINED = new TextEncoding(new byte[]{0, 0, 0, 0, 0, 0, 0, 0}, "ISO-8859-1");
        private static final TextEncoding TEXT_ENCODING_UNICODE = new TextEncoding(new byte[]{85, 78, 73, 67, 79, 68, 69, 0}, "UTF-8");

        private static final class TextEncoding {
            public final String encodingName;
            public final byte[] prefix;

            public TextEncoding(byte[] bArr, String str) {
                this.prefix = bArr;
                this.encodingName = str;
            }
        }

        public Text(String str, int i, FieldType fieldType, int i2, ExifDirectoryType exifDirectoryType) {
            super(str, i, fieldType, i2, exifDirectoryType);
        }

        public Text(String str, int i, FieldType[] fieldTypeArr, int i2, ExifDirectoryType exifDirectoryType) {
            super(str, i, fieldTypeArr, i2, exifDirectoryType);
        }

        public byte[] encodeValue(FieldType fieldType, Object obj, int i) throws ImageWriteException {
            if (obj instanceof String) {
                String str = (String) obj;
                try {
                    byte[] bytes = str.getBytes(TEXT_ENCODING_ASCII.encodingName);
                    if (new String(bytes, TEXT_ENCODING_ASCII.encodingName).equals(str)) {
                        byte[] bArr = new byte[(bytes.length + TEXT_ENCODING_ASCII.prefix.length)];
                        System.arraycopy(TEXT_ENCODING_ASCII.prefix, 0, bArr, 0, TEXT_ENCODING_ASCII.prefix.length);
                        System.arraycopy(bytes, 0, bArr, TEXT_ENCODING_ASCII.prefix.length, bytes.length);
                        return bArr;
                    }
                    byte[] bytes2 = str.getBytes(TEXT_ENCODING_UNICODE.encodingName);
                    byte[] bArr2 = new byte[(bytes2.length + TEXT_ENCODING_UNICODE.prefix.length)];
                    System.arraycopy(TEXT_ENCODING_UNICODE.prefix, 0, bArr2, 0, TEXT_ENCODING_UNICODE.prefix.length);
                    System.arraycopy(bytes2, 0, bArr2, TEXT_ENCODING_UNICODE.prefix.length, bytes2.length);
                    return bArr2;
                } catch (UnsupportedEncodingException e) {
                    throw new ImageWriteException(e.getMessage(), e);
                }
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Text value not String: ");
                sb.append(obj);
                sb.append(" (");
                sb.append(Debug.getType(obj));
                sb.append(")");
                throw new ImageWriteException(sb.toString());
            }
        }

        public Object getValue(TiffField tiffField) throws ImageReadException {
            if (tiffField.type == FIELD_TYPE_ASCII.type) {
                return FIELD_TYPE_ASCII.getSimpleValue(tiffField);
            }
            if (tiffField.type == FIELD_TYPE_UNDEFINED.type || tiffField.type == FIELD_TYPE_BYTE.type) {
                byte[] rawBytes = tiffField.fieldType.getRawBytes(tiffField);
                if (rawBytes.length < 8) {
                    try {
                        return new String(rawBytes, C.ASCII_NAME);
                    } catch (UnsupportedEncodingException unused) {
                        throw new ImageReadException("Text field missing encoding prefix.");
                    }
                } else {
                    int i = 0;
                    while (true) {
                        TextEncoding[] textEncodingArr = TEXT_ENCODINGS;
                        if (i < textEncodingArr.length) {
                            TextEncoding textEncoding = textEncodingArr[i];
                            if (BinaryFileFunctions.compareBytes(rawBytes, 0, textEncoding.prefix, 0, textEncoding.prefix.length)) {
                                try {
                                    return new String(rawBytes, textEncoding.prefix.length, rawBytes.length - textEncoding.prefix.length, textEncoding.encodingName);
                                } catch (UnsupportedEncodingException e) {
                                    throw new ImageReadException(e.getMessage(), e);
                                }
                            } else {
                                i++;
                            }
                        } else {
                            try {
                                return new String(rawBytes, C.ASCII_NAME);
                            } catch (UnsupportedEncodingException unused2) {
                                throw new ImageReadException("Unknown text encoding prefix.");
                            }
                        }
                    }
                }
            } else {
                Debug.debug("entry.type", tiffField.type);
                Debug.debug("entry.directoryType", tiffField.directoryType);
                Debug.debug("entry.type", tiffField.getDescriptionWithoutValue());
                Debug.debug("entry.type", (Object) tiffField.fieldType);
                throw new ImageReadException("Text field not encoded as bytes.");
            }
        }
    }

    public static final class Unknown extends TagInfo {
        public Unknown(String str, int i, FieldType[] fieldTypeArr, int i2, ExifDirectoryType exifDirectoryType) {
            super(str, i, fieldTypeArr, i2, exifDirectoryType);
        }

        public byte[] encodeValue(FieldType fieldType, Object obj, int i) throws ImageWriteException {
            return TagInfo.super.encodeValue(fieldType, obj, i);
        }

        public Object getValue(TiffField tiffField) throws ImageReadException {
            return TagInfo.super.getValue(tiffField);
        }
    }

    public TagInfo(String str, int i, FieldType fieldType, int i2, ExifDirectoryType exifDirectoryType) {
        this(str, i, new FieldType[]{fieldType}, i2, exifDirectoryType);
    }

    public TagInfo(String str, int i, FieldType[] fieldTypeArr, int i2, ExifDirectoryType exifDirectoryType) {
        this.name = str;
        this.tag = i;
        this.dataTypes = fieldTypeArr;
        this.length = i2;
        this.directoryType = exifDirectoryType;
    }

    public Object getValue(TiffField tiffField) throws ImageReadException {
        return tiffField.fieldType.getSimpleValue(tiffField);
    }

    public byte[] encodeValue(FieldType fieldType, Object obj, int i) throws ImageWriteException {
        return fieldType.writeData(obj, i);
    }

    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.tag);
        sb.append(" (0x");
        sb.append(Integer.toHexString(this.tag));
        sb.append(": ");
        sb.append(this.name);
        sb.append("): ");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[TagInfo. tag: ");
        sb.append(this.tag);
        sb.append(" (0x");
        sb.append(Integer.toHexString(this.tag));
        sb.append(", name: ");
        sb.append(this.name);
        sb.append("]");
        return sb.toString();
    }
}
