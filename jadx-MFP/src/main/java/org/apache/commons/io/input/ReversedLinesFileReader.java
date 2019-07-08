package org.apache.commons.io.input;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ReversedLinesFileReader implements Closeable {
    private final RandomAccessFile randomAccessFile;

    public void close() throws IOException {
        this.randomAccessFile.close();
    }
}
