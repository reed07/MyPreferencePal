package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class DemuxOutputStream extends OutputStream {
    private final InheritableThreadLocal<OutputStream> m_streams = new InheritableThreadLocal<>();

    public void close() throws IOException {
        OutputStream outputStream = (OutputStream) this.m_streams.get();
        if (outputStream != null) {
            outputStream.close();
        }
    }

    public void flush() throws IOException {
        OutputStream outputStream = (OutputStream) this.m_streams.get();
        if (outputStream != null) {
            outputStream.flush();
        }
    }

    public void write(int i) throws IOException {
        OutputStream outputStream = (OutputStream) this.m_streams.get();
        if (outputStream != null) {
            outputStream.write(i);
        }
    }
}
