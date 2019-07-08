package com.myfitnesspal.feature.settings.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.event.DialogTimezoneEvent;
import com.myfitnesspal.feature.settings.ui.dialog.TimezoneDialogFragment;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Notifications;
import com.myfitnesspal.shared.event.DialogTimePickerEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.Timezone;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.impl.TimePickerDialogFragment;
import com.myfitnesspal.shared.util.CalendarUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.TimeZoneHelper;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.ViewUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class NotificationSettingsView extends MfpActivity {
    private static final int ID_QT_BEGIN = 5000;
    private static final int ID_QT_END = 5001;
    private static final int ID_QT_TIMEZONE = 5002;
    /* access modifiers changed from: private */
    public HashMap<String, Boolean> editedSettings;
    @BindView(2131362918)
    ListView notificationSettingsListView;
    private HashMap<String, Boolean> originalSettings;
    /* access modifiers changed from: private */
    public Date quietTimeBegin;
    /* access modifiers changed from: private */
    public Date quietTimeEnd;
    /* access modifiers changed from: private */
    public Timezone timezone;

    public String getAnalyticsScreenTag() {
        return Screens.NOTIFICATION_SETTINGS;
    }

    public static Intent newStartIntent(Context context) {
        return new Intent(context, NotificationSettingsView.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.notification_settings_view);
        setTitle(R.string.notifications);
        UserProfile profile = getSession().getUser().getProfile();
        this.originalSettings = profile.notificationSettings;
        this.editedSettings = (HashMap) this.originalSettings.clone();
        this.quietTimeBegin = profile.getLocalTimeForQuietTimeBegin();
        this.quietTimeEnd = profile.getLocalTimeForQuietTimeEnd();
        this.timezone = profile.getCurrentTimezone();
        initializeUI();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        saveChanges();
    }

    private void initializeUI() {
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.preference_settings_header, null);
        textView.setText(R.string.send_me_a_push_notification_when);
        this.notificationSettingsListView.addHeaderView(textView, null, false);
        this.notificationSettingsListView.setHeaderDividersEnabled(true);
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Notifications.NOTIFY_OF_NEW_MESSAGES, getString(R.string.notify_of_new_message));
        linkedHashMap.put(Notifications.NOTIFY_OF_FRIEND_REQUESTS, getString(R.string.notify_of_friend_requests));
        linkedHashMap.put(Notifications.NOTIFY_OF_WALL_POSTS, getString(R.string.notify_of_wall_posts));
        linkedHashMap.put(Notifications.NOTIFY_OF_NEW_COMMENTS, getString(R.string.notify_of_new_comments));
        linkedHashMap.put(Notifications.NOTIFY_OF_STATUS_LIKES, getString(R.string.notify_of_status_likes));
        linkedHashMap.put(Notifications.NOTIFY_OF_STATUS_COMMENT_LIKES, getString(R.string.notify_of_status_comment_likes));
        linkedHashMap.put(Notifications.NOTIFY_OF_STATUS_THREAD_COMMENTS, getString(R.string.notify_of_status_thread_comments));
        if (isNotificationTypeEnabled(Notifications.NOTIFY_OF_FRIEND_LOGGED_WORKOUT)) {
            linkedHashMap.put(Notifications.NOTIFY_OF_FRIEND_LOGGED_WORKOUT, getString(R.string.notify_of_friends_logs_workout));
        }
        if (isNotificationTypeEnabled(Notifications.NOTIFY_OF_FRIEND_JOINED)) {
            linkedHashMap.put(Notifications.NOTIFY_OF_FRIEND_JOINED, getString(R.string.notify_of_facebook_friends_joins));
        }
        if (isNotificationTypeEnabled(Notifications.NOTIFY_OF_FRIENDS_HIT_LOGIN_STREAK)) {
            linkedHashMap.put(Notifications.NOTIFY_OF_FRIENDS_HIT_LOGIN_STREAK, getString(R.string.notify_of_friends_hits_login_streak));
        }
        linkedHashMap.put(Notifications.ENABLE_QUIET_TIME, getString(R.string.quiet_time));
        String str = Notifications.QUIET_TIME_BEGIN_OFFSET;
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        sb.append(getString(R.string.quiet_time_between));
        linkedHashMap.put(str, sb.toString());
        String str2 = Notifications.QUIET_TIME_END_OFFSET;
        StringBuilder sb2 = new StringBuilder();
        sb2.append("    ");
        sb2.append(getString(R.string.quiet_time_and));
        linkedHashMap.put(str2, sb2.toString());
        String str3 = Basic.TIMEZONE_IDENTIFIER;
        StringBuilder sb3 = new StringBuilder();
        sb3.append("    ");
        sb3.append(getString(R.string.quiet_time_timezone));
        linkedHashMap.put(str3, sb3.toString());
        AnonymousClass1 r2 = new ArrayAdapter<String>(this, R.layout.preference_row, 16908310, new ArrayList(linkedHashMap.keySet())) {
            private static final int VIEW_TYPE_CHECKBOX = 0;
            private static final int VIEW_TYPE_SUMMARY = 1;

            public int getViewTypeCount() {
                return 2;
            }

            public int getCount() {
                int count = super.getCount();
                return !NotificationSettingsView.this.getEditedSettingValue(Notifications.ENABLE_QUIET_TIME) ? count - 3 : count;
            }

            public int getItemViewType(int i) {
                return i >= super.getCount() + -3 ? 1 : 0;
            }

            public long getItemId(int i) {
                String str = (String) getItem(i);
                if (str.equals(Notifications.QUIET_TIME_BEGIN_OFFSET)) {
                    return DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
                }
                if (str.equals(Notifications.QUIET_TIME_END_OFFSET)) {
                    return 5001;
                }
                if (str.equals(Basic.TIMEZONE_IDENTIFIER)) {
                    return 5002;
                }
                return super.getItemId(i);
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                String str = (String) getItem(i);
                ((TextView) ViewUtils.findById(view2, 16908310)).setText((CharSequence) linkedHashMap.get(str));
                ViewGroup viewGroup2 = (ViewGroup) ViewUtils.findById(view2, 16908312);
                int itemViewType = getItemViewType(i);
                if (viewGroup2.getChildCount() == 0) {
                    switch (itemViewType) {
                        case 0:
                            View.inflate(getContext(), R.layout.checked_text_view_settings, viewGroup2);
                            break;
                        case 1:
                            View.inflate(getContext(), R.layout.preference_summary, viewGroup2);
                            break;
                    }
                }
                TextView textView = (TextView) ViewUtils.findById(viewGroup2, R.id.text);
                switch ((int) getItemId(i)) {
                    case 5000:
                        textView.setText(DateTimeUtils.localeFormattedTime(getContext(), NotificationSettingsView.this.quietTimeBegin));
                        break;
                    case 5001:
                        textView.setText(DateTimeUtils.localeFormattedTime(getContext(), NotificationSettingsView.this.quietTimeEnd));
                        break;
                    case 5002:
                        textView.setText(NotificationSettingsView.this.timezone.getTimezone_name());
                        break;
                    default:
                        boolean access$000 = NotificationSettingsView.this.getEditedSettingValue(str);
                        CheckedTextView checkedTextView = (CheckedTextView) ViewUtils.findById(viewGroup2, R.id.chkDescription);
                        checkedTextView.setText("");
                        checkedTextView.setChecked(access$000);
                        break;
                }
                return view2;
            }
        };
        this.notificationSettingsListView.setAdapter(r2);
        this.notificationSettingsListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                boolean z = false;
                switch ((int) j) {
                    case 5000:
                    case 5001:
                        Calendar instance = Calendar.getInstance();
                        instance.setTime(j == DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS ? NotificationSettingsView.this.quietTimeBegin : NotificationSettingsView.this.quietTimeEnd);
                        TimePickerDialogFragment.newInstance().setId(j).setTime(instance).show(NotificationSettingsView.this.getSupportFragmentManager(), Fragments.TIME_PICKER);
                        break;
                    case 5002:
                        new TimezoneDialogFragment().show(NotificationSettingsView.this.getSupportFragmentManager(), Fragments.LOCATION_DIALOG);
                        break;
                    default:
                        String str = (String) adapterView.getItemAtPosition(i);
                        boolean z2 = !NotificationSettingsView.this.getEditedSettingValue(str);
                        NotificationSettingsView.this.editedSettings.put(str, Boolean.valueOf(z2));
                        AnalyticsService analyticsService = NotificationSettingsView.this.getAnalyticsService();
                        String[] strArr = new String[2];
                        strArr[0] = Attributes.CHANGED_STATUS;
                        strArr[1] = z2 ? Attributes.TURNED_ON : Attributes.TURNED_OFF;
                        analyticsService.reportEvent(str, MapUtil.createMap(strArr));
                        if (Notifications.ENABLE_QUIET_TIME.equals(str) && z2) {
                            z = true;
                            break;
                        }
                }
                ListViewUtils.notifyDataSetChanged(NotificationSettingsView.this.notificationSettingsListView);
                if (z) {
                    ListViewUtils.jumpToLastItem(NotificationSettingsView.this.notificationSettingsListView);
                }
            }
        });
    }

    @Subscribe
    public void onTimePicked(DialogTimePickerEvent dialogTimePickerEvent) {
        Date time = dialogTimePickerEvent.getCalendar().getTime();
        switch ((int) dialogTimePickerEvent.getId()) {
            case 5000:
                this.quietTimeBegin = time;
                break;
            case 5001:
                this.quietTimeEnd = time;
                break;
        }
        ListViewUtils.notifyDataSetChanged(this.notificationSettingsListView);
    }

    @Subscribe
    public void onTimezonePicked(DialogTimezoneEvent dialogTimezoneEvent) {
        this.timezone = dialogTimezoneEvent.getTimezone();
        ListViewUtils.notifyDataSetChanged(this.notificationSettingsListView);
    }

    private void saveChanges() {
        User user = getSession().getUser();
        boolean z = false;
        for (String str : this.editedSettings.keySet()) {
            boolean editedSettingValue = getEditedSettingValue(str);
            if (editedSettingValue != getOriginalSettingValue(str)) {
                user.setProperty(str, editedSettingValue ? "true" : "false");
                user.updatePropertyNamed(str);
                z = true;
            }
        }
        boolean hasChangedTimezone = hasChangedTimezone();
        if (hasChangedTimezone) {
            user.getProfile().setCurrentTimezone(this.timezone);
            user.updatePropertyNamed(Basic.TIMEZONE_IDENTIFIER);
            z = true;
        }
        if (hasChangedQuietTimeInterval() || hasChangedTimezone || hasChangedQuietTimeEnabledFlag()) {
            user.getProfile().setLocalTimeForQuietTimeBegin(this.quietTimeBegin);
            user.getProfile().setLocalTimeForQuietTimeEnd(this.quietTimeEnd);
            user.updatePropertyNamed(Notifications.ENABLE_QUIET_TIME);
            user.updatePropertyNamed(Notifications.QUIET_TIME_BEGIN_OFFSET);
            user.updatePropertyNamed(Notifications.QUIET_TIME_END_OFFSET);
            z = true;
        }
        if (z) {
            getMessageBus().post(new StartSyncEvent());
        }
    }

    private boolean hasChangedQuietTimeEnabledFlag() {
        return getEditedSettingValue(Notifications.ENABLE_QUIET_TIME) != getOriginalSettingValue(Notifications.ENABLE_QUIET_TIME);
    }

    private boolean hasChangedTimezone() {
        return !this.timezone.equals(getSession().getUser().getProfile().getCurrentTimezone());
    }

    private boolean hasChangedQuietTimeInterval() {
        UserProfile profile = getSession().getUser().getProfile();
        return (TimeZoneHelper.offsetFromMidnightUTCFromLocalTimeOfDay(CalendarUtils.getCalendarFromDate(profile.getLocalTimeForQuietTimeBegin()), profile.getCurrentTimezone().getTimezone_identifier()) == TimeZoneHelper.offsetFromMidnightUTCFromLocalTimeOfDay(CalendarUtils.getCalendarFromDate(this.quietTimeBegin), profile.getCurrentTimezone().getTimezone_identifier()) && TimeZoneHelper.offsetFromMidnightUTCFromLocalTimeOfDay(CalendarUtils.getCalendarFromDate(profile.getLocalTimeForQuietTimeEnd()), profile.getCurrentTimezone().getTimezone_identifier()) == TimeZoneHelper.offsetFromMidnightUTCFromLocalTimeOfDay(CalendarUtils.getCalendarFromDate(this.quietTimeEnd), profile.getCurrentTimezone().getTimezone_identifier())) ? false : true;
    }

    /* access modifiers changed from: private */
    public boolean getEditedSettingValue(String str) {
        return getSettingValue(this.editedSettings, str);
    }

    private boolean getOriginalSettingValue(String str) {
        return getSettingValue(this.originalSettings, str);
    }

    private boolean getSettingValue(HashMap<String, Boolean> hashMap, String str) {
        Boolean bool = (Boolean) hashMap.get(str);
        if (bool == null) {
            return getSession().getUser().getProfile().getDefaultValueForNotificationSetting(str);
        }
        return bool.booleanValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0039  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean isNotificationTypeEnabled(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -1326895656(0xffffffffb0e92dd8, float:-1.6966011E-9)
            if (r0 == r1) goto L_0x0028
            r1 = -688807409(0xffffffffd6f1a20f, float:-1.32839169E14)
            if (r0 == r1) goto L_0x001e
            r1 = 1656300655(0x62b9246f, float:1.7076365E21)
            if (r0 == r1) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            java.lang.String r0 = "notify_of_friend_logged_workout"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 0
            goto L_0x0033
        L_0x001e:
            java.lang.String r0 = "notify_of_friend_hit_login_streak"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 2
            goto L_0x0033
        L_0x0028:
            java.lang.String r0 = "notify_of_friend_joined"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 1
            goto L_0x0033
        L_0x0032:
            r3 = -1
        L_0x0033:
            switch(r3) {
                case 0: goto L_0x003f;
                case 1: goto L_0x003c;
                case 2: goto L_0x0039;
                default: goto L_0x0036;
            }
        L_0x0036:
            java.lang.String r3 = "control"
            goto L_0x0041
        L_0x0039:
            java.lang.String r3 = "friend-login-streak-notification-android-2015-10"
            goto L_0x0041
        L_0x003c:
            java.lang.String r3 = "friend-joined-notification-android-2015-10"
            goto L_0x0041
        L_0x003f:
            java.lang.String r3 = "friend-logged-exercise-notification-android-2015-10"
        L_0x0041:
            com.myfitnesspal.shared.service.config.ConfigService r0 = r2.getConfigService()
            boolean r3 = r0.isVariantEnabled(r3)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.ui.activity.NotificationSettingsView.isNotificationTypeEnabled(java.lang.String):boolean");
    }
}
