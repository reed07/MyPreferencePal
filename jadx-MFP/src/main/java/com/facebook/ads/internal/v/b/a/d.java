package com.facebook.ads.internal.v.b.a;

import java.io.File;
import java.util.Comparator;

class d {

    private static final class a implements Comparator<File> {
        private a() {
        }

        /* renamed from: a */
        public int compare(File file, File file2) {
            int i = (file.lastModified() > file2.lastModified() ? 1 : (file.lastModified() == file2.lastModified() ? 0 : -1));
            if (i < 0) {
                return -1;
            }
            return i == 0 ? 0 : 1;
        }
    }
}
