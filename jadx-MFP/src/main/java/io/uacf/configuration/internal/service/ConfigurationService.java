package io.uacf.configuration.internal.service;

import io.uacf.configuration.internal.model.Configuration;
import io.uacf.configuration.sdk.exception.UacfRequestedIncorrectTypeException;
import io.uacf.core.api.UacfApiException;
import java.util.Map;

public interface ConfigurationService {
    void clearConfiguration();

    boolean deleteConfiguration(String str);

    void fetchConfiguration() throws UacfApiException;

    void forceFetchConfiguration() throws UacfApiException;

    Map<String, Configuration> getAllConfigurations();

    Integer getIntegerForKey(String str, Integer num) throws UacfRequestedIncorrectTypeException;

    Map getMapForKey(String str, Map map) throws UacfRequestedIncorrectTypeException;

    String getMetaDataUpdateAt();

    String getMetaDataUpdateBy();

    Integer getMetaDataVersion();

    String getStringForKey(String str, String str2) throws UacfRequestedIncorrectTypeException;

    void setFloatForKey(String str, Float f);

    void setIntegerForKey(String str, Integer num);

    void setMapForKey(String str, Map map);

    void setStringForKey(String str, String str2);
}
