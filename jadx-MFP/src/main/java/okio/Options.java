package okio;

import java.util.AbstractList;
import java.util.RandomAccess;

public final class Options extends AbstractList<ByteString> implements RandomAccess {
    final ByteString[] byteStrings;

    public ByteString get(int i) {
        return this.byteStrings[i];
    }

    public int size() {
        return this.byteStrings.length;
    }
}
