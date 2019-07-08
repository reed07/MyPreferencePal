package com.myfitnesspal.feature.progress.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.images.service.ImageAssociationService;
import com.myfitnesspal.feature.progress.constants.ImportSource;
import com.myfitnesspal.feature.progress.ui.activity.ImportPhotoActivity;
import com.myfitnesspal.feature.progress.util.ProgressPhotosUtil;
import com.myfitnesspal.framework.taskrunner.TaskBase;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextResImageItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.Measurements;
import com.uacf.taskrunner.Task;
import dagger.Lazy;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

public class ProgressEntryLongPressDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_IMAGE_ASSOCIATION_LOCAL_ID = "extra_image_association_local_id";
    private static final String EXTRA_MEASUREMENT_DATE = "extra_measurement_date";
    private static final String EXTRA_MEASUREMENT_LOCAL_ID = "extra_measurement_local_id";
    private static final String EXTRA_MEASUREMENT_TYPE = "extra_measurement_type";
    private static final String EXTRA_MEASUREMENT_UID = "extra_measurement_local_uid";
    private static final int ITEM_ID_DELETE_ENTRY = 3;
    private static final int ITEM_ID_DELETE_PHOTO = 2;
    private static final int ITEM_ID_REPLACE_PHOTO = 1;
    private static final DateFormat TITLE_FORMATTER = DateFormat.getDateInstance(2);
    private long associationId;
    @Inject
    Lazy<ImageAssociationService> imageAssociationService;
    private Date measurementDate;
    private long measurementId;
    @Inject
    Lazy<MeasurementsService> measurementService;
    private String measurementType;
    private String measurementUid;
    private OnDismissListener onDismissListener;

    private static final class DeleteResourceTask extends TaskBase<Boolean, RuntimeException> {
        private ImageAssociationService imageAssociationService;
        private long measurementId;
        private MeasurementsService measurementService;
        private String measurementUid;
        private DeleteType type;

        public enum DeleteType {
            Measurement,
            Association
        }

        public DeleteResourceTask(MeasurementsService measurementsService, ImageAssociationService imageAssociationService2, long j, String str, DeleteType deleteType) {
            this.measurementService = measurementsService;
            this.imageAssociationService = imageAssociationService2;
            this.measurementId = j;
            this.measurementUid = str;
            this.type = deleteType;
        }

        /* access modifiers changed from: protected */
        public Boolean exec(Context context) {
            if (this.type == DeleteType.Association) {
                this.imageAssociationService.markForRemoteDisassociation(this.measurementId, this.measurementUid, "measurement");
            } else if (this.type == DeleteType.Measurement) {
                this.measurementService.markForRemoteDeletion(this.measurementId);
            } else {
                throw new RuntimeException("unrecognized resource type");
            }
            return Boolean.valueOf(true);
        }
    }

    public static boolean supportsMeasurementType(String str) {
        return Measurements.isWeight(str) || Measurements.isLength(str);
    }

    public static ProgressEntryLongPressDialogFragment newInstance(String str, ProgressEntryViewModel progressEntryViewModel) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_MEASUREMENT_TYPE, str);
        bundle.putLong(EXTRA_IMAGE_ASSOCIATION_LOCAL_ID, progressEntryViewModel.getImageAssociationLocalId());
        bundle.putLong(EXTRA_MEASUREMENT_DATE, progressEntryViewModel.getDate().getTime());
        bundle.putLong(EXTRA_MEASUREMENT_LOCAL_ID, progressEntryViewModel.getMeasurementId());
        bundle.putString(EXTRA_MEASUREMENT_UID, progressEntryViewModel.getMeasurementUid());
        ProgressEntryLongPressDialogFragment progressEntryLongPressDialogFragment = new ProgressEntryLongPressDialogFragment();
        progressEntryLongPressDialogFragment.setArguments(bundle);
        return progressEntryLongPressDialogFragment;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener2) {
        this.onDismissListener = onDismissListener2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        Bundle arguments = getArguments();
        this.measurementType = arguments.getString(EXTRA_MEASUREMENT_TYPE);
        this.associationId = arguments.getLong(EXTRA_IMAGE_ASSOCIATION_LOCAL_ID);
        this.measurementId = arguments.getLong(EXTRA_MEASUREMENT_LOCAL_ID);
        this.measurementUid = arguments.getString(EXTRA_MEASUREMENT_UID);
        this.measurementDate = new Date(arguments.getLong(EXTRA_MEASUREMENT_DATE));
    }

    public Dialog onCreateDialog(Bundle bundle) {
        final ArrayList arrayList = new ArrayList();
        if (ProgressPhotosUtil.measurementTypeSupportsImageAssociations(this.measurementType)) {
            arrayList.add(new DialogListTextResImageItem(this.associationId > 0 ? R.string.progress_photos_entry_replace_photo : R.string.progress_photos_entry_import_photo, R.drawable.ic_collections_black_24dp, 1));
        }
        if (this.associationId > 0) {
            arrayList.add(new DialogListTextResImageItem(R.string.progress_photos_entry_delete_photo, R.drawable.ic_delete_black_24dp, 2));
        }
        arrayList.add(new DialogListTextResImageItem(R.string.progress_photos_entry_delete_entry, R.drawable.ic_delete_black_24dp, 3));
        return new MfpAlertDialogBuilder(getActivity()).setTitle((CharSequence) TITLE_FORMATTER.format(this.measurementDate)).setItems(arrayList, new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ProgressEntryLongPressDialogFragment.this.onDialogItemClicked(((DialogListTextResImageItem) arrayList.get(i)).getItemId());
            }
        }).setDismissOnItemClick(false).create();
    }

    /* access modifiers changed from: private */
    public void onDialogItemClicked(int i) {
        if (i == 1) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(this.measurementDate);
            getNavigationHelper().withIntent(ImportPhotoActivity.newStartIntent(getActivity(), ImportSource.ProgressScreen, this.measurementId, this.measurementUid, "measurement", this.measurementType, instance)).startActivity(180);
            notifyAndDismiss();
            return;
        }
        DeleteResourceTask deleteResourceTask = new DeleteResourceTask((MeasurementsService) this.measurementService.get(), (ImageAssociationService) this.imageAssociationService.get(), this.measurementId, this.measurementUid, i == 2 ? DeleteType.Association : DeleteType.Measurement);
        deleteResourceTask.run(getRunner());
    }

    public void onTaskCompleted(String str, long j, Task task, Object obj) {
        notifyAndDismiss();
    }

    private void notifyAndDismiss() {
        OnDismissListener onDismissListener2 = this.onDismissListener;
        if (onDismissListener2 != null) {
            onDismissListener2.onDismiss(getDialog());
        }
        dismiss();
    }
}
