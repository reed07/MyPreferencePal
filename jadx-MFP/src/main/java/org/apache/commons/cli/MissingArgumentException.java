package org.apache.commons.cli;

public class MissingArgumentException extends ParseException {
    private Option option;

    public MissingArgumentException(String str) {
        super(str);
    }

    public MissingArgumentException(Option option2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Missing argument for option: ");
        stringBuffer.append(option2.getKey());
        this(stringBuffer.toString());
        this.option = option2;
    }
}
