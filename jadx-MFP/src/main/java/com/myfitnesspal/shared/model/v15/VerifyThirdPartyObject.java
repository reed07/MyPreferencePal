package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.Strings;

public class VerifyThirdPartyObject implements BinaryApiSerializable {
    public static final BinaryCreator<VerifyThirdPartyObject> BINARY_CREATOR = new BinaryCreator<VerifyThirdPartyObject>() {
        public VerifyThirdPartyObject create(BinaryDecoder binaryDecoder) {
            VerifyThirdPartyObject verifyThirdPartyObject = new VerifyThirdPartyObject();
            verifyThirdPartyObject.readData(binaryDecoder);
            return verifyThirdPartyObject;
        }
    };
    protected static final int Failed = 2;
    protected static final int Succeeded = 3;
    protected static final int Unknown = 1;
    private int state;
    private String username;

    public void writeData(BinaryEncoder binaryEncoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.state = binaryDecoder.decode2ByteInt();
        this.username = isSuccessful() ? Strings.toString(binaryDecoder.decodeString()) : "";
    }

    public int getState() {
        return this.state;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isSuccessful() {
        return getState() == 3;
    }
}
