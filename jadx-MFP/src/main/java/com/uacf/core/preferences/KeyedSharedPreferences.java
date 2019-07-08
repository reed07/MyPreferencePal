package com.uacf.core.preferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class KeyedSharedPreferences implements SharedPreferences {
    private final SharedPreferences delegate;
    /* access modifiers changed from: private */
    public OnSharedPreferenceChangeListener delegateListener = new OnSharedPreferenceChangeListener() {
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
            if (KeyedSharedPreferences.this.externalListener != null) {
                OnSharedPreferenceChangeListener access$300 = KeyedSharedPreferences.this.delegateListener;
                KeyedSharedPreferences keyedSharedPreferences = KeyedSharedPreferences.this;
                access$300.onSharedPreferenceChanged(keyedSharedPreferences, keyedSharedPreferences.reverseKey(str));
            }
        }
    };
    /* access modifiers changed from: private */
    public OnSharedPreferenceChangeListener externalListener;
    private final KeyProvider keyProvider;

    public interface KeyProvider {
        String getKey();
    }

    private static class KeyedEditor implements Editor {
        private final Editor delegate;
        private final KeyedSharedPreferences parent;

        public KeyedEditor(KeyedSharedPreferences keyedSharedPreferences, Editor editor) {
            this.parent = keyedSharedPreferences;
            this.delegate = editor;
        }

        public Editor putString(String str, String str2) {
            this.delegate.putString(this.parent.createKey(str), str2);
            return this;
        }

        public Editor putStringSet(String str, Set<String> set) {
            this.delegate.putStringSet(this.parent.createKey(str), set);
            return this;
        }

        public Editor putInt(String str, int i) {
            this.delegate.putInt(this.parent.createKey(str), i);
            return this;
        }

        public Editor putLong(String str, long j) {
            this.delegate.putLong(this.parent.createKey(str), j);
            return this;
        }

        public Editor putFloat(String str, float f) {
            this.delegate.putFloat(this.parent.createKey(str), f);
            return this;
        }

        public Editor putBoolean(String str, boolean z) {
            this.delegate.putBoolean(this.parent.createKey(str), z);
            return this;
        }

        public Editor remove(String str) {
            this.delegate.remove(this.parent.createKey(str));
            return this;
        }

        public Editor clear() {
            this.delegate.clear();
            return this;
        }

        public boolean commit() {
            return this.delegate.commit();
        }

        public void apply() {
            this.delegate.apply();
        }
    }

    public KeyedSharedPreferences(SharedPreferences sharedPreferences, KeyProvider keyProvider2) {
        this.delegate = sharedPreferences;
        this.keyProvider = keyProvider2;
    }

    public Map<String, ?> getAll() {
        Map all = this.delegate.getAll();
        HashMap hashMap = new HashMap();
        for (String str : all.keySet()) {
            if (doesKeyMatchKeyProvider(str)) {
                hashMap.put(str, all.get(str));
            }
        }
        return hashMap;
    }

    public String getString(String str, String str2) {
        return this.delegate.getString(createKey(str), str2);
    }

    public Set<String> getStringSet(String str, Set<String> set) {
        return this.delegate.getStringSet(createKey(str), set);
    }

    public int getInt(String str, int i) {
        return this.delegate.getInt(createKey(str), i);
    }

    public long getLong(String str, long j) {
        return this.delegate.getLong(createKey(str), j);
    }

    public float getFloat(String str, float f) {
        return this.delegate.getFloat(createKey(str), f);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.delegate.getBoolean(createKey(str), z);
    }

    public boolean contains(String str) {
        return this.delegate.contains(createKey(str));
    }

    public Editor edit() {
        return new KeyedEditor(this, this.delegate.edit());
    }

    public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.externalListener = onSharedPreferenceChangeListener;
        this.delegate.registerOnSharedPreferenceChangeListener(this.delegateListener);
    }

    public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.delegate.unregisterOnSharedPreferenceChangeListener(this.delegateListener);
        this.externalListener = null;
    }

    /* access modifiers changed from: private */
    public String createKey(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(getSuffix());
        return sb.toString();
    }

    private String getSuffix() {
        StringBuilder sb = new StringBuilder();
        sb.append("_");
        sb.append(Strings.toString(this.keyProvider.getKey()));
        return sb.toString();
    }

    /* access modifiers changed from: private */
    public String reverseKey(String str) {
        return str.split("_")[0];
    }

    private boolean doesKeyMatchKeyProvider(String str) {
        return str.endsWith(getSuffix());
    }
}
