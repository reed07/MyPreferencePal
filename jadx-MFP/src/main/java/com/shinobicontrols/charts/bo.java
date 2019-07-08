package com.shinobicontrols.charts;

import java.util.ArrayList;
import java.util.Collection;

class bo<E> extends ArrayList<E> {
    private static final long serialVersionUID = 8109538979144194735L;

    bo() {
    }

    public boolean add(E e) {
        a(e == null);
        return super.add(e);
    }

    public void add(int i, E e) {
        a(e == null);
        super.add(i, e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        a(collection.contains(null));
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        a(collection.contains(null));
        return super.addAll(i, collection);
    }

    public E set(int i, E e) {
        a(e == null);
        return super.set(i, e);
    }

    private void a(boolean z) {
        if (z) {
            throw new NullPointerException("Cannot add null elements.");
        }
    }
}
