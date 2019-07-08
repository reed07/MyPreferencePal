package com.myfitnesspal.feature.addentry.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.ui.activity.PremiumUpsellActivity;
import com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin;
import com.myfitnesspal.feature.timestamp.view.TimestampRowView;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.QuickAddFood;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.CalorieAddErrorDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment.OnMealSelectedListener;
import com.myfitnesspal.shared.util.DecimalDigitsInputFilter;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import com.uacf.sync.engine.UacfScheduler;
import dagger.Lazy;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;

public class QuickAddActivity extends MfpActivity {
    private static final int DONE = 100;
    public static final String EXTRA_QUICK_ADD_OBJECT = "quick_add_object";
    private String EMPTY = "";
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @BindView(2131362083)
    View carbLock;
    private float carbs;
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    /* access modifiers changed from: private */
    public float defaultNutrientValue;
    @Inject
    Lazy<DiaryService> diaryService;
    /* access modifiers changed from: private */
    public float energy;
    private float fat;
    @BindView(2131362561)
    View fatLock;
    private QuickAddFood foodToEdit;
    private TextWatcher inputTextWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            QuickAddActivity.this.updateCaloriesBasedOnMacrosIfNecessary();
        }
    };
    private boolean isQuickAddMacrosSubscribed;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private OnClickListener lockListener;
    @Inject
    Lazy<PremiumService> premiumService;
    private float protein;
    @BindView(2131363349)
    View proteinLock;
    @BindView(2131363499)
    View rlQuickCalories;
    @BindView(2131363357)
    View rlQuickCarbs;
    @BindView(2131363358)
    View rlQuickFat;
    @BindView(2131363359)
    View rlQuickProtein;
    private int roundedEnergy;
    @Inject
    Lazy<UacfScheduler<SyncType>> syncScheduler;
    @BindView(2131363360)
    TimestampRowView timeLayout;
    private TimestampPickerMixin timestampPickerMixin;
    @BindView(2131363894)
    TextView tvCaloriesLabel;
    @BindView(2131363933)
    TextView tvQuickCalories;
    @BindView(2131363934)
    TextView tvQuickCarbs;
    @BindView(2131363935)
    TextView tvQuickFat;
    @BindView(2131363936)
    TextView tvQuickProtein;
    @BindView(2131363937)
    TextView tvRecalculateLabel;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public static class Extras implements Parcelable {
        public static final Creator<Extras> CREATOR = new Creator<Extras>() {
            public Extras createFromParcel(Parcel parcel) {
                return new Extras(parcel);
            }

            public Extras[] newArray(int i) {
                return new Extras[i];
            }
        };
        private String mealName;
        private QuickAddFood quickAddFood;

        public int describeContents() {
            return 0;
        }

        public Extras() {
        }

        public Extras(Parcel parcel) {
            this.quickAddFood = (QuickAddFood) parcel.readParcelable(QuickAddFood.class.getClassLoader());
            this.mealName = parcel.readString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.quickAddFood, i);
            parcel.writeString(this.mealName);
        }

        public QuickAddFood getQuickAddFood() {
            return this.quickAddFood;
        }

        public Extras setQuickAddFood(QuickAddFood quickAddFood2) {
            this.quickAddFood = quickAddFood2;
            return this;
        }

        public String getMealName() {
            return this.mealName;
        }

        public Extras setMealName(String str) {
            this.mealName = str;
            return this;
        }
    }

    private class MealNameDialogListener implements OnMealSelectedListener {
        private final QuickAddFood quickAddFood;

        private MealNameDialogListener(QuickAddFood quickAddFood2) {
            this.quickAddFood = quickAddFood2;
        }

        public void onMealSelected(String str) {
            FoodEntry foodEntry;
            if (((PremiumService) QuickAddActivity.this.premiumService.get()).isFeatureSubscribed(PremiumFeature.QuickAddMacros)) {
                foodEntry = FoodEntry.quickAddedPremiumFoodEntry(QuickAddActivity.this.getSession().getUser(), this.quickAddFood, str, (DbConnectionManager) QuickAddActivity.this.dbConnectionManager.get());
            } else {
                foodEntry = FoodEntry.quickAddedCaloriesFoodEntry(((UserEnergyService) QuickAddActivity.this.userEnergyService.get()).getCalories(QuickAddActivity.this.energy), str, QuickAddActivity.this.getSession(), (DbConnectionManager) QuickAddActivity.this.dbConnectionManager.get());
            }
            ((DiaryService) QuickAddActivity.this.diaryService.get()).getDiaryDayForActiveDateSync().addFoodEntry(foodEntry);
            ((UacfScheduler) QuickAddActivity.this.syncScheduler.get()).schedulePeriodicSyncIfNecessary();
            QuickAddActivity.this.getNavigationHelper().withIntent(Diary.newStartIntent(QuickAddActivity.this)).startActivity();
        }
    }

    public static Intent newStartIntent(Context context, Extras extras) {
        return new Intent(context, QuickAddActivity.class).putExtra("extras", extras);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x008a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r10) {
        /*
            r9 = this;
            super.onCreate(r10)
            r10 = 2131558968(0x7f0d0238, float:1.8743267E38)
            r9.setContentView(r10)
            com.myfitnesspal.shared.injection.component.ApplicationComponent r10 = r9.component()
            r10.inject(r9)
            dagger.Lazy<com.myfitnesspal.feature.premium.service.PremiumService> r10 = r9.premiumService
            java.lang.Object r10 = r10.get()
            com.myfitnesspal.feature.premium.service.PremiumService r10 = (com.myfitnesspal.feature.premium.service.PremiumService) r10
            com.myfitnesspal.feature.premium.service.PremiumFeature r0 = com.myfitnesspal.feature.premium.service.PremiumFeature.QuickAddMacros
            boolean r10 = r10.isFeatureSubscribed(r0)
            r9.isQuickAddMacrosSubscribed = r10
            java.lang.Double r10 = com.myfitnesspal.shared.model.v2.MfpNutritionalContents.DEFAULT_VALUE
            float r10 = com.uacf.core.util.NumberUtils.getFloatValue(r10)
            r9.defaultNutrientValue = r10
            android.content.Intent r10 = r9.getIntent()
            r0 = 0
            if (r10 == 0) goto L_0x0052
            android.content.Intent r10 = r9.getIntent()
            android.os.Bundle r10 = r10.getExtras()
            java.lang.String r1 = "extras"
            java.lang.Class<com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity$Extras> r2 = com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity.Extras.class
            java.lang.ClassLoader r2 = r2.getClassLoader()
            android.os.Parcelable r10 = com.uacf.core.util.BundleUtils.getParcelable(r10, r1, r2)
            com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity$Extras r10 = (com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity.Extras) r10
            if (r10 == 0) goto L_0x0052
            com.myfitnesspal.shared.model.QuickAddFood r1 = r10.getQuickAddFood()
            r9.foodToEdit = r1
            java.lang.String r10 = r10.getMealName()
            goto L_0x0053
        L_0x0052:
            r10 = r0
        L_0x0053:
            com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin r1 = new com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin
            dagger.Lazy<com.myfitnesspal.feature.diary.service.DiaryService> r2 = r9.diaryService
            java.lang.Object r2 = r2.get()
            com.myfitnesspal.feature.diary.service.DiaryService r2 = (com.myfitnesspal.feature.diary.service.DiaryService) r2
            com.myfitnesspal.shared.model.v1.DiaryDay r2 = r2.getDiaryDayForActiveDateSync()
            java.util.Date r10 = r2.getLatestEntryTimeForMealName(r10)
            com.myfitnesspal.feature.timestamp.view.TimestampRowView r2 = r9.timeLayout
            com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper$FoodScreenType r3 = com.myfitnesspal.feature.timestamp.service.TimestampAnalyticsHelper.FoodScreenType.QUICK_ADD
            r1.<init>(r9, r10, r2, r3)
            r9.timestampPickerMixin = r1
            r10 = 1
            com.myfitnesspal.framework.mixin.RunnerLifecycleMixin[] r10 = new com.myfitnesspal.framework.mixin.RunnerLifecycleMixin[r10]
            r1 = 0
            com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin r2 = r9.timestampPickerMixin
            r10[r1] = r2
            r9.registerMixin(r10)
            r9.setupViewBasedOnFeature()
            android.support.v4.app.FragmentManager r10 = r9.getSupportFragmentManager()
            java.lang.String r1 = "mealNamesDialog"
            android.support.v4.app.Fragment r10 = r10.findFragmentByTag(r1)
            com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment r10 = (com.myfitnesspal.shared.ui.dialog.impl.MealNamesDialogFragment) r10
            if (r10 == 0) goto L_0x00b2
            com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity$MealNameDialogListener r1 = new com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity$MealNameDialogListener
            com.myfitnesspal.shared.model.QuickAddFood r8 = new com.myfitnesspal.shared.model.QuickAddFood
            dagger.Lazy<com.myfitnesspal.shared.service.userdata.UserEnergyService> r2 = r9.userEnergyService
            java.lang.Object r2 = r2.get()
            com.myfitnesspal.shared.service.userdata.UserEnergyService r2 = (com.myfitnesspal.shared.service.userdata.UserEnergyService) r2
            float r3 = r9.energy
            float r3 = r2.getCalories(r3)
            float r4 = r9.carbs
            float r5 = r9.fat
            float r6 = r9.protein
            com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin r2 = r9.timestampPickerMixin
            java.util.Date r7 = r2.getTimestamp()
            r2 = r8
            r2.<init>(r3, r4, r5, r6, r7)
            r1.<init>(r8)
            r10.setListener(r1)
        L_0x00b2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity.onCreate(android.os.Bundle):void");
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        menu.removeGroup(0);
        boolean z = this.energy > BitmapDescriptorFactory.HUE_RED;
        menu.add(0, 100, 0, R.string.done).setIcon(z ? R.drawable.ic_check_white_24dp : R.drawable.ic_check_disabled_white_24dp).setEnabled(z).setShowAsAction(2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 100) {
            onDone();
        }
        getImmHelper().hideSoftInput();
        return super.onOptionsItemSelected(menuItem);
    }

    public void onUpPressed() {
        getImmHelper().hideSoftInput();
        super.onUpPressed();
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x00ca  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00e0  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupViewBasedOnFeature() {
        /*
            r6 = this;
            com.myfitnesspal.shared.model.QuickAddFood r0 = r6.foodToEdit
            r1 = 0
            if (r0 == 0) goto L_0x0019
            dagger.Lazy<com.myfitnesspal.shared.service.userdata.UserEnergyService> r0 = r6.userEnergyService
            java.lang.Object r0 = r0.get()
            com.myfitnesspal.shared.service.userdata.UserEnergyService r0 = (com.myfitnesspal.shared.service.userdata.UserEnergyService) r0
            com.myfitnesspal.shared.model.QuickAddFood r2 = r6.foodToEdit
            float r2 = r2.getCalories()
            double r2 = (double) r2
            float r0 = r0.getCurrentEnergy(r2)
            goto L_0x001a
        L_0x0019:
            r0 = 0
        L_0x001a:
            r2 = 0
            r6.updateEnergyValue(r0, r2)
            com.myfitnesspal.shared.model.QuickAddFood r0 = r6.foodToEdit
            if (r0 == 0) goto L_0x0047
            float r0 = r0.getCarbohydrate()
            r6.carbs = r0
            com.myfitnesspal.shared.model.QuickAddFood r0 = r6.foodToEdit
            float r0 = r0.getFat()
            r6.fat = r0
            com.myfitnesspal.shared.model.QuickAddFood r0 = r6.foodToEdit
            float r0 = r0.getProtein()
            r6.protein = r0
            com.myfitnesspal.feature.timestamp.mixin.TimestampPickerMixin r0 = r6.timestampPickerMixin
            com.myfitnesspal.shared.model.QuickAddFood r3 = r6.foodToEdit
            java.util.Date r3 = r3.getEntryTime()
            r0.showCurrentTimestamp(r3)
            r6.supportInvalidateOptionsMenu()
            goto L_0x004f
        L_0x0047:
            float r0 = r6.defaultNutrientValue
            r6.carbs = r0
            r6.fat = r0
            r6.protein = r0
        L_0x004f:
            android.widget.TextView r0 = r6.tvCaloriesLabel
            dagger.Lazy<com.myfitnesspal.shared.util.LocalizedStringsUtil> r3 = r6.localizedStringsUtil
            java.lang.Object r3 = r3.get()
            com.myfitnesspal.shared.util.LocalizedStringsUtil r3 = (com.myfitnesspal.shared.util.LocalizedStringsUtil) r3
            java.lang.String r4 = "quick_add_label"
            dagger.Lazy<com.myfitnesspal.shared.service.userdata.UserEnergyService> r5 = r6.userEnergyService
            java.lang.String r3 = r3.getLocalizedString(r4, r5)
            r0.setText(r3)
            android.widget.TextView r0 = r6.tvQuickCalories
            dagger.Lazy<com.myfitnesspal.shared.util.LocalizedStringsUtil> r3 = r6.localizedStringsUtil
            java.lang.Object r3 = r3.get()
            com.myfitnesspal.shared.util.LocalizedStringsUtil r3 = (com.myfitnesspal.shared.util.LocalizedStringsUtil) r3
            java.lang.String r4 = "quick_add_hint"
            dagger.Lazy<com.myfitnesspal.shared.service.userdata.UserEnergyService> r5 = r6.userEnergyService
            java.lang.String r3 = r3.getLocalizedString(r4, r5)
            r0.setHint(r3)
            android.widget.TextView r0 = r6.tvQuickCarbs
            boolean r3 = r6.isQuickAddMacrosSubscribed
            r0.setEnabled(r3)
            android.widget.TextView r0 = r6.tvQuickFat
            boolean r3 = r6.isQuickAddMacrosSubscribed
            r0.setEnabled(r3)
            android.widget.TextView r0 = r6.tvQuickProtein
            boolean r3 = r6.isQuickAddMacrosSubscribed
            r0.setEnabled(r3)
            boolean r0 = r6.isQuickAddMacrosSubscribed
            if (r0 == 0) goto L_0x009a
            r0 = 2131888625(0x7f1209f1, float:1.941189E38)
            java.lang.String r0 = r6.getString(r0)
            goto L_0x009c
        L_0x009a:
            java.lang.String r0 = r6.EMPTY
        L_0x009c:
            android.widget.TextView r3 = r6.tvQuickCarbs
            r3.setHint(r0)
            android.widget.TextView r3 = r6.tvQuickFat
            r3.setHint(r0)
            android.widget.TextView r3 = r6.tvQuickProtein
            r3.setHint(r0)
            r6.setupPremiumLocksIfNecessary()
            android.widget.TextView r0 = r6.tvQuickCarbs
            boolean r3 = r6.isQuickAddMacrosSubscribed
            if (r3 == 0) goto L_0x00bf
            float r3 = r6.carbs
            int r4 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r4 <= 0) goto L_0x00bf
            java.lang.String r3 = com.uacf.core.util.NumberUtils.localeStringFromFloat(r3, r2)
            goto L_0x00c1
        L_0x00bf:
            java.lang.String r3 = r6.EMPTY
        L_0x00c1:
            r0.setText(r3)
            android.widget.TextView r0 = r6.tvQuickFat
            boolean r3 = r6.isQuickAddMacrosSubscribed
            if (r3 == 0) goto L_0x00d5
            float r3 = r6.fat
            int r4 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r4 <= 0) goto L_0x00d5
            java.lang.String r3 = com.uacf.core.util.NumberUtils.localeStringFromFloat(r3, r2)
            goto L_0x00d7
        L_0x00d5:
            java.lang.String r3 = r6.EMPTY
        L_0x00d7:
            r0.setText(r3)
            android.widget.TextView r0 = r6.tvQuickProtein
            boolean r3 = r6.isQuickAddMacrosSubscribed
            if (r3 == 0) goto L_0x00eb
            float r3 = r6.protein
            int r1 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r1 <= 0) goto L_0x00eb
            java.lang.String r1 = com.uacf.core.util.NumberUtils.localeStringFromFloat(r3, r2)
            goto L_0x00ed
        L_0x00eb:
            java.lang.String r1 = r6.EMPTY
        L_0x00ed:
            r0.setText(r1)
            r6.setupCalorieFieldListeners()
            r6.setupMacrosListeners()
            android.widget.TextView r0 = r6.tvQuickCalories
            r0.requestFocus()
            com.myfitnesspal.shared.util.InputMethodManagerHelper r0 = r6.getImmHelper()
            r0.showSoftInput()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.addentry.ui.activity.QuickAddActivity.setupViewBasedOnFeature():void");
    }

    private void setupMacrosListeners() {
        this.rlQuickCalories.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                QuickAddActivity.lambda$setupMacrosListeners$0(QuickAddActivity.this, view);
            }
        });
        this.rlQuickCarbs.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                QuickAddActivity.this.quickClickAction(view);
            }
        });
        this.rlQuickFat.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                QuickAddActivity.this.quickClickAction(view);
            }
        });
        this.rlQuickProtein.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                QuickAddActivity.this.quickClickAction(view);
            }
        });
        if (!this.isQuickAddMacrosSubscribed) {
            this.carbLock.setOnClickListener(this.lockListener);
            this.fatLock.setOnClickListener(this.lockListener);
            this.proteinLock.setOnClickListener(this.lockListener);
        }
        this.tvQuickCarbs.setOnEditorActionListener(new OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return QuickAddActivity.lambda$setupMacrosListeners$1(QuickAddActivity.this, textView, i, keyEvent);
            }
        });
        this.tvQuickFat.setOnEditorActionListener(new OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return QuickAddActivity.lambda$setupMacrosListeners$2(QuickAddActivity.this, textView, i, keyEvent);
            }
        });
        this.tvQuickProtein.setOnEditorActionListener(new OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return QuickAddActivity.lambda$setupMacrosListeners$3(QuickAddActivity.this, textView, i, keyEvent);
            }
        });
        this.tvQuickCarbs.addTextChangedListener(this.inputTextWatcher);
        this.tvQuickFat.addTextChangedListener(this.inputTextWatcher);
        this.tvQuickProtein.addTextChangedListener(this.inputTextWatcher);
        setInputLengthRestrictions();
    }

    public static /* synthetic */ void lambda$setupMacrosListeners$0(QuickAddActivity quickAddActivity, View view) {
        quickAddActivity.tvQuickCalories.requestFocus();
        quickAddActivity.getImmHelper().showSoftInput(quickAddActivity.tvQuickCalories);
    }

    public static /* synthetic */ boolean lambda$setupMacrosListeners$1(QuickAddActivity quickAddActivity, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 5) {
            return false;
        }
        quickAddActivity.nextAction(quickAddActivity.tvQuickFat);
        return true;
    }

    public static /* synthetic */ boolean lambda$setupMacrosListeners$2(QuickAddActivity quickAddActivity, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 5) {
            return false;
        }
        quickAddActivity.nextAction(quickAddActivity.tvQuickProtein);
        return true;
    }

    public static /* synthetic */ boolean lambda$setupMacrosListeners$3(QuickAddActivity quickAddActivity, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        quickAddActivity.updateCaloriesBasedOnMacrosIfNecessary();
        quickAddActivity.getImmHelper().hideSoftInput();
        return true;
    }

    private void setupCalorieFieldListeners() {
        this.tvQuickCalories.setOnEditorActionListener(new OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return QuickAddActivity.lambda$setupCalorieFieldListeners$4(QuickAddActivity.this, textView, i, keyEvent);
            }
        });
        this.tvQuickCalories.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                QuickAddActivity.this.setEnergy(Strings.notEmpty((Object) editable) ? QuickAddActivity.this.getFloatFromString(editable) : QuickAddActivity.this.defaultNutrientValue);
                QuickAddActivity.this.supportInvalidateOptionsMenu();
            }
        });
    }

    public static /* synthetic */ boolean lambda$setupCalorieFieldListeners$4(QuickAddActivity quickAddActivity, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 5) {
            return false;
        }
        quickAddActivity.nextAction(quickAddActivity.tvQuickCarbs);
        return true;
    }

    private void setInputLengthRestrictions() {
        InputFilter[] inputFilterArr = {new DecimalDigitsInputFilter(4, 1)};
        this.tvQuickCarbs.setFilters(inputFilterArr);
        this.tvQuickFat.setFilters(inputFilterArr);
        this.tvQuickProtein.setFilters(inputFilterArr);
    }

    /* access modifiers changed from: private */
    public void quickClickAction(View view) {
        if (this.isQuickAddMacrosSubscribed) {
            view.requestFocus();
            getImmHelper().showSoftInput(view);
            updateCaloriesBasedOnMacrosIfNecessary();
            return;
        }
        showPremiumUpsell();
    }

    /* access modifiers changed from: private */
    public void showPremiumUpsell() {
        getImmHelper().hideSoftInput();
        getNavigationHelper().withIntent(PremiumUpsellActivity.newStartIntent((Context) this, PremiumFeature.QuickAddMacros)).startActivity();
    }

    private void setupPremiumLocksIfNecessary() {
        if (!this.isQuickAddMacrosSubscribed) {
            this.lockListener = new OnClickListener() {
                public final void onClick(View view) {
                    QuickAddActivity.this.showPremiumUpsell();
                }
            };
        }
        ViewUtils.setVisible(!this.isQuickAddMacrosSubscribed, this.carbLock, this.fatLock, this.proteinLock);
    }

    private void nextAction(View view) {
        view.setFocusable(true);
        view.requestFocus();
        view.performClick();
    }

    /* access modifiers changed from: private */
    public void updateCaloriesBasedOnMacrosIfNecessary() {
        if (this.isQuickAddMacrosSubscribed) {
            updateEnergyValue(calculateEnergyBasedOnMacros(), true);
        }
    }

    private float calculateEnergyBasedOnMacros() {
        if (!this.isQuickAddMacrosSubscribed) {
            return BitmapDescriptorFactory.HUE_RED;
        }
        setEnergy(getFloatFromString(this.tvQuickCalories.getText()));
        this.carbs = getNutrientValueFromTextView(this.tvQuickCarbs);
        this.fat = getNutrientValueFromTextView(this.tvQuickFat);
        this.protein = getNutrientValueFromTextView(this.tvQuickProtein);
        return ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy((double) NutritionUtils.getCaloriesForMacros(this.carbs, this.fat, this.protein));
    }

    private float getNutrientValueFromTextView(TextView textView) {
        return Strings.isEmpty((Object) textView.getText()) ? this.defaultNutrientValue : getFloatFromString(textView.getText());
    }

    private void updateEnergyValue(float f, boolean z) {
        setEnergy(f);
        ViewUtils.setVisible(z, this.tvRecalculateLabel);
        TextView textView = this.tvQuickCalories;
        int i = this.roundedEnergy;
        textView.setText(i > 0 ? Strings.toString(Integer.valueOf(i)) : this.EMPTY);
    }

    private void onDone() {
        if (!NutritionUtils.areFoodValuesInRange(this.energy, this.isQuickAddMacrosSubscribed ? this.carbs : this.defaultNutrientValue, this.isQuickAddMacrosSubscribed ? this.fat : this.defaultNutrientValue, this.isQuickAddMacrosSubscribed ? this.protein : this.defaultNutrientValue)) {
            showDialogFragment(CalorieAddErrorDialogFragment.newInstance(Dialogs.OUT_OF_RANGE_ERROR_DIALOG), Fragments.CALORIES_ADD_ERROR_DIALOG);
            return;
        }
        $$Lambda$QuickAddActivity$GRWG1bvfNjIILrjV5bV5ciVMBpc r0 = new Function0() {
            public final void execute() {
                QuickAddActivity.lambda$onDone$6(QuickAddActivity.this);
            }
        };
        float calculateEnergyBasedOnMacros = calculateEnergyBasedOnMacros();
        if (this.roundedEnergy < Math.round(calculateEnergyBasedOnMacros)) {
            new MfpAlertDialogBuilder(this).setTitle((int) R.string.are_you_sure).setMessage((CharSequence) ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.QUICK_ADD_TOO_LOW, this.userEnergyService)).setPositiveButton((int) R.string.yesBtn, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(calculateEnergyBasedOnMacros, r0) {
                private final /* synthetic */ float f$1;
                private final /* synthetic */ Function0 f$2;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    QuickAddActivity.lambda$onDone$7(QuickAddActivity.this, this.f$1, this.f$2, dialogInterface, i);
                }
            }).setNegativeButton((int) R.string.noBtn, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    Function0.this.execute();
                }
            }).setOnCancelListener(new OnCancelListener() {
                public final void onCancel(DialogInterface dialogInterface) {
                    QuickAddActivity.this.tvQuickCalories.requestFocus();
                }
            }).show();
        } else {
            r0.execute();
        }
    }

    public static /* synthetic */ void lambda$onDone$6(QuickAddActivity quickAddActivity) throws RuntimeException {
        QuickAddFood quickAddFood = new QuickAddFood(((UserEnergyService) quickAddActivity.userEnergyService.get()).getCalories(quickAddActivity.energy), quickAddActivity.carbs, quickAddActivity.fat, quickAddActivity.protein, quickAddActivity.timestampPickerMixin.getTimestamp());
        quickAddActivity.getImmHelper().hideSoftInput();
        quickAddActivity.reportEvents();
        if (quickAddActivity.getCallingActivity() == null) {
            MealNamesDialogFragment newInstance = MealNamesDialogFragment.newInstance();
            quickAddActivity.showDialogFragment(newInstance, Fragments.MEAL_NAMES_DIALOG);
            newInstance.setListener(new MealNameDialogListener(quickAddFood));
            return;
        }
        quickAddActivity.getNavigationHelper().setResult(-1, new Intent().putExtra(EXTRA_QUICK_ADD_OBJECT, quickAddFood)).done();
    }

    public static /* synthetic */ void lambda$onDone$7(QuickAddActivity quickAddActivity, float f, Function0 function0, DialogInterface dialogInterface, int i) {
        quickAddActivity.updateEnergyValue(f, true);
        function0.execute();
    }

    private void reportEvents() {
        if (this.isQuickAddMacrosSubscribed) {
            HashMap hashMap = new HashMap();
            hashMap.put("energy_unit", ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergyUnit());
            hashMap.put(Attributes.QUICK_ENERGY_VALUE, Strings.toString(Integer.valueOf(Math.round(this.energy))));
            hashMap.put(Attributes.QUICK_CARB_VALUE, Strings.toString(Float.valueOf(this.carbs)));
            hashMap.put(Attributes.QUICK_FAT_VALUE, Strings.toString(Float.valueOf(this.fat)));
            hashMap.put(Attributes.QUICK_PROTEIN_VALUE, Strings.toString(Float.valueOf(this.protein)));
            ((AnalyticsService) this.analyticsService.get()).reportEvent("quick_add", (Map<String, String>) hashMap);
        }
    }

    /* access modifiers changed from: private */
    public float getFloatFromString(CharSequence charSequence) {
        return NumberUtils.tryParseFloat(Strings.toString(charSequence));
    }

    /* access modifiers changed from: private */
    public void setEnergy(float f) {
        this.energy = f;
        this.roundedEnergy = Math.round(this.energy);
    }
}
