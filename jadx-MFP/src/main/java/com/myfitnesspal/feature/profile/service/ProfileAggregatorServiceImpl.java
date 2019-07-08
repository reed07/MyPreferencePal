package com.myfitnesspal.feature.profile.service;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary;
import com.myfitnesspal.feature.challenges.model.ChallengeSummary.API_RESPONSE_MAPPER;
import com.myfitnesspal.feature.challenges.util.ChallengesUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitStepsUtils;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.feature.externalsync.impl.shealth.util.SHealthUtil;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.OfflineData;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.OnlineData;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.ProfileChallenges;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.ProfileFriend;
import com.myfitnesspal.feature.profile.service.ProfileAggregatorService.ProfileFriends;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Challenges;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.Holder;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import javax.inject.Provider;

public class ProfileAggregatorServiceImpl implements ProfileAggregatorService {
    private static final String URL_KEY_APPS_LIMIT = "apps_limit";
    private static final String URL_KEY_FRIENDS_LIMIT = "friends_limit";
    private static final String URL_KEY_FRIENDS_ORDER = "friends_order";
    /* access modifiers changed from: private */
    public static final Integer URL_VALUE_APPS_LIMIT = Integer.valueOf(3);
    /* access modifiers changed from: private */
    public static final Integer URL_VALUE_FRIENDS_LIMIT = Integer.valueOf(3);
    private static final String URL_VALUE_FRIENDS_ORDER_LAST_LOGIN = "last_login";
    private static final String URL_VALUE_USER_STATUS_ALL = "all";
    /* access modifiers changed from: private */
    public final Provider<MfpV2Api> api;
    /* access modifiers changed from: private */
    public final Lazy<CountryService> countryService;
    private final GoogleFitClient fitClient;
    private final Lazy<NutrientGoalService> goalsService;
    private final SHealthConnection sHealthConnection;
    /* access modifiers changed from: private */
    public final Lazy<Session> session;
    private final Lazy<UserWeightService> userWeightService;

    private static class OfflineDataImpl implements OfflineData {
        private static final MfpDailyGoal DEFAULT_GOAL = new MfpDailyGoal();
        private static final LocalizedWeight DEFAULT_WEIGHT = LocalizedWeight.from(0.0d, Weight.POUNDS);
        private final LocalizedWeight currentWeight;
        private final MfpDailyGoal dailyGoal;
        private final LocalizedWeight goalWeight;
        private final LocalizedWeight startingWeight;
        private final LocalizedWeight weightLost;

        OfflineDataImpl(LocalizedWeight localizedWeight, LocalizedWeight localizedWeight2, LocalizedWeight localizedWeight3, LocalizedWeight localizedWeight4, MfpDailyGoal mfpDailyGoal) {
            this.currentWeight = localizedWeight;
            this.goalWeight = localizedWeight2;
            this.startingWeight = localizedWeight3;
            this.weightLost = localizedWeight4;
            this.dailyGoal = mfpDailyGoal;
        }

        public LocalizedWeight getCurrentWeight() {
            LocalizedWeight localizedWeight = this.currentWeight;
            return localizedWeight == null ? DEFAULT_WEIGHT : localizedWeight;
        }

        public LocalizedWeight getGoalWeight() {
            LocalizedWeight localizedWeight = this.goalWeight;
            return localizedWeight == null ? DEFAULT_WEIGHT : localizedWeight;
        }

        public LocalizedWeight getStartingWeight() {
            LocalizedWeight localizedWeight = this.startingWeight;
            return localizedWeight == null ? DEFAULT_WEIGHT : localizedWeight;
        }

        public LocalizedWeight getWeightDelta() {
            LocalizedWeight localizedWeight = this.weightLost;
            return localizedWeight == null ? DEFAULT_WEIGHT : localizedWeight;
        }

        public MfpDailyGoal getDailyGoal() {
            MfpDailyGoal mfpDailyGoal = this.dailyGoal;
            return mfpDailyGoal == null ? DEFAULT_GOAL : mfpDailyGoal;
        }
    }

    private static class OnlineDataImpl implements OnlineData {
        private List<MfpPlatformApp> apps;
        private ProfileChallenges challenges;
        private ProfileFriends friends;

        OnlineDataImpl() {
        }

        public void setFriends(ProfileFriends profileFriends) {
            this.friends = profileFriends;
        }

        public void setChallenges(ProfileChallenges profileChallenges) {
            this.challenges = profileChallenges;
        }

        public void setApps(List<MfpPlatformApp> list) {
            this.apps = list;
        }

        public ProfileFriends getFriends() {
            return this.friends;
        }

        public ProfileChallenges getChallenges() {
            return this.challenges;
        }

        public List<MfpPlatformApp> getApps() {
            return this.apps;
        }
    }

    private static class ProfileChallengesImpl implements ProfileChallenges {
        @Expose
        List<ChallengeSummary> activeChallenges;
        @Expose
        int newChallengeCount;

