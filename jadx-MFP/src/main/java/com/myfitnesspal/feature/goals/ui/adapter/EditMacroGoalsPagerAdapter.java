package com.myfitnesspal.feature.goals.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.event.MacroGoalsUpdatedEvent;
import com.myfitnesspal.feature.goals.ui.fragment.EditMacroGoalsByGramsFragment;
import com.myfitnesspal.feature.goals.ui.fragment.EditMacroGoalsByPercentFragment;
import com.myfitnesspal.feature.goals.ui.fragment.EditMacroGoalsByPercentFragment.Mode;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.premium.service.PremiumService.Availability;
import dagger.Lazy;

public class EditMacroGoalsPagerAdapter extends FragmentPagerAdapter {
    private static final int BY_GRAM_INDEX = 1;
    private static final int BY_PERCENT_INDEX = 0;
    private static final int FRAGMENT_COUNT = 2;
    private static final int[] tabTitleResId = {R.string.goals_percent, R.string.goals_gram};
    private final float carb;
    private final Context context;
    private EditMacroGoalsByGramsFragment editMacroGoalsByGramsFragment;
    private EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment;
    private final float fat;
    private final boolean isMacroGoalFormatGrams;
    private final float localizedEnergyValue;
    private final Lazy<PremiumService> premiumService;
    private final float protein;

    public int getCount() {
        return 2;
    }

    public EditMacroGoalsPagerAdapter(Context context2, FragmentManager fragmentManager, float f, float f2, float f3, float f4, boolean z, Lazy<PremiumService> lazy) {
        super(fragmentManager);
        this.context = context2;
        this.localizedEnergyValue = f;
        this.carb = f2;
        this.protein = f3;
        this.fat = f4;
        this.isMacroGoalFormatGrams = z;
        this.premiumService = lazy;
    }

    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                if (this.editMacroGoalsByPercentFragment == null) {
                    this.editMacroGoalsByPercentFragment = EditMacroGoalsByPercentFragment.newInstance(Mode.ByValue, null, this.localizedEnergyValue, this.carb, this.protein, this.fat, this.isMacroGoalFormatGrams);
                }
                return this.editMacroGoalsByPercentFragment;
            case 1:
                if (this.editMacroGoalsByGramsFragment == null) {
                    this.editMacroGoalsByGramsFragment = EditMacroGoalsByGramsFragment.newInstance(this.localizedEnergyValue, this.carb, this.protein, this.fat, this.isMacroGoalFormatGrams);
                }
                return this.editMacroGoalsByGramsFragment;
            default:
                return null;
        }
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        switch (i) {
            case 0:
                this.editMacroGoalsByPercentFragment = (EditMacroGoalsByPercentFragment) fragment;
                break;
            case 1:
                this.editMacroGoalsByGramsFragment = (EditMacroGoalsByGramsFragment) fragment;
                break;
        }
        return fragment;
    }

    public MacroGoalsUpdatedEvent executeDoneAction(int i) {
        switch (i) {
            case 0:
                return this.editMacroGoalsByPercentFragment.execute();
            case 1:
                return this.editMacroGoalsByGramsFragment.execute();
            default:
                throw new IllegalArgumentException("selectedTabPosition is invalid");
        }
    }

    public boolean isTotalValidOnPercentFragment() {
        EditMacroGoalsByPercentFragment editMacroGoalsByPercentFragment2 = this.editMacroGoalsByPercentFragment;
        boolean z = false;
        if (editMacroGoalsByPercentFragment2 == null) {
            return false;
        }
        if (editMacroGoalsByPercentFragment2.getCurrentMacronutrientTotalPercent() == 100) {
            z = true;
        }
        return z;
    }

    public CharSequence getPageTitle(int i) {
        if (((PremiumService) this.premiumService.get()).getFeatureAvailability(PremiumFeature.TrackMacrosByGram) != Availability.Locked || i != 1) {
            return this.context.getString(tabTitleResId[i]);
        }
        Drawable drawable = this.context.getResources().getDrawable(R.drawable.ic_premium_lock);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        String string = this.context.getString(tabTitleResId[i]);
        StringBuilder sb = new StringBuilder();
        sb.append(string);
        sb.append("   ");
        SpannableString spannableString = new SpannableString(sb.toString());
        spannableString.setSpan(new ImageSpan(drawable, 1), string.length() + 2, string.length() + 3, 33);
        return spannableString;
    }
}
