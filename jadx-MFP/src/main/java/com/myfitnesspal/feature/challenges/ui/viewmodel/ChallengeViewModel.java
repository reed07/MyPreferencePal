package com.myfitnesspal.feature.challenges.ui.viewmodel;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.AvailableAchievementCriteria;
import com.myfitnesspal.feature.challenges.model.AvailableAchievementProgress;
import com.myfitnesspal.feature.challenges.model.Challenge;
import com.myfitnesspal.feature.challenges.model.ChallengeAvailableAchievement;
import com.myfitnesspal.feature.challenges.model.ChallengeDynamicTab;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.model.ChallengeParticipant;
import com.myfitnesspal.feature.challenges.model.ChallengePrize;
import com.myfitnesspal.feature.challenges.model.ChallengeSocial;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.service.FetchChallengeByIdTask;
import com.myfitnesspal.feature.challenges.service.FetchParticipantsForChallengeTask;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ChallengeViewModel extends RunnerViewModel<Boolean> {
    private static final int MAX_ACHIEVEMENTS_FOR_PREVIEW = 3;
    private String challengeId;
    private boolean challengeInitialized;
    private Lazy<ChallengesService> challengesService;
    private Context context;
    private Challenge currentChallenge;
    private int currentSelectedTab;
    private boolean externalWebDisplayed;
    private State friendLoadState = State.NotLoaded;
    private List<ChallengeParticipant> friendsInChallenge;
    private List<ChallengeAvailableAchievement> mainAchievements;
    private int previousSelectedTab;
    private boolean reloadOnComplete;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int CHALLENGE_ALL_TABS = ViewModelPropertyId.next();
    }

    public ChallengeViewModel(Runner runner, Context context2, String str, Lazy<ChallengesService> lazy) {
        super(runner);
        this.context = context2;
        this.challengeId = str;
        this.challengesService = lazy;
        this.currentSelectedTab = 0;
        this.previousSelectedTab = 0;
        this.externalWebDisplayed = false;
    }

    public void refresh() {
        load(Boolean.valueOf(true));
    }

    public void load(Boolean... boolArr) {
        boolean z = false;
        if (!(boolArr == null || boolArr.length == 0)) {
            z = boolArr[0].booleanValue();
        }
        if (getState() != State.Loading) {
            setState(State.Loading);
            new FetchChallengeByIdTask(this.challengesService, this.challengeId).run(getRunner(), z);
            new FetchParticipantsForChallengeTask(this.challengesService, this.challengeId, "friends").run(getRunner(), z);
        } else if (z) {
            this.reloadOnComplete = true;
        }
    }

    public void clearCacheAndLoad() {
        load(Boolean.valueOf(true));
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner())) {
            if (taskDetails.getFailure() != null) {
                handleError(taskDetails);
            } else if (FetchChallengeByIdTask.matches(taskDetails)) {
                processChallengeObject((Challenge) taskDetails.getResult());
                setState(State.Loaded);
            } else if (FetchParticipantsForChallengeTask.matches(taskDetails)) {
                updateFriendsListState((List) taskDetails.getResult());
                setState(State.Loaded);
            }
            if (loaded() && this.reloadOnComplete) {
                this.reloadOnComplete = false;
                load(Boolean.valueOf(true));
            }
        }
    }

    private boolean loaded() {
        boolean z = this.friendLoadState == State.Loaded || this.friendLoadState == State.Error;
        if (!this.challengeInitialized || !z) {
            return false;
        }
        return true;
    }

    private void handleError(TaskDetails taskDetails) {
        if (FetchParticipantsForChallengeTask.matches(taskDetails)) {
            this.friendLoadState = State.Error;
            notifyIfRequired();
            return;
        }
        setError(taskDetails.getFailure());
    }

    private void updateFriendsListState(List<ChallengeParticipant> list) {
        this.friendsInChallenge = list;
        this.friendLoadState = State.Loaded;
        notifyIfRequired();
    }

    private void processChallengeObject(Challenge challenge) {
        this.currentChallenge = challenge;
        this.challengeInitialized = true;
        notifyIfRequired();
    }

    private void notifyIfRequired() {
        if (loaded()) {
            notifyPropertyChanged(Property.CHALLENGE_ALL_TABS);
        }
    }

    public ChallengeDetailsSummaryViewModel getChallengeSummaryViewModel() {
        ChallengeDetailsSummaryViewModel challengeDetailsSummaryViewModel = new ChallengeDetailsSummaryViewModel(getBannerImage(), this.currentChallenge.getStartAt(), this.currentChallenge.getEndAt(), this.currentChallenge.getParticipantCount(), this.currentChallenge.getMetrics(), isChallengeActive(), getGrandPrize());
        return challengeDetailsSummaryViewModel;
    }

    private ChallengePrize getGrandPrize() {
        List prizes = this.currentChallenge.getPrizes();
        ChallengePrize challengePrize = (ChallengePrize) Enumerable.firstOrDefault(prizes, new ReturningFunction1<Boolean, ChallengePrize>() {
            public Boolean execute(ChallengePrize challengePrize) throws RuntimeException {
                return Boolean.valueOf(challengePrize.isMain());
            }
        });
        return challengePrize != null ? challengePrize : (ChallengePrize) prizes.get(0);
    }

    public ChallengeImageOutput getBannerImage() {
        return this.currentChallenge.getBannerImage();
    }

    public ChallengeDetailsFriendsViewModel getFriendsViewModel() {
        return new ChallengeDetailsFriendsViewModel(this.friendLoadState, this.challengeId, getChallengeTitle(), this.friendsInChallenge);
    }

    public List<ChallengeDynamicTab> getChallengeDynamicTabs() {
        return this.currentChallenge.getDynamicTabs();
    }

    public String getRulesUrl() {
        return this.currentChallenge.getRulesUrl();
    }

    public ChallengePrizesViewModel getPrizesViewModel() {
        return new ChallengePrizesViewModel(this.currentChallenge.getPrizes(), this.currentChallenge.getBannerImage());
    }

    public ChallengeAchievementsViewModel getAchievementsViewModel() {
        return new ChallengeAchievementsViewModel(this.currentChallenge.getAvailableAchievements(), this.currentChallenge.getTitle(), this.currentChallenge.getId());
    }

    public boolean hasUserJoinedChallenge() {
        ChallengeParticipant participant = this.currentChallenge.getParticipant();
        return participant != null && Strings.equals(participant.getStatus(), "joined");
    }

    public void setCurrentTab(int i) {
        int i2 = this.currentSelectedTab;
        if (i2 != i) {
            this.previousSelectedTab = i2;
            this.currentSelectedTab = i;
        }
    }

    public int getCurrentSelectedTab() {
        return this.currentSelectedTab;
    }

    public int getPreviousSelectedTab() {
        return this.previousSelectedTab;
    }

    public void setExternalWebDisplayed(boolean z) {
        this.externalWebDisplayed = z;
    }

    public boolean isExternalWebDisplayed() {
        return this.externalWebDisplayed;
    }

    public boolean isChallengeActive() {
        return ChallengesUtil.isChallengeStatusStarted(this.currentChallenge.getStatus());
    }

    public boolean hasChallengeEnded() {
        Challenge challenge = this.currentChallenge;
        return challenge != null && ChallengesUtil.isChallengeStatusEnded(challenge.getStatus());
    }

    public boolean isChallengePrivate() {
        return this.currentChallenge.isPrivate();
    }

    public List<String> getTabTitlesForChallenge() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.context.getString(R.string.challenge_home));
        if (hasUserJoinedChallenge()) {
            arrayList.add(this.context.getString(R.string.friends));
        }
        if (shouldAddPrizesTab()) {
            arrayList.add(this.context.getString(R.string.challenge_prize));
        }
        arrayList.add(this.context.getString(R.string.challenge_rules));
        if (hasUserJoinedChallenge()) {
            arrayList.addAll(Enumerable.select((Collection<T>) getChallengeDynamicTabs(), (ReturningFunction1<U, T>) new ReturningFunction1<String, ChallengeDynamicTab>() {
                public String execute(ChallengeDynamicTab challengeDynamicTab) {
                    return challengeDynamicTab.getTitle();
                }
            }));
        }
        return arrayList;
    }

    public boolean shouldAddPrizesTab() {
        return !hasUserJoinedChallenge() || CollectionUtils.notEmpty((Collection<?>) this.currentChallenge.getPrizes());
    }

    public boolean isChallengeNull() {
        return this.currentChallenge == null;
    }

    public String getChallengeTitle() {
        return this.currentChallenge.getTitle();
    }

    public String getChallengeSponsor() {
        return this.currentChallenge.getSponsor().getName();
    }

    public String getChallengeId() {
        return this.currentChallenge.getId();
    }

    public String getParticipantId() {
        return this.currentChallenge.getParticipant() != null ? this.currentChallenge.getParticipant().getId() : "";
    }

    public boolean isEmailOptInOption() {
        return this.currentChallenge.getAdmin().isEmailOptInOption();
    }

    public String getEmailOptInText() {
        return this.currentChallenge.getAdmin().getEmailOptInText();
    }

    public ChallengeSocial getChallengeSocialData() {
        return this.currentChallenge.getAdmin().getSocial();
    }

    public String getChallengeGoal() {
        return this.currentChallenge.getChallengeGoal();
    }

    public JoinedProgressViewModel getJoinedProgressViewModel() {
        ArrayList arrayList = new ArrayList();
        for (ChallengeAvailableAchievement challengeAvailableAchievement : getMainAchievements()) {
            List criteria = challengeAvailableAchievement.getCriteria();
            if (!CollectionUtils.isEmpty((Collection<?>) criteria)) {
                AvailableAchievementCriteria availableAchievementCriteria = (AvailableAchievementCriteria) Enumerable.firstOrDefault(criteria);
                AvailableAchievementProgress achievementProgress = ChallengesUtil.getAchievementProgress(this.currentChallenge, availableAchievementCriteria);
                String title = challengeAvailableAchievement.getTitle();
                String description = challengeAvailableAchievement.getDescription();
                String url = ChallengesUtil.getAchievementIcon(true, this.currentChallenge, challengeAvailableAchievement).getUrl();
                boolean isAchievementEarned = ChallengesUtil.isAchievementEarned(this.currentChallenge, challengeAvailableAchievement);
                int progressValue = ChallengesUtil.getProgressValue(achievementProgress);
                int minimumAmount = availableAchievementCriteria.getMinimumAmount();
                String criteriaTypeString = ChallengesUtil.getCriteriaTypeString(this.context, availableAchievementCriteria, achievementProgress.getValue());
                String emailBodyShare = challengeAvailableAchievement.getEmailBodyShare();
                String emailSubjectShare = challengeAvailableAchievement.getEmailSubjectShare();
                String twitterShare = challengeAvailableAchievement.getTwitterShare();
                String detailsUrl = challengeAvailableAchievement.getDetailsUrl();
                ChallengeAchievement challengeAchievement = r6;
                ChallengeAchievement challengeAchievement2 = new ChallengeAchievement(title, description, url, isAchievementEarned, progressValue, minimumAmount, criteriaTypeString, emailBodyShare, emailSubjectShare, twitterShare, detailsUrl);
                arrayList.add(challengeAchievement);
            }
        }
        return new JoinedProgressViewModel(arrayList, getChallengeId(), getChallengeTitle(), isChallengePrivate());
    }

    private void reset(String str) {
        this.challengeId = str;
        this.challengeInitialized = false;
        this.friendLoadState = State.NotLoaded;
        this.currentChallenge = null;
        this.friendsInChallenge = null;
        this.mainAchievements = null;
    }

    private List<ChallengeAvailableAchievement> getMainAchievements() {
        if (CollectionUtils.isEmpty((Collection<?>) this.mainAchievements)) {
            this.mainAchievements = new ArrayList();
            this.mainAchievements.addAll(Enumerable.where((Collection<T>) this.currentChallenge.getAvailableAchievements(), (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, ChallengeAvailableAchievement>() {
                public Boolean execute(ChallengeAvailableAchievement challengeAvailableAchievement) {
                    return Boolean.valueOf(challengeAvailableAchievement.isMain());
                }
            }));
        }
        return this.mainAchievements;
    }

    public ArrayList<String> getFriendUserIdsInChallenge() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (CollectionUtils.notEmpty((Collection<?>) this.friendsInChallenge)) {
            for (ChallengeParticipant user : this.friendsInChallenge) {
                arrayList.add(user.getUser().getUsername());
            }
        }
        return arrayList;
    }

    public ChallengeFriendsAndAchievementsViewModel getChallengeFriendsAndAchievementsViewModel() {
        ChallengeFriendsAndAchievementsViewModel challengeFriendsAndAchievementsViewModel = new ChallengeFriendsAndAchievementsViewModel(getAchievementsViewModel(), getFriendsViewModel(), getMainAchievements(), hasChallengeEnded(), getChallengeSocialData(), this.currentChallenge.getBannerImage());
        return challengeFriendsAndAchievementsViewModel;
    }

    public List<ChallengeAchievement> getChallengeAchievements(boolean z) {
        List<ChallengeAvailableAchievement> list;
        ArrayList arrayList = new ArrayList();
        if (z) {
            list = getNonMainChallengeAvailableAchievements();
        } else {
            list = this.currentChallenge.getAvailableAchievements();
        }
        for (ChallengeAvailableAchievement challengeAvailableAchievement : list) {
            List criteria = challengeAvailableAchievement.getCriteria();
            if (!CollectionUtils.isEmpty((Collection<?>) criteria)) {
                arrayList.add(ChallengesUtil.getChallengeAchievement(this.context, this.currentChallenge, z, challengeAvailableAchievement, criteria));
            }
        }
        return arrayList;
    }

    public List<ChallengeAchievement> getChallengeAchievementsForPreview() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (ChallengeAvailableAchievement challengeAvailableAchievement : this.currentChallenge.getAvailableAchievements()) {
            List criteria = challengeAvailableAchievement.getCriteria();
            if (!CollectionUtils.isEmpty((Collection<?>) criteria)) {
                arrayList.add(ChallengesUtil.getChallengeAchievement(this.context, this.currentChallenge, false, challengeAvailableAchievement, criteria));
                i++;
                if (i >= 3) {
                    break;
                }
            }
        }
        return arrayList;
    }

    private List<ChallengeAvailableAchievement> getNonMainChallengeAvailableAchievements() {
        return (List) Enumerable.where((Collection<T>) this.currentChallenge.getAvailableAchievements(), (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, ChallengeAvailableAchievement>() {
            public Boolean execute(ChallengeAvailableAchievement challengeAvailableAchievement) throws RuntimeException {
                return Boolean.valueOf(!challengeAvailableAchievement.isMain());
            }
        });
    }
}
