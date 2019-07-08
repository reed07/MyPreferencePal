package com.myfitnesspal.feature.friends.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.feature.friends.task.SendMessageTask;
import com.myfitnesspal.feature.friends.task.SendMessageTask.CompletedEvent;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.util.Toaster;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class ComposeMessageActivity extends MfpActivity {
    private static final String EVENT_ATTR_SOURCE = "source";
    private static final String EVENT_ATTR_TYPE = "type";
    private static final String EVENT_MESSAGE_COMPLETED = "message_completed";
    private static final String EVENT_MESSAGE_STARTED = "message_started";
    private static final String EVENT_VALUE_NEW = "new";
    private static final String EVENT_VALUE_REPLY = "reply";
    private static final String EXTRA_PREVIOUS_MESSAGE = "previous_message";
    private static final String EXTRA_RECIPIENT = "recipient";
    private static final String EXTRA_SOURCE = "source";
    private static final int MENU_SEND_MESSAGE = 9001;
    private static final String SEND_PROGRESS_DIALOG_TAG = "send_progress_dialog";
    @BindView(2131363055)
    EditText messageEditText;
    @Inject
    Lazy<MessageService> messageService;
    @BindView(2131363302)
    TextView previousMessageTextView;
    @BindView(2131363303)
    TextView previousMessageUserNameTextView;
    @BindView(2131363742)
    EditText subjectEditText;
    @BindView(2131364118)
    EditText userNameEditText;

    public interface Source {
        public static final String FRIEND_PROFILE = "friend_profile";
        public static final String MESSAGES = "messages";
        public static final String NOTIFICATIONS = "notif";
    }

    public static Intent newStartIntentWithRecipient(Context context, String str, String str2) {
        return newStartIntent(context, str2).putExtra("recipient", str);
    }

    public static Intent newStartIntentWithPreviousMessage(Context context, InboxMessage inboxMessage, String str) {
        return newStartIntent(context, str).putExtra(EXTRA_PREVIOUS_MESSAGE, inboxMessage);
    }

    public static Intent newStartIntent(Context context, String str) {
        return new Intent(context, ComposeMessageActivity.class).putExtra("source", str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.compose_message);
        if (bundle == null) {
            prefillInputFieldsFromIntent();
            reportMessageStarted();
        }
        setupPreviousMessageView();
        this.messageEditText.setOnTouchListener($$Lambda$ComposeMessageActivity$6UyP0RgU2V5leo2FP7Fe7YPdIzo.INSTANCE);
    }

    static /* synthetic */ boolean lambda$onCreate$0(View view, MotionEvent motionEvent) {
        if (view.getId() == R.id.message_input) {
            ViewParent parent = view.getParent();
            while (parent != null && !(parent instanceof ScrollView)) {
                parent = parent.getParent();
            }
            if (parent != null) {
                parent.requestDisallowInterceptTouchEvent(true);
                if ((motionEvent.getAction() & 255) == 1) {
                    view.getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
        }
        return false;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        MenuItemCompat.setShowAsAction(menu.add(0, 9001, 0, R.string.sendBtn).setIcon(R.drawable.ic_send_white_24dp), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 9001) {
            return super.onOptionsItemSelected(menuItem);
        }
        sendMessage();
        return true;
    }

    @Subscribe
    public void onSendMessageTaskCompletedEvent(CompletedEvent completedEvent) {
        dismissSendProgressDialogFragment();
        if (completedEvent.successful()) {
            Toaster.showShort((Activity) this, (int) R.string.message_sent_desc);
            finish();
            reportMessageCompleted();
            return;
        }
        showErrorAlert(String.format("%s: %s", new Object[]{getString(R.string.msg_send_failed), completedEvent.getInboxMessage().getUserInfo().getUsername()}));
    }

    private void prefillInputFieldsFromIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("recipient")) {
            this.userNameEditText.setText(intent.getStringExtra("recipient"));
            this.subjectEditText.requestFocus();
        } else if (intent.hasExtra(EXTRA_PREVIOUS_MESSAGE)) {
            InboxMessage previousMessage = getPreviousMessage();
            this.userNameEditText.setText(previousMessage.getUserInfo().getUsername());
            this.subjectEditText.setText(String.format("Re: %s", new Object[]{previousMessage.getSubject()}));
            this.messageEditText.requestFocus();
        }
    }

    private void reportMessageStarted() {
        getAnalyticsService().reportEvent(EVENT_MESSAGE_STARTED, MapUtil.createMap("type", getIntent().hasExtra(EXTRA_PREVIOUS_MESSAGE) ? EVENT_VALUE_REPLY : EVENT_VALUE_NEW, "source", getIntent().getStringExtra("source")));
    }

    private void reportMessageCompleted() {
        getAnalyticsService().reportEvent(EVENT_MESSAGE_COMPLETED, MapUtil.createMap("type", getIntent().hasExtra(EXTRA_PREVIOUS_MESSAGE) ? EVENT_VALUE_REPLY : EVENT_VALUE_NEW));
    }

    private void setupPreviousMessageView() {
        if (getIntent().hasExtra(EXTRA_PREVIOUS_MESSAGE)) {
            InboxMessage previousMessage = getPreviousMessage();
            String username = previousMessage.getUserInfo().getUsername();
            if (previousMessage.isSentMessage() || Strings.equals(username, getSession().getUser().getUsername())) {
                this.previousMessageUserNameTextView.setText(getResources().getString(R.string.previously_wrote));
            } else {
                this.previousMessageUserNameTextView.setText(getString(R.string.previously, new Object[]{username}));
            }
            this.previousMessageTextView.setText(previousMessage.getBody());
            return;
        }
        ViewUtils.setVisible(false, this.previousMessageTextView, this.previousMessageUserNameTextView);
    }

    private void sendMessage() {
        String trim = Strings.toString(this.userNameEditText.getText()).trim();
        if (Strings.isEmpty(trim)) {
            showErrorAlert((int) R.string.alert_blank_username);
            return;
        }
        String trim2 = Strings.toString(this.subjectEditText.getText()).trim();
        if (Strings.isEmpty(trim2)) {
            showErrorAlert((int) R.string.alert_blank_subject);
            return;
        }
        String trim3 = Strings.toString(this.messageEditText.getText()).trim();
        if (Strings.isEmpty(trim3)) {
            showErrorAlert((int) R.string.alert_blank_message);
            return;
        }
        getRunner().run(new SendMessageTask(this.messageService, createMessageForSending(trim, trim2, trim3)));
        showSendProgressDialogFragment();
    }

    private InboxMessage createMessageForSending(String str, String str2, String str3) {
        MiniUserInfo miniUserInfo = new MiniUserInfo();
        miniUserInfo.setUsername(str);
        InboxMessage inboxMessage = new InboxMessage();
        inboxMessage.setUserInfo(miniUserInfo);
        inboxMessage.setSubject(str2);
        inboxMessage.setBody(str3);
        InboxMessage previousMessage = getPreviousMessage();
        if (previousMessage != null) {
            inboxMessage.setInReplyToMessageMasterId(previousMessage.getMasterDatabaseId());
            inboxMessage.setInReplyToMessageUid(previousMessage.getUid());
        }
        return inboxMessage;
    }

    private void showSendProgressDialogFragment() {
        DialogFragment currentSendProgressDialogFragment = getCurrentSendProgressDialogFragment();
        if (currentSendProgressDialogFragment == null) {
            currentSendProgressDialogFragment = ProgressDialogFragment.newInstance(R.string.sendingMsg);
        }
        if (!currentSendProgressDialogFragment.isAdded()) {
            currentSendProgressDialogFragment.setCancelable(false);
            currentSendProgressDialogFragment.show(getSupportFragmentManager(), SEND_PROGRESS_DIALOG_TAG);
        }
    }

    private void dismissSendProgressDialogFragment() {
        DialogFragment currentSendProgressDialogFragment = getCurrentSendProgressDialogFragment();
        if (currentSendProgressDialogFragment != null) {
            currentSendProgressDialogFragment.dismiss();
        }
    }

    private DialogFragment getCurrentSendProgressDialogFragment() {
        return (DialogFragment) getSupportFragmentManager().findFragmentByTag(SEND_PROGRESS_DIALOG_TAG);
    }

    private void showErrorAlert(int i) {
        showErrorAlert(getString(i));
    }

    private void showErrorAlert(String str) {
        new MfpAlertDialogBuilder(this).setMessage((CharSequence) str).setPositiveButton((int) R.string.ok, (OnClickListener) null).show();
    }

    private InboxMessage getPreviousMessage() {
        return (InboxMessage) getIntent().getParcelableExtra(EXTRA_PREVIOUS_MESSAGE);
    }
}
