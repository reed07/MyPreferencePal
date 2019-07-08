package net.minidev.json;

import java.io.IOException;

class JStylerObj {
    public static final Escape4Web ESCAPE4Web = new Escape4Web();
    public static final EscapeLT ESCAPE_LT = new EscapeLT();
    public static final MPAgressive MP_AGGRESIVE = new MPAgressive();
    public static final MPSimple MP_SIMPLE = new MPSimple();
    public static final MPTrue MP_TRUE = new MPTrue();

    private static class Escape4Web implements StringProtector {
        private Escape4Web() {
        }

        public void escape(String str, Appendable appendable) {
            try {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (charAt == '\"') {
                        appendable.append("\\\"");
                    } else if (charAt == '/') {
                        appendable.append("\\/");
                    } else if (charAt != '\\') {
                        switch (charAt) {
                            case 8:
                                appendable.append("\\b");
                                break;
                            case 9:
                                appendable.append("\\t");
                                break;
                            case 10:
                                appendable.append("\\n");
                                break;
                            default:
                                switch (charAt) {
                                    case 12:
                                        appendable.append("\\f");
                                        break;
                                    case 13:
                                        appendable.append("\\r");
                                        break;
                                    default:
                                        if ((charAt >= 0 && charAt <= 31) || ((charAt >= 127 && charAt <= 159) || (charAt >= 8192 && charAt <= 8447))) {
                                            appendable.append("\\u");
                                            String str2 = "0123456789ABCDEF";
                                            appendable.append(str2.charAt((charAt >> 12) & 15));
                                            appendable.append(str2.charAt((charAt >> 8) & 15));
                                            appendable.append(str2.charAt((charAt >> 4) & 15));
                                            appendable.append(str2.charAt((charAt >> 0) & 15));
                                            break;
                                        } else {
                                            appendable.append(charAt);
                                            break;
                                        }
                                }
                        }
                    } else {
                        appendable.append("\\\\");
                    }
                }
            } catch (IOException unused) {
                throw new RuntimeException("Impossible Error");
            }
        }
    }

    private static class EscapeLT implements StringProtector {
        private EscapeLT() {
        }

        public void escape(String str, Appendable appendable) {
            try {
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    if (charAt == '\"') {
                        appendable.append("\\\"");
                    } else if (charAt != '\\') {
                        switch (charAt) {
                            case 8:
                                appendable.append("\\b");
                                break;
                            case 9:
                                appendable.append("\\t");
                                break;
                            case 10:
                                appendable.append("\\n");
                                break;
                            default:
                                switch (charAt) {
                                    case 12:
                                        appendable.append("\\f");
                                        break;
                                    case 13:
                                        appendable.append("\\r");
                                        break;
                                    default:
                                        if ((charAt >= 0 && charAt <= 31) || ((charAt >= 127 && charAt <= 159) || (charAt >= 8192 && charAt <= 8447))) {
                                            appendable.append("\\u");
                                            String str2 = "0123456789ABCDEF";
                                            appendable.append(str2.charAt((charAt >> 12) & 15));
                                            appendable.append(str2.charAt((charAt >> 8) & 15));
                                            appendable.append(str2.charAt((charAt >> 4) & 15));
                                            appendable.append(str2.charAt((charAt >> 0) & 15));
                                            break;
                                        } else {
                                            appendable.append(charAt);
                                            break;
                                        }
                                }
                        }
                    } else {
                        appendable.append("\\\\");
                    }
                }
            } catch (IOException unused) {
                throw new RuntimeException("Impossible Exeption");
            }
        }
    }

    private static class MPAgressive implements MustProtect {
        private MPAgressive() {
        }

        public boolean mustBeProtect(String str) {
            if (str == null) {
                return false;
            }
            int length = str.length();
            if (length == 0 || str.trim() != str) {
                return true;
            }
            char charAt = str.charAt(0);
            if (JStylerObj.isSpecial(charAt) || JStylerObj.isUnicode(charAt)) {
                return true;
            }
            for (int i = 1; i < length; i++) {
                char charAt2 = str.charAt(i);
                if (JStylerObj.isSpecialClose(charAt2) || JStylerObj.isUnicode(charAt2)) {
                    return true;
                }
            }
            if (JStylerObj.isKeyword(str)) {
                return true;
            }
            char charAt3 = str.charAt(0);
            if ((charAt3 < '0' || charAt3 > '9') && charAt3 != '-') {
                return false;
            }
            char c = charAt3;
            int i2 = 1;
            while (i2 < length) {
                c = str.charAt(i2);
                if (c < '0' || c > '9') {
                    break;
                }
                i2++;
            }
            if (i2 == length) {
                return true;
            }
            if (c == '.') {
                i2++;
            }
            while (i2 < length) {
                c = str.charAt(i2);
                if (c < '0' || c > '9') {
                    break;
                }
                i2++;
            }
            if (i2 == length) {
                return true;
            }
            if (c == 'E' || c == 'e') {
                i2++;
                if (i2 == length) {
                    return false;
                }
                char charAt4 = str.charAt(i2);
                if (charAt4 == '+' || charAt4 == '-') {
                    i2++;
                    str.charAt(i2);
                }
            }
            if (i2 == length) {
                return false;
            }
            while (i2 < length) {
                char charAt5 = str.charAt(i2);
                if (charAt5 < '0' || charAt5 > '9') {
                    break;
                }
                i2++;
            }
            return i2 == length;
        }
    }

    private static class MPSimple implements MustProtect {
        private MPSimple() {
        }

        public boolean mustBeProtect(String str) {
            if (str == null) {
                return false;
            }
            int length = str.length();
            if (length == 0 || str.trim() != str) {
                return true;
            }
            char charAt = str.charAt(0);
            if ((charAt >= '0' && charAt <= '9') || charAt == '-') {
                return true;
            }
            for (int i = 0; i < length; i++) {
                char charAt2 = str.charAt(i);
                if (JStylerObj.isSpace(charAt2) || JStylerObj.isSpecial(charAt2) || JStylerObj.isSpecialChar(charAt2) || JStylerObj.isUnicode(charAt2)) {
                    return true;
                }
            }
            return JStylerObj.isKeyword(str);
        }
    }

    private static class MPTrue implements MustProtect {
        public boolean mustBeProtect(String str) {
            return true;
        }

        private MPTrue() {
        }
    }

    public interface MustProtect {
        boolean mustBeProtect(String str);
    }

    public interface StringProtector {
        void escape(String str, Appendable appendable);
    }

    public static boolean isSpace(char c) {
        return c == 13 || c == 10 || c == 9 || c == ' ';
    }

    public static boolean isSpecial(char c) {
        return c == '{' || c == '[' || c == ',' || c == '}' || c == ']' || c == ':' || c == '\'' || c == '\"';
    }

    public static boolean isSpecialChar(char c) {
        return c == 8 || c == 12 || c == 10;
    }

    public static boolean isSpecialClose(char c) {
        return c == '}' || c == ']' || c == ',' || c == ':';
    }

    public static boolean isUnicode(char c) {
        return (c >= 0 && c <= 31) || (c >= 127 && c <= 159) || (c >= 8192 && c <= 8447);
    }

    JStylerObj() {
    }

    public static boolean isKeyword(String str) {
        if (str.length() < 3) {
            return false;
        }
        char charAt = str.charAt(0);
        if (charAt == 'n') {
            return str.equals("null");
        }
        if (charAt == 't') {
            return str.equals("true");
        }
        if (charAt == 'f') {
            return str.equals("false");
        }
        if (charAt == 'N') {
            return str.equals("NaN");
        }
        return false;
    }
}
