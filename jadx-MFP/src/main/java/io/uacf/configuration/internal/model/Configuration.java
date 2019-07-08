package io.uacf.configuration.internal.model;

import com.google.gson.annotations.Expose;
import java.util.Arrays;
import java.util.Map;

public class Configuration {
    @Expose
    private Float floatValue;
    @Expose
    private Integer intValue;
    @Expose
    private String key;
    @Expose
    private Map<String, Configuration> mapValue;
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
        public Map<String, Configuration> mapValue;
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

        public Builder setMapValue(Map<String, Configuration> map) {
            this.mapValue = map;
            return this;
        }

        public Configuration build() {
            return new Configuration(this);
        }
    }

    private Configuration() {
    }

    private Configuration(Builder builder) {
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
        Configuration configuration = (Configuration) obj;
        String str = this.key;
        if (str != null ? str.equals(configuration.key) : configuration.key == null) {
            Integer num = this.intValue;
            if (num != null ? num.equals(configuration.intValue) : configuration.intValue == null) {
                Float f = this.floatValue;
                if (f != null ? Float.compare(f.floatValue(), configuration.floatValue.floatValue()) == 0 : configuration.floatValue == null) {
                    String str2 = this.stringValue;
                    if (str2 != null ? str2.equals(configuration.stringValue) : configuration.stringValue == null) {
                        Map<String, Configuration> map = this.mapValue;
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

    public String toString() {
        Object obj;
        Integer num = this.intValue;
        String str = null;
        if (num != null) {
            str = String.valueOf(num);
            obj = Integer.class.getSimpleName();
        } else {
            Float f = this.floatValue;
            if (f != null) {
                str = String.valueOf(f);
                obj = Float.class.getSimpleName();
            } else {
                String str2 = this.stringValue;
                if (str2 != null) {
                    str = str2;
                    obj = String.class.getSimpleName();
                } else {
                    Map<String, Configuration> map = this.mapValue;
                    if (map != null) {
                        str = map.toString();
                        obj = Map.class.getSimpleName();
                    } else {
                        obj = null;
                    }
                }
            }
        }
        return String.format("key:\"%s\" value:\"%s\" type:\"%s\"", new Object[]{this.key, str, obj});
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

    public Map<String, Configuration> getMapValue() {
        return this.mapValue;
    }
}
