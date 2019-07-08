package com.crashlytics.android.answers;

import kotlin.text.Typography;

public class CustomEvent extends AnswersEvent<CustomEvent> {
    private final String eventName;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{eventName:\"");
        sb.append(this.eventName);
        sb.append(Typography.quote);
        sb.append(", customAttributes:");
        sb.append(this.customAttributes);
        sb.append("}");
        return sb.toString();
    }
}
