package com.inmobi.ads;

import com.inmobi.ads.InMobiAdRequestStatus.StatusCode;
import com.inmobi.commons.core.network.d;

/* compiled from: AdNetworkResponse */
public final class g {
    d a;
    InMobiAdRequestStatus b;
    f c;

    public g(f fVar, d dVar) {
        this.c = fVar;
        this.a = dVar;
        if (this.a.b != null) {
            switch (this.a.b.a) {
                case NETWORK_UNAVAILABLE_ERROR:
                    this.b = new InMobiAdRequestStatus(StatusCode.NETWORK_UNREACHABLE);
                    return;
                case BAD_REQUEST:
                    this.b = new InMobiAdRequestStatus(StatusCode.REQUEST_INVALID);
                    if (this.a.b.b != null) {
                        this.b.setCustomMessage(this.a.b.b);
                        return;
                    }
                    break;
                case HTTP_GATEWAY_TIMEOUT:
                    this.b = new InMobiAdRequestStatus(StatusCode.REQUEST_TIMED_OUT);
                    return;
                case HTTP_INTERNAL_SERVER_ERROR:
                case HTTP_NOT_IMPLEMENTED:
                case HTTP_BAD_GATEWAY:
                case HTTP_SERVER_NOT_AVAILABLE:
                case HTTP_VERSION_NOT_SUPPORTED:
                    this.b = new InMobiAdRequestStatus(StatusCode.SERVER_ERROR);
                    return;
                case GDPR_COMPLIANCE_ENFORCED:
                    this.b = new InMobiAdRequestStatus(StatusCode.GDPR_COMPLIANCE_ENFORCED);
                    return;
                default:
                    this.b = new InMobiAdRequestStatus(StatusCode.INTERNAL_ERROR);
                    break;
            }
        }
    }
}
