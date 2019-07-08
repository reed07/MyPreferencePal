package android.support.v4.text;

import java.util.Locale;

public final class TextDirectionHeuristicsCompat {
    public static final TextDirectionHeuristicCompat ANYRTL_LTR = new TextDirectionHeuristicInternal(AnyStrong.INSTANCE_RTL, false);
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, false);
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL = new TextDirectionHeuristicInternal(FirstStrong.INSTANCE, true);
    public static final TextDirectionHeuristicCompat LOCALE = TextDirectionHeuristicLocale.INSTANCE;
    public static final TextDirectionHeuristicCompat LTR = new TextDirectionHeuristicInternal(null, false);
    public static final TextDirectionHeuristicCompat RTL = new TextDirectionHeuristicInternal(null, true);

    private static class AnyStrong implements TextDirectionAlgorithm {
        static final AnyStrong INSTANCE_LTR = new AnyStrong(false);
        static final AnyStrong INSTANCE_RTL = new AnyStrong(true);
        private final boolean mLookForRtl;

        public int checkRtl(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            boolean z = false;
            while (i < i3) {
                switch (TextDirectionHeuristicsCompat.isRtlText(Character.getDirectionality(charSequence.charAt(i)))) {
                    case 0:
                        if (!this.mLookForRtl) {
                            z = true;
                            break;
                        } else {
                            return 0;
                        }
                    case 1:
                        if (this.mLookForRtl) {
                            z = true;
                            break;
                        } else {
                            return 1;
                        }
                }
                i++;
            }
            if (z) {
                return this.mLookForRtl ? 1 : 0;
            }
            return 2;
        }

        private AnyStrong(boolean z) {
            this.mLookForRtl = z;
        }
    }

    private static class FirstStrong implements TextDirectionAlgorithm {
        static final FirstStrong INSTANCE = new FirstStrong();

        public int checkRtl(CharSequence charSequence, int i, int i2) {
            int i3 = i2 + i;
            int i4 = 2;
            while (i < i3 && i4 == 2) {
                i4 = TextDirectionHeuristicsCompat.isRtlTextOrFormat(Character.getDirectionality(charSequence.charAt(i)));
                i++;
            }
            return i4;
        }

        private FirstStrong() {
        }
    }

    private interface TextDirectionAlgorithm {
        int checkRtl(CharSequence charSequence, int i, int i2);
    }

    private static abstract class TextDirectionHeuristicImpl implements TextDirectionHeuristicCompat {
        private final TextDirectionAlgorithm mAlgorithm;

        /* access modifiers changed from: protected */
        public abstract boolean defaultIsRtl();

        TextDirectionHeuristicImpl(TextDirectionAlgorithm textDirectionAlgorithm) {
            this.mAlgorithm = textDirectionAlgorithm;
        }

        public boolean isRtl(CharSequence charSequence, int i, int i2) {
            if (charSequence == null || i < 0 || i2 < 0 || charSequence.length() - i2 < i) {
                throw new IllegalArgumentException();
            } else if (this.mAlgorithm == null) {
                return defaultIsRtl();
            } else {
                return doCheck(charSequence, i, i2);
            }
        }

        private boolean doCheck(CharSequence charSequence, int i, int i2) {
            switch (this.mAlgorithm.checkRtl(charSequence, i, i2)) {
                case 0:
                    return true;
                case 1:
                    return false;
                default:
                    return defaultIsRtl();
            }
        }
    }

    private static class TextDirectionHeuristicInternal extends TextDirectionHeuristicImpl {
        private final boolean mDefaultIsRtl;

        TextDirectionHeuristicInternal(TextDirectionAlgorithm textDirectionAlgorithm, boolean z) {
            super(textDirectionAlgorithm);
            this.mDefaultIsRtl = z;
        }

        /* access modifiers changed from: protected */
        public boolean defaultIsRtl() {
            return this.mDefaultIsRtl;
        }
    }

    private static class TextDirectionHeuristicLocale extends TextDirectionHeuristicImpl {
        static final TextDirectionHeuristicLocale INSTANCE = new TextDirectionHeuristicLocale();

        TextDirectionHeuristicLocale() {
            super(null);
        }

        /* access modifiers changed from: protected */
        public boolean defaultIsRtl() {
            return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
        }
    }

    static int isRtlText(int i) {
        switch (i) {
            case 0:
                return 1;
            case 1:
            case 2:
                return 0;
            default:
                return 2;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0009, code lost:
        return 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000b, code lost:
        return 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int isRtlTextOrFormat(int r0) {
        /*
            switch(r0) {
                case 0: goto L_0x000a;
                case 1: goto L_0x0008;
                case 2: goto L_0x0008;
                default: goto L_0x0003;
            }
        L_0x0003:
            switch(r0) {
                case 14: goto L_0x000a;
                case 15: goto L_0x000a;
                case 16: goto L_0x0008;
                case 17: goto L_0x0008;
                default: goto L_0x0006;
            }
        L_0x0006:
            r0 = 2
            return r0
        L_0x0008:
            r0 = 0
            return r0
        L_0x000a:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v4.text.TextDirectionHeuristicsCompat.isRtlTextOrFormat(int):int");
    }

    private TextDirectionHeuristicsCompat() {
    }
}
