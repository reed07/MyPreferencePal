package com.myfitnesspal.feature.friends.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.feature.friends.task.DeleteMessageTask;
import com.myfitnesspal.feature.friends.task.FetchMessageWithIdTask;
import com.myfitnesspal.feature.friends.task.FetchMessageWithIdTask.CompletedEvent;
import com.myfitnesspal.feature.friends.task.MarkMessageReadTask;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.myfitnesspal.shared.util.Toaster;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DateFormat;
import javax.inject.Inject;

public class DetailedMessageActivity extends MfpActivity {
    private static final int DELETE_MENU_ITEM = 9001;
    private static final String DELETE_MESSAGE_CONFIRM_TAG = "delete_message_confirm";
    private static final String DELETE_PROGRESS_DIALOG_TAG = "delete_progress_dialog";
    private static final String EVENT_MESSAGE_DELETED = "message_deleted";
    private static final String EXTRA_INBOX_MESSAGE = "inbox_message";
    private static final String EXTRA_MESSAGE_ID = "message_id";
    private static final String EXTRA_SOURCE = "source";
    private static final String FAIL_DELETE_MESSAGE_TAG = "fail_delete_message";
    @BindView(2131361926)
    MfpImageView avatarImageView;
    @BindView(2131362279)
    TextView dateTextView;
    private final DialogPositiveListener dialogPositiveListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            DetailedMessageActivity.this.getRunner().run(new DeleteMessageTask(DetailedMessageActivity.this.messageService, DetailedMessageActivity.this.inboxMessage.getMasterDatabaseId(), DetailedMessageActivity.this.inboxMessage.getUid()));
            DetailedMessageActivity.this.showDeleteProgressDialogFragment();
        }
    };
    /* access modifiers changed from: private */
    public InboxMessage inboxMessage;
    @BindView(2131362950)
    View loadingView;
    @Inject
    Lazy<MessageService> messageService;
    @BindView(2131363056)
    TextView messageTextView;
    @BindView(2131363453)
    View replyButtonView;
    @BindView(2131363743)
    TextView subjectTextView;
    @BindView(2131364119)
    TextView userNameTextView;

    public static Intent newStartIntent(Context context, InboxMessage inboxMessage2, String str) {
        return new Intent(context, DetailedMessageActivity.class).putExtra("inbox_message", inboxMessage2).putExtra("source", str);
    }

    public static Intent newStartIntent(Context context, long j, String str) {
        return new Intent(context, DetailedMessageActivity.class).putExtra("message_id", j).putExtra("source", str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.message_detail);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
        MaterialUtils.setFixedFooterScrollingBehavior(this, true);
        Intent intent = getIntent();
        if (intent.hasExtra("inbox_message")) {
            showMessageInfo((InboxMessage) ExtrasUtils.getParcelable(getIntent(), "inbox_message", InboxMessage.class.getClassLoader()));
        } else if (intent.hasExtra("message_id")) {
            getRunner().run(new FetchMessageWithIdTask(this.messageService, ExtrasUtils.getLong(intent, "message_id")));
            showLoadingState();
        } else {
            showErrorAndFinish();
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        if (this.inboxMessage != null) {
            MenuItemCompat.setShowAsAction(menu.add(0, 9001, 0, R.string.delete).setIcon(R.drawable.ic_delete_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 9001) {
            return super.onOptionsItemSelected(menuItem);
        }
        deleteMessage();
        return true;
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(DELETE_MESSAGE_CONFIRM_TAG, str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.dialogPositiveListener);
        return true;
    }

    @Subscribe
    public void onFetchMessageWithIdTaskCompletedEvent(CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            InboxMessage inboxMessage2 = (InboxMessage) completedEvent.getResult();
            if (inboxMessage2 == null) {
                showErrorAndFinish();
            } else {
                showMessageInfo(inboxMessage2);
            }
        } else {
            showErrorAndFinish();
        }
    }

    @Subscribe
    public void onDeleteMessageTaskCompletedEvent(DeleteMessageTask.CompletedEvent completedEvent) {
        dismissDeleteProgressDialogFragment();
        if (completedEvent.successful()) {
            Toaster.showShort((Activity) this, (int) R.string.message_deleted);
            reportMessageDeleted();
            finish();
            return;
        }
        showDialogFragment(((AlertDialogFragment) new AlertDialogFragment().setMessage((int) R.string.delete_msg_failed)).setPositiveText(R.string.ok, null), FAIL_DELETE_MESSAGE_TAG);
    }

    private void showLoadingState() {
        ViewUtils.setVisible(this.loadingView);
        toggleMessageInfoViewVisibility(false);
    }

    private void showMessageInfo(final InboxMessage inboxMessage2) {
        this.inboxMessage = inboxMessage2;
        invalidateOptionsMenu();
        ViewUtils.setGone(this.loadingView);
        toggleMessageInfoViewVisibility(true);
        MiniUserInfo userInfo = inboxMessage2.getUserInfo();
        this.avatarImageView.setUrl(userInfo.getImageUrl());
        this.userNameTextView.setText(userInfo.getUsername());
        this.subjectTextView.setText(inboxMessage2.getSubject());
        this.messageTextView.setText(inboxMessage2.getBody());
        this.dateTextView.setText(DateFormat.getDateInstance(2).format(inboxMessage2.getSentAtDate()));
        this.replyButtonView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                DetailedMessageActivity.this.getNavigationHelper().withIntent(ComposeMessageActivity.newStartIntentWithPreviousMessage(DetailedMessageActivity.this.getActivity(), inboxMessage2, ExtrasUtils.getString(DetailedMessageActivity.this.getIntent().getExtras(), "source", ""))).startActivity(112);
            }
        });
        if (inboxMessage2.getReadAtDate() == null) {
            getRunner().run(new MarkMessageReadTask(this.messageService, inboxMessage2.getMasterDatabaseId(), inboxMessage2.getUid()));
        }
    }

    private void reportMessageDeleted() {
        getAnalyticsService().reportEvent(EVENT_MESSAGE_DELETED, MapUtil.createMap("source", getIntent().getStringExtra("source")));
    }

    private void deleteMessage() {
        showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.deleteMessage1)).setMessage((int) R.string.discard_msg_confirm)).setPositiveText(R.string.deleteBtn, this.dialogPositiveListener), DELETE_MESSAGE_CONFIRM_TAG);
    }

    private void toggleMessageInfoViewVisibility(boolean z) {
        ViewUtils.setVisible(z, this.avatarImageView, this.userNameTextView, this.subjectTextView, this.messageTextView, this.dateTextView, this.replyButtonView);
    }

    /* access modifiers changed from: private */
    public void showDeleteProgressDialogFragment() {
        DialogFragment currentDeleteProgressDialogFragment = getCurrentDeleteProgressDialogFragment();
        if (currentDeleteProgressDialogFragment == null) {
            currentDeleteProgressDialogFragment = ProgressDialogFragment.newInstance(R.string.deleting_msg);
        }
        if (!currentDeleteProgressDialogFragment.isAdded()) {
            currentDeleteProgressDialogFragment.setCancelable(false);
            currentDeleteProgressDialogFragment.show(getSupportFragmentManager(), DELETE_PROGRESS_DIALOG_TAG);
        }
    }

    private void dismissDeleteProgressDialogFragment() {
        DialogFragment currentDeleteProgressDialogFragment = getCurrentDeleteProgressDialogFragment();
        if (currentDeleteProgressDialogFragment != null) {
            currentDeleteProgressDialogFragment.dismiss();
        }
    }

    private DialogFragment getCurrentDeleteProgressDialogFragment() {
        return (DialogFragment) getSupportFragmentManager().findFragmentByTag(DELETE_PROGRESS_DIALOG_TAG);
    }

    private void showErrorAndFinish() {
        Toaster.showShort((Activity) this, (int) R.string.error_could_not_load_message);
        finish();
    }
}
