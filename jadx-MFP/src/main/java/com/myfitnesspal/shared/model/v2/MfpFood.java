package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatchData;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.constants.Constants.MealTypeName;
import com.myfitnesspal.shared.db.table.FoodsTable.Columns;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.FoodPortionMapperImpl;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.uacf.core.database.ContentValuesMapper;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MfpFood extends MfpVersionedDatabaseObjectV2 implements MfpMenuItemMatchData, SearchResultItem {
    public static final Creator<MfpFood> CREATOR = new Creator<MfpFood>() {
        public MfpFood createFromParcel(Parcel parcel) {
            return new MfpFood(parcel);
        }

        public MfpFood[] newArray(int i) {
            return new MfpFood[i];
        }
    };
    public static final int DEFAULT_SELECTED_SERVING_SIZE_INDEX = 0;
    public static final float DEFAULT_SERVING_AMOUNT = 1.0f;
    private static final FoodMapper FOOD_MAPPER = new FoodMapperImpl(new FoodPortionMapperImpl());
    @Expose
    private String brandName;
    @Expose(deserialize = true, serialize = false)
    private int confirmations;
    @Expose
    private String description;
    private FoodPermission foodPermission;
    @SerializedName("public")
    @Expose(deserialize = true, serialize = false)
    private Boolean isPublic;
    @Expose
    private MfpNutritionalContents nutritionalContents;
    private long promotedFromMasterId;
    private String promotedFromUid;
    private float selectedServingAmount = 1.0f;
    private int selectedServingSizeIndex = 0;
    @Expose
    private List<MfpServingSize> servingSizes;
    @Expose(deserialize = true, serialize = false)
    private boolean verified;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpFood> {
    }

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public String getSyncResourceName() {
        return "food";
    }

    public MfpFood() {
    }

    public MfpFood(Parcel parcel) {
        super(parcel);
        this.description = parcel.readString();
        this.brandName = parcel.readString();
        this.nutritionalContents = (MfpNutritionalContents) parcel.readValue(MfpNutritionalContents.class.getClassLoader());
        this.confirmations = parcel.readInt();
        this.isPublic = Boolean.valueOf(ParcelableUtil.readBoolean(parcel));
        if (parcel.readByte() == 0) {
            this.servingSizes = null;
        } else {
            this.servingSizes = new ArrayList();
            parcel.readList(this.servingSizes, MfpServingSize.class.getClassLoader());
        }
        this.verified = ParcelableUtil.readBoolean(parcel);
        this.selectedServingSizeIndex = parcel.readInt();
        this.selectedServingAmount = parcel.readFloat();
        this.promotedFromMasterId = parcel.readLong();
        this.promotedFromUid = parcel.readString();
        this.foodPermission = (FoodPermission) parcel.readParcelable(FoodPermission.class.getClassLoader());
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getBrandName() {
        return this.brandName;
    }

    public void setBrandName(String str) {
        this.brandName = str;
    }

    public MfpNutritionalContents getNutritionalContents() {
        return this.nutritionalContents;
    }

    public void setNutritionalContents(MfpNutritionalContents mfpNutritionalContents) {
        this.nutritionalContents = mfpNutritionalContents;
    }

    public List<MfpServingSize> getServingSizes() {
        return this.servingSizes;
    }

    public void setServingSizes(List<MfpServingSize> list) {
        this.servingSizes = list;
    }

    public Boolean isPublic() {
        Boolean bool = this.isPublic;
        return Boolean.valueOf(bool == null ? false : bool.booleanValue());
    }

    public boolean isPublicSafe() {
        Boolean bool = this.isPublic;
        return bool != null && bool.booleanValue();
    }

    public void setPublic(boolean z) {
        this.isPublic = Boolean.valueOf(z);
    }

    public boolean getVerified() {
        return this.verified;
    }

    public void setVerified(boolean z) {
        this.verified = z;
    }

    public String brandAndDescription() {
        if (!hasBrand()) {
            return getDescription();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getDescription());
        sb.append(" (");
        sb.append(this.brandName);
        sb.append(")");
        return sb.toString();
    }

    public boolean hasBrand() {
        String str = this.brandName;
        return str != null && str.length() > 0;
    }

    public int getSelectedServingSizeIndex() {
        return this.selectedServingSizeIndex;
    }

    public void setSelectedServingSizeIndex(int i) {
        this.selectedServingSizeIndex = i;
    }

    public float getSelectedServingAmount() {
        return this.selectedServingAmount;
    }

    public void setSelectedServingAmount(float f) {
        this.selectedServingAmount = f;
    }

    public long getPromotedFromMasterId() {
        return this.promotedFromMasterId;
    }

    public void setPromotedFromMasterId(long j) {
        this.promotedFromMasterId = j;
    }

    public String getPromotedFromUid() {
        return this.promotedFromUid;
    }

    public void setPromotedFromUid(String str) {
        this.promotedFromUid = str;
    }

    public MfpServingSize getSelectedServingSize() {
        return (MfpServingSize) this.servingSizes.get(this.selectedServingSizeIndex);
    }

    public FoodPermission getFoodPermission() {
        return this.foodPermission;
    }

    public void setFoodPermission(FoodPermission foodPermission2) {
        this.foodPermission = foodPermission2;
    }

    public boolean isQuickAddOfAnySort() {
        return Strings.equalsIgnoreCase(this.description, MealTypeName.QUICK_ADD) || Strings.equalsIgnoreCase(this.description, MealTypeName.LEGACY_QUICK_ADDED_CALORIES);
    }

    public FoodEntry toFoodEntry(User user, String str, Date date) {
        return FOOD_MAPPER.mapFromMfpFood(this, user).toFoodEntry(str, date, this.selectedServingSizeIndex, this.selectedServingAmount);
    }

    public Food toFood(User user) {
        return FOOD_MAPPER.mapFromMfpFood(this, user);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        MfpFood mfpFood = (MfpFood) obj;
        if (this.confirmations != mfpFood.confirmations || this.verified != mfpFood.verified || this.promotedFromMasterId != mfpFood.promotedFromMasterId || this.selectedServingSizeIndex != mfpFood.selectedServingSizeIndex || Float.compare(mfpFood.selectedServingAmount, this.selectedServingAmount) != 0) {
            return false;
        }
        String str = this.description;
        if (str == null ? mfpFood.description != null : !str.equals(mfpFood.description)) {
            return false;
        }
        String str2 = this.brandName;
        if (str2 == null ? mfpFood.brandName != null : !str2.equals(mfpFood.brandName)) {
            return false;
        }
        MfpNutritionalContents mfpNutritionalContents = this.nutritionalContents;
        if (mfpNutritionalContents == null ? mfpFood.nutritionalContents != null : !mfpNutritionalContents.equals(mfpFood.nutritionalContents)) {
            return false;
        }
        List<MfpServingSize> list = this.servingSizes;
        if (list == null ? mfpFood.servingSizes != null : !list.equals(mfpFood.servingSizes)) {
            return false;
        }
        if (isPublicSafe() != mfpFood.isPublicSafe()) {
            return false;
        }
        String str3 = this.promotedFromUid;
        if (str3 == null ? mfpFood.promotedFromUid != null : !str3.equals(mfpFood.promotedFromUid)) {
            return false;
        }
        FoodPermission foodPermission2 = this.foodPermission;
        if (foodPermission2 != null) {
            z = foodPermission2.equals(mfpFood.foodPermission);
        } else if (mfpFood.foodPermission != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int hashCode = super.hashCode() * 31;
        String str = this.description;
        int i = 0;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.brandName;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        MfpNutritionalContents mfpNutritionalContents = this.nutritionalContents;
        int hashCode4 = (hashCode3 + (mfpNutritionalContents != null ? mfpNutritionalContents.hashCode() : 0)) * 31;
        List<MfpServingSize> list = this.servingSizes;
        int hashCode5 = (((((hashCode4 + (list != null ? list.hashCode() : 0)) * 31) + this.confirmations) * 31) + (this.verified ? 1 : 0)) * 31;
        Boolean bool = this.isPublic;
        int hashCode6 = (hashCode5 + (bool != null ? bool.hashCode() : 0)) * 31;
        long j = this.promotedFromMasterId;
        int i2 = (hashCode6 + ((int) (j ^ (j >>> 32)))) * 31;
        String str3 = this.promotedFromUid;
        int hashCode7 = (i2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        FoodPermission foodPermission2 = this.foodPermission;
        int hashCode8 = (((hashCode7 + (foodPermission2 != null ? foodPermission2.hashCode() : 0)) * 31) + this.selectedServingSizeIndex) * 31;
        float f = this.selectedServingAmount;
        if (f != BitmapDescriptorFactory.HUE_RED) {
            i = Float.floatToIntBits(f);
        }
        return hashCode8 + i;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.description);
        parcel.writeString(this.brandName);
        parcel.writeValue(this.nutritionalContents);
        parcel.writeInt(this.confirmations);
        ParcelableUtil.writeBoolean(parcel, isPublicSafe());
        if (this.servingSizes == null) {
            parcel.writeByte(0);
        } else {
            parcel.writeByte(1);
            parcel.writeList(this.servingSizes);
        }
        ParcelableUtil.writeBoolean(parcel, this.verified);
        parcel.writeInt(this.selectedServingSizeIndex);
        parcel.writeFloat(this.selectedServingAmount);
        parcel.writeLong(this.promotedFromMasterId);
        parcel.writeString(this.promotedFromUid);
        parcel.writeParcelable(this.foodPermission, i);
    }

    /* access modifiers changed from: protected */
    public void readV1Information(CursorMapper cursorMapper) {
        this.originalLocalId = cursorMapper.getLong("original_food_id");
        this.originalMasterId = cursorMapper.getLong("original_food_master_id");
        this.promotedFromMasterId = cursorMapper.getLong(Columns.PROMOTED_FROM_MASTER_ID);
        this.promotedFromUid = cursorMapper.getString(Columns.PROMOTED_FROM_UID);
        this.ownerUserLocalId = cursorMapper.getLong("owner_user_id");
        this.ownerUserMasterId = cursorMapper.getLong("owner_user_master_id");
    }

    /* access modifiers changed from: protected */
    public void writeV1Information(ContentValuesMapper contentValuesMapper) {
        contentValuesMapper.put("original_food_id", Long.valueOf(this.originalLocalId));
        contentValuesMapper.put("original_food_master_id", Long.valueOf(this.originalMasterId));
        contentValuesMapper.put(Columns.PROMOTED_FROM_MASTER_ID, Long.valueOf(this.promotedFromMasterId));
        contentValuesMapper.put(Columns.PROMOTED_FROM_UID, this.promotedFromUid);
        contentValuesMapper.put("owner_user_id", Long.valueOf(this.ownerUserLocalId));
        contentValuesMapper.put("owner_user_master_id", Long.valueOf(this.ownerUserMasterId));
    }
}
