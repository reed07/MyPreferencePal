package org.apache.commons.io.input;

import java.io.IOException;

public class CountingInputStream extends ProxyInputStream {
    private long count;

    public synchronized long skip(long j) throws IOException {
        long skip;
        skip = super.skip(j);
        this.count += skip;
        return skip;
    }

    /* access modifiers changed from: protected */
    public synchronized void afterRead(int i) {
        if (i != -1) {
            this.count += (long) i;
        }
    }
}
