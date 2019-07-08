package com.uacf.sync.provider.internal.model;

import com.google.gson.annotations.Expose;
import com.uacf.sync.provider.sdk.model.SyncItem.Action;
import java.util.HashMap;
import java.util.Map;

public class SyncRawResponseItem {
    @Expose
    private Action action;
    @Expose
    private Map<String, Object> data = new HashMap();
    @Expose
    private String id;
    @Expose
    private String type;

    public String getId() {
        return this.id;
    }

    public Action getAction() {
        return this.action;
    }

    public String getType() {
        return this.type;
    }

    public Map<String, Object> getData() {
        return this.data;
    }
}
