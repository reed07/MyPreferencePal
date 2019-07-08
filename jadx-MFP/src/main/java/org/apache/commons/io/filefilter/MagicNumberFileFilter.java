package org.apache.commons.io.filefilter;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.io.IOUtils;

public class MagicNumberFileFilter extends AbstractFileFilter implements Serializable {
    private static final long serialVersionUID = -547733176983104172L;
    private final long byteOffset;
    private final byte[] magicNumbers;

    public boolean accept(File file) {
        RandomAccessFile randomAccessFile;
        byte[] bArr;
        if (file != null && file.isFile() && file.canRead()) {
            try {
                bArr = new byte[this.magicNumbers.length];
                randomAccessFile = new RandomAccessFile(file, "r");
            } catch (IOException unused) {
                randomAccessFile = null;
                IOUtils.closeQuietly((Closeable) randomAccessFile);
                return false;
            } catch (Throwable th) {
                th = th;
                randomAccessFile = null;
                IOUtils.closeQuietly((Closeable) randomAccessFile);
                throw th;
            }
            try {
                randomAccessFile.seek(this.byteOffset);
                if (randomAccessFile.read(bArr) != this.magicNumbers.length) {
                    IOUtils.closeQuietly((Closeable) randomAccessFile);
                    return false;
                }
                boolean equals = Arrays.equals(this.magicNumbers, bArr);
                IOUtils.closeQuietly((Closeable) randomAccessFile);
                return equals;
            } catch (IOException unused2) {
                IOUtils.closeQuietly((Closeable) randomAccessFile);
                return false;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.closeQuietly((Closeable) randomAccessFile);
                throw th;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append("(");
        sb.append(new String(this.magicNumbers));
        sb.append(",");
        sb.append(this.byteOffset);
        sb.append(")");
        return sb.toString();
    }
}
