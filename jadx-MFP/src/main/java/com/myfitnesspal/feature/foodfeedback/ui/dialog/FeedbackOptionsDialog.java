package com.myfitnesspal.feature.foodfeedback.ui.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodfeedback.ui.activity.FoodFeedbackActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u0010H\u0002J\b\u0010\u001a\u001a\u00020\u0010H\u0002J\b\u0010\u001b\u001a\u00020\u0010H\u0016J\b\u0010\u001c\u001a\u00020\u0010H\u0002J\b\u0010\u001d\u001a\u00020\u0010H\u0002J\u001a\u0010\u001e\u001a\u00020\u00102\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010!\u001a\u00020\u0018H\u0017J\b\u0010\"\u001a\u00020\u0010H\u0002R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000RE\u0010\b\u001a-\u0012#\u0012!\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\f¢\u0006\f\b\r\u0012\b\b\u000e\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00100\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006$"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/ui/dialog/FeedbackOptionsDialog;", "Landroid/support/design/widget/BottomSheetDialogFragment;", "()V", "bottomSheetBehavior", "Landroid/support/design/widget/BottomSheetBehavior;", "Landroid/view/View;", "checkChangeListener", "Landroid/widget/CompoundButton$OnCheckedChangeListener;", "onFeedbackSubmitClick", "Lkotlin/Function1;", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "Lkotlin/ParameterName;", "name", "feedbackTypes", "", "getOnFeedbackSubmitClick", "()Lkotlin/jvm/functions/Function1;", "setOnFeedbackSubmitClick", "(Lkotlin/jvm/functions/Function1;)V", "getSelectedFeedbacks", "selectedItemsIdList", "", "", "initListeners", "mapOptionsToFeedbackType", "onStart", "populateCheckBoxMap", "saveSelectedOptions", "setupDialog", "dialog", "Landroid/app/Dialog;", "style", "toggleSaveCheckMarkState", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FeedbackOptionsDialog.kt */
public final class FeedbackOptionsDialog extends BottomSheetDialogFragment {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String TAG = "FEEDBACK_OPTIONS_DIALOG";
    /* access modifiers changed from: private */
    public static HashMap<Integer, Boolean> checkBoxSelectionMap = new HashMap<>();
    private HashMap _$_findViewCache;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    private OnCheckedChangeListener checkChangeListener;
    @NotNull
    private Function1<? super ArrayList<String>, Unit> onFeedbackSubmitClick = FeedbackOptionsDialog$onFeedbackSubmitClick$1.INSTANCE;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b`\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/ui/dialog/FeedbackOptionsDialog$Companion;", "", "()V", "TAG", "", "checkBoxSelectionMap", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "newInstance", "Lcom/myfitnesspal/feature/foodfeedback/ui/dialog/FeedbackOptionsDialog;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FeedbackOptionsDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final FeedbackOptionsDialog newInstance() {
            return new FeedbackOptionsDialog();
        }
    }

    @JvmStatic
    @NotNull
    public static final FeedbackOptionsDialog newInstance() {
        return Companion.newInstance();
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
    public final Function1<ArrayList<String>, Unit> getOnFeedbackSubmitClick() {
        return this.onFeedbackSubmitClick;
    }

    public final void setOnFeedbackSubmitClick(@NotNull Function1<? super ArrayList<String>, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "<set-?>");
        this.onFeedbackSubmitClick = function1;
    }

    @SuppressLint({"RestrictedApi"})
    public void setupDialog(@Nullable Dialog dialog, int i) {
        super.setupDialog(dialog, i);
        if (dialog != null) {
            View inflate = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_feedback, null);
            dialog.setContentView(inflate);
            Intrinsics.checkExpressionValueIsNotNull(inflate, Param.CONTENT);
            ViewParent parent = inflate.getParent();
            if (parent != null) {
                this.bottomSheetBehavior = BottomSheetBehavior.from((View) parent);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.View");
        }
    }

    public void onStart() {
        super.onStart();
        initListeners();
        populateCheckBoxMap();
        mapOptionsToFeedbackType();
        toggleSaveCheckMarkState();
        BottomSheetBehavior<View> bottomSheetBehavior2 = this.bottomSheetBehavior;
        if (bottomSheetBehavior2 != null) {
            bottomSheetBehavior2.setState(3);
            bottomSheetBehavior2.setSkipCollapsed(true);
        }
    }

    private final void initListeners() {
        this.checkChangeListener = new FeedbackOptionsDialog$initListeners$1(this);
        Dialog dialog = getDialog();
        if (dialog != null) {
            ((ImageView) dialog.findViewById(R.id.save_check_mark)).setOnClickListener(new FeedbackOptionsDialog$initListeners$$inlined$apply$lambda$1(dialog, this));
            ((ImageView) dialog.findViewById(R.id.close_image)).setOnClickListener(new FeedbackOptionsDialog$initListeners$2$2(dialog));
            ((CheckBox) dialog.findViewById(R.id.incorrect_food_name)).setOnCheckedChangeListener(this.checkChangeListener);
            ((CheckBox) dialog.findViewById(R.id.incorrect_brand_name)).setOnCheckedChangeListener(this.checkChangeListener);
            ((CheckBox) dialog.findViewById(R.id.wrong_nutrition_info)).setOnCheckedChangeListener(this.checkChangeListener);
            ((CheckBox) dialog.findViewById(R.id.duplicate)).setOnCheckedChangeListener(this.checkChangeListener);
            ((CheckBox) dialog.findViewById(R.id.inappropriate_listing)).setOnCheckedChangeListener(this.checkChangeListener);
            ((CheckBox) dialog.findViewById(R.id.serving_size_not_available)).setOnCheckedChangeListener(this.checkChangeListener);
        }
    }

    private final void mapOptionsToFeedbackType() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            ((CheckBox) dialog.findViewById(R.id.incorrect_food_name)).setTag(R.id.feedback_type, "brand_name");
            ((CheckBox) dialog.findViewById(R.id.incorrect_brand_name)).setTag(R.id.feedback_type, "description");
            ((CheckBox) dialog.findViewById(R.id.wrong_nutrition_info)).setTag(R.id.feedback_type, "nutritional_contents");
            ((CheckBox) dialog.findViewById(R.id.duplicate)).setTag(R.id.feedback_type, FoodFeedbackActivity.DUPLICATE_ENTRY);
            ((CheckBox) dialog.findViewById(R.id.inappropriate_listing)).setTag(R.id.feedback_type, FoodFeedbackActivity.INAPPROPRIATE_LISTING);
            ((CheckBox) dialog.findViewById(R.id.serving_size_not_available)).setTag(R.id.feedback_type, FoodFeedbackActivity.PREFERRED_SERVING_NOT_AVAILABLE);
        }
    }

    /* access modifiers changed from: private */
    public final void toggleSaveCheckMarkState() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            ImageView imageView = (ImageView) dialog.findViewById(R.id.save_check_mark);
            if (imageView != null) {
                imageView.setVisibility(checkBoxSelectionMap.containsValue(Boolean.valueOf(true)) ? 0 : 4);
            }
        }
    }

    private final void populateCheckBoxMap() {
        Dialog dialog = getDialog();
        if (dialog != null) {
            CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.incorrect_food_name);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "incorrect_food_name");
            Integer valueOf = Integer.valueOf(checkBox.getId());
            CheckBox checkBox2 = (CheckBox) dialog.findViewById(R.id.incorrect_food_name);
            Intrinsics.checkExpressionValueIsNotNull(checkBox2, "incorrect_food_name");
            CheckBox checkBox3 = (CheckBox) dialog.findViewById(R.id.incorrect_brand_name);
            Intrinsics.checkExpressionValueIsNotNull(checkBox3, "incorrect_brand_name");
            Integer valueOf2 = Integer.valueOf(checkBox3.getId());
            CheckBox checkBox4 = (CheckBox) dialog.findViewById(R.id.incorrect_brand_name);
            Intrinsics.checkExpressionValueIsNotNull(checkBox4, "incorrect_brand_name");
            CheckBox checkBox5 = (CheckBox) dialog.findViewById(R.id.wrong_nutrition_info);
            Intrinsics.checkExpressionValueIsNotNull(checkBox5, "wrong_nutrition_info");
            Integer valueOf3 = Integer.valueOf(checkBox5.getId());
            CheckBox checkBox6 = (CheckBox) dialog.findViewById(R.id.wrong_nutrition_info);
            Intrinsics.checkExpressionValueIsNotNull(checkBox6, "wrong_nutrition_info");
            CheckBox checkBox7 = (CheckBox) dialog.findViewById(R.id.duplicate);
            Intrinsics.checkExpressionValueIsNotNull(checkBox7, "duplicate");
            Integer valueOf4 = Integer.valueOf(checkBox7.getId());
            CheckBox checkBox8 = (CheckBox) dialog.findViewById(R.id.duplicate);
            Intrinsics.checkExpressionValueIsNotNull(checkBox8, "duplicate");
            CheckBox checkBox9 = (CheckBox) dialog.findViewById(R.id.inappropriate_listing);
            Intrinsics.checkExpressionValueIsNotNull(checkBox9, FoodFeedbackActivity.INAPPROPRIATE_LISTING);
            Integer valueOf5 = Integer.valueOf(checkBox9.getId());
            CheckBox checkBox10 = (CheckBox) dialog.findViewById(R.id.inappropriate_listing);
            Intrinsics.checkExpressionValueIsNotNull(checkBox10, FoodFeedbackActivity.INAPPROPRIATE_LISTING);
            CheckBox checkBox11 = (CheckBox) dialog.findViewById(R.id.serving_size_not_available);
            Intrinsics.checkExpressionValueIsNotNull(checkBox11, "serving_size_not_available");
            Integer valueOf6 = Integer.valueOf(checkBox11.getId());
            CheckBox checkBox12 = (CheckBox) dialog.findViewById(R.id.serving_size_not_available);
            Intrinsics.checkExpressionValueIsNotNull(checkBox12, "serving_size_not_available");
            checkBoxSelectionMap = MapsKt.hashMapOf(TuplesKt.to(valueOf, Boolean.valueOf(checkBox2.isChecked())), TuplesKt.to(valueOf2, Boolean.valueOf(checkBox4.isChecked())), TuplesKt.to(valueOf3, Boolean.valueOf(checkBox6.isChecked())), TuplesKt.to(valueOf4, Boolean.valueOf(checkBox8.isChecked())), TuplesKt.to(valueOf5, Boolean.valueOf(checkBox10.isChecked())), TuplesKt.to(valueOf6, Boolean.valueOf(checkBox12.isChecked())));
        }
    }

    /* access modifiers changed from: private */
    public final void saveSelectedOptions() {
        Map map = checkBoxSelectionMap;
        Map linkedHashMap = new LinkedHashMap();
        for (Entry entry : map.entrySet()) {
            if (((Boolean) entry.getValue()).booleanValue()) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        this.onFeedbackSubmitClick.invoke(getSelectedFeedbacks(linkedHashMap.keySet()));
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0038 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0030 A[LOOP:0: B:1:0x000a->B:9:0x0030, LOOP_END] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.ArrayList<java.lang.String> getSelectedFeedbacks(java.util.Set<java.lang.Integer> r6) {
        /*
            r5 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r1 = r6.size()
            r2 = 0
        L_0x000a:
            if (r2 >= r1) goto L_0x0040
            android.app.Dialog r3 = r5.getDialog()
            if (r3 == 0) goto L_0x002d
            r4 = r6
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.lang.Object r4 = kotlin.collections.CollectionsKt.elementAt(r4, r2)
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            android.view.View r3 = r3.findViewById(r4)
            if (r3 == 0) goto L_0x002d
            r4 = 2131362568(0x7f0a0308, float:1.834492E38)
            java.lang.Object r3 = r3.getTag(r4)
            goto L_0x002e
        L_0x002d:
            r3 = 0
        L_0x002e:
            if (r3 == 0) goto L_0x0038
            java.lang.String r3 = (java.lang.String) r3
            r0.add(r3)
            int r2 = r2 + 1
            goto L_0x000a
        L_0x0038:
            kotlin.TypeCastException r6 = new kotlin.TypeCastException
            java.lang.String r0 = "null cannot be cast to non-null type kotlin.String"
            r6.<init>(r0)
            throw r6
        L_0x0040:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodfeedback.ui.dialog.FeedbackOptionsDialog.getSelectedFeedbacks(java.util.Set):java.util.ArrayList");
    }
}
