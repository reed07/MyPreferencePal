package com.myfitnesspal.feature.barcode.event;

import com.myfitnesspal.shared.event.MfpEventBase;

public class BarcodeScanSuccessEvent extends MfpEventBase {
    final String referrer;

    public BarcodeScanSuccessEvent(String str) {
        this.referrer = str;
    }

    public String getReferrer() {
        return this.referrer;
    }
}
