package com.myfitnesspal.feature.foodfeedback.ui.activity;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Switch;
import android.widget.TextView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.common.Constants;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackViewModel;
import com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackViewModel.FeedbackMode;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelper;
import com.myfitnesspal.feature.foodfeedback.service.FoodFeedbackAnalyticsHelperImpl;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpNutritionalContents;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.myfitnesspal.shared.util.DecimalDigitsInputFilter;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 N2\u00020\u0001:\u0001NB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0002J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020\u001dH\u0002J\u0012\u0010(\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\u0012\u0010+\u001a\u00020\u001d2\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0010\u0010.\u001a\u00020\u001d2\u0006\u0010/\u001a\u000200H\u0016J\b\u00101\u001a\u00020&H\u0002J\b\u00102\u001a\u00020&H\u0002J\b\u00103\u001a\u00020&H\u0002J\b\u00104\u001a\u00020&H\u0002J\u0018\u00105\u001a\u00020&2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\"H\u0002J\u0010\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020;H\u0002J\u0018\u0010<\u001a\u00020&2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\"H\u0002J\u0010\u0010@\u001a\u00020&2\u0006\u0010:\u001a\u00020;H\u0002J\b\u0010A\u001a\u00020&H\u0002J\b\u0010B\u001a\u00020&H\u0002J\u0010\u0010C\u001a\u00020&2\u0006\u0010D\u001a\u00020EH\u0002J\u0010\u0010F\u001a\u00020&2\u0006\u0010G\u001a\u00020EH\u0002J5\u0010H\u001a\u00020\u001d*\u0002072\u0017\u0010I\u001a\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u001d0J¢\u0006\u0002\bK2\u0006\u0010L\u001a\u00020E2\u0006\u0010M\u001a\u00020$H\u0002R$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R$\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00048\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001e\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006O"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/ui/activity/FoodFeedbackActivity;", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "()V", "foodFeedbackAnalyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/foodfeedback/service/FoodFeedbackAnalyticsHelper;", "getFoodFeedbackAnalyticsHelper", "()Ldagger/Lazy;", "setFoodFeedbackAnalyticsHelper", "(Ldagger/Lazy;)V", "servingSizeMenuClickListener", "Landroid/widget/PopupMenu$OnMenuItemClickListener;", "userEnergyService", "Lcom/myfitnesspal/shared/service/userdata/UserEnergyService;", "getUserEnergyService", "setUserEnergyService", "viewModel", "Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel;", "getViewModel", "()Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel;", "setViewModel", "(Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel;)V", "vmFactory", "Landroid/arch/lifecycle/ViewModelProvider$Factory;", "getVmFactory", "()Landroid/arch/lifecycle/ViewModelProvider$Factory;", "setVmFactory", "(Landroid/arch/lifecycle/ViewModelProvider$Factory;)V", "brandLengthValid", "", "caloriesNotEmpty", "createNutritionValuesFromUserInput", "Lcom/myfitnesspal/shared/model/v1/NutritionalValues;", "getLocalizedFloatValues", "", "nutrientValue", "", "hideProgressDialog", "", "nameNotEmpty", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "menu", "Landroid/view/Menu;", "performSave", "postToFeedbackServiceAndSave", "saveIfNoFoodWithSameName", "saveToMyFoods", "setEditTextValue", "editTextView", "Landroid/widget/EditText;", "value", "setupFoodNameBrand", "mfpFood", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "setupNutritionValues", "nutritionalContents", "Lcom/myfitnesspal/shared/model/v2/MfpNutritionalContents;", "nutritionMultiplier", "setupServingSize", "setupSwitchToggleListener", "setupViewsBasedOnMode", "showProgressDialog", "stringId", "", "showSnackBar", "messageStringId", "validate", "invalidCase", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "snackbarError", "analyticsError", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackActivity.kt */
public final class FoodFeedbackActivity extends MfpActivity {
    public static final Companion Companion = new Companion(null);
    private static final String DEFAULT_VALUE_EDIT_TXT = "";
    private static final int DIGITS_AFTER_DECIMAL = 1;
    private static final int DIGITS_BEFORE_DECIMAL = 4;
    @NotNull
    public static final String DUPLICATE_ENTRY = "duplicate_entry";
    @NotNull
    public static final String EXTRA_FEEDBACK_TYPE = "extra_feedback_type";
    @NotNull
    public static final String EXTRA_FOOD = "extra_food";
    private static final String EXTRA_NEW_FOOD = "extra_new_food";
    @NotNull
    public static final String INAPPROPRIATE_LISTING = "inappropriate_listing";
    @NotNull
    public static final String INCORRECT_BRAND_NAME = "brand_name";
    @NotNull
    public static final String INCORRECT_FOOD_NAME = "description";
    private static final int MENU_SAVE_ACTION = 1213;
    @NotNull
    public static final String PREFERRED_SERVING_NOT_AVAILABLE = "serving_not_available";
    private static final String PROGRESS_DIALOG_TAG = "progress_dialog";
    @NotNull
    public static final String WRONG_NUTRITION_INFO = "nutritional_contents";
    private HashMap _$_findViewCache;
    @Inject
    @NotNull
    public Lazy<FoodFeedbackAnalyticsHelper> foodFeedbackAnalyticsHelper;
    /* access modifiers changed from: private */
    public OnMenuItemClickListener servingSizeMenuClickListener;
    @Inject
    @NotNull
    public Lazy<UserEnergyService> userEnergyService;
    @NotNull
    public FoodFeedbackViewModel viewModel;
    @Inject
    @NotNull
    public Factory vmFactory;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J&\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00040\u001aH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/ui/activity/FoodFeedbackActivity$Companion;", "", "()V", "DEFAULT_VALUE_EDIT_TXT", "", "DIGITS_AFTER_DECIMAL", "", "DIGITS_BEFORE_DECIMAL", "DUPLICATE_ENTRY", "EXTRA_FEEDBACK_TYPE", "EXTRA_FOOD", "EXTRA_NEW_FOOD", "INAPPROPRIATE_LISTING", "INCORRECT_BRAND_NAME", "INCORRECT_FOOD_NAME", "MENU_SAVE_ACTION", "PREFERRED_SERVING_NOT_AVAILABLE", "PROGRESS_DIALOG_TAG", "WRONG_NUTRITION_INFO", "newStartIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "mfpFood", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "feedbackTypes", "Ljava/util/ArrayList;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodFeedbackActivity.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final Intent newStartIntent(@NotNull Context context, @NotNull MfpFood mfpFood, @NotNull ArrayList<String> arrayList) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            Intrinsics.checkParameterIsNotNull(mfpFood, "mfpFood");
            Intrinsics.checkParameterIsNotNull(arrayList, "feedbackTypes");
            Intent putExtra = new Intent(context, FoodFeedbackActivity.class).putExtra("extra_food", mfpFood).putExtra(FoodFeedbackActivity.EXTRA_FEEDBACK_TYPE, arrayList);
            Intrinsics.checkExpressionValueIsNotNull(putExtra, "Intent(context, FoodFeed…BACK_TYPE, feedbackTypes)");
            return putExtra;
        }
    }

    @JvmStatic
    @NotNull
    public static final Intent newStartIntent(@NotNull Context context, @NotNull MfpFood mfpFood, @NotNull ArrayList<String> arrayList) {
        return Companion.newStartIntent(context, mfpFood, arrayList);
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
    public final Factory getVmFactory() {
        Factory factory = this.vmFactory;
        if (factory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        return factory;
    }

    public final void setVmFactory(@NotNull Factory factory) {
        Intrinsics.checkParameterIsNotNull(factory, "<set-?>");
        this.vmFactory = factory;
    }

    @NotNull
    public final Lazy<FoodFeedbackAnalyticsHelper> getFoodFeedbackAnalyticsHelper() {
        Lazy<FoodFeedbackAnalyticsHelper> lazy = this.foodFeedbackAnalyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("foodFeedbackAnalyticsHelper");
        }
        return lazy;
    }

    public final void setFoodFeedbackAnalyticsHelper(@NotNull Lazy<FoodFeedbackAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.foodFeedbackAnalyticsHelper = lazy;
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
    public final FoodFeedbackViewModel getViewModel() {
        FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
        if (foodFeedbackViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        return foodFeedbackViewModel;
    }

    public final void setViewModel(@NotNull FoodFeedbackViewModel foodFeedbackViewModel) {
        Intrinsics.checkParameterIsNotNull(foodFeedbackViewModel, "<set-?>");
        this.viewModel = foodFeedbackViewModel;
    }

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.food_edit);
        FragmentActivity fragmentActivity = this;
        Factory factory = this.vmFactory;
        if (factory == null) {
            Intrinsics.throwUninitializedPropertyAccessException("vmFactory");
        }
        ViewModel viewModel2 = ViewModelProviders.of(fragmentActivity, factory).get(FoodFeedbackViewModel.class);
        Intrinsics.checkExpressionValueIsNotNull(viewModel2, "ViewModelProviders.of(th…ackViewModel::class.java)");
        this.viewModel = (FoodFeedbackViewModel) viewModel2;
        FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
        if (foodFeedbackViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        Intent intent = getIntent();
        Intrinsics.checkExpressionValueIsNotNull(intent, Constants.INTENT_SCHEME);
        Session session = getSession();
        Intrinsics.checkExpressionValueIsNotNull(session, "session");
        Lazy<UserEnergyService> lazy = this.userEnergyService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEnergyService");
        }
        Object obj = lazy.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "userEnergyService.get()");
        Energy userCurrentEnergyUnit = ((UserEnergyService) obj).getUserCurrentEnergyUnit();
        Intrinsics.checkExpressionValueIsNotNull(userCurrentEnergyUnit, "userEnergyService.get().userCurrentEnergyUnit");
        foodFeedbackViewModel.initialize(intent, session, userCurrentEnergyUnit);
        setupViewsBasedOnMode();
    }

    public boolean onPrepareOptionsMenu(@NotNull Menu menu) {
        Intrinsics.checkParameterIsNotNull(menu, "menu");
        menu.clear();
        menu.add(0, MENU_SAVE_ACTION, 0, R.string.save).setEnabled(true).setIcon(R.drawable.ic_check_white_24dp).setShowAsAction(2);
        return true;
    }

    public boolean onOptionsItemSelected(@Nullable MenuItem menuItem) {
        Integer valueOf = menuItem != null ? Integer.valueOf(menuItem.getItemId()) : null;
        if (valueOf == null || valueOf.intValue() != MENU_SAVE_ACTION) {
            return super.onOptionsItemSelected(menuItem);
        }
        Boolean isOffline = ConnectivityUtil.isOffline();
        Intrinsics.checkExpressionValueIsNotNull(isOffline, "ConnectivityUtil.isOffline()");
        if (isOffline.booleanValue()) {
            showSnackBar(R.string.feedback_offline);
            FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
            if (foodFeedbackViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            MfpFood mfpFood = foodFeedbackViewModel.getMfpFood();
            if (mfpFood != null) {
                Lazy<FoodFeedbackAnalyticsHelper> lazy = this.foodFeedbackAnalyticsHelper;
                if (lazy == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("foodFeedbackAnalyticsHelper");
                }
                FoodFeedbackAnalyticsHelper foodFeedbackAnalyticsHelper2 = (FoodFeedbackAnalyticsHelper) lazy.get();
                String id = mfpFood.getId();
                Intrinsics.checkExpressionValueIsNotNull(id, "it.id");
                String version = mfpFood.getVersion();
                Intrinsics.checkExpressionValueIsNotNull(version, "it.version");
                foodFeedbackAnalyticsHelper2.reportFoodCorrectionFailed(id, version, FoodFeedbackAnalyticsHelperImpl.ERROR_NO_INTERNET);
            }
        } else {
            FoodFeedbackViewModel foodFeedbackViewModel2 = this.viewModel;
            if (foodFeedbackViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            foodFeedbackViewModel2.clearMaps();
            FoodFeedbackViewModel foodFeedbackViewModel3 = this.viewModel;
            if (foodFeedbackViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            EditText editText = (EditText) _$_findCachedViewById(R.id.editTxtBrandName);
            Intrinsics.checkExpressionValueIsNotNull(editText, "editTxtBrandName");
            String obj = editText.getText().toString();
            EditText editText2 = (EditText) _$_findCachedViewById(R.id.editTxtFoodName);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "editTxtFoodName");
            foodFeedbackViewModel3.mapChangedNameAndBrand(obj, editText2.getText().toString());
            FoodFeedbackViewModel foodFeedbackViewModel4 = this.viewModel;
            if (foodFeedbackViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            if (foodFeedbackViewModel4.getCurrentMode() == FeedbackMode.FEEDBACK_NUTRITION_INFO) {
                FoodFeedbackViewModel foodFeedbackViewModel5 = this.viewModel;
                if (foodFeedbackViewModel5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                }
                foodFeedbackViewModel5.mapChangedNutritionValues(createNutritionValuesFromUserInput());
            }
            FoodFeedbackViewModel foodFeedbackViewModel6 = this.viewModel;
            if (foodFeedbackViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            if (foodFeedbackViewModel6.areChangesMade()) {
                performSave();
            } else {
                setResult(0);
                finish();
            }
        }
        return true;
    }

    private final void setupViewsBasedOnMode() {
        FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
        if (foodFeedbackViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        MfpFood mfpFood = foodFeedbackViewModel.getMfpFood();
        if (mfpFood != null) {
            FoodFeedbackViewModel foodFeedbackViewModel2 = this.viewModel;
            if (foodFeedbackViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            switch (foodFeedbackViewModel2.getCurrentMode()) {
                case FEEDBACK_NUTRITION_INFO:
                    ViewUtils.setVisible(true, _$_findCachedViewById(R.id.name_brand_layout));
                    setupFoodNameBrand(mfpFood);
                    ViewUtils.setVisible(true, _$_findCachedViewById(R.id.save_to_foods_toggle));
                    setupSwitchToggleListener();
                    ViewUtils.setVisible(true, _$_findCachedViewById(R.id.serving_size_layout));
                    setupServingSize(mfpFood);
                    ViewStub viewStub = (ViewStub) findViewById(R.id.nutrient_facts_layout);
                    Intrinsics.checkExpressionValueIsNotNull(viewStub, "nutrient_facts_layout");
                    viewStub.setLayoutResource(ConfigUtils.isNewNutrientsForUsEnabled(getConfigService()) ? R.layout.nutrition_facts_edit_nutrients_us : R.layout.nutrition_facts_edit_nutrients);
                    ((ViewStub) findViewById(R.id.nutrient_facts_layout)).inflate();
                    MfpNutritionalContents nutritionalContents = mfpFood.getNutritionalContents();
                    Intrinsics.checkExpressionValueIsNotNull(nutritionalContents, "it.nutritionalContents");
                    List servingSizes = mfpFood.getServingSizes();
                    FoodFeedbackViewModel foodFeedbackViewModel3 = this.viewModel;
                    if (foodFeedbackViewModel3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    }
                    Object obj = servingSizes.get(foodFeedbackViewModel3.getSelectedServingId());
                    Intrinsics.checkExpressionValueIsNotNull(obj, "it.servingSizes[viewModel.selectedServingId]");
                    setupNutritionValues(nutritionalContents, (float) ((MfpServingSize) obj).getNutritionMultiplier().doubleValue());
                    Lazy<FoodFeedbackAnalyticsHelper> lazy = this.foodFeedbackAnalyticsHelper;
                    if (lazy == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("foodFeedbackAnalyticsHelper");
                    }
                    FoodFeedbackAnalyticsHelper foodFeedbackAnalyticsHelper2 = (FoodFeedbackAnalyticsHelper) lazy.get();
                    String id = mfpFood.getId();
                    Intrinsics.checkExpressionValueIsNotNull(id, "it.id");
                    String version = mfpFood.getVersion();
                    Intrinsics.checkExpressionValueIsNotNull(version, "it.version");
                    foodFeedbackAnalyticsHelper2.reportInputCorrectionViewed(id, version);
                    return;
                case FEEDBACK_NAME_BRAND:
                    ViewUtils.setVisible(true, _$_findCachedViewById(R.id.name_brand_layout));
                    setupFoodNameBrand(mfpFood);
                    Lazy<FoodFeedbackAnalyticsHelper> lazy2 = this.foodFeedbackAnalyticsHelper;
                    if (lazy2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("foodFeedbackAnalyticsHelper");
                    }
                    FoodFeedbackAnalyticsHelper foodFeedbackAnalyticsHelper3 = (FoodFeedbackAnalyticsHelper) lazy2.get();
                    String id2 = mfpFood.getId();
                    Intrinsics.checkExpressionValueIsNotNull(id2, "it.id");
                    String version2 = mfpFood.getVersion();
                    Intrinsics.checkExpressionValueIsNotNull(version2, "it.version");
                    foodFeedbackAnalyticsHelper3.reportInputCorrectionViewed(id2, version2);
                    return;
                case FEEDBACK_NO_USER_INPUT:
                    FoodFeedbackViewModel foodFeedbackViewModel4 = this.viewModel;
                    if (foodFeedbackViewModel4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                    }
                    foodFeedbackViewModel4.postToFeedbackService();
                    setResult(-1);
                    finish();
                    return;
                default:
                    return;
            }
        }
    }

    private final void setupSwitchToggleListener() {
        ((Switch) _$_findCachedViewById(R.id.switch_save_to_foods)).setOnCheckedChangeListener(new FoodFeedbackActivity$setupSwitchToggleListener$1(this));
    }

    private final void setupServingSize(MfpFood mfpFood) {
        this.servingSizeMenuClickListener = new FoodFeedbackActivity$setupServingSize$1(this, mfpFood);
        TextView textView = (TextView) _$_findCachedViewById(R.id.serving_size_values);
        Intrinsics.checkExpressionValueIsNotNull(textView, "serving_size_values");
        List servingSizes = mfpFood.getServingSizes();
        FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
        if (foodFeedbackViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        textView.setText(((MfpServingSize) servingSizes.get(foodFeedbackViewModel.getSelectedServingId())).descriptionWithAmount());
        ((TextView) _$_findCachedViewById(R.id.serving_size_values)).setOnClickListener(new FoodFeedbackActivity$setupServingSize$2(this, mfpFood));
    }

    private final void setupFoodNameBrand(MfpFood mfpFood) {
        ((EditText) _$_findCachedViewById(R.id.editTxtFoodName)).setText(mfpFood.getDescription());
        ((EditText) _$_findCachedViewById(R.id.editTxtBrandName)).setText(mfpFood.getBrandName());
    }

    /* access modifiers changed from: private */
    public final void setupNutritionValues(MfpNutritionalContents mfpNutritionalContents, float f) {
        TextView textView = (TextView) _$_findCachedViewById(R.id.calories);
        Lazy<UserEnergyService> lazy = this.userEnergyService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEnergyService");
        }
        Object obj = lazy.get();
        Intrinsics.checkExpressionValueIsNotNull(obj, "userEnergyService.get()");
        textView.setText(((UserEnergyService) obj).getCurrentEnergyStringId());
        float[] valuesWithDefault = NutritionalValues.valuesWithDefault(mfpNutritionalContents, f);
        EditText editText = (EditText) _$_findCachedViewById(R.id.editTextCalories);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editTextCalories");
        Lazy<UserEnergyService> lazy2 = this.userEnergyService;
        if (lazy2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEnergyService");
        }
        setEditTextValue(editText, ((UserEnergyService) lazy2.get()).getCurrentEnergy((double) valuesWithDefault[0]));
        if (ConfigUtils.isNewNutrientsForUsEnabled(getConfigService())) {
            EditText editText2 = (EditText) _$_findCachedViewById(R.id.editTextTotalFat);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "editTextTotalFat");
            setEditTextValue(editText2, valuesWithDefault[1]);
            EditText editText3 = (EditText) _$_findCachedViewById(R.id.editTextSaturated);
            Intrinsics.checkExpressionValueIsNotNull(editText3, "editTextSaturated");
            setEditTextValue(editText3, valuesWithDefault[2]);
            EditText editText4 = (EditText) _$_findCachedViewById(R.id.editTextTrans);
            Intrinsics.checkExpressionValueIsNotNull(editText4, "editTextTrans");
            setEditTextValue(editText4, valuesWithDefault[5]);
            EditText editText5 = (EditText) _$_findCachedViewById(R.id.editTextPolyunsaturated);
            Intrinsics.checkExpressionValueIsNotNull(editText5, "editTextPolyunsaturated");
            setEditTextValue(editText5, valuesWithDefault[3]);
            EditText editText6 = (EditText) _$_findCachedViewById(R.id.editTextMonosaturated);
            Intrinsics.checkExpressionValueIsNotNull(editText6, "editTextMonosaturated");
            setEditTextValue(editText6, valuesWithDefault[4]);
            EditText editText7 = (EditText) _$_findCachedViewById(R.id.editTextCholesterol);
            Intrinsics.checkExpressionValueIsNotNull(editText7, "editTextCholesterol");
            setEditTextValue(editText7, valuesWithDefault[6]);
            EditText editText8 = (EditText) _$_findCachedViewById(R.id.editTextSodium);
            Intrinsics.checkExpressionValueIsNotNull(editText8, "editTextSodium");
            setEditTextValue(editText8, valuesWithDefault[7]);
            EditText editText9 = (EditText) _$_findCachedViewById(R.id.editTextTotalCarbs);
            Intrinsics.checkExpressionValueIsNotNull(editText9, "editTextTotalCarbs");
            setEditTextValue(editText9, valuesWithDefault[9]);
            EditText editText10 = (EditText) _$_findCachedViewById(R.id.editTextDieFiber);
            Intrinsics.checkExpressionValueIsNotNull(editText10, "editTextDieFiber");
            setEditTextValue(editText10, valuesWithDefault[10]);
            EditText editText11 = (EditText) _$_findCachedViewById(R.id.editTextSugars);
            Intrinsics.checkExpressionValueIsNotNull(editText11, "editTextSugars");
            setEditTextValue(editText11, valuesWithDefault[11]);
            EditText editText12 = (EditText) _$_findCachedViewById(R.id.editTextAddedSugars);
            Intrinsics.checkExpressionValueIsNotNull(editText12, "editTextAddedSugars");
            setEditTextValue(editText12, valuesWithDefault[17]);
            EditText editText13 = (EditText) _$_findCachedViewById(R.id.editTextSugarAlcohols);
            Intrinsics.checkExpressionValueIsNotNull(editText13, "editTextSugarAlcohols");
            setEditTextValue(editText13, valuesWithDefault[19]);
            EditText editText14 = (EditText) _$_findCachedViewById(R.id.editTextProtein);
            Intrinsics.checkExpressionValueIsNotNull(editText14, "editTextProtein");
            setEditTextValue(editText14, valuesWithDefault[12]);
            EditText editText15 = (EditText) _$_findCachedViewById(R.id.editTextVitaminD);
            Intrinsics.checkExpressionValueIsNotNull(editText15, "editTextVitaminD");
            setEditTextValue(editText15, valuesWithDefault[18]);
            EditText editText16 = (EditText) _$_findCachedViewById(R.id.editTextCalcium);
            Intrinsics.checkExpressionValueIsNotNull(editText16, "editTextCalcium");
            setEditTextValue(editText16, valuesWithDefault[15]);
            EditText editText17 = (EditText) _$_findCachedViewById(R.id.editTextIron);
            Intrinsics.checkExpressionValueIsNotNull(editText17, "editTextIron");
            setEditTextValue(editText17, valuesWithDefault[16]);
            EditText editText18 = (EditText) _$_findCachedViewById(R.id.editTextPotassium);
            Intrinsics.checkExpressionValueIsNotNull(editText18, "editTextPotassium");
            setEditTextValue(editText18, valuesWithDefault[8]);
            EditText editText19 = (EditText) _$_findCachedViewById(R.id.editTextVitaminA);
            Intrinsics.checkExpressionValueIsNotNull(editText19, "editTextVitaminA");
            setEditTextValue(editText19, valuesWithDefault[13]);
            EditText editText20 = (EditText) _$_findCachedViewById(R.id.editTextVitaminC);
            Intrinsics.checkExpressionValueIsNotNull(editText20, "editTextVitaminC");
            setEditTextValue(editText20, valuesWithDefault[14]);
            return;
        }
        EditText editText21 = (EditText) _$_findCachedViewById(R.id.editTxtFat);
        Intrinsics.checkExpressionValueIsNotNull(editText21, "editTxtFat");
        setEditTextValue(editText21, valuesWithDefault[1]);
        EditText editText22 = (EditText) _$_findCachedViewById(R.id.editTxtSaturated);
        Intrinsics.checkExpressionValueIsNotNull(editText22, "editTxtSaturated");
        setEditTextValue(editText22, valuesWithDefault[2]);
        EditText editText23 = (EditText) _$_findCachedViewById(R.id.editTxtTrans);
        Intrinsics.checkExpressionValueIsNotNull(editText23, "editTxtTrans");
        setEditTextValue(editText23, valuesWithDefault[5]);
        EditText editText24 = (EditText) _$_findCachedViewById(R.id.editTxtPolyunsaturated);
        Intrinsics.checkExpressionValueIsNotNull(editText24, "editTxtPolyunsaturated");
        setEditTextValue(editText24, valuesWithDefault[3]);
        EditText editText25 = (EditText) _$_findCachedViewById(R.id.editTxtMonosaturated);
        Intrinsics.checkExpressionValueIsNotNull(editText25, "editTxtMonosaturated");
        setEditTextValue(editText25, valuesWithDefault[4]);
        EditText editText26 = (EditText) _$_findCachedViewById(R.id.editTxtCholesterol);
        Intrinsics.checkExpressionValueIsNotNull(editText26, "editTxtCholesterol");
        setEditTextValue(editText26, valuesWithDefault[6]);
        EditText editText27 = (EditText) _$_findCachedViewById(R.id.editTxtSodium);
        Intrinsics.checkExpressionValueIsNotNull(editText27, "editTxtSodium");
        setEditTextValue(editText27, valuesWithDefault[7]);
        EditText editText28 = (EditText) _$_findCachedViewById(R.id.editTxtCarbs);
        Intrinsics.checkExpressionValueIsNotNull(editText28, "editTxtCarbs");
        setEditTextValue(editText28, valuesWithDefault[9]);
        EditText editText29 = (EditText) _$_findCachedViewById(R.id.editTxtFiber);
        Intrinsics.checkExpressionValueIsNotNull(editText29, "editTxtFiber");
        setEditTextValue(editText29, valuesWithDefault[10]);
        EditText editText30 = (EditText) _$_findCachedViewById(R.id.editTxtSugars);
        Intrinsics.checkExpressionValueIsNotNull(editText30, "editTxtSugars");
        setEditTextValue(editText30, valuesWithDefault[11]);
        EditText editText31 = (EditText) _$_findCachedViewById(R.id.editTxtProtein);
        Intrinsics.checkExpressionValueIsNotNull(editText31, "editTxtProtein");
        setEditTextValue(editText31, valuesWithDefault[12]);
        EditText editText32 = (EditText) _$_findCachedViewById(R.id.editTxtCalcium);
        Intrinsics.checkExpressionValueIsNotNull(editText32, "editTxtCalcium");
        setEditTextValue(editText32, valuesWithDefault[15]);
        EditText editText33 = (EditText) _$_findCachedViewById(R.id.editTxtIron);
        Intrinsics.checkExpressionValueIsNotNull(editText33, "editTxtIron");
        setEditTextValue(editText33, valuesWithDefault[16]);
        EditText editText34 = (EditText) _$_findCachedViewById(R.id.editTxtPotassium);
        Intrinsics.checkExpressionValueIsNotNull(editText34, "editTxtPotassium");
        setEditTextValue(editText34, valuesWithDefault[8]);
        EditText editText35 = (EditText) _$_findCachedViewById(R.id.editTxtVitaminA);
        Intrinsics.checkExpressionValueIsNotNull(editText35, "editTxtVitaminA");
        setEditTextValue(editText35, valuesWithDefault[13]);
        EditText editText36 = (EditText) _$_findCachedViewById(R.id.editTxtVitaminC);
        Intrinsics.checkExpressionValueIsNotNull(editText36, "editTxtVitaminC");
        setEditTextValue(editText36, valuesWithDefault[14]);
    }

    private final NutritionalValues createNutritionValuesFromUserInput() {
        NutritionalValues nutritionalValues = new NutritionalValues();
        nutritionalValues.initAsBlank();
        Lazy<UserEnergyService> lazy = this.userEnergyService;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userEnergyService");
        }
        UserEnergyService userEnergyService2 = (UserEnergyService) lazy.get();
        EditText editText = (EditText) _$_findCachedViewById(R.id.editTextCalories);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editTextCalories");
        nutritionalValues.setNutrientIndex(0, userEnergyService2.getCalories(editText.getText().toString()));
        if (ConfigUtils.isNewNutrientsForUsEnabled(getConfigService())) {
            EditText editText2 = (EditText) _$_findCachedViewById(R.id.editTextTotalFat);
            Intrinsics.checkExpressionValueIsNotNull(editText2, "editTextTotalFat");
            nutritionalValues.setNutrientIndex(1, getLocalizedFloatValues(editText2.getText().toString()));
            EditText editText3 = (EditText) _$_findCachedViewById(R.id.editTextSaturated);
            Intrinsics.checkExpressionValueIsNotNull(editText3, "editTextSaturated");
            nutritionalValues.setNutrientIndex(2, getLocalizedFloatValues(editText3.getText().toString()));
            EditText editText4 = (EditText) _$_findCachedViewById(R.id.editTextTrans);
            Intrinsics.checkExpressionValueIsNotNull(editText4, "editTextTrans");
            nutritionalValues.setNutrientIndex(5, getLocalizedFloatValues(editText4.getText().toString()));
            EditText editText5 = (EditText) _$_findCachedViewById(R.id.editTextPolyunsaturated);
            Intrinsics.checkExpressionValueIsNotNull(editText5, "editTextPolyunsaturated");
            nutritionalValues.setNutrientIndex(3, getLocalizedFloatValues(editText5.getText().toString()));
            EditText editText6 = (EditText) _$_findCachedViewById(R.id.editTextMonosaturated);
            Intrinsics.checkExpressionValueIsNotNull(editText6, "editTextMonosaturated");
            nutritionalValues.setNutrientIndex(4, getLocalizedFloatValues(editText6.getText().toString()));
            EditText editText7 = (EditText) _$_findCachedViewById(R.id.editTextCholesterol);
            Intrinsics.checkExpressionValueIsNotNull(editText7, "editTextCholesterol");
            nutritionalValues.setNutrientIndex(6, getLocalizedFloatValues(editText7.getText().toString()));
            EditText editText8 = (EditText) _$_findCachedViewById(R.id.editTextSodium);
            Intrinsics.checkExpressionValueIsNotNull(editText8, "editTextSodium");
            nutritionalValues.setNutrientIndex(7, getLocalizedFloatValues(editText8.getText().toString()));
            EditText editText9 = (EditText) _$_findCachedViewById(R.id.editTextTotalCarbs);
            Intrinsics.checkExpressionValueIsNotNull(editText9, "editTextTotalCarbs");
            nutritionalValues.setNutrientIndex(9, getLocalizedFloatValues(editText9.getText().toString()));
            EditText editText10 = (EditText) _$_findCachedViewById(R.id.editTextDieFiber);
            Intrinsics.checkExpressionValueIsNotNull(editText10, "editTextDieFiber");
            nutritionalValues.setNutrientIndex(10, getLocalizedFloatValues(editText10.getText().toString()));
            EditText editText11 = (EditText) _$_findCachedViewById(R.id.editTextSugars);
            Intrinsics.checkExpressionValueIsNotNull(editText11, "editTextSugars");
            nutritionalValues.setNutrientIndex(11, getLocalizedFloatValues(editText11.getText().toString()));
            EditText editText12 = (EditText) _$_findCachedViewById(R.id.editTextAddedSugars);
            Intrinsics.checkExpressionValueIsNotNull(editText12, "editTextAddedSugars");
            nutritionalValues.setNutrientIndex(17, getLocalizedFloatValues(editText12.getText().toString()));
            EditText editText13 = (EditText) _$_findCachedViewById(R.id.editTextSugarAlcohols);
            Intrinsics.checkExpressionValueIsNotNull(editText13, "editTextSugarAlcohols");
            nutritionalValues.setNutrientIndex(19, getLocalizedFloatValues(editText13.getText().toString()));
            EditText editText14 = (EditText) _$_findCachedViewById(R.id.editTextProtein);
            Intrinsics.checkExpressionValueIsNotNull(editText14, "editTextProtein");
            nutritionalValues.setNutrientIndex(12, getLocalizedFloatValues(editText14.getText().toString()));
            EditText editText15 = (EditText) _$_findCachedViewById(R.id.editTextVitaminD);
            Intrinsics.checkExpressionValueIsNotNull(editText15, "editTextVitaminD");
            nutritionalValues.setNutrientIndex(18, getLocalizedFloatValues(editText15.getText().toString()));
            EditText editText16 = (EditText) _$_findCachedViewById(R.id.editTextCalcium);
            Intrinsics.checkExpressionValueIsNotNull(editText16, "editTextCalcium");
            nutritionalValues.setNutrientIndex(15, getLocalizedFloatValues(editText16.getText().toString()));
            EditText editText17 = (EditText) _$_findCachedViewById(R.id.editTextIron);
            Intrinsics.checkExpressionValueIsNotNull(editText17, "editTextIron");
            nutritionalValues.setNutrientIndex(16, getLocalizedFloatValues(editText17.getText().toString()));
            EditText editText18 = (EditText) _$_findCachedViewById(R.id.editTextPotassium);
            Intrinsics.checkExpressionValueIsNotNull(editText18, "editTextPotassium");
            nutritionalValues.setNutrientIndex(8, getLocalizedFloatValues(editText18.getText().toString()));
            EditText editText19 = (EditText) _$_findCachedViewById(R.id.editTextVitaminA);
            Intrinsics.checkExpressionValueIsNotNull(editText19, "editTextVitaminA");
            nutritionalValues.setNutrientIndex(13, getLocalizedFloatValues(editText19.getText().toString()));
            EditText editText20 = (EditText) _$_findCachedViewById(R.id.editTextVitaminC);
            Intrinsics.checkExpressionValueIsNotNull(editText20, "editTextVitaminC");
            nutritionalValues.setNutrientIndex(14, getLocalizedFloatValues(editText20.getText().toString()));
        } else {
            EditText editText21 = (EditText) _$_findCachedViewById(R.id.editTxtFat);
            Intrinsics.checkExpressionValueIsNotNull(editText21, "editTxtFat");
            nutritionalValues.setNutrientIndex(1, getLocalizedFloatValues(editText21.getText().toString()));
            EditText editText22 = (EditText) _$_findCachedViewById(R.id.editTxtSaturated);
            Intrinsics.checkExpressionValueIsNotNull(editText22, "editTxtSaturated");
            nutritionalValues.setNutrientIndex(2, getLocalizedFloatValues(editText22.getText().toString()));
            EditText editText23 = (EditText) _$_findCachedViewById(R.id.editTxtTrans);
            Intrinsics.checkExpressionValueIsNotNull(editText23, "editTxtTrans");
            nutritionalValues.setNutrientIndex(5, getLocalizedFloatValues(editText23.getText().toString()));
            EditText editText24 = (EditText) _$_findCachedViewById(R.id.editTxtPolyunsaturated);
            Intrinsics.checkExpressionValueIsNotNull(editText24, "editTxtPolyunsaturated");
            nutritionalValues.setNutrientIndex(3, getLocalizedFloatValues(editText24.getText().toString()));
            EditText editText25 = (EditText) _$_findCachedViewById(R.id.editTxtMonosaturated);
            Intrinsics.checkExpressionValueIsNotNull(editText25, "editTxtMonosaturated");
            nutritionalValues.setNutrientIndex(4, getLocalizedFloatValues(editText25.getText().toString()));
            EditText editText26 = (EditText) _$_findCachedViewById(R.id.editTxtCholesterol);
            Intrinsics.checkExpressionValueIsNotNull(editText26, "editTxtCholesterol");
            nutritionalValues.setNutrientIndex(6, getLocalizedFloatValues(editText26.getText().toString()));
            EditText editText27 = (EditText) _$_findCachedViewById(R.id.editTxtSodium);
            Intrinsics.checkExpressionValueIsNotNull(editText27, "editTxtSodium");
            nutritionalValues.setNutrientIndex(7, getLocalizedFloatValues(editText27.getText().toString()));
            EditText editText28 = (EditText) _$_findCachedViewById(R.id.editTxtCarbs);
            Intrinsics.checkExpressionValueIsNotNull(editText28, "editTxtCarbs");
            nutritionalValues.setNutrientIndex(9, getLocalizedFloatValues(editText28.getText().toString()));
            EditText editText29 = (EditText) _$_findCachedViewById(R.id.editTxtFiber);
            Intrinsics.checkExpressionValueIsNotNull(editText29, "editTxtFiber");
            nutritionalValues.setNutrientIndex(10, getLocalizedFloatValues(editText29.getText().toString()));
            EditText editText30 = (EditText) _$_findCachedViewById(R.id.editTxtSugars);
            Intrinsics.checkExpressionValueIsNotNull(editText30, "editTxtSugars");
            nutritionalValues.setNutrientIndex(11, getLocalizedFloatValues(editText30.getText().toString()));
            EditText editText31 = (EditText) _$_findCachedViewById(R.id.editTxtProtein);
            Intrinsics.checkExpressionValueIsNotNull(editText31, "editTxtProtein");
            nutritionalValues.setNutrientIndex(12, getLocalizedFloatValues(editText31.getText().toString()));
            EditText editText32 = (EditText) _$_findCachedViewById(R.id.editTxtCalcium);
            Intrinsics.checkExpressionValueIsNotNull(editText32, "editTxtCalcium");
            nutritionalValues.setNutrientIndex(15, getLocalizedFloatValues(editText32.getText().toString()));
            EditText editText33 = (EditText) _$_findCachedViewById(R.id.editTxtIron);
            Intrinsics.checkExpressionValueIsNotNull(editText33, "editTxtIron");
            nutritionalValues.setNutrientIndex(16, getLocalizedFloatValues(editText33.getText().toString()));
            EditText editText34 = (EditText) _$_findCachedViewById(R.id.editTxtPotassium);
            Intrinsics.checkExpressionValueIsNotNull(editText34, "editTxtPotassium");
            nutritionalValues.setNutrientIndex(8, getLocalizedFloatValues(editText34.getText().toString()));
            EditText editText35 = (EditText) _$_findCachedViewById(R.id.editTxtVitaminA);
            Intrinsics.checkExpressionValueIsNotNull(editText35, "editTxtVitaminA");
            nutritionalValues.setNutrientIndex(13, getLocalizedFloatValues(editText35.getText().toString()));
            EditText editText36 = (EditText) _$_findCachedViewById(R.id.editTxtVitaminC);
            Intrinsics.checkExpressionValueIsNotNull(editText36, "editTxtVitaminC");
            nutritionalValues.setNutrientIndex(14, getLocalizedFloatValues(editText36.getText().toString()));
        }
        return nutritionalValues;
    }

    private final float getLocalizedFloatValues(String str) {
        float f;
        CharSequence charSequence = str;
        if (charSequence.length() == 0) {
            return -1.0f;
        }
        char decimalSeparator = new DecimalFormatSymbols(Locale.getDefault()).getDecimalSeparator();
        if (StringsKt.contains(charSequence, decimalSeparator, true)) {
            f = NumberUtils.localeFloatFromString(str);
        } else {
            f = NumberUtils.localeFloatFromString(StringsKt.replace(StringsKt.replace(str, '.', decimalSeparator, true), ',', decimalSeparator, true));
        }
        return f;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        if ((r0.getCurrentMode() == com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackViewModel.FeedbackMode.FEEDBACK_NUTRITION_INFO ? caloriesNotEmpty() : true) != false) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void performSave() {
        /*
            r3 = this;
            boolean r0 = r3.nameNotEmpty()
            r1 = 1
            if (r0 == 0) goto L_0x0027
            boolean r0 = r3.brandLengthValid()
            if (r0 == 0) goto L_0x0027
            com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackViewModel r0 = r3.viewModel
            if (r0 != 0) goto L_0x0016
            java.lang.String r2 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x0016:
            com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackViewModel$FeedbackMode r0 = r0.getCurrentMode()
            com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackViewModel$FeedbackMode r2 = com.myfitnesspal.feature.foodfeedback.model.FoodFeedbackViewModel.FeedbackMode.FEEDBACK_NUTRITION_INFO
            if (r0 != r2) goto L_0x0023
            boolean r0 = r3.caloriesNotEmpty()
            goto L_0x0024
        L_0x0023:
            r0 = 1
        L_0x0024:
            if (r0 == 0) goto L_0x0027
            goto L_0x0028
        L_0x0027:
            r1 = 0
        L_0x0028:
            if (r1 == 0) goto L_0x002d
            r3.postToFeedbackServiceAndSave()
        L_0x002d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodfeedback.ui.activity.FoodFeedbackActivity.performSave():void");
    }

    private final boolean caloriesNotEmpty() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.editTextCalories);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editTextCalories");
        return validate(editText, FoodFeedbackActivity$caloriesNotEmpty$1.INSTANCE, R.string.feedback_validation_error_calorie, FoodFeedbackAnalyticsHelperImpl.ERROR_EMPTY_CALORIES);
    }

    private final boolean nameNotEmpty() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.editTxtFoodName);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editTxtFoodName");
        return validate(editText, FoodFeedbackActivity$nameNotEmpty$1.INSTANCE, R.string.feedback_validation_error, FoodFeedbackAnalyticsHelperImpl.ERROR_EMPTY_NAME);
    }

    private final boolean brandLengthValid() {
        EditText editText = (EditText) _$_findCachedViewById(R.id.editTxtBrandName);
        Intrinsics.checkExpressionValueIsNotNull(editText, "editTxtBrandName");
        return validate(editText, FoodFeedbackActivity$brandLengthValid$1.INSTANCE, R.string.brand_length_error, FoodFeedbackAnalyticsHelperImpl.ERROR_BRAND_LENGTH);
    }

    private final boolean validate(@NotNull EditText editText, Function1<? super EditText, Boolean> function1, int i, String str) {
        if (!((Boolean) function1.invoke(editText)).booleanValue()) {
            return true;
        }
        showSnackBar(i);
        FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
        if (foodFeedbackViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        MfpFood mfpFood = foodFeedbackViewModel.getMfpFood();
        if (mfpFood != null) {
            Lazy<FoodFeedbackAnalyticsHelper> lazy = this.foodFeedbackAnalyticsHelper;
            if (lazy == null) {
                Intrinsics.throwUninitializedPropertyAccessException("foodFeedbackAnalyticsHelper");
            }
            FoodFeedbackAnalyticsHelper foodFeedbackAnalyticsHelper2 = (FoodFeedbackAnalyticsHelper) lazy.get();
            String id = mfpFood.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "it.id");
            String version = mfpFood.getVersion();
            Intrinsics.checkExpressionValueIsNotNull(version, "it.version");
            foodFeedbackAnalyticsHelper2.reportFoodCorrectionFailed(id, version, str);
        }
        return false;
    }

    private final void postToFeedbackServiceAndSave() {
        FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
        if (foodFeedbackViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        foodFeedbackViewModel.postToFeedbackService();
        Switch switchR = (Switch) _$_findCachedViewById(R.id.switch_save_to_foods);
        Intrinsics.checkExpressionValueIsNotNull(switchR, "switch_save_to_foods");
        boolean isChecked = switchR.isChecked();
        FoodFeedbackViewModel foodFeedbackViewModel2 = this.viewModel;
        if (foodFeedbackViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        MfpFood mfpFood = foodFeedbackViewModel2.getMfpFood();
        if (mfpFood != null) {
            Lazy<FoodFeedbackAnalyticsHelper> lazy = this.foodFeedbackAnalyticsHelper;
            if (lazy == null) {
                Intrinsics.throwUninitializedPropertyAccessException("foodFeedbackAnalyticsHelper");
            }
            FoodFeedbackAnalyticsHelper foodFeedbackAnalyticsHelper2 = (FoodFeedbackAnalyticsHelper) lazy.get();
            String id = mfpFood.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "it.id");
            String version = mfpFood.getVersion();
            Intrinsics.checkExpressionValueIsNotNull(version, "it.version");
            FoodFeedbackViewModel foodFeedbackViewModel3 = this.viewModel;
            if (foodFeedbackViewModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            foodFeedbackAnalyticsHelper2.reportFoodCorrectionCompleted(id, version, foodFeedbackViewModel3.getChangedFieldsForAnalytics(), isChecked);
        }
        if (isChecked) {
            saveIfNoFoodWithSameName();
            return;
        }
        setResult(-1);
        finish();
    }

    private final void saveIfNoFoodWithSameName() {
        showProgressDialog(R.string.please_wait);
        FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
        if (foodFeedbackViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        foodFeedbackViewModel.checkIfFoodWithSameNameDescriptionExists().observe(this, new FoodFeedbackActivity$saveIfNoFoodWithSameName$1(this));
    }

    /* access modifiers changed from: private */
    public final void saveToMyFoods() {
        showProgressDialog(R.string.please_wait);
        FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
        if (foodFeedbackViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        }
        foodFeedbackViewModel.createNewFood().observe(this, new FoodFeedbackActivity$saveToMyFoods$1(this));
    }

    private final void showProgressDialog(int i) {
        Activity activity = getActivity();
        if (activity == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v4.app.FragmentActivity");
        } else if (((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag("progress_dialog") == null) {
            showDialogFragment(ProgressDialogFragment.newInstance(i), "progress_dialog");
        }
    }

    /* access modifiers changed from: private */
    public final void hideProgressDialog() {
        Activity activity = getActivity();
        if (activity != null) {
            Fragment findFragmentByTag = ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag("progress_dialog");
            if (!(findFragmentByTag instanceof ProgressDialogFragment)) {
                findFragmentByTag = null;
            }
            ProgressDialogFragment progressDialogFragment = (ProgressDialogFragment) findFragmentByTag;
            if (progressDialogFragment != null) {
                progressDialogFragment.dismiss();
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.support.v4.app.FragmentActivity");
    }

    /* access modifiers changed from: private */
    public final void showSnackBar(int i) {
        new SnackbarBuilder((LinearLayout) _$_findCachedViewById(R.id.food_edit_root)).setMessage(i).setDuration(0).show();
    }

    private final void setEditTextValue(EditText editText, float f) {
        String str;
        editText.setFilters(new InputFilter[0]);
        if (f >= BitmapDescriptorFactory.HUE_RED) {
            FoodFeedbackViewModel foodFeedbackViewModel = this.viewModel;
            if (foodFeedbackViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            }
            str = foodFeedbackViewModel.localeStringFromFloatWithMaxFractionDigits(f, 1);
        } else {
            str = "";
        }
        editText.setText(str);
        editText.setFilters((InputFilter[]) new DecimalDigitsInputFilter[]{new DecimalDigitsInputFilter(4, 1)});
    }
}
