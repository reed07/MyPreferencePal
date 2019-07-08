package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import org.apache.commons.io.FileUtils;

public class AgeFileFilter extends AbstractFileFilter implements Serializable {
    private final boolean acceptOlder;
    private final long cutoff;

    public boolean accept(File file) {
        boolean isFileNewer = FileUtils.isFileNewer(file, this.cutoff);
        if (this.acceptOlder) {
            return !isFileNewer;
        }
        return isFileNewer;
    }

    public String toString() {
        String str = this.acceptOlder ? "<=" : ">";
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        sb.append(str);
        sb.append(this.cutoff);
        sb.append(")");
        return sb.toString();
    }
}
