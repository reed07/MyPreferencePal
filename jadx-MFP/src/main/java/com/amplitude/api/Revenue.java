package com.amplitude.api;

import org.json.JSONObject;

public class Revenue {
    private static AmplitudeLog logger = AmplitudeLog.getLogger();
    protected Double price = null;
    protected String productId = null;
    protected JSONObject properties = null;
    protected int quantity = 1;
    protected String receipt = null;
    protected String receiptSig = null;
    protected String revenueType = null;

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Revenue revenue = (Revenue) obj;
        if (this.quantity != revenue.quantity) {
            return false;
        }
        String str = this.productId;
        if (str == null ? revenue.productId != null : !str.equals(revenue.productId)) {
            return false;
        }
        Double d = this.price;
        if (d == null ? revenue.price != null : !d.equals(revenue.price)) {
            return false;
        }
        String str2 = this.revenueType;
        if (str2 == null ? revenue.revenueType != null : !str2.equals(revenue.revenueType)) {
            return false;
        }
        String str3 = this.receipt;
        if (str3 == null ? revenue.receipt != null : !str3.equals(revenue.receipt)) {
            return false;
        }
        String str4 = this.receiptSig;
        if (str4 == null ? revenue.receiptSig != null : !str4.equals(revenue.receiptSig)) {
            return false;
        }
        JSONObject jSONObject = this.properties;
        if (jSONObject == null ? revenue.properties != null : !Utils.compareJSONObjects(jSONObject, revenue.properties)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        String str = this.productId;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.quantity) * 31;
        Double d = this.price;
        int hashCode2 = (hashCode + (d != null ? d.hashCode() : 0)) * 31;
        String str2 = this.revenueType;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.receipt;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.receiptSig;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        JSONObject jSONObject = this.properties;
        if (jSONObject != null) {
            i = jSONObject.hashCode();
        }
        return hashCode5 + i;
    }
}
