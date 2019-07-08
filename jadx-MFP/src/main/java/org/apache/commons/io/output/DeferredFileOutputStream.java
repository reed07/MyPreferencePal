package org.apache.commons.io.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DeferredFileOutputStream extends ThresholdingOutputStream {
    private boolean closed;
    private OutputStream currentOutputStream;
    private final File directory;
    private ByteArrayOutputStream memoryOutputStream;
    private File outputFile;
    private final String prefix;
    private final String suffix;

    /* access modifiers changed from: protected */
    public OutputStream getStream() throws IOException {
        return this.currentOutputStream;
    }

    /* access modifiers changed from: protected */
    public void thresholdReached() throws IOException {
        String str = this.prefix;
        if (str != null) {
            this.outputFile = File.createTempFile(str, this.suffix, this.directory);
        }
        FileOutputStream fileOutputStream = new FileOutputStream(this.outputFile);
        this.memoryOutputStream.writeTo(fileOutputStream);
        this.currentOutputStream = fileOutputStream;
        this.memoryOutputStream = null;
    }

    public void close() throws IOException {
        super.close();
        this.closed = true;
    }
}
