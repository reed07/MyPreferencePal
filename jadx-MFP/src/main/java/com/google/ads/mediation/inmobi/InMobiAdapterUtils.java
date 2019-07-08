package com.google.ads.mediation.inmobi;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.inmobi.sdk.InMobiSdk;
import com.inmobi.sdk.InMobiSdk.AgeGroup;
import com.inmobi.sdk.InMobiSdk.Education;
import com.inmobi.sdk.InMobiSdk.Ethnicity;
import com.inmobi.sdk.InMobiSdk.Gender;
import com.inmobi.sdk.InMobiSdk.HouseHoldIncome;
import com.inmobi.sdk.InMobiSdk.LogLevel;
import java.util.Calendar;
import java.util.Objects;

class InMobiAdapterUtils {
    InMobiAdapterUtils() {
    }

    public static void buildAdRequest(MediationAdRequest mediationAdRequest, Bundle bundle) {
        if (bundle == null) {
            Log.d("InMobiAdapter", "Bundle extras are null");
            bundle = new Bundle();
        }
        String str = "";
        String str2 = "";
        String str3 = "";
        for (String str4 : bundle.keySet()) {
            String string = bundle.getString(str4);
            if (str4.equals(InMobiNetworkKeys.AREA_CODE)) {
                if (!"".equals(string)) {
                    InMobiSdk.setAreaCode(string);
                }
            } else if (str4.equals(InMobiNetworkKeys.INCOME)) {
                try {
                    if (!"".equals(string)) {
                        InMobiSdk.setIncome(Integer.parseInt(string));
                    }
                } catch (NumberFormatException e) {
                    Log.d("Invalid Income value", e.getMessage());
                    e.printStackTrace();
                }
            } else if (str4.equals(InMobiNetworkKeys.AGE)) {
                try {
                    if (!"".equals(string)) {
                        InMobiSdk.setAge(Integer.parseInt(string));
                    }
                } catch (NumberFormatException e2) {
                    Log.d("Please Set age properly", e2.getMessage());
                    e2.printStackTrace();
                }
            } else if (str4.equals(InMobiNetworkKeys.POSTAL_CODE)) {
                if (!"".equals(string)) {
                    InMobiSdk.setPostalCode(string);
                }
            } else if (str4.equals(InMobiNetworkKeys.NATIONALITY)) {
                if (!"".equals(string)) {
                    InMobiSdk.setNationality(string);
                }
            } else if (str4.equals(InMobiNetworkKeys.LANGUAGE)) {
                if (!"".equals(string)) {
                    InMobiSdk.setLanguage(string);
                }
            } else if (str4.equals(InMobiNetworkKeys.CITY)) {
                str = string;
            } else if (str4.equals(InMobiNetworkKeys.STATE)) {
                str2 = string;
            } else if (str4.equals(InMobiNetworkKeys.COUNTRY)) {
                str3 = string;
            } else if (str4.equals(InMobiNetworkKeys.AGE_GROUP)) {
                if (string != null) {
                    AgeGroup ageGroup = getAgeGroup(string);
                    if (ageGroup != null) {
                        InMobiSdk.setAgeGroup(ageGroup);
                    }
                }
            } else if (str4.equals(InMobiNetworkKeys.EDUCATION)) {
                if (string != null) {
                    Education education = getEducation(string);
                    if (education != null) {
                        InMobiSdk.setEducation(education);
                    }
                }
            } else if (str4.equals(InMobiNetworkKeys.ETHNICITY)) {
                if (string != null) {
                    InMobiSdk.setEthnicity(getEthnicity(string));
                } else {
                    InMobiSdk.setEthnicity(Ethnicity.OTHER);
                }
            } else if (str4.equals(InMobiNetworkKeys.HOUSEHOLD_INCOME)) {
                if (string != null) {
                    HouseHoldIncome houseHoldIncome = getHouseHoldIncome(string);
                    if (houseHoldIncome != null) {
                        InMobiSdk.setHouseHoldIncome(houseHoldIncome);
                    }
                }
            } else if (str4.equals(InMobiNetworkKeys.LOGLEVEL)) {
                if (string != null) {
                    InMobiSdk.setLogLevel(getLogLevel(string));
                } else {
                    InMobiSdk.setLogLevel(LogLevel.NONE);
                }
            } else if (str4.equals(InMobiNetworkKeys.INTERESTS)) {
                InMobiSdk.setInterests(string);
            }
        }
        if (VERSION.SDK_INT >= 19 && str != "" && !Objects.equals(str2, "") && !Objects.equals(str3, "")) {
            InMobiSdk.setLocationWithCityStateCountry(str, str2, str3);
        }
        if (mediationAdRequest.getLocation() != null) {
            InMobiSdk.setLocation(mediationAdRequest.getLocation());
        }
        if (mediationAdRequest.getBirthday() != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(mediationAdRequest.getBirthday());
            InMobiSdk.setYearOfBirth(instance.get(1));
        }
        if (mediationAdRequest.getGender() != -1) {
            switch (mediationAdRequest.getGender()) {
                case 1:
                    InMobiSdk.setGender(Gender.MALE);
                    return;
                case 2:
                    InMobiSdk.setGender(Gender.FEMALE);
                    return;
                default:
                    return;
            }
        }
    }

