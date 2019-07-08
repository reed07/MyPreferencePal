package io.uacf.configuration.sdk;

import io.uacf.configuration.internal.model.Configuration;
import io.uacf.configuration.internal.service.ConfigurationService;
import io.uacf.configuration.sdk.exception.UacfRequestedIncorrectTypeException;
import io.uacf.configuration.sdk.model.UacfConfiguration;
import io.uacf.configuration.sdk.model.UacfConfiguration.Builder;
import io.uacf.core.api.UacfApiException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class UacfConfigurationSdkImpl implements UacfConfigurationSdk {
    private final ConfigurationService configurationService;

    public UacfConfigurationSdkImpl(ConfigurationService configurationService2) {
        this.configurationService = configurationService2;
    }

    public void fetchConfiguration() throws UacfApiException {
        this.configurationService.fetchConfiguration();
    }

    public void forceFetchConfiguration() throws UacfApiException {
        this.configurationService.forceFetchConfiguration();
    }

    public Map<String, UacfConfiguration> getAllConfigurations() {
        Map allConfigurations = this.configurationService.getAllConfigurations();
        HashMap hashMap = new HashMap();
        for (Entry entry : allConfigurations.entrySet()) {
            hashMap.put(entry.getKey(), mapConfigurationToUacfConfiguration((Configuration) entry.getValue()));
        }
        return hashMap;
    }

    public void clearConfiguration() {
        this.configurationService.clearConfiguration();
    }

    public boolean deleteConfiguration(String str) {
        return this.configurationService.deleteConfiguration(str);
    }

    public Integer getIntegerForKey(String str) throws UacfRequestedIncorrectTypeException {
        return getIntegerForKey(str, null);
    }

    public Integer getIntegerForKey(String str, Integer num) throws UacfRequestedIncorrectTypeException {
        return this.configurationService.getIntegerForKey(str, num);
    }

    public void setIntegerForKey(String str, Integer num) {
        this.configurationService.setIntegerForKey(str, num);
    }

    public void setFloatForKey(String str, Float f) {
        this.configurationService.setFloatForKey(str, f);
    }

    public String getStringForKey(String str) throws UacfRequestedIncorrectTypeException {
        return getStringForKey(str, null);
    }

    public String getStringForKey(String str, String str2) throws UacfRequestedIncorrectTypeException {
        return this.configurationService.getStringForKey(str, str2);
    }

    public void setStringForKey(String str, String str2) {
        this.configurationService.setStringForKey(str, str2);
    }

    public Map getMapForKey(String str) throws UacfRequestedIncorrectTypeException {
        return getMapForKey(str, null);
    }

    public Map getMapForKey(String str, Map map) throws UacfRequestedIncorrectTypeException {
        return this.configurationService.getMapForKey(str, map);
    }

    public void setMapForKey(String str, Map map) {
        this.configurationService.setMapForKey(str, map);
    }

    public Integer getMetaDataVersion() {
        return this.configurationService.getMetaDataVersion();
    }

    public String getMetaDataUpdateBy() {
        return this.configurationService.getMetaDataUpdateBy();
    }

    public String getMetaDataUpdateAt() {
        return this.configurationService.getMetaDataUpdateAt();
    }

    private UacfConfiguration mapConfigurationToUacfConfiguration(Configuration configuration) {
        Map mapValue = configuration.getMapValue();
        HashMap hashMap = null;
        if (mapValue != null) {
            for (Entry entry : mapValue.entrySet()) {
                if (hashMap == null) {
                    hashMap = new HashMap();
                }
                hashMap.put(entry.getKey(), mapConfigurationToUacfConfiguration((Configuration) entry.getValue()));
            }
        }
        return new Builder().setKey(configuration.getKey()).setIntValue(configuration.getIntValue()).setFloatValue(configuration.getFloatValue()).setStringValue(configuration.getStringValue()).setMapValue(hashMap).build();
    }
}
