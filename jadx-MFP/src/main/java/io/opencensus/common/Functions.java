package io.opencensus.common;

public final class Functions {
    private static final Function<Object, Void> RETURN_NULL = new Function<Object, Void>() {
    };
    private static final Function<Object, String> RETURN_TO_STRING = new Function<Object, String>() {
    };
    private static final Function<Object, Void> THROW_ASSERTION_ERROR = new Function<Object, Void>() {
    };
    private static final Function<Object, Void> THROW_ILLEGAL_ARGUMENT_EXCEPTION = new Function<Object, Void>() {
    };

    private Functions() {
    }
}
