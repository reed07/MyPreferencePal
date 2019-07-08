package com.myfitnesspal.shared.util;

import android.content.SharedPreferences;
import com.google.gson.JsonSyntaxException;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.uacf.core.util.Strings;
import java.io.IOException;
import java.util.NoSuchElementException;

public class SharedPreferencesObjectQueue<T> {
    private static final int MAX_SIZE = 1000000;
    private static final String PREF_KEY = "key_";
    private static final String PREF_NEXT = "next";
    private static final String PREF_START = "start";
    private final ApiJsonMapper mapper;
    private int maxSize = MAX_SIZE;
    private final SharedPreferences prefs;

    public SharedPreferencesObjectQueue(SharedPreferences sharedPreferences, ApiJsonMapper apiJsonMapper) {
        this.prefs = sharedPreferences;
        this.mapper = apiJsonMapper;
    }

    public int getMaxSize() {
        return this.maxSize;
    }

    public void setMaxSize(int i) {
        this.maxSize = i;
    }

    public int size() {
        int start = getStart();
        int next = getNext();
        int i = 0;
        if (start < 0) {
            return 0;
        }
        if (start == next) {
            return this.maxSize;
        }
        int i2 = next - start;
        if (start >= next) {
            i = this.maxSize;
        }
        return i2 + i;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean notEmpty() {
        return size() > 0;
    }

    public void add(T t) {
        int start = getStart();
        int next = getNext();
        if (start != next) {
            if (start < 0) {
                start = next;
            }
            String key = getKey(next);
            this.prefs.edit().putString(key, this.mapper.reverseMap((Object) t)).putInt("start", start).putInt(PREF_NEXT, (next + 1) % this.maxSize).apply();
            return;
        }
        throw new IllegalStateException("Cannot add to a full queue");
    }

    public T peek() {
        while (true) {
            int start = getStart();
            if (start < 0) {
                return null;
            }
            try {
                return getItem(start);
            } catch (Exception unused) {
                remove();
            }
        }
    }

    public void remove() {
        int start = getStart();
        int next = getNext();
        if (start >= 0) {
            String key = getKey(start);
            int i = (start + 1) % this.maxSize;
            if (i == next) {
                i = -1;
                next = 0;
            }
            this.prefs.edit().remove(key).putInt("start", i).putInt(PREF_NEXT, next).apply();
            return;
        }
        throw new NoSuchElementException("Cannot remove from an empty queue");
    }

    private String getKey(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREF_KEY);
        sb.append(i);
        return sb.toString();
    }

    private int getStart() {
        return this.prefs.getInt("start", -1);
    }

    private int getNext() {
        return this.prefs.getInt(PREF_NEXT, 0);
    }

    private T getItem(int i) throws IOException, JsonSyntaxException {
        String string = this.prefs.getString(getKey(i), null);
        if (Strings.notEmpty(string)) {
            return this.mapper.mapFrom(string);
        }
        return null;
    }
}
