package com.uacf.core.database;

import com.uacf.core.database.ColumnMapper;
import java.util.HashMap;
import java.util.Map;

public abstract class ColumnMapper<T extends ColumnMapper> {
    private final Map<Class<?>, Map<String, String>> columnMap = new HashMap();

    protected ColumnMapper() {
    }

    public String getActualColumnName(String str) {
        return getActualColumnName(null, str);
    }

    public String getActualColumnName(Class<?> cls, String str) {
        if (cls != null) {
            Map map = (Map) this.columnMap.get(cls);
            if (map != null) {
                String str2 = (String) map.get(str);
                if (str2 != null) {
                    return str2;
                }
            }
        }
        return str;
    }
}
