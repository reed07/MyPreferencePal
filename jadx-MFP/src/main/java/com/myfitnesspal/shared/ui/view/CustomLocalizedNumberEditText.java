package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import com.uacf.core.util.Strings;
import java.text.DecimalFormatSymbols;

public class CustomLocalizedNumberEditText extends StyledEditText {
    private boolean decimal;
    private boolean sign;

    public static class CustomKeyListener extends NumberKeyListener {
        private static final char[][] CHARS = {new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'}, new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-'}, new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '.', ','}, new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '.', ','}};
        private static final int DECIMAL = 2;
        private static final int SIGN = 1;
        private char[] accepted;
        private boolean decimal;
        private boolean isDotSeparator;
        private boolean sign;

        /* access modifiers changed from: protected */
        public char[] getAcceptedChars() {
            return this.accepted;
        }

        public CustomKeyListener() {
            this(false, false);
        }

        public CustomKeyListener(boolean z, boolean z2) {
            this.sign = z;
            this.decimal = z2;
            this.isDotSeparator = isDotSeparator();
            this.accepted = CHARS[z | (z2 ? (char) 2 : 0)];
        }

        public static CustomKeyListener getInstance(String str) {
            CustomKeyListener customKeyListener = new CustomKeyListener();
            customKeyListener.accepted = new char[str.length()];
            str.getChars(0, str.length(), customKeyListener.accepted, 0);
            return customKeyListener;
        }

        public int getInputType() {
            int i = this.sign ? 4098 : 2;
            return this.decimal ? i | 8192 : i;
        }

        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            int i5;
            CharSequence charSequence2;
            int i6;
            char c;
            Spanned spanned2 = spanned;
            int i7 = i3;
            CharSequence filter = super.filter(charSequence, i, i2, spanned, i3, i4);
            char c2 = '.';
            char c3 = this.isDotSeparator ? ',' : '.';
            char c4 = this.isDotSeparator ? '.' : ',';
            if (!this.sign && !this.decimal) {
                return filter.toString().replace(c3, c4);
            }
            if (filter != null) {
                i6 = filter.length();
                charSequence2 = filter;
                i5 = 0;
            } else {
                charSequence2 = charSequence;
                i5 = i;
                i6 = i2;
            }
            int length = spanned.length();
            int i8 = 0;
            int i9 = -1;
            int i10 = -1;
            while (true) {
                c = '-';
                if (i8 >= i7) {
                    break;
                }
                char charAt = spanned2.charAt(i8);
                if (charAt == '-') {
                    i9 = i8;
                } else if (charAt == '.' || charAt == ',') {
                    i10 = i8;
                }
                i8++;
            }
            int i11 = i4;
            while (i11 < length) {
                char charAt2 = spanned2.charAt(i11);
                if (charAt2 == '-') {
                    return "";
                }
                if (charAt2 == c2 || charAt2 == ',') {
                    i10 = i11;
                }
                i11++;
                c2 = '.';
            }
            int i12 = i6 - 1;
            SpannableStringBuilder spannableStringBuilder = null;
            while (true) {
                boolean z = true;
                if (i12 >= i5) {
                    char charAt3 = charSequence2.charAt(i12);
                    if (charAt3 != c) {
                        if (charAt3 != '.') {
                            if (charAt3 != ',') {
                                z = false;
                            }
                        }
                        if (i10 < 0) {
                            i10 = i12;
                            z = false;
                        }
                    } else if (i12 == i5 && i7 == 0) {
                        if (i9 < 0) {
                            i9 = i12;
                            z = false;
                        }
                    }
                    if (z) {
                        if (i6 == i5 + 1) {
                            return "";
                        }
                        if (spannableStringBuilder == null) {
                            spannableStringBuilder = new SpannableStringBuilder(charSequence2, i5, i6);
                        }
                        spannableStringBuilder.delete(i12 - i5, (i12 + 1) - i5);
                    }
                    i12--;
                    c = '-';
                } else if (spannableStringBuilder != null) {
                    return spannableStringBuilder.toString().replace(c3, c4);
                } else {
                    if (filter != null) {
                        return filter.toString().replace(c3, c4);
                    }
                    if (spannableStringBuilder == null && filter == null && charSequence2 != null && charSequence2.length() == 1) {
                        return charSequence2.toString().replace(c3, c4);
                    }
                    return null;
                }
            }
        }

        private boolean isDotSeparator() {
            return Strings.equals((Object) Character.valueOf(new DecimalFormatSymbols().getDecimalSeparator()), (Object) ".");
        }
    }

    public CustomLocalizedNumberEditText(Context context) {
        super(context);
        init(true, true);
    }

    public CustomLocalizedNumberEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(true, true);
    }

    public CustomLocalizedNumberEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(true, true);
    }

    public CustomLocalizedNumberEditText(Context context, AttributeSet attributeSet, int i, boolean z, boolean z2) {
        super(context, attributeSet, i);
        init(z, z2);
    }

    private void init(boolean z, boolean z2) {
        this.sign = z;
        this.decimal = z2;
        setKeyListener(new CustomKeyListener(z, z2));
    }

    public void setAllowSign(boolean z) {
        this.sign = z;
        init(this.sign, this.decimal);
    }

    public void setAllowDecimal(boolean z) {
        this.decimal = z;
        init(this.sign, this.decimal);
    }
}
