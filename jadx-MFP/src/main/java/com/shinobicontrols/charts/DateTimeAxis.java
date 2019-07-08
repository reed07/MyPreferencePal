package com.shinobicontrols.charts;

import android.graphics.PointF;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DateTimeAxis extends Axis<Date, DateFrequency> {
    private final SimpleDateFormat A = ((SimpleDateFormat) DateFormat.getDateInstance());
    private DateFormat B;
    private final PointF C = new PointF(1.0f, 1.0f);
    private boolean D;
    private long E = 0;
    private String F = "";
    private final Map<RepeatedTimePeriod, List<Range<Date>>> G = new LinkedHashMap();
    private final ad H = new ad(this);
    private final ac I = new ac();
    private Date J;

    /* access modifiers changed from: protected */
    public Date transformChartValueToUserValue(Date date) {
        return date;
    }

    /* access modifiers changed from: protected */
    public Date transformUserValueToChartValue(Date date) {
        return date;
    }

    /* access modifiers changed from: 0000 */
    public double x() {
        return 1.728E8d;
    }

    public DateTimeAxis() {
    }

    public DateTimeAxis(DateRange dateRange) {
        setDefaultRange(dateRange);
        a(dateRange.getMinimum());
    }

    /* access modifiers changed from: 0000 */
    public boolean isDataValid(Object obj) {
        return obj instanceof Date;
    }

    /* access modifiers changed from: 0000 */
    public double convertPoint(Object obj) {
        return translatePoint(obj);
    }

    private void a(Date date) {
        if (this.E == 0) {
            this.E = date.getTime();
            this.J = new Date(this.E);
        }
    }

    /* access modifiers changed from: 0000 */
    public double translatePoint(Object obj) {
        validateUserData(obj);
        return transformUserValueToInternal((Date) obj);
    }

    /* access modifiers changed from: 0000 */
    public double transformExternalValueToInternal(Date date) {
        long time = date.getTime();
        a(date);
        return (double) (time - this.E);
    }

    /* access modifiers changed from: 0000 */
    public Date transformInternalValueToExternal(double d) {
        if (d == Double.POSITIVE_INFINITY) {
            return new Date(Long.MAX_VALUE);
        }
        if (d == Double.NEGATIVE_INFINITY) {
            return new Date(Long.MIN_VALUE);
        }
        return new Date(((long) d) + this.E);
    }

    /* access modifiers changed from: 0000 */
    public Range<Date> createRange(Date date, Date date2) {
        return new DateRange(date, date2);
    }

    /* access modifiers changed from: 0000 */
    public String i() {
        I();
        if (k()) {
            return this.x;
        }
        if (!H()) {
            switch (((DateFrequency) this.r).b) {
                case SECONDS:
                case MINUTES:
                    this.w = " 00:00:00 ";
                    break;
                case HOURS:
                    this.w = " Mmm 00:00 ";
                    break;
                case MONTHS:
                    this.w = " Mmm 00 ";
                    break;
                case YEARS:
                    this.w = " 2000 ";
                    break;
                default:
                    this.w = " 00 Mmm ";
                    break;
            }
        } else {
            String format = this.B.format(Double.valueOf(this.i.a));
            String format2 = this.B.format(Double.valueOf(this.i.b));
            if (format2.length() > format.length()) {
                format = format2;
            }
            this.w = format;
        }
        return this.w;
    }

    public DateFormat getLabelFormat() {
        return this.B;
    }

    public void setLabelFormat(DateFormat dateFormat) {
        this.B = dateFormat;
    }

    public String getFormattedString(Date date) {
        return H() ? this.B.format(date) : this.A.format(date);
    }

    private boolean H() {
        return this.B != null;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0245 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0264  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void c(int r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = 0
            r0.D = r1
            java.lang.Object r2 = r0.r
            r3 = 1
            if (r2 != 0) goto L_0x0013
            com.shinobicontrols.charts.DateFrequency r2 = new com.shinobicontrols.charts.DateFrequency
            com.shinobicontrols.charts.DateFrequency$Denomination r4 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r2.<init>(r3, r4)
            r0.r = r2
        L_0x0013:
            java.lang.Object r2 = r0.s
            if (r2 != 0) goto L_0x0020
            com.shinobicontrols.charts.DateFrequency r2 = new com.shinobicontrols.charts.DateFrequency
            com.shinobicontrols.charts.DateFrequency$Denomination r4 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r2.<init>(r3, r4)
            r0.s = r2
        L_0x0020:
            com.shinobicontrols.charts.NumberRange r2 = r0.i
            double r4 = r2.b()
            r6 = 4666723172467343360(0x40c3880000000000, double:10000.0)
            double r4 = r4 / r6
            r6 = 4607632778762754458(0x3ff199999999999a, double:1.1)
            r2 = 14
            r8 = 12
            r9 = 2
            r10 = 7
            r11 = 3
            r12 = 6
            r13 = 5
            r14 = 30
            r15 = 15
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x004d
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r4.a(r3, r5)
            goto L_0x01f1
        L_0x004d:
            r6 = 4625337554797854720(0x4030800000000000, double:16.5)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x0061
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r4.a(r15, r5)
            goto L_0x01f1
        L_0x0061:
            r6 = 4629841154425225216(0x4040800000000000, double:33.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x007e
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r4.a(r14, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r4.a(r15, r5)
            goto L_0x01f1
        L_0x007e:
            r6 = 4634344754052595712(0x4050800000000000, double:66.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x009b
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r4.a(r3, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r4.a(r14, r5)
            goto L_0x01f1
        L_0x009b:
            r6 = 4651919347910967296(0x408ef00000000000, double:990.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x00b8
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r4.a(r15, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r4.a(r13, r5)
            goto L_0x01f1
        L_0x00b8:
            r6 = 4656422947538337792(0x409ef00000000000, double:1980.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x00d5
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r4.a(r14, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r4.a(r15, r5)
            goto L_0x01f1
        L_0x00d5:
            r6 = 4660926547165708288(0x40aef00000000000, double:3960.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x00f2
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r4.a(r3, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r4.a(r14, r5)
            goto L_0x01f1
        L_0x00f2:
            r6 = 4672260313024823296(0x40d7340000000000, double:23760.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x010f
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r4.a(r12, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r4.a(r3, r5)
            goto L_0x01f1
        L_0x010f:
            r6 = 4676763912652193792(0x40e7340000000000, double:47520.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x012c
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r4.a(r8, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r4.a(r12, r5)
            goto L_0x01f1
        L_0x012c:
            r6 = 4681267512279564289(0x40f7340000000001, double:95040.00000000001)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x0149
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r4.a(r3, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r4.a(r8, r5)
            goto L_0x01f1
        L_0x0149:
            r6 = 4693961923778052096(0x41244d8000000000, double:665280.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x0166
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r4.a(r10, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r4.a(r3, r5)
            goto L_0x01f1
        L_0x0166:
            r6 = 4698465523405422592(0x41344d8000000000, double:1330560.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x0182
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r4.a(r2, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r4.a(r10, r5)
            goto L_0x01f1
        L_0x0182:
            r6 = 4703581413570510849(0x41467a6000000001, double:2946240.0000000005)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x019e
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r4.a(r3, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r4.a(r10, r5)
            goto L_0x01f1
        L_0x019e:
            r6 = 4711006862269480960(0x4160dbc800000000, double:8838720.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x01ba
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r4.a(r11, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r4.a(r3, r5)
            goto L_0x01f1
        L_0x01ba:
            r6 = 4694574214315769856(0x41267a6000000000, double:736560.0)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x01d6
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r4.a(r12, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r4.a(r11, r5)
            goto L_0x01f1
        L_0x01d6:
            r6 = 4719927958167355392(0x41808d7880000000, double:3.471336E7)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x01f3
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r3, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r4.a(r11, r5)
        L_0x01f1:
            r4 = 1
            goto L_0x0223
        L_0x01f3:
            r6 = 4724431557794725888(0x41908d7880000000, double:6.942672E7)
            int r16 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r16 >= 0) goto L_0x0210
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r9, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r3, r5)
            r4 = 2
            goto L_0x0223
        L_0x0210:
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r13, r5)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r5 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r9, r5)
            r4 = 5
        L_0x0223:
            r19.r()
            com.shinobicontrols.charts.NumberRange r5 = r0.i
            double r5 = r5.b()
            java.lang.Object r7 = r0.r
            com.shinobicontrols.charts.DateFrequency r7 = (com.shinobicontrols.charts.DateFrequency) r7
            long r14 = r7.a()
            double r14 = (double) r14
            double r5 = r5 / r14
            double r5 = java.lang.Math.floor(r5)
            int r5 = (int) r5
            android.graphics.PointF r6 = r0.C
            r7 = r20
            boolean r5 = r0.a(r5, r7, r6)
            if (r5 == 0) goto L_0x0264
            java.lang.Object r2 = r0.r
            com.shinobicontrols.charts.DateFrequency r2 = (com.shinobicontrols.charts.DateFrequency) r2
            com.shinobicontrols.charts.DateFrequency$Denomination r4 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            boolean r2 = r2.b(r3, r4)
            if (r2 != 0) goto L_0x0261
            java.lang.Object r2 = r0.r
            com.shinobicontrols.charts.DateFrequency r2 = (com.shinobicontrols.charts.DateFrequency) r2
            com.shinobicontrols.charts.DateFrequency$Denomination r4 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            boolean r2 = r2.b(r3, r4)
            if (r2 == 0) goto L_0x025e
            goto L_0x0261
        L_0x025e:
            r0.D = r1
            goto L_0x0263
        L_0x0261:
            r0.D = r3
        L_0x0263:
            return
        L_0x0264:
            int[] r5 = com.shinobicontrols.charts.DateTimeAxis.AnonymousClass1.a
            java.lang.Object r6 = r0.r
            com.shinobicontrols.charts.DateFrequency r6 = (com.shinobicontrols.charts.DateFrequency) r6
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = r6.b
            int r6 = r6.ordinal()
            r5 = r5[r6]
            switch(r5) {
                case 1: goto L_0x042d;
                case 2: goto L_0x03c4;
                case 3: goto L_0x0379;
                case 4: goto L_0x032e;
                case 5: goto L_0x02c6;
                case 6: goto L_0x027b;
                default: goto L_0x0275;
            }
        L_0x0275:
            r6 = 30
            r15 = 15
            goto L_0x0482
        L_0x027b:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            int r5 = r5.a
            if (r5 == r3) goto L_0x02ae
            if (r5 == r10) goto L_0x029b
            if (r5 == r2) goto L_0x0288
            goto L_0x02c0
        L_0x0288:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r5.a(r3, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r5.a(r10, r6)
            goto L_0x02c0
        L_0x029b:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r5.a(r2, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r5.a(r10, r6)
            goto L_0x02c0
        L_0x02ae:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r5.a(r10, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r5.a(r3, r6)
        L_0x02c0:
            r6 = 30
            r15 = 15
            goto L_0x0482
        L_0x02c6:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            int r5 = r5.a
            if (r4 == r13) goto L_0x0313
            switch(r4) {
                case 1: goto L_0x02f8;
                case 2: goto L_0x02d7;
                default: goto L_0x02d1;
            }
        L_0x02d1:
            r6 = 30
            r15 = 15
            goto L_0x0482
        L_0x02d7:
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            double r14 = (double) r5
            r17 = 4612811918334230528(0x4004000000000000, double:2.5)
            double r14 = r14 * r17
            int r6 = (int) r14
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r6, r14)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            int r5 = r5 / 2
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r5, r6)
            r4 = 5
            r6 = 30
            r15 = 15
            goto L_0x0482
        L_0x02f8:
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            int r6 = r5 * 2
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r6, r14)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r5, r6)
            r4 = 2
            r6 = 30
            r15 = 15
            goto L_0x0482
        L_0x0313:
            java.lang.Object r4 = r0.r
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            int r6 = r5 * 2
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r6, r14)
            java.lang.Object r4 = r0.s
            com.shinobicontrols.charts.DateFrequency r4 = (com.shinobicontrols.charts.DateFrequency) r4
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r4.a(r5, r6)
            r4 = 1
            r6 = 30
            r15 = 15
            goto L_0x0482
        L_0x032e:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            int r5 = r5.a
            if (r5 == r3) goto L_0x0361
            if (r5 == r11) goto L_0x034e
            if (r5 == r12) goto L_0x033b
            goto L_0x0373
        L_0x033b:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.YEARS
            r5.a(r3, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r5.a(r11, r6)
            goto L_0x0373
        L_0x034e:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r5.a(r12, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r5.a(r11, r6)
            goto L_0x0373
        L_0x0361:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r5.a(r11, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MONTHS
            r5.a(r3, r6)
        L_0x0373:
            r6 = 30
            r15 = 15
            goto L_0x0482
        L_0x0379:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            int r5 = r5.a
            if (r5 == r3) goto L_0x03ac
            if (r5 == r12) goto L_0x0399
            if (r5 == r8) goto L_0x0386
            goto L_0x03be
        L_0x0386:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.DAYS
            r5.a(r3, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r5.a(r8, r6)
            goto L_0x03be
        L_0x0399:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r5.a(r8, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r5.a(r12, r6)
            goto L_0x03be
        L_0x03ac:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r5.a(r12, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r5.a(r3, r6)
        L_0x03be:
            r6 = 30
            r15 = 15
            goto L_0x0482
        L_0x03c4:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            int r5 = r5.a
            if (r5 == r3) goto L_0x0416
            if (r5 == r13) goto L_0x0401
            r6 = 15
            if (r5 == r6) goto L_0x03ea
            r6 = 30
            if (r5 == r6) goto L_0x03d7
            goto L_0x0428
        L_0x03d7:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.HOURS
            r5.a(r3, r14)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r5.a(r6, r14)
            goto L_0x0428
        L_0x03ea:
            r6 = 30
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r5.a(r6, r14)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r14 = 15
            r5.a(r14, r6)
            goto L_0x0428
        L_0x0401:
            r14 = 15
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r5.a(r14, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r5.a(r13, r6)
            goto L_0x0428
        L_0x0416:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r5.a(r13, r6)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r6 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r5.a(r3, r6)
        L_0x0428:
            r6 = 30
            r15 = 15
            goto L_0x0482
        L_0x042d:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            int r5 = r5.a
            if (r5 == r3) goto L_0x046c
            r6 = 15
            if (r5 == r6) goto L_0x0455
            r6 = 30
            if (r5 == r6) goto L_0x0440
            r15 = 15
            goto L_0x0482
        L_0x0440:
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.MINUTES
            r5.a(r3, r14)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r5.a(r6, r14)
            r15 = 15
            goto L_0x0482
        L_0x0455:
            r6 = 30
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r5.a(r6, r14)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r15 = 15
            r5.a(r15, r14)
            goto L_0x0482
        L_0x046c:
            r6 = 30
            r15 = 15
            java.lang.Object r5 = r0.r
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r5.a(r15, r14)
            java.lang.Object r5 = r0.s
            com.shinobicontrols.charts.DateFrequency r5 = (com.shinobicontrols.charts.DateFrequency) r5
            com.shinobicontrols.charts.DateFrequency$Denomination r14 = com.shinobicontrols.charts.DateFrequency.Denomination.SECONDS
            r5.a(r3, r14)
        L_0x0482:
            r14 = 30
            goto L_0x0223
        */
        throw new UnsupportedOperationException("Method not decompiled: com.shinobicontrols.charts.DateTimeAxis.c(int):void");
    }

    /* access modifiers changed from: 0000 */
    public double a(int i) {
        return this.H.a((DateFrequency) this.r);
    }

    /* access modifiers changed from: 0000 */
    public double b(int i) {
        return this.H.b((DateFrequency) this.s);
    }

    /* access modifiers changed from: 0000 */
    public boolean b(double d) {
        return this.H.c(d, (DateFrequency) this.r);
    }

    /* access modifiers changed from: 0000 */
    public double a(double d, boolean z) {
        return (double) this.H.a(d, (DateFrequency) (z ? this.r : this.s));
    }

    /* access modifiers changed from: 0000 */
    public double transformExternalFrequencyToInternal(DateFrequency dateFrequency) {
        return (double) dateFrequency.a();
    }

    private void I() {
        if (H()) {
            this.u.c();
            return;
        }
        String J2 = J();
        this.A.applyPattern(J2);
        if (J2 != this.F) {
            this.u.c();
            this.F = J2;
        }
    }

    private String J() {
        switch (((DateFrequency) this.r).b) {
            case SECONDS:
            case MINUTES:
                return "HH:mm:ss";
            case HOURS:
                return "EEE HH:mm";
            case MONTHS:
                return "MMM yy";
            case YEARS:
                return "yyyy";
            default:
                return "dd MMM";
        }
    }

    /* access modifiers changed from: 0000 */
    public void setMajorTickFrequencyInternal(DateFrequency dateFrequency) {
        if (dateFrequency == null) {
            this.p = null;
            return;
        }
        if (dateFrequency.a > 0) {
            this.p = dateFrequency;
        } else {
            cx.b(this.b != null ? this.b.getContext().getString(R.string.DateTimeAxisInvalidDateFrequency) : "The DateFrequency is invalid and will be ignored");
            this.p = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void setMinorTickFrequencyInternal(DateFrequency dateFrequency) {
        if (dateFrequency == null) {
            this.q = null;
            return;
        }
        if (dateFrequency.a > 0) {
            this.q = dateFrequency;
        } else {
            cx.b(this.b != null ? this.b.getContext().getString(R.string.DateTimeAxisInvalidDateFrequency) : "The DateFrequency is invalid and will be ignored");
            this.q = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public void p() {
        if (this.p != null) {
            this.r = ((DateFrequency) this.p).clone();
        }
    }

    /* access modifiers changed from: 0000 */
    public void q() {
        if (l() && this.q != null) {
            this.s = ((DateFrequency) this.q).clone();
        }
    }

    /* access modifiers changed from: 0000 */
    public Date getDefaultBaseline() {
        return new Date(this.E);
    }

    /* access modifiers changed from: 0000 */
    public Date applyMappingForSkipRangesToUserValue(Date date) {
        return new Date((long) this.y.a((double) date.getTime()));
    }

    /* access modifiers changed from: 0000 */
    public double convertUserValueTypeToInternalDataType(Object obj) {
        return (double) ((Date) obj).getTime();
    }

    /* access modifiers changed from: 0000 */
    public Date removeMappingForSkipRangesFromChartValue(Date date) {
        return new Date((long) this.z.a((double) date.getTime()));
    }

    public void addRepeatedSkipRange(RepeatedTimePeriod repeatedTimePeriod) {
        if (this.b != null) {
            this.b.s();
        }
        if (repeatedTimePeriod == null) {
            cx.b(this.b != null ? this.b.getContext().getString(R.string.CannotAddNullRepeatedSkipRange) : "Cannot add a null repeated skip range.");
            return;
        }
        List a = this.I.a(repeatedTimePeriod, (Range<Date>) K());
        b(a);
        this.G.put(repeatedTimePeriod, a);
        if (!a.isEmpty()) {
            D();
        }
    }

    private void b(List<Range<Date>> list) {
        ArrayList arrayList = new ArrayList();
        for (Range range : list) {
            if (!b(range)) {
                arrayList.add(range);
                c(range);
            }
        }
        list.removeAll(arrayList);
    }

    private void c(Range<Date> range) {
        String str;
        String date = ((Date) range.getMinimum()).toString();
        String date2 = ((Date) range.getMaximum()).toString();
        if (this.b != null) {
            str = this.b.getContext().getString(R.string.CannotAddCalculatedUndefinedOrEmptySkip, new Object[]{date, date2});
        } else {
            str = String.format("Calculated skip range with min: %1$s and max: %2$s is invalid: cannot be added as it has zero or negative span", new Object[]{date, date2});
        }
        cx.b(str);
    }

    private DateRange K() {
        return new DateRange((Date) transformInternalValueToUser(this.v.d()), (Date) transformInternalValueToUser(this.v.f()));
    }

    public void removeRepeatedSkipRange(RepeatedTimePeriod repeatedTimePeriod) {
        if (((List) this.G.remove(repeatedTimePeriod)) != null) {
            D();
        }
    }

    public void removeAllRepeatedSkipRanges() {
        ArrayList<RepeatedTimePeriod> arrayList = new ArrayList<>(this.G.keySet());
        if (!arrayList.isEmpty()) {
            for (RepeatedTimePeriod remove : arrayList) {
                this.G.remove(remove);
            }
            D();
        }
    }

    public List<RepeatedTimePeriod> getRepeatedSkipRanges() {
        return Collections.unmodifiableList(new ArrayList(this.G.keySet()));
    }

    /* access modifiers changed from: 0000 */
    public List<Range<Date>> E() {
        ArrayList arrayList = new ArrayList(super.E());
        for (List addAll : this.G.values()) {
            arrayList.addAll(addAll);
        }
        return arrayList;
    }

    /* access modifiers changed from: 0000 */
    public void g() {
        if (!this.G.isEmpty()) {
            DateRange K = K();
            if (this.I.a(K)) {
                for (RepeatedTimePeriod repeatedTimePeriod : this.G.keySet()) {
                    List a = this.I.a(repeatedTimePeriod, (Range<Date>) K);
                    b(a);
                    this.G.put(repeatedTimePeriod, a);
                }
                D();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(v vVar) {
        super.a(vVar);
        if (vVar != null && !this.G.isEmpty()) {
            vVar.s();
        }
    }

    /* access modifiers changed from: 0000 */
    public void D() {
        this.H.a();
        super.D();
    }

    /* access modifiers changed from: 0000 */
    public double G() {
        Date date = this.J;
        if (date == null) {
            return 0.0d;
        }
        return transformUserValueToInternal(date);
    }

    public void disableTickMarkCaching(boolean z) {
        this.H.a = z;
    }

    public boolean isTickMarkCachingDisabled() {
        return this.H.a;
    }
}
