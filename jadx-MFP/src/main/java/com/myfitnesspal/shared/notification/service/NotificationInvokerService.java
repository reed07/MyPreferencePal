package com.myfitnesspal.shared.notification.service;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import com.google.android.exoplayer2.C;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.friends.ui.activity.CommentsActivity;
import com.myfitnesspal.feature.friends.ui.activity.FriendsActivity;
import com.myfitnesspal.feature.friends.ui.activity.FriendsActivity.Tab;
import com.myfitnesspal.feature.friends.ui.activity.MessagesActivity;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.home.ui.activity.HomeMessagesActivity;
import com.myfitnesspal.shared.model.v2.MfpNotificationType;
import com.myfitnesspal.shared.receiver.NotificationBroadcastReceiver;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class NotificationInvokerService extends JobService {
    public static final String QUERY_PARAM_GCM_MFP_NOTIFICATION = "gcm_mfp_notification";
    @Inject
    Lazy<ConfigService> configService;

    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }

    public boolean onStartJob(JobParameters jobParameters) {
        ((MyFitnessPalApp) getApplicationContext()).component().inject(this);
        showHomeScreen(getBaseContext());
        showNotificationActivity(getBaseContext(), jobParameters);
        jobFinished(jobParameters, false);
        return false;
    }

    private void showNotificationActivity(Context context, JobParameters jobParameters) {
        PersistableBundle extras = jobParameters.getExtras();
        String string = extras.getString(NotificationBroadcastReceiver.EXTRA_NOTIFICATION_TYPE);
        if (string != null) {
            MfpNotificationType valueOf = MfpNotificationType.valueOf(string);
            long j = extras.getLong(NotificationBroadcastReceiver.EXTRA_RESOURCE_ID);
            String string2 = extras.getString(NotificationBroadcastReceiver.EXTRA_URL);
            Intent intent = null;
            switch (valueOf) {
                case MESSAGE:
                    if (!ConfigUtils.isLeftDrawerHidden((ConfigService) this.configService.get())) {
                        intent = MessagesActivity.newStartIntent(context);
                        break;
                    } else {
                        intent = HomeMessagesActivity.newStartIntentForInbox(context);
                        break;
                    }
                case STATUS:
                    intent = CommentsActivity.newStartIntent(context, j);
                    break;
                case INVITATION:
                    intent = FriendsActivity.newStartIntent(context, j, Tab.Requests);
                    break;
                case STATUS_COMMENT:
                    intent = CommentsActivity.newStartIntent(context, j);
                    break;
                case REMINDER:
                    String appendNotificationQueryParam = appendNotificationQueryParam(string2);
                    intent = Strings.notEmpty(appendNotificationQueryParam) ? SharedIntents.newUriIntent(appendNotificationQueryParam) : Diary.newStartIntent(context);
                    break;
                case GENERIC:
                    intent = HomeActivity.newStartIntent(context);
                    break;
                case URL_HANDLER:
                    if (!Strings.isEmpty(string2)) {
                        intent = SharedIntents.newUriIntent(appendNotificationQueryParam(string2));
                        break;
                    } else {
                        return;
                    }
            }
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    private void showHomeScreen(Context context) {
        Intent newStartIntent = HomeActivity.newStartIntent(context);
        newStartIntent.addFlags(872415232);
        context.startActivity(newStartIntent);
    }

    private String appendNotificationQueryParam(String str) {
        if (Strings.isEmpty(str)) {
            return str;
        }
        return Uri.parse(str).buildUpon().appendQueryParameter(QUERY_PARAM_GCM_MFP_NOTIFICATION, String.valueOf(true)).build().toString();
    }
}