        ProfileChallengesImpl(List<ChallengeSummary> list, int i) {
            if (list == null) {
                list = new ArrayList<>();
            }
            this.activeChallenges = list;
            this.newChallengeCount = i;
        }

        public List<ChallengeSummary> getActiveChallenges() {
            return new ArrayList(this.activeChallenges);
        }

        public int getNewChallengeCount() {
            return this.newChallengeCount;
        }
    }

    private static class ProfileFriendImpl implements ProfileFriend {
        @Expose
        String id;
        @Expose
        Location locationPreferences;
        @Expose
        List<Profile> profiles;
        @Expose
        String username;

        private static class Location {
            @Expose
            String city;
            @Expose
            String countryCode;
            @Expose
            String state;

            private Location() {
            }
        }

        private static class Profile {
            @Expose
            String mainImageUrl;
            @Expose
            String type;

            private Profile() {
            }
        }

        private ProfileFriendImpl() {
        }

        public String getId() {
            return this.id;
        }

        public String getUsername() {
            return this.username;
        }

        public String getImageUrl() {
            return this.profiles.size() > 0 ? ((Profile) this.profiles.get(0)).mainImageUrl : "";
        }

        public String getCity() {
            Location location = this.locationPreferences;
            return Strings.toString(location == null ? null : location.city);
        }

        public String getState() {
            Location location = this.locationPreferences;
            return Strings.toString(location == null ? null : location.state);
        }

        public String getCountryCode() {
            Location location = this.locationPreferences;
            return Strings.toString(location == null ? null : location.countryCode);
        }
    }

    private static class ProfileFriendsImpl implements ProfileFriends {
        private final int friendCount;
        private final List<ProfileFriend> friends = new ArrayList();

        ProfileFriendsImpl(List<? extends ProfileFriend> list, int i) {
            if (list != null) {
                this.friends.addAll(list);
            }
            this.friendCount = i;
        }

        public List<ProfileFriend> getFriends() {
            return new ArrayList(this.friends);
        }

        public int getTotalFriendCount() {
            return this.friendCount;
        }
    }

    private static class UserAggregationResponse {
        @Expose
        List<MfpPlatformApp> apps;
        @Expose
        List<ProfileFriendImpl> friends;
        @Expose
        int totalFriends;

        public static class API_RESPONSE_MAPPER extends ApiResponse<UserAggregationResponse> {
        }

        private UserAggregationResponse() {
        }
    }

    public ProfileAggregatorServiceImpl(Lazy<Session> lazy, Provider<MfpV2Api> provider, SHealthConnection sHealthConnection2, GoogleFitClient googleFitClient, Lazy<CountryService> lazy2, Lazy<NutrientGoalService> lazy3, Lazy<UserWeightService> lazy4) {
        this.session = lazy;
        this.api = provider;
        this.sHealthConnection = sHealthConnection2;
        this.fitClient = googleFitClient;
        this.countryService = lazy2;
        this.goalsService = lazy3;
        this.userWeightService = lazy4;
    }

    public OnlineData getOnlineData() throws Throwable {
        final Holder holder = new Holder();
        final OnlineDataImpl onlineDataImpl = new OnlineDataImpl();
        Observable.zip(getFriendsAndAppsInfo(onlineDataImpl), getChallengesInfo(onlineDataImpl), new BiFunction<Boolean, Boolean, OnlineData>() {
            public OnlineData apply(Boolean bool, Boolean bool2) {
                return onlineDataImpl;
            }
        }).subscribeOn(Schedulers.io()).blockingSubscribe(new Consumer<OnlineData>() {
            public void accept(OnlineData onlineData) {
            }
        }, new Consumer<Throwable>() {
            public void accept(Throwable th) {
                holder.setValue(th);
            }
        });
        if (holder.getValue() == null) {
            return onlineDataImpl;
        }
        throw ((Throwable) holder.getValue());
    }

