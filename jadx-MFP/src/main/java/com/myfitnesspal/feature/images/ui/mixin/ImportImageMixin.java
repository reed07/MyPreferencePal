package com.myfitnesspal.feature.images.ui.mixin;

import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.images.ui.dialog.ChooseImageDialogFragment;
import com.myfitnesspal.feature.images.ui.dialog.ChooseImageDialogFragment.OnImportDeviceSelectedListener;
import com.myfitnesspal.feature.images.util.ImageUploadUtil;
import com.myfitnesspal.feature.permissions.PermissionsMixin;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.feature.progress.ui.activity.AddWeightImageImportActivity;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.constants.SharedConstants;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.navigation.SharedIntents;
import com.myfitnesspal.shared.ui.view.GlideHideProgressListener;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.IntentUtil;
import com.uacf.core.util.Strings;
import java.io.IOException;
import kotlin.jvm.functions.Function0;

public class ImportImageMixin extends RunnerLifecycleMixin {
    private static final String EXTRA_IMAGE_CAPTURE_URI = "extra_image_capture_uri";
    private static final String EXTRA_SELECTED_IMAGE_PATH = "extra_selected_image_path";
    private static final String EXTRA_VISIBLE_IMAGE_UID = "visible_image_uid";
    private static final String EXTRA_VISIBLE_IMAGE_WAS_IMPORTED = "extra_visible_image_was_imported";
    private static final String IMPORT_PHOTO_DIALOG_FRAGMENT = "import_photo_dialog_fragment";
    private static final String TEMP_IMAGE_DEFAULT_FILENAME = "mfp-food-editor-image-capture.jpg";
    private Uri imageCaptureUri = null;
    private ImageChangedListener imageChangedListener;
    private GlideHideProgressListener imageLoadListener = new GlideHideProgressListener();
    private ImageView imageView;
    private final OnImportDeviceSelectedListener onImportPhotoDeviceSelectedListener = new OnImportDeviceSelectedListener() {
        public void onImportDeviceSelected(ImportDevice importDevice) {
            if (importDevice == ImportDevice.Camera) {
                ImportImageMixin.this.checkPermission();
            } else {
                ImportImageMixin.this.startPickerImport();
            }
        }

        public void onRemovePhoto() {
            if (ImportImageMixin.this.removeImageListener != null) {
                ImportImageMixin.this.removeImageListener.onRemoveMenuItemSelected();
            }
        }
    };
    private View progressView;
    /* access modifiers changed from: private */
    public RemoveImageListener removeImageListener;
    private String visibleImagePath = null;
    private String visibleImageUid;
    private boolean visibleImageWasImported = false;

    public interface ImageChangedListener {
        void onChanged(String str);

        void onImportFinished(String str);

        void onImportStarted();
    }

    public interface RemoveImageListener {
        boolean canRemovePhoto();

        void onRemoveMenuItemSelected();
    }

    public ImportImageMixin(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.visibleImagePath = bundle.getString(EXTRA_SELECTED_IMAGE_PATH);
            this.visibleImageUid = bundle.getString(EXTRA_VISIBLE_IMAGE_UID);
            this.visibleImageWasImported = bundle.getBoolean(EXTRA_VISIBLE_IMAGE_WAS_IMPORTED);
            this.imageCaptureUri = (Uri) BundleUtils.getParcelable(bundle, EXTRA_IMAGE_CAPTURE_URI, Uri.class.getClassLoader());
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(EXTRA_SELECTED_IMAGE_PATH, this.visibleImagePath);
        bundle.putString(EXTRA_VISIBLE_IMAGE_UID, this.visibleImageUid);
        bundle.putBoolean(EXTRA_VISIBLE_IMAGE_WAS_IMPORTED, this.visibleImageWasImported);
        bundle.putParcelable(EXTRA_IMAGE_CAPTURE_URI, this.imageCaptureUri);
    }

