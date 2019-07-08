package com.myfitnesspal.shared.model.v1;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.StringRes;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.service.syncv1.BinaryDecoder;
import com.myfitnesspal.shared.service.syncv1.BinaryEncoder;
import com.uacf.core.database.CursorMapper;
import java.util.HashMap;
import java.util.Map;

public class FoodPermission implements Parcelable {
    public static final Creator<FoodPermission> CREATOR = new Creator<FoodPermission>() {
        public FoodPermission createFromParcel(Parcel parcel) {
            return new FoodPermission(parcel);
        }

        public FoodPermission[] newArray(int i) {
            return new FoodPermission[i];
        }
    };
    private long foodLocalId;
    private long foodMasterId;
    private String foodUid;
    private long id;
    private long masterId;
    private long originalFoodMasterId;
    private String originalFoodUid;
    private long permissionValue;
    private long userId;

    public enum Permission {
        Private(0, R.string.only_me, R.string.private_info),
        Friends(1, R.string.diary_sharing_friends_only, R.string.friends_info),
        Community(2, R.string.diary_sharing_community, 0),
        Public(4, R.string.diary_sharing_public, R.string.public_info);
        
        private static final Map<Long, Permission> LOOKUP = null;
        private final int infoResId;
        private final int nameResId;
        private final long value;

        static {
            int i;
            Permission[] values;
            LOOKUP = new HashMap();
            for (Permission permission : values()) {
                LOOKUP.put(Long.valueOf(permission.getValue()), permission);
            }
        }

        public static Permission get(long j) {
            return (Permission) LOOKUP.get(Long.valueOf(j));
        }

        private Permission(long j, int i, int i2) {
            this.value = j;
            this.nameResId = i;
            this.infoResId = i2;
        }

        public long getValue() {
            return this.value;
        }

        @StringRes
        public int getNameResId() {
            return this.nameResId;
        }

        @StringRes
        public int getInfoResId() {
            return this.infoResId;
        }
    }

    public int describeContents() {
        return 0;
    }

    public FoodPermission() {
    }

    public FoodPermission(Parcel parcel) {
        this.id = parcel.readLong();
        this.masterId = parcel.readLong();
        this.foodLocalId = parcel.readLong();
        this.foodMasterId = parcel.readLong();
        this.foodUid = parcel.readString();
        this.originalFoodMasterId = parcel.readLong();
        this.originalFoodUid = parcel.readString();
        this.userId = parcel.readLong();
        this.permissionValue = parcel.readLong();
    }

    public FoodPermission(CursorMapper cursorMapper) {
        this.id = cursorMapper.getLong("id");
        this.masterId = cursorMapper.getLong("master_id");
        this.foodLocalId = cursorMapper.getLong("food_local_id");
        this.foodMasterId = cursorMapper.getLong("food_master_id");
        this.foodUid = cursorMapper.getString("food_uid");
        this.originalFoodMasterId = cursorMapper.getLong("original_food_master_id");
        this.originalFoodUid = cursorMapper.getString("original_food_uid");
        this.userId = cursorMapper.getLong("user_id");
        this.permissionValue = cursorMapper.getLong("permissions");
    }

    public void readData(BinaryDecoder binaryDecoder) {
        this.masterId = binaryDecoder.decode8ByteInt();
        this.foodLocalId = binaryDecoder.decode8ByteInt();
        this.foodMasterId = binaryDecoder.decode8ByteInt();
        this.foodUid = binaryDecoder.decodeString();
        this.originalFoodMasterId = binaryDecoder.decode8ByteInt();
        this.originalFoodUid = binaryDecoder.decodeString();
        this.permissionValue = binaryDecoder.decode4ByteInt();
    }

    public void writeData(BinaryEncoder binaryEncoder) {
        binaryEncoder.write8ByteInt(this.id);
        binaryEncoder.write8ByteInt(this.foodLocalId);
        binaryEncoder.write8ByteInt(this.foodMasterId);
        binaryEncoder.writeString(this.foodUid);
        binaryEncoder.write8ByteInt(this.originalFoodMasterId);
        binaryEncoder.writeString(this.originalFoodUid);
        binaryEncoder.write4ByteInt(this.permissionValue);
    }

    public long getId() {
        return this.id;
    }

    public long getMasterId() {
        return this.masterId;
    }

    public long getFoodLocalId() {
        return this.foodLocalId;
    }

    public long getFoodMasterId() {
        return this.foodMasterId;
    }

    public String getFoodUid() {
        return this.foodUid;
    }

    public long getOriginalFoodMasterId() {
        return this.originalFoodMasterId;
    }

    public String getOriginalFoodUid() {
        return this.originalFoodUid;
    }

    public long getUserId() {
        return this.userId;
    }

    public long getPermissionValue() {
        return this.permissionValue;
    }

    public void setId(long j) {
        this.id = j;
    }

    public void setMasterId(long j) {
        this.masterId = j;
    }

    public void setFoodLocalId(long j) {
        this.foodLocalId = j;
    }

    public void setFoodMasterId(long j) {
        this.foodMasterId = j;
    }

    public void setFoodUid(String str) {
        this.foodUid = str;
    }

    public void setOriginalFoodMasterId(long j) {
        this.originalFoodMasterId = j;
    }

    public void setOriginalFoodUid(String str) {
        this.originalFoodUid = str;
    }

    public void setUserId(long j) {
        this.userId = j;
    }

    public void setPermissionValue(long j) {
        this.permissionValue = j;
    }

    public boolean hasMasterId() {
        return this.masterId > 0;
    }

    public boolean hasFoodLocalId() {
        return this.foodLocalId > 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.id);
        parcel.writeLong(this.masterId);
        parcel.writeLong(this.foodLocalId);
        parcel.writeLong(this.foodMasterId);
        parcel.writeString(this.foodUid);
        parcel.writeLong(this.originalFoodMasterId);
        parcel.writeString(this.originalFoodUid);
        parcel.writeLong(this.userId);
        parcel.writeLong(this.permissionValue);
    }
}
