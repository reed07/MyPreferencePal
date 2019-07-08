package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.io.FileUtils;

public class SizeFileComparator extends AbstractFileComparator implements Serializable {
    public static final Comparator<File> SIZE_COMPARATOR = new SizeFileComparator();
    public static final Comparator<File> SIZE_REVERSE = new ReverseComparator(SIZE_COMPARATOR);
    public static final Comparator<File> SIZE_SUMDIR_COMPARATOR = new SizeFileComparator(true);
    public static final Comparator<File> SIZE_SUMDIR_REVERSE = new ReverseComparator(SIZE_SUMDIR_COMPARATOR);
    private final boolean sumDirectoryContents;

    public SizeFileComparator() {
        this.sumDirectoryContents = false;
    }

    public SizeFileComparator(boolean z) {
        this.sumDirectoryContents = z;
    }

    public int compare(File file, File file2) {
        long j = file.isDirectory() ? (!this.sumDirectoryContents || !file.exists()) ? 0 : FileUtils.sizeOfDirectory(file) : file.length();
        long j2 = file2.isDirectory() ? (!this.sumDirectoryContents || !file2.exists()) ? 0 : FileUtils.sizeOfDirectory(file2) : file2.length();
        int i = ((j - j2) > 0 ? 1 : ((j - j2) == 0 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("[sumDirectoryContents=");
        sb.append(this.sumDirectoryContents);
        sb.append("]");
        return sb.toString();
    }
}
