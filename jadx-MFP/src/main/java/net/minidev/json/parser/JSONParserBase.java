package net.minidev.json.parser;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

abstract class JSONParserBase {
    protected static boolean[] stopAll = new boolean[126];
    protected static boolean[] stopArray = new boolean[126];
    protected static boolean[] stopKey = new boolean[126];
    protected static boolean[] stopValue = new boolean[126];
    protected static boolean[] stopX = new boolean[126];
    protected final boolean acceptLeadinZero;
    protected final boolean acceptNaN;
    protected final boolean acceptNonQuote;
    protected final boolean acceptSimpleQuote;
    protected final boolean acceptUselessComma;
    protected char c;
    protected final boolean checkTaillingData;
    protected final boolean checkTaillingSpace;
    protected ContainerFactory containerFactory;
    protected ContentHandler handler;
    protected final boolean ignoreControlChar;
    protected int pos;
    protected final MSB sb = new MSB(15);
    protected final boolean useHiPrecisionFloat;
    protected final boolean useIntegerStorage;
    protected Object xo;
    protected String xs;

    public static class MSB {
        char[] b;
        int p = -1;

        public MSB(int i) {
            this.b = new char[i];
        }

        public void append(char c) {
            this.p++;
            char[] cArr = this.b;
            if (cArr.length <= this.p) {
                char[] cArr2 = new char[((cArr.length * 2) + 1)];
                System.arraycopy(cArr, 0, cArr2, 0, cArr.length);
                this.b = cArr2;
            }
            this.b[this.p] = c;
        }

        public String toString() {
            return new String(this.b, 0, this.p + 1);
        }

        public void clear() {
            this.p = -1;
        }
    }

    /* access modifiers changed from: protected */
    public abstract void read() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void readNQString(boolean[] zArr) throws IOException;

    /* access modifiers changed from: protected */
    public abstract void readNoEnd() throws ParseException, IOException;

    /* access modifiers changed from: protected */
    public abstract Object readNumber(boolean[] zArr) throws ParseException, IOException;

    /* access modifiers changed from: 0000 */
    public abstract void readS() throws IOException;

    /* access modifiers changed from: protected */
    public abstract void readString() throws ParseException, IOException;

    static {
        boolean[] zArr = stopKey;
        zArr[26] = true;
        zArr[58] = true;
        boolean[] zArr2 = stopValue;
        zArr2[26] = true;
        zArr2[125] = true;
        zArr2[44] = true;
        boolean[] zArr3 = stopArray;
        zArr3[26] = true;
        zArr3[93] = true;
        zArr3[44] = true;
        stopX[26] = true;
        boolean[] zArr4 = stopAll;
        zArr4[58] = true;
        zArr4[44] = true;
        zArr4[26] = true;
        zArr4[125] = true;
        zArr4[93] = true;
    }

    public JSONParserBase(int i) {
        boolean z = false;
        this.acceptNaN = (i & 4) > 0;
        this.acceptNonQuote = (i & 2) > 0;
        this.acceptSimpleQuote = (i & 1) > 0;
        this.ignoreControlChar = (i & 8) > 0;
        this.useIntegerStorage = (i & 16) > 0;
        this.acceptLeadinZero = (i & 32) > 0;
        this.acceptUselessComma = (i & 64) > 0;
        this.useHiPrecisionFloat = (i & 128) > 0;
        this.checkTaillingData = (i & 768) == 0;
        if ((i & 512) == 0) {
            z = true;
        }
        this.checkTaillingSpace = z;
    }

    public void checkControleChar() throws ParseException {
        if (!this.ignoreControlChar) {
            int length = this.xs.length();
            for (int i = 0; i < length; i++) {
                char charAt = this.xs.charAt(i);
                if (charAt >= 0) {
                    if (charAt <= 31) {
                        throw new ParseException(this.pos + i, 0, Character.valueOf(charAt));
                    } else if (charAt == 127) {
                        throw new ParseException(this.pos + i, 0, Character.valueOf(charAt));
                    }
                }
            }
        }
    }

