package com.crashlytics.android.answers;

import com.google.ads.mediation.inmobi.InMobiNetworkValues;

public class RatingEvent extends PredefinedEvent<RatingEvent> {
    /* access modifiers changed from: 0000 */
    public String getPredefinedType() {
        return InMobiNetworkValues.RATING;
    }
}
