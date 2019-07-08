package com.myfitnesspal.feature.home.ui.viewmodel;

import com.myfitnesspal.feature.achievementinterstitialad.service.AchievementAdManager;
import com.myfitnesspal.feature.achievementinterstitialad.ui.OnShowAdListener;
import com.myfitnesspal.feature.home.model.NewsFeedRequestData;
import com.myfitnesspal.feature.home.model.NewsFeedRequestData.FetchType;
import com.myfitnesspal.feature.home.task.FetchNewsFeedTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.api.MfpApiUtil;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntry;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryListContainer;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import java.util.Collection;
import java.util.List;

public class NewsFeedViewModel extends RunnerViewModel<FetchType> {
    public static final int ITEMS_PER_PAGE = 20;
    private static final int MAX_CARD_TO_FETCH = 100;
    private Lazy<AchievementAdManager> achievementAdManager;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Lazy<ConfigService> configService;
    private boolean fetchingNewsFeedItems;
    private MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer;
    private final Lazy<NewsFeedService> newsFeedService;
    private NewsFeedType newsFeedType;
    private String nextUrl;
    private OnShowAdListener onShowAdListener;
    private boolean reachedEndOfTimeline;
    private String userId;

    public enum NewsFeedType {
        Profile,
        Home
    }

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int NEWS_FEED_FETCHED = ViewModelPropertyId.next();
        public static final int NEWS_FEED_FETCH_FAILED = ViewModelPropertyId.next();
    }

    static /* synthetic */ void lambda$interstitialAdShowed$2() throws Exception {
    }

    static /* synthetic */ void lambda$interstitialAdShowed$3(Throwable th) throws Exception {
    }

    static /* synthetic */ void lambda$onFragmentCreated$1(Throwable th) throws Exception {
    }

    static /* synthetic */ void lambda$requestInterstitialAd$5(Throwable th) throws Exception {
    }

    public NewsFeedViewModel(Runner runner, Lazy<NewsFeedService> lazy, String str, NewsFeedType newsFeedType2, Lazy<ConfigService> lazy2, Lazy<AchievementAdManager> lazy3) {
        super(runner);
        this.newsFeedService = lazy;
        this.userId = str;
        this.newsFeedType = newsFeedType2;
        this.configService = lazy2;
        this.achievementAdManager = lazy3;
        initDataFromCache();
    }

    private void initDataFromCache() {
        this.mfpNewsFeedActivityEntryListContainer = getCachedTimelineBasedOnNewsfeedType();
        this.nextUrl = getNextUrlFromLink();
    }

    private MfpNewsFeedActivityEntryListContainer getCachedTimelineBasedOnNewsfeedType() {
        if (this.newsFeedType == NewsFeedType.Home) {
            return ((NewsFeedService) this.newsFeedService.get()).getCachedHomeNewsFeed();
        }
        return ((NewsFeedService) this.newsFeedService.get()).getUserTimelineCachedFeed(this.userId);
    }

    public void load(FetchType... fetchTypeArr) {
        String str;
        this.fetchingNewsFeedItems = true;
        FetchType fetchType = fetchTypeArr[0];
        if (fetchType == null) {
            throw new IllegalArgumentException("Need to specify a FetchType for the request");
        } else if (fetchType != FetchType.OlderItems || !this.reachedEndOfTimeline) {
            switch (fetchType) {
                case NewItems:
                    this.reachedEndOfTimeline = false;
                    str = Uri.ACTIVITY_TIMELINE;
                    break;
                case OlderItems:
                    if (!Strings.isEmpty(this.nextUrl)) {
                        str = this.nextUrl;
                        break;
                    } else {
                        String str2 = Uri.ACTIVITY_TIMELINE;
                        str = str2;
                        fetchType = FetchType.NewItems;
                        break;
                    }
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Unhandled FetchType: ");
                    sb.append(fetchType);
                    throw new IllegalArgumentException(sb.toString());
            }
            new FetchNewsFeedTask(this.newsFeedService, new NewsFeedRequestData(str, 20, fetchType, this.userId), this.newsFeedType).run(getRunner());
        } else {
            this.fetchingNewsFeedItems = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.matches(getRunner(), FetchNewsFeedTask.class)) {
            if (!taskDetails.successful()) {
                setError(taskDetails.getFailure());
                notifyPropertyChanged(Property.NEWS_FEED_FETCH_FAILED);
            } else {
                setState(State.Loaded);
                this.mfpNewsFeedActivityEntryListContainer = (MfpNewsFeedActivityEntryListContainer) taskDetails.getResult();
                this.nextUrl = getNextUrlFromLink();
                this.reachedEndOfTimeline = Strings.isEmpty(this.nextUrl) || CollectionUtils.size((Collection<?>) this.mfpNewsFeedActivityEntryListContainer.getEntries()) >= 100;
                notifyPropertyChanged(Property.NEWS_FEED_FETCHED);
            }
            this.fetchingNewsFeedItems = false;
        }
    }

    private String getNextUrlFromLink() {
        MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer2 = this.mfpNewsFeedActivityEntryListContainer;
        if (mfpNewsFeedActivityEntryListContainer2 == null) {
            return null;
        }
        return Strings.toString(MfpApiUtil.getNextUrlFromLink(mfpNewsFeedActivityEntryListContainer2.getLink()));
    }

    public List<MfpNewsFeedActivityEntry> getNewsFeedEntries() {
        MfpNewsFeedActivityEntryListContainer mfpNewsFeedActivityEntryListContainer2 = this.mfpNewsFeedActivityEntryListContainer;
        if (mfpNewsFeedActivityEntryListContainer2 == null) {
            return null;
        }
        return mfpNewsFeedActivityEntryListContainer2.getEntries();
    }

    public boolean isFetchingNewsFeedItems() {
        return this.fetchingNewsFeedItems;
    }

    public boolean hasReachedEndOfTimeline() {
        return this.reachedEndOfTimeline;
    }

    public void setOnShowAdListener(OnShowAdListener onShowAdListener2) {
        this.onShowAdListener = onShowAdListener2;
    }

    public void onFragmentCreated() {
        if (checkIsNeedToShowInterstitialAdForThisUser()) {
            this.compositeDisposable.add(((AchievementAdManager) this.achievementAdManager.get()).checkIsNeedToShowAchievementAd().subscribe(new Consumer() {
                public final void accept(Object obj) {
                    NewsFeedViewModel.lambda$onFragmentCreated$0(NewsFeedViewModel.this, (Boolean) obj);
                }
            }, $$Lambda$NewsFeedViewModel$ceoCcwcbv1KVCc5wFeMG6YjPWc.INSTANCE));
        }
    }

    public static /* synthetic */ void lambda$onFragmentCreated$0(NewsFeedViewModel newsFeedViewModel, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            newsFeedViewModel.requestInterstitialAd();
        }
    }

    public void interstitialAdShowed() {
        this.compositeDisposable.add(((AchievementAdManager) this.achievementAdManager.get()).achievementAdShowed().subscribe($$Lambda$NewsFeedViewModel$A4tURch27RB5pfnl2f8dNjzJ454.INSTANCE, $$Lambda$NewsFeedViewModel$DmSL58_18rWzLhDk9OAC2df8EO8.INSTANCE));
    }

    public void onDestroyView() {
        this.compositeDisposable.dispose();
    }

    private boolean checkIsNeedToShowInterstitialAdForThisUser() {
        return ConfigUtils.isStreakAdInterstitialEnabled((ConfigService) this.configService.get());
    }

    private void requestInterstitialAd() {
        this.compositeDisposable.add(((AchievementAdManager) this.achievementAdManager.get()).preloadInterstitialAd().subscribe(new Consumer() {
            public final void accept(Object obj) {
                NewsFeedViewModel.lambda$requestInterstitialAd$4(NewsFeedViewModel.this, (Boolean) obj);
            }
        }, $$Lambda$NewsFeedViewModel$OfF0hxi2BrRgAvkAcXYrF6eP19c.INSTANCE));
    }

    public static /* synthetic */ void lambda$requestInterstitialAd$4(NewsFeedViewModel newsFeedViewModel, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            OnShowAdListener onShowAdListener2 = newsFeedViewModel.onShowAdListener;
            if (onShowAdListener2 != null) {
                onShowAdListener2.showInterstitialAd();
            }
        }
    }
}
