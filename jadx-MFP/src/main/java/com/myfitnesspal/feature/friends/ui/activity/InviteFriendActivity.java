package com.myfitnesspal.feature.friends.ui.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.model.ContactInfo;
import com.myfitnesspal.feature.friends.util.ContactAccessor;
import com.myfitnesspal.shared.api.ApiErrorCallback;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import javax.inject.Inject;

public class InviteFriendActivity extends MfpActivity {
    private static final int INVITE_FRIEND_PROGRESS_DIALOG = 5895;
    private static final int MENU_SEND = 11;
    private static HashMap<String, String> hmContacts = new HashMap<>();
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @BindView(2131361994)
    View btnGetContacts;
    @BindView(2131362430)
    EditText editTxtMessage;
    @BindView(2131362411)
    EditText editTxtRecipients;
    @BindView(2131362426)
    EditText editTxtUserName;
    @Inject
    Lazy<FriendService> friendService;
    private String friendToInvite;
    /* access modifiers changed from: private */
    public ContactAccessor mContactAccessor;
    @BindView(2131362690)
    TextView txtFullNamePrompt;

    public static Intent newStartIntent(Context context) {
        return newStartIntent(context, null, null);
    }

    public static Intent newStartIntent(Context context, String str) {
        return newStartIntent(context, str, null);
    }

