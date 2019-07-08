package org.apache.sanselan.formats.tiff;

import java.util.Comparator;

public abstract class TiffElement {
    public static final Comparator COMPARATOR = new Comparator() {
        public int compare(Object obj, Object obj2) {
            return ((TiffElement) obj).offset - ((TiffElement) obj2).offset;
        }
    };
    public final int length;
    public final int offset;

    public static abstract class DataElement extends TiffElement {
        public final byte[] data;

        public DataElement(int i, int i2, byte[] bArr) {
            super(i, i2);
            this.data = bArr;
        }
    }

    public static final class Stub extends TiffElement {
        public Stub(int i, int i2) {
            super(i, i2);
        }
    }

    public TiffElement(int i, int i2) {
        this.offset = i;
        this.length = i2;
    }
}
