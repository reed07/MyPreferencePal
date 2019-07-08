package com.myfitnesspal.feature.registration.model;

import android.content.SharedPreferences;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.registration.constants.SignUpBmi;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Registration;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Units;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.unitconv.LocalizedLength;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.v1.Country;
import com.myfitnesspal.shared.model.v1.PreferredUnits;
import com.myfitnesspal.shared.model.v1.UserLinearMeasurement;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.model.v1.UserV1GoalPreferences;
import com.myfitnesspal.shared.model.v1.UserWeight;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.Gender;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.myfitnesspal.shared.util.UnitsUtils.Water;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SignUpModel {
    private static final int COUNTRY_SHORT_NAME_LENGTH = 2;
    private static final String PREFS_KEY_SIGN_UP_MODEL_DATA = "sign_up_model_data";
    private final Lazy<CountryService> countryService;
    private Data data = new Data();
    private ApiJsonMapper mapper = new ApiJsonMapper();
    private final Lazy<Session> session;
    private final Lazy<SharedPreferences> signUpPrefs;

    private static class Data {
        /* access modifiers changed from: private */
        @Expose
        public List<String> acceptedConsentsId;
        /* access modifiers changed from: private */
        @Expose
        public String activityLevel;
        /* access modifiers changed from: private */
        @Expose
        public Date birthday;
        /* access modifiers changed from: private */
        @Expose
        public int bmiType;
        /* access modifiers changed from: private */
        @Expose
        public String consentMatrixVersion;
        /* access modifiers changed from: private */
        @Expose
        public String countryName;
        /* access modifiers changed from: private */
        @Expose
        public LocalizedLength currentHeight;
        /* access modifiers changed from: private */
        @Expose
        public LocalizedWeight currentWeight;
        /* access modifiers changed from: private */
        @Expose
        public Length distanceUnit;
        /* access modifiers changed from: private */
        @Expose
        public String emailAddress;
        /* access modifiers changed from: private */
        @Expose
        public Energy energyUnit;
        /* access modifiers changed from: private */
        @Expose
        public String firstName;
        /* access modifiers changed from: private */
        @Expose
        public String gender;
        /* access modifiers changed from: private */
        @Expose
        public String goalType;
        /* access modifiers changed from: private */
        @Expose
        public LocalizedWeight goalWeight;
        /* access modifiers changed from: private */
        @Expose
        public Length heightUnit;
        /* access modifiers changed from: private */
        @Expose
        public String lastName;
        /* access modifiers changed from: private */
        @Expose
        public int numberOfConsents;
        /* access modifiers changed from: private */
        public String password;
        /* access modifiers changed from: private */
        @Expose
        public String region;
        /* access modifiers changed from: private */
        @Expose
        public String uacfUserId;
        /* access modifiers changed from: private */
        @Expose
        public String username;
        /* access modifiers changed from: private */
        @Expose
        public Water waterUnit;
        /* access modifiers changed from: private */
        @Expose
        public LocalizedWeight weeklyWaitGoal;
        /* access modifiers changed from: private */
        @Expose
        public Weight weightUnit;
        /* access modifiers changed from: private */
        @Expose
        public String zipcode;

        private Data() {
            this.goalType = "";
            this.zipcode = "";
            this.consentMatrixVersion = "0";
            this.numberOfConsents = 0;
            this.acceptedConsentsId = Collections.emptyList();
        }
    }

    public SignUpModel(Lazy<SharedPreferences> lazy, Lazy<CountryService> lazy2, Lazy<Session> lazy3) {
        this.signUpPrefs = lazy;
        this.countryService = lazy2;
        this.session = lazy3;
        load();
    }

    private void load() {
        this.data = (Data) this.mapper.tryMapFrom(((SharedPreferences) this.signUpPrefs.get()).getString(PREFS_KEY_SIGN_UP_MODEL_DATA, null));
        if (this.data == null) {
            this.data = new Data();
        }
        initializeDefaultUnits();
    }

    public void save() {
        String reverseMap = this.mapper.reverseMap((Object) this.data);
        if (Strings.notEmpty(reverseMap)) {
            ((SharedPreferences) this.signUpPrefs.get()).edit().putString(PREFS_KEY_SIGN_UP_MODEL_DATA, reverseMap).apply();
        }
    }

    public void writeAllToServices() {
        User user = ((Session) this.session.get()).getUser();
        UserV1 userV1 = user.getUserV1();
        UserV1GoalPreferences goalPreferences = userV1.getGoalPreferences();
        UserProfile profile = user.getProfile();
        userV1.setUsername(getUsername());
        userV1.setEmail(getEmailAddress());
        profile.setCurrentWeight(new UserWeight((float) getCurrentWeight().getValue(Weight.POUNDS)));
        profile.setGoalWeight(new UserWeight((float) getGoalWeight().getValue(Weight.POUNDS)));
        float value = (float) getWeeklyWeightGoal().getValue(Weight.POUNDS);
        if (Registration.GAIN.equals(getGoalType())) {
            value = -value;
        }
        goalPreferences.setGoalLossPerWeek(value);
        user.setProperty(Units.BODY_WEIGHT_UNIT_PREFERENCE, String.valueOf(getWeightUnit().getValue()));
        profile.setHeight(new UserLinearMeasurement((float) getCurrentHeight().getValue(Length.INCHES), true));
        user.setProperty(Units.HEIGHT_UNIT_PREFERENCE, String.valueOf(getHeightUnit().getValue()));
        user.setProperty(Units.WATER_UNIT_PREFERENCE, String.valueOf(getWaterUnit().getValue()));
        user.setProperty(Basic.UACF_ID, Strings.notEmpty(this.data.uacfUserId) ? this.data.uacfUserId : null);
        profile.setGenderString(getGender());
        profile.setDateOfBirth(getBirthday());
        profile.setCountryName(getCountryName());
        profile.setPostalCode(getZipCode());
        profile.setLifestyleName(getActivityLevel());
    }

    public void clear() {
        ((SharedPreferences) this.signUpPrefs.get()).edit().clear().apply();
        load();
    }

    public String getGoalType() {
        return this.data.goalType;
    }

    public void setGoalType(String str) {
        this.data.goalType = str;
    }

    public String getUsername() {
        return this.data.username;
    }

    public void setUsername(String str) {
        this.data.username = str;
    }

    public String getFirstName() {
        return this.data.firstName;
    }

    public void setFirstName(String str) {
        this.data.firstName = str;
    }

    public String getLastName() {
        return this.data.lastName;
    }

    public void setLastName(String str) {
        this.data.lastName = str;
    }

    public String getPassword() {
        return this.data.password;
    }

    public void setPassword(String str) {
        this.data.password = str;
    }

    public String getEmailAddress() {
        return this.data.emailAddress;
    }

    public void setEmailAddress(String str) {
        this.data.emailAddress = str;
    }

    public Water getWaterUnit() {
        return this.data.waterUnit;
    }

    public void setWaterUnit(Water water) {
        this.data.waterUnit = water;
    }

    public Weight getWeightUnit() {
        return this.data.weightUnit;
    }

    public void setWeightUnit(Weight weight) {
        this.data.weightUnit = weight;
    }

    public void setCurrentWeight(LocalizedWeight localizedWeight) {
        this.data.currentWeight = localizedWeight;
    }

    public LocalizedWeight getCurrentWeight() {
        return this.data.currentWeight != null ? this.data.currentWeight : LocalizedWeight.fromPounds(0.0d);
    }

    public void setGoalWeight(LocalizedWeight localizedWeight) {
        this.data.goalWeight = localizedWeight;
    }

    public LocalizedWeight getGoalWeight() {
        return this.data.goalWeight != null ? this.data.goalWeight : LocalizedWeight.fromPounds(0.0d);
    }

    public void setHeightUnit(Length length) {
        this.data.heightUnit = length;
    }

    public Length getHeightUnit() {
        return this.data.heightUnit;
    }

    public void setCurrentHeight(LocalizedLength localizedLength) {
        this.data.currentHeight = localizedLength;
    }

    public LocalizedLength getCurrentHeight() {
        return this.data.currentHeight != null ? this.data.currentHeight : LocalizedLength.fromFeet(0.0d);
    }

    public void setGender(Gender gender) {
        if (gender == Gender.Unknown) {
            gender = Gender.Male;
        }
        this.data.gender = gender.toString();
    }

    public void setConsentsMatrixVersion(String str) {
        this.data.consentMatrixVersion = str;
    }

    public String getConsentsMatrixVersion() {
        return this.data.consentMatrixVersion;
    }

    public void setNumberOfConsents(int i) {
        this.data.numberOfConsents = i;
    }

    public int getNumberOfConsents() {
        return this.data.numberOfConsents;
    }

    public void setAcceptedConsentsId(List<String> list) {
        Data data2 = this.data;
        if (!CollectionUtils.notEmpty((Collection<?>) list)) {
            list = Collections.emptyList();
        }
        data2.acceptedConsentsId = list;
    }

    public List<String> getAcceptedConsentsId() {
        return this.data.acceptedConsentsId;
    }

    public SignUpBmi getBmiType() {
        return SignUpBmi.fromInt(this.data.bmiType);
    }

    public void setBmiType(SignUpBmi signUpBmi) {
        Data data2 = this.data;
        if (signUpBmi == null) {
            signUpBmi = SignUpBmi.Normal;
        }
        data2.bmiType = signUpBmi.toInt();
    }

    public void setGender(String str) {
        this.data.gender = str;
    }

    public final String getGender() {
        return this.data.gender;
    }

    public void setBirthday(Date date) {
        this.data.birthday = date;
    }

    public Date getBirthday() {
        return this.data.birthday;
    }

    public void setCountryName(String str) {
        this.data.countryName = str;
    }

    public final String getCountryName() {
        if (Strings.isEmpty(this.data.countryName)) {
            this.data.countryName = ((CountryService) this.countryService.get()).getCurrentCountryLongName();
        } else if (this.data.countryName.length() == 2) {
            String longName = ((CountryService) this.countryService.get()).getCountryFromCountryCode(this.data.countryName).getLongName();
            Data data2 = this.data;
            if (Strings.isEmpty(longName)) {
                longName = ((CountryService) this.countryService.get()).getCurrentCountryLongName();
            }
            data2.countryName = longName;
        }
        return this.data.countryName;
    }

    public void setZipCode(String str) {
        this.data.zipcode = str;
    }

    public final String getZipCode() {
        return this.data.zipcode;
    }

    public void setActivityLevel(String str) {
        this.data.activityLevel = str;
    }

    public final String getActivityLevel() {
        return this.data.activityLevel;
    }

    public void setRegion(String str) {
        this.data.region = str;
    }

    public String getRegion() {
        return this.data.region;
    }

    public void setUacfUserId(String str) {
        this.data.uacfUserId = str;
    }

    public String getUacfUserId() {
        return this.data.uacfUserId;
    }

    public void setWeeklyWeightGoal(LocalizedWeight localizedWeight) {
        this.data.weeklyWaitGoal = localizedWeight;
    }

    public LocalizedWeight getWeeklyWeightGoal() {
        if (this.data.weeklyWaitGoal != null) {
            return this.data.weeklyWaitGoal;
        }
        String goalType = getGoalType();
        Weight weightUnit = getWeightUnit();
        if (Registration.LOSE.equals(goalType)) {
            return LocalizedWeight.fromPounds(weightUnit == Weight.KILOGRAMS ? 1.100000023841858d : 1.0d);
        } else if (!Registration.GAIN.equals(goalType)) {
            return LocalizedWeight.fromPounds(0.0d);
        } else {
            return LocalizedWeight.fromPounds(weightUnit == Weight.KILOGRAMS ? -1.100000023841858d : -1.0d);
        }
    }

    private void initializeDefaultUnits() {
        String country = Locale.getDefault().getCountry();
        Lazy<CountryService> lazy = this.countryService;
        Country countryFromCountryCode = lazy != null ? ((CountryService) lazy.get()).getCountryFromCountryCode(country) : null;
        PreferredUnits preferredUnits = countryFromCountryCode != null ? countryFromCountryCode.getPreferredUnits() : PreferredUnits.PREFERRED_UNITS_DEFAULT;
        Data data2 = this.data;
        data2.weightUnit = data2.weightUnit != null ? this.data.weightUnit : preferredUnits.getWeight();
        Data data3 = this.data;
        data3.heightUnit = data3.heightUnit != null ? this.data.heightUnit : preferredUnits.getHeight();
        Data data4 = this.data;
        data4.distanceUnit = data4.distanceUnit != null ? this.data.distanceUnit : preferredUnits.getDistance();
        Data data5 = this.data;
        data5.energyUnit = data5.energyUnit != null ? this.data.energyUnit : preferredUnits.getEnergy();
        Data data6 = this.data;
        data6.waterUnit = data6.waterUnit != null ? this.data.waterUnit : preferredUnits.getWater();
    }

    public static Date getDefaultDate() {
        Date date = new Date();
        date.setYear(83);
        date.setMonth(4);
        date.setDate(11);
        return date;
    }

    public String toJson() {
        return this.mapper.reverseMap((Object) this.data);
    }
}
