package com.myfitnesspal.feature.settings.service;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Uri;
import com.myfitnesspal.shared.service.premium.PaymentsLogger;
import io.reactivex.Observable;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J4\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0015H\u0002J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\bH\u0016J\b\u0010\u001b\u001a\u00020\u0011H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/myfitnesspal/feature/settings/service/TroubleshootingServiceImpl;", "Lcom/myfitnesspal/feature/settings/service/TroubleshootingService;", "context", "Landroid/content/Context;", "connectivityManager", "Landroid/net/ConnectivityManager;", "(Landroid/content/Context;Landroid/net/ConnectivityManager;)V", "dataFilesDirectory", "", "kotlin.jvm.PlatformType", "mainDatabasePath", "paymentDatabasePath", "paymentsLogDirectory", "profilePath", "regularLogDirectory", "stockDataDatabasePath", "addFilesToZipFileArrays", "", "inputDirectory", "outputPrefix", "inputPaths", "", "outputFilenames", "getDiagnostics", "Lio/reactivex/Observable;", "Landroid/net/Uri;", "code", "processDeviceProfile", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TroubleshootingServiceImpl.kt */
public final class TroubleshootingServiceImpl implements TroubleshootingService {
    private static final String APP_PACKAGE = "com.myfitnesspal.android";
    public static final Companion Companion = new Companion(null);
    private static final String MAIN_DATABASE_FILENAME = "myfitnesspal.db";
    private static final String PAYMENT_DATABASE_FILENAME = "subscriptions.db";
    private static final String PAYMENT_LOGS_PREFIX = "payment";
    private static final String PROFILE_FILENAME = "profile.txt";
    private static final String REGULAR_LOGS_DIRECTORY = "logs";
    private static final String REGULAR_LOGS_PREFIX = "logs";
    private static final String STOCK_DATA_DATABASE_FILENAME = "stock_data.db";
    private static final String ZIP_FILENAME_FORMAT = "android-diagnostic-data-%s.zip";
    private ConnectivityManager connectivityManager;
    /* access modifiers changed from: private */
    public Context context;
    /* access modifiers changed from: private */
    public final String dataFilesDirectory;
    /* access modifiers changed from: private */
    public final String mainDatabasePath;
    /* access modifiers changed from: private */
    public final String paymentDatabasePath;
    /* access modifiers changed from: private */
    public final String paymentsLogDirectory;
    /* access modifiers changed from: private */
    public final String profilePath;
    /* access modifiers changed from: private */
    public final String regularLogDirectory;
    /* access modifiers changed from: private */
    public final String stockDataDatabasePath;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/myfitnesspal/feature/settings/service/TroubleshootingServiceImpl$Companion;", "", "()V", "APP_PACKAGE", "", "MAIN_DATABASE_FILENAME", "PAYMENT_DATABASE_FILENAME", "PAYMENT_LOGS_PREFIX", "PROFILE_FILENAME", "REGULAR_LOGS_DIRECTORY", "REGULAR_LOGS_PREFIX", "STOCK_DATA_DATABASE_FILENAME", "ZIP_FILENAME_FORMAT", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TroubleshootingServiceImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Inject
    public TroubleshootingServiceImpl(@NotNull Context context2, @NotNull ConnectivityManager connectivityManager2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(connectivityManager2, "connectivityManager");
        this.context = context2;
        this.connectivityManager = connectivityManager2;
        File filesDir = this.context.getFilesDir();
        Intrinsics.checkExpressionValueIsNotNull(filesDir, "context.filesDir");
        this.dataFilesDirectory = filesDir.getAbsolutePath();
        File databasePath = this.context.getDatabasePath("myfitnesspal.db");
        Intrinsics.checkExpressionValueIsNotNull(databasePath, "context.getDatabasePath(MAIN_DATABASE_FILENAME)");
        this.mainDatabasePath = databasePath.getAbsolutePath();
        File databasePath2 = this.context.getDatabasePath(PAYMENT_DATABASE_FILENAME);
        Intrinsics.checkExpressionValueIsNotNull(databasePath2, "context.getDatabasePath(PAYMENT_DATABASE_FILENAME)");
        this.paymentDatabasePath = databasePath2.getAbsolutePath();
        File databasePath3 = this.context.getDatabasePath("stock_data.db");
        Intrinsics.checkExpressionValueIsNotNull(databasePath3, "context.getDatabasePath(…K_DATA_DATABASE_FILENAME)");
        this.stockDataDatabasePath = databasePath3.getAbsolutePath();
        StringBuilder sb = new StringBuilder();
        sb.append(this.dataFilesDirectory);
        sb.append(File.separator);
        sb.append(PROFILE_FILENAME);
        this.profilePath = sb.toString();
        File logDirectory = PaymentsLogger.getLogDirectory(this.context);
        Intrinsics.checkExpressionValueIsNotNull(logDirectory, "PaymentsLogger.getLogDirectory(context)");
        this.paymentsLogDirectory = logDirectory.getAbsolutePath();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.dataFilesDirectory);
        sb2.append(File.separator);
        sb2.append("logs");
        this.regularLogDirectory = sb2.toString();
    }

    @NotNull
    public Observable<Uri> getDiagnostics(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "code");
        Observable<Uri> fromCallable = Observable.fromCallable(new TroubleshootingServiceImpl$getDiagnostics$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Observable.fromCallable<…I_PERMISSION) }\n        }");
        return fromCallable;
    }

    /* access modifiers changed from: private */
    public final void addFilesToZipFileArrays(String str, String str2, List<String> list, List<String> list2) {
        File[] listFiles;
        File file = new File(str);
        if (file.exists() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                Intrinsics.checkExpressionValueIsNotNull(file2, "file");
                String absolutePath = file2.getAbsolutePath();
                Intrinsics.checkExpressionValueIsNotNull(absolutePath, "file.absolutePath");
                list.add(absolutePath);
                StringBuilder sb = new StringBuilder();
                sb.append(str2);
                sb.append("-");
                sb.append(file2.getName());
                list2.add(sb.toString());
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046 A[Catch:{ Exception -> 0x00ff }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void processDeviceProfile() {
        /*
            r6 = this;
            java.text.DateFormat r0 = java.text.DateFormat.getTimeInstance()     // Catch:{ Exception -> 0x00ff }
            java.lang.String r1 = "df"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r1 = "gmt"
            java.util.TimeZone r1 = java.util.TimeZone.getTimeZone(r1)     // Catch:{ Exception -> 0x00ff }
            r0.setTimeZone(r1)     // Catch:{ Exception -> 0x00ff }
            android.net.ConnectivityManager r1 = r6.connectivityManager     // Catch:{ Exception -> 0x00ff }
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()     // Catch:{ Exception -> 0x00ff }
            r2 = 0
            if (r1 == 0) goto L_0x0024
            int r3 = r1.getType()     // Catch:{ Exception -> 0x00ff }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x00ff }
            goto L_0x0025
        L_0x0024:
            r3 = r2
        L_0x0025:
            r4 = 1
            if (r3 != 0) goto L_0x0029
            goto L_0x0034
        L_0x0029:
            int r5 = r3.intValue()     // Catch:{ Exception -> 0x00ff }
            if (r5 != r4) goto L_0x0034
            java.lang.String r3 = r1.getTypeName()     // Catch:{ Exception -> 0x00ff }
            goto L_0x0044
        L_0x0034:
            if (r3 != 0) goto L_0x0037
            goto L_0x0042
        L_0x0037:
            int r3 = r3.intValue()     // Catch:{ Exception -> 0x00ff }
            if (r3 != 0) goto L_0x0042
            java.lang.String r3 = r1.getSubtypeName()     // Catch:{ Exception -> 0x00ff }
            goto L_0x0044
        L_0x0042:
            java.lang.String r3 = ""
        L_0x0044:
            if (r1 == 0) goto L_0x004e
            boolean r1 = r1.isRoaming()     // Catch:{ Exception -> 0x00ff }
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)     // Catch:{ Exception -> 0x00ff }
        L_0x004e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00ff }
            r1.<init>()     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                MyFitnessPalApp App Version: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            android.content.Context r4 = r6.context     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = com.uacf.core.util.VersionUtils.getAppVersionName(r4)     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                OS Version: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "os.version"
            java.lang.String r4 = java.lang.System.getProperty(r4)     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                Brand: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = android.os.Build.BRAND     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                Model: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = android.os.Build.MODEL     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                Product: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = android.os.Build.PRODUCT     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                Display: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = android.os.Build.DISPLAY     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                Industrial Design: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = android.os.Build.DEVICE     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                Hardware Board: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = android.os.Build.BOARD     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                Device Build release: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = android.os.Build.VERSION.RELEASE     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                Device Build incremental: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = android.os.Build.VERSION.INCREMENTAL     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                Device Build SDK: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            int r4 = android.os.Build.VERSION.SDK_INT     // Catch:{ Exception -> 0x00ff }
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r4 = "\n                UTC Time: "
            r1.append(r4)     // Catch:{ Exception -> 0x00ff }
            java.util.Date r4 = new java.util.Date     // Catch:{ Exception -> 0x00ff }
            r4.<init>()     // Catch:{ Exception -> 0x00ff }
            java.lang.String r0 = r0.format(r4)     // Catch:{ Exception -> 0x00ff }
            r1.append(r0)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r0 = "\n                Network Type: "
            r1.append(r0)     // Catch:{ Exception -> 0x00ff }
            r1.append(r3)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r0 = "\n                Roaming: "
            r1.append(r0)     // Catch:{ Exception -> 0x00ff }
            r1.append(r2)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r0 = "\n                -- end --\n                "
            r1.append(r0)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r0 = r1.toString()     // Catch:{ Exception -> 0x00ff }
            java.lang.String r0 = kotlin.text.StringsKt.trimIndent(r0)     // Catch:{ Exception -> 0x00ff }
            java.lang.String r1 = r6.dataFilesDirectory     // Catch:{ Exception -> 0x00ff }
            java.lang.String r2 = "profile.txt"
            com.uacf.core.util.FileUtils.writeFile(r1, r0, r2)     // Catch:{ Exception -> 0x00ff }
            goto L_0x0105
        L_0x00ff:
            r0 = move-exception
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            com.uacf.core.util.Ln.e(r0)
        L_0x0105:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.settings.service.TroubleshootingServiceImpl.processDeviceProfile():void");
    }
}
