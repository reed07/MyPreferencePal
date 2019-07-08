package com.myfitnesspal.feature.settings.ui.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Basic;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Email;
import com.myfitnesspal.shared.constants.Constants.UserProperties.Newsletter;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.UserProfile;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserPropertiesService;
import com.myfitnesspal.shared.uacf.UacfEmailVerificationManager;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.validation.Validator;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.MapUtil.Builder;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import javax.inject.Inject;
import javax.inject.Named;

public class EmailSettingsListFragment extends MfpFragment {
    private static final String SEND_HEADER = "send_header";
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    /* access modifiers changed from: private */
    public HashMap<String, Boolean> editedSettings;
    @BindView(2131362435)
    TextView emailText;
    @Inject
    @Named("emailValidator")
    Validator emailValidator;
    @Inject
    Lazy<UacfEmailVerificationManager> emailVerificationManager;
    @BindView(2131362451)
    View emailVerifyParent;
    @BindView(2131362918)
    ListView listView;
    @Inject
    Lazy<LoginModel> loginModel;
    /* access modifiers changed from: private */
    public String modifiedEmailAddress;
    private OnItemClickListener onListItemClick = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            String str = (String) EmailSettingsListFragment.this.listView.getAdapter().getItem(i);
            boolean access$000 = EmailSettingsListFragment.this.getEditedSettingValue(str);
            EmailSettingsListFragment.this.editedSettings.put(str, Boolean.valueOf(!access$000));
            ListViewUtils.notifyDataSetChanged(EmailSettingsListFragment.this.listView);
            for (String equals : UserProfile.getNewsletterSettingsPropertyKeys()) {
                if (Strings.equals(str, equals) && access$000) {
                    EmailSettingsListFragment.this.showEmailUnsubscribeAlertDialog();
                }
            }
        }
    };
    private HashMap<String, Boolean> originalSettings;
    @BindView(2131363458)
    TextView resendButton;
    @BindView(2131364088)
    View updateButton;
    @Inject
    Lazy<UserPropertiesService> userPropertiesService;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.generic_list_fragment, viewGroup, false);
        this.listView = (ListView) inflate.findViewById(R.id.list);
        if (this.listView.getHeaderViewsCount() == 0) {
            this.listView.addHeaderView(View.inflate(getActivity(), R.layout.email_settings_header, null));
            this.listView.setHeaderDividersEnabled(false);
        }
        ButterKnife.bind((Object) this, inflate);
        setupList();
        if (Strings.notEmpty(ExtrasUtils.getStringExtra(getActivity().getIntent(), "edit"))) {
            showEmailAddressDialog();
        }
        return inflate;
    }

    public void onPause() {
        super.onPause();
        saveChanges();
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
        if (z) {
            updateUserV1PropertiesInBackground();
            refreshUacfUserInBackground();
        }
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
            return getSession().getUser().getProfile().getDefaultValueForEmailSetting(str);
        }
        return bool.booleanValue();
    }

    /* access modifiers changed from: private */
    public void showEmailAddressDialog() {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.email_address_input, null);
        final EditText editText = (EditText) ViewUtils.findById(inflate, 16908291);
        editText.setText(this.modifiedEmailAddress);
        new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.email_text).setView(inflate).setPositiveButton(17039370, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = editText.getText().toString();
                if (EmailSettingsListFragment.this.emailValidator.validate(obj)) {
                    EmailSettingsListFragment.this.setModifiedEmailAddress(obj);
                    ((ActionTrackingService) EmailSettingsListFragment.this.actionTrackingService.get()).registerAndReportEvent(Events.EMAIL_CHANGED);
                    return;
                }
                EmailSettingsListFragment emailSettingsListFragment = EmailSettingsListFragment.this;
                emailSettingsListFragment.postEvent(new AlertEvent(emailSettingsListFragment.getString(R.string.invalid_email_address)));
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).show();
    }

    /* access modifiers changed from: private */
    public void setModifiedEmailAddress(String str) {
        final User user = getSession().getUser();
        this.modifiedEmailAddress = str;
        this.emailText.setText(this.modifiedEmailAddress);
        if (!Strings.equals(this.modifiedEmailAddress, user.getEmail())) {
            ((UserPropertiesService) this.userPropertiesService.get()).updateProperties(new Builder().put("email", this.modifiedEmailAddress).build(), new Function0() {
                public void execute() {
                    user.setEmail(EmailSettingsListFragment.this.modifiedEmailAddress);
                    user.updatePropertyNamed("email");
                    user.updatePropertyNamed(Basic.VALID_EMAIL);
                    EmailSettingsListFragment.this.updateUserV1PropertiesInBackground();
                    EmailSettingsListFragment.this.refreshUacfUserInBackground();
                }
            }, new ApiErrorCallback() {
                public void execute(ApiException apiException) {
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void updateUserV1PropertiesInBackground() {
        new Thread(new Runnable() {
            public void run() {
                ((DbConnectionManager) EmailSettingsListFragment.this.dbConnectionManager.get()).userPropertiesDbAdapter().saveUserProperties(EmailSettingsListFragment.this.getSession().getUser().getUserV1(), (LoginModel) EmailSettingsListFragment.this.loginModel.get());
                EmailSettingsListFragment.this.postEvent(new StartSyncEvent());
            }
        }).start();
    }

    /* access modifiers changed from: private */
    public void refreshUacfUserInBackground() {
        new Thread(new Runnable() {
            public void run() {
                EmailSettingsListFragment.this.postEvent(new StartSyncEvent(SyncType.UacfUser));
            }
        }).start();
    }

    private void setupList() {
        setModifiedEmailAddress(getSession().getUser().getEmail());
        this.emailText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (((UacfEmailVerificationManager) EmailSettingsListFragment.this.emailVerificationManager.get()).isUserVerified()) {
                    EmailSettingsListFragment.this.showEmailAddressDialog();
                }
            }
        });
        ViewUtils.setVisible(!((UacfEmailVerificationManager) this.emailVerificationManager.get()).isUserVerified(), this.emailVerifyParent);
        this.resendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((UserPropertiesService) EmailSettingsListFragment.this.userPropertiesService.get()).resendEmailVerificationAsync(new Function0() {
                    public void execute() throws RuntimeException {
                        EmailSettingsListFragment.this.postEvent(new AlertEvent(EmailSettingsListFragment.this.getString(R.string.email_verification_email_has_been_sent)).asToast());
                        EmailSettingsListFragment.this.resendButton.setText(R.string.sentBtn);
                        EmailSettingsListFragment.this.resendButton.setBackgroundResource(R.drawable.grey_send_btn);
                        ((ActionTrackingService) EmailSettingsListFragment.this.actionTrackingService.get()).registerAndReportEvent(Events.RESEND_EMAIL_CONFIRM);
                    }
                }, new ApiErrorCallback() {
                    public void execute(ApiException apiException) throws RuntimeException {
                        EmailSettingsListFragment.this.postEvent(new AlertEvent(EmailSettingsListFragment.this.getString(R.string.error_occured)).asToast());
                    }
                });
            }
        });
        this.updateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EmailSettingsListFragment.this.showEmailAddressDialog();
            }
        });
        this.originalSettings = getSession().getUser().getProfile().emailSettings;
        this.editedSettings = (HashMap) this.originalSettings.clone();
        final LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(Email.FIND_BY_EMAIL_ENABLED, getString(R.string.friends_can_find_me_by_email));
        linkedHashMap.put(Newsletter.NEWSLETTER_FOR_FEATURE_ANNOUNCEMENTS, getString(R.string.new_feature_announcements));
        linkedHashMap.put(Newsletter.NEWSLETTER_FOR_HEALTHY_LIVING_TIP, getString(R.string.healthy_tips_newsletter));
        linkedHashMap.put("recipe", getString(R.string.recipes_newsletter));
        linkedHashMap.put(Newsletter.NEWSLETTER_FOR_WORKOUTS, getString(R.string.workouts_newsletter));
        linkedHashMap.put(Newsletter.NEWSLETTER_FOR_UA_GEAR, getString(R.string.gear_recommendations));
        if (ConfigUtils.isWeeklyDigestSettingOn((ConfigService) this.configService.get())) {
            linkedHashMap.put(Newsletter.NEWSLETTER_FOR_WEEKLY_DIGEST, getString(R.string.weekly_digest));
        }
        linkedHashMap.put(SEND_HEADER, getString(R.string.send_me_an_email_when));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_MESSAGES, getString(R.string.send_email_for_messages));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_FRIEND_REQUESTS, getString(R.string.send_email_for_friend_requests));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_GROUP_INVITES, getString(R.string.send_email_for_group_invites));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_ACCEPTED_FRIEND_REQUESTS, getString(R.string.send_email_for_accepted_friend_requests));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_ACCEPTED_GROUP_INVITES, getString(R.string.send_email_for_accepted_group_invites));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_PROFILE_COMMENTS, getString(R.string.send_email_for_profile_comments));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_BLOG_COMMENTS, getString(R.string.send_email_for_blog_comments));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_STATUS_COMMENTS, getString(R.string.send_email_for_status_comments));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_STATUS_LIKES, getString(R.string.send_email_for_status_likes));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_STATUS_COMMENT_LIKES, getString(R.string.send_email_for_status_comment_likes));
        linkedHashMap.put(Email.SEND_EMAIL_FOR_STATUS_THREAD_COMMENTS, getString(R.string.send_email_for_status_thread_comments));
        if (ChallengesUtil.isChallengesEmailPrefAvailable((ConfigService) this.configService.get())) {
            linkedHashMap.put(Email.SEND_EMAIL_FOR_CHALLENGE_AVAILABLE, getString(R.string.send_email_for_challenge_available));
            linkedHashMap.put(Email.SEND_EMAIL_FOR_EARN_CHALLENGE_ACHIEVEMENT, getString(R.string.send_email_for_earn_challenge_achievement));
            linkedHashMap.put(Email.SEND_EMAIL_FOR_FRIEND_JOINED_CHALLENEGE, getString(R.string.send_email_for_friend_joined_challenge));
            linkedHashMap.put(Email.SEND_EMAIL_FOR_FRIEND_CHALLENGE_INVITE, getString(R.string.send_email_for_friend_challenge_invite));
            linkedHashMap.put(Email.SEND_EMAIL_FOR_FRIEND_COMPLETES_CHALLENGE, getString(R.string.send_email_for_friend_completes_challenge));
            linkedHashMap.put(Email.SEND_EMAIL_FOR_USER_COMPLETES_CHALLENGE, getString(R.string.send_email_for_user_completes_challenge));
        }
        AnonymousClass10 r1 = new ArrayAdapter<String>(getActivity(), R.layout.preference_row, 16908310, new ArrayList(linkedHashMap.keySet())) {
            public boolean areAllItemsEnabled() {
                return false;
            }

            public boolean isEnabled(int i) {
                return !Strings.equals((String) getItem(i), EmailSettingsListFragment.SEND_HEADER);
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                View view2 = super.getView(i, view, viewGroup);
                String str = (String) getItem(i);
                ViewGroup viewGroup2 = (ViewGroup) ViewUtils.findById(view2, 16908312);
                View findById = ViewUtils.findById(view2, R.id.pref_row);
                View findById2 = ViewUtils.findById(view2, R.id.header_row);
                if (viewGroup2.getChildCount() == 0) {
                    View.inflate(getContext(), R.layout.checked_text_view_settings, viewGroup2);
                }
                CheckedTextView checkedTextView = (CheckedTextView) ViewUtils.findById(viewGroup2, R.id.chkDescription);
                checkedTextView.setText("");
                if (!Strings.equals(str, EmailSettingsListFragment.SEND_HEADER)) {
                    ((TextView) ViewUtils.findById(view2, 16908310)).setText((CharSequence) linkedHashMap.get(str));
                    checkedTextView.setChecked(EmailSettingsListFragment.this.getEditedSettingValue(str));
                    checkedTextView.setVisibility(0);
                    ViewUtils.setVisible(true, findById);
                    ViewUtils.setVisible(false, findById2);
                } else {
                    ((TextView) ViewUtils.findById(view2, R.id.header_row)).setText((CharSequence) linkedHashMap.get(str));
                    checkedTextView.setVisibility(4);
                    ViewUtils.setVisible(false, findById);
                    ViewUtils.setVisible(true, findById2);
                }
                return view2;
            }
        };
        this.listView.setAdapter(r1);
        this.listView.setOnItemClickListener(this.onListItemClick);
    }

    /* access modifiers changed from: private */
    public void showEmailUnsubscribeAlertDialog() {
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.alert)).setMessage(getString(R.string.expect_delay_message))).setPositiveText(R.string.ok, null);
        alertDialogFragment.setCancelable(false);
        showDialogFragment(alertDialogFragment, AlertDialogFragment.class.getName());
    }
}
