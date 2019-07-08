package com.myfitnesspal.feature.challenges.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.ui.adapter.UserProfileAchievementsAdapter;
import com.myfitnesspal.feature.challenges.ui.viewmodel.ChallengeViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.UserProfileViewModel;
import com.myfitnesspal.feature.challenges.ui.viewmodel.UserProfileViewModel.Property;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;

public class UserAchievementsActivity extends MfpActivity {
    private UserProfileAchievementsAdapter adapter;
    @Inject
    Lazy<ChallengesService> challengesService;
    @BindView(2131362496)
    TextView errorMessage;
    @Inject
    Lazy<ImageService> imageService;
    @BindView(2131362875)
    Button joinChallenge;
    @BindView(2131362976)
    ListView listView;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    private String userId;
    private UserProfileViewModel viewModel;

    public static Intent newStartIntent(Context context, String str) {
        return new Intent(context, UserAchievementsActivity.class).putExtra("user_id", str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        getWindow().requestFeature(8);
        super.onCreate(bundle);
        component().inject(this);
        setContentView((int) R.layout.user_achievement_activity);
        ButterKnife.bind((Activity) this);
        this.userId = ExtrasUtils.getString(getIntent(), "user_id");
        init();
    }

    private void init() {
        this.viewModel = (UserProfileViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (UserProfileViewModel) setViewModel(new UserProfileViewModel(this, getRunner(), this.userId, this.challengesService));
        }
        this.viewModel.loadIfNotLoaded(new Void[0]);
        setBusy(this.viewModel.isBusy());
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        rebindView();
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.USER_ACHIEVEMENT_LIST) {
            updateAdapter();
        } else if (i == ChallengeViewModel.Property.LOAD_STATE && this.viewModel.getState() == State.Error) {
            getMessageBus().post(new AlertEvent(getString(R.string.failed_to_load_app_data)));
        }
        setBusy(this.viewModel.isBusy());
    }

    private void updateAdapter() {
        if (this.adapter == null) {
            this.adapter = new UserProfileAchievementsAdapter(getLayoutInflater(), new ArrayList(), this.imageService, this);
            this.listView.setAdapter(this.adapter);
        }
        UserProfileViewModel userProfileViewModel = this.viewModel;
        if (userProfileViewModel != null && userProfileViewModel.isLoadedOrError()) {
            boolean isEmpty = CollectionUtils.isEmpty((Collection<?>) this.viewModel.getUserEarnedAchievementsList());
            boolean equals = Strings.equals(this.userId, getSession().getUser().getUserId());
            ViewUtils.setVisible(!isEmpty, this.listView);
            ViewUtils.setVisible(isEmpty, this.errorMessage);
            ViewUtils.setVisible(!isEmpty, this.listView);
            if (!isEmpty) {
                ListViewUtils.refill(this.adapter, this.viewModel.getUserEarnedAchievementsList());
            } else if (!equals || ((LocalSettingsService) this.localSettingsService.get()).getJoinedChallengesCount() != 0) {
                this.errorMessage.setText(getString(R.string.no_achievements_yet));
            } else {
                this.errorMessage.setText(getString(R.string.no_achievements_join_challenge));
                ViewUtils.setVisible(true, this.joinChallenge);
                this.joinChallenge.setOnClickListener(new OnClickListener() {
                    public void onClick(View view) {
                        UserAchievementsActivity.this.getNavigationHelper().withIntent(ChallengesActivity.newStartIntent(UserAchievementsActivity.this)).startActivity(RequestCodes.CHALLENGES);
                    }
                });
            }
        }
    }
}
