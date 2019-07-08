package org.apache.commons.cli;

import com.myfitnesspal.shared.constants.Constants;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class HelpFormatter {
    public String defaultArgName = "arg";
    public int defaultDescPad = 3;
    public int defaultLeftPad = 1;
    public String defaultLongOptPrefix = Constants.TWO_HYPHENS;
    public String defaultNewLine = System.getProperty("line.separator");
    public String defaultOptPrefix = "-";
    public String defaultSyntaxPrefix = "usage: ";
    public int defaultWidth = 74;
    protected Comparator optionComparator = new OptionComparator();

    private static class OptionComparator implements Comparator {
        private OptionComparator() {
        }

        public int compare(Object obj, Object obj2) {
            return ((Option) obj).getKey().compareToIgnoreCase(((Option) obj2).getKey());
        }
    }

    public int getWidth() {
        return this.defaultWidth;
    }

    public int getLeftPadding() {
        return this.defaultLeftPad;
    }

    public int getDescPadding() {
        return this.defaultDescPad;
    }

    public Comparator getOptionComparator() {
        return this.optionComparator;
    }

    public void printOptions(PrintWriter printWriter, int i, Options options, int i2, int i3) {
        StringBuffer stringBuffer = new StringBuffer();
        renderOptions(stringBuffer, i, options, i2, i3);
        printWriter.println(stringBuffer.toString());
    }

    /* access modifiers changed from: protected */
    public StringBuffer renderOptions(StringBuffer stringBuffer, int i, Options options, int i2, int i3) {
        String createPadding = createPadding(i2);
        String createPadding2 = createPadding(i3);
        ArrayList arrayList = new ArrayList();
        List<Option> helpOptions = options.helpOptions();
        Collections.sort(helpOptions, getOptionComparator());
        int i4 = 0;
        int i5 = 0;
        for (Option option : helpOptions) {
            StringBuffer stringBuffer2 = new StringBuffer(8);
            if (option.getOpt() == null) {
                stringBuffer2.append(createPadding);
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("   ");
                stringBuffer3.append(this.defaultLongOptPrefix);
                stringBuffer2.append(stringBuffer3.toString());
                stringBuffer2.append(option.getLongOpt());
            } else {
                stringBuffer2.append(createPadding);
                stringBuffer2.append(this.defaultOptPrefix);
                stringBuffer2.append(option.getOpt());
                if (option.hasLongOpt()) {
                    stringBuffer2.append(',');
                    stringBuffer2.append(this.defaultLongOptPrefix);
                    stringBuffer2.append(option.getLongOpt());
                }
            }
            if (option.hasArg()) {
                if (option.hasArgName()) {
                    stringBuffer2.append(" <");
                    stringBuffer2.append(option.getArgName());
                    stringBuffer2.append(">");
                } else {
                    stringBuffer2.append(' ');
                }
            }
            arrayList.add(stringBuffer2);
            if (stringBuffer2.length() > i5) {
                i5 = stringBuffer2.length();
            }
        }
        Iterator it = helpOptions.iterator();
        while (it.hasNext()) {
            Option option2 = (Option) it.next();
            int i6 = i4 + 1;
            StringBuffer stringBuffer4 = new StringBuffer(arrayList.get(i4).toString());
            if (stringBuffer4.length() < i5) {
                stringBuffer4.append(createPadding(i5 - stringBuffer4.length()));
            }
            stringBuffer4.append(createPadding2);
            int i7 = i5 + i3;
            if (option2.getDescription() != null) {
                stringBuffer4.append(option2.getDescription());
            }
            renderWrappedText(stringBuffer, i, i7, stringBuffer4.toString());
            if (it.hasNext()) {
                stringBuffer.append(this.defaultNewLine);
            }
            i4 = i6;
        }
        return stringBuffer;
    }

    /* access modifiers changed from: protected */
    public StringBuffer renderWrappedText(StringBuffer stringBuffer, int i, int i2, String str) {
        int findWrapPos = findWrapPos(str, i, 0);
        if (findWrapPos == -1) {
            stringBuffer.append(rtrim(str));
            return stringBuffer;
        }
        stringBuffer.append(rtrim(str.substring(0, findWrapPos)));
        stringBuffer.append(this.defaultNewLine);
        if (i2 >= i) {
            i2 = 1;
        }
        String createPadding = createPadding(i2);
        while (true) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(createPadding);
            stringBuffer2.append(str.substring(findWrapPos).trim());
            str = stringBuffer2.toString();
            findWrapPos = findWrapPos(str, i, 0);
            if (findWrapPos == -1) {
                stringBuffer.append(str);
                return stringBuffer;
            }
            if (str.length() > i && findWrapPos == i2 - 1) {
                findWrapPos = i;
            }
            stringBuffer.append(rtrim(str.substring(0, findWrapPos)));
            stringBuffer.append(this.defaultNewLine);
        }
    }

    /* access modifiers changed from: protected */
    public int findWrapPos(String str, int i, int i2) {
        int indexOf = str.indexOf(10, i2);
        if (indexOf == -1 || indexOf > i) {
            indexOf = str.indexOf(9, i2);
            if (indexOf == -1 || indexOf > i) {
                int i3 = i + i2;
                if (i3 >= str.length()) {
                    return -1;
                }
                int i4 = i3;
                while (i4 >= i2) {
                    char charAt = str.charAt(i4);
                    if (charAt == ' ' || charAt == 10 || charAt == 13) {
                        break;
                    }
                    i4--;
                }
                if (i4 > i2) {
                    return i4;
                }
                while (i3 <= str.length()) {
                    char charAt2 = str.charAt(i3);
                    if (charAt2 == ' ' || charAt2 == 10 || charAt2 == 13) {
                        break;
                    }
                    i3++;
                }
                if (i3 == str.length()) {
                    i3 = -1;
                }
                return i3;
            }
        }
        return indexOf + 1;
    }

    /* access modifiers changed from: protected */
    public String createPadding(int i) {
        StringBuffer stringBuffer = new StringBuffer(i);
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(' ');
        }
        return stringBuffer.toString();
    }

    /* access modifiers changed from: protected */
    public String rtrim(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        int length = str.length();
        while (length > 0 && Character.isWhitespace(str.charAt(length - 1))) {
            length--;
        }
        return str.substring(0, length);
    }
}
