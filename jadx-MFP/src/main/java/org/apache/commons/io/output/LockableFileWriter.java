package org.apache.commons.io.output;

import java.io.File;
import java.io.IOException;
import java.io.Writer;

public class LockableFileWriter extends Writer {
    private final File lockFile;
    private final Writer out;

    private void testLockDir(File file) throws IOException {
        if (!file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Could not find lockDir: ");
            sb.append(file.getAbsolutePath());
            throw new IOException(sb.toString());
        } else if (!file.canWrite()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Could not write to lockDir: ");
            sb2.append(file.getAbsolutePath());
            throw new IOException(sb2.toString());
        }
    }

    public void close() throws IOException {
        try {
            this.out.close();
        } finally {
            this.lockFile.delete();
        }
    }

    public void write(int i) throws IOException {
        this.out.write(i);
    }

    public void write(char[] cArr) throws IOException {
        this.out.write(cArr);
    }

    public void write(char[] cArr, int i, int i2) throws IOException {
        this.out.write(cArr, i, i2);
    }

    public void write(String str) throws IOException {
        this.out.write(str);
    }

    public void write(String str, int i, int i2) throws IOException {
        this.out.write(str, i, i2);
    }

    public void flush() throws IOException {
        this.out.flush();
    }
}
