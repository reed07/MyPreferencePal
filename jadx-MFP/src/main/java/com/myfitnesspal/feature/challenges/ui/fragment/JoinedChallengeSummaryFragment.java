package com.myfitnesspal.feature.challenges.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengesActivity;
import com.myfitnesspal.feature.challenges.ui.view.AchievementsView;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeDetailsSummaryView;
import com.myfitnesspal.feature.challenges.ui.view.OverallProgressView;
import com.myfitnesspal.feature.challenges.ui.viewmodel.JoinedSummaryViewModel;
import com.myfitnesspal.feature.challenges.util.AchievementAdapterType;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Collection;
import javax.inject.Inject;

public class JoinedChallengeSummaryFragment extends ChallengeTabFragmentBase {
    private static final String LEAVE_DIALOG_TAG = "JoinedChallengeSummaryFragment.LeaveDialog";
    @Nullable
    @BindView(2131362605)
    FrameLayout achievementsContainer;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @Inject
    Lazy<ChallengesService> challengesService;
    /* access modifiers changed from: private */
    public CompositeDisposable disposable;
    @Inject
    Lazy<ImageService> imageService;
    @BindView(2131361996)
    TextView leaveChallenge;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    private DialogPositiveListener onDialogOkListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            ((ChallengesAnalyticsHelper) JoinedChallengeSummaryFragment.this.challengesAnalyticsHelper.get()).reportChallengeLeaveEvent(JoinedChallengeSummaryFragment.this.viewModel.getChallengeName(), JoinedChallengeSummaryFragment.this.viewModel.getChallengeId());
            JoinedChallengeSummaryFragment.this.disposable.add(((ChallengesService) JoinedChallengeSummaryFragment.this.challengesService.get()).leaveChallenge(JoinedChallengeSummaryFragment.this.viewModel.getChallengeId(), JoinedChallengeSummaryFragment.this.viewModel.getParticipantId()).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() {
                public final void run() {
                    AnonymousClass1.lambda$onClick$0(AnonymousClass1.this);
                }
            }, new Consumer() {
                public final void accept(Object obj) {
                    AnonymousClass1.lambda$onClick$1(AnonymousClass1.this, (Throwable) obj);
                }
            }));
        }

        public static /* synthetic */ void lambda$onClick$0(AnonymousClass1 r2) throws Exception {
            ((LocalSettingsService) JoinedChallengeSummaryFragment.this.localSettingsService.get()).updateJoinedChallengesCountBy(-1);
            FragmentActivity activity = JoinedChallengeSummaryFragment.this.getActivity();
            if (activity != null) {
                JoinedChallengeSummaryFragment.this.getNavigationHelper().withIntent(ChallengesActivity.newStartIntent(activity)).withClearTopAndNewTask().finishActivityAfterNavigation().startActivity(RequestCodes.CHALLENGES);
            }
        }

        public static /* synthetic */ void lambda$onClick$1(AnonymousClass1 r2, Throwable th) throws Exception {
            Ln.e(th);
            JoinedChallengeSummaryFragment joinedChallengeSummaryFragment = JoinedChallengeSummaryFragment.this;
            joinedChallengeSummaryFragment.postEvent(new AlertEvent(joinedChallengeSummaryFragment.getString(R.string.generic_error_msg)));
        }
    };
    @BindView(2131362609)
    FrameLayout summaryContainer;
    /* access modifiers changed from: private */
    public JoinedSummaryViewModel viewModel;

    static /* synthetic */ void lambda$confirmLeavingChallenge$2() {
    }

    static /* synthetic */ void lambda$onRebindDialogFragment$0() {
    }

    public static JoinedChallengeSummaryFragment newInstance(JoinedSummaryViewModel joinedSummaryViewModel) {
        JoinedChallengeSummaryFragment joinedChallengeSummaryFragment = new JoinedChallengeSummaryFragment();
        joinedChallengeSummaryFragment.setViewModel(joinedSummaryViewModel);
        return joinedChallengeSummaryFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.disposable = new CompositeDisposable();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.joined_summary_layout, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        updateUi();
    }

    public void onDestroy() {
        super.onDestroy();
        this.disposable.dispose();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!LEAVE_DIALOG_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        AlertDialogFragment alertDialogFragment = (AlertDialogFragment) dialogFragment;
        alertDialogFragment.setPositiveListener(this.onDialogOkListener);
        alertDialogFragment.setNegativeListener($$Lambda$JoinedChallengeSummaryFragment$wEqqcoQsPd3c9iHJ4IyB8iz8rn4.INSTANCE);
        return true;
    }

    private void updateUi() {
        this.viewModel = (JoinedSummaryViewModel) getViewModel();
        JoinedSummaryViewModel joinedSummaryViewModel = this.viewModel;
        if (joinedSummaryViewModel != null) {
            new ChallengeDetailsSummaryView(joinedSummaryViewModel.getSummaryViewModel(), this.imageService).addView(getActivity(), this.summaryContainer, new OverallProgressView(this.viewModel.getProgressViewModel(), this.imageService, getNavigationHelper()).init(getActivity()));
            if (CollectionUtils.notEmpty((Collection<?>) this.viewModel.getAchievementList())) {
                ViewUtils.setVisible(true, this.achievementsContainer);
                AchievementsView achievementsView = new AchievementsView(this.viewModel.getAchievementList(), this.imageService, getNavigationHelper(), this.viewModel.getChallengeName(), this.viewModel.getChallengeId(), this.viewModel.isChallengePrivate(), AchievementAdapterType.Joined);
                achievementsView.addView(getActivity(), this.achievementsContainer, true);
            }
            this.leaveChallenge.setOnClickListener(new OnClickListener() {
                public final void onClick(View view) {
                    JoinedChallengeSummaryFragment.this.confirmLeavingChallenge();
                }
            });
            ViewUtils.setVisible(this.viewModel.isChallengeActive(), this.leaveChallenge);
        }
    }

    /* access modifiers changed from: private */
    public void confirmLeavingChallenge() {
        showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.leave_challenge_title)).setMessage((int) R.string.leave_challenge_msg)).setPositiveText(R.string.leave_challenge, this.onDialogOkListener)).setNegativeText(R.string.cancel, $$Lambda$JoinedChallengeSummaryFragment$8X7QRG_ZZtDSerU0S3ZDBm2veqo.INSTANCE), LEAVE_DIALOG_TAG);
    }
}
