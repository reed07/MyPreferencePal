package com.liulishuo.filedownloader.stream;

import com.liulishuo.filedownloader.util.FileDownloadHelper.OutputStreamCreator;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileDownloadRandomAccessFile implements FileDownloadOutputStream {
    private final FileDescriptor fd = this.randomAccess.getFD();
    private final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(this.randomAccess.getFD()));
    private final RandomAccessFile randomAccess;

    public static class Creator implements OutputStreamCreator {
        public boolean supportSeek() {
            return true;
        }

        public FileDownloadOutputStream create(File file) throws IOException {
            return new FileDownloadRandomAccessFile(file);
        }
    }

    FileDownloadRandomAccessFile(File file) throws IOException {
        this.randomAccess = new RandomAccessFile(file, "rw");
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
    }

    public void flushAndSync() throws IOException {
        this.out.flush();
        this.fd.sync();
    }

    public void close() throws IOException {
        this.out.close();
        this.randomAccess.close();
    }

    public void seek(long j) throws IOException {
        this.randomAccess.seek(j);
    }

    public void setLength(long j) throws IOException {
        this.randomAccess.setLength(j);
    }
}
