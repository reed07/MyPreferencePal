package com.myfitnesspal.feature.search.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.registration.model.Resource;
import com.myfitnesspal.feature.registration.model.Resource.Loading;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.model.SponsoredFood;
import com.myfitnesspal.feature.search.repository.OnlineFoodSearchRepository;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Extras;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Trigger;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Flows;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.myfitnesspal.shared.model.v2.SearchResultItem;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¸\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u0000 {2\u00020\u0001:\u0001{BS\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\t\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0002\u0010\u0013J\b\u0010a\u001a\u00020>H\u0002J\u0016\u0010b\u001a\u00020\u00152\f\u0010c\u001a\b\u0012\u0004\u0012\u00020J0dH\u0002J\u0006\u0010e\u001a\u00020>J\u0016\u0010f\u001a\b\u0012\u0004\u0012\u00020h0g2\u0006\u0010A\u001a\u00020\u0019H\u0002J\u0018\u0010i\u001a\u00020>2\u0006\u0010j\u001a\u0002072\u0006\u0010k\u001a\u00020\u0015H\u0002J\b\u0010l\u001a\u00020>H\u0014J\u0012\u0010m\u001a\u00020>2\b\u0010%\u001a\u0004\u0018\u00010$H\u0002J\u0018\u0010n\u001a\u00020>2\b\u0010o\u001a\u0004\u0018\u00010p2\u0006\u0010q\u001a\u000207J\u0006\u0010r\u001a\u00020>J\u0006\u0010s\u001a\u00020>J\u0006\u0010t\u001a\u00020>J(\u0010u\u001a\u00020>2\b\u0010A\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010v\u001a\u0004\u0018\u00010L2\b\b\u0002\u0010w\u001a\u00020\u0015H\u0007J\u000e\u0010x\u001a\u0002072\u0006\u0010q\u001a\u000207J\u000e\u0010y\u001a\u00020>2\u0006\u0010q\u001a\u000207J\u0010\u0010z\u001a\u00020>2\u0006\u0010#\u001a\u00020\u0019H\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\tX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001c\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u001fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u000e¢\u0006\u0002\n\u0000R(\u0010%\u001a\u0004\u0018\u00010$2\b\u0010#\u001a\u0004\u0018\u00010$@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010*\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u001b\"\u0004\b,\u0010\u001dR\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010.\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0017\"\u0004\b0\u00101R\u0017\u00102\u001a\b\u0012\u0004\u0012\u00020\u001503¢\u0006\b\n\u0000\u001a\u0004\b2\u00104R\u0011\u00105\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b5\u0010\u0017R\u000e\u00106\u001a\u000207X\u000e¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u001c\u00109\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010\u001b\"\u0004\b;\u0010\u001dR\u001d\u0010<\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020>0=03¢\u0006\b\n\u0000\u001a\u0004\b?\u00104R\u0010\u0010@\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010A\u001a\u0004\u0018\u00010\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u001b\"\u0004\bC\u0010\u001dR(\u0010E\u001a\u0004\u0018\u00010\u00192\b\u0010D\u001a\u0004\u0018\u00010\u0019@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010\u001b\"\u0004\bG\u0010\u001dR#\u0010H\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020J0I0=03¢\u0006\b\n\u0000\u001a\u0004\bK\u00104R(\u0010M\u001a\u0004\u0018\u00010L2\b\u0010D\u001a\u0004\u0018\u00010L@BX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u001a\u0010R\u001a\u00020\u0015X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bS\u0010\u0017\"\u0004\bT\u00101R\u0014\u0010U\u001a\u00020\u00158BX\u0004¢\u0006\u0006\u001a\u0004\bV\u0010\u0017R\u0014\u0010W\u001a\u00020\u00158BX\u0004¢\u0006\u0006\u001a\u0004\bX\u0010\u0017R\u001c\u0010Y\u001a\u0004\u0018\u00010ZX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u0017\u0010_\u001a\b\u0012\u0004\u0012\u00020\u001903¢\u0006\b\n\u0000\u001a\u0004\b`\u00104¨\u0006|"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/viewmodel/OnlineFoodSearchViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "onlineSearchRepo", "Lcom/myfitnesspal/feature/search/repository/OnlineFoodSearchRepository;", "actionTrackingService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/ActionTrackingService;", "configService", "Lcom/myfitnesspal/shared/service/config/ConfigService;", "analyticsService", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "premiumService", "Lcom/myfitnesspal/feature/premium/service/PremiumService;", "foodSearchAnalyticsHelper", "Lcom/myfitnesspal/feature/search/service/FoodSearchAnalyticsHelper;", "(Landroid/app/Application;Lcom/myfitnesspal/shared/service/install/CountryService;Lcom/myfitnesspal/feature/search/repository/OnlineFoodSearchRepository;Ldagger/Lazy;Lcom/myfitnesspal/shared/service/config/ConfigService;Ldagger/Lazy;Lcom/myfitnesspal/feature/premium/service/PremiumService;Lcom/myfitnesspal/feature/search/service/FoodSearchAnalyticsHelper;)V", "areSearchImprovementsLive", "", "getAreSearchImprovementsLive", "()Z", "barcode", "", "getBarcode", "()Ljava/lang/String;", "setBarcode", "(Ljava/lang/String;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "containsSponsoredFood", "dfpAd", "Lcom/google/android/gms/ads/formats/NativeCustomTemplateAd;", "value", "Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Extras;", "extras", "getExtras", "()Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Extras;", "setExtras", "(Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Extras;)V", "flowId", "getFlowId", "setFlowId", "isFirstItemSelected", "isInWalkThrough", "isMealFoodCreationFlow", "setMealFoodCreationFlow", "(Z)V", "isVerifiedFoodsOnly", "Landroid/arch/lifecycle/MutableLiveData;", "()Landroid/arch/lifecycle/MutableLiveData;", "isVerifiedOnlyAvailable", "itemsSelectedOnView", "", "listType", "mealName", "getMealName", "setMealName", "nextPage", "Lcom/myfitnesspal/feature/registration/model/Resource;", "", "getNextPage", "nextPageLink", "query", "getQuery", "setQuery", "<set-?>", "searchRequestId", "getSearchRequestId", "setSearchRequestId", "searchResults", "", "Lcom/myfitnesspal/shared/model/v2/MfpFoodSearchResult;", "getSearchResults", "Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Trigger;", "searchTrigger", "getSearchTrigger", "()Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Trigger;", "setSearchTrigger", "(Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Trigger;)V", "shouldDisableMultiAdd", "getShouldDisableMultiAdd", "setShouldDisableMultiAdd", "shouldFetchSearchAd", "getShouldFetchSearchAd", "shouldShowVenues", "getShouldShowVenues", "source", "Lcom/myfitnesspal/feature/search/model/SearchSource;", "getSource", "()Lcom/myfitnesspal/feature/search/model/SearchSource;", "setSource", "(Lcom/myfitnesspal/feature/search/model/SearchSource;)V", "sponsoredCategory", "getSponsoredCategory", "appendCommonAnltAttrsToOnlineFoodSummary", "doesListContainVenueSearchResult", "foodSearchResults", "", "fetchNextPage", "fetchSponsoredFood", "Lio/reactivex/Single;", "Lcom/myfitnesspal/feature/search/model/SponsoredFood;", "logSearchResultSize", "searchResultsSize", "containsVenueSearchResult", "onCleared", "parseArguments", "reportFoodLookupEvent", "searchResultItem", "Lcom/myfitnesspal/shared/model/v2/SearchResultItem;", "position", "reportFoodPressed", "reportOnlineSearchSummary", "reportSponsoredAdPressed", "search", "trigger", "verifiedOnly", "shiftPositionIfAdPresent", "trackClickInSearchSummary", "updateFoodSearchBreadcrumb", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: OnlineFoodSearchViewModel.kt */
public final class OnlineFoodSearchViewModel extends AndroidViewModel {
    private static final String ANLT_ATTR_CONTAINS_FOOD_AD = "contains_food_ad";
    private static final String ANLT_ATTR_CONTAINS_VENUE_SEARCH_RESULT = "contains_venue_search_result";
    private static final String ANLT_ATTR_SCREEN = "screen";
    private static final String ANLT_ATTR_SEARCH_TERM = "search_term";
    private static final String ANLT_ATTR_TRIGGER = "trigger";
    private static final String ANLT_VALUE_ADD_TO_DIARY = "add_to_diary";
    private static final String ANLT_VALUE_CREATE_MEAL = "create_meal";
    public static final Companion Companion = new Companion(null);
    private static final String LANGUAGE_ENGLISH = "en";
    private static final int POSITION_SPONSORED_FOOD = 0;
    private static final long REQUEST_TIMEOUT_SPONSORED_FOOD_IN_MILLISECONDS = 2500;
    /* access modifiers changed from: private */
    public final Lazy<ActionTrackingService> actionTrackingService;
    /* access modifiers changed from: private */
    public final Lazy<AnalyticsService> analyticsService;
    private final boolean areSearchImprovementsLive = ConfigUtils.isFoodSearchPhase1Enabled(this.configService);
    @Nullable
    private String barcode;
    private final CompositeDisposable compositeDisposable;
    private final ConfigService configService;
    /* access modifiers changed from: private */
    public boolean containsSponsoredFood;
    /* access modifiers changed from: private */
    public NativeCustomTemplateAd dfpAd;
    @Nullable
    private Extras extras;
    @Nullable
    private String flowId;
    /* access modifiers changed from: private */
    public final FoodSearchAnalyticsHelper foodSearchAnalyticsHelper;
    private boolean isFirstItemSelected;
    private boolean isInWalkThrough;
    private boolean isMealFoodCreationFlow;
    @NotNull
    private final MutableLiveData<Boolean> isVerifiedFoodsOnly = new MutableLiveData<>();
    private final boolean isVerifiedOnlyAvailable;
    private int itemsSelectedOnView;
    private String listType;
    @Nullable
    private String mealName;
    @NotNull
    private final MutableLiveData<Resource<Unit>> nextPage = new MutableLiveData<>();
    /* access modifiers changed from: private */
    public String nextPageLink;
    /* access modifiers changed from: private */
    public final OnlineFoodSearchRepository onlineSearchRepo;
    private final PremiumService premiumService;
    @Nullable
    private String query;
    /* access modifiers changed from: private */
    @Nullable
    public String searchRequestId;
    @NotNull
    private final MutableLiveData<Resource<List<MfpFoodSearchResult>>> searchResults = new MutableLiveData<>();
    @Nullable
    private Trigger searchTrigger;
    private boolean shouldDisableMultiAdd;
    @Nullable
    private SearchSource source;
    @NotNull
    private final MutableLiveData<String> sponsoredCategory = new MutableLiveData<>();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fXT¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/viewmodel/OnlineFoodSearchViewModel$Companion;", "", "()V", "ANLT_ATTR_CONTAINS_FOOD_AD", "", "ANLT_ATTR_CONTAINS_VENUE_SEARCH_RESULT", "ANLT_ATTR_SCREEN", "ANLT_ATTR_SEARCH_TERM", "ANLT_ATTR_TRIGGER", "ANLT_VALUE_ADD_TO_DIARY", "ANLT_VALUE_CREATE_MEAL", "LANGUAGE_ENGLISH", "POSITION_SPONSORED_FOOD", "", "REQUEST_TIMEOUT_SPONSORED_FOOD_IN_MILLISECONDS", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: OnlineFoodSearchViewModel.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @JvmOverloads
    public final void search(@Nullable String str) {
        search$default(this, str, null, false, 6, null);
    }

    @JvmOverloads
    public final void search(@Nullable String str, @Nullable Trigger trigger) {
        search$default(this, str, trigger, false, 4, null);
    }

    @Inject
    public OnlineFoodSearchViewModel(@NotNull Application application, @NotNull CountryService countryService, @NotNull OnlineFoodSearchRepository onlineFoodSearchRepository, @NotNull Lazy<ActionTrackingService> lazy, @NotNull ConfigService configService2, @NotNull Lazy<AnalyticsService> lazy2, @NotNull PremiumService premiumService2, @NotNull FoodSearchAnalyticsHelper foodSearchAnalyticsHelper2) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        Intrinsics.checkParameterIsNotNull(countryService, "countryService");
        Intrinsics.checkParameterIsNotNull(onlineFoodSearchRepository, "onlineSearchRepo");
        Intrinsics.checkParameterIsNotNull(lazy, "actionTrackingService");
        Intrinsics.checkParameterIsNotNull(configService2, "configService");
        Intrinsics.checkParameterIsNotNull(lazy2, "analyticsService");
        Intrinsics.checkParameterIsNotNull(premiumService2, "premiumService");
        Intrinsics.checkParameterIsNotNull(foodSearchAnalyticsHelper2, "foodSearchAnalyticsHelper");
        super(application);
        this.onlineSearchRepo = onlineFoodSearchRepository;
        this.actionTrackingService = lazy;
        this.configService = configService2;
        this.analyticsService = lazy2;
        this.premiumService = premiumService2;
        this.foodSearchAnalyticsHelper = foodSearchAnalyticsHelper2;
        boolean z = true;
        if (!this.areSearchImprovementsLive || !StringsKt.equals(countryService.getCurrentLanguage(), LANGUAGE_ENGLISH, true)) {
            z = false;
        }
        this.isVerifiedOnlyAvailable = z;
        this.compositeDisposable = new CompositeDisposable();
    }

    @NotNull
    public final MutableLiveData<Resource<List<MfpFoodSearchResult>>> getSearchResults() {
        return this.searchResults;
    }

    @NotNull
    public final MutableLiveData<Resource<Unit>> getNextPage() {
        return this.nextPage;
    }

    @NotNull
    public final MutableLiveData<String> getSponsoredCategory() {
        return this.sponsoredCategory;
    }

    @NotNull
    public final MutableLiveData<Boolean> isVerifiedFoodsOnly() {
        return this.isVerifiedFoodsOnly;
    }

    public final boolean getAreSearchImprovementsLive() {
        return this.areSearchImprovementsLive;
    }

    public final boolean isVerifiedOnlyAvailable() {
        return this.isVerifiedOnlyAvailable;
    }

    @Nullable
    public final Extras getExtras() {
        return this.extras;
    }

    public final void setExtras(@Nullable Extras extras2) {
        parseArguments(extras2);
        search$default(this, this.query, null, false, 6, null);
    }

    @Nullable
    public final String getQuery() {
        return this.query;
    }

    public final void setQuery(@Nullable String str) {
        this.query = str;
    }

    @Nullable
    public final String getFlowId() {
        return this.flowId;
    }

    public final void setFlowId(@Nullable String str) {
        this.flowId = str;
    }

    @Nullable
    public final String getMealName() {
        return this.mealName;
    }

    public final void setMealName(@Nullable String str) {
        this.mealName = str;
    }

    @Nullable
    public final String getBarcode() {
        return this.barcode;
    }

    public final void setBarcode(@Nullable String str) {
        this.barcode = str;
    }

    @Nullable
    public final SearchSource getSource() {
        return this.source;
    }

    public final void setSource(@Nullable SearchSource searchSource) {
        this.source = searchSource;
    }

    public final boolean isMealFoodCreationFlow() {
        return this.isMealFoodCreationFlow;
    }

    public final void setMealFoodCreationFlow(boolean z) {
        this.isMealFoodCreationFlow = z;
    }

    public final boolean getShouldDisableMultiAdd() {
        return this.shouldDisableMultiAdd;
    }

    public final void setShouldDisableMultiAdd(boolean z) {
        this.shouldDisableMultiAdd = z;
    }

    private final void setSearchTrigger(Trigger trigger) {
        this.searchTrigger = trigger;
    }

    @Nullable
    public final Trigger getSearchTrigger() {
        return this.searchTrigger;
    }

    private final void setSearchRequestId(String str) {
        this.searchRequestId = str;
    }

    @Nullable
    public final String getSearchRequestId() {
        return this.searchRequestId;
    }

    private final boolean getShouldFetchSearchAd() {
        return ConfigUtils.isFoodSearchAdV1Enabled(this.configService) && !this.premiumService.isPremiumSubscribed() && !this.isInWalkThrough;
    }

    /* access modifiers changed from: private */
    public final boolean getShouldShowVenues() {
        return ConfigUtils.isRLSearchIntegrationEnabled(this.configService) && Strings.notEmpty(this.flowId);
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        this.compositeDisposable.dispose();
        NativeCustomTemplateAd nativeCustomTemplateAd = this.dfpAd;
        if (nativeCustomTemplateAd != null) {
            nativeCustomTemplateAd.destroy();
        }
    }

    @JvmOverloads
    public static /* synthetic */ void search$default(OnlineFoodSearchViewModel onlineFoodSearchViewModel, String str, Trigger trigger, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            trigger = onlineFoodSearchViewModel.searchTrigger;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        onlineFoodSearchViewModel.search(str, trigger, z);
    }

    @JvmOverloads
    public final void search(@Nullable String str, @Nullable Trigger trigger, boolean z) {
        if (str != null) {
            this.query = str;
            this.searchTrigger = trigger;
            this.isVerifiedFoodsOnly.setValue(Boolean.valueOf(z));
            this.searchResults.setValue(new Loading());
            this.compositeDisposable.add(fetchSponsoredFood(str).subscribeOn(Schedulers.io()).flatMap(new OnlineFoodSearchViewModel$search$1(this, str)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new OnlineFoodSearchViewModel$search$2(this, str), new OnlineFoodSearchViewModel$search$3(this)));
        }
    }

    public final void fetchNextPage() {
        CharSequence charSequence = this.nextPageLink;
        if (!(charSequence == null || charSequence.length() == 0) && !(this.nextPage.getValue() instanceof Loading)) {
            String str = this.nextPageLink;
            if (str != null) {
                this.nextPage.setValue(new Loading());
                this.compositeDisposable.add(this.onlineSearchRepo.fetchFoodsByLink(str, this.flowId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new OnlineFoodSearchViewModel$fetchNextPage$$inlined$let$lambda$1(this), new OnlineFoodSearchViewModel$fetchNextPage$$inlined$let$lambda$2(this)));
            }
        }
    }

    public final void trackClickInSearchSummary(int i) {
        this.itemsSelectedOnView++;
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.SELECTED_COUNT, Strings.toString(Integer.valueOf(this.itemsSelectedOnView)));
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.LAST_SELECTION_INDEX, Strings.toString(Integer.valueOf(i)));
        if (!this.isFirstItemSelected) {
            this.isFirstItemSelected = true;
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.FIRST_SELECTION_INDEX, Strings.toString(Integer.valueOf(i)));
        }
    }

    public final void reportFoodLookupEvent(@Nullable SearchResultItem searchResultItem, int i) {
        this.foodSearchAnalyticsHelper.reportFoodLookupEvent(searchResultItem, this.flowId, this.query, i, this.source, this.searchRequestId, this.containsSponsoredFood);
    }

    public final void reportFoodPressed() {
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent("is_last_pressed_search", "is_last_pressed_search", Strings.toString(Boolean.valueOf(true)));
        updateFoodSearchBreadcrumb(Attributes.ADD_VIEW);
        ActionTrackingService actionTrackingService2 = (ActionTrackingService) this.actionTrackingService.get();
        String str = Flows.LOGGING;
        String[] strArr = new String[8];
        strArr[0] = Attributes.ITEM_COUNT;
        strArr[1] = AppEventsConstants.EVENT_PARAM_VALUE_YES;
        strArr[2] = "list_type";
        strArr[3] = this.listType;
        strArr[4] = "source";
        SearchSource searchSource = this.source;
        strArr[5] = searchSource != null ? searchSource.getTitle() : null;
        strArr[6] = "flow_id";
        strArr[7] = this.flowId;
        actionTrackingService2.appendToEvent(str, MapUtil.createMap(strArr));
    }

    public final void reportOnlineSearchSummary() {
        ((ActionTrackingService) this.actionTrackingService.get()).reportEventToAnalytics(Events.ONLINE_SEARCH_SUMMARY, Events.ONLINE_SEARCH_SUMMARY, true);
    }

    public final void reportSponsoredAdPressed() {
        NativeCustomTemplateAd nativeCustomTemplateAd = this.dfpAd;
        if (nativeCustomTemplateAd != null) {
            nativeCustomTemplateAd.performClick("");
        }
    }

    public final int shiftPositionIfAdPresent(int i) {
        return (!this.containsSponsoredFood || i <= 0) ? i : i - 1;
    }

    private final void updateFoodSearchBreadcrumb(String str) {
        ((ActionTrackingService) this.actionTrackingService.get()).deleteBreadcrumbs(Attributes.FOOD_SEARCH_BREADCRUMB);
        ((ActionTrackingService) this.actionTrackingService.get()).addToBreadcrumbs(Attributes.FOOD_SEARCH_BREADCRUMB, str, "");
    }

    /* access modifiers changed from: private */
    public final void logSearchResultSize(int i, boolean z) {
        String str = i == 0 ? "0" : i < 5 ? Attributes.RESULT_COUNT_1_4 : i < 10 ? Attributes.RESULT_COUNT_5_9 : i < 25 ? Attributes.RESULT_COUNT_10_24 : Attributes.RESULT_COUNT_25;
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, ANLT_ATTR_CONTAINS_VENUE_SEARCH_RESULT, Boolean.toString(z));
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.RESULT_COUNT, str);
    }

    /* access modifiers changed from: private */
    public final void appendCommonAnltAttrsToOnlineFoodSummary() {
        ActionTrackingService actionTrackingService2 = (ActionTrackingService) this.actionTrackingService.get();
        String str = Events.ONLINE_SEARCH_SUMMARY;
        Pair[] pairArr = new Pair[6];
        pairArr[0] = TuplesKt.to("flow_id", this.flowId);
        pairArr[1] = TuplesKt.to("source", "online_search");
        pairArr[2] = TuplesKt.to(Attributes.LOGGED, "no");
        String str2 = ANLT_ATTR_TRIGGER;
        Trigger trigger = this.searchTrigger;
        pairArr[3] = TuplesKt.to(str2, trigger != null ? trigger.anltName : null);
        pairArr[4] = TuplesKt.to("search_term", this.query);
        pairArr[5] = TuplesKt.to("screen", this.isMealFoodCreationFlow ? ANLT_VALUE_CREATE_MEAL : ANLT_VALUE_ADD_TO_DIARY);
        actionTrackingService2.appendToEvent(str, MapsKt.mapOf(pairArr));
    }

    private final void parseArguments(Extras extras2) {
        if (extras2 != null) {
            this.query = extras2.getQuery();
            this.mealName = extras2.getMealName();
            this.isMealFoodCreationFlow = extras2.isInMealFoodCreationFlow();
            this.flowId = extras2.getFlowId();
            this.source = extras2.getSource();
            this.listType = extras2.getListType();
            this.barcode = extras2.getBarcode();
            this.isInWalkThrough = extras2.isInWalkthrough();
            this.shouldDisableMultiAdd = extras2.isShouldDisableMultiAdd();
            this.searchTrigger = extras2.getTrigger();
        }
    }

    /* access modifiers changed from: private */
    public final boolean doesListContainVenueSearchResult(List<? extends MfpFoodSearchResult> list) {
        for (SearchResultItem searchResultItem : SequencesKt.map(CollectionsKt.asSequence(list), OnlineFoodSearchViewModel$doesListContainVenueSearchResult$1.INSTANCE)) {
            if (searchResultItem instanceof Venue) {
                return true;
            }
        }
        return false;
    }

    private final Single<SponsoredFood> fetchSponsoredFood(String str) {
        if (getShouldFetchSearchAd()) {
            OnlineFoodSearchRepository onlineFoodSearchRepository = this.onlineSearchRepo;
            if (str != null) {
                String lowerCase = str.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                Single<SponsoredFood> onErrorReturn = onlineFoodSearchRepository.fetchSponsoredCategoryForQuery(lowerCase).timeout(REQUEST_TIMEOUT_SPONSORED_FOOD_IN_MILLISECONDS, TimeUnit.MILLISECONDS).flatMap(new OnlineFoodSearchViewModel$fetchSponsoredFood$1(this)).onErrorReturn(OnlineFoodSearchViewModel$fetchSponsoredFood$2.INSTANCE);
                Intrinsics.checkExpressionValueIsNotNull(onErrorReturn, "onlineSearchRepo.fetchSp…n { SponsoredFood.EMPTY }");
                return onErrorReturn;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        Single<SponsoredFood> just = Single.just(SponsoredFood.Companion.getEMPTY());
        Intrinsics.checkExpressionValueIsNotNull(just, "Single.just(SponsoredFood.EMPTY)");
        return just;
    }
}
