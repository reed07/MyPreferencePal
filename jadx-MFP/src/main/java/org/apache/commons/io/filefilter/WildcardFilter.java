package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.io.FilenameUtils;

@Deprecated
public class WildcardFilter extends AbstractFileFilter implements Serializable {
    private final String[] wildcards;

    public boolean accept(File file, String str) {
        if (file != null && new File(file, str).isDirectory()) {
            return false;
        }
        for (String wildcardMatch : this.wildcards) {
            if (FilenameUtils.wildcardMatch(str, wildcardMatch)) {
                return true;
            }
        }
        return false;
    }

    public boolean accept(File file) {
        if (file.isDirectory()) {
            return false;
        }
        for (String wildcardMatch : this.wildcards) {
            if (FilenameUtils.wildcardMatch(file.getName(), wildcardMatch)) {
                return true;
            }
        }
        return false;
    }
}
