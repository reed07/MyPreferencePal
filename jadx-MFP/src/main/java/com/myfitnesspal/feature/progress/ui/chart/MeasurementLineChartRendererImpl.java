package com.myfitnesspal.feature.progress.ui.chart;

import android.content.Context;
import android.view.ViewGroup;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.feature.nutrition.service.CoreRendererBase;
import com.myfitnesspal.feature.nutrition.ui.view.CustomLineChart;
import com.myfitnesspal.feature.progress.constants.GraphPeriod;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.CalendarUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MeasurementLineChartRendererImpl extends CoreRendererBase implements MeasurementLineChartRenderer {
    private CustomLineChart chart;
    private float yAxisIncrement;
    private float yMax;
    private float yMin;

    public MeasurementLineChartRendererImpl(Context context) {
        super(context);
    }

    public CustomLineChart renderLineChart(int i, List<ProgressEntryViewModel> list, ViewGroup viewGroup, Session session) {
        reset();
        List ensureAtLeastTwoItem = ensureAtLeastTwoItem(list, i, session);
        boolean z = i == GraphPeriod.AllTime.getDaysBack(session);
        boolean z2 = z || i == GraphPeriod.StartingWeight.getDaysBack(session);
        if (z) {
            Date date = new Date();
            i = (int) DateTimeUtils.getNumberOfDaysBetween(CollectionUtils.notEmpty((Collection<?>) ensureAtLeastTwoItem) ? ((ProgressEntryViewModel) ensureAtLeastTwoItem.get(0)).getDate() : date, date);
        }
        Calendar instance = Calendar.getInstance();
        int max = Math.max(1, i) * -1;
        Calendar instance2 = Calendar.getInstance();
        instance2.add(5, max);
        Date time = instance2.getTime();
        Date time2 = instance.getTime();
        addLastDateValuesIfRequired(ensureAtLeastTwoItem, time2);
        calculateTickSpacings(time, time2, ensureAtLeastTwoItem);
        CustomLineChart customLineChart = new CustomLineChart(this.activity, ensureAtLeastTwoItem, time, time2, this.yMin, this.yMax, this.yAxisIncrement, z2, viewGroup);
        this.chart = customLineChart;
        return this.chart;
    }

    public void reset() {
        CustomLineChart customLineChart = this.chart;
        if (customLineChart != null) {
            customLineChart.destroy();
            this.chart = null;
        }
    }

    private void addLastDateValuesIfRequired(List<ProgressEntryViewModel> list, Date date) {
        if (!CollectionUtils.isEmpty((Collection<?>) list)) {
            ProgressEntryViewModel progressEntryViewModel = (ProgressEntryViewModel) CollectionUtils.getLastItemFromList(list);
            Date date2 = progressEntryViewModel.getDate();
            Calendar instance = Calendar.getInstance();
            instance.setTime(date2);
            Calendar instance2 = Calendar.getInstance();
            instance2.setTime(date);
            if (!CalendarUtils.areDatesEqual(instance2, instance)) {
                ProgressEntryViewModel progressEntryViewModel2 = new ProgressEntryViewModel(progressEntryViewModel.getMeasurementId(), progressEntryViewModel.getMeasurementUid(), date, progressEntryViewModel.getNormalizedUnitValue(), progressEntryViewModel.getValue(), progressEntryViewModel.getImageId(), progressEntryViewModel.getImageLocalFilepath(), progressEntryViewModel.getImageAssociationLocalId(), false);
                list.add(progressEntryViewModel2);
            }
        }
    }

    private void calculateTickSpacings(Date date, Date date2, List<ProgressEntryViewModel> list) {
        if (CollectionUtils.isEmpty((Collection<?>) list)) {
            this.yMin = BitmapDescriptorFactory.HUE_RED;
            this.yMax = 100.0f;
            this.yAxisIncrement = 100.0f;
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (ProgressEntryViewModel progressEntryViewModel : list) {
            if (progressEntryViewModel.getDate().equals(date) || progressEntryViewModel.getDate().equals(date2) || (progressEntryViewModel.getDate().after(date) && progressEntryViewModel.getDate().before(date2))) {
                arrayList.add(progressEntryViewModel);
            }
        }
        this.yMin = (float) ((ProgressEntryViewModel) Collections.min(arrayList)).getValue();
        this.yMax = (float) ((ProgressEntryViewModel) Collections.max(arrayList)).getValue();
        float f = this.yMin;
        float f2 = this.yMax;
        if (f > f2) {
            this.yMin = f2;
            this.yMax = f;
        }
        float f3 = this.yMax;
        float f4 = this.yMin;
        if (f3 == f4) {
            this.yMax = f4 + 2.0f;
            this.yMin = f4 - 2.0f;
        }
        float f5 = this.yMax;
        float f6 = this.yMin;
        float f7 = (f5 - f6) * 0.15f;
        this.yMin = f6 - f7;
        this.yMax = f5 + f7;
        calculateYAxisIncrement();
    }

    private void calculateYAxisIncrement() {
        int i = ((((int) ((this.yMax - this.yMin) / 6.0f)) + 4) / 5) * 5;
        if (i == 0) {
            i = 1;
        }
        this.yAxisIncrement = (float) i;
        double d = (double) this.yAxisIncrement;
        float f = this.yMax;
        float f2 = this.yMin;
        if (d > ((double) (f - f2)) / 2.0d) {
            this.yAxisIncrement = (float) ((int) (((double) (f - f2)) / 2.0d));
            if (((double) this.yAxisIncrement) < 1.0d) {
                this.yAxisIncrement = 1.0f;
            }
        }
    }

    private List<ProgressEntryViewModel> ensureAtLeastTwoItem(List<ProgressEntryViewModel> list, int i, Session session) {
        Session session2 = session;
        int i2 = i;
        int daysBack = i2 == GraphPeriod.AllTime.getDaysBack(session2) ? GraphPeriod.OneWeek.getDaysBack(session2) : i2;
        if (list.size() != 1) {
            return list;
        }
        ProgressEntryViewModel progressEntryViewModel = (ProgressEntryViewModel) list.get(0);
        Calendar instance = Calendar.getInstance();
        instance.add(5, -daysBack);
        ProgressEntryViewModel progressEntryViewModel2 = new ProgressEntryViewModel(progressEntryViewModel.getMeasurementId(), progressEntryViewModel.getMeasurementUid(), instance.getTime(), progressEntryViewModel.getNormalizedUnitValue(), progressEntryViewModel.getValue(), progressEntryViewModel.getImageId(), progressEntryViewModel.getImageLocalFilepath(), progressEntryViewModel.getImageAssociationLocalId(), progressEntryViewModel.isShowInList());
        ArrayList arrayList = new ArrayList();
        arrayList.add(progressEntryViewModel2);
        arrayList.add(progressEntryViewModel);
        return arrayList;
    }
}
