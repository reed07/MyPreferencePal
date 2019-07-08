package com.myfitnesspal.feature.friends.ui.adapter;

import android.graphics.Typeface;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MessagesAdapter extends Adapter<RecyclerViewHolder<InboxMessage>> {
    private static final String FONT_MEDIUM = "sans-serif-medium";
    private static Typeface mediumTypeface;
    private final List<InboxMessage> inboxMessages = new ArrayList();
    private final OnItemClickListener onItemClickListener;

    static class InboxMessageViewHolder extends RecyclerViewHolder<InboxMessage> {
        @BindView(2131361926)
        MfpImageView avatarImageView;
        @BindView(2131362279)
        TextView dateTextView;
        @BindView(2131363056)
        TextView messageTextView;
        private final OnClickListener onClickListener = new OnClickListener() {
            public void onClick(View view) {
                Tuple2 tuple2 = (Tuple2) view.getTag();
                InboxMessageViewHolder.this.onItemClickListener.onItemClick((InboxMessage) tuple2.getItem1(), ((Integer) tuple2.getItem2()).intValue());
            }
        };
        /* access modifiers changed from: private */
        public final OnItemClickListener onItemClickListener;
        @BindView(2131363452)
        View repliedIndicator;
        @BindView(2131363743)
        TextView subjectTextView;
        @BindView(2131364119)
        TextView userNameTextView;

        InboxMessageViewHolder(ViewGroup viewGroup, OnItemClickListener onItemClickListener2) {
            super(R.layout.message_item, viewGroup);
            this.onItemClickListener = onItemClickListener2;
        }

        public void setData(InboxMessage inboxMessage, int i) {
            MiniUserInfo userInfo = inboxMessage.getUserInfo();
            this.avatarImageView.setUrl(userInfo.getImageUrl());
            this.userNameTextView.setText(userInfo.getUsername());
            this.dateTextView.setText(formatMessageDateTime(DateTimeUtils.convertUtcToLocal(inboxMessage.getSentAtDate())));
            this.subjectTextView.setText(inboxMessage.getSubject());
            this.messageTextView.setText(inboxMessage.getBody());
            ViewUtils.setVisible(inboxMessage.isReplied(), this.repliedIndicator);
            setupViewForUnread(inboxMessage.hasBeenRead());
            this.itemView.setTag(Tuple.create(inboxMessage, Integer.valueOf(i)));
            this.itemView.setOnClickListener(this.onClickListener);
        }

        private void setupViewForUnread(boolean z) {
            Typeface access$000 = z ? Typeface.DEFAULT : MessagesAdapter.getMediumTypeface();
            this.userNameTextView.setTypeface(access$000);
            this.dateTextView.setTypeface(access$000);
            this.subjectTextView.setTypeface(access$000);
        }

        private String formatMessageDateTime(Date date) {
            Calendar instance = Calendar.getInstance();
            DateTimeUtils.resetTime(instance);
            if (!instance.getTime().after(date)) {
                return DateTimeUtils.localeFormattedTime(this.context, date);
            }
            instance.add(5, -1);
            if (!instance.getTime().after(date)) {
                return this.context.getString(R.string.yesterday);
            }
            instance.add(5, -5);
            if (!instance.getTime().after(date)) {
                return DateTimeUtils.format("EEEE", date);
            }
            return DateTimeUtils.getShortLocaleFormattedDate(date);
        }
    }

    public class InboxMessageViewHolder_ViewBinding implements Unbinder {
        private InboxMessageViewHolder target;

        @UiThread
        public InboxMessageViewHolder_ViewBinding(InboxMessageViewHolder inboxMessageViewHolder, View view) {
            this.target = inboxMessageViewHolder;
            inboxMessageViewHolder.avatarImageView = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.avatar_image, "field 'avatarImageView'", MfpImageView.class);
            inboxMessageViewHolder.dateTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.date_text, "field 'dateTextView'", TextView.class);
            inboxMessageViewHolder.userNameTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.user_name_text, "field 'userNameTextView'", TextView.class);
            inboxMessageViewHolder.subjectTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.subject_text, "field 'subjectTextView'", TextView.class);
            inboxMessageViewHolder.messageTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.message_text, "field 'messageTextView'", TextView.class);
            inboxMessageViewHolder.repliedIndicator = Utils.findRequiredView(view, R.id.replied_indicator, "field 'repliedIndicator'");
        }

        @CallSuper
        public void unbind() {
            InboxMessageViewHolder inboxMessageViewHolder = this.target;
            if (inboxMessageViewHolder != null) {
                this.target = null;
                inboxMessageViewHolder.avatarImageView = null;
                inboxMessageViewHolder.dateTextView = null;
                inboxMessageViewHolder.userNameTextView = null;
                inboxMessageViewHolder.subjectTextView = null;
                inboxMessageViewHolder.messageTextView = null;
                inboxMessageViewHolder.repliedIndicator = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public interface OnItemClickListener {
        void onItemClick(InboxMessage inboxMessage, int i);
    }

    public MessagesAdapter(OnItemClickListener onItemClickListener2) {
        this.onItemClickListener = onItemClickListener2;
    }

    public RecyclerViewHolder<InboxMessage> onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new InboxMessageViewHolder(viewGroup, this.onItemClickListener);
    }

    public void onBindViewHolder(RecyclerViewHolder<InboxMessage> recyclerViewHolder, int i) {
        recyclerViewHolder.setData(getItem(i), i);
    }

    public int getItemCount() {
        return this.inboxMessages.size();
    }

    public void addAll(List<InboxMessage> list) {
        this.inboxMessages.clear();
        this.inboxMessages.addAll(list);
        notifyDataSetChanged();
    }

    private InboxMessage getItem(int i) {
        return (InboxMessage) this.inboxMessages.get(i);
    }

    /* access modifiers changed from: private */
    public static Typeface getMediumTypeface() {
        if (mediumTypeface == null) {
            mediumTypeface = Typeface.create(FONT_MEDIUM, 0);
        }
        return mediumTypeface;
    }
}
