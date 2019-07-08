package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;

public class SizeFileFilter extends AbstractFileFilter implements Serializable {
    private final boolean acceptLarger;
    private final long size;

    public boolean accept(File file) {
        boolean z = file.length() < this.size;
        if (!this.acceptLarger) {
            return z;
        }
        if (!z) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str = this.acceptLarger ? ">=" : "<";
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        sb.append(str);
        sb.append(this.size);
        sb.append(")");
        return sb.toString();
    }
}
