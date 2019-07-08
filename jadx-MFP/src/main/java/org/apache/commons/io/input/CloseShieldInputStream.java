package org.apache.commons.io.input;

public class CloseShieldInputStream extends ProxyInputStream {
    public void close() {
        this.in = new ClosedInputStream();
    }
}
