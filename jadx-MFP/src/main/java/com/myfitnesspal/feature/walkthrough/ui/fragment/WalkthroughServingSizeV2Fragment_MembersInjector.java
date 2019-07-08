package com.myfitnesspal.feature.walkthrough.ui.fragment;

import com.bumptech.glide.Glide;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.service.BackgroundJobHelper;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment_MembersInjector;
import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import javax.inject.Provider;

public final class WalkthroughServingSizeV2Fragment_MembersInjector implements MembersInjector<WalkthroughServingSizeV2Fragment> {
    private final Provider<ActionTrackingService> actionTrackingServiceProvider;
    private final Provider<BackgroundJobHelper> backgroundServiceHelperProvider;
    private final Provider<DiaryService> diaryServiceProvider;
    private final Provider<FoodMapper> foodMapperProvider;
    private final Provider<FoodService> foodServiceProvider;
    private final Provider<Glide> glideProvider;
    private final Provider<WalkthroughUtil> walkthroughUtilProvider;

    public WalkthroughServingSizeV2Fragment_MembersInjector(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<WalkthroughUtil> provider3, Provider<FoodMapper> provider4, Provider<FoodService> provider5, Provider<DiaryService> provider6, Provider<ActionTrackingService> provider7) {
        this.backgroundServiceHelperProvider = provider;
        this.glideProvider = provider2;
        this.walkthroughUtilProvider = provider3;
        this.foodMapperProvider = provider4;
        this.foodServiceProvider = provider5;
        this.diaryServiceProvider = provider6;
        this.actionTrackingServiceProvider = provider7;
    }

    public static MembersInjector<WalkthroughServingSizeV2Fragment> create(Provider<BackgroundJobHelper> provider, Provider<Glide> provider2, Provider<WalkthroughUtil> provider3, Provider<FoodMapper> provider4, Provider<FoodService> provider5, Provider<DiaryService> provider6, Provider<ActionTrackingService> provider7) {
        WalkthroughServingSizeV2Fragment_MembersInjector walkthroughServingSizeV2Fragment_MembersInjector = new WalkthroughServingSizeV2Fragment_MembersInjector(provider, provider2, provider3, provider4, provider5, provider6, provider7);
        return walkthroughServingSizeV2Fragment_MembersInjector;
    }

    public void injectMembers(WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment) {
        MfpFragment_MembersInjector.injectBackgroundServiceHelper(walkthroughServingSizeV2Fragment, DoubleCheck.lazy(this.backgroundServiceHelperProvider));
        MfpFragment_MembersInjector.injectGlide(walkthroughServingSizeV2Fragment, (Glide) this.glideProvider.get());
        injectWalkthroughUtil(walkthroughServingSizeV2Fragment, (WalkthroughUtil) this.walkthroughUtilProvider.get());
        injectFoodMapper(walkthroughServingSizeV2Fragment, (FoodMapper) this.foodMapperProvider.get());
        injectFoodService(walkthroughServingSizeV2Fragment, DoubleCheck.lazy(this.foodServiceProvider));
        injectDiaryService(walkthroughServingSizeV2Fragment, DoubleCheck.lazy(this.diaryServiceProvider));
        injectActionTrackingService(walkthroughServingSizeV2Fragment, DoubleCheck.lazy(this.actionTrackingServiceProvider));
    }

    public static void injectWalkthroughUtil(WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment, WalkthroughUtil walkthroughUtil) {
        walkthroughServingSizeV2Fragment.walkthroughUtil = walkthroughUtil;
    }

    public static void injectFoodMapper(WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment, FoodMapper foodMapper) {
        walkthroughServingSizeV2Fragment.foodMapper = foodMapper;
    }

    public static void injectFoodService(WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment, Lazy<FoodService> lazy) {
        walkthroughServingSizeV2Fragment.foodService = lazy;
    }

    public static void injectDiaryService(WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment, Lazy<DiaryService> lazy) {
        walkthroughServingSizeV2Fragment.diaryService = lazy;
    }

    public static void injectActionTrackingService(WalkthroughServingSizeV2Fragment walkthroughServingSizeV2Fragment, Lazy<ActionTrackingService> lazy) {
        walkthroughServingSizeV2Fragment.actionTrackingService = lazy;
    }
}
