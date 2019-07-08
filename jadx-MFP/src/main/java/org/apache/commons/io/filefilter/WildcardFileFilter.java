package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOCase;

public class WildcardFileFilter extends AbstractFileFilter implements Serializable {
    private final IOCase caseSensitivity;
    private final String[] wildcards;

    public boolean accept(File file, String str) {
        for (String wildcardMatch : this.wildcards) {
            if (FilenameUtils.wildcardMatch(str, wildcardMatch, this.caseSensitivity)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file) {
        String name = file.getName();
        for (String wildcardMatch : this.wildcards) {
            if (FilenameUtils.wildcardMatch(name, wildcardMatch, this.caseSensitivity)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        if (this.wildcards != null) {
            for (int i = 0; i < this.wildcards.length; i++) {
                if (i > 0) {
                    sb.append(",");
                }
                sb.append(this.wildcards[i]);
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
