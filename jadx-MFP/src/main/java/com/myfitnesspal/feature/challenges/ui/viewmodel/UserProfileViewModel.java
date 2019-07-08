package com.myfitnesspal.feature.challenges.ui.viewmodel;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.AvailableAchievementCriteria;
import com.myfitnesspal.feature.challenges.model.ChallengeAvailableAchievement;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.model.EarnedAchievement;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.service.FetchFriendProfileTask;
import com.myfitnesspal.feature.challenges.ui.adapter.AchievementListItem;
import com.myfitnesspal.feature.challenges.ui.adapter.AchievementListItemWithTitle;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.ui.fragment.Refreshable;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserProfileViewModel extends RunnerViewModel<Void> implements Refreshable {
    private Lazy<ChallengesService> challengesService;
    private Context context;
    private List<AchievementListItem> userEarnedAchievementsList = new ArrayList();
    private String userId;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int USER_ACHIEVEMENT_LIST = ViewModelPropertyId.next();
    }

    public UserProfileViewModel(Context context2, Runner runner, String str, Lazy<ChallengesService> lazy) {
        super(runner);
        this.context = context2.getApplicationContext();
        this.userId = str;
        this.challengesService = lazy;
    }

    public void load(Void... voidArr) {
        load(false);
    }

    public void refresh() {
        load(true);
    }

    private void load(boolean z) {
        if (getState() != State.Loading) {
            setState(State.Loading);
            new FetchFriendProfileTask(this.challengesService, this.userId).run(getRunner(), z);
        }
    }

    public boolean isBusy() {
        return getState() == State.Loading;
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner()) && FetchFriendProfileTask.matches(taskDetails)) {
            if (taskDetails.getFailure() == null) {
                setState(State.Loaded);
                constructList((List) taskDetails.getResult());
                return;
            }
            setState(State.NotLoaded);
            Ln.e(taskDetails.getFailure());
            setError(taskDetails.getFailure());
        }
    }

    private void constructList(List<ChallengeSummary> list) {
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            for (ChallengeSummary challengeSummary : list) {
                AchievementListItemWithTitle achievementListItemWithTitle = new AchievementListItemWithTitle(challengeSummary.getTitle(), getTimeStatus(challengeSummary.getEndAt()), isChallengeActive(challengeSummary.getEndAt()));
                List earnedAchievementsWithIconImage = getEarnedAchievementsWithIconImage(challengeSummary);
                if (CollectionUtils.notEmpty((Collection<?>) earnedAchievementsWithIconImage)) {
                    this.userEarnedAchievementsList.add(achievementListItemWithTitle);
                    this.userEarnedAchievementsList.addAll(earnedAchievementsWithIconImage);
                }
            }
        }
        notifyPropertyChanged(Property.USER_ACHIEVEMENT_LIST);
    }

    private List<ChallengeAchievement> getEarnedAchievementsWithIconImage(ChallengeSummary challengeSummary) {
        ArrayList arrayList = new ArrayList();
        Map achievementsMap = ChallengesUtil.getAchievementsMap(challengeSummary.getAvailableAchievements());
        for (EarnedAchievement availableAchievementId : challengeSummary.getParticipant().getEarnedAchievements()) {
            ChallengeAvailableAchievement challengeAvailableAchievement = (ChallengeAvailableAchievement) achievementsMap.get(availableAchievementId.getAvailableAchievementId());
            if (challengeAvailableAchievement != null && CollectionUtils.notEmpty((Collection<?>) challengeAvailableAchievement.getCriteria())) {
                AvailableAchievementCriteria availableAchievementCriteria = (AvailableAchievementCriteria) challengeAvailableAchievement.getCriteria().get(0);
                int minimumAmount = availableAchievementCriteria.getMinimumAmount();
                String title = challengeAvailableAchievement.getTitle();
                String description = challengeAvailableAchievement.getDescription();
                String url = challengeAvailableAchievement.getEarnedIconImage().getUrl();
                String quantityString = this.context.getResources().getQuantityString(ChallengesUtil.getStringResourceIdBasedOnCriteriaTypeAndValue(availableAchievementCriteria.getType()), minimumAmount, new Object[]{Integer.valueOf(minimumAmount)});
                String emailBodyShare = challengeAvailableAchievement.getEmailBodyShare();
                String emailSubjectShare = challengeAvailableAchievement.getEmailSubjectShare();
                String twitterShare = challengeAvailableAchievement.getTwitterShare();
                String detailsUrl = challengeAvailableAchievement.getDetailsUrl();
                ChallengeAchievement challengeAchievement = r6;
                ChallengeAchievement challengeAchievement2 = new ChallengeAchievement(title, description, url, true, minimumAmount, minimumAmount, quantityString, emailBodyShare, emailSubjectShare, twitterShare, detailsUrl);
                arrayList.add(challengeAchievement);
            }
        }
        return arrayList;
    }

    public List<AchievementListItem> getUserEarnedAchievementsList() {
        return this.userEarnedAchievementsList;
    }

    private String getTimeStatus(String str) {
        Date date = new Date();
        Date parse = DateTimeUtils.parse(ChallengesUtil.newIso8601DateTimeFormatWithTimezone().toPattern(), str);
        if (date.getTime() > parse.getTime()) {
            return this.context.getResources().getString(R.string.challenge_ended, new Object[]{DateTimeUtils.getDateStringFromDate(parse, Challenges.ENDED_CHALLENGE_DATE_FORMAT)});
        }
        long numberOfDaysBetween = DateTimeUtils.getNumberOfDaysBetween(DateTimeUtils.getCalendarFromDate(date), DateTimeUtils.getCalendarFromDate(parse));
        int i = (int) numberOfDaysBetween;
        return this.context.getResources().getQuantityString(R.plurals.challenge_ends_in_format, i, new Object[]{Integer.valueOf(i)});
    }

    private boolean isChallengeActive(String str) {
        return new Date().getTime() < DateTimeUtils.parse(ChallengesUtil.newIso8601DateTimeFormatWithTimezone().toPattern(), str).getTime();
    }
}
