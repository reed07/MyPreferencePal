package com.myfitnesspal.shared.extension;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.DimenRes;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a&\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a\u0012\u0010\t\u001a\u00020\n*\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n\u001a\u0014\u0010\t\u001a\u00020\n*\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u0006\u001a\u0012\u0010\r\u001a\u00020\n*\u00020\u00042\u0006\u0010\u000e\u001a\u00020\n¨\u0006\u000f"}, d2 = {"createAlertDialogRadioButtonClickListener", "Landroid/view/View$OnClickListener;", "Landroid/widget/AdapterView;", "itemView", "Landroid/view/View;", "position", "", "id", "", "dp", "", "dpValue", "value", "sp", "spValue", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
/* compiled from: ViewExt.kt */
public final class ViewExtKt {
    public static final float dp(@NotNull View view, float f) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        return TypedValue.applyDimension(1, f, resources.getDisplayMetrics());
    }

    public static final float dp(@NotNull View view, @DimenRes int i) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        return dp(view, context.getResources().getDimension(i));
    }

    public static final float sp(@NotNull View view, float f) {
        Intrinsics.checkParameterIsNotNull(view, "receiver$0");
        Context context = view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "context");
        Resources resources = context.getResources();
        Intrinsics.checkExpressionValueIsNotNull(resources, "context.resources");
        return TypedValue.applyDimension(2, f, resources.getDisplayMetrics());
    }

    @NotNull
    public static final OnClickListener createAlertDialogRadioButtonClickListener(@NotNull AdapterView<?> adapterView, @NotNull View view, int i, long j) {
        Intrinsics.checkParameterIsNotNull(adapterView, "receiver$0");
        Intrinsics.checkParameterIsNotNull(view, "itemView");
        ViewExtKt$createAlertDialogRadioButtonClickListener$1 viewExtKt$createAlertDialogRadioButtonClickListener$1 = new ViewExtKt$createAlertDialogRadioButtonClickListener$1(adapterView, view, i, j);
        return viewExtKt$createAlertDialogRadioButtonClickListener$1;
    }
}
