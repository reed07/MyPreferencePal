package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class FalseFileFilter implements Serializable, IOFileFilter {
    public static final IOFileFilter FALSE = new FalseFileFilter();
    public static final IOFileFilter INSTANCE = FALSE;

    public boolean accept(File file) {
        return false;
    }

    public boolean accept(File file, String str) {
        return false;
    }

    protected FalseFileFilter() {
    }
}
