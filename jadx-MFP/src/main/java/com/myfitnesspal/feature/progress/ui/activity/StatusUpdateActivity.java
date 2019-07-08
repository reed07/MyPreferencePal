package com.myfitnesspal.feature.progress.ui.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.foodeditor.task.FetchFoodNotesTask;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodNotesActivity;
import com.myfitnesspal.feature.home.ui.activity.HomeActivity;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageUploadService;
import com.myfitnesspal.feature.meals.service.MealService;
import com.myfitnesspal.feature.meals.task.UpdateFoodPermissionTask;
import com.myfitnesspal.feature.meals.ui.dialog.PermissionRestrictionDialogFragment;
import com.myfitnesspal.feature.meals.ui.dialog.PermissionRestrictionDialogFragment.OnChangePermissionClickListener;
import com.myfitnesspal.feature.meals.util.MealSharingDirectionsAnalyticsHelper;
import com.myfitnesspal.feature.progress.constants.ShareTarget;
import com.myfitnesspal.feature.progress.constants.SharingPermission;
import com.myfitnesspal.feature.progress.event.SharePermissionChangedEvent;
import com.myfitnesspal.feature.progress.model.ImageStatusMetadata;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.progress.ui.fragment.SharePermissionDialogFragment;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.db.table.FoodNotesTable;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.service.foods.FoodPermissionsService;
import com.myfitnesspal.shared.service.newsfeed.NewsFeedService;
import com.myfitnesspal.shared.task.SyncAndUploadImagePostStatusTask;
import com.myfitnesspal.shared.task.UploadImagePostStatusTask;
import com.myfitnesspal.shared.task.UploadImagePostStatusTask.CompletedEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.dialog.impl.ProgressDialogFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.myfitnesspal.shared.util.Toaster;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.FileUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.io.File;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;

public class StatusUpdateActivity extends MfpActivity {
    private static final String APP_FACEBOOK = "Facebook";
    private static final String APP_INSTAGRAM = "Instagram";
    private static final String CLIPBOARD_KEY = "Share status";
    private static final int DONE = 100;
    private static final String EXTRA_CURRENT_PERMISSION = "current_permission";
    private static final String EXTRA_MEAL_FOOD = "meal_food";
    private static final String EXTRA_MEAL_FOOD_IMAGE_UID = "meal_food_image_uid";
    private static final String EXTRA_PHOTO_TYPE_ORDINAL = "photo_type_ordinal";
    private static final String EXTRA_STATUS = "status";
    private static final int FOOD_NOTES_REQUEST_CODE = 128;
    private static final String IMAGE_CONTENT_TYPE = "image/png";
    private static final int KEYBOARD_SHOW_DELAY_MS = 300;
    private static final String MFP_GALLERY_FOLDER = "/MyFitnessPal/";
    private static final String MFP_PROGRESS_FILENAME = "myfitnesspal-%d.png";
    private static final String PERMISSIONS_DIALOG_TAG = "permissions_dialog";
    private static final String PERMISSION_RESTRICTION_DIALOG_TAG = "permission_restriction_dialog_tag";
    private static final String SHARE_HASHTAG = "#MyFitnessPal";
    private static final String UPLOADING_DIALOG_TAG = "uploading_dialog_tag";
    private static final String UPLOAD_IMAGE_FAILED_TAG = "upload_image_failed";
    @BindView(2131362041)
    View buttonContainer;
    private boolean containsCongratulationsImage;
    private Uri contentUri;
    private SharingPermission currentPermission;
    @BindView(2131362551)
    View facebook;
    @Inject
    Lazy<FoodNotesTable> foodNotes;
    @Inject
    Lazy<FoodPermissionsService> foodPermissionsService;
    private String galleryViewMode;
    private Handler handler = new Handler();
    @BindView(2131363497)
    View headerContainer;
    @Inject
    Lazy<ImageService> imageService;
    private ImageStatusMetadata imageStatusMetadata;
    @Inject
    Lazy<ImageUploadService> imageUploadService;
    @BindView(2131362825)
    View instagram;
    @Inject
    Lazy<MealAnalyticsHelper> mealAnalyticsHelper;
    @Inject
    Lazy<MealSharingDirectionsAnalyticsHelper> mealBrowseAnalytics;
    @BindView(2131362936)
    View mealNotesHint;
    @Inject
    Lazy<MealService> mealService;
    @BindView(2131363072)
    View moreButton;
    @Inject
    Lazy<NewsFeedService> newsFeedService;
    private OnChangePermissionClickListener onChangePermissionClickListener = new OnChangePermissionClickListener() {
        public void onPermissionChangeClick() {
            ((MealAnalyticsHelper) StatusUpdateActivity.this.mealAnalyticsHelper.get()).reportShareMealPermissionAlertAccepted();
            new UpdateFoodPermissionTask(StatusUpdateActivity.this.foodPermissionsService, Permission.Public, StatusUpdateActivity.this.getMealFoodFromIntent()).run(StatusUpdateActivity.this.getRunner());
        }

        public void onCancelled() {
            ((MealAnalyticsHelper) StatusUpdateActivity.this.mealAnalyticsHelper.get()).reportShareMealPermissionAlertCancelled();
        }
    };
    /* access modifiers changed from: private */
    public PhotoType photoType;
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;
    @BindView(2131363533)
    View saveToGalleryButton;
    private String selectedArtifactType;
    @BindView(2131362863)
    ImageView shareArtifact;
    private Map<ShareTarget, Intent> shareIntents;
    @BindView(2131362499)
    EditText shareStatus;
    @BindView(2131363639)
    View shareWithContainer;
    private boolean shouldShowDone = true;
    @BindView(2131363938)
    TextView statusShareWith;
    @BindView(2131363733)
    View statusUpdateContainer;

