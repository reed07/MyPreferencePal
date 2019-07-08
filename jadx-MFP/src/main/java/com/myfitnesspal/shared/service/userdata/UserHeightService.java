package com.myfitnesspal.shared.service.userdata;

import android.content.Context;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Units;
import com.myfitnesspal.shared.model.v1.UserLinearMeasurement;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.uacf.core.util.NumberUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class UserHeightService {
    private final Context mContext;
    private final Lazy<Session> session;

    @Inject
    public UserHeightService(Context context, Lazy<Session> lazy) {
        this.mContext = context;
        this.session = lazy;
    }

    public void setUseCurrentHeightUnit(Length length) {
        ((Session) this.session.get()).getUser().setProperty(Units.HEIGHT_UNIT_PREFERENCE, String.valueOf(length.getValue()));
    }

    public Length getUserCurrentHeightUnit() {
        return Length.fromInt(((Session) this.session.get()).getUser().getHeightUnitPreference());
    }

    public boolean setHeight(String[] strArr) {
        return setHeight(strArr, getUserCurrentHeightUnit());
    }

    public boolean setHeight(String[] strArr, Length length) {
        float validate = validate(strArr, length);
        if (validate < BitmapDescriptorFactory.HUE_RED) {
            return false;
        }
        ((Session) this.session.get()).getUser().getProfile().setHeight(new UserLinearMeasurement(validate, true));
        return true;
    }

    public float validate(String[] strArr, Length length) {
        boolean z = true;
        boolean z2 = false;
        float f = BitmapDescriptorFactory.HUE_RED;
        if (strArr != null && strArr.length >= 2) {
            if (length == Length.CENTIMETERS) {
                try {
                    float localeFloatFromString = NumberUtils.localeFloatFromString(strArr[0]);
                    if (localeFloatFromString >= 92.0f) {
                        if (localeFloatFromString <= 273.0f) {
                            f = (float) UnitsUtils.getInchesFromCentimeters((double) localeFloatFromString);
                            z2 = z;
                        }
                    }
                    z = false;
                    z2 = z;
                } catch (NumberFormatException unused) {
                }
            } else {
                int localeFloatFromString2 = (int) NumberUtils.localeFloatFromString(strArr[0]);
                int localeFloatFromString3 = (int) NumberUtils.localeFloatFromString(strArr[1]);
                if (localeFloatFromString2 != 0) {
                    f = (float) UnitsUtils.getInchesFromFeetInches((double) localeFloatFromString2, (double) localeFloatFromString3);
                    z2 = true;
                }
            }
        }
        if (z2) {
            return f;
        }
        return -1.0f;
    }

    public String[] getCurrentHeight() {
        UserProfile profile = ((Session) this.session.get()).getUser().getProfile();
        if (profile != null) {
            return getHeightStrings(profile.getHeight().getInches(), getUserCurrentHeightUnit());
        }
        return new String[]{"0", "0"};
    }

    public double getCurrentHeightInInches() {
        UserProfile profile = ((Session) this.session.get()).getUser().getProfile();
        if (profile != null) {
            return (double) profile.getHeight().getInches();
        }
        return 0.0d;
    }

    public String getDisplayableString() {
        String[] currentHeight = getCurrentHeight();
        switch (getUserCurrentHeightUnit()) {
            case CENTIMETERS:
                return String.format(this.mContext.getString(R.string.height_in_centimeters), new Object[]{currentHeight[0]});
            case FEET_INCHES:
                return String.format(this.mContext.getString(R.string.height_in_feet_inches), new Object[]{currentHeight[0], currentHeight[1]});
            default:
                return String.format(this.mContext.getString(R.string.height_in_centimeters), new Object[]{currentHeight[0]});
        }
    }

    public String getCurrentHeightUnitString() {
        if (AnonymousClass1.$SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Length[getUserCurrentHeightUnit().ordinal()] != 2) {
            return this.mContext.getString(R.string.cm_setting);
        }
        return this.mContext.getString(R.string.feet_inches_setting);
    }

    private String[] getHeightStrings(float f, Length length) {
        if (length == Length.CENTIMETERS) {
            return new String[]{UnitsUtils.getLocalizedLengthString(Length.INCHES, Length.CENTIMETERS, (double) f, false), "0"};
        }
        int feetFromInches = (int) UnitsUtils.getFeetFromInches((double) f);
        float round = (float) Math.round(f - ((float) (feetFromInches * 12)));
        double d = (double) round;
        if (d >= 12.0d) {
            feetFromInches++;
            round = (float) (d - 12.0d);
        }
        return new String[]{Integer.toString(feetFromInches), NumberUtils.localeStringFromDoubleNoDecimal((double) round)};
    }
}
