package com.myfitnesspal.shared.extension;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\u0002H\u00012\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"withArgs", "T", "Landroid/support/v4/app/Fragment;", "argsBuilder", "Lkotlin/Function1;", "Landroid/os/Bundle;", "", "Lkotlin/ExtensionFunctionType;", "(Landroid/support/v4/app/Fragment;Lkotlin/jvm/functions/Function1;)Landroid/support/v4/app/Fragment;", "app_googleRelease"}, k = 2, mv = {1, 1, 13})
/* compiled from: FragmentExt.kt */
public final class FragmentExtKt {
    @NotNull
    public static final <T extends Fragment> T withArgs(@NotNull T t, @NotNull Function1<? super Bundle, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(t, "receiver$0");
        Intrinsics.checkParameterIsNotNull(function1, "argsBuilder");
        Bundle bundle = new Bundle();
        function1.invoke(bundle);
        t.setArguments(bundle);
        return t;
    }
}
