package io.requery;

public class TransactionException extends PersistenceException {
    public TransactionException(String str) {
        super(str);
    }

    public TransactionException(Throwable th) {
        super(th);
    }
}
