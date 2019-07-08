package com.myfitnesspal.shared.service.facebook;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.AccessToken;
import com.facebook.FacebookRequestError;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.GraphJSONArrayCallback;
import com.facebook.GraphRequest.GraphJSONObjectCallback;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.feature.registration.model.LoginModel.FacebookData;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.SharedConstants.Facebook;
import com.myfitnesspal.shared.model.v1.FacebookFriend;
import com.myfitnesspal.shared.model.v1.FacebookLoggedInUser;
import com.myfitnesspal.shared.model.v15.DissociateThirdPartyObject;
import com.myfitnesspal.shared.model.v15.VerifyThirdPartyObject;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.thirdparty.ThirdPartyService;
import com.myfitnesspal.shared.util.AccountUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.Gender;
import com.myfitnesspal.shared.util.PincodeHelper;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookServiceImpl implements FacebookService {
    private final Lazy<LoginModel> loginModel;
    private final Lazy<Session> session;
    private final Lazy<ThirdPartyService> thirdPartyService;

    public FacebookServiceImpl(Lazy<ThirdPartyService> lazy, Lazy<LoginModel> lazy2, Lazy<Session> lazy3) {
        this.thirdPartyService = lazy;
        this.loginModel = lazy2;
        this.session = lazy3;
    }

    public void connect(Activity activity, Function1<String> function1, Function2<Integer, String> function2) {
        connect(activity, function1, function2, false);
    }

    public void connectEmailCompulsory(Activity activity, Function1<String> function1, Function2<Integer, String> function2) {
        connect(activity, function1, function2, true);
    }

    public boolean requiresReconnect() {
        return ((LoginModel) this.loginModel.get()).getFacebookData().hasUserIdWithInvalidToken();
    }

    public void changePasswordAndDisconnect(String str, Function0 function0, Function2<Integer, String> function2) {
        disablePasscodeLock();
        Ln.d("startChangePasswordAndDisconnect", new Object[0]);
        ((ThirdPartyService) this.thirdPartyService.get()).changePassword(str, new Function0(function0, function2) {
            private final /* synthetic */ Function0 f$1;
            private final /* synthetic */ Function2 f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void execute() {
                FacebookServiceImpl.this.disconnectFromFacebook(this.f$1, this.f$2);
            }
        }, new ApiErrorCallback(function2) {
            private final /* synthetic */ Function2 f$1;

            {
                this.f$1 = r2;
            }

            public final void execute(Object obj) {
                FacebookServiceImpl.lambda$changePasswordAndDisconnect$1(FacebookServiceImpl.this, this.f$1, (ApiException) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$changePasswordAndDisconnect$1(FacebookServiceImpl facebookServiceImpl, Function2 function2, ApiException apiException) throws RuntimeException {
        Ln.d("PASSWORD: NOT changed, code = %s, message = %s", Integer.valueOf(apiException.getStatusCode()), apiException.getMessage());
        facebookServiceImpl.enablePasscodeLock();
        FunctionUtils.invokeIfValid(function2, Integer.valueOf(apiException.getStatusCode()), apiException.getMessage());
    }

    public void disconnect(Function0 function0, Function2<Integer, String> function2) {
        disablePasscodeLock();
        disconnectFromFacebook(function0, function2);
    }

    public void getUserFriendsList(Function1<List<FacebookFriend>> function1, Function1<FacebookRequestError> function12) {
        GraphRequest newMyFriendsRequest = GraphRequest.newMyFriendsRequest(AccessToken.getCurrentAccessToken(), new GraphJSONArrayCallback(function1, function12) {
            private final /* synthetic */ Function1 f$1;
            private final /* synthetic */ Function1 f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onCompleted(JSONArray jSONArray, GraphResponse graphResponse) {
                FacebookServiceImpl.lambda$getUserFriendsList$2(FacebookServiceImpl.this, this.f$1, this.f$2, jSONArray, graphResponse);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.FIELDS_PARAM, TextUtils.join(",", Arrays.asList(new String[]{"id", "name"})));
        newMyFriendsRequest.setParameters(bundle);
        newMyFriendsRequest.executeAndWait();
    }

    public static /* synthetic */ void lambda$getUserFriendsList$2(FacebookServiceImpl facebookServiceImpl, Function1 function1, Function1 function12, JSONArray jSONArray, GraphResponse graphResponse) {
        Ln.d(graphResponse.getRawResponse(), new Object[0]);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                arrayList.add(facebookServiceImpl.getFacebookFriendFromJsonObject((JSONObject) jSONArray.get(i)));
                i++;
            } catch (JSONException e) {
                Ln.d(e.getLocalizedMessage(), new Object[0]);
            }
        }
        if (CollectionUtils.notEmpty((Collection<?>) arrayList)) {
            FunctionUtils.invokeIfValid(function1, arrayList);
        } else {
            FunctionUtils.invokeIfValid(function12, graphResponse.getError());
        }
    }

    private void connect(Activity activity, Function1<String> function1, Function2<Integer, String> function2, boolean z) {
        disablePasscodeLock();
        ((LoginModel) this.loginModel.get()).setFacebookAccessToken("");
        getUserFacebookData(new Function0(activity, function1, function2) {
            private final /* synthetic */ Activity f$1;
            private final /* synthetic */ Function1 f$2;
            private final /* synthetic */ Function2 f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void execute() {
                FacebookServiceImpl.this.verifyFacebookUser(this.f$1, this.f$2, this.f$3);
            }
        }, function2, z);
    }

    private void associateWithFacebook(Function1<String> function1, Function2<Integer, String> function2) {
        FacebookData facebookData = ((LoginModel) this.loginModel.get()).getFacebookData();
        String userId = facebookData.getUserId();
        String accessToken = facebookData.getAccessToken();
        ((ThirdPartyService) this.thirdPartyService.get()).associate(1, userId, accessToken, new Function0(userId, accessToken, function1) {
            private final /* synthetic */ String f$1;
            private final /* synthetic */ String f$2;
            private final /* synthetic */ Function1 f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void execute() {
                FacebookServiceImpl.lambda$associateWithFacebook$4(FacebookServiceImpl.this, this.f$1, this.f$2, this.f$3);
            }
        }, new ApiErrorCallback(function2) {
            private final /* synthetic */ Function2 f$1;

            {
                this.f$1 = r2;
            }

            public final void execute(Object obj) {
                FacebookServiceImpl.lambda$associateWithFacebook$5(FacebookServiceImpl.this, this.f$1, (ApiException) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$associateWithFacebook$4(FacebookServiceImpl facebookServiceImpl, String str, String str2, Function1 function1) throws RuntimeException {
        ((LoginModel) facebookServiceImpl.loginModel.get()).setFacebookUserIdAndToken(str, str2);
        facebookServiceImpl.enablePasscodeLock();
        Ln.d("facebook associate success", new Object[0]);
        FunctionUtils.invokeIfValid(function1, ((Session) facebookServiceImpl.session.get()).getUser().getUsername());
    }

    public static /* synthetic */ void lambda$associateWithFacebook$5(FacebookServiceImpl facebookServiceImpl, Function2 function2, ApiException apiException) throws RuntimeException {
        Ln.d("FACEBOOK: NOT associated, code = %s, message = %s", Integer.valueOf(apiException.getStatusCode()), apiException.getMessage());
        facebookServiceImpl.clearFacebookDataAndEnablePasscodeLock();
        FunctionUtils.invokeIfValid(function2, Integer.valueOf(apiException.getStatusCode()), apiException.getLocalizedMessage());
    }

    private void clearFacebookDataAndEnablePasscodeLock() {
        enablePasscodeLock();
        ((LoginModel) this.loginModel.get()).clearFacebookData();
    }

    /* access modifiers changed from: private */
    public void disconnectFromFacebook(Function0 function0, Function2<Integer, String> function2) {
        ((ThirdPartyService) this.thirdPartyService.get()).dissociate(1, new Function1(function0) {
            private final /* synthetic */ Function0 f$1;

            {
                this.f$1 = r2;
            }

            public final void execute(Object obj) {
                FacebookServiceImpl.lambda$disconnectFromFacebook$6(FacebookServiceImpl.this, this.f$1, (DissociateThirdPartyObject) obj);
            }
        }, new ApiErrorCallback(function2) {
            private final /* synthetic */ Function2 f$1;

            {
                this.f$1 = r2;
            }

            public final void execute(Object obj) {
                FacebookServiceImpl.lambda$disconnectFromFacebook$7(FacebookServiceImpl.this, this.f$1, (ApiException) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$disconnectFromFacebook$6(FacebookServiceImpl facebookServiceImpl, Function0 function0, DissociateThirdPartyObject dissociateThirdPartyObject) throws RuntimeException {
        facebookServiceImpl.enablePasscodeLock();
        ((LoginModel) facebookServiceImpl.loginModel.get()).clearFacebookData();
        LoginManager.getInstance().logOut();
        FunctionUtils.invokeIfValid(function0);
    }

    public static /* synthetic */ void lambda$disconnectFromFacebook$7(FacebookServiceImpl facebookServiceImpl, Function2 function2, ApiException apiException) throws RuntimeException {
        facebookServiceImpl.enablePasscodeLock();
        FunctionUtils.invokeIfValid(function2, Integer.valueOf(apiException.getStatusCode()), apiException.getMessage());
    }

    /* access modifiers changed from: private */
    public void verifyFacebookUser(Activity activity, Function1<String> function1, Function2<Integer, String> function2) {
        FacebookData facebookData = ((LoginModel) this.loginModel.get()).getFacebookData();
        String userId = facebookData.getUserId();
        String accessToken = facebookData.getAccessToken();
        ThirdPartyService thirdPartyService2 = (ThirdPartyService) this.thirdPartyService.get();
        $$Lambda$FacebookServiceImpl$0NS24U_whwFYO5oRwxW4VNhJfBc r1 = new Function1(function1, function2, userId, accessToken, activity) {
            private final /* synthetic */ Function1 f$1;
            private final /* synthetic */ Function2 f$2;
            private final /* synthetic */ String f$3;
            private final /* synthetic */ String f$4;
            private final /* synthetic */ Activity f$5;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
                this.f$4 = r5;
                this.f$5 = r6;
            }

            public final void execute(Object obj) {
                FacebookServiceImpl.lambda$verifyFacebookUser$8(FacebookServiceImpl.this, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, (VerifyThirdPartyObject) obj);
            }
        };
        thirdPartyService2.verifyThirdPartyUser(true, 1, userId, accessToken, r1, new ApiErrorCallback(function2, activity) {
            private final /* synthetic */ Function2 f$1;
            private final /* synthetic */ Activity f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void execute(Object obj) {
                FacebookServiceImpl.lambda$verifyFacebookUser$9(FacebookServiceImpl.this, this.f$1, this.f$2, (ApiException) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$verifyFacebookUser$8(FacebookServiceImpl facebookServiceImpl, Function1 function1, Function2 function2, String str, String str2, Activity activity, VerifyThirdPartyObject verifyThirdPartyObject) throws RuntimeException {
        String username = verifyThirdPartyObject.getUsername();
        if (verifyThirdPartyObject.isSuccessful()) {
            String username2 = ((Session) facebookServiceImpl.session.get()).getUser().getUsername();
            if (!Strings.isEmpty(username)) {
                facebookServiceImpl.enablePasscodeLock();
                if (!((Session) facebookServiceImpl.session.get()).getUser().isLoggedIn() || Strings.isEmpty(username2) || Strings.equals(username, username2)) {
                    ((LoginModel) facebookServiceImpl.loginModel.get()).setFacebookUserIdAndToken(str, str2);
                    Ln.d("facebook verify success with mfpUsernameForThirdPartyAccount = %s", username);
                    FunctionUtils.invokeIfValid(function1, username);
                    return;
                }
                facebookServiceImpl.clearFacebookDataAndEnablePasscodeLock();
                FunctionUtils.invokeIfValid(function2, Integer.valueOf(0), activity.getResources().getString(R.string.error_facebook_account_connected_different_mfp_account));
            } else if (Strings.notEmpty(username2)) {
                Ln.d("facebook verify success with no mfpUsernameForThirdPartyAccount, but we have a logged in user", new Object[0]);
                facebookServiceImpl.associateWithFacebook(function1, function2);
            } else {
                FunctionUtils.invokeIfValid(function1, null);
                facebookServiceImpl.enablePasscodeLock();
            }
        } else {
            Ln.d("failed verify Facebook user", new Object[0]);
            facebookServiceImpl.clearFacebookDataAndEnablePasscodeLock();
            FunctionUtils.invokeIfValid(function2, Integer.valueOf(0), activity.getResources().getString(R.string.failVerifyFacebookUser));
        }
    }

    public static /* synthetic */ void lambda$verifyFacebookUser$9(FacebookServiceImpl facebookServiceImpl, Function2 function2, Activity activity, ApiException apiException) throws RuntimeException {
        Ln.d("failed verify Facebook user", new Object[0]);
        facebookServiceImpl.clearFacebookDataAndEnablePasscodeLock();
        FunctionUtils.invokeIfValid(function2, Integer.valueOf(apiException.getStatusCode()), activity.getResources().getString(R.string.failVerifyFacebookUser));
    }

    private void getUserFacebookData(Function0 function0, Function2<Integer, String> function2, boolean z) {
        getUserProfileData(new Function1(z, function0, function2) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ Function0 f$2;
            private final /* synthetic */ Function2 f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void execute(Object obj) {
                FacebookServiceImpl.lambda$getUserFacebookData$10(FacebookServiceImpl.this, this.f$1, this.f$2, this.f$3, (FacebookLoggedInUser) obj);
            }
        }, new Function1(function2) {
            private final /* synthetic */ Function2 f$1;

            {
                this.f$1 = r2;
            }

            public final void execute(Object obj) {
                FacebookServiceImpl.lambda$getUserFacebookData$11(FacebookServiceImpl.this, this.f$1, (FacebookRequestError) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getUserFacebookData$10(FacebookServiceImpl facebookServiceImpl, boolean z, Function0 function0, Function2 function2, FacebookLoggedInUser facebookLoggedInUser) throws RuntimeException {
        boolean z2 = false;
        boolean z3 = !z || Strings.notEmpty(facebookLoggedInUser.getEmail());
        if (facebookLoggedInUser.getBirthday() == null || AccountUtils.validateAge(facebookLoggedInUser.getBirthday())) {
            z2 = true;
        }
        if (!z3 || !z2) {
            facebookServiceImpl.clearFacebookDataAndEnablePasscodeLock();
            FunctionUtils.invokeIfValid(function2, Integer.valueOf(!z3 ? 2001 : 2000), null);
            return;
        }
        ((LoginModel) facebookServiceImpl.loginModel.get()).setFacebookData(facebookLoggedInUser);
        FunctionUtils.invokeIfValid(function0);
    }

    public static /* synthetic */ void lambda$getUserFacebookData$11(FacebookServiceImpl facebookServiceImpl, Function2 function2, FacebookRequestError facebookRequestError) throws RuntimeException {
        int i;
        String str = "";
        if (facebookRequestError != null) {
            int errorCode = facebookRequestError.getErrorCode();
            str = facebookRequestError.getErrorMessage();
            i = errorCode;
        } else {
            i = 0;
        }
        facebookServiceImpl.clearFacebookDataAndEnablePasscodeLock();
        FunctionUtils.invokeIfValid(function2, Integer.valueOf(i), str);
    }

    private void getUserProfileData(Function1<FacebookLoggedInUser> function1, Function1<FacebookRequestError> function12) {
        AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
        GraphRequest newMeRequest = GraphRequest.newMeRequest(currentAccessToken, new GraphJSONObjectCallback(currentAccessToken, function1, function12) {
            private final /* synthetic */ AccessToken f$1;
            private final /* synthetic */ Function1 f$2;
            private final /* synthetic */ Function1 f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void onCompleted(JSONObject jSONObject, GraphResponse graphResponse) {
                FacebookServiceImpl.lambda$getUserProfileData$12(FacebookServiceImpl.this, this.f$1, this.f$2, this.f$3, jSONObject, graphResponse);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString(GraphRequest.FIELDS_PARAM, TextUtils.join(",", Arrays.asList(new String[]{"id", "name", "email", "gender", Facebook.BIRTHDAY, Facebook.TIMEZONE})));
        newMeRequest.setParameters(bundle);
        newMeRequest.executeAsync();
    }

    public static /* synthetic */ void lambda$getUserProfileData$12(FacebookServiceImpl facebookServiceImpl, AccessToken accessToken, Function1 function1, Function1 function12, JSONObject jSONObject, GraphResponse graphResponse) {
        Ln.v(graphResponse.toString(), new Object[0]);
        FacebookRequestError error = graphResponse.getError();
        if (error == null) {
            try {
                FacebookLoggedInUser facebookLoggedInUser = new FacebookLoggedInUser();
                facebookLoggedInUser.copy(facebookServiceImpl.getFacebookFriendFromJsonObject(jSONObject));
                facebookLoggedInUser.setAccessToken(accessToken.getToken());
                FunctionUtils.invokeIfValid(function1, facebookLoggedInUser);
            } catch (Exception e) {
                e.printStackTrace();
                FunctionUtils.invokeIfValid(function12, null);
            }
        } else {
            FunctionUtils.invokeIfValid(function12, error);
        }
    }

    private FacebookFriend getFacebookFriendFromJsonObject(JSONObject jSONObject) {
        FacebookFriend facebookFriend = new FacebookFriend();
        facebookFriend.setId(jSONObject.optString("id"));
        facebookFriend.setMfpUsername("");
        facebookFriend.setHasBeenInvited(false);
        facebookFriend.setBirthday(DateTimeUtils.parse(Format.newFacebookDateFormat().toPattern(), jSONObject.optString(Facebook.BIRTHDAY)));
        facebookFriend.setName(jSONObject.optString("name"));
        facebookFriend.setEmail(jSONObject.optString("email"));
        facebookFriend.setTimezone((float) jSONObject.optInt(Facebook.TIMEZONE));
        facebookFriend.setGender(Gender.fromString(jSONObject.optString("gender", Gender.Unknown.toString()).toLowerCase()));
        return facebookFriend;
    }

    private void enablePasscodeLock() {
        PincodeHelper.current().setIgnorePincodeTemporarily(false);
    }

    private void disablePasscodeLock() {
        PincodeHelper.current().setIgnorePincodeTemporarily(true);
    }
}
