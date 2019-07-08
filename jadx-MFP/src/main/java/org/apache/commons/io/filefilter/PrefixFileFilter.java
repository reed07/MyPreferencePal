package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.io.IOCase;

public class PrefixFileFilter extends AbstractFileFilter implements Serializable {
    private final IOCase caseSensitivity;
    private final String[] prefixes;

    public boolean accept(File file) {
        String name = file.getName();
        for (String checkStartsWith : this.prefixes) {
            if (this.caseSensitivity.checkStartsWith(name, checkStartsWith)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file, String str) {
        for (String checkStartsWith : this.prefixes) {
            if (this.caseSensitivity.checkStartsWith(str, checkStartsWith)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        if (this.prefixes != null) {
            for (int i = 0; i < this.prefixes.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(this.prefixes[i]);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
