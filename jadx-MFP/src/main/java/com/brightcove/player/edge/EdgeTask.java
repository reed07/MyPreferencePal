package com.brightcove.player.edge;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.event.RegisteringEventEmitter;
import com.brightcove.player.network.HttpRequestConfig;
import com.brightcove.player.network.HttpService;
import com.brightcove.player.util.ErrorUtil;
import com.brightcove.player.util.EventEmitterUtil;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import kotlin.text.Typography;
import org.json.JSONException;
import org.json.JSONObject;

@ListensFor(events = {})
@Emits(events = {"analyticsCatalogRequest", "analyticsCatalogResponse", "error"})
abstract class EdgeTask extends AsyncTask<URI, Void, JSONObject> implements Component {
    public static final String AD_SUPPORTED = "AD_SUPPORTED";
    private static final String BRIGHTCOVE_POLICY_HEADER_KEY = "BCOV-POLICY";
    public static final String ECONOMICS = "economics";
    public static final String FREE = "FREE";
    private static final String TAG = "EdgeTask";
    protected String account;
    protected String baseURL;
    protected List<String> errors = new ArrayList();
    protected EventEmitter eventEmitter;
    protected final HttpRequestConfig httpRequestConfig;
    protected HttpService httpService = new HttpService();
    private String policy;
    protected URI uri;

    EdgeTask(@NonNull EventEmitter eventEmitter2, @NonNull String str, @NonNull HttpRequestConfig httpRequestConfig2, @NonNull String str2, @NonNull String str3) {
        this.eventEmitter = RegisteringEventEmitter.build(eventEmitter2, EdgeTask.class);
        this.baseURL = str;
        this.account = str2;
        this.policy = str3;
        this.httpRequestConfig = httpRequestConfig2;
    }

    /* access modifiers changed from: protected */
    public JSONObject doInBackground(URI... uriArr) {
        if (uriArr == null || uriArr.length != 1) {
            throw new IllegalArgumentException(ErrorUtil.getMessage(ErrorUtil.URI_REQUIRED));
        }
        this.uri = uriArr[0];
        HashMap hashMap = new HashMap();
        hashMap.put(BRIGHTCOVE_POLICY_HEADER_KEY, this.policy);
        hashMap.putAll(this.httpRequestConfig.getRequestHeaders());
        try {
            return this.httpService.doJSONGetRequest(this.uri, hashMap);
        } catch (IOException e) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("IOException: ");
            sb.append(e);
            Log.e(str, sb.toString());
            EventEmitterUtil.emitError(this.eventEmitter, String.format(Locale.getDefault(), ErrorUtil.getMessage(ErrorUtil.URI_ERROR), new Object[]{this.uri.toString()}), e);
            return null;
        } catch (JSONException e2) {
            String str2 = TAG;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("JSONException");
            sb2.append(e2);
            Log.e(str2, sb2.toString());
            EventEmitterUtil.emitError(this.eventEmitter, "Not a valid JSON Response", e2);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public URI createURI(String... strArr) throws URISyntaxException {
        StringBuilder sb = new StringBuilder();
        sb.append(this.baseURL);
        int i = 0;
        if (strArr != null && strArr.length > 0) {
            for (String str : strArr) {
                sb.append('/');
                sb.append(str);
            }
        }
        for (Entry entry : this.httpRequestConfig.getQueryParameters().entrySet()) {
            String str2 = (String) entry.getKey();
            String str3 = (String) entry.getValue();
            if (!(str2 == null || str3 == null)) {
                if (i == 0) {
                    sb.append('?');
                } else {
                    sb.append(Typography.amp);
                }
                sb.append(str2);
                sb.append('=');
                sb.append(str3);
                i++;
            }
        }
        return new URI(sb.toString());
    }
}
