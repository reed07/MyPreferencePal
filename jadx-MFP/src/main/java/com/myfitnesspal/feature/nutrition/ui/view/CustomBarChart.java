package com.myfitnesspal.feature.nutrition.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.nutrition.event.GoalsClickedEvent;
import com.myfitnesspal.feature.nutrition.event.GraphSubTypeChanged;
import com.myfitnesspal.feature.nutrition.event.LegendClickedEvent;
import com.myfitnesspal.feature.nutrition.model.CalorieValues;
import com.myfitnesspal.feature.nutrition.model.CaloriesAndMacroValues;
import com.myfitnesspal.feature.nutrition.model.CustomCalorieGoalLegend;
import com.myfitnesspal.feature.nutrition.service.ChartLegendFactory;
import com.myfitnesspal.shared.constants.Constants.Graphs.Colors;
import com.myfitnesspal.shared.constants.Constants.Graphs.Types;
import com.myfitnesspal.shared.model.LegendData;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.service.session.Session;
import com.shinobicontrols.charts.Annotation;
import com.shinobicontrols.charts.AnnotationsManager;
import com.shinobicontrols.charts.CategoryAxis;
import com.shinobicontrols.charts.ChartStyle;
import com.shinobicontrols.charts.ChartView;
import com.shinobicontrols.charts.ColumnSeries;
import com.shinobicontrols.charts.ColumnSeriesStyle;
import com.shinobicontrols.charts.DataAdapter;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.NumberRange;
import com.shinobicontrols.charts.Series;
import com.shinobicontrols.charts.Series.SelectionMode;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.ShinobiChart.OnSeriesSelectionListener;
import com.shinobicontrols.charts.SimpleDataAdapter;
import com.shinobicontrols.charts.TickMark.ClippingMode;
import com.squareup.otto.Bus;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class CustomBarChart extends CustomChartBase implements LegendListener, OnSeriesSelectionListener {
    public static final double DEFAULT_TOP_PADDING_PX = 20.0d;
    public static final double NEGATIVE_VALUE_TOOLTIP_START = 100.0d;
    private AnnotationsManager annotationsManager;
    private float avgGoalCalories;
    private CaloriesAndMacroValues avgMacroValues;
    private ShinobiChart barChart;
    private Context context;
    @Inject
    Lazy<ChartLegendFactory> coreChartLegendFactory;
    /* access modifiers changed from: private */
    public View currentlySelectedGraphButton;
    private List<CustomCalorieGoalLegend> customCalorieGoalLegendList;
    private NumberRange customRange;
    private List<CalorieValues> dailyCalorieValuesList;
    private OnClickListener graphSelector;
    private String[] labels;
    private List<LegendData> legendDataList;
    private List<CaloriesAndMacroValues> macroValuesList;
    private MealNames mealNames;
    @Inject
    Lazy<Bus> messageBus;
    private int nutrientIndex;
    @Inject
    Lazy<Session> session;
    private boolean showingTip;
    private List<Annotation> tooltipList;
    private double topPadding;
    private CalorieValues weeklyCalorieValues;
    private double yAxisIncrement;

    public void onSeriesSelectionStateChanged(Series<?> series) {
    }

    public CustomBarChart(Context context2, ViewGroup viewGroup) {
        super(context2, viewGroup);
        this.topPadding = 20.0d;
        this.customRange = null;
        this.nutrientIndex = -1;
        this.graphSelector = new OnClickListener() {
            public void onClick(View view) {
                if (CustomBarChart.this.currentlySelectedGraphButton.getId() != view.getId()) {
                    CustomBarChart.this.selectGraphSelectorButton(view);
                    int i = 1;
                    if (view.getId() == R.id.right_toggle_btn) {
                        i = 2;
                    }
                    CustomBarChart.this.setupChartData(Types.CALORIES, i);
                    ((Bus) CustomBarChart.this.messageBus.get()).post(new GraphSubTypeChanged(i));
                }
            }
        };
        MyFitnessPalApp.getInstance().component().inject(this);
        this.context = context2;
        ((LayoutInflater) context2.getSystemService("layout_inflater")).inflate(R.layout.custom_bar_chart, viewGroup, true);
        this.tooltipList = new ArrayList();
        this.mealNames = ((Session) this.session.get()).getUser().getMealNames();
    }

    public CustomBarChart(Context context2, String[] strArr, List<CalorieValues> list, List<CustomCalorieGoalLegend> list2, CalorieValues calorieValues, List<LegendData> list3, float f, double d, String str, int i, NumberRange numberRange, int i2, ViewGroup viewGroup) {
        this(context2, viewGroup);
        this.customRange = numberRange;
        initValuesForCaloriesOrNutrientBarChart(strArr, list, list2, calorieValues, list3, f, d, str, i, 0.0d, i2);
    }

    public CustomBarChart(Context context2, String[] strArr, List<CaloriesAndMacroValues> list, CaloriesAndMacroValues caloriesAndMacroValues, List<LegendData> list2, double d, double d2, ViewGroup viewGroup) {
        Context context3 = context2;
        this(context2, viewGroup);
        initValuesForMacrosBarChart(strArr, list, caloriesAndMacroValues, list2, d, d2);
    }

    private void initValuesForMacrosBarChart(String[] strArr, List<CaloriesAndMacroValues> list, CaloriesAndMacroValues caloriesAndMacroValues, List<LegendData> list2, double d, double d2) {
        this.labels = strArr;
        this.macroValuesList = list;
        this.avgMacroValues = caloriesAndMacroValues;
        this.yAxisIncrement = d;
        this.legendDataList = list2;
        this.topPadding = d2;
        initChart(Types.MACROS);
        hideGraphSelectorButtons();
    }

    public void initValuesForCaloriesOrNutrientBarChart(String[] strArr, List<CalorieValues> list, List<CustomCalorieGoalLegend> list2, CalorieValues calorieValues, List<LegendData> list3, float f, double d, String str, int i, double d2, int i2) {
        this.labels = strArr;
        this.dailyCalorieValuesList = list;
        this.customCalorieGoalLegendList = list2;
        this.weeklyCalorieValues = calorieValues;
        this.avgGoalCalories = f;
        this.yAxisIncrement = d;
        this.legendDataList = list3;
        this.topPadding = d2;
        this.nutrientIndex = i2;
        initChart(str, i);
        if (Strings.equals(Types.CALORIES, str)) {
            setupGraphSelectorButtons(i);
        } else {
            hideGraphSelectorButtons();
        }
    }

    private void setupGraphSelectorButtons(int i) {
        ViewGroup container = getContainer();
        ViewUtils.setVisible(ViewUtils.findById(container, R.id.custom_toggle_container));
        View findById = ViewUtils.findById(container, R.id.left_toggle_btn);
        View findById2 = ViewUtils.findById(container, R.id.right_toggle_btn);
        findById.setOnClickListener(this.graphSelector);
        findById2.setOnClickListener(this.graphSelector);
        if (i != 2) {
            findById2 = findById;
        }
        selectGraphSelectorButton(findById2);
    }

    private void hideGraphSelectorButtons() {
        ViewUtils.setGone(ViewUtils.findById(getContainer(), R.id.custom_toggle_container));
    }

    private void setupLegend(String str, int i) {
        ViewGroup viewGroup = (ViewGroup) ViewUtils.findById(getContainer(), R.id.legend);
        viewGroup.removeAllViews();
        CustomPieLegend pieChartRenderer = ((ChartLegendFactory) this.coreChartLegendFactory.get()).getPieChartRenderer(this.context, str, i, true, null, this.weeklyCalorieValues, this.customCalorieGoalLegendList, this.nutrientIndex, viewGroup);
        pieChartRenderer.registerListener(this);
        pieChartRenderer.setLegendData(this.legendDataList);
    }

    private void initChartViews() {
        this.chartView = (ChartView) findViewById(R.id.bar_container);
        this.barChart = this.chartView.getShinobiChart();
        this.annotationsManager = this.barChart.getAnnotationsManager();
    }

    private void setChartBackground() {
        ChartStyle style = this.barChart.getStyle();
        style.setPlotAreaBackgroundColor(getResources().getColor(R.color.white));
        style.setBackgroundColor(getResources().getColor(R.color.white));
    }

    private String getAvgString() {
        return getResources().getString(R.string.weekly_graph_average_token);
    }

    private void setXAxis() {
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.getStyle().setLineWidth(1.0f);
        categoryAxis.getStyle().setLineColor(getAxisLineColor());
        categoryAxis.getStyle().getTickStyle().setLabelColor(getAxisLabelColor());
        categoryAxis.getStyle().getTickStyle().setLabelTextSize((float) getAxisLabelTextSize());
        categoryAxis.getStyle().getTickStyle().setMajorTicksShown(false);
        categoryAxis.getStyle().getTickStyle().setLabelsShown(true);
        this.barChart.setXAxis(categoryAxis);
    }

    private void setYAxis() {
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setMajorTickFrequency(Double.valueOf(this.yAxisIncrement));
        numberAxis.setTickMarkClippingModeHigh(ClippingMode.NEITHER_PERSIST);
        numberAxis.setTickMarkClippingModeLow(ClippingMode.NEITHER_PERSIST);
        numberAxis.getStyle().getGridlineStyle().setGridlinesShown(true);
        numberAxis.getStyle().getGridlineStyle().setLineColor(getAxisLineColor());
        numberAxis.getStyle().getTickStyle().setLabelTextSize((float) getAxisLabelTextSize());
        numberAxis.getStyle().getTickStyle().setLabelColor(getAxisLabelColor());
        numberAxis.getStyle().getTickStyle().setMajorTicksShown(false);
        numberAxis.getStyle().getTickStyle().setLabelsShown(true);
        this.barChart.setYAxis(numberAxis);
    }

    private int getAxisLineColor() {
        return getColor(R.color.graph_axis_line);
    }

    private int getAxisLabelColor() {
        return getColor(R.color.black_secondary_text);
    }

    private int getAxisLabelTextSize() {
        return getResources().getInteger(R.integer.bar_graph_label_text_size);
    }

    private List<Tuple2<DataAdapter<String, Double>, Integer>> getChartDataAdapterAndColorList() {
        double d;
        SimpleDataAdapter simpleDataAdapter = new SimpleDataAdapter();
        SimpleDataAdapter simpleDataAdapter2 = new SimpleDataAdapter();
        int i = 0;
        while (true) {
            d = 0.0d;
            if (i >= CollectionUtils.size((Collection<?>) this.dailyCalorieValuesList)) {
                break;
            }
            CalorieValues calorieValues = (CalorieValues) this.dailyCalorieValuesList.get(i);
            double net2 = (double) calorieValues.getNet();
            double goal = (double) calorieValues.getGoal();
            if (goal <= net2) {
                d = net2 - goal;
            }
            simpleDataAdapter.add(new DataPoint(this.labels[i], Double.valueOf(net2 - d)));
            simpleDataAdapter2.add(new DataPoint(this.labels[i], Double.valueOf(d)));
            i++;
        }
        float netAverage = this.weeklyCalorieValues.getNetAverage();
        float f = this.avgGoalCalories;
        if (f <= netAverage) {
            d = (double) (netAverage - f);
        }
        simpleDataAdapter.add(new DataPoint(getAvgString(), Double.valueOf(((double) netAverage) - d)));
        simpleDataAdapter2.add(new DataPoint(getAvgString(), Double.valueOf(d)));
        ArrayList arrayList = new ArrayList();
        arrayList.add(Tuple.create(simpleDataAdapter, Integer.valueOf(getColor(R.color.graph_net_positive))));
        arrayList.add(Tuple.create(simpleDataAdapter2, Integer.valueOf(getColor(R.color.graph_net_negative))));
        return arrayList;
    }

    private List<Tuple2<DataAdapter<String, Double>, Integer>> getMealsChartDataAdapterAndColorList() {
        if (CollectionUtils.isEmpty((Collection<?>) this.dailyCalorieValuesList)) {
            return new ArrayList();
        }
        int size = this.mealNames.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new SimpleDataAdapter());
        }
        for (int i2 = 0; i2 < this.dailyCalorieValuesList.size(); i2++) {
            CalorieValues calorieValues = (CalorieValues) this.dailyCalorieValuesList.get(i2);
            float[] mealPercentages = calorieValues.getMealPercentages();
            for (int i3 = 0; i3 < mealPercentages.length; i3++) {
                ((DataAdapter) arrayList.get(i3)).add(new DataPoint(this.labels[i2], Double.valueOf((double) NumberUtils.getValueFromPercentage(mealPercentages[i3], calorieValues.getTotal()))));
            }
        }
        float[] mealPercentages2 = this.weeklyCalorieValues.getMealPercentages();
        for (int i4 = 0; i4 < mealPercentages2.length; i4++) {
            ((DataAdapter) arrayList.get(i4)).add(new DataPoint(getAvgString(), Double.valueOf((double) NumberUtils.getValueFromPercentage(mealPercentages2[i4], this.weeklyCalorieValues.getTotalAverage()))));
        }
        int length = Colors.MEAL_COLORS_RES.length;
        int[] iArr = new int[length];
        for (int i5 = 0; i5 < length; i5++) {
            iArr[i5] = getColor(Colors.MEAL_COLORS_RES[i5]);
        }
        int size2 = CollectionUtils.size((Collection<?>) arrayList);
        ArrayList arrayList2 = new ArrayList(size2);
        for (int i6 = 0; i6 < size2; i6++) {
            arrayList2.add(Tuple.create(arrayList.get(i6), Integer.valueOf(iArr[i6 % iArr.length])));
        }
        return arrayList2;
    }

    private List<Tuple2<DataAdapter<String, Double>, Integer>> getMacrosChartDataAdapterAndColorList() {
        ArrayList arrayList = new ArrayList(3);
        for (int i = 0; i < 3; i++) {
            arrayList.add(new SimpleDataAdapter());
        }
        for (int i2 = 0; i2 < 7; i2++) {
            addDataPointFromMacro(arrayList, (CaloriesAndMacroValues) this.macroValuesList.get(i2), this.labels[i2]);
        }
        addDataPointFromMacro(arrayList, this.avgMacroValues, getAvgString());
        ArrayList arrayList2 = new ArrayList(3);
        for (int i3 = 0; i3 < 3; i3++) {
            arrayList2.add(Tuple.create(arrayList.get(i3), Integer.valueOf(getColor(Colors.MACROS_COLORS_RES[i3]))));
        }
        return arrayList2;
    }

    private void addDataPointFromMacro(List<DataAdapter<String, Double>> list, CaloriesAndMacroValues caloriesAndMacroValues, String str) {
        for (int i = 0; i < 3; i++) {
            DataAdapter dataAdapter = (DataAdapter) list.get(i);
            float f = BitmapDescriptorFactory.HUE_RED;
            switch (i) {
                case 0:
                    f = caloriesAndMacroValues.getCarbsCaloriesFromDiary();
                    break;
                case 1:
                    f = caloriesAndMacroValues.getFatCaloriesFromDiary();
                    break;
                case 2:
                    f = caloriesAndMacroValues.getProteinCaloriesFromDiary();
                    break;
            }
            dataAdapter.add(new DataPoint(str, Double.valueOf((double) f)));
        }
    }

    private void addSeriesToChart(List<Tuple2<DataAdapter<String, Double>, Integer>> list) {
        for (Tuple2 tuple2 : list) {
            DataAdapter dataAdapter = (DataAdapter) tuple2.getItem1();
            int intValue = ((Integer) tuple2.getItem2()).intValue();
            ColumnSeries columnSeries = new ColumnSeries();
            columnSeries.setDataAdapter(dataAdapter);
            columnSeries.setStackId(Integer.valueOf(1));
            columnSeries.setSelectionMode(SelectionMode.POINT_SINGLE);
            ((ColumnSeriesStyle) columnSeries.getStyle()).setAreaColor(intValue);
            ((ColumnSeriesStyle) columnSeries.getStyle()).setFillStyle(FillStyle.FLAT);
            ((ColumnSeriesStyle) columnSeries.getStyle()).setLineColor(intValue);
            columnSeries.setCrosshairEnabled(false);
            this.barChart.addSeries(columnSeries);
        }
    }

    /* access modifiers changed from: protected */
    public void initChart(String str) {
        initChart(str, 1);
    }

    /* access modifiers changed from: protected */
    public void initChart(String str, int i) {
        super.init();
        initChartViews();
        setChartBackground();
        setXAxis();
        setYAxis();
        this.barChart.getCrosshair().getStyle().setLineColor(-1);
        this.barChart.setOnSeriesSelectionListener(this);
        setupChartData(str, i);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x004c  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0061 A[FALL_THROUGH] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0079  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setupChartData(java.lang.String r7, int r8) {
        /*
            r6 = this;
            r6.setupLegend(r7, r8)
            r6.removeCurrentDataFromGraph()
            int r0 = r7.hashCode()
            r1 = -1997878713(0xffffffff88eaca47, float:-1.4130918E-33)
            r2 = 2
            r3 = 0
            r4 = 1
            if (r0 == r1) goto L_0x0031
            r1 = -104321242(0xfffffffff9c82f26, float:-1.2992696E35)
            if (r0 == r1) goto L_0x0027
            r1 = 1933126767(0x73392c6f, float:1.4670962E31)
            if (r0 == r1) goto L_0x001d
            goto L_0x003b
        L_0x001d:
            java.lang.String r0 = "SingleNutrient"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x003b
            r7 = 2
            goto L_0x003c
        L_0x0027:
            java.lang.String r0 = "Calories"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x003b
            r7 = 0
            goto L_0x003c
        L_0x0031:
            java.lang.String r0 = "Macros"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L_0x003b
            r7 = 1
            goto L_0x003c
        L_0x003b:
            r7 = -1
        L_0x003c:
            switch(r7) {
                case 0: goto L_0x004c;
                case 1: goto L_0x0046;
                case 2: goto L_0x0040;
                default: goto L_0x003f;
            }
        L_0x003f:
            goto L_0x0061
        L_0x0040:
            java.util.List r7 = r6.getMealsChartDataAdapterAndColorList()
            r8 = 1
            goto L_0x0063
        L_0x0046:
            java.util.List r7 = r6.getMacrosChartDataAdapterAndColorList()
            r8 = 0
            goto L_0x0063
        L_0x004c:
            if (r8 != r4) goto L_0x0054
            java.util.List r7 = r6.getMealsChartDataAdapterAndColorList()
            r8 = 0
            goto L_0x0063
        L_0x0054:
            if (r8 != r2) goto L_0x0061
            java.util.List r7 = r6.getChartDataAdapterAndColorList()
            java.util.List<com.myfitnesspal.feature.nutrition.model.CustomCalorieGoalLegend> r8 = r6.customCalorieGoalLegendList
            boolean r8 = com.uacf.core.util.CollectionUtils.isEmpty(r8)
            goto L_0x0063
        L_0x0061:
            r7 = 0
            r8 = 0
        L_0x0063:
            r6.addSeriesToChart(r7)
            com.shinobicontrols.charts.ShinobiChart r7 = r6.barChart
            com.shinobicontrols.charts.Axis r7 = r7.getYAxis()
            com.shinobicontrols.charts.NumberAxis r7 = (com.shinobicontrols.charts.NumberAxis) r7
            double r0 = r6.topPadding
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r7.setRangePaddingHigh(r0)
            if (r8 == 0) goto L_0x00a1
            com.shinobicontrols.charts.AnnotationsManager r8 = r6.annotationsManager
            float r0 = r6.avgGoalCalories
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            r1 = 1073741824(0x40000000, float:2.0)
            com.shinobicontrols.charts.ShinobiChart r2 = r6.barChart
            com.shinobicontrols.charts.Axis r2 = r2.getXAxis()
            com.shinobicontrols.charts.ShinobiChart r5 = r6.barChart
            com.shinobicontrols.charts.Axis r5 = r5.getYAxis()
            com.shinobicontrols.charts.Annotation r8 = r8.addHorizontalLineAnnotation(r0, r1, r2, r5)
            com.shinobicontrols.charts.AnnotationStyle r8 = r8.getStyle()
            r0 = 2131099937(0x7f060121, float:1.7812241E38)
            int r0 = r6.getColor(r0)
            r8.setBackgroundColor(r0)
        L_0x00a1:
            com.shinobicontrols.charts.NumberRange r8 = r6.customRange
            if (r8 == 0) goto L_0x00ba
            r7.allowPanningOutOfDefaultRange(r4)
            r7.allowPanningOutOfMaxRange(r4)
            com.shinobicontrols.charts.NumberRange r8 = r6.customRange
            java.lang.Double r8 = r8.getMinimum()
            com.shinobicontrols.charts.NumberRange r0 = r6.customRange
            java.lang.Double r0 = r0.getMaximum()
            r7.requestCurrentDisplayedRange(r8, r0, r3, r3)
        L_0x00ba:
            com.shinobicontrols.charts.ShinobiChart r7 = r6.barChart
            r7.redrawChart()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.nutrition.ui.view.CustomBarChart.setupChartData(java.lang.String, int):void");
    }

    private void removeCurrentDataFromGraph() {
        for (Series removeSeries : new ArrayList(this.barChart.getSeries())) {
            this.barChart.removeSeries(removeSeries);
        }
        this.annotationsManager.removeAllAnnotations();
    }

    public void onPointSelectionStateChanged(Series<?> series, int i) {
        if (this.showingTip) {
            hideToolTips();
        } else {
            showToolTips();
        }
        this.barChart.redrawChart();
        this.showingTip = !this.showingTip;
    }

    private void showToolTips() {
        for (int i = 0; i < ((Series) this.barChart.getSeries().get(0)).getDataAdapter().size(); i++) {
            Annotation showToolTipForBar = showToolTipForBar(i);
            if (showToolTipForBar != null) {
                this.tooltipList.add(showToolTipForBar);
            }
        }
    }

    private void hideToolTips() {
        for (Annotation view : this.tooltipList) {
            ViewUtils.setVisible(false, view.getView());
        }
        this.tooltipList.clear();
    }

    private Annotation showToolTipForBar(int i) {
        double d = 0.0d;
        for (Series dataAdapter : this.barChart.getSeries()) {
            d += ((Double) dataAdapter.getDataAdapter().get(i).getY()).doubleValue();
        }
        int i2 = (d > 0.0d ? 1 : (d == 0.0d ? 0 : -1));
        if (i2 == 0) {
            return null;
        }
        Annotation addTextAnnotation = this.annotationsManager.addTextAnnotation(NumberUtils.localeStringFromDoubleNoDecimal(d), Integer.valueOf(i), Double.valueOf(i2 > 0 ? d + (this.yAxisIncrement / 10.0d) : 100.0d), this.barChart.getXAxis(), this.barChart.getYAxis());
        addTextAnnotation.getStyle().setTextColor(getColor(R.color.black_text));
        addTextAnnotation.getStyle().setTextSize((float) this.context.getResources().getInteger(R.integer.annotation_text_size));
        addTextAnnotation.getStyle().setBackgroundColor(getColor(R.color.graph_background));
        addTextAnnotation.getView().setVisibility(0);
        return addTextAnnotation;
    }

    public void onLegendClicked(int i) {
        ((Bus) this.messageBus.get()).post(new LegendClickedEvent(i));
    }

    public void onGoalClicked(int i) {
        ((Bus) this.messageBus.get()).post(new GoalsClickedEvent(i));
    }

    /* access modifiers changed from: private */
    public void selectGraphSelectorButton(View view) {
        View view2 = this.currentlySelectedGraphButton;
        if (view2 != null) {
            view2.setSelected(false);
        }
        view.setSelected(true);
        this.currentlySelectedGraphButton = view;
    }
}
