package com.myfitnesspal.feature.friends.ui.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.service.StatusAnalytics;
import com.myfitnesspal.feature.friends.service.StatusAnalytics.Source;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.feature.images.util.ImageUploadUtil;
import com.myfitnesspal.feature.permissions.PermissionsMixin;
import com.myfitnesspal.feature.progress.constants.GalleryDataMode;
import com.myfitnesspal.feature.progress.constants.GalleryViewMode;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.feature.progress.task.AddPhotoToMediaStoreTask;
import com.myfitnesspal.feature.progress.task.AddPhotoToMediaStoreTask.CompletedEvent;
import com.myfitnesspal.feature.progress.ui.activity.AddWeightImageImportActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressPhotosGalleryActivity;
import com.myfitnesspal.feature.progress.ui.activity.ProgressPhotosGalleryActivity.ToolbarCta;
import com.myfitnesspal.shared.api.ApiResponseBase;
import com.myfitnesspal.shared.api.v2.MfpV2ApiErrorCallback;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Dialogs.Fragments;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.constants.SharedConstants;
import com.myfitnesspal.shared.event.DeletePhotoEvent;
import com.myfitnesspal.shared.event.ShareProgressPhotoEvent;
import com.myfitnesspal.shared.event.StartCameraEvent;
import com.myfitnesspal.shared.event.StartMediaChooserEvent;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.task.UploadImagePostStatusTask;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.dialog.impl.ImageChooserDialogFragment;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.GlideUtil;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.IntentUtil;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import javax.annotation.Nullable;
import javax.inject.Inject;
import kotlin.Unit;

