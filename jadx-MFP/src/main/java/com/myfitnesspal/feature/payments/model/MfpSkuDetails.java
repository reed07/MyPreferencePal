package com.myfitnesspal.feature.payments.model;

import com.myfitnesspal.feature.payments.util.GooglePlayConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class MfpSkuDetails {
    private final String mDescription;
    private final String mItemType;
    private final String mJson;
    private final String mPrice;
    private final long mPriceAmountMicros;
    private final String mPriceCurrencyCode;
    private final String mSku;
    private final String mTitle;
    private final String mType;

    public MfpSkuDetails(String str) throws JSONException {
        this("inapp", str);
    }

    public MfpSkuDetails(String str, String str2) throws JSONException {
        this.mItemType = str;
        this.mJson = str2;
        JSONObject jSONObject = new JSONObject(this.mJson);
        this.mSku = jSONObject.optString(GooglePlayConstants.BILLING_JSON_FIELD_PRODUCT_ID);
        this.mType = jSONObject.optString("type");
        this.mPrice = jSONObject.optString("price");
        this.mPriceAmountMicros = jSONObject.optLong(GooglePlayConstants.BILLING_JSON_FIELD_PRICE_AMOUNT_MICROS);
        this.mPriceCurrencyCode = jSONObject.optString(GooglePlayConstants.BILLING_JSON_FIELD_PRICE_CURRENCY_CODE);
        this.mTitle = jSONObject.optString("title");
        this.mDescription = jSONObject.optString("description");
    }

    public String getSku() {
        return this.mSku;
    }

    public String getType() {
        return this.mType;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public long getPriceAmountMicros() {
        return this.mPriceAmountMicros;
    }

    public String getPriceCurrencyCode() {
        return this.mPriceCurrencyCode;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SkuDetails:");
        sb.append(this.mJson);
        return sb.toString();
    }
}
