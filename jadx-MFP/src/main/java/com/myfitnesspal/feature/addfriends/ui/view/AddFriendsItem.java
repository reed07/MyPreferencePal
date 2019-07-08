package com.myfitnesspal.feature.addfriends.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.service.InviteSucceeded;
import com.myfitnesspal.shared.model.v1.Friend;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;

public abstract class AddFriendsItem<TFriend extends Friend> extends LinearLayout {
    protected TextView action;
    protected TFriend friend;
    private MfpImageView image;
    protected ProgressBar inviteProgress;
    private InviteClickedListener<TFriend> listener;
    protected View spinnerButtonContainer;
    private TextView txtPrimary;
    private TextView txtSubtitle;

    public interface InviteClickedListener<TFriend extends Friend> {
        boolean onInviteClicked(AddFriendsItem<TFriend> addFriendsItem, InviteSucceeded inviteSucceeded);
    }

    protected AddFriendsItem(Context context) {
        this(context, null);
    }

    protected AddFriendsItem(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    protected AddFriendsItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        inflate(context, R.layout.add_friends_item, this);
        this.image = (MfpImageView) ViewUtils.findById(this, R.id.image);
        this.txtPrimary = (TextView) ViewUtils.findById(this, R.id.txt_primary);
        this.txtSubtitle = (TextView) ViewUtils.findById(this, R.id.txt_subtitle);
        this.spinnerButtonContainer = ViewUtils.findById(this, R.id.spinnerButtonContainer);
        this.inviteProgress = (ProgressBar) ViewUtils.findById(this, R.id.inviteProgress);
        this.action = (TextView) ViewUtils.findById(this, R.id.btn_action);
        this.spinnerButtonContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddFriendsItem.this.onActionClicked();
            }
        });
    }

    public TFriend getFriend() {
        return this.friend;
    }

    public void setFriend(TFriend tfriend) {
        this.friend = tfriend;
        updateUI();
    }

    public InviteClickedListener<TFriend> getListener() {
        return this.listener;
    }

    public void setListener(InviteClickedListener<TFriend> inviteClickedListener) {
        this.listener = inviteClickedListener;
    }

    /* access modifiers changed from: protected */
    public void onActionClicked() {
        if (this.listener != null) {
            ViewUtils.setVisible(false, this.action);
            ViewUtils.setVisible(true, this.inviteProgress);
            this.listener.onInviteClicked(this, new InviteSucceeded() {
                public final void execute(Object obj) {
                    AddFriendsItem.lambda$onActionClicked$1(AddFriendsItem.this, (Boolean) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$onActionClicked$1(AddFriendsItem addFriendsItem, Boolean bool) throws RuntimeException {
        addFriendsItem.friend.setHasBeenInvited(bool.booleanValue());
        addFriendsItem.updateUI();
    }

    /* access modifiers changed from: protected */
    public void setImage(String str) {
        this.image.setUrl(str);
    }

    /* access modifiers changed from: protected */
    public void setPrimaryText(String str) {
        setTextOrHide(this.txtPrimary, str);
    }

    private void setTextOrHide(TextView textView, String str) {
        if (Strings.isEmpty(str)) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        textView.setText(str);
    }

    /* access modifiers changed from: protected */
    public void showFriendsInCommon(int i) {
        if (i > 0) {
            this.txtSubtitle.setText(getResources().getString(R.string.addfriends_in_common_format, new Object[]{Integer.valueOf(i)}));
            return;
        }
        this.txtSubtitle.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void updateUI() {
        ViewUtils.setVisible(false, this.inviteProgress);
        ViewUtils.setVisible(true, this.action);
        TextView textView = this.action;
        int i = this.friend.hasBeenInvited() ? R.string.addfriends_action_sent : this.friend.isOnMfp() ? R.string.addfriends_action_add : R.string.addfriends_action_invite;
        textView.setText(i);
        this.spinnerButtonContainer.setEnabled(!this.friend.hasBeenInvited());
        this.action.setEnabled(true ^ this.friend.hasBeenInvited());
    }
}
