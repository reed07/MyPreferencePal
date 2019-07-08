package com.myfitnesspal.feature.nutrition.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.nutrition.event.GoalsClickedEvent;
import com.myfitnesspal.feature.nutrition.event.LegendClickedEvent;
import com.myfitnesspal.feature.nutrition.model.CalorieValues;
import com.myfitnesspal.feature.nutrition.model.NutrientEntry;
import com.myfitnesspal.feature.nutrition.service.ChartLegendFactory;
import com.myfitnesspal.shared.constants.Constants.Graphs.Colors;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.myfitnesspal.shared.model.LegendData;
import com.shinobicontrols.charts.ChartView;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.PieDonutSeries.RadialEffect;
import com.shinobicontrols.charts.PieSeries;
import com.shinobicontrols.charts.PieSeriesStyle;
import com.shinobicontrols.charts.Series;
import com.shinobicontrols.charts.Series.SelectionMode;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.ShinobiChart.OnSeriesSelectionListener;
import com.shinobicontrols.charts.SimpleDataAdapter;
import com.squareup.otto.Bus;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class CustomPieChart extends CustomChartBase implements LegendListener, OnSeriesSelectionListener {
    private static final float MIN_VALUE_TO_SHOW = 14.0f;
    @Inject
    Lazy<ChartLegendFactory> coreChartLegendFactory;
    private CalorieValues dailyCalorieValues;
    private int[] defaultColorsRes;
    @Inject
    Lazy<Bus> messageBus;
    private ShinobiChart pieChart;
    private CustomPieLegend pieLegend;

    public void onSeriesSelectionStateChanged(Series<?> series) {
    }

    public CustomPieChart(Context context, CalorieValues calorieValues, String str, List<LegendData> list, ViewGroup viewGroup) {
        this(context, calorieValues, str, list, -1, null, viewGroup);
    }

    public CustomPieChart(Context context, CalorieValues calorieValues, String str, List<LegendData> list, int i, NutrientEntry nutrientEntry, ViewGroup viewGroup) {
        super(context, viewGroup);
        init(str, nutrientEntry, calorieValues, i, viewGroup);
        setChartData(list);
    }

    private void init(String str, NutrientEntry nutrientEntry, CalorieValues calorieValues, int i, ViewGroup viewGroup) {
        MyFitnessPalApp.getInstance().component().inject(this);
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.custom_pie_chart, viewGroup, true);
        ViewGroup viewGroup2 = (ViewGroup) findViewById(R.id.legend);
        if (!Strings.equals(str, Types.MACROS)) {
            this.defaultColorsRes = Colors.MEAL_COLORS_RES;
        } else {
            this.defaultColorsRes = Colors.MACROS_COLORS_RES;
        }
        this.dailyCalorieValues = calorieValues;
        this.pieLegend = ((ChartLegendFactory) this.coreChartLegendFactory.get()).getPieChartRenderer(getContext(), str, 1, false, nutrientEntry, calorieValues, null, i, viewGroup2);
        this.pieLegend.registerListener(this);
    }

    private void setChartData(List<LegendData> list) {
        this.pieLegend.setLegendData(list);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        this.chartView = (ChartView) findViewById(R.id.pie_container);
        this.pieChart = this.chartView.getShinobiChart();
        this.pieChart.setOnSeriesSelectionListener(this);
        float[] mealPercentages = this.dailyCalorieValues.getMealPercentages();
        int length = mealPercentages.length;
        boolean z = true;
        for (int i = 0; i < length; i++) {
            if (mealPercentages[i] > BitmapDescriptorFactory.HUE_RED) {
                z = false;
            }
        }
        SimpleDataAdapter simpleDataAdapter = new SimpleDataAdapter();
        PieSeries pieSeries = new PieSeries();
        if (!z) {
            for (float abs : this.dailyCalorieValues.getMealPercentages()) {
                float abs2 = Math.abs(abs);
                simpleDataAdapter.add(new DataPoint(getSliceLabel(abs2), Float.valueOf(abs2)));
            }
            pieSeries.setSelectionMode(SelectionMode.NONE);
        } else {
            simpleDataAdapter.add(new DataPoint(getSliceLabel(BitmapDescriptorFactory.HUE_RED), Float.valueOf(100.0f)));
            this.defaultColorsRes = Colors.EMPTY_COLORS_RES;
            pieSeries.setSelectionMode(SelectionMode.NONE);
        }
        pieSeries.setDataAdapter(simpleDataAdapter);
        pieSeries.setSelectedPosition(Float.valueOf(BitmapDescriptorFactory.HUE_RED));
        this.pieChart.addSeries(pieSeries);
        this.pieChart.getStyle().setPlotAreaBackgroundColor(getColor(R.color.white));
        int length2 = this.defaultColorsRes.length;
        int[] iArr = new int[length2];
        for (int i2 = 0; i2 < length2; i2++) {
            iArr[i2] = getColor(this.defaultColorsRes[i2]);
        }
        PieSeriesStyle pieSeriesStyle = (PieSeriesStyle) pieSeries.getStyle();
        pieSeriesStyle.setFlavorColors(iArr);
        pieSeriesStyle.setRadialEffect(RadialEffect.FLAT);
        pieSeriesStyle.setCrustShown(false);
        pieSeriesStyle.setLabelTextSize((float) getResources().getInteger(R.integer.pie_chart_label_text_size));
        pieSeriesStyle.setCrustThickness(BitmapDescriptorFactory.HUE_RED);
        PieSeriesStyle pieSeriesStyle2 = (PieSeriesStyle) pieSeries.getSelectedStyle();
        pieSeriesStyle2.setFlavorColors(iArr);
        pieSeriesStyle2.setRadialEffect(RadialEffect.FLAT);
        pieSeriesStyle2.setCrustShown(false);
        pieSeries.setSelectedStyle(pieSeriesStyle2);
    }

    private String getSliceLabel(float f) {
        if (f < MIN_VALUE_TO_SHOW) {
            return " ";
        }
        return getContext().getString(R.string.percent_value, new Object[]{Integer.valueOf(Math.round(f))});
    }

    public void onLegendClicked(int i) {
        ((Bus) this.messageBus.get()).post(new LegendClickedEvent(i));
    }

    public void onGoalClicked(int i) {
        ((Bus) this.messageBus.get()).post(new GoalsClickedEvent(i));
    }

    public void onPointSelectionStateChanged(Series<?> series, int i) {
        if (((Series) this.pieChart.getSeries().get(0)).isPointSelected(i)) {
            this.pieLegend.onSeriesClicked(i);
        } else if (this.pieLegend.getClickedIndex() == i || (this.pieLegend.getCurrentIndex() == i && !((Series) this.pieChart.getSeries().get(0)).isPointSelected(i))) {
            this.pieLegend.onSeriesClear();
        }
    }
}
