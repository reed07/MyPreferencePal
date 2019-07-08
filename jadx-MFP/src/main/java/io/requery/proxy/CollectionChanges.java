package io.requery.proxy;

import io.requery.meta.Attribute;
import io.requery.util.CollectionObserver;
import io.requery.util.Objects;
import java.util.ArrayList;
import java.util.Collection;

public class CollectionChanges<T, E> implements CollectionObserver<E> {
    private final Collection<E> added = new ArrayList();
    private final Attribute<T, ?> attribute;
    private final EntityProxy<T> proxy;
    private final Collection<E> removed = new ArrayList();

    CollectionChanges(EntityProxy<T> entityProxy, Attribute<T, ?> attribute2) {
        this.proxy = entityProxy;
        this.attribute = attribute2;
    }

    public Collection<E> addedElements() {
        return this.added;
    }

    public Collection<E> removedElements() {
        return this.removed;
    }

    public void elementAdded(E e) {
        Objects.requireNotNull(e);
        if (!this.removed.remove(e) && this.added.add(e)) {
            this.proxy.setState(this.attribute, PropertyState.MODIFIED);
        }
    }

    public void elementRemoved(E e) {
        Objects.requireNotNull(e);
        if (!this.added.remove(e) && this.removed.add(e)) {
            this.proxy.setState(this.attribute, PropertyState.MODIFIED);
        }
    }

    public void clear() {
        this.added.clear();
        this.removed.clear();
    }
}
