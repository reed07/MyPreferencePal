package com.myfitnesspal.feature.progress.ui.dialog;

import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.event.MeasurementTypeChangeEvent;
import com.myfitnesspal.feature.progress.model.MeasurementTypeItem;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.service.steps.StepService;
import com.myfitnesspal.shared.ui.dialog.SingleChoiceListDialogWithCustomAdapterAndButton;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import java.util.List;
import javax.inject.Inject;

public class MeasurementTypeDialogFragment extends SingleChoiceListDialogWithCustomAdapterAndButton<MeasurementTypeItem> {
    private static final String EXTRA_SELECTED_ITEM = "selected_item";
    @Inject
    Lazy<DbConnectionManager> dbConnectionManager;
    private String initialSelection;
    private List<MeasurementTypeItem> items;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<StepService> stepServices;

    public static MeasurementTypeDialogFragment newInstance(String str) {
        MeasurementTypeDialogFragment measurementTypeDialogFragment = new MeasurementTypeDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_SELECTED_ITEM, str);
        measurementTypeDialogFragment.setArguments(bundle);
        return measurementTypeDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.initialSelection = getArguments().getString(EXTRA_SELECTED_ITEM);
        this.items = ((DbConnectionManager) this.dbConnectionManager.get()).measurementTypesDbAdapter().getMeasurementTypesForUserId(getSession().getUser().getLocalId());
        if (((StepService) this.stepServices.get()).shouldTrackSteps()) {
            this.items.add(1, new MeasurementTypeItem(1, Measurement.STEPS));
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getArguments().putString(EXTRA_SELECTED_ITEM, ((MeasurementTypeItem) this.items.get(getCurrentSelectedItemIndex())).getDescription());
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getContext().getString(R.string.measurements);
    }

    /* access modifiers changed from: protected */
    public List<MeasurementTypeItem> getItems() {
        return this.items;
    }

    /* access modifiers changed from: protected */
    public int getInitialSelectedItemIndex() {
        for (int i = 0; i < this.items.size(); i++) {
            if (((MeasurementTypeItem) this.items.get(i)).getDescription().equals(this.initialSelection)) {
                return i;
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public String getTextFromItem(MeasurementTypeItem measurementTypeItem) {
        return ((LocalizedStringsUtil) this.localizedStringsUtil.get()).getLocalizedMeasurementName(measurementTypeItem.getDescription());
    }

    /* access modifiers changed from: protected */
    public void onOkClicked(MeasurementTypeItem measurementTypeItem) {
        this.messageBus.post(new MeasurementTypeChangeEvent(measurementTypeItem));
    }
}
