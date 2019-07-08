package com.facebook.stetho.inspector;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri.Builder;
import com.facebook.stetho.common.ProcessUtil;
import com.facebook.stetho.server.SocketLike;
import com.facebook.stetho.server.http.ExactPathMatcher;
import com.facebook.stetho.server.http.HandlerRegistry;
import com.facebook.stetho.server.http.HttpHandler;
import com.facebook.stetho.server.http.HttpStatus;
import com.facebook.stetho.server.http.LightHttpBody;
import com.facebook.stetho.server.http.LightHttpRequest;
import com.facebook.stetho.server.http.LightHttpResponse;
import com.mopub.common.Constants;
import com.myfitnesspal.shared.constants.HttpConstants;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChromeDiscoveryHandler implements HttpHandler {
    private static final String PAGE_ID = "1";
    private static final String PATH_ACTIVATE = "/json/activate/1";
    private static final String PATH_PAGE_LIST = "/json";
    private static final String PATH_VERSION = "/json/version";
    private static final String PROTOCOL_VERSION = "1.1";
    private static final String USER_AGENT = "Stetho";
    private static final String WEBKIT_REV = "@188492";
    private static final String WEBKIT_VERSION = "537.36 (@188492)";
    private final Context mContext;
    private final String mInspectorPath;
    @Nullable
    private LightHttpBody mPageListResponse;
    @Nullable
    private LightHttpBody mVersionResponse;

    public ChromeDiscoveryHandler(Context context, String str) {
        this.mContext = context;
        this.mInspectorPath = str;
    }

    public void register(HandlerRegistry handlerRegistry) {
        handlerRegistry.register(new ExactPathMatcher(PATH_PAGE_LIST), this);
        handlerRegistry.register(new ExactPathMatcher(PATH_VERSION), this);
        handlerRegistry.register(new ExactPathMatcher(PATH_ACTIVATE), this);
    }

    public boolean handleRequest(SocketLike socketLike, LightHttpRequest lightHttpRequest, LightHttpResponse lightHttpResponse) {
        String path = lightHttpRequest.uri.getPath();
        try {
            if (PATH_VERSION.equals(path)) {
                handleVersion(lightHttpResponse);
            } else if (PATH_PAGE_LIST.equals(path)) {
                handlePageList(lightHttpResponse);
            } else if (PATH_ACTIVATE.equals(path)) {
                handleActivate(lightHttpResponse);
            } else {
                lightHttpResponse.code = HttpStatus.HTTP_NOT_IMPLEMENTED;
                lightHttpResponse.reasonPhrase = "Not implemented";
                StringBuilder sb = new StringBuilder();
                sb.append("No support for ");
                sb.append(path);
                sb.append("\n");
                lightHttpResponse.body = LightHttpBody.create(sb.toString(), "text/plain");
            }
        } catch (JSONException e) {
            lightHttpResponse.code = 500;
            lightHttpResponse.reasonPhrase = "Internal server error";
            StringBuilder sb2 = new StringBuilder();
            sb2.append(e.toString());
            sb2.append("\n");
            lightHttpResponse.body = LightHttpBody.create(sb2.toString(), "text/plain");
        }
        return true;
    }

    private void handleVersion(LightHttpResponse lightHttpResponse) throws JSONException {
        if (this.mVersionResponse == null) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("WebKit-Version", WEBKIT_VERSION);
            jSONObject.put("User-Agent", USER_AGENT);
            jSONObject.put("Protocol-Version", PROTOCOL_VERSION);
            jSONObject.put("Browser", getAppLabelAndVersion());
            jSONObject.put("Android-Package", this.mContext.getPackageName());
            this.mVersionResponse = LightHttpBody.create(jSONObject.toString(), HttpConstants.CONTENT_TYPE_JSON);
        }
        setSuccessfulResponse(lightHttpResponse, this.mVersionResponse);
    }

    private void handlePageList(LightHttpResponse lightHttpResponse) throws JSONException {
        if (this.mPageListResponse == null) {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", "app");
            jSONObject.put("title", makeTitle());
            jSONObject.put("id", "1");
            jSONObject.put("description", "");
            StringBuilder sb = new StringBuilder();
            sb.append("ws://");
            sb.append(this.mInspectorPath);
            jSONObject.put("webSocketDebuggerUrl", sb.toString());
            jSONObject.put("devtoolsFrontendUrl", new Builder().scheme(Constants.HTTP).authority("chrome-devtools-frontend.appspot.com").appendEncodedPath("serve_rev").appendEncodedPath(WEBKIT_REV).appendEncodedPath("devtools.html").appendQueryParameter("ws", this.mInspectorPath).build().toString());
            jSONArray.put(jSONObject);
            this.mPageListResponse = LightHttpBody.create(jSONArray.toString(), HttpConstants.CONTENT_TYPE_JSON);
        }
        setSuccessfulResponse(lightHttpResponse, this.mPageListResponse);
    }

    private String makeTitle() {
        StringBuilder sb = new StringBuilder();
        sb.append(getAppLabel());
        sb.append(" (powered by Stetho)");
        String processName = ProcessUtil.getProcessName();
        int indexOf = processName.indexOf(58);
        if (indexOf >= 0) {
            sb.append(processName.substring(indexOf));
        }
        return sb.toString();
    }

    private void handleActivate(LightHttpResponse lightHttpResponse) {
        setSuccessfulResponse(lightHttpResponse, LightHttpBody.create("Target activation ignored\n", "text/plain"));
    }

    private static void setSuccessfulResponse(LightHttpResponse lightHttpResponse, LightHttpBody lightHttpBody) {
        lightHttpResponse.code = 200;
        lightHttpResponse.reasonPhrase = "OK";
        lightHttpResponse.body = lightHttpBody;
    }

    private String getAppLabelAndVersion() {
        StringBuilder sb = new StringBuilder();
        PackageManager packageManager = this.mContext.getPackageManager();
        sb.append(getAppLabel());
        sb.append('/');
        try {
            sb.append(packageManager.getPackageInfo(this.mContext.getPackageName(), 0).versionName);
            return sb.toString();
        } catch (NameNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private CharSequence getAppLabel() {
        return this.mContext.getPackageManager().getApplicationLabel(this.mContext.getApplicationInfo());
    }
}
