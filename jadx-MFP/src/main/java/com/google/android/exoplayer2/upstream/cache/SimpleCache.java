package com.google.android.exoplayer2.upstream.cache;

import android.os.ConditionVariable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.cache.Cache.CacheException;
import com.google.android.exoplayer2.upstream.cache.Cache.Listener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public final class SimpleCache implements Cache {
    private static final String TAG = "SimpleCache";
    private static boolean cacheFolderLockingDisabled;
    private static final HashSet<File> lockedCacheDirs = new HashSet<>();
    private final File cacheDir;
    /* access modifiers changed from: private */
    public final CacheEvictor evictor;
    private final CachedContentIndex index;
    private final HashMap<String, ArrayList<Listener>> listeners;
    private boolean released;
    private long totalSpace;

    public static synchronized boolean isCacheFolderLocked(File file) {
        boolean contains;
        synchronized (SimpleCache.class) {
            contains = lockedCacheDirs.contains(file.getAbsoluteFile());
        }
        return contains;
    }

    @Deprecated
    public static synchronized void disableCacheFolderLocking() {
        synchronized (SimpleCache.class) {
            cacheFolderLockingDisabled = true;
            lockedCacheDirs.clear();
        }
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor) {
        this(file, cacheEvictor, null, false);
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, byte[] bArr) {
        this(file, cacheEvictor, bArr, bArr != null);
    }

    public SimpleCache(File file, CacheEvictor cacheEvictor, byte[] bArr, boolean z) {
        this(file, cacheEvictor, new CachedContentIndex(file, bArr, z));
    }

    SimpleCache(File file, CacheEvictor cacheEvictor, CachedContentIndex cachedContentIndex) {
        if (lockFolder(file)) {
            this.cacheDir = file;
            this.evictor = cacheEvictor;
            this.index = cachedContentIndex;
            this.listeners = new HashMap<>();
            final ConditionVariable conditionVariable = new ConditionVariable();
            new Thread("SimpleCache.initialize()") {
                public void run() {
                    synchronized (SimpleCache.this) {
                        conditionVariable.open();
                        SimpleCache.this.initialize();
                        SimpleCache.this.evictor.onCacheInitialized();
                    }
                }
            }.start();
            conditionVariable.block();
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Another SimpleCache instance uses the folder: ");
        sb.append(file);
        throw new IllegalStateException(sb.toString());
    }

    public synchronized void release() {
        if (!this.released) {
            this.listeners.clear();
            removeStaleSpans();
            try {
                this.index.store();
                unlockFolder(this.cacheDir);
            } catch (CacheException e) {
                try {
                    Log.e(TAG, "Storing index file failed", e);
                    unlockFolder(this.cacheDir);
                } catch (Throwable th) {
                    unlockFolder(this.cacheDir);
                    this.released = true;
                    throw th;
                }
            }
            this.released = true;
            return;
        }
        return;
    }

    public synchronized NavigableSet<CacheSpan> addListener(String str, Listener listener) {
        Assertions.checkState(!this.released);
        ArrayList arrayList = (ArrayList) this.listeners.get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.listeners.put(str, arrayList);
        }
        arrayList.add(listener);
        return getCachedSpans(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0020, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeListener(java.lang.String r2, com.google.android.exoplayer2.upstream.cache.Cache.Listener r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.released     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            java.util.HashMap<java.lang.String, java.util.ArrayList<com.google.android.exoplayer2.upstream.cache.Cache$Listener>> r0 = r1.listeners     // Catch:{ all -> 0x0021 }
            java.lang.Object r0 = r0.get(r2)     // Catch:{ all -> 0x0021 }
            java.util.ArrayList r0 = (java.util.ArrayList) r0     // Catch:{ all -> 0x0021 }
            if (r0 == 0) goto L_0x001f
            r0.remove(r3)     // Catch:{ all -> 0x0021 }
            boolean r3 = r0.isEmpty()     // Catch:{ all -> 0x0021 }
            if (r3 == 0) goto L_0x001f
            java.util.HashMap<java.lang.String, java.util.ArrayList<com.google.android.exoplayer2.upstream.cache.Cache$Listener>> r3 = r1.listeners     // Catch:{ all -> 0x0021 }
            r3.remove(r2)     // Catch:{ all -> 0x0021 }
        L_0x001f:
            monitor-exit(r1)
            return
        L_0x0021:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.SimpleCache.removeListener(java.lang.String, com.google.android.exoplayer2.upstream.cache.Cache$Listener):void");
    }

    @NonNull
    public synchronized NavigableSet<CacheSpan> getCachedSpans(String str) {
        TreeSet treeSet;
        Assertions.checkState(!this.released);
        CachedContent cachedContent = this.index.get(str);
        if (cachedContent != null) {
            if (!cachedContent.isEmpty()) {
                treeSet = new TreeSet(cachedContent.getSpans());
            }
        }
        treeSet = new TreeSet();
        return treeSet;
    }

    public synchronized Set<String> getKeys() {
        Assertions.checkState(!this.released);
        return new HashSet(this.index.getKeys());
    }

    public synchronized long getCacheSpace() {
        Assertions.checkState(!this.released);
        return this.totalSpace;
    }

    public synchronized SimpleCacheSpan startReadWrite(String str, long j) throws InterruptedException, CacheException {
        SimpleCacheSpan startReadWriteNonBlocking;
        while (true) {
            startReadWriteNonBlocking = startReadWriteNonBlocking(str, j);
            if (startReadWriteNonBlocking == null) {
                wait();
            }
        }
        return startReadWriteNonBlocking;
    }

    @Nullable
    public synchronized SimpleCacheSpan startReadWriteNonBlocking(String str, long j) throws CacheException {
        Assertions.checkState(!this.released);
        SimpleCacheSpan span = getSpan(str, j);
        if (span.isCached) {
            try {
                SimpleCacheSpan simpleCacheSpan = this.index.get(str).touch(span);
                notifySpanTouched(span, simpleCacheSpan);
                return simpleCacheSpan;
            } catch (CacheException unused) {
                return span;
            }
        } else {
            CachedContent orAdd = this.index.getOrAdd(str);
            if (orAdd.isLocked()) {
                return null;
            }
            orAdd.setLocked(true);
            return span;
        }
    }

    public synchronized File startFile(String str, long j, long j2) throws CacheException {
        CachedContent cachedContent;
        Assertions.checkState(!this.released);
        cachedContent = this.index.get(str);
        Assertions.checkNotNull(cachedContent);
        Assertions.checkState(cachedContent.isLocked());
        if (!this.cacheDir.exists()) {
            this.cacheDir.mkdirs();
            removeStaleSpans();
        }
        this.evictor.onStartFile(this, str, j, j2);
        return SimpleCacheSpan.getCacheFile(this.cacheDir, cachedContent.id, j, System.currentTimeMillis());
    }

    public synchronized void commitFile(File file) throws CacheException {
        boolean z = true;
        Assertions.checkState(!this.released);
        SimpleCacheSpan createCacheEntry = SimpleCacheSpan.createCacheEntry(file, this.index);
        Assertions.checkState(createCacheEntry != null);
        CachedContent cachedContent = this.index.get(createCacheEntry.key);
        Assertions.checkNotNull(cachedContent);
        Assertions.checkState(cachedContent.isLocked());
        if (file.exists()) {
            if (file.length() == 0) {
                file.delete();
                return;
            }
            long contentLength = ContentMetadataInternal.getContentLength(cachedContent.getMetadata());
            if (contentLength != -1) {
                if (createCacheEntry.position + createCacheEntry.length > contentLength) {
                    z = false;
                }
                Assertions.checkState(z);
            }
            addSpan(createCacheEntry);
            this.index.store();
            notifyAll();
        }
    }

    public synchronized void releaseHoleSpan(CacheSpan cacheSpan) {
        Assertions.checkState(!this.released);
        CachedContent cachedContent = this.index.get(cacheSpan.key);
        Assertions.checkNotNull(cachedContent);
        Assertions.checkState(cachedContent.isLocked());
        cachedContent.setLocked(false);
        this.index.maybeRemove(cachedContent.key);
        notifyAll();
    }

    public synchronized void removeSpan(CacheSpan cacheSpan) {
        Assertions.checkState(!this.released);
        removeSpanInternal(cacheSpan);
    }

    public synchronized boolean isCached(String str, long j, long j2) {
        boolean z;
        z = true;
        Assertions.checkState(!this.released);
        CachedContent cachedContent = this.index.get(str);
        if (cachedContent == null || cachedContent.getCachedBytesLength(j, j2) < j2) {
            z = false;
        }
        return z;
    }

    public synchronized long getCachedLength(String str, long j, long j2) {
        CachedContent cachedContent;
        Assertions.checkState(!this.released);
        cachedContent = this.index.get(str);
        return cachedContent != null ? cachedContent.getCachedBytesLength(j, j2) : -j2;
    }

    public synchronized void setContentLength(String str, long j) throws CacheException {
        ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
        ContentMetadataInternal.setContentLength(contentMetadataMutations, j);
        applyContentMetadataMutations(str, contentMetadataMutations);
    }

    public synchronized long getContentLength(String str) {
        return ContentMetadataInternal.getContentLength(getContentMetadata(str));
    }

    public synchronized void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) throws CacheException {
        Assertions.checkState(!this.released);
        this.index.applyContentMetadataMutations(str, contentMetadataMutations);
        this.index.store();
    }

    public synchronized ContentMetadata getContentMetadata(String str) {
        Assertions.checkState(!this.released);
        return this.index.getContentMetadata(str);
    }

    private SimpleCacheSpan getSpan(String str, long j) throws CacheException {
        SimpleCacheSpan span;
        CachedContent cachedContent = this.index.get(str);
        if (cachedContent == null) {
            return SimpleCacheSpan.createOpenHole(str, j);
        }
        while (true) {
            span = cachedContent.getSpan(j);
            if (!span.isCached || span.file.exists()) {
                return span;
            }
            removeStaleSpans();
        }
        return span;
    }

    /* access modifiers changed from: private */
    public void initialize() {
        if (!this.cacheDir.exists()) {
            this.cacheDir.mkdirs();
            return;
        }
        this.index.load();
        File[] listFiles = this.cacheDir.listFiles();
        if (listFiles != null) {
            int length = listFiles.length;
            for (int i = 0; i < length; i++) {
                File file = listFiles[i];
                if (!file.getName().equals(CachedContentIndex.FILE_NAME)) {
                    SimpleCacheSpan createCacheEntry = file.length() > 0 ? SimpleCacheSpan.createCacheEntry(file, this.index) : null;
                    if (createCacheEntry != null) {
                        addSpan(createCacheEntry);
                    } else {
                        file.delete();
                    }
                }
            }
            this.index.removeEmpty();
            try {
                this.index.store();
            } catch (CacheException e) {
                Log.e(TAG, "Storing index file failed", e);
            }
        }
    }

    private void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.index.getOrAdd(simpleCacheSpan.key).addSpan(simpleCacheSpan);
        this.totalSpace += simpleCacheSpan.length;
        notifySpanAdded(simpleCacheSpan);
    }

    private void removeSpanInternal(CacheSpan cacheSpan) {
        CachedContent cachedContent = this.index.get(cacheSpan.key);
        if (cachedContent != null && cachedContent.removeSpan(cacheSpan)) {
            this.totalSpace -= cacheSpan.length;
            this.index.maybeRemove(cachedContent.key);
            notifySpanRemoved(cacheSpan);
        }
    }

    private void removeStaleSpans() {
        ArrayList arrayList = new ArrayList();
        for (CachedContent spans : this.index.getAll()) {
            Iterator it = spans.getSpans().iterator();
            while (it.hasNext()) {
                CacheSpan cacheSpan = (CacheSpan) it.next();
                if (!cacheSpan.file.exists()) {
                    arrayList.add(cacheSpan);
                }
            }
        }
        for (int i = 0; i < arrayList.size(); i++) {
            removeSpanInternal((CacheSpan) arrayList.get(i));
        }
    }

    private void notifySpanRemoved(CacheSpan cacheSpan) {
        ArrayList arrayList = (ArrayList) this.listeners.get(cacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Listener) arrayList.get(size)).onSpanRemoved(this, cacheSpan);
            }
        }
        this.evictor.onSpanRemoved(this, cacheSpan);
    }

    private void notifySpanAdded(SimpleCacheSpan simpleCacheSpan) {
        ArrayList arrayList = (ArrayList) this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Listener) arrayList.get(size)).onSpanAdded(this, simpleCacheSpan);
            }
        }
        this.evictor.onSpanAdded(this, simpleCacheSpan);
    }

    private void notifySpanTouched(SimpleCacheSpan simpleCacheSpan, CacheSpan cacheSpan) {
        ArrayList arrayList = (ArrayList) this.listeners.get(simpleCacheSpan.key);
        if (arrayList != null) {
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                ((Listener) arrayList.get(size)).onSpanTouched(this, simpleCacheSpan, cacheSpan);
            }
        }
        this.evictor.onSpanTouched(this, simpleCacheSpan, cacheSpan);
    }

    private static synchronized boolean lockFolder(File file) {
        synchronized (SimpleCache.class) {
            if (cacheFolderLockingDisabled) {
                return true;
            }
            boolean add = lockedCacheDirs.add(file.getAbsoluteFile());
            return add;
        }
    }

    private static synchronized void unlockFolder(File file) {
        synchronized (SimpleCache.class) {
            if (!cacheFolderLockingDisabled) {
                lockedCacheDirs.remove(file.getAbsoluteFile());
            }
        }
    }
}
