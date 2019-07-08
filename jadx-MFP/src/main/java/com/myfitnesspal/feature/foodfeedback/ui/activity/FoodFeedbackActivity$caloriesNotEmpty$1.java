package com.myfitnesspal.feature.foodfeedback.ui.activity;

import android.text.Editable;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Landroid/widget/EditText;", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: FoodFeedbackActivity.kt */
final class FoodFeedbackActivity$caloriesNotEmpty$1 extends Lambda implements Function1<EditText, Boolean> {
    public static final FoodFeedbackActivity$caloriesNotEmpty$1 INSTANCE = new FoodFeedbackActivity$caloriesNotEmpty$1();

    FoodFeedbackActivity$caloriesNotEmpty$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        return Boolean.valueOf(invoke((EditText) obj));
    }

    public final boolean invoke(@NotNull EditText editText) {
        Intrinsics.checkParameterIsNotNull(editText, "receiver$0");
        Editable text = editText.getText();
        Intrinsics.checkExpressionValueIsNotNull(text, "this.text");
        return text.length() == 0;
    }
}
