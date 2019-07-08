package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Serializable;
import org.apache.commons.io.TaggedIOException;

public class TaggedInputStream extends ProxyInputStream {
    private final Serializable tag;

    /* access modifiers changed from: protected */
    public void handleIOException(IOException iOException) throws IOException {
        throw new TaggedIOException(iOException, this.tag);
    }
}
