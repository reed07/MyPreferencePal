package com.myfitnesspal.feature.progress.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.ui.dialog.ChooseImageDialogFragment;
import com.myfitnesspal.feature.images.ui.dialog.ChooseImageDialogFragment.OnImportDeviceSelectedListener;
import com.myfitnesspal.feature.progress.constants.GalleryDataMode;
import com.myfitnesspal.feature.progress.constants.GalleryViewMode;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.feature.progress.constants.ImportSource;
import com.myfitnesspal.feature.progress.constants.ProgressEntryPoint;
import com.myfitnesspal.feature.progress.model.ImageStatusMetadata;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics.TapTarget;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.feature.progress.task.GenerateArtifactImageTask;
import com.myfitnesspal.feature.progress.task.GenerateArtifactImageTask.CompletedEvent;
import com.myfitnesspal.feature.progress.ui.view.GalleryImageView;
import com.myfitnesspal.feature.progress.ui.view.GalleryImageView.OnImportClickListener;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryImageViewModel;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryImageViewModel.ImageType;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryScrollerViewModel;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryScrollerViewModel.Property;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogPositiveListener;
import com.myfitnesspal.shared.ui.fragment.impl.HorizontalGalleryFragment;
import com.myfitnesspal.shared.ui.fragment.impl.HorizontalGalleryFragment.OnPositionChangedListener;
import com.myfitnesspal.shared.ui.fragment.impl.HorizontalGalleryFragment.ScrollMode;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;

