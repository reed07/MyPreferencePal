package com.myfitnesspal.feature.settings.ui.activity;

import android.arch.lifecycle.Observer;
import com.myfitnesspal.feature.consents.model.Resource;
import com.myfitnesspal.feature.consents.model.Resource.Error;
import com.myfitnesspal.feature.consents.model.Resource.Loading;
import com.myfitnesspal.feature.consents.model.Resource.Success;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "resource", "Lcom/myfitnesspal/feature/consents/model/Resource;", "", "onChanged"}, k = 3, mv = {1, 1, 13})
/* compiled from: ChangePasswordActivity.kt */
final class ChangePasswordActivity$initUi$1<T> implements Observer<Resource<? extends Boolean>> {
    final /* synthetic */ ChangePasswordActivity this$0;

    ChangePasswordActivity$initUi$1(ChangePasswordActivity changePasswordActivity) {
        this.this$0 = changePasswordActivity;
    }

    public final void onChanged(@Nullable Resource<Boolean> resource) {
        if (resource instanceof Success) {
            this.this$0.continuePasswordChanged();
        } else if (resource instanceof Loading) {
            this.this$0.showLoading();
        } else if (resource instanceof Error) {
            this.this$0.showLoadError();
        }
    }
}
