package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class EmailUniquenessCheckObject implements BinaryApiSerializable {
    public static final BinaryCreator<EmailUniquenessCheckObject> BINARY_CREATOR = new BinaryCreator<EmailUniquenessCheckObject>() {
        public EmailUniquenessCheckObject create(BinaryDecoder binaryDecoder) {
            EmailUniquenessCheckObject emailUniquenessCheckObject = new EmailUniquenessCheckObject();
            emailUniquenessCheckObject.readData(binaryDecoder);
            return emailUniquenessCheckObject;
        }
    };
    private boolean isUnique;

    public boolean isUnique() {
        return this.isUnique;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeBoolean(this.isUnique);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.isUnique = binaryDecoder.decodeBoolean();
    }
}
