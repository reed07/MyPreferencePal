package com.facebook.ads.internal.v.b.a;

import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class e implements a {
    private final ExecutorService a = Executors.newSingleThreadExecutor();

    private class a implements Callable<Void> {
        private final File b;

        public a(File file) {
            this.b = file;
        }

        /* renamed from: a */
        public Void call() {
            e.a(e.this, this.b);
            return null;
        }
    }

    e() {
    }

    static /* synthetic */ void a(e eVar, File file) {
        if (file.exists()) {
            long currentTimeMillis = System.currentTimeMillis();
            if (!file.setLastModified(currentTimeMillis)) {
                long length = file.length();
                if (length != 0) {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
                    long j = length - 1;
                    randomAccessFile.seek(j);
                    byte readByte = randomAccessFile.readByte();
                    randomAccessFile.seek(j);
                    randomAccessFile.write(readByte);
                    randomAccessFile.close();
                } else if (!file.delete() || !file.createNewFile()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Error recreate zero-size file ");
                    sb.append(file);
                    throw new IOException(sb.toString());
                }
                if (file.lastModified() < currentTimeMillis) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Error set last modified date to ");
                    sb2.append(file);
                    throw new IOException(sb2.toString());
                }
            }
        }
        File parentFile = file.getParentFile();
        List linkedList = new LinkedList();
        File[] listFiles = parentFile.listFiles();
        if (listFiles != null) {
            linkedList = Arrays.asList(listFiles);
            Collections.sort(linkedList, new a());
        }
        eVar.a(linkedList);
    }

    private void a(List<File> list) {
        long j = 0;
        for (File length : list) {
            j += length.length();
        }
        int size = list.size();
        for (File file : list) {
            if (!a(file, j, size)) {
                long length2 = file.length();
                if (file.delete()) {
                    size--;
                    j -= length2;
                    StringBuilder sb = new StringBuilder();
                    sb.append("Cache file ");
                    sb.append(file);
                    sb.append(" is deleted because it exceeds cache limit");
                    Log.i("ProxyCache", sb.toString());
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Error deleting file ");
                    sb2.append(file);
                    sb2.append(" for trimming cache");
                    Log.e("ProxyCache", sb2.toString());
                }
            }
        }
    }

    public void a(File file) {
        this.a.submit(new a(file));
    }

    /* access modifiers changed from: protected */
    public abstract boolean a(File file, long j, int i);
}