public class ProgressPhotosGalleryActivity extends MfpActivity {
    private static final String ASSET_IMAGE_FAILED_TAG = "asset_image_failed";
    private static final String CACHE_FILENAME = "mfp-progress-photos-image-artifact.png";
    public static final String EXTRA_CONGRATS_IMAGE_URI = "congrats_image_uri";
    public static final String EXTRA_CONGRATS_MESSAGE = "extra_congrats_message";
    public static final String EXTRA_DATA_MODE = "data_mode";
    public static final String EXTRA_INITIAL_SELECTION = "initial_selection";
    public static final String EXTRA_TOOLBAR_CTA = "toolbar_cta";
    public static final String EXTRA_VIEW_MODE = "view_mode";
    private static final String GALLERY_FRAGMENT_TAG = "gallery_fragment";
    private static final String IMPORT_DEVICE_DIALOG_TAG = "import_device_dialog";
    private static final String LEFT_IMAGE_BUNDLE_KEY_PREFIX = "left_image.";
    private static final String NO_PHOTOS_ERROR_TAG = "no_photos_error";
    private static final String RIGHT_IMAGE_BUNDLE_KEY_PREFIX = "right_image.";
    private static final int SHARE_MENU_ID = 1000;
    private static final int UPDATE_IMAGE_DEBOUNCE_DELAY_MS = 100;
    private Uri congratsImageUri;
    private String congratsMessage;
    private HorizontalGalleryFragment galleryFragment;
    private Handler handler = new Handler();
    @Inject
    Lazy<ImageService> imageService;
    /* access modifiers changed from: private */
    public GalleryScrollerViewModel imageViewModel;
    @BindView(2131362791)
    ViewGroup imagesContainer;
    private boolean isNewCongratsSharingOn;
    @BindView(2131362901)
    ViewGroup leftImageContainer;
    @BindView(2131362902)
    ViewGroup leftImageDetails;
    /* access modifiers changed from: private */
    public GalleryImageView leftImageView;
    private OnImportClickListener onImportClicked = new OnImportClickListener() {
        public void onImportClicked(GalleryImageView galleryImageView) {
            int position = galleryImageView.getPosition();
            if (position > -1) {
                GalleryImageViewModel galleryImageViewModel = ProgressPhotosGalleryActivity.this.imageViewModel.get(position);
                Calendar instance = Calendar.getInstance();
                instance.setTime(galleryImageViewModel.getRawDate());
                if (DateTimeUtils.isDateToday(instance)) {
                    ChooseImageDialogFragment newInstance = ChooseImageDialogFragment.newInstance(instance.getTime());
                    newInstance.setOnImportDeviceSelectedListener(ProgressPhotosGalleryActivity.this.onImportDeviceSelectedListener);
                    ProgressPhotosGalleryActivity.this.showDialogFragment(newInstance, ProgressPhotosGalleryActivity.IMPORT_DEVICE_DIALOG_TAG);
                    return;
                }
                ProgressPhotosGalleryActivity.this.getNavigationHelper().withIntent(ImportPhotoActivity.newStartIntent(ProgressPhotosGalleryActivity.this, ImportSource.GalleryScreen, galleryImageViewModel.getResourceId(), galleryImageViewModel.getResourceUid(), galleryImageViewModel.getResourceType(), null, instance)).startActivity(180);
            }
        }
    };
    /* access modifiers changed from: private */
    public OnImportDeviceSelectedListener onImportDeviceSelectedListener = new OnImportDeviceSelectedListener() {
        public void onRemovePhoto() {
        }

        public void onImportDeviceSelected(ImportDevice importDevice) {
            ProgressPhotosGalleryActivity.this.getNavigationHelper().withNoAnimations().withIntent(AddWeightActivity.newStartIntent(ProgressPhotosGalleryActivity.this, ProgressEntryPoint.GalleryScreen, importDevice)).startActivity(25);
        }
    };
    private OnClickListener onLeftPaneClicked = new OnClickListener() {
        public void onClick(View view) {
            ProgressPhotosGalleryActivity progressPhotosGalleryActivity = ProgressPhotosGalleryActivity.this;
            progressPhotosGalleryActivity.setSelectedImageView(progressPhotosGalleryActivity.leftImageView);
            ProgressPhotosGalleryActivity.this.scrollToSelectedImage(ScrollMode.Animate);
        }
    };
    private DialogPositiveListener onNoPhotosErrorOkClickListener = new DialogPositiveListener() {
        public void onClick(Object obj) {
            ProgressPhotosGalleryActivity.this.finish();
        }
    };
    private OnClickListener onRightPaneClicked = new OnClickListener() {
        public void onClick(View view) {
            ProgressPhotosGalleryActivity progressPhotosGalleryActivity = ProgressPhotosGalleryActivity.this;
            progressPhotosGalleryActivity.setSelectedImageView(progressPhotosGalleryActivity.rightImageView);
            ProgressPhotosGalleryActivity.this.scrollToSelectedImage(ScrollMode.Animate);
        }
    };
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;
    @Inject
    Lazy<ProgressService> progressService;
    @BindView(2131363489)
    ViewGroup rightImageContainer;
    @BindView(2131363490)
    ViewGroup rightImageDetails;
    /* access modifiers changed from: private */
    public GalleryImageView rightImageView;
    /* access modifiers changed from: private */
    public Debouncer<Integer> scrollDebouncer = new Debouncer<Integer>(100) {
        /* access modifiers changed from: protected */
        public void onDebounced(Integer num) {
            if (num.intValue() >= 0 && ProgressPhotosGalleryActivity.this.selectedImageView != null) {
                ProgressPhotosGalleryActivity.this.selectedImageView.bind(ProgressPhotosGalleryActivity.this.imageService, ((UserWeightService) ProgressPhotosGalleryActivity.this.userWeightService.get()).getUserCurrentWeightUnit(), num.intValue(), ProgressPhotosGalleryActivity.this.imageViewModel.get(num.intValue()));
                ProgressPhotosGalleryActivity.this.setNextButtonVisibility();
            }
        }
    };
    private long scrollToImageAssociationId = -1;
    /* access modifiers changed from: private */
    public GalleryImageView selectedImageView;
    private boolean showNext = true;
    @BindView(2131363666)
    View singleImageButton;
    @BindView(2131363696)
    View splitImageButton;
    private ToolbarCta toolbarCta = ToolbarCta.ShareIcon;
    @Inject
    Lazy<UserWeightService> userWeightService;
    private GalleryViewMode viewMode;

    public enum ToolbarCta {
        ShareIcon,
        NextText
    }

