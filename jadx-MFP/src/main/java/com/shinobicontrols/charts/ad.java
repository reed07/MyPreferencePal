package com.shinobicontrols.charts;

import java.util.Date;
import java.util.GregorianCalendar;

class ad {
    boolean a = false;
    private final DateTimeAxis b;
    private final GregorianCalendar c;
    private final a d = new a();
    private final a e = new a();

    private static class a {
        long a;
        boolean b;
        int c;
        int d;

        a() {
            a();
        }

        /* access modifiers changed from: 0000 */
        public void a() {
            this.a = Long.MAX_VALUE;
            this.b = false;
        }
    }

    ad(DateTimeAxis dateTimeAxis) {
        this.b = dateTimeAxis;
        this.c = new GregorianCalendar();
        this.c.clear();
    }

    /* access modifiers changed from: 0000 */
    public long a(double d2, DateFrequency dateFrequency) {
        return a((long) d2, dateFrequency, false);
    }

    /* access modifiers changed from: 0000 */
    public long b(double d2, DateFrequency dateFrequency) {
        return a((long) d2, dateFrequency, true);
    }

    /* access modifiers changed from: 0000 */
    public double a(DateFrequency dateFrequency) {
        if (!c(dateFrequency, this.d)) {
            this.d.a();
        }
        return (double) a(dateFrequency, this.d);
    }

    /* access modifiers changed from: 0000 */
    public double b(DateFrequency dateFrequency) {
        if (!c(dateFrequency, this.e)) {
            this.e.a();
        }
        return (double) a(dateFrequency, this.e);
    }

    /* access modifiers changed from: 0000 */
    public boolean c(double d2, DateFrequency dateFrequency) {
        long b2 = b(dateFrequency, this.d);
        boolean z = this.d.b;
        double d3 = (double) b2;
        if (d3 < d2) {
            while (true) {
                double d4 = (double) b2;
                if (d4 >= d2) {
                    break;
                }
                b2 = a(d4, dateFrequency);
                z = !z;
            }
        } else if (d3 > d2) {
            while (true) {
                double d5 = (double) b2;
                if (d5 <= d2) {
                    break;
                }
                b2 = b(d5, dateFrequency);
                z = !z;
            }
        }
        return z;
    }

    private long a(long j, DateFrequency dateFrequency, boolean z) {
        this.c.setTime((Date) this.b.transformInternalValueToUser((double) j));
        int i = dateFrequency.a;
        if (z) {
            i *= -1;
        }
        if (this.c.get(0) == 0) {
            i *= -1;
        }
        this.c.add(dateFrequency.b.a, i);
        Date time = this.c.getTime();
        long transformUserValueToInternal = (long) this.b.transformUserValueToInternal(time);
        return transformUserValueToInternal == j ? a(time, dateFrequency.b.a, i) : transformUserValueToInternal;
    }

    private long a(Date date, int i, int i2) {
        this.c.setTimeInMillis((long) a(date).b);
        this.c.add(i, i2);
        return (long) this.b.transformUserValueToInternal(this.c.getTime());
    }

    private ap a(Date date) {
        return this.b.y.b((double) date.getTime());
    }

    private long a(DateFrequency dateFrequency, a aVar) {
        long b2 = b(dateFrequency, aVar);
        boolean z = aVar.b;
        long j = (long) this.b.i.a;
        long a2 = a((double) j, dateFrequency);
        while (b2 < j) {
            b2 = a((double) b2, dateFrequency);
            z = !z;
        }
        while (b2 > a2) {
            b2 = b((double) b2, dateFrequency);
            z = !z;
        }
        aVar.a = b2;
        aVar.b = z;
        aVar.c = dateFrequency.a;
        aVar.d = dateFrequency.b.a;
        return b2;
    }

    private long b(DateFrequency dateFrequency, a aVar) {
        if (aVar.a != Long.MAX_VALUE) {
            return aVar.a;
        }
        return (long) this.b.G();
    }

    private boolean c(DateFrequency dateFrequency, a aVar) {
        return (this.a && d(dateFrequency, aVar)) ? false : true;
    }

    private boolean d(DateFrequency dateFrequency, a aVar) {
        return (dateFrequency.a == aVar.c && dateFrequency.b.a == aVar.d) ? false : true;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.d.a();
        this.e.a();
    }
}
