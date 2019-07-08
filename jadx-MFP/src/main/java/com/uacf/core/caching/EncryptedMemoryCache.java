package com.uacf.core.caching;

import com.uacf.core.encryption.AESEncryptorUtil;
import com.uacf.core.encryption.EncryptedValue;
import com.uacf.core.encryption.UacfEncryptionException;
import com.uacf.core.mapping.Mapper2;
import com.uacf.core.util.Ln;
import java.util.HashMap;
import java.util.Map;

public class EncryptedMemoryCache<T> implements Cache<T> {
    private Cache<EncryptedValue> delegatingCache;
    private AESEncryptorUtil encryptUtil;
    private String encryptionKeyAlias;
    private Map<String, T> map;
    private Mapper2 mapper;

    public Cache<T> withMapper(Mapper2 mapper2) {
        this.mapper = mapper2;
        return this;
    }

    public synchronized T get(String str) {
        T t = this.map.get(str);
        if (t == null) {
            try {
                EncryptedValue encryptedValue = (EncryptedValue) this.delegatingCache.get(str);
                if (encryptedValue == null) {
                    Ln.r("IDM1497: item for key %s is null", str);
                    return null;
                }
                this.encryptUtil.generateAndStoreEncryptionKey(this.encryptionKeyAlias);
                t = this.mapper.tryMapFrom(this.encryptUtil.decrypt(this.encryptionKeyAlias, encryptedValue));
                if (t == null) {
                    return null;
                }
                this.map.put(str, t);
                return t;
            } catch (UacfEncryptionException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Failed to get value for key: ");
                sb.append(str);
                Ln.e(e, sb.toString(), new Object[0]);
                return t;
            }
        }
    }

    public synchronized void put(String str, T t) {
        try {
            this.encryptUtil.generateAndStoreEncryptionKey(this.encryptionKeyAlias);
            String str2 = (String) this.mapper.reverseMap(t);
            if (str2 != null) {
                this.delegatingCache.put(str, this.encryptUtil.encrypt(this.encryptionKeyAlias, str2));
                this.map.put(str, t);
            } else {
                return;
            }
        } catch (UacfEncryptionException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to store value for key: ");
            sb.append(str);
            Ln.e(e, sb.toString(), new Object[0]);
        }
        return;
    }

    public void remove(String str) {
        this.map.remove(str);
        this.delegatingCache.remove(str);
    }

    public boolean contains(String str) {
        return this.map.containsKey(str) || this.delegatingCache.contains(str);
    }

    public long getMetadata(String str, long j) {
        return this.delegatingCache.getMetadata(str, j);
    }

    public void putMetadata(String str, long j) {
        this.delegatingCache.putMetadata(str, j);
    }

    public synchronized void clear() {
        this.map.clear();
        this.delegatingCache.clear();
    }

    public Map<String, T> allItems() {
        flush();
        HashMap hashMap = new HashMap();
        for (String str : this.delegatingCache.allItems().keySet()) {
            hashMap.put(str, get(str));
        }
        return hashMap;
    }

    public void flush() {
        this.delegatingCache.flush();
    }
}
