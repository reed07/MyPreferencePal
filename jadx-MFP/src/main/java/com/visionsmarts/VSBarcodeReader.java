package com.visionsmarts;

public class VSBarcodeReader {

    public static class DecoderValues {
        public int evaluationDays;
        public int left;
        public Point lineEnd = new Point();
        public Point lineStart = new Point();
        public int right;
        public int type;
    }

    public static class Point {
    }

    public static native int VSinit();

    public static native String decodeNextImage(byte[] bArr, int i, int i2, DecoderValues decoderValues);

    public static native int reset();

    public static native int setBlurryAcceptanceThresholdWithAF(double d);

    static {
        System.loadLibrary("VSBarcodeReader");
    }

    public static String format(String str, int i) {
        if (i != 4) {
            switch (i) {
                case 1:
                    if (str.substring(0, 1).equals("0")) {
                        return String.format("%s-%s-%s-%s", new Object[]{str.substring(1, 2), str.substring(2, 7), str.substring(7, 12), str.substring(12, 13)});
                    }
                    return String.format("%s-%s-%s", new Object[]{str.substring(0, 1), str.substring(1, 7), str.substring(7, 13)});
                case 2:
                    return String.format("%s-%s", new Object[]{str.substring(0, 4), str.substring(4, 8)});
                default:
                    return str;
            }
        } else {
            return String.format("%s-%s-%s", new Object[]{str.substring(0, 1), str.substring(1, 7), str.substring(7, 8)});
        }
    }
}
