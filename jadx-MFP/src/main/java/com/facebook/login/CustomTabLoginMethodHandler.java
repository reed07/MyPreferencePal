package com.facebook.login;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.v4.app.FragmentActivity;
import com.facebook.AccessTokenSource;
import com.facebook.CustomTabMainActivity;
import com.facebook.FacebookException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.FacebookSdk;
import com.facebook.FacebookServiceException;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.Utility;
import com.facebook.internal.Validate;
import com.facebook.login.LoginClient.Request;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomTabLoginMethodHandler extends WebLoginMethodHandler {
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int CHALLENGE_LENGTH = 20;
    private static final String[] CHROME_PACKAGES = {"com.android.chrome", "com.chrome.beta", "com.chrome.dev"};
    public static final Creator<CustomTabLoginMethodHandler> CREATOR = new Creator() {
        public CustomTabLoginMethodHandler createFromParcel(Parcel parcel) {
            return new CustomTabLoginMethodHandler(parcel);
        }

        public CustomTabLoginMethodHandler[] newArray(int i) {
            return new CustomTabLoginMethodHandler[i];
        }
    };
    private static final String CUSTOM_TABS_SERVICE_ACTION = "android.support.customtabs.action.CustomTabsService";
    private static final int CUSTOM_TAB_REQUEST_CODE = 1;
    private String currentPackage;
    private String expectedChallenge;

    public int describeContents() {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public String getNameForLogging() {
        return "custom_tab";
    }

    /* access modifiers changed from: protected */
    public String getSSODevice() {
        return "chrome_custom_tab";
    }

    CustomTabLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
        this.expectedChallenge = Utility.generateRandomString(20);
    }

    /* access modifiers changed from: 0000 */
    public AccessTokenSource getTokenSource() {
        return AccessTokenSource.CHROME_CUSTOM_TAB;
    }

    /* access modifiers changed from: 0000 */
    public boolean tryAuthorize(Request request) {
        if (!isCustomTabsAllowed()) {
            return false;
        }
        Bundle addExtraParameters = addExtraParameters(getParameters(request), request);
        Intent intent = new Intent(this.loginClient.getActivity(), CustomTabMainActivity.class);
        intent.putExtra(CustomTabMainActivity.EXTRA_PARAMS, addExtraParameters);
        intent.putExtra(CustomTabMainActivity.EXTRA_CHROME_PACKAGE, getChromePackage());
        this.loginClient.getFragment().startActivityForResult(intent, 1);
        return true;
    }

    private boolean isCustomTabsAllowed() {
        return isCustomTabsEnabled() && getChromePackage() != null && isCustomTabsCompatibleWithAutofill() && Validate.hasCustomTabRedirectActivity(FacebookSdk.getApplicationContext());
    }

    private boolean isCustomTabsEnabled() {
        FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(Utility.getMetadataApplicationId(this.loginClient.getActivity()));
        return appSettingsWithoutQuery != null && appSettingsWithoutQuery.getCustomTabsEnabled();
    }

    private String getChromePackage() {
        String str = this.currentPackage;
        if (str != null) {
            return str;
        }
        FragmentActivity activity = this.loginClient.getActivity();
        List<ResolveInfo> queryIntentServices = activity.getPackageManager().queryIntentServices(new Intent(CUSTOM_TABS_SERVICE_ACTION), 0);
        if (queryIntentServices != null) {
            HashSet hashSet = new HashSet(Arrays.asList(CHROME_PACKAGES));
            for (ResolveInfo resolveInfo : queryIntentServices) {
                ServiceInfo serviceInfo = resolveInfo.serviceInfo;
                if (serviceInfo != null && hashSet.contains(serviceInfo.packageName)) {
                    this.currentPackage = serviceInfo.packageName;
                    return this.currentPackage;
                }
            }
        }
        return null;
    }

    private boolean isCustomTabsCompatibleWithAutofill() {
        return !Utility.isAutofillAvailable(this.loginClient.getActivity());
    }

    /* access modifiers changed from: 0000 */
    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            return super.onActivityResult(i, i2, intent);
        }
        Request pendingRequest = this.loginClient.getPendingRequest();
        if (i2 == -1) {
            onCustomTabComplete(intent.getStringExtra(CustomTabMainActivity.EXTRA_URL), pendingRequest);
            return true;
        }
        super.onComplete(pendingRequest, null, new FacebookOperationCanceledException());
        return false;
    }

    private void onCustomTabComplete(String str, Request request) {
        int i;
        if (str != null && str.startsWith(CustomTabMainActivity.getRedirectUrl())) {
            Uri parse = Uri.parse(str);
            Bundle parseUrlQueryString = Utility.parseUrlQueryString(parse.getQuery());
            parseUrlQueryString.putAll(Utility.parseUrlQueryString(parse.getFragment()));
            if (!validateChallengeParam(parseUrlQueryString)) {
                super.onComplete(request, null, new FacebookException("Invalid state parameter"));
                return;
            }
            String string = parseUrlQueryString.getString("error");
            if (string == null) {
                string = parseUrlQueryString.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
            }
            String string2 = parseUrlQueryString.getString("error_msg");
            if (string2 == null) {
                string2 = parseUrlQueryString.getString("error_message");
            }
            if (string2 == null) {
                string2 = parseUrlQueryString.getString("error_description");
            }
            String string3 = parseUrlQueryString.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
            if (!Utility.isNullOrEmpty(string3)) {
                try {
                    i = Integer.parseInt(string3);
                } catch (NumberFormatException unused) {
                    i = -1;
                }
            } else {
                i = -1;
            }
            if (Utility.isNullOrEmpty(string) && Utility.isNullOrEmpty(string2) && i == -1) {
                super.onComplete(request, parseUrlQueryString, null);
            } else if (string != null && (string.equals("access_denied") || string.equals("OAuthAccessDeniedException"))) {
                super.onComplete(request, null, new FacebookOperationCanceledException());
            } else if (i == API_EC_DIALOG_CANCEL) {
                super.onComplete(request, null, new FacebookOperationCanceledException());
            } else {
                super.onComplete(request, null, new FacebookServiceException(new FacebookRequestError(i, string, string2), string2));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void putChallengeParam(JSONObject jSONObject) throws JSONException {
        jSONObject.put("7_challenge", this.expectedChallenge);
    }

    private boolean validateChallengeParam(Bundle bundle) {
        try {
            String string = bundle.getString("state");
            if (string == null) {
                return false;
            }
            return new JSONObject(string).getString("7_challenge").equals(this.expectedChallenge);
        } catch (JSONException unused) {
            return false;
        }
    }

    CustomTabLoginMethodHandler(Parcel parcel) {
        super(parcel);
        this.expectedChallenge = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.expectedChallenge);
    }
}
