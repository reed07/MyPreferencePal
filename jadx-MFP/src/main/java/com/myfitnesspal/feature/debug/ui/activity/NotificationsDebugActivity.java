package com.myfitnesspal.feature.debug.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.notificationinbox.service.CreateDebugNotificationTask;
import com.myfitnesspal.feature.notificationinbox.service.CreateDebugNotificationTask.CompletedEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.Toaster;
import com.squareup.otto.Subscribe;
import io.uacf.inbox.internal.notification.NotificationService;
import io.uacf.inbox.sdk.UacfNotificationSdkFactory;

public class NotificationsDebugActivity extends MfpActivity {
    private static final String CONTENT_STRING_NOTIF_1 = "{\n          \"primary_image\": {\n              \"link\": {\n                  \"deeplink\": \"mmfapp://something/34324/\"\n              },\n              \"uri\": \"https://cdn.uacf.io/dfgnkgjdfg.png\"\n          },\n          \"secondary_image\": null,\n          \"body\": {\n              \"link\": {\n                \"deeplink\": \"mmfapp://something/34324/\"  \n              },\n              \"plain_text\": {\n                  \"text\": \"%1$s wants to be your friend wants to be your friend wants to be your friend wants to be your friend\"\n              },\n              \"image\": null\n          }\n      }";
    private static final String CONTENT_STRING_NOTIF_2 = "{\n          \"primary_image\": {\n              \"link\": {\n                  \"deeplink\": \"mmfapp://something/34324/\"\n              },\n              \"uri\": \"http://1y2u3hx8yml32svgcf0087imj.wpengine.netdna-cdn.com/wp-content/uploads/2016/08/Beginners-Guides-Roundup-for-Workout-Education-960x603.png\"\n          },\n          \"secondary_image\": null,\n          \"body\": {\n              \"link\": {\n                \"deeplink\": \"mfp://myfitnesspal/blog?url=http://blog.myfitnesspal.com/6-beginners-guides-workout-inspiration/\"  \n              },\n              \"plain_text\": {\n                  \"text\": \"6 Beginners Guides for Workout Inspiration\"\n              },\n              \"image\": null\n          }\n      }";
    private static final String CONTENT_STRING_NOTIF_3 = "{\n          \"primary_image\": {\n              \"link\": {\n                  \"deeplink\": \"mmfapp://something/34324/\"\n              },\n              \"uri\": \"http://www.jrtstudio.com/sites/default/files/ico_android.png\"\n          },\n          \"secondary_image\": {\n              \"link\": {\n                  \"deeplink\": \"mmfapp://something/34324/\"\n              },\n              \"uri\": \"http://onlineabsn.marian.edu/wp-content/uploads/myfitnesspal-fitness-app-150x150.png\"\n          },\n          \"body\": {\n              \"link\": {\n                \"deeplink\": \"mmfapp://something/34324/\"  \n              },\n              \"plain_text\": {\n                  \"text\": \"Test notification with 2 images\"\n              },\n              \"image\": null\n          }\n      }";
    private static final String CONTENT_STRING_NOTIF_4 = "{\n          \"primary_image\": {\n              \"link\": {\n                  \"deeplink\": \"mmfapp://something/34324/\"\n              },\n              \"uri\": \"https://cdn.uacf.io/dfgnkgjdfg.png\"\n          },\n          \"secondary_image\": null,\n          \"body\": {\n              \"link\": {\n                \"deeplink\": \"mmfapp://something/34324/\"  \n              },\n              \"plain_text\": {\n                  \"text\": \"te envió un mensaje\"\n              },\n              \"image\": null\n          }\n      }";
    @BindView(2131363170)
    Button notif1;
    @BindView(2131363171)
    Button notif2;
    @BindView(2131363172)
    Button notif3;
    @BindView(2131363173)
    Button notif4;
    private NotificationService notificationService;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, NotificationsDebugActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.notification_debug_activity);
        this.notificationService = new UacfNotificationSdkFactory().newNotificationServiceInstance();
        this.notif1.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationsDebugActivity.this.createDebugNotification(NotificationsDebugActivity.CONTENT_STRING_NOTIF_1);
            }
        });
        this.notif2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationsDebugActivity.this.createDebugNotification(NotificationsDebugActivity.CONTENT_STRING_NOTIF_2);
            }
        });
        this.notif3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationsDebugActivity.this.createDebugNotification(NotificationsDebugActivity.CONTENT_STRING_NOTIF_3);
            }
        });
        this.notif4.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NotificationsDebugActivity.this.createDebugNotification(NotificationsDebugActivity.CONTENT_STRING_NOTIF_4);
            }
        });
    }

    /* access modifiers changed from: private */
    public void createDebugNotification(String str) {
        new CreateDebugNotificationTask(str, this.notificationService).run(getRunner());
    }

    @Subscribe
    public void onCreateDebugNotificationTask(CompletedEvent completedEvent) {
        if (completedEvent.getRunnerId() == getRunner().getId() && completedEvent.successful()) {
            Toaster.showShort((Activity) this, ((Boolean) completedEvent.getResult()).booleanValue() ? "Created test notification" : "Failed to create test notification");
        }
    }
}
