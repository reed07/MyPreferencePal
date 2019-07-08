package io.requery.query;

import io.requery.util.CloseableIterable;
import io.requery.util.CloseableIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class BaseResult<E> implements Result<E>, CloseableIterable<E> {
    private final AtomicBoolean closed;
    private final Queue<CloseableIterator<E>> iterators;
    private final Integer maxSize;

    public abstract CloseableIterator<E> iterator(int i, int i2);

    protected BaseResult() {
        this(null);
    }

    protected BaseResult(Integer num) {
        this.maxSize = num;
        this.iterators = new ConcurrentLinkedQueue();
        this.closed = new AtomicBoolean();
    }

    public List<E> toList() {
        Integer num = this.maxSize;
        ArrayList arrayList = num == null ? new ArrayList() : new ArrayList(num.intValue());
        collect(arrayList);
        return Collections.unmodifiableList(arrayList);
    }

    public <C extends Collection<E>> C collect(C c) {
        Throwable th;
        CloseableIterator it = iterator();
        while (it.hasNext()) {
            try {
                c.add(it.next());
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        if (it != null) {
            it.close();
        }
        return c;
        throw th;
    }

    public E first() {
        CloseableIterator it = iterator();
        try {
            E next = it.next();
            if (it != null) {
                it.close();
            }
            return next;
        } catch (Throwable th) {
            th = th;
        }
        throw th;
        if (it != null) {
            if (r2 != null) {
                try {
                    it.close();
                } catch (Throwable th2) {
                    r2.addSuppressed(th2);
                }
            } else {
                it.close();
            }
        }
        throw th;
    }

    public E firstOr(E e) {
        Throwable th;
        CloseableIterator it = iterator();
        try {
            if (it.hasNext()) {
                E next = it.next();
                if (it != null) {
                    it.close();
                }
                return next;
            }
            if (it != null) {
                it.close();
            }
            return e;
        } catch (Throwable th2) {
            th.addSuppressed(th2);
        }
        throw th;
    }

    public E firstOrNull() {
        return firstOr(null);
    }

    public CloseableIterator<E> iterator() {
        if (!this.closed.get()) {
            CloseableIterator<E> it = iterator(0, Integer.MAX_VALUE);
            this.iterators.add(it);
            return it;
        }
        throw new IllegalStateException();
    }

    public void close() {
        if (this.closed.compareAndSet(false, true)) {
            CloseableIterator closeableIterator = (CloseableIterator) this.iterators.poll();
            while (closeableIterator != null) {
                closeableIterator.close();
                closeableIterator = (CloseableIterator) this.iterators.poll();
            }
        }
    }
}
