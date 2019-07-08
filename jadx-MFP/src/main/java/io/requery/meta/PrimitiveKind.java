package io.requery.meta;

public enum PrimitiveKind {
    INT,
    LONG,
    SHORT,
    BOOLEAN,
    FLOAT,
    DOUBLE,
    CHAR,
    BYTE;

    static PrimitiveKind fromClass(Class cls) {
        if (cls.isPrimitive()) {
            if (cls == Integer.TYPE) {
                return INT;
            }
            if (cls == Long.TYPE) {
                return LONG;
            }
            if (cls == Short.TYPE) {
                return SHORT;
            }
            if (cls == Float.TYPE) {
                return FLOAT;
            }
            if (cls == Double.TYPE) {
                return DOUBLE;
            }
            if (cls == Boolean.TYPE) {
                return BOOLEAN;
            }
            if (cls == Character.TYPE) {
                return CHAR;
            }
            if (cls == Byte.TYPE) {
                return BYTE;
            }
        }
        return null;
    }
}
