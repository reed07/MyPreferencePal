package io.uacf.inbox.internal.notification;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;

public class NotificationBatchUpdateData {
    @Expose
    List<String> engagementIds = new ArrayList();
    @Expose
    String state;

    public NotificationBatchUpdateData(String str) {
        this.state = str;
    }

    public void add(String str) {
        this.engagementIds.add(str);
    }

    public List<String> getEngagementIds() {
        return this.engagementIds;
    }
}
