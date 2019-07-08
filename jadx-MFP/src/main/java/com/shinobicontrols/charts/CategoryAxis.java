package com.shinobicontrols.charts;

import android.graphics.PointF;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CategoryAxis extends NumberAxis {
    private final List<String> A = new ArrayList();
    private final List<String> B = new ArrayList();

    /* access modifiers changed from: 0000 */
    public double a(double d, boolean z) {
        return d + 1.0d;
    }

    /* access modifiers changed from: 0000 */
    public double b(double d, boolean z) {
        return d - 1.0d;
    }

    /* access modifiers changed from: 0000 */
    public double b(int i) {
        return Double.NaN;
    }

    /* access modifiers changed from: 0000 */
    public boolean j() {
        return true;
    }

    public CategoryAxis() {
    }

    public CategoryAxis(NumberRange numberRange) {
        setDefaultRange(numberRange);
    }

    /* access modifiers changed from: 0000 */
    public boolean isDataValid(Object obj) {
        return obj.toString() != null;
    }

    /* access modifiers changed from: 0000 */
    public double convertPoint(Object obj) {
        validateUserData(obj);
        String obj2 = obj.toString();
        b(obj2);
        c(obj2);
        return (double) this.A.indexOf(obj2);
    }

    private void b(String str) {
        if (!this.B.contains(str)) {
            this.B.add(str);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.b.getContext().getString(R.string.CategoryAxisSeriesAlreadyContainsPoint));
        sb.append(" '");
        sb.append(str);
        sb.append("'");
        throw new IllegalArgumentException(sb.toString());
    }

    private void c(String str) {
        if (!this.A.contains(str)) {
            this.A.add(str);
        }
    }

    public List<String> getCategories() {
        return Collections.unmodifiableList(this.A);
    }

    public boolean requestCurrentDisplayedRange(int i, int i2) {
        return requestCurrentDisplayedRange(Double.valueOf((double) i), Double.valueOf((double) i2));
    }

    public boolean requestCurrentDisplayedRange(int i, int i2, boolean z, boolean z2) {
        return requestCurrentDisplayedRange(Double.valueOf((double) i), Double.valueOf((double) i2), z, z2);
    }

    /* access modifiers changed from: 0000 */
    public String i() {
        if (k()) {
            return this.x;
        }
        if (this.A.size() == 0) {
            this.w = null;
        } else {
            int size = this.A.size();
            int i = 0;
            float f = BitmapDescriptorFactory.HUE_RED;
            for (int i2 = 0; i2 < size; i2++) {
                PointF pointF = new PointF();
                a(pointF, (String) this.A.get(i2));
                float f2 = pointF.x;
                if (f2 > f) {
                    List<String> list = this.A;
                    i = list.indexOf(list.get(i2));
                    f = f2;
                }
            }
            this.w = (String) this.A.get(i);
        }
        return this.w;
    }

    public String getFormattedString(Double d) {
        if (d == null) {
            return null;
        }
        int round = (int) Math.round(d.doubleValue());
        if (e(round)) {
            return (String) this.A.get(round);
        }
        return null;
    }

    private boolean e(int i) {
        return i >= 0 && i < this.A.size();
    }

    /* access modifiers changed from: 0000 */
    public void c(int i) {
        this.r = Double.valueOf(1.0d);
        r();
    }

    /* access modifiers changed from: 0000 */
    public double a(int i) {
        double ceil = Math.ceil(this.i.a);
        int i2 = (int) ceil;
        int intValue = this.j.getMinimum().intValue();
        if (i2 < intValue) {
            ceil = (double) intValue;
        }
        return !a(ceil, (double) i, this.i.b()) ? a(ceil, true) : ceil;
    }

    /* access modifiers changed from: 0000 */
    public boolean b(double d) {
        return Math.IEEEremainder(d, 2.0d) == 0.0d;
    }

    public void setMajorTickMarkValues(List<Double> list) {
        cx.b(this.b != null ? this.b.getContext().getString(R.string.CategoryAxisIgnoresCustomTickValues) : "Category axes ignore custom tick mark values");
    }

    /* access modifiers changed from: 0000 */
    public void A() {
        this.B.clear();
        this.u.c();
    }

    /* access modifiers changed from: 0000 */
    public df B() {
        return new u();
    }

    public void addSkipRange(Range<Double> range) {
        H();
    }

    public void addSkipRanges(List<? extends Range<Double>> list) {
        H();
    }

    private void H() {
        cx.b(this.b != null ? this.b.getContext().getString(R.string.CannotAddSkipToCategoryAxis) : "Cannot add a skip range to a category axis.");
    }
}
