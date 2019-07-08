package org.apache.commons.io.comparator;

import java.io.File;
import java.io.Serializable;
import java.util.Comparator;

public class CompositeFileComparator extends AbstractFileComparator implements Serializable {
    private static final Comparator<?>[] NO_COMPARATORS = new Comparator[0];
    private final Comparator<File>[] delegates;

    public int compare(File file, File file2) {
        int i = 0;
        for (Comparator<File> compare : this.delegates) {
            i = compare.compare(file, file2);
            if (i != 0) {
                break;
            }
        }
        return i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append('{');
        for (int i = 0; i < this.delegates.length; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(this.delegates[i]);
        }
        sb.append('}');
        return sb.toString();
    }
}
