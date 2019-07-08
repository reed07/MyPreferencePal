package net.minidev.json.parser;

import java.io.IOException;
import kotlin.text.Typography;
import net.minidev.json.JSONStyle;
import net.minidev.json.JSONValue;

public class ContentHandlerCompressor implements ContentHandler {
    JSONStyle compression;
    Appendable out;
    int pos;
    int[] stack;

    public void endJSON() throws ParseException, IOException {
    }

    public boolean endObjectEntry() throws ParseException, IOException {
        return false;
    }

    public void startJSON() throws ParseException, IOException {
    }

    private void push(int i) {
        this.pos += 2;
        int i2 = this.pos;
        int[] iArr = this.stack;
        if (i2 >= iArr.length) {
            int[] iArr2 = new int[(iArr.length * 2)];
            System.arraycopy(iArr, 0, iArr2, 0, iArr.length);
            this.stack = iArr2;
        }
        int[] iArr3 = this.stack;
        int i3 = this.pos;
        iArr3[i3] = i;
        iArr3[i3 + 1] = 0;
    }

    private boolean isInObject() {
        return this.stack[this.pos] == 0;
    }

    private boolean isInArray() {
        return this.stack[this.pos] == 1;
    }

    public boolean startObject() throws ParseException, IOException {
        if (isInArray()) {
            int[] iArr = this.stack;
            int i = this.pos + 1;
            int i2 = iArr[i];
            iArr[i] = i2 + 1;
            if (i2 > 0) {
                this.out.append(',');
            }
        }
        this.out.append('{');
        push(0);
        return false;
    }

    public boolean endObject() throws ParseException, IOException {
        this.out.append('}');
        this.pos -= 2;
        return false;
    }

    public boolean startObjectEntry(String str) throws ParseException, IOException {
        int[] iArr = this.stack;
        int i = this.pos + 1;
        int i2 = iArr[i];
        iArr[i] = i2 + 1;
        if (i2 > 0) {
            this.out.append(',');
        }
        if (str == null) {
            this.out.append("null");
        } else if (!this.compression.mustProtectKey(str)) {
            this.out.append(str);
        } else {
            this.out.append(Typography.quote);
            JSONValue.escape(str, this.out, this.compression);
            this.out.append(Typography.quote);
        }
        this.out.append(':');
        return false;
    }

    public boolean startArray() throws ParseException, IOException {
        if (isInArray()) {
            int[] iArr = this.stack;
            int i = this.pos + 1;
            int i2 = iArr[i];
            iArr[i] = i2 + 1;
            if (i2 > 0) {
                this.out.append(',');
            }
        }
        this.out.append('[');
        push(1);
        return false;
    }

    public boolean endArray() throws ParseException, IOException {
        this.out.append(']');
        this.pos -= 2;
        return false;
    }

    public boolean primitive(Object obj) throws ParseException, IOException {
        if (!isInObject()) {
            int[] iArr = this.stack;
            int i = this.pos + 1;
            int i2 = iArr[i];
            iArr[i] = i2 + 1;
            if (i2 > 0) {
                this.out.append(',');
            }
        }
        if (obj instanceof String) {
            this.compression.writeString(this.out, (String) obj);
        } else {
            JSONValue.writeJSONString(obj, this.out, this.compression);
        }
        return false;
    }
}
