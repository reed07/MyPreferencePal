package com.samsung.android.sdk.healthdata;

import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.util.HashMap;
import java.util.UnknownFormatConversionException;

public abstract class HealthDataUnit {
    public static final HealthDataUnit CELSIUS = new a(0);
    public static final HealthDataUnit CENTIMETER = new b(0);
    public static final HealthDataUnit FAHRENHEIT = new c(0);
    public static final HealthDataUnit FOOT = new d(0);
    public static final HealthDataUnit GRAM = new e(0);
    public static final HealthDataUnit INCH = new f(0);
    public static final HealthDataUnit KELVIN = new g(0);
    public static final HealthDataUnit KILOGRAM = new h(0);
    public static final HealthDataUnit KILOMETER = new i(0);
    public static final HealthDataUnit METER = new j(0);
    public static final HealthDataUnit MILE = new k(0);
    public static final HealthDataUnit MILLIMETER = new l(0);
    public static final HealthDataUnit POUND = new m(0);
    public static final HealthDataUnit RANKINE = new n(0);
    public static final HealthDataUnit YARD = new o(0);
    private static final HashMap<String, HealthDataUnit> a = new HashMap<>();
    protected String mUnit;

    static class a extends HealthDataUnit {
        /* synthetic */ a(byte b) {
            this();
        }

        private a() {
            this.mUnit = "C";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("C")) {
                return d;
            }
            if (str.equalsIgnoreCase(Attributes.FRIDAY)) {
                return ((d * 9.0d) / 5.0d) + 32.0d;
            }
            if (str.equalsIgnoreCase("K")) {
                return d + 273.15d;
            }
            if (str.equalsIgnoreCase("R")) {
                return ((d + 273.15d) * 9.0d) / 5.0d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 3;
        }
    }

    static class b extends HealthDataUnit {
        /* synthetic */ b(byte b) {
            this();
        }

        private b() {
            this.mUnit = "cm";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("cm")) {
                return d;
            }
            if (str.equalsIgnoreCase("ft")) {
                return (d * 3.280839895013d) / 100.0d;
            }
            if (str.equalsIgnoreCase("in")) {
                return (d * 39.37007874016d) / 100.0d;
            }
            if (str.equalsIgnoreCase("m")) {
                return d / 100.0d;
            }
            if (str.equalsIgnoreCase("mm")) {
                return d * 10.0d;
            }
            if (str.equalsIgnoreCase("mi")) {
                return (d * 6.21371192E-4d) / 100.0d;
            }
            if (str.equalsIgnoreCase("km")) {
                return d / 100000.0d;
            }
            if (str.equalsIgnoreCase("yd")) {
                return (d * 1.09361329338d) / 100.0d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 1;
        }
    }

    static class c extends HealthDataUnit {
        /* synthetic */ c(byte b) {
            this();
        }

        private c() {
            this.mUnit = Attributes.FRIDAY;
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("C")) {
                return ((d - 32.0d) * 5.0d) / 9.0d;
            }
            if (str.equalsIgnoreCase(Attributes.FRIDAY)) {
                return d;
            }
            if (str.equalsIgnoreCase("K")) {
                return ((d + 459.67d) * 5.0d) / 9.0d;
            }
            if (str.equalsIgnoreCase("R")) {
                return d + 459.67d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 3;
        }
    }

    static class d extends HealthDataUnit {
        /* synthetic */ d(byte b) {
            this();
        }

        private d() {
            this.mUnit = "ft";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("cm")) {
                return (d / 3.280839895013d) * 100.0d;
            }
            if (str.equalsIgnoreCase("ft")) {
                return d;
            }
            if (str.equalsIgnoreCase("in")) {
                return d * 12.0d;
            }
            if (str.equalsIgnoreCase("m")) {
                return d / 3.280839895013d;
            }
            if (str.equalsIgnoreCase("mm")) {
                return (d / 3.280839895013d) * 1000.0d;
            }
            if (str.equalsIgnoreCase("mi")) {
                return d * 1.893939E-4d;
            }
            if (str.equalsIgnoreCase("km")) {
                return (d / 3.280839895013d) / 1000.0d;
            }
            if (str.equalsIgnoreCase("yd")) {
                return d * 0.3333333d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 1;
        }
    }

