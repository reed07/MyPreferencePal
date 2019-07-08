package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.network.MoPubRequestUtils;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.Response.Listener;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.HttpHeaderParser;
import com.mopub.volley.toolbox.JsonRequest;
import com.myfitnesspal.shared.constants.Constants.Extras;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PositioningRequest extends JsonRequest<MoPubClientPositioning> {
    @NonNull
    private final Context mContext;
    @NonNull
    private final String mOriginalUrl;

    public PositioningRequest(@NonNull Context context, String str, Listener<MoPubClientPositioning> listener, ErrorListener errorListener) {
        super(MoPubRequestUtils.chooseMethod(str), MoPubRequestUtils.truncateQueryParamsIfPost(str), null, listener, errorListener);
        this.mOriginalUrl = str;
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public void deliverResponse(MoPubClientPositioning moPubClientPositioning) {
        super.deliverResponse(moPubClientPositioning);
    }

    /* access modifiers changed from: protected */
    public Response<MoPubClientPositioning> parseNetworkResponse(NetworkResponse networkResponse) {
        if (networkResponse.statusCode != 200) {
            return Response.error(new VolleyError(networkResponse));
        }
        if (networkResponse.data.length == 0) {
            return Response.error(new VolleyError("Empty positioning response", new JSONException("Empty response")));
        }
        try {
            return Response.success(parseJson(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new VolleyError("Couldn't parse JSON from Charset", e));
        } catch (JSONException e2) {
            return Response.error(new VolleyError("JSON Parsing Error", e2));
        } catch (MoPubNetworkError e3) {
            return Response.error(e3);
        }
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @VisibleForTesting
    public MoPubClientPositioning parseJson(@NonNull String str) throws JSONException, MoPubNetworkError {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("error", null);
        if (optString == null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("fixed");
            JSONObject optJSONObject = jSONObject.optJSONObject("repeating");
            if (optJSONArray == null && optJSONObject == null) {
                throw new JSONException("Must contain fixed or repeating positions");
            }
            MoPubClientPositioning moPubClientPositioning = new MoPubClientPositioning();
            if (optJSONArray != null) {
                parseFixedJson(optJSONArray, moPubClientPositioning);
            }
            if (optJSONObject != null) {
                parseRepeatingJson(optJSONObject, moPubClientPositioning);
            }
            return moPubClientPositioning;
        } else if (optString.equalsIgnoreCase("WARMING_UP")) {
            throw new MoPubNetworkError(Reason.WARMING_UP);
        } else {
            throw new JSONException(optString);
        }
    }

    private void parseFixedJson(@NonNull JSONArray jSONArray, @NonNull MoPubClientPositioning moPubClientPositioning) throws JSONException {
        int i = 0;
        while (i < jSONArray.length()) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            int optInt = jSONObject.optInt(Extras.SECTION, 0);
            if (optInt >= 0) {
                if (optInt <= 0) {
                    int i2 = jSONObject.getInt("position");
                    if (i2 < 0 || i2 > 65536) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Invalid position ");
                        sb.append(i2);
                        sb.append(" in JSON response");
                        throw new JSONException(sb.toString());
                    }
                    moPubClientPositioning.addFixedPosition(i2);
                }
                i++;
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid section ");
                sb2.append(optInt);
                sb2.append(" in JSON response");
                throw new JSONException(sb2.toString());
            }
        }
    }

    private void parseRepeatingJson(@NonNull JSONObject jSONObject, @NonNull MoPubClientPositioning moPubClientPositioning) throws JSONException {
        int i = jSONObject.getInt("interval");
        if (i < 2 || i > 65536) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid interval ");
            sb.append(i);
            sb.append(" in JSON response");
            throw new JSONException(sb.toString());
        }
        moPubClientPositioning.enableRepeatingPositions(i);
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getParams() {
        if (!MoPubRequestUtils.isMoPubRequest(getUrl())) {
            return null;
        }
        return MoPubRequestUtils.convertQueryToMap(this.mContext, this.mOriginalUrl);
    }

    public byte[] getBody() {
        String generateBodyFromParams = MoPubRequestUtils.generateBodyFromParams(getParams(), getUrl());
        if (generateBodyFromParams == null) {
            return null;
        }
        return generateBodyFromParams.getBytes();
    }
}
