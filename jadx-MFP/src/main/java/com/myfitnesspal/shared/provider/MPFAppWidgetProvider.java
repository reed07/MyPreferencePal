package com.myfitnesspal.shared.provider;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.RemoteViews;
import com.google.android.exoplayer2.C;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.login.Welcome;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.barcode.ui.activity.BarcodeScannerActivity;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase;
import com.uacf.core.util.ApplicationUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.Function0;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Date;
import javax.inject.Inject;

public class MPFAppWidgetProvider extends AppWidgetProvider {
    private static final String ACTION_ADD_ENTRY = "com.myfitnesspal.action.ACTION_ADD_ENTRY";
    private static final String ACTION_REQUERY_DATA = "com.myfitnesspal.action.ACTION_REQUERY_DATA";
    public static final String ACTION_SIGNED_OUT = "com.myfitnesspal.action.ACTION_SIGNED_OUT";
    private static final String ACTION_START_BARCODE_SCANNER = "com.myfitnesspal.action.ACTION_START_BARCODE_SCANNER";
    private static final String ACTION_START_DIARY = "com.myfitnesspal.action.ACTION_START_DIARY";
    private static Debouncer<Context> UPDATE_WIDGET_DEBOUNCER = new Debouncer<Context>(2000) {
        /* access modifiers changed from: protected */
        public void onDebounced(Context context) {
            context.sendBroadcast(new Intent(context, MPFAppWidgetProvider.class).setAction(MPFAppWidgetProvider.ACTION_REQUERY_DATA));
        }
    };
    /* access modifiers changed from: private */
    public static final WidgetViewModel VIEW_MODEL = new WidgetViewModel();
    private static final String WIDGET_APP_OPENED_ACTION = "action";
    public static final String WIDGET_REFERRER_ID = "widget";
    private static final int WIDGET_UPDATE_DEBOUNCE_MILLIS = 2000;
    @Inject
    Lazy<AnalyticsService> analyticsService;
    private final AsyncService async = new AsyncService();
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<SyncService> syncService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    private class AsyncService extends SimpleAsyncServiceBase {
        /* access modifiers changed from: private */
        public final Function0 onCompleted;

        /* access modifiers changed from: protected */
        public int getMaxThreads() {
            return 1;
        }

        /* access modifiers changed from: protected */
        public String getThreadName() {
            return "AppWidgetProvider.AsyncService";
        }

        private AsyncService() {
            this.onCompleted = new Function0() {
                public void execute() throws RuntimeException {
                    MPFAppWidgetProvider.this.redrawWidget();
                }
            };
        }

        /* access modifiers changed from: 0000 */
        public void requeryDataModelAndRedrawWidget() {
            if (!MPFAppWidgetProvider.this.isUserLoggedIn()) {
                invokeOnMainThread(this.onCompleted);
            } else {
                async(new Runnable() {
                    public void run() {
                        Date date = new Date();
                        MPFAppWidgetProvider.VIEW_MODEL.update((UserEnergyService) MPFAppWidgetProvider.this.userEnergyService.get(), ((DiaryService) MPFAppWidgetProvider.this.diaryService.get()).getDiaryDayForDateSync(date), ((NutrientGoalService) MPFAppWidgetProvider.this.nutrientGoalService.get()).getDailyGoalForDayOfWeekSync(date));
                        AsyncService asyncService = AsyncService.this;
                        asyncService.invokeOnMainThread(asyncService.onCompleted);
                    }
                });
            }
        }
    }

    private static class WidgetViewModel {
        private String remainingEnergy;

        private WidgetViewModel() {
        }

        /* access modifiers changed from: 0000 */
        public synchronized void update(UserEnergyService userEnergyService, DiaryDay diaryDay, MfpDailyGoal mfpDailyGoal) {
            float currentEnergy = userEnergyService.getCurrentEnergy((double) mfpDailyGoal.getValueForNutritionalValuesIndex(0));
            float caloriesConsumed = diaryDay.caloriesConsumed(true);
            float caloriesBurnedByExercise = caloriesConsumed - diaryDay.caloriesBurnedByExercise(true);
            if (!mfpDailyGoal.isAssignExerciseEnergyOn()) {
                caloriesBurnedByExercise = caloriesConsumed;
            }
            this.remainingEnergy = NumberUtils.localeStringFromDoubleNoDecimal((double) (currentEnergy - caloriesBurnedByExercise));
        }

