package io.requery.proxy;

public interface DoubleProperty<E> extends Property<E, Double> {
    double getDouble(E e);

    void setDouble(E e, double d);
}
