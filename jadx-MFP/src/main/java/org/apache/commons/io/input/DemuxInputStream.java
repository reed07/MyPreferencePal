package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;

public class DemuxInputStream extends InputStream {
    private final InheritableThreadLocal<InputStream> m_streams = new InheritableThreadLocal<>();

    public void close() throws IOException {
        InputStream inputStream = (InputStream) this.m_streams.get();
        if (inputStream != null) {
            inputStream.close();
        }
    }

    public int read() throws IOException {
        InputStream inputStream = (InputStream) this.m_streams.get();
        if (inputStream != null) {
            return inputStream.read();
        }
        return -1;
    }
}
