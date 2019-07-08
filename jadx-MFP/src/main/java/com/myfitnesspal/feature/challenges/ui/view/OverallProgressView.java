package com.myfitnesspal.feature.challenges.ui.view;

import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeAchievementActivity;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.feature.challenges.ui.viewmodel.JoinedProgressViewModel;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class OverallProgressView {
    private Lazy<ImageService> imageService;
    private NavigationHelper navigationHelper;
    private final JoinedProgressViewModel viewModel;

    static class ViewHolder {
        @BindView(2131362854)
        MfpImageView ivAchievement;
        View parentView;
        @BindView(2131363252)
        ProgressBar pbProgress;
        @BindView(2131363889)
        TextView tvAchievementDesc;
        @BindView(2131363890)
        TextView tvAchievementName;
        @BindView(2131363931)
        TextView tvProgression;
        @BindView(2131363932)
        TextView tvProgressionType;
        @BindView(2131363941)
        TextView tvTarget;

        public ViewHolder(View view) {
            this.parentView = view;
            ButterKnife.bind((Object) this, view);
        }

        public void bind(Context context, Lazy<ImageService> lazy, final ChallengeAchievement challengeAchievement, final NavigationHelper navigationHelper, final JoinedProgressViewModel joinedProgressViewModel) {
            this.tvAchievementName.setText(challengeAchievement.getTitle());
            this.tvAchievementDesc.setText(challengeAchievement.getDescription());
            this.tvProgression.setText(Strings.toString(Integer.valueOf(challengeAchievement.getProgress())));
            this.tvProgressionType.setText(challengeAchievement.getCriteriaType());
            this.tvTarget.setText(Strings.toString(Integer.valueOf(challengeAchievement.getProgressMax())));
            this.pbProgress.setMax(challengeAchievement.getProgressMax());
            this.pbProgress.setProgress(challengeAchievement.getProgress());
            this.parentView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    NavigationHelper navigationHelper = navigationHelper;
                    navigationHelper.withIntent(ChallengeAchievementActivity.newStartIntent(navigationHelper.getContext(), joinedProgressViewModel.isChallengePrivate(), true, joinedProgressViewModel.getChallengeId(), joinedProgressViewModel.getChallengeName(), ChallengesAnalyticsHelper.ACHIEVEMENT_LIST_SOURCE, challengeAchievement)).startActivity();
                }
            });
            ChallengesUtil.setImageToImageView(context, new ChallengeImageOutput(challengeAchievement.getImageUrl()), this.ivAchievement, lazy);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.ivAchievement = (MfpImageView) Utils.findRequiredViewAsType(view, R.id.ivAchievement, "field 'ivAchievement'", MfpImageView.class);
            viewHolder.tvAchievementName = (TextView) Utils.findRequiredViewAsType(view, R.id.tvAchievementName, "field 'tvAchievementName'", TextView.class);
            viewHolder.tvAchievementDesc = (TextView) Utils.findRequiredViewAsType(view, R.id.tvAchievementDesc, "field 'tvAchievementDesc'", TextView.class);
            viewHolder.tvProgression = (TextView) Utils.findRequiredViewAsType(view, R.id.tvProgression, "field 'tvProgression'", TextView.class);
            viewHolder.tvProgressionType = (TextView) Utils.findRequiredViewAsType(view, R.id.tvProgressionType, "field 'tvProgressionType'", TextView.class);
            viewHolder.tvTarget = (TextView) Utils.findRequiredViewAsType(view, R.id.tvTarget, "field 'tvTarget'", TextView.class);
            viewHolder.pbProgress = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.pbOverallProgress, "field 'pbProgress'", ProgressBar.class);
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder != null) {
                this.target = null;
                viewHolder.ivAchievement = null;
                viewHolder.tvAchievementName = null;
                viewHolder.tvAchievementDesc = null;
                viewHolder.tvProgression = null;
                viewHolder.tvProgressionType = null;
                viewHolder.tvTarget = null;
                viewHolder.pbProgress = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public OverallProgressView(JoinedProgressViewModel joinedProgressViewModel, Lazy<ImageService> lazy, NavigationHelper navigationHelper2) {
        this.viewModel = joinedProgressViewModel;
        this.imageService = lazy;
        this.navigationHelper = navigationHelper2;
    }

    public View init(Context context) {
        LayoutInflater from = LayoutInflater.from(context);
        ViewGroup viewGroup = (ViewGroup) from.inflate(R.layout.challenge_overall_progress, null, false);
        ViewGroup viewGroup2 = (ViewGroup) ViewUtils.findById(viewGroup, R.id.flOverallProgressContainer);
        for (ChallengeAchievement inflateProgressAndAdd : this.viewModel.getOverallProgressList()) {
            viewGroup2.addView(inflateProgressAndAdd(context, from, viewGroup2, inflateProgressAndAdd, this.viewModel));
        }
        return viewGroup;
    }

    private View inflateProgressAndAdd(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup, ChallengeAchievement challengeAchievement, JoinedProgressViewModel joinedProgressViewModel) {
        View inflate = layoutInflater.inflate(R.layout.overall_progress_item, viewGroup, false);
        new ViewHolder(inflate).bind(context, this.imageService, challengeAchievement, this.navigationHelper, joinedProgressViewModel);
        return inflate;
    }
}
