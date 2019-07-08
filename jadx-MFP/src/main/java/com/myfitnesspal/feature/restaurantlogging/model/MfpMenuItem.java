package com.myfitnesspal.feature.restaurantlogging.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.model.FoodEditorItem;
import com.myfitnesspal.shared.model.v2.MfpVersionedDatabaseObjectV2;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MfpMenuItem implements MfpMenuListItem, FoodEditorItem {
    public static final Creator<MfpMenuItem> CREATOR = new Creator<MfpMenuItem>() {
        public MfpMenuItem createFromParcel(Parcel parcel) {
            return new MfpMenuItem(parcel);
        }

        public MfpMenuItem[] newArray(int i) {
            return new MfpMenuItem[i];
        }
    };
    @Expose
    private String description;
    @Expose
    private String id;
    @Expose
    private List<MfpMenuItemMatch> matches;
    @Expose
    private String menuId;
    @Expose
    private String name;
    @Expose
    private String sectionId;

    public static class API_RESPONSE_MAPPER extends ApiResponse<MfpMenuItem> {
    }

    public int describeContents() {
        return 0;
    }

    public MfpMenuItem() {
    }

    public MfpMenuItem(Parcel parcel) {
        this.id = parcel.readString();
        this.menuId = parcel.readString();
        this.sectionId = parcel.readString();
        this.name = parcel.readString();
        this.description = parcel.readString();
        this.matches = ParcelableUtil.readList(parcel, MfpMenuItemMatch.class);
    }

    public String getId() {
        return this.id;
    }

    public String getMenuId() {
        return this.menuId;
    }

    public String getSectionId() {
        return this.sectionId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public List<MfpMenuItemMatch> getMatches() {
        return this.matches;
    }

    public String getDisplayableEnergyString(UserEnergyService userEnergyService) {
        if (CollectionUtils.isEmpty((Collection<?>) this.matches)) {
            return null;
        }
        Object basedOnMatchData = ((MfpMenuItemMatch) this.matches.get(0)).getBasedOnMatchData();
        if (basedOnMatchData instanceof FoodGroup) {
            FoodGroup foodGroup = (FoodGroup) basedOnMatchData;
            if (CollectionUtils.isEmpty((Collection<?>) foodGroup)) {
                return null;
            }
            basedOnMatchData = foodGroup.getSelectedFood();
        }
        return userEnergyService.getV2ObjectDesctiption((MfpVersionedDatabaseObjectV2) basedOnMatchData);
    }

    public void setMfpMenuItem(MfpMenuItem mfpMenuItem) {
        this.id = mfpMenuItem.id;
        this.menuId = mfpMenuItem.menuId;
        this.sectionId = mfpMenuItem.sectionId;
        this.name = mfpMenuItem.name;
        this.description = mfpMenuItem.description;
        this.matches = mfpMenuItem.matches;
    }

    public void addNewPrimaryMatch(MfpMenuItemMatch mfpMenuItemMatch) {
        if (this.matches == null) {
            this.matches = new ArrayList();
        }
        this.matches.add(0, mfpMenuItemMatch);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.menuId);
        parcel.writeString(this.sectionId);
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        ParcelableUtil.writeList(parcel, this.matches);
    }
}
