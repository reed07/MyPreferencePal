package com.myfitnesspal.feature.challenges.ui.viewmodel;

import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ChallengesListViewModel extends ChallengesListViewModelBase {
    private int activeChallengesCount;
    private int activeItemTitleIndex;
    private int endedItemTitleIndex;
    private boolean hasActiveChallenges;
    private boolean hasEndedChallenges;
    private boolean hasNewChallenges;
    private int newChallengesCount;

    public int getEmptyStateStringId() {
        return R.string.empty_new_challenges;
    }

    /* access modifiers changed from: protected */
    public String getListType() {
        return "all";
    }

    public ChallengesListViewModel(Runner runner, Lazy<ChallengesService> lazy) {
        super(runner, lazy);
    }

    public int getIndexForEndedTitleItem() {
        return this.endedItemTitleIndex;
    }

    public boolean hasActiveChallenges() {
        return this.hasActiveChallenges;
    }

    public int getActiveChallengesCount() {
        if (CollectionUtils.isEmpty((Collection<?>) getChallengesList())) {
            return 0;
        }
        return this.activeChallengesCount;
    }

    public boolean hasEndedChallenges() {
        return this.hasEndedChallenges;
    }

    public int getActiveItemTitleIndex() {
        return this.activeItemTitleIndex;
    }

    public boolean hasNewChallenges() {
        return this.hasNewChallenges;
    }

    public int getNewChallengesCount() {
        return this.newChallengesCount;
    }

    /* access modifiers changed from: protected */
    public void updateChallengeList(List<ChallengeSummary> list) {
        filterStartedAndEndedChallenges(filterChallengesWithAchievements(list));
    }

    private void filterStartedAndEndedChallenges(List<ChallengeSummary> list) {
        int i;
        int i2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        Iterator it = list.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            ChallengeSummary challengeSummary = (ChallengeSummary) it.next();
            boolean isChallengeStatusStarted = ChallengesUtil.isChallengeStatusStarted(challengeSummary.getStatus());
            if (isChallengeStatusStarted && challengeSummary.getParticipant() != null) {
                i = 1;
            }
            if (i != 0) {
                arrayList2.add(challengeSummary);
            } else if (isChallengeStatusStarted) {
                arrayList.add(challengeSummary);
            } else {
                arrayList3.add(challengeSummary);
            }
        }
        this.newChallengesCount = CollectionUtils.size((Collection<?>) arrayList);
        this.hasNewChallenges = this.newChallengesCount > 0;
        this.activeChallengesCount = CollectionUtils.size((Collection<?>) arrayList2);
        this.hasActiveChallenges = this.activeChallengesCount > 0;
        this.hasEndedChallenges = CollectionUtils.size((Collection<?>) arrayList3) > 0;
        this.activeItemTitleIndex = this.hasNewChallenges ? this.newChallengesCount + 1 : 0;
        if (this.hasActiveChallenges) {
            i2 = this.activeItemTitleIndex + this.activeChallengesCount;
        } else {
            if (this.hasNewChallenges) {
                i2 = this.newChallengesCount;
            }
            this.endedItemTitleIndex = i;
            sortStartedChallengedByTime(arrayList2);
            List createSummaryItemList = createSummaryItemList(arrayList, "new_challenge");
            createSummaryItemList.addAll(createSummaryItemList(arrayList2, "joined_challenge"));
            createSummaryItemList.addAll(createEndedItemList(arrayList3, ChallengesAnalyticsHelper.LIST_PAST));
            setListAndNotifyPropertyChange(createSummaryItemList);
        }
        i = i2 + 1;
        this.endedItemTitleIndex = i;
        sortStartedChallengedByTime(arrayList2);
        List createSummaryItemList2 = createSummaryItemList(arrayList, "new_challenge");
        createSummaryItemList2.addAll(createSummaryItemList(arrayList2, "joined_challenge"));
        createSummaryItemList2.addAll(createEndedItemList(arrayList3, ChallengesAnalyticsHelper.LIST_PAST));
        setListAndNotifyPropertyChange(createSummaryItemList2);
    }

    private void sortStartedChallengedByTime(List<ChallengeSummary> list) {
        Collections.sort(list, new Comparator<ChallengeSummary>() {
            public int compare(ChallengeSummary challengeSummary, ChallengeSummary challengeSummary2) {
                int i = (ChallengesUtil.getTimeRemainingInMillis(challengeSummary.getEndAt()) > ChallengesUtil.getTimeRemainingInMillis(challengeSummary2.getEndAt()) ? 1 : (ChallengesUtil.getTimeRemainingInMillis(challengeSummary.getEndAt()) == ChallengesUtil.getTimeRemainingInMillis(challengeSummary2.getEndAt()) ? 0 : -1));
                if (i > 0) {
                    return 1;
                }
                return i == 0 ? 0 : -1;
            }
        });
    }
}
