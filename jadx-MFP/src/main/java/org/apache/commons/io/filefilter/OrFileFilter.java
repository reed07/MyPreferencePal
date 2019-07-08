package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrFileFilter extends AbstractFileFilter implements Serializable, ConditionalFileFilter {
    private final List<IOFileFilter> fileFilters = new ArrayList();

    public boolean accept(File file) {
        for (IOFileFilter accept : this.fileFilters) {
            if (accept.accept(file)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file, String str) {
        for (IOFileFilter accept : this.fileFilters) {
            if (accept.accept(file, str)) {
                return true;
            }
        }
        return false;
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
