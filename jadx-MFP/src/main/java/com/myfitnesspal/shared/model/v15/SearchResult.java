package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.exception.UacfNotImplementedException;

public abstract class SearchResult<T> implements BinaryApiSerializable {
    protected T data;

    public T getData() {
        return this.data;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        throw new RuntimeException(new UacfNotImplementedException());
    }
}
