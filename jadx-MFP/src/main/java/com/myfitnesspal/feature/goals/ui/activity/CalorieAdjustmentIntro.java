package com.myfitnesspal.feature.goals.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.help.ui.activity.Faq;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.LocalizedStrings;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.service.ExerciseStringService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import javax.inject.Inject;

public class CalorieAdjustmentIntro extends MfpActivity {
    @BindView(2131361878)
    View adjustmentContainer;
    @BindView(2131362057)
    ImageView adjustmentIcon;
    private boolean allowNegative;
    @BindView(2131363242)
    ImageView appIcon;
    private float calories;
    @BindView(2131362069)
    TextView caloriesEarned;
    @BindView(2131362070)
    TextView caloriesEarnedLabel;
    @Inject
    Lazy<ExerciseStringService> exerciseStringService;
    private String imageUrl;
    @BindView(2131362897)
    Button learnMore;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @BindView(2131363106)
    TextView negativeAdjustmentLabel;
    @BindView(2131363244)
    TextView partnerAppLabel;
    private String partnerName;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public static Intent newStartIntent(Context context, float f, String str, String str2, boolean z, ExerciseEntry exerciseEntry) {
        return new Intent(context, CalorieAdjustmentIntro.class).putExtra("calories", f).putExtra("image_url", str).putExtra(Extras.PARTNER_NAME, str2).putExtra("allow_negative_calorie_adjustment", z).putExtra("exercise_entry", exerciseEntry);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.calorie_adjustment_intro);
        Intent intent = getIntent();
        this.calories = ExtrasUtils.getFloat(intent, "calories");
        this.imageUrl = ExtrasUtils.getString(intent, "image_url");
        this.partnerName = ExtrasUtils.getString(intent, Extras.PARTNER_NAME);
        this.allowNegative = ExtrasUtils.getBoolean(intent, "allow_negative_calorie_adjustment");
        setListeners();
        setup();
    }

    private void setup() {
        RequestBuilder requestBuilder;
        Resources resources = getResources();
        if (this.calories < BitmapDescriptorFactory.HUE_RED) {
            this.adjustmentIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_calorie_adjust_walking));
            this.caloriesEarnedLabel.setTextColor(resources.getColor(R.color.calorie_adjust_gray));
            this.caloriesEarnedLabel.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.ADJUSTMENT_HEADER, this.userEnergyService.get()));
            this.caloriesEarned.setTextColor(resources.getColor(R.color.calorie_adjust_gray));
        } else {
            this.adjustmentIcon.setImageDrawable(resources.getDrawable(R.drawable.ic_calorie_adjust_trophy));
            this.caloriesEarnedLabel.setTextColor(resources.getColor(R.color.calorie_adjust_green));
            this.caloriesEarnedLabel.setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(LocalizedStrings.EXTRA_EARNED, this.userEnergyService.get()));
        }
        this.caloriesEarned.setText(((UserEnergyService) this.userEnergyService.get()).getRoundedCurrentEnergyAsString((double) this.calories, false));
        this.negativeAdjustmentLabel.setText(getString(this.allowNegative ? R.string.negative_calorie_enabled : R.string.negative_calorie_disabled));
        boolean equals = "mfp-mobile-android-google".equals(this.partnerName);
        TextView textView = this.partnerAppLabel;
        ExerciseStringService exerciseStringService2 = (ExerciseStringService) this.exerciseStringService.get();
        String string = getString(R.string.you_are_using);
        Object[] objArr = new Object[1];
        objArr[0] = equals ? getString(R.string.google_fit) : this.partnerName;
        textView.setText(exerciseStringService2.getAdjustedExerciseName(String.format(string, objArr), ((UserEnergyService) this.userEnergyService.get()).isCalories()));
        if (equals) {
            requestBuilder = Glide.with((Context) this).load(Integer.valueOf(R.drawable.ic_google_fit_steps));
        } else if (!Strings.notEmpty(this.imageUrl)) {
            requestBuilder = Glide.with((Context) this).load("");
        } else if (Strings.equals(this.imageUrl, Extras.MFP_MOBILE_IOS)) {
            requestBuilder = Glide.with((Context) this).load(Integer.valueOf(R.drawable.ic_steps_mobile));
        } else {
            requestBuilder = Glide.with((Context) this).load(this.imageUrl);
        }
        requestBuilder.apply(new RequestOptions().placeholder((int) R.drawable.ic_steps_missing).error(R.drawable.ic_steps_missing)).into(this.appIcon);
    }

    private void setListeners() {
        this.learnMore.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                CalorieAdjustmentIntro.this.getNavigationHelper().withIntent(Faq.newStartIntent(CalorieAdjustmentIntro.this, 102, CalorieAdjustmentIntro.this.getString(R.string.faq))).startActivity();
            }
        });
        this.adjustmentContainer.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                CalorieAdjustmentIntro.this.getNavigationHelper().withIntent(CalorieAdjustmentExplanationView.newStartIntent(CalorieAdjustmentIntro.this, (ExerciseEntry) ExtrasUtils.getParcelable(CalorieAdjustmentIntro.this.getIntent(), "exercise_entry", ExerciseEntry.class.getClassLoader()))).startActivity();
            }
        });
    }
}