    private void onNewImageSelected(String str) {
        if (Strings.notEmpty(str)) {
            ImageView imageView2 = this.imageView;
            if (imageView2 != null) {
                this.imageLoadListener.setLoading(this.progressView, imageView2);
                boolean z = true;
                boolean z2 = !str.endsWith(TEMP_IMAGE_DEFAULT_FILENAME);
                RequestBuilder listener = Glide.with(getActivity()).load(str).listener(this.imageLoadListener);
                RequestOptions requestOptions = new RequestOptions();
                if (z2) {
                    z = false;
                }
                listener.apply(requestOptions.skipMemoryCache(z).diskCacheStrategy(z2 ? DiskCacheStrategy.ALL : DiskCacheStrategy.NONE).centerCrop()).into(this.imageView);
            }
            if (!str.equals(this.visibleImagePath)) {
                this.visibleImagePath = str;
                ImageChangedListener imageChangedListener2 = this.imageChangedListener;
                if (imageChangedListener2 != null) {
                    imageChangedListener2.onChanged(this.visibleImagePath);
                    return;
                }
                return;
            }
            return;
        }
        this.imageLoadListener.setLoaded(this.progressView, this.imageView);
        this.imageView.setImageDrawable(null);
        this.visibleImagePath = null;
        ImageChangedListener imageChangedListener3 = this.imageChangedListener;
        if (imageChangedListener3 != null) {
            imageChangedListener3.onChanged(null);
        }
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!str.equals(IMPORT_PHOTO_DIALOG_FRAGMENT)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((ChooseImageDialogFragment) dialogFragment).setOnImportDeviceSelectedListener(this.onImportPhotoDeviceSelectedListener);
        return true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1003) {
            if (i2 == -1) {
                String dataString = IntentUtil.hasData(intent) ? intent.getDataString() : this.imageCaptureUri.toString();
                this.visibleImageWasImported = true;
                this.visibleImageUid = null;
                onNewImageSelected(dataString);
                ImageChangedListener imageChangedListener2 = this.imageChangedListener;
                if (imageChangedListener2 != null) {
                    imageChangedListener2.onImportFinished(dataString);
                }
            }
            this.imageCaptureUri = null;
        } else if (i == 1004) {
            if (i2 == -1) {
                getMfpActivity().getNavigationHelper().withIntent(AddWeightImageImportActivity.newStartIntent(getActivity(), intent.getData())).startActivity(RequestCodes.CONFIRM_PHOTO_IMPORT);
            }
        } else if (i == 193) {
            if (IntentUtil.hasData(intent)) {
                this.visibleImageWasImported = true;
                this.visibleImageUid = null;
                String dataString2 = intent.getDataString();
                onNewImageSelected(dataString2);
                ImageChangedListener imageChangedListener3 = this.imageChangedListener;
                if (imageChangedListener3 != null) {
                    imageChangedListener3.onImportFinished(dataString2);
                }
            }
            this.imageCaptureUri = null;
        } else if (i == 205) {
            checkPermission();
        }
    }

    public void initializeViews(ImageView imageView2, View view) {
        this.imageView = imageView2;
        this.progressView = view;
        if (Strings.notEmpty(this.visibleImagePath)) {
            onNewImageSelected(this.visibleImagePath);
        }
    }

    public void setImagePathAndUid(String str, String str2) {
        this.visibleImageWasImported = false;
        this.visibleImageUid = str2;
        onNewImageSelected(str);
    }

    public void setRemoveImageListener(RemoveImageListener removeImageListener2) {
        this.removeImageListener = removeImageListener2;
    }

    public void setImageChangedListener(ImageChangedListener imageChangedListener2) {
        this.imageChangedListener = imageChangedListener2;
    }

    public String getVisibleImagePath() {
        return this.visibleImagePath;
    }

    public String getVisibleImageUid() {
        return this.visibleImageUid;
    }

    public String getImportedImagePath() {
        if (this.visibleImageWasImported) {
            return this.visibleImagePath;
        }
        return null;
    }

    public void showImportDialog() {
        MfpActivity mfpActivity = getMfpActivity();
        String string = mfpActivity.getString(R.string.meal_photos_dialog_title);
        RemoveImageListener removeImageListener2 = this.removeImageListener;
        ChooseImageDialogFragment newInstance = ChooseImageDialogFragment.newInstance(string, removeImageListener2 != null ? removeImageListener2.canRemovePhoto() : false);
        newInstance.setOnImportDeviceSelectedListener(this.onImportPhotoDeviceSelectedListener);
        mfpActivity.showDialogFragment(newInstance, IMPORT_PHOTO_DIALOG_FRAGMENT);
    }

    /* access modifiers changed from: private */
    public void startCameraImport() {
        ImageChangedListener imageChangedListener2 = this.imageChangedListener;
        if (imageChangedListener2 != null) {
            imageChangedListener2.onImportStarted();
        }
        MfpActivity mfpActivity = getMfpActivity();
        try {
            this.imageCaptureUri = ImageUploadUtil.getCacheShareUriForFile(mfpActivity, TEMP_IMAGE_DEFAULT_FILENAME);
            mfpActivity.getNavigationHelper().withIntent(SharedIntents.newImageCaptureIntent(this.imageCaptureUri)).startActivity(SharedConstants.RequestCodes.PICK_FROM_CAMERA);
        } catch (IOException unused) {
            new MfpAlertDialogBuilder(mfpActivity).setTitle((int) R.string.app_name).setMessage((int) R.string.progress_photos_camera_error_cant_open).setPositiveButton((int) R.string.ok, (OnClickListener) null).show();
        }
    }

    /* access modifiers changed from: private */
    public void startPickerImport() {
        ImageChangedListener imageChangedListener2 = this.imageChangedListener;
        if (imageChangedListener2 != null) {
            imageChangedListener2.onImportStarted();
        }
        getMfpActivity().getNavigationHelper().withIntent(new Intent("android.intent.action.PICK").setType("image/*")).startActivity(1004);
    }

    /* access modifiers changed from: private */
    public void checkPermission() {
        ((PermissionsMixin) getMfpActivity().mixin(PermissionsMixin.class)).checkPermissions(new String[]{"android.permission.CAMERA"}, new Function0() {
            public final Object invoke() {
                return ImportImageMixin.this.startCameraImport();
            }
        }, new Function0() {
            public final Object invoke() {
                return ImportImageMixin.this.showPermissionDeniedDialog();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showPermissionDeniedDialog() {
        new Handler().post(new Runnable() {
            public final void run() {
                ((PermissionsMixin) ImportImageMixin.this.getMfpActivity().mixin(PermissionsMixin.class)).showPermissionDeniedDialog(R.string.permission_settings_camera, null);
            }
        });
    }
}
