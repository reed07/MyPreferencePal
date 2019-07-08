package org.apache.sanselan.common;

public abstract class RationalNumberUtilities extends Number {

    private static class Option {
        public final double error;
        public final RationalNumber rationalNumber;

        private Option(RationalNumber rationalNumber2, double d) {
            this.rationalNumber = rationalNumber2;
            this.error = d;
        }

        public static final Option factory(RationalNumber rationalNumber2, double d) {
            return new Option(rationalNumber2, Math.abs(rationalNumber2.doubleValue() - d));
        }

        public String toString() {
            return this.rationalNumber.toString();
        }
    }

    public static final RationalNumber getRationalNumber(double d) {
        boolean z;
        RationalNumber rationalNumber;
        RationalNumber rationalNumber2;
        RationalNumber rationalNumber3;
        if (d >= 2.147483647E9d) {
            return new RationalNumber(Integer.MAX_VALUE, 1);
        }
        if (d <= -2.147483647E9d) {
            return new RationalNumber(-2147483647, 1);
        }
        int i = 0;
        if (d < 0.0d) {
            d = Math.abs(d);
            z = true;
        } else {
            z = false;
        }
        if (d == 0.0d) {
            return new RationalNumber(0, 1);
        }
        if (d >= 1.0d) {
            int i2 = (int) d;
            if (((double) i2) < d) {
                rationalNumber = new RationalNumber(i2, 1);
                rationalNumber3 = new RationalNumber(i2 + 1, 1);
            } else {
                rationalNumber = new RationalNumber(i2 - 1, 1);
                rationalNumber3 = new RationalNumber(i2, 1);
            }
            rationalNumber2 = rationalNumber3;
        } else {
            int i3 = (int) (1.0d / d);
            if (1.0d / ((double) i3) < d) {
                rationalNumber = new RationalNumber(1, i3);
                rationalNumber2 = new RationalNumber(1, i3 - 1);
            } else {
                rationalNumber = new RationalNumber(1, i3 + 1);
                rationalNumber2 = new RationalNumber(1, i3);
            }
        }
        Option factory = Option.factory(rationalNumber, d);
        Option factory2 = Option.factory(rationalNumber2, d);
        Option option = factory.error < factory2.error ? factory : factory2;
        while (option.error > 1.0E-8d && i < 100) {
            RationalNumber factoryMethod = RationalNumber.factoryMethod(((long) factory.rationalNumber.numerator) + ((long) factory2.rationalNumber.numerator), ((long) factory.rationalNumber.divisor) + ((long) factory2.rationalNumber.divisor));
            Option factory3 = Option.factory(factoryMethod, d);
            if (d < factoryMethod.doubleValue()) {
                if (factory2.error <= factory3.error) {
                    break;
                }
                factory2 = factory3;
            } else if (factory.error <= factory3.error) {
                break;
            } else {
                factory = factory3;
            }
            if (factory3.error < option.error) {
                option = factory3;
            }
            i++;
        }
        return z ? option.rationalNumber.negate() : option.rationalNumber;
    }
}
