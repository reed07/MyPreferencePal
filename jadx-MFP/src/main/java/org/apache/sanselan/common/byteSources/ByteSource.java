package org.apache.sanselan.common.byteSources;

import java.io.IOException;
import java.io.InputStream;
import org.apache.sanselan.common.BinaryFileFunctions;

public abstract class ByteSource extends BinaryFileFunctions {
    protected final String filename;

    public abstract byte[] getBlock(int i, int i2) throws IOException;

    public abstract InputStream getInputStream() throws IOException;

    public abstract long getLength() throws IOException;

    public ByteSource(String str) {
        this.filename = str;
    }

    public final String getFilename() {
        return this.filename;
    }
}
