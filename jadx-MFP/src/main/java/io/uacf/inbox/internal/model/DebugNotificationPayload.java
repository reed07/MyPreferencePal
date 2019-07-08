package io.uacf.inbox.internal.model;

import android.text.TextUtils;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.annotations.Expose;
import com.uacf.core.mapping.GsonMappableIso8601Date;
import com.uacf.core.mapping.GsonObjectMapper;
import com.uacf.core.util.Strings;
import io.uacf.core.app.UacfAppId;
import java.util.Random;
import java.util.UUID;

/* compiled from: DebugPayload */
class DebugNotificationPayload {
    @Expose
    private NotificationAnalyticData analyticData;
    @Expose
    private String collapseKey;
    @Expose
    private NotificationContent content;
    @Expose
    private GsonMappableIso8601Date createdAt;
    @Expose
    private String engagementId;
    @Expose
    private String expiredAction = "DO_NOTHING";
    @Expose
    private GsonMappableIso8601Date expiresAt;
    @Expose
    private boolean priority;
    @Expose
    private String state;

    public DebugNotificationPayload(String str, UacfAppId uacfAppId, String str2, String str3, String str4, boolean z, GsonMappableIso8601Date gsonMappableIso8601Date, String str5) {
        String str6;
        if (!Strings.notEmpty(str2)) {
            str2 = UUID.randomUUID().toString();
        }
        this.collapseKey = str2;
        this.createdAt = new GsonMappableIso8601Date();
        this.engagementId = str4;
        this.state = "PENDING";
        GsonObjectMapper gsonObjectMapper = new GsonObjectMapper(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        GsonObjectMapper withType = gsonObjectMapper.withType(NotificationAnalyticData.class);
        if (TextUtils.isEmpty(str5)) {
            str6 = "{\n          \"data\": { }\n      }";
        } else {
            str6 = String.format("{\n          \"data\": {\n              \"notification_category\": \"%s\"\n          }\n      }", new Object[]{str5});
        }
        this.analyticData = (NotificationAnalyticData) withType.tryMapFrom(str6);
        StringBuilder sb = new StringBuilder();
        sb.append("User");
        sb.append(new Random().nextInt(10000000));
        String sb2 = sb.toString();
        if (!Strings.notEmpty(str3)) {
            str3 = "{\n          \"primary_image\": {\n              \"link\": {\n                  \"deeplink\": \"mmfapp://something/34324/\"\n              },\n              \"uri\": \"https://cdn.uacf.io/dfgnkgjdfg.png\"\n          },\n          \"secondary_image\": null,\n          \"body\": {\n              \"link\": {\n                \"deeplink\": \"mmfapp://something/34324/\"  \n              },\n              \"plain_text\": {\n                  \"text\": \"%1$s wants to be your friend\"\n              },\n              \"image\": null\n          }\n      }";
        }
        this.priority = z;
        this.expiresAt = gsonMappableIso8601Date;
        this.content = (NotificationContent) gsonObjectMapper.withType(NotificationContent.class).tryMapFrom(String.format(str3, new Object[]{sb2}));
    }
}
