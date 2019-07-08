package org.apache.commons.io.output;

public class CloseShieldOutputStream extends ProxyOutputStream {
    public void close() {
        this.out = new ClosedOutputStream();
    }
}
