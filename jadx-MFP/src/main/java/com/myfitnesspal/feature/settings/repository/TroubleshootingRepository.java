package com.myfitnesspal.feature.settings.repository;

import android.content.Context;
import android.net.Uri;
import io.reactivex.Observable;
import javax.inject.Singleton;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Singleton
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0004H&J(\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lcom/myfitnesspal/feature/settings/repository/TroubleshootingRepository;", "", "checkDiagnosticCode", "Lio/reactivex/Observable;", "", "diagnosticCode", "uploadDiagnostics", "fileUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TroubleshootingRepository.kt */
public interface TroubleshootingRepository {
    @NotNull
    Observable<String> checkDiagnosticCode(@NotNull String str);

    @Nullable
    Observable<String> uploadDiagnostics(@NotNull String str, @NotNull Uri uri, @NotNull Context context);
}
