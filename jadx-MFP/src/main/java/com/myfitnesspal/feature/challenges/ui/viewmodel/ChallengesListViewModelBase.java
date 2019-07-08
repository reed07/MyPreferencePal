package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.service.FetchChallengeSummaryTask;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeListItem;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeSummaryListItem;
import com.myfitnesspal.feature.challenges.ui.adapter.EndedChallengeListItem;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.ui.fragment.Refreshable;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ChallengesListViewModelBase extends RunnerViewModel<Boolean> implements Refreshable {
    private final List<ChallengeListItem> challengesList = new ArrayList();
    private Lazy<ChallengesService> challengesService;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int CHALLENGE_LIST = ViewModelPropertyId.next();
        public static final int CHALLENGE_LIST_EMPTY = ViewModelPropertyId.next();
    }

    public abstract int getEmptyStateStringId();

    /* access modifiers changed from: protected */
    public abstract String getListType();

    /* access modifiers changed from: protected */
    public abstract void updateChallengeList(List<ChallengeSummary> list);

    public ChallengesListViewModelBase(Runner runner, Lazy<ChallengesService> lazy) {
        super(runner);
        this.challengesService = lazy;
    }

    public void load(Boolean... boolArr) {
        boolean z = false;
        if (!(boolArr == null || boolArr.length == 0)) {
            z = boolArr[0].booleanValue();
        }
        if (getState() != State.Loading) {
            setState(State.Loading);
            new FetchChallengeSummaryTask(this.challengesService, getListType()).run(getRunner(), z);
        }
    }

    public void refresh() {
        load(Boolean.valueOf(true));
    }

    public List<ChallengeListItem> getChallengesList() {
        return this.challengesList;
    }

    public boolean isBusy() {
        return getState() == State.Loading;
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner()) && FetchChallengeSummaryTask.matches(taskDetails)) {
            if (taskDetails.getFailure() == null) {
                processChallengeList((List) taskDetails.getResult());
                setState(State.Loaded);
                return;
            }
            setError(taskDetails.getFailure());
        }
    }

    private void processChallengeList(List<ChallengeSummary> list) {
        if (list == null) {
            return;
        }
        if (list.size() == 0) {
            this.challengesList.clear();
            notifyPropertyChanged(Property.CHALLENGE_LIST);
            notifyPropertyChanged(Property.CHALLENGE_LIST_EMPTY);
            return;
        }
        updateChallengeList(list);
    }

    protected static List<ChallengeSummary> filterChallengesWithAchievements(List<ChallengeSummary> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) $$Lambda$ChallengesListViewModelBase$KPlJMuh82LIkr9MjaYqyQnD66y4.INSTANCE));
        return arrayList;
    }

    static /* synthetic */ Boolean lambda$filterChallengesWithAchievements$0(ChallengeSummary challengeSummary) throws RuntimeException {
        return Boolean.valueOf(CollectionUtils.notEmpty((Collection<?>) challengeSummary.getAvailableAchievements()) && ChallengesUtil.areAchievementsCriteriaValid(challengeSummary.getAvailableAchievements()));
    }

    /* access modifiers changed from: protected */
    public final void setListAndNotifyPropertyChange(List<ChallengeListItem> list) {
        this.challengesList.clear();
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            this.challengesList.addAll(list);
        }
        notifyPropertyChanged(Property.CHALLENGE_LIST);
        if (this.challengesList.size() == 0) {
            notifyPropertyChanged(Property.CHALLENGE_LIST_EMPTY);
        }
    }

    /* access modifiers changed from: protected */
    public final List<ChallengeListItem> createSummaryItemList(List<ChallengeSummary> list, String str) {
        ArrayList arrayList = new ArrayList();
        for (ChallengeSummary challengeSummaryViewModel : list) {
            arrayList.add(new ChallengeSummaryListItem(new ChallengeSummaryViewModel(challengeSummaryViewModel, str)));
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public final List<EndedChallengeListItem> createEndedItemList(List<ChallengeSummary> list, String str) {
        ArrayList arrayList = new ArrayList();
        for (ChallengeSummary challengeSummaryViewModel : list) {
            arrayList.add(new EndedChallengeListItem(new ChallengeSummaryViewModel(challengeSummaryViewModel, str)));
        }
        return arrayList;
    }
}
