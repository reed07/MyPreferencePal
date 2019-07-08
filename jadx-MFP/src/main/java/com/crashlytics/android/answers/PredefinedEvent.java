package com.crashlytics.android.answers;

import com.crashlytics.android.answers.PredefinedEvent;
import kotlin.text.Typography;

public abstract class PredefinedEvent<T extends PredefinedEvent> extends AnswersEvent<T> {
    final AnswersAttributes predefinedAttributes = new AnswersAttributes(this.validator);

    /* access modifiers changed from: 0000 */
    public abstract String getPredefinedType();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{type:\"");
        sb.append(getPredefinedType());
        sb.append(Typography.quote);
        sb.append(", predefinedAttributes:");
        sb.append(this.predefinedAttributes);
        sb.append(", customAttributes:");
        sb.append(this.customAttributes);
        sb.append("}");
        return sb.toString();
    }
}
