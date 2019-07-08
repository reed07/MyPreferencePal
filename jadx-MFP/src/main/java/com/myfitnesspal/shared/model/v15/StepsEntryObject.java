package com.myfitnesspal.shared.model.v15;

import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import java.util.Date;

public class StepsEntryObject extends BaseObject {
    private long associatedCalorieAdjustmentEntryMasterId;
    private String associatedCalorieAdjustmentEntryUid;
    private float calories;
    private String clientId;
    private Date date;
    private String deviceId;
    private int stepGoal;
    private int stepSource;
    private int steps;

    public static final class StepSources {
        public static final int NONPRIMARY = 0;
        public static final int PRIMARY = 1;
    }

    public StepsEntryObject() {
    }

    public StepsEntryObject(int i, String str, String str2) {
        this();
        this.steps = i;
        this.clientId = str;
        this.deviceId = str2;
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(getMasterId());
        binaryEncoder.writeString(getUid());
        binaryEncoder.writeDate(this.date);
        binaryEncoder.write8ByteInt(this.associatedCalorieAdjustmentEntryMasterId);
        binaryEncoder.writeString(this.associatedCalorieAdjustmentEntryUid);
        binaryEncoder.write4ByteInt((long) this.steps);
        binaryEncoder.writeFloat(this.calories);
        binaryEncoder.writeString(this.clientId);
        binaryEncoder.writeString(this.deviceId);
        binaryEncoder.write4ByteInt((long) this.stepGoal);
        binaryEncoder.write2ByteInt(this.stepSource);
    }

    public void readData(BinaryDecoder binaryDecoder) {
        setMasterId(binaryDecoder.decode8ByteInt());
        setUid(binaryDecoder.decodeString());
        this.date = binaryDecoder.decodeDate();
        this.associatedCalorieAdjustmentEntryMasterId = binaryDecoder.decode8ByteInt();
        this.associatedCalorieAdjustmentEntryUid = binaryDecoder.decodeString();
        this.steps = (int) binaryDecoder.decode4ByteInt();
        this.calories = binaryDecoder.decodeFloat();
        this.clientId = binaryDecoder.decodeString();
        this.deviceId = binaryDecoder.decodeString();
        this.stepGoal = (int) binaryDecoder.decode4ByteInt();
        this.stepSource = binaryDecoder.decode2ByteInt();
    }

    public long getAssociatedCalorieAdjustmentEntryMasterId() {
        return this.associatedCalorieAdjustmentEntryMasterId;
    }

    public void setAssociatedCalorieAdjustmentEntryMasterId(long j) {
        this.associatedCalorieAdjustmentEntryMasterId = j;
    }

    public String getAssociatedCalorieAdjustmentEntryUid() {
        return this.associatedCalorieAdjustmentEntryUid;
    }

    public void setAssociatedCalorieAdjustmentEntryUid(String str) {
        this.associatedCalorieAdjustmentEntryUid = str;
    }

    public float getCalories() {
        return this.calories;
    }

    public void setCalories(float f) {
        this.calories = f;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public int getSteps() {
        return this.steps;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public int getStepGoal() {
        return this.stepGoal;
    }

    public void setStepGoal(int i) {
        this.stepGoal = i;
    }

    public int getStepSource() {
        return this.stepSource;
    }

    public void setStepSource(int i) {
        this.stepSource = i;
    }

    public boolean isPrimary() {
        return this.stepSource == 1;
    }
}
