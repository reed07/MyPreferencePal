package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.internal.FetchedAppSettings;
import com.facebook.internal.FetchedAppSettingsManager;
import com.facebook.internal.Utility;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public final class FacebookRequestError implements Parcelable {
    private static final String BODY_KEY = "body";
    private static final String CODE_KEY = "code";
    public static final Creator<FacebookRequestError> CREATOR = new Creator<FacebookRequestError>() {
        public FacebookRequestError createFromParcel(Parcel parcel) {
            return new FacebookRequestError(parcel);
        }

        public FacebookRequestError[] newArray(int i) {
            return new FacebookRequestError[i];
        }
    };
    private static final String ERROR_CODE_FIELD_KEY = "code";
    private static final String ERROR_CODE_KEY = "error_code";
    private static final String ERROR_IS_TRANSIENT_KEY = "is_transient";
    private static final String ERROR_KEY = "error";
    private static final String ERROR_MESSAGE_FIELD_KEY = "message";
    private static final String ERROR_MSG_KEY = "error_msg";
    private static final String ERROR_REASON_KEY = "error_reason";
    private static final String ERROR_SUB_CODE_KEY = "error_subcode";
    private static final String ERROR_TYPE_FIELD_KEY = "type";
    private static final String ERROR_USER_MSG_KEY = "error_user_msg";
    private static final String ERROR_USER_TITLE_KEY = "error_user_title";
    static final Range HTTP_RANGE_SUCCESS = new Range(200, 299);
    public static final int INVALID_ERROR_CODE = -1;
    public static final int INVALID_HTTP_STATUS_CODE = -1;
    private final Object batchRequestResult;
    private final Category category;
    private final HttpURLConnection connection;
    private final int errorCode;
    private final String errorMessage;
    private final String errorRecoveryMessage;
    private final String errorType;
    private final String errorUserMessage;
    private final String errorUserTitle;
    private final FacebookException exception;
    private final JSONObject requestResult;
    private final JSONObject requestResultBody;
    private final int requestStatusCode;
    private final int subErrorCode;

    public enum Category {
        LOGIN_RECOVERABLE,
        OTHER,
        TRANSIENT
    }

    private static class Range {
        private final int end;
        private final int start;

        private Range(int i, int i2) {
            this.start = i;
            this.end = i2;
        }

        /* access modifiers changed from: 0000 */
        public boolean contains(int i) {
            return this.start <= i && i <= this.end;
        }
    }

    public int describeContents() {
        return 0;
    }

    private FacebookRequestError(int i, int i2, int i3, String str, String str2, String str3, String str4, boolean z, JSONObject jSONObject, JSONObject jSONObject2, Object obj, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        boolean z2;
        Category category2;
        this.requestStatusCode = i;
        this.errorCode = i2;
        this.subErrorCode = i3;
        this.errorType = str;
        this.errorMessage = str2;
        this.requestResultBody = jSONObject;
        this.requestResult = jSONObject2;
        this.batchRequestResult = obj;
        this.connection = httpURLConnection;
        this.errorUserTitle = str3;
        this.errorUserMessage = str4;
        if (facebookException != null) {
            this.exception = facebookException;
            z2 = true;
        } else {
            this.exception = new FacebookServiceException(this, str2);
            z2 = false;
        }
        FacebookRequestErrorClassification errorClassification = getErrorClassification();
        if (z2) {
            category2 = Category.OTHER;
        } else {
            category2 = errorClassification.classify(i2, i3, z);
        }
        this.category = category2;
        this.errorRecoveryMessage = errorClassification.getRecoveryMessage(this.category);
    }

    FacebookRequestError(HttpURLConnection httpURLConnection, Exception exc) {
        Exception exc2 = exc;
        this(-1, -1, -1, null, null, null, null, false, null, null, null, httpURLConnection, exc2 instanceof FacebookException ? (FacebookException) exc2 : new FacebookException((Throwable) exc2));
    }

    public FacebookRequestError(int i, String str, String str2) {
        this(-1, i, -1, str, str2, null, null, false, null, null, null, null, null);
    }

    public Category getCategory() {
        return this.category;
    }

    public int getRequestStatusCode() {
        return this.requestStatusCode;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public int getSubErrorCode() {
        return this.subErrorCode;
    }

    public String getErrorType() {
        return this.errorType;
    }

    public String getErrorMessage() {
        String str = this.errorMessage;
        if (str != null) {
            return str;
        }
        return this.exception.getLocalizedMessage();
    }

    public String getErrorRecoveryMessage() {
        return this.errorRecoveryMessage;
    }

    public String getErrorUserMessage() {
        return this.errorUserMessage;
    }

    public String getErrorUserTitle() {
        return this.errorUserTitle;
    }

    public JSONObject getRequestResultBody() {
        return this.requestResultBody;
    }

    public JSONObject getRequestResult() {
        return this.requestResult;
    }

    public Object getBatchRequestResult() {
        return this.batchRequestResult;
    }

    public HttpURLConnection getConnection() {
        return this.connection;
    }

    public FacebookException getException() {
        return this.exception;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("{HttpStatus: ");
        sb.append(this.requestStatusCode);
        sb.append(", errorCode: ");
        sb.append(this.errorCode);
        sb.append(", subErrorCode: ");
        sb.append(this.subErrorCode);
        sb.append(", errorType: ");
        sb.append(this.errorType);
        sb.append(", errorMessage: ");
        sb.append(getErrorMessage());
        sb.append("}");
        return sb.toString();
    }

    static FacebookRequestError checkResponseAndCreateError(JSONObject jSONObject, Object obj, HttpURLConnection httpURLConnection) {
        String str;
        boolean z;
        String str2;
        String str3;
        String str4;
        int i;
        JSONObject jSONObject2 = jSONObject;
        try {
            if (jSONObject2.has("code")) {
                int i2 = jSONObject2.getInt("code");
                Object stringPropertyAsJSON = Utility.getStringPropertyAsJSON(jSONObject2, "body", GraphResponse.NON_JSON_RESPONSE_PROPERTY);
                if (stringPropertyAsJSON != null && (stringPropertyAsJSON instanceof JSONObject)) {
                    JSONObject jSONObject3 = (JSONObject) stringPropertyAsJSON;
                    boolean z2 = true;
                    int i3 = -1;
                    if (jSONObject3.has("error")) {
                        JSONObject jSONObject4 = (JSONObject) Utility.getStringPropertyAsJSON(jSONObject3, "error", null);
                        str4 = jSONObject4.optString("type", null);
                        str3 = jSONObject4.optString("message", null);
                        int optInt = jSONObject4.optInt("code", -1);
                        int optInt2 = jSONObject4.optInt("error_subcode", -1);
                        String optString = jSONObject4.optString(ERROR_USER_MSG_KEY, null);
                        str = jSONObject4.optString(ERROR_USER_TITLE_KEY, null);
                        i = optInt2;
                        i3 = optInt;
                        str2 = optString;
                        z = jSONObject4.optBoolean(ERROR_IS_TRANSIENT_KEY, false);
                    } else {
                        if (!jSONObject3.has("error_code") && !jSONObject3.has(ERROR_MSG_KEY)) {
                            if (!jSONObject3.has(ERROR_REASON_KEY)) {
                                str4 = null;
                                str3 = null;
                                str2 = null;
                                str = null;
                                z2 = false;
                                i = -1;
                                z = false;
                            }
                        }
                        String optString2 = jSONObject3.optString(ERROR_REASON_KEY, null);
                        String optString3 = jSONObject3.optString(ERROR_MSG_KEY, null);
                        int optInt3 = jSONObject3.optInt("error_code", -1);
                        i = jSONObject3.optInt("error_subcode", -1);
                        i3 = optInt3;
                        str2 = null;
                        str = null;
                        z = false;
                        str3 = optString3;
                        str4 = optString2;
                    }
                    if (z2) {
                        FacebookRequestError facebookRequestError = new FacebookRequestError(i2, i3, i, str4, str3, str, str2, z, jSONObject3, jSONObject, obj, httpURLConnection, null);
                        return facebookRequestError;
                    }
                }
                if (!HTTP_RANGE_SUCCESS.contains(i2)) {
                    FacebookRequestError facebookRequestError2 = new FacebookRequestError(i2, -1, -1, null, null, null, null, false, jSONObject2.has("body") ? (JSONObject) Utility.getStringPropertyAsJSON(jSONObject2, "body", GraphResponse.NON_JSON_RESPONSE_PROPERTY) : null, jSONObject, obj, httpURLConnection, null);
                    return facebookRequestError2;
                }
            }
        } catch (JSONException unused) {
        }
        return null;
    }

    static synchronized FacebookRequestErrorClassification getErrorClassification() {
        synchronized (FacebookRequestError.class) {
            FetchedAppSettings appSettingsWithoutQuery = FetchedAppSettingsManager.getAppSettingsWithoutQuery(FacebookSdk.getApplicationId());
            if (appSettingsWithoutQuery == null) {
                FacebookRequestErrorClassification defaultErrorClassification = FacebookRequestErrorClassification.getDefaultErrorClassification();
                return defaultErrorClassification;
            }
            FacebookRequestErrorClassification errorClassification = appSettingsWithoutQuery.getErrorClassification();
            return errorClassification;
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.requestStatusCode);
        parcel.writeInt(this.errorCode);
        parcel.writeInt(this.subErrorCode);
        parcel.writeString(this.errorType);
        parcel.writeString(this.errorMessage);
        parcel.writeString(this.errorUserTitle);
        parcel.writeString(this.errorUserMessage);
    }

    private FacebookRequestError(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), false, null, null, null, null, null);
    }
}
