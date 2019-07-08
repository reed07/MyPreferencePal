package net.minidev.json.parser;

import java.io.IOException;

public class ContentHandlerDumy implements ContentHandler {
    public static ContentHandlerDumy HANDLER = new ContentHandlerDumy();

    public boolean endArray() throws ParseException {
        return false;
    }

    public void endJSON() throws ParseException {
    }

    public boolean endObject() throws ParseException {
        return false;
    }

    public boolean endObjectEntry() throws ParseException {
        return false;
    }

    public boolean primitive(Object obj) throws ParseException {
        return false;
    }

    public boolean startArray() throws ParseException {
        return false;
    }

    public void startJSON() throws ParseException {
    }

    public boolean startObject() throws ParseException, IOException {
        return false;
    }

    public boolean startObjectEntry(String str) throws ParseException {
        return false;
    }
}
