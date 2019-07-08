package com.mopub.volley.toolbox;

import com.mopub.volley.Cache;
import com.mopub.volley.Cache.Entry;

public class NoCache implements Cache {
    public void clear() {
    }

    public Entry get(String str) {
        return null;
    }

    public void initialize() {
    }

    public void invalidate(String str, boolean z) {
    }

    public void put(String str, Entry entry) {
    }

    public void remove(String str) {
    }
}
