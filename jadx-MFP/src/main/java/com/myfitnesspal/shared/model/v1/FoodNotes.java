package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.myfitnesspal.shared.db.table.FoodNotesTable.Columns;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable;
import com.myfitnesspal.shared.model.v15.BinaryApiSerializable.BinaryCreator;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.database.CursorMapper;

public class FoodNotes implements Parcelable, BinaryApiSerializable {
    public static final BinaryCreator<FoodNotes> BINARY_CREATOR = new BinaryCreator<FoodNotes>() {
        public FoodNotes create(BinaryDecoder binaryDecoder) {
            FoodNotes foodNotes = new FoodNotes();
            foodNotes.readData(binaryDecoder);
            return foodNotes;
        }
    };
    public static final Creator<FoodNotes> CREATOR = new Creator<FoodNotes>() {
        public FoodNotes[] newArray(int i) {
            return new FoodNotes[i];
        }

        public FoodNotes createFromParcel(Parcel parcel) {
            return new FoodNotes(parcel);
        }
    };
    private long foodLocalId;
    private long foodMasterId;
    private String foodUid;
    private long localId;
    private long masterId;
    private String notes;
    private long originalFoodMasterId;
    private String originalFoodUid;
    private String uid;
    private long userId;

    public int describeContents() {
        return 0;
    }

    public FoodNotes() {
    }

    public FoodNotes(Parcel parcel) {
        this.localId = parcel.readLong();
        this.masterId = parcel.readLong();
        this.uid = parcel.readString();
        this.foodLocalId = parcel.readLong();
        this.foodMasterId = parcel.readLong();
        this.foodUid = parcel.readString();
        this.originalFoodMasterId = parcel.readLong();
        this.originalFoodUid = parcel.readString();
        this.notes = parcel.readString();
        this.userId = parcel.readLong();
    }

    public FoodNotes(CursorMapper cursorMapper) {
        this.localId = cursorMapper.getLong("id");
        this.masterId = cursorMapper.getLong("master_id");
        this.uid = cursorMapper.getString("uid");
        this.foodLocalId = cursorMapper.getLong("food_local_id");
        this.foodMasterId = cursorMapper.getLong("food_master_id");
        this.foodUid = cursorMapper.getString("food_uid");
        this.originalFoodMasterId = cursorMapper.getLong("original_food_master_id");
        this.originalFoodUid = cursorMapper.getString("original_food_uid");
        this.notes = cursorMapper.getString(Columns.NOTES);
        this.userId = cursorMapper.getLong("user_id");
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.masterId = binaryDecoder.decode8ByteInt();
        this.uid = binaryDecoder.decodeString();
        this.foodLocalId = binaryDecoder.decode8ByteInt();
        this.foodMasterId = binaryDecoder.decode8ByteInt();
        this.foodUid = binaryDecoder.decodeString();
        this.originalFoodMasterId = binaryDecoder.decode8ByteInt();
        this.originalFoodUid = binaryDecoder.decodeString();
        this.notes = binaryDecoder.decodeString();
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(this.localId);
        binaryEncoder.writeString(this.uid);
        binaryEncoder.write8ByteInt(this.foodLocalId);
        binaryEncoder.write8ByteInt(this.foodMasterId);
        binaryEncoder.writeString(this.foodUid);
        binaryEncoder.write8ByteInt(this.originalFoodMasterId);
        binaryEncoder.writeString(this.originalFoodUid);
        binaryEncoder.writeString(this.notes);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.localId);
        parcel.writeLong(this.masterId);
        parcel.writeString(this.uid);
        parcel.writeLong(this.foodLocalId);
        parcel.writeLong(this.foodMasterId);
        parcel.writeString(this.foodUid);
        parcel.writeLong(this.originalFoodMasterId);
        parcel.writeString(this.originalFoodUid);
        parcel.writeString(this.notes);
        parcel.writeLong(this.userId);
    }

    public long getLocalId() {
        return this.localId;
    }

    public void setLocalId(long j) {
        this.localId = j;
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

    public long getFoodLocalId() {
        return this.foodLocalId;
    }

    public void setFoodLocalId(long j) {
        this.foodLocalId = j;
    }

    public long getFoodMasterId() {
        return this.foodMasterId;
    }

    public void setFoodMasterId(long j) {
        this.foodMasterId = j;
    }

    public String getFoodUid() {
        return this.foodUid;
    }

    public void setFoodUid(String str) {
        this.foodUid = str;
    }

    public long getOriginalFoodMasterId() {
        return this.originalFoodMasterId;
    }

    public void setOriginalFoodMasterId(long j) {
        this.originalFoodMasterId = j;
    }

    public String getOriginalFoodUid() {
        return this.originalFoodUid;
    }

    public void setOriginalFoodUid(String str) {
        this.originalFoodUid = str;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String str) {
        this.notes = str;
    }

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long j) {
        this.userId = j;
    }
}
