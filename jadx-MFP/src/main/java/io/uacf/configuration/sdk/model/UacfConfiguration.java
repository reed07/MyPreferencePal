package io.uacf.configuration.sdk.model;

import com.google.gson.annotations.Expose;
import java.util.Arrays;
import java.util.Map;

public class UacfConfiguration {
    @Expose
    private Float floatValue;
    @Expose
    private Integer intValue;
    @Expose
    private String key;
    @Expose
    private Map<String, UacfConfiguration> mapValue;
    @Expose
    private String stringValue;

    public static class Builder {
        /* access modifiers changed from: private */
        public Float floatValue;
        /* access modifiers changed from: private */
        public Integer intValue;
        /* access modifiers changed from: private */
        public String key;
        /* access modifiers changed from: private */
        public Map<String, UacfConfiguration> mapValue;
        /* access modifiers changed from: private */
        public String stringValue;

        public Builder setKey(String str) {
            this.key = str;
            return this;
        }

        public Builder setIntValue(Integer num) {
            this.intValue = num;
            return this;
        }

        public Builder setFloatValue(Float f) {
            this.floatValue = f;
            return this;
        }

        public Builder setStringValue(String str) {
            this.stringValue = str;
            return this;
        }

        public Builder setMapValue(Map<String, UacfConfiguration> map) {
            this.mapValue = map;
            return this;
        }

        public UacfConfiguration build() {
            return new UacfConfiguration(this);
        }
    }

    private UacfConfiguration() {
    }

    private UacfConfiguration(Builder builder) {
        this.key = builder.key;
        this.intValue = builder.intValue;
        this.floatValue = builder.floatValue;
        this.stringValue = builder.stringValue;
        this.mapValue = builder.mapValue;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UacfConfiguration uacfConfiguration = (UacfConfiguration) obj;
        String str = this.key;
        if (str != null ? str.equals(uacfConfiguration.key) : uacfConfiguration.key == null) {
            Integer num = this.intValue;
            if (num != null ? num.equals(uacfConfiguration.intValue) : uacfConfiguration.intValue == null) {
                Float f = this.floatValue;
                if (f != null ? Float.compare(f.floatValue(), uacfConfiguration.floatValue.floatValue()) == 0 : uacfConfiguration.floatValue == null) {
                    String str2 = this.stringValue;
                    if (str2 != null ? str2.equals(uacfConfiguration.stringValue) : uacfConfiguration.stringValue == null) {
                        Map<String, UacfConfiguration> map = this.mapValue;
                        if (map != null) {
                        }
                    }
                }
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.key, this.intValue, this.floatValue, this.stringValue, this.mapValue});
    }

    public String getKey() {
        return this.key;
    }

    public Integer getIntValue() {
        return this.intValue;
    }

    public Float getFloatValue() {
        return this.floatValue;
    }

    public String getStringValue() {
        return this.stringValue;
    }

    public Map<String, UacfConfiguration> getMapValue() {
        return this.mapValue;
    }
}
