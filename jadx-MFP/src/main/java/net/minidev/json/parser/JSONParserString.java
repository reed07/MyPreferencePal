package net.minidev.json.parser;

class JSONParserString extends JSONParserMemory {
    private String in;

    public JSONParserString(int i) {
        super(i);
    }

    public Object parse(String str) throws ParseException {
        return parse(str, ContainerFactory.FACTORY_SIMPLE, ContentHandlerDumy.HANDLER);
    }

    public Object parse(String str, ContainerFactory containerFactory, ContentHandler contentHandler) throws ParseException {
        this.in = str;
        this.len = str.length();
        this.pos = -1;
        return parse(containerFactory, contentHandler);
    }

    /* access modifiers changed from: protected */
    public void extractString(int i, int i2) {
        this.xs = this.in.substring(i, i2);
    }

    /* access modifiers changed from: protected */
    public int indexOf(char c, int i) {
        return this.in.indexOf(c, i);
    }

    /* access modifiers changed from: protected */
    public void read() {
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.len) {
            this.c = 26;
        } else {
            this.c = this.in.charAt(this.pos);
        }
    }

    /* access modifiers changed from: protected */
    public void readS() {
        int i = this.pos + 1;
        this.pos = i;
        if (i >= this.len) {
            this.c = 26;
        } else {
            this.c = this.in.charAt(this.pos);
        }
    }

    /* access modifiers changed from: protected */
    public void readNoEnd() throws ParseException {
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.len) {
            this.c = this.in.charAt(this.pos);
        } else {
            this.c = 26;
            throw new ParseException(this.pos - 1, 3, "EOF");
        }
    }
}
