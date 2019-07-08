package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class TrueFileFilter implements Serializable, IOFileFilter {
    public static final IOFileFilter INSTANCE = TRUE;
    public static final IOFileFilter TRUE = new TrueFileFilter();

    public boolean accept(File file) {
        return true;
    }

    public boolean accept(File file, String str) {
        return true;
    }

    protected TrueFileFilter() {
    }
}
