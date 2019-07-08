package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.brightcove.player.event.EventType;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.service.NutrientGoalService;
import com.myfitnesspal.feature.help.ui.activity.Faq;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.db.table.ExerciseEntryPropertiesTable.Keys;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.myfitnesspal.shared.util.UnitsUtils.Energy;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Arrays;
import javax.inject.Inject;

public class CalorieAdjustmentExplanationView extends MfpActivity {
    public static float LOW_CALORIES_MEN = 1500.0f;
    public static float LOW_CALORIES_WOMEN = 1200.0f;
    private TextView _calGoalsTxt;
    private TextView _caloriesAdjustmentAmountLabel;
    private TextView _caloriesAdjustmentFooterComment;
    private TextView _caloriesAdjustmentLabel;
    private TextView _caloriesBurnedAmountLabelTxt;
    private TextView _caloriesBurnedLabelTxt;
    private TextView _dayProjectionStaticTextLabel;
    private TextView _explanationBlurbLabelTxt;
    private TextView _explanationBlurbLabelTxt2;
    private TextView _faqlink;
    private TextView _includedFromExercise;
    private TextView _negativeAdjustmentHelpView;
    private TextView _runningCaloriesLabelTxt;
    private TextView calorieAdjustmentHeader;
    private ExerciseEntry exerciseEntry;
    private TextView howWeCalculateText;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private TextView mfpDailyCalorieGoalLabel;
    @Inject
    Lazy<NutrientGoalService> nutrientGoalService;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public static Intent newStartIntent(Context context, ExerciseEntry exerciseEntry2) {
        return new Intent(context, CalorieAdjustmentExplanationView.class).putExtra("exercise_entry", exerciseEntry2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.calorie_adjustment_explanation);
        component().inject(this);
        this.exerciseEntry = (ExerciseEntry) ExtrasUtils.getParcelable(getIntent(), "exercise_entry", ExerciseEntry.class.getClassLoader());
        initializeUI();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        String property = getProperty(Keys.CLIENT_APP_NAME);
        String property2 = getProperty(Keys.CLIENT_APP_PROJECTION_TIMESTAMP);
        String localizeEnergyValueString = localizeEnergyValueString(getProperty(Keys.CLIENT_APP_EXERCISE_CALORIES));
        setTitle(property);
        this._explanationBlurbLabelTxt.setText(getString(R.string.calorie_adjustment_text1, new Object[]{property}));
        this._explanationBlurbLabelTxt2.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ADJUSTMENT_TEXT, this.userEnergyService.get()));
        this.howWeCalculateText.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.HOW_WE_CALC_HEADER, this.userEnergyService.get()));
        this.mfpDailyCalorieGoalLabel.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.DAILY_GOAL_LABEL, this.userEnergyService.get()));
        this._caloriesBurnedLabelTxt.setText(String.format(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.BURNED_LABEL, this.userEnergyService.get()), new Object[]{property}));
        this._caloriesBurnedAmountLabelTxt.setText(((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(localizeEnergyValueString(getProperty(Keys.CLIENT_APP_CALORIE_BURNED_PROJECTION_AMOUNT))));
        this.calorieAdjustmentHeader.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ADJUSTMENT_HEADER, this.userEnergyService.get()));
        if (Strings.notEmpty(property2)) {
            this._runningCaloriesLabelTxt.setText(String.format(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.BASED_ON, this.userEnergyService.get()), new Object[]{((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(localizeEnergyValueString(getProperty(Keys.CLIENT_APP_CALORIE_BURNED_AMOUNT))), DateTimeUtils.stringWithFormattedDate(Database.decodeDateAndTimeStringAsLocalTime(property2), "h:mm a")}));
        }
        this._calGoalsTxt.setText(((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(localizeEnergyValueString(getProperty(Keys.CLIENT_APP_MFP_CALORIE_PROJECTION))));
        if (Strings.notEmpty(localizeEnergyValueString)) {
            this._includedFromExercise.setText(String.format(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.INCLUDES_TEXT, this.userEnergyService.get()), new Object[]{((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(localizeEnergyValueString)}));
        }
        this._caloriesAdjustmentLabel.setText(String.format(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.APP_ADJUSTMENT_LABEL, this.userEnergyService.get()), new Object[]{property}));
        boolean equalsIgnoreCase = getProperty("allow_negative_calorie_adjustment").equalsIgnoreCase("true");
        ExerciseEntry exerciseEntry2 = this.exerciseEntry;
        String displayableEnergy = ((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(NumberUtils.localeStringFromFloat(exerciseEntry2 != null ? exerciseEntry2.getCalories() : BitmapDescriptorFactory.HUE_RED));
        if (!equalsIgnoreCase) {
            StringBuilder sb = new StringBuilder();
            sb.append(displayableEnergy);
            sb.append(EventType.ANY);
            displayableEnergy = sb.toString();
        }
        this._caloriesAdjustmentAmountLabel.setText(displayableEnergy);
        if (getProperty(Keys.CALORIE_ADJUSTMENT_REDUCED).equalsIgnoreCase("true")) {
            float energy = ((UserEnergyService) this.userEnergyService.get()).getEnergy(Energy.CALORIES, ((NutrientGoalService) this.nutrientGoalService.get()).getCachedDefaultGoal().getEnergy());
            float currentEnergy = ((UserEnergyService) this.userEnergyService.get()).getCurrentEnergy((double) (((Session) this.session.get()).getUser().getProfile().isFemale().booleanValue() ? LOW_CALORIES_WOMEN : LOW_CALORIES_MEN));
            if (energy > currentEnergy) {
                energy = currentEnergy;
            }
            this._caloriesAdjustmentFooterComment.setText(String.format(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.APP_ADJUSTMENT_COMMENT, this.userEnergyService.get()), new Object[]{((UserEnergyService) this.userEnergyService.get()).getDisplayableEnergy(String.format("%d", new Object[]{Integer.valueOf((int) energy)}))}));
        }
        this._includedFromExercise.setVisibility(!Strings.notEmpty(localizeEnergyValueString) ? 8 : 0);
        if (!Strings.notEmpty(property2)) {
            this._dayProjectionStaticTextLabel.setVisibility(8);
            this._runningCaloriesLabelTxt.setVisibility(8);
        }
        if (getProperty(Keys.CALORIE_ADJUSTMENT_REDUCED).equalsIgnoreCase("false")) {
            this._caloriesAdjustmentFooterComment.setVisibility(8);
        }
        ViewUtils.setVisible(!getProperty("allow_negative_calorie_adjustment").equalsIgnoreCase("true"), this._negativeAdjustmentHelpView);
        this._faqlink.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NavigationHelper navigationHelper = CalorieAdjustmentExplanationView.this.getNavigationHelper();
                CalorieAdjustmentExplanationView calorieAdjustmentExplanationView = CalorieAdjustmentExplanationView.this;
                navigationHelper.withIntent(Faq.newStartIntent(calorieAdjustmentExplanationView, 102, calorieAdjustmentExplanationView.getString(R.string.faq))).startActivity();
            }
        });
    }

    private String localizeEnergyValueString(String str) {
        return NumberUtils.localeStringFromFloat(Float.parseFloat(str));
    }

    private void initializeUI() {
        this._explanationBlurbLabelTxt = (TextView) findById(R.id.calorie_adjustment_text1);
        this._explanationBlurbLabelTxt2 = (TextView) findById(R.id.calorie_adjustment_text2);
        this.howWeCalculateText = (TextView) findById(R.id.how_we_calc_your_calorie_adjustment_header);
        this.mfpDailyCalorieGoalLabel = (TextView) findById(R.id.myfitnesspal_daily_calorie_goal_label);
        this._caloriesBurnedLabelTxt = (TextView) findById(R.id.calories_burned_label);
        this._caloriesBurnedAmountLabelTxt = (TextView) findById(R.id.calories_burned);
        this._runningCaloriesLabelTxt = (TextView) findById(R.id.based_on_text);
        this._calGoalsTxt = (TextView) findById(R.id.myfitnesspal_daily_calorie_goal);
        this._includedFromExercise = (TextView) findById(R.id.includes_text);
        this._caloriesAdjustmentLabel = (TextView) findById(R.id.app_calorie_adjustment_label);
        this._caloriesAdjustmentAmountLabel = (TextView) findById(R.id.calories_adjustment);
        this._caloriesAdjustmentFooterComment = (TextView) findById(R.id.app_calorie_adjustment_comment);
        this._faqlink = (TextView) findById(R.id.faq_link);
        this._dayProjectionStaticTextLabel = (TextView) findById(R.id.full_day_projection_label);
        this._negativeAdjustmentHelpView = (TextView) findById(R.id.negative_adjustment_link);
        this.calorieAdjustmentHeader = (TextView) findViewById(R.id.calorie_adjustment_header);
    }

    public static boolean displaybleWithExerciseEntry(ExerciseEntry exerciseEntry2) {
        return exerciseEntry2.containsAllExtraProperties(Arrays.asList(new String[]{Keys.CLIENT_APP_CALORIE_BURNED_PROJECTION_AMOUNT, Keys.CLIENT_APP_MFP_CALORIE_PROJECTION, Keys.CLIENT_APP_NAME, Keys.CALORIE_ADJUSTMENT_REDUCED, "allow_negative_calorie_adjustment"}));
    }

    /* access modifiers changed from: 0000 */
    public final String getProperty(String str) {
        ExerciseEntry exerciseEntry2 = this.exerciseEntry;
        if (exerciseEntry2 == null || !CollectionUtils.notEmpty(exerciseEntry2.getExtraProperties())) {
            return null;
        }
        return this.exerciseEntry.extraPropertyNamed(str);
    }
}
