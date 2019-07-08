package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;

public class ClosedOutputStream extends OutputStream {
    public static final ClosedOutputStream CLOSED_OUTPUT_STREAM = new ClosedOutputStream();

    public void write(int i) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("write(");
        sb.append(i);
        sb.append(") failed: stream is closed");
        throw new IOException(sb.toString());
    }
}
