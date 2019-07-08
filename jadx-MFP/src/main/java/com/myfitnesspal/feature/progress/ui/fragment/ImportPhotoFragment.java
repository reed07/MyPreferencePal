package com.myfitnesspal.feature.progress.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.ImportSource;
import com.myfitnesspal.feature.progress.service.ProgressAnalytics;
import com.myfitnesspal.shared.event.DialogCalendarEvent;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;
import javax.inject.Inject;

public class ImportPhotoFragment extends MfpFragment {
    private static final String DATE_PICKER_FRAGMENT = "date_picker_fragment";
    private static final DateFormat DISPLAY_DATE_FORMATTER = DateFormat.getDateInstance(1, Locale.getDefault());
    private static final String EXTRA_ADD_TO_MEDIA_STORE = "extra_add_to_media_store";
    private static final String EXTRA_DATE = "extra_date";
    private static final String EXTRA_DATE_PICKER_MODE = "extra_date_picker_mode";
    private static final String EXTRA_IMAGE_URI = "extra_image_uri";
    private static final String EXTRA_IMPORT_SOURCE = "import_source";
    private static final String EXTRA_INITIAL_DATE = "extra_initial_date";
    private boolean addToMediaStore = false;
    @BindView(2131362278)
    TextView datePicker;
    private DatePickerMode datePickerMode = DatePickerMode.Enabled;
    private String imageContentUri;
    @BindView(2131362776)
    ImageView imageView;
    private Calendar initialDate;
    @Inject
    Lazy<ProgressAnalytics> progressAnalytics;
    private Calendar selectedDate;
    private ImportSource source;
    private UiChangedListener uiChangedListener;

    public enum DatePickerMode {
        Enabled,
        Disabled,
        Hidden
    }

    public interface UiChangedListener {
        void onUiChanged(ImportPhotoFragment importPhotoFragment);
    }

    public static ImportPhotoFragment newInstance(ImportSource importSource, String str) {
        return newInstance(importSource, str, null);
    }

    public static ImportPhotoFragment newInstance(ImportSource importSource, String str, Calendar calendar) {
        ImportPhotoFragment importPhotoFragment = new ImportPhotoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_IMAGE_URI, str);
        bundle.putSerializable("import_source", importSource);
        if (calendar != null) {
            bundle.putLong("extra_date", calendar.getTimeInMillis());
        }
        importPhotoFragment.setArguments(bundle);
        return importPhotoFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.addToMediaStore = BundleUtils.getBoolean(bundle, EXTRA_ADD_TO_MEDIA_STORE, this.addToMediaStore);
        this.datePickerMode = (DatePickerMode) BundleUtils.getSerializable(bundle, EXTRA_DATE_PICKER_MODE, this.datePickerMode, DatePickerMode.class.getClassLoader());
        this.source = (ImportSource) BundleUtils.getSerializable(getArguments(), "import_source", ImportSource.ProgressScreen, ImportSource.class.getClassLoader());
        setPhotoUri(BundleUtils.getString(bundle, EXTRA_IMAGE_URI, getArguments().getString(EXTRA_IMAGE_URI)));
        long j = BundleUtils.getLong(bundle, "extra_date", getArguments().getLong("extra_date", Calendar.getInstance().getTimeInMillis()));
        this.selectedDate = Calendar.getInstance();
        this.selectedDate.setTimeInMillis(j);
        long j2 = BundleUtils.getLong(bundle, EXTRA_INITIAL_DATE, j);
        this.initialDate = Calendar.getInstance();
        this.initialDate.setTimeInMillis(j2);
        if (bundle == null) {
            reportImportStarted();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString(EXTRA_IMAGE_URI, this.imageContentUri);
        bundle.putLong("extra_date", this.selectedDate.getTimeInMillis());
        bundle.putLong(EXTRA_INITIAL_DATE, this.initialDate.getTimeInMillis());
        bundle.putBoolean(EXTRA_ADD_TO_MEDIA_STORE, this.addToMediaStore);
        bundle.putSerializable(EXTRA_DATE_PICKER_MODE, this.datePickerMode);
    }

