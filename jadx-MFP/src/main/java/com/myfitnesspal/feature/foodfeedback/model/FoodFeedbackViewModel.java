package com.myfitnesspal.feature.foodfeedback.model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Intent;
import com.mopub.common.Constants;
import com.myfitnesspal.feature.foodfeedback.repository.FoodFeedbackAction;
import com.myfitnesspal.feature.foodfeedback.ui.activity.FoodFeedbackActivity;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpServingSize;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.UnitsUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref.ObjectRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002:\u0001PB\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\u0006\u0010/\u001a\u000200J\u0017\u00101\u001a\b\u0012\u0004\u0012\u000200022\u0006\u00103\u001a\u000204H\u0001J\f\u00105\u001a\b\u0012\u0004\u0012\u00020006J\u0006\u00107\u001a\u000208J\u0017\u00109\u001a\b\u0012\u0004\u0012\u00020:022\u0006\u0010;\u001a\u00020:H\u0001J\f\u0010<\u001a\b\u0012\u0004\u0012\u00020:06J\u001a\u0010=\u001a\u0004\u0018\u00010\t2\u0006\u0010>\u001a\u00020\u00062\u0006\u0010?\u001a\u00020\u0006H\u0002J\u0016\u0010@\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u001dj\b\u0012\u0004\u0012\u00020\u0006`\u001eJ\u001e\u0010A\u001a\u0002082\u0006\u0010B\u001a\u00020C2\u0006\u0010+\u001a\u00020,2\u0006\u0010\u0014\u001a\u00020\u0015J\u0016\u0010D\u001a\u00020\u00062\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020\fJ\u0016\u0010H\u001a\u0002082\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006J\u000e\u0010I\u001a\u0002082\u0006\u0010-\u001a\u00020.J\b\u0010J\u001a\u000208H\u0014J\u0011\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NH\u0001J\u0006\u0010O\u001a\u000208J\b\u0010\u001a\u001a\u00020\u0017H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R*\u0010\u0007\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\bj\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t`\nX\u0004¢\u0006\u0002\n\u0000R*\u0010\u000b\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\nX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u00020\u0017X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001e\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u001dj\b\u0012\u0004\u0012\u00020\u0006`\u001eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0004\u0018\u00010!X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u0010\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u000e¢\u0006\u0002\n\u0000¨\u0006Q"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel;", "Landroid/arch/lifecycle/ViewModel;", "Lcom/myfitnesspal/feature/foodfeedback/repository/FoodFeedbackAction;", "foodFeedbackRepository", "(Lcom/myfitnesspal/feature/foodfeedback/repository/FoodFeedbackAction;)V", "brandName", "", "changedNameBrandMap", "Ljava/util/HashMap;", "Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterString;", "Lkotlin/collections/HashMap;", "changedNutritionalValuesMap", "", "Lcom/myfitnesspal/feature/foodfeedback/model/BeforeAfterFloat;", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "getCompositeDisposable", "()Lio/reactivex/disposables/CompositeDisposable;", "compositeDisposable$delegate", "Lkotlin/Lazy;", "currentEnergyUnit", "Lcom/myfitnesspal/shared/util/UnitsUtils$Energy;", "currentMode", "Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel$FeedbackMode;", "getCurrentMode", "()Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel$FeedbackMode;", "setCurrentMode", "(Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel$FeedbackMode;)V", "feedbackTypes", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "foodName", "mfpFood", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "getMfpFood", "()Lcom/myfitnesspal/shared/model/v2/MfpFood;", "setMfpFood", "(Lcom/myfitnesspal/shared/model/v2/MfpFood;)V", "selectedServingId", "getSelectedServingId", "()I", "setSelectedServingId", "(I)V", "session", "Lcom/myfitnesspal/shared/service/session/Session;", "userCreatedNutritionalValues", "Lcom/myfitnesspal/shared/model/v1/NutritionalValues;", "areChangesMade", "", "checkIfFoodWithDescriptionExists", "Lio/reactivex/Single;", "foodExistsCheckItem", "Lcom/myfitnesspal/feature/foodfeedback/model/FoodExistsCheckItem;", "checkIfFoodWithSameNameDescriptionExists", "Landroid/arch/lifecycle/MutableLiveData;", "clearMaps", "", "createCustomFood", "Lcom/myfitnesspal/shared/model/v1/Food;", "food", "createNewFood", "getBeforeAfterStringWhenNotEqual", "originalValue", "newValue", "getChangedFieldsForAnalytics", "initialize", "intent", "Landroid/content/Intent;", "localeStringFromFloatWithMaxFractionDigits", "value", "", "maxFractionDigits", "mapChangedNameAndBrand", "mapChangedNutritionValues", "onCleared", "postFeedback", "Lio/reactivex/Completable;", "foodFeedbackItem", "Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackItem;", "postToFeedbackService", "FeedbackMode", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackViewModel.kt */
public final class FoodFeedbackViewModel extends ViewModel implements FoodFeedbackAction {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(FoodFeedbackViewModel.class), "compositeDisposable", "getCompositeDisposable()Lio/reactivex/disposables/CompositeDisposable;"))};
    private String brandName = "";
    private final HashMap<String, BeforeAfterString> changedNameBrandMap = new HashMap<>();
    private final HashMap<Integer, BeforeAfterFloat> changedNutritionalValuesMap = new HashMap<>();
    private final Lazy compositeDisposable$delegate = LazyKt.lazy(FoodFeedbackViewModel$compositeDisposable$2.INSTANCE);
    private Energy currentEnergyUnit = Energy.CALORIES;
    @NotNull
    public FeedbackMode currentMode;
    private ArrayList<String> feedbackTypes = new ArrayList<>();
    private final FoodFeedbackAction foodFeedbackRepository;
    private String foodName = "";
    @Nullable
    private MfpFood mfpFood;
    private int selectedServingId;
    private Session session;
    private NutritionalValues userCreatedNutritionalValues;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/model/FoodFeedbackViewModel$FeedbackMode;", "", "(Ljava/lang/String;I)V", "FEEDBACK_NUTRITION_INFO", "FEEDBACK_NAME_BRAND", "FEEDBACK_NO_USER_INPUT", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodFeedbackViewModel.kt */
    public enum FeedbackMode {
        FEEDBACK_NUTRITION_INFO,
        FEEDBACK_NAME_BRAND,
        FEEDBACK_NO_USER_INPUT
    }

    private final CompositeDisposable getCompositeDisposable() {
        Lazy lazy = this.compositeDisposable$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (CompositeDisposable) lazy.getValue();
    }

    @NotNull
    public Single<Boolean> checkIfFoodWithDescriptionExists(@NotNull FoodExistsCheckItem foodExistsCheckItem) {
        Intrinsics.checkParameterIsNotNull(foodExistsCheckItem, "foodExistsCheckItem");
        return this.foodFeedbackRepository.checkIfFoodWithDescriptionExists(foodExistsCheckItem);
    }

    @NotNull
    public Single<Food> createCustomFood(@NotNull Food food) {
        Intrinsics.checkParameterIsNotNull(food, "food");
        return this.foodFeedbackRepository.createCustomFood(food);
    }

    @NotNull
    public Completable postFeedback(@NotNull FoodFeedbackItem foodFeedbackItem) {
        Intrinsics.checkParameterIsNotNull(foodFeedbackItem, "foodFeedbackItem");
        return this.foodFeedbackRepository.postFeedback(foodFeedbackItem);
    }

    @Inject
    public FoodFeedbackViewModel(@NotNull FoodFeedbackAction foodFeedbackAction) {
        Intrinsics.checkParameterIsNotNull(foodFeedbackAction, "foodFeedbackRepository");
        this.foodFeedbackRepository = foodFeedbackAction;
    }

    @NotNull
    public final FeedbackMode getCurrentMode() {
        FeedbackMode feedbackMode = this.currentMode;
        if (feedbackMode == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentMode");
        }
        return feedbackMode;
    }

    public final void setCurrentMode(@NotNull FeedbackMode feedbackMode) {
        Intrinsics.checkParameterIsNotNull(feedbackMode, "<set-?>");
        this.currentMode = feedbackMode;
    }

    public final int getSelectedServingId() {
        return this.selectedServingId;
    }

    public final void setSelectedServingId(int i) {
        this.selectedServingId = i;
    }

    @Nullable
    public final MfpFood getMfpFood() {
        return this.mfpFood;
    }

    public final void setMfpFood(@Nullable MfpFood mfpFood2) {
        this.mfpFood = mfpFood2;
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        getCompositeDisposable().clear();
        super.onCleared();
    }

    public final void initialize(@NotNull Intent intent, @NotNull Session session2, @NotNull Energy energy) {
        Intrinsics.checkParameterIsNotNull(intent, Constants.INTENT_SCHEME);
        Intrinsics.checkParameterIsNotNull(session2, "session");
        Intrinsics.checkParameterIsNotNull(energy, "currentEnergyUnit");
        this.session = session2;
        this.currentEnergyUnit = energy;
        this.mfpFood = (MfpFood) intent.getParcelableExtra("extra_food");
        Serializable serializableExtra = intent.getSerializableExtra(FoodFeedbackActivity.EXTRA_FEEDBACK_TYPE);
        if (serializableExtra != null) {
            this.feedbackTypes = (ArrayList) serializableExtra;
            this.currentMode = setCurrentMode();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.String> /* = java.util.ArrayList<kotlin.String> */");
    }

    public final void clearMaps() {
        this.changedNutritionalValuesMap.clear();
        this.changedNameBrandMap.clear();
        this.foodName = "";
        this.brandName = "";
    }

    public final void mapChangedNutritionValues(@NotNull NutritionalValues nutritionalValues) {
        Intrinsics.checkParameterIsNotNull(nutritionalValues, "userCreatedNutritionalValues");
        this.userCreatedNutritionalValues = nutritionalValues;
        MfpFood mfpFood2 = this.mfpFood;
        if (mfpFood2 != null) {
            Object obj = mfpFood2.getServingSizes().get(this.selectedServingId);
            Intrinsics.checkExpressionValueIsNotNull(obj, "it.servingSizes[selectedServingId]");
            float[] valuesWithDefault = NutritionalValues.valuesWithDefault(mfpFood2.getNutritionalContents(), (float) ((MfpServingSize) obj).getNutritionMultiplier().doubleValue());
            int length = valuesWithDefault.length;
            for (int i = 0; i < length; i++) {
                if (nutritionalValues.getValues()[i] != NumberUtils.localeFloatFromString(localeStringFromFloatWithMaxFractionDigits(valuesWithDefault[i], 1))) {
                    this.changedNutritionalValuesMap.put(Integer.valueOf(i), new BeforeAfterFloat(valuesWithDefault[i], nutritionalValues.getValues()[i]));
                }
            }
        }
    }

    public final void mapChangedNameAndBrand(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "brandName");
        Intrinsics.checkParameterIsNotNull(str2, "foodName");
        this.brandName = str;
        this.foodName = str2;
        MfpFood mfpFood2 = this.mfpFood;
        if (mfpFood2 != null) {
            String strings = Strings.toString(mfpFood2.getBrandName());
            Intrinsics.checkExpressionValueIsNotNull(strings, "Strings.toString(it.brandName)");
            BeforeAfterString beforeAfterStringWhenNotEqual = getBeforeAfterStringWhenNotEqual(strings, str);
            if (beforeAfterStringWhenNotEqual != null) {
                this.changedNameBrandMap.put("brand_name", beforeAfterStringWhenNotEqual);
            }
            String strings2 = Strings.toString(mfpFood2.getDescription());
            Intrinsics.checkExpressionValueIsNotNull(strings2, "Strings.toString(it.description)");
            BeforeAfterString beforeAfterStringWhenNotEqual2 = getBeforeAfterStringWhenNotEqual(strings2, str2);
            if (beforeAfterStringWhenNotEqual2 != null) {
                this.changedNameBrandMap.put("description", beforeAfterStringWhenNotEqual2);
            }
        }
    }

    public final void postToFeedbackService() {
        NutritionalContentsFeedback nutritionalContentsFeedback;
        MfpFood mfpFood2;
        MfpFood mfpFood3 = this.mfpFood;
        if (mfpFood3 != null) {
            int i = this.selectedServingId;
            Object obj = mfpFood3.getServingSizes().get(this.selectedServingId);
            Intrinsics.checkExpressionValueIsNotNull(obj, "it.servingSizes[selectedServingId]");
            Double nutritionMultiplier = ((MfpServingSize) obj).getNutritionMultiplier();
            Intrinsics.checkExpressionValueIsNotNull(nutritionMultiplier, "it.servingSizes[selected…ngId].nutritionMultiplier");
            double doubleValue = nutritionMultiplier.doubleValue();
            Object obj2 = mfpFood3.getServingSizes().get(this.selectedServingId);
            Intrinsics.checkExpressionValueIsNotNull(obj2, "it.servingSizes[selectedServingId]");
            String unit = ((MfpServingSize) obj2).getUnit();
            Intrinsics.checkExpressionValueIsNotNull(unit, "it.servingSizes[selectedServingId].unit");
            Object obj3 = mfpFood3.getServingSizes().get(this.selectedServingId);
            Intrinsics.checkExpressionValueIsNotNull(obj3, "it.servingSizes[selectedServingId]");
            Double value = ((MfpServingSize) obj3).getValue();
            Intrinsics.checkExpressionValueIsNotNull(value, "it.servingSizes[selectedServingId].value");
            ServingSize servingSize = new ServingSize(i, doubleValue, unit, value.doubleValue());
            BeforeAfterCalories beforeAfterCalories = null;
            BeforeAfterFloat beforeAfterFloat = (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(0));
            if (beforeAfterFloat != null) {
                String name = this.currentEnergyUnit.name();
                if (name != null) {
                    String lowerCase = name.toLowerCase();
                    Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                    UnitValue unitValue = new UnitValue(lowerCase, UnitsUtils.getEnergyFloat(this.currentEnergyUnit, beforeAfterFloat.getBefore()));
                    String name2 = this.currentEnergyUnit.name();
                    if (name2 != null) {
                        String lowerCase2 = name2.toLowerCase();
                        Intrinsics.checkExpressionValueIsNotNull(lowerCase2, "(this as java.lang.String).toLowerCase()");
                        beforeAfterCalories = new BeforeAfterCalories(unitValue, new UnitValue(lowerCase2, UnitsUtils.getEnergyFloat(this.currentEnergyUnit, beforeAfterFloat.getAfter())));
                    } else {
                        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                    }
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            BeforeAfterCalories beforeAfterCalories2 = beforeAfterCalories;
            if (!this.changedNutritionalValuesMap.isEmpty()) {
                mfpFood2 = mfpFood3;
                NutritionalContentsFeedback nutritionalContentsFeedback2 = new NutritionalContentsFeedback(servingSize, beforeAfterCalories2, (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(1)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(2)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(3)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(4)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(5)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(6)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(7)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(8)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(9)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(10)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(11)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(12)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(13)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(14)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(15)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(16)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(17)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(18)), (BeforeAfterFloat) this.changedNutritionalValuesMap.get(Integer.valueOf(19)));
                nutritionalContentsFeedback = nutritionalContentsFeedback2;
            } else {
                mfpFood2 = mfpFood3;
                nutritionalContentsFeedback = null;
            }
            Feedback feedback = new Feedback(this.feedbackTypes.contains(FoodFeedbackActivity.DUPLICATE_ENTRY), this.feedbackTypes.contains(FoodFeedbackActivity.INAPPROPRIATE_LISTING), this.feedbackTypes.contains(FoodFeedbackActivity.PREFERRED_SERVING_NOT_AVAILABLE), (BeforeAfterString) this.changedNameBrandMap.get("brand_name"), (BeforeAfterString) this.changedNameBrandMap.get("description"), nutritionalContentsFeedback);
            String id = mfpFood2.getId();
            Intrinsics.checkExpressionValueIsNotNull(id, "it.id");
            String version = mfpFood2.getVersion();
            Intrinsics.checkExpressionValueIsNotNull(version, "it.version");
            getCompositeDisposable().add(this.foodFeedbackRepository.postFeedback(new FoodFeedbackItem(id, version, feedback)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(FoodFeedbackViewModel$postToFeedbackService$1$2.INSTANCE, FoodFeedbackViewModel$postToFeedbackService$1$3.INSTANCE));
        }
    }

    @NotNull
    public final MutableLiveData<Food> createNewFood() {
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = new MutableLiveData();
        MfpFood mfpFood2 = this.mfpFood;
        if (mfpFood2 != null) {
            Session session2 = this.session;
            if (session2 != null) {
                User user = session2.getUser();
                Food food = new Food();
                food.setOriginalId(0);
                food.setOwnerUserId(user.getLocalId());
                food.setOwnerUserMasterId(user.getMasterDatabaseId());
                food.setDescription(this.foodName);
                food.setBrand(this.brandName);
                food.setGrams(1.0f);
                FoodPortion foodPortion = ((MfpServingSize) mfpFood2.getServingSizes().get(this.selectedServingId)).toFoodPortion();
                Intrinsics.checkExpressionValueIsNotNull(foodPortion, "selectedServing");
                foodPortion.setNutritionMultiplier(Double.valueOf(1.0d));
                foodPortion.setGramWeight(1.0f);
                food.setFoodPortions(new FoodPortion[]{foodPortion});
                food.setNutritionalValues(this.userCreatedNutritionalValues);
                getCompositeDisposable().add(this.foodFeedbackRepository.createCustomFood(food).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FoodFeedbackViewModel$createNewFood$$inlined$let$lambda$1(mfpFood2, this, objectRef), FoodFeedbackViewModel$createNewFood$1$1$2.INSTANCE));
            }
        }
        return (MutableLiveData) objectRef.element;
    }

    @NotNull
    public final MutableLiveData<Boolean> checkIfFoodWithSameNameDescriptionExists() {
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = new MutableLiveData();
        Session session2 = this.session;
        if (session2 != null) {
            String str = this.foodName;
            String str2 = this.brandName;
            if (str2 != null) {
                FoodExistsCheckItem foodExistsCheckItem = new FoodExistsCheckItem(str, StringsKt.trim((CharSequence) str2).toString(), true, false, 1, session2.getUser().getLocalId());
                getCompositeDisposable().add(this.foodFeedbackRepository.checkIfFoodWithDescriptionExists(foodExistsCheckItem).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new FoodFeedbackViewModel$checkIfFoodWithSameNameDescriptionExists$$inlined$let$lambda$1(this, objectRef), FoodFeedbackViewModel$checkIfFoodWithSameNameDescriptionExists$1$2.INSTANCE));
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
        return (MutableLiveData) objectRef.element;
    }

    public final boolean areChangesMade() {
        return !this.changedNutritionalValuesMap.isEmpty() || !this.changedNameBrandMap.isEmpty();
    }

    @NotNull
    public final ArrayList<String> getChangedFieldsForAnalytics() {
        ArrayList<String> arrayList = new ArrayList<>();
        if (this.mfpFood != null) {
            arrayList.addAll(this.changedNameBrandMap.keySet());
            Set keySet = this.changedNutritionalValuesMap.keySet();
            Intrinsics.checkExpressionValueIsNotNull(keySet, "changedNutritionalValuesMap.keys");
            Iterable<Integer> iterable = keySet;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (Integer num : iterable) {
                Intrinsics.checkExpressionValueIsNotNull(num, "nutrientId");
                arrayList2.add(NutritionalValues.getIdentifierForNutrientIndex(num.intValue()));
            }
            arrayList.addAll((List) arrayList2);
        }
        return arrayList;
    }

    @NotNull
    public final String localeStringFromFloatWithMaxFractionDigits(float f, int i) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        Intrinsics.checkExpressionValueIsNotNull(numberInstance, "nf");
        numberInstance.setGroupingUsed(false);
        numberInstance.setMaximumFractionDigits(i);
        String format = numberInstance.format((double) f);
        Intrinsics.checkExpressionValueIsNotNull(format, "nf.format(value.toDouble())");
        return format;
    }

    private final BeforeAfterString getBeforeAfterStringWhenNotEqual(String str, String str2) {
        if (!Intrinsics.areEqual((Object) str, (Object) str2)) {
            return new BeforeAfterString(str, str2);
        }
        return null;
    }

    private final FeedbackMode setCurrentMode() {
        if (this.feedbackTypes.contains("nutritional_contents")) {
            return FeedbackMode.FEEDBACK_NUTRITION_INFO;
        }
        if (this.feedbackTypes.contains("brand_name") || this.feedbackTypes.contains("description")) {
            return FeedbackMode.FEEDBACK_NAME_BRAND;
        }
        return FeedbackMode.FEEDBACK_NO_USER_INPUT;
    }
}
