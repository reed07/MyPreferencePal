package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.util.LogicUtils;

public abstract class ExerciseObject extends BaseObject {
    private String description;
    private int flags;
    private float mets;
    private String originalUid;
    private long ownerLocalUserId;
    private long ownerMasterUserId;
    private int type;

    public static final class Flags {
        public static final int DELETED = 2;
        public static final int DONT_FORCE_CREATION = 4;
        public static final int IS_CALORIE_ADJUSTMENT = 8;
        public static final int PUBLIC = 1;
    }

    public static final class Types {
        public static final int CARDIO = 0;
        public static final int STRENGTH = 1;
    }

    public String getOriginalUid() {
        return this.originalUid;
    }

    public void setOriginalUid(String str) {
        this.originalUid = str;
    }

    public long getOwnerLocalUserId() {
        return this.ownerLocalUserId;
    }

    public void setOwnerLocalUserId(long j) {
        this.ownerLocalUserId = j;
    }

    public long getOwnerMasterUserId() {
        return this.ownerMasterUserId;
    }

    public void setOwnerMasterUserId(long j) {
        this.ownerMasterUserId = j;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public int getFlags() {
        return this.flags;
    }

    public void setFlags(int i) {
        this.flags = i;
    }

    public boolean isDeleted() {
        return LogicUtils.checkFlags((long) this.flags, 2);
    }

    public void setIsDeleted(boolean z) {
        this.flags = (int) (z ? LogicUtils.setFlags((long) this.flags, 2) : LogicUtils.clearFlags((long) this.flags, 2));
    }

    public boolean isPublic() {
        return LogicUtils.checkFlags((long) this.flags, 1);
    }

    public void setIsPublic(boolean z) {
        this.flags = (int) (z ? LogicUtils.setFlags((long) this.flags, 1) : LogicUtils.clearFlags((long) this.flags, 1));
    }

    public boolean isCalorieAdjustment() {
        return LogicUtils.checkFlags((long) this.flags, 8);
    }

    public void setIsCalorieAdjustment(boolean z) {
        this.flags = (int) (z ? LogicUtils.setFlags((long) this.flags, 8) : LogicUtils.clearFlags((long) this.flags, 8));
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public float getMets() {
        return this.mets;
    }

    public void setMets(float f) {
        this.mets = f;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write4ByteInt(0);
        binaryEncoder.writeString(this.originalUid);
        binaryEncoder.write2ByteInt(this.type);
        binaryEncoder.writeString(this.description);
        binaryEncoder.write4ByteInt((long) this.flags);
        binaryEncoder.writeFloat(this.mets);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        binaryDecoder.decode4ByteInt();
        this.originalUid = binaryDecoder.decodeString();
        this.type = binaryDecoder.decode2ByteInt();
        this.description = binaryDecoder.decodeString();
        this.flags = (int) binaryDecoder.decode4ByteInt();
        this.mets = binaryDecoder.decodeFloat();
    }
}
