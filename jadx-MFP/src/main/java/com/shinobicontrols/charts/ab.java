package com.shinobicontrols.charts;

import com.shinobicontrols.charts.Series.Orientation;

class ab {
    static DataPoint<?, ?> a(InternalDataPoint internalDataPoint, CartesianSeries<?> cartesianSeries) {
        if (c(internalDataPoint, cartesianSeries)) {
            return a(internalDataPoint.a, internalDataPoint, cartesianSeries);
        }
        return a(internalDataPoint.a, internalDataPoint.b, internalDataPoint.h, cartesianSeries);
    }

    static DataPoint<?, ?> b(InternalDataPoint internalDataPoint, CartesianSeries<?> cartesianSeries) {
        return a(internalDataPoint.c, internalDataPoint.d, internalDataPoint.h, cartesianSeries);
    }

    static DataPoint<?, ?> a(bz bzVar, InternalDataPoint internalDataPoint, CartesianSeries<?> cartesianSeries) {
        return a(bzVar.b, bzVar.c, internalDataPoint.h, cartesianSeries);
    }

    private static boolean c(InternalDataPoint internalDataPoint, CartesianSeries<?> cartesianSeries) {
        return cartesianSeries.getDataAdapter().get(internalDataPoint.i) instanceof MultiValueData;
    }

    private static DataPoint<?, ?> a(double d, double d2, boolean z, CartesianSeries<?> cartesianSeries) {
        return new DataPoint<>(cartesianSeries.getXAxis().transformInternalValueToUser(d), cartesianSeries.getYAxis().transformInternalValueToUser(d2), z);
    }

    private static DataPoint<?, ?> a(double d, InternalDataPoint internalDataPoint, CartesianSeries<?> cartesianSeries) {
        Axis a = a(cartesianSeries);
        Axis b = b(cartesianSeries);
        Comparable transformInternalValueToUser = a.transformInternalValueToUser(d);
        Comparable transformInternalValueToUser2 = b.transformInternalValueToUser(((Double) internalDataPoint.j.get("Low")).doubleValue());
        Comparable transformInternalValueToUser3 = b.transformInternalValueToUser(((Double) internalDataPoint.j.get("High")).doubleValue());
        Double d2 = (Double) internalDataPoint.j.get("Open");
        Double d3 = (Double) internalDataPoint.j.get("Close");
        if (d2 == null || d3 == null) {
            return new MultiValueDataPoint(transformInternalValueToUser, transformInternalValueToUser2, transformInternalValueToUser3, internalDataPoint.h);
        }
        MultiValueDataPoint multiValueDataPoint = new MultiValueDataPoint(transformInternalValueToUser, transformInternalValueToUser2, transformInternalValueToUser3, b.transformInternalValueToUser(d2.doubleValue()), b.transformInternalValueToUser(d3.doubleValue()), internalDataPoint.h);
        return multiValueDataPoint;
    }

    private static Axis<?, ?> a(CartesianSeries<?> cartesianSeries) {
        return c(cartesianSeries) ? cartesianSeries.getYAxis() : cartesianSeries.getXAxis();
    }

    private static Axis<?, ?> b(CartesianSeries<?> cartesianSeries) {
        return c(cartesianSeries) ? cartesianSeries.getXAxis() : cartesianSeries.getYAxis();
    }

    private static boolean c(CartesianSeries<?> cartesianSeries) {
        return cartesianSeries.j == Orientation.VERTICAL;
    }
}
