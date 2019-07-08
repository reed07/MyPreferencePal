package io.uacf.configuration.sdk;

import io.uacf.configuration.sdk.exception.UacfRequestedIncorrectTypeException;
import io.uacf.configuration.sdk.model.UacfConfiguration;
import io.uacf.core.api.UacfApiException;
import java.util.Map;

public interface UacfConfigurationSdk {
    void clearConfiguration();

    boolean deleteConfiguration(String str);

    void fetchConfiguration() throws UacfApiException;

    void forceFetchConfiguration() throws UacfApiException;

    Map<String, UacfConfiguration> getAllConfigurations();

    Integer getIntegerForKey(String str) throws UacfRequestedIncorrectTypeException;

    Map getMapForKey(String str) throws UacfRequestedIncorrectTypeException;

    String getMetaDataUpdateAt();

    String getMetaDataUpdateBy();

    Integer getMetaDataVersion();

    String getStringForKey(String str) throws UacfRequestedIncorrectTypeException;

    void setFloatForKey(String str, Float f);

    void setIntegerForKey(String str, Integer num);

    void setMapForKey(String str, Map map);

    void setStringForKey(String str, String str2);
}
