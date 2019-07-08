package com.shinobicontrols.charts;

public interface Data<Tx, Ty> {
    Tx getX();

    Ty getY();
}
