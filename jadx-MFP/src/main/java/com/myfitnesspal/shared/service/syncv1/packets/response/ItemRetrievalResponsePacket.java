package com.myfitnesspal.shared.service.syncv1.packets.response;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;

public class ItemRetrievalResponsePacket extends ApiResponsePacketImpl {
    private int itemCount;
    private int limit;
    private int offset;
    private int resultType;
    private int totalNumberOfItems;

    public int getPacketType() {
        return 106;
    }

    public boolean processForSync() {
        return false;
    }

    public int getTotalNumberOfItems() {
        return this.totalNumberOfItems;
    }

    public void setTotalNumberOfItems(int i) {
        this.totalNumberOfItems = i;
    }

    public int getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(int i) {
        this.itemCount = i;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int i) {
        this.limit = i;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public int getResultType() {
        return this.resultType;
    }

    public void setResultType(int i) {
        this.resultType = i;
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.totalNumberOfItems = (int) binaryDecoder.decode4ByteInt();
        this.itemCount = (int) binaryDecoder.decode4ByteInt();
        this.limit = (int) binaryDecoder.decode4ByteInt();
        this.offset = (int) binaryDecoder.decode4ByteInt();
        this.resultType = (int) binaryDecoder.decode4ByteInt();
    }
}
