package com.crashlytics.android.answers;

import com.crashlytics.android.answers.AnswersEvent;
import io.fabric.sdk.android.Fabric;

public abstract class AnswersEvent<T extends AnswersEvent> {
    final AnswersAttributes customAttributes = new AnswersAttributes(this.validator);
    final AnswersEventValidator validator = new AnswersEventValidator(20, 100, Fabric.isDebuggable());
}
