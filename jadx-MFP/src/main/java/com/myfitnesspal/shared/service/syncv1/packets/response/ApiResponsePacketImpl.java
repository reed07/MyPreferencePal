package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.google.gson.annotations.Expose;

public abstract class ApiResponsePacketImpl<T> implements ApiResponsePacket<T> {
    @Expose
    private T payload;

    public boolean processForSync() {
        return true;
    }

    public T getPayload() {
        return this.payload;
    }

    /* access modifiers changed from: protected */
    public void setPayload(T t) {
        this.payload = t;
    }
}
