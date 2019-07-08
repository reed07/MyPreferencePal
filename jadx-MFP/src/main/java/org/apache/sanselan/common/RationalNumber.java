package org.apache.sanselan.common;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class RationalNumber extends Number {
    private static final NumberFormat nf = DecimalFormat.getInstance();
    private static final long serialVersionUID = -1;
    public final int divisor;
    public final int numerator;

    public RationalNumber(int i, int i2) {
        this.numerator = i;
        this.divisor = i2;
    }

    public static final RationalNumber factoryMethod(long j, long j2) {
        if (j > 2147483647L || j < -2147483648L || j2 > 2147483647L || j2 < -2147483648L) {
            while (true) {
                if ((j > 2147483647L || j < -2147483648L || j2 > 2147483647L || j2 < -2147483648L) && Math.abs(j) > 1 && Math.abs(j2) > 1) {
                    j >>= 1;
                    j2 >>= 1;
                }
            }
            if (j2 == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid value, numerator: ");
                sb.append(j);
                sb.append(", divisor: ");
                sb.append(j2);
                throw new NumberFormatException(sb.toString());
            }
        }
        long gcd = gcd(j, j2);
        return new RationalNumber((int) (j / gcd), (int) (j2 / gcd));
    }

    private static long gcd(long j, long j2) {
        return j2 == 0 ? j : gcd(j2, j % j2);
    }

    public RationalNumber negate() {
        return new RationalNumber(-this.numerator, this.divisor);
    }

    public double doubleValue() {
        return ((double) this.numerator) / ((double) this.divisor);
    }

    public float floatValue() {
        return ((float) this.numerator) / ((float) this.divisor);
    }

    public int intValue() {
        return this.numerator / this.divisor;
    }

    public long longValue() {
        return ((long) this.numerator) / ((long) this.divisor);
    }

    public String toString() {
        int i = this.divisor;
        if (i == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid rational (");
            sb.append(this.numerator);
            sb.append("/");
            sb.append(this.divisor);
            sb.append(")");
            return sb.toString();
        }
        int i2 = this.numerator;
        if (i2 % i == 0) {
            return nf.format((long) (i2 / i));
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.numerator);
        sb2.append("/");
        sb2.append(this.divisor);
        sb2.append(" (");
        sb2.append(nf.format(((double) this.numerator) / ((double) this.divisor)));
        sb2.append(")");
        return sb2.toString();
    }

    public String toDisplayString() {
        if (this.numerator % this.divisor == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append("");
            sb.append(this.numerator / this.divisor);
            return sb.toString();
        }
        NumberFormat instance = DecimalFormat.getInstance();
        instance.setMaximumFractionDigits(3);
        return instance.format(((double) this.numerator) / ((double) this.divisor));
    }
}
