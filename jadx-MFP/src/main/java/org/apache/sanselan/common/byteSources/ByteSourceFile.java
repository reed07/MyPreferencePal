package org.apache.sanselan.common.byteSources;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import org.apache.sanselan.util.Debug;

public class ByteSourceFile extends ByteSource {
    private final File file;

    public ByteSourceFile(File file2) {
        super(file2.getName());
        this.file = file2;
    }

    public InputStream getInputStream() throws IOException {
        return new BufferedInputStream(new FileInputStream(this.file));
    }

    public byte[] getBlock(int i, int i2) throws IOException {
        RandomAccessFile randomAccessFile = null;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(this.file, "r");
            try {
                byte[] rAFBytes = getRAFBytes(randomAccessFile2, (long) i, i2, "Could not read value from file");
                try {
                    randomAccessFile2.close();
                } catch (Exception e) {
                    Debug.debug((Throwable) e);
                }
                return rAFBytes;
            } catch (Throwable th) {
                th = th;
                randomAccessFile = randomAccessFile2;
                try {
                    randomAccessFile.close();
                } catch (Exception e2) {
                    Debug.debug((Throwable) e2);
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile.close();
            throw th;
        }
    }

    public long getLength() {
        return this.file.length();
    }
}
