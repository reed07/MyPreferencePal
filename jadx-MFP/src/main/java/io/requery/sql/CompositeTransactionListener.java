package io.requery.sql;

import io.requery.TransactionIsolation;
import io.requery.TransactionListener;
import io.requery.meta.Type;
import io.requery.util.function.Supplier;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class CompositeTransactionListener extends HashSet<TransactionListener> implements TransactionListener {
    CompositeTransactionListener(Set<Supplier<TransactionListener>> set) {
        for (Supplier supplier : set) {
            TransactionListener transactionListener = (TransactionListener) supplier.get();
            if (transactionListener != null) {
                add(transactionListener);
            }
        }
    }

    public void beforeBegin(TransactionIsolation transactionIsolation) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((TransactionListener) it.next()).beforeBegin(transactionIsolation);
        }
    }

    public void afterBegin(TransactionIsolation transactionIsolation) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((TransactionListener) it.next()).afterBegin(transactionIsolation);
        }
    }

    public void beforeCommit(Set<Type<?>> set) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((TransactionListener) it.next()).beforeCommit(set);
        }
    }

    public void afterCommit(Set<Type<?>> set) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((TransactionListener) it.next()).afterCommit(set);
        }
    }

    public void beforeRollback(Set<Type<?>> set) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((TransactionListener) it.next()).beforeRollback(set);
        }
    }

    public void afterRollback(Set<Type<?>> set) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((TransactionListener) it.next()).afterRollback(set);
        }
    }
}
