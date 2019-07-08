package io.requery.query.function;

public class Random extends Function<Float> {
    public Random() {
        super("random", Float.class);
    }

    public Object[] arguments() {
        return new Object[0];
    }
}
