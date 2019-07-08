package org.apache.commons.io;

import java.io.IOException;
import java.util.Locale;

public class FileSystemUtils {
    private static final String DF;
    private static final FileSystemUtils INSTANCE = new FileSystemUtils();
    private static final int OS;

    static {
        String str = "df";
        int i = -1;
        try {
            String property = System.getProperty("os.name");
            if (property != null) {
                String lowerCase = property.toLowerCase(Locale.ENGLISH);
                if (lowerCase.indexOf("windows") != -1) {
                    i = 1;
                } else {
                    if (lowerCase.indexOf("linux") == -1 && lowerCase.indexOf("mpe/ix") == -1 && lowerCase.indexOf("freebsd") == -1 && lowerCase.indexOf("irix") == -1 && lowerCase.indexOf("digital unix") == -1 && lowerCase.indexOf("unix") == -1) {
                        if (lowerCase.indexOf("mac os x") == -1) {
                            if (lowerCase.indexOf("sun os") == -1 && lowerCase.indexOf("sunos") == -1) {
                                if (lowerCase.indexOf("solaris") == -1) {
                                    if (lowerCase.indexOf("hp-ux") == -1) {
                                        if (lowerCase.indexOf("aix") == -1) {
                                            i = 0;
                                        }
                                    }
                                    i = 3;
                                }
                            }
                            str = "/usr/xpg4/bin/df";
                            i = 3;
                        }
                    }
                    i = 2;
                }
                OS = i;
                DF = str;
                return;
            }
            throw new IOException("os.name not found");
        } catch (Exception unused) {
        }
    }
}
