package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;

public class FoodObjectFromServer extends FoodObject {
    public static final BinaryCreator<FoodObjectFromServer> BINARY_CREATOR = new BinaryCreator<FoodObjectFromServer>() {
        public FoodObjectFromServer create(BinaryDecoder binaryDecoder) {
            FoodObjectFromServer foodObjectFromServer = new FoodObjectFromServer();
            foodObjectFromServer.readData(binaryDecoder);
            return foodObjectFromServer;
        }
    };

    public void writeData(BinaryEncoder binaryEncoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setMasterId(binaryDecoder.decode4ByteInt());
        setUid(binaryDecoder.decodeString());
        setOwnerMasterUserId(binaryDecoder.decode4ByteInt());
        super.readData(binaryDecoder);
    }
}
