package com.twitter.sdk.android.core.internal.scribe;

import java.io.IOException;

public interface FileRollOverManager {
    void cancelTimeBasedFileRollOver();

    boolean rollFileOver() throws IOException;
}
