package com.crashlytics.android.core;

import android.content.Context;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.PromptSettingsData;

class DialogStringResolver {
    private final Context context;
    private final PromptSettingsData promptData;

    public DialogStringResolver(Context context2, PromptSettingsData promptSettingsData) {
        this.context = context2;
        this.promptData = promptSettingsData;
    }

    public String getTitle() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionPromptTitle", this.promptData.title);
    }

    public String getMessage() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionPromptMessage", this.promptData.message);
    }

    public String getSendButtonTitle() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionSendTitle", this.promptData.sendButtonTitle);
    }

    public String getAlwaysSendButtonTitle() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionAlwaysSendTitle", this.promptData.alwaysSendButtonTitle);
    }

    public String getCancelButtonTitle() {
        return resourceOrFallbackValue("com.crashlytics.CrashSubmissionCancelTitle", this.promptData.cancelButtonTitle);
    }

    private String resourceOrFallbackValue(String str, String str2) {
        return stringOrFallback(CommonUtils.getStringsFileValue(this.context, str), str2);
    }

    private String stringOrFallback(String str, String str2) {
        return isNullOrEmpty(str) ? str2 : str;
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
