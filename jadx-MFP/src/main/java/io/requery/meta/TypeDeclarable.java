package io.requery.meta;

interface TypeDeclarable<T> {
    void setDeclaringType(Type<T> type);
}
