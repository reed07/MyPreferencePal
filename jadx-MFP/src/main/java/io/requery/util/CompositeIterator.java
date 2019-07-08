package io.requery.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class CompositeIterator<E> implements Iterator<E> {
    private Iterator<E> current;
    private final Queue<Iterator<E>> queue = new LinkedList();

    @SafeVarargs
    public CompositeIterator(Iterator<E>... itArr) {
        this.queue.addAll(Arrays.asList(itArr));
        if (!this.queue.isEmpty()) {
            this.current = (Iterator) this.queue.poll();
        }
    }

    public boolean hasNext() {
        Iterator<E> it = this.current;
        if (it == null) {
            return false;
        }
        boolean hasNext = it.hasNext();
        while (!hasNext) {
            if (this.queue.isEmpty()) {
                return false;
            }
            this.current = (Iterator) this.queue.poll();
            hasNext = this.current.hasNext();
            if (hasNext) {
                return true;
            }
        }
        return true;
    }

    public E next() {
        Iterator<E> it = this.current;
        if (it != null) {
            if (it.hasNext()) {
                return this.current.next();
            }
            while (!this.queue.isEmpty()) {
                this.current = (Iterator) this.queue.poll();
                if (this.current.hasNext()) {
                    return this.current.next();
                }
            }
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        Iterator<E> it = this.current;
        if (it != null) {
            it.remove();
            return;
        }
        throw new IllegalStateException();
    }
}
