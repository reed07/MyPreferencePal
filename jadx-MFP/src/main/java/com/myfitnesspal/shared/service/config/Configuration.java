package com.myfitnesspal.shared.service.config;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v15.ABTestObject;
import java.util.List;

public class Configuration {
    @Expose
    private List<ABTestObject> abtests;
    @Expose
    private AppConfig configuration;

    public AppConfig getAppConfig() {
        return this.configuration;
    }

    public void setAppConfig(AppConfig appConfig) {
        this.configuration = appConfig;
    }

    public List<ABTestObject> getAbtests() {
        return this.abtests;
    }

    public void setAbtests(List<ABTestObject> list) {
        this.abtests = list;
    }
}
