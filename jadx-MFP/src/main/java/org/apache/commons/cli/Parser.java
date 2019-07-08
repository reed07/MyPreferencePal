package org.apache.commons.cli;

import com.facebook.appevents.AppEventsConstants;
import com.myfitnesspal.shared.constants.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;

public abstract class Parser implements CommandLineParser {
    protected CommandLine cmd;
    private Options options;
    private List requiredOptions;

    /* access modifiers changed from: protected */
    public abstract String[] flatten(Options options2, String[] strArr, boolean z);

    /* access modifiers changed from: protected */
    public void setOptions(Options options2) {
        this.options = options2;
        this.requiredOptions = new ArrayList(options2.getRequiredOptions());
    }

    /* access modifiers changed from: protected */
    public Options getOptions() {
        return this.options;
    }

    /* access modifiers changed from: protected */
    public List getRequiredOptions() {
        return this.requiredOptions;
    }

    public CommandLine parse(Options options2, String[] strArr, boolean z) throws ParseException {
        return parse(options2, strArr, null, z);
    }

    public CommandLine parse(Options options2, String[] strArr, Properties properties, boolean z) throws ParseException {
        for (Option clearValues : options2.helpOptions()) {
            clearValues.clearValues();
        }
        setOptions(options2);
        this.cmd = new CommandLine();
        boolean z2 = false;
        if (strArr == null) {
            strArr = new String[0];
        }
        ListIterator listIterator = Arrays.asList(flatten(getOptions(), strArr, z)).listIterator();
        while (listIterator.hasNext()) {
            String str = (String) listIterator.next();
            if (Constants.TWO_HYPHENS.equals(str)) {
                z2 = true;
            } else if ("-".equals(str)) {
                if (z) {
                    z2 = true;
                } else {
                    this.cmd.addArg(str);
                }
            } else if (!str.startsWith("-")) {
                this.cmd.addArg(str);
                if (z) {
                    z2 = true;
                }
            } else if (!z || getOptions().hasOption(str)) {
                processOption(str, listIterator);
            } else {
                this.cmd.addArg(str);
                z2 = true;
            }
            if (z2) {
                while (listIterator.hasNext()) {
                    String str2 = (String) listIterator.next();
                    if (!Constants.TWO_HYPHENS.equals(str2)) {
                        this.cmd.addArg(str2);
                    }
                }
            }
        }
        processProperties(properties);
        checkRequiredOptions();
        return this.cmd;
    }

    /* access modifiers changed from: protected */
    public void processProperties(Properties properties) {
        if (properties != null) {
            Enumeration propertyNames = properties.propertyNames();
            while (propertyNames.hasMoreElements()) {
                String obj = propertyNames.nextElement().toString();
                if (!this.cmd.hasOption(obj)) {
                    Option option = getOptions().getOption(obj);
                    String property = properties.getProperty(obj);
                    if (!option.hasArg()) {
                        if (!"yes".equalsIgnoreCase(property) && !"true".equalsIgnoreCase(property) && !AppEventsConstants.EVENT_PARAM_VALUE_YES.equalsIgnoreCase(property)) {
                            break;
                        }
                    } else if (option.getValues() == null || option.getValues().length == 0) {
                        try {
                            option.addValueForProcessing(property);
                        } catch (RuntimeException unused) {
                        }
                    }
                    this.cmd.addOption(option);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkRequiredOptions() throws MissingOptionException {
        if (!getRequiredOptions().isEmpty()) {
            throw new MissingOptionException(getRequiredOptions());
        }
    }

    public void processArgs(Option option, ListIterator listIterator) throws ParseException {
        while (true) {
            if (!listIterator.hasNext()) {
                break;
            }
            String str = (String) listIterator.next();
            if (getOptions().hasOption(str) && str.startsWith("-")) {
                listIterator.previous();
                break;
            } else {
                try {
                    option.addValueForProcessing(Util.stripLeadingAndTrailingQuotes(str));
                } catch (RuntimeException unused) {
                    listIterator.previous();
                }
            }
        }
        if (option.getValues() == null && !option.hasOptionalArg()) {
            throw new MissingArgumentException(option);
        }
    }

    /* access modifiers changed from: protected */
    public void processOption(String str, ListIterator listIterator) throws ParseException {
        if (getOptions().hasOption(str)) {
            Option option = (Option) getOptions().getOption(str).clone();
            if (option.isRequired()) {
                getRequiredOptions().remove(option.getKey());
            }
            if (getOptions().getOptionGroup(option) != null) {
                OptionGroup optionGroup = getOptions().getOptionGroup(option);
                if (optionGroup.isRequired()) {
                    getRequiredOptions().remove(optionGroup);
                }
                optionGroup.setSelected(option);
            }
            if (option.hasArg()) {
                processArgs(option, listIterator);
            }
            this.cmd.addOption(option);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Unrecognized option: ");
        stringBuffer.append(str);
        throw new UnrecognizedOptionException(stringBuffer.toString(), str);
    }
}
