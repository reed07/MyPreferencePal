package com.myfitnesspal.feature.payments.util;

import com.myfitnesspal.shared.constants.Constants.Payments.GenericError;

public interface GooglePlayConstants {
    public static final int API_VERSION = 3;
    public static final String BILLING_EXTRA_BUY_INTENT = "BUY_INTENT";
    public static final String BILLING_EXTRA_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    public static final String BILLING_EXTRA_DETAILS_LIST = "DETAILS_LIST";
    public static final String BILLING_EXTRA_INAPP_CONTINUATION_TOKEN = "INAPP_CONTINUATION_TOKEN";
    public static final String BILLING_EXTRA_INAPP_PURCHASE_DATA = "INAPP_PURCHASE_DATA";
    public static final String BILLING_EXTRA_INAPP_PURCHASE_DATA_LIST = "INAPP_PURCHASE_DATA_LIST";
    public static final String BILLING_EXTRA_ITEM_ID_LIST = "ITEM_ID_LIST";
    public static final String BILLING_EXTRA_RESPONSE_CODE = "RESPONSE_CODE";
    public static final String BILLING_EXTRA_RESPONSE_INAPP_ITEM_LIST = "INAPP_PURCHASE_ITEM_LIST";
    public static final String BILLING_EXTRA_RESPONSE_INAPP_SIGNATURE_LIST = "INAPP_DATA_SIGNATURE_LIST";
    public static final String BILLING_EXTRA_SIGNATURE = "INAPP_DATA_SIGNATURE";
    public static final String BILLING_JSON_FIELD_DEVELOPER_PAYLOAD = "developerPayload";
    public static final String BILLING_JSON_FIELD_DISPLAY_PRICE = "price";
    public static final String BILLING_JSON_FIELD_ORDER_ID = "orderId";
    public static final String BILLING_JSON_FIELD_PRICE_AMOUNT_MICROS = "price_amount_micros";
    public static final String BILLING_JSON_FIELD_PRICE_CURRENCY_CODE = "price_currency_code";
    public static final String BILLING_JSON_FIELD_PRODUCT_ID = "productId";
    public static final String BILLING_JSON_FIELD_PURCHASE_STATE = "purchaseState";
    public static final String BILLING_JSON_FIELD_PURCHASE_TOKEN = "purchaseToken";
    public static final String BILLING_JSON_FIELD_TOKEN = "token";
    public static final String BILLING_PRODUCT_TYPE_IN_APP = "inapp";
    public static final String BILLING_PRODUCT_TYPE_SUBSCRIPTION = "subs";
    public static final int BILLING_PURCHASE_STATE_CANCELED = 1;
    public static final int BILLING_PURCHASE_STATE_PURCHASED = 0;
    public static final int BILLING_PURCHASE_STATE_REFUNDED = 2;
    public static final String BILLING_VERIFICATION_KEY_FACTORY_ALGORITHM = "RSA";
    public static final String BILLING_VERIFICATION_SIGNATURE_ALGORITHM = "SHA1withRSA";
    public static final int ERROR_BASE = -1000;
    public static final String GOOGLE_BILLING_BIND_INTENT = "com.android.vending.billing.InAppBillingService.BIND";
    public static final String GOOGLE_BILLING_PACKAGE_NAME = "com.android.vending";
    public static final String ITEM_TYPE_INAPP = "inapp";
    public static final String ITEM_TYPE_SUBS = "subs";
    public static final String KEY_PART_1 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj+5Rka";
    public static final String KEY_PART_2 = "rDwdWGXXruMjNrnpt09B40EA7wKi4DLrGmPK2W2mDzRe/yjJZF";
    public static final String KEY_PART_3 = "v+Kzd11jRHRBra+FVlSFUs+RGJyVUOjGKY06va2FSeVW8IrwxF";
    public static final String KEY_PART_4 = "V2yNQUEDHjEen/ZvlJtB6RjC8Dc2uAh2/f7FdAe4i1+lOjLGAB";
    public static final String KEY_PART_5 = "eedj7iZHeuxLzhhPgerqHUADzFEGeBy1leGBei57emyndXJVYb";
    public static final String KEY_PART_6 = "oeMT8Oe6UrnEqqESzI7DYiel8weS163Hya1Jca8fffTyDKdAL1";
    public static final String KEY_PART_7 = "plYxXjYDDBPb5Kq7f5OinzgRwl734UhvxjdA1TSDqy1U2qIV8m";
    public static final String KEY_PART_8 = "h7fsPVJ77I+ct1I2GcJO1ODz+v7H10/I3gYQIDAQAB";

    public enum BillingResponse {
        BILLING_RESPONSE_RESULT_OK(0, GenericError.NO_ERROR),
        BILLING_RESPONSE_RESULT_USER_CANCELED(1, GenericError.USER_CANCELED),
        BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE(2, GenericError.EXTERNAL_BACKEND_SERVICE_ERROR),
        BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE(3, GenericError.EXTERNAL_INVOCATION_ERROR),
        BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE(4, GenericError.ITEM_UNAVAILABLE),
        BILLING_RESPONSE_RESULT_DEVELOPER_ERROR(5, GenericError.SDK_USAGE_ERROR),
        BILLING_RESPONSE_RESULT_ERROR(6, GenericError.UNKNOWN_ERROR),
        BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED(7, GenericError.ITEM_UNAVAILABLE),
        BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED(8, GenericError.ITEM_UNAVAILABLE),
        BILLING_RESPONSE_RESULT_UNKNOWN(-1, GenericError.UNKNOWN_ERROR);
        
        private final GenericError genericError;
        private final int googleResponseCode;

        private BillingResponse(int i, GenericError genericError2) {
            this.genericError = genericError2;
            this.googleResponseCode = i;
        }

        public static BillingResponse fromResponseCode(int i) {
            switch (i) {
                case 0:
                    return BILLING_RESPONSE_RESULT_OK;
                case 1:
                    return BILLING_RESPONSE_RESULT_USER_CANCELED;
                case 2:
                    return BILLING_RESPONSE_RESULT_SERVICE_UNAVAILABLE;
                case 3:
                    return BILLING_RESPONSE_RESULT_BILLING_UNAVAILABLE;
                case 4:
                    return BILLING_RESPONSE_RESULT_ITEM_UNAVAILABLE;
                case 5:
                    return BILLING_RESPONSE_RESULT_DEVELOPER_ERROR;
                case 6:
                    return BILLING_RESPONSE_RESULT_ERROR;
                case 7:
                    return BILLING_RESPONSE_RESULT_ITEM_ALREADY_OWNED;
                case 8:
                    return BILLING_RESPONSE_RESULT_ITEM_NOT_OWNED;
                default:
                    return BILLING_RESPONSE_RESULT_UNKNOWN;
            }
        }

        public int getGoogleResponseCode() {
            return this.googleResponseCode;
        }

        public GenericError getGenericError() {
            return this.genericError;
        }
    }
}
