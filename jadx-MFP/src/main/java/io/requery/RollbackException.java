package io.requery;

public class RollbackException extends TransactionException {
    public RollbackException(Throwable th) {
        super(th);
    }
}
