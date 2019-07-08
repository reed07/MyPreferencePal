package com.google.android.exoplayer2.upstream.cache;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.cache.Cache.CacheException;
import com.google.android.exoplayer2.util.Assertions;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.TreeSet;

final class CachedContent {
    private static final int VERSION_MAX = Integer.MAX_VALUE;
    private static final int VERSION_METADATA_INTRODUCED = 2;
    private final TreeSet<SimpleCacheSpan> cachedSpans = new TreeSet<>();
    public final int id;
    public final String key;
    private boolean locked;
    private DefaultContentMetadata metadata = DefaultContentMetadata.EMPTY;

    public static CachedContent readFromStream(int i, DataInputStream dataInputStream) throws IOException {
        CachedContent cachedContent = new CachedContent(dataInputStream.readInt(), dataInputStream.readUTF());
        if (i < 2) {
            long readLong = dataInputStream.readLong();
            ContentMetadataMutations contentMetadataMutations = new ContentMetadataMutations();
            ContentMetadataInternal.setContentLength(contentMetadataMutations, readLong);
            cachedContent.applyMetadataMutations(contentMetadataMutations);
        } else {
            cachedContent.metadata = DefaultContentMetadata.readFromStream(dataInputStream);
        }
        return cachedContent;
    }

    public CachedContent(int i, String str) {
        this.id = i;
        this.key = str;
    }

    public void writeToStream(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(this.id);
        dataOutputStream.writeUTF(this.key);
        this.metadata.writeToStream(dataOutputStream);
    }

    public ContentMetadata getMetadata() {
        return this.metadata;
    }

    public boolean applyMetadataMutations(ContentMetadataMutations contentMetadataMutations) {
        DefaultContentMetadata defaultContentMetadata = this.metadata;
        this.metadata = defaultContentMetadata.copyWithMutationsApplied(contentMetadataMutations);
        return !this.metadata.equals(defaultContentMetadata);
    }

    public boolean isLocked() {
        return this.locked;
    }

    public void setLocked(boolean z) {
        this.locked = z;
    }

    public void addSpan(SimpleCacheSpan simpleCacheSpan) {
        this.cachedSpans.add(simpleCacheSpan);
    }

    public TreeSet<SimpleCacheSpan> getSpans() {
        return this.cachedSpans;
    }

    public SimpleCacheSpan getSpan(long j) {
        SimpleCacheSpan simpleCacheSpan;
        SimpleCacheSpan createLookup = SimpleCacheSpan.createLookup(this.key, j);
        SimpleCacheSpan simpleCacheSpan2 = (SimpleCacheSpan) this.cachedSpans.floor(createLookup);
        if (simpleCacheSpan2 != null && simpleCacheSpan2.position + simpleCacheSpan2.length > j) {
            return simpleCacheSpan2;
        }
        SimpleCacheSpan simpleCacheSpan3 = (SimpleCacheSpan) this.cachedSpans.ceiling(createLookup);
        if (simpleCacheSpan3 == null) {
            simpleCacheSpan = SimpleCacheSpan.createOpenHole(this.key, j);
        } else {
            simpleCacheSpan = SimpleCacheSpan.createClosedHole(this.key, j, simpleCacheSpan3.position - j);
        }
        return simpleCacheSpan;
    }

    public long getCachedBytesLength(long j, long j2) {
        SimpleCacheSpan span = getSpan(j);
        if (span.isHoleSpan()) {
            return -Math.min(span.isOpenEnded() ? Long.MAX_VALUE : span.length, j2);
        }
        long j3 = j + j2;
        long j4 = span.position + span.length;
        if (j4 < j3) {
            for (SimpleCacheSpan simpleCacheSpan : this.cachedSpans.tailSet(span, false)) {
                if (simpleCacheSpan.position <= j4) {
                    j4 = Math.max(j4, simpleCacheSpan.position + simpleCacheSpan.length);
                    if (j4 >= j3) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        return Math.min(j4 - j, j2);
    }

    public SimpleCacheSpan touch(SimpleCacheSpan simpleCacheSpan) throws CacheException {
        SimpleCacheSpan copyWithUpdatedLastAccessTime = simpleCacheSpan.copyWithUpdatedLastAccessTime(this.id);
        if (simpleCacheSpan.file.renameTo(copyWithUpdatedLastAccessTime.file)) {
            Assertions.checkState(this.cachedSpans.remove(simpleCacheSpan));
            this.cachedSpans.add(copyWithUpdatedLastAccessTime);
            return copyWithUpdatedLastAccessTime;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Renaming of ");
        sb.append(simpleCacheSpan.file);
        sb.append(" to ");
        sb.append(copyWithUpdatedLastAccessTime.file);
        sb.append(" failed.");
        throw new CacheException(sb.toString());
    }

    public boolean isEmpty() {
        return this.cachedSpans.isEmpty();
    }

    public boolean removeSpan(CacheSpan cacheSpan) {
        if (!this.cachedSpans.remove(cacheSpan)) {
            return false;
        }
        cacheSpan.file.delete();
        return true;
    }

    public int headerHashCode(int i) {
        int hashCode = (this.id * 31) + this.key.hashCode();
        if (i >= 2) {
            return (hashCode * 31) + this.metadata.hashCode();
        }
        long contentLength = ContentMetadataInternal.getContentLength(this.metadata);
        return (hashCode * 31) + ((int) (contentLength ^ (contentLength >>> 32)));
    }

    public int hashCode() {
        return (headerHashCode(Integer.MAX_VALUE) * 31) + this.cachedSpans.hashCode();
    }

    public boolean equals(@Nullable Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CachedContent cachedContent = (CachedContent) obj;
        if (this.id != cachedContent.id || !this.key.equals(cachedContent.key) || !this.cachedSpans.equals(cachedContent.cachedSpans) || !this.metadata.equals(cachedContent.metadata)) {
            z = false;
        }
        return z;
    }
}
