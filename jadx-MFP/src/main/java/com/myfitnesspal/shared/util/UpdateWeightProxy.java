package com.myfitnesspal.shared.util;

import android.content.Context;
import android.os.AsyncTask;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.images.util.ImageUploadUtil;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.progress.ui.activity.RecommendGoal;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.StartSyncEvent;
import com.myfitnesspal.shared.service.userdata.UserHeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.squareup.otto.Bus;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import dagger.Lazy;
import java.io.IOException;
import java.util.Calendar;
import javax.inject.Inject;

public class UpdateWeightProxy {
    public static final Double MAX_NORMAL_BMI = Double.valueOf(25.0d);
    public static final Double MAX_UNDERWEIGHT_BMI = Double.valueOf(18.5d);
    /* access modifiers changed from: private */
    public final Bus bus;
    @Inject
    Lazy<ConfigService> configService;
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public boolean didPromptForWarnings;
    @Inject
    Lazy<MeasurementsService> measurementsService;
    /* access modifiers changed from: private */
    public final NavigationHelper navigationHelper;
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<UserHeightService> userHeightService;
    @Inject
    Lazy<UserWeightService> userWeightService;

    public enum FinishMode {
        Back,
        Home
    }

    public interface Listener {
        void onCancel();

        void onNavigatedForward();

        void onSuccess();
    }

    public enum UpdateMode {
        Weight,
        WeightAndGoalLoss
    }

    private boolean showRecommendGoals(float f, double d, float f2, double d2) {
        int i = (d > 18.5d ? 1 : (d == 18.5d ? 0 : -1));
        return (i < 0 && f2 < f) || (i < 0 && f2 == f) || (d2 < 18.5d && f2 < f);
    }

    public UpdateWeightProxy(Context context2, NavigationHelper navigationHelper2, Bus bus2) {
        this.context = context2;
        this.navigationHelper = navigationHelper2;
        this.bus = bus2;
        this.didPromptForWarnings = false;
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public UpdateWeightProxy() {
        this(null, null, null);
    }

    public void updateWeightAndPromptForWarnings(float f, FinishMode finishMode, UpdateMode updateMode) {
        updateWeightAndPromptForWarnings(f, finishMode, updateMode, null);
    }

    public void updateWeightAndPromptForWarnings(float f, FinishMode finishMode, UpdateMode updateMode, Listener listener) {
        updateWeightAndPromptForWarnings(f, null, null, finishMode, updateMode, listener);
    }

    public void updateWeightAndPromptForWarnings(float f, Calendar calendar, String str, FinishMode finishMode, UpdateMode updateMode, Listener listener) {
        final Listener listener2 = listener;
        double calculateBmi = calculateBmi((double) f, ((UserHeightService) this.userHeightService.get()).getCurrentHeightInInches());
        float goalWeightInPounds = ((UserWeightService) this.userWeightService.get()).getGoalWeightInPounds();
        if (showRecommendGoals(f, calculateBmi, goalWeightInPounds, calculateBmi((double) goalWeightInPounds, ((UserHeightService) this.userHeightService.get()).getCurrentHeightInInches()))) {
            final FinishMode finishMode2 = finishMode;
            updateWeightAndBmi(f, calendar, str, calculateBmi, updateMode, new Function0() {
                public void execute() throws RuntimeException {
                    UpdateWeightProxy.this.navigationHelper.withExtra(Extras.GO_HOME_INSTEAD_OF_BACK, finishMode2 == FinishMode.Home).withIntent(RecommendGoal.newStartIntent(UpdateWeightProxy.this.context)).startActivity();
                    UpdateWeightProxy.this.didPromptForWarnings = true;
                    Listener listener = listener2;
                    if (listener != null) {
                        listener.onNavigatedForward();
                    }
                }
            });
            return;
        }
        updateWeightAndBmi(f, calendar, str, calculateBmi, updateMode, new Function0() {
            public void execute() throws RuntimeException {
                if (((UserWeightService) UpdateWeightProxy.this.userWeightService.get()).shouldRecalculateGoals()) {
                    Listener listener = listener2;
                    if (listener != null) {
                        listener.onNavigatedForward();
                        return;
                    }
                    return;
                }
                Listener listener2 = listener2;
                if (listener2 != null) {
                    listener2.onSuccess();
                }
            }
        });
        this.didPromptForWarnings = false;
    }

    public boolean getDidPromptForWarnings() {
        return this.didPromptForWarnings;
    }

    public Tuple2<LocalizedWeight, LocalizedWeight> validateAppropriateGoalWeightEntryOrSuggestRange(double d, double d2) {
        if (calculateBmi(d, d2) <= MAX_UNDERWEIGHT_BMI.doubleValue()) {
            double d3 = d2 * d2;
            return Tuple.create(LocalizedWeight.fromPounds(Double.valueOf((MAX_UNDERWEIGHT_BMI.doubleValue() / 703.0d) * d3).doubleValue()), LocalizedWeight.fromPounds(Double.valueOf((MAX_NORMAL_BMI.doubleValue() / 703.0d) * d3).doubleValue()));
        }
        LocalizedWeight fromPounds = LocalizedWeight.fromPounds(d);
        return Tuple.create(fromPounds, fromPounds);
    }

    private void updateWeightAndBmi(float f, Calendar calendar, String str, double d, UpdateMode updateMode, Function0 function0) {
        final Calendar instance = calendar != null ? calendar : Calendar.getInstance();
        final String str2 = str;
        final float f2 = f;
        final UpdateMode updateMode2 = updateMode;
        final double d2 = d;
        final Function0 function02 = function0;
        AnonymousClass3 r1 = new AsyncTask<Void, Void, Void>() {
            /* access modifiers changed from: protected */
            public Void doInBackground(Void... voidArr) {
                String str = str2;
                if (Strings.notEmpty(str)) {
                    try {
                        str = ImageUploadUtil.copyFileToUploadDirectory(UpdateWeightProxy.this.context, str2);
                    } catch (IOException unused) {
                        Ln.e("unable to copy image for upload!", new Object[0]);
                    }
                }
                ((MeasurementsService) UpdateWeightProxy.this.measurementsService.get()).insertOrUpdateMeasurement(Measurement.WEIGHT, instance, f2, str);
                if (DateTimeUtils.isDateToday(instance)) {
                    ((UserWeightService) UpdateWeightProxy.this.userWeightService.get()).setWeight(f2, WeightType.CURRENT);
                    if (updateMode2 == UpdateMode.WeightAndGoalLoss) {
                        ((UserWeightService) UpdateWeightProxy.this.userWeightService.get()).recalculateAndUpdateGoalLossPerWeek();
                    }
                    ((Session) UpdateWeightProxy.this.session.get()).getUser().updateCurrentWeightFromMeasurements(UpdateWeightProxy.this.context);
                    ((Session) UpdateWeightProxy.this.session.get()).getUser().getProfile().setCurrentBMI(d2);
                }
                return null;
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(Void voidR) {
                UpdateWeightProxy.this.bus.post(new StartSyncEvent());
                function02.execute();
            }
        };
        r1.execute(new Void[0]);
    }

    public static double calculateBmi(double d, double d2) {
        return (d * 703.0d) / Math.pow(d2, 2.0d);
    }

    public static double calculateWeight(double d, double d2) {
        return (Math.pow(d2, 2.0d) * d) / 703.0d;
    }
}