    static class e extends HealthDataUnit {
        /* synthetic */ e(byte b) {
            this();
        }

        private e() {
            this.mUnit = "g";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("g")) {
                return d;
            }
            if (str.equalsIgnoreCase("kg")) {
                return d / 1000.0d;
            }
            if (str.equalsIgnoreCase("lb")) {
                return (d * 2.2046215d) / 1000.0d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 2;
        }
    }

    static class f extends HealthDataUnit {
        /* synthetic */ f(byte b) {
            this();
        }

        private f() {
            this.mUnit = "in";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("cm")) {
                return (d / 39.37007874016d) * 100.0d;
            }
            if (str.equalsIgnoreCase("ft")) {
                return d / 12.0d;
            }
            if (str.equalsIgnoreCase("in")) {
                return d;
            }
            if (str.equalsIgnoreCase("m")) {
                return d / 39.37007874016d;
            }
            if (str.equalsIgnoreCase("mm")) {
                return (d / 39.37007874016d) * 1000.0d;
            }
            if (str.equalsIgnoreCase("mi")) {
                return d / 63360.0d;
            }
            if (str.equalsIgnoreCase("km")) {
                return (d / 39.37007874016d) / 1000.0d;
            }
            if (str.equalsIgnoreCase("yd")) {
                return d / 36.0d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 1;
        }
    }

    static class g extends HealthDataUnit {
        /* synthetic */ g(byte b) {
            this();
        }

        private g() {
            this.mUnit = "K";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("C")) {
                return d - 273.15d;
            }
            if (str.equalsIgnoreCase(Attributes.FRIDAY)) {
                return ((d * 9.0d) / 5.0d) - 459.67d;
            }
            if (str.equalsIgnoreCase("K")) {
                return d;
            }
            if (str.equalsIgnoreCase("R")) {
                return d * 1.8d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 3;
        }
    }

    static class h extends HealthDataUnit {
        /* synthetic */ h(byte b) {
            this();
        }

        private h() {
            this.mUnit = "kg";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("g")) {
                return d * 1000.0d;
            }
            if (str.equalsIgnoreCase("kg")) {
                return d;
            }
            if (str.equalsIgnoreCase("lb")) {
                return d / 0.45359237d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 2;
        }
    }

    static class i extends HealthDataUnit {
        /* synthetic */ i(byte b) {
            this();
        }

        private i() {
            this.mUnit = "km";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("cm")) {
                return d * 100000.0d;
            }
            if (str.equalsIgnoreCase("ft")) {
                return d * 3.280839895013d * 1000.0d;
            }
            if (str.equalsIgnoreCase("in")) {
                return d * 39.37007874016d * 1000.0d;
            }
            if (str.equalsIgnoreCase("m")) {
                return d * 1000.0d;
            }
            if (str.equalsIgnoreCase("mm")) {
                return d * 1000000.0d;
            }
            if (str.equalsIgnoreCase("mi")) {
                return d * 6.21371192E-4d * 1000.0d;
            }
            if (str.equalsIgnoreCase("km")) {
                return d;
            }
            if (str.equalsIgnoreCase("yd")) {
                return d * 1.09361329338d * 1000.0d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 1;
        }
    }

    static class j extends HealthDataUnit {
        /* synthetic */ j(byte b) {
            this();
        }

