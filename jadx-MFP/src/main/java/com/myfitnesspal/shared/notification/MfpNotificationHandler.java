package com.myfitnesspal.shared.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.TaskStackBuilder;
import com.google.android.exoplayer2.C;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.NotificationAps;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MFPNotificationMethod;
import com.myfitnesspal.shared.model.v2.MfpNotificationActionId;
import com.myfitnesspal.shared.model.v2.MfpNotificationActionItem;
import com.myfitnesspal.shared.model.v2.MfpNotificationIconId;
import com.myfitnesspal.shared.model.v2.MfpNotificationPayload;
import com.myfitnesspal.shared.model.v2.MfpNotificationType;
import com.myfitnesspal.shared.notification.JobServiceFactory.Job;
import com.myfitnesspal.shared.receiver.MfpNotificationActionReceiver;
import com.myfitnesspal.shared.receiver.NotificationBroadcastReceiver;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.UriUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MfpNotificationHandler {
    private static final String ANALYTIC_VALUE_UTM_MEDIUM = "push";
    private static final String ANALYTIC_VALUE_UTM_SOURCE = "mfp";
    private final Lazy<AnalyticsService> analyticsService;
    private final Lazy<ApiJsonMapper> apiJsonMapper;
    private final Lazy<BackgroundJobHelper> backgroundJobHelper;
    private final NotificationManager notificationManager;

    @Inject
    public MfpNotificationHandler(Lazy<AnalyticsService> lazy, Lazy<BackgroundJobHelper> lazy2, Lazy<ApiJsonMapper> lazy3, Context context) {
        this.analyticsService = lazy;
        this.backgroundJobHelper = lazy2;
        this.apiJsonMapper = lazy3;
        this.notificationManager = (NotificationManager) context.getSystemService("notification");
    }

    public void removeNotificationWithId(int i) {
        NotificationManager notificationManager2 = this.notificationManager;
        if (notificationManager2 != null) {
            notificationManager2.cancel(i);
        }
    }

    public void cancelAllNotifications() {
        NotificationManager notificationManager2 = this.notificationManager;
        if (notificationManager2 != null) {
            notificationManager2.cancelAll();
        }
    }

    public void handleNoticeNotificationForItemType(Context context, MfpNotificationType mfpNotificationType, MfpNotificationPayload mfpNotificationPayload) {
        String str;
        String str2;
        if (mfpNotificationType != null && this.notificationManager != null) {
            PendingIntent createNotificationIntent = createNotificationIntent(context, mfpNotificationPayload);
            if (createNotificationIntent != null) {
                if ((mfpNotificationType == MfpNotificationType.MESSAGE || mfpNotificationType == MfpNotificationType.INVITATION) && !((BackgroundJobHelper) this.backgroundJobHelper.get()).isJobRunning(Job.NOTIFICATION_IN_APP.getJobId())) {
                    ((BackgroundJobHelper) this.backgroundJobHelper.get()).updateInAppNotifications();
                }
                int notificationId = mfpNotificationType.getNotificationId();
                NotificationAps aps = mfpNotificationPayload.getAps();
                if (aps != null) {
                    str = aps.getTitle();
                    str2 = aps.getAlert();
                } else {
                    str = "";
                    str2 = null;
                }
                String stringOrDefaultIfEmpty = Strings.toStringOrDefaultIfEmpty(str, context.getString(mfpNotificationType.getDefaultTitleResId()));
                String stringOrDefaultIfEmpty2 = Strings.toStringOrDefaultIfEmpty(str2, " ");
                Builder style = new Builder(context, mfpNotificationType.toNotificationChannel().getChannelId()).setSmallIcon(R.drawable.ic_reminder).setContentTitle(stringOrDefaultIfEmpty).setContentText(stringOrDefaultIfEmpty2).setContentIntent(createNotificationIntent).setAutoCancel(true).setStyle(new BigTextStyle().bigText(stringOrDefaultIfEmpty2));
                bindActionsToNotificationBuilder(context, mfpNotificationPayload, style, notificationId);
                this.notificationManager.notify(notificationId, style.build());
                trackEvent(mfpNotificationPayload);
            }
        }
    }

    @Nullable
    private PendingIntent createNotificationIntent(Context context, MfpNotificationPayload mfpNotificationPayload) {
        MfpNotificationType objectType = mfpNotificationPayload.getObjectType();
        if (objectType == null) {
            return null;
        }
        Intent intent = new Intent(context, NotificationBroadcastReceiver.class);
        intent.putExtra(NotificationBroadcastReceiver.EXTRA_NOTIFICATION_TYPE, objectType.name());
        intent.putExtra("notification_id", objectType.getNotificationId());
        intent.putExtra(NotificationBroadcastReceiver.EXTRA_RESOURCE_ID, NumberUtils.tryParseLong(mfpNotificationPayload.getObjectId()));
        intent.putExtra(NotificationBroadcastReceiver.EXTRA_URL, mfpNotificationPayload.getUrl());
        return PendingIntent.getBroadcast(context, 0, intent, C.ENCODING_PCM_MU_LAW);
    }

    private void bindActionsToNotificationBuilder(Context context, MfpNotificationPayload mfpNotificationPayload, Builder builder, int i) {
        List actions = mfpNotificationPayload.getActions();
        String strings = Strings.toString(mfpNotificationPayload.getUtmCampaign());
        if (CollectionUtils.notEmpty((Collection<?>) actions)) {
            for (int i2 = 0; i2 < actions.size(); i2++) {
                Action createNotificationAction = createNotificationAction(context, (MfpNotificationActionItem) actions.get(i2), i, strings, i2);
                if (createNotificationAction != null) {
                    builder.addAction(createNotificationAction);
                }
            }
        }
    }

    private Action createNotificationAction(Context context, MfpNotificationActionItem mfpNotificationActionItem, int i, String str, int i2) {
        PendingIntent pendingIntent;
        if (mfpNotificationActionItem == null) {
            return null;
        }
        String title = mfpNotificationActionItem.getTitle();
        MfpNotificationIconId iconId = mfpNotificationActionItem.getIconId();
        String deepLink = mfpNotificationActionItem.getDeepLink();
        MFPNotificationMethod method = mfpNotificationActionItem.getMethod();
        int currentTimeMillis = i2 + ((int) System.currentTimeMillis());
        if (Strings.notEmpty(title) && iconId != null && Strings.notEmpty(deepLink)) {
            if (method == MFPNotificationMethod.LOCAL) {
                pendingIntent = getDeepLinkPendingIntent(context, deepLink, currentTimeMillis);
            } else if (method != MFPNotificationMethod.POST) {
                pendingIntent = null;
            } else if (isUserActionRequired(mfpNotificationActionItem.getId())) {
                pendingIntent = getDeepLinkPendingIntent(context, deepLink, currentTimeMillis);
            } else {
                Intent intent = new Intent(context, MfpNotificationActionReceiver.class);
                intent.setAction(MfpNotificationActionReceiver.NOTIFICATION_POST_ACTION);
                intent.putExtra("url", mfpNotificationActionItem.getUrl());
                intent.putExtra("body", ((ApiJsonMapper) this.apiJsonMapper.get()).reverseMap((Object) mfpNotificationActionItem.getBody()));
                intent.putExtra("notification_id", i);
                intent.putExtra("utm_campaign", str);
                pendingIntent = PendingIntent.getBroadcast(context, currentTimeMillis, intent, 134217728);
            }
            if (pendingIntent != null) {
                return new Action.Builder(iconId.getIconResId(), title, pendingIntent).build();
            }
        }
        return null;
    }

    private boolean isUserActionRequired(MfpNotificationActionId mfpNotificationActionId) {
        return mfpNotificationActionId == MfpNotificationActionId.COMMENT;
    }

    private PendingIntent getDeepLinkPendingIntent(Context context, String str, int i) {
        return TaskStackBuilder.create(context).addParentStack(HomeActivity.class).addNextIntent(SharedIntents.newUriIntent(str)).getPendingIntent(i, 134217728);
    }

    private void trackEvent(MfpNotificationPayload mfpNotificationPayload) {
        MapUtil.Builder builder = new MapUtil.Builder();
        if (Strings.notEmpty(mfpNotificationPayload.getUrl())) {
            Uri parse = Uri.parse(mfpNotificationPayload.getUrl());
            for (String str : UriUtils.getQueryParameterNames(parse)) {
                builder.put(str, parse.getQueryParameter(str));
            }
        }
        builder.put("utm_campaign", Strings.toString(mfpNotificationPayload.getUtmCampaign())).put("utm_source", "mfp").put("utm_medium", ANALYTIC_VALUE_UTM_MEDIUM);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.PUSH_NOTIFICATION_RECEIVED, builder.build());
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.NOTIFICATION_RECEIVED, builder.build());
    }
}