    public void onDestroy() {
        super.onDestroy();
        this.uiChangedListener = null;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.progress_photos_import_photo_fragment, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        updateDatePicker();
        redrawPhoto();
        initEventHandlers();
        updateDisplayDate();
        return inflate;
    }

    private void updateDatePicker() {
        if (this.datePicker == null) {
            return;
        }
        if (this.datePickerMode == DatePickerMode.Disabled) {
            this.datePicker.setEnabled(false);
        } else if (this.datePickerMode == DatePickerMode.Hidden) {
            this.datePicker.setVisibility(8);
        } else {
            this.datePicker.setEnabled(true);
            this.datePicker.setVisibility(0);
        }
    }

    private void redrawPhoto() {
        if (this.imageView != null) {
            Glide.with(getActivity()).load(this.imageContentUri).apply(new RequestOptions().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).error(R.drawable.ic_photo_camera_black_24dp).fitCenter()).into(this.imageView);
        }
    }

    public void setDatePickerMode(DatePickerMode datePickerMode2) {
        this.datePickerMode = datePickerMode2;
        updateDatePicker();
    }

    public void setUiChangedListener(UiChangedListener uiChangedListener2) {
        this.uiChangedListener = uiChangedListener2;
    }

    public String getPhotoUri() {
        return this.imageContentUri;
    }

    public void setPhotoUri(String str) {
        this.imageContentUri = str;
        redrawPhoto();
        onUiChanged();
    }

    public Calendar getPhotoDate() {
        return this.selectedDate;
    }

    public boolean addPhotoToMediaStore() {
        return this.addToMediaStore;
    }

    public void setAddPhotoToMediaStore(boolean z) {
        this.addToMediaStore = z;
    }

    public String getTitle(Context context) {
        return context.getString(R.string.progress_photos_import_photo);
    }

    public void onPhotoConsumed() {
        reportImportCompleted();
    }

    @Subscribe
    public void onDateSelectedEvent(DialogCalendarEvent dialogCalendarEvent) {
        Calendar calendar = dialogCalendarEvent.getCalendar();
        if (calendar == null) {
            calendar = this.selectedDate;
        }
        this.selectedDate = calendar;
        updateDisplayDate();
    }

    private void updateDisplayDate() {
        this.datePicker.setText(DISPLAY_DATE_FORMATTER.format(this.selectedDate.getTime()));
    }

    private void onUiChanged() {
        UiChangedListener uiChangedListener2 = this.uiChangedListener;
        if (uiChangedListener2 != null) {
            uiChangedListener2.onUiChanged(this);
        }
    }

    private void initEventHandlers() {
        this.datePicker.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ImportPhotoFragment.this.showDatePickerDialogFragment();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showDatePickerDialogFragment() {
        DatePickerFragment.newInstance(this.selectedDate).show(getFragmentManager(), DATE_PICKER_FRAGMENT);
    }

    private void reportImportStarted() {
        if (this.source == ImportSource.GalleryScreen) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportGalleryImportPhotoStarted();
        } else if (this.source == ImportSource.ProgressScreen) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportProgressImportPhotoStarted();
        }
    }

    private void reportImportCompleted() {
        if (this.source == ImportSource.GalleryScreen) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportGalleryImportPhotoCompleted();
        } else if (this.source == ImportSource.ProgressScreen) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportProgressImportPhotoCompleted();
        } else if (this.source == ImportSource.WeightPicker) {
            ((ProgressAnalytics) this.progressAnalytics.get()).reportWeightEntryPhotoImported(DateTimeUtils.isOnSameDayAsDate(this.selectedDate.getTime(), this.initialDate.getTime()), this.selectedDate.getTime());
        }
    }
}
