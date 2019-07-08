package com.shinobicontrols.charts;

import android.graphics.PointF;
import com.shinobicontrols.charts.Series.SelectionMode;

class cn {
    private final v a;

    private static class a {
        double a;
        double b;
        double c;
        double d;

        private a() {
        }
    }

    enum b {
        CROSSHAIR_ENABLED,
        SELECTION_MODE_NOT_NONE
    }

    cn(v vVar) {
        this.a = vVar;
    }

    /* access modifiers changed from: 0000 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.shinobicontrols.charts.Series.a a(android.graphics.PointF r6, com.shinobicontrols.charts.cn.b r7) {
        /*
            r5 = this;
            com.shinobicontrols.charts.v r0 = r5.a
            java.util.List r0 = r0.h()
            java.util.Iterator r0 = r0.iterator()
            r1 = 0
        L_0x000b:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0043
            java.lang.Object r2 = r0.next()
            com.shinobicontrols.charts.CartesianSeries r2 = (com.shinobicontrols.charts.CartesianSeries) r2
            boolean r3 = r2.y
            if (r3 == 0) goto L_0x001c
            goto L_0x000b
        L_0x001c:
            int[] r3 = com.shinobicontrols.charts.cn.AnonymousClass1.a
            int r4 = r7.ordinal()
            r3 = r3[r4]
            switch(r3) {
                case 1: goto L_0x002f;
                case 2: goto L_0x0028;
                default: goto L_0x0027;
            }
        L_0x0027:
            goto L_0x0036
        L_0x0028:
            com.shinobicontrols.charts.Series$SelectionMode r3 = r2.s
            com.shinobicontrols.charts.Series$SelectionMode r4 = com.shinobicontrols.charts.Series.SelectionMode.NONE
            if (r3 != r4) goto L_0x0036
            goto L_0x000b
        L_0x002f:
            com.shinobicontrols.charts.aa r3 = r2.l
            boolean r3 = r3.a
            if (r3 != 0) goto L_0x0036
            goto L_0x000b
        L_0x0036:
            r3 = 0
            com.shinobicontrols.charts.Series$a r2 = r5.a(r2, r6, r3)
            boolean r3 = r2.a(r1)
            if (r3 == 0) goto L_0x000b
            r1 = r2
            goto L_0x000b
        L_0x0043:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.cn.a(android.graphics.PointF, com.shinobicontrols.charts.cn$b):com.shinobicontrols.charts.Series$a");
    }

    /* access modifiers changed from: 0000 */
    public a a(PointF pointF) {
        double d = (double) (-(pointF.x - ((float) this.a.b.b.centerX())));
        double centerY = (double) (pointF.y - ((float) this.a.b.b.centerY()));
        float sqrt = (float) (Math.sqrt((centerY * centerY) + (d * d)) / (((double) Math.min(this.a.b.b.width(), this.a.b.b.height())) / 2.0d));
        for (Series series : this.a.c) {
            if (!(series.getSelectionMode() == SelectionMode.NONE || series.getSelectionMode() == SelectionMode.SERIES || !series.s())) {
                PieDonutSeries pieDonutSeries = (PieDonutSeries) series;
                if (pieDonutSeries.getInnerRadius() < sqrt && sqrt <= pieDonutSeries.getOuterRadius() + Math.max(((PieDonutSeriesStyle) pieDonutSeries.r).getProtrusion(), ((PieDonutSeriesStyle) pieDonutSeries.q).getProtrusion())) {
                    a aVar = new a(pieDonutSeries);
                    int length = series.n.c.length;
                    int i = 0;
                    if (length == 1) {
                        aVar.a(series.n.c[0]);
                        return aVar;
                    }
                    cg g = pieDonutSeries.g();
                    float rotation = pieDonutSeries.getRotation();
                    float a2 = g.a(d, centerY);
                    while (i < length) {
                        PieDonutSlice pieDonutSlice = (PieDonutSlice) series.n.c[i];
                        float a3 = g.a(rotation, pieDonutSlice.n);
                        float b2 = g.b(rotation, pieDonutSlice.o);
                        int i2 = (b2 > a2 ? 1 : (b2 == a2 ? 0 : -1));
                        if ((i2 <= 0 || a2 <= a3) && (a3 <= b2 || (a2 <= a3 && i2 <= 0))) {
                            i++;
                        } else {
                            aVar.a((InternalDataPoint) pieDonutSlice);
                            return aVar;
                        }
                    }
                    return aVar;
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public a a(CartesianSeries<?> cartesianSeries, PointF pointF, boolean z) {
        a aVar = new a(cartesianSeries);
        cartesianSeries.a(aVar, new bz(cartesianSeries.getXAxis().e((double) pointF.x), cartesianSeries.getYAxis().e((double) pointF.y)), z);
        return aVar;
    }

    static double a(bz bzVar, bz bzVar2, bz bzVar3, a aVar) {
        double d = (bzVar.b - bzVar2.b) / bzVar3.b;
        double d2 = (bzVar.c - bzVar2.c) / bzVar3.c;
        switch (aVar) {
            case CROW_FLIES:
                return Math.sqrt((d * d) + (d2 * d2));
            case HORIZONTAL:
                return Math.abs(d);
            case VERTICAL:
                return Math.abs(d2);
            default:
                throw new IllegalStateException(String.format("Bad distance mode %d", new Object[]{aVar}));
        }
    }

    static bz a(CartesianSeries<?> cartesianSeries) {
        return new bz(cartesianSeries.getXAxis().i.getSpan(), cartesianSeries.getYAxis().i.getSpan() * (((double) cartesianSeries.o.b.b.width()) / ((double) cartesianSeries.o.b.b.height())));
    }

    private static void b(CartesianSeries<?> cartesianSeries, a aVar, bz bzVar, boolean z) {
        bz a2 = a(cartesianSeries);
        bz bzVar2 = new bz();
        double d = Double.MAX_VALUE;
        for (InternalDataPoint internalDataPoint : cartesianSeries.n.c) {
            bzVar2.b = internalDataPoint.c;
            bzVar2.c = internalDataPoint.d;
            double a3 = a(bzVar, bzVar2, a2, z ? cartesianSeries.g() : cartesianSeries.f());
            if (a3 < d) {
                aVar.a(a3);
                aVar.a(internalDataPoint);
                d = a3;
            }
        }
    }

    static void a(CartesianSeries<?> cartesianSeries, a aVar, bz bzVar, boolean z) {
        b(cartesianSeries, aVar, bzVar, z);
        if (a.b(aVar)) {
            cartesianSeries.a(aVar, bzVar);
        }
    }

    static void a(BarColumnSeries<?> barColumnSeries, a aVar, bz bzVar, boolean z) {
        a((CartesianSeries<?>) barColumnSeries, aVar, bzVar, z);
        if (a.b(aVar)) {
            InternalDataPoint b2 = aVar.b();
            double d = (double) barColumnSeries.a;
            a aVar2 = new a();
            aVar2.b = b2.d - (d / 2.0d);
            aVar2.d = d;
            double b3 = barColumnSeries.t.b((CartesianSeries<?>) barColumnSeries);
            double d2 = b2.a;
            boolean z2 = true;
            if (barColumnSeries.c != null) {
                if (d2 >= 0.0d) {
                    z2 = false;
                }
                if (z2) {
                    aVar2.a = b2.c;
                    aVar2.c = b3 - b2.a;
                } else {
                    aVar2.a = b2.c - b2.a;
                    aVar2.c = d2;
                }
            } else {
                if (d2 >= b3) {
                    z2 = false;
                }
                if (z2) {
                    aVar2.a = d2;
                    aVar2.c = b3 - d2;
                } else {
                    aVar2.a = b3;
                    aVar2.c = d2 - b3;
                }
            }
            a(aVar, bzVar, aVar2, z);
        }
    }

    static void b(BarColumnSeries<?> barColumnSeries, a aVar, bz bzVar, boolean z) {
        a((CartesianSeries<?>) barColumnSeries, aVar, bzVar, z);
        if (a.b(aVar)) {
            InternalDataPoint b2 = aVar.b();
            double d = (double) barColumnSeries.a;
            a aVar2 = new a();
            aVar2.a = b2.c - (d / 2.0d);
            aVar2.c = d;
            double b3 = barColumnSeries.t.b((CartesianSeries<?>) barColumnSeries);
            double d2 = b2.b;
            boolean z2 = true;
            if (barColumnSeries.c != null) {
                if (d2 >= 0.0d) {
                    z2 = false;
                }
                if (z2) {
                    aVar2.b = b2.d;
                    aVar2.d = b3 - b2.b;
                } else {
                    aVar2.b = b2.d - b2.b;
                    aVar2.d = d2;
                }
            } else {
                if (d2 >= b3) {
                    z2 = false;
                }
                if (z2) {
                    aVar2.b = d2;
                    aVar2.d = b3 - d2;
                } else {
                    aVar2.b = b3;
                    aVar2.d = d2 - b3;
                }
            }
            a(aVar, bzVar, aVar2, z);
        }
    }

    private static void a(a aVar, bz bzVar, a aVar2, boolean z) {
        if (a(bzVar, aVar2)) {
            aVar.a(0.0d);
            return;
        }
        aVar.a(Double.MAX_VALUE);
        if (!z) {
            aVar.e();
        }
    }

    private static boolean a(bz bzVar, a aVar) {
        a(aVar);
        return aVar.a <= bzVar.b && bzVar.b <= aVar.a + aVar.c && aVar.b <= bzVar.c && bzVar.c <= aVar.b + aVar.d;
    }

    private static void a(a aVar) {
        if (aVar.c < 0.0d) {
            aVar.a += aVar.c;
            aVar.c = Math.abs(aVar.c);
        }
        if (aVar.d < 0.0d) {
            aVar.b += aVar.d;
            aVar.d = Math.abs(aVar.d);
        }
    }
}
