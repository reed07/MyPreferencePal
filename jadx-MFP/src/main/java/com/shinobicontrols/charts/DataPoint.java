package com.shinobicontrols.charts;

public class DataPoint<Tx, Ty> implements Data<Tx, Ty>, SelectableData {
    private final Tx a;
    private final Ty b;
    private boolean c;

    public DataPoint(Tx tx, Ty ty) {
        if (tx == null || ty == null) {
            throw new IllegalArgumentException("You must supply all DataPoint parameter arguments, non-null");
        }
        this.a = tx;
        this.b = ty;
    }

    public DataPoint(Tx tx, Ty ty, boolean z) {
        if (tx == null || ty == null) {
            throw new IllegalArgumentException("You must supply all DataPoint parameter arguments, non-null");
        }
        this.a = tx;
        this.b = ty;
        this.c = z;
    }

    public Tx getX() {
        return this.a;
    }

    public Ty getY() {
        return this.b;
    }

    public boolean getSelected() {
        return this.c;
    }
}
