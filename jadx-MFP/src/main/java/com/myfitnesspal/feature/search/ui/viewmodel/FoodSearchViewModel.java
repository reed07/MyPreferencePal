package com.myfitnesspal.feature.search.ui.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.Intent;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.consents.model.Resource;
import com.myfitnesspal.feature.consents.model.Resource.Error;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.service.RecipeService;
import com.myfitnesspal.feature.recipes.service.RecipesAnalyticsHelper;
import com.myfitnesspal.feature.search.service.FoodSearchAnalyticsHelper;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivityV2.Extras;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet.AddItemOption;
import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.QuickAddFood;
import com.myfitnesspal.shared.model.mapper.impl.MealIngredientMapper;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Tuple2;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00012\u00020\u0001:\u0002\u0001Bñ\u0001\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0007\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0007\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007\u0012\u0012\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u0007\u0012\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0007\u0012\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0007\u0012\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0007\u0012\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0007\u0012\u0006\u0010&\u001a\u00020'¢\u0006\u0002\u0010(J\u0010\u0010b\u001a\u00020c2\b\u0010d\u001a\u0004\u0018\u00010eJ9\u0010f\u001a\u00020c2\b\u0010g\u001a\u0004\u0018\u00010h2'\u0010i\u001a#\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020l0k¢\u0006\f\bm\u0012\b\bn\u0012\u0004\b\b(o\u0012\u0004\u0012\u00020c0jJ\u0006\u0010p\u001a\u00020cJ\b\u0010q\u001a\u00020cH\u0014J\u0016\u0010r\u001a\b\u0012\u0004\u0012\u00020Z0Y2\b\u0010d\u001a\u0004\u0018\u00010eJ\u000e\u0010s\u001a\u00020c2\u0006\u0010t\u001a\u00020NJ\u0010\u0010u\u001a\u00020c2\u0006\u0010v\u001a\u00020=H\u0002J\u0006\u0010w\u001a\u00020cJ\u000e\u0010x\u001a\u00020c2\u0006\u0010d\u001a\u00020yJ\u000e\u0010z\u001a\u00020c2\u0006\u0010d\u001a\u00020yJ\u0006\u0010{\u001a\u00020cJ\u000e\u0010|\u001a\u00020c2\u0006\u0010}\u001a\u00020~J\u001d\u0010\u001a\u00020c2\t\u0010\u0001\u001a\u0004\u0018\u00010*2\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001J\u000f\u0010\u0001\u001a\u00020c2\u0006\u0010v\u001a\u00020=J\t\u0010\u0001\u001a\u00020cH\u0002R\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010)\u001a\u00020*8F¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0007X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u00020.X.¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0004¢\u0006\u0002\n\u0000R(\u00107\u001a\u0004\u0018\u0001062\b\u00105\u001a\u0004\u0018\u000106@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u0014\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010<\u001a\u00020=¢\u0006\b\n\u0000\u001a\u0004\b<\u0010>R\u0017\u0010?\u001a\b\u0012\u0004\u0012\u00020=0@¢\u0006\b\n\u0000\u001a\u0004\b?\u0010AR\u0011\u0010B\u001a\u00020=8F¢\u0006\u0006\u001a\u0004\bB\u0010>R\u0011\u0010C\u001a\u00020=8F¢\u0006\u0006\u001a\u0004\bC\u0010>R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010D\u001a\u0004\u0018\u00010EX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u0011\u0010J\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u0017\u0010M\u001a\b\u0012\u0004\u0012\u00020N0@¢\u0006\b\n\u0000\u001a\u0004\bO\u0010AR\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0007X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010P\u001a\u00020Q8F¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010T\u001a\u00020E8F¢\u0006\u0006\u001a\u0004\bU\u0010GR\u0011\u0010V\u001a\u00020E¢\u0006\b\n\u0000\u001a\u0004\bW\u0010GR\u001f\u0010X\u001a\u0010\u0012\f\u0012\n [*\u0004\u0018\u00010Z0Z0Y8F¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007X\u0004¢\u0006\u0002\n\u0000R$\u0010^\u001a\u00020=2\u0006\u00105\u001a\u00020=8F@FX\u000e¢\u0006\f\u001a\u0004\b_\u0010>\"\u0004\b`\u0010aR\u001a\u0010\u001b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\u001c0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0001"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/viewmodel/FoodSearchViewModel;", "Landroid/arch/lifecycle/AndroidViewModel;", "applicationContext", "Landroid/app/Application;", "countryService", "Lcom/myfitnesspal/shared/service/install/CountryService;", "multiAddFoodHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;", "localizedStringsUtil", "Lcom/myfitnesspal/shared/util/LocalizedStringsUtil;", "userEnergyService", "Lcom/myfitnesspal/shared/service/userdata/UserEnergyService;", "diaryService", "Lcom/myfitnesspal/feature/diary/service/DiaryService;", "session", "Lcom/myfitnesspal/shared/service/session/Session;", "mealIngredientMapper", "Lcom/myfitnesspal/shared/model/mapper/impl/MealIngredientMapper;", "premiumService", "Lcom/myfitnesspal/feature/premium/service/PremiumService;", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "recipeService", "Lcom/myfitnesspal/feature/recipes/service/RecipeService;", "dbConnectionManager", "Lcom/myfitnesspal/shared/db/DbConnectionManager;", "syncScheduler", "Lcom/uacf/sync/engine/UacfScheduler;", "Lcom/myfitnesspal/shared/service/syncv2/SyncType;", "foodSearchAnalyticsHelper", "Lcom/myfitnesspal/feature/search/service/FoodSearchAnalyticsHelper;", "recipesAnalyticsHelper", "Lcom/myfitnesspal/feature/recipes/service/RecipesAnalyticsHelper;", "foodEditorAnalytics", "Lcom/myfitnesspal/feature/foodeditor/ui/service/FoodEditorAnalytics;", "actionTrackingService", "Lcom/myfitnesspal/shared/service/analytics/ActionTrackingService;", "configService", "Lcom/myfitnesspal/shared/service/config/ConfigService;", "(Landroid/app/Application;Lcom/myfitnesspal/shared/service/install/CountryService;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Ldagger/Lazy;Lcom/myfitnesspal/shared/service/config/ConfigService;)V", "currentActiveDate", "Ljava/util/Date;", "getCurrentActiveDate", "()Ljava/util/Date;", "defaultSearchTab", "Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "getDefaultSearchTab", "()Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "setDefaultSearchTab", "(Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;)V", "disposable", "Lio/reactivex/disposables/CompositeDisposable;", "value", "Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$Extras;", "extras", "getExtras", "()Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$Extras;", "setExtras", "(Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$Extras;)V", "isEnglishCurrentLanguage", "", "()Z", "isMultiAddEnabled", "Landroid/arch/lifecycle/MutableLiveData;", "()Landroid/arch/lifecycle/MutableLiveData;", "isRecipeParsingEnabled", "isTimestampFeatureEnabled", "mealName", "", "getMealName", "()Ljava/lang/String;", "setMealName", "(Ljava/lang/String;)V", "multiAddHelper", "getMultiAddHelper", "()Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;", "multiAddItemsCount", "", "getMultiAddItemsCount", "quickAddMacrosAvailability", "Lcom/myfitnesspal/feature/premium/service/PremiumService$Availability;", "getQuickAddMacrosAvailability", "()Lcom/myfitnesspal/feature/premium/service/PremiumService$Availability;", "screenTitle", "getScreenTitle", "searchFlowId", "getSearchFlowId", "selectedMultiAddEntries", "Ljava/util/ArrayList;", "Lcom/myfitnesspal/shared/model/v1/FoodEntry;", "kotlin.jvm.PlatformType", "getSelectedMultiAddEntries", "()Ljava/util/ArrayList;", "shouldShowWalkthrough", "getShouldShowWalkthrough", "setShouldShowWalkthrough", "(Z)V", "addAndLogItemsFromActivityResultData", "", "data", "Landroid/content/Intent;", "addOrUpdateQuickFoodEntry", "quickAddFood", "Lcom/myfitnesspal/shared/model/QuickAddFood;", "resultAction", "Lkotlin/Function1;", "Lcom/myfitnesspal/feature/consents/model/Resource;", "Ljava/lang/Void;", "Lkotlin/ParameterName;", "name", "resource", "appendBarcodeEventToOnlineSearchSummary", "onCleared", "packMealIngredientsAsFoodEntries", "reportExistingUserWalkthroughStepShowed", "index", "reportMultiAddButtonToggled", "isEnabled", "reportMultiAddLogged", "reportRecipeFlowStarted", "Lcom/myfitnesspal/feature/recipes/model/RecipeAnalyticsIntentData;", "reportRecipeImportShown", "reportToolbarPlusClicked", "reportToolbarPlusItemSelected", "item", "Lcom/myfitnesspal/feature/search/ui/dialog/AddItemBottomSheet$AddItemOption;", "saveEntriesToDiary", "time", "timestampOption", "Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;", "toggleMultiAdd", "updateMultiAddSelectionCount", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodSearchViewModel.kt */
public final class FoodSearchViewModel extends AndroidViewModel {
    public static final Companion Companion = new Companion(null);
    private static final String LANGUAGE_ENGLISH = "en";
    private static final int SEARCH_VERSION = 2;
    private final Lazy<ActionTrackingService> actionTrackingService;
    private final ConfigService configService;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    @NotNull
    public FoodSearchTab defaultSearchTab;
    private final Lazy<DiaryService> diaryService;
    private final CompositeDisposable disposable;
    @Nullable
    private Extras extras;
    private final Lazy<FoodEditorAnalytics> foodEditorAnalytics;
    private final Lazy<FoodSearchAnalyticsHelper> foodSearchAnalyticsHelper;
    private final boolean isEnglishCurrentLanguage;
    @NotNull
    private final MutableLiveData<Boolean> isMultiAddEnabled = new MutableLiveData<>();
    private final LocalSettingsService localSettingsService;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private final Lazy<MealIngredientMapper> mealIngredientMapper;
    @Nullable
    private String mealName;
    @NotNull
    private final MultiAddFoodHelper multiAddHelper;
    @NotNull
    private final MutableLiveData<Integer> multiAddItemsCount = new MutableLiveData<>();
    private final Lazy<PremiumService> premiumService;
    private final Lazy<RecipeService> recipeService;
    private final Lazy<RecipesAnalyticsHelper> recipesAnalyticsHelper;
    @NotNull
    private final String searchFlowId;
    private final Lazy<Session> session;
    private final Lazy<UacfScheduler<SyncType>> syncScheduler;
    private final Lazy<UserEnergyService> userEnergyService;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/viewmodel/FoodSearchViewModel$Companion;", "", "()V", "LANGUAGE_ENGLISH", "", "SEARCH_VERSION", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodSearchViewModel.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public FoodSearchViewModel(@NotNull Application application, @NotNull CountryService countryService, @NotNull Lazy<MultiAddFoodHelper> lazy, @NotNull Lazy<LocalizedStringsUtil> lazy2, @NotNull Lazy<UserEnergyService> lazy3, @NotNull Lazy<DiaryService> lazy4, @NotNull Lazy<Session> lazy5, @NotNull Lazy<MealIngredientMapper> lazy6, @NotNull Lazy<PremiumService> lazy7, @NotNull LocalSettingsService localSettingsService2, @NotNull Lazy<RecipeService> lazy8, @NotNull Lazy<DbConnectionManager> lazy9, @NotNull Lazy<UacfScheduler<SyncType>> lazy10, @NotNull Lazy<FoodSearchAnalyticsHelper> lazy11, @NotNull Lazy<RecipesAnalyticsHelper> lazy12, @NotNull Lazy<FoodEditorAnalytics> lazy13, @NotNull Lazy<ActionTrackingService> lazy14, @NotNull ConfigService configService2) {
        Lazy<LocalizedStringsUtil> lazy15 = lazy2;
        Lazy<UserEnergyService> lazy16 = lazy3;
        Lazy<DiaryService> lazy17 = lazy4;
        Lazy<Session> lazy18 = lazy5;
        Lazy<MealIngredientMapper> lazy19 = lazy6;
        Lazy<PremiumService> lazy20 = lazy7;
        LocalSettingsService localSettingsService3 = localSettingsService2;
        Lazy<RecipeService> lazy21 = lazy8;
        Lazy<DbConnectionManager> lazy22 = lazy9;
        Lazy<UacfScheduler<SyncType>> lazy23 = lazy10;
        Lazy<FoodSearchAnalyticsHelper> lazy24 = lazy11;
        Lazy<RecipesAnalyticsHelper> lazy25 = lazy12;
        Lazy<FoodEditorAnalytics> lazy26 = lazy13;
        Lazy<ActionTrackingService> lazy27 = lazy14;
        ConfigService configService3 = configService2;
        Intrinsics.checkParameterIsNotNull(application, "applicationContext");
        Intrinsics.checkParameterIsNotNull(countryService, "countryService");
        Intrinsics.checkParameterIsNotNull(lazy, "multiAddFoodHelper");
        Intrinsics.checkParameterIsNotNull(lazy15, "localizedStringsUtil");
        Intrinsics.checkParameterIsNotNull(lazy16, "userEnergyService");
        Intrinsics.checkParameterIsNotNull(lazy17, "diaryService");
        Intrinsics.checkParameterIsNotNull(lazy18, "session");
        Intrinsics.checkParameterIsNotNull(lazy19, "mealIngredientMapper");
        Intrinsics.checkParameterIsNotNull(lazy20, "premiumService");
        Intrinsics.checkParameterIsNotNull(localSettingsService3, "localSettingsService");
        Intrinsics.checkParameterIsNotNull(lazy21, "recipeService");
        Intrinsics.checkParameterIsNotNull(lazy22, "dbConnectionManager");
        Intrinsics.checkParameterIsNotNull(lazy23, "syncScheduler");
        Intrinsics.checkParameterIsNotNull(lazy24, "foodSearchAnalyticsHelper");
        Intrinsics.checkParameterIsNotNull(lazy25, "recipesAnalyticsHelper");
        Intrinsics.checkParameterIsNotNull(lazy26, "foodEditorAnalytics");
        Intrinsics.checkParameterIsNotNull(lazy27, "actionTrackingService");
        ConfigService configService4 = configService2;
        Intrinsics.checkParameterIsNotNull(configService4, "configService");
        super(application);
        this.localizedStringsUtil = lazy15;
        this.userEnergyService = lazy16;
        this.diaryService = lazy17;
        this.session = lazy18;
        this.mealIngredientMapper = lazy19;
        this.premiumService = lazy20;
        this.localSettingsService = localSettingsService3;
        this.recipeService = lazy21;
        this.dbConnectionManager = lazy22;
        this.syncScheduler = lazy23;
        this.foodSearchAnalyticsHelper = lazy24;
        this.recipesAnalyticsHelper = lazy25;
        this.foodEditorAnalytics = lazy26;
        this.actionTrackingService = lazy27;
        this.configService = configService4;
        Object obj = lazy.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "multiAddFoodHelper.get()");
        this.multiAddHelper = (MultiAddFoodHelper) obj;
        this.isEnglishCurrentLanguage = StringsKt.equals(countryService.getCurrentLanguage(), LANGUAGE_ENGLISH, true);
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.searchFlowId = uuid;
        this.disposable = new CompositeDisposable();
        if (this.localSettingsService.getMultiAddToggleOnByDefault()) {
            this.multiAddHelper.enable();
            this.isMultiAddEnabled.setValue(Boolean.valueOf(true));
        } else {
            this.multiAddHelper.disable();
        }
        this.disposable.add(this.multiAddHelper.getItemsChangedSubject().subscribe((Consumer<? super T>) new Consumer<Boolean>(this) {
            final /* synthetic */ FoodSearchViewModel this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(Boolean bool) {
                this.this$0.updateMultiAddSelectionCount();
            }
        }));
        this.disposable.add(this.multiAddHelper.getMultiAddEnabledSubject().subscribeOn(Schedulers.computation()).subscribe((Consumer<? super T>) new Consumer<Boolean>(this) {
            final /* synthetic */ FoodSearchViewModel this$0;

            {
                this.this$0 = r1;
            }

            public final void accept(Boolean bool) {
                this.this$0.isMultiAddEnabled().postValue(bool);
            }
        }));
    }

    @NotNull
    public final MutableLiveData<Boolean> isMultiAddEnabled() {
        return this.isMultiAddEnabled;
    }

    @NotNull
    public final String getScreenTitle() {
        Extras extras2 = this.extras;
        if (extras2 == null || !extras2.isInMealFoodCreationFlow()) {
            String mealNameString = ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(this.mealName, (UserEnergyService) this.userEnergyService.get());
            Intrinsics.checkExpressionValueIsNotNull(mealNameString, "localizedStringsUtil.get… userEnergyService.get())");
            return mealNameString;
        }
        String string = ((MyFitnessPalApp) getApplication()).getString(R.string.addFood);
        Intrinsics.checkExpressionValueIsNotNull(string, "getApplication<MyFitness…tString(R.string.addFood)");
        return string;
    }

    @NotNull
    public final MutableLiveData<Integer> getMultiAddItemsCount() {
        return this.multiAddItemsCount;
    }

    @NotNull
    public final MultiAddFoodHelper getMultiAddHelper() {
        return this.multiAddHelper;
    }

    @NotNull
    public final ArrayList<FoodEntry> getSelectedMultiAddEntries() {
        List allSelectedItemsAsFoodEntries = this.multiAddHelper.getAllSelectedItemsAsFoodEntries(this.mealIngredientMapper, (DiaryService) this.diaryService.get(), (Session) this.session.get());
        if (allSelectedItemsAsFoodEntries != null) {
            return (ArrayList) allSelectedItemsAsFoodEntries;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.ArrayList<com.myfitnesspal.shared.model.v1.FoodEntry!>");
    }

    @NotNull
    public final Availability getQuickAddMacrosAvailability() {
        Availability featureAvailability = ((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.QuickAddMacros);
        Intrinsics.checkExpressionValueIsNotNull(featureAvailability, "premiumService.get().get…umFeature.QuickAddMacros)");
        return featureAvailability;
    }

    public final boolean isRecipeParsingEnabled() {
        Object obj = this.recipeService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "recipeService.get()");
        return ((RecipeService) obj).isRecipeParsingEnabledForCurrentLocale();
    }

    public final boolean isTimestampFeatureEnabled() {
        return ((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.FoodTimestamps) && this.localSettingsService.shouldShowFoodTimestamps();
    }

    @NotNull
    public final Date getCurrentActiveDate() {
        Date activeDate = ((Session) this.session.get()).getUser().getActiveDate();
        Intrinsics.checkExpressionValueIsNotNull(activeDate, "session.get().user.activeDate");
        return activeDate;
    }

    public final boolean isEnglishCurrentLanguage() {
        return this.isEnglishCurrentLanguage;
    }

    @NotNull
    public final String getSearchFlowId() {
        return this.searchFlowId;
    }

    @NotNull
    public final FoodSearchTab getDefaultSearchTab() {
        FoodSearchTab foodSearchTab = this.defaultSearchTab;
        if (foodSearchTab == null) {
            Intrinsics.throwUninitializedPropertyAccessException("defaultSearchTab");
        }
        return foodSearchTab;
    }

    public final void setDefaultSearchTab(@NotNull FoodSearchTab foodSearchTab) {
        Intrinsics.checkParameterIsNotNull(foodSearchTab, "<set-?>");
        this.defaultSearchTab = foodSearchTab;
    }

    @Nullable
    public final String getMealName() {
        return this.mealName;
    }

    public final void setMealName(@Nullable String str) {
        this.mealName = str;
    }

    public final boolean getShouldShowWalkthrough() {
        Extras extras2 = this.extras;
        if (extras2 != null && extras2.getShowWalkthrough()) {
            return true;
        }
        boolean z = false;
        boolean z2 = ConfigUtils.isSearchWalkthroughForExistingUserEnabled(this.configService) && !this.localSettingsService.didUserSeeFoodSearchWalkthrough();
        boolean hasLoggedAtLeastOneItem = ((DbConnectionManager) this.dbConnectionManager.get()).foodEntriesDbAdapter().hasLoggedAtLeastOneItem();
        if (z2 && hasLoggedAtLeastOneItem) {
            z = true;
        } else if (z2 && !hasLoggedAtLeastOneItem) {
            this.localSettingsService.userSawFoodSearchWalkthrough();
        }
        return z;
    }

    public final void setShouldShowWalkthrough(boolean z) {
        if (!z) {
            this.localSettingsService.userSawFoodSearchWalkthrough();
        }
    }

    @Nullable
    public final Extras getExtras() {
        return this.extras;
    }

    public final void setExtras(@Nullable Extras extras2) {
        FoodSearchTab foodSearchTab;
        this.extras = extras2;
        if (extras2 != null) {
            if (extras2.getHasMealIndex()) {
                this.mealName = ((Session) this.session.get()).getUser().getMealNames().nameForIndex(extras2.getMealIndex());
            } else if (extras2.getMealName() != null) {
                this.mealName = extras2.getMealName();
            }
            if (extras2.getShouldSelectMealTab()) {
                foodSearchTab = FoodSearchTab.MEALS;
            } else {
                foodSearchTab = FoodSearchTab.Companion.fromTabId(this.localSettingsService.getDefaultSearchTab());
                if (foodSearchTab != null) {
                    switch (foodSearchTab) {
                        case RECENT:
                        case FREQUENT:
                            break;
                    }
                }
                foodSearchTab = FoodSearchTab.ALL;
            }
            this.defaultSearchTab = foodSearchTab;
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        this.disposable.dispose();
        super.onCleared();
    }

    public final void saveEntriesToDiary(@Nullable Date date, @Nullable TimestampOption timestampOption) {
        TimeValue timeValue;
        this.multiAddHelper.addAllSelectedEntriesToDiaryWithMealName(this.mealName, this.mealIngredientMapper, (DiaryService) this.diaryService.get(), (Session) this.session.get(), date);
        String str = this.mealName;
        if (str != null) {
            FoodSearchAnalyticsHelper foodSearchAnalyticsHelper2 = (FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get();
            String str2 = this.searchFlowId;
            Tuple2 mealAndRecipeFoodCount = this.multiAddHelper.getMealAndRecipeFoodCount();
            Intrinsics.checkExpressionValueIsNotNull(mealAndRecipeFoodCount, "multiAddHelper.mealAndRecipeFoodCount");
            if (timestampOption != null) {
                timeValue = TimeValue.Companion.fromTimestampOption(timestampOption);
            } else {
                timeValue = TimeValue.NO_TIME;
            }
            foodSearchAnalyticsHelper2.fireFoodLoggedFromMultiAdd(str2, str, mealAndRecipeFoodCount, timeValue);
        }
    }

    public final void addOrUpdateQuickFoodEntry(@Nullable QuickAddFood quickAddFood, @NotNull Function1<? super Resource<Void>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "resultAction");
        FoodEntry quickAddedPremiumFoodEntry = FoodEntry.quickAddedPremiumFoodEntry(((Session) this.session.get()).getUser(), quickAddFood, this.mealName, (DbConnectionManager) this.dbConnectionManager.get());
        if (quickAddedPremiumFoodEntry == null) {
            function1.invoke(new Error(new Throwable(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.QUICK_ADD_FAIL, this.userEnergyService.get()))));
        }
        if (this.multiAddHelper.isMultiAddModeOn()) {
            this.multiAddHelper.addItem(quickAddedPremiumFoodEntry);
            updateMultiAddSelectionCount();
            return;
        }
        Object obj = this.diaryService.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "diaryService.get()");
        ((DiaryService) obj).getDiaryDayForActiveDateSync().addFoodEntry(quickAddedPremiumFoodEntry);
        ((UacfScheduler) this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
        function1.invoke(new Success(null));
    }

    @NotNull
    public final ArrayList<FoodEntry> packMealIngredientsAsFoodEntries(@Nullable Intent intent) {
        ArrayList<FoodEntry> arrayList = new ArrayList<>();
        Bundle bundle = null;
        FoodEntry foodEntry = (FoodEntry) BundleUtils.getParcelable(intent != null ? intent.getExtras() : null, "food_entry", FoodEntry.class.getClassLoader());
        if (intent != null) {
            bundle = intent.getExtras();
        }
        ArrayList parcelableArrayList = BundleUtils.getParcelableArrayList(bundle, MealEditorMixin.EXTRA_FOOD_ENTRIES, FoodEntry.class.getClassLoader());
        if (parcelableArrayList != null) {
            Collection collection = parcelableArrayList;
            if (!collection.isEmpty()) {
                arrayList.addAll(collection);
            }
        }
        if (foodEntry != null) {
            arrayList.add(foodEntry);
        }
        return arrayList;
    }

    public final void toggleMultiAdd(boolean z) {
        if (z) {
            this.multiAddHelper.enable();
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.FOOD_LOGGED, "version", String.valueOf(2));
        } else {
            this.multiAddHelper.disable();
            ((ActionTrackingService) this.actionTrackingService.get()).deleteEvent(Events.FOOD_LOGGED);
        }
        reportMultiAddButtonToggled(z);
        updateMultiAddSelectionCount();
    }

    public final void addAndLogItemsFromActivityResultData(@Nullable Intent intent) {
        this.multiAddHelper.addAndLogItemsFromActivityResultData(intent != null ? intent.getExtras() : null, this.foodEditorAnalytics);
    }

    public final void reportRecipeFlowStarted(@NotNull RecipeAnalyticsIntentData recipeAnalyticsIntentData) {
        Intrinsics.checkParameterIsNotNull(recipeAnalyticsIntentData, "data");
        ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportRecipeFlowStarted(recipeAnalyticsIntentData);
    }

    public final void reportRecipeImportShown(@NotNull RecipeAnalyticsIntentData recipeAnalyticsIntentData) {
        Intrinsics.checkParameterIsNotNull(recipeAnalyticsIntentData, "data");
        ((RecipesAnalyticsHelper) this.recipesAnalyticsHelper.get()).reportImportRecipe(recipeAnalyticsIntentData, "manual");
    }

    public final void reportToolbarPlusClicked() {
        ((FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get()).reportToolbarPlusClicked();
    }

    public final void reportToolbarPlusItemSelected(@NotNull AddItemOption addItemOption) {
        Intrinsics.checkParameterIsNotNull(addItemOption, Attributes.ITEM);
        ((FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get()).reportToolbarPlusItemSelected(addItemOption);
    }

    public final void reportMultiAddLogged() {
        ((FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get()).reportAddAllClickAnalyticsEvent();
    }

    public final void reportExistingUserWalkthroughStepShowed(int i) {
        ((FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get()).reportExisitingUserSawTooltipWithIndex(i);
    }

    public final void appendBarcodeEventToOnlineSearchSummary() {
        ((FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get()).reportBarcodeEvent();
    }

    /* access modifiers changed from: private */
    public final void updateMultiAddSelectionCount() {
        this.multiAddItemsCount.setValue(Integer.valueOf(this.multiAddHelper.totalItemCount()));
    }

    private final void reportMultiAddButtonToggled(boolean z) {
        ((FoodSearchAnalyticsHelper) this.foodSearchAnalyticsHelper.get()).reportMultiAddEnabled(z);
    }
}
