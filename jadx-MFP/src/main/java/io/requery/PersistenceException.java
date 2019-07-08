package io.requery;

public class PersistenceException extends RuntimeException {
    public PersistenceException() {
    }

    public PersistenceException(Throwable th) {
        super(th);
    }

    public PersistenceException(String str, Throwable th) {
        super(str, th);
    }

    public PersistenceException(String str) {
        super(str);
    }
}
