package org.apache.commons.cli;

import com.myfitnesspal.shared.constants.Constants;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class OptionGroup implements Serializable {
    private static final long serialVersionUID = 1;
    private Map optionMap = new HashMap();
    private boolean required;
    private String selected;

    public Collection getOptions() {
        return this.optionMap.values();
    }

    public void setSelected(Option option) throws AlreadySelectedException {
        String str = this.selected;
        if (str == null || str.equals(option.getOpt())) {
            this.selected = option.getOpt();
            return;
        }
        throw new AlreadySelectedException(this, option);
    }

    public String getSelected() {
        return this.selected;
    }

    public boolean isRequired() {
        return this.required;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = getOptions().iterator();
        stringBuffer.append("[");
        while (it.hasNext()) {
            Option option = (Option) it.next();
            if (option.getOpt() != null) {
                stringBuffer.append("-");
                stringBuffer.append(option.getOpt());
            } else {
                stringBuffer.append(Constants.TWO_HYPHENS);
                stringBuffer.append(option.getLongOpt());
            }
            stringBuffer.append(" ");
            stringBuffer.append(option.getDescription());
            if (it.hasNext()) {
                stringBuffer.append(", ");
            }
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
