package org.apache.commons.io.output;

public class CountingOutputStream extends ProxyOutputStream {
    private long count;

    /* access modifiers changed from: protected */
    public synchronized void beforeWrite(int i) {
        this.count += (long) i;
    }
}
