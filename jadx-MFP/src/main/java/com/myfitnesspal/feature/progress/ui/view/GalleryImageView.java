package com.myfitnesspal.feature.progress.ui.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryImageViewModel;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryImageViewModel.ImageType;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.text.SimpleDateFormat;
import uk.co.senab.photoview.PhotoViewAttacher;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

public class GalleryImageView {
    private static final String EXTRA_DISPLAY_MATRIX = "display_matrix";
    private static final String EXTRA_DISPLAY_SCALE = "display_scale";
    private static final String EXTRA_LAST_POSITION_KEY = "last_position";
    private static final RequestOptions GLIDE_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).dontTransform().error(R.drawable.ic_photo_camera_black_24dp);
    private static final SimpleDateFormat IMPORT_DATE_FORMAT = new SimpleDateFormat("MMM yyyy");
    private static final int RESTORE_PHOTO_VIEW_STATE_DELAY_MS = 100;
    private final String bundlePrefix;
    private final Context context;
    private final View detailsContainer;
    private final TextView detailsSubtext;
    private final TextView detailsText;
    /* access modifiers changed from: private */
    public final Handler handler;
    private boolean hasImageAssociation;
    private final View imageBorder;
    private final View imageContainer;
    /* access modifiers changed from: private */
    public String imageUrl;
    private final TouchImageView imageView;
    private final View importArea;
    private final ImageView importIcon;
    private final TextView importText;
    /* access modifiers changed from: private */
    public OnImportClickListener onImportClickListener;
    private final View overlay;
    /* access modifiers changed from: private */
    public OnTouchListener photoAttacherListener;
    /* access modifiers changed from: private */
    public PhotoViewAttacher photoViewAttacher;
    /* access modifiers changed from: private */
    public PhotoViewState photoViewState = new PhotoViewState();
    private int position = -1;
    /* access modifiers changed from: private */
    public final View progressbar;
    private boolean selected = false;
    /* access modifiers changed from: private */
    public Runnable setZoomableRunnable = new Runnable() {
        public void run() {
            GalleryImageView.this.photoViewAttacher.setZoomable(true);
            GalleryImageView.this.photoViewAttacher.update();
            GalleryImageView.this.photoViewState.apply(GalleryImageView.this.photoViewAttacher);
        }
    };
    private final View viewArea;
    private final LocalizedWeight weight = LocalizedWeight.fromPounds(0.0d);

    public interface OnImportClickListener {
        void onImportClicked(GalleryImageView galleryImageView);
    }

    private static class PhotoViewState {
        private Matrix restoredMatrix;
        private float restoredScale;

        private PhotoViewState() {
        }

        public void save(Bundle bundle, String str, PhotoViewAttacher photoViewAttacher) {
            float[] fArr = new float[9];
            photoViewAttacher.getDisplayMatrix().getValues(fArr);
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(GalleryImageView.EXTRA_DISPLAY_MATRIX);
            bundle.putFloatArray(sb.toString(), fArr);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(GalleryImageView.EXTRA_DISPLAY_SCALE);
            bundle.putFloat(sb2.toString(), photoViewAttacher.getScale());
        }

        public void load(Bundle bundle, String str) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(GalleryImageView.EXTRA_DISPLAY_MATRIX);
            float[] floatArray = bundle.getFloatArray(sb.toString());
            if (floatArray != null && floatArray.length == 9) {
                this.restoredMatrix = new Matrix();
                this.restoredMatrix.setValues(floatArray);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(GalleryImageView.EXTRA_DISPLAY_SCALE);
            this.restoredScale = bundle.getFloat(sb2.toString());
        }

        public void apply(PhotoViewAttacher photoViewAttacher) {
            Matrix matrix = this.restoredMatrix;
            if (matrix != null) {
                photoViewAttacher.setDisplayMatrix(matrix);
                photoViewAttacher.setScale(this.restoredScale);
                this.restoredMatrix = null;
            }
        }
    }

    public GalleryImageView(Bundle bundle, String str, Handler handler2, ViewGroup viewGroup, ViewGroup viewGroup2, OnImportClickListener onImportClickListener2) {
        this.bundlePrefix = str;
        this.handler = handler2;
        this.context = viewGroup.getContext().getApplicationContext();
        this.onImportClickListener = onImportClickListener2;
        this.imageContainer = viewGroup;
        this.imageView = (TouchImageView) viewGroup.findViewById(R.id.image);
        this.progressbar = viewGroup.findViewById(R.id.progress);
        this.detailsContainer = viewGroup2;
        this.detailsText = (TextView) viewGroup2.findViewById(R.id.image_header_text);
        this.detailsSubtext = (TextView) viewGroup2.findViewById(R.id.image_header_subtext);
        this.overlay = viewGroup.findViewById(R.id.shadow_overlay);
        this.importArea = viewGroup.findViewById(R.id.import_area);
        this.viewArea = viewGroup.findViewById(R.id.view_area);
        this.importText = (TextView) viewGroup.findViewById(R.id.import_text);
        this.importIcon = (ImageView) viewGroup.findViewById(R.id.import_icon);
        this.imageBorder = viewGroup.findViewById(R.id.image_border);
        this.photoViewAttacher = new PhotoViewAttacher(this.imageView);
        this.photoViewAttacher.setScaleType(ScaleType.CENTER_CROP);
        this.photoAttacherListener = this.imageView.getOnTouchListener();
        initEventHandlers();
        onRestoreInstanceState(bundle);
        setSelected(false);
        ViewUtils.setVisible(false, this.progressbar);
    }

    public void onBeforeScreenshot() {
        setSelected(false);
        this.imageView.destroyDrawingCache();
        this.imageView.buildDrawingCache();
    }

    public void onAfterScreenshot() {
        ViewUtils.setVisible(this.selected, this.overlay);
    }

    public void onSaveInstanceState(Bundle bundle) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.bundlePrefix);
        sb.append(EXTRA_LAST_POSITION_KEY);
        bundle.putInt(sb.toString(), this.position);
        this.photoViewState.save(bundle, this.bundlePrefix, this.photoViewAttacher);
    }

    private void onRestoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.bundlePrefix);
            sb.append(EXTRA_LAST_POSITION_KEY);
            this.position = bundle.getInt(sb.toString(), -1);
            this.photoViewState.load(bundle, this.bundlePrefix);
        }
    }

    public void setSelected(boolean z) {
        this.selected = z;
        ViewUtils.setVisible(z, this.overlay);
        setBorderVisibility(z);
    }

    private void setBorderVisibility(boolean z) {
        ViewUtils.setVisible(z, this.imageBorder);
    }

    public void setOnClickListener(final OnClickListener onClickListener) {
        this.imageContainer.setOnClickListener(onClickListener);
        this.detailsContainer.setOnClickListener(onClickListener);
        this.progressbar.setOnClickListener(onClickListener);
        this.imageContainer.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (onClickListener != null && motionEvent.getAction() == 0) {
                    onClickListener.onClick(view);
                }
                return false;
            }
        });
        this.imageView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (onClickListener != null && motionEvent.getAction() == 0) {
                    onClickListener.onClick(view);
                }
                if (GalleryImageView.this.photoAttacherListener != null) {
                    return GalleryImageView.this.photoAttacherListener.onTouch(view, motionEvent);
                }
                return false;
            }
        });
        this.photoViewAttacher.setOnViewTapListener(new OnViewTapListener() {
            public void onViewTap(View view, float f, float f2) {
                OnClickListener onClickListener = onClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(view);
                }
            }
        });
    }

    public int getPosition() {
        return this.position;
    }

    public LocalizedWeight getWeight() {
        return this.weight;
    }

    public boolean hasImageAssociation() {
        return this.hasImageAssociation;
    }

    public void bind(Lazy<ImageService> lazy, Weight weight2, int i, GalleryImageViewModel galleryImageViewModel) {
        this.position = i;
        if (galleryImageViewModel.getImageType() == ImageType.CONGRATS_IMAGE) {
            this.detailsText.setText(galleryImageViewModel.getCongratsMessage());
        } else {
            this.weight.setValue(Weight.POUNDS, galleryImageViewModel.getResourceValue());
            this.detailsText.setText(LocalizedWeight.getDisplayString(this.context, this.weight, weight2));
        }
        this.detailsSubtext.setText(galleryImageViewModel.getFormattedDate());
        this.hasImageAssociation = galleryImageViewModel.hasImageAssociation();
        if (this.hasImageAssociation) {
            bindAsImage(lazy, galleryImageViewModel);
        } else {
            bindAsImport(galleryImageViewModel);
        }
    }

    private void bindAsImage(Lazy<ImageService> lazy, GalleryImageViewModel galleryImageViewModel) {
        ViewUtils.setVisible(false, this.importArea);
        ViewUtils.setVisible(true, this.viewArea);
        String fullSizedUri = galleryImageViewModel.getFullSizedUri(lazy);
        if (Strings.notEmpty(fullSizedUri) && !fullSizedUri.equals(this.imageUrl)) {
            this.imageUrl = fullSizedUri;
            this.imageView.setImageDrawable(null);
            ViewUtils.setVisible(true, this.progressbar);
            this.photoViewAttacher.setZoomable(false);
            this.photoViewAttacher.update();
            this.photoViewAttacher.setScaleType(ScaleType.CENTER);
            Glide.with(this.context).load(fullSizedUri).apply(GLIDE_OPTIONS).listener(new RequestListener<Drawable>() {
                public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                    GalleryImageView.this.imageUrl = null;
                    ViewUtils.setVisible(false, GalleryImageView.this.progressbar);
                    return false;
                }

                public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                    ViewUtils.setVisible(false, GalleryImageView.this.progressbar);
                    GalleryImageView.this.photoViewAttacher.setScaleType(ScaleType.CENTER_CROP);
                    GalleryImageView.this.handler.postDelayed(GalleryImageView.this.setZoomableRunnable, 100);
                    return false;
                }
            }).into((ImageView) this.imageView);
        }
    }

    private void initEventHandlers() {
        this.overlay.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return false;
            }
        });
        this.importArea.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (GalleryImageView.this.onImportClickListener != null) {
                    GalleryImageView.this.onImportClickListener.onImportClicked(GalleryImageView.this);
                }
            }
        });
    }

    private void bindAsImport(GalleryImageViewModel galleryImageViewModel) {
        ViewUtils.setVisible(true, this.importArea);
        ViewUtils.setVisible(false, this.viewArea);
        if (DateTimeUtils.isDateToday(galleryImageViewModel.getRawDate())) {
            this.importText.setText(R.string.progress_photos_import_photo_from_today);
            this.importIcon.setImageResource(R.drawable.ic_photo_camera_black_24dp);
            return;
        }
        this.importText.setText(this.context.getString(R.string.progress_photos_import_photo_from_date, new Object[]{IMPORT_DATE_FORMAT.format(galleryImageViewModel.getRawDate())}));
        this.importIcon.setImageResource(R.drawable.ic_camera_roll_blk_54);
    }

    public void setVisible(boolean z) {
        ViewUtils.setVisible(z, this.imageContainer);
        ViewUtils.setVisible(z, this.detailsContainer);
    }
}
