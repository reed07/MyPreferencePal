package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.nutrition.ui.view.CustomChartBase;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.shinobicontrols.charts.AxisStyle;
import com.shinobicontrols.charts.ChartStyle;
import com.shinobicontrols.charts.ChartView;
import com.shinobicontrols.charts.ColumnSeries;
import com.shinobicontrols.charts.ColumnSeriesStyle;
import com.shinobicontrols.charts.DataPoint;
import com.shinobicontrols.charts.DateTimeAxis;
import com.shinobicontrols.charts.GridlineStyle;
import com.shinobicontrols.charts.NumberAxis;
import com.shinobicontrols.charts.SeriesStyle.FillStyle;
import com.shinobicontrols.charts.ShinobiChart;
import com.shinobicontrols.charts.SimpleDataAdapter;
import com.shinobicontrols.charts.TickStyle;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StepsBarChart extends CustomChartBase {
    private final Context context;
    private double topPadding;
    private List<Integer> values;
    private List<Date> xLabels;
    private float yAxisIncrement;

    public StepsBarChart(Context context2, ViewGroup viewGroup, List<Date> list, List<Integer> list2, double d, float f) {
        super(context2, viewGroup);
        this.context = context2;
        addChartToContainer(viewGroup);
        setData(list, list2, d, f);
    }

    private void addChartToContainer(ViewGroup viewGroup) {
        viewGroup.removeAllViews();
        ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.steps_bar_chart, viewGroup, true);
    }

    public void setData(List<Date> list, List<Integer> list2, double d, float f) {
        this.xLabels = list;
        this.values = list2;
        this.topPadding = d;
        this.yAxisIncrement = f;
        initChart();
    }

    private void initChart() {
        init();
        this.chartView = (ChartView) findViewById(R.id.bar_container);
        ShinobiChart shinobiChart = this.chartView.getShinobiChart();
        SimpleDataAdapter simpleDataAdapter = new SimpleDataAdapter();
        for (int i = 0; i < this.values.size(); i++) {
            simpleDataAdapter.add(new DataPoint(this.xLabels.get(i), this.values.get(i)));
        }
        DateTimeAxis dateTimeAxis = getDateTimeAxis();
        NumberAxis numberAxis = getNumberAxis();
        int color = this.context.getResources().getColor(R.color.white);
        ChartStyle style = shinobiChart.getStyle();
        style.setPlotAreaBackgroundColor(color);
        style.setBackgroundColor(color);
        shinobiChart.setXAxis(dateTimeAxis);
        shinobiChart.setYAxis(numberAxis);
        shinobiChart.getCrosshair().getStyle().setLineColor(-1);
        shinobiChart.redrawChart();
        ColumnSeries columnSeries = new ColumnSeries();
        columnSeries.setDataAdapter(simpleDataAdapter);
        columnSeries.setStackId(Integer.valueOf(1));
        columnSeries.setCrosshairEnabled(true);
        ColumnSeriesStyle columnSeriesStyle = (ColumnSeriesStyle) columnSeries.getStyle();
        columnSeriesStyle.setAreaColor(Color.argb(255, Statements.GetOwnedFoodIdsDateDescending, RequestCodes.SEARCH_MATCH, 221));
        columnSeriesStyle.setFillStyle(FillStyle.FLAT);
        columnSeriesStyle.setLineColor(Color.argb(255, Statements.GetOwnedFoodIdsDateDescending, RequestCodes.SEARCH_MATCH, 221));
        shinobiChart.addSeries(columnSeries);
        ((NumberAxis) shinobiChart.getYAxis()).setRangePaddingHigh(Double.valueOf(this.topPadding));
    }

    private DateTimeAxis getDateTimeAxis() {
        DateTimeAxis dateTimeAxis = new DateTimeAxis();
        AxisStyle style = dateTimeAxis.getStyle();
        style.setLineWidth(1.0f);
        style.setLineColor(Color.argb(255, 0, 0, 0));
        setTickStyling(style.getTickStyle());
        dateTimeAxis.setLabelFormat(new SimpleDateFormat("MM/dd", Locale.getDefault()));
        return dateTimeAxis;
    }

    private NumberAxis getNumberAxis() {
        NumberAxis numberAxis = new NumberAxis();
        numberAxis.setMajorTickFrequency(Double.valueOf((double) this.yAxisIncrement));
        AxisStyle style = numberAxis.getStyle();
        GridlineStyle gridlineStyle = style.getGridlineStyle();
        gridlineStyle.setGridlinesShown(true);
        gridlineStyle.setLineColor(Color.argb(255, RequestCodes.VIEW_FOOD, RequestCodes.VIEW_FOOD, RequestCodes.VIEW_FOOD));
        setTickStyling(style.getTickStyle());
        return numberAxis;
    }

    private void setTickStyling(TickStyle tickStyle) {
        tickStyle.setLabelColor(Color.argb(255, 102, 102, 102));
        tickStyle.setLabelTextSize((float) this.context.getResources().getInteger(R.integer.barchart_axis_text_size));
        tickStyle.setMajorTicksShown(false);
        tickStyle.setLabelsShown(true);
    }
}
