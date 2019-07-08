package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class NullObject implements BinaryApiSerializable {
    public void readData(BinaryDecoder binaryDecoder) {
    }

    public void writeData(BinaryEncoder binaryEncoder) {
    }
}
