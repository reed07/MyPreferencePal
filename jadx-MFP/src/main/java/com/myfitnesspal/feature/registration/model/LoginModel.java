package com.myfitnesspal.feature.registration.model;

import android.content.SharedPreferences;
import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v1.FacebookLoggedInUser;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.service.globalsettings.GlobalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.Gender;
import com.myfitnesspal.shared.util.TestMode;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Date;

public class LoginModel {
    private static final String LOGIN_MODEL_KEY = "login_model";
    private int MIGRATED_FACEBOOK = 2;
    private int MIGRATED_USER_PASS = 1;
    private Data data;
    private Lazy<GlobalSettingsService> globalSettingsService;
    private final ApiJsonMapper mapper = new ApiJsonMapper();
    private final Lazy<Session> session;
    private final SharedPreferences sharedPrefs;

    private static class Data {
        @Expose
        FacebookData facebookData;
        @Expose
        String lastUsername;
        @Expose
        int migrated;
        @Expose
        String signInAuthType;
        @Expose
        String username;

        private Data() {
            this.facebookData = new FacebookData();
        }
    }

    public static class FacebookData {
        /* access modifiers changed from: private */
        @Expose
        public String accessToken;
        @Expose
        private Date birthday;
        /* access modifiers changed from: private */
        @Expose
        public String email;
        @Expose
        private Gender gender;
        /* access modifiers changed from: private */
        @Expose
        public String userId;

        protected FacebookData() {
        }

        protected FacebookData(FacebookLoggedInUser facebookLoggedInUser) {
            this.userId = facebookLoggedInUser.getId();
            this.accessToken = facebookLoggedInUser.getAccessToken();
            this.email = facebookLoggedInUser.getEmail();
            this.gender = facebookLoggedInUser.getGender();
            this.birthday = facebookLoggedInUser.getBirthday();
        }

        public boolean isValid() {
            return Strings.notEmpty(this.userId) && Strings.notEmpty(this.accessToken);
        }

        public boolean hasUserIdWithInvalidToken() {
            return Strings.notEmpty(this.userId) && Strings.isEmpty(this.accessToken);
        }

        public String getUserId() {
            return this.userId;
        }

        public String getAccessToken() {
            return this.accessToken;
        }

        public String getEmail() {
            return this.email;
        }

        public Gender getGender() {
            return this.gender;
        }

        public Date getBirthday() {
            return this.birthday;
        }
    }

    public LoginModel(Lazy<Session> lazy, SharedPreferences sharedPreferences, Lazy<GlobalSettingsService> lazy2) {
        this.session = lazy;
        this.sharedPrefs = sharedPreferences;
        this.globalSettingsService = lazy2;
        load();
    }

    private void load() {
        this.data = (Data) this.mapper.tryMapFrom(this.sharedPrefs.getString(LOGIN_MODEL_KEY, null), Data.class);
        if (this.data == null) {
            this.data = new Data();
        }
        if (this.data.facebookData == null) {
            this.data.facebookData = new FacebookData();
        }
        if ((this.data.migrated & this.MIGRATED_USER_PASS) == 0) {
            migrateLegacyUserPass();
        }
    }

    public void save() {
        this.sharedPrefs.edit().putString(LOGIN_MODEL_KEY, this.mapper.reverseMap((Object) this.data)).apply();
    }

    public void setUsername(String str) {
        this.data.username = str;
        save();
    }

    public void logout() {
        Data data2 = this.data;
        data2.username = null;
        data2.facebookData = new FacebookData();
        save();
    }

    public String getUsername() {
        return this.data.username;
    }

    public String getLastUsername() {
        return this.data.lastUsername;
    }

    public void setLastUsername(String str) {
        this.data.lastUsername = str;
        save();
    }

    public void clearFacebookData() {
        this.data.facebookData = new FacebookData();
        save();
    }

    public void setFacebookData(FacebookLoggedInUser facebookLoggedInUser) {
        this.data.facebookData = new FacebookData(facebookLoggedInUser);
        save();
    }

    public void setFacebookUserId(String str) {
        this.data.facebookData.userId = str;
        save();
    }

    public void setFacebookUserIdAndToken(String str, String str2) {
        this.data.facebookData.userId = str;
        this.data.facebookData.accessToken = str2;
        save();
    }

    public void setFacebookAccessToken(String str) {
        this.data.facebookData.accessToken = str;
        save();
    }

    public void setFacebookData(FacebookData facebookData) {
        this.data.facebookData = facebookData;
        save();
    }

    @Deprecated
    public void setFacebookEmail(String str) {
        this.data.facebookData.email = str;
    }

    public FacebookData getFacebookData() {
        if ((this.data.migrated & this.MIGRATED_FACEBOOK) == 0) {
            migrateLegacyFacebookData();
        }
        return this.data.facebookData;
    }

    public void setSignInAuthType(String str) {
        this.data.signInAuthType = str;
    }

    public String getSignInAuthType() {
        return this.data.signInAuthType;
    }

    private void migrateLegacyUserPass() {
        if (!TestMode.INSTANCE.enabled()) {
            this.data.username = ((GlobalSettingsService) this.globalSettingsService.get()).getCurrentLoggedInUserName();
            this.data.lastUsername = ((GlobalSettingsService) this.globalSettingsService.get()).getLastLoggedInUser();
            this.data.migrated |= this.MIGRATED_USER_PASS;
            save();
        }
    }

    private void migrateLegacyFacebookData() {
        if (!TestMode.INSTANCE.enabled()) {
            if (!this.data.facebookData.isValid()) {
                UserV1 userV1 = ((Session) this.session.get()).getUser().getUserV1();
                if (userV1 != null) {
                    String thirdPartyUserId = userV1.getThirdPartyUserId();
                    String thirdPartyAuthToken = userV1.getThirdPartyAuthToken();
                    if (Strings.notEmpty(thirdPartyUserId) && Strings.notEmpty(thirdPartyAuthToken)) {
                        this.data.facebookData.userId = thirdPartyUserId;
                        this.data.facebookData.accessToken = thirdPartyAuthToken;
                    }
                }
            }
            this.data.migrated |= this.MIGRATED_FACEBOOK;
            save();
        }
    }
}
