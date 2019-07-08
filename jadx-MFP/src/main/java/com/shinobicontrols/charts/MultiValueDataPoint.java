package com.shinobicontrols.charts;

public class MultiValueDataPoint<Tx, Tv> extends DataPoint<Tx, Tv> implements Data<Tx, Tv>, MultiValueData<Tv>, SelectableData {
    private final Tv a;
    private final Tv b;
    private final Tv c;
    private final Tv d;

    public MultiValueDataPoint(Tx tx, Tv tv, Tv tv2, Tv tv3, Tv tv4) {
        super(tx, tv4);
        if (tv == null || tv2 == null || tv3 == null || tv4 == null) {
            throw new IllegalArgumentException("You must supply all DataPoint parameter arguments, non-null");
        }
        this.a = tv;
        this.b = tv2;
        this.c = tv3;
        this.d = tv4;
    }

    public MultiValueDataPoint(Tx tx, Tv tv, Tv tv2, Tv tv3, Tv tv4, boolean z) {
        super(tx, tv4, z);
        if (tv == null || tv2 == null || tv3 == null || tv4 == null) {
            throw new IllegalArgumentException("You must supply all DataPoint parameter arguments, non-null");
        }
        this.a = tv;
        this.b = tv2;
        this.c = tv3;
        this.d = tv4;
    }

    public MultiValueDataPoint(Tx tx, Tv tv, Tv tv2) {
        super(tx, tv2);
        if (tv == null || tv2 == null) {
            throw new IllegalArgumentException("You must supply all DataPoint parameter arguments, non-null");
        }
        this.a = tv;
        this.b = tv2;
        this.c = null;
        this.d = null;
    }

    public MultiValueDataPoint(Tx tx, Tv tv, Tv tv2, boolean z) {
        super(tx, tv2, z);
        if (tv == null || tv2 == null) {
            throw new IllegalArgumentException("You must supply all DataPoint parameter arguments, non-null");
        }
        this.a = tv;
        this.b = tv2;
        this.c = null;
        this.d = null;
    }

    public Tv getOpen() {
        return this.c;
    }

    public Tv getHigh() {
        return this.b;
    }

    public Tv getLow() {
        return this.a;
    }

    public Tv getClose() {
        return this.d;
    }
}