    public OfflineData getOfflineData() {
        LocalizedWeight localizedWeight;
        MfpDailyGoal dailyGoalForDayOfWeekSync = ((NutrientGoalService) this.goalsService.get()).getDailyGoalForDayOfWeekSync(new Date());
        LocalizedWeight fromPounds = LocalizedWeight.fromPounds((double) ((UserWeightService) this.userWeightService.get()).getCurrentWeightInPounds());
        LocalizedWeight fromPounds2 = LocalizedWeight.fromPounds((double) ((UserWeightService) this.userWeightService.get()).getGoalWeightInPounds());
        LocalizedWeight fromPounds3 = LocalizedWeight.fromPounds((double) ((UserWeightService) this.userWeightService.get()).getStartingWeightInPounds());
        LocalizedWeight fromPounds4 = LocalizedWeight.fromPounds(0.0d);
        if (fromPounds2.isGreaterThan(fromPounds3)) {
            if (fromPounds.isGreaterThanOrEqualTo(fromPounds3)) {
                localizedWeight = LocalizedWeight.fromPounds(fromPounds.toPounds() - fromPounds3.toPounds());
                OfflineDataImpl offlineDataImpl = new OfflineDataImpl(fromPounds, fromPounds2, fromPounds3, localizedWeight, dailyGoalForDayOfWeekSync);
                return offlineDataImpl;
            }
        } else if (fromPounds.isLessThanOrEqualTo(fromPounds3)) {
            localizedWeight = LocalizedWeight.fromPounds(fromPounds3.toPounds() - fromPounds.toPounds());
            OfflineDataImpl offlineDataImpl2 = new OfflineDataImpl(fromPounds, fromPounds2, fromPounds3, localizedWeight, dailyGoalForDayOfWeekSync);
            return offlineDataImpl2;
        }
        localizedWeight = fromPounds4;
        OfflineDataImpl offlineDataImpl22 = new OfflineDataImpl(fromPounds, fromPounds2, fromPounds3, localizedWeight, dailyGoalForDayOfWeekSync);
        return offlineDataImpl22;
    }

    private Observable<Boolean> getFriendsAndAppsInfo(final OnlineDataImpl onlineDataImpl) {
        return Observable.fromCallable(new Callable<Boolean>() {
            public Boolean call() {
                try {
                    UserAggregationResponse userAggregationResponse = (UserAggregationResponse) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ProfileAggregatorServiceImpl.this.api.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.FRIENDS_AND_APPS, ProfileAggregatorServiceImpl.URL_KEY_APPS_LIMIT, String.valueOf(ProfileAggregatorServiceImpl.URL_VALUE_APPS_LIMIT), ProfileAggregatorServiceImpl.URL_KEY_FRIENDS_LIMIT, String.valueOf(ProfileAggregatorServiceImpl.URL_VALUE_FRIENDS_LIMIT), ProfileAggregatorServiceImpl.URL_KEY_FRIENDS_ORDER, ProfileAggregatorServiceImpl.URL_VALUE_FRIENDS_ORDER_LAST_LOGIN)).getItem();
                    onlineDataImpl.setApps(ProfileAggregatorServiceImpl.this.mergeAppsWithClientConnections(userAggregationResponse.apps));
                    onlineDataImpl.setFriends(new ProfileFriendsImpl(userAggregationResponse.friends, userAggregationResponse.totalFriends));
                    return Boolean.valueOf(true);
                } catch (ApiException unused) {
                    return Boolean.valueOf(false);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public List<MfpPlatformApp> mergeAppsWithClientConnections(List<MfpPlatformApp> list) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            arrayList.addAll(list);
        }
        if (this.fitClient.isEnabled()) {
            arrayList.add(0, GoogleFitStepsUtils.createMockPlatformApp());
        }
        this.sHealthConnection.connectAndWait();
        if (this.sHealthConnection.isPaired()) {
            arrayList.add(0, SHealthUtil.createMockPlatformApp());
        }
        Collections.sort(arrayList, new Comparator<MfpPlatformApp>() {
            public int compare(MfpPlatformApp mfpPlatformApp, MfpPlatformApp mfpPlatformApp2) {
                return Strings.toString(mfpPlatformApp.getName()).compareTo(Strings.toString(mfpPlatformApp2.getName()));
            }
        });
        return arrayList;
    }

    private Observable<Boolean> getChallengesInfo(final OnlineDataImpl onlineDataImpl) {
        return Observable.fromCallable(new Callable<Boolean>() {
            public Boolean call() {
                try {
                    User user = ((Session) ProfileAggregatorServiceImpl.this.session.get()).getUser();
                    ApiResponse apiResponse = (ApiResponse) ((MfpV2Api) ((MfpV2Api) ProfileAggregatorServiceImpl.this.api.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.CHALLENGES, "user_status", "all", Challenges.USER_GENDER, ChallengesUtil.getGenderString(user.getGender()), Challenges.USER_COUNTRY_CODE, ((CountryService) ProfileAggregatorServiceImpl.this.countryService.get()).getShortNameFromLongName(user.getProfile().getCountryName()), Challenges.USER_AGE, Integer.valueOf(ChallengesUtil.getAge(user.getProfile().getDateOfBirth())));
                    ArrayList arrayList = new ArrayList();
                    int i = 0;
                    for (ChallengeSummary challengeSummary : apiResponse.getItems()) {
                        if (ChallengesUtil.isChallengeStatusStarted(challengeSummary.getStatus())) {
                            if (challengeSummary.getParticipant() == null) {
                                i++;
                            } else {
                                arrayList.add(challengeSummary);
                            }
                        }
                    }
                    onlineDataImpl.setChallenges(new ProfileChallengesImpl(arrayList, i));
                    return Boolean.valueOf(true);
                } catch (Exception unused) {
                    return Boolean.valueOf(false);
                }
            }
        });
    }
}
