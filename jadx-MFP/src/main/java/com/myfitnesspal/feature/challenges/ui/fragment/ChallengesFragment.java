package com.myfitnesspal.feature.challenges.ui.fragment;

import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeDetailActivity;
import com.myfitnesspal.feature.challenges.ui.activity.UserAchievementsActivity;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeListItem;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeListItemWithTitle;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeSummaryListAdapter;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeSummaryListItem;
import com.myfitnesspal.feature.challenges.ui.adapter.EndedChallengeListItem;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeSummaryViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengesListViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengesListViewModelBase;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengesListViewModelBase.Property;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class ChallengesFragment extends MfpFragment {
    private static final int ACHIEVEMENTS_ITEM = 1001;
    protected ChallengeSummaryListAdapter adapter;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @BindView(2131362973)
    ListView challengesList;
    @Inject
    Lazy<ChallengesService> challengesService;
    @BindView(2131362455)
    View emptyState;
    @Inject
    Lazy<ImageService> imageService;
    private boolean initialized;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @BindView(2131363909)
    TextView tvEmptyState;
    private ChallengesListViewModelBase viewModel;

    public static ChallengesFragment newInstance() {
        return new ChallengesFragment();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.challenges_activity, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        setTitle(R.string.challenges_activity_title, new Object[0]);
        return inflate;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);
        component().inject(this);
    }

    public void onResume() {
        super.onResume();
        initializeUi();
    }

    public void onStop() {
        super.onStop();
        this.initialized = false;
    }

    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.achievements).setIcon(R.drawable.ic_achievements_white), 2);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        getNavigationHelper().withIntent(UserAchievementsActivity.newStartIntent(getActivity(), getSession().getUser().getUserId())).startActivity();
        return true;
    }

    private void initializeUi() {
        if (!this.initialized) {
            attachEventListeners();
            initializeViewModel();
            this.adapter = new ChallengeSummaryListAdapter(getActivity().getLayoutInflater(), new ArrayList(), this.imageService, getActivity());
            this.challengesList.setAdapter(this.adapter);
            this.initialized = true;
        }
        rebindView();
    }

    private void attachEventListeners() {
        this.challengesList.setOnItemClickListener(new OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                ChallengesFragment.lambda$attachEventListeners$0(ChallengesFragment.this, adapterView, view, i, j);
            }
        });
    }

    public static /* synthetic */ void lambda$attachEventListeners$0(ChallengesFragment challengesFragment, AdapterView adapterView, View view, int i, long j) {
        ChallengeListItem item = challengesFragment.adapter.getItem(i);
        String str = null;
        ChallengeSummaryViewModel challengeSummaryViewModel = item instanceof ChallengeSummaryListItem ? ((ChallengeSummaryListItem) item).getChallengeSummary() : item instanceof EndedChallengeListItem ? ((EndedChallengeListItem) item).getChallengeSummary() : null;
        if (challengeSummaryViewModel != null) {
            challengesFragment.reportAnalytics(challengeSummaryViewModel);
            str = challengeSummaryViewModel.getChallengeId();
        }
        if (str != null) {
            challengesFragment.getNavigationHelper().withIntent(ChallengeDetailActivity.newStartIntent(challengesFragment.getActivity(), str, ChallengesAnalyticsHelper.SOURCE_CHALLENGES_HUB)).withDisableStartFromFragment(true).startActivity(RequestCodes.CHALLENGE_DETAIL);
        }
    }

    private void reportAnalytics(ChallengeSummaryViewModel challengeSummaryViewModel) {
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportChallengeSelectedEvent(challengeSummaryViewModel.getListType(), challengeSummaryViewModel.getChallengeName(), challengeSummaryViewModel.getChallengeId());
    }

    private void initializeViewModel() {
        this.viewModel = (ChallengesListViewModelBase) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (ChallengesListViewModelBase) setViewModel(createViewModel());
        }
        this.viewModel.refresh();
    }

    /* access modifiers changed from: protected */
    public ChallengesListViewModelBase createViewModel() {
        return new ChallengesListViewModel(getRunner(), this.challengesService);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.CHALLENGE_LIST) {
            setEmptyStateVisibility(false, true);
            reportChallengeScreenAnalytics();
            if (this.adapter != null) {
                ChallengesListViewModel challengesListViewModel = (ChallengesListViewModel) getViewModel();
                ArrayList arrayList = new ArrayList();
                if (!CollectionUtils.isEmpty((Collection<?>) challengesListViewModel.getChallengesList())) {
                    if (challengesListViewModel.hasNewChallenges()) {
                        arrayList.add(new ChallengeListItemWithTitle(getResources().getString(R.string.new_challenges), 0));
                    }
                    arrayList.addAll(challengesListViewModel.getChallengesList());
                    if (challengesListViewModel.hasActiveChallenges()) {
                        arrayList.add(challengesListViewModel.getActiveItemTitleIndex(), new ChallengeListItemWithTitle(getResources().getString(R.string.list_active_challenges), 1));
                    }
                    if (challengesListViewModel.hasEndedChallenges()) {
                        arrayList.add(challengesListViewModel.getIndexForEndedTitleItem(), new ChallengeListItemWithTitle(getResources().getString(R.string.list_ended_challenges), 2));
                    }
                    resetAdapter(arrayList);
                } else {
                    setEmptyStateVisibility(true, false);
                }
            }
            resetNewChallengesValues();
        } else if (i == Property.LOAD_STATE) {
            if (this.viewModel.getState() == State.Error) {
                getMessageBus().post(new AlertEvent(getString(R.string.failed_to_load_app_data)));
            }
            setBusy(this.viewModel.isBusy());
        } else if (i == Property.CHALLENGE_LIST_EMPTY) {
            setEmptyStateVisibility(true, false);
            TextViewUtils.setText(this.tvEmptyState, getString(this.viewModel.getEmptyStateStringId()));
        }
    }

    private void reportChallengeScreenAnalytics() {
        ChallengesListViewModel challengesListViewModel = (ChallengesListViewModel) getViewModel();
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportChallengeScreenAnalytics(challengesListViewModel.getNewChallengesCount(), challengesListViewModel.getActiveChallengesCount());
    }

    private void resetAdapter(List<ChallengeListItem> list) {
        ListViewUtils.refill(this.adapter, list);
    }

    private void setEmptyStateVisibility(boolean z, boolean z2) {
        ViewUtils.setVisible(z, this.emptyState);
        ViewUtils.setVisible(z2, this.challengesList);
    }

    private void resetNewChallengesValues() {
        ((LocalSettingsService) this.localSettingsService.get()).setUnseenNewChallenges(0);
    }
}
