package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.NotificationAps;
import java.util.List;

public class MfpNotificationPayload {
    @Expose
    private List<MfpNotificationActionItem> actions;
    @Expose
    private NotificationAps aps;
    @Expose
    private String objectId;
    @Expose
    private MfpNotificationType objectType;
    @Expose
    private String type;
    @Expose
    private String url;
    @Expose
    private String utmCampaign;

    public MfpNotificationType getObjectType() {
        return this.objectType;
    }

    public void setObjecType(MfpNotificationType mfpNotificationType) {
        this.objectType = mfpNotificationType;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public NotificationAps getAps() {
        return this.aps;
    }

    public void setAps(NotificationAps notificationAps) {
        this.aps = notificationAps;
    }

    public void setActions(List<MfpNotificationActionItem> list) {
        this.actions = list;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getUtmCampaign() {
        return this.utmCampaign;
    }

    public void setUtmCampaign(String str) {
        this.utmCampaign = str;
    }

    public List<MfpNotificationActionItem> getActions() {
        return this.actions;
    }
}
