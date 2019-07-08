package net.minidev.json.parser;

import java.io.IOException;

abstract class JSONParserMemory extends JSONParserBase {
    protected int len;

    /* access modifiers changed from: protected */
    public abstract void extractString(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract int indexOf(char c, int i);

    public JSONParserMemory(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    public void readNQString(boolean[] zArr) throws IOException {
        int i = this.pos;
        skipNQString(zArr);
        extractStringTrim(i, this.pos);
    }

    /* access modifiers changed from: protected */
    public Object readNumber(boolean[] zArr) throws ParseException, IOException {
        int i = this.pos;
        read();
        skipDigits();
        if (this.c == '.' || this.c == 'E' || this.c == 'e') {
            if (this.c == '.') {
                read();
                skipDigits();
            }
            if (this.c == 'E' || this.c == 'e') {
                this.sb.append('E');
                read();
                if (this.c == '+' || this.c == '-' || (this.c >= '0' && this.c <= '9')) {
                    this.sb.append(this.c);
                    read();
                    skipDigits();
                    skipSpace();
                    if (this.c < 0 || this.c >= '~' || zArr[this.c] || this.c == 26) {
                        extractStringTrim(i, this.pos);
                        return extractFloat();
                    }
                    skipNQString(zArr);
                    extractStringTrim(i, this.pos);
                    if (this.acceptNonQuote) {
                        return this.xs;
                    }
                    throw new ParseException(this.pos, 1, this.xs);
                }
                skipNQString(zArr);
                extractStringTrim(i, this.pos);
                if (this.acceptNonQuote) {
                    if (!this.acceptLeadinZero) {
                        checkLeadinZero();
                    }
                    return this.xs;
                }
                throw new ParseException(this.pos, 1, this.xs);
            }
            skipSpace();
            if (this.c < 0 || this.c >= '~' || zArr[this.c] || this.c == 26) {
                extractStringTrim(i, this.pos);
                return extractFloat();
            }
            skipNQString(zArr);
            extractStringTrim(i, this.pos);
            if (this.acceptNonQuote) {
                return this.xs;
            }
            throw new ParseException(this.pos, 1, this.xs);
        }
        skipSpace();
        if (this.c < 0 || this.c >= '~' || zArr[this.c] || this.c == 26) {
            extractStringTrim(i, this.pos);
            return parseNumber(this.xs);
        }
        skipNQString(zArr);
        extractStringTrim(i, this.pos);
        if (this.acceptNonQuote) {
            return this.xs;
        }
        throw new ParseException(this.pos, 1, this.xs);
    }

    /* access modifiers changed from: protected */
    public void readString() throws ParseException, IOException {
        if (this.acceptSimpleQuote || this.c != '\'') {
            int indexOf = indexOf(this.c, this.pos + 1);
            if (indexOf != -1) {
                extractString(this.pos + 1, indexOf);
                if (this.xs.indexOf(92) == -1) {
                    checkControleChar();
                    this.pos = indexOf;
                    read();
                    return;
                }
                this.sb.clear();
                readString2();
                return;
            }
            throw new ParseException(this.len, 3, null);
        } else if (this.acceptNonQuote) {
            readNQString(stopAll);
        } else {
            throw new ParseException(this.pos, 0, Character.valueOf(this.c));
        }
    }

    /* access modifiers changed from: protected */
    public void extractStringTrim(int i, int i2) {
        extractString(i, i2);
        this.xs = this.xs.trim();
    }
}
