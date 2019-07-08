package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public class UserPropertyUpdateObject implements BinaryApiSerializable {
    public static final BinaryCreator<UserPropertyUpdateObject> BINARY_CREATOR = new BinaryCreator<UserPropertyUpdateObject>() {
        public UserPropertyUpdateObject create(BinaryDecoder binaryDecoder) {
            UserPropertyUpdateObject userPropertyUpdateObject = new UserPropertyUpdateObject();
            userPropertyUpdateObject.readData(binaryDecoder);
            return userPropertyUpdateObject;
        }
    };
    private Map<String, String> properties;

    public Map<String, String> getProperties() {
        return this.properties;
    }

    public void setProperties(Map<String, String> map) {
        this.properties = map;
    }

    public void addProperty(String str, String str2) {
        if (this.properties == null) {
            this.properties = new HashMap();
        }
        this.properties.put(str, str2);
    }

    public String getProperty(String str) {
        if (Strings.notEmpty(str)) {
            Map<String, String> map = this.properties;
            if (map != null) {
                return (String) map.get(str);
            }
        }
        return null;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeStringToStringMap(this.properties);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.properties = binaryDecoder.decodeStringToStringMap();
    }
}