    public void checkLeadinZero() throws ParseException {
        int length = this.xs.length();
        if (length != 1) {
            if (length != 2) {
                char charAt = this.xs.charAt(0);
                char charAt2 = this.xs.charAt(1);
                if (charAt == '-') {
                    char charAt3 = this.xs.charAt(2);
                    if (charAt2 == '0' && charAt3 >= '0' && charAt3 <= '9') {
                        throw new ParseException(this.pos, 6, this.xs);
                    }
                } else if (charAt == '0' && charAt2 >= '0' && charAt2 <= '9') {
                    throw new ParseException(this.pos, 6, this.xs);
                }
            } else if (this.xs.equals("00")) {
                throw new ParseException(this.pos, 6, this.xs);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Number extractFloat() throws ParseException {
        if (!this.acceptLeadinZero) {
            checkLeadinZero();
        }
        if (!this.useHiPrecisionFloat) {
            return Float.valueOf(Float.parseFloat(this.xs));
        }
        if (this.xs.length() > 18) {
            return new BigDecimal(this.xs);
        }
        return Double.valueOf(Double.parseDouble(this.xs));
    }

    /* access modifiers changed from: protected */
    public Object parse(ContainerFactory containerFactory2, ContentHandler contentHandler) throws ParseException {
        this.containerFactory = containerFactory2;
        this.handler = contentHandler;
        try {
            read();
            contentHandler.startJSON();
            Object readMain = readMain(stopX);
            contentHandler.endJSON();
            if (this.checkTaillingData) {
                if (!this.checkTaillingSpace) {
                    skipSpace();
                }
                if (this.c != 26) {
                    throw new ParseException(this.pos - 1, 1, Character.valueOf(this.c));
                }
            }
            this.xs = null;
            this.xo = null;
            return readMain;
        } catch (IOException e) {
            throw new ParseException(this.pos, e);
        }
    }

    /* access modifiers changed from: protected */
    public Number parseNumber(String str) throws ParseException {
        boolean z;
        int i;
        int i2;
        int i3;
        boolean z2;
        int length = str.length();
        boolean z3 = false;
        if (str.charAt(0) == '-') {
            i2 = 20;
            if (this.acceptLeadinZero || length < 3 || str.charAt(1) != '0') {
                i = 1;
                z = true;
            } else {
                throw new ParseException(this.pos, 6, str);
            }
        } else if (this.acceptLeadinZero || length < 2 || str.charAt(0) != '0') {
            i2 = 19;
            i = 0;
            z = false;
        } else {
            throw new ParseException(this.pos, 6, str);
        }
        if (length < i2) {
            i3 = length;
            z2 = false;
        } else if (length > i2) {
            return new BigInteger(str, 10);
        } else {
            i3 = length - 1;
            z2 = true;
        }
        long j = 0;
        while (i < i3) {
            j = (j * 10) + ((long) ('0' - str.charAt(i)));
            i++;
        }
        if (z2) {
            int i4 = (j > -922337203685477580L ? 1 : (j == -922337203685477580L ? 0 : -1));
            if (i4 <= 0) {
                if (i4 < 0) {
                    z3 = true;
                } else if (z) {
                    if (str.charAt(i) > '8') {
                        z3 = true;
                    }
                } else if (str.charAt(i) > '7') {
                    z3 = true;
                }
            }
            if (z3) {
                return new BigInteger(str, 10);
            }
            j = (j * 10) + ((long) ('0' - str.charAt(i)));
        }
        if (!z) {
            long j2 = -j;
            if (!this.useIntegerStorage || j2 > 2147483647L) {
                return Long.valueOf(j2);
            }
            return Integer.valueOf((int) j2);
        } else if (!this.useIntegerStorage || j < -2147483648L) {
            return Long.valueOf(j);
        } else {
            return Integer.valueOf((int) j);
        }
    }

    /* access modifiers changed from: protected */
    public List<Object> readArray() throws ParseException, IOException {
        List<Object> createArrayContainer = this.containerFactory.createArrayContainer();
        if (this.c == '[') {
            read();
            this.handler.startArray();
            boolean z = false;
            while (true) {
                char c2 = this.c;
                if (c2 != 13) {
                    if (c2 == 26) {
                        throw new ParseException(this.pos - 1, 3, "EOF");
                    } else if (c2 != ' ') {
                        if (c2 != ',') {
                            if (c2 != ':') {
                                if (c2 != ']') {
                                    if (c2 != '}') {
                                        switch (c2) {
                                            case 9:
                                            case 10:
                                                break;
                                            default:
                                                createArrayContainer.add(readMain(stopArray));
                                                z = false;
                                                continue;
                                        }
                                    }
                                } else if (!z || this.acceptUselessComma) {
                                    read();
                                    this.handler.endArray();
                                    return createArrayContainer;
                                } else {
                                    throw new ParseException(this.pos, 0, Character.valueOf(c2));
                                }
                            }
                        } else if (!z || this.acceptUselessComma) {
                            read();
                            z = true;
                        } else {
                            throw new ParseException(this.pos, 0, Character.valueOf(c2));
                        }
                    }
                }
                read();
            }
            throw new ParseException(this.pos, 0, Character.valueOf(this.c));
        }
        throw new RuntimeException("Internal Error");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0119, code lost:
        throw new net.minidev.json.parser.ParseException(r3.pos, 0, java.lang.Character.valueOf(r3.c));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object readMain(boolean[] r4) throws net.minidev.json.parser.ParseException, java.io.IOException {
        /*
            r3 = this;
        L_0x0000:
            char r0 = r3.c
            switch(r0) {
                case 9: goto L_0x012a;
                case 10: goto L_0x012a;
                default: goto L_0x0005;
            }
        L_0x0005:
            switch(r0) {
                case 48: goto L_0x011a;
                case 49: goto L_0x011a;
                case 50: goto L_0x011a;
                case 51: goto L_0x011a;
                case 52: goto L_0x011a;
                case 53: goto L_0x011a;
                case 54: goto L_0x011a;
                case 55: goto L_0x011a;
                case 56: goto L_0x011a;
                case 57: goto L_0x011a;
                case 58: goto L_0x010b;
                default: goto L_0x0008;
            }
        L_0x0008:
            r1 = 1
            switch(r0) {
                case 13: goto L_0x012a;
                case 32: goto L_0x012a;
                case 34: goto L_0x00fe;
                case 39: goto L_0x00fe;
                case 45: goto L_0x011a;
                case 78: goto L_0x00bb;
                case 91: goto L_0x00b6;
                case 93: goto L_0x010b;
                case 102: goto L_0x0087;
                case 110: goto L_0x005b;
                case 116: goto L_0x002c;
                case 123: goto L_0x0027;
                case 125: goto L_0x010b;
                default: goto L_0x000c;
            }
        L_0x000c:
            r3.readNQString(r4)
            boolean r4 = r3.acceptNonQuote
            if (r4 == 0) goto L_0x001d
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            java.lang.String r0 = r3.xs
            r4.primitive(r0)
            java.lang.String r4 = r3.xs
            return r4
        L_0x001d:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x0027:
            java.util.Map r4 = r3.readObject()
            return r4
        L_0x002c:
            r3.readNQString(r4)
            java.lang.String r4 = "true"
            java.lang.String r0 = r3.xs
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x0043
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r4.primitive(r0)
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            return r4
        L_0x0043:
            boolean r4 = r3.acceptNonQuote
            if (r4 == 0) goto L_0x0051
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            java.lang.String r0 = r3.xs
            r4.primitive(r0)
            java.lang.String r4 = r3.xs
            return r4
        L_0x0051:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x005b:
            r3.readNQString(r4)
            java.lang.String r4 = "null"
            java.lang.String r0 = r3.xs
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x006f
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            r0 = 0
            r4.primitive(r0)
            return r0
        L_0x006f:
            boolean r4 = r3.acceptNonQuote
            if (r4 == 0) goto L_0x007d
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            java.lang.String r0 = r3.xs
            r4.primitive(r0)
            java.lang.String r4 = r3.xs
            return r4
        L_0x007d:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x0087:
            r3.readNQString(r4)
            java.lang.String r4 = "false"
            java.lang.String r0 = r3.xs
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x009e
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r4.primitive(r0)
            java.lang.Boolean r4 = java.lang.Boolean.FALSE
            return r4
        L_0x009e:
            boolean r4 = r3.acceptNonQuote
            if (r4 == 0) goto L_0x00ac
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            java.lang.String r0 = r3.xs
            r4.primitive(r0)
            java.lang.String r4 = r3.xs
            return r4
        L_0x00ac:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x00b6:
            java.util.List r4 = r3.readArray()
            return r4
        L_0x00bb:
            r3.readNQString(r4)
            boolean r4 = r3.acceptNaN
            if (r4 == 0) goto L_0x00f4
            java.lang.String r4 = "NaN"
            java.lang.String r0 = r3.xs
            boolean r4 = r4.equals(r0)
            if (r4 == 0) goto L_0x00dc
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            r0 = 2143289344(0x7fc00000, float:NaN)
            java.lang.Float r1 = java.lang.Float.valueOf(r0)
            r4.primitive(r1)
            java.lang.Float r4 = java.lang.Float.valueOf(r0)
            return r4
        L_0x00dc:
            boolean r4 = r3.acceptNonQuote
            if (r4 == 0) goto L_0x00ea
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            java.lang.String r0 = r3.xs
            r4.primitive(r0)
            java.lang.String r4 = r3.xs
            return r4
        L_0x00ea:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x00f4:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            java.lang.String r2 = r3.xs
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x00fe:
            r3.readString()
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            java.lang.String r0 = r3.xs
            r4.primitive(r0)
            java.lang.String r4 = r3.xs
            return r4
        L_0x010b:
            net.minidev.json.parser.ParseException r4 = new net.minidev.json.parser.ParseException
            int r0 = r3.pos
            r1 = 0
            char r2 = r3.c
            java.lang.Character r2 = java.lang.Character.valueOf(r2)
            r4.<init>(r0, r1, r2)
            throw r4
        L_0x011a:
            java.lang.Object r4 = r3.readNumber(r4)
            r3.xo = r4
            net.minidev.json.parser.ContentHandler r4 = r3.handler
            java.lang.Object r0 = r3.xo
            r4.primitive(r0)
            java.lang.Object r4 = r3.xo
            return r4
        L_0x012a:
            r3.read()
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: net.minidev.json.parser.JSONParserBase.readMain(boolean[]):java.lang.Object");
    }

    /* access modifiers changed from: protected */
    public Map<String, Object> readObject() throws ParseException, IOException {
        Map<String, Object> createObjectContainer = this.containerFactory.createObjectContainer();
        if (this.c == '{') {
            this.handler.startObject();
            boolean z = false;
            boolean z2 = true;
            while (true) {
                read();
                char c2 = this.c;
                if (!(c2 == 13 || c2 == ' ')) {
                    if (c2 != ',') {
                        if (c2 != ':' && c2 != '[' && c2 != ']' && c2 != '{') {
                            if (c2 != '}') {
                                switch (c2) {
                                    case 9:
                                    case 10:
                                        continue;
                                    default:
                                        int i = this.pos;
                                        if (c2 == '\"' || c2 == '\'') {
                                            readString();
                                        } else {
                                            readNQString(stopKey);
                                            if (!this.acceptNonQuote) {
                                                throw new ParseException(this.pos, 1, this.xs);
                                            }
                                        }
                                        String str = this.xs;
                                        if (z2) {
                                            this.handler.startObjectEntry(str);
                                            skipSpace();
                                            char c3 = this.c;
                                            if (c3 == ':') {
                                                readNoEnd();
                                                if (createObjectContainer.put(str, readMain(stopValue)) == null) {
                                                    this.handler.endObjectEntry();
                                                    char c4 = this.c;
                                                    if (c4 == '}') {
                                                        read();
                                                        this.handler.endObject();
                                                        return createObjectContainer;
                                                    } else if (c4 != 26) {
                                                        if (c4 != ',') {
                                                            z = false;
                                                            z2 = false;
                                                            break;
                                                        } else {
                                                            z = true;
                                                            z2 = true;
                                                            break;
                                                        }
                                                    } else {
                                                        throw new ParseException(this.pos - 1, 3, null);
                                                    }
                                                } else {
                                                    throw new ParseException(i, 5, str);
                                                }
                                            } else if (c3 == 26) {
                                                throw new ParseException(this.pos - 1, 3, null);
                                            } else {
                                                throw new ParseException(this.pos - 1, 0, Character.valueOf(c3));
                                            }
                                        } else {
                                            throw new ParseException(this.pos, 1, str);
                                        }
                                }
                            } else if (!z || this.acceptUselessComma) {
                                read();
                                this.handler.endObject();
                                return createObjectContainer;
                            } else {
                                throw new ParseException(this.pos, 0, Character.valueOf(c2));
                            }
                        }
                    } else if (!z || this.acceptUselessComma) {
                        z = true;
                        z2 = true;
                    } else {
                        throw new ParseException(this.pos, 0, Character.valueOf(c2));
                    }
                }
            }
            throw new ParseException(this.pos, 0, Character.valueOf(this.c));
        }
        throw new RuntimeException("Internal Error");
    }

    /* access modifiers changed from: protected */
    public void readString2() throws ParseException, IOException {
        char c2 = this.c;
        while (true) {
            read();
            char c3 = this.c;
            if (c3 == '\"' || c3 == '\'') {
                char c4 = this.c;
                if (c2 == c4) {
                    read();
                    this.xs = this.sb.toString();
                    return;
                }
                this.sb.append(c4);
            } else if (c3 != '\\') {
                if (c3 != 127) {
                    switch (c3) {
                        case 0:
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 10:
                        case 11:
                        case 12:
                        case 13:
                        case 14:
                        case 15:
                        case 16:
                        case 17:
                        case 18:
                        case 19:
                        case 20:
                        case 21:
                        case 22:
                        case 23:
                        case 24:
                        case 25:
                        case 27:
                        case 28:
                        case 29:
                        case 30:
                        case 31:
                            break;
                        case 26:
                            throw new ParseException(this.pos - 1, 3, null);
                        default:
                            this.sb.append(c3);
                            continue;
                    }
                }
                if (!this.ignoreControlChar) {
                    throw new ParseException(this.pos, 0, Character.valueOf(this.c));
                }
            } else {
                read();
                char c5 = this.c;
                if (c5 == '\"') {
                    this.sb.append(Typography.quote);
                } else if (c5 == '\'') {
                    this.sb.append('\'');
                } else if (c5 == '/') {
                    this.sb.append('/');
                } else if (c5 == '\\') {
                    this.sb.append('\\');
                } else if (c5 == 'b') {
                    this.sb.append(8);
                } else if (c5 == 'f') {
                    this.sb.append(12);
                } else if (c5 == 'n') {
                    this.sb.append(10);
                } else if (c5 == 'r') {
                    this.sb.append(13);
                } else if (c5 != 'x') {
                    switch (c5) {
                        case 't':
                            this.sb.append(9);
                            break;
                        case 'u':
                            this.sb.append(readUnicode(4));
                            break;
                    }
                } else {
                    this.sb.append(readUnicode(2));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public char readUnicode(int i) throws ParseException, IOException {
        int i2;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = i3 * 16;
            read();
            char c2 = this.c;
            if (c2 > '9' || c2 < '0') {
                char c3 = this.c;
                if (c3 > 'F' || c3 < 'A') {
                    char c4 = this.c;
                    if (c4 < 'a' || c4 > 'f') {
                        char c5 = this.c;
                        if (c5 == 26) {
                            throw new ParseException(this.pos, 3, "EOF");
                        }
                        throw new ParseException(this.pos, 4, Character.valueOf(c5));
                    }
                    i2 = (c4 - 'a') + 10;
                } else {
                    i2 = (c3 - 'A') + 10;
                }
            } else {
                i2 = c2 - '0';
            }
            i3 = i5 + i2;
        }
        return (char) i3;
    }

    /* access modifiers changed from: protected */
    public void skipDigits() throws IOException {
        while (true) {
            char c2 = this.c;
            if (c2 >= '0' && c2 <= '9') {
                readS();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void skipNQString(boolean[] zArr) throws IOException {
        while (true) {
            char c2 = this.c;
            if (c2 == 26) {
                return;
            }
            if (c2 < 0 || c2 >= '~' || !zArr[c2]) {
                readS();
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void skipSpace() throws IOException {
        while (true) {
            char c2 = this.c;
            if (c2 <= ' ' && c2 != 26) {
                readS();
            } else {
                return;
            }
        }
    }
}
