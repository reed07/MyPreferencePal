package com.myfitnesspal.feature.settings.service;

import android.net.Uri;
import com.myfitnesspal.android.BuildConfig;
import com.myfitnesspal.shared.constants.Constants.Database;
import com.myfitnesspal.shared.util.FileUploadUtil;
import com.uacf.core.util.FileUtils;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/net/Uri;", "call"}, k = 3, mv = {1, 1, 13})
/* compiled from: TroubleshootingServiceImpl.kt */
final class TroubleshootingServiceImpl$getDiagnostics$1<V> implements Callable<T> {
    final /* synthetic */ String $code;
    final /* synthetic */ TroubleshootingServiceImpl this$0;

    TroubleshootingServiceImpl$getDiagnostics$1(TroubleshootingServiceImpl troubleshootingServiceImpl, String str) {
        this.this$0 = troubleshootingServiceImpl;
        this.$code = str;
    }

    @NotNull
    public final Uri call() {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Object[] objArr = {this.$code};
        String format = String.format("android-diagnostic-data-%s.zip", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        List mutableListOf = CollectionsKt.mutableListOf(this.this$0.mainDatabasePath, this.this$0.paymentDatabasePath, this.this$0.stockDataDatabasePath, this.this$0.profilePath);
        List mutableListOf2 = CollectionsKt.mutableListOf(Database.MAIN_DATABASE_NAME, "subscriptions.db", Database.STOCK_DATABASE_NAME, "profile.txt");
        TroubleshootingServiceImpl troubleshootingServiceImpl = this.this$0;
        String access$getPaymentsLogDirectory$p = troubleshootingServiceImpl.paymentsLogDirectory;
        Intrinsics.checkExpressionValueIsNotNull(access$getPaymentsLogDirectory$p, "paymentsLogDirectory");
        troubleshootingServiceImpl.addFilesToZipFileArrays(access$getPaymentsLogDirectory$p, "payment", mutableListOf, mutableListOf2);
        TroubleshootingServiceImpl troubleshootingServiceImpl2 = this.this$0;
        troubleshootingServiceImpl2.addFilesToZipFileArrays(troubleshootingServiceImpl2.regularLogDirectory, "logs", mutableListOf, mutableListOf2);
        this.this$0.processDeviceProfile();
        String access$getDataFilesDirectory$p = this.this$0.dataFilesDirectory;
        Collection collection = mutableListOf;
        if (collection != null) {
            Object[] array = collection.toArray(new String[0]);
            if (array != null) {
                String[] strArr = (String[]) array;
                Collection collection2 = mutableListOf2;
                if (collection2 != null) {
                    Object[] array2 = collection2.toArray(new String[0]);
                    if (array2 != null) {
                        FileUtils.compress(access$getDataFilesDirectory$p, format, strArr, (String[]) array2);
                        if (new File(this.this$0.profilePath).exists()) {
                            new File(this.this$0.profilePath).delete();
                        }
                        Uri temporaryImageFilepath = FileUploadUtil.Companion.getTemporaryImageFilepath(this.this$0.context, format);
                        this.this$0.context.grantUriPermission(BuildConfig.APPLICATION_ID, temporaryImageFilepath, 3);
                        return temporaryImageFilepath;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
                }
                throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        }
        throw new TypeCastException("null cannot be cast to non-null type java.util.Collection<T>");
    }
}
