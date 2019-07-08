package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.io.TaggedIOException;

public class TaggedOutputStream extends ProxyOutputStream {
    private final Serializable tag;

    /* access modifiers changed from: protected */
    public void handleIOException(IOException iOException) throws IOException {
        throw new TaggedIOException(iOException, this.tag);
    }
}