    private enum PhotoType {
        ProgressPhoto,
        MealFood
    }

    public static Intent newStartIntentForProgressPhotoShare(Context context, Uri uri, String str, String str2, ImageStatusMetadata imageStatusMetadata2, boolean z) {
        return new Intent(context, StatusUpdateActivity.class).putExtra(Extras.CONTENT_URI, uri).putExtra(Extras.SELECTED_ARTIFACT_TYPE, str).putExtra(Extras.GALLERY_VIEW_MODE, str2).putExtra(Extras.IMAGE_STATUS_METADATA, imageStatusMetadata2).putExtra(Extras.IS_CONGRATS_IMAGE, z).putExtra(EXTRA_PHOTO_TYPE_ORDINAL, PhotoType.ProgressPhoto.ordinal());
    }

    public static Intent newStartIntentForMealFoodShare(Context context, Uri uri, MealFood mealFood, String str) {
        return new Intent(context, StatusUpdateActivity.class).putExtra(Extras.CONTENT_URI, uri).putExtra("meal_food", mealFood).putExtra(EXTRA_PHOTO_TYPE_ORDINAL, PhotoType.MealFood.ordinal()).putExtra(EXTRA_MEAL_FOOD_IMAGE_UID, str);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.progress_status_update_activity);
        component().inject(this);
        Bundle extras = getIntent().getExtras();
        this.contentUri = (Uri) extras.getParcelable(Extras.CONTENT_URI);
        this.selectedArtifactType = BundleUtils.getString(extras, Extras.SELECTED_ARTIFACT_TYPE);
        this.shareIntents = ShareTarget.getAvailableShareTargets(this, IMAGE_CONTENT_TYPE);
        this.galleryViewMode = extras.getString(Extras.GALLERY_VIEW_MODE);
        this.imageStatusMetadata = (ImageStatusMetadata) BundleUtils.getParcelable(extras, Extras.IMAGE_STATUS_METADATA, ImageStatusMetadata.class.getClassLoader());
        this.containsCongratulationsImage = BundleUtils.getBoolean(extras, Extras.IS_CONGRATS_IMAGE);
        this.photoType = PhotoType.values()[BundleUtils.getInt(extras, EXTRA_PHOTO_TYPE_ORDINAL)];
        if (this.photoType != PhotoType.MealFood) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportProgressShareScreenViewed();
        }
        this.currentPermission = SharingPermission.Community;
        initViewsAndEventHandlers();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        EditText editText = this.shareStatus;
        editText.setSelection(editText.getText().length());
        checkFetchMealNotes();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        getImmHelper().hideSoftInput();
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 128) {
            if (i2 == -1) {
                new SnackbarBuilder(findViewById(R.id.llMain)).setMessage(getString(R.string.meal_sharing_meal_updated)).setDuration(0).showWithDelay();
            } else {
                checkShowKeyboardAfterDelay();
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    private void checkShowKeyboardAfterDelay() {
        this.handler.postDelayed(new Runnable() {
            public void run() {
                if (StatusUpdateActivity.this.isEnabled() && StatusUpdateActivity.this.shareStatus.isFocused()) {
                    StatusUpdateActivity.this.getImmHelper().showSoftInput(StatusUpdateActivity.this.shareStatus);
                }
            }
        }, 300);
    }

    private void checkFetchMealNotes() {
        if (ConfigUtils.isMealNotesShareTipEnabled(getConfigService())) {
            MealFood mealFoodFromIntent = getMealFoodFromIntent();
            if (this.photoType == PhotoType.MealFood && mealFoodFromIntent != null) {
                new FetchFoodNotesTask((FoodNotesTable) this.foodNotes.get(), mealFoodFromIntent).run(getRunner());
            }
        }
    }

    private void initViewsAndEventHandlers() {
        ViewUtils.setGone(this.mealNotesHint);
        this.mealNotesHint.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ((MealSharingDirectionsAnalyticsHelper) StatusUpdateActivity.this.mealBrowseAnalytics.get()).reportSharingPromptTapped();
                StatusUpdateActivity.this.getNavigationHelper().withIntent(FoodNotesActivity.newStartIntentForSave(StatusUpdateActivity.this.getActivity(), "", StatusUpdateActivity.this.getMealFoodFromIntent())).startActivity(128);
            }
        });
        this.headerContainer.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StatusUpdateActivity.this.shareStatus.requestFocus();
                StatusUpdateActivity.this.getImmHelper().showSoftInput(StatusUpdateActivity.this.shareStatus);
            }
        });
        this.shareArtifact.setImageURI(this.contentUri);
        this.shareArtifact.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (StatusUpdateActivity.this.photoType == PhotoType.MealFood) {
                    ((MealAnalyticsHelper) StatusUpdateActivity.this.mealAnalyticsHelper.get()).reportShareMealArtifactViewed();
                }
                StatusUpdateActivity.this.showImagePreview();
            }
        });
        this.statusShareWith.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StatusUpdateActivity.this.showPermissionsDialog();
                StatusUpdateActivity.this.reportPostPrivacyPermissionViewed();
            }
        });
        if (this.photoType != PhotoType.MealFood) {
            initButton(this.instagram, ShareTarget.Instagram, R.string.progress_share_instagram, R.drawable.ic_asset_share_instagram);
            initButton(this.facebook, ShareTarget.Facebook, R.string.progress_share_facebook, R.drawable.ic_asset_share_facebook);
            initButton(this.saveToGalleryButton, ShareTarget.SaveToGallery, Locale.getDefault().getLanguage().equals("en") ? R.string.progress_save_to_gallery : R.string.save, R.drawable.ic_camera_roll_blk_54);
            initButton(this.moreButton, ShareTarget.More, R.string.progress_share_more, R.drawable.ic_asset_share_more);
            return;
        }
        ViewUtils.setGone(this.buttonContainer);
        ViewUtils.setGone(this.shareWithContainer);
    }

    /* access modifiers changed from: private */
    public void showImagePreview() {
        getNavigationHelper().withIntent(ImagePreviewActivity.newStartIntent(getApplicationContext(), this.contentUri)).startActivity();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(EXTRA_CURRENT_PERMISSION, this.currentPermission);
        bundle.putString("status", this.shareStatus.getText().toString());
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        setPermission((SharingPermission) bundle.getSerializable(EXTRA_CURRENT_PERMISSION));
        this.shareStatus.setText(bundle.getString("status"));
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (!Strings.equals(str, PERMISSION_RESTRICTION_DIALOG_TAG)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        }
        ((PermissionRestrictionDialogFragment) dialogFragment).setOnChangePermissionClickListener(this.onChangePermissionClickListener);
        return true;
    }

    /* access modifiers changed from: private */
    public void showPermissionsDialog() {
        if (!isDialogVisible(PERMISSIONS_DIALOG_TAG)) {
            showDialogFragment(SharePermissionDialogFragment.newInstance(this.currentPermission), PERMISSIONS_DIALOG_TAG);
        }
    }

    @Subscribe
    public void onSharingPermissionChanged(SharePermissionChangedEvent sharePermissionChangedEvent) {
        setPermission(sharePermissionChangedEvent.getSharingPermission());
        reportSharePermissionChanged(sharePermissionChangedEvent.getSharingPermission());
    }

    private void setPermission(SharingPermission sharingPermission) {
        this.currentPermission = sharingPermission;
        this.statusShareWith.setText(getString(this.currentPermission.getStringId()));
    }

    private boolean isDialogVisible(String str) {
        return getSupportFragmentManager().findFragmentByTag(str) != null;
    }

    private void initButton(View view, final ShareTarget shareTarget, int i, int i2) {
        ((TextView) view.findViewById(R.id.caption)).setText(i);
        ((ImageView) view.findViewById(R.id.icon)).setImageResource(i2);
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                StatusUpdateActivity.this.shareArtifact(shareTarget);
            }
        });
    }

    /* access modifiers changed from: private */
    public void shareArtifact(final ShareTarget shareTarget) {
        if (shareTarget == ShareTarget.SaveToGallery) {
            saveImageToGallery();
            reportArtifactShareStarted(shareTarget);
        } else if (((Intent) this.shareIntents.get(shareTarget)) != null) {
            final Intent intent = (Intent) ParcelableUtil.clone((Parcelable) this.shareIntents.get(shareTarget), Intent.CREATOR);
            intent.setType(IMAGE_CONTENT_TYPE);
            intent.putExtra("android.intent.extra.STREAM", this.contentUri);
            intent.putExtra("android.intent.extra.SUBJECT", SHARE_HASHTAG);
            if (shareTarget == ShareTarget.More) {
                intent = Intent.createChooser(intent, getString(R.string.progress_photos_share_more_title));
            }
            getImmHelper().hideSoftInput();
            if (shareTarget == ShareTarget.SaveToGallery || !Strings.notEmpty(this.shareStatus.getText().toString())) {
                startShareActivity(shareTarget, intent);
            } else {
                ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(CLIPBOARD_KEY, Strings.toString(this.shareStatus.getText())));
                AnonymousClass7 r2 = new Callback() {
                    public void onShown(Snackbar snackbar) {
                    }

                    public void onDismissed(Snackbar snackbar, int i) {
                        StatusUpdateActivity.this.startShareActivity(shareTarget, intent);
                        StatusUpdateActivity.this.setDoneButtonActive(true);
                    }
                };
                setDoneButtonActive(false);
                new SnackbarBuilder(this.statusUpdateContainer).setMessage((int) R.string.text_copied_snackbar).setDuration(-1).setCallback(r2).build().show();
            }
            reportArtifactShareStarted(shareTarget);
        } else {
            postEvent(new AlertEvent(getString(R.string.app_not_available_message, new Object[]{getAppNameFromShareTarget(shareTarget)})));
        }
    }

    /* access modifiers changed from: private */
    public void setDoneButtonActive(boolean z) {
        this.shouldShowDone = z;
        invalidateOptionsMenu();
    }

    /* access modifiers changed from: private */
    public void startShareActivity(ShareTarget shareTarget, Intent intent) {
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            postEvent(new AlertEvent(getString(R.string.app_not_available_message, new Object[]{getAppNameFromShareTarget(shareTarget)})));
            StringBuilder sb = new StringBuilder();
            sb.append(shareTarget.getAnalyticsValue());
            sb.append(" activity not found");
            Log.e("ProgressPics", sb.toString());
        }
    }

    private void saveImageToGallery() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory());
        sb.append("/");
        sb.append(Environment.DIRECTORY_PICTURES);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        sb3.append(MFP_GALLERY_FOLDER);
        String sb4 = sb3.toString();
        String format = String.format(MFP_PROGRESS_FILENAME, new Object[]{Long.valueOf(System.currentTimeMillis())});
        File file = new File(sb4);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            FileUtils.copyFile(this, this.contentUri.getPath(), sb4, format);
            StringBuilder sb5 = new StringBuilder();
            sb5.append(sb4);
            sb5.append(format);
            scanFiles(sb5.toString());
            Toaster.showShort((Activity) this, (int) R.string.image_saved_sucessfully);
        } catch (Exception unused) {
            Toaster.showShort((Activity) this, (int) R.string.image_not_saved);
        }
    }

    private void scanFiles(String str) {
        MediaScannerConnection.scanFile(this, new String[]{new File(str).toString()}, null, new OnScanCompletedListener() {
            public void onScanCompleted(String str, Uri uri) {
                StringBuilder sb = new StringBuilder();
                sb.append("Scanned ");
                sb.append(str);
                sb.append(":-> uri=");
                sb.append(uri);
                Log.i("ExternalStorage", sb.toString());
            }
        });
    }

    private String getAppNameFromShareTarget(ShareTarget shareTarget) {
        return shareTarget == ShareTarget.Facebook ? APP_FACEBOOK : APP_INSTAGRAM;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        menu.removeGroup(0);
        if (this.shouldShowDone) {
            MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.done).setIcon(R.drawable.ic_check_white_24dp).setEnabled(true), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 100) {
            onDone();
        }
        getImmHelper().hideSoftInput();
        return super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: private */
    public void onDone() {
        Permission permission;
        if (this.photoType == PhotoType.MealFood) {
            FoodPermission foodPermission = getMealFoodFromIntent().getFoodPermission();
            if (foodPermission == null) {
                permission = Permission.Private;
            } else {
                permission = Permission.get(foodPermission.getPermissionValue());
            }
            if (permission == Permission.Private) {
                ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportShareMealPermissionAlertShown(permission, Permission.Public);
                PermissionRestrictionDialogFragment newInstance = PermissionRestrictionDialogFragment.newInstance();
                newInstance.setOnChangePermissionClickListener(this.onChangePermissionClickListener);
                showDialogFragment(newInstance, PERMISSION_RESTRICTION_DIALOG_TAG);
                return;
            }
        }
        startShareTask();
    }

    private void startShareTask() {
        String userId = getSession().getUser().getUserId();
        String path = this.contentUri.getPath();
        String obj = this.shareStatus.getText().toString();
        if (this.photoType == PhotoType.MealFood) {
            ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportShareMealShareStartedNewsfeed(obj);
            SyncAndUploadImagePostStatusTask.newInstanceForMealFood(userId, path, obj, this.imageService, this.imageUploadService, this.newsFeedService, getMealFoodFromIntent(), ExtrasUtils.getString(getIntent(), EXTRA_MEAL_FOOD_IMAGE_UID), this.mealService).run(getRunner());
        } else {
            reportProgressShareStartedEvent();
            UploadImagePostStatusTask.newInstanceForProgressPhoto(userId, path, obj, this.imageStatusMetadata, getSession().getUser(), this.imageService, this.imageUploadService, this.newsFeedService).run(getRunner());
        }
        ProgressDialogFragment.newInstance(R.string.progress_dialog_uploading, R.string.progress_dialog_uploading_message).show(getSupportFragmentManager(), UPLOADING_DIALOG_TAG);
    }

    @Subscribe
    public void onUploadImagePostStatusTaskCompleted(CompletedEvent completedEvent) {
        DialogFragment dialogFragment = (DialogFragment) getSupportFragmentManager().findFragmentByTag(UPLOADING_DIALOG_TAG);
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
        if (completedEvent.successful()) {
            reportShareCompletedEvent(completedEvent.getStatusText());
            getNavigationHelper().withIntent(HomeActivity.newStartIntent(this)).startActivity();
            return;
        }
        showUnableToPostStatusError();
    }

    @Subscribe
    public void onUpdateFoodPermissionTaskCompletedEvent(UpdateFoodPermissionTask.CompletedEvent completedEvent) {
        MealFood mealFoodFromIntent = getMealFoodFromIntent();
        mealFoodFromIntent.setFoodPermission((FoodPermission) completedEvent.getResult());
        getIntent().putExtra("meal_food", mealFoodFromIntent);
        startShareTask();
    }

    @Subscribe
    public void onFetchFoodNotesTaskCompleted(FetchFoodNotesTask.CompletedEvent completedEvent) {
        boolean isEmpty = Strings.isEmpty((String) completedEvent.getResult());
        ViewUtils.setVisible(isEmpty, this.mealNotesHint);
        if (isEmpty) {
            ((MealSharingDirectionsAnalyticsHelper) this.mealBrowseAnalytics.get()).reportSharingPromptDisplayed();
        }
    }

    /* access modifiers changed from: private */
    public MealFood getMealFoodFromIntent() {
        return (MealFood) ExtrasUtils.getParcelable(getIntent(), "meal_food", MealFood.class.getClassLoader());
    }

    private void showUnableToPostStatusError() {
        AlertDialogFragmentBase alertDialogFragmentBase;
        if (getSupportFragmentManager().findFragmentByTag(UPLOAD_IMAGE_FAILED_TAG) == null) {
            boolean z = this.photoType == PhotoType.MealFood;
            if (z) {
                alertDialogFragmentBase = ((AlertDialogFragment) AlertDialogFragment.newInstance().setMessage((int) R.string.meal_share_failed)).setPositiveText(R.string.ok, null);
            } else {
                alertDialogFragmentBase = ((AlertDialogFragment) ((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.upload_image_failed_dialog_title)).setMessage((int) R.string.upload_image_failed_dialog_message)).setPositiveText(R.string.try_again, new DialogPositiveListener() {
                    public void onClick(Object obj) {
                        StatusUpdateActivity.this.onDone();
                    }
                })).setNegativeText(R.string.cancel, null);
            }
            showDialogFragment(alertDialogFragmentBase, UPLOAD_IMAGE_FAILED_TAG);
            if (z) {
                ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportShareMealFailedDuringSyncOrUpload();
            }
        }
    }

    private void reportArtifactShareStarted(ShareTarget shareTarget) {
        if (shareTarget != null) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportShareProgressShareStarted(shareTarget, this.selectedArtifactType, false, this.galleryViewMode, this.containsCongratulationsImage);
        }
    }

    /* access modifiers changed from: private */
    public void reportPostPrivacyPermissionViewed() {
        ((ProgressAnalytics) this.progressAnalytics.get()).reportPostPrivacyPermissionViewed();
    }

    private void reportSharePermissionChanged(SharingPermission sharingPermission) {
        ((ProgressAnalytics) this.progressAnalytics.get()).reportPostPrivacyPermissions(sharingPermission);
    }

    private void reportProgressShareStartedEvent() {
        ((ProgressAnalytics) this.progressAnalytics.get()).reportProgressPhotosShareStartedNewsfeed(this.selectedArtifactType, this.shareStatus.getText().toString(), this.galleryViewMode, this.containsCongratulationsImage);
    }

    private void reportShareCompletedEvent(String str) {
        if (this.photoType == PhotoType.MealFood) {
            ((MealAnalyticsHelper) this.mealAnalyticsHelper.get()).reportShareMealShareCompleted(str);
        } else {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportProgressPhotosShareCompletedNewsfeed(this.selectedArtifactType, this.shareStatus.getText().toString(), this.galleryViewMode, this.containsCongratulationsImage);
        }
    }
}
