package com.myfitnesspal.feature.fileexport.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.fileexport.constant.ReportingPeriod;
import com.myfitnesspal.shared.model.CheckableListItem;
import com.myfitnesspal.shared.ui.adapter.DialogSingleChoiceAdapter;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment.OnDateSelectedListener;
import com.myfitnesspal.shared.util.Toaster;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FileExportReportingPeriodSelectionDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_FROM_DATE = "from_date";
    private static final String EXTRA_SELECTED_REPORTING_PERIOD = "selected_reporting_period";
    private static final String EXTRA_TO_DATE = "to_date";
    public static final String TAG = "reporting_period_selection_dialog";
    /* access modifiers changed from: private */
    public ReportingPeriod currentSelectedReportingPeriod;
    @BindView(2131362261)
    View customDatesContainer;
    /* access modifiers changed from: private */
    public Date fromDate;
    @BindView(2131362689)
    EditText fromDateEditText;
    /* access modifiers changed from: private */
    public final OnDateSelectedListener onFromDateSelectedListener = new OnDateSelectedListener() {
        public void onDateSelected(Calendar calendar) {
            Date time = calendar.getTime();
            if (FileExportReportingPeriodSelectionDialogFragment.this.toDate == null || !FileExportReportingPeriodSelectionDialogFragment.this.toDate.before(time)) {
                FileExportReportingPeriodSelectionDialogFragment.this.fromDate = time;
                FileExportReportingPeriodSelectionDialogFragment fileExportReportingPeriodSelectionDialogFragment = FileExportReportingPeriodSelectionDialogFragment.this;
                fileExportReportingPeriodSelectionDialogFragment.setDateInEditText(fileExportReportingPeriodSelectionDialogFragment.fromDate, FileExportReportingPeriodSelectionDialogFragment.this.fromDateEditText);
                return;
            }
            Toaster.showShort((Activity) FileExportReportingPeriodSelectionDialogFragment.this.getActivity(), (int) R.string.start_date_after_end_date);
        }
    };
    /* access modifiers changed from: private */
    public OnPeriodSelectedListener onPeriodSelectedListener;
    /* access modifiers changed from: private */
    public final OnDateSelectedListener onToDateSelectedListener = new OnDateSelectedListener() {
        public void onDateSelected(Calendar calendar) {
            Date time = calendar.getTime();
            if (FileExportReportingPeriodSelectionDialogFragment.this.fromDate == null || !FileExportReportingPeriodSelectionDialogFragment.this.fromDate.after(time)) {
                FileExportReportingPeriodSelectionDialogFragment.this.toDate = calendar.getTime();
                FileExportReportingPeriodSelectionDialogFragment fileExportReportingPeriodSelectionDialogFragment = FileExportReportingPeriodSelectionDialogFragment.this;
                fileExportReportingPeriodSelectionDialogFragment.setDateInEditText(fileExportReportingPeriodSelectionDialogFragment.toDate, FileExportReportingPeriodSelectionDialogFragment.this.toDateEditText);
                return;
            }
            Toaster.showShort((Activity) FileExportReportingPeriodSelectionDialogFragment.this.getActivity(), (int) R.string.end_date_before_start_date);
        }
    };
    @BindView(2131363457)
    ListView reportingSelectionListView;
    /* access modifiers changed from: private */
    public Date toDate;
    @BindView(2131363844)
    EditText toDateEditText;

    public interface OnPeriodSelectedListener {
        void onPeriodSelected(ReportingPeriod reportingPeriod, Date date, Date date2);
    }

    public static FileExportReportingPeriodSelectionDialogFragment newInstance(ReportingPeriod reportingPeriod, Date date, Date date2) {
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_SELECTED_REPORTING_PERIOD, reportingPeriod.ordinal());
        if (date != null) {
            bundle.putLong(EXTRA_FROM_DATE, date.getTime());
        }
        if (date2 != null) {
            bundle.putLong(EXTRA_TO_DATE, date2.getTime());
        }
        FileExportReportingPeriodSelectionDialogFragment fileExportReportingPeriodSelectionDialogFragment = new FileExportReportingPeriodSelectionDialogFragment();
        fileExportReportingPeriodSelectionDialogFragment.setArguments(bundle);
        return fileExportReportingPeriodSelectionDialogFragment;
    }

    public void setOnPeriodSelectedListener(OnPeriodSelectedListener onPeriodSelectedListener2) {
        this.onPeriodSelectedListener = onPeriodSelectedListener2;
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(EXTRA_SELECTED_REPORTING_PERIOD, this.currentSelectedReportingPeriod.ordinal());
        Date date = this.fromDate;
        if (date != null) {
            bundle.putLong(EXTRA_FROM_DATE, date.getTime());
        }
        Date date2 = this.toDate;
        if (date2 != null) {
            bundle.putLong(EXTRA_TO_DATE, date2.getTime());
        }
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        final ReportingPeriod[] values = ReportingPeriod.values();
        Context context = getContext();
        View inflate = LayoutInflater.from(context).inflate(R.layout.file_export_reporting_period_selection_dialog, null);
        ButterKnife.bind((Object) this, inflate);
        this.currentSelectedReportingPeriod = values[BundleUtils.getInt(bundle == null ? getArguments() : bundle, EXTRA_SELECTED_REPORTING_PERIOD)];
        this.fromDate = extractDateFromBundleOrArgument(bundle, EXTRA_FROM_DATE);
        this.toDate = extractDateFromBundleOrArgument(bundle, EXTRA_TO_DATE);
        setDateInEditText(this.fromDate, this.fromDateEditText);
        setDateInEditText(this.toDate, this.toDateEditText);
        final ArrayList arrayList = new ArrayList(values.length);
        int length = values.length;
        for (int i = 0; i < length; i++) {
            ReportingPeriod reportingPeriod = values[i];
            arrayList.add(new CheckableListItem(getString(reportingPeriod.getStringResId()), reportingPeriod == this.currentSelectedReportingPeriod));
        }
        toggleCustomDatesContainerVisibility();
        final DialogSingleChoiceAdapter dialogSingleChoiceAdapter = new DialogSingleChoiceAdapter(context, arrayList);
        this.reportingSelectionListView.setAdapter(dialogSingleChoiceAdapter);
        this.reportingSelectionListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((CheckableListItem) arrayList.get(FileExportReportingPeriodSelectionDialogFragment.this.getCurrentSelectedIndex())).setState(false);
                ((CheckableListItem) arrayList.get(i)).setState(true);
                dialogSingleChoiceAdapter.notifyDataSetChanged();
                FileExportReportingPeriodSelectionDialogFragment.this.currentSelectedReportingPeriod = values[i];
                FileExportReportingPeriodSelectionDialogFragment.this.toggleCustomDatesContainerVisibility();
            }
        });
        this.fromDateEditText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FileExportReportingPeriodSelectionDialogFragment fileExportReportingPeriodSelectionDialogFragment = FileExportReportingPeriodSelectionDialogFragment.this;
                fileExportReportingPeriodSelectionDialogFragment.onCustomDateInputClick(fileExportReportingPeriodSelectionDialogFragment.fromDate, FileExportReportingPeriodSelectionDialogFragment.this.onFromDateSelectedListener);
            }
        });
        this.toDateEditText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                FileExportReportingPeriodSelectionDialogFragment fileExportReportingPeriodSelectionDialogFragment = FileExportReportingPeriodSelectionDialogFragment.this;
                fileExportReportingPeriodSelectionDialogFragment.onCustomDateInputClick(fileExportReportingPeriodSelectionDialogFragment.toDate, FileExportReportingPeriodSelectionDialogFragment.this.onToDateSelectedListener);
            }
        });
        return new MfpAlertDialogBuilder(context).setTitle((int) R.string.date_range).setView(inflate).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (FileExportReportingPeriodSelectionDialogFragment.this.currentSelectedReportingPeriod == ReportingPeriod.CustomDates && (FileExportReportingPeriodSelectionDialogFragment.this.fromDate == null || FileExportReportingPeriodSelectionDialogFragment.this.toDate == null)) {
                    int i2 = (FileExportReportingPeriodSelectionDialogFragment.this.fromDate == null && FileExportReportingPeriodSelectionDialogFragment.this.toDate == null) ? R.string.start_end_date_cannot_be_empty : FileExportReportingPeriodSelectionDialogFragment.this.fromDate == null ? R.string.start_date_cannot_be_empty : R.string.end_date_cannot_be_empty;
                    Toaster.showShort((Activity) FileExportReportingPeriodSelectionDialogFragment.this.getActivity(), i2);
                    return;
                }
                FileExportReportingPeriodSelectionDialogFragment.this.onPeriodSelectedListener.onPeriodSelected(FileExportReportingPeriodSelectionDialogFragment.this.currentSelectedReportingPeriod, FileExportReportingPeriodSelectionDialogFragment.this.fromDate, FileExportReportingPeriodSelectionDialogFragment.this.toDate);
            }
        }).setNegativeButton((int) R.string.cancel, (DialogInterface.OnClickListener) null).create();
    }

    private Date extractDateFromBundleOrArgument(Bundle bundle, String str) {
        long j = BundleUtils.getLong(bundle, str, BundleUtils.getLong(getArguments(), str));
        if (j > 0) {
            return new Date(j);
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void toggleCustomDatesContainerVisibility() {
        ViewUtils.setVisible(this.currentSelectedReportingPeriod == ReportingPeriod.CustomDates, this.customDatesContainer);
    }

    /* access modifiers changed from: private */
    public int getCurrentSelectedIndex() {
        ReportingPeriod[] values = ReportingPeriod.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i] == this.currentSelectedReportingPeriod) {
                return i;
            }
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public void onCustomDateInputClick(Date date, OnDateSelectedListener onDateSelectedListener) {
        Calendar instance = Calendar.getInstance();
        if (date != null) {
            instance.setTime(date);
        }
        DatePickerFragment newInstance = DatePickerFragment.newInstance(instance);
        newInstance.setListener(onDateSelectedListener);
        newInstance.show(getFragmentManager(), DatePickerFragment.class.getName());
    }

    /* access modifiers changed from: private */
    public void setDateInEditText(Date date, EditText editText) {
        if (date != null) {
            editText.setText(new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()).format(date));
        }
    }
}
