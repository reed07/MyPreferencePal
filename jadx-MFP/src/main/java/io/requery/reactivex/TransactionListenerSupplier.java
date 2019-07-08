package io.requery.reactivex;

import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import io.requery.TransactionIsolation;
import io.requery.TransactionListener;
import io.requery.meta.Type;
import io.requery.util.function.Supplier;
import java.util.Set;

final class TransactionListenerSupplier implements Supplier<TransactionListener> {
    /* access modifiers changed from: private */
    public final Subject<Set<Type<?>>> commitSubject = PublishSubject.create().toSerialized();
    /* access modifiers changed from: private */
    public final Subject<Set<Type<?>>> rollbackSubject = PublishSubject.create().toSerialized();

    TransactionListenerSupplier() {
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
                TransactionListenerSupplier.this.commitSubject.onNext(set);
            }

            public void afterRollback(Set<Type<?>> set) {
                TransactionListenerSupplier.this.rollbackSubject.onNext(set);
            }
        };
    }
}
