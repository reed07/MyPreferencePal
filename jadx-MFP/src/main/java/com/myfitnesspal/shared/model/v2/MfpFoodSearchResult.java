package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.MapOfStringObject;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import java.util.HashMap;
import java.util.Map;

public class MfpFoodSearchResult implements Parcelable {
    public static final Creator<MfpFoodSearchResult> CREATOR = new Creator<MfpFoodSearchResult>() {
        public MfpFoodSearchResult createFromParcel(Parcel parcel) {
            return new MfpFoodSearchResult(parcel);
        }

        public MfpFoodSearchResult[] newArray(int i) {
            return new MfpFoodSearchResult[i];
        }
    };
    private static final Map<String, Class<? extends SearchResultItem>> TYPE_TO_CLASS_MAP = new HashMap();
    private ApiJsonMapper apiJsonMapper;
    @Expose
    private Map<String, Object> item;
    private SearchResultItem searchResultItem;
    @Expose
    private String type;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpFoodSearchResult> {
    }

    public static final class DataTypes {
        public static final String FOOD = "food";
        public static final String VENUE = "venue";
    }

    public int describeContents() {
        return 0;
    }

    static {
        TYPE_TO_CLASS_MAP.put("food", MfpFood.class);
        TYPE_TO_CLASS_MAP.put(DataTypes.VENUE, Venue.class);
    }

    public MfpFoodSearchResult() {
    }

    private MfpFoodSearchResult(Parcel parcel) {
        this.type = parcel.readString();
        this.item = (Map) getApiJsonMapper().withType(MapOfStringObject.class).tryMapFrom(parcel.readString());
    }

    public Map<String, Object> getItem() {
        if (this.item == null && this.searchResultItem != null) {
            ApiJsonMapper apiJsonMapper2 = getApiJsonMapper();
            this.item = (Map) apiJsonMapper2.withType(MapOfStringObject.class).tryMapFrom(apiJsonMapper2.reverseMap((Object) this.searchResultItem));
        }
        return this.item;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public <T extends SearchResultItem> T getSearchResultItem() {
        if (this.searchResultItem == null && this.item != null && TYPE_TO_CLASS_MAP.containsKey(this.type)) {
            ApiJsonMapper apiJsonMapper2 = getApiJsonMapper();
            this.searchResultItem = (SearchResultItem) apiJsonMapper2.withType((Class) TYPE_TO_CLASS_MAP.get(this.type)).tryMapFrom(apiJsonMapper2.reverseMap((Object) this.item));
        }
        return this.searchResultItem;
    }

    public void setSearchResultItem(SearchResultItem searchResultItem2) {
        this.searchResultItem = searchResultItem2;
        this.item = getItem();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.type);
        parcel.writeString(getApiJsonMapper().reverseMap((Object) this.item));
    }

    public ApiJsonMapper getApiJsonMapper() {
        if (this.apiJsonMapper == null) {
            this.apiJsonMapper = new ApiJsonMapper();
        }
        return this.apiJsonMapper;
    }
}
