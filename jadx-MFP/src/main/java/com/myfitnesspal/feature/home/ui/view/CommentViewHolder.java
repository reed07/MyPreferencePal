package com.myfitnesspal.feature.home.ui.view;

import android.os.Build.VERSION;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnContextClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityComment;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant.ProfileVisibility;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedLikeDetails;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import java.util.Date;

public class CommentViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    /* access modifiers changed from: private */
    public final NewsFeedItemActionListener actionListener;
    @BindView(2131362170)
    View commentContainer;
    private final OnLongClickListener commentLongClickListener = new OnLongClickListener() {
        public boolean onLongClick(View view) {
            Tuple2 tuple2 = (Tuple2) view.getTag();
            CommentViewHolder.this.actionListener.onCommentLongClick((String) tuple2.getItem1(), ((Integer) tuple2.getItem2()).intValue());
            return true;
        }
    };
    @BindView(2131362279)
    TextView date;
    @BindView(2131364115)
    MfpImageView image;
    @BindView(2131362912)
    CheckBox likeCheckBox;
    private final OnCheckedChangeListener likeCheckedChangeListener = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            CommentViewHolder.this.actionListener.onCommentLikeClick((String) compoundButton.getTag(), z, new Function1<Integer>() {
                public void execute(Integer num) throws RuntimeException {
                    CommentViewHolder.this.setNumberOfLikesAndLikeCheckBox(num.intValue());
                }
            });
        }
    };
    private final OnClickListener navigateToProfileClickListener = new OnClickListener() {
        public void onClick(View view) {
            CommentViewHolder.this.navigateToProfile((MfpNewsFeedActivityParticipant) view.getTag());
        }
    };
    private final NavigationHelper navigationHelper;
    @BindView(2131363732)
    TextView statusBody;
    private final OnClickListener totalLikesClickListener = new OnClickListener() {
        public void onClick(View view) {
            CommentViewHolder.this.actionListener.onCommentLikeCountClick((String) view.getTag());
        }
    };
    @BindView(2131362911)
    TextView totalNumberOfLikes;
    @BindView(2131362350)
    TextView txtDotSeparator;
    @BindView(2131362351)
    TextView txtDotSeparator2;
    @BindView(2131364121)
    TextView username;

    public CommentViewHolder(ViewGroup viewGroup, NavigationHelper navigationHelper2, NewsFeedItemActionListener newsFeedItemActionListener) {
        super(R.layout.comments_item, viewGroup);
        this.navigationHelper = navigationHelper2;
        this.actionListener = newsFeedItemActionListener;
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        MfpNewsFeedActivityComment mfpNewsFeedActivityComment = (MfpNewsFeedActivityComment) newsFeedItem;
        MfpNewsFeedActivityParticipant sourceUser = mfpNewsFeedActivityComment.getSourceUser();
        setImage(sourceUser);
        setStatusBody(mfpNewsFeedActivityComment.getText(), i);
        setDate(mfpNewsFeedActivityComment.getCreatedAt());
        setUserName(sourceUser);
        boolean isLikesEnabled = mfpNewsFeedActivityComment.isLikesEnabled();
        MfpNewsFeedLikeDetails likes = mfpNewsFeedActivityComment.getLikes();
        setLikeCheckBoxVisibility(isLikesEnabled);
        setNumberOfLikesAndLikeCheckBox((!isLikesEnabled || likes == null) ? 0 : likes.getCount());
        setLikeCheckedState(likes);
        setListeners(sourceUser, mfpNewsFeedActivityComment.getId(), i);
    }

    private void setLikeCheckedState(MfpNewsFeedLikeDetails mfpNewsFeedLikeDetails) {
        this.likeCheckBox.setOnCheckedChangeListener(null);
        this.likeCheckBox.setChecked(mfpNewsFeedLikeDetails.isUserLiked());
    }

    private void setStatusBody(String str, int i) {
        this.statusBody.setMovementMethod(LinkMovementMethod.getInstance());
        this.statusBody.setTag(Integer.valueOf(i));
        this.statusBody.setText(str);
    }

    private void setDate(Date date2) {
        this.date.setText(DateTimeUtils.formatHumanReadableTime(this.context, date2));
    }

    /* access modifiers changed from: private */
    public void setNumberOfLikesAndLikeCheckBox(int i) {
        if (i == 0) {
            ViewUtils.setVisible(false, this.totalNumberOfLikes);
            ViewUtils.setVisible(false, this.txtDotSeparator2);
            this.likeCheckBox.setChecked(false);
            return;
        }
        ViewUtils.setVisible(true, this.totalNumberOfLikes);
        ViewUtils.setVisible(true, this.txtDotSeparator2);
        this.totalNumberOfLikes.setText(this.context.getString(i == 1 ? R.string.like_count_format_single : R.string.like_count_format_plural, new Object[]{Integer.valueOf(i)}));
        this.likeCheckBox.setChecked(true);
    }

    private void setImage(final MfpNewsFeedActivityParticipant mfpNewsFeedActivityParticipant) {
        if (mfpNewsFeedActivityParticipant != null) {
            String profilePhotoUrl = mfpNewsFeedActivityParticipant.getProfilePhotoUrl();
            if (Strings.notEmpty(profilePhotoUrl)) {
                this.image.setUrl(profilePhotoUrl);
            } else {
                this.image.setBackgroundResource(R.drawable.ic_profile);
            }
            if (Strings.notEmpty(mfpNewsFeedActivityParticipant.getUsername())) {
                this.image.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        CommentViewHolder.this.navigateToProfile(mfpNewsFeedActivityParticipant);
                    }
                });
                return;
            }
            return;
        }
        this.image.setBackgroundResource(R.drawable.ic_profile);
    }

    private void setLikeCheckBoxVisibility(boolean z) {
        ViewUtils.setVisible(z, this.likeCheckBox);
        ViewUtils.setVisible(z, this.txtDotSeparator);
    }

    private void setUserName(MfpNewsFeedActivityParticipant mfpNewsFeedActivityParticipant) {
        if (mfpNewsFeedActivityParticipant != null) {
            String displayName = mfpNewsFeedActivityParticipant.getDisplayName();
            String username2 = mfpNewsFeedActivityParticipant.getUsername();
            if (!Strings.notEmpty(displayName)) {
                displayName = Strings.notEmpty(username2) ? username2 : "";
            }
            this.username.setText(displayName);
            ViewUtils.setVisible(true, this.username);
            return;
        }
        ViewUtils.setVisible(false, this.username);
    }

    private boolean isParticipantProfileVisible(MfpNewsFeedActivityParticipant mfpNewsFeedActivityParticipant) {
        return Strings.equalsIgnoreCase(mfpNewsFeedActivityParticipant.getProfileVisibility(), ProfileVisibility.VISIBLE);
    }

    /* access modifiers changed from: private */
    public void navigateToProfile(MfpNewsFeedActivityParticipant mfpNewsFeedActivityParticipant) {
        this.navigationHelper.withExtra(Extras.IS_PROFILE_VISIBLE, isParticipantProfileVisible(mfpNewsFeedActivityParticipant)).withIntent(ProfileView.newStartIntent(this.context, mfpNewsFeedActivityParticipant.getUsername(), mfpNewsFeedActivityParticipant.getUserId())).startActivity(36);
    }

    private void setListeners(MfpNewsFeedActivityParticipant mfpNewsFeedActivityParticipant, String str, int i) {
        this.username.setTag(mfpNewsFeedActivityParticipant);
        this.username.setOnClickListener(this.navigateToProfileClickListener);
        this.totalNumberOfLikes.setTag(str);
        this.totalNumberOfLikes.setOnClickListener(this.totalLikesClickListener);
        this.likeCheckBox.setTag(str);
        this.likeCheckBox.setOnCheckedChangeListener(this.likeCheckedChangeListener);
        this.commentContainer.setTag(Tuple.create(str, Integer.valueOf(i)));
        this.commentContainer.setOnLongClickListener(this.commentLongClickListener);
        if (VERSION.SDK_INT >= 23) {
            this.commentContainer.setOnContextClickListener(new OnContextClickListener() {
                public final boolean onContextClick(View view) {
                    return CommentViewHolder.lambda$setListeners$0(CommentViewHolder.this, view);
                }
            });
        }
    }

    public static /* synthetic */ boolean lambda$setListeners$0(CommentViewHolder commentViewHolder, View view) {
        Tuple2 tuple2 = (Tuple2) view.getTag();
        commentViewHolder.actionListener.onCommentLongClick((String) tuple2.getItem1(), ((Integer) tuple2.getItem2()).intValue());
        return true;
    }
}
