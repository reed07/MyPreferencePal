package com.uacf.core.caching;

import android.content.SharedPreferences;
import com.uacf.core.mapping.Mapper2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public class SharedPreferenceCache<T> implements Cache<T> {
    private Mapper2 mapper;
    private final SharedPreferences sharedPreferences;

    public void flush() {
    }

    public SharedPreferenceCache(SharedPreferences sharedPreferences2) {
        this.sharedPreferences = sharedPreferences2;
    }

    public Cache<T> withMapper(Mapper2 mapper2) {
        this.mapper = mapper2;
        return this;
    }

    public void put(String str, T t) {
        this.sharedPreferences.edit().putString(str, Strings.toString(this.mapper.reverseMap(t))).apply();
    }

    public T get(String str) {
        Object string = contains(str) ? this.sharedPreferences.getString(str, null) : null;
        if (string == null) {
            return null;
        }
        T tryMapFrom = this.mapper.tryMapFrom(string);
        if (tryMapFrom == null) {
            Ln.r("IDM1497: SharedPreferenceCache: item for key %s is null for json %s", str, string);
        }
        return tryMapFrom;
    }

    public void remove(String str) {
        this.sharedPreferences.edit().remove(str).apply();
    }

    public boolean contains(String str) {
        return this.sharedPreferences.contains(str);
    }

    public long getMetadata(String str, long j) {
        return this.sharedPreferences.getLong(str, j);
    }

    public void putMetadata(String str, long j) {
        this.sharedPreferences.edit().putLong(str, j).apply();
    }

    public void clear() {
        this.sharedPreferences.edit().clear().apply();
    }

    public Map<String, T> allItems() {
        Map all = this.sharedPreferences.getAll();
        HashMap hashMap = new HashMap();
        for (String str : all.keySet()) {
            if (all.get(str) instanceof String) {
                hashMap.put(str, get(str));
            }
        }
        return hashMap;
    }
}
