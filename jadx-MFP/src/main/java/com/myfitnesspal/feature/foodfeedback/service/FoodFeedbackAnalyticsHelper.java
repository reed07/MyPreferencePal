package com.myfitnesspal.feature.foodfeedback.service;

import java.util.ArrayList;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H&J0\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0016\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u00030\rj\b\u0012\u0004\u0012\u00020\u0003`\u000eH&J8\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0016\u0010\u0010\u001a\u0012\u0012\u0004\u0012\u00020\u00030\rj\b\u0012\u0004\u0012\u00020\u0003`\u000e2\u0006\u0010\u0011\u001a\u00020\u0005H&J \u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0003H&J\u0018\u0010\u0014\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0005H&J\b\u0010\u0016\u001a\u00020\bH&¨\u0006\u0017"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/service/FoodFeedbackAnalyticsHelper;", "", "getCorrectedBy", "", "isFromFoodFeedback", "", "getFlowId", "reportFeedbackButtonTapped", "", "foodId", "foodVersionId", "reportFeedbackReasonsTapped", "reasons", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "reportFoodCorrectionCompleted", "changedFields", "saveEnabled", "reportFoodCorrectionFailed", "error", "reportInputCorrectionViewed", "reportSaveCorrectedVersionToggled", "resetFlowId", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackAnalyticsHelper.kt */
public interface FoodFeedbackAnalyticsHelper {
    @NotNull
    String getCorrectedBy(boolean z);

    @NotNull
    String getFlowId();

    void reportFeedbackButtonTapped(@NotNull String str, @NotNull String str2);

    void reportFeedbackReasonsTapped(@NotNull String str, @NotNull String str2, @NotNull ArrayList<String> arrayList);

    void reportFoodCorrectionCompleted(@NotNull String str, @NotNull String str2, @NotNull ArrayList<String> arrayList, boolean z);

    void reportFoodCorrectionFailed(@NotNull String str, @NotNull String str2, @NotNull String str3);

    void reportInputCorrectionViewed(@NotNull String str, @NotNull String str2);

    void reportSaveCorrectedVersionToggled(boolean z);

    void resetFlowId();
}
