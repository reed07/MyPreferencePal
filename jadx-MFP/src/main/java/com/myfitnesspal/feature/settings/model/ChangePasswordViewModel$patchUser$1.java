package com.myfitnesspal.feature.settings.model;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H@ø\u0001\u0000"}, d2 = {"patchUser", "", "password", "", "continuation", "Lkotlin/coroutines/Continuation;", ""}, k = 3, mv = {1, 1, 13})
@DebugMetadata(c = "com/myfitnesspal/feature/settings/model/ChangePasswordViewModel", f = "ChangePasswordViewModel.kt", i = {0, 0}, l = {141, 147}, m = "patchUser", n = {"this", "password"}, s = {"L$0", "L$1"})
/* compiled from: ChangePasswordViewModel.kt */
final class ChangePasswordViewModel$patchUser$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ChangePasswordViewModel this$0;

    ChangePasswordViewModel$patchUser$1(ChangePasswordViewModel changePasswordViewModel, Continuation continuation) {
        this.this$0 = changePasswordViewModel;
        super(continuation);
    }

    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.patchUser(null, this);
    }
}
