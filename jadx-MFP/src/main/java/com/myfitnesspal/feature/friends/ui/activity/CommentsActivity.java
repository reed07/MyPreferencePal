package com.myfitnesspal.feature.friends.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.task.DeleteCommentTask;
import com.myfitnesspal.feature.friends.task.FetchStatusV2IdForV1IdTask;
import com.myfitnesspal.feature.friends.task.FetchStatusV2IdForV1IdTask.CompletedEvent;
import com.myfitnesspal.feature.friends.task.PostCommentLikeTask;
import com.myfitnesspal.feature.friends.task.PostCommentTask;
import com.myfitnesspal.feature.friends.ui.dialog.DeleteCommentDialogFragment;
import com.myfitnesspal.feature.friends.ui.listener.DeleteCommentListener;
import com.myfitnesspal.feature.friends.ui.viewmodel.CommentsViewModel;
import com.myfitnesspal.feature.friends.ui.viewmodel.CommentsViewModel.Property;
import com.myfitnesspal.feature.home.listener.NewsFeedItemActionListener;
import com.myfitnesspal.feature.home.model.NewsFeedDisplayActivityName;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.home.ui.adapter.NewsFeedAdapter;
import com.myfitnesspal.feature.home.util.NewsFeedItemActionUtils;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.api.request.MfpNewsFeedLikesPostData.Actions;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityComment;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityConversation;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedLikeDetails;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.id.IdService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.widget.CatchIOOBLinearLayoutManager;
import com.myfitnesspal.shared.util.Toaster;
import com.squareup.otto.Subscribe;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.FunctionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class CommentsActivity extends MfpActivity implements DeleteCommentListener, NewsFeedItemActionListener {
    private static final String DELETE_COMMENT_DIALOG = "delete_comment_dialog";
    public static final String EXTRA_EXTERNAL_PARENT = "extra_external_parent";
    public static final String EXTRA_NEWSFEED_PARENT = "extra_newsfeed_parent";
    private static final String EXTRA_NEWS_FEED_ACTIVITY_ENTRY = "news_feed_activity_entry";
    public static final String EXTRA_PARENT_CONTEXT = "extra_parent_context";
    public static final String EXTRA_STATUS_ID = "status_id";
    private static final String IMAGE_ACTION_DIALOG_TAG = "image_action_dialog";
    private static final int POST_COMMENT_DEBOUNCE_WAIT_TIME_MS = 500;
    private static final int POST_MENU_ITEM = 100;
    @BindView(2131362171)
    EditText commentEditText;
    @BindView(2131362172)
    RecyclerView commentsRecyclerView;
    @Inject
    Lazy<ConfigService> configService;
    @Inject
    Lazy<IdService> idService;
    @Inject
    Lazy<ImageService> imageService;
    private NewsFeedAdapter newsFeedAdapter;
    @Inject
    Lazy<NewsFeedAnalyticsHelper> newsFeedAnalyticsHelper;
    @Inject
    Lazy<NewsFeedService> newsFeedService;
    private Debouncer<String> postCommentDebouncer = new Debouncer<String>(500) {
        /* access modifiers changed from: protected */
        public void onDebounced(String str) {
            CommentsActivity commentsActivity = CommentsActivity.this;
            commentsActivity.postComment(commentsActivity.getEntryId(), str, CommentsActivity.this.getSession().getUser().getUserId());
        }
    };
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    private CommentsViewModel viewModel;

    public static Intent newStartIntent(Context context, MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        return new Intent(context, CommentsActivity.class).putExtra(EXTRA_NEWS_FEED_ACTIVITY_ENTRY, mfpNewsFeedActivityEntry).putExtra(EXTRA_PARENT_CONTEXT, EXTRA_NEWSFEED_PARENT);
    }

    public static Intent newStartIntent(Context context, long j) {
        return new Intent(context, CommentsActivity.class).putExtra(EXTRA_STATUS_ID, j).putExtra(EXTRA_PARENT_CONTEXT, EXTRA_EXTERNAL_PARENT);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.comments);
        component().inject(this);
        initViewModel();
        setupRecyclerViewAndAdapter();
        getDataFromIntentOrViewModel();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!DELETE_COMMENT_DIALOG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((DeleteCommentDialogFragment) dialogFragment).setDeleteCommentListener(this);
        return true;
    }

    private void initViewModel() {
        this.viewModel = (CommentsViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (CommentsViewModel) setViewModel(new CommentsViewModel(this.newsFeedService, getIntent(), getRunner()));
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        menu.clear();
        if (Strings.notEmpty(this.commentEditText.getText().toString())) {
            MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.post).setIcon(R.drawable.ic_check_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return super.onOptionsItemSelected(menuItem);
        }
        postComment(getEntryId(), this.commentEditText.getText().toString(), getSession().getUser().getUserId());
        return true;
    }

    private void getDataFromIntentOrViewModel() {
        if (this.viewModel.getNewsFeedActivityEntry() != null) {
            displayCardAndComments();
            return;
        }
        Intent intent = getIntent();
        if (ExtrasUtils.hasExtra(intent, EXTRA_NEWS_FEED_ACTIVITY_ENTRY)) {
            MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry = (MfpNewsFeedActivityEntry) ExtrasUtils.getParcelable(intent, EXTRA_NEWS_FEED_ACTIVITY_ENTRY, MfpNewsFeedActivityEntry.class.getClassLoader());
            if (mfpNewsFeedActivityEntry == null) {
                notifyCouldNotLoadAndFinish();
            } else {
                this.viewModel.setNewsFeedActivityEntry(mfpNewsFeedActivityEntry);
                displayCardAndComments();
            }
        } else if (ExtrasUtils.hasExtra(intent, EXTRA_STATUS_ID)) {
            String statusIdFromIntent = getStatusIdFromIntent();
            if (Strings.isEmpty(statusIdFromIntent)) {
                notifyCouldNotLoadAndFinish();
                return;
            }
            this.viewModel.load(statusIdFromIntent);
        } else {
            notifyCouldNotLoadAndFinish();
        }
    }

    private String getStatusIdFromIntent() {
        return Strings.toString(Long.valueOf(ExtrasUtils.getLong(getIntent(), EXTRA_STATUS_ID)));
    }

    private void setupRecyclerViewAndAdapter() {
        NewsFeedAdapter newsFeedAdapter2 = new NewsFeedAdapter(getNavigationHelper(), this.premiumService, this.imageService, this, this.newsFeedAnalyticsHelper, this.configService, getSession(), NewsFeedDisplayActivityName.Comments, this.userApplicationSettingsService, getSession().getUser().getUsername(), "");
        this.newsFeedAdapter = newsFeedAdapter2;
        CatchIOOBLinearLayoutManager catchIOOBLinearLayoutManager = new CatchIOOBLinearLayoutManager(this);
        this.commentsRecyclerView.setHasFixedSize(true);
        this.commentsRecyclerView.setLayoutManager(catchIOOBLinearLayoutManager);
        this.commentsRecyclerView.setAdapter(this.newsFeedAdapter);
    }

    private void setupCommentEditText() {
        ViewUtils.setVisible(this.viewModel.getNewsFeedActivityEntry().isCommentableByUser(), this.commentEditText);
        this.commentEditText.setOnEditorActionListener(new OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return CommentsActivity.lambda$setupCommentEditText$0(CommentsActivity.this, textView, i, keyEvent);
            }
        });
        this.commentEditText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                CommentsActivity.this.invalidateOptionsMenu();
            }
        });
    }

    public static /* synthetic */ boolean lambda$setupCommentEditText$0(CommentsActivity commentsActivity, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6 && i != 0) {
            return false;
        }
        if (keyEvent == null || keyEvent.getAction() == 1) {
            commentsActivity.postCommentDebouncer.call(textView.getText().toString());
            commentsActivity.getImmHelper().hideSoftInput((View) textView);
        }
        return true;
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.NEWS_FEED_ENTRY_FETCHED) {
            displayCardAndComments();
        } else if (i != Property.NEWS_FEED_ENTRY_FETCH_FAILED) {
        } else {
            if (this.viewModel.hasRetriedAfterFetchingV2IdForV1IdOnce()) {
                notifyCouldNotLoadAndFinish();
            } else {
                new FetchStatusV2IdForV1IdTask(this.idService, getStatusIdFromIntent()).run(getRunner());
            }
        }
    }

    @Subscribe
    public void onFetchStatusV2IdForV1IdTaskCompletedEvent(CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            this.viewModel.setHasRetriedAfterFetchingV2IdForV1IdOnce(true);
            this.viewModel.load((String) completedEvent.getResult());
            return;
        }
        notifyCouldNotLoadAndFinish();
    }

    private void displayCardAndComments() {
        MfpNewsFeedActivityEntry newsFeedActivityEntry = this.viewModel.getNewsFeedActivityEntry();
        ArrayList arrayList = new ArrayList();
        arrayList.add(newsFeedActivityEntry);
        MfpNewsFeedActivityConversation conversation = newsFeedActivityEntry.getConversation();
        if (conversation != null) {
            List comments = conversation.getComments();
            if (CollectionUtils.notEmpty((Collection<?>) comments)) {
                arrayList.addAll(comments);
            }
        }
        this.newsFeedAdapter.refreshItems(arrayList);
        setupCommentEditText();
    }

    /* access modifiers changed from: private */
    public void postComment(String str, String str2, String str3) {
        if (!Strings.isEmpty(str) && !Strings.isEmpty(str2) && !Strings.isEmpty(str3)) {
            setBusyInternal(true);
            new PostCommentTask(this.newsFeedService, str, str3, str2).run(getRunner());
        }
    }

    @Subscribe
    public void onPostCommentTaskCompletedEvent(PostCommentTask.CompletedEvent completedEvent) {
        setBusyInternal(false);
        if (completedEvent.successful()) {
            addComment((MfpNewsFeedActivityComment) completedEvent.getResult());
            this.commentEditText.setText("");
            ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportActivityCommentAdded(this.viewModel.getNewsFeedActivityEntryType(), this.viewModel.getParentContext());
            return;
        }
        Toaster.showShort((Activity) this, (int) R.string.unable_post_comment);
    }

    private void addComment(MfpNewsFeedActivityComment mfpNewsFeedActivityComment) {
        MfpNewsFeedActivityEntry newsFeedActivityEntry = this.viewModel.getNewsFeedActivityEntry();
        if (newsFeedActivityEntry != null) {
            MfpNewsFeedActivityConversation conversation = newsFeedActivityEntry.getConversation();
            if (conversation == null) {
                conversation = new MfpNewsFeedActivityConversation(0, (List<MfpNewsFeedActivityComment>) new ArrayList<MfpNewsFeedActivityComment>());
                newsFeedActivityEntry.setConversation(conversation);
            }
            conversation.addCommentAndUpdateCount(mfpNewsFeedActivityComment);
            this.newsFeedAdapter.addItem(mfpNewsFeedActivityComment);
        } else {
            Ln.e("NewsFeedActivityEntry is null after post comment", new Object[0]);
        }
        this.newsFeedAdapter.notifyItemChanged(0);
        this.commentsRecyclerView.scrollToPosition(this.newsFeedAdapter.getItemCount() - 1);
    }

    private void removeComment(int i) {
        this.viewModel.getNewsFeedActivityEntry().getConversation().removeCommentAndUpdateCount(i - 1);
        this.newsFeedAdapter.removeItem(i);
        this.newsFeedAdapter.notifyDataSetChanged();
    }

    private void notifyCouldNotLoadAndFinish() {
        Toaster.showShort((Activity) this, (int) R.string.failed_to_load_app_data);
        finish();
    }

    private void setBusyInternal(boolean z) {
        setBusy(1, z);
    }

    public void onCardCloseClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry) {
        throw new UacfNotImplementedException();
    }

    public void onLikeClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, String str, String str2, Function1<MfpNewsFeedActivityEntry> function1, Function1<MfpNewsFeedActivityEntry> function12) {
        NewsFeedItemActionUtils.likeClick(this, this.newsFeedService, getMessageBus(), mfpNewsFeedActivityEntry, str, str2, function1, function12);
    }

    public void onImageCardActionClick(MfpNewsFeedActivityEntry mfpNewsFeedActivityEntry, boolean z) {
        NewsFeedItemActionUtils.imageCardActionClick(this, getSupportFragmentManager(), mfpNewsFeedActivityEntry, IMAGE_ACTION_DIALOG_TAG, z);
    }

    public void onCommentLikeCountClick(String str) {
        getNavigationHelper().withIntent(LikesActivity.newStartIntent(this, getEntryId(), str)).startActivity();
    }

    public void onCommentLikeClick(String str, boolean z, Function1<Integer> function1) {
        String str2 = z ? Actions.LIKE : Actions.UNLIKE;
        setBusyInternal(true);
        ((NewsFeedAnalyticsHelper) this.newsFeedAnalyticsHelper.get()).reportLikeClicked("comment", z, Extras.ACTIVITY_ENTRY_DETAIL);
        PostCommentLikeTask postCommentLikeTask = new PostCommentLikeTask(this.newsFeedService, getEntryId(), str, str2, function1);
        postCommentLikeTask.run(getRunner());
    }

    public void onUpdateStatusClick() {
        throw new UacfNotImplementedException();
    }

    @Subscribe
    public void onPostCommentLikeTaskCompletedEvent(PostCommentLikeTask.CompletedEvent completedEvent) {
        setBusyInternal(false);
        if (completedEvent.successful()) {
            FunctionUtils.invokeIfValid(completedEvent.getLikePostSuccessCallback(), Integer.valueOf(((MfpNewsFeedLikeDetails) completedEvent.getResult()).getCount()));
        }
    }

    public void onCommentLongClick(String str, int i) {
        DeleteCommentDialogFragment newInstance = DeleteCommentDialogFragment.newInstance(str, i);
        newInstance.setDeleteCommentListener(this);
        showDialogFragment(newInstance, DELETE_COMMENT_DIALOG);
    }

    public void onViewMealClick(String str, String str2, String str3, String str4) {
        NewsFeedItemActionUtils.viewMealFoodClick(getNavigationHelper(), this, str, str2, str3, str4, getSession());
    }

    public void onDeleteCommentClick(String str, int i) {
        setBusyInternal(true);
        new DeleteCommentTask(this.newsFeedService, getEntryId(), str, i).run(getRunner());
    }

    public void onCameraClick() {
        NewsFeedItemActionUtils.statusCameraClick(getNavigationHelper(), this);
    }

    @Subscribe
    public void onDeleteCommentTaskCompletedEvent(DeleteCommentTask.CompletedEvent completedEvent) {
        setBusyInternal(false);
        if (completedEvent.successful()) {
            removeComment(completedEvent.getPosition());
        } else {
            Toaster.showShort((Activity) this, (int) R.string.failDeleteComment);
        }
    }

    /* access modifiers changed from: private */
    public String getEntryId() {
        return this.viewModel.getNewsFeedActivityEntry().getId();
    }
}
