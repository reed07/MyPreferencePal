package com.uacf.core.util;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class FileUtils {
    private static final String CONTENT_URI_PREFIX = "content://";
    private static final int MD5_CHECKSUM_BUFFER_SIZE = 131072;

    public static String readAllLines(String str) {
        if (Strings.isEmpty(str)) {
            return null;
        }
        return readAllLines(new File(str));
    }

    private static String readAllLines(File file) {
        if (file.exists()) {
            try {
                return readAllLinesFromStream(new FileReader(file));
            } catch (FileNotFoundException e) {
                Ln.e(e);
            }
        }
        return null;
    }

    public static String readAllLinesFromAsset(Context context, String str) {
        try {
            return readAllLines(context.getAssets().open(str));
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }

    public static String readAllLines(InputStream inputStream) {
        return readAllLinesFromStream(new InputStreamReader(inputStream));
    }

    private static String readAllLinesFromStream(Reader reader) {
        if (reader == null) {
            return null;
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return sb.toString();
                }
                sb.append(readLine);
            }
        } catch (IOException e) {
            Ln.e(e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c A[SYNTHETIC, Splitter:B:20:0x003c] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0042 A[SYNTHETIC, Splitter:B:23:0x0042] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String readFileContents(java.lang.String r4) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r4)
            java.lang.StringBuffer r4 = new java.lang.StringBuffer
            r4.<init>()
            boolean r1 = r0.exists()
            if (r1 == 0) goto L_0x0046
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0036 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ IOException -> 0x0036 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0036 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x0036 }
        L_0x001b:
            java.lang.String r0 = r2.readLine()     // Catch:{ IOException -> 0x0031, all -> 0x002e }
            if (r0 == 0) goto L_0x002a
            r4.append(r0)     // Catch:{ IOException -> 0x0031, all -> 0x002e }
            r0 = 10
            r4.append(r0)     // Catch:{ IOException -> 0x0031, all -> 0x002e }
            goto L_0x001b
        L_0x002a:
            r2.close()     // Catch:{ Exception -> 0x0046 }
            goto L_0x0046
        L_0x002e:
            r4 = move-exception
            r1 = r2
            goto L_0x0040
        L_0x0031:
            r0 = move-exception
            r1 = r2
            goto L_0x0037
        L_0x0034:
            r4 = move-exception
            goto L_0x0040
        L_0x0036:
            r0 = move-exception
        L_0x0037:
            com.uacf.core.util.Ln.d(r0)     // Catch:{ all -> 0x0034 }
            if (r1 == 0) goto L_0x0046
            r1.close()     // Catch:{ Exception -> 0x0046 }
            goto L_0x0046
        L_0x0040:
            if (r1 == 0) goto L_0x0045
            r1.close()     // Catch:{ Exception -> 0x0045 }
        L_0x0045:
            throw r4
        L_0x0046:
            java.lang.String r4 = r4.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.FileUtils.readFileContents(java.lang.String):java.lang.String");
    }

    public static void deleteDirectory(File file) throws IOException {
        if (file.exists()) {
            if (!isSymlink(file)) {
                cleanDirectory(file);
            }
            if (!file.delete()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unable to delete directory ");
                sb.append(file);
                sb.append(".");
                throw new IOException(sb.toString());
            }
        }
    }

    public static boolean isSymlink(File file) throws IOException {
        if (file != null) {
            if (file.getParent() != null) {
                file = new File(file.getParentFile().getCanonicalFile(), file.getName());
            }
            return !file.getCanonicalFile().equals(file.getAbsoluteFile());
        }
        throw new NullPointerException("File must not be null");
    }

    public static void cleanDirectory(File file) throws IOException {
        IOException e = null;
        for (File forceDelete : verifiedListFiles(file)) {
            try {
                forceDelete(forceDelete);
            } catch (IOException e2) {
                e = e2;
            }
        }
        if (e != null) {
            throw e;
        }
    }

    private static File[] verifiedListFiles(File file) throws IOException {
        if (!file.exists()) {
            StringBuilder sb = new StringBuilder();
            sb.append(file);
            sb.append(" does not exist");
            throw new IllegalArgumentException(sb.toString());
        } else if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null) {
                return listFiles;
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Failed to list contents of ");
            sb2.append(file);
            throw new IOException(sb2.toString());
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(file);
            sb3.append(" is not a directory");
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    public static void forceDelete(File file) throws IOException {
        if (file.isDirectory()) {
            deleteDirectory(file);
            return;
        }
        boolean exists = file.exists();
        if (file.delete()) {
            return;
        }
        if (!exists) {
            StringBuilder sb = new StringBuilder();
            sb.append("File does not exist: ");
            sb.append(file);
            throw new FileNotFoundException(sb.toString());
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Unable to delete file: ");
        sb2.append(file);
        throw new IOException(sb2.toString());
    }

    public static void copyFile(File file, File file2) throws IOException {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            fileOutputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    fileInputStream.close();
                    return;
                }
            }
        } catch (Throwable th) {
            fileInputStream.close();
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x007c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String copyFile(android.content.Context r3, java.lang.String r4, java.lang.String r5, java.lang.String r6) throws java.io.IOException {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r5)
            r0.mkdirs()
            boolean r0 = r0.exists()
            if (r0 == 0) goto L_0x0080
            java.lang.String r0 = "file://"
            boolean r0 = r4.startsWith(r0)
            if (r0 == 0) goto L_0x001e
            android.net.Uri r4 = android.net.Uri.parse(r4)
            java.lang.String r4 = r4.getPath()
        L_0x001e:
            java.io.File r0 = new java.io.File
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r5)
            r1.append(r6)
            java.lang.String r5 = r1.toString()
            r0.<init>(r5)
            boolean r5 = r0.exists()
            if (r5 == 0) goto L_0x0047
            boolean r5 = r0.delete()
            if (r5 == 0) goto L_0x003f
            goto L_0x0047
        L_0x003f:
            java.io.IOException r3 = new java.io.IOException
            java.lang.String r4 = "output file exists, but couldn't be deleted"
            r3.<init>(r4)
            throw r3
        L_0x0047:
            java.io.InputStream r3 = loadFromLocalFileOrContentUri(r3, r4)
            r4 = 0
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ all -> 0x0071 }
            r5.<init>(r0)     // Catch:{ all -> 0x0071 }
            r4 = 50000(0xc350, float:7.0065E-41)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x006f }
        L_0x0056:
            int r6 = r3.read(r4)     // Catch:{ all -> 0x006f }
            r1 = -1
            if (r6 == r1) goto L_0x0062
            r1 = 0
            r5.write(r4, r1, r6)     // Catch:{ all -> 0x006f }
            goto L_0x0056
        L_0x0062:
            if (r3 == 0) goto L_0x0067
            r3.close()
        L_0x0067:
            r5.close()
            java.lang.String r3 = r0.getAbsolutePath()
            return r3
        L_0x006f:
            r4 = move-exception
            goto L_0x0075
        L_0x0071:
            r5 = move-exception
            r2 = r5
            r5 = r4
            r4 = r2
        L_0x0075:
            if (r3 == 0) goto L_0x007a
            r3.close()
        L_0x007a:
            if (r5 == 0) goto L_0x007f
            r5.close()
        L_0x007f:
            throw r4
        L_0x0080:
            java.io.IOException r3 = new java.io.IOException
            java.lang.String r4 = "unable to create output directory"
            r3.<init>(r4)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.FileUtils.copyFile(android.content.Context, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    public static byte[] readFileBytes(String str) {
        File file = new File(str);
        if (file.exists()) {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = bufferedInputStream.read(bArr);
                    if (read == -1) {
                        return byteArrayOutputStream.toByteArray();
                    }
                    byteArrayOutputStream.write(bArr, 0, read);
                }
            } catch (IOException e) {
                Ln.d(e);
            }
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0037 A[SYNTHETIC, Splitter:B:19:0x0037] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0042 A[SYNTHETIC, Splitter:B:24:0x0042] */
    /* JADX WARNING: Removed duplicated region for block: B:30:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void writeFile(java.lang.String r3, java.lang.String r4, java.lang.String r5) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0031 }
            r1.<init>(r4)     // Catch:{ IOException -> 0x0031 }
            r1.mkdir()     // Catch:{ IOException -> 0x0031 }
            java.io.File r1 = new java.io.File     // Catch:{ IOException -> 0x0031 }
            r1.<init>(r4, r5)     // Catch:{ IOException -> 0x0031 }
            boolean r4 = r1.exists()     // Catch:{ IOException -> 0x0031 }
            if (r4 != 0) goto L_0x0017
            r1.createNewFile()     // Catch:{ IOException -> 0x0031 }
        L_0x0017:
            java.io.BufferedWriter r4 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x0031 }
            java.io.FileWriter r5 = new java.io.FileWriter     // Catch:{ IOException -> 0x0031 }
            r2 = 1
            r5.<init>(r1, r2)     // Catch:{ IOException -> 0x0031 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0031 }
            r4.write(r3)     // Catch:{ IOException -> 0x002c, all -> 0x0029 }
            r4.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x0029:
            r3 = move-exception
            r0 = r4
            goto L_0x0040
        L_0x002c:
            r3 = move-exception
            r0 = r4
            goto L_0x0032
        L_0x002f:
            r3 = move-exception
            goto L_0x0040
        L_0x0031:
            r3 = move-exception
        L_0x0032:
            com.uacf.core.util.Ln.e(r3)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x003f
            r0.close()     // Catch:{ IOException -> 0x003b }
            goto L_0x003f
        L_0x003b:
            r3 = move-exception
            com.uacf.core.util.Ln.e(r3)
        L_0x003f:
            return
        L_0x0040:
            if (r0 == 0) goto L_0x004a
            r0.close()     // Catch:{ IOException -> 0x0046 }
            goto L_0x004a
        L_0x0046:
            r4 = move-exception
            com.uacf.core.util.Ln.e(r4)
        L_0x004a:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.FileUtils.writeFile(java.lang.String, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x002b A[SYNTHETIC, Splitter:B:21:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0033 A[Catch:{ IOException -> 0x002f }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x003e A[SYNTHETIC, Splitter:B:30:0x003e] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0046 A[Catch:{ IOException -> 0x0042 }] */
    /* JADX WARNING: Removed duplicated region for block: B:39:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void appendToFile(java.lang.String r3, java.lang.String r4) {
        /*
            r0 = 0
            java.io.FileWriter r1 = new java.io.FileWriter     // Catch:{ IOException -> 0x0024, all -> 0x0021 }
            r2 = 1
            r1.<init>(r3, r2)     // Catch:{ IOException -> 0x0024, all -> 0x0021 }
            java.io.BufferedWriter r3 = new java.io.BufferedWriter     // Catch:{ IOException -> 0x001f }
            r3.<init>(r1)     // Catch:{ IOException -> 0x001f }
            r3.write(r4)     // Catch:{ IOException -> 0x001c, all -> 0x0019 }
            r3.newLine()     // Catch:{ IOException -> 0x001c, all -> 0x0019 }
            r3.close()     // Catch:{ IOException -> 0x002f }
            r1.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x003a
        L_0x0019:
            r4 = move-exception
            r0 = r3
            goto L_0x003c
        L_0x001c:
            r4 = move-exception
            r0 = r3
            goto L_0x0026
        L_0x001f:
            r4 = move-exception
            goto L_0x0026
        L_0x0021:
            r4 = move-exception
            r1 = r0
            goto L_0x003c
        L_0x0024:
            r4 = move-exception
            r1 = r0
        L_0x0026:
            com.uacf.core.util.Ln.e(r4)     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0031
            r0.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x0031
        L_0x002f:
            r3 = move-exception
            goto L_0x0037
        L_0x0031:
            if (r1 == 0) goto L_0x003a
            r1.close()     // Catch:{ IOException -> 0x002f }
            goto L_0x003a
        L_0x0037:
            com.uacf.core.util.Ln.e(r3)
        L_0x003a:
            return
        L_0x003b:
            r4 = move-exception
        L_0x003c:
            if (r0 == 0) goto L_0x0044
            r0.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x0044
        L_0x0042:
            r3 = move-exception
            goto L_0x004a
        L_0x0044:
            if (r1 == 0) goto L_0x004d
            r1.close()     // Catch:{ IOException -> 0x0042 }
            goto L_0x004d
        L_0x004a:
            com.uacf.core.util.Ln.e(r3)
        L_0x004d:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.FileUtils.appendToFile(java.lang.String, java.lang.String):void");
    }

    public static String getBasename(String str) {
        return getPartsOfFilename(str)[0];
    }

    public static String getExtension(String str) {
        String[] partsOfFilename = getPartsOfFilename(str);
        if (partsOfFilename.length > 1) {
            return partsOfFilename[1];
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0094 A[Catch:{ Exception -> 0x009f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String compress(java.lang.String r8, java.lang.String r9, java.lang.String[] r10, java.lang.String[] r11) {
        /*
            r0 = 0
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x009f }
            r1.<init>(r8)     // Catch:{ Exception -> 0x009f }
            r1.mkdir()     // Catch:{ Exception -> 0x009f }
            java.io.File r1 = new java.io.File     // Catch:{ Exception -> 0x009f }
            r1.<init>(r8, r9)     // Catch:{ Exception -> 0x009f }
            r1.delete()     // Catch:{ Exception -> 0x009f }
            boolean r2 = r1.exists()     // Catch:{ Exception -> 0x009f }
            if (r2 == 0) goto L_0x001a
            r1.delete()     // Catch:{ Exception -> 0x009f }
        L_0x001a:
            r1.createNewFile()     // Catch:{ Exception -> 0x009f }
            java.lang.String r1 = "Compressing file!"
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]     // Catch:{ Exception -> 0x009f }
            com.uacf.core.util.Ln.w(r1, r3)     // Catch:{ Exception -> 0x009f }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x009f }
            r1.<init>()     // Catch:{ Exception -> 0x009f }
            r1.append(r8)     // Catch:{ Exception -> 0x009f }
            java.lang.String r8 = java.io.File.separator     // Catch:{ Exception -> 0x009f }
            r1.append(r8)     // Catch:{ Exception -> 0x009f }
            r1.append(r9)     // Catch:{ Exception -> 0x009f }
            java.lang.String r8 = r1.toString()     // Catch:{ Exception -> 0x009f }
            java.io.FileOutputStream r9 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x009f }
            r9.<init>(r8)     // Catch:{ Exception -> 0x009f }
            java.util.zip.ZipOutputStream r1 = new java.util.zip.ZipOutputStream     // Catch:{ Exception -> 0x009f }
            java.io.BufferedOutputStream r3 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x009f }
            r3.<init>(r9)     // Catch:{ Exception -> 0x009f }
            r1.<init>(r3)     // Catch:{ Exception -> 0x009f }
            r3 = r0
            r9 = 0
        L_0x004a:
            int r4 = r10.length     // Catch:{ Exception -> 0x009f }
            if (r9 >= r4) goto L_0x009b
            java.io.File r4 = new java.io.File     // Catch:{ Exception -> 0x009f }
            r5 = r10[r9]     // Catch:{ Exception -> 0x009f }
            r4.<init>(r5)     // Catch:{ Exception -> 0x009f }
            boolean r4 = r4.exists()     // Catch:{ Exception -> 0x009f }
            if (r4 == 0) goto L_0x0098
            r4 = 2048(0x800, float:2.87E-42)
            byte[] r5 = new byte[r4]     // Catch:{ Exception -> 0x0089 }
            java.io.FileInputStream r6 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0089 }
            r7 = r10[r9]     // Catch:{ Exception -> 0x0089 }
            r6.<init>(r7)     // Catch:{ Exception -> 0x0089 }
            java.io.BufferedInputStream r7 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0089 }
            r7.<init>(r6, r4)     // Catch:{ Exception -> 0x0089 }
            java.util.zip.ZipEntry r3 = new java.util.zip.ZipEntry     // Catch:{ Exception -> 0x0087 }
            r6 = r11[r9]     // Catch:{ Exception -> 0x0087 }
            r3.<init>(r6)     // Catch:{ Exception -> 0x0087 }
            r1.putNextEntry(r3)     // Catch:{ Exception -> 0x0087 }
        L_0x0074:
            int r3 = r7.read(r5, r2, r4)     // Catch:{ Exception -> 0x0087 }
            r6 = -1
            if (r3 == r6) goto L_0x007f
            r1.write(r5, r2, r3)     // Catch:{ Exception -> 0x0087 }
            goto L_0x0074
        L_0x007f:
            r1.closeEntry()     // Catch:{ Exception -> 0x0087 }
            r7.close()     // Catch:{ Exception -> 0x0087 }
            r3 = r7
            goto L_0x0098
        L_0x0087:
            r3 = move-exception
            goto L_0x008c
        L_0x0089:
            r4 = move-exception
            r7 = r3
            r3 = r4
        L_0x008c:
            com.uacf.core.util.Ln.w(r3)     // Catch:{ Exception -> 0x009f }
            r1.closeEntry()     // Catch:{ Exception -> 0x009f }
            if (r7 == 0) goto L_0x0097
            r7.close()     // Catch:{ Exception -> 0x009f }
        L_0x0097:
            r3 = r7
        L_0x0098:
            int r9 = r9 + 1
            goto L_0x004a
        L_0x009b:
            r1.close()     // Catch:{ Exception -> 0x009f }
            return r8
        L_0x009f:
            r8 = move-exception
            com.uacf.core.util.Ln.e(r8)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.util.FileUtils.compress(java.lang.String, java.lang.String, java.lang.String[], java.lang.String[]):java.lang.String");
    }

    private static String[] getPartsOfFilename(String str) {
        String str2 = "";
        String str3 = "";
        if (Strings.notEmpty(str)) {
            str2 = new File(str).getName();
            int lastIndexOf = str2.lastIndexOf(46);
            if (lastIndexOf >= 0) {
                String substring = str2.substring(0, lastIndexOf);
                String substring2 = str2.substring(lastIndexOf);
                str2 = substring;
                str3 = substring2;
            }
        }
        return new String[]{str2, str3};
    }

    public static List<File> getFilesSortedByModifiedTime(File file) {
        if (file == null || !file.exists()) {
            return null;
        }
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return null;
        }
        List<File> asList = Arrays.asList(listFiles);
        if (CollectionUtils.size((Collection<?>) asList) > 1) {
            Collections.sort(asList, new Comparator<File>() {
                public int compare(File file, File file2) {
                    long lastModified = file.lastModified();
                    long lastModified2 = file2.lastModified();
                    if (lastModified < lastModified2) {
                        return -1;
                    }
                    return lastModified2 < lastModified ? 1 : 0;
                }
            });
        }
        return asList;
    }

    public static void recursiveDelete(File file) {
        if (file != null) {
            if (file.isDirectory()) {
                String[] list = file.list();
                for (String file2 : list) {
                    recursiveDelete(new File(file, file2));
                }
            }
            file.delete();
        }
    }

    public static InputStream loadFromLocalFileOrContentUri(Context context, String str) throws FileNotFoundException {
        if (!str.startsWith(CONTENT_URI_PREFIX)) {
            return new FileInputStream(str);
        }
        Uri parse = Uri.parse(str);
        if (parse != null) {
            return context.getContentResolver().openInputStream(parse);
        }
        return null;
    }

    public static String computeChecksum(Context context, String str) throws FileNotFoundException, IOException {
        DigestInputStream digestInputStream;
        InputStream inputStream;
        InputStream inputStream2 = null;
        try {
            inputStream = new BufferedInputStream(loadFromLocalFileOrContentUri(context, str));
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                digestInputStream = new DigestInputStream(inputStream, instance);
                try {
                    byte[] bArr = new byte[131072];
                    while (digestInputStream.available() > 0) {
                        digestInputStream.read(bArr);
                    }
                    String encodeToString = Base64.encodeToString(instance.digest(), 2);
                    closeInputStream(inputStream);
                    closeInputStream(digestInputStream);
                    return encodeToString;
                } catch (NoSuchAlgorithmException e) {
                    e = e;
                    inputStream2 = inputStream;
                    try {
                        throw new RuntimeException(e);
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStream2;
                        closeInputStream(inputStream);
                        closeInputStream(digestInputStream);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    closeInputStream(inputStream);
                    closeInputStream(digestInputStream);
                    throw th;
                }
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                digestInputStream = null;
                inputStream2 = inputStream;
                throw new RuntimeException(e);
            } catch (Throwable th3) {
                th = th3;
                digestInputStream = null;
                closeInputStream(inputStream);
                closeInputStream(digestInputStream);
                throw th;
            }
        } catch (NoSuchAlgorithmException e3) {
            e = e3;
            digestInputStream = null;
            throw new RuntimeException(e);
        } catch (Throwable th4) {
            th = th4;
            digestInputStream = null;
            inputStream = null;
            closeInputStream(inputStream);
            closeInputStream(digestInputStream);
            throw th;
        }
    }

    private static void closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
