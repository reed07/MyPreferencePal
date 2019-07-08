package io.uacf.rollouts.internal.model;

import com.google.gson.annotations.Expose;

public class Variant {
    @Expose
    private String rolloutName;
    @Expose
    private Integer rolloutVersion;
    @Expose
    private Boolean trackEvent;
    @Expose
    private Integer variantIndex;
    @Expose
    private String variantName;

    public Variant(Boolean bool, Integer num, String str, Integer num2, String str2) {
        this.variantIndex = num2;
        this.rolloutName = str;
        this.rolloutVersion = num;
        this.trackEvent = bool;
        this.variantName = str2;
    }

    public String getRolloutName() {
        return this.rolloutName;
    }

    public Integer getRolloutVersion() {
        return this.rolloutVersion;
    }

    public String getVariantName() {
        return this.variantName;
    }

    public Integer getVariantIndex() {
        Integer num = this.variantIndex;
        if (num == null || num.intValue() < 0) {
            return Integer.valueOf(-1);
        }
        return this.variantIndex;
    }

    public Boolean isVariantTracked() {
        return this.trackEvent;
    }
}
