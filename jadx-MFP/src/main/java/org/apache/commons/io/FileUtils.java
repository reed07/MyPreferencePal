package org.apache.commons.io;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class FileUtils {
    public static final File[] EMPTY_FILE_ARRAY = new File[0];
    public static final BigInteger ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
    public static final BigInteger ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
    public static final BigInteger ONE_KB_BI = BigInteger.valueOf(1024);
    public static final BigInteger ONE_MB_BI;
    public static final BigInteger ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
    public static final BigInteger ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
    public static final BigInteger ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
    public static final BigInteger ONE_ZB = BigInteger.valueOf(1024).multiply(BigInteger.valueOf(1152921504606846976L));
    private static final Charset UTF8 = Charset.forName("UTF-8");

    static {
        BigInteger bigInteger = ONE_KB_BI;
        ONE_MB_BI = bigInteger.multiply(bigInteger);
    }

    public static long sizeOf(File file) {
        if (!file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append(file);
            sb.append(" does not exist");
            throw new IllegalArgumentException(sb.toString());
        } else if (file.isDirectory()) {
            return sizeOfDirectory(file);
        } else {
            return file.length();
        }
    }

    public static long sizeOfDirectory(File file) {
        checkDirectory(file);
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return 0;
        }
        long j = 0;
        for (File file2 : listFiles) {
            try {
                if (!isSymlink(file2)) {
                    j += sizeOf(file2);
                    if (j < 0) {
                        break;
                    }
                } else {
                    continue;
                }
            } catch (IOException unused) {
            }
        }
        return j;
    }

    private static void checkDirectory(File file) {
        if (!file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append(file);
            sb.append(" does not exist");
            throw new IllegalArgumentException(sb.toString());
        } else if (!file.isDirectory()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(file);
            sb2.append(" is not a directory");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static boolean isFileNewer(File file, long j) {
        if (file != null) {
            boolean z = false;
            if (!file.exists()) {
                return false;
            }
            if (file.lastModified() > j) {
                z = true;
            }
            return z;
        }
        throw new IllegalArgumentException("No specified file");
    }

    public static boolean isSymlink(File file) throws IOException {
        if (file == null) {
            throw new NullPointerException("File must not be null");
        } else if (FilenameUtils.isSystemWindows()) {
            return false;
        } else {
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            if (file.getCanonicalFile().equals(file.getAbsoluteFile())) {
                return false;
            }
            return true;
        }
    }
}
