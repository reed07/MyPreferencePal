package com.myfitnesspal.feature.settings.model;

import android.net.Uri;
import com.facebook.share.internal.ShareConstants;
import com.myfitnesspal.feature.consents.model.Resource.Error;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
/* compiled from: TroubleshootingViewModel.kt */
final class TroubleshootingViewModel$getDiagnostics$3<T> implements Consumer<Throwable> {
    final /* synthetic */ ObjectRef $uri;
    final /* synthetic */ TroubleshootingViewModel this$0;

    TroubleshootingViewModel$getDiagnostics$3(TroubleshootingViewModel troubleshootingViewModel, ObjectRef objectRef) {
        this.this$0 = troubleshootingViewModel;
        this.$uri = objectRef;
    }

    public final void accept(Throwable th) {
        this.this$0.getUploadDiagnosticCodeResponse$app_googleRelease().postValue(new Error(new Throwable("File could not be uploaded")));
        TroubleshootingViewModel troubleshootingViewModel = this.this$0;
        T t = this.$uri.element;
        if (t == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ShareConstants.MEDIA_URI);
        }
        troubleshootingViewModel.clearFile((Uri) t);
    }
}
