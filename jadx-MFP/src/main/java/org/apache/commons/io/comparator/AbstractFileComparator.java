package org.apache.commons.io.comparator;

import java.io.File;
import java.util.Comparator;

abstract class AbstractFileComparator implements Comparator<File> {
    AbstractFileComparator() {
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
