package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class ThirdPartyAccountInformationObject implements BinaryApiSerializable {
    public static final BinaryCreator<ThirdPartyAccountInformationObject> BINARY_CREATOR = new BinaryCreator<ThirdPartyAccountInformationObject>() {
        public ThirdPartyAccountInformationObject create(BinaryDecoder binaryDecoder) {
            ThirdPartyAccountInformationObject thirdPartyAccountInformationObject = new ThirdPartyAccountInformationObject();
            thirdPartyAccountInformationObject.readData(binaryDecoder);
            return thirdPartyAccountInformationObject;
        }
    };
    private boolean isServerSideTokenValid;
    private int serviceId;
    private long thirdPartyAccountMasterId;
    private String thirdPartyAccountUid;
    private String userId;

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(this.thirdPartyAccountMasterId);
        binaryEncoder.writeString(this.thirdPartyAccountUid);
        binaryEncoder.write2ByteInt(this.serviceId);
        binaryEncoder.writeString(this.userId);
        binaryEncoder.writeBoolean(this.isServerSideTokenValid);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.thirdPartyAccountMasterId = binaryDecoder.decode8ByteInt();
        this.thirdPartyAccountUid = binaryDecoder.decodeString();
        this.serviceId = binaryDecoder.decode2ByteInt();
        this.userId = binaryDecoder.decodeString();
        this.isServerSideTokenValid = binaryDecoder.decodeBoolean();
    }
}
