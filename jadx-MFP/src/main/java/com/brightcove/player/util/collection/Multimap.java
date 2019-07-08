package com.brightcove.player.util.collection;

import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Multimap<K, V> {
    private Map<K, List<V>> map = new HashMap();

    @NonNull
    public List<V> get(K k) {
        List<V> list = (List) this.map.get(k);
        return list == null ? Collections.emptyList() : list;
    }

    public void put(K k, V v) {
        List list = (List) this.map.get(k);
        if (list == null) {
            list = new ArrayList();
            this.map.put(k, list);
        }
        list.add(v);
    }

    public int size() {
        int i = 0;
        for (List size : this.map.values()) {
            i += size.size();
        }
        return i;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }
}
