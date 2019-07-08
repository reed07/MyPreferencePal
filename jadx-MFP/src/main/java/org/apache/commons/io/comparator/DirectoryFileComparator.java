package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

public class DirectoryFileComparator extends AbstractFileComparator implements Serializable {
    public static final Comparator<File> DIRECTORY_COMPARATOR = new DirectoryFileComparator();
    public static final Comparator<File> DIRECTORY_REVERSE = new ReverseComparator(DIRECTORY_COMPARATOR);

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    public int compare(File file, File file2) {
        return getType(file) - getType(file2);
    }

    private int getType(File file) {
        return file.isDirectory() ? 1 : 2;
    }
}
