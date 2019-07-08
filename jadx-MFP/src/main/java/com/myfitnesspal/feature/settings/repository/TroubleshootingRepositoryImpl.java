package com.myfitnesspal.feature.settings.repository;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import com.myfitnesspal.feature.settings.api.TroubleShootingApi;
import com.myfitnesspal.feature.settings.util.FileUtils;
import com.myfitnesspal.shared.util.FileUploadUtil;
import io.reactivex.Observable;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.MultipartBody.Part;
import okhttp3.RequestBody;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\tH\u0016J(\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/myfitnesspal/feature/settings/repository/TroubleshootingRepositoryImpl;", "Lcom/myfitnesspal/feature/settings/repository/TroubleshootingRepository;", "troubleshootingApi", "Lcom/myfitnesspal/feature/settings/api/TroubleShootingApi;", "(Lcom/myfitnesspal/feature/settings/api/TroubleShootingApi;)V", "getTroubleshootingApi", "()Lcom/myfitnesspal/feature/settings/api/TroubleShootingApi;", "checkDiagnosticCode", "Lio/reactivex/Observable;", "", "diagnosticCode", "uploadDiagnostics", "fileUri", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TroubleshootingRepositoryImpl.kt */
public final class TroubleshootingRepositoryImpl implements TroubleshootingRepository {
    public static final Companion Companion = new Companion(null);
    private static final String UPLOADED_DATA_PARAM = "uploaded_data";
    @NotNull
    private final TroubleShootingApi troubleshootingApi;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/feature/settings/repository/TroubleshootingRepositoryImpl$Companion;", "", "()V", "UPLOADED_DATA_PARAM", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TroubleshootingRepositoryImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public TroubleshootingRepositoryImpl(@NotNull TroubleShootingApi troubleShootingApi) {
        Intrinsics.checkParameterIsNotNull(troubleShootingApi, "troubleshootingApi");
        this.troubleshootingApi = troubleShootingApi;
    }

    @NotNull
    public final TroubleShootingApi getTroubleshootingApi() {
        return this.troubleshootingApi;
    }

    @NotNull
    public Observable<String> checkDiagnosticCode(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "diagnosticCode");
        Observable<String> checkDiagnosticsStatus = this.troubleshootingApi.checkDiagnosticsStatus(str);
        Intrinsics.checkExpressionValueIsNotNull(checkDiagnosticsStatus, "troubleshootingApi.check…icsStatus(diagnosticCode)");
        return checkDiagnosticsStatus;
    }

    @Nullable
    public Observable<String> uploadDiagnostics(@NotNull String str, @NotNull Uri uri, @NotNull Context context) {
        Intrinsics.checkParameterIsNotNull(str, "diagnosticCode");
        Intrinsics.checkParameterIsNotNull(uri, "fileUri");
        Intrinsics.checkParameterIsNotNull(context, "context");
        RequestBody create = RequestBody.create(MultipartBody.FORM, str);
        Intrinsics.checkExpressionValueIsNotNull(create, "RequestBody.create(Multi…ody.FORM, diagnosticCode)");
        File file = FileUtils.INSTANCE.getFile(uri);
        if (file != null) {
            ContentResolver contentResolver = context.getContentResolver();
            com.myfitnesspal.shared.util.FileUploadUtil.Companion companion = FileUploadUtil.Companion;
            String name = file.getName();
            Intrinsics.checkExpressionValueIsNotNull(name, "originalFile.name");
            if (contentResolver.getType(companion.getCacheShareUriForFile(context, name)) != null) {
                ContentResolver contentResolver2 = context.getContentResolver();
                com.myfitnesspal.shared.util.FileUploadUtil.Companion companion2 = FileUploadUtil.Companion;
                String name2 = file.getName();
                Intrinsics.checkExpressionValueIsNotNull(name2, "originalFile.name");
                RequestBody create2 = RequestBody.create(MediaType.parse(contentResolver2.getType(companion2.getCacheShareUriForFile(context, name2))), file);
                Intrinsics.checkExpressionValueIsNotNull(create2, "RequestBody.create(Media…le.name))), originalFile)");
                Part createFormData = Part.createFormData(UPLOADED_DATA_PARAM, file.getName(), create2);
                Intrinsics.checkExpressionValueIsNotNull(createFormData, "MultipartBody.Part.creat…ginalFile.name, filePart)");
                return this.troubleshootingApi.uploadDiagnostics(create, createFormData);
            }
        }
        return null;
    }
}
