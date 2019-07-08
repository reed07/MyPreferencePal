package com.myfitnesspal.feature.progress.ui.activity;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.MenuItemCompat;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.alexainterstitial.ui.activity.AlexaInterstitialActivity;
import com.myfitnesspal.feature.alexainterstitial.ui.activity.AlexaInterstitialActivity.Mode;
import com.myfitnesspal.feature.images.util.ImageUploadUtil;
import com.myfitnesspal.feature.permissions.PermissionsMixin;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.feature.progress.constants.ProgressEntryPoint;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService;
import com.myfitnesspal.feature.progress.task.AddPhotoToMediaStoreTask;
import com.myfitnesspal.feature.progress.task.AddPhotoToMediaStoreTask.CompletedEvent;
import com.myfitnesspal.feature.userapplicationsettings.service.UserApplicationSettingsService;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.SharedConstants.RequestCodes;
import com.myfitnesspal.shared.event.DeletePhotoEvent;
import com.myfitnesspal.shared.event.StartCameraEvent;
import com.myfitnesspal.shared.event.StartMediaChooserEvent;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.localsettings.LocalSettingsService;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.ImageChooserDialogFragment;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.DecimalDigitsInputFilter;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.myfitnesspal.shared.util.UpdateWeightProxy;
import com.myfitnesspal.shared.util.UpdateWeightProxy.FinishMode;
import com.myfitnesspal.shared.util.UpdateWeightProxy.Listener;
import com.myfitnesspal.shared.util.UpdateWeightProxy.UpdateMode;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.IntentUtil;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;
import kotlin.jvm.functions.Function0;

