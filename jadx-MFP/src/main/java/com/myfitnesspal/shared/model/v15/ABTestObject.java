package com.myfitnesspal.shared.model.v15;

import com.google.gson.annotations.Expose;
import java.util.HashMap;
import java.util.Map;

public class ABTestObject {
    @Expose
    private boolean disableTracking;
    @Expose
    private String name;
    @Expose
    private Map<String, String> properties = new HashMap();
    @Expose
    private String variant;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getVariant() {
        return this.variant;
    }

    public void setVariant(String str) {
        this.variant = str;
    }

    public boolean isDisableTracking() {
        return this.disableTracking;
    }

    public void setDisableTracking(boolean z) {
        this.disableTracking = z;
    }

    public Map<String, String> getProperties() {
        return new HashMap(this.properties);
    }

    public void setProperties(Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        this.properties = map;
    }
}
