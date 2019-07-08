package com.crashlytics.android.answers;

import com.google.firebase.analytics.FirebaseAnalytics.Event;

public class LoginEvent extends PredefinedEvent<LoginEvent> {
    /* access modifiers changed from: 0000 */
    public String getPredefinedType() {
        return Event.LOGIN;
    }
}