public class NewStatusOrCommentActivity extends MfpActivity {
    private static final String CAMERA = "camera";
    private static final String EXTRA_ANALYTICS_REPORTED = "extra_analytics_reported";
    private static final String EXTRA_IMAGE_URI = "image_uri";
    private static final String EXTRA_IMPORT_TYPE = "import_type";
    private static final String EXTRA_SHOW_CHOOSER_ON_START = "show_chooser_on_start";
    private static final String GALLERY = "gallery";
    private static final int IMPORT_CONFIRMATION = 101;
    private static final int POST_MENU_ITEM = 100;
    private static final int PROGRESS_DIALOG = 2;
    private static final String PROGRESS_PHOTO = "progress_photo";
    private static final String TEMP_IMAGE_DEFAULT_FILENAME = "mfp-status-photo-image-capture-%d.jpg";
    private static final String UPLOADING_DIALOG_TAG = "uploading_dialog_tag";
    private static final String UPLOAD_IMAGE_FAILED_TAG = "upload_image_failed";
    @Inject
    Lazy<ConfigService> configService;
    private String destinationUserUid;
    final DialogPositiveListener dialogPositiveListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            NewStatusOrCommentActivity newStatusOrCommentActivity = NewStatusOrCommentActivity.this;
            newStatusOrCommentActivity.postStatus(Strings.trimmed((Object) newStatusOrCommentActivity.status.getText()));
        }
    };
    private Uri imageCaptureUri;
    @Inject
    Lazy<ImageService> imageService;
    @Inject
    Lazy<ImageUploadService> imageUploadService;
    private ImportDevice importType;
    private boolean isPostImageToStatusOn;
    private int itemType;
    @Nullable
    @BindView(2131362873)
    ImageView ivStatusPic;
    @Inject
    Lazy<NewsFeedService> newsFeedService;
    private boolean processing;
    @BindView(2131364124)
    View separator;
    private boolean showChooserOnStart;
    @BindView(2131362382)
    EditText status;
    @Inject
    Lazy<StatusAnalytics> statusAnalytics;
    /* access modifiers changed from: private */
    public int statusBoxLineCount = 6;
    private String statusImageUri;
    @BindView(2131363950)
    TextView tvAddProgressPic;

    public String getAnalyticsScreenTag() {
        return Screens.NEW_STATUS_OR_COMMENT;
    }

    public static Intent newStartIntent(Context context, Source source) {
        Intent intent = new Intent(context, NewStatusOrCommentActivity.class);
        intent.putExtra(StatusAnalytics.EXTRA_SOURCE, source);
        return intent;
    }

    public static Intent newStartIntentWithImageChooserVisible(Context context, Source source) {
        return newStartIntent(context, source).putExtra(EXTRA_SHOW_CHOOSER_ON_START, true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setContentView((int) R.layout.new_status_update);
        Intent intent = getIntent();
        this.itemType = ExtrasUtils.getInt(intent, Extras.ITEM_TYPE);
        this.destinationUserUid = ExtrasUtils.getString(intent, Extras.DESTINATION_USER_UID);
        reportViewAnalytics(bundle);
        this.isPostImageToStatusOn = ConfigUtils.isPostImageToStatusOn((ConfigService) this.configService.get());
        this.statusImageUri = BundleUtils.getString(bundle, EXTRA_IMAGE_URI);
        this.showChooserOnStart = BundleUtils.getBoolean(EXTRA_SHOW_CHOOSER_ON_START, Boolean.valueOf(false), bundle, intent.getExtras()).booleanValue();
        this.importType = (ImportDevice) BundleUtils.getSerializable(bundle, EXTRA_IMPORT_TYPE, ImportDevice.class.getClassLoader());
        initUi();
        initEventListeners();
        this.status.requestFocus();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(EXTRA_ANALYTICS_REPORTED, true);
        bundle.putString(EXTRA_IMAGE_URI, this.statusImageUri);
        bundle.putSerializable(EXTRA_IMPORT_TYPE, this.importType);
        bundle.putBoolean(EXTRA_SHOW_CHOOSER_ON_START, this.showChooserOnStart);
    }

    private void reportViewAnalytics(Bundle bundle) {
        if (!BundleUtils.getBoolean(bundle, EXTRA_ANALYTICS_REPORTED)) {
            Source source = (Source) BundleUtils.getSerializable(getIntent().getExtras(), StatusAnalytics.EXTRA_SOURCE, Source.Unknown, StatusAnalytics.class.getClassLoader());
            if (source != Source.Unknown) {
                ((StatusAnalytics) this.statusAnalytics.get()).reportUpdateStatusClicked(source);
            }
            ((StatusAnalytics) this.statusAnalytics.get()).reportStatusUpdateViewed();
        }
    }

    private void initUi() {
        this.tvAddProgressPic.setText(this.isPostImageToStatusOn ? R.string.meal_photos_add_photo_button : R.string.add_progress_photo);
        boolean isPostingStatusForLoggedInUser = isPostingStatusForLoggedInUser();
        boolean z = (ConfigUtils.isProgressPhotosNewsfeedOn((ConfigService) this.configService.get()) || this.isPostImageToStatusOn) && isPostingStatusForLoggedInUser;
        switch (this.itemType) {
            case 17:
                setTitle(isPostingStatusForLoggedInUser ? R.string.update_status : R.string.writeSomething);
                this.status.setHint(getResources().getString(R.string.status_field_hint));
                ViewUtils.setVisible(z, this.tvAddProgressPic);
                ViewUtils.setVisible(z, this.separator);
                break;
            case 18:
                this.status.setHint(getResources().getString(R.string.newCommentHint));
                setTitle(R.string.add_comment);
                break;
        }
        if (this.isPostImageToStatusOn && Strings.notEmpty(this.statusImageUri)) {
            loadImage();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.showChooserOnStart) {
            showImageChooserDialog();
            this.showChooserOnStart = false;
            return;
        }
        getImmHelper().showSoftInput();
    }

    private void initEventListeners() {
        this.status.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                int lineCount = NewStatusOrCommentActivity.this.status.getLineCount();
                if (lineCount > NewStatusOrCommentActivity.this.statusBoxLineCount) {
                    NewStatusOrCommentActivity.this.status.setLines(lineCount);
                } else {
                    NewStatusOrCommentActivity.this.status.setLines(NewStatusOrCommentActivity.this.statusBoxLineCount);
                }
            }
        });
        this.tvAddProgressPic.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                NewStatusOrCommentActivity.lambda$initEventListeners$0(NewStatusOrCommentActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initEventListeners$0(NewStatusOrCommentActivity newStatusOrCommentActivity, View view) {
        newStatusOrCommentActivity.getImmHelper().hideSoftInput();
        if (newStatusOrCommentActivity.isPostImageToStatusOn) {
            ((StatusAnalytics) newStatusOrCommentActivity.statusAnalytics.get()).reportStatusUpdateAddPhotoClicked();
            newStatusOrCommentActivity.showImageChooserDialog();
            return;
        }
        newStatusOrCommentActivity.startShareProgressPicFlow();
    }

    private void startShareProgressPicFlow() {
        ((StatusAnalytics) this.statusAnalytics.get()).reportStatusUpdateAddPhotoOptionClicked(PROGRESS_PHOTO);
        getNavigationHelper().withIntent(ProgressPhotosGalleryActivity.newStartIntent(getApplicationContext(), GalleryViewMode.Split, GalleryDataMode.Import, ToolbarCta.NextText)).startActivity(RequestCodes.PROGRESS_PHOTOS_GALLERY);
    }

    private void showImageChooserDialog() {
        ImageChooserDialogFragment.newInstance(R.string.meal_photos_add_photo_button, R.string.add_progress_photo, R.drawable.ic_progressphoto, Strings.notEmpty(this.statusImageUri)).show(getSupportFragmentManager(), Fragments.STATUS_PHOTO_DIALOG);
    }

    @Subscribe
    public void onStartCameraEvent(StartCameraEvent startCameraEvent) {
        checkPermissions();
    }

    @Subscribe
    public void onStartMediaChooserEvent(StartMediaChooserEvent startMediaChooserEvent) {
        this.importType = ImportDevice.Picker;
        ((StatusAnalytics) this.statusAnalytics.get()).reportStatusUpdateAddPhotoOptionClicked(GALLERY);
        startPickerImport();
    }

    @Subscribe
    public void onShareProgressPhotoEvent(ShareProgressPhotoEvent shareProgressPhotoEvent) {
        startShareProgressPicFlow();
    }

    @Subscribe
    public void onDeletePhotoEvent(DeletePhotoEvent deletePhotoEvent) {
        this.statusImageUri = null;
        this.ivStatusPic.setImageDrawable(null);
        ViewUtils.setGone(this.ivStatusPic);
    }

    private void startCameraImport() {
        getImmHelper().hideSoftInput();
        try {
            this.imageCaptureUri = ImageUploadUtil.getCacheShareUriForFile(this, String.format(TEMP_IMAGE_DEFAULT_FILENAME, new Object[]{Long.valueOf(System.currentTimeMillis())}));
            getNavigationHelper().withIntent(SharedIntents.newImageCaptureIntent(this.imageCaptureUri)).startActivity(SharedConstants.RequestCodes.PICK_FROM_CAMERA);
        } catch (Exception unused) {
            new MfpAlertDialogBuilder(this).setTitle((int) R.string.app_name).setMessage((int) R.string.progress_photos_camera_error_cant_open).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) null).show();
        }
    }

    private void startPickerImport() {
        getImmHelper().hideSoftInput();
        startActivityForResult(new Intent("android.intent.action.PICK").setType("image/*"), 1004);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 101) {
            onConfirmedImage(i2, intent);
        } else if (i != 205) {
            switch (i) {
                case SharedConstants.RequestCodes.PICK_FROM_CAMERA /*1003*/:
                    setImageToView(onPickFromCameraActivityResult(i2, intent));
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

    private void onConfirmedImage(int i, Intent intent) {
        if (i == -1) {
            this.importType = ImportDevice.Picker;
            setImageToView(intent);
            return;
        }
        startPickerImport();
    }

    private Intent onPickFromCameraActivityResult(int i, Intent intent) {
        if (i != -1) {
            return null;
        }
        if (!IntentUtil.hasData(intent)) {
            intent = new Intent().setData(this.imageCaptureUri);
        }
        this.imageCaptureUri = null;
        this.importType = ImportDevice.Camera;
        return intent;
    }

    private void setImageToView(Intent intent) {
        if (IntentUtil.hasData(intent)) {
            this.statusImageUri = intent.getData().toString();
            loadImage();
        }
    }

    private void loadImage() {
        ViewUtils.setVisible(Strings.notEmpty(this.statusImageUri), this.ivStatusPic);
        Context baseContext = getBaseContext();
        String str = this.statusImageUri;
        ImageView imageView = this.ivStatusPic;
        GlideUtil.loadImageWithFailureHandling(baseContext, str, imageView, imageView);
    }

    private void onPickFromFileActivityResult(int i, Intent intent) {
        if (i == -1) {
            getNavigationHelper().withIntent(AddWeightImageImportActivity.newStartIntent(this, intent.getData())).startActivity(101);
        }
    }

    private void saveStatusOrComment() {
        if (this.itemType == 17) {
            postStatus(Strings.trimmed((Object) this.status.getText()));
        }
    }

    /* access modifiers changed from: private */
    public void postStatus(String str) {
        if (!this.processing) {
            ((StatusAnalytics) this.statusAnalytics.get()).reportPostStatusUpdateStarted();
            if (!this.isPostImageToStatusOn || !Strings.notEmpty(this.statusImageUri)) {
                if (Strings.notEmpty(str)) {
                    setProcessing(true);
                    ((NewsFeedService) this.newsFeedService.get()).postNewActivityEntryAsync(str, this.destinationUserUid, new Function0() {
                        public final void execute() {
                            NewStatusOrCommentActivity.lambda$postStatus$1(NewStatusOrCommentActivity.this);
                        }
                    }, new MfpV2ApiErrorCallback() {
                        public final void execute(Object obj) {
                            NewStatusOrCommentActivity.lambda$postStatus$2(NewStatusOrCommentActivity.this, (ApiResponseBase) obj);
                        }
                    });
                }
            } else if (this.importType == ImportDevice.Camera) {
                new AddPhotoToMediaStoreTask(this.statusImageUri).run(getRunner());
            } else {
                uploadAndPostStatus(this.statusImageUri);
            }
        }
    }

    public static /* synthetic */ void lambda$postStatus$1(NewStatusOrCommentActivity newStatusOrCommentActivity) throws RuntimeException {
        newStatusOrCommentActivity.done(-1);
        ((StatusAnalytics) newStatusOrCommentActivity.statusAnalytics.get()).reportPostStatusUpdateCompleted(null);
    }

    public static /* synthetic */ void lambda$postStatus$2(NewStatusOrCommentActivity newStatusOrCommentActivity, ApiResponseBase apiResponseBase) throws RuntimeException {
        ((StatusAnalytics) newStatusOrCommentActivity.statusAnalytics.get()).reportPostStatusUpdateFailed(apiResponseBase.getError());
        newStatusOrCommentActivity.showError(apiResponseBase.getErrorDescription());
    }

    @Subscribe
    public void onPhotoAddedToMediaStore(CompletedEvent completedEvent) {
        if (completedEvent.successful()) {
            uploadAndPostStatus((String) completedEvent.getResult());
            return;
        }
        Ln.e(completedEvent.getFailure());
        showPhotoTransferFailedDialog();
    }

    private void uploadAndPostStatus(String str) {
        UploadImagePostStatusTask.newInstanceForStatusPhoto(this.destinationUserUid, str, Strings.trimmed((Object) this.status.getText()), null, getSession().getUser(), this.imageService, this.imageUploadService, this.newsFeedService).run(getRunner());
        ProgressDialogFragment.newInstance(R.string.progress_dialog_uploading, R.string.progress_dialog_uploading_message).show(getSupportFragmentManager(), UPLOADING_DIALOG_TAG);
    }

    @Subscribe
    public void onUploadImagePostStatusTaskCompleted(UploadImagePostStatusTask.CompletedEvent completedEvent) {
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(UPLOADING_DIALOG_TAG);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
        if (completedEvent.successful()) {
            ((StatusAnalytics) this.statusAnalytics.get()).reportPostStatusUpdateCompleted(completedEvent.getImageType());
            done(-1);
            return;
        }
        ((StatusAnalytics) this.statusAnalytics.get()).reportPostStatusUpdateFailed((String) completedEvent.getResult());
        showUnableToPostStatusError();
    }

    private void showPhotoTransferFailedDialog() {
        new MfpAlertDialogBuilder(this).setTitle((int) R.string.app_name).setMessage((int) R.string.progress_photos_photo_transfer_failed).setPositiveButton((int) R.string.ok, (DialogInterface.OnClickListener) null).show();
    }

    private void showUnableToPostStatusError() {
        if (getSupportFragmentManager().findFragmentByTag(UPLOAD_IMAGE_FAILED_TAG) == null) {
            showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.upload_image_failed_dialog_title)).setMessage((int) R.string.upload_image_failed_dialog_message)).setPositiveText(R.string.try_again, this.dialogPositiveListener)).setNegativeText(R.string.cancel, null), UPLOAD_IMAGE_FAILED_TAG);
        }
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!UPLOAD_IMAGE_FAILED_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((AlertDialogFragment) dialogFragment).setPositiveListener(this.dialogPositiveListener);
        return true;
    }

    /* access modifiers changed from: protected */
    public Dialog onCreateDialog(int i) {
        if (i != 2) {
            try {
                return super.onCreateDialog(i);
            } catch (Exception e) {
                Ln.e(e);
                return super.onCreateDialog(i);
            }
        } else {
            String str = "";
            switch (this.itemType) {
                case 17:
                    str = getResources().getString(R.string.savingStatus);
                    break;
                case 18:
                    str = getResources().getString(R.string.creatingComment);
                    break;
            }
            return ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).createProgressDialog("", str, true, false);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!super.onPrepareOptionsMenu(menu)) {
            return false;
        }
        MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.post).setIcon(R.drawable.ic_check_white_24dp), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 100) {
            return super.onOptionsItemSelected(menuItem);
        }
        saveStatusOrComment();
        return true;
    }

    public void onBackPressed() {
        done(0);
        getImmHelper().hideSoftInput((View) this.status);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getImmHelper().hideSoftInput((View) this.status);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        getImmHelper().hideSoftInput((View) this.status);
    }

    private void showError(String str) {
        setProcessing(false);
        if (!Strings.notEmpty(str)) {
            str = getString(R.string.failCreateUpdate);
        }
        ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(getString(R.string.request_failed), str, getString(R.string.dismiss));
    }

    private void done(int i) {
        setProcessing(false);
        ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).dismissProgressDialog();
        getNavigationHelper().setResult(i).done();
        getImmHelper().hideSoftInput((View) this.status);
    }

    private void setProcessing(boolean z) {
        this.processing = z;
        if (z) {
            showDialog(2);
        } else {
            ((LegacyAlertMixin) mixin(LegacyAlertMixin.class)).dismissProgressDialog();
        }
    }

    private boolean isPostingStatusForLoggedInUser() {
        return Strings.isEmpty(this.destinationUserUid) || Strings.equals(this.destinationUserUid, getSession().getUser().getUserId());
    }

    private void checkPermissions() {
        ((PermissionsMixin) mixin(PermissionsMixin.class)).checkPermissions(new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"}, new kotlin.jvm.functions.Function0() {
            public final Object invoke() {
                return NewStatusOrCommentActivity.lambda$checkPermissions$3(NewStatusOrCommentActivity.this);
            }
        }, new kotlin.jvm.functions.Function0() {
            public final Object invoke() {
                return NewStatusOrCommentActivity.this.showPermissionDeniedDialog();
            }
        });
    }

    public static /* synthetic */ Unit lambda$checkPermissions$3(NewStatusOrCommentActivity newStatusOrCommentActivity) {
        newStatusOrCommentActivity.importType = ImportDevice.Camera;
        ((StatusAnalytics) newStatusOrCommentActivity.statusAnalytics.get()).reportStatusUpdateAddPhotoOptionClicked("camera");
        newStatusOrCommentActivity.startCameraImport();
        return null;
    }

    /* access modifiers changed from: private */
    public void showPermissionDeniedDialog() {
        new Handler().post(new Runnable() {
            public final void run() {
                ((PermissionsMixin) NewStatusOrCommentActivity.this.mixin(PermissionsMixin.class)).showPermissionDeniedDialog(R.string.permission_settings_camera_storage, null);
            }
        });
    }
}
