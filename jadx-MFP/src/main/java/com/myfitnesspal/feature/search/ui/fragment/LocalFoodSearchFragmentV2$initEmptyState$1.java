package com.myfitnesspal.feature.search.ui.fragment;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.extension.SpannableUtil;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\b\u0001\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"setupEmptyResources", "", "image", "", "message", "invoke"}, k = 3, mv = {1, 1, 13})
/* compiled from: LocalFoodSearchFragmentV2.kt */
final class LocalFoodSearchFragmentV2$initEmptyState$1 extends Lambda implements Function2<Integer, Integer, Unit> {
    final /* synthetic */ View $view;
    final /* synthetic */ LocalFoodSearchFragmentV2 this$0;

    LocalFoodSearchFragmentV2$initEmptyState$1(LocalFoodSearchFragmentV2 localFoodSearchFragmentV2, View view) {
        this.this$0 = localFoodSearchFragmentV2;
        this.$view = view;
        super(2);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke(((Number) obj).intValue(), ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }

    public final void invoke(@DrawableRes int i, @StringRes int i2) {
        ((ImageView) this.$view.findViewById(R.id.emptyResultsIcon)).setImageResource(i);
        TextView textView = (TextView) this.$view.findViewById(R.id.emptyResultsMessage);
        Intrinsics.checkExpressionValueIsNotNull(textView, "view.emptyResultsMessage");
        String string = this.this$0.getString(i2);
        Intrinsics.checkExpressionValueIsNotNull(string, "getString(message)");
        SpannableUtil.setImageSpan(textView, string, LocalFoodSearchFragmentV2.PLUS_IMAGE_MAPPING);
    }
}
