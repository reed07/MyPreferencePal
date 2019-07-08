package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

@GwtCompatible(emulated = true, serializable = true)
abstract class ImmutableAsList<E> extends ImmutableList<E> {

    @GwtIncompatible
    static class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final ImmutableCollection<?> collection;

        SerializedForm(ImmutableCollection<?> immutableCollection) {
            this.collection = immutableCollection;
        }

        /* access modifiers changed from: 0000 */
        public Object readResolve() {
            return this.collection.asList();
        }
    }

    /* access modifiers changed from: 0000 */
    public abstract ImmutableCollection<E> delegateCollection();

    ImmutableAsList() {
    }

    public boolean contains(Object obj) {
        return delegateCollection().contains(obj);
    }

    public int size() {
        return delegateCollection().size();
    }

    public boolean isEmpty() {
        return delegateCollection().isEmpty();
    }

    /* access modifiers changed from: 0000 */
    public boolean isPartialView() {
        return delegateCollection().isPartialView();
    }

    @GwtIncompatible
    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Use SerializedForm");
    }

    /* access modifiers changed from: 0000 */
    @GwtIncompatible
    public Object writeReplace() {
        return new SerializedForm(delegateCollection());
    }
}
