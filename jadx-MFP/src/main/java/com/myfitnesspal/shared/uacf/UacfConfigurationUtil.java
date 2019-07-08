package com.myfitnesspal.shared.uacf;

import android.text.TextUtils;
import com.myfitnesspal.shared.constants.Constants.ConfigParam;
import com.myfitnesspal.shared.model.AdType;
import com.myfitnesspal.shared.model.AdUnit;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import io.uacf.configuration.internal.model.Configuration;
import io.uacf.configuration.sdk.UacfConfigurationSdk;
import io.uacf.configuration.sdk.exception.UacfRequestedIncorrectTypeException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class UacfConfigurationUtil {
    private static final boolean EMPTY_BOOLEAN_PARAM_VALUE = false;
    private static final int EMPTY_INT_PARAM_VALUE = -1;
    private static final String EMPTY_STRING_PARAM_VALUE = "";
    private Lazy<UacfConfigurationSdk> configurationSdk;

    public UacfConfigurationUtil(Lazy<UacfConfigurationSdk> lazy) {
        this.configurationSdk = lazy;
    }

    @NotNull
    public Boolean getBoolean(ConfigParam configParam) {
        return getBoolean(configParam, null);
    }

    @NotNull
    public Boolean getBoolean(ConfigParam configParam, @Nullable Boolean bool) {
        Boolean booleanValue = getBooleanValue(configParam.getPath());
        boolean z = booleanValue != null ? booleanValue.booleanValue() : bool != null ? bool.booleanValue() : false;
        return Boolean.valueOf(z);
    }

    @NotNull
    public Integer getInteger(ConfigParam configParam) {
        return getInteger(configParam, null);
    }

    @NotNull
    public Integer getInteger(ConfigParam configParam, @Nullable Integer num) {
        Integer integerValue = getIntegerValue(configParam.getPath());
        int i = integerValue != null ? integerValue.intValue() : num != null ? num.intValue() : -1;
        return Integer.valueOf(i);
    }

    @NotNull
    public String getString(ConfigParam configParam) {
        return getString(configParam, null);
    }

    @NotNull
    public String getString(ConfigParam configParam, @Nullable String str) {
        String stringValue = getStringValue(configParam.getPath());
        if (stringValue != null) {
            return stringValue;
        }
        return str != null ? str : "";
    }

    @NotNull
    public AdUnit getAdUnit(@NotNull AdType adType, @NotNull String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, boolean z) {
        String stringValue = getStringValue(str);
        String stringValue2 = !TextUtils.isEmpty(str2) ? getStringValue(str2) : null;
        String stringValue3 = !TextUtils.isEmpty(str3) ? getStringValue(str3) : null;
        if (stringValue == null) {
            stringValue = "";
        }
        AdUnit adUnit = new AdUnit(adType, stringValue, stringValue2, stringValue3, str4, z);
        return adUnit;
    }

    @Nullable
    private Integer getIntegerValue(@NotNull String str) {
        try {
            String[] split = str.split("/");
            if (split.length == 1) {
                return ((UacfConfigurationSdk) this.configurationSdk.get()).getIntegerForKey(split[0]);
            }
            Map mapFromConfig = getMapFromConfig((UacfConfigurationSdk) this.configurationSdk.get(), Arrays.asList(split).subList(0, split.length - 1));
            if (mapFromConfig == null || split.length <= 0 || !mapFromConfig.containsKey(split[split.length - 1])) {
                return null;
            }
            return ((Configuration) mapFromConfig.get(split[split.length - 1])).getIntValue();
        } catch (Exception e) {
            Ln.e(e, "Failed to get value for $path from the UacfConfigurationSdk as a Integer", new Object[0]);
            return null;
        }
    }

    private String getStringValue(@NotNull String str) {
        try {
            String[] split = str.split("/");
            if (split.length == 1) {
                return ((UacfConfigurationSdk) this.configurationSdk.get()).getStringForKey(split[0]);
            }
            Map mapFromConfig = getMapFromConfig((UacfConfigurationSdk) this.configurationSdk.get(), Arrays.asList(split).subList(0, split.length - 1));
            if (mapFromConfig.isEmpty() || split.length <= 0 || !mapFromConfig.containsKey(split[split.length - 1])) {
                return null;
            }
            return ((Configuration) mapFromConfig.get(split[split.length - 1])).getStringValue();
        } catch (Exception e) {
            Ln.e(e, "Failed to get value for $path from the UacfConfigurationSdk as a String", new Object[0]);
            return null;
        }
    }

    @Nullable
    private Boolean getBooleanValue(@NotNull String str) {
        String stringValue = getStringValue(str);
        if (getStringValue(str) != null) {
            return Boolean.valueOf(Boolean.parseBoolean(stringValue));
        }
        return null;
    }

    private Map<String, Configuration> getMapFromConfig(@NotNull UacfConfigurationSdk uacfConfigurationSdk, @NotNull List<String> list) {
        Map<String, Configuration> hashMap = new HashMap<>();
        try {
            for (String str : list) {
                if (hashMap == null || !hashMap.containsKey(str)) {
                    hashMap = uacfConfigurationSdk.getMapForKey(str);
                } else {
                    hashMap = ((Configuration) hashMap.get(str)).getMapValue();
                }
            }
        } catch (UacfRequestedIncorrectTypeException e) {
            Ln.e(e, "Couldn't get map from configuration", new Object[0]);
        }
        return hashMap;
    }
}
