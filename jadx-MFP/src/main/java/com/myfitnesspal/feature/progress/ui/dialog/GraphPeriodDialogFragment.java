package com.myfitnesspal.feature.progress.ui.dialog;

import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.GraphPeriod;
import com.myfitnesspal.feature.progress.event.GraphPeriodChangeEvent;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.dialog.SingleChoiceListDialogWithCustomAdapterAndButton;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;

public class GraphPeriodDialogFragment extends SingleChoiceListDialogWithCustomAdapterAndButton<GraphPeriod> {
    private static final String EXTRA_GRAPH_PERIOD = "graph_period";
    private static final String EXTRA_MEASUREMENT_TYPE = "measurement_type";
    private final ArrayList<GraphPeriod> GRAPH_PERIODS = new ArrayList<>(Arrays.asList(new GraphPeriod[]{GraphPeriod.OneWeek, GraphPeriod.OneMonth, GraphPeriod.TwoMonths, GraphPeriod.ThreeMonths, GraphPeriod.SixMonths, GraphPeriod.OneYear, GraphPeriod.AllTime}));
    @Inject
    Lazy<ConfigService> configService;
    private GraphPeriod initialPeriod;
    private String measurementType;

    public static GraphPeriodDialogFragment newInstance(GraphPeriod graphPeriod, String str) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_GRAPH_PERIOD, graphPeriod);
        bundle.putString("measurement_type", str);
        GraphPeriodDialogFragment graphPeriodDialogFragment = new GraphPeriodDialogFragment();
        graphPeriodDialogFragment.setArguments(bundle);
        return graphPeriodDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.initialPeriod = (GraphPeriod) BundleUtils.getSerializable(getArguments(), EXTRA_GRAPH_PERIOD, GraphPeriod.OneMonth, GraphPeriod.class.getClassLoader());
        this.measurementType = BundleUtils.getString(getArguments(), "measurement_type", Measurement.WEIGHT);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getArguments().putSerializable(EXTRA_GRAPH_PERIOD, (Serializable) this.GRAPH_PERIODS.get(getCurrentSelectedItemIndex()));
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getContext().getString(R.string.progress_graph_period_dialog_title);
    }

    /* access modifiers changed from: protected */
    public List<GraphPeriod> getItems() {
        if (Strings.equals(this.measurementType, Measurement.WEIGHT) && ConfigUtils.isNewStartingWeightOn((ConfigService) this.configService.get())) {
            ArrayList<GraphPeriod> arrayList = this.GRAPH_PERIODS;
            arrayList.add(arrayList.size() - 1, GraphPeriod.StartingWeight);
        }
        return this.GRAPH_PERIODS;
    }

    /* access modifiers changed from: protected */
    public int getInitialSelectedItemIndex() {
        return this.GRAPH_PERIODS.indexOf(this.initialPeriod);
    }

    /* access modifiers changed from: protected */
    public String getTextFromItem(GraphPeriod graphPeriod) {
        return getContext().getString(graphPeriod.getStringId());
    }

    /* access modifiers changed from: protected */
    public void onOkClicked(GraphPeriod graphPeriod) {
        this.messageBus.post(new GraphPeriodChangeEvent(graphPeriod));
    }
}
