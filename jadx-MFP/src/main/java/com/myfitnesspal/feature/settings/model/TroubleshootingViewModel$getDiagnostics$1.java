package com.myfitnesspal.feature.settings.model;

import android.content.Context;
import android.net.Uri;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "", "it", "Landroid/net/Uri;", "apply"}, k = 3, mv = {1, 1, 13})
/* compiled from: TroubleshootingViewModel.kt */
final class TroubleshootingViewModel$getDiagnostics$1<T, R> implements Function<T, ObservableSource<? extends R>> {
    final /* synthetic */ Context $context;
    final /* synthetic */ ObjectRef $uri;
    final /* synthetic */ TroubleshootingViewModel this$0;

    TroubleshootingViewModel$getDiagnostics$1(TroubleshootingViewModel troubleshootingViewModel, ObjectRef objectRef, Context context) {
        this.this$0 = troubleshootingViewModel;
        this.$uri = objectRef;
        this.$context = context;
    }

    @Nullable
    public final Observable<String> apply(@NotNull Uri uri) {
        Intrinsics.checkParameterIsNotNull(uri, "it");
        this.$uri.element = uri;
        return this.this$0.troubleshootingRepository.uploadDiagnostics(this.this$0.getDiagnosticCode$app_googleRelease(), uri, this.$context);
    }
}
