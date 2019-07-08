package com.inmobi.ads;

public final class InMobiAdRequestStatus {
    private StatusCode a;
    private String b;

    public enum StatusCode {
        NO_ERROR,
        NETWORK_UNREACHABLE,
        NO_FILL,
        REQUEST_INVALID,
        REQUEST_PENDING,
        REQUEST_TIMED_OUT,
        INTERNAL_ERROR,
        SERVER_ERROR,
        AD_ACTIVE,
        EARLY_REFRESH_REQUEST,
        AD_NO_LONGER_AVAILABLE,
        MISSING_REQUIRED_DEPENDENCIES,
        REPETITIVE_LOAD,
        GDPR_COMPLIANCE_ENFORCED,
        LOAD_CALLED_AFTER_GET_SIGNALS,
        GET_SIGNALS_NOT_CALLED_FOR_LOAD_WITH_RESPONSE,
        GET_SIGNALS_CALLED_WHILE_LOADING,
        FETCHING_SIGNALS_STATE_ERROR,
        LOAD_WITH_RESPONSE_CALLED_WHILE_LOADING,
        INVALID_RESPONSE_IN_LOAD
    }

    public InMobiAdRequestStatus(StatusCode statusCode) {
        this.a = statusCode;
        switch (this.a) {
            case INTERNAL_ERROR:
                this.b = "The InMobi SDK encountered an internal error.";
                return;
            case NETWORK_UNREACHABLE:
                this.b = "The Internet is unreachable. Please check your Internet connection.";
                return;
            case REQUEST_INVALID:
                this.b = "An invalid ad request was sent and was rejected by the Ad Network. Please validate the ad request and try again";
                return;
            case REQUEST_PENDING:
                this.b = "The SDK is pending response to a previous ad request. Please wait for the previous ad request to complete before requesting for another ad.";
                return;
            case REQUEST_TIMED_OUT:
                this.b = "The Ad Request timed out waiting for a response from the network. This can be caused due to a bad network connection. Please try again after a few minutes.";
                return;
            case SERVER_ERROR:
                this.b = "The Ad Server encountered an error when processing the ad request. This may be a transient issue. Please try again in a few minutes";
                return;
            case NO_FILL:
                this.b = "Ad request successful but no ad served.";
                return;
            case AD_ACTIVE:
                this.b = "The Ad Request could not be submitted as the user is viewing another Ad.";
                return;
            case EARLY_REFRESH_REQUEST:
                this.b = "The Ad Request cannot be done so frequently. Please wait for some time before loading another ad.";
                return;
            case AD_NO_LONGER_AVAILABLE:
                this.b = "An ad is no longer available. Please call load() to fetch a fresh ad.";
                return;
            case MISSING_REQUIRED_DEPENDENCIES:
                this.b = "The SDK rejected the ad request as one or more required dependencies could not be found. Please ensure you have included the required dependencies.";
                return;
            case REPETITIVE_LOAD:
                this.b = "The SDK rejected the ad load request. Multiple load() call on the same object is not allowed if the previous ad request was successful.";
                return;
            case GDPR_COMPLIANCE_ENFORCED:
                this.b = "Network Request dropped as current request is not GDPR compliant.";
                return;
            case GET_SIGNALS_NOT_CALLED_FOR_LOAD_WITH_RESPONSE:
                this.b = "getSignals() must be called before calling load(response).";
                return;
            case GET_SIGNALS_CALLED_WHILE_LOADING:
                this.b = "An ad load is already in progress, getSignals() call in this state is not allowed.";
                return;
            case FETCHING_SIGNALS_STATE_ERROR:
                this.b = "One getSignals request is already been processed, please wait for the result and try again.";
                return;
            case LOAD_WITH_RESPONSE_CALLED_WHILE_LOADING:
                this.b = "An ad load is already in progress, load(response) call in this state is not allowed.";
                return;
            case LOAD_CALLED_AFTER_GET_SIGNALS:
                this.b = "The load() API cannot be called once the getSignals(Bundle) has been called. Call load(byte[]) to render ad, if getSignals(Bundle) was called.";
                return;
            case INVALID_RESPONSE_IN_LOAD:
                this.b = "Null or empty response as parameter is not allowed in load(response).";
                break;
        }
    }

    public final InMobiAdRequestStatus setCustomMessage(String str) {
        if (str != null) {
            this.b = str;
        }
        return this;
    }

    public final StatusCode getStatusCode() {
        return this.a;
    }

    public final String getMessage() {
        return this.b;
    }
}
