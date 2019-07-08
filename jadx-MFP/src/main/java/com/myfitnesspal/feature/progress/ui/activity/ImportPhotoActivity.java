package com.myfitnesspal.feature.progress.ui.activity;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.ImportSource;
import com.myfitnesspal.feature.progress.task.AssociateMeasurementImageTask;
import com.myfitnesspal.feature.progress.task.AssociateMeasurementImageTask.CompletedEvent;
import com.myfitnesspal.feature.progress.ui.fragment.ImportPhotoFragment;
import com.myfitnesspal.feature.progress.ui.fragment.ImportPhotoFragment.DatePickerMode;
import com.myfitnesspal.feature.progress.ui.fragment.ImportPhotoFragment.UiChangedListener;
import com.myfitnesspal.shared.service.measurements.MeasurementsService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.IntentUtil;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.Calendar;
import javax.inject.Inject;

public class ImportPhotoActivity extends MfpActivity {
    public static final String EXTRA_DATE = "extra_date";
    public static final String EXTRA_IMPORT_SOURCE = "import_source";
    public static final String EXTRA_RESOURCE_ID = "resource_id";
    public static final String EXTRA_RESOURCE_SUBTYPE = "resource_subtype";
    public static final String EXTRA_RESOURCE_TYPE = "resource_type";
    public static final String EXTRA_RESOURCE_UID = "resource_uid";
    private static final String FRAGMENT_TAG = "import_photo_fragment";
    private static final int SAVE_MENU_ID = 1001;
    private ImportPhotoFragment fragment;
    private Intent imagePickerResult;
    @Inject
    Lazy<MeasurementsService> measurementsService;
    private long resourceId;
    private String resourceSubType;
    private String resourceType;
    private String resourceUid;
    private ImportSource source;
    private UiChangedListener uiChangedListener = new UiChangedListener() {
        public void onUiChanged(ImportPhotoFragment importPhotoFragment) {
            ImportPhotoActivity.this.invalidateOptionsMenu();
        }
    };

    public static Intent newStartIntent(Context context, ImportSource importSource, long j, String str, String str2, String str3, Calendar calendar) {
        return new Intent(context, ImportPhotoActivity.class).putExtra(EXTRA_IMPORT_SOURCE, importSource).putExtra("resource_id", j).putExtra("resource_uid", str).putExtra("resource_type", str2).putExtra(EXTRA_RESOURCE_SUBTYPE, str3).putExtra("extra_date", calendar.getTimeInMillis());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        Bundle extras = getIntent().getExtras();
        this.resourceId = extras.getLong("resource_id", -1);
        this.resourceUid = extras.getString("resource_uid", null);
        this.resourceType = extras.getString("resource_type", null);
        this.resourceSubType = extras.getString(EXTRA_RESOURCE_SUBTYPE, null);
        Ln.d("onCreate resourceId=%s, resourceUid=%s", Long.valueOf(this.resourceId), this.resourceUid);
        this.source = (ImportSource) BundleUtils.getSerializable(extras, EXTRA_IMPORT_SOURCE, ImportSource.None, ImportSource.class.getClassLoader());
        setContentView((int) R.layout.progress_photos_import_photo_activity);
        initFragment(bundle);
        setResult(0);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        checkShowImagePicker();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1004) {
            if (i2 == -1) {
                this.imagePickerResult = intent;
            } else {
                finish();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        boolean onPrepareOptionsMenu = super.onPrepareOptionsMenu(menu);
        if (Strings.notEmpty(this.fragment.getPhotoUri())) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1001, 0, R.string.save).setIcon(R.drawable.ic_check_white_24dp), 2);
        }
        return onPrepareOptionsMenu;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return super.onOptionsItemSelected(menuItem);
        }
        associateImage();
        return true;
    }

    @Subscribe
    public void onImageAssociated(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setBusy(false);
            if (completedEvent.successful()) {
                setResult(-1);
                this.fragment.onPhotoConsumed();
                finish();
                return;
            }
            Ln.e(completedEvent.getFailure());
            new MfpAlertDialogBuilder(this).setTitle((int) R.string.app_name).setMessage((int) R.string.progress_photos_photo_transfer_failed).setPositiveButton((int) R.string.ok, (OnClickListener) null).show();
        }
    }

    private void associateImage() {
        setBusy(true);
        AssociateMeasurementImageTask associateMeasurementImageTask = new AssociateMeasurementImageTask(this.fragment.getPhotoUri(), this.resourceSubType, this.resourceId, this.resourceUid, this.fragment.getPhotoDate(), this.measurementsService);
        associateMeasurementImageTask.setDedupeMode(DedupeMode.UseExisting).run(getRunner());
    }

    private void checkShowImagePicker() {
        Intent intent = this.imagePickerResult;
        if (intent != null) {
            if (IntentUtil.hasData(intent)) {
                this.fragment.setPhotoUri(this.imagePickerResult.getData().toString());
                this.imagePickerResult = null;
            } else {
                finish();
                return;
            }
        }
        if (Strings.isEmpty(this.fragment.getPhotoUri())) {
            startActivityForResult(new Intent("android.intent.action.PICK").setType("image/*"), 1004);
        }
    }

    private void initFragment(Bundle bundle) {
        if (bundle == null) {
            Calendar instance = Calendar.getInstance();
            long j = BundleUtils.getLong(getIntent().getExtras(), "extra_date", -1);
            if (j != -1) {
                instance.setTimeInMillis(j);
            }
            this.fragment = ImportPhotoFragment.newInstance(this.source, null, instance);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, this.fragment, FRAGMENT_TAG).commit();
        } else {
            this.fragment = (ImportPhotoFragment) getSupportFragmentManager().findFragmentByTag(FRAGMENT_TAG);
        }
        this.fragment.setDatePickerMode(DatePickerMode.Disabled);
        this.fragment.setUiChangedListener(this.uiChangedListener);
        invalidateOptionsMenu();
    }
}
