package com.myfitnesspal.feature.challenges.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.service.ExecuteJoinChallengeTask;
import com.myfitnesspal.feature.challenges.service.ExecuteJoinChallengeTask.CompletedEvent;
import com.myfitnesspal.feature.challenges.ui.adapter.JoinedChallengePagerAdapter;
import com.myfitnesspal.feature.challenges.ui.adapter.UnjoinedChallengePagerAdapter;
import com.myfitnesspal.feature.challenges.ui.fragment.ChallengeWebViewLoadListener;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeViewModel.Property;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.ViewModelComponent;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.inject.Inject;

public class ChallengeDetailActivity extends MfpActivity implements ChallengeWebViewLoadListener {
    private static final int CHILD_VIEW_MODEL_RESET_DELAY_MS = 500;
    private static final int MENU_SHARE = 1001;
    @BindView(2131362108)
    CheckBox cbEmailConsent;
    private String challengeId;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @Inject
    Lazy<ChallengesService> challengesService;
    private Debouncer childViewModelResetDebouncer = new Debouncer(500) {
        /* access modifiers changed from: protected */
        public void onDebounced(Object obj) {
            if (ChallengeDetailActivity.this.viewModel == null) {
                return;
            }
            if (ChallengeDetailActivity.this.viewModel.isLoaded()) {
                int currentItem = ChallengeDetailActivity.this.viewPager.getCurrentItem();
                ChallengeDetailActivity.this.recreateAdapter();
                if (currentItem >= 0) {
                    ChallengeDetailActivity.this.viewPager.setCurrentItem(currentItem, false);
                    return;
                }
                return;
            }
            ChallengeDetailActivity.this.viewModel.loadIfNotLoaded(new Boolean[0]);
        }
    };
    @BindView(2131361995)
    Button joinButton;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    private OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {
        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            ((ChallengesAnalyticsHelper) ChallengeDetailActivity.this.challengesAnalyticsHelper.get()).reportDetailsTabSelectedEvent(ChallengeDetailActivity.this.viewModel.getChallengeTitle(), ChallengeDetailActivity.this.viewModel.getTabTitlesForChallenge(), i, ChallengeDetailActivity.this.viewModel.hasUserJoinedChallenge(), ChallengeDetailActivity.this.viewModel.hasChallengeEnded(), ChallengeDetailActivity.this.viewModel.getChallengeId());
            ChallengeDetailActivity.this.viewModel.setCurrentTab(i);
        }
    };
    /* access modifiers changed from: private */
    public String source;
    @BindView(2131363840)
    TabLayout tabLayout;
    /* access modifiers changed from: private */
    public ChallengeViewModel viewModel;
    @BindView(2131364192)
    ViewPager viewPager;

    public String getAnalyticsScreenTag() {
        return Screens.SINGLE_CHALLENGE;
    }

    public static Intent newStartIntent(Context context, String str, String str2) {
        return new Intent(context, ChallengeDetailActivity.class).putExtra("challenge_id", str).putExtra("source", str2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.single_challenge_activity);
        component().inject(this);
        Intent intent = getIntent();
        this.challengeId = ExtrasUtils.getString(intent, "challenge_id");
        this.source = ExtrasUtils.getString(intent, "source", "other");
        ButterKnife.bind((Activity) this);
        MaterialUtils.removeDefaultToolbarElevation(this);
        MaterialUtils.enableAppBarScrollIfLollipop(this);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        initViewModel();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 174 && i2 == -1) {
            reloadViewModel();
        } else {
            super.onActivityResult(i, i2, intent);
        }
    }

    private void onShareClicked() {
        if (this.viewModel.isLoaded()) {
            ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportChallengeShared(this.challengeId, this.viewModel.getChallengeTitle(), this.viewModel.getTabTitlesForChallenge(), this.viewPager.getCurrentItem(), this.viewModel.hasUserJoinedChallenge(), this.viewModel.hasChallengeEnded());
            Intent intent = new Intent();
            intent.setAction("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.TEXT", ChallengesUtil.getSocialTextWithDispatchUrl(this.viewModel.getChallengeSocialData().getTwitterInvite()));
            intent.setType("text/plain");
            startActivity(Intent.createChooser(intent, getResources().getText(R.string.share)));
        }
    }

    private void initViewModel() {
        this.viewModel = (ChallengeViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (ChallengeViewModel) setViewModel(new ChallengeViewModel(getRunner(), this, this.challengeId, this.challengesService));
        }
        this.viewModel.refresh();
        if (this.viewModel.getState() == State.Loaded) {
            updateUi();
        }
        setBusy(this.viewModel.isBusy());
    }

    private void reloadViewModel() {
        ChallengeViewModel challengeViewModel = this.viewModel;
        if (challengeViewModel != null) {
            challengeViewModel.clearCacheAndLoad();
        }
    }

    private void setupButtonState() {
        ChallengeViewModel challengeViewModel = this.viewModel;
        boolean z = challengeViewModel != null && !challengeViewModel.hasUserJoinedChallenge() && !this.viewModel.hasChallengeEnded();
        ViewUtils.setVisible(z, this.joinButton);
        if (!z || !this.viewModel.isEmailOptInOption()) {
            ViewUtils.setGone(this.cbEmailConsent);
        } else {
            ViewUtils.setVisible(this.cbEmailConsent);
            this.cbEmailConsent.setText(this.viewModel.getEmailOptInText());
        }
        this.joinButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((ChallengesAnalyticsHelper) ChallengeDetailActivity.this.challengesAnalyticsHelper.get()).reportChallengeJoinEvent(ChallengeDetailActivity.this.source, ChallengeDetailActivity.this.viewModel.getChallengeTitle(), ChallengeDetailActivity.this.viewModel.getTabTitlesForChallenge(), ChallengeDetailActivity.this.viewPager.getCurrentItem(), ChallengeDetailActivity.this.viewModel.getChallengeId(), true, ChallengeDetailActivity.this.cbEmailConsent.isChecked());
                ChallengeDetailActivity.this.handleJoinFlow();
            }
        });
        MaterialUtils.setFixedFooterScrollingBehavior(this, z);
    }

    /* access modifiers changed from: private */
    public void handleJoinFlow() {
        if (!this.viewModel.isChallengePrivate() || this.viewModel.isEmailOptInOption()) {
            new ExecuteJoinChallengeTask(this.challengesService, this.challengeId, this.cbEmailConsent.isChecked()).run(getRunner());
        } else {
            new ExecuteJoinChallengeTask(this.challengesService, this.challengeId, false).run(getRunner());
        }
    }

    @Subscribe
    public void onExecuteJoinChallengeEvent(CompletedEvent completedEvent) {
        setBusy(false);
        if (ChallengesUtil.didUserJoinSuccessfully(completedEvent, getRunner())) {
            ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportChallengeJoined(this.viewModel.getChallengeTitle(), this.challengeId, ChallengesUtil.getChallengeDetailsTabNameForUnjoined(this.viewModel.getTabTitlesForChallenge(), this.viewPager.getCurrentItem()), this.source, this.cbEmailConsent.isChecked());
            getNavigationHelper().withIntent(JoinChallengeActivity.newStartIntent(this, this.challengeId, this.viewModel.getChallengeTitle(), this.viewModel.getFriendUserIdsInChallenge(), this.viewModel.getChallengeSocialData(), this.source)).startActivity(RequestCodes.JOIN_CHALLENGE);
        } else if (ChallengesUtil.hasUserAlreadyJoinedChallenge((ApiException) completedEvent.getFailure())) {
            reloadJoinedState();
        } else {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getString(R.string.error), getString(R.string.join_challenge_error), getString(R.string.ok));
        }
    }

    private void reloadJoinedState() {
        ((LocalSettingsService) this.localSettingsService.get()).updateJoinedChallengesCountBy(1);
        reloadViewModel();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        ChallengeViewModel challengeViewModel = this.viewModel;
        if (challengeViewModel != null && !challengeViewModel.hasChallengeEnded()) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.sendBtn).setIcon(R.drawable.ic_share_white_24dp), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        onShareClicked();
        return true;
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.CHALLENGE_ALL_TABS) {
            updateUi();
        } else if (i != Property.LOAD_STATE) {
            getNavigationHelper().withIntent(ChallengesActivity.newStartIntent(this)).finishActivityAfterNavigation().startActivity(RequestCodes.CHALLENGES);
        } else if (this.viewModel.getState() == State.Error) {
            getMessageBus().post(new AlertEvent(getString(R.string.failed_to_load_app_data)));
        }
        setBusy(this.viewModel.isBusy());
    }

    private void updateUi() {
        if (this.viewModel.isChallengeNull()) {
            reloadViewModel();
        } else if (!this.viewModel.hasChallengeEnded() || this.viewModel.hasUserJoinedChallenge()) {
            setTitle(this.viewModel.getChallengeTitle());
            recreateAdapter();
            setupButtonState();
            this.viewPager.setCurrentItem(this.viewModel.getCurrentSelectedTab());
            reportChallengeScreenViewedEvent();
            invalidateOptionsMenu();
        } else {
            getNavigationHelper().finishActivityAfterNavigation().withIntent(ChallengesActivity.newStartIntent(this)).startActivity(RequestCodes.CHALLENGES);
        }
    }

    private void reportChallengeScreenViewedEvent() {
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportChallengeScreenViewed(this.source, this.viewModel.getChallengeTitle(), this.challengeId, this.viewModel.hasUserJoinedChallenge(), this.viewModel.hasChallengeEnded());
    }

    /* access modifiers changed from: private */
    public void recreateAdapter() {
        PagerAdapter pagerAdapter;
        ViewPager viewPager2 = this.viewPager;
        if (this.viewModel.hasUserJoinedChallenge()) {
            pagerAdapter = new JoinedChallengePagerAdapter(this.viewModel, getSupportFragmentManager());
        } else {
            pagerAdapter = new UnjoinedChallengePagerAdapter(this.viewModel, getSupportFragmentManager());
        }
        viewPager2.setAdapter(pagerAdapter);
        this.tabLayout.setupWithViewPager(this.viewPager);
        this.viewPager.addOnPageChangeListener(this.onPageChangeListener);
    }

    public void onChildViewModelReset(ViewModelComponent viewModelComponent) {
        this.childViewModelResetDebouncer.call();
    }

    public void onExternalWebViewLoaded() {
        if (this.viewPager != null) {
            ChallengeViewModel challengeViewModel = this.viewModel;
            if (challengeViewModel != null && challengeViewModel.isExternalWebDisplayed()) {
                this.viewPager.setCurrentItem(this.viewModel.getPreviousSelectedTab());
                this.viewModel.setExternalWebDisplayed(false);
            }
        }
    }
}
