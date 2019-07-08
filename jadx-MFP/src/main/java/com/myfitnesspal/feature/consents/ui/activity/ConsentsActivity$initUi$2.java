package com.myfitnesspal.feature.consents.ui.activity;

import android.arch.lifecycle.Observer;
import android.view.View;
import android.widget.CheckBox;
import com.myfitnesspal.android.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "isSingleConsent", "", "onChanged", "(Ljava/lang/Boolean;)V"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsActivity.kt */
final class ConsentsActivity$initUi$2<T> implements Observer<Boolean> {
    final /* synthetic */ ConsentsActivity this$0;

    ConsentsActivity$initUi$2(ConsentsActivity consentsActivity) {
        this.this$0 = consentsActivity;
    }

    public final void onChanged(@Nullable Boolean bool) {
        if (bool == null) {
            Intrinsics.throwNpe();
        }
        if (bool.booleanValue() || this.this$0.getViewModel().isEditMode()) {
            CheckBox checkBox = (CheckBox) this.this$0._$_findCachedViewById(R.id.accept_all);
            Intrinsics.checkExpressionValueIsNotNull(checkBox, "accept_all");
            checkBox.setVisibility(8);
            View _$_findCachedViewById = this.this$0._$_findCachedViewById(R.id.divider1);
            Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById, "divider1");
            _$_findCachedViewById.setVisibility(8);
            return;
        }
        CheckBox checkBox2 = (CheckBox) this.this$0._$_findCachedViewById(R.id.accept_all);
        Intrinsics.checkExpressionValueIsNotNull(checkBox2, "accept_all");
        checkBox2.setVisibility(0);
        View _$_findCachedViewById2 = this.this$0._$_findCachedViewById(R.id.divider1);
        Intrinsics.checkExpressionValueIsNotNull(_$_findCachedViewById2, "divider1");
        _$_findCachedViewById2.setVisibility(0);
    }
}
