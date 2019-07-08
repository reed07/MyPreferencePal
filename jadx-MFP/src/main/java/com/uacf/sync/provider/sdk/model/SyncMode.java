package com.uacf.sync.provider.sdk.model;

import com.uacf.core.util.CollectionUtils;
import java.util.Collection;
import java.util.List;

public abstract class SyncMode {
    private final List<String> filters;
    private String finishedPrefsKey;
    private String tokenPrefsKey;

    protected SyncMode(String str, String str2, List<String> list) {
        this.tokenPrefsKey = str;
        this.finishedPrefsKey = str2;
        this.filters = list;
    }

    public boolean isImport() {
        return CollectionUtils.notEmpty((Collection<?>) this.filters);
    }

    public String getTokenPrefsKey() {
        return this.tokenPrefsKey;
    }

    public String getFinishedPrefsKey() {
        return this.finishedPrefsKey;
    }

    public List<String> getFilters() {
        return this.filters;
    }
}
