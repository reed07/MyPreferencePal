package com.shinobicontrols.charts;

import java.util.Collection;

public class SimpleDataAdapter<Tx, Ty> extends DataAdapter<Tx, Ty> {
    public boolean add(Data<Tx, Ty> data) {
        boolean add = super.add(data);
        if (add) {
            fireUpdateHandler();
        }
        return add;
    }

    public void add(int i, Data<Tx, Ty> data) {
        super.add(i, data);
        fireUpdateHandler();
    }

    public boolean addAll(Collection<? extends Data<Tx, Ty>> collection) {
        boolean addAll = super.addAll(collection);
        if (addAll) {
            fireUpdateHandler();
        }
        return addAll;
    }

    public boolean addAll(int i, Collection<? extends Data<Tx, Ty>> collection) {
        boolean addAll = super.addAll(i, collection);
        if (addAll) {
            fireUpdateHandler();
        }
        return addAll;
    }

    public void clear() {
        int size = size();
        super.clear();
        if (size > 0) {
            fireUpdateHandler();
        }
    }

    public Data<Tx, Ty> remove(int i) {
        Data<Tx, Ty> remove = super.remove(i);
        fireUpdateHandler();
        return remove;
    }

    public boolean remove(Object obj) {
        boolean remove = super.remove(obj);
        if (remove) {
            fireUpdateHandler();
        }
        return remove;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean removeAll = super.removeAll(collection);
        if (removeAll) {
            fireUpdateHandler();
        }
        return removeAll;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean retainAll = super.retainAll(collection);
        if (retainAll) {
            fireUpdateHandler();
        }
        return retainAll;
    }

    public Data<Tx, Ty> set(int i, Data<Tx, Ty> data) {
        Data<Tx, Ty> data2 = super.set(i, data);
        fireUpdateHandler();
        return data2;
    }
}
