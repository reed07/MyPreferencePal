package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class NotFileFilter extends AbstractFileFilter implements Serializable {
    private final IOFileFilter filter;

    public NotFileFilter(IOFileFilter iOFileFilter) {
        if (iOFileFilter != null) {
            this.filter = iOFileFilter;
            return;
        }
        throw new IllegalArgumentException("The filter must not be null");
    }

    public boolean accept(File file) {
        return !this.filter.accept(file);
    }

    public boolean accept(File file, String str) {
        return !this.filter.accept(file, str);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        sb.append(this.filter.toString());
        sb.append(")");
        return sb.toString();
    }
}
