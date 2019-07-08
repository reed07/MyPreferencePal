package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class EmptyFileFilter extends AbstractFileFilter implements Serializable {
    public static final IOFileFilter EMPTY = new EmptyFileFilter();
    public static final IOFileFilter NOT_EMPTY = new NotFileFilter(EMPTY);

    protected EmptyFileFilter() {
    }

    public boolean accept(File file) {
        boolean z = true;
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (!(listFiles == null || listFiles.length == 0)) {
                z = false;
            }
            return z;
        }
        if (file.length() != 0) {
            z = false;
        }
        return z;
    }
}
