package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public class UserPropertyUpdateRequestPacket extends ApiRequestPacketImpl {
    private final Map<String, String> properties;

    public UserPropertyUpdateRequestPacket() {
        super(13);
        this.properties = new HashMap();
    }

    public UserPropertyUpdateRequestPacket(String... strArr) {
        this();
        for (int i = 0; i < strArr.length; i += 2) {
            addProperty(strArr[i], strArr[i + 1]);
        }
    }

    public UserPropertyUpdateRequestPacket(Map<String, String> map) {
        this();
        this.properties.putAll(map);
    }

    public UserPropertyUpdateRequestPacket addProperty(String str, String str2) {
        this.properties.put(str, str2);
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return CollectionUtils.size(this.properties) > 0;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeStringToStringMap(this.properties);
    }

    /* access modifiers changed from: protected */
    public void dump(StringBuilder sb) {
        sb.append(Strings.toString(this.properties));
    }
}
