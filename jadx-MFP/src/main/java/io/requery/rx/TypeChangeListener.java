package io.requery.rx;

import io.requery.TransactionIsolation;
import io.requery.TransactionListener;
import io.requery.meta.Type;
import io.requery.util.function.Supplier;
import java.util.Set;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;

final class TypeChangeListener implements Supplier<TransactionListener> {
    /* access modifiers changed from: private */
    public final SerializedSubject<Set<Type<?>>, Set<Type<?>>> commitSubject = new SerializedSubject<>(PublishSubject.create());
    /* access modifiers changed from: private */
    public final SerializedSubject<Set<Type<?>>, Set<Type<?>>> rollbackSubject = new SerializedSubject<>(PublishSubject.create());

    TypeChangeListener() {
    }

    public TransactionListener get() {
        return new TransactionListener() {
            public void afterBegin(TransactionIsolation transactionIsolation) {
            }

            public void beforeBegin(TransactionIsolation transactionIsolation) {
            }

            public void beforeCommit(Set<Type<?>> set) {
            }

            public void beforeRollback(Set<Type<?>> set) {
            }

            public void afterCommit(Set<Type<?>> set) {
                TypeChangeListener.this.commitSubject.onNext(set);
            }

            public void afterRollback(Set<Type<?>> set) {
                TypeChangeListener.this.rollbackSubject.onNext(set);
            }
        };
    }
}
