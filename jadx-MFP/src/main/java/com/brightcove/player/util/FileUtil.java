package com.brightcove.player.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

public class FileUtil {

    public static class StrictMode {
        @NonNull
        public static File makeReadWriteDirectory(@Nullable final File file) {
            return (File) Observable.fromCallable(new Callable<File>() {
                public File call() throws Exception {
                    return FileUtil.makeReadWriteDirectory(file);
                }
            }).subscribeOn(Schedulers.io()).blockingSingle();
        }

        @NonNull
        public static File getExternalDirectory(@NonNull final Context context, @Nullable final String str, @Nullable final String str2) {
            return (File) Observable.fromCallable(new Callable<File>() {
                public File call() throws Exception {
                    return FileUtil.getExternalDirectory(context, str, str2);
                }
            }).subscribeOn(Schedulers.io()).blockingSingle();
        }

        public static boolean delete(@NonNull final File file) {
            return ((Boolean) Observable.fromCallable(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return Boolean.valueOf(FileUtil.delete(file));
                }
            }).subscribeOn(Schedulers.io()).blockingSingle()).booleanValue();
        }

        public static boolean isFile(@Nullable final String str) {
            return ((Boolean) Observable.fromCallable(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return Boolean.valueOf(FileUtil.isFile(str));
                }
            }).subscribeOn(Schedulers.io()).blockingSingle()).booleanValue();
        }

        public static boolean isDirectory(@Nullable final String str) {
            return ((Boolean) Observable.fromCallable(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return Boolean.valueOf(FileUtil.isDirectory(str));
                }
            }).subscribeOn(Schedulers.io()).blockingSingle()).booleanValue();
        }

        public static boolean exists(@Nullable final String str) {
            return ((Boolean) Observable.fromCallable(new Callable<Boolean>() {
                public Boolean call() throws Exception {
                    return Boolean.valueOf(FileUtil.exists(str));
                }
            }).subscribeOn(Schedulers.io()).blockingSingle()).booleanValue();
        }
    }

    private FileUtil() {
    }

    @NonNull
    public static File makeReadWriteDirectory(@Nullable File file) {
        if (file == null || (!file.isDirectory() && !file.mkdirs())) {
            StringBuilder sb = new StringBuilder();
            sb.append("Directory path is invalid: ");
            sb.append(file);
            throw new IllegalArgumentException(sb.toString());
        } else if (!file.canRead()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Directory path is not readable: ");
            sb2.append(file);
            throw new IllegalArgumentException(sb2.toString());
        } else if (file.canWrite()) {
            return file;
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Directory path is not writable: ");
            sb3.append(file);
            throw new IllegalArgumentException(sb3.toString());
        }
    }

    public static boolean isFileValid(@Nullable File file) {
        try {
            makeReadWriteDirectory(file);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    @NonNull
    public static File getExternalDirectory(@NonNull Context context, @Nullable String str, @Nullable String str2) {
        File externalFilesDir = context.getExternalFilesDir(str);
        return str2 != null ? new File(externalFilesDir, str2) : externalFilesDir;
    }

    public static boolean delete(@NonNull File file) {
        if (!file.exists()) {
            return true;
        }
        if (file.isDirectory()) {
            for (File delete : file.listFiles()) {
                delete(delete);
            }
        }
        return file.delete();
    }

    public static String getFileName(String str) {
        String str2 = "";
        String str3 = str.split("\\?")[0];
        if (str3 != null && !str3.isEmpty()) {
            String[] split = str3.split("/");
            if (split != null && split.length > 0) {
                String str4 = split[split.length - 1];
                if (str4.contains(".")) {
                    return str4;
                }
            }
        }
        return str2;
    }

    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void saveInputStream(@NonNull File file, @NonNull InputStream inputStream) throws IOException {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        bufferedOutputStream2.write(bArr, 0, read);
                        bufferedOutputStream2.flush();
                    } else {
                        closeQuietly(bufferedOutputStream2);
                        return;
                    }
                }
            } catch (Throwable th) {
                th = th;
                bufferedOutputStream = bufferedOutputStream2;
                closeQuietly(bufferedOutputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            closeQuietly(bufferedOutputStream);
            throw th;
        }
    }

    public static boolean isFile(@Nullable String str) {
        return str != null && new File(str).isFile();
    }

    public static boolean isDirectory(@Nullable String str) {
        return str != null && new File(str).isDirectory();
    }

    public static boolean exists(@Nullable String str) {
        return str != null && new File(str).exists();
    }
}
