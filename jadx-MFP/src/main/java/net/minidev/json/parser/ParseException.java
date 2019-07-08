package net.minidev.json.parser;

public class ParseException extends Exception {
    private static final long serialVersionUID = 8879024178584091857L;
    private int errorType;
    private int position;
    private Object unexpectedObject;

    public ParseException(int i, int i2, Object obj) {
        super(toMessage(i, i2, obj));
        this.position = i;
        this.errorType = i2;
        this.unexpectedObject = obj;
    }

    public ParseException(int i, Throwable th) {
        super(toMessage(i, 2, th), th);
        this.position = i;
        this.errorType = 2;
        this.unexpectedObject = th;
    }

    private static String toMessage(int i, int i2, Object obj) {
        StringBuilder sb = new StringBuilder();
        if (i2 == 0) {
            sb.append("Unexpected character (");
            sb.append(obj);
            sb.append(") at position ");
            sb.append(i);
            sb.append(".");
        } else if (i2 == 1) {
            sb.append("Unexpected token ");
            sb.append(obj);
            sb.append(" at position ");
            sb.append(i);
            sb.append(".");
        } else if (i2 == 2) {
            sb.append("Unexpected exception ");
            sb.append(obj);
            sb.append(" occur at position ");
            sb.append(i);
            sb.append(".");
        } else if (i2 == 3) {
            sb.append("Unexpected End Of File position ");
            sb.append(i);
            sb.append(": ");
            sb.append(obj);
        } else if (i2 == 4) {
            sb.append("Unexpected unicode escape sequence ");
            sb.append(obj);
            sb.append(" at position ");
            sb.append(i);
            sb.append(".");
        } else if (i2 == 5) {
            sb.append("Unexpected duplicate key:");
            sb.append(obj);
            sb.append(" at position ");
            sb.append(i);
            sb.append(".");
        } else if (i2 == 6) {
            sb.append("Unexpected leading 0 in digit for token:");
            sb.append(obj);
            sb.append(" at position ");
            sb.append(i);
            sb.append(".");
        } else {
            sb.append("Unkown error at position ");
            sb.append(i);
            sb.append(".");
        }
        return sb.toString();
    }
}
