package net.minidev.json.parser;

public class JSONParser {
    public static int DEFAULT_PERMISSIVE_MODE = (System.getProperty("JSON_SMART_SIMPLE") != null ? 960 : -1);
    private int mode;
    private JSONParserString pString;

    public JSONParser() {
        this.mode = DEFAULT_PERMISSIVE_MODE;
    }

    public JSONParser(int i) {
        this.mode = i;
    }

    public Object parse(String str) throws ParseException {
        if (this.pString == null) {
            this.pString = new JSONParserString(this.mode);
        }
        return this.pString.parse(str);
    }
}
