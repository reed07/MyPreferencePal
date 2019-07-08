package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;

public class ExerciseEntryFromServer extends ExerciseEntryBaseObject {
    public static final BinaryCreator<ExerciseEntryFromServer> BINARY_CREATOR = new BinaryCreator<ExerciseEntryFromServer>() {
        public ExerciseEntryFromServer create(BinaryDecoder binaryDecoder) {
            ExerciseEntryFromServer exerciseEntryFromServer = new ExerciseEntryFromServer();
            exerciseEntryFromServer.readData(binaryDecoder);
            return exerciseEntryFromServer;
        }
    };

    public void writeData(BinaryEncoder binaryEncoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setMasterId(binaryDecoder.decode8ByteInt());
        setUid(binaryDecoder.decodeString());
        setExercise((ExerciseObject) binaryDecoder.decodeObject(ExerciseObjectFromServer.BINARY_CREATOR));
        setDate(binaryDecoder.decodeDate());
        setQuantity((int) binaryDecoder.decode4ByteInt());
        setSets((int) binaryDecoder.decode4ByteInt());
        setWeight(binaryDecoder.decodeFloat());
        setCalories(binaryDecoder.decodeFloat());
        setExtras(binaryDecoder.decodeStringToStringMap());
    }
}
