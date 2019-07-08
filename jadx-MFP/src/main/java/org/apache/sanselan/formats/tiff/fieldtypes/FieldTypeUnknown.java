package org.apache.sanselan.formats.tiff.fieldtypes;

import com.facebook.internal.AnalyticsEvents;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.formats.tiff.TiffField;
import org.apache.sanselan.util.Debug;

public class FieldTypeUnknown extends FieldType {
    public FieldTypeUnknown() {
        super(-1, 1, AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);
    }

    public Object getSimpleValue(TiffField tiffField) {
        if (tiffField.length == 1) {
            return new Byte(tiffField.valueOffsetBytes[0]);
        }
        return getRawBytes(tiffField);
    }

    public byte[] writeData(Object obj, int i) throws ImageWriteException {
        if (obj instanceof Byte) {
            return new byte[]{((Byte) obj).byteValue()};
        } else if (obj instanceof byte[]) {
            return (byte[]) obj;
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
