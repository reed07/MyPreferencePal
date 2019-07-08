package net.minidev.json;

import java.io.IOException;

public interface JSONStreamAwareEx extends JSONStreamAware {
    void writeJSONString(Appendable appendable, JSONStyle jSONStyle) throws IOException;
}
