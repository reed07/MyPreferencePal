package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class ExerciseObjectFromServer extends ExerciseObject {
    public static final BinaryCreator<ExerciseObjectFromServer> BINARY_CREATOR = new BinaryCreator<ExerciseObjectFromServer>() {
        public ExerciseObjectFromServer create(BinaryDecoder binaryDecoder) {
            ExerciseObjectFromServer exerciseObjectFromServer = new ExerciseObjectFromServer();
            exerciseObjectFromServer.readData(binaryDecoder);
            return exerciseObjectFromServer;
        }
    };

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write4ByteInt(getMasterId());
        binaryEncoder.writeString(getUid());
        binaryEncoder.write4ByteInt(getOwnerMasterUserId());
        super.writeData(binaryEncoder);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setMasterId(binaryDecoder.decode4ByteInt());
        setUid(binaryDecoder.decodeString());
        setOwnerMasterUserId(binaryDecoder.decode4ByteInt());
        super.readData(binaryDecoder);
    }
}
