package com.myfitnesspal.shared.service.syncv1.packets.request;

import com.myfitnesspal.shared.model.v15.SearchRequestObject;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public abstract class SearchRequestPacket extends ApiRequestPacketImpl {
    private final SearchRequestObject searchRequestObject;

    protected SearchRequestPacket(SearchRequestObject searchRequestObject2) {
        super(18);
        this.searchRequestObject = searchRequestObject2;
    }

    public SearchRequestObject getSearchRequestObject() {
        return this.searchRequestObject;
    }

    /* access modifiers changed from: protected */
    public boolean validatePacketData() {
        return this.searchRequestObject != null;
    }

    /* access modifiers changed from: protected */
    public void writeDataInternal(BinaryEncoder binaryEncoder) {
        this.searchRequestObject.writeData(binaryEncoder);
    }
}