        /* access modifiers changed from: 0000 */
        public synchronized void reset() {
            this.remainingEnergy = null;
        }

        public boolean isEmpty() {
            return Strings.isEmpty(this.remainingEnergy);
        }

        /* access modifiers changed from: 0000 */
        public String getRemainingEnergy() {
            return valueOrPlaceholder(this.remainingEnergy);
        }

        private static String valueOrPlaceholder(String str) {
            return Strings.isEmpty(str) ? "-" : str;
        }
    }

    public static void update(Context context) {
        UPDATE_WIDGET_DEBOUNCER.call(context.getApplicationContext());
    }

    public MPFAppWidgetProvider() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void onEnabled(Context context) {
        super.onEnabled(context);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.WIDGET_INSTALLED);
    }

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] iArr) {
        super.onUpdate(context, appWidgetManager, iArr);
        redrawWidgetRequeryIfEmpty();
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if (intent != null) {
            String action = intent.getAction();
            if (!Strings.isEmpty(action)) {
                char c = 65535;
                switch (action.hashCode()) {
                    case -1524475711:
                        if (action.equals(ACTION_ADD_ENTRY)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1395946193:
                        if (action.equals(ACTION_START_BARCODE_SCANNER)) {
                            c = 0;
                            break;
                        }
                        break;
                    case -981827714:
                        if (action.equals(ACTION_SIGNED_OUT)) {
                            c = 4;
                            break;
                        }
                        break;
                    case -721930285:
                        if (action.equals(ACTION_START_DIARY)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1770342023:
                        if (action.equals(ACTION_REQUERY_DATA)) {
                            c = 3;
                            break;
                        }
                        break;
                }
                switch (c) {
                    case 0:
                        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.WIDGET_APP_OPENED, MapUtil.createMap("action", "barcode"));
                        Intent newStartIntent = Diary.newStartIntent(context);
                        newStartIntent.addFlags(603979776);
                        navigateTo(context, BarcodeScannerActivity.newStartIntent(context, new Date()).putExtra("referrer", WIDGET_REFERRER_ID).putExtra(Extras.ADD_TO_MEAL_ON_SUCCESS, true).putExtra(Extras.SEARCH_ON_NO_MATCH, true).putExtra(Extras.STARTED_FROM_WIDGET_OR_NOTIFICATION, true).putExtra(BarcodeScannerActivity.EXTRA_NAVIGATION_ACTIVITY_TO_START, newStartIntent).addFlags(C.ENCODING_PCM_MU_LAW));
                        break;
                    case 1:
                        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.WIDGET_APP_OPENED, MapUtil.createMap("action", "diary"));
                        navigateTo(context, Diary.newStartIntent(context).addFlags(C.ENCODING_PCM_MU_LAW).putExtra("referrer", WIDGET_REFERRER_ID));
                        break;
                    case 2:
                        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.WIDGET_APP_OPENED, MapUtil.createMap("action", "add"));
                        navigateToHomeAndShowFab(context);
                        break;
                    case 3:
                        this.async.requeryDataModelAndRedrawWidget();
                        break;
                    case 4:
                        VIEW_MODEL.reset();
                        redrawWidget();
                        break;
                }
            }
        }
    }

    public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager, int i, Bundle bundle) {
        super.onAppWidgetOptionsChanged(context, appWidgetManager, i, bundle);
        redrawWidgetRequeryIfEmpty();
    }

    public void onDisabled(Context context) {
        super.onDisabled(context);
        ((AnalyticsService) this.analyticsService.get()).reportEvent(Events.WIDGET_UNINSTALLED);
    }

    private void redrawWidgetRequeryIfEmpty() {
        if (VIEW_MODEL.isEmpty()) {
            this.async.requeryDataModelAndRedrawWidget();
        } else {
            redrawWidget();
        }
    }

    private boolean shouldShowSpacer(int i) {
        if (((int) TypedValue.applyDimension(1, (float) BundleUtils.getInt(AppWidgetManager.getInstance(getContext()).getAppWidgetOptions(i), "appWidgetMinWidth", 0), getContext().getResources().getDisplayMetrics())) > ((int) getContext().getResources().getDimension(R.dimen.widget_min_spacer_width))) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void redrawWidget() {
        int[] appWidgetIds;
        MyFitnessPalApp instance = MyFitnessPalApp.getInstance();
        AppWidgetManager instance2 = AppWidgetManager.getInstance(instance);
        RemoteViews remoteViews = new RemoteViews(instance.getPackageName(), R.layout.mfp_appwidget);
        for (int i : instance2.getAppWidgetIds(new ComponentName(instance, getClass()))) {
            bindWidgetValues(remoteViews, i);
            bindWidgetEventHandlers(remoteViews);
            instance2.updateAppWidget(i, remoteViews);
        }
    }

    private void bindWidgetEventHandlers(RemoteViews remoteViews) {
        Context context = getContext();
        remoteViews.setOnClickPendingIntent(R.id.main_container, PendingIntent.getBroadcast(context, 0, new Intent(context, MPFAppWidgetProvider.class).setAction(ACTION_START_DIARY), 134217728));
        remoteViews.setOnClickPendingIntent(R.id.add_btn, PendingIntent.getBroadcast(context, 0, new Intent(context, MPFAppWidgetProvider.class).setAction(ACTION_ADD_ENTRY), 134217728));
        remoteViews.setOnClickPendingIntent(R.id.scan_btn, PendingIntent.getBroadcast(context, 0, new Intent(context, MPFAppWidgetProvider.class).setAction(ACTION_START_BARCODE_SCANNER), 134217728));
        remoteViews.setOnClickPendingIntent(R.id.widget_sign_in, PendingIntent.getActivity(context, 0, new Intent(context, Welcome.class), 134217728));
    }

    private void bindWidgetValues(RemoteViews remoteViews, int i) {
        int i2 = 0;
        if (isUserLoggedIn()) {
            Context context = getContext();
            String remainingEnergy = VIEW_MODEL.getRemainingEnergy();
            int color = context.getResources().getColor(NumberUtils.localeFloatFromString(remainingEnergy) >= BitmapDescriptorFactory.HUE_RED ? R.color.balance_color_positive : R.color.balance_color_negative);
            if (!ApplicationUtils.hasCameraFeature(context)) {
                remoteViews.setViewVisibility(R.id.scan_btn, 8);
            }
            remoteViews.setViewVisibility(R.id.widget_sign_in, 8);
            remoteViews.setViewVisibility(R.id.main_container, 0);
            remoteViews.setTextViewText(R.id.remaining, remainingEnergy);
            remoteViews.setTextViewText(R.id.remaining_label, ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString("summary_remaining", this.userEnergyService.get()));
            remoteViews.setTextColor(R.id.remaining, color);
        } else {
            remoteViews.setViewVisibility(R.id.main_container, 8);
            remoteViews.setViewVisibility(R.id.widget_sign_in, 0);
        }
        if (!shouldShowSpacer(i)) {
            i2 = 8;
        }
        remoteViews.setViewVisibility(R.id.spacer, i2);
    }

    private Context getContext() {
        return MyFitnessPalApp.getInstance();
    }

    /* access modifiers changed from: private */
    public boolean isUserLoggedIn() {
        return ((Session) this.session.get()).getUser().isLoggedIn();
    }

    private static void navigateTo(Context context, Intent intent) {
        Intent newStartIntent = HomeActivity.newStartIntent(context);
        newStartIntent.addFlags(872415232);
        context.startActivity(newStartIntent);
        context.startActivity(intent);
    }

    private static void navigateToHomeAndShowFab(Context context) {
        context.startActivity(HomeActivity.newStartIntentWithFabShowing(context).addFlags(C.ENCODING_PCM_MU_LAW).putExtra("referrer", WIDGET_REFERRER_ID));
    }
}
