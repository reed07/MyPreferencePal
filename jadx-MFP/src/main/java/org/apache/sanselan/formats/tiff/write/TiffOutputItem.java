package org.apache.sanselan.formats.tiff.write;

import java.io.IOException;
import org.apache.sanselan.ImageWriteException;
import org.apache.sanselan.common.BinaryOutputStream;
import org.apache.sanselan.formats.tiff.constants.AllTagConstants;

abstract class TiffOutputItem implements AllTagConstants {
    private int offset = -1;

    public static class Value extends TiffOutputItem {
        private final byte[] bytes;
        private final String name;

        public Value(String str, byte[] bArr) {
            this.name = str;
            this.bytes = bArr;
        }

        public int getItemLength() {
            return this.bytes.length;
        }

        public void updateValue(byte[] bArr) throws ImageWriteException {
            byte[] bArr2 = this.bytes;
            if (bArr2.length == bArr.length) {
                System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Updated data size mismatch: ");
            sb.append(this.bytes.length);
            sb.append(" vs. ");
            sb.append(bArr.length);
            throw new ImageWriteException(sb.toString());
        }

        public void writeItem(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException {
            binaryOutputStream.write(this.bytes);
        }
    }

    public abstract int getItemLength();

    public abstract void writeItem(BinaryOutputStream binaryOutputStream) throws IOException, ImageWriteException;

    TiffOutputItem() {
    }

    /* access modifiers changed from: protected */
    public int getOffset() {
        return this.offset;
    }

    /* access modifiers changed from: protected */
    public void setOffset(int i) {
        this.offset = i;
    }
}
