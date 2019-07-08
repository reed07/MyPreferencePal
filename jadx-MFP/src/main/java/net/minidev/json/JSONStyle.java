package net.minidev.json;

import java.io.IOException;
import kotlin.text.Typography;
import net.minidev.json.JStylerObj.MustProtect;
import net.minidev.json.JStylerObj.StringProtector;

public class JSONStyle {
    public static final JSONStyle LT_COMPRESS = new JSONStyle(2);
    public static final JSONStyle MAX_COMPRESS = new JSONStyle(-1);
    public static final JSONStyle NO_COMPRESS = new JSONStyle();
    private boolean _ignore_null;
    private boolean _protect4Web;
    private boolean _protectKeys;
    private boolean _protectValues;
    private StringProtector esc;
    private MustProtect mpKey;
    private MustProtect mpValue;

    public void arrayObjectEnd(Appendable appendable) throws IOException {
    }

    public void arrayfirstObject(Appendable appendable) throws IOException {
    }

    public void objectElmStop(Appendable appendable) throws IOException {
    }

    public void objectFirstStart(Appendable appendable) throws IOException {
    }

    public JSONStyle(int i) {
        MustProtect mustProtect;
        boolean z = false;
        this._protectKeys = (i & 1) == 0;
        this._protectValues = (i & 4) == 0;
        this._protect4Web = (i & 2) == 0;
        if ((i & 16) > 0) {
            z = true;
        }
        this._ignore_null = z;
        if ((i & 8) > 0) {
            mustProtect = JStylerObj.MP_AGGRESIVE;
        } else {
            mustProtect = JStylerObj.MP_SIMPLE;
        }
        if (this._protectValues) {
            this.mpValue = JStylerObj.MP_TRUE;
        } else {
            this.mpValue = mustProtect;
        }
        if (this._protectKeys) {
            this.mpKey = JStylerObj.MP_TRUE;
        } else {
            this.mpKey = mustProtect;
        }
        if (this._protect4Web) {
            this.esc = JStylerObj.ESCAPE4Web;
        } else {
            this.esc = JStylerObj.ESCAPE_LT;
        }
    }

    public JSONStyle() {
        this(0);
    }

    public boolean ignoreNull() {
        return this._ignore_null;
    }

    public boolean mustProtectKey(String str) {
        return this.mpKey.mustBeProtect(str);
    }

    public boolean mustProtectValue(String str) {
        return this.mpValue.mustBeProtect(str);
    }

    public void writeString(Appendable appendable, String str) throws IOException {
        if (!mustProtectValue(str)) {
            appendable.append(str);
            return;
        }
        appendable.append(Typography.quote);
        JSONValue.escape(str, appendable, this);
        appendable.append(Typography.quote);
    }

    public void escape(String str, Appendable appendable) {
        this.esc.escape(str, appendable);
    }

    public void objectStart(Appendable appendable) throws IOException {
        appendable.append('{');
    }

    public void objectStop(Appendable appendable) throws IOException {
        appendable.append('}');
    }

    public void objectNext(Appendable appendable) throws IOException {
        appendable.append(',');
    }

    public void objectEndOfKey(Appendable appendable) throws IOException {
        appendable.append(':');
    }

    public void arrayStart(Appendable appendable) throws IOException {
        appendable.append('[');
    }

    public void arrayStop(Appendable appendable) throws IOException {
        appendable.append(']');
    }

    public void arrayNextElm(Appendable appendable) throws IOException {
        appendable.append(',');
    }
}
