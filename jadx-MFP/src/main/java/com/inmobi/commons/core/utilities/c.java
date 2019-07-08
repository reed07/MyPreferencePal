package com.inmobi.commons.core.utilities;

import android.net.Uri;
import java.io.File;

/* compiled from: FileUtils */
public class c {
    private static final String a = "c";

    public static void a(File file) {
        try {
            if (file.exists()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2.isDirectory()) {
                            a(file2);
                        } else {
                            file2.delete();
                        }
                    }
                }
                file.delete();
            }
        } catch (Exception e) {
            new StringBuilder("SDK encountered unexpected error in deleting directory; ").append(e.getMessage());
        }
    }

    public static long a(String str) {
        try {
            File file = new File(Uri.parse(str).getPath());
            if (file.exists()) {
                return file.length();
            }
            return 0;
        } catch (Exception unused) {
            return 0;
        }
    }
}
