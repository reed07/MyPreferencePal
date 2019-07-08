package com.myfitnesspal.shared.service.install;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.constants.UtmParams;
import com.uacf.core.util.Strings;
import java.util.HashMap;
import java.util.Map;

public class UtmInformation {
    @Expose
    private final String campaign;
    @Expose
    private final String content;
    @Expose
    private final String medium;
    @Expose
    private final String source;
    @Expose
    private final String term;

    public UtmInformation() {
        this(null, null, null, null, null);
    }

    public UtmInformation(String str, String str2, String str3, String str4, String str5) {
        this.campaign = str;
        this.source = str2;
        this.medium = str3;
        this.content = str4;
        this.term = str5;
    }

    public String getCampaign() {
        return this.campaign;
    }

    public String getContent() {
        return this.content;
    }

    public String getMedium() {
        return this.medium;
    }

    public String getSource() {
        return this.source;
    }

    public String getTerm() {
        return this.term;
    }

    public boolean isValid() {
        return Strings.notEmpty(this.campaign) && Strings.notEmpty(this.medium);
    }

    public Map<String, String> toMap() {
        HashMap hashMap = new HashMap();
        addToMap(hashMap);
        return hashMap;
    }

    public void addToMap(Map<String, String> map) {
        map.put("utm_campaign", this.campaign);
        map.put("utm_source", this.source);
        map.put("utm_medium", this.medium);
        map.put(UtmParams.UTM_CONTENT, this.content);
        map.put(UtmParams.UTM_TERM, this.term);
    }

    public static UtmInformation fromMap(Map<String, String> map) {
        UtmInformation utmInformation = new UtmInformation((String) map.get("utm_campaign"), (String) map.get("utm_source"), (String) map.get("utm_medium"), (String) map.get(UtmParams.UTM_CONTENT), (String) map.get(UtmParams.UTM_TERM));
        return utmInformation;
    }
}
