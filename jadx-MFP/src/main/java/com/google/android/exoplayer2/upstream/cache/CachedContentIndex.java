package com.google.android.exoplayer2.upstream.cache;

import android.util.SparseArray;
import android.util.SparseBooleanArray;
import com.google.android.exoplayer2.upstream.cache.Cache.CacheException;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.AtomicFile;
import com.google.android.exoplayer2.util.ReusableBufferedOutputStream;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

class CachedContentIndex {
    public static final String FILE_NAME = "cached_content_index.exi";
    private static final int FLAG_ENCRYPTED_INDEX = 1;
    private static final int VERSION = 2;
    private final AtomicFile atomicFile;
    private ReusableBufferedOutputStream bufferedOutputStream;
    private boolean changed;
    private final Cipher cipher;
    private final boolean encrypt;
    private final SparseArray<String> idToKey;
    private final HashMap<String, CachedContent> keyToContent;
    private final SparseBooleanArray removedIds;
    private final SecretKeySpec secretKeySpec;

    public CachedContentIndex(File file) {
        this(file, null);
    }

    public CachedContentIndex(File file, byte[] bArr) {
        this(file, bArr, bArr != null);
    }

    public CachedContentIndex(File file, byte[] bArr, boolean z) {
        this.encrypt = z;
        boolean z2 = true;
        if (bArr != null) {
            if (bArr.length != 16) {
                z2 = false;
            }
            Assertions.checkArgument(z2);
            try {
                this.cipher = getCipher();
                this.secretKeySpec = new SecretKeySpec(bArr, "AES");
            } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
                throw new IllegalStateException(e);
            }
        } else {
            Assertions.checkState(!z);
            this.cipher = null;
            this.secretKeySpec = null;
        }
        this.keyToContent = new HashMap<>();
        this.idToKey = new SparseArray<>();
        this.removedIds = new SparseBooleanArray();
        this.atomicFile = new AtomicFile(new File(file, FILE_NAME));
    }

    public void load() {
        Assertions.checkState(!this.changed);
        if (!readFile()) {
            this.atomicFile.delete();
            this.keyToContent.clear();
            this.idToKey.clear();
        }
    }

    public void store() throws CacheException {
        if (this.changed) {
            writeFile();
            this.changed = false;
            int size = this.removedIds.size();
            for (int i = 0; i < size; i++) {
                this.idToKey.remove(this.removedIds.keyAt(i));
            }
            this.removedIds.clear();
        }
    }

    public CachedContent getOrAdd(String str) {
        CachedContent cachedContent = (CachedContent) this.keyToContent.get(str);
        return cachedContent == null ? addNew(str) : cachedContent;
    }

    public CachedContent get(String str) {
        return (CachedContent) this.keyToContent.get(str);
    }

    public Collection<CachedContent> getAll() {
        return this.keyToContent.values();
    }

    public int assignIdForKey(String str) {
        return getOrAdd(str).id;
    }

    public String getKeyForId(int i) {
        return (String) this.idToKey.get(i);
    }

    public void maybeRemove(String str) {
        CachedContent cachedContent = (CachedContent) this.keyToContent.get(str);
        if (cachedContent != null && cachedContent.isEmpty() && !cachedContent.isLocked()) {
            this.keyToContent.remove(str);
            this.changed = true;
            this.idToKey.put(cachedContent.id, null);
            this.removedIds.put(cachedContent.id, true);
        }
    }

    public void removeEmpty() {
        String[] strArr = new String[this.keyToContent.size()];
        this.keyToContent.keySet().toArray(strArr);
        for (String maybeRemove : strArr) {
            maybeRemove(maybeRemove);
        }
    }

    public Set<String> getKeys() {
        return this.keyToContent.keySet();
    }

    public void applyContentMetadataMutations(String str, ContentMetadataMutations contentMetadataMutations) {
        if (getOrAdd(str).applyMetadataMutations(contentMetadataMutations)) {
            this.changed = true;
        }
    }

    public ContentMetadata getContentMetadata(String str) {
        CachedContent cachedContent = get(str);
        return cachedContent != null ? cachedContent.getMetadata() : DefaultContentMetadata.EMPTY;
    }

    /* JADX WARNING: Removed duplicated region for block: B:51:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00a2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean readFile() {
        /*
            r9 = this;
            r0 = 0
            r1 = 0
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x009f, all -> 0x0097 }
            com.google.android.exoplayer2.util.AtomicFile r3 = r9.atomicFile     // Catch:{ IOException -> 0x009f, all -> 0x0097 }
            java.io.InputStream r3 = r3.openRead()     // Catch:{ IOException -> 0x009f, all -> 0x0097 }
            r2.<init>(r3)     // Catch:{ IOException -> 0x009f, all -> 0x0097 }
            java.io.DataInputStream r3 = new java.io.DataInputStream     // Catch:{ IOException -> 0x009f, all -> 0x0097 }
            r3.<init>(r2)     // Catch:{ IOException -> 0x009f, all -> 0x0097 }
            int r1 = r3.readInt()     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            if (r1 < 0) goto L_0x008f
            r4 = 2
            if (r1 <= r4) goto L_0x001d
            goto L_0x008f
        L_0x001d:
            int r5 = r3.readInt()     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r6 = 1
            r5 = r5 & r6
            if (r5 == 0) goto L_0x0057
            javax.crypto.Cipher r5 = r9.cipher     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            if (r5 != 0) goto L_0x002d
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
            return r0
        L_0x002d:
            r5 = 16
            byte[] r5 = new byte[r5]     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r3.readFully(r5)     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            javax.crypto.spec.IvParameterSpec r7 = new javax.crypto.spec.IvParameterSpec     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r7.<init>(r5)     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            javax.crypto.Cipher r5 = r9.cipher     // Catch:{ InvalidKeyException -> 0x0050, InvalidAlgorithmParameterException -> 0x004e }
            javax.crypto.spec.SecretKeySpec r8 = r9.secretKeySpec     // Catch:{ InvalidKeyException -> 0x0050, InvalidAlgorithmParameterException -> 0x004e }
            r5.init(r4, r8, r7)     // Catch:{ InvalidKeyException -> 0x0050, InvalidAlgorithmParameterException -> 0x004e }
            java.io.DataInputStream r4 = new java.io.DataInputStream     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            javax.crypto.CipherInputStream r5 = new javax.crypto.CipherInputStream     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            javax.crypto.Cipher r7 = r9.cipher     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r5.<init>(r2, r7)     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r3 = r4
            goto L_0x005d
        L_0x004e:
            r1 = move-exception
            goto L_0x0051
        L_0x0050:
            r1 = move-exception
        L_0x0051:
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            throw r2     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
        L_0x0057:
            boolean r2 = r9.encrypt     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            if (r2 == 0) goto L_0x005d
            r9.changed = r6     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
        L_0x005d:
            int r2 = r3.readInt()     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r4 = 0
            r5 = 0
        L_0x0063:
            if (r4 >= r2) goto L_0x0074
            com.google.android.exoplayer2.upstream.cache.CachedContent r7 = com.google.android.exoplayer2.upstream.cache.CachedContent.readFromStream(r1, r3)     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r9.add(r7)     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            int r7 = r7.headerHashCode(r1)     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            int r5 = r5 + r7
            int r4 = r4 + 1
            goto L_0x0063
        L_0x0074:
            int r1 = r3.readInt()     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            int r2 = r3.read()     // Catch:{ IOException -> 0x0095, all -> 0x0093 }
            r4 = -1
            if (r2 != r4) goto L_0x0081
            r2 = 1
            goto L_0x0082
        L_0x0081:
            r2 = 0
        L_0x0082:
            if (r1 != r5) goto L_0x008b
            if (r2 != 0) goto L_0x0087
            goto L_0x008b
        L_0x0087:
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
            return r6
        L_0x008b:
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
            return r0
        L_0x008f:
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
            return r0
        L_0x0093:
            r0 = move-exception
            goto L_0x0099
        L_0x0095:
            goto L_0x00a0
        L_0x0097:
            r0 = move-exception
            r3 = r1
        L_0x0099:
            if (r3 == 0) goto L_0x009e
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
        L_0x009e:
            throw r0
        L_0x009f:
            r3 = r1
        L_0x00a0:
            if (r3 == 0) goto L_0x00a5
            com.google.android.exoplayer2.util.Util.closeQuietly(r3)
        L_0x00a5:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.upstream.cache.CachedContentIndex.readFile():boolean");
    }

    private void writeFile() throws CacheException {
        DataOutputStream dataOutputStream;
        Throwable e;
        try {
            OutputStream startWrite = this.atomicFile.startWrite();
            if (this.bufferedOutputStream == null) {
                this.bufferedOutputStream = new ReusableBufferedOutputStream(startWrite);
            } else {
                this.bufferedOutputStream.reset(startWrite);
            }
            dataOutputStream = new DataOutputStream(this.bufferedOutputStream);
            try {
                dataOutputStream.writeInt(2);
                int i = 0;
                dataOutputStream.writeInt(this.encrypt ? 1 : 0);
                if (this.encrypt) {
                    byte[] bArr = new byte[16];
                    new Random().nextBytes(bArr);
                    dataOutputStream.write(bArr);
                    try {
                        this.cipher.init(1, this.secretKeySpec, new IvParameterSpec(bArr));
                        dataOutputStream.flush();
                        dataOutputStream = new DataOutputStream(new CipherOutputStream(this.bufferedOutputStream, this.cipher));
                    } catch (InvalidKeyException e2) {
                        e = e2;
                        throw new IllegalStateException(e);
                    } catch (InvalidAlgorithmParameterException e3) {
                        e = e3;
                        throw new IllegalStateException(e);
                    }
                }
                dataOutputStream.writeInt(this.keyToContent.size());
                for (CachedContent cachedContent : this.keyToContent.values()) {
                    cachedContent.writeToStream(dataOutputStream);
                    i += cachedContent.headerHashCode(2);
                }
                dataOutputStream.writeInt(i);
                this.atomicFile.endWrite(dataOutputStream);
                Util.closeQuietly((Closeable) null);
            } catch (IOException e4) {
                e = e4;
                try {
                    throw new CacheException(e);
                } catch (Throwable th) {
                    th = th;
                    Util.closeQuietly((Closeable) dataOutputStream);
                    throw th;
                }
            }
        } catch (IOException e5) {
            Throwable th2 = e5;
            dataOutputStream = null;
            e = th2;
            throw new CacheException(e);
        } catch (Throwable th3) {
            Throwable th4 = th3;
            dataOutputStream = null;
            th = th4;
            Util.closeQuietly((Closeable) dataOutputStream);
            throw th;
        }
    }

    private CachedContent addNew(String str) {
        CachedContent cachedContent = new CachedContent(getNewId(this.idToKey), str);
        add(cachedContent);
        this.changed = true;
        return cachedContent;
    }

    private void add(CachedContent cachedContent) {
        this.keyToContent.put(cachedContent.key, cachedContent);
        this.idToKey.put(cachedContent.id, cachedContent.key);
    }

    private static Cipher getCipher() throws NoSuchPaddingException, NoSuchAlgorithmException {
        if (Util.SDK_INT == 18) {
            try {
                return Cipher.getInstance("AES/CBC/PKCS5PADDING", "BC");
            } catch (Throwable unused) {
            }
        }
        return Cipher.getInstance("AES/CBC/PKCS5PADDING");
    }

    public static int getNewId(SparseArray<String> sparseArray) {
        int i;
        int size = sparseArray.size();
        if (size == 0) {
            i = 0;
        } else {
            i = sparseArray.keyAt(size - 1) + 1;
        }
        if (i < 0) {
            int i2 = 0;
            while (i < size && i == sparseArray.keyAt(i)) {
                i2 = i + 1;
            }
        }
        return i;
    }
}