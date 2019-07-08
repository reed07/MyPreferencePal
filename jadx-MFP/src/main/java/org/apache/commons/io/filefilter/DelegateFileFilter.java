package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.Serializable;

public class DelegateFileFilter extends AbstractFileFilter implements Serializable {
    private final FileFilter fileFilter;
    private final FilenameFilter filenameFilter;

    public boolean accept(File file) {
        FileFilter fileFilter2 = this.fileFilter;
        if (fileFilter2 != null) {
            return fileFilter2.accept(file);
        }
        return super.accept(file);
    }

    public boolean accept(File file, String str) {
        FilenameFilter filenameFilter2 = this.filenameFilter;
        if (filenameFilter2 != null) {
            return filenameFilter2.accept(file, str);
        }
        return super.accept(file, str);
    }

    public String toString() {
        Object obj = this.fileFilter;
        if (obj == null) {
            obj = this.filenameFilter;
        }
        String obj2 = obj.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("(");
        sb.append(obj2);
        sb.append(")");
        return sb.toString();
    }
}
