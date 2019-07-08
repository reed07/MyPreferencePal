package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;

public class FoodObjectFromClient extends FoodObject {
    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write4ByteInt(getLocalId());
        binaryEncoder.writeString(getUid());
        binaryEncoder.write4ByteInt(getOriginalLocalId());
        super.writeData(binaryEncoder);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }
}
