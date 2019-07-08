package com.myfitnesspal.feature.gdprhelp.util;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/myfitnesspal/feature/gdprhelp/util/GDPRHelpAnalyticsHelper;", "", "reportAboutUsSee", "", "reportFaqFeedbackSee", "reportHelpSee", "source", "", "reportLogout", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: GDPRHelpAnalyticsHelper.kt */
public interface GDPRHelpAnalyticsHelper {
    void reportAboutUsSee();

    void reportFaqFeedbackSee();

    void reportHelpSee(@NotNull String str);

    void reportLogout();
}
