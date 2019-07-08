package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.io.IOCase;

public class SuffixFileFilter extends AbstractFileFilter implements Serializable {
    private final IOCase caseSensitivity;
    private final String[] suffixes;

    public boolean accept(File file) {
        String name = file.getName();
        for (String checkEndsWith : this.suffixes) {
            if (this.caseSensitivity.checkEndsWith(name, checkEndsWith)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file, String str) {
        for (String checkEndsWith : this.suffixes) {
            if (this.caseSensitivity.checkEndsWith(str, checkEndsWith)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        if (this.suffixes != null) {
            for (int i = 0; i < this.suffixes.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(this.suffixes[i]);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
