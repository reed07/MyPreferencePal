package com.myfitnesspal.feature.nutrition.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.nutrition.model.CalorieValues;
import com.myfitnesspal.feature.nutrition.model.CustomCalorieGoalLegend;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.shared.model.LegendData;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class CaloriePieLegend extends CustomPieLegend {
    @BindView(2131361958)
    ViewGroup bottomRows;
    private CalorieValues calorieValues;
    private int chartSubType;
    private String chartType;
    private int clickedIndex;
    private int currentIndex;
    private List<CustomCalorieGoalLegend> customCalorieGoalLegendList;
    private boolean isWeekly;
    private LayoutInflater layoutInflater;
    @BindView(2131362905)
    TableLayout legendTable;
    private final int legendTablePadding;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private NutrientEntry nutrientEntry;
    /* access modifiers changed from: private */
    public int nutrientIndex;
    private OnClickListener onGoalItemClickListener = new OnClickListener() {
        public void onClick(View view) {
            CaloriePieLegend.this.pieLegendListener.onGoalClicked(CaloriePieLegend.this.nutrientIndex);
        }
    };
    /* access modifiers changed from: private */
    public LegendListener pieLegendListener;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public CaloriePieLegend(Context context, String str, int i, boolean z, NutrientEntry nutrientEntry2, CalorieValues calorieValues2, List<CustomCalorieGoalLegend> list, int i2, ViewGroup viewGroup) {
        super(context, viewGroup);
        this.layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        ButterKnife.bind((Object) this, this.layoutInflater.inflate(R.layout.calorie_pie_legend, viewGroup, true));
        MyFitnessPalApp.getInstance().component().inject(this);
        this.currentIndex = Integer.MIN_VALUE;
        this.clickedIndex = Integer.MIN_VALUE;
        this.chartType = str;
        this.chartSubType = i;
        this.isWeekly = z;
        this.nutrientEntry = nutrientEntry2;
        this.calorieValues = calorieValues2;
        this.customCalorieGoalLegendList = list;
        this.nutrientEntry = nutrientEntry2;
        this.nutrientIndex = i2;
        this.legendTablePadding = getResources().getDimensionPixelSize(R.dimen.legend_center_margin);
    }

    public void registerListener(LegendListener legendListener) {
        this.pieLegendListener = legendListener;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00eb  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x0151  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0162  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setLegendData(java.util.List<com.myfitnesspal.shared.model.LegendData> r9) {
        /*
            r8 = this;
            android.view.ViewGroup r0 = r8.bottomRows
            r0.removeAllViews()
            r0 = 1
            android.view.View[] r1 = new android.view.View[r0]
            android.view.ViewGroup r2 = r8.bottomRows
            r3 = 0
            r1[r3] = r2
            com.uacf.core.util.ViewUtils.setVisible(r1)
            java.lang.String r1 = r8.chartType
            int r2 = r1.hashCode()
            r4 = -104321242(0xfffffffff9c82f26, float:-1.2992696E35)
            if (r2 == r4) goto L_0x002b
            r4 = 1933126767(0x73392c6f, float:1.4670962E31)
            if (r2 == r4) goto L_0x0021
            goto L_0x0035
        L_0x0021:
            java.lang.String r2 = "SingleNutrient"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0035
            r1 = 0
            goto L_0x0036
        L_0x002b:
            java.lang.String r2 = "Calories"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x0035
            r1 = 1
            goto L_0x0036
        L_0x0035:
            r1 = -1
        L_0x0036:
            r2 = 2131887992(0x7f120778, float:1.9410607E38)
            r4 = 2131886961(0x7f120371, float:1.9408516E38)
            r5 = 0
            switch(r1) {
                case 0: goto L_0x00eb;
                case 1: goto L_0x004a;
                default: goto L_0x0040;
            }
        L_0x0040:
            android.view.ViewGroup r1 = r8.bottomRows
            r2 = 8
            r1.setVisibility(r2)
            r1 = 1
            goto L_0x014f
        L_0x004a:
            boolean r1 = r8.isWeekly
            if (r1 == 0) goto L_0x00ae
            int r1 = r8.chartSubType
            r2 = 0
            switch(r1) {
                case 1: goto L_0x0082;
                case 2: goto L_0x0055;
                default: goto L_0x0054;
            }
        L_0x0054:
            goto L_0x00ab
        L_0x0055:
            com.myfitnesspal.feature.nutrition.model.CalorieValues r1 = r8.calorieValues
            float r1 = r1.getGoal()
            com.myfitnesspal.feature.nutrition.model.CalorieValues r4 = r8.calorieValues
            float r4 = r4.getNet()
            float r1 = r1 - r4
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0068
            r2 = 1
            goto L_0x0069
        L_0x0068:
            r2 = 0
        L_0x0069:
            java.lang.String r4 = "net_under_weekly_goal_new"
            java.lang.String r6 = "net_over_weekly_goal_new"
            r8.addGoalDiffBottomItem(r2, r4, r6, r1)
            r1 = 2131888430(0x7f12092e, float:1.9411495E38)
            com.myfitnesspal.feature.nutrition.model.CalorieValues r2 = r8.calorieValues
            float r2 = r2.getNetAverage()
            r8.addAverageBottomItem(r1, r2)
            r8.addCaloriesBottomItem()
            r1 = 0
            goto L_0x014f
        L_0x0082:
            com.myfitnesspal.feature.nutrition.model.CalorieValues r1 = r8.calorieValues
            float r1 = r1.getGoal()
            com.myfitnesspal.feature.nutrition.model.CalorieValues r6 = r8.calorieValues
            float r6 = r6.getTotal()
            float r1 = r1 - r6
            int r2 = (r1 > r2 ? 1 : (r1 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0095
            r2 = 1
            goto L_0x0096
        L_0x0095:
            r2 = 0
        L_0x0096:
            java.lang.String r6 = "total_under_weekly_goal"
            java.lang.String r7 = "total_over_weekly_goal"
            r8.addGoalDiffBottomItem(r2, r6, r7, r1)
            com.myfitnesspal.feature.nutrition.model.CalorieValues r1 = r8.calorieValues
            float r1 = r1.getTotalAverage()
            r8.addAverageBottomItem(r4, r1)
            r8.addCaloriesBottomItem()
        L_0x00ab:
            r1 = 1
            goto L_0x014f
        L_0x00ae:
            java.lang.String r1 = "total"
            java.lang.String r1 = r8.getLocalizedString(r1)
            com.myfitnesspal.feature.nutrition.model.CalorieValues r4 = r8.calorieValues
            float r4 = r4.getTotal()
            java.lang.String r4 = r8.roundedStringFromFloat(r4)
            r8.addBottomItem(r1, r4)
            java.lang.String r1 = "net_weekly"
            java.lang.String r1 = r8.getLocalizedString(r1)
            com.myfitnesspal.feature.nutrition.model.CalorieValues r4 = r8.calorieValues
            float r4 = r4.getNet()
            java.lang.String r4 = r8.roundedStringFromFloat(r4)
            r8.addBottomItem(r1, r4)
            java.lang.String[] r1 = new java.lang.String[r3]
            java.lang.String r1 = r8.getString(r2, r1)
            com.myfitnesspal.feature.nutrition.model.CalorieValues r2 = r8.calorieValues
            float r2 = r2.getGoal()
            java.lang.String r2 = r8.roundedStringFromFloat(r2)
            android.view.View r5 = r8.addBottomItem(r1, r2, r0)
            r1 = 1
            goto L_0x014f
        L_0x00eb:
            boolean r1 = r8.isWeekly
            if (r1 != 0) goto L_0x0141
            int r1 = r8.nutrientIndex
            if (r1 == 0) goto L_0x0117
            r1 = 2131889373(0x7f120cdd, float:1.9413408E38)
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]
            com.myfitnesspal.feature.nutrition.model.NutrientEntry r5 = r8.nutrientEntry
            java.lang.String r5 = r5.getFieldLabel()
            r4[r3] = r5
            com.myfitnesspal.feature.nutrition.model.NutrientEntry r5 = r8.nutrientEntry
            java.lang.String r5 = r5.getUnit()
            r4[r0] = r5
            java.lang.String r1 = r8.getString(r1, r4)
            com.myfitnesspal.feature.nutrition.model.NutrientEntry r4 = r8.nutrientEntry
            java.lang.String r4 = r4.getTotal()
            r8.addBottomItem(r1, r4)
            goto L_0x0127
        L_0x0117:
            java.lang.String r1 = "total"
            java.lang.String r1 = r8.getLocalizedString(r1)
            com.myfitnesspal.feature.nutrition.model.NutrientEntry r4 = r8.nutrientEntry
            java.lang.String r4 = r4.getTotal()
            r8.addBottomItem(r1, r4)
        L_0x0127:
            com.myfitnesspal.feature.nutrition.model.NutrientEntry r1 = r8.nutrientEntry
            java.lang.String r1 = r1.getGoal()
            java.lang.String r4 = "\\D+"
            java.lang.String r5 = ""
            java.lang.String r1 = r1.replaceAll(r4, r5)
            java.lang.String[] r4 = new java.lang.String[r3]
            java.lang.String r2 = r8.getString(r2, r4)
            android.view.View r5 = r8.addBottomItem(r2, r1, r0)
            r1 = 1
            goto L_0x014f
        L_0x0141:
            com.myfitnesspal.feature.nutrition.model.CalorieValues r1 = r8.calorieValues
            float r1 = r1.getTotalAverage()
            r8.addAverageBottomItem(r4, r1)
            android.view.View r5 = r8.addGoalBottomItem()
            r1 = 1
        L_0x014f:
            if (r5 == 0) goto L_0x0156
            android.view.View$OnClickListener r2 = r8.onGoalItemClickListener
            r5.setOnClickListener(r2)
        L_0x0156:
            if (r1 != 0) goto L_0x0162
            android.view.View[] r9 = new android.view.View[r0]
            android.widget.TableLayout r0 = r8.legendTable
            r9[r3] = r0
            com.uacf.core.util.ViewUtils.setVisible(r3, r9)
            return
        L_0x0162:
            android.view.View[] r0 = new android.view.View[r0]
            android.widget.TableLayout r1 = r8.legendTable
            r0[r3] = r1
            com.uacf.core.util.ViewUtils.setVisible(r0)
        L_0x016b:
            int r0 = com.uacf.core.util.CollectionUtils.size(r9)
            if (r3 >= r0) goto L_0x018a
            android.widget.TableRow r0 = new android.widget.TableRow
            android.content.Context r1 = r8.getContext()
            r0.<init>(r1)
            android.widget.TableLayout r1 = r8.legendTable
            r1.addView(r0)
            r8.addLegendCellToTableRow(r9, r3, r0)
            int r1 = r3 + 1
            r8.addLegendCellToTableRow(r9, r1, r0)
            int r3 = r3 + 2
            goto L_0x016b
        L_0x018a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.nutrition.ui.view.CaloriePieLegend.setLegendData(java.util.List):void");
    }

    private void addLegendCellToTableRow(List<LegendData> list, int i, TableRow tableRow) {
        View view;
        if (CollectionUtils.size((Collection<?>) list) > i) {
            LegendData legendData = (LegendData) list.get(i);
            view = this.layoutInflater.inflate(R.layout.chart_legend_label, tableRow, false);
            View findById = ViewUtils.findById(view, R.id.legend);
            TextView textView = (TextView) ViewUtils.findById(view, R.id.tvSubtitle);
            ((TextView) ViewUtils.findById(view, R.id.tvLabel)).setText(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(legendData.getLabel(), (UserEnergyService) this.userEnergyService.get()));
            textView.setText(getContext().getString(R.string.legend_calorie_percentage_calorie, new Object[]{Integer.valueOf(legendData.getValuePercentage()), legendData.getActualData()}));
            findById.setBackgroundDrawable(getLegendDrawable(legendData.getLabelColor()));
        } else {
            view = new View(getContext());
        }
        tableRow.addView(view);
        if (i % 2 == 0) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.setMargins(layoutParams.leftMargin, layoutParams.topMargin, layoutParams.rightMargin + this.legendTablePadding, layoutParams.bottomMargin);
            view.setLayoutParams(layoutParams);
        }
    }

    private void addCaloriesBottomItem() {
        if (CollectionUtils.isEmpty((Collection<?>) this.customCalorieGoalLegendList)) {
            addGoalBottomItem().setOnClickListener(this.onGoalItemClickListener);
            return;
        }
        for (CustomCalorieGoalLegend customCalorieGoalLegend : this.customCalorieGoalLegendList) {
            addBottomItem(customCalorieGoalLegend.getGoalDays(), customCalorieGoalLegend.getGoalValue(), true).setOnClickListener(this.onGoalItemClickListener);
        }
    }

    private View addGoalDiffBottomItem(boolean z, String str, String str2, float f) {
        if (!z) {
            str = str2;
        }
        return addBottomItem(getLocalizedString(str), roundedStringFromFloat(Math.abs(f)));
    }

    private View addAverageBottomItem(int i, float f) {
        return addBottomItem(getString(i, new String[0]), roundedStringFromFloat(f));
    }

    private View addGoalBottomItem() {
        return addBottomItem(getString(R.string.goalTxt, new String[0]), roundedStringFromFloat(this.calorieValues.getGoal() / 7.0f), true);
    }

    private String roundedStringFromFloat(float f) {
        return NumberUtils.localeStringFromInt(Math.round(f));
    }

    private String getLocalizedString(String str) {
        return ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedString(str, this.userEnergyService.get());
    }

    private View addBottomItem(String str, String str2) {
        return addBottomItem(str, str2, false);
    }

    private View addBottomItem(String str, String str2, boolean z) {
        View inflate = this.layoutInflater.inflate(R.layout.graph_legend_bottom_item, this.bottomRows, false);
        TextView textView = (TextView) ViewUtils.findById(inflate, R.id.tvValue);
        TextView textView2 = (TextView) ViewUtils.findById(inflate, R.id.tvGoalValue);
        ((TextView) ViewUtils.findById(inflate, R.id.tvLabel)).setText(str);
        textView.setText(str2);
        textView2.setText(str2);
        ViewUtils.setVisible(!z, textView);
        ViewUtils.setVisible(z, textView2);
        this.bottomRows.addView(inflate);
        inflate.setClickable(z);
        return inflate;
    }

    private void setBackgrounds(int i) {
        this.currentIndex = i;
    }

    public void onSeriesClicked(int i) {
        if (this.currentIndex != i) {
            setBackgrounds(i);
        }
    }

    public void onSeriesClear() {
        this.currentIndex = Integer.MIN_VALUE;
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public int getClickedIndex() {
        return this.clickedIndex;
    }
}
