package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.JsonElement;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.ParcelableUtil;
import java.util.Map;

public class MfpMenuItemMatch implements Parcelable {
    public static final Creator<MfpMenuItemMatch> CREATOR = new Creator<MfpMenuItemMatch>() {
        public MfpMenuItemMatch createFromParcel(Parcel parcel) {
            return new MfpMenuItemMatch(parcel);
        }

        public MfpMenuItemMatch[] newArray(int i) {
            return new MfpMenuItemMatch[i];
        }
    };
    private static final Map<String, Class<? extends MfpMenuItemMatchData>> dataTypeMap = new Builder().put("food", MfpFood.class).put("recipe", MfpRecipe.class).put(DataTypes.FOOD_GROUP, FoodGroup.class).build();
    @SerializedName("based_on")
    @Expose
    private JsonElement basedOnMap;
    private MfpMenuItemMatchData basedOnMatchData;
    @SerializedName("verified")
    @Expose
    private boolean isVerified;
    private MfpMenuItemMatchData logMatchData;
    @SerializedName("log_object")
    @Expose
    private JsonElement logObjectMap;
    private ApiJsonMapper mapper;
    @Expose
    private String source;
    @Expose
    private String type;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpMenuItemMatch> {
    }

    private static final class DataTypes {
        public static final String FOOD = "food";
        public static final String FOOD_GROUP = "food_group";
        public static final String RECIPE = "recipe";

        private DataTypes() {
        }
    }

    public int describeContents() {
        return 0;
    }

    public MfpMenuItemMatch() {
    }

    private MfpMenuItemMatch(Parcel parcel) {
        this.type = parcel.readString();
        this.source = parcel.readString();
        this.isVerified = ParcelableUtil.readBoolean(parcel);
        this.basedOnMatchData = (MfpMenuItemMatchData) parcel.readParcelable(MfpMenuItemMatchData.class.getClassLoader());
        this.logMatchData = (MfpMenuItemMatchData) parcel.readParcelable(MfpMenuItemMatchData.class.getClassLoader());
        getBasedOnMap();
        getLogObjectMap();
    }

    public MfpMenuItemMatch(MfpFood mfpFood) {
        this.type = "food";
        this.source = "online";
        this.basedOnMatchData = mfpFood;
        getBasedOnMap();
    }

    public MfpMenuItemMatch(MfpMenuItemMatch mfpMenuItemMatch) {
        this.type = mfpMenuItemMatch.type;
        this.source = mfpMenuItemMatch.source;
        this.isVerified = mfpMenuItemMatch.isVerified;
        this.basedOnMap = mfpMenuItemMatch.basedOnMap;
        this.logObjectMap = mfpMenuItemMatch.logObjectMap;
        this.basedOnMatchData = mfpMenuItemMatch.basedOnMatchData;
        this.logMatchData = mfpMenuItemMatch.logMatchData;
    }

    public String getType() {
        return this.type;
    }

    public String getSource() {
        return this.source;
    }

    public boolean isVerified() {
        return this.isVerified;
    }

    public JsonElement getBasedOnMap() {
        if (this.basedOnMap == null) {
            this.basedOnMap = getDataMapFromObject(this.basedOnMatchData);
        }
        return this.basedOnMap;
    }

    public JsonElement getLogObjectMap() {
        if (this.logObjectMap == null) {
            this.logObjectMap = getDataMapFromObject(this.logMatchData);
        }
        return this.logObjectMap;
    }

    public void setBasedOnMatchData(MfpMenuItemMatchData mfpMenuItemMatchData) {
        this.basedOnMatchData = mfpMenuItemMatchData;
        this.basedOnMap = null;
        getBasedOnMap();
    }

    public MfpMenuItemMatchData getBasedOnMatchData() {
        if (this.basedOnMatchData == null) {
            this.basedOnMatchData = getObjectFromDataMap(this.basedOnMap);
        }
        return this.basedOnMatchData;
    }

    public MfpMenuItemMatchData getLogMatchData() {
        if (this.logMatchData == null) {
            this.logMatchData = getObjectFromDataMap(this.logObjectMap);
        }
        return this.logMatchData;
    }

    private JsonElement getDataMapFromObject(MfpMenuItemMatchData mfpMenuItemMatchData) {
        if (mfpMenuItemMatchData == null || !isTypeValid()) {
            return null;
        }
        return (JsonElement) mapObjectFromData(mfpMenuItemMatchData, JsonElement.class);
    }

    private MfpMenuItemMatchData getObjectFromDataMap(JsonElement jsonElement) {
        if (jsonElement == null || !isTypeValid()) {
            return null;
        }
        return (MfpMenuItemMatchData) mapObjectFromData(jsonElement, (Class) dataTypeMap.get(this.type));
    }

    private <T> T mapObjectFromData(Object obj, Class<? extends T> cls) {
        if (this.mapper == null) {
            this.mapper = new ApiJsonMapper();
        }
        String reverseMap = this.mapper.reverseMap(obj);
        this.mapper.withType(cls);
        return this.mapper.tryMapFrom(reverseMap);
    }

    private boolean isTypeValid() {
        return dataTypeMap.containsKey(this.type);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeString(this.source);
        ParcelableUtil.writeBoolean(parcel, this.isVerified);
        getBasedOnMatchData();
        getLogMatchData();
        parcel.writeParcelable(this.basedOnMatchData, i);
        parcel.writeParcelable(this.logMatchData, i);
    }
}
