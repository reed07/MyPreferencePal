package com.myfitnesspal.feature.friends.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v15.FriendRequestObject;
import com.myfitnesspal.shared.ui.graphics.TextDrawable;
import com.myfitnesspal.shared.ui.graphics.TextDrawable.Builder;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import java.util.List;

public class FriendRequestsAdapter extends Adapter<ViewHolder> {
    private final Context context;
    private List<FriendRequestObject> friendRequests;
    /* access modifiers changed from: private */
    public OnFriendRequestClickListener requestClickListener;

    public interface OnFriendRequestClickListener {
        void onFriendClick(FriendRequestObject friendRequestObject);

        void onRequestAccepted(FriendRequestObject friendRequestObject);

        void onRequestDeclined(FriendRequestObject friendRequestObject);
    }

    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        @BindView(2131362667)
        View acceptButton;
        @BindView(2131362670)
        TextView friendMessage;
        @BindView(2131362669)
        View friendRequestLayout;
        @BindView(2131362673)
        TextView friendRequestStatus;
        @BindView(2131362671)
        View progressLayout;
        @BindView(2131362672)
        View rejectButton;
        @BindView(2131362674)
        MfpImageView thumbnail;
        @BindView(2131362668)
        TextView userFullNameView;
        @BindView(2131362675)
        TextView userNameTextView;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.friendRequestLayout = Utils.findRequiredView(view, R.id.friend_request_info_layout, "field 'friendRequestLayout'");
            viewHolder.userNameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.friend_request_username, "field 'userNameTextView'", TextView.class);
            viewHolder.userFullNameView = (TextView) Utils.findRequiredViewAsType(view, R.id.friend_request_full_name, "field 'userFullNameView'", TextView.class);
            viewHolder.friendMessage = (TextView) Utils.findRequiredViewAsType(view, R.id.friend_request_message, "field 'friendMessage'", TextView.class);
            viewHolder.acceptButton = Utils.findRequiredView(view, R.id.friend_request_accept_button, "field 'acceptButton'");
            viewHolder.rejectButton = Utils.findRequiredView(view, R.id.friend_request_reject_button, "field 'rejectButton'");
            viewHolder.thumbnail = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.friend_request_thumbnail_image, "field 'thumbnail'", MfpImageView.class);
            viewHolder.friendRequestStatus = (TextView) Utils.findRequiredViewAsType(view, R.id.friend_request_status, "field 'friendRequestStatus'", TextView.class);
            viewHolder.progressLayout = Utils.findRequiredView(view, R.id.friend_request_progress_layout, "field 'progressLayout'");
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder != null) {
                this.target = null;
                viewHolder.friendRequestLayout = null;
                viewHolder.userNameTextView = null;
                viewHolder.userFullNameView = null;
                viewHolder.friendMessage = null;
                viewHolder.acceptButton = null;
                viewHolder.rejectButton = null;
                viewHolder.thumbnail = null;
                viewHolder.friendRequestStatus = null;
                viewHolder.progressLayout = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public FriendRequestsAdapter(Context context2, List<FriendRequestObject> list) {
        this.context = context2;
        this.friendRequests = list;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.request_item, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        FriendRequestObject friendRequestObject = (FriendRequestObject) this.friendRequests.get(i);
        int statusCode = friendRequestObject.getStatusCode();
        if (statusCode != 5) {
            switch (statusCode) {
                case 1:
                    friendRequestIgnored(friendRequestObject, viewHolder);
                    return;
                case 2:
                    friendRequestAccepted(friendRequestObject, viewHolder);
                    return;
                default:
                    friendRequestView(friendRequestObject, viewHolder);
                    return;
            }
        } else {
            friendRequestProcessing(viewHolder);
        }
    }

    public int getItemCount() {
        return this.friendRequests.size();
    }

    public void updateRequestStatus(int i, long j) {
        for (int i2 = 0; i2 < this.friendRequests.size(); i2++) {
            FriendRequestObject friendRequestObject = (FriendRequestObject) this.friendRequests.get(i2);
            if (friendRequestObject.getRequestMasterId() == j) {
                friendRequestObject.setStatusCode(i);
                notifyItemChanged(i2);
                return;
            }
        }
    }

    private void friendRequestAccepted(FriendRequestObject friendRequestObject, ViewHolder viewHolder) {
        String otherPartyUsername = friendRequestObject.getOtherPartyUsername();
        TextView textView = viewHolder.friendRequestStatus;
        Resources resources = this.context.getResources();
        StringBuilder sb = new StringBuilder();
        sb.append(" <b> ");
        sb.append(otherPartyUsername);
        sb.append("</b> ");
        textView.setText(resources.getString(R.string.friend_added, new Object[]{Html.fromHtml(sb.toString())}));
        viewHolder.friendRequestLayout.setVisibility(8);
        viewHolder.friendRequestStatus.setVisibility(0);
        viewHolder.progressLayout.setVisibility(8);
    }

    private void friendRequestIgnored(FriendRequestObject friendRequestObject, ViewHolder viewHolder) {
        String otherPartyUsername = friendRequestObject.getOtherPartyUsername();
        viewHolder.friendRequestStatus.setText(Html.fromHtml(this.context.getResources().getString(R.string.friend_ignored, new Object[]{otherPartyUsername})));
        viewHolder.friendRequestLayout.setVisibility(8);
        viewHolder.friendRequestStatus.setVisibility(0);
        viewHolder.progressLayout.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void friendRequestProcessing(ViewHolder viewHolder) {
        viewHolder.friendRequestLayout.setVisibility(8);
        viewHolder.friendRequestStatus.setVisibility(8);
        viewHolder.progressLayout.setVisibility(0);
    }

    private void friendRequestView(final FriendRequestObject friendRequestObject, final ViewHolder viewHolder) {
        viewHolder.userNameTextView.setText(Strings.toString(friendRequestObject.getOtherPartyUsername()));
        viewHolder.userFullNameView.setText(Strings.toString(friendRequestObject.getOtherPartyFullName()));
        ViewUtils.setVisible(Strings.notEmpty(Strings.toString(friendRequestObject.getOtherPartyFullName())), viewHolder.userFullNameView);
        viewHolder.friendMessage.setText(friendRequestObject.getBody());
        ViewUtils.setVisible(Strings.notEmpty(friendRequestObject.getBody()), viewHolder.friendMessage);
        TextDrawable build = new Builder().text(friendRequestObject.getOtherPartyUsername().substring(0, 1).toUpperCase()).build();
        if (friendRequestObject.getOtherPartyImageUrl() == null || friendRequestObject.getOtherPartyImageUrl().isEmpty()) {
            viewHolder.thumbnail.setImageDrawable(build);
        } else {
            viewHolder.thumbnail.setPlaceholderDrawable(build);
            viewHolder.thumbnail.setUrl(friendRequestObject.getOtherPartyImageUrl());
        }
        viewHolder.rejectButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                friendRequestObject.setStatusCode(5);
                FriendRequestsAdapter.this.friendRequestProcessing(viewHolder);
                if (FriendRequestsAdapter.this.requestClickListener != null) {
                    FriendRequestsAdapter.this.requestClickListener.onRequestDeclined(friendRequestObject);
                }
            }
        });
        viewHolder.acceptButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                friendRequestObject.setStatusCode(5);
                FriendRequestsAdapter.this.friendRequestProcessing(viewHolder);
                if (FriendRequestsAdapter.this.requestClickListener != null) {
                    FriendRequestsAdapter.this.requestClickListener.onRequestAccepted(friendRequestObject);
                }
            }
        });
        viewHolder.friendRequestLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (FriendRequestsAdapter.this.requestClickListener != null) {
                    FriendRequestsAdapter.this.requestClickListener.onFriendClick(friendRequestObject);
                }
            }
        });
        viewHolder.friendRequestLayout.setVisibility(0);
        viewHolder.friendRequestStatus.setVisibility(8);
        viewHolder.progressLayout.setVisibility(8);
    }

    public void setRequestClickListener(OnFriendRequestClickListener onFriendRequestClickListener) {
        this.requestClickListener = onFriendRequestClickListener;
    }
}
