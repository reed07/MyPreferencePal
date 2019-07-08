package com.myfitnesspal.feature.challenges.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.ListView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeParticipantsWithBadgesListAdapter;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeFriendsAndAchievementsViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeViewModel.Property;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.DeviceInfo;
import dagger.Lazy;
import javax.inject.Inject;

public class ChallengeFriendListActivity extends MfpActivity {
    private static final String ERROR_DIALOG_TAG = "ChallengeFriendListActivity.ErrorDialog";
    private String challengeId;
    private String challengeName;
    @Inject
    Lazy<ChallengesService> challengeService;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @Inject
    Lazy<DeviceInfo> deviceInfo;
    @Inject
    Lazy<ImageService> imageService;
    @BindView(16908298)
    ListView listView;
    private DialogPositiveListener onDialogOkListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            ChallengeFriendListActivity.this.finish();
        }
    };
    private ChallengeViewModel viewModel;

    public static Intent newStartIntent(Context context, String str, String str2) {
        return new Intent(context, ChallengeFriendListActivity.class).putExtra("challenge_id", str).putExtra("challenge_name", str2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.challenges_friends_list_activity);
        component().inject(this);
        Bundle extras = getIntent().getExtras();
        this.challengeId = BundleUtils.getString(extras, "challenge_id");
        this.challengeName = BundleUtils.getString(extras, "challenge_name");
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportViewAllFriendsEvent(this.challengeName, this.challengeId);
        initViewModel();
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        super.onViewModelPropertyChanged(observable, i);
        if (i == Property.CHALLENGE_ALL_TABS) {
            updateUi();
        } else if (i == Property.LOAD_STATE && this.viewModel.getState() == State.Error) {
            showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) new AlertDialogFragment().setTitle(R.string.error)).setMessage((int) R.string.failed_to_load_app_data)).setPositiveText(R.string.ok, this.onDialogOkListener), ERROR_DIALOG_TAG);
        }
        setBusy(this.viewModel.isBusy());
    }

    private void updateUi() {
        ChallengeFriendsAndAchievementsViewModel challengeFriendsAndAchievementsViewModel = this.viewModel.getChallengeFriendsAndAchievementsViewModel();
        setTitle(getResources().getString(R.string.friends_in_challenge));
        ListView listView2 = this.listView;
        ChallengeParticipantsWithBadgesListAdapter challengeParticipantsWithBadgesListAdapter = new ChallengeParticipantsWithBadgesListAdapter(LayoutInflater.from(this), challengeFriendsAndAchievementsViewModel.getFriendsViewModel().getFriends(), challengeFriendsAndAchievementsViewModel.getAchievementsViewModel(), challengeFriendsAndAchievementsViewModel.getMainAchievements(), R.layout.challenge_participant_with_badges_item, this.imageService, this, getNavigationHelper(), this.challengesAnalyticsHelper, (DeviceInfo) this.deviceInfo.get(), getSession().getUser().getUsername(), true);
        listView2.setAdapter(challengeParticipantsWithBadgesListAdapter);
    }

    private void initViewModel() {
        this.viewModel = (ChallengeViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (ChallengeViewModel) setViewModel(new ChallengeViewModel(getRunner(), this, this.challengeId, this.challengeService));
        }
        this.viewModel.loadIfNotLoaded(new Boolean[0]);
        if (this.viewModel.getState() == State.Loaded) {
            updateUi();
        }
        setBusy(this.viewModel.isBusy());
        rebindView();
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!ERROR_DIALOG_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onDialogOkListener);
        return true;
    }
}
