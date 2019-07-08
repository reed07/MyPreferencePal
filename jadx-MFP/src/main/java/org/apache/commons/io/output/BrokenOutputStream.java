package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class BrokenOutputStream extends OutputStream {
    private final IOException exception;

    public BrokenOutputStream(IOException iOException) {
        this.exception = iOException;
    }

    public BrokenOutputStream() {
        this(new IOException("Broken output stream"));
    }

    public void write(int i) throws IOException {
        throw this.exception;
    }

    public void flush() throws IOException {
        throw this.exception;
    }

    public void close() throws IOException {
        throw this.exception;
    }
}
