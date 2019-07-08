package com.myfitnesspal.shared.ui.activity.impl.deeplink.mixin;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import com.myfitnesspal.feature.challenges.model.Challenge;
import com.myfitnesspal.feature.challenges.model.ChallengeAvailableAchievement;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.service.FetchChallengeByIdTask;
import com.myfitnesspal.feature.challenges.service.FetchChallengeByIdTask.CompletedEvent;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeAchievementActivity;
import com.myfitnesspal.feature.challenges.ui.activity.ChallengeDetailActivity;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class ChallengesMixin extends DeepLinkMixinBase {
    private static final String DEEPLINK = "deeplink";
    private static final String PARAM_SOURCE = "source";
    private String achievementId;
    private String challengeId;
    private String challengeName;
    @Inject
    Lazy<ChallengesService> challengesService;
    private String source;

    public ChallengesMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
        component().inject(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Uri data = getActivity().getIntent().getData();
        String[] split = data.getPath().toString().split("/");
        if (split.length == 3) {
            this.source = data.getQueryParameter("source");
            if (Strings.isEmpty(this.source)) {
                this.source = DEEPLINK;
            }
            getComponentInterface().getNavigationHelper().withIntent(ChallengeDetailActivity.newStartIntent(getActivity(), split[split.length - 1], this.source)).finishActivityAfterNavigation().startActivity();
        } else if (split.length > 3) {
            this.source = data.getQueryParameter("source");
            this.achievementId = split[split.length - 1];
            this.challengeId = split[split.length - 3];
            new FetchChallengeByIdTask(this.challengesService, this.challengeId).run(getRunner());
        }
    }

    @Subscribe
    public void onFetchChallengeByIdComplete(CompletedEvent completedEvent) {
        Challenge challenge = (Challenge) completedEvent.getResult();
        if (challenge != null) {
            this.challengeName = challenge.getTitle();
            ChallengeAvailableAchievement filterAchievementById = filterAchievementById(challenge.getAvailableAchievements(), this.achievementId);
            if (filterAchievementById != null) {
                List criteria = filterAchievementById.getCriteria();
                if (!CollectionUtils.isEmpty((Collection<?>) criteria)) {
                    Activity activity = getComponentInterface().getActivity();
                    boolean isPrivate = challenge.isPrivate();
                    boolean z = challenge.getParticipant() != null;
                    getComponentInterface().getNavigationHelper().withIntent(ChallengeAchievementActivity.newStartIntent(activity, isPrivate, z, this.challengeId, this.challengeName, this.source, ChallengesUtil.getChallengeAchievement(activity, challenge, z, filterAchievementById, criteria))).finishActivityAfterNavigation().startActivity();
                }
            }
        }
    }

    private ChallengeAvailableAchievement filterAchievementById(List<ChallengeAvailableAchievement> list, final String str) {
        List list2 = (List) Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, ChallengeAvailableAchievement>() {
            public Boolean execute(ChallengeAvailableAchievement challengeAvailableAchievement) throws RuntimeException {
                return Boolean.valueOf(Strings.equals(challengeAvailableAchievement.getId(), str));
            }
        });
        if (CollectionUtils.notEmpty((Collection<?>) list2)) {
            return (ChallengeAvailableAchievement) list2.get(0);
        }
        return null;
    }
}
