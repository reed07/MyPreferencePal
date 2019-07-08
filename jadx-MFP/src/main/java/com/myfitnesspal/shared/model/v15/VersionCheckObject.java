package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import java.util.List;

public class VersionCheckObject implements BinaryApiSerializable {
    private List<String> itemTypesToCheck;

    public List<String> getItemTypesToCheck() {
        return this.itemTypesToCheck;
    }

    public void setItemTypesToCheck(List<String> list) {
        this.itemTypesToCheck = list;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.writeStringList(this.itemTypesToCheck);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.itemTypesToCheck = binaryDecoder.decodeStringList();
    }
}
