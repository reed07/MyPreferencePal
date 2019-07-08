package com.myfitnesspal.feature.diary.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.diary.listener.DiaryAdapterItemActionListener;
import com.myfitnesspal.feature.diary.model.DiarySectionNutritionTotals;
import com.myfitnesspal.feature.diary.model.MealMacrosDisplayUnit;
import com.myfitnesspal.feature.diary.ui.adapter.DiaryAdapter.EntryViewHolder_ViewBinding;
import com.myfitnesspal.feature.diary.ui.fragment.DiaryFragmentBase;
import com.myfitnesspal.feature.diary.ui.item.DiaryAdapterItem;
import com.myfitnesspal.feature.diary.ui.item.SectionHeaderItem;
import com.myfitnesspal.feature.diary.util.DiaryDelegate;
import com.myfitnesspal.feature.diary.util.MealMacrosUtil;
import com.myfitnesspal.feature.home.service.NewsFeedAnalyticsHelper;
import com.myfitnesspal.feature.nutrition.model.MacroValues;
import com.myfitnesspal.shared.api.request.FoodAnalyzerResponseData;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryDay;
import com.myfitnesspal.shared.model.v1.DiaryEntryItem;
import com.myfitnesspal.shared.model.v1.ExerciseEntry;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.view.RecyclerViewHolder;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.Tuple3;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DiaryLandscapeAdapter extends DiaryAdapter {
    private static final int DEFAULT_ENTRY_TEXT_SIZE = 14;
    private static final int DEFAULT_SECTION_HEADER_TEXT_SIZE = 16;
    private static final int MAX_CHAR_COUNT_FOR_NUTRIENT = 5;
    private static final int SMALLER_TEXT_SIZE = 12;
    /* access modifiers changed from: private */
    public final MfpDailyGoal dailyGoal;

    protected class DiaryLandscapeFooterViewHolder extends RecyclerViewHolder<DiaryAdapterItem> {
        @BindView(2131363431)
        View remainingContainer;
        @BindView(2131363875)
        View totalsContainer;

        protected DiaryLandscapeFooterViewHolder(ViewGroup viewGroup) {
            super(R.layout.diary_totals_landscape, viewGroup);
        }

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
            int i2;
            UserEnergyService userEnergyService = (UserEnergyService) DiaryLandscapeAdapter.this.userEnergyService.get();
            if (DiaryLandscapeAdapter.this.dailyGoal == null || DiaryLandscapeAdapter.this.dailyGoal.isAssignExerciseEnergyOn()) {
                i2 = 0;
                for (ExerciseEntry calories : DiaryLandscapeAdapter.this.currentDiaryDay.getExerciseEntries()) {
                    i2 += userEnergyService.getRoundedCurrentEnergy((double) calories.getCalories());
                }
            } else {
                i2 = 0;
            }
            this.totalsContainer.setOnClickListener(DiaryLandscapeAdapter.this.sectionHeaderOnClickListener);
            MealMacrosDisplayUnit mealMacrosDisplayUnit = DiaryLandscapeAdapter.this.getMealMacrosDisplayUnit();
            boolean shouldDisplayDiaryMealMacros = DiaryLandscapeAdapter.this.shouldDisplayDiaryMealMacros();
            Tuple2 nutrientValues = getNutrientValues(DiaryLandscapeAdapter.this.currentDiaryDay, 0);
            Tuple2 nutrientValues2 = getNutrientValues(DiaryLandscapeAdapter.this.currentDiaryDay, 9);
            Tuple2 nutrientValues3 = getNutrientValues(DiaryLandscapeAdapter.this.currentDiaryDay, 1);
            Tuple2 nutrientValues4 = getNutrientValues(DiaryLandscapeAdapter.this.currentDiaryDay, 12);
            Tuple2 nutrientValues5 = getNutrientValues(DiaryLandscapeAdapter.this.currentDiaryDay, 7);
            Tuple2 nutrientValues6 = getNutrientValues(DiaryLandscapeAdapter.this.currentDiaryDay, 11);
            Tuple3 macroValuesBasedOnUserPreference = MealMacrosUtil.getMacroValuesBasedOnUserPreference(mealMacrosDisplayUnit, new MacroValues(((Float) nutrientValues2.getItem1()).floatValue(), ((Float) nutrientValues3.getItem1()).floatValue(), ((Float) nutrientValues4.getItem1()).floatValue()), shouldDisplayDiaryMealMacros);
            ArrayList arrayList = new ArrayList();
            arrayList.add(Tuple.create(Integer.valueOf(R.id.calories), nutrientValues.getItem1()));
            arrayList.add(Tuple.create(Integer.valueOf(R.id.carbs), macroValuesBasedOnUserPreference.getItem1()));
            arrayList.add(Tuple.create(Integer.valueOf(R.id.fat), macroValuesBasedOnUserPreference.getItem2()));
            arrayList.add(Tuple.create(Integer.valueOf(R.id.protein), macroValuesBasedOnUserPreference.getItem3()));
            arrayList.add(Tuple.create(Integer.valueOf(R.id.sodium), nutrientValues5.getItem1()));
            arrayList.add(Tuple.create(Integer.valueOf(R.id.sugars), nutrientValues6.getItem1()));
            populateNutrientView(this.totalsContainer, arrayList, false);
            if (DiaryLandscapeAdapter.this.isForRemoteUser) {
                ViewUtils.setGone(this.remainingContainer);
                return;
            }
            ViewUtils.setVisible(this.remainingContainer);
            Tuple3 macroValuesBasedOnUserPreference2 = MealMacrosUtil.getMacroValuesBasedOnUserPreference(mealMacrosDisplayUnit, new MacroValues(((Float) nutrientValues2.getItem2()).floatValue() - ((Float) nutrientValues2.getItem1()).floatValue(), ((Float) nutrientValues3.getItem2()).floatValue() - ((Float) nutrientValues3.getItem1()).floatValue(), ((Float) nutrientValues4.getItem2()).floatValue() - ((Float) nutrientValues4.getItem1()).floatValue()), shouldDisplayDiaryMealMacros);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Tuple.create(Integer.valueOf(R.id.calories), Float.valueOf((((Float) nutrientValues.getItem2()).floatValue() - ((Float) nutrientValues.getItem1()).floatValue()) + ((float) i2))));
            arrayList2.add(Tuple.create(Integer.valueOf(R.id.carbs), macroValuesBasedOnUserPreference2.getItem1()));
            arrayList2.add(Tuple.create(Integer.valueOf(R.id.fat), macroValuesBasedOnUserPreference2.getItem2()));
            arrayList2.add(Tuple.create(Integer.valueOf(R.id.protein), macroValuesBasedOnUserPreference2.getItem3()));
            arrayList2.add(Tuple.create(Integer.valueOf(R.id.sodium), Float.valueOf(((Float) nutrientValues5.getItem2()).floatValue() - ((Float) nutrientValues5.getItem1()).floatValue())));
            arrayList2.add(Tuple.create(Integer.valueOf(R.id.sugars), Float.valueOf(((Float) nutrientValues6.getItem2()).floatValue() - ((Float) nutrientValues6.getItem1()).floatValue())));
            populateNutrientView(this.remainingContainer, arrayList2, true);
        }

        private Tuple2<Float, Float> getNutrientValues(DiaryDay diaryDay, int i) {
            if (i != 0) {
                return Tuple.create(Float.valueOf(diaryDay.amountOfNutrientConsumed(i)), Float.valueOf(diaryDay.getNutrientGoal(i)));
            }
            UserEnergyService userEnergyService = (UserEnergyService) DiaryLandscapeAdapter.this.userEnergyService.get();
            return Tuple.create(Float.valueOf(diaryDay.caloriesConsumed(true)), Float.valueOf((float) userEnergyService.getRoundedCurrentEnergy((double) diaryDay.getNutrientGoal(i))));
        }

        private void populateNutrientView(View view, List<Tuple2<Integer, Float>> list, boolean z) {
            String str;
            boolean shouldShrinkTextSize = shouldShrinkTextSize(list);
            for (Tuple2 tuple2 : list) {
                int intValue = ((Integer) tuple2.getItem1()).intValue();
                float floatValue = ((Float) tuple2.getItem2()).floatValue();
                TextView textView = (TextView) ViewUtils.findById(view, intValue);
                boolean z2 = floatValue == -2.14748365E9f;
                if (z2) {
                    str = "-";
                } else {
                    str = NumberUtils.localeStringFromDoubleNoDecimal((double) floatValue);
                }
                textView.setText(str);
                if (z) {
                    Resources resources = getContext().getResources();
                    int i = z2 ? R.color.black_text : floatValue >= BitmapDescriptorFactory.HUE_RED ? R.color.diary_green : R.color.dark_orange;
                    textView.setTextColor(resources.getColor(i));
                }
                int i2 = shouldShrinkTextSize ? 12 : z ? 14 : 16;
                textView.setTextSize(2, (float) i2);
            }
        }

        private boolean shouldShrinkTextSize(List<Tuple2<Integer, Float>> list) {
            for (Tuple2 item2 : list) {
                if (NumberUtils.localeStringFromAbsDoubleNoDecimal((double) ((Float) item2.getItem2()).floatValue()).length() > 5) {
                    return true;
                }
            }
            return false;
        }
    }

    public class DiaryLandscapeFooterViewHolder_ViewBinding implements Unbinder {
        private DiaryLandscapeFooterViewHolder target;

        @UiThread
        public DiaryLandscapeFooterViewHolder_ViewBinding(DiaryLandscapeFooterViewHolder diaryLandscapeFooterViewHolder, View view) {
            this.target = diaryLandscapeFooterViewHolder;
            diaryLandscapeFooterViewHolder.totalsContainer = Utils.findRequiredView(view, R.id.total_header, "field 'totalsContainer'");
            diaryLandscapeFooterViewHolder.remainingContainer = Utils.findRequiredView(view, R.id.remaining, "field 'remainingContainer'");
        }

        @CallSuper
        public void unbind() {
            DiaryLandscapeFooterViewHolder diaryLandscapeFooterViewHolder = this.target;
            if (diaryLandscapeFooterViewHolder != null) {
                this.target = null;
                diaryLandscapeFooterViewHolder.totalsContainer = null;
                diaryLandscapeFooterViewHolder.remainingContainer = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    protected class EntryLandscapeViewHolder extends EntryViewHolder {
        @BindView(2131362085)
        TextView txtCarbs;
        @BindView(2131362559)
        TextView txtFat;
        @BindView(2131363347)
        TextView txtProtein;
        @BindView(2131363675)
        TextView txtSodium;
        @BindView(2131363752)
        TextView txtSugar;

        EntryLandscapeViewHolder(ViewGroup viewGroup) {
            super((int) R.layout.diary_entry_landscape, viewGroup);
            this.txtItemDescription = (TextView) findById(R.id.description);
            this.txtItemDetails = (TextView) findById(R.id.details);
            this.txtCalories = (TextView) findById(R.id.calories);
        }

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
            DiaryEntryItem diaryEntryItem = (DiaryEntryItem) diaryAdapterItem;
            int sectionType = diaryEntryItem.getSectionType();
            ViewUtils.setVisible(this.txtItemDetails);
            if (this.divider != null) {
                ViewUtils.setVisible(this.divider);
            }
            if (sectionType == 2) {
                bindAsFoodEntry(diaryEntryItem);
                this.txtCalories.setTextSize(2, Strings.toString(this.txtCalories.getText()).length() > 5 ? 12.0f : 14.0f);
                bindFoodEntryMacros(diaryEntryItem);
            }
        }

        private void bindFoodEntryMacros(DiaryEntryItem diaryEntryItem) {
            FoodEntry foodEntry = (FoodEntry) diaryEntryItem.getDatabaseObject();
            Tuple3 macroValuesBasedOnUserPreference = MealMacrosUtil.getMacroValuesBasedOnUserPreference(DiaryLandscapeAdapter.this.getMealMacrosDisplayUnit(), new MacroValues(foodEntry.getCarbsValue(), foodEntry.getFatValue(), foodEntry.getProteinValue()), DiaryLandscapeAdapter.this.shouldDisplayDiaryMealMacros());
            boolean shouldDecreaseEntryRowTextSize = shouldDecreaseEntryRowTextSize(foodEntry);
            setEntryText(this.txtCarbs, ((Float) macroValuesBasedOnUserPreference.getItem1()).floatValue(), shouldDecreaseEntryRowTextSize);
            setEntryText(this.txtFat, ((Float) macroValuesBasedOnUserPreference.getItem2()).floatValue(), shouldDecreaseEntryRowTextSize);
            setEntryText(this.txtProtein, ((Float) macroValuesBasedOnUserPreference.getItem3()).floatValue(), shouldDecreaseEntryRowTextSize);
            setEntryText(this.txtSodium, foodEntry.getSodiumValue(), shouldDecreaseEntryRowTextSize);
            setEntryText(this.txtSugar, foodEntry.getSugarsValue(), shouldDecreaseEntryRowTextSize);
        }

        private void setEntryText(TextView textView, float f, boolean z) {
            DiaryLandscapeAdapter.this.checkForNegativeAndSetTextAndSize(textView, f, z ? 12 : 14);
        }

        private boolean shouldDecreaseEntryRowTextSize(FoodEntry foodEntry) {
            return DiaryLandscapeAdapter.this.getNutrientValueString(foodEntry.getCarbsValue()).length() > 5 || DiaryLandscapeAdapter.this.getNutrientValueString(foodEntry.getFatValue()).length() > 5 || DiaryLandscapeAdapter.this.getNutrientValueString(foodEntry.getProteinValue()).length() > 5 || DiaryLandscapeAdapter.this.getNutrientValueString(foodEntry.getSodiumValue()).length() > 5 || DiaryLandscapeAdapter.this.getNutrientValueString(foodEntry.getSugarsValue()).length() > 5;
        }
    }

    public class EntryLandscapeViewHolder_ViewBinding extends EntryViewHolder_ViewBinding {
        private EntryLandscapeViewHolder target;

        @UiThread
        public EntryLandscapeViewHolder_ViewBinding(EntryLandscapeViewHolder entryLandscapeViewHolder, View view) {
            super(entryLandscapeViewHolder, view);
            this.target = entryLandscapeViewHolder;
            entryLandscapeViewHolder.txtCarbs = (TextView) Utils.findRequiredViewAsType(view, R.id.carbs, "field 'txtCarbs'", TextView.class);
            entryLandscapeViewHolder.txtFat = (TextView) Utils.findRequiredViewAsType(view, R.id.fat, "field 'txtFat'", TextView.class);
            entryLandscapeViewHolder.txtProtein = (TextView) Utils.findRequiredViewAsType(view, R.id.protein, "field 'txtProtein'", TextView.class);
            entryLandscapeViewHolder.txtSodium = (TextView) Utils.findRequiredViewAsType(view, R.id.sodium, "field 'txtSodium'", TextView.class);
            entryLandscapeViewHolder.txtSugar = (TextView) Utils.findRequiredViewAsType(view, R.id.sugars, "field 'txtSugar'", TextView.class);
        }

        public void unbind() {
            EntryLandscapeViewHolder entryLandscapeViewHolder = this.target;
            if (entryLandscapeViewHolder != null) {
                this.target = null;
                entryLandscapeViewHolder.txtCarbs = null;
                entryLandscapeViewHolder.txtFat = null;
                entryLandscapeViewHolder.txtProtein = null;
                entryLandscapeViewHolder.txtSodium = null;
                entryLandscapeViewHolder.txtSugar = null;
                super.unbind();
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    protected class SectionHeaderLandscapeViewHolder extends RecyclerViewHolder<DiaryAdapterItem> {
        @BindView(2131362339)
        View divider;
        @BindView(2131363831)
        ViewGroup tipContainer;
        @BindView(2131362058)
        TextView txtSectionCalories;
        @BindView(2131362085)
        TextView txtSectionCarbs;
        @BindView(2131362559)
        TextView txtSectionFat;
        @BindView(2131363091)
        TextView txtSectionHeader;
        @BindView(2131363347)
        TextView txtSectionProtein;
        @BindView(2131363675)
        TextView txtSectionSodium;
        @BindView(2131363752)
        TextView txtSectionSugar;

        SectionHeaderLandscapeViewHolder(ViewGroup viewGroup) {
            super(R.layout.section_header_landscape, viewGroup);
        }

        public void setData(DiaryAdapterItem diaryAdapterItem, int i) {
            SectionHeaderItem sectionHeaderItem = (SectionHeaderItem) diaryAdapterItem;
            getParent().setOnClickListener(DiaryLandscapeAdapter.this.sectionHeaderOnClickListener);
            DiarySectionNutritionTotals macroTotals = sectionHeaderItem.getMacroTotals();
            Tuple3 macroValuesBasedOnUserPreference = MealMacrosUtil.getMacroValuesBasedOnUserPreference(DiaryLandscapeAdapter.this.getMealMacrosDisplayUnit(), macroTotals.getMacroValues(), DiaryLandscapeAdapter.this.shouldDisplayDiaryMealMacros());
            boolean shouldDecreaseSectionHeaderTextSize = shouldDecreaseSectionHeaderTextSize(macroTotals);
            this.txtSectionHeader.setText(sectionHeaderItem.getLocalizeddHeaderName());
            DiaryLandscapeAdapter.this.setAnnotationTextForSectionHeader(this.txtSectionCalories, sectionHeaderItem);
            TextView textView = this.txtSectionCalories;
            textView.setTextSize(2, textView.getText().length() > 5 ? 12.0f : 16.0f);
            setSectionHeaderText(this.txtSectionCarbs, ((Float) macroValuesBasedOnUserPreference.getItem1()).floatValue(), shouldDecreaseSectionHeaderTextSize);
            setSectionHeaderText(this.txtSectionFat, ((Float) macroValuesBasedOnUserPreference.getItem2()).floatValue(), shouldDecreaseSectionHeaderTextSize);
            setSectionHeaderText(this.txtSectionProtein, ((Float) macroValuesBasedOnUserPreference.getItem3()).floatValue(), shouldDecreaseSectionHeaderTextSize);
            setSectionHeaderText(this.txtSectionSodium, macroTotals.getSodium(), shouldDecreaseSectionHeaderTextSize);
            setSectionHeaderText(this.txtSectionSugar, macroTotals.getSugars(), shouldDecreaseSectionHeaderTextSize);
            DiaryLandscapeAdapter.this.showMealHeaderTip(sectionHeaderItem, this.tipContainer, this.divider, i);
        }

        private void setSectionHeaderText(TextView textView, float f, boolean z) {
            textView.setTextSize(2, z ? 12.0f : 16.0f);
            DiaryLandscapeAdapter.this.checkForNegativeAndSetTextAndSize(textView, f, z ? 12 : 16);
        }

        private boolean shouldDecreaseSectionHeaderTextSize(DiarySectionNutritionTotals diarySectionNutritionTotals) {
            return DiaryLandscapeAdapter.this.getNutrientValueString(diarySectionNutritionTotals.getCarbs()).length() > 5 || DiaryLandscapeAdapter.this.getNutrientValueString(diarySectionNutritionTotals.getFat()).length() > 5 || DiaryLandscapeAdapter.this.getNutrientValueString(diarySectionNutritionTotals.getProtein()).length() > 5 || DiaryLandscapeAdapter.this.getNutrientValueString(diarySectionNutritionTotals.getSodium()).length() > 5 || DiaryLandscapeAdapter.this.getNutrientValueString(diarySectionNutritionTotals.getSugars()).length() > 5;
        }
    }

    public class SectionHeaderLandscapeViewHolder_ViewBinding implements Unbinder {
        private SectionHeaderLandscapeViewHolder target;

        @UiThread
        public SectionHeaderLandscapeViewHolder_ViewBinding(SectionHeaderLandscapeViewHolder sectionHeaderLandscapeViewHolder, View view) {
            this.target = sectionHeaderLandscapeViewHolder;
            sectionHeaderLandscapeViewHolder.txtSectionHeader = (TextView) Utils.findRequiredViewAsType(view, R.id.name, "field 'txtSectionHeader'", TextView.class);
            sectionHeaderLandscapeViewHolder.txtSectionCalories = (TextView) Utils.findRequiredViewAsType(view, R.id.calories, "field 'txtSectionCalories'", TextView.class);
            sectionHeaderLandscapeViewHolder.txtSectionCarbs = (TextView) Utils.findRequiredViewAsType(view, R.id.carbs, "field 'txtSectionCarbs'", TextView.class);
            sectionHeaderLandscapeViewHolder.txtSectionFat = (TextView) Utils.findRequiredViewAsType(view, R.id.fat, "field 'txtSectionFat'", TextView.class);
            sectionHeaderLandscapeViewHolder.txtSectionProtein = (TextView) Utils.findRequiredViewAsType(view, R.id.protein, "field 'txtSectionProtein'", TextView.class);
            sectionHeaderLandscapeViewHolder.txtSectionSodium = (TextView) Utils.findRequiredViewAsType(view, R.id.sodium, "field 'txtSectionSodium'", TextView.class);
            sectionHeaderLandscapeViewHolder.txtSectionSugar = (TextView) Utils.findRequiredViewAsType(view, R.id.sugars, "field 'txtSectionSugar'", TextView.class);
            sectionHeaderLandscapeViewHolder.tipContainer = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.tip_container, "field 'tipContainer'", ViewGroup.class);
            sectionHeaderLandscapeViewHolder.divider = Utils.findRequiredView(view, R.id.divider, "field 'divider'");
        }

        @CallSuper
        public void unbind() {
            SectionHeaderLandscapeViewHolder sectionHeaderLandscapeViewHolder = this.target;
            if (sectionHeaderLandscapeViewHolder != null) {
                this.target = null;
                sectionHeaderLandscapeViewHolder.txtSectionHeader = null;
                sectionHeaderLandscapeViewHolder.txtSectionCalories = null;
                sectionHeaderLandscapeViewHolder.txtSectionCarbs = null;
                sectionHeaderLandscapeViewHolder.txtSectionFat = null;
                sectionHeaderLandscapeViewHolder.txtSectionProtein = null;
                sectionHeaderLandscapeViewHolder.txtSectionSodium = null;
                sectionHeaderLandscapeViewHolder.txtSectionSugar = null;
                sectionHeaderLandscapeViewHolder.tipContainer = null;
                sectionHeaderLandscapeViewHolder.divider = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public DiaryLandscapeAdapter(Context context, List<DiaryAdapterItem> list, Map<SectionHeaderItem, List<DatabaseObject>> map, DiaryDay diaryDay, List<FoodAnalyzerResponseData> list2, List<Long> list3, DiaryDelegate diaryDelegate, boolean z, boolean z2, DiaryFragmentBase diaryFragmentBase, int i, DiaryAdapterItemActionListener diaryAdapterItemActionListener, MfpDailyGoal mfpDailyGoal, Lazy<NewsFeedAnalyticsHelper> lazy) {
        super(context, list, map, diaryDay, list2, list3, diaryDelegate, z, z2, diaryFragmentBase, i, diaryAdapterItemActionListener, lazy);
        this.dailyGoal = mfpDailyGoal;
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    /* access modifiers changed from: protected */
    public RecyclerViewHolder<DiaryAdapterItem> getSectionHeaderViewHolder(ViewGroup viewGroup) {
        return new SectionHeaderLandscapeViewHolder(viewGroup);
    }

    /* access modifiers changed from: protected */
    public RecyclerViewHolder<DiaryAdapterItem> getEntryViewHolder(ViewGroup viewGroup) {
        return new EntryLandscapeViewHolder(viewGroup);
    }

    /* access modifiers changed from: protected */
    public RecyclerViewHolder<DiaryAdapterItem> getLandscapeFooterViewHolder(ViewGroup viewGroup) {
        return new DiaryLandscapeFooterViewHolder(viewGroup);
    }

    /* access modifiers changed from: private */
    public void checkForNegativeAndSetTextAndSize(TextView textView, float f, int i) {
        textView.setText(getNutrientValueString(f));
        textView.setTextSize(2, (float) i);
    }

    /* access modifiers changed from: private */
    public String getNutrientValueString(float f) {
        return f < BitmapDescriptorFactory.HUE_RED ? getContext().getString(R.string.dash) : NumberUtils.localeStringFromDoubleNoDecimal((double) f);
    }
}
