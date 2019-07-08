package com.myfitnesspal.feature.fileexport.ui.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class FileExportReportingPeriodSelectionDialogFragment_ViewBinding implements Unbinder {
    private FileExportReportingPeriodSelectionDialogFragment target;

    @UiThread
    public FileExportReportingPeriodSelectionDialogFragment_ViewBinding(FileExportReportingPeriodSelectionDialogFragment fileExportReportingPeriodSelectionDialogFragment, View view) {
        this.target = fileExportReportingPeriodSelectionDialogFragment;
        fileExportReportingPeriodSelectionDialogFragment.reportingSelectionListView = (ListView) Utils.findRequiredViewAsType(view, R.id.reporting_selection_list, "field 'reportingSelectionListView'", ListView.class);
        fileExportReportingPeriodSelectionDialogFragment.customDatesContainer = Utils.findRequiredView(view, R.id.custom_dates_container, "field 'customDatesContainer'");
        fileExportReportingPeriodSelectionDialogFragment.fromDateEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.from_date, "field 'fromDateEditText'", EditText.class);
        fileExportReportingPeriodSelectionDialogFragment.toDateEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.to_date, "field 'toDateEditText'", EditText.class);
    }

    @CallSuper
    public void unbind() {
        FileExportReportingPeriodSelectionDialogFragment fileExportReportingPeriodSelectionDialogFragment = this.target;
        if (fileExportReportingPeriodSelectionDialogFragment != null) {
            this.target = null;
            fileExportReportingPeriodSelectionDialogFragment.reportingSelectionListView = null;
            fileExportReportingPeriodSelectionDialogFragment.customDatesContainer = null;
            fileExportReportingPeriodSelectionDialogFragment.fromDateEditText = null;
            fileExportReportingPeriodSelectionDialogFragment.toDateEditText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
