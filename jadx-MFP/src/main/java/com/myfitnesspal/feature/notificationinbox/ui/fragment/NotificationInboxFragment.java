package com.myfitnesspal.feature.notificationinbox.ui.fragment;

import android.content.Intent;
import android.databinding.Observable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsParent;
import com.myfitnesspal.feature.notificationinbox.model.NotificationTapTarget;
import com.myfitnesspal.feature.notificationinbox.service.BulkUpdatePendingStateTask;
import com.myfitnesspal.feature.notificationinbox.service.DeleteNotificationTask;
import com.myfitnesspal.feature.notificationinbox.service.PatchReadStateTask;
import com.myfitnesspal.feature.notificationinbox.service.PatchReadStateTask.CompletedEvent;
import com.myfitnesspal.feature.notificationinbox.service.ReportNotificationCountsTask;
import com.myfitnesspal.feature.notificationinbox.ui.adapter.NotificationsAdapter;
import com.myfitnesspal.feature.notificationinbox.ui.adapter.NotificationsAdapter.NotificationsAdapterOperationListener;
import com.myfitnesspal.feature.notificationinbox.ui.fragment.NotificationLongPressDialogFragment.OnDeleteClickedListener;
import com.myfitnesspal.feature.notificationinbox.ui.viewmodel.NotificationInboxViewModel;
import com.myfitnesspal.feature.notificationinbox.ui.viewmodel.NotificationInboxViewModel.Property;
import com.myfitnesspal.feature.notificationinbox.util.NotificationInboxAnalyticsHelper;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.DividerItemDecorator;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import dagger.Lazy;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.UacfNotificationSdkFactory;
import io.uacf.inbox.sdk.model.UacfNotification;
import io.uacf.inbox.sdk.model.UacfNotification.Builder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class NotificationInboxFragment extends MfpFragment implements NotificationsAdapterOperationListener {
    private static final String DELETE_DIALOG_TAG = "delete_dialog";
    /* access modifiers changed from: private */
    public NotificationsAdapter adapter;
    private OnClickListener addFriendsClickListener = new OnClickListener() {
        public void onClick(View view) {
            ((NotificationInboxAnalyticsHelper) NotificationInboxFragment.this.inboxAnalyticsHelper.get()).reportNotifEmptyStateAddFriendTapped();
            NotificationInboxFragment.this.getNavigationHelper().withIntent(AddFriendsParent.newStartIntent(NotificationInboxFragment.this.getActivity())).startActivity();
        }
    };
    @BindView(2131362022)
    Button btnNotifUpdate;
    private OnClickListener btnNotifUpdateClickListener = new OnClickListener() {
        public void onClick(View view) {
            if (ViewUtils.isVisible(NotificationInboxFragment.this.notifEmptyState)) {
                NotificationInboxFragment.this.setVisibility(false, true);
            }
            ((NotificationInboxAnalyticsHelper) NotificationInboxFragment.this.inboxAnalyticsHelper.get()).reportPillTapped(((Integer) NotificationInboxFragment.this.btnNotifUpdate.getTag()).intValue());
            NotificationInboxFragment.this.fillNotificationsToAdapter();
            NotificationInboxFragment.this.notificationsView.smoothScrollToPosition(0);
            NotificationInboxFragment.this.adapter.notifyDataSetChanged();
            NotificationInboxFragment.this.hideRefreshButton();
            NotificationInboxFragment.this.markAllPendingAsUnread();
        }
    };
    private int currentNotifCount;
    private OnDeleteClickedListener deleteClickedListener = new OnDeleteClickedListener() {
        public void onDeleteClicked(String str) {
        }

        public void onDeleteClicked(int i) {
            new DeleteNotificationTask(i, ((UacfNotification) NotificationInboxFragment.this.notificationListForAdapter.get(i)).getEngagementId(), NotificationInboxFragment.this.notificationSdk).run(NotificationInboxFragment.this.getRunner());
        }
    };
    @Inject
    Lazy<NotificationInboxAnalyticsHelper> inboxAnalyticsHelper;
    private LinearLayoutManager layoutManager;
    @BindView(2131363176)
    View notifEmptyState;
    @BindView(2131363178)
    SwipeRefreshLayout notifRefreshContainer;
    /* access modifiers changed from: private */
    public List<UacfNotification> notificationListForAdapter = new ArrayList();
    private List<UacfNotification> notificationListFromFetch = new ArrayList();
    /* access modifiers changed from: private */
    public UacfNotificationSdk notificationSdk;
    @BindView(2131363184)
    View notificationsContainer;
    @BindView(2131363185)
    RecyclerView notificationsView;
    OnScrollListener onScrollListener = new OnScrollListener() {
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            if (ViewUtils.isVisible(NotificationInboxFragment.this.btnNotifUpdate) && i2 > 5) {
                NotificationInboxFragment.this.hideRefreshButton();
            }
        }
    };
    /* access modifiers changed from: private */
    public RefreshMode refreshMode = RefreshMode.None;
    @Inject
    Lazy<SyncService> syncService;
    @BindView(2131363949)
    TextView tvAddFriends;
    private NotificationInboxViewModel viewModel;

    private enum RefreshMode {
        Sync,
        PullToRefresh,
        None
    }

    public static NotificationInboxFragment newInstance() {
        return new NotificationInboxFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.notification_inbox_activity, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        reportCounts();
        setupUI();
        initViewModel();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setTitle(getString(R.string.notifications));
        this.notificationSdk = new UacfNotificationSdkFactory().newSdkInstance();
    }

    private void initViewModel() {
        this.viewModel = (NotificationInboxViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (NotificationInboxViewModel) setViewModel(new NotificationInboxViewModel(getRunner(), this.notificationSdk));
        }
        this.viewModel.loadIfNotLoaded(new String[0]);
        if (this.viewModel.getState() == State.Loaded) {
            this.notificationListFromFetch = this.viewModel.getUacfNotificationList();
            executePostFetchSteps();
        }
        setBusy(this.viewModel.isBusy());
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        setBusy(false);
        if (i == Property.NOTIFICATIONS_LIST) {
            this.notificationListFromFetch = this.viewModel.getUacfNotificationList();
            setupUI();
            executePostFetchSteps();
        }
        super.onViewModelPropertyChanged(observable, i);
    }

    public void setTitle(CharSequence charSequence) {
        TextView textView = (TextView) getActivity().findViewById(R.id.title);
        if (textView != null) {
            textView.setText(charSequence);
        }
    }

    private void reportCounts() {
        new ReportNotificationCountsTask(this.inboxAnalyticsHelper, this.notificationSdk).run(getRunner());
    }

    private void setupUI() {
        this.tvAddFriends.setOnClickListener(this.addFriendsClickListener);
        this.btnNotifUpdate.setOnClickListener(this.btnNotifUpdateClickListener);
        this.adapter = new NotificationsAdapter(this.notificationListForAdapter, this);
        this.notificationsView.setAdapter(this.adapter);
        this.layoutManager = new LinearLayoutManager(getActivity());
        this.notificationsView.setLayoutManager(this.layoutManager);
        this.notificationsView.addItemDecoration(new DividerItemDecorator(getActivity()));
        this.notificationsView.addOnScrollListener(this.onScrollListener);
        this.notifRefreshContainer.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                ((NotificationInboxAnalyticsHelper) NotificationInboxFragment.this.inboxAnalyticsHelper.get()).reportPullToRefresh();
                NotificationInboxFragment.this.refreshMode = RefreshMode.PullToRefresh;
                ((SyncService) NotificationInboxFragment.this.syncService.get()).enqueue(SyncType.Incremental);
            }
        });
    }

    @Subscribe
    public void onSyncFinished(UacfScheduleFinishedInfo<SyncType> uacfScheduleFinishedInfo) {
        if (!uacfScheduleFinishedInfo.isSuccessful() || uacfScheduleFinishedInfo.getScheduleGroup() != SyncType.Incremental) {
            this.notifRefreshContainer.setRefreshing(false);
            return;
        }
        this.currentNotifCount = this.notificationListForAdapter.size();
        this.viewModel.load(new String[0]);
    }

    private void executePostFetchSteps() {
        refresh();
        this.notifRefreshContainer.setRefreshing(false);
        markAllPendingAsUnread();
    }

    /* access modifiers changed from: private */
    public void markAllPendingAsUnread() {
        new BulkUpdatePendingStateTask(this.notificationSdk).run(getRunner());
    }

    @Subscribe
    public void onPatchReadStateTask(CompletedEvent completedEvent) {
        if (completedEvent.getRunnerId() == getRunner().getId() && ((Boolean) completedEvent.getResult()).booleanValue()) {
            int position = completedEvent.getPosition();
            this.notificationListForAdapter.set(position, new Builder((UacfNotification) this.notificationListForAdapter.get(position)).withState(completedEvent.isMarkRead() ? "READ" : "UNREAD").build());
            this.adapter.notifyItemChanged(position);
            if (completedEvent.isMarkRead()) {
                launchDeepLink(completedEvent.getDeeplinkToLaunch());
            }
        }
    }

    @Subscribe
    public void onDeleteNotificationTask(DeleteNotificationTask.CompletedEvent completedEvent) {
        if (completedEvent.getRunnerId() == getRunner().getId() && ((Boolean) completedEvent.getResult()).booleanValue()) {
            int position = completedEvent.getPosition();
            UacfNotification uacfNotification = (UacfNotification) this.notificationListForAdapter.get(position);
            ((NotificationInboxAnalyticsHelper) this.inboxAnalyticsHelper.get()).reportNotificationDeleted(uacfNotification.getEngagementId(), uacfNotification.getBodyDeepLink(), position, uacfNotification.getState(), uacfNotification.getAnalytic());
            this.notificationListForAdapter.remove(position);
            this.adapter.notifyItemRemoved(position);
            if (CollectionUtils.isEmpty((Collection<?>) this.notificationListForAdapter)) {
                setVisibility(true, false);
            }
        }
    }

    private void refresh() {
        if (this.refreshMode == RefreshMode.Sync) {
            activateRefreshButton();
            return;
        }
        this.refreshMode = RefreshMode.Sync;
        if (CollectionUtils.notEmpty((Collection<?>) this.notificationListFromFetch)) {
            fillNotificationsToAdapter();
            this.adapter.notifyDataSetChanged();
            setVisibility(false, true);
            markAllPendingAsUnread();
            return;
        }
        setVisibility(true, false);
    }

    /* access modifiers changed from: private */
    public void fillNotificationsToAdapter() {
        this.notificationListForAdapter.clear();
        this.notificationListForAdapter.addAll(this.notificationListFromFetch);
        this.notificationListFromFetch.clear();
    }

    private void activateRefreshButton() {
        int size = this.notificationListFromFetch.size() - this.currentNotifCount;
        ViewUtils.setVisible(size > 0, this.btnNotifUpdate);
        this.btnNotifUpdate.animate().translationY(30.0f).setInterpolator(new BounceInterpolator());
        this.btnNotifUpdate.setText(getString(R.string.new_notif_count, Integer.valueOf(size)));
        this.btnNotifUpdate.setTag(Integer.valueOf(size));
        ((NotificationInboxAnalyticsHelper) this.inboxAnalyticsHelper.get()).reportPillDisplayed(size);
    }

    @NonNull
    private Animation getRefreshButtonFadeOutAnimation() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, BitmapDescriptorFactory.HUE_RED);
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        alphaAnimation.setDuration(500);
        alphaAnimation.setAnimationListener(new AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                ViewUtils.setGone(NotificationInboxFragment.this.btnNotifUpdate);
            }
        });
        return alphaAnimation;
    }

    /* access modifiers changed from: private */
    public void setVisibility(boolean z, boolean z2) {
        ViewUtils.setVisible(z, this.notifEmptyState);
        ViewUtils.setVisible(z2, this.notificationsContainer);
        if (z) {
            this.tvAddFriends.setOnClickListener(this.addFriendsClickListener);
        }
    }

    /* access modifiers changed from: private */
    public void hideRefreshButton() {
        this.refreshMode = RefreshMode.Sync;
        this.btnNotifUpdate.startAnimation(getRefreshButtonFadeOutAnimation());
    }

    public void onItemClicked(int i) {
        onNotificationClicked(i, NotificationTapTarget.Primary);
    }

    public void onItemLongClicked(int i) {
        NotificationLongPressDialogFragment newInstance = NotificationLongPressDialogFragment.newInstance(i);
        newInstance.setOnDeleteClickedListener(this.deleteClickedListener);
        showDialogFragment(newInstance, DELETE_DIALOG_TAG);
    }

    public void onProfileImageClicked(int i) {
        onNotificationClicked(i, NotificationTapTarget.UserProfile);
    }

    private void onNotificationClicked(int i, NotificationTapTarget notificationTapTarget) {
        UacfNotification uacfNotification = (UacfNotification) this.notificationListForAdapter.get(i);
        String deeplinkBasedOnTapTarget = getDeeplinkBasedOnTapTarget(uacfNotification, notificationTapTarget);
        ((NotificationInboxAnalyticsHelper) this.inboxAnalyticsHelper.get()).reportNotificationTapped(notificationTapTarget, uacfNotification.getEngagementId(), deeplinkBasedOnTapTarget, i, uacfNotification.getAnalytic(), uacfNotification.getCreatedAt());
        if (!uacfNotification.getState().equals("READ")) {
            markNotificationState(uacfNotification, i, "READ", deeplinkBasedOnTapTarget);
        } else {
            launchDeepLink(deeplinkBasedOnTapTarget);
        }
    }

    private void markNotificationState(UacfNotification uacfNotification, int i, String str, String str2) {
        PatchReadStateTask patchReadStateTask = new PatchReadStateTask(uacfNotification, i, getNumOfNotificationsVisible(), str.equals("READ"), this.notificationSdk, str2);
        patchReadStateTask.run(getRunner());
    }

    private int getNumOfNotificationsVisible() {
        return (this.layoutManager.findLastCompletelyVisibleItemPosition() - this.layoutManager.findFirstCompletelyVisibleItemPosition()) + 1;
    }

    private void launchDeepLink(String str) {
        if (Strings.notEmpty(str)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str).buildUpon().appendQueryParameter("withinApp", "true").build());
            if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                getNavigationHelper().withIntent(intent).startActivity();
            }
        }
    }

    private String getDeeplinkBasedOnTapTarget(UacfNotification uacfNotification, NotificationTapTarget notificationTapTarget) {
        return notificationTapTarget == NotificationTapTarget.UserProfile ? uacfNotification.getPrimaryImageDeepLink() : uacfNotification.getBodyDeepLink();
    }
}
