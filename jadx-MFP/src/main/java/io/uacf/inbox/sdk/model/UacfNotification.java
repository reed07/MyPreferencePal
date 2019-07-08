package io.uacf.inbox.sdk.model;

import com.google.gson.annotations.Expose;
import io.uacf.inbox.sdk.model.UacfNotificationContent.Body;
import io.uacf.inbox.sdk.model.UacfNotificationContent.Image;
import io.uacf.inbox.sdk.model.UacfNotificationContent.Link;
import io.uacf.inbox.sdk.model.UacfNotificationContent.PlainText;
import java.util.Date;

public class UacfNotification {
    /* access modifiers changed from: private */
    @Expose
    public UacfNotificationAnalyticData analytic;
    /* access modifiers changed from: private */
    @Expose
    public String category;
    /* access modifiers changed from: private */
    @Expose
    public String collapseKey;
    /* access modifiers changed from: private */
    @Expose
    public UacfNotificationContent content;
    /* access modifiers changed from: private */
    @Expose
    public Date createdAt;
    /* access modifiers changed from: private */
    @Expose
    public String engagementId;
    /* access modifiers changed from: private */
    @Expose
    public Date expiresAt;
    /* access modifiers changed from: private */
    @Expose
    public boolean markedAsExpired;
    /* access modifiers changed from: private */
    @Expose
    public boolean priority;
    /* access modifiers changed from: private */
    @Expose
    public String state;

    public static final class Builder {
        private UacfNotificationAnalyticData analytic;
        private String category;
        private String collapseKey;
        private UacfNotificationContent content;
        private Date createdAt;
        private String engagementId;
        private Date expiresAt;
        private boolean markedAsExpired;
        private boolean priority;
        private String state;

        public Builder() {
        }

        public Builder(UacfNotification uacfNotification) {
            if (uacfNotification != null) {
                this.engagementId = uacfNotification.engagementId;
                this.createdAt = uacfNotification.createdAt;
                this.state = uacfNotification.state;
                this.category = uacfNotification.category;
                this.collapseKey = uacfNotification.collapseKey;
                this.analytic = uacfNotification.analytic;
                this.content = uacfNotification.content;
                this.expiresAt = uacfNotification.expiresAt;
                this.markedAsExpired = uacfNotification.markedAsExpired;
            }
        }

        public Builder withEngagementId(String str) {
            this.engagementId = str;
            return this;
        }

        public Builder withCreatedAt(Date date) {
            this.createdAt = date;
            return this;
        }

        public Builder withState(String str) {
            this.state = str;
            return this;
        }

        public Builder withCategory(String str) {
            this.category = str;
            return this;
        }

        public Builder withCollapseKey(String str) {
            this.collapseKey = str;
            return this;
        }

        public Builder withAnalytic(UacfNotificationAnalyticData uacfNotificationAnalyticData) {
            this.analytic = uacfNotificationAnalyticData;
            return this;
        }

        public Builder withContent(UacfNotificationContent uacfNotificationContent) {
            this.content = uacfNotificationContent;
            return this;
        }

        public Builder withExpiresAt(Date date) {
            this.expiresAt = date;
            return this;
        }

        public Builder withMarkedAsExpired(boolean z) {
            this.markedAsExpired = z;
            return this;
        }

        public Builder withPriority(boolean z) {
            this.priority = z;
            return this;
        }

        public UacfNotification build() {
            UacfNotification uacfNotification = new UacfNotification();
            uacfNotification.engagementId = this.engagementId;
            uacfNotification.createdAt = this.createdAt;
            uacfNotification.state = this.state;
            uacfNotification.category = this.category;
            uacfNotification.collapseKey = this.collapseKey;
            uacfNotification.analytic = this.analytic;
            uacfNotification.content = this.content;
            uacfNotification.expiresAt = this.expiresAt;
            uacfNotification.markedAsExpired = this.markedAsExpired;
            uacfNotification.priority = this.priority;
            return uacfNotification;
        }
    }

    public String getEngagementId() {
        return this.engagementId;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public String getState() {
        return this.state;
    }

    public String getCategory() {
        return this.category;
    }

    public UacfNotificationAnalyticData getAnalytic() {
        return this.analytic;
    }

    public Date getExpiresAt() {
        return this.expiresAt;
    }

    public boolean getMarkedAsExpired() {
        return this.markedAsExpired;
    }

    public boolean getPriority() {
        return this.priority;
    }

    public String getBodyText() {
        Body body = getBody();
        if (body != null) {
            PlainText plainText = body.getPlainText();
            if (plainText != null) {
                return plainText.getText();
            }
        }
        return null;
    }

    public String getBodyDeepLink() {
        Body body = getBody();
        if (body != null) {
            return getDeepLink(body.getLink());
        }
        return null;
    }

    public String getPrimaryImageUri() {
        return getImageUri(getPrimaryImage());
    }

    public String getPrimaryImageDeepLink() {
        return getImageLink(getPrimaryImage());
    }

    public String getSecondaryImageUri() {
        return getImageUri(getSecondaryImage());
    }

    private Body getBody() {
        UacfNotificationContent uacfNotificationContent = this.content;
        if (uacfNotificationContent != null) {
            return uacfNotificationContent.getBody();
        }
        return null;
    }

    private Image getPrimaryImage() {
        UacfNotificationContent uacfNotificationContent = this.content;
        if (uacfNotificationContent != null) {
            return uacfNotificationContent.getPrimaryImage();
        }
        return null;
    }

    private Image getSecondaryImage() {
        UacfNotificationContent uacfNotificationContent = this.content;
        if (uacfNotificationContent != null) {
            return uacfNotificationContent.getSecondaryImage();
        }
        return null;
    }

    private String getImageUri(Image image) {
        if (image != null) {
            return image.getUri();
        }
        return null;
    }

    private String getImageLink(Image image) {
        if (image != null) {
            return getDeepLink(image.getLink());
        }
        return null;
    }

    private String getDeepLink(Link link) {
        if (link != null) {
            return link.getDeeplink();
        }
        return null;
    }
}
