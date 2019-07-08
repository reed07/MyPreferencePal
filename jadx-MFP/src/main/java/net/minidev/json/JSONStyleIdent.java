package net.minidev.json;

import java.io.IOException;

public class JSONStyleIdent extends JSONStyle {
    int deep = 0;
    char identChar = ' ';
    String newline = "\n";

    public void arrayObjectEnd(Appendable appendable) throws IOException {
    }

    public void arrayfirstObject(Appendable appendable) throws IOException {
    }

    public void objectElmStop(Appendable appendable) throws IOException {
    }

    public void objectFirstStart(Appendable appendable) throws IOException {
    }

    private void ident(Appendable appendable) throws IOException {
        appendable.append(this.newline);
        for (int i = 0; i < this.deep; i++) {
            appendable.append(this.identChar);
        }
    }

    public void objectStart(Appendable appendable) throws IOException {
        appendable.append('{');
        this.deep++;
        ident(appendable);
    }

    public void objectStop(Appendable appendable) throws IOException {
        this.deep--;
        ident(appendable);
        appendable.append('}');
    }

    public void objectNext(Appendable appendable) throws IOException {
        appendable.append(',');
        ident(appendable);
    }

    public void objectEndOfKey(Appendable appendable) throws IOException {
        appendable.append(':');
    }

    public void arrayStart(Appendable appendable) throws IOException {
        appendable.append('[');
        this.deep++;
        ident(appendable);
    }

    public void arrayStop(Appendable appendable) throws IOException {
        this.deep--;
        ident(appendable);
        appendable.append(']');
    }

    public void arrayNextElm(Appendable appendable) throws IOException {
        appendable.append(',');
        ident(appendable);
    }
}
