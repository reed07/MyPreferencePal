package com.myfitnesspal.feature.settings.model;

import com.myfitnesspal.shared.constants.Constants.Preference;
import com.myfitnesspal.shared.model.User;

public class PersonalizedSettingsBase {
    /* access modifiers changed from: protected */
    public String personalizePreferenceName(String str, User user) {
        if (user == null || str == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Long.toString(user.getMasterDatabaseId()));
        sb.append("_");
        sb.append(Preference.TIP_CARD);
        sb.append(str);
        return sb.toString();
    }
}