    public static Intent newStartIntent(Context context, long j, Uri uri, String str, GalleryViewMode galleryViewMode, GalleryDataMode galleryDataMode, ToolbarCta toolbarCta2) {
        Intent intent = new Intent(context, ProgressPhotosGalleryActivity.class);
        intent.putExtra(EXTRA_INITIAL_SELECTION, j);
        intent.putExtra(EXTRA_CONGRATS_IMAGE_URI, uri);
        intent.putExtra(EXTRA_CONGRATS_MESSAGE, str);
        intent.putExtra(EXTRA_VIEW_MODE, galleryViewMode);
        intent.putExtra(EXTRA_DATA_MODE, galleryDataMode);
        intent.putExtra(EXTRA_TOOLBAR_CTA, toolbarCta2);
        return intent;
    }

    public static Intent newStartIntent(Context context, long j, GalleryViewMode galleryViewMode, GalleryDataMode galleryDataMode, ToolbarCta toolbarCta2) {
        return newStartIntent(context, j, null, null, galleryViewMode, galleryDataMode, toolbarCta2);
    }

    public static Intent newStartIntent(Context context, GalleryViewMode galleryViewMode, GalleryDataMode galleryDataMode, ToolbarCta toolbarCta2) {
        return newStartIntent(context, -1, null, null, galleryViewMode, galleryDataMode, toolbarCta2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.isNewCongratsSharingOn = ConfigUtils.isProgressShareCongratsOn(getConfigService());
        loadInstanceState(bundle);
        setContentView((int) R.layout.progress_photos_gallery);
        initViews(bundle);
        initGalleryFragment(bundle);
        initViewModel();
        initEventHandlers();
        setMode(this.viewMode);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setSelectedImageView(this.leftImageView);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (this.toolbarCta == ToolbarCta.ShareIcon) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1000, 0, R.string.share).setIcon(R.drawable.ic_share_white_24dp), 2);
        } else if (this.showNext) {
            MenuItemCompat.setShowAsAction(menu.add(0, 1000, 0, R.string.next), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1000) {
            return super.onOptionsItemSelected(menuItem);
        }
        setBusy(true);
        startScreenshotTask();
        return true;
    }

    private void loadInstanceState(Bundle bundle) {
        Bundle extras = getIntent().getExtras();
        this.viewMode = (GalleryViewMode) BundleUtils.getSerializable(extras, EXTRA_VIEW_MODE, GalleryViewMode.Split, GalleryViewMode.class.getClassLoader());
        this.viewMode = (GalleryViewMode) BundleUtils.getSerializable(bundle, EXTRA_VIEW_MODE, this.viewMode, GalleryViewMode.class.getClassLoader());
        this.scrollToImageAssociationId = BundleUtils.getLong(extras, EXTRA_INITIAL_SELECTION, -1);
        this.scrollToImageAssociationId = BundleUtils.getLong(bundle, EXTRA_INITIAL_SELECTION, this.scrollToImageAssociationId);
        this.toolbarCta = (ToolbarCta) BundleUtils.getSerializable(extras, EXTRA_TOOLBAR_CTA, ToolbarCta.ShareIcon, ToolbarCta.class.getClassLoader());
        if (this.isNewCongratsSharingOn) {
            this.congratsImageUri = (Uri) extras.getParcelable(EXTRA_CONGRATS_IMAGE_URI);
            this.congratsMessage = BundleUtils.getString(extras, EXTRA_CONGRATS_MESSAGE);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.leftImageView.onSaveInstanceState(bundle);
        this.rightImageView.onSaveInstanceState(bundle);
        bundle.putSerializable(EXTRA_VIEW_MODE, this.viewMode);
        bundle.putLong(EXTRA_INITIAL_SELECTION, -1);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i2 == -1 && (i == 180 || i == 25)) {
            GalleryScrollerViewModel galleryScrollerViewModel = this.imageViewModel;
            if (galleryScrollerViewModel != null) {
                galleryScrollerViewModel.load(new Void[0]);
            }
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        super.onViewModelPropertyChanged(observable, i);
        if (i != Property.IMAGE_LIST) {
            return;
        }
        if (this.imageViewModel.getCount() == 0) {
            showNoPhotosError();
            return;
        }
        if (this.isNewCongratsSharingOn && Strings.notEmpty(this.congratsMessage)) {
            this.imageViewModel.addHeaderImages(getHeaderImages());
        }
        this.galleryFragment.notifyDataSetChanged();
        initDefaultImagesIfNecessary();
    }

    private List<GalleryImageViewModel> getHeaderImages() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(GalleryImageViewModel.createGalleryImageViewModelForCongratsImage(this.congratsImageUri.getPath(), this.congratsMessage, getString(R.string.today)));
        return arrayList;
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        if (NO_PHOTOS_ERROR_TAG.equals(str)) {
            ((AlertDialogFragment) dialogFragment).setPositiveListener(this.onNoPhotosErrorOkClickListener);
            return true;
        } else if (!IMPORT_DEVICE_DIALOG_TAG.equals(str)) {
            return super.onRebindDialogFragment(dialogFragment, str);
        } else {
            ((ChooseImageDialogFragment) dialogFragment).setOnImportDeviceSelectedListener(this.onImportDeviceSelectedListener);
            return true;
        }
    }

    @Subscribe
    public void onArtifactGenerated(CompletedEvent completedEvent) {
        if (completedEvent.isFrom(getRunner())) {
            setBusy(false);
            this.leftImageView.onAfterScreenshot();
            this.rightImageView.onAfterScreenshot();
            if (completedEvent.successful()) {
                String str = (String) completedEvent.getResult();
                String dateForViewModel = this.viewMode == GalleryViewMode.Single ? null : getDateForViewModel(this.leftImageView);
                String dateForViewModel2 = this.viewMode == GalleryViewMode.Single ? null : getDateForViewModel(this.rightImageView);
                getNavigationHelper().withIntent(ShareProgressActivity.newStartIntent(this, str, dateForViewModel, dateForViewModel2, this.viewMode, collectImageMetadata(dateForViewModel2), isCongratsImage(this.leftImageView) || isCongratsImage(this.rightImageView))).startActivity(RequestCodes.SHARE_PROGRESS);
                ((ProgressAnalytics) this.progressAnalytics.get()).reportGalleryShareTapped(this.viewMode);
                return;
            }
            showUnableToGenerateArtifactError();
        }
    }

    private boolean isCongratsImage(GalleryImageView galleryImageView) {
        return ImageType.CONGRATS_IMAGE == getImageType(galleryImageView);
    }

    private ImageStatusMetadata collectImageMetadata(String str) {
        String str2;
        String str3 = null;
        if (!ConfigUtils.isProgressPhotosNewsfeedOn(getConfigService())) {
            return null;
        }
        String dateForViewModel = getDateForViewModel(this.leftImageView);
        String displayStringWithoutUnit = LocalizedWeight.getDisplayStringWithoutUnit(getApplicationContext(), this.leftImageView.getWeight(), Weight.POUNDS);
        String imageIdForViewModel = getImageIdForViewModel(this.leftImageView);
        if (this.viewMode == GalleryViewMode.Split) {
            str3 = LocalizedWeight.getDisplayStringWithoutUnit(getApplicationContext(), this.rightImageView.getWeight(), Weight.POUNDS);
            str2 = getImageIdForViewModel(this.rightImageView);
        } else {
            str = null;
            str2 = null;
        }
        ImageStatusMetadata imageStatusMetadata = new ImageStatusMetadata();
        imageStatusMetadata.setDate1(dateForViewModel);
        imageStatusMetadata.setMeasurementValue1(displayStringWithoutUnit);
        imageStatusMetadata.setImageId1(imageIdForViewModel);
        imageStatusMetadata.setDate2(str);
        imageStatusMetadata.setMeasurementValue2(str3);
        imageStatusMetadata.setImageId2(str2);
        return imageStatusMetadata;
    }

    private void showNoPhotosError() {
        if (getSupportFragmentManager().findFragmentByTag(NO_PHOTOS_ERROR_TAG) == null) {
            showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.progress_photos)).setMessage((int) R.string.progress_photos_no_photos_error)).setPositiveText(R.string.ok, this.onNoPhotosErrorOkClickListener), NO_PHOTOS_ERROR_TAG);
        }
    }

    private void showUnableToGenerateArtifactError() {
        if (getSupportFragmentManager().findFragmentByTag(ASSET_IMAGE_FAILED_TAG) == null) {
            showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.progress_photos)).setMessage((int) R.string.progress_photos_artifact_image_error)).setPositiveText(R.string.ok, null), ASSET_IMAGE_FAILED_TAG);
        }
    }

    private void bindDefaultImage(GalleryImageView galleryImageView, Weight weight, int i) {
        int count = this.imageViewModel.getCount() - 1;
        if (i == -1) {
            i = galleryImageView.getPosition();
        }
        int clamp = NumberUtils.clamp(i, 0, count);
        galleryImageView.bind(this.imageService, weight, clamp, this.imageViewModel.get(clamp));
    }

    private void initDefaultImagesIfNecessary() {
        int count = this.imageViewModel.getCount();
        if (count >= 0) {
            Weight userCurrentWeightUnit = ((UserWeightService) this.userWeightService.get()).getUserCurrentWeightUnit();
            int position = this.leftImageView.getPosition();
            long j = this.scrollToImageAssociationId;
            if (j != -1) {
                position = this.imageViewModel.getIndexOf(j);
                this.scrollToImageAssociationId = -1;
            }
            int position2 = this.rightImageView.getPosition();
            if (position2 == -1) {
                position2 = count - 1;
            }
            bindDefaultImage(this.leftImageView, userCurrentWeightUnit, position);
            bindDefaultImage(this.rightImageView, userCurrentWeightUnit, position2);
            GalleryImageView galleryImageView = this.selectedImageView;
            if (galleryImageView == null) {
                galleryImageView = this.leftImageView;
            }
            this.galleryFragment.setPosition(galleryImageView.getPosition());
        }
    }

    private void initViewModel() {
        this.imageViewModel = (GalleryScrollerViewModel) getViewModel();
        if (this.imageViewModel == null) {
            GalleryScrollerViewModel galleryScrollerViewModel = new GalleryScrollerViewModel(this, this.imageService, this.progressService, (GalleryDataMode) BundleUtils.getSerializable(getIntent().getExtras(), EXTRA_DATA_MODE, GalleryDataMode.View, GalleryDataMode.class.getClassLoader()), getRunner());
            this.imageViewModel = (GalleryScrollerViewModel) setViewModel(galleryScrollerViewModel);
        }
        this.imageViewModel.loadIfNotLoaded(new Void[0]);
        this.galleryFragment.setImageMetadataProvider(this.imageViewModel);
    }

    private void initGalleryFragment(Bundle bundle) {
        if (bundle == null) {
            this.galleryFragment = HorizontalGalleryFragment.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.gallery_scroller, this.galleryFragment, GALLERY_FRAGMENT_TAG).commit();
        } else {
            this.galleryFragment = (HorizontalGalleryFragment) getSupportFragmentManager().findFragmentByTag(GALLERY_FRAGMENT_TAG);
        }
        if (this.leftImageView.getPosition() != -1) {
            this.galleryFragment.setPosition(this.leftImageView.getPosition());
        }
    }

    private void initViews(Bundle bundle) {
        GalleryImageView galleryImageView = new GalleryImageView(bundle, LEFT_IMAGE_BUNDLE_KEY_PREFIX, this.handler, this.leftImageContainer, this.leftImageDetails, this.onImportClicked);
        this.leftImageView = galleryImageView;
        GalleryImageView galleryImageView2 = new GalleryImageView(bundle, RIGHT_IMAGE_BUNDLE_KEY_PREFIX, this.handler, this.rightImageContainer, this.rightImageDetails, this.onImportClicked);
        this.rightImageView = galleryImageView2;
        setSelectedImageView(this.leftImageView);
    }

    private void startScreenshotTask() {
        this.leftImageView.onBeforeScreenshot();
        this.rightImageView.onBeforeScreenshot();
        new GenerateArtifactImageTask(CACHE_FILENAME, this.imagesContainer).run(getRunner());
    }

    private void initEventHandlers() {
        this.singleImageButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProgressPhotosGalleryActivity.this.setMode(GalleryViewMode.Single);
                ((ProgressAnalytics) ProgressPhotosGalleryActivity.this.progressAnalytics.get()).reportViewTapped(TapTarget.GallerySinglePane);
            }
        });
        this.splitImageButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ProgressPhotosGalleryActivity.this.setMode(GalleryViewMode.Split);
                ((ProgressAnalytics) ProgressPhotosGalleryActivity.this.progressAnalytics.get()).reportViewTapped(TapTarget.GallerySplitPane);
            }
        });
        this.leftImageView.setOnClickListener(this.onLeftPaneClicked);
        this.rightImageView.setOnClickListener(this.onRightPaneClicked);
        this.galleryFragment.setOnPositionChangedListener(new OnPositionChangedListener() {
            public void onChanged(int i, boolean z) {
                if (!z) {
                    ProgressPhotosGalleryActivity.this.scrollDebouncer.call(Integer.valueOf(i));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void scrollToSelectedImage(ScrollMode scrollMode) {
        GalleryImageView galleryImageView = this.selectedImageView;
        if (galleryImageView != null && galleryImageView.getPosition() != -1) {
            this.galleryFragment.setPosition(this.selectedImageView.getPosition(), scrollMode);
        }
    }

    /* access modifiers changed from: private */
    public void setMode(GalleryViewMode galleryViewMode) {
        boolean z = true;
        this.singleImageButton.setEnabled(galleryViewMode == GalleryViewMode.Split);
        this.splitImageButton.setEnabled(galleryViewMode == GalleryViewMode.Single);
        GalleryImageView galleryImageView = this.rightImageView;
        if (galleryViewMode != GalleryViewMode.Split) {
            z = false;
        }
        galleryImageView.setVisible(z);
        if (galleryViewMode == GalleryViewMode.Single) {
            setSelectedImageView(this.leftImageView);
            this.galleryFragment.setPosition(this.leftImageView.getPosition(), ScrollMode.Animate);
        }
        this.viewMode = galleryViewMode;
        setNextButtonVisibility();
    }

    /* access modifiers changed from: private */
    public void setSelectedImageView(GalleryImageView galleryImageView) {
        this.leftImageView.setSelected(false);
        this.rightImageView.setSelected(false);
        this.selectedImageView = galleryImageView;
        this.selectedImageView.setSelected(true);
    }

    private String getDateForViewModel(GalleryImageView galleryImageView) {
        if (galleryImageView == null || galleryImageView.getPosition() < 0) {
            return null;
        }
        return this.imageViewModel.get(galleryImageView.getPosition()).getDatabaseDate();
    }

    private String getImageIdForViewModel(GalleryImageView galleryImageView) {
        if (galleryImageView == null || galleryImageView.getPosition() < 0) {
            return null;
        }
        return this.imageViewModel.get(galleryImageView.getPosition()).getImageId();
    }

    private ImageType getImageType(GalleryImageView galleryImageView) {
        if (galleryImageView == null || galleryImageView.getPosition() < 0) {
            return ImageType.MEASUREMENT_IMAGE;
        }
        return this.imageViewModel.get(galleryImageView.getPosition()).getImageType();
    }

    /* access modifiers changed from: private */
    public void setNextButtonVisibility() {
        boolean z;
        GalleryImageView galleryImageView = this.leftImageView;
        boolean z2 = false;
        boolean z3 = galleryImageView != null && galleryImageView.hasImageAssociation();
        if (this.viewMode == GalleryViewMode.Split) {
            GalleryImageView galleryImageView2 = this.rightImageView;
            z = galleryImageView2 != null && galleryImageView2.hasImageAssociation();
        } else {
            z = true;
        }
        if (z3 && z) {
            z2 = true;
        }
        this.showNext = z2;
        invalidateOptionsMenu();
    }
}
