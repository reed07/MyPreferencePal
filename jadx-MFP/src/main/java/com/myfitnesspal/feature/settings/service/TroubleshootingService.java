package com.myfitnesspal.feature.settings.service;

import android.net.Uri;
import io.reactivex.Observable;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007"}, d2 = {"Lcom/myfitnesspal/feature/settings/service/TroubleshootingService;", "", "getDiagnostics", "Lio/reactivex/Observable;", "Landroid/net/Uri;", "code", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TroubleshootingService.kt */
public interface TroubleshootingService {
    @NotNull
    Observable<Uri> getDiagnostics(@NotNull String str);
}
