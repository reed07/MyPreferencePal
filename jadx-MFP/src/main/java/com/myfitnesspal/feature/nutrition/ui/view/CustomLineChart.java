package com.myfitnesspal.feature.nutrition.ui.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.service.syncv1.PacketTypes;
import com.shinobicontrols.charts.AxisStyle;
import com.shinobicontrols.charts.ChartView;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.DateRange;
import com.shinobicontrols.charts.DateTimeAxis;
import com.shinobicontrols.charts.LineSeries;
import com.shinobicontrols.charts.LineSeriesStyle;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.NumberRange;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ViewUtils;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomLineChart extends CustomChartBase {
    private static final int LABEL_COLOR = Color.argb(255, 102, 102, 102);
    private static final int LINE_COLOR = Color.argb(255, 247, PacketTypes.ThirdPartyDissociateResponse, 30);
    private Context context;
    private List<ProgressEntryViewModel> progressEntries;
    private boolean unbounded;
    private Date xMax;
    private Date xMin;
    private float yAxisIncrement;
    private float yMax;
    private float yMin;

    public CustomLineChart(Context context2, List<ProgressEntryViewModel> list, Date date, Date date2, float f, float f2, float f3, boolean z, ViewGroup viewGroup) {
        Context context3 = context2;
        this(context2, viewGroup);
        setDate(list, date, date2, f, f2, f3, z);
    }

    public CustomLineChart(Context context2, ViewGroup viewGroup) {
        super(context2, viewGroup);
        MyFitnessPalApp.getInstance().component().inject(this);
        this.context = context2;
        ((LayoutInflater) context2.getSystemService("layout_inflater")).inflate(R.layout.measurement_line_chart, viewGroup, true);
    }

    public void setDate(List<ProgressEntryViewModel> list, Date date, Date date2, float f, float f2, float f3, boolean z) {
        this.progressEntries = list;
        this.xMin = date;
        this.xMax = date2;
        this.yMin = f;
        this.yMax = f2;
        this.yAxisIncrement = f3;
        this.unbounded = z;
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        super.init();
        boolean notEmpty = CollectionUtils.notEmpty((Collection<?>) this.progressEntries);
        Resources resources = this.context.getResources();
        this.chartView = (ChartView) findViewById(R.id.line_container);
        View findViewById = findViewById(R.id.llNoDataAvailable);
        ShinobiChart shinobiChart = this.chartView.getShinobiChart();
        if (notEmpty) {
            ViewUtils.setVisible(true, this.chartView);
            ViewUtils.setVisible(false, findViewById);
            DateTimeAxis dateTimeAxis = new DateTimeAxis(new DateRange(this.xMin, this.xMax));
            AxisStyle style = dateTimeAxis.getStyle();
            style.setLineWidth(1.0f);
            style.setLineColor(Color.argb(255, 0, 0, 0));
            style.getTickStyle().setLabelColor(LABEL_COLOR);
            style.getTickStyle().setLabelTextSize((float) resources.getInteger(R.integer.line_axis_text_size));
            style.getTickStyle().setMajorTicksShown(false);
            style.getTickStyle().setLabelsShown(true);
            dateTimeAxis.setLabelFormat(new SimpleDateFormat(this.unbounded ? "MM/yy" : "MM/dd", Locale.getDefault()));
            NumberAxis numberAxis = new NumberAxis(new NumberRange(Double.valueOf((double) this.yMin), Double.valueOf((double) this.yMax)));
            numberAxis.setMajorTickFrequency(Double.valueOf((double) this.yAxisIncrement));
            AxisStyle style2 = numberAxis.getStyle();
            style2.getGridlineStyle().setGridlinesShown(true);
            style2.getGridlineStyle().setLineColor(Color.argb(200, RequestCodes.VIEW_FOOD, RequestCodes.VIEW_FOOD, RequestCodes.VIEW_FOOD));
            style2.getTickStyle().setLabelTextSize((float) resources.getInteger(R.integer.line_axis_text_size));
            style2.getTickStyle().setLabelColor(LABEL_COLOR);
            style2.getTickStyle().setLabelTextSize((float) resources.getInteger(R.integer.line_axis_text_size));
            style2.getTickStyle().setMajorTicksShown(false);
            style2.getTickStyle().setLabelsShown(true);
            shinobiChart.getStyle().setPlotAreaBackgroundColor(this.context.getResources().getColor(R.color.white));
            shinobiChart.getStyle().setBackgroundColor(this.context.getResources().getColor(R.color.white));
            shinobiChart.setXAxis(dateTimeAxis);
            shinobiChart.setYAxis(numberAxis);
            LineSeries lineSeries = new LineSeries();
            ((LineSeriesStyle) lineSeries.getStyle()).setLineColor(LINE_COLOR);
            ((LineSeriesStyle) lineSeries.getStyle()).setLineWidth(2.0f);
            SimpleDataAdapter simpleDataAdapter = new SimpleDataAdapter();
            for (ProgressEntryViewModel progressEntryViewModel : this.progressEntries) {
                simpleDataAdapter.add(new DataPoint(progressEntryViewModel.getDate(), Float.valueOf((float) progressEntryViewModel.getValue())));
            }
            lineSeries.setDataAdapter(simpleDataAdapter);
            shinobiChart.addSeries(lineSeries);
            return;
        }
        ViewUtils.setVisible(false, this.chartView);
        ViewUtils.setVisible(true, findViewById);
    }
}
