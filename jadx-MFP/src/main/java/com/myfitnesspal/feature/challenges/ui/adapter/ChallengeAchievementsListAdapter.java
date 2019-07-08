package com.myfitnesspal.feature.challenges.ui.adapter;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.util.AchievementAdapterType;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.List;

public class ChallengeAchievementsListAdapter extends BaseAdapter {
    private AchievementAdapterType achievementAdapterType;
    private List<ChallengeAchievement> achievementList;
    private Context context;
    private Lazy<ImageService> imageService;
    private LayoutInflater inflater;
    private int layoutId;
    private boolean showProgress;

    private static class ViewHolder {
        private TextView description;
        private MfpImageView imageView;
        private TextView name;
        private ProgressBar progress;
        private View progressContainer;
        private TextView progression;
        private TextView progressionType;
        private View separator;
        private TextView target;

        public ViewHolder(View view) {
            this.name = (TextView) ViewUtils.findById(view, R.id.tvPrizeName);
            this.description = (TextView) ViewUtils.findById(view, R.id.tvPrizeDescription);
            this.imageView = (MfpImageView) ViewUtils.findById(view, R.id.ivPrize);
            this.separator = ViewUtils.findById(view, R.id.separator);
            this.progress = (ProgressBar) ViewUtils.findById(view, R.id.pbProgress);
            this.progressContainer = ViewUtils.findById(view, R.id.progressContainer);
            this.progression = (TextView) ViewUtils.findById(view, R.id.tvProgression);
            this.progressionType = (TextView) ViewUtils.findById(view, R.id.tvProgressionType);
            this.target = (TextView) ViewUtils.findById(view, R.id.tvTarget);
        }

        /* access modifiers changed from: private */
        public void bind(Context context, Lazy<ImageService> lazy, ChallengeAchievement challengeAchievement, boolean z, AchievementAdapterType achievementAdapterType) {
            ViewUtils.setVisible(true, this.separator);
            ChallengesUtil.setImageToImageView(context, new ChallengeImageOutput(challengeAchievement.getImageUrl()), this.imageView, lazy);
            this.name.setText(challengeAchievement.getTitle());
            if (achievementAdapterType == AchievementAdapterType.Joined) {
                this.description.setMaxLines(1);
                this.description.setEllipsize(TruncateAt.END);
            }
            this.description.setText(challengeAchievement.getDescription());
            if (challengeAchievement.getProgress() == challengeAchievement.getProgressMax()) {
                ViewUtils.setGone(this.progressContainer);
            } else {
                ViewUtils.setVisible(z, this.progressContainer);
            }
            this.progress.setMax(challengeAchievement.getProgressMax());
            this.progress.setProgress(challengeAchievement.getProgress());
            this.progression.setText(Strings.toString(Integer.valueOf(challengeAchievement.getProgress())));
            this.target.setText(Strings.toString(Integer.valueOf(challengeAchievement.getProgressMax())));
            this.progressionType.setText(challengeAchievement.getCriteriaType());
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ChallengeAchievementsListAdapter(LayoutInflater layoutInflater, List<ChallengeAchievement> list, int i, Lazy<ImageService> lazy, Context context2, boolean z, AchievementAdapterType achievementAdapterType2) {
        this.inflater = layoutInflater;
        this.achievementList = list;
        this.layoutId = i;
        this.imageService = lazy;
        this.context = context2;
        this.showProgress = z;
        this.achievementAdapterType = achievementAdapterType2;
    }

    public int getCount() {
        return this.achievementList.size();
    }

    public Object getItem(int i) {
        return this.achievementList.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = this.inflater.inflate(this.layoutId, viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            ButterKnife.bind((Object) viewHolder, view);
            view.setTag(viewHolder);
        }
        ((ViewHolder) view.getTag()).bind(this.context, this.imageService, (ChallengeAchievement) this.achievementList.get(i), this.showProgress, this.achievementAdapterType);
        return view;
    }
}