    public static Intent newStartIntent(Context context, String str, String str2) {
        return new Intent(context, InviteFriendActivity.class).putExtra(Extras.FRIEND_TO_INVITE, str).putExtra("message", str2);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.invite_friends_request);
        try {
            this.mContactAccessor = ContactAccessor.getInstance();
        } catch (Exception e) {
            Ln.e(e);
        }
        initializeUi();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        MenuItemCompat.setShowAsAction(menu.add(0, 11, 0, R.string.sendBtn).setIcon(R.drawable.ic_send_white_24dp), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 11) {
            return super.onOptionsItemSelected(menuItem);
        }
        doneInviting();
        return true;
    }

    private void initializeUi() {
        this.btnGetContacts.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                InviteFriendActivity.this.doLaunchContactPicker(view);
            }
        });
        this.editTxtUserName.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                ViewUtils.setVisible(Strings.isEmpty((Object) charSequence), InviteFriendActivity.this.txtFullNamePrompt);
            }
        });
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.friendToInvite = extras.getString(Extras.FRIEND_TO_INVITE);
            if (Strings.notEmpty(this.friendToInvite)) {
                this.editTxtRecipients.setText(this.friendToInvite);
            }
            String string = extras.getString("message");
            if (Strings.notEmpty(string)) {
                this.editTxtMessage.setText(string);
            }
        }
    }

    public void doLaunchContactPicker(View view) {
        try {
            startActivityForResult(this.mContactAccessor.getPickContactIntent(), 114);
        } catch (Exception e) {
            Ln.w(e, "doLaunchContactPicker()", new Object[0]);
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(getResources().getString(R.string.cannot_load_contacts));
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 114 && i2 == -1) {
            loadContactInfo(intent.getData());
        }
    }

    private void loadContactInfo(Uri uri) {
        try {
            new AsyncTask<Uri, Void, ContactInfo>() {
                /* access modifiers changed from: protected */
                public ContactInfo doInBackground(Uri... uriArr) {
                    return InviteFriendActivity.this.mContactAccessor.loadContact(InviteFriendActivity.this.getContentResolver(), uriArr[0]);
                }

                /* access modifiers changed from: protected */
                public void onPostExecute(ContactInfo contactInfo) {
                    InviteFriendActivity.this.bindResults(contactInfo);
                }
            }.execute(new Uri[]{uri});
        } catch (Exception e) {
            Ln.w("loadContactInfo()", new Object[0]);
            Ln.e(e);
        }
    }

    /* access modifiers changed from: protected */
    public void bindResults(ContactInfo contactInfo) {
        if (contactInfo != null) {
            String lowerCase = Strings.trimmed((Object) contactInfo.getEmail()).toLowerCase();
            String trimmed = Strings.trimmed((Object) contactInfo.getDisplayName());
            if (Strings.notEmpty(lowerCase) && Strings.notEmpty(trimmed)) {
                if (!hmContacts.containsKey(trimmed) && !hmContacts.containsValue(lowerCase)) {
                    hmContacts.put(trimmed, lowerCase);
                }
                HashSet hashSet = new HashSet();
                hashSet.addAll(hmContacts.keySet());
                String trimmed2 = Strings.trimmed((Object) this.editTxtRecipients.getText());
                if (Strings.notEmpty(trimmed2)) {
                    for (String trimmed3 : trimmed2.split(",")) {
                        hashSet.add(Strings.trimmed((Object) trimmed3));
                    }
                }
                this.editTxtRecipients.setText(Strings.join(", ", (Collection<T>) hashSet));
            }
        }
    }

    private void doneInviting() {
        if (!ConnectivityUtil.isOnline()) {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getResources().getString(R.string.request_failed), getResources().getString(R.string.offline_msg), getResources().getString(R.string.dismiss));
            return;
        }
        String trimmed = Strings.trimmed((Object) this.editTxtRecipients.getText());
        if (Strings.isEmpty(trimmed)) {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialog(getString(R.string.emptyFriendRequest));
            return;
        }
        String trimmed2 = Strings.trimmed((Object) this.editTxtMessage.getText());
        String trimmed3 = Strings.trimmed((Object) this.editTxtUserName.getText());
        String[] split = trimmed.split(",");
        ArrayList arrayList = new ArrayList(split.length);
        ArrayList arrayList2 = new ArrayList();
        for (String trimmed4 : split) {
            String trimmed5 = Strings.trimmed((Object) trimmed4);
            if (!Strings.isEmpty(trimmed5)) {
                if (hmContacts.containsKey(trimmed5)) {
                    arrayList.add(hmContacts.get(trimmed5));
                    arrayList2.add(Events.INVITE_FROM_CONTACT_INFO);
                } else {
                    arrayList.add(trimmed5);
                }
                arrayList2.add(Events.LOCALYTICS_REGISTER_INVITE_SENT);
            }
        }
        String join = Strings.join(", ", (Collection<T>) arrayList);
        showDialog(INVITE_FRIEND_PROGRESS_DIALOG);
        Ln.w(join, new Object[0]);
        ((FriendService) this.friendService.get()).sendInvitations(arrayList, trimmed3, trimmed2, new Function0(arrayList2) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void execute() {
                InviteFriendActivity.lambda$doneInviting$0(InviteFriendActivity.this, this.f$1);
            }
        }, new ApiErrorCallback() {
            public final void execute(Object obj) {
                InviteFriendActivity.lambda$doneInviting$1(InviteFriendActivity.this, (ApiException) obj);
            }
        });
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.String>, for r4v0, types: [java.util.List, java.util.List<java.lang.String>] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ void lambda$doneInviting$0(com.myfitnesspal.feature.friends.ui.activity.InviteFriendActivity r3, java.util.List<java.lang.String> r4) throws java.lang.RuntimeException {
        /*
            java.util.Iterator r4 = r4.iterator()
        L_0x0004:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0018
            java.lang.Object r0 = r4.next()
            java.lang.String r0 = (java.lang.String) r0
            com.myfitnesspal.shared.service.analytics.AnalyticsService r1 = r3.getAnalyticsService()
            r1.reportEvent(r0)
            goto L_0x0004
        L_0x0018:
            r4 = 5895(0x1707, float:8.26E-42)
            r3.dismissDialog(r4)
            android.widget.EditText r4 = r3.editTxtRecipients
            java.lang.String r0 = ""
            r4.setText(r0)
            android.widget.EditText r4 = r3.editTxtUserName
            java.lang.String r0 = ""
            r4.setText(r0)
            java.util.HashMap<java.lang.String, java.lang.String> r4 = hmContacts
            r4.clear()
            dagger.Lazy<com.myfitnesspal.shared.service.analytics.ActionTrackingService> r4 = r3.actionTrackingService
            java.lang.Object r4 = r4.get()
            com.myfitnesspal.shared.service.analytics.ActionTrackingService r4 = (com.myfitnesspal.shared.service.analytics.ActionTrackingService) r4
            java.lang.String r0 = "Friend Invite Sent"
            r4.registerEvent(r0)
            dagger.Lazy<com.myfitnesspal.shared.service.analytics.ActionTrackingService> r4 = r3.actionTrackingService
            java.lang.Object r4 = r4.get()
            com.myfitnesspal.shared.service.analytics.ActionTrackingService r4 = (com.myfitnesspal.shared.service.analytics.ActionTrackingService) r4
            java.lang.String r0 = "Friend Invite Sent"
            r4.reportEventToAnalytics(r0)
            r4 = -1
            android.content.Intent r0 = new android.content.Intent
            r0.<init>()
            java.lang.String r1 = "friendToInviteUsername"
            java.lang.String r2 = r3.friendToInvite
            android.content.Intent r0 = r0.putExtra(r1, r2)
            r3.setResult(r4, r0)
            r3.finish()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.friends.ui.activity.InviteFriendActivity.lambda$doneInviting$0(com.myfitnesspal.feature.friends.ui.activity.InviteFriendActivity, java.util.List):void");
    }

    public static /* synthetic */ void lambda$doneInviting$1(InviteFriendActivity inviteFriendActivity, ApiException apiException) throws RuntimeException {
        inviteFriendActivity.dismissDialog(INVITE_FRIEND_PROGRESS_DIALOG);
        ((LegacyAlertMixin) inviteFriendActivity.mixin(LegacyAlertMixin.class)).showAlertDialog(Strings.notEmpty(apiException.getBody()) ? apiException.getBody() : inviteFriendActivity.getString(R.string.unable_send_invitation));
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i != INVITE_FRIEND_PROGRESS_DIALOG) {
            return super.onCreateDialog(i);
        }
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle(getString(R.string.please_wait));
        progressDialog.setMessage(getString(R.string.inviting_friends));
        return progressDialog;
    }

    public void onBackPressed() {
        setResult(0, new Intent().putExtra(Extras.FRIEND_TO_INVITE, this.friendToInvite));
        super.onBackPressed();
    }
}
