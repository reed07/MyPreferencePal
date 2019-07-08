package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AndFileFilter extends AbstractFileFilter implements Serializable, ConditionalFileFilter {
    private final List<IOFileFilter> fileFilters;

    public AndFileFilter() {
        this.fileFilters = new ArrayList();
    }

    public AndFileFilter(List<IOFileFilter> list) {
        if (list == null) {
            this.fileFilters = new ArrayList();
        } else {
            this.fileFilters = new ArrayList(list);
        }
    }

    public AndFileFilter(IOFileFilter iOFileFilter, IOFileFilter iOFileFilter2) {
        if (iOFileFilter == null || iOFileFilter2 == null) {
            throw new IllegalArgumentException("The filters must not be null");
        }
        this.fileFilters = new ArrayList(2);
        addFileFilter(iOFileFilter);
        addFileFilter(iOFileFilter2);
    }

    public void addFileFilter(IOFileFilter iOFileFilter) {
        this.fileFilters.add(iOFileFilter);
    }

    public boolean accept(File file) {
        if (this.fileFilters.isEmpty()) {
            return false;
        }
        for (IOFileFilter accept : this.fileFilters) {
            if (!accept.accept(file)) {
                return false;
            }
        }
        return true;
    }

    public boolean accept(File file, String str) {
        if (this.fileFilters.isEmpty()) {
            return false;
        }
        for (IOFileFilter accept : this.fileFilters) {
            if (!accept.accept(file, str)) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        if (this.fileFilters != null) {
            for (int i = 0; i < this.fileFilters.size(); i++) {
                if (i > 0) {
                    sb.append(",");
                }
                Object obj = this.fileFilters.get(i);
                if (obj == null) {
                    str = "null";
                } else {
                    str = obj.toString();
                }
                sb.append(str);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
