package com.myfitnesspal.feature.challenges.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfileAchievementsAdapter extends ArrayAdapter<AchievementListItem> {
    private static Map<Class<?>, Integer> CLASS_TO_VIEW_ID_MAP = new HashMap();
    private Context context;
    private Lazy<ImageService> imageService;
    private LayoutInflater inflater;
    private List<AchievementListItem> list;

    private static class AchievementViewHolder implements InternalViewHolder {
        private TextView description;
        private MfpImageView imageView;
        private TextView name;

        public AchievementViewHolder(View view) {
            this.name = (TextView) ViewUtils.findById(view, R.id.tvPrizeName);
            this.description = (TextView) ViewUtils.findById(view, R.id.tvPrizeDescription);
            this.imageView = (MfpImageView) ViewUtils.findById(view, R.id.ivPrize);
        }

        public void bind(Context context, AchievementListItem achievementListItem, Lazy<ImageService> lazy) {
            ChallengeAchievement challengeAchievement = (ChallengeAchievement) achievementListItem;
            ChallengesUtil.setImageToImageView(context, new ChallengeImageOutput(challengeAchievement.getImageUrl()), this.imageView, lazy);
            this.name.setText(challengeAchievement.getTitle());
            this.description.setText(challengeAchievement.getDescription());
        }
    }

    interface InternalViewHolder {
        void bind(Context context, AchievementListItem achievementListItem, Lazy<ImageService> lazy);
    }

    private static class ListTitleViewHolder implements InternalViewHolder {
        private TextView listName;
        private TextView listTimeStatus;
        private View parent;

        protected ListTitleViewHolder(View view) {
            this.parent = view;
            this.listName = (TextView) ViewUtils.findById(view, R.id.tvChallengeListName);
            this.listTimeStatus = (TextView) ViewUtils.findById(view, R.id.tvChallengeTimeStatus);
        }

        public void bind(Context context, AchievementListItem achievementListItem, Lazy<ImageService> lazy) {
            this.parent.setEnabled(false);
            this.parent.setOnClickListener(null);
            AchievementListItemWithTitle achievementListItemWithTitle = (AchievementListItemWithTitle) achievementListItem;
            TextViewUtils.setText(this.listName, achievementListItemWithTitle.getChallengeTitle());
            TextViewUtils.setText(this.listTimeStatus, achievementListItemWithTitle.getTimeStatus());
            if (achievementListItemWithTitle.isChallengeActive()) {
                this.listTimeStatus.setTextColor(context.getResources().getColor(R.color.light_green));
            }
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    static {
        CLASS_TO_VIEW_ID_MAP.put(AchievementListItemWithTitle.class, Integer.valueOf(0));
        CLASS_TO_VIEW_ID_MAP.put(ChallengeAchievement.class, Integer.valueOf(1));
    }

    public UserProfileAchievementsAdapter(LayoutInflater layoutInflater, List<AchievementListItem> list2, Lazy<ImageService> lazy, Context context2) {
        super(context2, -1, list2);
        this.inflater = layoutInflater;
        this.list = list2;
        this.imageService = lazy;
        this.context = context2;
    }

    public int getCount() {
        return this.list.size();
    }

    public AchievementListItem getItem(int i) {
        return (AchievementListItem) this.list.get(i);
    }

    public int getItemViewType(int i) {
        return ListViewUtils.getViewType(this.list, CLASS_TO_VIEW_ID_MAP, i);
    }

    public int getViewTypeCount() {
        return CLASS_TO_VIEW_ID_MAP.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        InternalViewHolder internalViewHolder;
        AchievementListItem achievementListItem = (AchievementListItem) this.list.get(i);
        if (view == null) {
            if (achievementListItem instanceof AchievementListItemWithTitle) {
                view = this.inflater.inflate(R.layout.achievement_list_title_item, viewGroup, false);
                internalViewHolder = new ListTitleViewHolder(view);
            } else if (achievementListItem instanceof ChallengeAchievement) {
                view = this.inflater.inflate(R.layout.challenge_achievement_summary_item, viewGroup, false);
                internalViewHolder = new AchievementViewHolder(view);
            } else {
                throw new RuntimeException("unexpected item type!");
            }
            view.setTag(internalViewHolder);
        } else {
            internalViewHolder = (InternalViewHolder) view.getTag();
        }
        internalViewHolder.bind(this.context, (AchievementListItem) this.list.get(i), this.imageService);
        return view;
    }
}