        private j() {
            this.mUnit = "m";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("cm")) {
                return d * 100.0d;
            }
            if (str.equalsIgnoreCase("ft")) {
                return d * 3.280839895013d;
            }
            if (str.equalsIgnoreCase("in")) {
                return d * 39.37007874016d;
            }
            if (str.equalsIgnoreCase("m")) {
                return d;
            }
            if (str.equalsIgnoreCase("mm")) {
                return d * 1000.0d;
            }
            if (str.equalsIgnoreCase("mi")) {
                return d * 6.21371192E-4d;
            }
            if (str.equalsIgnoreCase("km")) {
                return d / 1000.0d;
            }
            if (str.equalsIgnoreCase("yd")) {
                return d * 1.09361329338d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 1;
        }
    }

    static class k extends HealthDataUnit {
        /* synthetic */ k(byte b) {
            this();
        }

        private k() {
            this.mUnit = "mi";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("cm")) {
                return (d / 6.21371192E-4d) * 100.0d;
            }
            if (str.equalsIgnoreCase("ft")) {
                return d / 1.893939E-4d;
            }
            if (str.equalsIgnoreCase("in")) {
                return d * 63360.0d;
            }
            if (str.equalsIgnoreCase("m")) {
                return d / 6.21371192E-4d;
            }
            if (str.equalsIgnoreCase("mm")) {
                return (d / 6.21371192E-4d) * 1000.0d;
            }
            if (str.equalsIgnoreCase("mi")) {
                return d;
            }
            if (str.equalsIgnoreCase("km")) {
                return (d / 6.21371192E-4d) / 1000.0d;
            }
            if (str.equalsIgnoreCase("yd")) {
                return d * 1760.0d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 1;
        }
    }

    static class l extends HealthDataUnit {
        /* synthetic */ l(byte b) {
            this();
        }

        private l() {
            this.mUnit = "mm";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("cm")) {
                return d / 10.0d;
            }
            if (str.equalsIgnoreCase("ft")) {
                return (d * 3.280839895013d) / 1000.0d;
            }
            if (str.equalsIgnoreCase("in")) {
                return (d * 39.37007874016d) / 1000.0d;
            }
            if (str.equalsIgnoreCase("m")) {
                return d / 1000.0d;
            }
            if (str.equalsIgnoreCase("mm")) {
                return d;
            }
            if (str.equalsIgnoreCase("mi")) {
                return (d * 6.21371192E-4d) / 1000.0d;
            }
            if (str.equalsIgnoreCase("km")) {
                return d / 1000000.0d;
            }
            if (str.equalsIgnoreCase("yd")) {
                return (d * 1.09361329338d) / 1000.0d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 1;
        }
    }

    static class m extends HealthDataUnit {
        /* synthetic */ m(byte b) {
            this();
        }

        private m() {
            this.mUnit = "lb";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("g")) {
                return d * 1000.0d * 0.45359237d;
            }
            if (str.equalsIgnoreCase("kg")) {
                return d * 0.45359237d;
            }
            if (str.equalsIgnoreCase("lb")) {
                return d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 2;
        }
    }

    static class n extends HealthDataUnit {
        /* synthetic */ n(byte b) {
            this();
        }

        private n() {
            this.mUnit = "R";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("C")) {
                return ((d - 491.67d) * 5.0d) / 9.0d;
            }
            if (str.equalsIgnoreCase(Attributes.FRIDAY)) {
                return d - 459.67d;
            }
            if (str.equalsIgnoreCase("K")) {
                return (d * 5.0d) / 9.0d;
            }
            if (str.equalsIgnoreCase("R")) {
                return d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 3;
        }
    }

    static class o extends HealthDataUnit {
        /* synthetic */ o(byte b) {
            this();
        }

        private o() {
            this.mUnit = "yd";
        }

