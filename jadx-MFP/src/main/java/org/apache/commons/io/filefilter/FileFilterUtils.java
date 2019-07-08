package org.apache.commons.io.filefilter;

import java.util.ArrayList;
import java.util.List;

public class FileFilterUtils {
    private static final IOFileFilter cvsFilter = notFileFilter(and(directoryFileFilter(), nameFileFilter("CVS")));
    private static final IOFileFilter svnFilter = notFileFilter(and(directoryFileFilter(), nameFileFilter(".svn")));

    public static IOFileFilter nameFileFilter(String str) {
        return new NameFileFilter(str);
    }

    public static IOFileFilter directoryFileFilter() {
        return DirectoryFileFilter.DIRECTORY;
    }

    public static IOFileFilter and(IOFileFilter... iOFileFilterArr) {
        return new AndFileFilter(toList(iOFileFilterArr));
    }

    public static List<IOFileFilter> toList(IOFileFilter... iOFileFilterArr) {
        if (iOFileFilterArr != null) {
            ArrayList arrayList = new ArrayList(iOFileFilterArr.length);
            int i = 0;
            while (i < iOFileFilterArr.length) {
                if (iOFileFilterArr[i] != null) {
                    arrayList.add(iOFileFilterArr[i]);
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("The filter[");
                    sb.append(i);
                    sb.append("] is null");
                    throw new IllegalArgumentException(sb.toString());
                }
            }
            return arrayList;
        }
        throw new IllegalArgumentException("The filters must not be null");
    }

    public static IOFileFilter notFileFilter(IOFileFilter iOFileFilter) {
        return new NotFileFilter(iOFileFilter);
    }
}