    private static AgeGroup getAgeGroup(String str) {
        if (str.equals(InMobiNetworkValues.ABOVE_65)) {
            return AgeGroup.ABOVE_65;
        }
        if (str.equals(InMobiNetworkValues.BELOW_18)) {
            return AgeGroup.BELOW_18;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_18_AND_24)) {
            return AgeGroup.BETWEEN_18_AND_24;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_25_AND_29)) {
            return AgeGroup.BETWEEN_25_AND_29;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_30_AND_34)) {
            return AgeGroup.BETWEEN_30_AND_34;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_35_AND_44)) {
            return AgeGroup.BETWEEN_35_AND_44;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_45_AND_54)) {
            return AgeGroup.BETWEEN_45_AND_54;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_55_AND_65)) {
            return AgeGroup.BETWEEN_55_AND_65;
        }
        return null;
    }

    private static Ethnicity getEthnicity(String str) {
        if (str.equals(InMobiNetworkValues.ETHNICITY_AFRICAN_AMERICAN)) {
            return Ethnicity.AFRICAN_AMERICAN;
        }
        if (str.equals(InMobiNetworkValues.ETHNICITY_ASIAN)) {
            return Ethnicity.ASIAN;
        }
        if (str.equals(InMobiNetworkValues.ETHNICITY_CAUCASIAN)) {
            return Ethnicity.CAUCASIAN;
        }
        if (str.equals(InMobiNetworkValues.ETHNICITY_HISPANIC)) {
            return Ethnicity.HISPANIC;
        }
        return Ethnicity.OTHER;
    }

    private static Education getEducation(String str) {
        if (str.equals(InMobiNetworkValues.EDUCATION_COLLEGEORGRADUATE)) {
            return Education.COLLEGE_OR_GRADUATE;
        }
        if (str.equals(InMobiNetworkValues.EDUCATION_HIGHSCHOOLORLESS)) {
            return Education.HIGH_SCHOOL_OR_LESS;
        }
        if (str.equals(InMobiNetworkValues.EDUCATION_POSTGRADUATEORABOVE)) {
            return Education.POST_GRADUATE_OR_ABOVE;
        }
        return null;
    }

    private static HouseHoldIncome getHouseHoldIncome(String str) {
        if (str.equals(InMobiNetworkValues.ABOVE_USD_150K)) {
            return HouseHoldIncome.ABOVE_USD_150K;
        }
        if (str.equals(InMobiNetworkValues.BELOW_USD_5K)) {
            return HouseHoldIncome.BELOW_USD_5K;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_USD_100K_AND_150K)) {
            return HouseHoldIncome.BETWEEN_USD_100K_AND_150K;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_USD_10K_AND_15K)) {
            return HouseHoldIncome.BETWEEN_USD_10K_AND_15K;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_USD_15K_AND_20K)) {
            return HouseHoldIncome.BETWEEN_USD_15K_AND_20K;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_USD_20K_AND_25K)) {
            return HouseHoldIncome.BETWEEN_USD_20K_AND_25K;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_USD_25K_AND_50K)) {
            return HouseHoldIncome.BETWEEN_USD_25K_AND_50K;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_USD_50K_AND_75K)) {
            return HouseHoldIncome.BETWEEN_USD_50K_AND_75K;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_USD_5K_AND_10K)) {
            return HouseHoldIncome.BETWEEN_USD_5K_AND_10K;
        }
        if (str.equals(InMobiNetworkValues.BETWEEN_USD_75K_AND_100K)) {
            return HouseHoldIncome.BETWEEN_USD_75K_AND_100K;
        }
        return null;
    }

    private static LogLevel getLogLevel(String str) {
        if (str.equals(InMobiNetworkValues.LOGLEVEL_DEBUG)) {
            return LogLevel.DEBUG;
        }
        if (str.equals(InMobiNetworkValues.LOGLEVEL_ERROR)) {
            return LogLevel.ERROR;
        }
        if (str.equals(InMobiNetworkValues.LOGLEVEL_NONE)) {
            return LogLevel.NONE;
        }
        return LogLevel.NONE;
    }

    public static <T> T mandatoryChecking(@Nullable T t, String str) throws MandatoryParamException {
        if (t != null && !t.toString().isEmpty()) {
            return t;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Mandatory param ");
        sb.append(str);
        sb.append(" not present");
        throw new MandatoryParamException(sb.toString());
    }
}
