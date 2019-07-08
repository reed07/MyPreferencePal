package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.io.IOCase;

public class NameFileFilter extends AbstractFileFilter implements Serializable {
    private final IOCase caseSensitivity;
    private final String[] names;

    public NameFileFilter(String str) {
        this(str, null);
    }

    public NameFileFilter(String str, IOCase iOCase) {
        if (str != null) {
            this.names = new String[]{str};
            if (iOCase == null) {
                iOCase = IOCase.SENSITIVE;
            }
            this.caseSensitivity = iOCase;
            return;
        }
        throw new IllegalArgumentException("The wildcard must not be null");
    }

    public boolean accept(File file) {
        String name = file.getName();
        for (String checkEquals : this.names) {
            if (this.caseSensitivity.checkEquals(name, checkEquals)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file, String str) {
        for (String checkEquals : this.names) {
            if (this.caseSensitivity.checkEquals(str, checkEquals)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        if (this.names != null) {
            for (int i = 0; i < this.names.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(this.names[i]);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
