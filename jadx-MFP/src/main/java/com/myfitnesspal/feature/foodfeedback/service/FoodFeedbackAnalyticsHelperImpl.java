package com.myfitnesspal.feature.foodfeedback.service;

import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.uacf.core.util.MapUtil;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\tH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0016J&\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u0014H\u0016J.\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u00142\u0006\u0010\u0017\u001a\u00020\fH\u0016J \u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0016J\u0018\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\tH\u0016J\u0010\u0010\u001b\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\fH\u0016J\b\u0010\u001c\u001a\u00020\u000fH\u0016R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/service/FoodFeedbackAnalyticsHelperImpl;", "Lcom/myfitnesspal/feature/foodfeedback/service/FoodFeedbackAnalyticsHelper;", "analyticsService", "Ldagger/Lazy;", "Lcom/myfitnesspal/shared/service/analytics/AnalyticsService;", "(Ldagger/Lazy;)V", "getAnalyticsService", "()Ldagger/Lazy;", "flowId", "", "getCorrectedBy", "isFromFoodFeedback", "", "getFlowId", "reportFeedbackButtonTapped", "", "foodId", "foodVersionId", "reportFeedbackReasonsTapped", "reasons", "Ljava/util/ArrayList;", "reportFoodCorrectionCompleted", "changedFields", "saveEnabled", "reportFoodCorrectionFailed", "error", "reportInputCorrectionViewed", "reportSaveCorrectedVersionToggled", "resetFlowId", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FoodFeedbackAnalyticsHelperImpl.kt */
public final class FoodFeedbackAnalyticsHelperImpl implements FoodFeedbackAnalyticsHelper {
    private static final String CORRECTED_BY_USER = "user";
    public static final Companion Companion = new Companion(null);
    private static final String ERROR = "error";
    @NotNull
    public static final String ERROR_BRAND_LENGTH = "brand_length_invalid";
    @NotNull
    public static final String ERROR_EMPTY_CALORIES = "empty_calories";
    @NotNull
    public static final String ERROR_EMPTY_NAME = "empty_name";
    @NotNull
    public static final String ERROR_FOOD_SAME_NAME = "same_food_name_description";
    @NotNull
    public static final String ERROR_NO_INTERNET = "internet_access";
    private static final String FLOW_ID = "flow_id";
    private static final String FOOD_CORRECTION_COMPLETED = "input_food_correction_completed";
    private static final String FOOD_CORRECTION_FAILED = "input_food_correction_failed";
    private static final String FOOD_CORRECTION_VIEWED = "input_food_correction_viewed";
    private static final String FOOD_FEEDBACK_BUTTON_TAPPED = "food_feedback_button_tapped";
    private static final String FOOD_FEEDBACK_REASONS_TAPPED = "food_feedback_reasons_tapped";
    private static final String FOOD_ID = "food_id";
    private static final String FOOD_NOT_CORRECTED = "false";
    private static final String FOOD_VERSION_ID = "food_version_id";
    private static final String NUM_UPDATED_FIELDS = "num_updated_fields";
    private static final String REASONS = "reasons";
    private static final String SAVE_CORRECT_VERSION_TOGGLED = "save_corrected_version_toggled";
    private static final String SAVE_ENABLED = "save_private_food";
    private static final String SAVE_PRIVATE_FOOD = "save_private_food";
    private static final String UPDATED_FIELDS = "updated_fields";
    @NotNull
    private final Lazy<AnalyticsService> analyticsService;
    private String flowId;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0016\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/myfitnesspal/feature/foodfeedback/service/FoodFeedbackAnalyticsHelperImpl$Companion;", "", "()V", "CORRECTED_BY_USER", "", "ERROR", "ERROR_BRAND_LENGTH", "ERROR_EMPTY_CALORIES", "ERROR_EMPTY_NAME", "ERROR_FOOD_SAME_NAME", "ERROR_NO_INTERNET", "FLOW_ID", "FOOD_CORRECTION_COMPLETED", "FOOD_CORRECTION_FAILED", "FOOD_CORRECTION_VIEWED", "FOOD_FEEDBACK_BUTTON_TAPPED", "FOOD_FEEDBACK_REASONS_TAPPED", "FOOD_ID", "FOOD_NOT_CORRECTED", "FOOD_VERSION_ID", "NUM_UPDATED_FIELDS", "REASONS", "SAVE_CORRECT_VERSION_TOGGLED", "SAVE_ENABLED", "SAVE_PRIVATE_FOOD", "UPDATED_FIELDS", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FoodFeedbackAnalyticsHelperImpl.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @NotNull
    public String getCorrectedBy(boolean z) {
        return z ? "user" : "false";
    }

