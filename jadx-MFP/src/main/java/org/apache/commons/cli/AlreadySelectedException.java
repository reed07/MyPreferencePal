package org.apache.commons.cli;

public class AlreadySelectedException extends ParseException {
    private OptionGroup group;
    private Option option;

    public AlreadySelectedException(String str) {
        super(str);
    }

    public AlreadySelectedException(OptionGroup optionGroup, Option option2) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("The option '");
        stringBuffer.append(option2.getKey());
        stringBuffer.append("' was specified but an option from this group ");
        stringBuffer.append("has already been selected: '");
        stringBuffer.append(optionGroup.getSelected());
        stringBuffer.append("'");
        this(stringBuffer.toString());
        this.group = optionGroup;
        this.option = option2;
    }
}
