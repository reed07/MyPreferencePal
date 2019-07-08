package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;

public class DissociateThirdPartyObject implements BinaryApiSerializable {
    public static final BinaryCreator<DissociateThirdPartyObject> BINARY_CREATOR = new BinaryCreator<DissociateThirdPartyObject>() {
        public DissociateThirdPartyObject create(BinaryDecoder binaryDecoder) {
            DissociateThirdPartyObject dissociateThirdPartyObject = new DissociateThirdPartyObject();
            dissociateThirdPartyObject.readData(binaryDecoder);
            return dissociateThirdPartyObject;
        }
    };
    private String hashedPassword;

    public void writeData(BinaryEncoder binaryEncoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.hashedPassword = binaryDecoder.decodeString();
    }

    public String getHashedPassword() {
        return this.hashedPassword;
    }
}
