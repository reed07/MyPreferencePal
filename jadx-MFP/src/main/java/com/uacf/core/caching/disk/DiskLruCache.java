package com.uacf.core.caching.disk;

import com.facebook.appevents.AppEventsConstants;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class DiskLruCache implements Closeable {
    private final int appVersion;
    private final Callable<Void> cleanupCallable;
    /* access modifiers changed from: private */
    public final File directory;
    private final ExecutorService executorService;
    private final File journalFile;
    private final File journalFileTmp;
    /* access modifiers changed from: private */
    public Writer journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private final long maxSize;
    private long nextSequenceNumber = 0;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size = 0;
    /* access modifiers changed from: private */
    public final int valueCount;

    public final class Editor {
        /* access modifiers changed from: private */
        public final Entry entry;
        /* access modifiers changed from: private */
        public boolean hasErrors;
        /* access modifiers changed from: private */
        public final boolean[] written;

        private class FaultHidingOutputStream extends FilterOutputStream {
            private FaultHidingOutputStream(OutputStream outputStream) {
                super(outputStream);
            }

            public void write(int i) {
                try {
                    this.out.write(i);
                } catch (IOException unused) {
                    Editor.this.hasErrors = true;
                }
            }

            public void write(byte[] bArr, int i, int i2) {
                try {
                    this.out.write(bArr, i, i2);
                } catch (IOException unused) {
                    Editor.this.hasErrors = true;
                }
            }

            public void close() {
                try {
                    this.out.close();
                } catch (IOException unused) {
                    Editor.this.hasErrors = true;
                }
            }

            public void flush() {
                try {
                    this.out.flush();
                } catch (IOException unused) {
                    Editor.this.hasErrors = true;
                }
            }
        }

        private Editor(Entry entry2) {
            this.entry = entry2;
            this.written = entry2.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        public OutputStream newOutputStream(int i) throws IOException {
            FaultHidingOutputStream faultHidingOutputStream;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor == this) {
                    if (!this.entry.readable) {
                        this.written[i] = true;
                    }
                    faultHidingOutputStream = new FaultHidingOutputStream(new FileOutputStream(this.entry.getDirtyFile(i)));
                } else {
                    throw new IllegalStateException();
                }
            }
            return faultHidingOutputStream;
        }

        public void commit() throws IOException {
            if (this.hasErrors) {
                DiskLruCache.this.completeEdit(this, false);
                DiskLruCache.this.remove(this.entry.key);
                return;
            }
            DiskLruCache.this.completeEdit(this, true);
        }

        public void abort() throws IOException {
            DiskLruCache.this.completeEdit(this, false);
        }
    }

    private final class Entry {
        /* access modifiers changed from: private */
        public Editor currentEditor;
        /* access modifiers changed from: private */
        public final String key;
        /* access modifiers changed from: private */
        public final long[] lengths;
        /* access modifiers changed from: private */
        public boolean readable;
        /* access modifiers changed from: private */
        public long sequenceNumber;

        private Entry(String str) {
            this.key = str;
            this.lengths = new long[DiskLruCache.this.valueCount];
        }

        public String getLengths() throws IOException {
            long[] jArr;
            StringBuilder sb = new StringBuilder();
            for (long j : this.lengths) {
                sb.append(' ');
                sb.append(j);
            }
            return sb.toString();
        }

        /* access modifiers changed from: private */
        public void setLengths(String[] strArr) throws IOException {
            if (strArr.length == DiskLruCache.this.valueCount) {
                int i = 0;
                while (i < strArr.length) {
                    try {
                        this.lengths[i] = Long.parseLong(strArr[i]);
                        i++;
                    } catch (NumberFormatException unused) {
                        throw invalidLengths(strArr);
                    }
                }
                return;
            }
            throw invalidLengths(strArr);
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected journal line: ");
            sb.append(Arrays.toString(strArr));
            throw new IOException(sb.toString());
        }

        public File getCleanFile(int i) {
            File access$2300 = DiskLruCache.this.directory;
            StringBuilder sb = new StringBuilder();
            sb.append(this.key);
            sb.append(".");
            sb.append(i);
            return new File(access$2300, sb.toString());
        }

        public File getDirtyFile(int i) {
            File access$2300 = DiskLruCache.this.directory;
            StringBuilder sb = new StringBuilder();
            sb.append(this.key);
            sb.append(".");
            sb.append(i);
            sb.append(".tmp");
            return new File(access$2300, sb.toString());
        }
    }

    public final class Snapshot implements Closeable {
        private final InputStream[] ins;
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;

        private Snapshot(String str, long j, InputStream[] inputStreamArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.ins = inputStreamArr;
            this.lengths = jArr;
        }

        public InputStream getInputStream(int i) {
            return this.ins[i];
        }

        public void close() {
            for (InputStream access$1800 : this.ins) {
                DiskLruCache.this.closeQuietly(access$1800);
            }
        }
    }

    private DiskLruCache(File file, int i, int i2, long j) {
        File file2 = file;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.executorService = threadPoolExecutor;
        this.cleanupCallable = new Callable<Void>() {
            /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
                return null;
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Void call() throws java.lang.Exception {
                /*
                    r4 = this;
                    com.uacf.core.caching.disk.DiskLruCache r0 = com.uacf.core.caching.disk.DiskLruCache.this
                    monitor-enter(r0)
                    com.uacf.core.caching.disk.DiskLruCache r1 = com.uacf.core.caching.disk.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    java.io.Writer r1 = r1.journalWriter     // Catch:{ all -> 0x0028 }
                    r2 = 0
                    if (r1 != 0) goto L_0x000e
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    return r2
                L_0x000e:
                    com.uacf.core.caching.disk.DiskLruCache r1 = com.uacf.core.caching.disk.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    r1.trimToSize()     // Catch:{ all -> 0x0028 }
                    com.uacf.core.caching.disk.DiskLruCache r1 = com.uacf.core.caching.disk.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    boolean r1 = r1.journalRebuildRequired()     // Catch:{ all -> 0x0028 }
                    if (r1 == 0) goto L_0x0026
                    com.uacf.core.caching.disk.DiskLruCache r1 = com.uacf.core.caching.disk.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    r1.rebuildJournal()     // Catch:{ all -> 0x0028 }
                    com.uacf.core.caching.disk.DiskLruCache r1 = com.uacf.core.caching.disk.DiskLruCache.this     // Catch:{ all -> 0x0028 }
                    r3 = 0
                    r1.redundantOpCount = r3     // Catch:{ all -> 0x0028 }
                L_0x0026:
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    return r2
                L_0x0028:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x0028 }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.caching.disk.DiskLruCache.AnonymousClass1.call():java.lang.Void");
            }
        };
        this.directory = file2;
        this.appVersion = i;
        this.journalFile = new File(file2, "journal");
        this.journalFileTmp = new File(file2, "journal.tmp");
        this.valueCount = i2;
        this.maxSize = j;
    }

    public static DiskLruCache open(File file, int i, int i2, long j) throws IOException {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            DiskLruCache diskLruCache = new DiskLruCache(file, i, i2, j);
            if (diskLruCache.journalFile.exists()) {
                try {
                    diskLruCache.readJournal();
                    diskLruCache.processJournal();
                    diskLruCache.journalWriter = new BufferedWriter(new FileWriter(diskLruCache.journalFile, true));
                    return diskLruCache;
                } catch (IOException e) {
                    PrintStream printStream = System.out;
                    StringBuilder sb = new StringBuilder();
                    sb.append("DiskLruCache, ");
                    sb.append(file);
                    sb.append(" is corrupt: ");
                    sb.append(e.getMessage());
                    sb.append(", removing");
                    printStream.println(sb.toString());
                    diskLruCache.delete();
                }
            }
            file.mkdirs();
            DiskLruCache diskLruCache2 = new DiskLruCache(file, i, i2, j);
            diskLruCache2.rebuildJournal();
            return diskLruCache2;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:16|17|18|19) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.redundantOpCount = r1 - r8.lruEntries.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0069, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005d */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x006a=Splitter:B:20:0x006a, B:16:0x005d=Splitter:B:16:0x005d} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readJournal() throws java.io.IOException {
        /*
            r8 = this;
            com.uacf.core.util.StrictLineReader r0 = new com.uacf.core.util.StrictLineReader
            java.io.FileInputStream r1 = new java.io.FileInputStream
            java.io.File r2 = r8.journalFile
            r1.<init>(r2)
            java.nio.charset.Charset r2 = com.uacf.core.util.StrictLineReader.US_ASCII
            r0.<init>(r1, r2)
            java.lang.String r1 = r0.readLine()     // Catch:{ all -> 0x009e }
            java.lang.String r2 = r0.readLine()     // Catch:{ all -> 0x009e }
            java.lang.String r3 = r0.readLine()     // Catch:{ all -> 0x009e }
            java.lang.String r4 = r0.readLine()     // Catch:{ all -> 0x009e }
            java.lang.String r5 = r0.readLine()     // Catch:{ all -> 0x009e }
            java.lang.String r6 = "com.uacf.core.caching.disk.DiskLruCache"
            boolean r6 = r6.equals(r1)     // Catch:{ all -> 0x009e }
            if (r6 == 0) goto L_0x006a
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r2)     // Catch:{ all -> 0x009e }
            if (r6 == 0) goto L_0x006a
            int r6 = r8.appVersion     // Catch:{ all -> 0x009e }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x009e }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x009e }
            if (r3 == 0) goto L_0x006a
            int r3 = r8.valueCount     // Catch:{ all -> 0x009e }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ all -> 0x009e }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x009e }
            if (r3 == 0) goto L_0x006a
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x009e }
            if (r3 == 0) goto L_0x006a
            r1 = 0
        L_0x0053:
            java.lang.String r2 = r0.readLine()     // Catch:{ EOFException -> 0x005d }
            r8.readJournalLine(r2)     // Catch:{ EOFException -> 0x005d }
            int r1 = r1 + 1
            goto L_0x0053
        L_0x005d:
            java.util.LinkedHashMap<java.lang.String, com.uacf.core.caching.disk.DiskLruCache$Entry> r2 = r8.lruEntries     // Catch:{ all -> 0x009e }
            int r2 = r2.size()     // Catch:{ all -> 0x009e }
            int r1 = r1 - r2
            r8.redundantOpCount = r1     // Catch:{ all -> 0x009e }
            r8.closeQuietly(r0)
            return
        L_0x006a:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x009e }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
            r6.<init>()     // Catch:{ all -> 0x009e }
            java.lang.String r7 = "unexpected journal header: ["
            r6.append(r7)     // Catch:{ all -> 0x009e }
            r6.append(r1)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x009e }
            r6.append(r2)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x009e }
            r6.append(r4)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x009e }
            r6.append(r5)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = "]"
            r6.append(r1)     // Catch:{ all -> 0x009e }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x009e }
            r3.<init>(r1)     // Catch:{ all -> 0x009e }
            throw r3     // Catch:{ all -> 0x009e }
        L_0x009e:
            r1 = move-exception
            r8.closeQuietly(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.caching.disk.DiskLruCache.readJournal():void");
    }

    private void readJournalLine(String str) throws IOException {
        String str2;
        int indexOf = str.indexOf(32);
        if (indexOf != -1) {
            int i = indexOf + 1;
            int indexOf2 = str.indexOf(32, i);
            if (indexOf2 == -1) {
                str2 = str.substring(i);
                if (indexOf == 6 && str.startsWith("REMOVE")) {
                    this.lruEntries.remove(str2);
                    return;
                }
            } else {
                str2 = str.substring(i, indexOf2);
            }
            Entry entry = (Entry) this.lruEntries.get(str2);
            if (entry == null) {
                entry = new Entry(str2);
                this.lruEntries.put(str2, entry);
            }
            if (indexOf2 != -1 && indexOf == 5 && str.startsWith("CLEAN")) {
                String[] split = str.substring(indexOf2 + 1).split(" ");
                entry.readable = true;
                entry.currentEditor = null;
                entry.setLengths(split);
            } else if (indexOf2 == -1 && indexOf == 5 && str.startsWith("DIRTY")) {
                entry.currentEditor = new Editor(entry);
            } else if (!(indexOf2 == -1 && indexOf == 4 && str.startsWith("READ"))) {
                StringBuilder sb = new StringBuilder();
                sb.append("unexpected journal line: ");
                sb.append(str);
                throw new IOException(sb.toString());
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("unexpected journal line: ");
        sb2.append(str);
        throw new IOException(sb2.toString());
    }

    private void processJournal() throws IOException {
        deleteIfExists(this.journalFileTmp);
        Iterator it = this.lruEntries.values().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int i = 0;
            if (entry.currentEditor == null) {
                while (i < this.valueCount) {
                    this.size += entry.lengths[i];
                    i++;
                }
            } else {
                entry.currentEditor = null;
                while (i < this.valueCount) {
                    deleteIfExists(entry.getCleanFile(i));
                    deleteIfExists(entry.getDirtyFile(i));
                    i++;
                }
                it.remove();
            }
        }
    }

    /* access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        if (this.journalWriter != null) {
            this.journalWriter.close();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.journalFileTmp));
        bufferedWriter.write("com.uacf.core.caching.disk.DiskLruCache");
        bufferedWriter.write("\n");
        bufferedWriter.write(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.appVersion));
        bufferedWriter.write("\n");
        bufferedWriter.write(Integer.toString(this.valueCount));
        bufferedWriter.write("\n");
        bufferedWriter.write("\n");
        for (Entry entry : this.lruEntries.values()) {
            if (entry.currentEditor != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("DIRTY ");
                sb.append(entry.key);
                sb.append(10);
                bufferedWriter.write(sb.toString());
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("CLEAN ");
                sb2.append(entry.key);
                sb2.append(entry.getLengths());
                sb2.append(10);
                bufferedWriter.write(sb2.toString());
            }
        }
        bufferedWriter.close();
        this.journalFileTmp.renameTo(this.journalFile);
        this.journalWriter = new BufferedWriter(new FileWriter(this.journalFile, true));
    }

    private static void deleteIfExists(File file) throws IOException {
        file.delete();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:32|33|28|27) */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        r11.redundantOpCount++;
        r1 = r11.journalWriter;
        r2 = new java.lang.StringBuilder();
        r2.append("READ ");
        r2.append(r12);
        r2.append(10);
        r1.append(r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0059, code lost:
        if (journalRebuildRequired() == false) goto L_0x0062;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005b, code lost:
        r11.executorService.submit(r11.cleanupCallable);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0062, code lost:
        r3 = new com.uacf.core.caching.disk.DiskLruCache.Snapshot(r11, r12, com.uacf.core.caching.disk.DiskLruCache.Entry.access$1200(r0), r8, com.uacf.core.caching.disk.DiskLruCache.Entry.access$1000(r0), null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0074, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0086, code lost:
        return null;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0075 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.uacf.core.caching.disk.DiskLruCache.Snapshot get(java.lang.String r12) throws java.io.IOException {
        /*
            r11 = this;
            monitor-enter(r11)
            r11.checkNotClosed()     // Catch:{ all -> 0x0087 }
            r11.validateKey(r12)     // Catch:{ all -> 0x0087 }
            java.util.LinkedHashMap<java.lang.String, com.uacf.core.caching.disk.DiskLruCache$Entry> r0 = r11.lruEntries     // Catch:{ all -> 0x0087 }
            java.lang.Object r0 = r0.get(r12)     // Catch:{ all -> 0x0087 }
            com.uacf.core.caching.disk.DiskLruCache$Entry r0 = (com.uacf.core.caching.disk.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0087 }
            r1 = 0
            if (r0 != 0) goto L_0x0014
            monitor-exit(r11)
            return r1
        L_0x0014:
            boolean r2 = r0.readable     // Catch:{ all -> 0x0087 }
            if (r2 != 0) goto L_0x001c
            monitor-exit(r11)
            return r1
        L_0x001c:
            int r2 = r11.valueCount     // Catch:{ all -> 0x0087 }
            java.io.InputStream[] r8 = new java.io.InputStream[r2]     // Catch:{ all -> 0x0087 }
            r2 = 0
            r3 = 0
        L_0x0022:
            int r4 = r11.valueCount     // Catch:{ FileNotFoundException -> 0x0075 }
            if (r3 >= r4) goto L_0x0034
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ FileNotFoundException -> 0x0075 }
            java.io.File r5 = r0.getCleanFile(r3)     // Catch:{ FileNotFoundException -> 0x0075 }
            r4.<init>(r5)     // Catch:{ FileNotFoundException -> 0x0075 }
            r8[r3] = r4     // Catch:{ FileNotFoundException -> 0x0075 }
            int r3 = r3 + 1
            goto L_0x0022
        L_0x0034:
            int r1 = r11.redundantOpCount     // Catch:{ all -> 0x0087 }
            int r1 = r1 + 1
            r11.redundantOpCount = r1     // Catch:{ all -> 0x0087 }
            java.io.Writer r1 = r11.journalWriter     // Catch:{ all -> 0x0087 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0087 }
            r2.<init>()     // Catch:{ all -> 0x0087 }
            java.lang.String r3 = "READ "
            r2.append(r3)     // Catch:{ all -> 0x0087 }
            r2.append(r12)     // Catch:{ all -> 0x0087 }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x0087 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0087 }
            r1.append(r2)     // Catch:{ all -> 0x0087 }
            boolean r1 = r11.journalRebuildRequired()     // Catch:{ all -> 0x0087 }
            if (r1 == 0) goto L_0x0062
            java.util.concurrent.ExecutorService r1 = r11.executorService     // Catch:{ all -> 0x0087 }
            java.util.concurrent.Callable<java.lang.Void> r2 = r11.cleanupCallable     // Catch:{ all -> 0x0087 }
            r1.submit(r2)     // Catch:{ all -> 0x0087 }
        L_0x0062:
            com.uacf.core.caching.disk.DiskLruCache$Snapshot r1 = new com.uacf.core.caching.disk.DiskLruCache$Snapshot     // Catch:{ all -> 0x0087 }
            long r6 = r0.sequenceNumber     // Catch:{ all -> 0x0087 }
            long[] r9 = r0.lengths     // Catch:{ all -> 0x0087 }
            r10 = 0
            r3 = r1
            r4 = r11
            r5 = r12
            r3.<init>(r5, r6, r8, r9)     // Catch:{ all -> 0x0087 }
            monitor-exit(r11)
            return r1
        L_0x0075:
            int r12 = r11.valueCount     // Catch:{ all -> 0x0087 }
            if (r2 >= r12) goto L_0x0085
            r12 = r8[r2]     // Catch:{ all -> 0x0087 }
            if (r12 == 0) goto L_0x0085
            r12 = r8[r2]     // Catch:{ all -> 0x0087 }
            r11.closeQuietly(r12)     // Catch:{ all -> 0x0087 }
            int r2 = r2 + 1
            goto L_0x0075
        L_0x0085:
            monitor-exit(r11)
            return r1
        L_0x0087:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.caching.disk.DiskLruCache.get(java.lang.String):com.uacf.core.caching.disk.DiskLruCache$Snapshot");
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.uacf.core.caching.disk.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.checkNotClosed()     // Catch:{ all -> 0x0061 }
            r5.validateKey(r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.uacf.core.caching.disk.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x0061 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0061 }
            com.uacf.core.caching.disk.DiskLruCache$Entry r0 = (com.uacf.core.caching.disk.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0061 }
            r1 = -1
            r3 = 0
            int r4 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r4 == 0) goto L_0x0022
            if (r0 == 0) goto L_0x0020
            long r1 = r0.sequenceNumber     // Catch:{ all -> 0x0061 }
            int r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0022
        L_0x0020:
            monitor-exit(r5)
            return r3
        L_0x0022:
            if (r0 != 0) goto L_0x002f
            com.uacf.core.caching.disk.DiskLruCache$Entry r0 = new com.uacf.core.caching.disk.DiskLruCache$Entry     // Catch:{ all -> 0x0061 }
            r0.<init>(r6)     // Catch:{ all -> 0x0061 }
            java.util.LinkedHashMap<java.lang.String, com.uacf.core.caching.disk.DiskLruCache$Entry> r7 = r5.lruEntries     // Catch:{ all -> 0x0061 }
            r7.put(r6, r0)     // Catch:{ all -> 0x0061 }
            goto L_0x0037
        L_0x002f:
            com.uacf.core.caching.disk.DiskLruCache$Editor r7 = r0.currentEditor     // Catch:{ all -> 0x0061 }
            if (r7 == 0) goto L_0x0037
            monitor-exit(r5)
            return r3
        L_0x0037:
            com.uacf.core.caching.disk.DiskLruCache$Editor r7 = new com.uacf.core.caching.disk.DiskLruCache$Editor     // Catch:{ all -> 0x0061 }
            r7.<init>(r0)     // Catch:{ all -> 0x0061 }
            r0.currentEditor = r7     // Catch:{ all -> 0x0061 }
            java.io.Writer r8 = r5.journalWriter     // Catch:{ all -> 0x0061 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0061 }
            r0.<init>()     // Catch:{ all -> 0x0061 }
            java.lang.String r1 = "DIRTY "
            r0.append(r1)     // Catch:{ all -> 0x0061 }
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            r6 = 10
            r0.append(r6)     // Catch:{ all -> 0x0061 }
            java.lang.String r6 = r0.toString()     // Catch:{ all -> 0x0061 }
            r8.write(r6)     // Catch:{ all -> 0x0061 }
            java.io.Writer r6 = r5.journalWriter     // Catch:{ all -> 0x0061 }
            r6.flush()     // Catch:{ all -> 0x0061 }
            monitor-exit(r5)
            return r7
        L_0x0061:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.caching.disk.DiskLruCache.edit(java.lang.String, long):com.uacf.core.caching.disk.DiskLruCache$Editor");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x011a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void completeEdit(com.uacf.core.caching.disk.DiskLruCache.Editor r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            com.uacf.core.caching.disk.DiskLruCache$Entry r0 = r10.entry     // Catch:{ all -> 0x0121 }
            com.uacf.core.caching.disk.DiskLruCache$Editor r1 = r0.currentEditor     // Catch:{ all -> 0x0121 }
            if (r1 != r10) goto L_0x011b
            r1 = 0
            if (r11 == 0) goto L_0x0063
            boolean r2 = r0.readable     // Catch:{ all -> 0x0121 }
            if (r2 != 0) goto L_0x0063
            r2 = 0
        L_0x0015:
            int r3 = r9.valueCount     // Catch:{ all -> 0x0121 }
            if (r2 >= r3) goto L_0x0063
            boolean[] r3 = r10.written     // Catch:{ all -> 0x0121 }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x0121 }
            if (r3 == 0) goto L_0x0049
            java.io.File r3 = r0.getDirtyFile(r2)     // Catch:{ all -> 0x0121 }
            boolean r3 = r3.exists()     // Catch:{ all -> 0x0121 }
            if (r3 != 0) goto L_0x0046
            r10.abort()     // Catch:{ all -> 0x0121 }
            java.io.PrintStream r10 = java.lang.System.out     // Catch:{ all -> 0x0121 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0121 }
            r11.<init>()     // Catch:{ all -> 0x0121 }
            java.lang.String r0 = "DiskLruCache, Newly created entry doesn't have file for index "
            r11.append(r0)     // Catch:{ all -> 0x0121 }
            r11.append(r2)     // Catch:{ all -> 0x0121 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0121 }
            r10.println(r11)     // Catch:{ all -> 0x0121 }
            monitor-exit(r9)
            return
        L_0x0046:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0049:
            r10.abort()     // Catch:{ all -> 0x0121 }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0121 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0121 }
            r11.<init>()     // Catch:{ all -> 0x0121 }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r11.append(r0)     // Catch:{ all -> 0x0121 }
            r11.append(r2)     // Catch:{ all -> 0x0121 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0121 }
            r10.<init>(r11)     // Catch:{ all -> 0x0121 }
            throw r10     // Catch:{ all -> 0x0121 }
        L_0x0063:
            int r10 = r9.valueCount     // Catch:{ all -> 0x0121 }
            if (r1 >= r10) goto L_0x0097
            java.io.File r10 = r0.getDirtyFile(r1)     // Catch:{ all -> 0x0121 }
            if (r11 == 0) goto L_0x0091
            boolean r2 = r10.exists()     // Catch:{ all -> 0x0121 }
            if (r2 == 0) goto L_0x0094
            java.io.File r2 = r0.getCleanFile(r1)     // Catch:{ all -> 0x0121 }
            r10.renameTo(r2)     // Catch:{ all -> 0x0121 }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x0121 }
            r3 = r10[r1]     // Catch:{ all -> 0x0121 }
            long r5 = r2.length()     // Catch:{ all -> 0x0121 }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x0121 }
            r10[r1] = r5     // Catch:{ all -> 0x0121 }
            long r7 = r9.size     // Catch:{ all -> 0x0121 }
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.size = r7     // Catch:{ all -> 0x0121 }
            goto L_0x0094
        L_0x0091:
            deleteIfExists(r10)     // Catch:{ all -> 0x0121 }
        L_0x0094:
            int r1 = r1 + 1
            goto L_0x0063
        L_0x0097:
            int r10 = r9.redundantOpCount     // Catch:{ all -> 0x0121 }
            r1 = 1
            int r10 = r10 + r1
            r9.redundantOpCount = r10     // Catch:{ all -> 0x0121 }
            r10 = 0
            r0.currentEditor = r10     // Catch:{ all -> 0x0121 }
            boolean r10 = r0.readable     // Catch:{ all -> 0x0121 }
            r10 = r10 | r11
            r2 = 10
            if (r10 == 0) goto L_0x00de
            r0.readable = r1     // Catch:{ all -> 0x0121 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0121 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0121 }
            r1.<init>()     // Catch:{ all -> 0x0121 }
            java.lang.String r3 = "CLEAN "
            r1.append(r3)     // Catch:{ all -> 0x0121 }
            java.lang.String r3 = r0.key     // Catch:{ all -> 0x0121 }
            r1.append(r3)     // Catch:{ all -> 0x0121 }
            java.lang.String r3 = r0.getLengths()     // Catch:{ all -> 0x0121 }
            r1.append(r3)     // Catch:{ all -> 0x0121 }
            r1.append(r2)     // Catch:{ all -> 0x0121 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0121 }
            r10.write(r1)     // Catch:{ all -> 0x0121 }
            if (r11 == 0) goto L_0x0104
            long r10 = r9.nextSequenceNumber     // Catch:{ all -> 0x0121 }
            r1 = 1
            long r1 = r1 + r10
            r9.nextSequenceNumber = r1     // Catch:{ all -> 0x0121 }
            r0.sequenceNumber = r10     // Catch:{ all -> 0x0121 }
            goto L_0x0104
        L_0x00de:
            java.util.LinkedHashMap<java.lang.String, com.uacf.core.caching.disk.DiskLruCache$Entry> r10 = r9.lruEntries     // Catch:{ all -> 0x0121 }
            java.lang.String r11 = r0.key     // Catch:{ all -> 0x0121 }
            r10.remove(r11)     // Catch:{ all -> 0x0121 }
            java.io.Writer r10 = r9.journalWriter     // Catch:{ all -> 0x0121 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0121 }
            r11.<init>()     // Catch:{ all -> 0x0121 }
            java.lang.String r1 = "REMOVE "
            r11.append(r1)     // Catch:{ all -> 0x0121 }
            java.lang.String r0 = r0.key     // Catch:{ all -> 0x0121 }
            r11.append(r0)     // Catch:{ all -> 0x0121 }
            r11.append(r2)     // Catch:{ all -> 0x0121 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0121 }
            r10.write(r11)     // Catch:{ all -> 0x0121 }
        L_0x0104:
            long r10 = r9.size     // Catch:{ all -> 0x0121 }
            long r0 = r9.maxSize     // Catch:{ all -> 0x0121 }
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x0112
            boolean r10 = r9.journalRebuildRequired()     // Catch:{ all -> 0x0121 }
            if (r10 == 0) goto L_0x0119
        L_0x0112:
            java.util.concurrent.ExecutorService r10 = r9.executorService     // Catch:{ all -> 0x0121 }
            java.util.concurrent.Callable<java.lang.Void> r11 = r9.cleanupCallable     // Catch:{ all -> 0x0121 }
            r10.submit(r11)     // Catch:{ all -> 0x0121 }
        L_0x0119:
            monitor-exit(r9)
            return
        L_0x011b:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0121 }
            r10.<init>()     // Catch:{ all -> 0x0121 }
            throw r10     // Catch:{ all -> 0x0121 }
        L_0x0121:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.caching.disk.DiskLruCache.completeEdit(com.uacf.core.caching.disk.DiskLruCache$Editor, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0090, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0092, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean remove(java.lang.String r8) throws java.io.IOException {
        /*
            r7 = this;
            monitor-enter(r7)
            r7.checkNotClosed()     // Catch:{ all -> 0x0093 }
            r7.validateKey(r8)     // Catch:{ all -> 0x0093 }
            java.util.LinkedHashMap<java.lang.String, com.uacf.core.caching.disk.DiskLruCache$Entry> r0 = r7.lruEntries     // Catch:{ all -> 0x0093 }
            java.lang.Object r0 = r0.get(r8)     // Catch:{ all -> 0x0093 }
            com.uacf.core.caching.disk.DiskLruCache$Entry r0 = (com.uacf.core.caching.disk.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0093 }
            r1 = 0
            if (r0 == 0) goto L_0x0091
            com.uacf.core.caching.disk.DiskLruCache$Editor r2 = r0.currentEditor     // Catch:{ all -> 0x0093 }
            if (r2 == 0) goto L_0x001a
            goto L_0x0091
        L_0x001a:
            int r2 = r7.valueCount     // Catch:{ all -> 0x0093 }
            if (r1 >= r2) goto L_0x005c
            java.io.File r2 = r0.getCleanFile(r1)     // Catch:{ all -> 0x0093 }
            boolean r3 = r2.exists()     // Catch:{ all -> 0x0093 }
            if (r3 == 0) goto L_0x0046
            boolean r3 = r2.delete()     // Catch:{ all -> 0x0093 }
            if (r3 == 0) goto L_0x002f
            goto L_0x0046
        L_0x002f:
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r0.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = "failed to delete "
            r0.append(r1)     // Catch:{ all -> 0x0093 }
            r0.append(r2)     // Catch:{ all -> 0x0093 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0093 }
            r8.<init>(r0)     // Catch:{ all -> 0x0093 }
            throw r8     // Catch:{ all -> 0x0093 }
        L_0x0046:
            long r2 = r7.size     // Catch:{ all -> 0x0093 }
            long[] r4 = r0.lengths     // Catch:{ all -> 0x0093 }
            r5 = r4[r1]     // Catch:{ all -> 0x0093 }
            long r2 = r2 - r5
            r7.size = r2     // Catch:{ all -> 0x0093 }
            long[] r2 = r0.lengths     // Catch:{ all -> 0x0093 }
            r3 = 0
            r2[r1] = r3     // Catch:{ all -> 0x0093 }
            int r1 = r1 + 1
            goto L_0x001a
        L_0x005c:
            int r0 = r7.redundantOpCount     // Catch:{ all -> 0x0093 }
            r1 = 1
            int r0 = r0 + r1
            r7.redundantOpCount = r0     // Catch:{ all -> 0x0093 }
            java.io.Writer r0 = r7.journalWriter     // Catch:{ all -> 0x0093 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x0093 }
            r2.<init>()     // Catch:{ all -> 0x0093 }
            java.lang.String r3 = "REMOVE "
            r2.append(r3)     // Catch:{ all -> 0x0093 }
            r2.append(r8)     // Catch:{ all -> 0x0093 }
            r3 = 10
            r2.append(r3)     // Catch:{ all -> 0x0093 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x0093 }
            r0.append(r2)     // Catch:{ all -> 0x0093 }
            java.util.LinkedHashMap<java.lang.String, com.uacf.core.caching.disk.DiskLruCache$Entry> r0 = r7.lruEntries     // Catch:{ all -> 0x0093 }
            r0.remove(r8)     // Catch:{ all -> 0x0093 }
            boolean r8 = r7.journalRebuildRequired()     // Catch:{ all -> 0x0093 }
            if (r8 == 0) goto L_0x008f
            java.util.concurrent.ExecutorService r8 = r7.executorService     // Catch:{ all -> 0x0093 }
            java.util.concurrent.Callable<java.lang.Void> r0 = r7.cleanupCallable     // Catch:{ all -> 0x0093 }
            r8.submit(r0)     // Catch:{ all -> 0x0093 }
        L_0x008f:
            monitor-exit(r7)
            return r1
        L_0x0091:
            monitor-exit(r7)
            return r1
        L_0x0093:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uacf.core.caching.disk.DiskLruCache.remove(java.lang.String):boolean");
    }

    private void checkNotClosed() {
        if (this.journalWriter == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void flush() throws IOException {
        checkNotClosed();
        trimToSize();
        this.journalWriter.flush();
    }

    public synchronized void close() throws IOException {
        if (this.journalWriter != null) {
            Iterator it = new ArrayList(this.lruEntries.values()).iterator();
            while (it.hasNext()) {
                Entry entry = (Entry) it.next();
                if (entry.currentEditor != null) {
                    entry.currentEditor.abort();
                }
            }
            trimToSize();
            this.journalWriter.close();
            this.journalWriter = null;
        }
    }

    /* access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            remove((String) ((java.util.Map.Entry) this.lruEntries.entrySet().iterator().next()).getKey());
        }
    }

    public void delete() throws IOException {
        close();
        deleteContents(this.directory);
    }

    private void deleteContents(File file) throws IOException {
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            int i = 0;
            while (i < length) {
                File file2 = listFiles[i];
                if (file2.isDirectory()) {
                    deleteContents(file2);
                }
                if (file2.delete()) {
                    i++;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("failed to delete file: ");
                    sb.append(file2);
                    throw new IOException(sb.toString());
                }
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("not a directory: ");
        sb2.append(file);
        throw new IllegalArgumentException(sb2.toString());
    }

    private void validateKey(String str) {
        if (str.contains(" ") || str.contains("\n") || str.contains("\r")) {
            StringBuilder sb = new StringBuilder();
            sb.append("keys must not contain spaces or newlines: \"");
            sb.append(str);
            sb.append("\"");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }
}