public class AddWeightActivity extends MfpActivity {
    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
    public static final String EXTRA_ENTRY_POINT = "extra_entry_point";
    private static final String EXTRA_IMAGE_CAPTURE_URI = "extra_image_capture_uri";
    private static final String EXTRA_IMPORT_TYPE = "extra_import_type";
    public static final String EXTRA_INITIAL_IMPORT_DEVICE = "extra_initial_import_device";
    public static final String EXTRA_WEIGHT_UPDATED = "extra_weight_updated";
    private static final int IMPORT_CONFIRMATION = 101;
    private static final int SAVE_ACTION_ITEM = 100;
    private static final String TEMP_IMAGE_DEFAULT_FILENAME = "mfp-progress-photo-image-capture.jpg";
    @Inject
    Lazy<ConfigService> configService;
    private Context context;
    @BindView(2131363335)
    EditText etProgressWeight;
    @BindView(2131363336)
    EditText etProgressWeightLbs;
    private Uri imageCaptureUri;
    private Intent imagePickerResult;
    private ImportDevice importType;
    private ImportDevice importTypeFromIntent;
    private Calendar initialDate;
    @BindView(2131363331)
    ImageView ivProgressImage;
    @Inject
    Lazy<LocalSettingsService> localSettingsService;
    @Inject
    Lazy<PremiumService> premiumService;
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;
    @Inject
    Lazy<ProgressCongratsService> progressCongrats;
    private String progressImageUri;
    private ImageChooserDialogFragment progressPhotoDialog;
    private OnClickListener requestFocusListener = new OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.row_weight /*2131363517*/:
                    AddWeightActivity.this.etProgressWeight.requestFocus();
                    break;
                case R.id.row_weight_lbs /*2131363518*/:
                    AddWeightActivity.this.etProgressWeightLbs.requestFocus();
                    break;
            }
            AddWeightActivity.this.getImmHelper().showSoftInput();
        }
    };
    private Calendar selectedDate;
    private ViewState state = ViewState.Normal;
    @BindView(2131363328)
    TextView tvProgressDate;
    @BindView(2131364217)
    TextView tvWeightWithUnit;
    @Inject
    Lazy<UserApplicationSettingsService> userApplicationSettingsService;
    @Inject
    Lazy<UserWeightService> userWeightService;
    @BindView(2131363517)
    View vRowWeight;
    @BindView(2131363518)
    View vRowWeightLbs;
    @BindView(2131363602)
    View vSeparatorWeight;
    private String[] weightBasedOnType;
    private Weight weightUnit = Weight.POUNDS;
    private LocalizedWeight weightValue = LocalizedWeight.fromPounds(0.0d);

    private enum ViewState {
        Normal,
        Photo
    }

    public String getAnalyticsScreenTag() {
        return "add_weight";
    }

    public static Intent newStartIntent(Context context2, ProgressEntryPoint progressEntryPoint, ImportDevice importDevice) {
        return new Intent(context2, AddWeightActivity.class).putExtra(EXTRA_INITIAL_IMPORT_DEVICE, importDevice).putExtra(EXTRA_ENTRY_POINT, progressEntryPoint);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        ProgressEntryPoint progressEntryPoint = (ProgressEntryPoint) BundleUtils.getSerializable(getIntent().getExtras(), EXTRA_ENTRY_POINT, ProgressEntryPoint.Unknown, ProgressEntryPoint.class.getClassLoader());
        if (bundle == null) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportWeightEntryScreenView(progressEntryPoint);
        }
        this.context = getApplicationContext();
        setContentView((int) R.layout.add_weight_activity);
        initInstanceState(bundle);
        initCurrentState();
        initViews();
        if (!((LocalSettingsService) this.localSettingsService.get()).hasProgressPhotosIntroBeenDisplayed()) {
            getNavigationHelper().withIntent(ProgressPhotosInterstitialActivity.newStartIntent(this)).finishActivityAfterNavigation().startActivity();
        } else if (bundle == null) {
            startInitialImportDevice();
        }
        setResult(0);
        EditText editText = this.etProgressWeight;
        editText.setSelection(0, editText.getText().length());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getImmHelper().hideSoftInput();
    }

    private void startInitialImportDevice() {
        switch (this.importTypeFromIntent) {
            case Camera:
                checkPermissions();
                return;
            case Picker:
                startPickerImport();
                return;
            default:
                return;
        }
    }

    private void initCurrentState() {
        this.importTypeFromIntent = (ImportDevice) BundleUtils.getSerializable(getIntent().getExtras(), EXTRA_INITIAL_IMPORT_DEVICE, ImportDevice.None, ImportDevice.class.getClassLoader());
        this.weightUnit = ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit();
        this.weightValue.setValue(Weight.POUNDS, (double) ((UserWeightService) this.userWeightService.get()).getCurrentWeightInPounds());
        this.weightBasedOnType = ((UserWeightService) this.userWeightService.get()).getWeight(this.weightUnit, WeightType.CURRENT, BitmapDescriptorFactory.HUE_RED);
        this.selectedDate = Calendar.getInstance();
        this.initialDate = Calendar.getInstance();
    }

    private void initInstanceState(Bundle bundle) {
        this.imageCaptureUri = (Uri) BundleUtils.getParcelable(bundle, EXTRA_IMAGE_CAPTURE_URI, Uri.class.getClassLoader());
        this.importType = (ImportDevice) BundleUtils.getSerializable(bundle, EXTRA_IMPORT_TYPE, this.importType, ImportDevice.class.getClassLoader());
    }

    private void initViews() {
        String str;
        boolean z = this.weightUnit == Weight.STONES || this.weightUnit == Weight.STONES_POUNDS;
        TextView textView = this.tvWeightWithUnit;
        if (z) {
            str = getString(R.string.stones);
        } else {
            str = getString(R.string.weight_with_units, new Object[]{LocalizedWeight.getAbbreviatedUnit(this.context, this.weightUnit)});
        }
        textView.setText(str);
        this.etProgressWeight.setText(String.valueOf(this.weightBasedOnType[0]));
        EditText editText = this.etProgressWeight;
        editText.setSelection(editText.getText().length());
        InputFilter[] inputFilterArr = {new DecimalDigitsInputFilter(4, 1)};
        this.etProgressWeight.setFilters(inputFilterArr);
        if (z) {
            ViewUtils.setVisible(true, this.vRowWeightLbs, this.vSeparatorWeight);
            this.etProgressWeightLbs.setText(this.weightBasedOnType[1]);
            this.etProgressWeightLbs.setFilters(inputFilterArr);
            this.vRowWeightLbs.setOnClickListener(this.requestFocusListener);
        }
        this.vRowWeight.setOnClickListener(this.requestFocusListener);
        boolean hasWeightBeenEnteredForToday = hasWeightBeenEnteredForToday();
        if (hasWeightBeenEnteredForToday) {
            setTitle(getString(R.string.progress_photos_import_photo));
        }
        if (!hasWeightBeenEnteredForToday) {
            this.etProgressWeight.post(new Runnable() {
                public final void run() {
                    AddWeightActivity.lambda$initViews$0(AddWeightActivity.this);
                }
            });
        }
        this.tvProgressDate.setText(DATE_FORMAT.format(this.selectedDate.getTime()));
        this.tvProgressDate.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddWeightActivity.this.launchDatePicker();
            }
        });
        this.ivProgressImage.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                AddWeightActivity.lambda$initViews$2(AddWeightActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initViews$0(AddWeightActivity addWeightActivity) {
        addWeightActivity.etProgressWeight.requestFocus();
        addWeightActivity.getImmHelper().showSoftInput();
    }

    public static /* synthetic */ void lambda$initViews$2(AddWeightActivity addWeightActivity, View view) {
        addWeightActivity.progressPhotoDialog = ImageChooserDialogFragment.newInstance(R.string.progress_photo, addWeightActivity.progressImageUri != null);
        addWeightActivity.progressPhotoDialog.show(addWeightActivity.getSupportFragmentManager(), Fragments.PROGRESS_PHOTO_DIALOG);
    }

    private boolean hasWeightBeenEnteredForToday() {
        ImportDevice importDevice = this.importTypeFromIntent;
        return (importDevice == null || importDevice == ImportDevice.None) ? false : true;
    }

    /* access modifiers changed from: private */
    public void launchDatePicker() {
        getImmHelper().hideSoftInput();
        showDatePicker(new OnDateSetListener() {
            public final void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                AddWeightActivity.lambda$launchDatePicker$3(AddWeightActivity.this, datePicker, i, i2, i3);
            }
        });
    }

    public static /* synthetic */ void lambda$launchDatePicker$3(AddWeightActivity addWeightActivity, DatePicker datePicker, int i, int i2, int i3) {
        addWeightActivity.selectedDate.set(1, i);
        addWeightActivity.selectedDate.set(2, i2);
        addWeightActivity.selectedDate.set(5, i3);
        addWeightActivity.tvProgressDate.setText(DATE_FORMAT.format(addWeightActivity.selectedDate.getTime()));
    }

    private void showDatePicker(OnDateSetListener onDateSetListener) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, this.selectedDate.get(1), this.selectedDate.get(2), this.selectedDate.get(5));
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.getDatePicker().setMinDate(0);
        datePickerDialog.show();
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.save).setIcon(R.drawable.ic_check_white_24dp).setEnabled(true), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 100) {
            onSaveClicked();
        }
        getImmHelper().hideSoftInput();
        return super.onOptionsItemSelected(menuItem);
    }

    private void onSaveClicked() {
        if (this.state != ViewState.Photo) {
            updateWeightAndAssociateImage(null, this.selectedDate);
        } else if (this.importType == ImportDevice.Camera) {
            new AddPhotoToMediaStoreTask(this.progressImageUri).run(getRunner());
        } else {
            updateWeightAndAssociateImage(this.progressImageUri, this.selectedDate);
        }
    }

    @Subscribe
    public void onPhotoAddedToMediaStore(CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            updateWeightAndAssociateImage((String) completedEvent.getResult(), this.selectedDate);
            return;
        }
        Ln.e(completedEvent.getFailure());
        showPhotoTransferFailedDialog();
    }

    private void showPhotoTransferFailedDialog() {
        new MfpAlertDialogBuilder(this).setTitle((int) R.string.app_name).setMessage((int) R.string.progress_photos_photo_transfer_failed).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    private void updateWeightAndAssociateImage(final String str, Calendar calendar) {
        final float weightEntered = getWeightEntered();
        if (weightEntered > BitmapDescriptorFactory.HUE_RED) {
            if (calendar == null) {
                calendar = Calendar.getInstance();
            }
            Calendar calendar2 = calendar;
            final UpdateWeightProxy updateWeightProxy = new UpdateWeightProxy(this, getNavigationHelper(), getMessageBus());
            updateWeightProxy.updateWeightAndPromptForWarnings(weightEntered, calendar2, str, FinishMode.Back, UpdateMode.Weight, new Listener() {
                public void onCancel() {
                }

                public void onSuccess() {
                    AddWeightActivity.this.onWeightUpdated(str, weightEntered, updateWeightProxy);
                }

                public void onNavigatedForward() {
                    AddWeightActivity.this.onWeightUpdated(str, weightEntered, updateWeightProxy);
                }
            });
            return;
        }
        ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getString(R.string.error), getString(R.string.enter_valid_weight), getString(R.string.ok));
    }

    private float getWeightEntered() {
        return ((UserWeightService) this.userWeightService.get()).validateConvertWeight(new String[]{Strings.toString(this.etProgressWeight.getText(), "0"), Strings.toString(this.etProgressWeightLbs.getText(), "0")});
    }

    /* access modifiers changed from: private */
    public void onWeightUpdated(String str, float f, UpdateWeightProxy updateWeightProxy) {
        setResult(-1, new Intent().putExtra(EXTRA_WEIGHT_UPDATED, true));
        if (((ProgressCongratsService) this.progressCongrats.get()).getPendingMessages().size() > 0) {
            getNavigationHelper().withIntent(ProgressCongratsActivity.newStartIntent(this)).startActivity();
        } else if (!updateWeightProxy.getDidPromptForWarnings()) {
            showAlexaInterstitialIfApplicable();
        }
        finish();
        reportImportCompleted();
        reportWeightEntrySaved(str, f);
    }

    private void reportImportCompleted() {
        ((ProgressAnalytics) this.progressAnalytics.get()).reportWeightEntryPhotoImported(DateTimeUtils.isOnSameDayAsDate(this.selectedDate.getTime(), this.initialDate.getTime()), this.selectedDate.getTime());
    }

    private void reportWeightEntrySaved(String str, float f) {
        ((ProgressAnalytics) this.progressAnalytics.get()).reportWeightEntrySaved((ProgressEntryPoint) BundleUtils.getSerializable(getIntent().getExtras(), EXTRA_ENTRY_POINT, ProgressEntryPoint.Unknown, ProgressEntryPoint.class.getClassLoader()), Strings.notEmpty(str), DateTimeUtils.isOnSameDayAsDate(this.selectedDate.getTime(), this.initialDate.getTime()), ((double) f) - this.weightValue.getValue(Weight.POUNDS) != 0.0d);
    }

    @Subscribe
    public void onStartCameraEvent(StartCameraEvent startCameraEvent) {
        this.importType = ImportDevice.Camera;
        checkPermissions();
    }

    @Subscribe
    public void onStartMediaChooserEvent(StartMediaChooserEvent startMediaChooserEvent) {
        this.importType = ImportDevice.Picker;
        startPickerImport();
    }

    @Subscribe
    public void onDeletePhotoEvent(DeletePhotoEvent deletePhotoEvent) {
        this.importType = ImportDevice.None;
        this.state = ViewState.Normal;
        this.progressImageUri = null;
        loadImage();
    }

    private void startPickerImport() {
        this.state = ViewState.Photo;
        getImmHelper().hideSoftInput();
        startActivityForResult(new Intent("android.intent.action.PICK").setType("image/*"), 1004);
    }

    /* access modifiers changed from: private */
    public void startCameraImport() {
        this.state = ViewState.Photo;
        getImmHelper().hideSoftInput();
        try {
            this.imageCaptureUri = ImageUploadUtil.getCacheShareUriForFile(this, TEMP_IMAGE_DEFAULT_FILENAME);
            getNavigationHelper().withIntent(SharedIntents.newImageCaptureIntent(this.imageCaptureUri)).startActivity(RequestCodes.PICK_FROM_CAMERA);
        } catch (IOException unused) {
            new MfpAlertDialogBuilder(this).setTitle((int) R.string.app_name).setMessage((int) R.string.progress_photos_camera_error_cant_open).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) null).show();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 101) {
            onConfirmedImage(i2, intent);
        } else if (i != 205) {
            switch (i) {
                case RequestCodes.PICK_FROM_CAMERA /*1003*/:
                    onPickFromCameraActivityResult(i2, intent);
                    setImageToView();
                    return;
                case 1004:
                    onPickFromFileActivityResult(i2, intent);
                    return;
                default:
                    return;
            }
        } else {
            checkPermissions();
        }
    }

    private void setImageToView() {
        if (IntentUtil.hasData(this.imagePickerResult)) {
            this.progressImageUri = this.imagePickerResult.getData().toString();
            loadImage();
        }
    }

    private void loadImage() {
        LayoutParams layoutParams = this.ivProgressImage.getLayoutParams();
        boolean z = !Strings.isEmpty(this.progressImageUri);
        layoutParams.width = (int) getResources().getDimension(z ? R.dimen.add_weight_image_width : R.dimen.add_weight_placeholder_width);
        this.ivProgressImage.setLayoutParams(layoutParams);
        this.ivProgressImage.requestLayout();
        this.ivProgressImage.setScaleType(z ? ScaleType.CENTER_CROP : ScaleType.CENTER_INSIDE);
        Glide.with(this.context).load(this.progressImageUri).apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).error(R.drawable.ic_camera)).into(this.ivProgressImage);
    }

    private void onPickFromFileActivityResult(int i, Intent intent) {
        if (i == -1) {
            getNavigationHelper().withIntent(AddWeightImageImportActivity.newStartIntent(this, intent.getData())).startActivity(101);
        }
    }

    private void onPickFromCameraActivityResult(int i, Intent intent) {
        if (i == -1) {
            if (!IntentUtil.hasData(intent)) {
                intent = new Intent().setData(this.imageCaptureUri);
            }
            this.imagePickerResult = intent;
            this.imageCaptureUri = null;
            this.importType = ImportDevice.Camera;
        }
    }

    private void onConfirmedImage(int i, Intent intent) {
        if (i == -1) {
            this.imagePickerResult = intent;
            this.importType = ImportDevice.Picker;
            setImageToView();
            return;
        }
        startPickerImport();
    }

    private void showAlexaInterstitialIfApplicable() {
        if (ConfigUtils.isAlexaInterstitialEnabled((ConfigService) this.configService.get()) && !((UserApplicationSettingsService) this.userApplicationSettingsService.get()).didSeeAlexaInterstitialForLogWeight()) {
            getNavigationHelper().withIntent(AlexaInterstitialActivity.newInstance(this, Mode.LOG_WEIGHT)).startActivity();
        }
    }

    private void checkPermissions() {
        ((PermissionsMixin) mixin(PermissionsMixin.class)).checkPermissions(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}, new Function0() {
            public final Object invoke() {
                return AddWeightActivity.this.startCameraImport();
            }
        }, new Function0() {
            public final Object invoke() {
                return AddWeightActivity.this.showPermissionDeniedDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showPermissionDeniedDialog() {
        new Handler().post(new Runnable() {
            public final void run() {
                ((PermissionsMixin) AddWeightActivity.this.mixin(PermissionsMixin.class)).showPermissionDeniedDialog(R.string.permission_settings_camera_storage, null);
            }
        });
    }
}
