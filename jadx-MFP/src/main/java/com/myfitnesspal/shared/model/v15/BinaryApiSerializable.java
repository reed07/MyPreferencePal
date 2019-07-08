package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public interface BinaryApiSerializable {

    public interface BinaryCreator<T> {
        T create(BinaryDecoder binaryDecoder);
    }

    void readData(BinaryDecoder binaryDecoder);

    void writeData(BinaryEncoder binaryEncoder);
}
