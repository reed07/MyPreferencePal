package com.myfitnesspal.feature.home.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.ui.AppGalleryActivity;
import com.myfitnesspal.feature.diary.ui.activity.FriendDiary;
import com.myfitnesspal.feature.friends.ui.activity.CommentsActivity;
import com.myfitnesspal.feature.friends.ui.activity.LikesActivity;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.NewsFeedDisplayActivityName;
import com.myfitnesspal.feature.home.model.NewsFeedItem;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.util.InternalURLSpan;
import com.myfitnesspal.feature.home.util.NewsFeedCardUtils;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.profile.ui.activity.ProfileView;
import com.myfitnesspal.shared.api.request.MfpNewsFeedLikesPostData.Actions;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityComment;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityConversation;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry.DataTypes;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityParticipant;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedChallengeLink;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedChallengeStatusEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedDiaryCompleteEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedImageStatusUpdateEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedLikeDetails;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedMealPhotoUpdateEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedPartnerActivityEntryData;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple3;
import com.uacf.core.util.Tuple4;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class NewsFeedStatusCardViewHolder extends RecyclerViewHolder<NewsFeedItem> {
    private static RequestOptions REQUEST_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
    @BindView(2131364114)
    MfpImageView avatarImg;
    @BindView(2131362088)
    ImageButton cardActionBtn;
    @BindView(2131362168)
    View commentBtn;
    @BindView(2131362169)
    TextView commentTextView;
    /* access modifiers changed from: private */
    public final Context context;
    @BindView(2131362272)
    TextView dateTxt;
    /* access modifiers changed from: private */
    public final NewsFeedDisplayActivityName feedDisplayActivityName;
    private final Lazy<ImageService> imageService;
    @BindView(2131362909)
    View likeBtn;
    @BindView(2131362910)
    TextView likeTextView;
    @BindView(2131362913)
    View llLikesComments;
    private final OnClickListener navToCommentsClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (NewsFeedStatusCardViewHolder.this.feedDisplayActivityName != NewsFeedDisplayActivityName.Comments) {
                MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) view.getTag();
                NewsFeedStatusCardViewHolder.this.reportCommentsButtonPressed(mfpNewsFeedActivityEntry);
                NewsFeedStatusCardViewHolder.this.navigationHelper.withIntent(CommentsActivity.newStartIntent(NewsFeedStatusCardViewHolder.this.context, mfpNewsFeedActivityEntry)).startActivity(35);
            }
        }
    };
    private final OnClickListener navigateToLikesClickListener = new OnClickListener() {
        public void onClick(View view) {
            NewsFeedStatusCardViewHolder.this.navigationHelper.withIntent(LikesActivity.newStartIntent(NewsFeedStatusCardViewHolder.this.context, (String) view.getTag())).startActivity();
        }
    };
    private final OnClickListener navigateToPartnerGalleryClickListener = new OnClickListener() {
        public void onClick(View view) {
            String str = (String) view.getTag();
            if (!Strings.isEmpty(str)) {
                NewsFeedStatusCardViewHolder.this.navigationHelper.withIntent(AppGalleryActivity.newStartIntent(NewsFeedStatusCardViewHolder.this.context, str)).startActivity();
            }
        }
    };
    private final OnClickListener navigateToProfileClickListener = new OnClickListener() {
        public void onClick(View view) {
            Tuple3 tuple3 = (Tuple3) view.getTag();
            NewsFeedStatusCardViewHolder.this.navigateToProfileViewScreen((String) tuple3.getItem1(), (String) tuple3.getItem2(), ((Boolean) tuple3.getItem3()).booleanValue());
        }
    };
    /* access modifiers changed from: private */
    public final NavigationHelper navigationHelper;
    /* access modifiers changed from: private */
    public final Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    /* access modifiers changed from: private */
    public final NewsFeedItemActionListener newsFeedItemActionListener;
    @BindView(2131363189)
    TextView numberOfComments;
    @BindView(2131363190)
    TextView numberOfLikes;
    private final OnClickListener onAddCommentClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (NewsFeedStatusCardViewHolder.this.feedDisplayActivityName != NewsFeedDisplayActivityName.Comments) {
                MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) view.getTag();
                NewsFeedStatusCardViewHolder.this.reportAddCommentsPressed(mfpNewsFeedActivityEntry);
                NewsFeedStatusCardViewHolder.this.navigationHelper.withExtra(Extras.ADD_COMMENT, true).withIntent(CommentsActivity.newStartIntent(NewsFeedStatusCardViewHolder.this.context, mfpNewsFeedActivityEntry)).startActivity(35);
            }
        }
    };
    private final OnClickListener onCloseClickListener = new OnClickListener() {
        public void onClick(View view) {
            NewsFeedStatusCardViewHolder.this.newsFeedItemActionListener.onCardCloseClick((MfpNewsFeedActivityEntry) view.getTag());
        }
    };
    private final OnClickListener onLikeClickListener = new OnClickListener() {
        public void onClick(View view) {
            MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) view.getTag();
            NewsFeedStatusCardViewHolder.this.postLike(mfpNewsFeedActivityEntry.getId(), !mfpNewsFeedActivityEntry.getLikes().isUserLiked(), mfpNewsFeedActivityEntry);
        }
    };
    private final Function1<MfpNewsFeedActivityEntry> onLikePostFinished = new Function1<MfpNewsFeedActivityEntry>() {
        public void execute(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) throws RuntimeException {
            NewsFeedStatusCardViewHolder.this.setLikeButton(mfpNewsFeedActivityEntry);
            NewsFeedStatusCardViewHolder.this.setLikeCount(mfpNewsFeedActivityEntry, mfpNewsFeedActivityEntry.getLikes().getCount());
        }
    };
    private final OnClickListener onProgressPhotoCardActionClickListener = new OnClickListener() {
        public void onClick(View view) {
            MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) view.getTag();
            NewsFeedStatusCardViewHolder.this.newsFeedItemActionListener.onImageCardActionClick(mfpNewsFeedActivityEntry, NewsFeedStatusCardViewHolder.this.isNewsfeedCardForLoggedInUser(mfpNewsFeedActivityEntry));
        }
    };
    private final OnClickListener onViewMealClickListener = new OnClickListener() {
        public void onClick(View view) {
            Tuple4 tuple4 = (Tuple4) view.getTag();
            NewsFeedStatusCardViewHolder.this.newsFeedItemActionListener.onViewMealClick((String) tuple4.getItem1(), (String) tuple4.getItem2(), (String) tuple4.getItem3(), (String) tuple4.getItem4());
            ((NewsFeedAnalyticsHelper) NewsFeedStatusCardViewHolder.this.newsFeedAnalyticsHelper.get()).reportActivityEntryDetailViewed(DataTypes.MEAL_STATUS_UPDATE);
        }
    };
    @BindView(2131363243)
    TextView partnerAppInfoTxt;
    @BindView(2131363314)
    ProgressBar progressBar;
    @BindView(2131362864)
    ImageView statusImage;
    @BindView(2131364181)
    FrameLayout statusImageViewArea;
    @BindView(2131363725)
    TextView statusTxt;
    @BindView(2131363927)
    TextView tvPhotoLoadFailed;
    @BindView(2131363945)
    TextView tvTryAgain;
    private final String userName;
    @BindView(2131364121)
    TextView userNameTxt;
    @BindView(2131364157)
    View viewImageFailed;
    @BindView(2131364183)
    View viewMealButton;

    public NewsFeedStatusCardViewHolder(ViewGroup viewGroup, Lazy<ImageService> lazy, Lazy<ConfigService> lazy2, NewsFeedItemActionListener newsFeedItemActionListener2, NavigationHelper navigationHelper2, Lazy<NewsFeedAnalyticsHelper> lazy3, NewsFeedDisplayActivityName newsFeedDisplayActivityName, String str) {
        super(R.layout.news_feed_status_card, viewGroup);
        this.imageService = lazy;
        this.context = viewGroup.getContext();
        this.newsFeedItemActionListener = newsFeedItemActionListener2;
        this.navigationHelper = navigationHelper2;
        this.newsFeedAnalyticsHelper = lazy3;
        this.feedDisplayActivityName = newsFeedDisplayActivityName;
        this.userName = str;
    }

    public void setData(NewsFeedItem newsFeedItem, int i) {
        String str;
        MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) newsFeedItem;
        MfpNewsFeedActivityParticipant owner = mfpNewsFeedActivityEntry.getOwner();
        MfpNewsFeedActivityEntryData entryData = mfpNewsFeedActivityEntry.getEntryData();
        boolean isProfileVisible = owner.isProfileVisible();
        String profilePhotoUrl = owner.getProfilePhotoUrl();
        String username = owner.getUsername();
        String userId = owner.getUserId();
        String displayName = owner.getDisplayName();
        String str2 = null;
        if (entryData instanceof MfpNewsFeedPartnerActivityEntryData) {
            MfpNewsFeedPartnerActivityEntryData mfpNewsFeedPartnerActivityEntryData = (MfpNewsFeedPartnerActivityEntryData) entryData;
            str2 = mfpNewsFeedPartnerActivityEntryData.getApplicationName();
            str = mfpNewsFeedPartnerActivityEntryData.getApplicationId();
        } else {
            str = null;
        }
        setNewsfeedImageCard(entryData, username, userId);
        setUserImage(profilePhotoUrl, username, userId, isProfileVisible);
        setUserName(displayName, username, userId, isProfileVisible);
        setStatusTimeStamp(mfpNewsFeedActivityEntry.getCreatedAt());
        setPartnerAppInfo(str2, str);
        setMoreOptions(mfpNewsFeedActivityEntry);
        setStatus(mfpNewsFeedActivityEntry);
        setupLikesAndComments(mfpNewsFeedActivityEntry);
        setLikeButton(mfpNewsFeedActivityEntry);
        setCommentButton(mfpNewsFeedActivityEntry);
    }

    private void setupLikesAndComments(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        int i;
        int i2;
        boolean z = false;
        if (mfpNewsFeedActivityEntry.isLikesEnabled()) {
            MfpNewsFeedLikeDetails likes = mfpNewsFeedActivityEntry.getLikes();
            i = likes != null ? likes.getCount() : 0;
            setLikeCount(mfpNewsFeedActivityEntry, i);
        } else {
            i = 0;
        }
        if (mfpNewsFeedActivityEntry.isConversationEnabled()) {
            MfpNewsFeedActivityConversation conversation = mfpNewsFeedActivityEntry.getConversation();
            i2 = conversation != null ? conversation.getCount() : 0;
            setCommentCount(mfpNewsFeedActivityEntry, i2);
        } else {
            i2 = 0;
        }
        MfpNewsFeedActivityEntryData entryData = mfpNewsFeedActivityEntry.getEntryData();
        if (i > 0 || i2 > 0) {
            z = true;
        }
        setLikesCommentsContainerVisibility(entryData, z);
    }

    private void setLikesCommentsContainerVisibility(MfpNewsFeedActivityEntryData mfpNewsFeedActivityEntryData, boolean z) {
        if (mfpNewsFeedActivityEntryData instanceof MfpNewsFeedImageStatusUpdateEntry) {
            ViewUtils.setVisible(z, this.llLikesComments);
            return;
        }
        ViewUtils.setVisible(this.llLikesComments);
    }

    private void setNewsfeedImageCard(MfpNewsFeedActivityEntryData mfpNewsFeedActivityEntryData, String str, String str2) {
        ViewUtils.setGone(this.statusImageViewArea);
        ViewUtils.setGone(this.statusImage);
        ViewUtils.setGone(this.viewMealButton);
        setFailedLayoutVisibility(false);
        if (mfpNewsFeedActivityEntryData instanceof MfpNewsFeedImageStatusUpdateEntry) {
            String imageId = ((MfpNewsFeedImageStatusUpdateEntry) mfpNewsFeedActivityEntryData).getImageId();
            boolean z = mfpNewsFeedActivityEntryData instanceof MfpNewsFeedMealPhotoUpdateEntry;
            loadStatusImage(imageId, z);
            setTryAgainAction(imageId, z);
            this.statusImageViewArea.setOnClickListener(null);
            if (z) {
                MfpNewsFeedMealPhotoUpdateEntry mfpNewsFeedMealPhotoUpdateEntry = (MfpNewsFeedMealPhotoUpdateEntry) mfpNewsFeedActivityEntryData;
                Tuple4 create = Tuple.create(mfpNewsFeedMealPhotoUpdateEntry.getFoodId(), mfpNewsFeedMealPhotoUpdateEntry.getOriginalImageId(), str, str2);
                this.viewMealButton.setTag(create);
                this.statusImageViewArea.setTag(create);
                this.viewMealButton.setOnClickListener(this.onViewMealClickListener);
                this.statusImageViewArea.setOnClickListener(this.onViewMealClickListener);
            }
        }
    }

    private void setTryAgainAction(final String str, final boolean z) {
        this.tvTryAgain.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NewsFeedStatusCardViewHolder.this.loadStatusImage(str, z);
            }
        });
    }

    /* access modifiers changed from: private */
    public void loadStatusImage(String str, final boolean z) {
        String stableImageUrlById = ((ImageService) this.imageService.get()).getStableImageUrlById(str);
        if (Strings.notEmpty(stableImageUrlById)) {
            ViewUtils.setVisible(this.statusImageViewArea);
            ViewUtils.setVisible(this.statusImage);
            setFailedLayoutVisibility(false);
            this.statusImage.setImageDrawable(null);
            ViewUtils.setVisible(this.progressBar);
            Glide.with(this.context).load(stableImageUrlById).apply(REQUEST_OPTIONS).listener(new RequestListener<Drawable>() {
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    ViewUtils.setGone(NewsFeedStatusCardViewHolder.this.progressBar);
                    ViewUtils.setGone(NewsFeedStatusCardViewHolder.this.statusImage);
                    ViewUtils.setGone(NewsFeedStatusCardViewHolder.this.viewMealButton);
                    NewsFeedStatusCardViewHolder.this.setFailedLayoutVisibility(true);
                    return false;
                }

                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    ViewUtils.setGone(NewsFeedStatusCardViewHolder.this.progressBar);
                    ViewUtils.setVisible(z, NewsFeedStatusCardViewHolder.this.viewMealButton);
                    NewsFeedStatusCardViewHolder.this.setFailedLayoutVisibility(false);
                    return false;
                }
            }).into(this.statusImage);
        }
    }

    /* access modifiers changed from: private */
    public void setFailedLayoutVisibility(boolean z) {
        ViewUtils.setVisible(z, this.viewImageFailed);
        ViewUtils.setVisible(z, this.tvPhotoLoadFailed);
        ViewUtils.setVisible(z, this.tvTryAgain);
    }

    private void setUserImage(String str, final String str2, final String str3, final boolean z) {
        if (Strings.notEmpty(str)) {
            this.avatarImg.setUrl(str);
        }
        this.avatarImg.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NewsFeedStatusCardViewHolder.this.navigateToProfileViewScreen(str2, str3, z);
            }
        });
    }

    private void setUserName(String str, String str2, String str3, boolean z) {
        TextView textView = this.userNameTxt;
        if (!Strings.notEmpty(str)) {
            str = Strings.notEmpty(str2) ? str2 : "";
        }
        textView.setText(str);
        setTagForNavigationToProfile(this.userNameTxt, str2, str3, z);
        this.userNameTxt.setOnClickListener(this.navigateToProfileClickListener);
    }

    private void setTagForNavigationToProfile(View view, String str, String str2, boolean z) {
        view.setTag(Tuple.create(str, str2, Boolean.valueOf(z)));
    }

    private void setStatusTimeStamp(Date date) {
        this.dateTxt.setText(DateTimeUtils.formatHumanReadableTime(this.context, date));
    }

    private void setPartnerAppInfo(String str, String str2) {
        if (Strings.notEmpty(str)) {
            ViewUtils.setVisible(true, this.partnerAppInfoTxt);
            this.partnerAppInfoTxt.setText(Html.fromHtml(this.context.getResources().getString(R.string.via, new Object[]{str})));
            this.partnerAppInfoTxt.setTag(str2);
            this.partnerAppInfoTxt.setOnClickListener(this.navigateToPartnerGalleryClickListener);
            return;
        }
        ViewUtils.setVisible(false, this.partnerAppInfoTxt);
    }

    private void setMoreOptions(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        boolean z = mfpNewsFeedActivityEntry.getEntryData() instanceof MfpNewsFeedImageStatusUpdateEntry;
        boolean z2 = this.feedDisplayActivityName != NewsFeedDisplayActivityName.Comments;
        this.cardActionBtn.setImageResource(z ? R.drawable.dots_horizontal : R.drawable.newsfeed_x);
        ViewUtils.setVisible((mfpNewsFeedActivityEntry.isIsRemovableByUser() || z) && z2, this.cardActionBtn);
        ViewUtils.increaseHitRectBy(this.context.getResources().getDimensionPixelSize(R.dimen.news_feed_icon_button_padding), this.cardActionBtn);
        this.cardActionBtn.setTag(mfpNewsFeedActivityEntry);
        this.cardActionBtn.setOnClickListener(z ? this.onProgressPhotoCardActionClickListener : this.onCloseClickListener);
    }

    private void setStatus(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        MfpNewsFeedActivityEntryData entryData = mfpNewsFeedActivityEntry.getEntryData();
        if (entryData != null) {
            String trim = Strings.toString(entryData.getText()).trim();
            String type = mfpNewsFeedActivityEntry.getType();
            MfpNewsFeedActivityParticipant owner = mfpNewsFeedActivityEntry.getOwner();
            if (Strings.equalsIgnoreCase(type, DataTypes.DIARY_COMPLETE) && ((MfpNewsFeedDiaryCompleteEntry) entryData).isShowDiary() && owner != null) {
                setDiaryCompletedStatus(mfpNewsFeedActivityEntry, owner, trim);
            } else if (Strings.equalsIgnoreCase(type, DataTypes.CHALLENGE_STATUS)) {
                setChallengeStatus(entryData, trim);
            } else {
                this.statusTxt.setTag(mfpNewsFeedActivityEntry);
                this.statusTxt.setText(trim);
                this.statusTxt.setOnClickListener(this.navToCommentsClickListener);
                ViewUtils.setVisible(!(mfpNewsFeedActivityEntry.getEntryData() instanceof MfpNewsFeedImageStatusUpdateEntry) || Strings.notEmpty(trim), this.statusTxt);
            }
        }
    }

    private void setDiaryCompletedStatus(final MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, final MfpNewsFeedActivityParticipant mfpNewsFeedActivityParticipant, String str) {
        Resources resources = this.context.getResources();
        String string = resources.getString(R.string.viewDiaryBtn);
        SpannableString spannableString = new SpannableString(String.format("%s %s", new Object[]{str, string}));
        spannableString.setSpan(new InternalURLSpan(new OnClickListener() {
            public void onClick(View view) {
                NewsFeedStatusCardViewHolder.this.navigationHelper.withExtra(Extras.USER_NAME, mfpNewsFeedActivityParticipant.getUsername()).withExtra(Extras.USER_UID, mfpNewsFeedActivityParticipant.getUserId()).withExtra("data", mfpNewsFeedActivityEntry.getCreatedAt().getTime()).withExtra("image_url", mfpNewsFeedActivityParticipant.getProfilePhotoUrl()).withIntent(FriendDiary.newStartIntent(view.getContext())).startActivity();
            }
        }, resources.getColor(R.color.hyperlink_fg_selector)), str.length() + 1, str.length() + string.length() + 1, 33);
        this.statusTxt.setOnClickListener(this.navToCommentsClickListener);
        this.statusTxt.setTag(mfpNewsFeedActivityEntry);
        this.statusTxt.setText(spannableString);
        this.statusTxt.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void setChallengeStatus(MfpNewsFeedActivityEntryData mfpNewsFeedActivityEntryData, String str) {
        SpannableString spannableString = new SpannableString(str);
        List<MfpNewsFeedChallengeLink> links = ((MfpNewsFeedChallengeStatusEntry) mfpNewsFeedActivityEntryData).getLinks();
        if (CollectionUtils.notEmpty((Collection<?>) links)) {
            for (final MfpNewsFeedChallengeLink mfpNewsFeedChallengeLink : links) {
                if (mfpNewsFeedChallengeLink != null && Strings.notEmpty(mfpNewsFeedChallengeLink.getText())) {
                    String text = mfpNewsFeedChallengeLink.getText();
                    if (Strings.notEmpty(text)) {
                        text = text.trim();
                    }
                    int indexOf = str.indexOf(text);
                    if (indexOf >= 0) {
                        spannableString.setSpan(new InternalURLSpan(new OnClickListener() {
                            public void onClick(View view) {
                                NewsFeedCardUtils.handleDeepLink(NewsFeedStatusCardViewHolder.this.navigationHelper, mfpNewsFeedChallengeLink);
                            }
                        }, this.context.getResources().getColor(R.color.hyperlink_fg_selector)), indexOf, Strings.length(text) + indexOf, 33);
                    }
                }
            }
        }
        this.statusTxt.setText(spannableString);
        this.statusTxt.setMovementMethod(LinkMovementMethod.getInstance());
        this.statusTxt.setOnClickListener(null);
    }

    /* access modifiers changed from: private */
    public void setLikeCount(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, int i) {
        this.numberOfLikes.setTag(mfpNewsFeedActivityEntry.getId());
        setLikesAndCommentsTextAndListener(this.numberOfLikes, i, R.string.like_count_format_single, R.string.like_count_format_plural, this.navigateToLikesClickListener);
        ViewUtils.increaseHitRectBy(10, this.numberOfLikes);
    }

    private void setCommentCount(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, int i) {
        this.numberOfComments.setTag(mfpNewsFeedActivityEntry);
        setLikesAndCommentsTextAndListener(this.numberOfComments, i, R.string.comment_count_format_single, R.string.comment_count_format_plural, this.navToCommentsClickListener);
    }

    private void setLikesAndCommentsTextAndListener(TextView textView, int i, int i2, int i3, OnClickListener onClickListener) {
        ViewUtils.setVisible(i > 0, textView);
        Context context2 = this.context;
        if (i > 1) {
            i2 = i3;
        }
        textView.setText(context2.getString(i2, new Object[]{Integer.valueOf(i)}));
        textView.setOnClickListener(onClickListener);
    }

    /* access modifiers changed from: private */
    public void setLikeButton(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        setLikeChecked(mfpNewsFeedActivityEntry.getLikes().isUserLiked(), mfpNewsFeedActivityEntry);
        this.likeBtn.setTag(mfpNewsFeedActivityEntry);
        this.likeBtn.setOnClickListener(this.onLikeClickListener);
    }

    private void setLikeChecked(boolean z, MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        this.likeTextView.setSelected(z);
        setupLikesAndComments(mfpNewsFeedActivityEntry);
    }

    /* access modifiers changed from: private */
    public void postLike(String str, boolean z, MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        String str2 = z ? Actions.LIKE : Actions.UNLIKE;
        ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportLikeClicked(mfpNewsFeedActivityEntry.getType(), z);
        setLikeChecked(z, mfpNewsFeedActivityEntry);
        NewsFeedItemActionListener newsFeedItemActionListener2 = this.newsFeedItemActionListener;
        if (newsFeedItemActionListener2 != null) {
            Function1<MfpNewsFeedActivityEntry> function1 = this.onLikePostFinished;
            newsFeedItemActionListener2.onLikeClick(mfpNewsFeedActivityEntry, str, str2, function1, function1);
        }
    }

    private void setCommentButton(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        boolean z;
        MfpNewsFeedActivityConversation conversation = mfpNewsFeedActivityEntry.getConversation();
        if (conversation != null) {
            List comments = conversation.getComments();
            if (CollectionUtils.notEmpty((Collection<?>) comments)) {
                Iterator it = comments.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    MfpNewsFeedActivityComment mfpNewsFeedActivityComment = (MfpNewsFeedActivityComment) it.next();
                    if (mfpNewsFeedActivityComment != null) {
                        MfpNewsFeedActivityParticipant sourceUser = mfpNewsFeedActivityComment.getSourceUser();
                        if (sourceUser != null && Strings.equalsIgnoreCase(sourceUser.getUsername(), this.userName)) {
                            z = true;
                            break;
                        }
                    }
                }
            }
        }
        z = false;
        this.commentTextView.setSelected(z);
        this.commentBtn.setTag(mfpNewsFeedActivityEntry);
        this.commentBtn.setOnClickListener(this.onAddCommentClickListener);
    }

    /* access modifiers changed from: private */
    public void navigateToProfileViewScreen(String str, String str2, boolean z) {
        if (!Strings.isEmpty(str)) {
            this.navigationHelper.withExtra(Extras.IS_PROFILE_VISIBLE, z).withIntent(ProfileView.newStartIntent(this.context, str, str2)).startActivity(36);
        }
    }

    /* access modifiers changed from: private */
    public void reportCommentsButtonPressed(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportCommentsButtonPressed(mfpNewsFeedActivityEntry.getType());
    }

    /* access modifiers changed from: private */
    public void reportAddCommentsPressed(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportAddCommentsPressed(mfpNewsFeedActivityEntry.getType());
    }

    /* access modifiers changed from: private */
    public boolean isNewsfeedCardForLoggedInUser(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        return mfpNewsFeedActivityEntry.isIsRemovableByUser() && Strings.equalsIgnoreCase(this.userName, mfpNewsFeedActivityEntry.getOwner().getUsername());
    }
}
