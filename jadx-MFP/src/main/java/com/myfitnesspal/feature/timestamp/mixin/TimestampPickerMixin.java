package com.myfitnesspal.feature.timestamp.mixin;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import com.brightcove.player.event.AbstractEvent;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.ui.dialog.TimestampOptionsDialog;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.timestamp.model.TimestampOption;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FeatureHighlightEvent;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FoodScreenType;
import com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.TimeValue;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlight;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlight.Gravity;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlightView;
import com.myfitnesspal.shared.util.DateTimeUtils;
import dagger.Lazy;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u0000 b2\u00020\u0001:\u0002bcB3\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB;\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\rJ\b\u0010H\u001a\u00020\u0005H\u0002J\b\u0010I\u001a\u00020JH\u0002J\u0010\u0010K\u001a\u00020J2\u0006\u0010L\u001a\u00020MH\u0016J\u0012\u0010N\u001a\u00020J2\b\u0010O\u001a\u0004\u0018\u00010PH\u0016J\u0012\u0010Q\u001a\u00020J2\b\u0010R\u001a\u0004\u0018\u00010PH\u0016J\u0010\u0010S\u001a\u00020J2\u0006\u0010T\u001a\u00020UH\u0002J\u0012\u0010V\u001a\u00020J2\b\u0010W\u001a\u0004\u0018\u00010\u0005H\u0002J\u0010\u0010X\u001a\u00020J2\b\u0010Y\u001a\u0004\u0018\u00010\u0005J\u0010\u0010Z\u001a\u00020J2\u0006\u0010[\u001a\u00020\\H\u0002J\u0006\u0010]\u001a\u00020JJ\b\u0010^\u001a\u00020JH\u0002J\b\u0010_\u001a\u00020JH\u0002J\u0016\u0010`\u001a\u00020\u0016*\u0004\u0018\u00010\u00052\b\u0010a\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0017\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0018R\u000e\u0010\u001a\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0016X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001e\u0010!\u001a\u00020\"8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&RJ\u0010'\u001a>\u0012\u0014\u0012\u0012\u0012\u0002\b\u0003 )*\b\u0012\u0002\b\u0003\u0018\u00010(0( )*\u001f\u0012\u0016\b\u0001\u0012\u0012\u0012\u0002\b\u0003 )*\b\u0012\u0002\b\u0003\u0018\u00010(0(\u0018\u00010(¨\u0006\u00010(¨\u0006\u0001X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u000e¢\u0006\u0002\n\u0000R\u001e\u0010,\u001a\u00020-8\u0006@\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R$\u00108\u001a\u0002072\u0006\u00106\u001a\u0002078F@FX\u000e¢\u0006\f\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u000e\u0010=\u001a\u000207X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u000e¢\u0006\u0002\n\u0000R(\u0010@\u001a\u0004\u0018\u00010\u00052\b\u00106\u001a\u0004\u0018\u00010\u00058F@BX\u000e¢\u0006\f\u001a\u0004\bA\u0010\u001e\"\u0004\bB\u0010 R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010G\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin;", "Lcom/myfitnesspal/framework/mixin/RunnerLifecycleMixin;", "activity", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "latestEntryTimestamp", "Ljava/util/Date;", "timestampRow", "Lcom/myfitnesspal/feature/timestamp/view/TimestampRowView;", "screenType", "Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FoodScreenType;", "(Lcom/myfitnesspal/shared/ui/activity/MfpActivity;Ljava/util/Date;Lcom/myfitnesspal/feature/timestamp/view/TimestampRowView;Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FoodScreenType;)V", "timestampChangeListener", "Lcom/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$OnTimestampChangedListener;", "(Lcom/myfitnesspal/shared/ui/activity/MfpActivity;Ljava/util/Date;Lcom/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$OnTimestampChangedListener;Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FoodScreenType;Lcom/myfitnesspal/feature/timestamp/view/TimestampRowView;)V", "analyticsHelper", "Ldagger/Lazy;", "Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper;", "getAnalyticsHelper", "()Ldagger/Lazy;", "setAnalyticsHelper", "(Ldagger/Lazy;)V", "isEditingEntry", "", "isFeatureAvailable", "()Z", "isFeatureEnabled", "isFeatureSubscribed", "isShowingOptionPicker", "isShowingTimePicker", "getLatestEntryTimestamp", "()Ljava/util/Date;", "setLatestEntryTimestamp", "(Ljava/util/Date;)V", "localSettingsService", "Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "getLocalSettingsService", "()Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;", "setLocalSettingsService", "(Lcom/myfitnesspal/shared/service/localsettings/LocalSettingsService;)V", "navigationHelper", "Lcom/myfitnesspal/shared/ui/navigation/NavigationHelper;", "kotlin.jvm.PlatformType", "optionPicketDialog", "Lcom/myfitnesspal/feature/diary/ui/dialog/TimestampOptionsDialog;", "premiumService", "Lcom/myfitnesspal/feature/premium/service/PremiumService;", "getPremiumService", "()Lcom/myfitnesspal/feature/premium/service/PremiumService;", "setPremiumService", "(Lcom/myfitnesspal/feature/premium/service/PremiumService;)V", "getScreenType", "()Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FoodScreenType;", "setScreenType", "(Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FoodScreenType;)V", "value", "Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;", "selectedOption", "getSelectedOption", "()Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;", "setSelectedOption", "(Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;)V", "selectedOptionValue", "timePickerDialog", "Landroid/app/TimePickerDialog;", "timestamp", "getTimestamp", "setTimestamp", "getTimestampChangeListener", "()Lcom/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$OnTimestampChangedListener;", "setTimestampChangeListener", "(Lcom/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$OnTimestampChangedListener;)V", "timestampValue", "constructCurrentTime", "highlightFeature", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onPostCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSaveInstanceState", "outState", "onTimestampFeatureDismissed", "analyticsEvent", "Lcom/myfitnesspal/feature/timestamp/service/TimestampAnalyticsHelper$FeatureHighlightEvent;", "setNewTimestamp", "newTimestamp", "showCurrentTimestamp", "currentTime", "showPremiumUpsell", "featureId", "", "showTimestampOptions", "showTimestampPicker", "validateDialogs", "equalTo", "other", "Companion", "OnTimestampChangedListener", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: TimestampPickerMixin.kt */
public final class TimestampPickerMixin extends RunnerLifecycleMixin {
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String EXTRA_CURRENT_SELECTED_TIMESTAMP = "current_selected_timestamp";
    @NotNull
    public static final String EXTRA_CURRENT_SELECTED_TIME_OPTION = "current_selected_time_option";
    @NotNull
    public static final String EXTRA_IS_SHOWING_OPTION_PICKER = "is_showing_option_picker";
    @NotNull
    public static final String EXTRA_IS_SHOWING_TIME_PICKER = "is_showing_time_picker";
    /* access modifiers changed from: private */
    public final MfpActivity activity;
    @Inject
    @NotNull
    public Lazy<TimestampAnalyticsHelper> analyticsHelper;
    /* access modifiers changed from: private */
    public boolean isEditingEntry;
    /* access modifiers changed from: private */
    public final boolean isFeatureSubscribed;
    /* access modifiers changed from: private */
    public boolean isShowingOptionPicker;
    /* access modifiers changed from: private */
    public boolean isShowingTimePicker;
    @Nullable
    private Date latestEntryTimestamp;
    @Inject
    @NotNull
    public LocalSettingsService localSettingsService;
    private final NavigationHelper<NavigationHelper<?>> navigationHelper;
    private TimestampOptionsDialog optionPicketDialog;
    @Inject
    @NotNull
    public PremiumService premiumService;
    @Nullable
    private FoodScreenType screenType;
    private TimestampOption selectedOptionValue;
    private TimePickerDialog timePickerDialog;
    @Nullable
    private OnTimestampChangedListener timestampChangeListener;
    private final TimestampRowView timestampRow;
    private Date timestampValue;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$Companion;", "", "()V", "EXTRA_CURRENT_SELECTED_TIMESTAMP", "", "EXTRA_CURRENT_SELECTED_TIME_OPTION", "EXTRA_IS_SHOWING_OPTION_PICKER", "EXTRA_IS_SHOWING_TIME_PICKER", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TimestampPickerMixin.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/myfitnesspal/feature/timestamp/mixin/TimestampPickerMixin$OnTimestampChangedListener;", "", "onTimestampChange", "", "time", "Ljava/util/Date;", "selectedOption", "Lcom/myfitnesspal/feature/timestamp/model/TimestampOption;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: TimestampPickerMixin.kt */
    public interface OnTimestampChangedListener {
        void onTimestampChange(@Nullable Date date, @NotNull TimestampOption timestampOption);
    }

    @JvmOverloads
    public TimestampPickerMixin(@NotNull MfpActivity mfpActivity) {
        this(mfpActivity, null, null, null, 14, null);
    }

    @JvmOverloads
    public TimestampPickerMixin(@NotNull MfpActivity mfpActivity, @Nullable Date date) {
        this(mfpActivity, date, null, null, 12, null);
    }

    @JvmOverloads
    public TimestampPickerMixin(@NotNull MfpActivity mfpActivity, @Nullable Date date, @Nullable OnTimestampChangedListener onTimestampChangedListener) {
        this(mfpActivity, date, onTimestampChangedListener, null, null, 24, null);
    }

    @JvmOverloads
    public TimestampPickerMixin(@NotNull MfpActivity mfpActivity, @Nullable Date date, @Nullable OnTimestampChangedListener onTimestampChangedListener, @Nullable FoodScreenType foodScreenType) {
        this(mfpActivity, date, onTimestampChangedListener, foodScreenType, null, 16, null);
    }

    @JvmOverloads
    public TimestampPickerMixin(@NotNull MfpActivity mfpActivity, @Nullable Date date, @Nullable TimestampRowView timestampRowView) {
        this(mfpActivity, date, timestampRowView, null, 8, null);
    }

    @Nullable
    public final Date getLatestEntryTimestamp() {
        return this.latestEntryTimestamp;
    }

    public final void setLatestEntryTimestamp(@Nullable Date date) {
        this.latestEntryTimestamp = date;
    }

    @Nullable
    public final OnTimestampChangedListener getTimestampChangeListener() {
        return this.timestampChangeListener;
    }

    public final void setTimestampChangeListener(@Nullable OnTimestampChangedListener onTimestampChangedListener) {
        this.timestampChangeListener = onTimestampChangedListener;
    }

    @JvmOverloads
    public /* synthetic */ TimestampPickerMixin(MfpActivity mfpActivity, Date date, OnTimestampChangedListener onTimestampChangedListener, FoodScreenType foodScreenType, TimestampRowView timestampRowView, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(mfpActivity, date, onTimestampChangedListener, (i & 8) != 0 ? null : foodScreenType, (i & 16) != 0 ? null : timestampRowView);
    }

    @Nullable
    public final FoodScreenType getScreenType() {
        return this.screenType;
    }

    public final void setScreenType(@Nullable FoodScreenType foodScreenType) {
        this.screenType = foodScreenType;
    }

    @JvmOverloads
    public TimestampPickerMixin(@NotNull MfpActivity mfpActivity, @Nullable Date date, @Nullable OnTimestampChangedListener onTimestampChangedListener, @Nullable FoodScreenType foodScreenType, @Nullable TimestampRowView timestampRowView) {
        Intrinsics.checkParameterIsNotNull(mfpActivity, AbstractEvent.ACTIVITY);
        super(mfpActivity);
        this.activity = mfpActivity;
        this.latestEntryTimestamp = date;
        this.timestampChangeListener = onTimestampChangedListener;
        this.screenType = foodScreenType;
        this.timestampRow = timestampRowView;
        this.selectedOptionValue = this.latestEntryTimestamp != null ? TimestampOption.LATEST_MEAL_ENTRY_TIME : TimestampOption.CURRENT_TIME;
        Date date2 = this.latestEntryTimestamp;
        if (date2 == null) {
            date2 = constructCurrentTime();
        }
        this.timestampValue = date2;
        this.navigationHelper = this.activity.getNavigationHelper();
        Context applicationContext = this.activity.getApplicationContext();
        if (applicationContext != null) {
            ((MyFitnessPalApp) applicationContext).component().inject(this);
            PremiumService premiumService2 = this.premiumService;
            if (premiumService2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("premiumService");
            }
            this.isFeatureSubscribed = premiumService2.isFeatureSubscribed(PremiumFeature.FoodTimestamps);
            TimestampRowView timestampRowView2 = this.timestampRow;
            if (timestampRowView2 != null) {
                if (this.isFeatureSubscribed) {
                    LocalSettingsService localSettingsService2 = this.localSettingsService;
                    if (localSettingsService2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("localSettingsService");
                    }
                    if (localSettingsService2.shouldShowFoodTimestamps()) {
                        timestampRowView2.setTime(getTimestamp());
                    } else {
                        timestampRowView2.setVisibility(8);
                        setTimestamp(null);
                    }
                } else if (isFeatureAvailable()) {
                    timestampRowView2.showLock();
                    setTimestamp(null);
                } else {
                    timestampRowView2.setVisibility(8);
                    setTimestamp(null);
                    return;
                }
                timestampRowView2.setOnClickListener(new TimestampPickerMixin$$special$$inlined$apply$lambda$1(this));
                LocalSettingsService localSettingsService3 = this.localSettingsService;
                if (localSettingsService3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("localSettingsService");
                }
                if (!localSettingsService3.didUserSeeTimestampFeature()) {
                    highlightFeature();
                    return;
                }
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.app.MyFitnessPalApp");
    }

    @NotNull
    public final PremiumService getPremiumService() {
        PremiumService premiumService2 = this.premiumService;
        if (premiumService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("premiumService");
        }
        return premiumService2;
    }

    public final void setPremiumService(@NotNull PremiumService premiumService2) {
        Intrinsics.checkParameterIsNotNull(premiumService2, "<set-?>");
        this.premiumService = premiumService2;
    }

    @NotNull
    public final LocalSettingsService getLocalSettingsService() {
        LocalSettingsService localSettingsService2 = this.localSettingsService;
        if (localSettingsService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("localSettingsService");
        }
        return localSettingsService2;
    }

    public final void setLocalSettingsService(@NotNull LocalSettingsService localSettingsService2) {
        Intrinsics.checkParameterIsNotNull(localSettingsService2, "<set-?>");
        this.localSettingsService = localSettingsService2;
    }

    @NotNull
    public final Lazy<TimestampAnalyticsHelper> getAnalyticsHelper() {
        Lazy<TimestampAnalyticsHelper> lazy = this.analyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("analyticsHelper");
        }
        return lazy;
    }

    public final void setAnalyticsHelper(@NotNull Lazy<TimestampAnalyticsHelper> lazy) {
        Intrinsics.checkParameterIsNotNull(lazy, "<set-?>");
        this.analyticsHelper = lazy;
    }

    public final boolean isFeatureEnabled() {
        if (this.isFeatureSubscribed) {
            LocalSettingsService localSettingsService2 = this.localSettingsService;
            if (localSettingsService2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localSettingsService");
            }
            if (localSettingsService2.shouldShowFoodTimestamps()) {
                return true;
            }
        }
        return false;
    }

    public final boolean isFeatureAvailable() {
        PremiumService premiumService2 = this.premiumService;
        if (premiumService2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("premiumService");
        }
        return premiumService2.isFeatureAvailable(PremiumFeature.FoodTimestamps);
    }

    @NotNull
    public final TimestampOption getSelectedOption() {
        return this.selectedOptionValue;
    }

    public final void setSelectedOption(@NotNull TimestampOption timestampOption) {
        Intrinsics.checkParameterIsNotNull(timestampOption, "value");
        this.selectedOptionValue = timestampOption;
        switch (timestampOption) {
            case SET_TIME:
                showTimestampPicker();
                return;
            case CURRENT_TIME:
                setNewTimestamp(constructCurrentTime());
                return;
            case LATEST_MEAL_ENTRY_TIME:
                setNewTimestamp(this.latestEntryTimestamp);
                return;
            case NO_TIME:
                setNewTimestamp(null);
                return;
            default:
                return;
        }
    }

    @Nullable
    public final Date getTimestamp() {
        return this.timestampValue;
    }

    private final void setTimestamp(Date date) {
        this.timestampValue = date;
        TimestampRowView timestampRowView = this.timestampRow;
        if (timestampRowView != null) {
            timestampRowView.setTime(date);
        }
        OnTimestampChangedListener onTimestampChangedListener = this.timestampChangeListener;
        if (onTimestampChangedListener != null) {
            onTimestampChangedListener.onTimestampChange(date, getSelectedOption());
        }
    }

    @JvmOverloads
    public /* synthetic */ TimestampPickerMixin(MfpActivity mfpActivity, Date date, TimestampRowView timestampRowView, FoodScreenType foodScreenType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            date = null;
        }
        if ((i & 4) != 0) {
            timestampRowView = null;
        }
        if ((i & 8) != 0) {
            foodScreenType = null;
        }
        this(mfpActivity, date, timestampRowView, foodScreenType);
    }

    @JvmOverloads
    public TimestampPickerMixin(@NotNull MfpActivity mfpActivity, @Nullable Date date, @Nullable TimestampRowView timestampRowView, @Nullable FoodScreenType foodScreenType) {
        Intrinsics.checkParameterIsNotNull(mfpActivity, AbstractEvent.ACTIVITY);
        this(mfpActivity, date, null, foodScreenType, timestampRowView);
    }

    public void onSaveInstanceState(@Nullable Bundle bundle) {
        if (bundle != null) {
            bundle.putBoolean(EXTRA_IS_SHOWING_OPTION_PICKER, this.isShowingOptionPicker);
            bundle.putBoolean(EXTRA_IS_SHOWING_TIME_PICKER, this.isShowingTimePicker);
            bundle.putSerializable(EXTRA_CURRENT_SELECTED_TIMESTAMP, getTimestamp());
            bundle.putSerializable(EXTRA_CURRENT_SELECTED_TIME_OPTION, this.selectedOptionValue);
        }
    }

    public void onPostCreate(@Nullable Bundle bundle) {
        boolean z = false;
        this.isShowingOptionPicker = bundle != null ? bundle.getBoolean(EXTRA_IS_SHOWING_OPTION_PICKER) : false;
        if (bundle != null) {
            z = bundle.getBoolean(EXTRA_IS_SHOWING_TIME_PICKER);
        }
        this.isShowingTimePicker = z;
        if (bundle != null && bundle.containsKey(EXTRA_CURRENT_SELECTED_TIMESTAMP)) {
            this.timestampValue = (Date) bundle.getSerializable(EXTRA_CURRENT_SELECTED_TIMESTAMP);
        }
        if (bundle != null && bundle.containsKey(EXTRA_CURRENT_SELECTED_TIME_OPTION)) {
            Serializable serializable = bundle.getSerializable(EXTRA_CURRENT_SELECTED_TIME_OPTION);
            if (serializable != null) {
                this.selectedOptionValue = (TimestampOption) serializable;
            } else {
                throw new TypeCastException("null cannot be cast to non-null type com.myfitnesspal.feature.timestamp.model.TimestampOption");
            }
        }
        validateDialogs();
        TimestampRowView timestampRowView = this.timestampRow;
        if (timestampRowView != null) {
            timestampRowView.setTime(this.timestampValue);
        }
    }

    public void onConfigurationChanged(@NotNull Configuration configuration) {
        Intrinsics.checkParameterIsNotNull(configuration, "newConfig");
        validateDialogs();
    }

    public final void showTimestampOptions() {
        com.myfitnesspal.feature.diary.ui.dialog.TimestampOptionsDialog.Companion companion = TimestampOptionsDialog.Companion;
        String localeFormattedTime = DateTimeUtils.localeFormattedTime((Context) this.activity, this.latestEntryTimestamp);
        Intrinsics.checkExpressionValueIsNotNull(localeFormattedTime, "DateTimeUtils.localeForm…ty, latestEntryTimestamp)");
        TimestampOptionsDialog newInstance = companion.newInstance(localeFormattedTime);
        newInstance.setOnOptionSelect(new TimestampPickerMixin$showTimestampOptions$$inlined$apply$lambda$1(this));
        newInstance.setOnDismiss(new TimestampPickerMixin$showTimestampOptions$$inlined$apply$lambda$2(this));
        this.optionPicketDialog = newInstance;
        TimestampOptionsDialog timestampOptionsDialog = this.optionPicketDialog;
        if (timestampOptionsDialog != null) {
            timestampOptionsDialog.show(this.activity.getSupportFragmentManager(), TimestampOptionsDialog.FRAGMENT_TAG);
        }
        this.isShowingOptionPicker = true;
    }

    public final void showCurrentTimestamp(@Nullable Date date) {
        setTimestamp(date);
        this.isEditingEntry = true;
    }

    private final void showTimestampPicker() {
        Calendar instance = Calendar.getInstance();
        if (getTimestamp() != null) {
            Intrinsics.checkExpressionValueIsNotNull(instance, "entryTime");
            instance.setTime(getTimestamp());
        }
        TimePickerDialog timePickerDialog2 = new TimePickerDialog(this.activity, new TimestampPickerMixin$showTimestampPicker$1(this, instance), instance.get(11), instance.get(12), DateFormat.is24HourFormat(this.activity));
        timePickerDialog2.setOnCancelListener(new TimestampPickerMixin$showTimestampPicker$$inlined$apply$lambda$1(this));
        timePickerDialog2.setOnDismissListener(new TimestampPickerMixin$showTimestampPicker$$inlined$apply$lambda$2(this));
        timePickerDialog2.setOnShowListener(new TimestampPickerMixin$showTimestampPicker$$inlined$apply$lambda$3(this));
        timePickerDialog2.show();
        this.timePickerDialog = timePickerDialog2;
    }

    private final void validateDialogs() {
        if (this.isShowingTimePicker) {
            TimePickerDialog timePickerDialog2 = this.timePickerDialog;
            if (timePickerDialog2 != null) {
                timePickerDialog2.dismiss();
            }
            showTimestampPicker();
        } else if (this.isShowingOptionPicker) {
            this.optionPicketDialog = (TimestampOptionsDialog) this.activity.getSupportFragmentManager().findFragmentByTag(TimestampOptionsDialog.FRAGMENT_TAG);
            TimestampOptionsDialog timestampOptionsDialog = this.optionPicketDialog;
            if (timestampOptionsDialog != null) {
                timestampOptionsDialog.setOnOptionSelect(new TimestampPickerMixin$validateDialogs$1(this));
            }
            TimestampOptionsDialog timestampOptionsDialog2 = this.optionPicketDialog;
            if (timestampOptionsDialog2 != null) {
                timestampOptionsDialog2.setOnDismiss(new TimestampPickerMixin$validateDialogs$2(this));
            }
        }
    }

    private final Date constructCurrentTime() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(11);
        int i2 = instance.get(12);
        instance.clear();
        instance.set(11, i);
        instance.set(12, i2);
        Intrinsics.checkExpressionValueIsNotNull(instance, "calendar");
        Date time = instance.getTime();
        Intrinsics.checkExpressionValueIsNotNull(time, "calendar.time");
        return time;
    }

    private final void highlightFeature() {
        String str;
        String str2;
        Lazy<TimestampAnalyticsHelper> lazy = this.analyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("analyticsHelper");
        }
        ((TimestampAnalyticsHelper) lazy.get()).reportFeatureHighlightEvent(FeatureHighlightEvent.DISPLAYED);
        setTimestamp(constructCurrentTime());
        TimestampRowView timestampRowView = this.timestampRow;
        if (timestampRowView != null) {
            timestampRowView.setVisibility(0);
            timestampRowView.showTime();
            com.myfitnesspal.shared.ui.tooltip.FeatureHighlightView.Companion companion = FeatureHighlightView.Companion;
            MfpActivity mfpActivity = this.activity;
            Activity activity2 = mfpActivity;
            View view = this.timestampRow;
            if (this.isFeatureSubscribed) {
                str = mfpActivity.getString(R.string.feature_food_timestamp_description_premium);
            } else {
                str = mfpActivity.getString(R.string.feature_food_timestamp_description_non_premium);
            }
            Intrinsics.checkExpressionValueIsNotNull(str, "if (isFeatureSubscribed)…                        }");
            if (this.isFeatureSubscribed) {
                String string = this.activity.getString(R.string.track_time);
                Intrinsics.checkExpressionValueIsNotNull(string, "activity.getString(R.string.track_time)");
                if (string != null) {
                    String upperCase = string.toUpperCase();
                    Intrinsics.checkExpressionValueIsNotNull(upperCase, "(this as java.lang.String).toUpperCase()");
                    str2 = upperCase;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            } else {
                String string2 = this.activity.getString(R.string.go_premium_exclamation);
                Intrinsics.checkExpressionValueIsNotNull(string2, "activity.getString(R.str…g.go_premium_exclamation)");
                if (string2 != null) {
                    String upperCase2 = string2.toUpperCase();
                    Intrinsics.checkExpressionValueIsNotNull(upperCase2, "(this as java.lang.String).toUpperCase()");
                    str2 = upperCase2;
                } else {
                    throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                }
            }
            String string3 = this.activity.getString(R.string.no_thanks);
            Intrinsics.checkExpressionValueIsNotNull(string3, "activity.getString(R.string.no_thanks)");
            if (string3 != null) {
                String upperCase3 = string3.toUpperCase();
                String str3 = upperCase3;
                Intrinsics.checkExpressionValueIsNotNull(upperCase3, "(this as java.lang.String).toUpperCase()");
                FeatureHighlight featureHighlight = new FeatureHighlight(view, str, null, 0, 0, 0, 0, str2, 0, str3, 0, 0, Gravity.RIGHT, Gravity.RIGHT, false, null, 52604, null);
                companion.showFor(activity2, featureHighlight, new TimestampPickerMixin$highlightFeature$$inlined$let$lambda$1(timestampRowView, this));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    /* access modifiers changed from: private */
    public final void onTimestampFeatureDismissed(FeatureHighlightEvent featureHighlightEvent) {
        Lazy<TimestampAnalyticsHelper> lazy = this.analyticsHelper;
        if (lazy == null) {
            Intrinsics.throwUninitializedPropertyAccessException("analyticsHelper");
        }
        ((TimestampAnalyticsHelper) lazy.get()).reportFeatureHighlightEvent(featureHighlightEvent);
        setTimestamp(null);
        if (this.isFeatureSubscribed) {
            LocalSettingsService localSettingsService2 = this.localSettingsService;
            if (localSettingsService2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("localSettingsService");
            }
            localSettingsService2.setShouldShowFoodTimestamps(false);
            TimestampRowView timestampRowView = this.timestampRow;
            if (timestampRowView != null) {
                timestampRowView.setVisibility(8);
                return;
            }
            return;
        }
        TimestampRowView timestampRowView2 = this.timestampRow;
        if (timestampRowView2 != null) {
            timestampRowView2.showLock();
        }
    }

    /* access modifiers changed from: private */
    public final void showPremiumUpsell(String str) {
        this.navigationHelper.withIntent(PremiumUpsellActivity.newStartIntent((Context) this.activity, str)).startActivity();
    }

    /* access modifiers changed from: private */
    public final void setNewTimestamp(Date date) {
        TimeValue timeValue;
        FoodScreenType foodScreenType = this.screenType;
        if (foodScreenType != null) {
            Lazy<TimestampAnalyticsHelper> lazy = this.analyticsHelper;
            if (lazy == null) {
                Intrinsics.throwUninitializedPropertyAccessException("analyticsHelper");
            }
            TimestampAnalyticsHelper timestampAnalyticsHelper = (TimestampAnalyticsHelper) lazy.get();
            if (this.isEditingEntry) {
                timeValue = TimeValue.LOGGED_TIME;
            } else {
                timeValue = TimeValue.Companion.fromTimestampOption(getSelectedOption());
            }
            timestampAnalyticsHelper.reportTimeFieldSaved(foodScreenType, timeValue, !equalTo(getTimestamp(), date));
        }
        setTimestamp(date);
    }

    public final boolean equalTo(@Nullable Date date, @Nullable Date date2) {
        if (date == null && date2 == null) {
            return true;
        }
        return date != null ? date.equals(date2) : false;
    }
}
