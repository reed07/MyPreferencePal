package io.uacf.configuration.ui.debug.mock;

import com.brightcove.player.event.EventType;
import io.uacf.configuration.sdk.UacfConfigurationSdk;
import io.uacf.configuration.sdk.exception.UacfRequestedIncorrectTypeException;
import io.uacf.configuration.sdk.model.UacfConfiguration;
import io.uacf.configuration.sdk.model.UacfConfiguration.Builder;
import io.uacf.core.api.UacfApiException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MockUacfConfigurationSdk implements UacfConfigurationSdk {
    private Map<String, UacfConfiguration> mockConfigs = new HashMap();

    public MockUacfConfigurationSdk() {
        loadMockConfigs();
    }

    private void loadMockConfigs() {
        loadMockMetaData();
        HashMap hashMap = new HashMap();
        hashMap.put("this", new Builder().setKey("this").setStringValue("is2").build());
        hashMap.put("a", new Builder().setKey("a").setStringValue("more2").build());
        hashMap.put("advanced", new Builder().setKey("advanced").setIntValue(Integer.valueOf(12)).build());
        this.mockConfigs.put("complex_key", new Builder().setKey("complex_key").setMapValue(hashMap).build());
        this.mockConfigs.put("some_float", new Builder().setKey("some_float").setFloatValue(Float.valueOf(9.1234f)).build());
        this.mockConfigs.put("some_int", new Builder().setKey("some_int").setIntValue(Integer.valueOf(9)).build());
        this.mockConfigs.put("second_key", new Builder().setKey("second_key").setStringValue("this is the 8nd").build());
        this.mockConfigs.put("this", new Builder().setKey(EventType.TEST).setStringValue("a").build());
    }

    private void loadMockMetaData() {
        this.mockConfigs.put("metadata_version", new Builder().setKey("version").setIntValue(Integer.valueOf(9)).build());
        this.mockConfigs.put("metadata_updated_by", new Builder().setKey("updated_by").setStringValue("matthew").build());
        this.mockConfigs.put("metadata_updated_at", new Builder().setKey("updated_at").setStringValue("2017-09-13T22:23:47.087Z").build());
    }

    public void fetchConfiguration() throws UacfApiException {
        loadMockConfigs();
    }

    public void forceFetchConfiguration() throws UacfApiException {
        loadMockConfigs();
    }

    public Map<String, UacfConfiguration> getAllConfigurations() {
        HashMap hashMap = new HashMap();
        for (Entry entry : this.mockConfigs.entrySet()) {
            if (!((String) entry.getKey()).startsWith("metadata_")) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return hashMap;
    }

    public void clearConfiguration() {
        this.mockConfigs.clear();
    }

    public boolean deleteConfiguration(String str) {
        return this.mockConfigs.remove(str) != null;
    }

    public Integer getIntegerForKey(String str) throws UacfRequestedIncorrectTypeException {
        return getIntegerForKey(str, null);
    }

    public Integer getIntegerForKey(String str, Integer num) throws UacfRequestedIncorrectTypeException {
        UacfConfiguration uacfConfiguration = (UacfConfiguration) this.mockConfigs.get(str);
        if (uacfConfiguration == null) {
            return num;
        }
        if (uacfConfiguration.getIntValue() == null) {
            throwUacfIncorrectRequestTypeException(uacfConfiguration, Integer.class);
        }
        return uacfConfiguration.getIntValue();
    }

    public void setIntegerForKey(String str, Integer num) {
        if (str != null) {
            this.mockConfigs.put(str, new Builder().setKey(str).setIntValue(num).build());
        }
    }

    public void setFloatForKey(String str, Float f) {
        if (str != null) {
            this.mockConfigs.put(str, new Builder().setKey(str).setFloatValue(f).build());
        }
    }

    public String getStringForKey(String str) throws UacfRequestedIncorrectTypeException {
        return getStringForKey(str, null);
    }

    public String getStringForKey(String str, String str2) throws UacfRequestedIncorrectTypeException {
        UacfConfiguration uacfConfiguration = (UacfConfiguration) this.mockConfigs.get(str);
        if (uacfConfiguration == null) {
            return str2;
        }
        if (uacfConfiguration.getStringValue() == null) {
            throwUacfIncorrectRequestTypeException(uacfConfiguration, String.class);
        }
        return uacfConfiguration.getStringValue();
    }

    public void setStringForKey(String str, String str2) {
        if (str != null) {
            this.mockConfigs.put(str, new Builder().setKey(str).setStringValue(str2).build());
        }
    }

    public Map getMapForKey(String str) throws UacfRequestedIncorrectTypeException {
        return getMapForKey(str, null);
    }

    public Map getMapForKey(String str, Map map) throws UacfRequestedIncorrectTypeException {
        UacfConfiguration uacfConfiguration = (UacfConfiguration) this.mockConfigs.get(str);
        if (uacfConfiguration == null) {
            return map;
        }
        if (uacfConfiguration.getMapValue() == null) {
            throwUacfIncorrectRequestTypeException(uacfConfiguration, Float.class);
        }
        return uacfConfiguration.getMapValue();
    }

    public void setMapForKey(String str, Map map) {
        if (str != null) {
            this.mockConfigs.put(str, new Builder().setKey(str).setMapValue(map).build());
        }
    }

    public Integer getMetaDataVersion() {
        UacfConfiguration uacfConfiguration = (UacfConfiguration) this.mockConfigs.get("metadata_version");
        if (uacfConfiguration == null) {
            return null;
        }
        return uacfConfiguration.getIntValue();
    }

    public String getMetaDataUpdateBy() {
        UacfConfiguration uacfConfiguration = (UacfConfiguration) this.mockConfigs.get("metadata_updated_by");
        if (uacfConfiguration == null) {
            return null;
        }
        return uacfConfiguration.getStringValue();
    }

    public String getMetaDataUpdateAt() {
        UacfConfiguration uacfConfiguration = (UacfConfiguration) this.mockConfigs.get("metadata_updated_at");
        if (uacfConfiguration == null) {
            return null;
        }
        return uacfConfiguration.getStringValue();
    }

    private void throwUacfIncorrectRequestTypeException(UacfConfiguration uacfConfiguration, Class cls) throws UacfRequestedIncorrectTypeException {
        Class cls2 = uacfConfiguration.getIntValue() != null ? Integer.class : uacfConfiguration.getFloatValue() != null ? Float.class : uacfConfiguration.getStringValue() != null ? String.class : uacfConfiguration.getMapValue() != null ? Map.class : null;
        Object[] objArr = new Object[3];
        objArr[0] = cls.getSimpleName();
        objArr[1] = uacfConfiguration.getKey();
        objArr[2] = cls2 != null ? cls2.getSimpleName() : "none";
        throw new UacfRequestedIncorrectTypeException(String.format("Requested value type %s for key %s, value for key %2$s is of type %s.", objArr));
    }
}
