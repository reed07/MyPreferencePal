package org.apache.sanselan.common;

import java.io.IOException;
import java.io.InputStream;

public class BinaryInputStream extends InputStream implements BinaryConstants {
    private final InputStream is;

    public int read() throws IOException {
        return this.is.read();
    }
}
