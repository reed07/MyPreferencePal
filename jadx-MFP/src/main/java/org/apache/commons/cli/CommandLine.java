package org.apache.commons.cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CommandLine implements Serializable {
    private static final long serialVersionUID = 1;
    private List args = new LinkedList();
    private List options = new ArrayList();

    CommandLine() {
    }

    public boolean hasOption(String str) {
        return this.options.contains(resolveOption(str));
    }

    private Option resolveOption(String str) {
        String stripLeadingHyphens = Util.stripLeadingHyphens(str);
        for (Option option : this.options) {
            if (stripLeadingHyphens.equals(option.getOpt())) {
                return option;
            }
            if (stripLeadingHyphens.equals(option.getLongOpt())) {
                return option;
            }
        }
        return null;
    }

    public List getArgList() {
        return this.args;
    }

    /* access modifiers changed from: 0000 */
    public void addArg(String str) {
        this.args.add(str);
    }

    /* access modifiers changed from: 0000 */
    public void addOption(Option option) {
        this.options.add(option);
    }
}
