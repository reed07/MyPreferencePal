package com.myfitnesspal.feature.challenges.util;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.AvailableAchievementCriteria;
import com.myfitnesspal.feature.challenges.model.AvailableAchievementProgress;
import com.myfitnesspal.feature.challenges.model.Challenge;
import com.myfitnesspal.feature.challenges.model.ChallengeAvailableAchievement;
import com.myfitnesspal.feature.challenges.model.ChallengeImageOutput;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.model.EarnedAchievement;
import com.myfitnesspal.feature.challenges.service.ExecuteJoinChallengeTask.CompletedEvent;
import com.myfitnesspal.feature.challenges.ui.adapter.ChallengeAchievement;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.constants.Constants.ABTest.ChallengesAndroid201510;
import com.myfitnesspal.shared.constants.Constants.ABTest.ChallengesEmailPrefAndroid201510;
import com.myfitnesspal.shared.constants.Constants.ABTest.ChallengesNewsFeedCardsAndroid201510;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.GlideUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public final class ChallengesUtil {
    public static final String CHALLENGES_DISPATCH_URL = "http://www.myfitnesspal.com/dispatch/mobile/challenges";
    private static final String FEMALE = "female";
    private static final String MALE = "male";
    private static final String PDF_EXTENSION = ".pdf";
    /* access modifiers changed from: private */
    public static final Map<String, Integer> criteriaTypeMap = new HashMap();
    private static final Map<String, Integer> criteriaTypeValueBasedMap = new HashMap();
    private static final Map<String, Integer> vanityMetricTitleMap = new HashMap();

    public enum ChallengeSocialType {
        Invites,
        Share
    }

    public static final class CriteriaType {
        public static final String BREAKFAST = "breakfast_logged_minimum_amount";
        public static final String CUPS_WATER = "cups_water_logged_minimum_amount";
        public static final String DAILY_FIBER = "daily_fiber_logged_minimum_amount";
        public static final String DAILY_PROTEIN = "daily_protein_logged_minimum_amount";
        public static final String DINNER = "dinner_logged_minimum_amount";
        public static final String EXERCISE_ENTERIES = "exercise_entries_logged_minimum_amount";
        public static final String EXERCISE_ENTERIES_WITH_OBJECT_IDS = "exercise_entries_logged_minimum_amount_with_object_ids";
        public static final String EXERCISE_ENTERIES_WITH_PATTERN_MATCH = "exercise_entries_logged_minimum_amount_with_pattern_match";
        public static final String FIBER = "fiber_logged_minimum_amount";
        public static final String FOOD_ENTERIES = "food_entries_logged_minimum_amount";
        public static final String FOOD_ENTERIES_WITH_OBJECT_IDS = "food_entries_logged_minimum_amount_with_object_ids";
        public static final String LUNCH = "lunch_logged_minimum_amount";
        public static final String MEALS = "meals_logged_minimum_amount";
        public static final String NUTRIENT_GOAL = "nutrient_goal_reached_minimum_amount";
        public static final String PROTEIN = "protein_logged_minimum_amount";
        public static final String RECIPES_LOGGED = "recipes_logged_minimum_amount";
        public static final String RECIPES_LOGGED_WITH_OBJECT_IDS = "recipes_logged_minimum_amount_with_object_ids";
        public static final String STEPS = "steps_logged_minimum_amount";
    }

    public static final class VanityMetricType {
        public static final String BREAKFAST = "breakfast_logged_count";
        public static final String CALORIES = "calories_burned_count";
        public static final String DAILY_FIBER = "daily_fiber_logged_count";
        public static final String DAILY_PROTEIN = "daily_protein_logged_count";
        public static final String DINNER = "dinner_logged_count";
        public static final String EXERCISE = "exercise_entries_logged_count";
        public static final String EXERCISE_WITH_OBJECT_IDS = "exercise_entries_logged_count_with_object_ids";
        public static final String EXERCISE_WITH_PATTERN_MATCH = "exercise_entries_logged_count_with_pattern_match";
        public static final String FIBER = "fiber_logged_count";
        public static final String FOOD = "food_entries_logged_count";
        public static final String FOOD_WITH_OBJECT_IDS = "food_entries_logged_count_with_object_ids";
        public static final String LUNCH = "lunch_logged_count";
        public static final String MEALS = "meals_logged_count";
        public static final String NUTRIENT_GOALS = "nutrient_goals_achieved_count";
        public static final String PROTEIN = "protein_logged_count";
        public static final String RECIPES = "recipes_logged_count";
        public static final String STEPS = "steps_count";
        public static final String WATER = "waters_logged_count";
    }

    public static String getChallengesListTabName(int i) {
        return i != 0 ? Challenges.CHALLENGE_TYPE_MY : "new_challenge";
    }

    public static String getEmailSharedString(boolean z) {
        return z ? "enabled" : "disabled";
    }

    public static String getGenderString(int i) {
        return i == 1 ? MALE : FEMALE;
    }

    public static final SimpleDateFormat newIso8601DateTimeFormatWithTimezone() {
        return new SimpleDateFormat(DateTimeUtils.FORMAT_ISO8601, Locale.ENGLISH);
    }

    public static String getChallengeDetailsTabName(List<String> list, int i, boolean z) {
        if (z) {
            return getChallengeDetailsTabNameForJoined(list, i);
        }
        return getChallengeDetailsTabNameForUnjoined(list, i);
    }

    static {
        criteriaTypeValueBasedMap.put(CriteriaType.FOOD_ENTERIES, Integer.valueOf(R.plurals.progress_food));
        criteriaTypeValueBasedMap.put(CriteriaType.FOOD_ENTERIES_WITH_OBJECT_IDS, Integer.valueOf(R.plurals.progress_food));
        criteriaTypeValueBasedMap.put(CriteriaType.MEALS, Integer.valueOf(R.plurals.progress_meals));
        criteriaTypeValueBasedMap.put(CriteriaType.EXERCISE_ENTERIES, Integer.valueOf(R.plurals.progress_exercise));
        criteriaTypeValueBasedMap.put(CriteriaType.EXERCISE_ENTERIES_WITH_OBJECT_IDS, Integer.valueOf(R.plurals.progress_exercise));
        criteriaTypeValueBasedMap.put(CriteriaType.EXERCISE_ENTERIES_WITH_PATTERN_MATCH, Integer.valueOf(R.plurals.progress_exercise));
        criteriaTypeValueBasedMap.put(CriteriaType.CUPS_WATER, Integer.valueOf(R.plurals.progress_water));
        criteriaTypeValueBasedMap.put(CriteriaType.STEPS, Integer.valueOf(R.plurals.progress_steps));
        criteriaTypeValueBasedMap.put(CriteriaType.NUTRIENT_GOAL, Integer.valueOf(R.plurals.progress_nutrient_goals));
        criteriaTypeValueBasedMap.put(CriteriaType.RECIPES_LOGGED, Integer.valueOf(R.plurals.progress_recipes));
        criteriaTypeValueBasedMap.put(CriteriaType.RECIPES_LOGGED_WITH_OBJECT_IDS, Integer.valueOf(R.plurals.progress_recipes));
        criteriaTypeValueBasedMap.put(CriteriaType.PROTEIN, Integer.valueOf(R.plurals.progress_protein));
        criteriaTypeValueBasedMap.put(CriteriaType.DAILY_PROTEIN, Integer.valueOf(R.plurals.progress_daily_protein));
        criteriaTypeValueBasedMap.put(CriteriaType.FIBER, Integer.valueOf(R.plurals.progress_fiber));
        criteriaTypeValueBasedMap.put(CriteriaType.DAILY_FIBER, Integer.valueOf(R.plurals.progress_daily_fiber));
        criteriaTypeValueBasedMap.put(CriteriaType.BREAKFAST, Integer.valueOf(R.plurals.progress_breakfast));
        criteriaTypeValueBasedMap.put(CriteriaType.LUNCH, Integer.valueOf(R.plurals.progress_lunch));
        criteriaTypeValueBasedMap.put(CriteriaType.DINNER, Integer.valueOf(R.plurals.progress_dinner));
        criteriaTypeMap.put(CriteriaType.FOOD_ENTERIES, Integer.valueOf(R.string.progress_food));
        criteriaTypeMap.put(CriteriaType.FOOD_ENTERIES_WITH_OBJECT_IDS, Integer.valueOf(R.string.progress_food));
        criteriaTypeMap.put(CriteriaType.MEALS, Integer.valueOf(R.string.progress_meals));
        criteriaTypeMap.put(CriteriaType.EXERCISE_ENTERIES, Integer.valueOf(R.string.progress_exercise));
        criteriaTypeMap.put(CriteriaType.EXERCISE_ENTERIES_WITH_OBJECT_IDS, Integer.valueOf(R.string.progress_exercise));
        criteriaTypeMap.put(CriteriaType.EXERCISE_ENTERIES_WITH_PATTERN_MATCH, Integer.valueOf(R.string.progress_exercise));
        criteriaTypeMap.put(CriteriaType.CUPS_WATER, Integer.valueOf(R.string.progress_water));
        criteriaTypeMap.put(CriteriaType.STEPS, Integer.valueOf(R.string.progress_steps));
        criteriaTypeMap.put(CriteriaType.NUTRIENT_GOAL, Integer.valueOf(R.string.progress_nutrient_goals));
        criteriaTypeMap.put(CriteriaType.RECIPES_LOGGED, Integer.valueOf(R.string.progress_recipes));
        criteriaTypeMap.put(CriteriaType.RECIPES_LOGGED_WITH_OBJECT_IDS, Integer.valueOf(R.string.progress_recipes));
        criteriaTypeMap.put(CriteriaType.PROTEIN, Integer.valueOf(R.string.progress_protein));
        criteriaTypeMap.put(CriteriaType.DAILY_PROTEIN, Integer.valueOf(R.string.progress_daily_protein));
        criteriaTypeMap.put(CriteriaType.FIBER, Integer.valueOf(R.string.progress_fiber));
        criteriaTypeMap.put(CriteriaType.DAILY_FIBER, Integer.valueOf(R.string.progress_daily_fiber));
        criteriaTypeMap.put(CriteriaType.BREAKFAST, Integer.valueOf(R.string.progress_breakfast));
        criteriaTypeMap.put(CriteriaType.LUNCH, Integer.valueOf(R.string.progress_lunch));
        criteriaTypeMap.put(CriteriaType.DINNER, Integer.valueOf(R.string.progress_dinner));
        vanityMetricTitleMap.put(VanityMetricType.EXERCISE, Integer.valueOf(R.string.vanity_exercise));
        vanityMetricTitleMap.put(VanityMetricType.EXERCISE_WITH_OBJECT_IDS, Integer.valueOf(R.string.vanity_exercise));
        vanityMetricTitleMap.put(VanityMetricType.EXERCISE_WITH_PATTERN_MATCH, Integer.valueOf(R.string.vanity_exercise));
        vanityMetricTitleMap.put(VanityMetricType.CALORIES, Integer.valueOf(R.string.vanity_calories));
        vanityMetricTitleMap.put(VanityMetricType.FOOD, Integer.valueOf(R.string.vanity_foods));
        vanityMetricTitleMap.put(VanityMetricType.FOOD_WITH_OBJECT_IDS, Integer.valueOf(R.string.vanity_foods));
        vanityMetricTitleMap.put(VanityMetricType.MEALS, Integer.valueOf(R.string.vanity_meals));
        vanityMetricTitleMap.put(VanityMetricType.NUTRIENT_GOALS, Integer.valueOf(R.string.vanity_nutrient_goals));
        vanityMetricTitleMap.put(VanityMetricType.STEPS, Integer.valueOf(R.string.vanity_steps));
        vanityMetricTitleMap.put(VanityMetricType.WATER, Integer.valueOf(R.string.vanity_water));
        vanityMetricTitleMap.put(VanityMetricType.RECIPES, Integer.valueOf(R.string.vanity_recipes));
        vanityMetricTitleMap.put(VanityMetricType.PROTEIN, Integer.valueOf(R.string.vanity_protein));
        vanityMetricTitleMap.put(VanityMetricType.DAILY_PROTEIN, Integer.valueOf(R.string.vanity_daily_protein));
        vanityMetricTitleMap.put(VanityMetricType.FIBER, Integer.valueOf(R.string.vanity_fiber));
        vanityMetricTitleMap.put(VanityMetricType.DAILY_FIBER, Integer.valueOf(R.string.vanity_daily_fiber));
        vanityMetricTitleMap.put(VanityMetricType.BREAKFAST, Integer.valueOf(R.string.vanity_breakfast));
        vanityMetricTitleMap.put(VanityMetricType.LUNCH, Integer.valueOf(R.string.vanity_lunch));
        vanityMetricTitleMap.put(VanityMetricType.DINNER, Integer.valueOf(R.string.vanity_dinner));
    }

    public static boolean isChallengeStatusStarted(String str) {
        return Strings.equals(str, "started");
    }

    public static boolean isChallengeStatusEnded(String str) {
        return Strings.equals(str, Challenges.CHALLENGE_STATUS_ENDED);
    }

    public static int getStringResourceIdBasedOnCriteriaType(String str) {
        return ((Integer) criteriaTypeMap.get(str)).intValue();
    }

    public static int getStringResourceIdBasedOnCriteriaTypeAndValue(String str) {
        return ((Integer) criteriaTypeValueBasedMap.get(str)).intValue();
    }

    public static int getVanityMetricStringBasedOnMetricType(String str) {
        return ((Integer) vanityMetricTitleMap.get(str)).intValue();
    }

    public static int getAge(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        int i = instance2.get(1) - instance.get(1);
        if (instance2.get(2) < instance.get(2)) {
            return i - 1;
        }
        return (instance2.get(2) != instance.get(2) || instance2.get(5) >= instance.get(5)) ? i : i - 1;
    }

    public static Map<String, ChallengeAvailableAchievement> getAchievementsMap(List<ChallengeAvailableAchievement> list) {
        HashMap hashMap = new HashMap();
        for (ChallengeAvailableAchievement challengeAvailableAchievement : list) {
            hashMap.put(challengeAvailableAchievement.getId(), challengeAvailableAchievement);
        }
        return hashMap;
    }

    public static AvailableAchievementProgress getAchievementCriteriaProgressBasedOnCriteriaId(String str, List<AvailableAchievementProgress> list) {
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            for (AvailableAchievementProgress availableAchievementProgress : list) {
                if (Strings.equalsIgnoreCase(availableAchievementProgress.getAvailableAchievementCriteriaId(), str)) {
                    return availableAchievementProgress;
                }
            }
        }
        return null;
    }

    public static boolean areAchievementsCriteriaValid(List<ChallengeAvailableAchievement> list) {
        return ((ChallengeAvailableAchievement) Enumerable.firstOrDefault(list, new ReturningFunction1<Boolean, ChallengeAvailableAchievement>() {
            public Boolean execute(ChallengeAvailableAchievement challengeAvailableAchievement) throws RuntimeException {
                return Boolean.valueOf(!ChallengesUtil.criteriaTypeMap.containsKey(((AvailableAchievementCriteria) challengeAvailableAchievement.getCriteria().get(0)).getType()));
            }
        })) == null;
    }

    public static boolean isChallengesAvailable(ConfigService configService) {
        return configService.isVariantOnAndCountrySupported(ChallengesAndroid201510.NAME);
    }

    public static boolean isChallengesEmailPrefAvailable(ConfigService configService) {
        return isChallengesAvailable(configService) && configService.isVariantEnabled(ChallengesEmailPrefAndroid201510.NAME);
    }

    public static boolean isChallengesNewsFeedAvailable(ConfigService configService) {
        return configService.isVariantEnabled(ChallengesNewsFeedCardsAndroid201510.NAME);
    }

    public static void setImageToImageView(Context context, ChallengeImageOutput challengeImageOutput, ImageView imageView, Lazy<ImageService> lazy, ProgressBar progressBar) {
        setImageToImageView(context, challengeImageOutput, imageView, lazy, 0, progressBar);
    }

    public static void setImageToImageView(Context context, ChallengeImageOutput challengeImageOutput, ImageView imageView, Lazy<ImageService> lazy) {
        setImageToImageView(context, challengeImageOutput, imageView, lazy, 0, null);
    }

    public static void setImageToImageView(Context context, ChallengeImageOutput challengeImageOutput, ImageView imageView, Lazy<ImageService> lazy, int i) {
        setImageToImageView(context, challengeImageOutput, imageView, lazy, i, null);
    }

    public static void setImageToImageView(Context context, ChallengeImageOutput challengeImageOutput, ImageView imageView, Lazy<ImageService> lazy, int i, ProgressBar progressBar) {
        if (challengeImageOutput != null) {
            loadImageViaGlide(context, challengeImageOutput.getUrl(), i, imageView, progressBar);
        } else if (i != 0) {
            GlideUtil.loadImage(context, null, i, imageView);
        }
    }

    private static void loadImageViaGlide(Context context, String str, int i, ImageView imageView, ProgressBar progressBar) {
        if (i == 0) {
            if (progressBar == null) {
                GlideUtil.loadImage(context, str, imageView);
            } else {
                GlideUtil.loadImageWithProgressSpinner(context, str, imageView, progressBar);
            }
        } else if (progressBar == null) {
            GlideUtil.loadImage(context, str, i, imageView);
        } else {
            GlideUtil.loadImageWithProgressSpinner(context, str, i, imageView, progressBar);
        }
    }

    public static void setNewUnseenChallengesStats(List<ChallengeSummary> list, Lazy<LocalSettingsService> lazy) {
        final long lastSeenNewChallengesDate = ((LocalSettingsService) lazy.get()).getLastSeenNewChallengesDate();
        List list2 = (List) Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, ChallengeSummary>() {
            public Boolean execute(ChallengeSummary challengeSummary) throws RuntimeException {
                return Boolean.valueOf(DateTimeUtils.parse(ChallengesUtil.newIso8601DateTimeFormatWithTimezone().toPattern(), challengeSummary.getStartAt()).getTime() > lastSeenNewChallengesDate);
            }
        });
        ((LocalSettingsService) lazy.get()).setUnseenNewChallenges(((LocalSettingsService) lazy.get()).getUnseenNewChallenges() + CollectionUtils.size((Collection<?>) list2));
        ((LocalSettingsService) lazy.get()).setLastSeenNewChallengesDate(new Date().getTime());
    }

    public static void setTotalJoinedChallengesStats(List<ChallengeSummary> list, Lazy<LocalSettingsService> lazy) {
        ((LocalSettingsService) lazy.get()).setJoinedChallengesCount(CollectionUtils.size((Collection<?>) list));
    }

    public static String getChallengeType(String str, boolean z) {
        if (Strings.equals(str, Challenges.USER_STATUS_ELIGIBLE)) {
            return "new_challenge";
        }
        return z ? Challenges.CHALLENGE_TYPE_EXPIRED : "joined_challenge";
    }

    public static String getChallengeDetailsTabNameForUnjoined(List<String> list, int i) {
        switch (i) {
            case 0:
                return Challenges.CHALLENGE_TAB_DETAILS;
            case 1:
                return Challenges.CHALLENGE_TAB_PRIZES;
            case 2:
                return Challenges.CHALLENGE_TAB_RULES;
            default:
                return (String) list.get(i);
        }
    }

    public static String getChallengeDetailsTabNameForJoined(List<String> list, int i) {
        switch (i) {
            case 0:
                return Challenges.CHALLENGE_TAB_SUMMARY;
            case 1:
                return "friends";
            case 2:
                return Challenges.CHALLENGE_TAB_PRIZES;
            case 3:
                return Challenges.CHALLENGE_TAB_RULES;
            default:
                return (String) list.get(i);
        }
    }

    public static String getTimeRemainingString(Context context, String str) {
        int i;
        long timeRemainingInMillis = getTimeRemainingInMillis(str);
        int convert = (int) TimeUnit.DAYS.convert(timeRemainingInMillis, TimeUnit.MILLISECONDS);
        if (convert > 0) {
            i = R.plurals.days_remaining_format;
        } else {
            convert = (int) TimeUnit.HOURS.convert(timeRemainingInMillis, TimeUnit.MILLISECONDS);
            i = R.plurals.hours_remaining_format;
        }
        return context.getResources().getQuantityString(i, convert, new Object[]{Integer.valueOf(convert)});
    }

    public static long getTimeRemainingInMillis(String str) {
        return DateTimeUtils.getTimeRemaining(DateTimeUtils.parse(newIso8601DateTimeFormatWithTimezone().toPattern(), str));
    }

    public static boolean isSocialTypeInvites(ChallengeSocialType challengeSocialType) {
        return challengeSocialType == ChallengeSocialType.Invites;
    }

    public static boolean isSocialTypeShare(ChallengeSocialType challengeSocialType) {
        return challengeSocialType == ChallengeSocialType.Share;
    }

    public static boolean didUserJoinSuccessfully(CompletedEvent completedEvent, Runner runner) {
        return completedEvent.getRunnerId() == runner.getId() && completedEvent.getFailure() == null;
    }

    public static boolean hasUserAlreadyJoinedChallenge(ApiException apiException) {
        return apiException.getStatusCode() == 422 && Strings.equalsIgnoreCase(apiException.getApiError().getError(), Challenges.ALREADY_JOINED_ERROR);
    }

    public static boolean isUrlForPDF(String str) {
        return Strings.notEmpty(str) && str.endsWith(PDF_EXTENSION);
    }

    public static ChallengeImageOutput getAchievementIcon(boolean z, Challenge challenge, ChallengeAvailableAchievement challengeAvailableAchievement) {
        ChallengeImageOutput challengeImageOutput;
        if (!z) {
            return challengeAvailableAchievement.getEarnedIconImage();
        }
        if (isAchievementEarned(challenge, challengeAvailableAchievement)) {
            challengeImageOutput = challengeAvailableAchievement.getEarnedIconImage();
        } else {
            challengeImageOutput = challengeAvailableAchievement.getUnearnedIconImage();
        }
        return challengeImageOutput;
    }

    public static boolean isAchievementEarned(Challenge challenge, final ChallengeAvailableAchievement challengeAvailableAchievement) {
        if (challenge.getParticipant() != null) {
            return Enumerable.any(challenge.getParticipant().getEarnedAchievements(), new ReturningFunction1<Boolean, EarnedAchievement>() {
                public Boolean execute(EarnedAchievement earnedAchievement) {
                    return Boolean.valueOf(Strings.equalsIgnoreCase(challengeAvailableAchievement.getId(), earnedAchievement.getAvailableAchievementId()));
                }
            });
        }
        return false;
    }

    public static ChallengeAchievement getChallengeAchievement(Context context, Challenge challenge, boolean z, ChallengeAvailableAchievement challengeAvailableAchievement, List<AvailableAchievementCriteria> list) {
        Challenge challenge2 = challenge;
        boolean z2 = z;
        ChallengeAvailableAchievement challengeAvailableAchievement2 = challengeAvailableAchievement;
        AvailableAchievementCriteria availableAchievementCriteria = (AvailableAchievementCriteria) Enumerable.firstOrDefault(list);
        int progressValue = z2 ? getProgressValue(getAchievementProgress(challenge2, availableAchievementCriteria)) : -1;
        ChallengeAchievement challengeAchievement = new ChallengeAchievement(challengeAvailableAchievement.getTitle(), challengeAvailableAchievement.getDescription(), getAchievementIcon(z2, challenge2, challengeAvailableAchievement2).getUrl(), isAchievementEarned(challenge2, challengeAvailableAchievement2), progressValue, z2 ? availableAchievementCriteria.getMinimumAmount() : -1, z2 ? getCriteriaTypeString(context, availableAchievementCriteria, progressValue) : "", challengeAvailableAchievement.getEmailBodyShare(), challengeAvailableAchievement.getEmailSubjectShare(), challengeAvailableAchievement.getTwitterShare(), challengeAvailableAchievement.getDetailsUrl());
        return challengeAchievement;
    }

    public static int getProgressValue(AvailableAchievementProgress availableAchievementProgress) {
        if (availableAchievementProgress != null) {
            return availableAchievementProgress.getValue();
        }
        return 0;
    }

    public static String getCriteriaTypeString(Context context, AvailableAchievementCriteria availableAchievementCriteria, int i) {
        return context.getResources().getQuantityString(getStringResourceIdBasedOnCriteriaTypeAndValue(availableAchievementCriteria.getType()), i);
    }

    public static AvailableAchievementProgress getAchievementProgress(Challenge challenge, AvailableAchievementCriteria availableAchievementCriteria) {
        return getAchievementCriteriaProgressBasedOnCriteriaId(availableAchievementCriteria.getId(), challenge.getParticipant().getProgressions());
    }

    public static String getSocialTextWithDispatchUrl(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(" ");
        sb.append(CHALLENGES_DISPATCH_URL);
        return sb.toString();
    }
}
