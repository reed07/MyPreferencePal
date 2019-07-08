package com.myfitnesspal.feature.search.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.StringRes;
import android.support.constraint.Group;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FrameMetricsAggregator;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.view.ActionMode;
import android.support.v7.view.ActionMode.Callback;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.util.SparseArrayKt;
import com.brightcove.player.event.AbstractEvent;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.common.Constants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.activity.AddFood;
import com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity;
import com.myfitnesspal.feature.barcode.util.BarcodeUtil;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.ActionType;
import com.myfitnesspal.feature.recipes.model.RecipeAnalyticsIntentData.StartScreen;
import com.myfitnesspal.feature.recipes.ui.activity.CreateRecipeManuallyActivity;
import com.myfitnesspal.feature.recipes.ui.dialog.CreateRecipeDialogFragment;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.dialog.AddItemBottomSheet;
import com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Trigger;
import com.myfitnesspal.feature.search.ui.viewmodel.FoodSearchViewModel;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin.OnTimestampChangedListener;
import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.model.AdUnit;
import com.myfitnesspal.shared.model.QuickAddFood;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.QuickAddCaloriesDialogFragment;
import com.myfitnesspal.shared.ui.factory.VMFactory;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.view.ClearableEditText;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u0000 ?2\u00020\u0001:\u0005?@ABCB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\b\u0010!\u001a\u00020\u001fH\u0002J\b\u0010\"\u001a\u00020\u001fH\u0002J\b\u0010#\u001a\u00020\u001fH\u0002J\b\u0010$\u001a\u00020\u001fH\u0002J\b\u0010%\u001a\u00020\u001fH\u0002J\b\u0010&\u001a\u00020\u001fH\u0002J\b\u0010'\u001a\u00020\u001fH\u0002J\"\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\u001d2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\u0012\u0010-\u001a\u00020\u001f2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\u0012\u00100\u001a\u00020\u001f2\b\u00101\u001a\u0004\u0018\u00010,H\u0014J\u0012\u00102\u001a\u00020\u00042\b\u00103\u001a\u0004\u0018\u000104H\u0016J\u0012\u00105\u001a\u00020\u00042\b\u00106\u001a\u0004\u0018\u000107H\u0016J\u001a\u00108\u001a\u00020\u001f2\u0006\u0010*\u001a\u00020\u001d2\b\u0010+\u001a\u0004\u0018\u00010,H\u0002J\b\u00109\u001a\u00020\u001fH\u0002J\u0012\u0010:\u001a\u00020\u001f2\b\u0010;\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010<\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020\u001bH\u0002J\u000e\u0010>\u001a\u00020\u001f2\u0006\u0010=\u001a\u00020\u001bR\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0018\u00010\u0011R\u00020\u0000X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00138\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006D"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "value", "", "isShowingOnlineSearch", "setShowingOnlineSearch", "(Z)V", "multiAddActionMode", "Landroid/support/v7/view/ActionMode;", "searchTrigger", "Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Trigger;", "timestampPickerMixin", "Lcom/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin;", "viewModel", "Lcom/myfitnesspal/feature/search/ui/viewmodel/FoodSearchViewModel;", "viewPagerAdapter", "Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$ViewPagerAdapter;", "vmFactory", "Lcom/myfitnesspal/shared/ui/factory/VMFactory;", "getVmFactory", "()Lcom/myfitnesspal/shared/ui/factory/VMFactory;", "setVmFactory", "(Lcom/myfitnesspal/shared/ui/factory/VMFactory;)V", "getAdUnit", "Lcom/myfitnesspal/shared/model/AdUnit;", "getAnalyticsScreenTag", "", "getCustomBaseLayoutResId", "", "initButtonListeners", "", "initTextWatcher", "initViewPager", "navigateToCreateFood", "navigateToCreateMeal", "navigateToCreateRecipe", "navigateToDiary", "navigateToQuickAdd", "navigateToScanBarcode", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "onScanBarcode", "showExistingUserWalkthrough", "showOnlineSearch", "trigger", "updateLocalFragmentQueryString", "query", "updateSearchElements", "Companion", "Extras", "MultiAddActionMode", "TimestampPickerListener", "ViewPagerAdapter", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodSearchActivityV2.kt */
public final class FoodSearchActivityV2 extends MfpActivity {
    public static final Companion Companion = new Companion(null);
    private static final String FRAGMENT_TAD_ADD_ITEM = "dialog_add_item";
    private static final int MENU_ITEM_ID_CREATE_ITEM = 101;
    private static final int MENU_ITEM_ID_MULTI_ADD = 100;
    private static final int MENU_ITEM_ID_MULTI_ADD_ADD_ALL = 102;
    private static final long PAGER_ITEM_ID_ONLINE_FRAGMENT = 100;
    private static final int POSITION_MENU_PLUS_SIGN = 1;
    private static final int POSITION_TAB_ALL = 0;
    private static final String REFERRER_FOOD_SEARCH = "food_search";
    private static final int REQUEST_CODE_ADD_FOOD = 1004;
    private static final int REQUEST_CODE_FOOD_EDITOR = 1005;
    private static final int REQUEST_CODE_QUICK_ADD = 1002;
    private static final int REQUEST_CODE_RECIPE_IMPORTER = 1006;
    private static final int REQUEST_CODE_SCAN_BARCODE = 1003;
    private static final int REQUEST_CODE_VENUE_LIST = 1001;
    private static final int SCROLL_FLAG_NO_SCROLL = 0;
    private static final int TAB_LAYOUT_MINIMAL_WIDTH_IN_PX = 720;
    /* access modifiers changed from: private */
    public static final HashMap<FoodSearchTab, Integer> tabToHintMap = MapsKt.hashMapOf(TuplesKt.to(FoodSearchTab.ALL, Integer.valueOf(R.string.search_for_a_food)), TuplesKt.to(FoodSearchTab.RECIPES, Integer.valueOf(R.string.search_for_a_recipe)), TuplesKt.to(FoodSearchTab.MEALS, Integer.valueOf(R.string.search_for_a_meal)), TuplesKt.to(FoodSearchTab.MY_FOODS, Integer.valueOf(R.string.search_for_a_food)));
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public boolean isShowingOnlineSearch;
    /* access modifiers changed from: private */
    public ActionMode multiAddActionMode;
    private Trigger searchTrigger;
    /* access modifiers changed from: private */
    public TimestampPickerMixin timestampPickerMixin;
    /* access modifiers changed from: private */
    public FoodSearchViewModel viewModel;
    /* access modifiers changed from: private */
    public ViewPagerAdapter viewPagerAdapter;
    @Inject
    @NotNull
    public VMFactory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R*\u0010\u0016\u001a\u001e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00060\u0017j\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0006`\u0019X\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$Companion;", "", "()V", "FRAGMENT_TAD_ADD_ITEM", "", "MENU_ITEM_ID_CREATE_ITEM", "", "MENU_ITEM_ID_MULTI_ADD", "MENU_ITEM_ID_MULTI_ADD_ADD_ALL", "PAGER_ITEM_ID_ONLINE_FRAGMENT", "", "POSITION_MENU_PLUS_SIGN", "POSITION_TAB_ALL", "REFERRER_FOOD_SEARCH", "REQUEST_CODE_ADD_FOOD", "REQUEST_CODE_FOOD_EDITOR", "REQUEST_CODE_QUICK_ADD", "REQUEST_CODE_RECIPE_IMPORTER", "REQUEST_CODE_SCAN_BARCODE", "REQUEST_CODE_VENUE_LIST", "SCROLL_FLAG_NO_SCROLL", "TAB_LAYOUT_MINIMAL_WIDTH_IN_PX", "tabToHintMap", "Ljava/util/HashMap;", "Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "Lkotlin/collections/HashMap;", "newStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "extras", "Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$Extras;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodSearchActivityV2.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Intent newStartIntent(@NotNull Context context, @Nullable Extras extras) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intent intent = new Intent(context, FoodSearchActivityV2.class);
            if (extras != null) {
                extras.writeToIntent(intent);
            }
            return intent;
        }
    }

    @Parcelize
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u0000 42\u00020\u0001:\u00014Be\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001f\u001a\u0004\u0018\u00010\bHÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\rHÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003Ji\u0010%\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÆ\u0001J\t\u0010&\u001a\u00020\u0006HÖ\u0001J\u0013\u0010'\u001a\u00020\u00032\b\u0010(\u001a\u0004\u0018\u00010)HÖ\u0003J\t\u0010*\u001a\u00020\u0006HÖ\u0001J\t\u0010+\u001a\u00020\bHÖ\u0001J\u000e\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/J\u0019\u00100\u001a\u00020-2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0010\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0012R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0012¨\u00065"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$Extras;", "Landroid/os/Parcelable;", "isMealCreated", "", "isMealEdited", "mealIndex", "", "mealName", "", "shouldSelectMealTab", "isInMealFoodCreationFlow", "mealFoodCreationFlowId", "latestMealEntryTime", "Ljava/util/Date;", "showWalkthrough", "(ZZILjava/lang/String;ZZLjava/lang/String;Ljava/util/Date;Z)V", "hasMealIndex", "getHasMealIndex", "()Z", "getLatestMealEntryTime", "()Ljava/util/Date;", "getMealFoodCreationFlowId", "()Ljava/lang/String;", "getMealIndex", "()I", "getMealName", "getShouldSelectMealTab", "getShowWalkthrough", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToIntent", "", "intent", "Landroid/content/Intent;", "writeToParcel", "parcel", "Landroid/os/Parcel;", "flags", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodSearchActivityV2.kt */
    public static final class Extras implements Parcelable {
        public static final android.os.Parcelable.Creator CREATOR = new Creator();
        public static final Companion Companion = new Companion(null);
        private static final String EXTRA_IS_IN_MEAL_CREATION_FLOW = "is_in_meal_creation_flow";
        private static final String EXTRA_IS_MEAL_CREATED = "is_meal_created";
        private static final String EXTRA_IS_MEAL_EDITED = "is_meal_edited";
        private static final String EXTRA_LATEST_MEAL_ENTRY_TIME = "latest_meal_creation_time";
        private static final String EXTRA_MEAL_CREATION_FLOW_ID = "meal_creation_flow_id";
        private static final String EXTRA_MEAL_INDEX = "user_meal_index";
        private static final String EXTRA_MEAL_NAME = "user_meal_name";
        private static final String EXTRA_SHOULD_SELECT_MEAL_TAB = "should_select_meal_tab";
        private static final String EXTRA_SHOW_WALKTHROUGH = "show_walkthrough";
        private final boolean isInMealFoodCreationFlow;
        private final boolean isMealCreated;
        private final boolean isMealEdited;
        @Nullable
        private final Date latestMealEntryTime;
        @Nullable
        private final String mealFoodCreationFlowId;
        private final int mealIndex;
        @Nullable
        private final String mealName;
        private final boolean shouldSelectMealTab;
        private final boolean showWalkthrough;

        @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$Extras$Companion;", "", "()V", "EXTRA_IS_IN_MEAL_CREATION_FLOW", "", "EXTRA_IS_MEAL_CREATED", "EXTRA_IS_MEAL_EDITED", "EXTRA_LATEST_MEAL_ENTRY_TIME", "EXTRA_MEAL_CREATION_FLOW_ID", "EXTRA_MEAL_INDEX", "EXTRA_MEAL_NAME", "EXTRA_SHOULD_SELECT_MEAL_TAB", "EXTRA_SHOW_WALKTHROUGH", "fromIntent", "Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$Extras;", "intent", "Landroid/content/Intent;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
        /* compiled from: FoodSearchActivityV2.kt */
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @NotNull
            public final Extras fromIntent(@NotNull Intent intent) {
                Intrinsics.checkParameterIsNotNull(intent, Constants.INTENT_SCHEME);
                boolean booleanExtra = intent.getBooleanExtra(Extras.EXTRA_IS_MEAL_CREATED, false);
                boolean booleanExtra2 = intent.getBooleanExtra(Extras.EXTRA_IS_MEAL_EDITED, false);
                int intExtra = intent.getIntExtra(Extras.EXTRA_MEAL_INDEX, Integer.MIN_VALUE);
                String stringExtra = intent.getStringExtra(Extras.EXTRA_MEAL_NAME);
                boolean booleanExtra3 = intent.getBooleanExtra(Extras.EXTRA_SHOULD_SELECT_MEAL_TAB, false);
                boolean booleanExtra4 = intent.getBooleanExtra(Extras.EXTRA_IS_IN_MEAL_CREATION_FLOW, false);
                String stringExtra2 = intent.getStringExtra(Extras.EXTRA_MEAL_CREATION_FLOW_ID);
                Serializable serializableExtra = intent.getSerializableExtra(Extras.EXTRA_LATEST_MEAL_ENTRY_TIME);
                if (!(serializableExtra instanceof Date)) {
                    serializableExtra = null;
                }
                Extras extras = new Extras(booleanExtra, booleanExtra2, intExtra, stringExtra, booleanExtra3, booleanExtra4, stringExtra2, (Date) serializableExtra, intent.getBooleanExtra(Extras.EXTRA_SHOW_WALKTHROUGH, false));
                return extras;
            }
        }

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
        public static class Creator implements android.os.Parcelable.Creator {
            @NotNull
            public final Object createFromParcel(@NotNull Parcel parcel) {
                Intrinsics.checkParameterIsNotNull(parcel, "in");
                Extras extras = new Extras(parcel.readInt() != 0, parcel.readInt() != 0, parcel.readInt(), parcel.readString(), parcel.readInt() != 0, parcel.readInt() != 0, parcel.readString(), (Date) parcel.readSerializable(), parcel.readInt() != 0);
                return extras;
            }

            @NotNull
            public final Object[] newArray(int i) {
                return new Extras[i];
            }
        }

        public Extras() {
            this(false, false, 0, null, false, false, null, null, false, FrameMetricsAggregator.EVERY_DURATION, null);
        }

        @NotNull
        public static /* synthetic */ Extras copy$default(Extras extras, boolean z, boolean z2, int i, String str, boolean z3, boolean z4, String str2, Date date, boolean z5, int i2, Object obj) {
            Extras extras2 = extras;
            int i3 = i2;
            return extras.copy((i3 & 1) != 0 ? extras2.isMealCreated : z, (i3 & 2) != 0 ? extras2.isMealEdited : z2, (i3 & 4) != 0 ? extras2.mealIndex : i, (i3 & 8) != 0 ? extras2.mealName : str, (i3 & 16) != 0 ? extras2.shouldSelectMealTab : z3, (i3 & 32) != 0 ? extras2.isInMealFoodCreationFlow : z4, (i3 & 64) != 0 ? extras2.mealFoodCreationFlowId : str2, (i3 & 128) != 0 ? extras2.latestMealEntryTime : date, (i3 & 256) != 0 ? extras2.showWalkthrough : z5);
        }

        public final boolean component1() {
            return this.isMealCreated;
        }

        public final boolean component2() {
            return this.isMealEdited;
        }

        public final int component3() {
            return this.mealIndex;
        }

        @Nullable
        public final String component4() {
            return this.mealName;
        }

        public final boolean component5() {
            return this.shouldSelectMealTab;
        }

        public final boolean component6() {
            return this.isInMealFoodCreationFlow;
        }

        @Nullable
        public final String component7() {
            return this.mealFoodCreationFlowId;
        }

        @Nullable
        public final Date component8() {
            return this.latestMealEntryTime;
        }

        public final boolean component9() {
            return this.showWalkthrough;
        }

        @NotNull
        public final Extras copy(boolean z, boolean z2, int i, @Nullable String str, boolean z3, boolean z4, @Nullable String str2, @Nullable Date date, boolean z5) {
            Extras extras = new Extras(z, z2, i, str, z3, z4, str2, date, z5);
            return extras;
        }

        public int describeContents() {
            return 0;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof Extras) {
                    Extras extras = (Extras) obj;
                    if (this.isMealCreated == extras.isMealCreated) {
                        if (this.isMealEdited == extras.isMealEdited) {
                            if ((this.mealIndex == extras.mealIndex) && Intrinsics.areEqual((Object) this.mealName, (Object) extras.mealName)) {
                                if (this.shouldSelectMealTab == extras.shouldSelectMealTab) {
                                    if ((this.isInMealFoodCreationFlow == extras.isInMealFoodCreationFlow) && Intrinsics.areEqual((Object) this.mealFoodCreationFlowId, (Object) extras.mealFoodCreationFlowId) && Intrinsics.areEqual((Object) this.latestMealEntryTime, (Object) extras.latestMealEntryTime)) {
                                        if (this.showWalkthrough == extras.showWalkthrough) {
                                            return true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            boolean z = this.isMealCreated;
            int i = 1;
            if (z) {
                z = true;
            }
            int i2 = (z ? 1 : 0) * true;
            boolean z2 = this.isMealEdited;
            if (z2) {
                z2 = true;
            }
            int hashCode = (((i2 + (z2 ? 1 : 0)) * 31) + Integer.hashCode(this.mealIndex)) * 31;
            String str = this.mealName;
            int i3 = 0;
            int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
            boolean z3 = this.shouldSelectMealTab;
            if (z3) {
                z3 = true;
            }
            int i4 = (hashCode2 + (z3 ? 1 : 0)) * 31;
            boolean z4 = this.isInMealFoodCreationFlow;
            if (z4) {
                z4 = true;
            }
            int i5 = (i4 + (z4 ? 1 : 0)) * 31;
            String str2 = this.mealFoodCreationFlowId;
            int hashCode3 = (i5 + (str2 != null ? str2.hashCode() : 0)) * 31;
            Date date = this.latestMealEntryTime;
            if (date != null) {
                i3 = date.hashCode();
            }
            int i6 = (hashCode3 + i3) * 31;
            boolean z5 = this.showWalkthrough;
            if (!z5) {
                i = z5;
            }
            return i6 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Extras(isMealCreated=");
            sb.append(this.isMealCreated);
            sb.append(", isMealEdited=");
            sb.append(this.isMealEdited);
            sb.append(", mealIndex=");
            sb.append(this.mealIndex);
            sb.append(", mealName=");
            sb.append(this.mealName);
            sb.append(", shouldSelectMealTab=");
            sb.append(this.shouldSelectMealTab);
            sb.append(", isInMealFoodCreationFlow=");
            sb.append(this.isInMealFoodCreationFlow);
            sb.append(", mealFoodCreationFlowId=");
            sb.append(this.mealFoodCreationFlowId);
            sb.append(", latestMealEntryTime=");
            sb.append(this.latestMealEntryTime);
            sb.append(", showWalkthrough=");
            sb.append(this.showWalkthrough);
            sb.append(")");
            return sb.toString();
        }

        public void writeToParcel(@NotNull Parcel parcel, int i) {
            Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
            parcel.writeInt(this.isMealCreated ? 1 : 0);
            parcel.writeInt(this.isMealEdited ? 1 : 0);
            parcel.writeInt(this.mealIndex);
            parcel.writeString(this.mealName);
            parcel.writeInt(this.shouldSelectMealTab ? 1 : 0);
            parcel.writeInt(this.isInMealFoodCreationFlow ? 1 : 0);
            parcel.writeString(this.mealFoodCreationFlowId);
            parcel.writeSerializable(this.latestMealEntryTime);
            parcel.writeInt(this.showWalkthrough ? 1 : 0);
        }

        public Extras(boolean z, boolean z2, int i, @Nullable String str, boolean z3, boolean z4, @Nullable String str2, @Nullable Date date, boolean z5) {
            this.isMealCreated = z;
            this.isMealEdited = z2;
            this.mealIndex = i;
            this.mealName = str;
            this.shouldSelectMealTab = z3;
            this.isInMealFoodCreationFlow = z4;
            this.mealFoodCreationFlowId = str2;
            this.latestMealEntryTime = date;
            this.showWalkthrough = z5;
        }

        public final boolean isMealCreated() {
            return this.isMealCreated;
        }

        public final boolean isMealEdited() {
            return this.isMealEdited;
        }

        public final int getMealIndex() {
            return this.mealIndex;
        }

        public /* synthetic */ Extras(boolean z, boolean z2, int i, String str, boolean z3, boolean z4, String str2, Date date, boolean z5, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            int i3 = i2;
            boolean z6 = false;
            boolean z7 = (i3 & 1) != 0 ? false : z;
            boolean z8 = (i3 & 2) != 0 ? false : z2;
            int i4 = (i3 & 4) != 0 ? Integer.MIN_VALUE : i;
            String str3 = (i3 & 8) != 0 ? null : str;
            boolean z9 = (i3 & 16) != 0 ? false : z3;
            boolean z10 = (i3 & 32) != 0 ? false : z4;
            String str4 = (i3 & 64) != 0 ? null : str2;
            Date date2 = (i3 & 128) != 0 ? null : date;
            if ((i3 & 256) == 0) {
                z6 = z5;
            }
            this(z7, z8, i4, str3, z9, z10, str4, date2, z6);
        }

        @Nullable
        public final String getMealName() {
            return this.mealName;
        }

        public final boolean getShouldSelectMealTab() {
            return this.shouldSelectMealTab;
        }

        public final boolean isInMealFoodCreationFlow() {
            return this.isInMealFoodCreationFlow;
        }

        @Nullable
        public final String getMealFoodCreationFlowId() {
            return this.mealFoodCreationFlowId;
        }

        @Nullable
        public final Date getLatestMealEntryTime() {
            return this.latestMealEntryTime;
        }

        public final boolean getShowWalkthrough() {
            return this.showWalkthrough;
        }

        public final boolean getHasMealIndex() {
            return this.mealIndex != Integer.MIN_VALUE;
        }

        public final void writeToIntent(@NotNull Intent intent) {
            Intrinsics.checkParameterIsNotNull(intent, Constants.INTENT_SCHEME);
            intent.putExtra(EXTRA_IS_MEAL_CREATED, this.isMealCreated);
            intent.putExtra(EXTRA_IS_MEAL_EDITED, this.isMealEdited);
            intent.putExtra(EXTRA_MEAL_INDEX, this.mealIndex);
            intent.putExtra(EXTRA_MEAL_NAME, this.mealName);
            intent.putExtra(EXTRA_SHOULD_SELECT_MEAL_TAB, this.shouldSelectMealTab);
            intent.putExtra(EXTRA_IS_IN_MEAL_CREATION_FLOW, this.isInMealFoodCreationFlow);
            intent.putExtra(EXTRA_MEAL_CREATION_FLOW_ID, this.mealFoodCreationFlowId);
            intent.putExtra(EXTRA_LATEST_MEAL_ENTRY_TIME, this.latestMealEntryTime);
            intent.putExtra(EXTRA_SHOW_WALKTHROUGH, this.showWalkthrough);
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0018\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\u000f\u001a\u00020\rH\u0002J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0004H\u0002¨\u0006\u0012"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$MultiAddActionMode;", "Landroid/support/v7/view/ActionMode$Callback;", "(Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2;)V", "onActionItemClicked", "", "mode", "Landroid/support/v7/view/ActionMode;", "item", "Landroid/view/MenuItem;", "onCreateActionMode", "menu", "Landroid/view/Menu;", "onDestroyActionMode", "", "onPrepareActionMode", "saveEntriesToMeal", "toggleToolbarScrolling", "isEnabled", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodSearchActivityV2.kt */
    private final class MultiAddActionMode implements Callback {
        public MultiAddActionMode() {
        }

        public boolean onCreateActionMode(@NotNull ActionMode actionMode, @NotNull Menu menu) {
            Intrinsics.checkParameterIsNotNull(actionMode, InternalAvidAdSessionContext.CONTEXT_MODE);
            Intrinsics.checkParameterIsNotNull(menu, "menu");
            FoodSearchActivityV2.this.multiAddActionMode = actionMode;
            FoodSearchActivityV2.access$getViewModel$p(FoodSearchActivityV2.this).toggleMultiAdd(true);
            toggleToolbarScrolling(false);
            return true;
        }

        public boolean onPrepareActionMode(@NotNull ActionMode actionMode, @NotNull Menu menu) {
            Intrinsics.checkParameterIsNotNull(actionMode, InternalAvidAdSessionContext.CONTEXT_MODE);
            Intrinsics.checkParameterIsNotNull(menu, "menu");
            menu.clear();
            boolean z = FoodSearchActivityV2.access$getViewModel$p(FoodSearchActivityV2.this).getMultiAddHelper().totalItemCount() > 0;
            menu.add(0, 102, 0, R.string.add_selected).setIcon(z ? R.drawable.ic_check_white_24dp : R.drawable.ic_check_disabled_white_24dp).setEnabled(z).setShowAsAction(2);
            return true;
        }

        public boolean onActionItemClicked(@NotNull ActionMode actionMode, @NotNull MenuItem menuItem) {
            Intrinsics.checkParameterIsNotNull(actionMode, InternalAvidAdSessionContext.CONTEXT_MODE);
            Intrinsics.checkParameterIsNotNull(menuItem, Attributes.ITEM);
            if (menuItem.getItemId() != 102) {
                return false;
            }
            FoodSearchActivityV2.access$getViewModel$p(FoodSearchActivityV2.this).reportMultiAddLogged();
            Extras extras = FoodSearchActivityV2.access$getViewModel$p(FoodSearchActivityV2.this).getExtras();
            if (extras != null && extras.isInMealFoodCreationFlow()) {
                saveEntriesToMeal();
                actionMode.finish();
            } else if (FoodSearchActivityV2.access$getViewModel$p(FoodSearchActivityV2.this).isTimestampFeatureEnabled()) {
                FoodSearchActivityV2.access$getTimestampPickerMixin$p(FoodSearchActivityV2.this).showTimestampOptions();
            } else {
                FoodSearchActivityV2.access$getViewModel$p(FoodSearchActivityV2.this).saveEntriesToDiary(null, null);
                actionMode.finish();
                FoodSearchActivityV2.this.navigateToDiary();
            }
            return true;
        }

        public void onDestroyActionMode(@NotNull ActionMode actionMode) {
            Intrinsics.checkParameterIsNotNull(actionMode, InternalAvidAdSessionContext.CONTEXT_MODE);
            FoodSearchActivityV2.access$getViewModel$p(FoodSearchActivityV2.this).toggleMultiAdd(false);
            FoodSearchActivityV2.this.multiAddActionMode = null;
            toggleToolbarScrolling(true);
        }

        private final void saveEntriesToMeal() {
            FoodSearchActivityV2 foodSearchActivityV2 = FoodSearchActivityV2.this;
            Intent intent = new Intent();
            intent.putExtra(MealEditorMixin.EXTRA_FOOD_ENTRIES, FoodSearchActivityV2.access$getViewModel$p(FoodSearchActivityV2.this).getSelectedMultiAddEntries());
            foodSearchActivityV2.setResult(-1, intent);
            FoodSearchActivityV2.this.finish();
        }

        private final void toggleToolbarScrolling(boolean z) {
            AppBarLayout appBarLayout = (AppBarLayout) FoodSearchActivityV2.this._$_findCachedViewById(R.id.toolbar_container);
            Intrinsics.checkExpressionValueIsNotNull(appBarLayout, "toolbar_container");
            AppBarLayout appBarLayout2 = (AppBarLayout) FoodSearchActivityV2.this._$_findCachedViewById(R.id.toolbar_container);
            Intrinsics.checkExpressionValueIsNotNull(appBarLayout2, "toolbar_container");
            LayoutParams layoutParams = appBarLayout2.getLayoutParams();
            if (layoutParams != null) {
                AppBarLayout.LayoutParams layoutParams2 = (AppBarLayout.LayoutParams) layoutParams;
                layoutParams2.setScrollFlags(z ? 1 : 0);
                appBarLayout.setLayoutParams(layoutParams2);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.support.design.widget.AppBarLayout.LayoutParams");
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$TimestampPickerListener;", "Lcom/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$OnTimestampChangedListener;", "(Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2;)V", "onTimestampChange", "", "time", "Ljava/util/Date;", "selectedOption", "Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodSearchActivityV2.kt */
    private final class TimestampPickerListener implements OnTimestampChangedListener {
        public TimestampPickerListener() {
        }

        public void onTimestampChange(@Nullable Date date, @NotNull TimestampOption timestampOption) {
            Intrinsics.checkParameterIsNotNull(timestampOption, "selectedOption");
            FoodSearchActivityV2.access$getViewModel$p(FoodSearchActivityV2.this).saveEntriesToDiary(date, timestampOption);
            ActionMode access$getMultiAddActionMode$p = FoodSearchActivityV2.this.multiAddActionMode;
            if (access$getMultiAddActionMode$p != null) {
                access$getMultiAddActionMode$p.finish();
            }
            FoodSearchActivityV2.this.navigateToDiary();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0004\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020\u001dH\u0016J\u0010\u0010!\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\"\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001dH\u0007J\u0010\u0010#\u001a\u00020$2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010'\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u000e\u0010*\u001a\u00020\u001d2\u0006\u0010+\u001a\u00020\u0006J\u0018\u0010,\u001a\u00020\u001f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0006\u0010-\u001a\u00020\u0019J\u001a\u0010.\u001a\u00020\u00192\u0006\u0010/\u001a\u00020\u00112\n\b\u0002\u00100\u001a\u0004\u0018\u00010\u000fR\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0013\u0010\u0014R\u0019\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016¨\u00061"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2$ViewPagerAdapter;", "Landroid/support/v4/app/FragmentPagerAdapter;", "manager", "Landroid/support/v4/app/FragmentManager;", "tabs", "", "Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "(Lcom/myfitnesspal/feature/search/ui/activity/FoodSearchActivityV2;Landroid/support/v4/app/FragmentManager;[Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;)V", "instantiatedFragments", "Landroid/util/SparseArray;", "Ljava/lang/ref/WeakReference;", "Lcom/myfitnesspal/shared/ui/fragment/MfpFragment;", "onlineSearchFragment", "Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment;", "searchTrigger", "Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Trigger;", "value", "", "showOnlineSearch", "setShowOnlineSearch", "(Z)V", "getTabs", "()[Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "[Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "destroyItem", "", "container", "Landroid/view/ViewGroup;", "position", "", "fragment", "", "getCount", "getFragmentAtPosition", "getHintForPosition", "getItem", "Landroid/support/v4/app/Fragment;", "getItemId", "", "getItemPosition", "getPageTitle", "", "getTabPosition", "tab", "instantiateItem", "invalidateFragmentData", "toggleSearchFragment", "shouldShow", "trigger", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodSearchActivityV2.kt */
    private final class ViewPagerAdapter extends FragmentPagerAdapter {
        private final SparseArray<WeakReference<MfpFragment>> instantiatedFragments = new SparseArray<>();
        private OnlineFoodSearchFragment onlineSearchFragment;
        private Trigger searchTrigger;
        private boolean showOnlineSearch;
        @NotNull
        private final FoodSearchTab[] tabs;
        final /* synthetic */ FoodSearchActivityV2 this$0;

        public ViewPagerAdapter(FoodSearchActivityV2 foodSearchActivityV2, @NotNull FragmentManager fragmentManager, @NotNull FoodSearchTab[] foodSearchTabArr) {
            Intrinsics.checkParameterIsNotNull(fragmentManager, "manager");
            Intrinsics.checkParameterIsNotNull(foodSearchTabArr, "tabs");
            this.this$0 = foodSearchActivityV2;
            super(fragmentManager);
            this.tabs = foodSearchTabArr;
        }

        @NotNull
        public final FoodSearchTab[] getTabs() {
            return this.tabs;
        }

        private final void setShowOnlineSearch(boolean z) {
            if (z != this.showOnlineSearch) {
                this.showOnlineSearch = z;
                if (!z) {
                    OnlineFoodSearchFragment onlineFoodSearchFragment = this.onlineSearchFragment;
                    if (onlineFoodSearchFragment != null) {
                        this.this$0.getSupportFragmentManager().beginTransaction().remove(onlineFoodSearchFragment).commitNow();
                    }
                }
                notifyDataSetChanged();
            }
        }

        @NotNull
        public Fragment getItem(int i) {
            boolean z = false;
            if (i != 0 || !this.showOnlineSearch) {
                com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2.Companion companion = LocalFoodSearchFragmentV2.Companion;
                FoodSearchTab foodSearchTab = this.tabs[i];
                String mealName = FoodSearchActivityV2.access$getViewModel$p(this.this$0).getMealName();
                Extras extras = FoodSearchActivityV2.access$getViewModel$p(this.this$0).getExtras();
                if (extras != null && extras.isInMealFoodCreationFlow()) {
                    z = true;
                }
                return companion.newInstance(new com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2.Extras(foodSearchTab, mealName, z, FoodSearchActivityV2.access$getViewModel$p(this.this$0).getSearchFlowId()));
            }
            com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Extras extras2 = new com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Extras();
            ClearableEditText clearableEditText = (ClearableEditText) this.this$0._$_findCachedViewById(R.id.searchEditText);
            Intrinsics.checkExpressionValueIsNotNull(clearableEditText, "searchEditText");
            com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Extras query = extras2.setQuery(String.valueOf(clearableEditText.getText()));
            Extras extras3 = FoodSearchActivityV2.access$getViewModel$p(this.this$0).getExtras();
            String str = null;
            com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Extras mealName2 = query.setMealName(extras3 != null ? extras3.getMealName() : null);
            Extras extras4 = FoodSearchActivityV2.access$getViewModel$p(this.this$0).getExtras();
            com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Extras flowId = mealName2.setInMealFoodCreationFlow(extras4 != null ? extras4.isInMealFoodCreationFlow() : false).setFlowId(FoodSearchActivityV2.access$getViewModel$p(this.this$0).getSearchFlowId());
            Extras extras5 = FoodSearchActivityV2.access$getViewModel$p(this.this$0).getExtras();
            if (extras5 != null) {
                str = extras5.getMealFoodCreationFlowId();
            }
            OnlineFoodSearchFragment newInstance = OnlineFoodSearchFragment.newInstance(flowId.setMealFoodCreationFlowId(str).setSource(SearchSource.ONLINE).setListType(this.tabs[0].getListType()).setTrigger(this.searchTrigger));
            this.onlineSearchFragment = newInstance;
            Intrinsics.checkExpressionValueIsNotNull(newInstance, "OnlineFoodSearchFragment… = this\n                }");
            return newInstance;
        }

        public int getCount() {
            return this.tabs.length;
        }

        @NotNull
        public String getPageTitle(int i) {
            String string = this.this$0.getString(this.tabs[i].getLabelResId());
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(tabs[position].labelResId)");
            return string;
        }

        @NotNull
        public Object instantiateItem(@NotNull ViewGroup viewGroup, int i) {
            Intrinsics.checkParameterIsNotNull(viewGroup, "container");
            Object instantiateItem = super.instantiateItem(viewGroup, i);
            if (instantiateItem != null) {
                MfpFragment mfpFragment = (MfpFragment) instantiateItem;
                if (mfpFragment instanceof LocalFoodSearchFragmentV2) {
                    LocalFoodSearchFragmentV2 localFoodSearchFragmentV2 = (LocalFoodSearchFragmentV2) mfpFragment;
                    ClearableEditText clearableEditText = (ClearableEditText) this.this$0._$_findCachedViewById(R.id.searchEditText);
                    Intrinsics.checkExpressionValueIsNotNull(clearableEditText, "searchEditText");
                    localFoodSearchFragmentV2.filterResults(String.valueOf(clearableEditText.getText()));
                    localFoodSearchFragmentV2.setOnSearchRequested(new FoodSearchActivityV2$ViewPagerAdapter$instantiateItem$1(this));
                } else if (mfpFragment instanceof OnlineFoodSearchFragment) {
                    OnlineFoodSearchFragment onlineFoodSearchFragment = (OnlineFoodSearchFragment) mfpFragment;
                    ClearableEditText clearableEditText2 = (ClearableEditText) this.this$0._$_findCachedViewById(R.id.searchEditText);
                    Intrinsics.checkExpressionValueIsNotNull(clearableEditText2, "searchEditText");
                    onlineFoodSearchFragment.performSearch(String.valueOf(clearableEditText2.getText()), this.searchTrigger);
                }
                this.instantiatedFragments.put(i, new WeakReference(mfpFragment));
                return mfpFragment;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.shared.ui.fragment.MfpFragment");
        }

        public void destroyItem(@NotNull ViewGroup viewGroup, int i, @NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(viewGroup, "container");
            Intrinsics.checkParameterIsNotNull(obj, AbstractEvent.FRAGMENT);
            this.instantiatedFragments.remove(i);
            super.destroyItem(viewGroup, i, obj);
        }

        public long getItemId(int i) {
            return (i != 0 || !this.showOnlineSearch) ? (long) i : FoodSearchActivityV2.PAGER_ITEM_ID_ONLINE_FRAGMENT;
        }

        public int getItemPosition(@NotNull Object obj) {
            Intrinsics.checkParameterIsNotNull(obj, AbstractEvent.FRAGMENT);
            boolean z = obj instanceof LocalFoodSearchFragmentV2;
            return ((!z || !this.showOnlineSearch) && (z || this.showOnlineSearch)) ? -1 : -2;
        }

        public static /* synthetic */ void toggleSearchFragment$default(ViewPagerAdapter viewPagerAdapter, boolean z, Trigger trigger, int i, Object obj) {
            if ((i & 2) != 0) {
                trigger = null;
            }
            viewPagerAdapter.toggleSearchFragment(z, trigger);
        }

        public final void toggleSearchFragment(boolean z, @Nullable Trigger trigger) {
            this.searchTrigger = trigger;
            setShowOnlineSearch(z);
        }

        public final int getTabPosition(@NotNull FoodSearchTab foodSearchTab) {
            Intrinsics.checkParameterIsNotNull(foodSearchTab, Attributes.TAB);
            return ArraysKt.indexOf((Object[]) this.tabs, (Object) foodSearchTab);
        }

        @StringRes
        public final int getHintForPosition(int i) {
            Integer num = (Integer) FoodSearchActivityV2.tabToHintMap.get(this.tabs[i]);
            return num != null ? num.intValue() : R.string.search_for_a_food;
        }

        @Nullable
        public final MfpFragment getFragmentAtPosition(int i) {
            WeakReference weakReference = (WeakReference) this.instantiatedFragments.get(i);
            if (weakReference != null) {
                return (MfpFragment) weakReference.get();
            }
            return null;
        }

        public final void invalidateFragmentData() {
            Iterator keyIterator = SparseArrayKt.keyIterator(this.instantiatedFragments);
            while (keyIterator.hasNext()) {
                WeakReference weakReference = (WeakReference) this.instantiatedFragments.get(((Number) keyIterator.next()).intValue());
                MfpFragment mfpFragment = weakReference != null ? (MfpFragment) weakReference.get() : null;
                if (mfpFragment != null ? mfpFragment instanceof LocalFoodSearchFragmentV2 : true) {
                    LocalFoodSearchFragmentV2 localFoodSearchFragmentV2 = (LocalFoodSearchFragmentV2) mfpFragment;
                    if (localFoodSearchFragmentV2 != null) {
                        localFoodSearchFragmentV2.invalidateData();
                    }
                }
            }
        }
    }

    @JvmStatic
    @NotNull
    public static final Intent newStartIntent(@NotNull Context context, @Nullable Extras extras) {
        return Companion.newStartIntent(context, extras);
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    @NotNull
    public String getAnalyticsScreenTag() {
        return Screens.FOOD_SEARCH;
    }

    public int getCustomBaseLayoutResId() {
        return R.layout.activity_food_search;
    }

    @NotNull
    public static final /* synthetic */ TimestampPickerMixin access$getTimestampPickerMixin$p(FoodSearchActivityV2 foodSearchActivityV2) {
        TimestampPickerMixin timestampPickerMixin2 = foodSearchActivityV2.timestampPickerMixin;
        if (timestampPickerMixin2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timestampPickerMixin");
        }
        return timestampPickerMixin2;
    }

    @NotNull
    public static final /* synthetic */ FoodSearchViewModel access$getViewModel$p(FoodSearchActivityV2 foodSearchActivityV2) {
        FoodSearchViewModel foodSearchViewModel = foodSearchActivityV2.viewModel;
        if (foodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return foodSearchViewModel;
    }

    @NotNull
    public final VMFactory getVmFactory() {
        VMFactory vMFactory = this.vmFactory;
        if (vMFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        return vMFactory;
    }

    public final void setVmFactory(@NotNull VMFactory vMFactory) {
        Intrinsics.checkParameterIsNotNull(vMFactory, "<set-?>");
        this.vmFactory = vMFactory;
    }

    /* access modifiers changed from: private */
    public final void setShowingOnlineSearch(boolean z) {
        this.isShowingOnlineSearch = z;
        ((AppBarLayout) _$_findCachedViewById(R.id.appBarLayout)).setExpanded(true, true);
        ViewPagerAdapter viewPagerAdapter2 = this.viewPagerAdapter;
        if (viewPagerAdapter2 != null) {
            viewPagerAdapter2.toggleSearchFragment(z, this.searchTrigger);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        Date date = null;
        setContentView((View) null);
        FragmentActivity fragmentActivity = this;
        VMFactory vMFactory = this.vmFactory;
        if (vMFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        ViewModel viewModel2 = ViewModelProviders.of(fragmentActivity, (Factory) vMFactory).get(FoodSearchViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel2, "ViewModelProviders.of(th…rchViewModel::class.java)");
        this.viewModel = (FoodSearchViewModel) viewModel2;
        FoodSearchViewModel foodSearchViewModel = this.viewModel;
        if (foodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Companion companion = Extras.Companion;
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, Constants.INTENT_SCHEME);
        foodSearchViewModel.setExtras(companion.fromIntent(intent));
        FoodSearchViewModel foodSearchViewModel2 = this.viewModel;
        if (foodSearchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        LifecycleOwner lifecycleOwner = this;
        foodSearchViewModel2.isMultiAddEnabled().observe(lifecycleOwner, new FoodSearchActivityV2$onCreate$1(this));
        FoodSearchViewModel foodSearchViewModel3 = this.viewModel;
        if (foodSearchViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        foodSearchViewModel3.getMultiAddItemsCount().observe(lifecycleOwner, new FoodSearchActivityV2$onCreate$2(this));
        FoodSearchViewModel foodSearchViewModel4 = this.viewModel;
        if (foodSearchViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        setTitle(foodSearchViewModel4.getScreenTitle());
        MfpActivity mfpActivity = this;
        FoodSearchViewModel foodSearchViewModel5 = this.viewModel;
        if (foodSearchViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Extras extras = foodSearchViewModel5.getExtras();
        if (extras != null) {
            date = extras.getLatestMealEntryTime();
        }
        TimestampPickerMixin timestampPickerMixin2 = new TimestampPickerMixin(mfpActivity, date, null, null, 12, null);
        this.timestampPickerMixin = timestampPickerMixin2;
        TimestampPickerMixin timestampPickerMixin3 = this.timestampPickerMixin;
        if (timestampPickerMixin3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timestampPickerMixin");
        }
        timestampPickerMixin3.setTimestampChangeListener(new TimestampPickerListener());
        RunnerLifecycleMixin[] runnerLifecycleMixinArr = new RunnerLifecycleMixin[1];
        TimestampPickerMixin timestampPickerMixin4 = this.timestampPickerMixin;
        if (timestampPickerMixin4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("timestampPickerMixin");
        }
        runnerLifecycleMixinArr[0] = timestampPickerMixin4;
        registerMixin(runnerLifecycleMixinArr);
        initViewPager();
        initTextWatcher();
        initButtonListeners();
        FoodSearchViewModel foodSearchViewModel6 = this.viewModel;
        if (foodSearchViewModel6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (foodSearchViewModel6.getShouldShowWalkthrough()) {
            showExistingUserWalkthrough();
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(@Nullable Intent intent) {
        ViewPagerAdapter viewPagerAdapter2 = this.viewPagerAdapter;
        if (viewPagerAdapter2 != null) {
            viewPagerAdapter2.invalidateFragmentData();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (!(i == 63 || i == 128)) {
                if (!(i == 183 || i == 185)) {
                    if (i != 196) {
                        if (i != 203) {
                            if (i != 1005) {
                                switch (i) {
                                    case 54:
                                    case 55:
                                        break;
                                    default:
                                        switch (i) {
                                            case 1001:
                                                break;
                                            case 1002:
                                                FoodSearchViewModel foodSearchViewModel = this.viewModel;
                                                if (foodSearchViewModel == null) {
                                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                                }
                                                foodSearchViewModel.addOrUpdateQuickFoodEntry(intent != null ? (QuickAddFood) intent.getParcelableExtra(QuickAddActivity.EXTRA_QUICK_ADD_OBJECT) : null, new FoodSearchActivityV2$onActivityResult$1(this));
                                                break;
                                            case 1003:
                                                onScanBarcode(i2, intent);
                                                break;
                                        }
                                }
                            }
                        }
                    } else {
                        FoodSearchViewModel foodSearchViewModel2 = this.viewModel;
                        if (foodSearchViewModel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        }
                        foodSearchViewModel2.addAndLogItemsFromActivityResultData(intent);
                        ViewPagerAdapter viewPagerAdapter2 = this.viewPagerAdapter;
                        if (viewPagerAdapter2 != null) {
                            ViewPager viewPager = (ViewPager) _$_findCachedViewById(R.id.viewPager);
                            Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
                            MfpFragment fragmentAtPosition = viewPagerAdapter2.getFragmentAtPosition(viewPager.getCurrentItem());
                            if (fragmentAtPosition != null) {
                                fragmentAtPosition.onActivityResult(i, i2, intent);
                            }
                        }
                    }
                }
                FoodSearchViewModel foodSearchViewModel3 = this.viewModel;
                if (foodSearchViewModel3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                }
                boolean areEqual = Intrinsics.areEqual((Object) (Boolean) foodSearchViewModel3.isMultiAddEnabled().getValue(), (Object) Boolean.valueOf(true));
                FoodSearchViewModel foodSearchViewModel4 = this.viewModel;
                if (foodSearchViewModel4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                }
                Extras extras = foodSearchViewModel4.getExtras();
                if (extras != null && extras.isInMealFoodCreationFlow() && !areEqual) {
                    Intent intent2 = new Intent();
                    String str = MealEditorMixin.EXTRA_FOOD_ENTRIES;
                    FoodSearchViewModel foodSearchViewModel5 = this.viewModel;
                    if (foodSearchViewModel5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    }
                    intent2.putExtra(str, foodSearchViewModel5.packMealIngredientsAsFoodEntries(intent));
                    setResult(-1, intent2);
                    finish();
                }
                ViewPagerAdapter viewPagerAdapter3 = this.viewPagerAdapter;
                if (viewPagerAdapter3 != null) {
                    viewPagerAdapter3.invalidateFragmentData();
                }
                if (!areEqual) {
                    finish();
                }
            }
            ViewPagerAdapter viewPagerAdapter4 = this.viewPagerAdapter;
            if (viewPagerAdapter4 != null) {
                viewPagerAdapter4.invalidateFragmentData();
            }
        }
    }

    public boolean onPrepareOptionsMenu(@Nullable Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        if (menu != null) {
            String string = getString(R.string.multi_add);
            Intrinsics.checkExpressionValueIsNotNull(string, "getString(R.string.multi_add)");
            if (string != null) {
                String upperCase = string.toUpperCase();
                Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                menu.add(0, 100, 0, upperCase).setShowAsAction(6);
                menu.add(0, 101, 0, R.string.create).setIcon(R.drawable.ic_plus).setShowAsAction(2);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf != null && valueOf.intValue() == 100) {
            FoodSearchViewModel foodSearchViewModel = this.viewModel;
            if (foodSearchViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            foodSearchViewModel.toggleMultiAdd(true);
        } else if (valueOf != null && valueOf.intValue() == 101) {
            FoodSearchViewModel foodSearchViewModel2 = this.viewModel;
            if (foodSearchViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            foodSearchViewModel2.reportToolbarPlusClicked();
            AddItemBottomSheet addItemBottomSheet = new AddItemBottomSheet();
            addItemBottomSheet.setOnItemSelected(new FoodSearchActivityV2$onOptionsItemSelected$$inlined$apply$lambda$1(this));
            showDialogFragment(addItemBottomSheet, FRAGMENT_TAD_ADD_ITEM);
        }
        return true;
    }

    @NotNull
    public AdUnit getAdUnit() {
        return getAdUnitService().getFoodSearchScreenAdUnitValue();
    }

    private final void initViewPager() {
        int i;
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "supportFragmentManager");
        int i2 = 0;
        this.viewPagerAdapter = new ViewPagerAdapter(this, supportFragmentManager, new FoodSearchTab[]{FoodSearchTab.ALL, FoodSearchTab.RECIPES, FoodSearchTab.MEALS, FoodSearchTab.MY_FOODS});
        ViewPager viewPager = (ViewPager) _$_findCachedViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager, "it");
        viewPager.setAdapter(this.viewPagerAdapter);
        viewPager.addOnPageChangeListener(new FoodSearchActivityV2$initViewPager$$inlined$let$lambda$1(this));
        ViewPagerAdapter viewPagerAdapter2 = this.viewPagerAdapter;
        if (viewPagerAdapter2 != null) {
            FoodSearchViewModel foodSearchViewModel = this.viewModel;
            if (foodSearchViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            i = viewPagerAdapter2.getTabPosition(foodSearchViewModel.getDefaultSearchTab());
        } else {
            i = 0;
        }
        viewPager.setCurrentItem(i);
        TabLayout tabLayout = (TabLayout) _$_findCachedViewById(R.id.tabLayout);
        Intrinsics.checkExpressionValueIsNotNull(tabLayout, "it");
        FoodSearchViewModel foodSearchViewModel2 = this.viewModel;
        if (foodSearchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (foodSearchViewModel2.isEnglishCurrentLanguage()) {
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            if (resources.getDisplayMetrics().widthPixels >= TAB_LAYOUT_MINIMAL_WIDTH_IN_PX) {
                i2 = 1;
            }
        }
        tabLayout.setTabMode(i2);
        tabLayout.setupWithViewPager((ViewPager) _$_findCachedViewById(R.id.viewPager));
        LinearLayout linearLayout = (LinearLayout) _$_findCachedViewById(R.id.ads_container);
        Intrinsics.checkExpressionValueIsNotNull(linearLayout, "ads_container");
        View view = linearLayout;
        if (!ViewCompat.isLaidOut(view) || view.isLayoutRequested()) {
            view.addOnLayoutChangeListener(new FoodSearchActivityV2$initViewPager$$inlined$doOnLayout$1(this));
            return;
        }
        ViewPager viewPager2 = (ViewPager) _$_findCachedViewById(R.id.viewPager);
        Intrinsics.checkExpressionValueIsNotNull(viewPager2, "viewPager");
        LayoutParams layoutParams = viewPager2.getLayoutParams();
        if (layoutParams != null) {
            ((CoordinatorLayout.LayoutParams) layoutParams).bottomMargin = view.getHeight();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.support.design.widget.CoordinatorLayout.LayoutParams");
    }

    private final void initTextWatcher() {
        ((ClearableEditText) _$_findCachedViewById(R.id.searchEditText)).setTextWatcherListener(new FoodSearchActivityV2$initTextWatcher$1(this));
        ((ClearableEditText) _$_findCachedViewById(R.id.searchEditText)).setOnEditorActionListener(new FoodSearchActivityV2$initTextWatcher$2(this));
    }

    private final void initButtonListeners() {
        ((ImageView) _$_findCachedViewById(R.id.restaurantLoggingButton)).setOnClickListener(new FoodSearchActivityV2$initButtonListeners$1(this));
        ((ImageView) _$_findCachedViewById(R.id.barcodeButton)).setOnClickListener(new FoodSearchActivityV2$initButtonListeners$2(this));
        ((TextView) _$_findCachedViewById(R.id.foodSearchCancelButton)).setOnClickListener(new FoodSearchActivityV2$initButtonListeners$3(this));
    }

    /* access modifiers changed from: private */
    public final void showOnlineSearch(Trigger trigger) {
        getImmHelper().hideSoftInput();
        ((ViewPager) _$_findCachedViewById(R.id.viewPager)).setCurrentItem(0, true);
        this.searchTrigger = trigger;
        setShowingOnlineSearch(true);
    }

    public final void updateSearchElements(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "query");
        if (str.length() == 0) {
            Group group = (Group) _$_findCachedViewById(R.id.advancedLoggingButtons);
            Intrinsics.checkExpressionValueIsNotNull(group, "advancedLoggingButtons");
            group.setVisibility(0);
            TextView textView = (TextView) _$_findCachedViewById(R.id.foodSearchCancelButton);
            Intrinsics.checkExpressionValueIsNotNull(textView, "foodSearchCancelButton");
            textView.setVisibility(8);
            return;
        }
        Group group2 = (Group) _$_findCachedViewById(R.id.advancedLoggingButtons);
        Intrinsics.checkExpressionValueIsNotNull(group2, "advancedLoggingButtons");
        group2.setVisibility(4);
        TextView textView2 = (TextView) _$_findCachedViewById(R.id.foodSearchCancelButton);
        Intrinsics.checkExpressionValueIsNotNull(textView2, "foodSearchCancelButton");
        textView2.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public final void updateLocalFragmentQueryString(String str) {
        MfpFragment mfpFragment;
        ViewPagerAdapter viewPagerAdapter2 = this.viewPagerAdapter;
        if (viewPagerAdapter2 != null) {
            ViewPager viewPager = (ViewPager) _$_findCachedViewById(R.id.viewPager);
            Intrinsics.checkExpressionValueIsNotNull(viewPager, "viewPager");
            mfpFragment = viewPagerAdapter2.getFragmentAtPosition(viewPager.getCurrentItem());
        } else {
            mfpFragment = null;
        }
        if (mfpFragment instanceof LocalFoodSearchFragmentV2) {
            ((LocalFoodSearchFragmentV2) mfpFragment).filterResults(str);
        }
    }

    /* access modifiers changed from: private */
    public final void navigateToDiary() {
        getNavigationHelper().finishActivityAfterNavigation().setResult(-1).withIntent(Diary.newStartIntentWithReferrer(getApplication(), com.myfitnesspal.shared.constants.Constants.Extras.REFERRER_DIARY_JUST_LOGGED)).withClearTopAndSingleTop().startActivity();
    }

    /* access modifiers changed from: private */
    public final void navigateToQuickAdd() {
        FoodSearchViewModel foodSearchViewModel = this.viewModel;
        if (foodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Availability quickAddMacrosAvailability = foodSearchViewModel.getQuickAddMacrosAvailability();
        if (quickAddMacrosAvailability == Availability.Available || quickAddMacrosAvailability == Availability.Locked) {
            NavigationHelper navigationHelper = getNavigationHelper();
            Context activity = getActivity();
            com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity.Extras extras = new com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity.Extras();
            FoodSearchViewModel foodSearchViewModel2 = this.viewModel;
            if (foodSearchViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            navigationHelper.withIntent(QuickAddActivity.newStartIntent(activity, extras.setMealName(foodSearchViewModel2.getMealName()))).startActivity(1002);
        } else if (quickAddMacrosAvailability == Availability.Revoked) {
            new MfpAlertDialogBuilder(this).setTitle((int) R.string.premium_feature_revoked).create().show();
        } else {
            String str = "";
            FoodSearchViewModel foodSearchViewModel3 = this.viewModel;
            if (foodSearchViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            QuickAddCaloriesDialogFragment newInstance = QuickAddCaloriesDialogFragment.newInstance(str, foodSearchViewModel3.getMealName());
            if (newInstance != null) {
                newInstance.show(getSupportFragmentManager(), Fragments.QUICK_ADD_DIALOG);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void navigateToScanBarcode() {
        NavigationHelper navigationHelper = getNavigationHelper();
        Context context = this;
        String str = "food_search";
        FoodSearchViewModel foodSearchViewModel = this.viewModel;
        if (foodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        navigationHelper.withIntent(BarcodeScannerActivity.newStartIntent(context, str, foodSearchViewModel.getCurrentActiveDate())).startActivity(1003);
        FoodSearchViewModel foodSearchViewModel2 = this.viewModel;
        if (foodSearchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        foodSearchViewModel2.appendBarcodeEventToOnlineSearchSummary();
    }

    private final void onScanBarcode(int i, Intent intent) {
        Intent intent2;
        FoodEditorType foodEditorType;
        FoodSearchViewModel foodSearchViewModel = this.viewModel;
        if (foodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Extras extras = foodSearchViewModel.getExtras();
        if (extras == null || !extras.isInMealFoodCreationFlow()) {
            FoodSearchViewModel foodSearchViewModel2 = this.viewModel;
            if (foodSearchViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            if (Intrinsics.areEqual((Object) (Boolean) foodSearchViewModel2.isMultiAddEnabled().getValue(), (Object) Boolean.valueOf(true))) {
                Intent intent3 = null;
                foodEditorType = FoodEditorType.BarcodeMultiAddFood;
                intent2 = intent3;
            } else {
                Intent newStartIntent = Diary.newStartIntent(this);
                newStartIntent.addFlags(603979776);
                foodEditorType = FoodEditorType.DiaryFood;
                intent2 = newStartIntent;
            }
        } else {
            Intent intent4 = null;
            foodEditorType = FoodEditorType.MealIngredient;
            intent2 = intent4;
        }
        MfpActivity mfpActivity = this;
        AnalyticsService analyticsService = getAnalyticsService();
        Session session = getSession();
        String str = "food_search";
        FoodSearchViewModel foodSearchViewModel3 = this.viewModel;
        if (foodSearchViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        String mealName = foodSearchViewModel3.getMealName();
        FoodSearchViewModel foodSearchViewModel4 = this.viewModel;
        if (foodSearchViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        String searchFlowId = foodSearchViewModel4.getSearchFlowId();
        FoodSearchViewModel foodSearchViewModel5 = this.viewModel;
        if (foodSearchViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        BarcodeUtil.handleScanResult(mfpActivity, analyticsService, foodEditorType, intent2, i, session, intent, str, mealName, searchFlowId, foodSearchViewModel5.getCurrentActiveDate());
    }

    /* access modifiers changed from: private */
    public final void navigateToCreateRecipe() {
        RecipeAnalyticsIntentData create = RecipeAnalyticsIntentData.create(StartScreen.FoodSearch, ActionType.Create);
        FoodSearchViewModel foodSearchViewModel = this.viewModel;
        if (foodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Intrinsics.checkExpressionValueIsNotNull(create, "analyticsIntentData");
        foodSearchViewModel.reportRecipeFlowStarted(create);
        FoodSearchViewModel foodSearchViewModel2 = this.viewModel;
        if (foodSearchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        if (foodSearchViewModel2.isRecipeParsingEnabled()) {
            FoodSearchViewModel foodSearchViewModel3 = this.viewModel;
            if (foodSearchViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            String mealName = foodSearchViewModel3.getMealName();
            FoodSearchViewModel foodSearchViewModel4 = this.viewModel;
            if (foodSearchViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            CreateRecipeDialogFragment.newInstance(mealName, foodSearchViewModel4.getCurrentActiveDate(), create).show(getSupportFragmentManager(), Fragments.CREATE_RECIPE_FRAGMENT);
            return;
        }
        FoodSearchViewModel foodSearchViewModel5 = this.viewModel;
        if (foodSearchViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        foodSearchViewModel5.reportRecipeImportShown(create);
        getNavigationHelper().withIntent(CreateRecipeManuallyActivity.newStartIntent(this, create)).startActivity(1006);
    }

    /* access modifiers changed from: private */
    public final void navigateToCreateMeal() {
        ActionMode actionMode = this.multiAddActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        NavigationHelper navigationHelper = getNavigationHelper();
        Context activity = getActivity();
        Companion companion = Companion;
        Activity activity2 = getActivity();
        Intrinsics.checkExpressionValueIsNotNull(activity2, AbstractEvent.ACTIVITY);
        Context context = activity2;
        FoodSearchViewModel foodSearchViewModel = this.viewModel;
        if (foodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Extras extras = foodSearchViewModel.getExtras();
        navigationHelper.withIntent(FoodEditorActivity.newMealItemEditorIntent(activity, companion.newStartIntent(context, extras != null ? Extras.copy$default(extras, true, false, 0, null, false, false, null, null, false, 510, null) : null), null, null, "food_search")).startActivity(1005);
    }

    /* access modifiers changed from: private */
    public final void navigateToCreateFood() {
        ActionMode actionMode = this.multiAddActionMode;
        if (actionMode != null) {
            actionMode.finish();
        }
        NavigationHelper navigationHelper = getNavigationHelper();
        String str = com.myfitnesspal.shared.constants.Constants.Extras.MEAL_NAME;
        FoodSearchViewModel foodSearchViewModel = this.viewModel;
        if (foodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        NavigationHelper withExtra = navigationHelper.withExtra(str, foodSearchViewModel.getMealName());
        String str2 = "flow_id";
        FoodSearchViewModel foodSearchViewModel2 = this.viewModel;
        if (foodSearchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        NavigationHelper withExtra2 = withExtra.withExtra(str2, foodSearchViewModel2.getSearchFlowId());
        Context context = this;
        FoodSearchViewModel foodSearchViewModel3 = this.viewModel;
        if (foodSearchViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        withExtra2.withIntent(AddFood.newStartIntent(context, foodSearchViewModel3.getMealName(), true)).startActivity(1004);
    }

    private final void showExistingUserWalkthrough() {
        getToolbar().setOnHierarchyChangeListener(new FoodSearchActivityV2$showExistingUserWalkthrough$2(this, new FoodSearchActivityV2$showExistingUserWalkthrough$1(this)));
    }
}
