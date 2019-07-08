package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.uacf.core.util.NumberUtils;

public class MfpNewsFeedExerciseEntry implements MfpNewsFeedPartnerActivityEntryData {
    @Expose
    private String applicationConnectUri;
    @Expose
    private Integer applicationId;
    @Expose
    private String applicationName;
    @Expose
    private String text;

    public String getApplicationId() {
        Integer num = this.applicationId;
        return String.valueOf(num == null ? -1 : num.intValue());
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public String getText() {
        return this.text;
    }

    public MfpNewsFeedExerciseEntry setApplicationId(String str) {
        this.applicationId = Integer.valueOf(NumberUtils.tryParseInt(str));
        return this;
    }

    public MfpNewsFeedExerciseEntry setApplicationName(String str) {
        this.applicationName = str;
        return this;
    }

    public MfpNewsFeedExerciseEntry setText(String str) {
        this.text = str;
        return this;
    }

    public String getApplicationConnectUri() {
        return this.applicationConnectUri;
    }

    public MfpNewsFeedExerciseEntry setApplicationConnectUri(String str) {
        this.applicationConnectUri = str;
        return this;
    }
}
