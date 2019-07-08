package com.myfitnesspal.feature.progress.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.ArtifactType;
import com.myfitnesspal.feature.progress.constants.GalleryViewMode;
import com.myfitnesspal.feature.progress.constants.ShareTarget;
import com.myfitnesspal.feature.progress.model.ImageStatusMetadata;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.feature.progress.task.GenerateArtifactImageTask;
import com.myfitnesspal.feature.progress.task.LoadArtifactDataTask;
import com.myfitnesspal.feature.progress.task.LoadArtifactDataTask.CompletedEvent;
import com.myfitnesspal.feature.progress.ui.adapter.ShareProgressAdapter;
import com.myfitnesspal.feature.progress.ui.adapter.ShareProgressAdapter.ShareViewHolder;
import com.myfitnesspal.feature.progress.ui.adapter.ShareProgressAdapter.ViewHolder;
import com.myfitnesspal.feature.progress.ui.viewmodel.ArtifactViewModel;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragment;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class ShareProgressActivity extends MfpActivity {
    private static final int ACTION_NEXT = 100;
    private static final String ASSET_IMAGE_FAILED_TAG = "asset_image_failed";
    private static final String EXTRA_CUSTOM_CAPTION = "extra_custom_caption";
    private static final String EXTRA_CUSTOM_CAPTION_VISIBLE = "extra_custom_caption_visible";
    public static final String EXTRA_FROM_DATE = "extra_from_date";
    public static final String EXTRA_PROGRESS_FILENAME = "extra_progress_filename";
    private static final String EXTRA_SHARE_FILENAME = "extra_share_filename";
    public static final String EXTRA_TO_DATE = "extra_to_date";
    public static final String EXTRA_VIEW_MODE = "extra_view_mode";
    private static final String IMAGE_CONTENT_TYPE = "image/png";
    private static final String SHARE_FILENAME = "mfp-progress-photos-share-artifact-%d.png";
    private static final String SHARE_HASHTAG = "#MyFitnessPal";
    /* access modifiers changed from: private */
    public View activityRoot;
    /* access modifiers changed from: private */
    public ShareProgressAdapter adapter;
    private boolean artifactsGenerated;
    @Nullable
    @BindView(2131362259)
    Switch captionSwitch;
    @Nullable
    @BindView(2131362260)
    View captionSwitchContainer;
    @Inject
    Lazy<ConfigService> configService;
    private List<ImageView> dots;
    @Nullable
    @BindView(2131362352)
    LinearLayout dotsContainer;
    @Nullable
    @BindView(2131362551)
    View facebook;
    @Nullable
    @BindView(2131362553)
    View facebookDivider;
    private String fromDate;
    private String imageFilename;
    private ImageStatusMetadata imageStatusMetadata;
    @Nullable
    @BindView(2131362825)
    View instagram;
    @Nullable
    @BindView(2131362826)
    View instagramDivider;
    private boolean isOneImageCongrats;
    private boolean isProgressPhotosNewsFeedOn;
    private OnGlobalLayoutListener onGlobalLayoutListener = new OnGlobalLayoutListener() {
        private static final int DEFAULT_KEYBOARD_HEIGHT_DP = 100;
        private final int keyboardHeightDpHeurestic;
        private boolean keyboardProbablyVisible;
        private final Rect visibleRect;

        {
            this.keyboardHeightDpHeurestic = (VERSION.SDK_INT >= 21 ? 48 : 0) + 100;
            this.visibleRect = new Rect();
        }

        public void onGlobalLayout() {
            boolean z = true;
            int applyDimension = (int) TypedValue.applyDimension(1, (float) this.keyboardHeightDpHeurestic, ShareProgressActivity.this.getResources().getDisplayMetrics());
            ShareProgressActivity.this.activityRoot.getWindowVisibleDisplayFrame(this.visibleRect);
            if (ShareProgressActivity.this.activityRoot.getHeight() - this.visibleRect.height() < applyDimension) {
                z = false;
            }
            if (z != this.keyboardProbablyVisible) {
                this.keyboardProbablyVisible = z;
                ShareProgressActivity.this.onKeyboardVisibilityChanged(z);
            }
        }
    };
    @BindView(2131364186)
    ViewPager pager;
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;
    @Inject
    Lazy<ProgressService> progressService;
    @BindView(2131363314)
    View progressbar;
    private Rect rect = new Rect();
    private Bundle restoredInstanceState;
    private ShareTarget selectedShareTarget;
    private String shareFilename;
    private Map<ShareTarget, Intent> shareIntents;
    private String toDate;
    @Nullable
    @BindView(2131363994)
    View twitter;

    public static Intent newStartIntent(Context context, String str, String str2, String str3, GalleryViewMode galleryViewMode, ImageStatusMetadata imageStatusMetadata2, boolean z) {
        return new Intent(context, ShareProgressActivity.class).putExtra(EXTRA_PROGRESS_FILENAME, str).putExtra(EXTRA_FROM_DATE, str2).putExtra(EXTRA_TO_DATE, str3).putExtra(EXTRA_VIEW_MODE, galleryViewMode).putExtra(Extras.IMAGE_STATUS_METADATA, imageStatusMetadata2).putExtra(Extras.IS_CONGRATS_IMAGE, z);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.restoredInstanceState = bundle;
        ((ProgressAnalytics) this.progressAnalytics.get()).reportArtifactScreenViewed();
        this.isProgressPhotosNewsFeedOn = ConfigUtils.isProgressPhotosNewsfeedOn((ConfigService) this.configService.get());
        if (this.isProgressPhotosNewsFeedOn) {
            setContentView((int) R.layout.progress_share_activity_v2);
        } else {
            setContentView((int) R.layout.progress_share_activity);
        }
        this.activityRoot = findViewById(16908290);
        this.activityRoot.getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
        Bundle extras = getIntent().getExtras();
        this.imageFilename = extras.getString(EXTRA_PROGRESS_FILENAME);
        this.fromDate = extras.getString(EXTRA_FROM_DATE);
        this.toDate = extras.getString(EXTRA_TO_DATE);
        this.imageStatusMetadata = (ImageStatusMetadata) extras.getParcelable(Extras.IMAGE_STATUS_METADATA);
        this.shareFilename = BundleUtils.getString(bundle, EXTRA_SHARE_FILENAME);
        this.isOneImageCongrats = BundleUtils.getBoolean(extras, Extras.IS_CONGRATS_IMAGE);
        this.shareIntents = ShareTarget.getAvailableShareTargets(this, IMAGE_CONTENT_TYPE);
        showProgressBar(true);
        if (!this.isProgressPhotosNewsFeedOn) {
            initViewsAndEventHandlers();
        }
        loadDataModel();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(EXTRA_SHARE_FILENAME, this.shareFilename);
        ShareProgressAdapter shareProgressAdapter = this.adapter;
        if (shareProgressAdapter != null) {
            bundle.putString(EXTRA_CUSTOM_CAPTION, shareProgressAdapter.getCustomCaption());
            bundle.putBoolean(EXTRA_CUSTOM_CAPTION_VISIBLE, this.adapter.isCustomCaptionVisible());
        }
    }

    private void showProgressBar(boolean z) {
        ViewUtils.setVisible(z, this.progressbar);
        ViewUtils.setVisible(!z, this.pager);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.activityRoot.getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        if (isFinishing()) {
            checkDeleteShareFile();
            reportArtifactDataType();
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        checkClearFocusOnTouchEvent((int) motionEvent.getX(), (int) motionEvent.getY());
        return super.dispatchTouchEvent(motionEvent);
    }

    @Subscribe
    public void onArtifactDataLoaded(CompletedEvent completedEvent) {
        showProgressBar(false);
        if (completedEvent.successful()) {
            initPager((Bitmap) ((Tuple2) completedEvent.getResult()).getItem1(), (List) ((Tuple2) completedEvent.getResult()).getItem2());
            this.artifactsGenerated = true;
            invalidateOptionsMenu();
            return;
        }
        showUnableToGenerateArtifactError();
    }

    @Subscribe
    public void onGenerateArtifactTaskCompleted(GenerateArtifactImageTask.CompletedEvent completedEvent) {
        setBusy(false);
        if (completedEvent.successful()) {
            Uri fromFile = Uri.fromFile(new File((String) completedEvent.getResult()));
            GalleryViewMode galleryViewMode = (GalleryViewMode) ExtrasUtils.getSerializable(getIntent(), EXTRA_VIEW_MODE, GalleryViewMode.class.getClassLoader());
            ShareTarget shareTarget = this.selectedShareTarget;
            if (shareTarget == null) {
                collectImageMetadata();
                reportArtifactScreenNextTapped(galleryViewMode);
                getNavigationHelper().withIntent(StatusUpdateActivity.newStartIntentForProgressPhotoShare(this, fromFile, getSelectedArtifactType().getAnalyticsValue(), galleryViewMode.getAnaltyicsValue(), this.imageStatusMetadata, this.isOneImageCongrats)).startActivity(RequestCodes.PROGRESS_STATUS_UPDATE);
                return;
            }
            Intent intent = (Intent) ParcelableUtil.clone((Parcelable) this.shareIntents.get(shareTarget), Intent.CREATOR);
            intent.setType(IMAGE_CONTENT_TYPE);
            intent.putExtra("android.intent.extra.STREAM", fromFile);
            intent.putExtra("android.intent.extra.SUBJECT", SHARE_HASHTAG);
            if (this.selectedShareTarget == ShareTarget.More) {
                intent = Intent.createChooser(intent, getString(R.string.progress_photos_share_more_title));
            }
            reportArtifactShareStarted(galleryViewMode);
            startActivity(intent);
            return;
        }
        showUnableToGenerateArtifactError();
    }

    private void collectImageMetadata() {
        this.imageStatusMetadata.setArtifactDataType(getSelectedArtifactType().getAnalyticsValue());
        this.imageStatusMetadata.setDataPoint(getDataPointforSelectedArtifact());
    }

    private void reportArtifactShareStarted(GalleryViewMode galleryViewMode) {
        if (this.selectedShareTarget != null && this.adapter != null) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportShareProgressShareStarted(this.selectedShareTarget, getSelectedArtifactType(), this.adapter.isCustomCaptionVisible(), galleryViewMode, this.isOneImageCongrats);
        }
    }

    private void reportArtifactScreenNextTapped(GalleryViewMode galleryViewMode) {
        ((ProgressAnalytics) this.progressAnalytics.get()).reportArtifactScreenNextTapped(getSelectedArtifactType(), galleryViewMode, this.isOneImageCongrats);
    }

    private void reportArtifactDataType() {
        ((ProgressAnalytics) this.progressAnalytics.get()).reportShareProgressArtifactView(getSelectedArtifactType());
    }

    private ArtifactType getSelectedArtifactType() {
        int currentItem = this.pager.getCurrentItem();
        if (currentItem >= 0) {
            ShareProgressAdapter shareProgressAdapter = this.adapter;
            if (shareProgressAdapter != null) {
                return shareProgressAdapter.getViewAtPosition(currentItem).getModel().getArtifactType();
            }
        }
        return null;
    }

    private String getDataPointforSelectedArtifact() {
        int currentItem = this.pager.getCurrentItem();
        if (currentItem >= 0) {
            ShareProgressAdapter shareProgressAdapter = this.adapter;
            if (shareProgressAdapter != null) {
                return shareProgressAdapter.getViewAtPosition(currentItem).getModel().getValue();
            }
        }
        return null;
    }

    private void showUnableToGenerateArtifactError() {
        if (getSupportFragmentManager().findFragmentByTag(ASSET_IMAGE_FAILED_TAG) == null) {
            showDialogFragment(((AlertDialogFragment) ((AlertDialogFragment) AlertDialogFragment.newInstance().setTitle(R.string.progress_photos)).setMessage((int) R.string.progress_photos_artifact_image_error)).setPositiveText(R.string.ok, null), ASSET_IMAGE_FAILED_TAG);
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (this.isProgressPhotosNewsFeedOn && this.artifactsGenerated) {
            MenuItemCompat.setShowAsAction(menu.add(0, 100, 0, R.string.next), 2);
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 100) {
            generateShareArtifact(null);
        }
        return true;
    }

    private void initPager(Bitmap bitmap, List<ArtifactViewModel> list) {
        this.pager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.progress_share_pager_item_margin));
        this.adapter = new ShareProgressAdapter(bitmap, list, getMessageBus(), this.isProgressPhotosNewsFeedOn);
        this.pager.setAdapter(this.adapter);
        Bundle bundle = this.restoredInstanceState;
        if (bundle != null) {
            this.adapter.setCustomCaption(bundle.getString(EXTRA_CUSTOM_CAPTION));
            this.adapter.setCustomCaptionVisible(this.restoredInstanceState.getBoolean(EXTRA_CUSTOM_CAPTION_VISIBLE));
            this.restoredInstanceState = null;
        }
        ViewUtils.setVisible(this.captionSwitchContainer);
        initDotsContainer();
    }

    private void initDotsContainer() {
        if (this.dotsContainer != null) {
            this.dots = new ArrayList();
            for (int i = 0; i < this.adapter.getCount(); i++) {
                ImageView imageView = new ImageView(this);
                imageView.setImageResource(R.drawable.ic_inactive_page_dot);
                this.dotsContainer.addView(imageView, new LayoutParams(-2, -2));
                this.dots.add(imageView);
            }
            this.pager.addOnPageChangeListener(new OnPageChangeListener() {
                public void onPageScrollStateChanged(int i) {
                }

                public void onPageScrolled(int i, float f, int i2) {
                }

                public void onPageSelected(int i) {
                    ShareProgressActivity.this.selectDot(i);
                }
            });
            selectDot(0);
        }
    }

    public void selectDot(int i) {
        getResources();
        int i2 = 0;
        while (i2 < this.adapter.getCount()) {
            ((ImageView) this.dots.get(i2)).setImageResource(i2 == i ? R.drawable.ic_active_page_dot : R.drawable.ic_inactive_page_dot);
            i2++;
        }
    }

    private void loadDataModel() {
        LoadArtifactDataTask loadArtifactDataTask = new LoadArtifactDataTask(this.progressService, this.fromDate, this.toDate, this.imageFilename, this.isOneImageCongrats);
        loadArtifactDataTask.setCacheMode(CacheMode.CacheOnSuccess).run(getRunner());
    }

    /* access modifiers changed from: private */
    public void generateShareArtifact(ShareTarget shareTarget) {
        ShareProgressAdapter shareProgressAdapter = this.adapter;
        if (shareProgressAdapter != null) {
            ShareViewHolder viewAtPosition = shareProgressAdapter.getViewAtPosition(this.pager.getCurrentItem());
            if (viewAtPosition != null) {
                setBusy(true);
                checkDeleteShareFile();
                String format = String.format(SHARE_FILENAME, new Object[]{Long.valueOf(System.currentTimeMillis())});
                this.selectedShareTarget = shareTarget;
                this.shareFilename = GenerateArtifactImageTask.getOutputFilePath(this, format);
                new GenerateArtifactImageTask(format, viewAtPosition.getRoot()).run(getRunner());
            }
        }
    }

    private void initViewsAndEventHandlers() {
        initButton(this.instagram, this.instagramDivider, ShareTarget.Instagram, R.string.progress_share_instagram, R.drawable.ic_asset_share_instagram);
        initButton(this.facebook, this.facebookDivider, ShareTarget.Facebook, R.string.progress_share_facebook, R.drawable.ic_asset_share_facebook);
        initButton(this.twitter, null, ShareTarget.More, this.shareIntents.size() > 1 ? R.string.progress_share_more : R.string.progress_share_share, this.shareIntents.size() > 1 ? R.drawable.ic_asset_share_more : R.drawable.ic_progress_share);
        this.captionSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (ShareProgressActivity.this.adapter != null) {
                    ShareProgressActivity.this.adapter.setCustomCaption(z ? "" : null);
                    ShareProgressActivity.this.adapter.setCustomCaptionVisible(z);
                    if (z) {
                        ShareProgressActivity.this.adapter.setCursorVisible(true);
                        ((ViewHolder) ShareProgressActivity.this.adapter.getViewAtPosition(ShareProgressActivity.this.pager.getCurrentItem())).captionText.requestFocus();
                        ShareProgressActivity.this.getImmHelper().showSoftInput();
                    }
                }
                ((ProgressAnalytics) ShareProgressActivity.this.progressAnalytics.get()).reportShareProgressMessageToggle(z);
            }
        });
    }

    private void initButton(View view, View view2, final ShareTarget shareTarget, int i, int i2) {
        if (this.shareIntents.containsKey(shareTarget)) {
            ((TextView) view.findViewById(R.id.caption)).setText(i);
            ((ImageView) view.findViewById(R.id.icon)).setImageResource(i2);
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ShareProgressActivity.this.generateShareArtifact(shareTarget);
                }
            });
            return;
        }
        ViewUtils.setVisible(false, view);
        ViewUtils.setVisible(false, view2);
    }

    /* access modifiers changed from: private */
    public void onKeyboardVisibilityChanged(boolean z) {
        ViewUtils.setVisible(!z, this.captionSwitchContainer);
        if (!z) {
            clearFocusAndHideSoftInput();
            checkHideCustomCaption();
        }
    }

    private void checkHideCustomCaption() {
        if (this.adapter.isCustomCaptionVisible() && Strings.isEmpty(this.adapter.getCustomCaption())) {
            this.adapter.setCustomCaptionVisible(false);
            this.captionSwitch.setChecked(false);
        }
    }

    private void checkClearFocusOnTouchEvent(int i, int i2) {
        ShareProgressAdapter shareProgressAdapter = this.adapter;
        if (shareProgressAdapter != null && shareProgressAdapter.isCustomCaptionVisible()) {
            ViewHolder viewHolder = (ViewHolder) this.adapter.getViewAtPosition(this.pager.getCurrentItem());
            if (viewHolder != null) {
                viewHolder.captionContainer.getGlobalVisibleRect(this.rect);
                if (this.rect.contains(i, i2)) {
                    this.adapter.setCursorVisible(true);
                    return;
                }
                clearFocusAndHideSoftInput();
                checkHideCustomCaption();
            }
        }
    }

    private void clearFocusAndHideSoftInput() {
        ShareProgressAdapter shareProgressAdapter = this.adapter;
        if (shareProgressAdapter != null) {
            shareProgressAdapter.setCursorVisible(false);
        }
        View currentFocus = getCurrentFocus();
        if (currentFocus != null) {
            currentFocus.clearFocus();
        }
        getImmHelper().hideSoftInput();
    }

    private void checkDeleteShareFile() {
        if (Strings.notEmpty(this.shareFilename)) {
            File file = new File(this.shareFilename);
            if (file.exists()) {
                file.delete();
            }
            this.shareFilename = null;
        }
    }
}