    public FoodFeedbackAnalyticsHelperImpl(@NotNull Lazy<AnalyticsService> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "analyticsService");
        this.analyticsService = lazy;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.flowId = uuid;
    }

    @NotNull
    public final Lazy<AnalyticsService> getAnalyticsService() {
        return this.analyticsService;
    }

    public void resetFlowId() {
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.flowId = uuid;
    }

    @NotNull
    public String getFlowId() {
        return this.flowId;
    }

    public void reportFeedbackButtonTapped(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "foodId");
        Intrinsics.checkParameterIsNotNull(str2, "foodVersionId");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FOOD_FEEDBACK_BUTTON_TAPPED, MapUtil.createMap("flow_id", this.flowId, "food_id", str, "food_version_id", str2));
    }

    public void reportInputCorrectionViewed(@NotNull String str, @NotNull String str2) {
        Intrinsics.checkParameterIsNotNull(str, "foodId");
        Intrinsics.checkParameterIsNotNull(str2, "foodVersionId");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FOOD_CORRECTION_VIEWED, MapUtil.createMap("flow_id", this.flowId, "food_id", str, "food_version_id", str2));
    }

    public void reportFeedbackReasonsTapped(@NotNull String str, @NotNull String str2, @NotNull ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(str, "foodId");
        Intrinsics.checkParameterIsNotNull(str2, "foodVersionId");
        Intrinsics.checkParameterIsNotNull(arrayList, REASONS);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FOOD_FEEDBACK_REASONS_TAPPED, MapUtil.createMap("flow_id", this.flowId, "food_id", str, "food_version_id", str2, REASONS, Arrays.toString(arrayList.toArray())));
    }

    public void reportSaveCorrectedVersionToggled(boolean z) {
        AnalyticsService analyticsService2 = (AnalyticsService) this.analyticsService.get();
        String str = SAVE_CORRECT_VERSION_TOGGLED;
        String[] strArr = new String[2];
        strArr[0] = "save_private_food";
        strArr[1] = z ? "yes" : "no";
        analyticsService2.reportEvent(str, MapUtil.createMap(strArr));
    }

    public void reportFoodCorrectionFailed(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.checkParameterIsNotNull(str, "foodId");
        Intrinsics.checkParameterIsNotNull(str2, "foodVersionId");
        Intrinsics.checkParameterIsNotNull(str3, "error");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FOOD_CORRECTION_FAILED, MapUtil.createMap("flow_id", this.flowId, "food_id", str, "food_version_id", str2, "error", str3));
    }

    public void reportFoodCorrectionCompleted(@NotNull String str, @NotNull String str2, @NotNull ArrayList<String> arrayList, boolean z) {
        Intrinsics.checkParameterIsNotNull(str, "foodId");
        Intrinsics.checkParameterIsNotNull(str2, "foodVersionId");
        Intrinsics.checkParameterIsNotNull(arrayList, "changedFields");
        ((AnalyticsService) this.analyticsService.get()).reportEvent(FOOD_CORRECTION_COMPLETED, MapUtil.createMap("flow_id", this.flowId, "food_id", str, "food_version_id", str2, NUM_UPDATED_FIELDS, String.valueOf(arrayList.size()), UPDATED_FIELDS, Arrays.toString(arrayList.toArray()), "save_private_food", String.valueOf(z)));
    }
}
