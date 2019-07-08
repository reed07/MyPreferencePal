package com.shinobicontrols.charts;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public abstract class DataAdapter<Tx, Ty> implements Iterable<Data<Tx, Ty>> {
    private final al a = new al();
    private final List<Data<Tx, Ty>> b = new bo();

    protected DataAdapter() {
    }

    public boolean add(Data<Tx, Ty> data) {
        return this.b.add(data);
    }

    public void add(int i, Data<Tx, Ty> data) {
        this.b.add(i, data);
    }

    public boolean addAll(Collection<? extends Data<Tx, Ty>> collection) {
        return this.b.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends Data<Tx, Ty>> collection) {
        return this.b.addAll(i, collection);
    }

    public void clear() {
        this.b.clear();
    }

    public boolean contains(Object obj) {
        return this.b.contains(obj);
    }

    public boolean containsAll(Collection<?> collection) {
        return this.b.containsAll(collection);
    }

    public Data<Tx, Ty> get(int i) {
        return (Data) this.b.get(i);
    }

    public int hashCode() {
        return this.b.hashCode();
    }

    public int indexOf(Object obj) {
        return this.b.indexOf(obj);
    }

    public boolean isEmpty() {
        return this.b.isEmpty();
    }

    public Iterator<Data<Tx, Ty>> iterator() {
        return this.b.iterator();
    }

    public int lastIndexOf(Object obj) {
        return this.b.lastIndexOf(obj);
    }

    public Data<Tx, Ty> remove(int i) {
        return (Data) this.b.remove(i);
    }

    public boolean remove(Object obj) {
        return this.b.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        return this.b.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        return this.b.retainAll(collection);
    }

    public Data<Tx, Ty> set(int i, Data<Tx, Ty> data) {
        return (Data) this.b.set(i, data);
    }

    public int size() {
        return this.b.size();
    }

    public Object[] toArray() {
        return this.b.toArray();
    }

    public List<Data<Tx, Ty>> getDataPointsForDisplay() {
        return this.b;
    }

    public <T> T[] toArray(T[] tArr) {
        return this.b.toArray(tArr);
    }

    /* access modifiers changed from: protected */
    public final void fireUpdateHandler() {
        this.a.a(new di());
    }

    /* access modifiers changed from: 0000 */
    public final am a(a aVar) {
        return this.a.a(di.a, (a) aVar);
    }
}
