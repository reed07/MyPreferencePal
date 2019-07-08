package io.requery;

public enum TransactionIsolation {
    NONE,
    READ_UNCOMMITTED,
    READ_COMMITTED,
    REPEATABLE_READ,
    SERIALIZABLE
}
