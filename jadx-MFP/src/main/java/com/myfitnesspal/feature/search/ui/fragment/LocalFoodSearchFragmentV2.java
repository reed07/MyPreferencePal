package com.myfitnesspal.feature.search.ui.fragment;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.constraint.Group;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixinBase;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.meals.model.MealFoodLoggedInfo;
import com.myfitnesspal.feature.meals.ui.mixin.MealEditorMixin;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.ui.adapter.LocalFoodSearchAdapter;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Trigger;
import com.myfitnesspal.feature.search.ui.viewmodel.FoodSearchViewModel;
import com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.factory.VMFactory;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import dagger.Lazy;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.android.parcel.Parcelize;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u0000 X2\u00020\u0001:\u0002XYB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010/\u001a\u00020\u001e2\u0006\u00100\u001a\u000201H\u0002J\b\u00102\u001a\u00020\u001eH\u0002J\u000e\u00103\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u0018J\u0010\u00104\u001a\u00020\u001e2\u0006\u00105\u001a\u000206H\u0002J\b\u00107\u001a\u00020\u001eH\u0002J\b\u00108\u001a\u00020\u001eH\u0002J\u0010\u00109\u001a\u00020\u001e2\u0006\u00105\u001a\u000206H\u0002J\u0006\u0010:\u001a\u00020\u001eJ\"\u0010;\u001a\u00020\u001e2\u0006\u0010<\u001a\u0002012\u0006\u0010=\u001a\u0002012\b\u0010>\u001a\u0004\u0018\u00010?H\u0016J\u0012\u0010@\u001a\u00020\u001e2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J&\u0010C\u001a\u0004\u0018\u0001062\u0006\u0010D\u001a\u00020E2\b\u0010F\u001a\u0004\u0018\u00010G2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\u0018\u0010H\u001a\u00020I2\u000e\u0010J\u001a\n\u0012\u0004\u0012\u00020L\u0018\u00010KH\u0002J\b\u0010M\u001a\u00020\u001eH\u0002J\u0018\u0010N\u001a\u00020\u001e2\u0006\u0010O\u001a\u00020P2\u0006\u00100\u001a\u000201H\u0002J*\u0010Q\u001a\u00020\u001e2\u0006\u0010R\u001a\u00020S2\b\u0010T\u001a\u0004\u0018\u00010U2\u0006\u0010V\u001a\u00020W2\u0006\u00100\u001a\u000201H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R$\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000bR$\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\t\"\u0004\b\u0015\u0010\u000bRJ\u0010\u0016\u001a2\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0013\u0012\u00110\u001c¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u001e0\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u0002\n\u0000R$\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u00068\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\t\"\u0004\b(\u0010\u000bR\u001e\u0010)\u001a\u00020*8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.¨\u0006Z"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2;", "Lcom/myfitnesspal/shared/ui/fragment/MfpFragment;", "()V", "foodAdapter", "Lcom/myfitnesspal/feature/search/ui/adapter/LocalFoodSearchAdapter;", "imageService", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/images/service/ImageService;", "getImageService", "()Ldagger/Lazy;", "setImageService", "(Ldagger/Lazy;)V", "localSearchViewModel", "Lcom/myfitnesspal/feature/search/ui/viewmodel/LocalFoodSearchViewModel;", "mealUtil", "Lcom/myfitnesspal/feature/meals/util/MealUtil;", "getMealUtil", "setMealUtil", "multiAddFoodHelper", "Lcom/myfitnesspal/shared/util/MultiAddFoodHelper;", "getMultiAddFoodHelper", "setMultiAddFoodHelper", "onSearchRequested", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "query", "Lcom/myfitnesspal/feature/search/ui/fragment/OnlineFoodSearchFragment$Trigger;", "trigger", "", "getOnSearchRequested", "()Lkotlin/jvm/functions/Function2;", "setOnSearchRequested", "(Lkotlin/jvm/functions/Function2;)V", "searchViewModel", "Lcom/myfitnesspal/feature/search/ui/viewmodel/FoodSearchViewModel;", "userEnergyService", "Lcom/myfitnesspal/shared/service/userdata/UserEnergyService;", "getUserEnergyService", "setUserEnergyService", "vmFactory", "Lcom/myfitnesspal/shared/ui/factory/VMFactory;", "getVmFactory", "()Lcom/myfitnesspal/shared/ui/factory/VMFactory;", "setVmFactory", "(Lcom/myfitnesspal/shared/ui/factory/VMFactory;)V", "addFoodLoggingExtrasToNavigationHelper", "position", "", "expandAppBarIfPresent", "filterResults", "initEmptyState", "view", "Landroid/view/View;", "initListeners", "initObservers", "initViews", "invalidateData", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "shouldShowEmptyState", "", "resultItems", "", "Lcom/myfitnesspal/shared/model/v1/DiaryEntryCellModel;", "showEmptyState", "switchToAddMealViewForFood", "mealFood", "Lcom/myfitnesspal/shared/model/v1/MealFood;", "switchToFoodSummaryViewForFood", "food", "Lcom/myfitnesspal/shared/model/v1/Food;", "portion", "Lcom/myfitnesspal/shared/model/v1/FoodPortion;", "servings", "", "Companion", "Extras", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
public final class LocalFoodSearchFragmentV2 extends MfpFragment {
    public static final Companion Companion = new Companion(null);
    private static final String FRAGMENT_TAG_SORT_ORDER = "search_result_sort_order_fragment";
    /* access modifiers changed from: private */
    public static final Pair<String, Integer> PLUS_IMAGE_MAPPING = TuplesKt.to(PLUS_SIGN_PLACEHOLDER, Integer.valueOf(R.drawable.ic_plus_18dp));
    private static final String PLUS_SIGN_PLACEHOLDER = "PLUS_SIGN_HERE";
    private static final int SEARCH_VERSION = 2;
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public LocalFoodSearchAdapter foodAdapter;
    @Inject
    @NotNull
    public Lazy<ImageService> imageService;
    /* access modifiers changed from: private */
    public LocalFoodSearchViewModel localSearchViewModel;
    @Inject
    @NotNull
    public Lazy<MealUtil> mealUtil;
    @Inject
    @NotNull
    public Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    @NotNull
    private Function2<? super String, ? super Trigger, Unit> onSearchRequested = LocalFoodSearchFragmentV2$onSearchRequested$1.INSTANCE;
    private FoodSearchViewModel searchViewModel;
    @Inject
    @NotNull
    public Lazy<UserEnergyService> userEnergyService;
    @Inject
    @NotNull
    public VMFactory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2$Companion;", "", "()V", "FRAGMENT_TAG_SORT_ORDER", "", "PLUS_IMAGE_MAPPING", "Lkotlin/Pair;", "", "PLUS_SIGN_PLACEHOLDER", "SEARCH_VERSION", "newInstance", "Lcom/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2;", "extras", "Lcom/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2$Extras;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LocalFoodSearchFragmentV2.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final LocalFoodSearchFragmentV2 newInstance(@NotNull Extras extras) {
            Intrinsics.checkParameterIsNotNull(extras, "extras");
            LocalFoodSearchFragmentV2 localFoodSearchFragmentV2 = new LocalFoodSearchFragmentV2();
            Bundle bundle = new Bundle();
            bundle.putParcelable("extras", extras);
            localFoodSearchFragmentV2.setArguments(bundle);
            return localFoodSearchFragmentV2;
        }
    }

    @Parcelize
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B3\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\tJ\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\u0019\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0018HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014¨\u0006\u001e"}, d2 = {"Lcom/myfitnesspal/feature/search/ui/fragment/LocalFoodSearchFragmentV2$Extras;", "Landroid/os/Parcelable;", "foodSearchTab", "Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "mealName", "", "isMealFoodCreationFlow", "", "mealFoodCreationFlowId", "(Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;Ljava/lang/String;ZLjava/lang/String;)V", "getFoodSearchTab", "()Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;", "setFoodSearchTab", "(Lcom/myfitnesspal/feature/search/ui/constants/FoodSearchTab;)V", "()Z", "setMealFoodCreationFlow", "(Z)V", "getMealFoodCreationFlowId", "()Ljava/lang/String;", "setMealFoodCreationFlowId", "(Ljava/lang/String;)V", "getMealName", "setMealName", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: LocalFoodSearchFragmentV2.kt */
    public static final class Extras implements Parcelable {
        public static final android.os.Parcelable.Creator CREATOR = new Creator();
        @Nullable
        private FoodSearchTab foodSearchTab;
        private boolean isMealFoodCreationFlow;
        @Nullable
        private String mealFoodCreationFlowId;
        @Nullable
        private String mealName;

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
        public static class Creator implements android.os.Parcelable.Creator {
            @NotNull
            public final Object createFromParcel(@NotNull Parcel parcel) {
                Intrinsics.checkParameterIsNotNull(parcel, "in");
                return new Extras(parcel.readInt() != 0 ? (FoodSearchTab) Enum.valueOf(FoodSearchTab.class, parcel.readString()) : null, parcel.readString(), parcel.readInt() != 0, parcel.readString());
            }

            @NotNull
            public final Object[] newArray(int i) {
                return new Extras[i];
            }
        }

        public Extras() {
            this(null, null, false, null, 15, null);
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(@NotNull Parcel parcel, int i) {
            Intrinsics.checkParameterIsNotNull(parcel, IpcUtil.KEY_PARCEL);
            FoodSearchTab foodSearchTab2 = this.foodSearchTab;
            if (foodSearchTab2 != null) {
                parcel.writeInt(1);
                parcel.writeString(foodSearchTab2.name());
            } else {
                parcel.writeInt(0);
            }
            parcel.writeString(this.mealName);
            parcel.writeInt(this.isMealFoodCreationFlow ? 1 : 0);
            parcel.writeString(this.mealFoodCreationFlowId);
        }

        public Extras(@Nullable FoodSearchTab foodSearchTab2, @Nullable String str, boolean z, @Nullable String str2) {
            this.foodSearchTab = foodSearchTab2;
            this.mealName = str;
            this.isMealFoodCreationFlow = z;
            this.mealFoodCreationFlowId = str2;
        }

        public /* synthetic */ Extras(FoodSearchTab foodSearchTab2, String str, boolean z, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            if ((i & 1) != 0) {
                foodSearchTab2 = null;
            }
            if ((i & 2) != 0) {
                str = null;
            }
            if ((i & 4) != 0) {
                z = false;
            }
            if ((i & 8) != 0) {
                str2 = null;
            }
            this(foodSearchTab2, str, z, str2);
        }

        @Nullable
        public final FoodSearchTab getFoodSearchTab() {
            return this.foodSearchTab;
        }

        public final void setFoodSearchTab(@Nullable FoodSearchTab foodSearchTab2) {
            this.foodSearchTab = foodSearchTab2;
        }

        @Nullable
        public final String getMealName() {
            return this.mealName;
        }

        public final void setMealName(@Nullable String str) {
            this.mealName = str;
        }

        public final boolean isMealFoodCreationFlow() {
            return this.isMealFoodCreationFlow;
        }

        public final void setMealFoodCreationFlow(boolean z) {
            this.isMealFoodCreationFlow = z;
        }

        @Nullable
        public final String getMealFoodCreationFlowId() {
            return this.mealFoodCreationFlowId;
        }

        public final void setMealFoodCreationFlowId(@Nullable String str) {
            this.mealFoodCreationFlowId = str;
        }
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
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @NotNull
    public static final /* synthetic */ LocalFoodSearchAdapter access$getFoodAdapter$p(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        LocalFoodSearchAdapter localFoodSearchAdapter = localFoodSearchFragmentV2.foodAdapter;
        if (localFoodSearchAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("foodAdapter");
        }
        return localFoodSearchAdapter;
    }

    @NotNull
    public static final /* synthetic */ LocalFoodSearchViewModel access$getLocalSearchViewModel$p(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2) {
        LocalFoodSearchViewModel localFoodSearchViewModel = localFoodSearchFragmentV2.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        return localFoodSearchViewModel;
    }

    @NotNull
    public final Function2<String, Trigger, Unit> getOnSearchRequested() {
        return this.onSearchRequested;
    }

    public final void setOnSearchRequested(@NotNull Function2<? super String, ? super Trigger, Unit> function2) {
        Intrinsics.checkParameterIsNotNull(function2, "<set-?>");
        this.onSearchRequested = function2;
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

    @NotNull
    public final Lazy<ImageService> getImageService() {
        Lazy<ImageService> lazy = this.imageService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageService");
        }
        return lazy;
    }

    public final void setImageService(@NotNull Lazy<ImageService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.imageService = lazy;
    }

    @NotNull
    public final Lazy<UserEnergyService> getUserEnergyService() {
        Lazy<UserEnergyService> lazy = this.userEnergyService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEnergyService");
        }
        return lazy;
    }

    public final void setUserEnergyService(@NotNull Lazy<UserEnergyService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.userEnergyService = lazy;
    }

    @NotNull
    public final Lazy<MultiAddFoodHelper> getMultiAddFoodHelper() {
        Lazy<MultiAddFoodHelper> lazy = this.multiAddFoodHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiAddFoodHelper");
        }
        return lazy;
    }

    public final void setMultiAddFoodHelper(@NotNull Lazy<MultiAddFoodHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.multiAddFoodHelper = lazy;
    }

    @NotNull
    public final Lazy<MealUtil> getMealUtil() {
        Lazy<MealUtil> lazy = this.mealUtil;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mealUtil");
        }
        return lazy;
    }

    public final void setMealUtil(@NotNull Lazy<MealUtil> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.mealUtil = lazy;
    }

    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        Fragment fragment = this;
        VMFactory vMFactory = this.vmFactory;
        if (vMFactory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        ViewModel viewModel = ViewModelProviders.of(fragment, (Factory) vMFactory).get(LocalFoodSearchViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel, "ViewModelProviders.of(th…rchViewModel::class.java)");
        this.localSearchViewModel = (LocalFoodSearchViewModel) viewModel;
        LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        Bundle arguments = getArguments();
        localFoodSearchViewModel.setExtras(arguments != null ? (Extras) arguments.getParcelable("extras") : null);
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(R.layout.fragment_local_food_search, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, Promotion.ACTION_VIEW);
        initViews(inflate);
        initListeners();
        initObservers();
        return inflate;
    }

    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 52 || i == 54 || i == 65 || i == 183 || i == 196) {
                LocalFoodSearchAdapter localFoodSearchAdapter = this.foodAdapter;
                if (localFoodSearchAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("foodAdapter");
                }
                localFoodSearchAdapter.notifyDataSetChanged();
            }
        }
    }

    public final void filterResults(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "query");
        new Handler().post(new LocalFoodSearchFragmentV2$filterResults$1(this, str));
    }

    public final void invalidateData() {
        LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        localFoodSearchViewModel.invalidateData();
    }

    private final void initViews(View view) {
        int i;
        Context context = getContext();
        LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        FoodSearchTab foodSearchTab = localFoodSearchViewModel.getFoodSearchTab();
        if (foodSearchTab == null) {
            foodSearchTab = FoodSearchTab.RECENT;
        }
        FoodSearchTab foodSearchTab2 = foodSearchTab;
        Lazy<ImageService> lazy = this.imageService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageService");
        }
        Lazy<MealUtil> lazy2 = this.mealUtil;
        if (lazy2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mealUtil");
        }
        Lazy<MultiAddFoodHelper> lazy3 = this.multiAddFoodHelper;
        if (lazy3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("multiAddFoodHelper");
        }
        Lazy<UserEnergyService> lazy4 = this.userEnergyService;
        if (lazy4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEnergyService");
        }
        LocalFoodSearchAdapter localFoodSearchAdapter = new LocalFoodSearchAdapter(null, null, context, foodSearchTab2, false, lazy3, lazy4, lazy, lazy2, 19, null);
        this.foodAdapter = localFoodSearchAdapter;
        TextView textView = (TextView) view.findViewById(R.id.searchTitle);
        LocalFoodSearchViewModel localFoodSearchViewModel2 = this.localSearchViewModel;
        if (localFoodSearchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        FoodSearchTab foodSearchTab3 = localFoodSearchViewModel2.getFoodSearchTab();
        if (foodSearchTab3 != null) {
            switch (foodSearchTab3) {
                case RECIPES:
                    i = R.string.recipes;
                    break;
                case MEALS:
                    i = R.string.meals;
                    break;
                case MY_FOODS:
                    i = R.string.my_foods_empty_state_title;
                    break;
            }
        }
        i = R.string.history;
        textView.setText(i);
        ((Button) view.findViewById(R.id.sortOrderButton)).setOnClickListener(new LocalFoodSearchFragmentV2$initViews$1(this));
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext(), 1, false);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "this");
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        LocalFoodSearchAdapter localFoodSearchAdapter2 = this.foodAdapter;
        if (localFoodSearchAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("foodAdapter");
        }
        recyclerView.setAdapter(localFoodSearchAdapter2);
        recyclerView.addOnScrollListener(new LocalFoodSearchFragmentV2$initViews$$inlined$apply$lambda$1(this));
        recyclerView.addOnScrollListener(new LocalFoodSearchFragmentV2$initViews$$inlined$apply$lambda$2(linearLayoutManager, this));
        initEmptyState(view);
    }

    private final void initObservers() {
        LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        LifecycleOwner lifecycleOwner = this;
        localFoodSearchViewModel.getSearchItems().observe(lifecycleOwner, new LocalFoodSearchFragmentV2$initObservers$1(this));
        LocalFoodSearchViewModel localFoodSearchViewModel2 = this.localSearchViewModel;
        if (localFoodSearchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        localFoodSearchViewModel2.getItemImages().observe(lifecycleOwner, new LocalFoodSearchFragmentV2$initObservers$2(this));
        LocalFoodSearchViewModel localFoodSearchViewModel3 = this.localSearchViewModel;
        if (localFoodSearchViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        localFoodSearchViewModel3.getQueryLiveData().observe(lifecycleOwner, new LocalFoodSearchFragmentV2$initObservers$3(this));
        LocalFoodSearchViewModel localFoodSearchViewModel4 = this.localSearchViewModel;
        if (localFoodSearchViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        localFoodSearchViewModel4.getSortOrder().observe(lifecycleOwner, new LocalFoodSearchFragmentV2$initObservers$4(this));
        LocalFoodSearchViewModel localFoodSearchViewModel5 = this.localSearchViewModel;
        if (localFoodSearchViewModel5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        localFoodSearchViewModel5.getShouldFilterAllMeals().observe(lifecycleOwner, new LocalFoodSearchFragmentV2$initObservers$5(this));
        FragmentActivity activity = getActivity();
        if (activity != null) {
            VMFactory vMFactory = this.vmFactory;
            if (vMFactory == null) {
                Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
            }
            this.searchViewModel = (FoodSearchViewModel) ViewModelProviders.of(activity, (Factory) vMFactory).get(FoodSearchViewModel.class);
            FoodSearchViewModel foodSearchViewModel = this.searchViewModel;
            if (foodSearchViewModel != null) {
                MutableLiveData isMultiAddEnabled = foodSearchViewModel.isMultiAddEnabled();
                if (isMultiAddEnabled != null) {
                    isMultiAddEnabled.observe(lifecycleOwner, new LocalFoodSearchFragmentV2$initObservers$$inlined$let$lambda$1(this));
                }
            }
        }
    }

    private final void initEmptyState(View view) {
        LocalFoodSearchFragmentV2$initEmptyState$1 localFoodSearchFragmentV2$initEmptyState$1 = new LocalFoodSearchFragmentV2$initEmptyState$1(this, view);
        LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        FoodSearchTab foodSearchTab = localFoodSearchViewModel.getFoodSearchTab();
        if (foodSearchTab != null) {
            switch (foodSearchTab) {
                case ALL:
                    localFoodSearchFragmentV2$initEmptyState$1.invoke((int) R.drawable.empty_state_all_foods, (int) R.string.all_tab_empty_message);
                    return;
                case RECIPES:
                    localFoodSearchFragmentV2$initEmptyState$1.invoke((int) R.drawable.empty_state_recipes, (int) R.string.recipes_tab_empty_message);
                    return;
                case MEALS:
                    localFoodSearchFragmentV2$initEmptyState$1.invoke((int) R.drawable.empty_state_meals, (int) R.string.meals_tab_empty_message);
                    return;
                case MY_FOODS:
                    localFoodSearchFragmentV2$initEmptyState$1.invoke((int) R.drawable.empty_state_recent_foods, (int) R.string.foods_tab_empty_message);
                    return;
            }
        }
        localFoodSearchFragmentV2$initEmptyState$1.invoke((int) R.drawable.empty_state_all_foods, (int) R.string.all_tab_empty_message);
    }

    private final void initListeners() {
        LocalFoodSearchAdapter localFoodSearchAdapter = this.foodAdapter;
        if (localFoodSearchAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("foodAdapter");
        }
        localFoodSearchAdapter.setOnItemClick(new LocalFoodSearchFragmentV2$initListeners$1(this));
        LocalFoodSearchAdapter localFoodSearchAdapter2 = this.foodAdapter;
        if (localFoodSearchAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("foodAdapter");
        }
        localFoodSearchAdapter2.setOnItemCheckedChange(new LocalFoodSearchFragmentV2$initListeners$2(this));
        LocalFoodSearchAdapter localFoodSearchAdapter3 = this.foodAdapter;
        if (localFoodSearchAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("foodAdapter");
        }
        localFoodSearchAdapter3.setOnFooterClick(new LocalFoodSearchFragmentV2$initListeners$3(this));
        LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        if (localFoodSearchViewModel.getFoodSearchTab() == FoodSearchTab.ALL) {
            LocalFoodSearchAdapter localFoodSearchAdapter4 = this.foodAdapter;
            if (localFoodSearchAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("foodAdapter");
            }
            localFoodSearchAdapter4.setOnItemLongClick(new LocalFoodSearchFragmentV2$initListeners$4(this));
        }
    }

    /* access modifiers changed from: private */
    public final boolean shouldShowEmptyState(List<? extends DiaryEntryCellModel> list) {
        if (list == null || list.isEmpty()) {
            LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
            if (localFoodSearchViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
            }
            if (StringsKt.isBlank(localFoodSearchViewModel.getQuery())) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public final void showEmptyState() {
        Group group = (Group) _$_findCachedViewById(R.id.emptyResultsGroup);
        Intrinsics.checkExpressionValueIsNotNull(group, "emptyResultsGroup");
        group.setVisibility(0);
        LocalFoodSearchAdapter localFoodSearchAdapter = this.foodAdapter;
        if (localFoodSearchAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("foodAdapter");
        }
        localFoodSearchAdapter.clearItems();
        LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        if (localFoodSearchViewModel.getFoodSearchTab() == FoodSearchTab.ALL) {
            LocalFoodSearchViewModel localFoodSearchViewModel2 = this.localSearchViewModel;
            if (localFoodSearchViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
            }
            if (Intrinsics.areEqual((Object) (Boolean) localFoodSearchViewModel2.getShouldFilterAllMeals().getValue(), (Object) Boolean.valueOf(false))) {
                Group group2 = (Group) _$_findCachedViewById(R.id.localSearchResultGroup);
                Intrinsics.checkExpressionValueIsNotNull(group2, "localSearchResultGroup");
                group2.setVisibility(0);
                ((TextView) _$_findCachedViewById(R.id.emptyResultsMessage)).setText(R.string.all_tab_meal_filter_empty_message);
                return;
            }
        }
        Group group3 = (Group) _$_findCachedViewById(R.id.localSearchResultGroup);
        Intrinsics.checkExpressionValueIsNotNull(group3, "localSearchResultGroup");
        group3.setVisibility(8);
        LocalFoodSearchViewModel localFoodSearchViewModel3 = this.localSearchViewModel;
        if (localFoodSearchViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        if (localFoodSearchViewModel3.getFoodSearchTab() == FoodSearchTab.ALL) {
            ((TextView) _$_findCachedViewById(R.id.emptyResultsMessage)).setText(R.string.all_tab_empty_message);
        }
    }

    /* access modifiers changed from: private */
    public final void switchToAddMealViewForFood(MealFood mealFood, int i) {
        addFoodLoggingExtrasToNavigationHelper(i);
        LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        String query = localFoodSearchViewModel.getQuery();
        String uid = mealFood.getUid();
        String title = SearchSource.LOCAL.getTitle();
        LocalFoodSearchViewModel localFoodSearchViewModel2 = this.localSearchViewModel;
        if (localFoodSearchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        FoodSearchTab foodSearchTab = localFoodSearchViewModel2.getFoodSearchTab();
        MealFoodLoggedInfo mealFoodLoggedInfo = new MealFoodLoggedInfo(query, i, uid, title, foodSearchTab != null ? foodSearchTab.getAnalyticsTabName() : null);
        NavigationHelper navigationHelper = getNavigationHelper();
        String str = MealEditorMixin.EXTRA_MEAL_FOOD_CREATION_FLOW;
        LocalFoodSearchViewModel localFoodSearchViewModel3 = this.localSearchViewModel;
        if (localFoodSearchViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        Extras extras = localFoodSearchViewModel3.getExtras();
        NavigationHelper withExtra = navigationHelper.withExtra(str, extras != null ? Boolean.valueOf(extras.isMealFoodCreationFlow()) : null).withExtra(MealEditorMixin.EXTRA_MEAL_FOOD_LOGGED_INFO, (Parcelable) mealFoodLoggedInfo).withExtra(FoodEditorMixinBase.EXTRA_SEARCH_VERSION, 2);
        Context activity = getActivity();
        LocalFoodSearchViewModel localFoodSearchViewModel4 = this.localSearchViewModel;
        if (localFoodSearchViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        withExtra.withIntent(FoodEditorActivity.newMealItemEditorIntent(activity, null, localFoodSearchViewModel4.getMealName(), mealFood, "food_search")).startActivity(RequestCodes.FOOD_EDITOR);
    }

    /* JADX WARNING: type inference failed for: r13v0 */
    /* JADX WARNING: type inference failed for: r13v1, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r13v2, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r13v3 */
    /* JADX WARNING: type inference failed for: r15v1, types: [android.content.Intent] */
    /* JADX WARNING: type inference failed for: r13v4, types: [android.content.Intent] */
    /* JADX WARNING: type inference failed for: r13v5 */
    /* JADX WARNING: type inference failed for: r13v6 */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r13v0
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], android.content.Intent, java.lang.String]
  uses: [java.lang.String, ?[OBJECT, ARRAY]]
  mth insns count: 203
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005b  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0103  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0154  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0167  */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x0172  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0179  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x018f  */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x0196  */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void switchToFoodSummaryViewForFood(com.myfitnesspal.shared.model.v1.Food r25, com.myfitnesspal.shared.model.v1.FoodPortion r26, float r27, int r28) {
        /*
            r24 = this;
            r0 = r24
            r1 = r25
            r2 = r28
            r3 = 0
            float r4 = (float) r3
            int r4 = (r27 > r4 ? 1 : (r27 == r4 ? 0 : -1))
            if (r4 > 0) goto L_0x000f
            r4 = 1065353216(0x3f800000, float:1.0)
            goto L_0x0011
        L_0x000f:
            r4 = r27
        L_0x0011:
            r5 = 1
            if (r26 != 0) goto L_0x002a
            com.myfitnesspal.shared.model.v1.FoodPortion[] r6 = r25.getFoodPortions()
            if (r6 == 0) goto L_0x002a
            int r6 = r6.length
            if (r6 != 0) goto L_0x001f
            r6 = 1
            goto L_0x0020
        L_0x001f:
            r6 = 0
        L_0x0020:
            r6 = r6 ^ r5
            if (r6 == 0) goto L_0x002a
            com.myfitnesspal.shared.model.v1.FoodPortion[] r6 = r25.getFoodPortions()
            r6 = r6[r3]
            goto L_0x002c
        L_0x002a:
            r6 = r26
        L_0x002c:
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r7 = r0.localSearchViewModel
            if (r7 != 0) goto L_0x0035
            java.lang.String r8 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
        L_0x0035:
            r7.reportFoodLookupEvent(r1, r2)
            r0.addFoodLoggingExtrasToNavigationHelper(r2)
            com.myfitnesspal.feature.search.event.UpdateFoodSearchBreadcrumbEvent r7 = new com.myfitnesspal.feature.search.event.UpdateFoodSearchBreadcrumbEvent
            java.lang.String r8 = "add_view"
            r7.<init>(r8)
            r0.postEvent(r7)
            boolean r7 = r1 instanceof com.myfitnesspal.shared.model.v1.RecipeFood
            com.myfitnesspal.shared.service.config.ConfigService r8 = r24.getConfigService()
            boolean r8 = com.myfitnesspal.shared.util.ConfigUtils.isNewAddFoodFlowOn(r8)
            r9 = 54
            r10 = 2
            r11 = 2131886269(0x7f1200bd, float:1.9407112E38)
            r12 = 2131886231(0x7f120097, float:1.9407035E38)
            r13 = 0
            if (r8 == 0) goto L_0x0196
            com.myfitnesspal.shared.model.v2.MfpFood r8 = com.myfitnesspal.shared.util.FoodMapperUtil.mapV1FoodToMfpFood(r25)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r15 = new com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras
            r15.<init>()
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras r14 = new com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras
            r14.<init>()
            java.lang.String r3 = "foodV2"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r3)
            r8.setSelectedServingAmount(r4)
            int r1 = r1.foodPortionIndexForPortion(r6)
            r8.setSelectedServingSizeIndex(r1)
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r1 = r0.localSearchViewModel
            if (r1 != 0) goto L_0x0081
            java.lang.String r3 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x0081:
            java.lang.String r1 = r1.getQuery()
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras r1 = r14.setSearchQuery(r1)
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r3 = r0.localSearchViewModel
            if (r3 != 0) goto L_0x0092
            java.lang.String r4 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L_0x0092:
            com.myfitnesspal.feature.search.ui.constants.FoodSearchTab r3 = r3.getFoodSearchTab()
            if (r3 == 0) goto L_0x009d
            java.lang.String r3 = r3.getAnalyticsTabName()
            goto L_0x009e
        L_0x009d:
            r3 = r13
        L_0x009e:
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras r1 = r1.setListType(r3)
            java.lang.String r3 = "analyticsExtras.setSearc…rchTab?.analyticsTabName)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r3)
            r1.setResultsListPosition(r2)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras$ActionType r1 = com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras.ActionType.Create
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r1 = r15.setActionType(r1)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r1 = r1.setSupportPairedFoods(r5)
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r2 = r0.localSearchViewModel
            if (r2 != 0) goto L_0x00bd
            java.lang.String r3 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x00bd:
            java.lang.String r2 = r2.getMealName()
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r1 = r1.setMealName(r2)
            com.myfitnesspal.shared.service.session.Session r2 = r24.getSession()
            com.myfitnesspal.shared.model.User r2 = r2.getUser()
            java.util.Date r2 = r2.getActiveDate()
            java.lang.String r3 = "session.user.activeDate"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            long r2 = r2.getTime()
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r1 = r1.setDate(r2)
            com.myfitnesspal.feature.search.ui.viewmodel.FoodSearchViewModel r2 = r0.searchViewModel
            if (r2 == 0) goto L_0x00ef
            android.arch.lifecycle.MutableLiveData r2 = r2.isMultiAddEnabled()
            if (r2 == 0) goto L_0x00ef
            java.lang.Object r2 = r2.getValue()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            goto L_0x00f0
        L_0x00ef:
            r2 = r13
        L_0x00f0:
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r5)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r1 = r1.setMultiAddOn(r2)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r1 = r1.setForRecipe(r7)
            if (r7 == 0) goto L_0x0103
            goto L_0x0106
        L_0x0103:
            r11 = 2131886231(0x7f120097, float:1.9407035E38)
        L_0x0106:
            java.lang.String r2 = r0.getString(r11)
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r1 = r1.setScreenTitle(r2)
            java.lang.String r2 = "editorExtras.setActionTy…e else R.string.addFood))"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r1, r2)
            r1.setFoodEditorAnalyticsExtras(r14)
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r1 = r0.localSearchViewModel
            if (r1 != 0) goto L_0x011f
            java.lang.String r2 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x011f:
            com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2$Extras r1 = r1.getExtras()
            if (r1 == 0) goto L_0x012c
            boolean r1 = r1.isMealFoodCreationFlow()
            if (r1 != r5) goto L_0x012c
            goto L_0x0136
        L_0x012c:
            android.content.Context r1 = r24.getContext()
            java.lang.String r2 = "just_logged"
            android.content.Intent r13 = com.myfitnesspal.feature.diary.ui.activity.Diary.newStartIntentWithReferrerAndForceHomeOnBack(r1, r2)
        L_0x0136:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r24.getNavigationHelper()
            java.lang.String r2 = "search_version"
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r1.withExtra(r2, r10)
            android.content.Context r14 = r24.getContext()
            com.myfitnesspal.shared.service.session.Session r2 = r24.getSession()
            com.myfitnesspal.shared.model.User r2 = r2.getUser()
            java.util.Date r17 = r2.getActiveDate()
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r2 = r0.localSearchViewModel
            if (r2 != 0) goto L_0x0159
            java.lang.String r3 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x0159:
            java.lang.String r18 = r2.getMealName()
            r19 = 0
            r20 = 0
            java.lang.String r21 = "local_food_search_fragment"
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r2 = r0.localSearchViewModel
            if (r2 != 0) goto L_0x016c
            java.lang.String r3 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
        L_0x016c:
            com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2$Extras r2 = r2.getExtras()
            if (r2 == 0) goto L_0x0179
            boolean r3 = r2.isMealFoodCreationFlow()
            r22 = r3
            goto L_0x017b
        L_0x0179:
            r22 = 0
        L_0x017b:
            r2 = r15
            r15 = r13
            r16 = r8
            r23 = r2
            android.content.Intent r3 = com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity.newDiaryFoodItemEditorIntent(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r1.withIntent(r3)
            boolean r2 = r2.isMultiAddOn()
            if (r2 == 0) goto L_0x0191
            r9 = 196(0xc4, float:2.75E-43)
        L_0x0191:
            r1.startActivity(r9)
            goto L_0x024e
        L_0x0196:
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r3 = r24.getNavigationHelper()
            android.support.v4.app.FragmentActivity r5 = r24.getActivity()
            android.content.Context r5 = (android.content.Context) r5
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r8 = new com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras
            r8.<init>()
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = r8.setFood(r1)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = r1.setFoodPortion(r6)
            com.myfitnesspal.shared.service.session.Session r6 = r24.getSession()
            com.myfitnesspal.shared.model.User r6 = r6.getUser()
            java.util.Date r6 = r6.getActiveDate()
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setDate(r6)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r6 = r0.localSearchViewModel
            if (r6 != 0) goto L_0x01c8
            java.lang.String r8 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
        L_0x01c8:
            java.lang.String r6 = r6.getMealName()
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setMealName(r6)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r6 = r0.localSearchViewModel
            if (r6 != 0) goto L_0x01db
            java.lang.String r8 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
        L_0x01db:
            com.myfitnesspal.feature.search.ui.constants.FoodSearchTab r6 = r6.getFoodSearchTab()
            if (r6 == 0) goto L_0x01e5
            java.lang.String r13 = r6.getAnalyticsTabName()
        L_0x01e5:
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = r1.setListType(r13)
            if (r7 == 0) goto L_0x01ec
            goto L_0x01ef
        L_0x01ec:
            r11 = 2131886231(0x7f120097, float:1.9407035E38)
        L_0x01ef:
            java.lang.String r6 = r0.getString(r11)
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setTitle(r6)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r6 = r0.localSearchViewModel
            if (r6 != 0) goto L_0x0202
            java.lang.String r7 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
        L_0x0202:
            java.lang.String r6 = r6.getQuery()
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setQuery(r6)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setSearchVersion(r10)
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setServings(r4)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setPosition(r2)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            com.myfitnesspal.feature.search.ui.viewmodel.LocalFoodSearchViewModel r2 = r0.localSearchViewModel
            if (r2 != 0) goto L_0x0225
            java.lang.String r4 = "localSearchViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
        L_0x0225:
            com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2$Extras r2 = r2.getExtras()
            if (r2 == 0) goto L_0x0230
            boolean r2 = r2.isMealFoodCreationFlow()
            goto L_0x0231
        L_0x0230:
            r2 = 0
        L_0x0231:
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setMealFoodCreationFlow(r2)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            com.myfitnesspal.feature.search.model.SearchSource r2 = com.myfitnesspal.feature.search.model.SearchSource.LOCAL
            java.lang.String r2 = r2.getTitle()
            com.myfitnesspal.feature.addentry.ui.extras.AddFoodExtrasBase r1 = r1.setSource(r2)
            com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView$Extras r1 = (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.Extras) r1
            android.content.Intent r1 = com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryView.newStartIntent(r5, r1)
            com.myfitnesspal.shared.ui.navigation.NavigationHelper r1 = r3.withIntent(r1)
            r1.startActivity(r9)
        L_0x024e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.search.ui.fragment.LocalFoodSearchFragmentV2.switchToFoodSummaryViewForFood(com.myfitnesspal.shared.model.v1.Food, com.myfitnesspal.shared.model.v1.FoodPortion, float, int):void");
    }

    private final void addFoodLoggingExtrasToNavigationHelper(int i) {
        NavigationHelper withExtra = getNavigationHelper().withExtra("index", i).withExtra(Attributes.ITEM_COUNT, 1);
        String str = "list_type";
        LocalFoodSearchViewModel localFoodSearchViewModel = this.localSearchViewModel;
        if (localFoodSearchViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        FoodSearchTab foodSearchTab = localFoodSearchViewModel.getFoodSearchTab();
        String str2 = null;
        NavigationHelper withExtra2 = withExtra.withExtra(str, foodSearchTab != null ? foodSearchTab.getListType() : null);
        String str3 = "foods";
        ApiJsonMapper apiJsonMapper = new ApiJsonMapper();
        LocalFoodSearchViewModel localFoodSearchViewModel2 = this.localSearchViewModel;
        if (localFoodSearchViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        NavigationHelper withExtra3 = withExtra2.withExtra(str3, apiJsonMapper.reverseMap((Object) localFoodSearchViewModel2.getMultiAddItems())).withExtra("source", (Serializable) SearchSource.LOCAL);
        String str4 = "flow_id";
        LocalFoodSearchViewModel localFoodSearchViewModel3 = this.localSearchViewModel;
        if (localFoodSearchViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        Extras extras = localFoodSearchViewModel3.getExtras();
        if (extras != null) {
            str2 = extras.getMealFoodCreationFlowId();
        }
        NavigationHelper withExtra4 = withExtra3.withExtra(str4, str2);
        String str5 = "meal";
        LocalFoodSearchViewModel localFoodSearchViewModel4 = this.localSearchViewModel;
        if (localFoodSearchViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSearchViewModel");
        }
        withExtra4.withExtra(str5, localFoodSearchViewModel4.getMealName());
    }

    /* access modifiers changed from: private */
    public final void expandAppBarIfPresent() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            AppBarLayout appBarLayout = (AppBarLayout) activity.findViewById(R.id.appBarLayout);
            if (appBarLayout != null) {
                appBarLayout.setExpanded(true, true);
            }
        }
    }
}
