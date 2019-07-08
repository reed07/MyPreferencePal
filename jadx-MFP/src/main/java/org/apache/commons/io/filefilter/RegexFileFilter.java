package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.regex.Pattern;

public class RegexFileFilter extends AbstractFileFilter implements Serializable {
    private final Pattern pattern;

    public boolean accept(File file, String str) {
        return this.pattern.matcher(str).matches();
    }
}
