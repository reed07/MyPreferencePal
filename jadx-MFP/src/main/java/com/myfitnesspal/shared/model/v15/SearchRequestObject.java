package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;

public abstract class SearchRequestObject implements BinaryApiSerializable {
    private final int maxResults;
    private final String searchTerm;
    private final int subType;
    private final int type;

    public SearchRequestObject(int i, int i2, String str, int i3) {
        this.type = i;
        this.subType = i2;
        this.searchTerm = str;
        this.maxResults = i3;
    }

    public int getType() {
        return this.type;
    }

    public int getSubType() {
        return this.subType;
    }

    public String getSearchTerm() {
        return this.searchTerm;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.type);
        binaryEncoder.write2ByteInt(this.subType);
        binaryEncoder.writeString(this.searchTerm);
        binaryEncoder.write2ByteInt(this.maxResults);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }
}
