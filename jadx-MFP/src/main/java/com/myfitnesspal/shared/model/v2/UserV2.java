package com.myfitnesspal.shared.model.v2;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.api.ApiResponse;
import com.uacf.core.util.ParcelableUtil;
import java.util.ArrayList;
import java.util.List;

public class UserV2 implements Parcelable {
    public static final Creator<UserV2> CREATOR = new Creator<UserV2>() {
        public UserV2 createFromParcel(Parcel parcel) {
            return new UserV2(parcel);
        }

        public UserV2[] newArray(int i) {
            return new UserV2[i];
        }
    };
    @Expose
    private MfpAccount account;
    @Expose
    private MfpAppPreferences appPreferences;
    @Expose
    private String email;
    @Expose
    private List<MfpGoalDisplay> goalDisplays;
    @Expose
    private MfpGoalPreferences goalPreferences;
    @Expose
    private String id;
    @Expose
    private MfpLocationPreferences locationPreferences;
    @Expose
    private MfpNotificationPreferences notificationPreferences;
    @Expose
    private MfpPrivacyPreferences privacyPreferences;
    @Expose
    private List<MfpProfile> profiles;
    @Expose
    private MfpSocialPreferences socialPreferences;
    @Expose
    private List<MfpStepSource> stepSources;
    @Expose
    private MfpSystemData systemData;
    @Expose
    private MfpUnitPreferences unitPreferences;
    @Expose
    private String username;

    public static class API_RESPONSE_MAPPER extends ApiResponse<UserV2> {
    }

    public int describeContents() {
        return 0;
    }

    public UserV2() {
    }

    public UserV2(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public void setUnitPreferences(MfpUnitPreferences mfpUnitPreferences) {
        this.unitPreferences = mfpUnitPreferences;
    }

    public void setProfiles(List<MfpProfile> list) {
        if (list == null) {
            this.profiles = new ArrayList();
        } else {
            this.profiles = list;
        }
    }

    public void setAccount(MfpAccount mfpAccount) {
        this.account = mfpAccount;
    }

    public void setPrivacyPreferences(MfpPrivacyPreferences mfpPrivacyPreferences) {
        this.privacyPreferences = mfpPrivacyPreferences;
    }

    public void setGoalPreferences(MfpGoalPreferences mfpGoalPreferences) {
        this.goalPreferences = mfpGoalPreferences;
    }

    public void setLocationPreferences(MfpLocationPreferences mfpLocationPreferences) {
        this.locationPreferences = mfpLocationPreferences;
    }

    public void setStepSources(List<MfpStepSource> list) {
        if (list == null) {
            this.stepSources = new ArrayList();
        } else {
            this.stepSources = list;
        }
    }

    public void setSystemData(MfpSystemData mfpSystemData) {
        this.systemData = mfpSystemData;
    }

    public void setSocialPreferences(MfpSocialPreferences mfpSocialPreferences) {
        this.socialPreferences = mfpSocialPreferences;
    }

    public void setNotificationPreferences(MfpNotificationPreferences mfpNotificationPreferences) {
        this.notificationPreferences = mfpNotificationPreferences;
    }

    public void setAppPreferences(MfpAppPreferences mfpAppPreferences) {
        this.appPreferences = mfpAppPreferences;
    }

    public void setGoalDisplays(List<MfpGoalDisplay> list) {
        if (list == null) {
            this.goalDisplays = new ArrayList();
        } else {
            this.goalDisplays = list;
        }
    }

    public String getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public MfpUnitPreferences getUnitPreferences() {
        return this.unitPreferences;
    }

    public List<MfpProfile> getProfiles() {
        return this.profiles;
    }

    public MfpAccount getAccount() {
        return this.account;
    }

    public MfpPrivacyPreferences getPrivacyPreferences() {
        return this.privacyPreferences;
    }

    public MfpGoalPreferences getGoalPreferences() {
        return this.goalPreferences;
    }

    public MfpLocationPreferences getLocationPreferences() {
        return this.locationPreferences;
    }

    public List<MfpStepSource> getStepSources() {
        ArrayList arrayList = new ArrayList();
        List<MfpStepSource> list = this.stepSources;
        if (list != null) {
            for (MfpStepSource clone : list) {
                arrayList.add(ParcelableUtil.clone(clone, MfpStepSource.CREATOR));
            }
        }
        return arrayList;
    }

    public MfpSystemData getSystemData() {
        return this.systemData;
    }

    public MfpSocialPreferences getSocialPreferences() {
        return this.socialPreferences;
    }

    public MfpNotificationPreferences getNotificationPreferences() {
        return this.notificationPreferences;
    }

    public MfpAppPreferences getAppPreferences() {
        return this.appPreferences;
    }

    public List<MfpGoalDisplay> getGoalDisplays() {
        return this.goalDisplays;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.username);
        parcel.writeString(this.email);
        if (this.unitPreferences != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.unitPreferences, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeList(this.profiles);
        if (this.account != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.account, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.privacyPreferences != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.privacyPreferences, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.goalPreferences != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.goalPreferences, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.locationPreferences != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.locationPreferences, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeList(this.stepSources);
        if (this.systemData != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.systemData, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.socialPreferences != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.socialPreferences, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.notificationPreferences != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.notificationPreferences, 0);
        } else {
            parcel.writeByte(0);
        }
        if (this.appPreferences != null) {
            parcel.writeByte(1);
            parcel.writeParcelable(this.appPreferences, 0);
        } else {
            parcel.writeByte(0);
        }
        parcel.writeList(this.goalDisplays);
    }

    private void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        this.username = parcel.readString();
        this.email = parcel.readString();
        if (parcel.readByte() == 1) {
            this.unitPreferences = (MfpUnitPreferences) parcel.readParcelable(MfpUnitPreferences.class.getClassLoader());
        }
        this.profiles.clear();
        parcel.readList(this.profiles, MfpProfile.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.account = (MfpAccount) parcel.readParcelable(MfpAccount.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.privacyPreferences = (MfpPrivacyPreferences) parcel.readParcelable(MfpPrivacyPreferences.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.goalPreferences = (MfpGoalPreferences) parcel.readParcelable(MfpGoalPreferences.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.locationPreferences = (MfpLocationPreferences) parcel.readParcelable(MfpLocationPreferences.class.getClassLoader());
        }
        this.stepSources.clear();
        parcel.readList(this.stepSources, MfpStepSource.class.getClassLoader());
        if (parcel.readByte() == 1) {
            this.systemData = (MfpSystemData) parcel.readParcelable(MfpSystemData.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.socialPreferences = (MfpSocialPreferences) parcel.readParcelable(MfpSocialPreferences.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.notificationPreferences = (MfpNotificationPreferences) parcel.readParcelable(MfpNotificationPreferences.class.getClassLoader());
        }
        if (parcel.readByte() == 1) {
            this.appPreferences = (MfpAppPreferences) parcel.readParcelable(MfpAppPreferences.class.getClassLoader());
        }
        this.goalDisplays.clear();
        parcel.readList(this.goalDisplays, MfpGoalDisplay.class.getClassLoader());
    }
}
