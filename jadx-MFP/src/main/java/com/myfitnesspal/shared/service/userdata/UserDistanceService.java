package com.myfitnesspal.shared.service.userdata;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Units;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import dagger.Lazy;
import javax.inject.Inject;

public class UserDistanceService {
    private static final String KILOMETERS_PREF = "_kilometer";
    private static final String MILES_PREF = "_mile";
    private final Context context;
    private Lazy<Session> session;

    @Inject
    public UserDistanceService(Context context2, Lazy<Session> lazy) {
        this.context = context2;
        this.session = lazy;
    }

    public void setUseCurrentDistanceUnit(Length length) {
        ((Session) this.session.get()).getUser().setProperty(Units.DISTANCE_UNIT_PREFERENCE, String.valueOf(length.getValue()));
    }

    public Length getUserCurrentDistanceUnit() {
        return Length.fromInt(((Session) this.session.get()).getUser().getDistanceUnitPreference());
    }

    public String getCurrentDistanceUnitString() {
        if (AnonymousClass1.$SwitchMap$com$myfitnesspal$shared$util$UnitsUtils$Length[getUserCurrentDistanceUnit().ordinal()] != 1) {
            return this.context.getString(R.string.kilometers_setting);
        }
        return this.context.getString(R.string.miles_setting);
    }

    public String getEndingForStringResource() {
        switch (getUserCurrentDistanceUnit()) {
            case MILES:
            case INCHES:
            case FEET:
            case FEET_INCHES:
                return MILES_PREF;
            case CENTIMETERS:
            case KILOMETERS:
            case METERS_CENTIMETERS:
                return KILOMETERS_PREF;
            default:
                return "";
        }
    }
}
