package net.minidev.json;

import java.io.IOException;

public interface JSONStreamAware {
    void writeJSONString(Appendable appendable) throws IOException;
}