        public final double convertTo(double d, HealthDataUnit healthDataUnit) {
            String str = healthDataUnit.mUnit;
            if (str.equalsIgnoreCase("cm")) {
                return (d / 1.09361329338d) * 100.0d;
            }
            if (str.equalsIgnoreCase("ft")) {
                return d / 0.3333333d;
            }
            if (str.equalsIgnoreCase("in")) {
                return d * 36.0d;
            }
            if (str.equalsIgnoreCase("m")) {
                return d / 1.09361329338d;
            }
            if (str.equalsIgnoreCase("mm")) {
                return (d / 1.09361329338d) * 1000.0d;
            }
            if (str.equalsIgnoreCase("mi")) {
                return d / 1760.0d;
            }
            if (str.equalsIgnoreCase("km")) {
                return (d / 1.09361329338d) / 1000.0d;
            }
            if (str.equalsIgnoreCase("yd")) {
                return d;
            }
            throw new UnknownFormatConversionException("No conversion is defined");
        }

        public final boolean isCompatible(HealthDataUnit healthDataUnit) {
            return checkUnitType(healthDataUnit) == 1;
        }
    }

    public boolean isCompatible(HealthDataUnit healthDataUnit) {
        return false;
    }

    static {
        a.put(CELSIUS.getUnitName(), CELSIUS);
        a.put(CENTIMETER.getUnitName(), CENTIMETER);
        a.put(GRAM.getUnitName(), GRAM);
        a.put(KELVIN.getUnitName(), KELVIN);
        a.put(KILOGRAM.getUnitName(), KILOGRAM);
        a.put(KILOMETER.getUnitName(), KILOMETER);
        a.put(METER.getUnitName(), METER);
        a.put(MILLIMETER.getUnitName(), MILLIMETER);
        a.put(FAHRENHEIT.getUnitName(), FAHRENHEIT);
        a.put(FOOT.getUnitName(), FOOT);
        a.put(INCH.getUnitName(), INCH);
        a.put(MILE.getUnitName(), MILE);
        a.put(POUND.getUnitName(), POUND);
        a.put(RANKINE.getUnitName(), RANKINE);
        a.put(YARD.getUnitName(), YARD);
    }

    protected HealthDataUnit() {
    }

    public String getUnitName() {
        return this.mUnit;
    }

    public static HealthDataUnit valueOf(String str) {
        HealthDataUnit healthDataUnit = (HealthDataUnit) a.get(str);
        if (healthDataUnit != null) {
            return healthDataUnit;
        }
        StringBuilder sb = new StringBuilder("No unit conversion allowed for ");
        sb.append(str);
        throw new UnknownFormatConversionException(sb.toString());
    }

    public boolean matchUnitName(String str) {
        return this.mUnit.equals(str);
    }

    public static boolean isCompatible(String str, String str2) {
        try {
            return valueOf(str).isCompatible(valueOf(str2));
        } catch (UnknownFormatConversionException unused) {
            return false;
        } catch (IllegalArgumentException unused2) {
            return false;
        }
    }

    public double convertTo(double d2, HealthDataUnit healthDataUnit) {
        throw new UnknownFormatConversionException("No conversion is defined");
    }

    public final double convertTo(double d2, String str) {
        return convertTo(d2, valueOf(str));
    }

    public static double convert(double d2, String str, String str2) {
        return valueOf(str).convertTo(d2, str2);
    }

    public static void registerDataUnit(HealthDataUnit healthDataUnit) {
        if (healthDataUnit != null) {
            String str = healthDataUnit.mUnit;
            if (!a.containsKey(str)) {
                a.put(str, healthDataUnit);
                return;
            }
            StringBuilder sb = new StringBuilder("unit ");
            sb.append(str);
            sb.append(" already registered");
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException("improper unit conversion object");
    }

    /* access modifiers changed from: protected */
    public int checkUnitType(HealthDataUnit healthDataUnit) {
        if (healthDataUnit == CENTIMETER || healthDataUnit == FOOT || healthDataUnit == INCH || healthDataUnit == KILOMETER || healthDataUnit == METER || healthDataUnit == MILE || healthDataUnit == MILLIMETER || healthDataUnit == YARD) {
            return 1;
        }
        return (healthDataUnit == GRAM || healthDataUnit == KILOGRAM || healthDataUnit == POUND) ? 2 : 3;
    }
}
