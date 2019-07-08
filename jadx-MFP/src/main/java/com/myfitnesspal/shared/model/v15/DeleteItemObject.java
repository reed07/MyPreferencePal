package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;

public class DeleteItemObject implements BinaryApiSerializable {
    public static final BinaryCreator<DeleteItemObject> BINARY_CREATOR = new BinaryCreator<DeleteItemObject>() {
        public DeleteItemObject create(BinaryDecoder binaryDecoder) {
            DeleteItemObject deleteItemObject = new DeleteItemObject();
            deleteItemObject.readData(binaryDecoder);
            return deleteItemObject;
        }
    };
    int destructionType;
    int itemType;
    long masterId;
    String uid;

    public static final class DestructionTypes {
        public static final int DELETED_DUE_TO_EDIT = 1;
        public static final int DELETED_FROM_SENT_MAIL = 3;
        public static final int DESTROYED = 2;
    }

    public static final class ItemTypes {
        public static final int EXERCISE = 2;
        public static final int EXERCISE_ENTRY = 5;
        public static final int FOOD = 1;
        public static final int FOOD_ENTRY = 4;
        public static final int NONE = 0;
        public static final int REMINDER = 20;
    }

    public int getItemType() {
        return this.itemType;
    }

    public void setItemType(int i) {
        this.itemType = i;
    }

    public long getMasterId() {
        return this.masterId;
    }

    public void setMasterId(long j) {
        this.masterId = j;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public int getDestructionType() {
        return this.destructionType;
    }

    public void setDestructionType(int i) {
        this.destructionType = i;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write2ByteInt(this.itemType);
        binaryEncoder.write8ByteInt(this.masterId);
        binaryEncoder.writeString(this.uid);
        binaryEncoder.write2ByteInt(this.destructionType);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.itemType = binaryDecoder.decode2ByteInt();
        this.masterId = binaryDecoder.decode8ByteInt();
        this.uid = binaryDecoder.decodeString();
        this.destructionType = binaryDecoder.decode2ByteInt();
    }
}
