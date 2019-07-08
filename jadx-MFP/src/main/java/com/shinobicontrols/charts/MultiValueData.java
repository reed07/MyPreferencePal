package com.shinobicontrols.charts;

public interface MultiValueData<T> {
    T getClose();

    T getHigh();

    T getLow();

    T getOpen();
}
