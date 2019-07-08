package com.squareup.okhttp.internal;

import com.facebook.appevents.AppEventsConstants;
import com.squareup.okhttp.internal.io.FileSystem;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import okio.Buffer;
import okio.BufferedSink;
import okio.Okio;
import okio.Sink;
import okio.Source;
import okio.Timeout;

public final class DiskLruCache implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
    /* access modifiers changed from: private */
    public static final Sink NULL_SINK = new Sink() {
        public void close() throws IOException {
        }

        public void flush() throws IOException {
        }

        public void write(Buffer buffer, long j) throws IOException {
            buffer.skip(j);
        }

        public Timeout timeout() {
            return Timeout.NONE;
        }
    };
    private final int appVersion;
    private final Runnable cleanupRunnable = new Runnable() {
        public void run() {
            synchronized (DiskLruCache.this) {
                if (!(!DiskLruCache.this.initialized) && !DiskLruCache.this.closed) {
                    try {
                        DiskLruCache.this.trimToSize();
                        if (DiskLruCache.this.journalRebuildRequired()) {
                            DiskLruCache.this.rebuildJournal();
                            DiskLruCache.this.redundantOpCount = 0;
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public boolean closed;
    /* access modifiers changed from: private */
    public final File directory;
    private final Executor executor;
    /* access modifiers changed from: private */
    public final FileSystem fileSystem;
    /* access modifiers changed from: private */
    public boolean hasJournalErrors;
    /* access modifiers changed from: private */
    public boolean initialized;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap<>(0, 0.75f, true);
    private long maxSize;
    private long nextSequenceNumber = 0;
    /* access modifiers changed from: private */
    public int redundantOpCount;
    private long size = 0;
    /* access modifiers changed from: private */
    public final int valueCount;

    public final class Editor {
        private boolean committed;
        /* access modifiers changed from: private */
        public final Entry entry;
        /* access modifiers changed from: private */
        public boolean hasErrors;
        /* access modifiers changed from: private */
        public final boolean[] written;

        private Editor(Entry entry2) {
            this.entry = entry2;
            this.written = entry2.readable ? null : new boolean[DiskLruCache.this.valueCount];
        }

        public Sink newSink(int i) throws IOException {
            AnonymousClass1 r1;
            synchronized (DiskLruCache.this) {
                if (this.entry.currentEditor == this) {
                    if (!this.entry.readable) {
                        this.written[i] = true;
                    }
                    try {
                        r1 = new FaultHidingSink(DiskLruCache.this.fileSystem.sink(this.entry.dirtyFiles[i])) {
                            /* access modifiers changed from: protected */
                            public void onException(IOException iOException) {
                                synchronized (DiskLruCache.this) {
                                    Editor.this.hasErrors = true;
                                }
                            }
                        };
                    } catch (FileNotFoundException unused) {
                        return DiskLruCache.NULL_SINK;
                    }
                } else {
                    throw new IllegalStateException();
                }
            }
            return r1;
        }

        public void commit() throws IOException {
            synchronized (DiskLruCache.this) {
                if (this.hasErrors) {
                    DiskLruCache.this.completeEdit(this, false);
                    DiskLruCache.this.removeEntry(this.entry);
                } else {
                    DiskLruCache.this.completeEdit(this, true);
                }
                this.committed = true;
            }
        }

        public void abort() throws IOException {
            synchronized (DiskLruCache.this) {
                DiskLruCache.this.completeEdit(this, false);
            }
        }
    }

    private final class Entry {
        /* access modifiers changed from: private */
        public final File[] cleanFiles;
        /* access modifiers changed from: private */
        public Editor currentEditor;
        /* access modifiers changed from: private */
        public final File[] dirtyFiles;
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
            this.cleanFiles = new File[DiskLruCache.this.valueCount];
            this.dirtyFiles = new File[DiskLruCache.this.valueCount];
            StringBuilder sb = new StringBuilder(str);
            sb.append('.');
            int length = sb.length();
            for (int i = 0; i < DiskLruCache.this.valueCount; i++) {
                sb.append(i);
                this.cleanFiles[i] = new File(DiskLruCache.this.directory, sb.toString());
                sb.append(".tmp");
                this.dirtyFiles[i] = new File(DiskLruCache.this.directory, sb.toString());
                sb.setLength(length);
            }
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

        /* access modifiers changed from: 0000 */
        public void writeLengths(BufferedSink bufferedSink) throws IOException {
            for (long writeDecimalLong : this.lengths) {
                bufferedSink.writeByte(32).writeDecimalLong(writeDecimalLong);
            }
        }

        private IOException invalidLengths(String[] strArr) throws IOException {
            StringBuilder sb = new StringBuilder();
            sb.append("unexpected journal line: ");
            sb.append(Arrays.toString(strArr));
            throw new IOException(sb.toString());
        }

        /* access modifiers changed from: 0000 */
        public Snapshot snapshot() {
            if (Thread.holdsLock(DiskLruCache.this)) {
                Source[] sourceArr = new Source[DiskLruCache.this.valueCount];
                long[] jArr = (long[]) this.lengths.clone();
                int i = 0;
                int i2 = 0;
                while (i2 < DiskLruCache.this.valueCount) {
                    try {
                        sourceArr[i2] = DiskLruCache.this.fileSystem.source(this.cleanFiles[i2]);
                        i2++;
                    } catch (FileNotFoundException unused) {
                        while (i < DiskLruCache.this.valueCount && sourceArr[i] != null) {
                            Util.closeQuietly((Closeable) sourceArr[i]);
                            i++;
                        }
                        return null;
                    }
                }
                Snapshot snapshot = new Snapshot(this.key, this.sequenceNumber, sourceArr, jArr);
                return snapshot;
            }
            throw new AssertionError();
        }
    }

    public final class Snapshot implements Closeable {
        private final String key;
        private final long[] lengths;
        private final long sequenceNumber;
        private final Source[] sources;

        private Snapshot(String str, long j, Source[] sourceArr, long[] jArr) {
            this.key = str;
            this.sequenceNumber = j;
            this.sources = sourceArr;
            this.lengths = jArr;
        }

        public Editor edit() throws IOException {
            return DiskLruCache.this.edit(this.key, this.sequenceNumber);
        }

        public Source getSource(int i) {
            return this.sources[i];
        }

        public void close() {
            for (Source closeQuietly : this.sources) {
                Util.closeQuietly((Closeable) closeQuietly);
            }
        }
    }

    DiskLruCache(FileSystem fileSystem2, File file, int i, int i2, long j, Executor executor2) {
        this.fileSystem = fileSystem2;
        this.directory = file;
        this.appVersion = i;
        this.journalFile = new File(file, "journal");
        this.journalFileTmp = new File(file, "journal.tmp");
        this.journalFileBackup = new File(file, "journal.bkp");
        this.valueCount = i2;
        this.maxSize = j;
        this.executor = executor2;
    }

    public synchronized void initialize() throws IOException {
        if (!this.initialized) {
            if (this.fileSystem.exists(this.journalFileBackup)) {
                if (this.fileSystem.exists(this.journalFile)) {
                    this.fileSystem.delete(this.journalFileBackup);
                } else {
                    this.fileSystem.rename(this.journalFileBackup, this.journalFile);
                }
            }
            if (this.fileSystem.exists(this.journalFile)) {
                try {
                    readJournal();
                    processJournal();
                    this.initialized = true;
                    return;
                } catch (IOException e) {
                    Platform platform = Platform.get();
                    StringBuilder sb = new StringBuilder();
                    sb.append("DiskLruCache ");
                    sb.append(this.directory);
                    sb.append(" is corrupt: ");
                    sb.append(e.getMessage());
                    sb.append(", removing");
                    platform.logW(sb.toString());
                    delete();
                    this.closed = false;
                }
            }
            rebuildJournal();
            this.initialized = true;
        }
    }

    public static DiskLruCache create(FileSystem fileSystem2, File file, int i, int i2, long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i2 > 0) {
            ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true));
            DiskLruCache diskLruCache = new DiskLruCache(fileSystem2, file, i, i2, j, threadPoolExecutor);
            return diskLruCache;
        } else {
            throw new IllegalArgumentException("valueCount <= 0");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:16|17|(1:19)(1:20)|21|22) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r8.redundantOpCount = r1 - r8.lruEntries.size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        if (r0.exhausted() == false) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        rebuildJournal();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006e, code lost:
        r8.journalWriter = newJournalWriter();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0077, code lost:
        return;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x005b */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0078=Splitter:B:23:0x0078, B:16:0x005b=Splitter:B:16:0x005b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void readJournal() throws java.io.IOException {
        /*
            r8 = this;
            com.squareup.okhttp.internal.io.FileSystem r0 = r8.fileSystem
            java.io.File r1 = r8.journalFile
            okio.Source r0 = r0.source(r1)
            okio.BufferedSource r0 = okio.Okio.buffer(r0)
            java.lang.String r1 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r2 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r3 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r4 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r5 = r0.readUtf8LineStrict()     // Catch:{ all -> 0x00ac }
            java.lang.String r6 = "libcore.io.DiskLruCache"
            boolean r6 = r6.equals(r1)     // Catch:{ all -> 0x00ac }
            if (r6 == 0) goto L_0x0078
            java.lang.String r6 = "1"
            boolean r6 = r6.equals(r2)     // Catch:{ all -> 0x00ac }
            if (r6 == 0) goto L_0x0078
            int r6 = r8.appVersion     // Catch:{ all -> 0x00ac }
            java.lang.String r6 = java.lang.Integer.toString(r6)     // Catch:{ all -> 0x00ac }
            boolean r3 = r6.equals(r3)     // Catch:{ all -> 0x00ac }
            if (r3 == 0) goto L_0x0078
            int r3 = r8.valueCount     // Catch:{ all -> 0x00ac }
            java.lang.String r3 = java.lang.Integer.toString(r3)     // Catch:{ all -> 0x00ac }
            boolean r3 = r3.equals(r4)     // Catch:{ all -> 0x00ac }
            if (r3 == 0) goto L_0x0078
            java.lang.String r3 = ""
            boolean r3 = r3.equals(r5)     // Catch:{ all -> 0x00ac }
            if (r3 == 0) goto L_0x0078
            r1 = 0
        L_0x0051:
            java.lang.String r2 = r0.readUtf8LineStrict()     // Catch:{ EOFException -> 0x005b }
            r8.readJournalLine(r2)     // Catch:{ EOFException -> 0x005b }
            int r1 = r1 + 1
            goto L_0x0051
        L_0x005b:
            java.util.LinkedHashMap<java.lang.String, com.squareup.okhttp.internal.DiskLruCache$Entry> r2 = r8.lruEntries     // Catch:{ all -> 0x00ac }
            int r2 = r2.size()     // Catch:{ all -> 0x00ac }
            int r1 = r1 - r2
            r8.redundantOpCount = r1     // Catch:{ all -> 0x00ac }
            boolean r1 = r0.exhausted()     // Catch:{ all -> 0x00ac }
            if (r1 != 0) goto L_0x006e
            r8.rebuildJournal()     // Catch:{ all -> 0x00ac }
            goto L_0x0074
        L_0x006e:
            okio.BufferedSink r1 = r8.newJournalWriter()     // Catch:{ all -> 0x00ac }
            r8.journalWriter = r1     // Catch:{ all -> 0x00ac }
        L_0x0074:
            com.squareup.okhttp.internal.Util.closeQuietly(r0)
            return
        L_0x0078:
            java.io.IOException r3 = new java.io.IOException     // Catch:{ all -> 0x00ac }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ac }
            r6.<init>()     // Catch:{ all -> 0x00ac }
            java.lang.String r7 = "unexpected journal header: ["
            r6.append(r7)     // Catch:{ all -> 0x00ac }
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            r6.append(r2)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            r6.append(r4)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = ", "
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            r6.append(r5)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = "]"
            r6.append(r1)     // Catch:{ all -> 0x00ac }
            java.lang.String r1 = r6.toString()     // Catch:{ all -> 0x00ac }
            r3.<init>(r1)     // Catch:{ all -> 0x00ac }
            throw r3     // Catch:{ all -> 0x00ac }
        L_0x00ac:
            r1 = move-exception
            com.squareup.okhttp.internal.Util.closeQuietly(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.DiskLruCache.readJournal():void");
    }

    private BufferedSink newJournalWriter() throws FileNotFoundException {
        return Okio.buffer((Sink) new FaultHidingSink(this.fileSystem.appendingSink(this.journalFile)) {
            static final /* synthetic */ boolean $assertionsDisabled = false;

            static {
                Class<DiskLruCache> cls = DiskLruCache.class;
            }

            /* access modifiers changed from: protected */
            public void onException(IOException iOException) {
                DiskLruCache.this.hasJournalErrors = true;
            }
        });
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
        this.fileSystem.delete(this.journalFileTmp);
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
                    this.fileSystem.delete(entry.cleanFiles[i]);
                    this.fileSystem.delete(entry.dirtyFiles[i]);
                    i++;
                }
                it.remove();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    public synchronized void rebuildJournal() throws IOException {
        if (this.journalWriter != null) {
            this.journalWriter.close();
        }
        BufferedSink buffer = Okio.buffer(this.fileSystem.sink(this.journalFileTmp));
        try {
            buffer.writeUtf8("libcore.io.DiskLruCache").writeByte(10);
            buffer.writeUtf8(AppEventsConstants.EVENT_PARAM_VALUE_YES).writeByte(10);
            buffer.writeDecimalLong((long) this.appVersion).writeByte(10);
            buffer.writeDecimalLong((long) this.valueCount).writeByte(10);
            buffer.writeByte(10);
            for (Entry entry : this.lruEntries.values()) {
                if (entry.currentEditor != null) {
                    buffer.writeUtf8("DIRTY").writeByte(32);
                    buffer.writeUtf8(entry.key);
                    buffer.writeByte(10);
                } else {
                    buffer.writeUtf8("CLEAN").writeByte(32);
                    buffer.writeUtf8(entry.key);
                    entry.writeLengths(buffer);
                    buffer.writeByte(10);
                }
            }
            buffer.close();
            if (this.fileSystem.exists(this.journalFile)) {
                this.fileSystem.rename(this.journalFile, this.journalFileBackup);
            }
            this.fileSystem.rename(this.journalFileTmp, this.journalFile);
            this.fileSystem.delete(this.journalFileBackup);
            this.journalWriter = newJournalWriter();
            this.hasJournalErrors = false;
        } catch (Throwable th) {
            buffer.close();
            throw th;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004f, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.squareup.okhttp.internal.DiskLruCache.Snapshot get(java.lang.String r4) throws java.io.IOException {
        /*
            r3 = this;
            monitor-enter(r3)
            r3.initialize()     // Catch:{ all -> 0x0052 }
            r3.checkNotClosed()     // Catch:{ all -> 0x0052 }
            r3.validateKey(r4)     // Catch:{ all -> 0x0052 }
            java.util.LinkedHashMap<java.lang.String, com.squareup.okhttp.internal.DiskLruCache$Entry> r0 = r3.lruEntries     // Catch:{ all -> 0x0052 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x0052 }
            com.squareup.okhttp.internal.DiskLruCache$Entry r0 = (com.squareup.okhttp.internal.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0052 }
            r1 = 0
            if (r0 == 0) goto L_0x0050
            boolean r2 = r0.readable     // Catch:{ all -> 0x0052 }
            if (r2 != 0) goto L_0x001c
            goto L_0x0050
        L_0x001c:
            com.squareup.okhttp.internal.DiskLruCache$Snapshot r0 = r0.snapshot()     // Catch:{ all -> 0x0052 }
            if (r0 != 0) goto L_0x0024
            monitor-exit(r3)
            return r1
        L_0x0024:
            int r1 = r3.redundantOpCount     // Catch:{ all -> 0x0052 }
            int r1 = r1 + 1
            r3.redundantOpCount = r1     // Catch:{ all -> 0x0052 }
            okio.BufferedSink r1 = r3.journalWriter     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = "READ"
            okio.BufferedSink r1 = r1.writeUtf8(r2)     // Catch:{ all -> 0x0052 }
            r2 = 32
            okio.BufferedSink r1 = r1.writeByte(r2)     // Catch:{ all -> 0x0052 }
            okio.BufferedSink r4 = r1.writeUtf8(r4)     // Catch:{ all -> 0x0052 }
            r1 = 10
            r4.writeByte(r1)     // Catch:{ all -> 0x0052 }
            boolean r4 = r3.journalRebuildRequired()     // Catch:{ all -> 0x0052 }
            if (r4 == 0) goto L_0x004e
            java.util.concurrent.Executor r4 = r3.executor     // Catch:{ all -> 0x0052 }
            java.lang.Runnable r1 = r3.cleanupRunnable     // Catch:{ all -> 0x0052 }
            r4.execute(r1)     // Catch:{ all -> 0x0052 }
        L_0x004e:
            monitor-exit(r3)
            return r0
        L_0x0050:
            monitor-exit(r3)
            return r1
        L_0x0052:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.DiskLruCache.get(java.lang.String):com.squareup.okhttp.internal.DiskLruCache$Snapshot");
    }

    public Editor edit(String str) throws IOException {
        return edit(str, -1);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.squareup.okhttp.internal.DiskLruCache.Editor edit(java.lang.String r6, long r7) throws java.io.IOException {
        /*
            r5 = this;
            monitor-enter(r5)
            r5.initialize()     // Catch:{ all -> 0x0067 }
            r5.checkNotClosed()     // Catch:{ all -> 0x0067 }
            r5.validateKey(r6)     // Catch:{ all -> 0x0067 }
            java.util.LinkedHashMap<java.lang.String, com.squareup.okhttp.internal.DiskLruCache$Entry> r0 = r5.lruEntries     // Catch:{ all -> 0x0067 }
            java.lang.Object r0 = r0.get(r6)     // Catch:{ all -> 0x0067 }
            com.squareup.okhttp.internal.DiskLruCache$Entry r0 = (com.squareup.okhttp.internal.DiskLruCache.Entry) r0     // Catch:{ all -> 0x0067 }
            r1 = -1
            r3 = 0
            int r4 = (r7 > r1 ? 1 : (r7 == r1 ? 0 : -1))
            if (r4 == 0) goto L_0x0025
            if (r0 == 0) goto L_0x0023
            long r1 = r0.sequenceNumber     // Catch:{ all -> 0x0067 }
            int r4 = (r1 > r7 ? 1 : (r1 == r7 ? 0 : -1))
            if (r4 == 0) goto L_0x0025
        L_0x0023:
            monitor-exit(r5)
            return r3
        L_0x0025:
            if (r0 == 0) goto L_0x002f
            com.squareup.okhttp.internal.DiskLruCache$Editor r7 = r0.currentEditor     // Catch:{ all -> 0x0067 }
            if (r7 == 0) goto L_0x002f
            monitor-exit(r5)
            return r3
        L_0x002f:
            okio.BufferedSink r7 = r5.journalWriter     // Catch:{ all -> 0x0067 }
            java.lang.String r8 = "DIRTY"
            okio.BufferedSink r7 = r7.writeUtf8(r8)     // Catch:{ all -> 0x0067 }
            r8 = 32
            okio.BufferedSink r7 = r7.writeByte(r8)     // Catch:{ all -> 0x0067 }
            okio.BufferedSink r7 = r7.writeUtf8(r6)     // Catch:{ all -> 0x0067 }
            r8 = 10
            r7.writeByte(r8)     // Catch:{ all -> 0x0067 }
            okio.BufferedSink r7 = r5.journalWriter     // Catch:{ all -> 0x0067 }
            r7.flush()     // Catch:{ all -> 0x0067 }
            boolean r7 = r5.hasJournalErrors     // Catch:{ all -> 0x0067 }
            if (r7 == 0) goto L_0x0051
            monitor-exit(r5)
            return r3
        L_0x0051:
            if (r0 != 0) goto L_0x005d
            com.squareup.okhttp.internal.DiskLruCache$Entry r0 = new com.squareup.okhttp.internal.DiskLruCache$Entry     // Catch:{ all -> 0x0067 }
            r0.<init>(r6)     // Catch:{ all -> 0x0067 }
            java.util.LinkedHashMap<java.lang.String, com.squareup.okhttp.internal.DiskLruCache$Entry> r7 = r5.lruEntries     // Catch:{ all -> 0x0067 }
            r7.put(r6, r0)     // Catch:{ all -> 0x0067 }
        L_0x005d:
            com.squareup.okhttp.internal.DiskLruCache$Editor r6 = new com.squareup.okhttp.internal.DiskLruCache$Editor     // Catch:{ all -> 0x0067 }
            r6.<init>(r0)     // Catch:{ all -> 0x0067 }
            r0.currentEditor = r6     // Catch:{ all -> 0x0067 }
            monitor-exit(r5)
            return r6
        L_0x0067:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.DiskLruCache.edit(java.lang.String, long):com.squareup.okhttp.internal.DiskLruCache$Editor");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0111, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void completeEdit(com.squareup.okhttp.internal.DiskLruCache.Editor r10, boolean r11) throws java.io.IOException {
        /*
            r9 = this;
            monitor-enter(r9)
            com.squareup.okhttp.internal.DiskLruCache$Entry r0 = r10.entry     // Catch:{ all -> 0x0118 }
            com.squareup.okhttp.internal.DiskLruCache$Editor r1 = r0.currentEditor     // Catch:{ all -> 0x0118 }
            if (r1 != r10) goto L_0x0112
            r1 = 0
            if (r11 == 0) goto L_0x0051
            boolean r2 = r0.readable     // Catch:{ all -> 0x0118 }
            if (r2 != 0) goto L_0x0051
            r2 = 0
        L_0x0015:
            int r3 = r9.valueCount     // Catch:{ all -> 0x0118 }
            if (r2 >= r3) goto L_0x0051
            boolean[] r3 = r10.written     // Catch:{ all -> 0x0118 }
            boolean r3 = r3[r2]     // Catch:{ all -> 0x0118 }
            if (r3 == 0) goto L_0x0037
            com.squareup.okhttp.internal.io.FileSystem r3 = r9.fileSystem     // Catch:{ all -> 0x0118 }
            java.io.File[] r4 = r0.dirtyFiles     // Catch:{ all -> 0x0118 }
            r4 = r4[r2]     // Catch:{ all -> 0x0118 }
            boolean r3 = r3.exists(r4)     // Catch:{ all -> 0x0118 }
            if (r3 != 0) goto L_0x0034
            r10.abort()     // Catch:{ all -> 0x0118 }
            monitor-exit(r9)
            return
        L_0x0034:
            int r2 = r2 + 1
            goto L_0x0015
        L_0x0037:
            r10.abort()     // Catch:{ all -> 0x0118 }
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0118 }
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0118 }
            r11.<init>()     // Catch:{ all -> 0x0118 }
            java.lang.String r0 = "Newly created entry didn't create value for index "
            r11.append(r0)     // Catch:{ all -> 0x0118 }
            r11.append(r2)     // Catch:{ all -> 0x0118 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0118 }
            r10.<init>(r11)     // Catch:{ all -> 0x0118 }
            throw r10     // Catch:{ all -> 0x0118 }
        L_0x0051:
            int r10 = r9.valueCount     // Catch:{ all -> 0x0118 }
            if (r1 >= r10) goto L_0x0091
            java.io.File[] r10 = r0.dirtyFiles     // Catch:{ all -> 0x0118 }
            r10 = r10[r1]     // Catch:{ all -> 0x0118 }
            if (r11 == 0) goto L_0x0089
            com.squareup.okhttp.internal.io.FileSystem r2 = r9.fileSystem     // Catch:{ all -> 0x0118 }
            boolean r2 = r2.exists(r10)     // Catch:{ all -> 0x0118 }
            if (r2 == 0) goto L_0x008e
            java.io.File[] r2 = r0.cleanFiles     // Catch:{ all -> 0x0118 }
            r2 = r2[r1]     // Catch:{ all -> 0x0118 }
            com.squareup.okhttp.internal.io.FileSystem r3 = r9.fileSystem     // Catch:{ all -> 0x0118 }
            r3.rename(r10, r2)     // Catch:{ all -> 0x0118 }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x0118 }
            r3 = r10[r1]     // Catch:{ all -> 0x0118 }
            com.squareup.okhttp.internal.io.FileSystem r10 = r9.fileSystem     // Catch:{ all -> 0x0118 }
            long r5 = r10.size(r2)     // Catch:{ all -> 0x0118 }
            long[] r10 = r0.lengths     // Catch:{ all -> 0x0118 }
            r10[r1] = r5     // Catch:{ all -> 0x0118 }
            long r7 = r9.size     // Catch:{ all -> 0x0118 }
            long r7 = r7 - r3
            long r7 = r7 + r5
            r9.size = r7     // Catch:{ all -> 0x0118 }
            goto L_0x008e
        L_0x0089:
            com.squareup.okhttp.internal.io.FileSystem r2 = r9.fileSystem     // Catch:{ all -> 0x0118 }
            r2.delete(r10)     // Catch:{ all -> 0x0118 }
        L_0x008e:
            int r1 = r1 + 1
            goto L_0x0051
        L_0x0091:
            int r10 = r9.redundantOpCount     // Catch:{ all -> 0x0118 }
            r1 = 1
            int r10 = r10 + r1
            r9.redundantOpCount = r10     // Catch:{ all -> 0x0118 }
            r10 = 0
            r0.currentEditor = r10     // Catch:{ all -> 0x0118 }
            boolean r10 = r0.readable     // Catch:{ all -> 0x0118 }
            r10 = r10 | r11
            r2 = 10
            r3 = 32
            if (r10 == 0) goto L_0x00d4
            r0.readable = r1     // Catch:{ all -> 0x0118 }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x0118 }
            java.lang.String r1 = "CLEAN"
            okio.BufferedSink r10 = r10.writeUtf8(r1)     // Catch:{ all -> 0x0118 }
            r10.writeByte(r3)     // Catch:{ all -> 0x0118 }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x0118 }
            java.lang.String r1 = r0.key     // Catch:{ all -> 0x0118 }
            r10.writeUtf8(r1)     // Catch:{ all -> 0x0118 }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x0118 }
            r0.writeLengths(r10)     // Catch:{ all -> 0x0118 }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x0118 }
            r10.writeByte(r2)     // Catch:{ all -> 0x0118 }
            if (r11 == 0) goto L_0x00f6
            long r10 = r9.nextSequenceNumber     // Catch:{ all -> 0x0118 }
            r1 = 1
            long r1 = r1 + r10
            r9.nextSequenceNumber = r1     // Catch:{ all -> 0x0118 }
            r0.sequenceNumber = r10     // Catch:{ all -> 0x0118 }
            goto L_0x00f6
        L_0x00d4:
            java.util.LinkedHashMap<java.lang.String, com.squareup.okhttp.internal.DiskLruCache$Entry> r10 = r9.lruEntries     // Catch:{ all -> 0x0118 }
            java.lang.String r11 = r0.key     // Catch:{ all -> 0x0118 }
            r10.remove(r11)     // Catch:{ all -> 0x0118 }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x0118 }
            java.lang.String r11 = "REMOVE"
            okio.BufferedSink r10 = r10.writeUtf8(r11)     // Catch:{ all -> 0x0118 }
            r10.writeByte(r3)     // Catch:{ all -> 0x0118 }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x0118 }
            java.lang.String r11 = r0.key     // Catch:{ all -> 0x0118 }
            r10.writeUtf8(r11)     // Catch:{ all -> 0x0118 }
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x0118 }
            r10.writeByte(r2)     // Catch:{ all -> 0x0118 }
        L_0x00f6:
            okio.BufferedSink r10 = r9.journalWriter     // Catch:{ all -> 0x0118 }
            r10.flush()     // Catch:{ all -> 0x0118 }
            long r10 = r9.size     // Catch:{ all -> 0x0118 }
            long r0 = r9.maxSize     // Catch:{ all -> 0x0118 }
            int r2 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r2 > 0) goto L_0x0109
            boolean r10 = r9.journalRebuildRequired()     // Catch:{ all -> 0x0118 }
            if (r10 == 0) goto L_0x0110
        L_0x0109:
            java.util.concurrent.Executor r10 = r9.executor     // Catch:{ all -> 0x0118 }
            java.lang.Runnable r11 = r9.cleanupRunnable     // Catch:{ all -> 0x0118 }
            r10.execute(r11)     // Catch:{ all -> 0x0118 }
        L_0x0110:
            monitor-exit(r9)
            return
        L_0x0112:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0118 }
            r10.<init>()     // Catch:{ all -> 0x0118 }
            throw r10     // Catch:{ all -> 0x0118 }
        L_0x0118:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.okhttp.internal.DiskLruCache.completeEdit(com.squareup.okhttp.internal.DiskLruCache$Editor, boolean):void");
    }

    /* access modifiers changed from: private */
    public boolean journalRebuildRequired() {
        int i = this.redundantOpCount;
        return i >= 2000 && i >= this.lruEntries.size();
    }

    public synchronized boolean remove(String str) throws IOException {
        initialize();
        checkNotClosed();
        validateKey(str);
        Entry entry = (Entry) this.lruEntries.get(str);
        if (entry == null) {
            return false;
        }
        return removeEntry(entry);
    }

    /* access modifiers changed from: private */
    public boolean removeEntry(Entry entry) throws IOException {
        if (entry.currentEditor != null) {
            entry.currentEditor.hasErrors = true;
        }
        for (int i = 0; i < this.valueCount; i++) {
            this.fileSystem.delete(entry.cleanFiles[i]);
            this.size -= entry.lengths[i];
            entry.lengths[i] = 0;
        }
        this.redundantOpCount++;
        this.journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(entry.key).writeByte(10);
        this.lruEntries.remove(entry.key);
        if (journalRebuildRequired()) {
            this.executor.execute(this.cleanupRunnable);
        }
        return true;
    }

    public synchronized boolean isClosed() {
        return this.closed;
    }

    private synchronized void checkNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public synchronized void close() throws IOException {
        Entry[] entryArr;
        if (this.initialized) {
            if (!this.closed) {
                for (Entry entry : (Entry[]) this.lruEntries.values().toArray(new Entry[this.lruEntries.size()])) {
                    if (entry.currentEditor != null) {
                        entry.currentEditor.abort();
                    }
                }
                trimToSize();
                this.journalWriter.close();
                this.journalWriter = null;
                this.closed = true;
                return;
            }
        }
        this.closed = true;
    }

    /* access modifiers changed from: private */
    public void trimToSize() throws IOException {
        while (this.size > this.maxSize) {
            removeEntry((Entry) this.lruEntries.values().iterator().next());
        }
    }

    public void delete() throws IOException {
        close();
        this.fileSystem.deleteContents(this.directory);
    }

    private void validateKey(String str) {
        if (!LEGAL_KEY_PATTERN.matcher(str).matches()) {
            StringBuilder sb = new StringBuilder();
            sb.append("keys must match regex [a-z0-9_-]{1,120}: \"");
            sb.append(str);
            sb.append("\"");
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
