package com.myfitnesspal.feature.progress.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageServiceUtil;
import com.myfitnesspal.feature.progress.model.ProgressEntryItem;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.model.unitconv.LocalizedWeight;
import com.myfitnesspal.shared.service.userdata.UserWeightService;
import com.myfitnesspal.shared.ui.view.GlideHideProgressListener;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.myfitnesspal.shared.util.Measurements;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.NumberUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ProgressEntryAdapter extends BaseAdapter {
    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();
    private static final SimpleDateFormat DAY_DATE_FORMAT = new SimpleDateFormat("EEEE");
    private final Context context;
    private List<ProgressEntryItem> entries;
    private final Lazy<ImageService> imageService;
    private ViewType mainItemViewType;
    private final String measurementType;
    private final LocalizedWeight weight = LocalizedWeight.fromPounds(0.0d);
    private final Lazy<UserWeightService> weightService;

    private static class EntryViewHolder extends ViewHolder {
        final TextView dateView;
        final TextView dayView;
        final GlideHideProgressListener imageLoadListener = new GlideHideProgressListener();
        final ImageView imageView;
        final View progressView;
        final TextView valueView;

        EntryViewHolder(View view) {
            super();
            this.dateView = (TextView) view.findViewById(R.id.entry_date);
            this.dayView = (TextView) view.findViewById(R.id.entry_day);
            this.valueView = (TextView) view.findViewById(R.id.value);
            this.imageView = (ImageView) view.findViewById(R.id.image);
            this.progressView = view.findViewById(R.id.progress);
        }
    }

    private static class ViewHolder {
        private ViewHolder() {
        }
    }

    private enum ViewType {
        Weight,
        Length,
        Fitbit
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ProgressEntryAdapter(Context context2, String str, Lazy<UserWeightService> lazy, Lazy<ImageService> lazy2, List<ProgressEntryItem> list) {
        this.context = context2;
        this.measurementType = str;
        this.imageService = lazy2;
        this.weightService = lazy;
        if (Measurements.isWeight(str)) {
            this.mainItemViewType = ViewType.Weight;
        } else if (Measurements.isFitbit(str)) {
            this.mainItemViewType = ViewType.Fitbit;
        } else {
            this.mainItemViewType = ViewType.Length;
        }
        setEntries(list);
    }

    public void setEntries(List<ProgressEntryItem> list) {
        this.entries = (List) Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) $$Lambda$ProgressEntryAdapter$nXPVc1ERti7Ami2cYLNoLGCEKc.INSTANCE);
        Collections.reverse(this.entries);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.entries.size();
    }

    public ProgressEntryItem getItem(int i) {
        return (ProgressEntryItem) this.entries.get(i);
    }

    public int getItemViewType(int i) {
        return this.mainItemViewType.ordinal();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewType viewType = ViewType.values()[getItemViewType(i)];
        ProgressEntryItem progressEntryItem = (ProgressEntryItem) this.entries.get(i);
        View inflate = View.inflate(this.context, getLayoutIdBasedOnViewType(viewType), null);
        inflate.setTag(new EntryViewHolder(inflate));
        ViewHolder viewHolder = (ViewHolder) inflate.getTag();
        switch (viewType) {
            case Weight:
                bindWeight((EntryViewHolder) viewHolder, (ProgressEntryViewModel) progressEntryItem);
                break;
            case Length:
                bindLength((EntryViewHolder) viewHolder, (ProgressEntryViewModel) progressEntryItem);
                break;
            case Fitbit:
                bindFitbit((EntryViewHolder) viewHolder, (ProgressEntryViewModel) progressEntryItem);
                break;
        }
        return inflate;
    }

    private int getLayoutIdBasedOnViewType(ViewType viewType) {
        switch (viewType) {
            case Weight:
                return R.layout.progress_photos_entry_list_item_with_image;
            case Length:
                return R.layout.progress_photos_entry_list_item_with_text;
            case Fitbit:
                return R.layout.progress_photos_entry_list_item_with_text;
            default:
                return R.layout.progress_photos_entry_list_item_with_image;
        }
    }

    public String getMeasurementType() {
        return this.measurementType;
    }

    private void bindWeight(EntryViewHolder entryViewHolder, ProgressEntryViewModel progressEntryViewModel) {
        this.weight.setValue(Weight.POUNDS, progressEntryViewModel.getNormalizedUnitValue());
        String displayString = LocalizedWeight.getDisplayString(this.context, this.weight, ((UserWeightService) this.weightService.get()).getUserCurrentWeightUnit());
        int i = DateTimeUtils.isDateToday(progressEntryViewModel.getDate()) ? R.drawable.ic_photo_camera_black_24dp : R.drawable.ic_camera_roll_blk_54;
        entryViewHolder.dateView.setText(DATE_FORMAT.format(progressEntryViewModel.getDate()));
        entryViewHolder.valueView.setText(displayString);
        entryViewHolder.imageView.setScaleType(ScaleType.CENTER);
        entryViewHolder.imageView.setImageResource(i);
        entryViewHolder.imageLoadListener.setLoaded(entryViewHolder.progressView, entryViewHolder.imageView);
        String imageUri = getImageUri(this.context, (ImageService) this.imageService.get(), progressEntryViewModel);
        if (Strings.notEmpty(imageUri)) {
            entryViewHolder.imageLoadListener.setLoading(entryViewHolder.progressView, entryViewHolder.imageView);
            Glide.with(this.context).load(imageUri).listener(entryViewHolder.imageLoadListener).apply(new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).error(i).dontAnimate().placeholder(i)).into(entryViewHolder.imageView);
        }
    }

    private void bindLength(EntryViewHolder entryViewHolder, ProgressEntryViewModel progressEntryViewModel) {
        entryViewHolder.dateView.setText(DATE_FORMAT.format(progressEntryViewModel.getDate()));
        entryViewHolder.dayView.setText(DAY_DATE_FORMAT.format(progressEntryViewModel.getDate()));
        entryViewHolder.valueView.setText(NumberUtils.localeStringFromDouble(progressEntryViewModel.getNormalizedUnitValue(), 1));
    }

    private void bindFitbit(EntryViewHolder entryViewHolder, ProgressEntryViewModel progressEntryViewModel) {
        entryViewHolder.dateView.setText(DATE_FORMAT.format(progressEntryViewModel.getDate()));
        entryViewHolder.dayView.setText(DAY_DATE_FORMAT.format(progressEntryViewModel.getDate()));
        entryViewHolder.valueView.setText(NumberUtils.localeStringFromIntWithSeparators((int) Math.round(progressEntryViewModel.getValue())));
    }

    private static String getImageUri(Context context2, ImageService imageService2, ProgressEntryViewModel progressEntryViewModel) {
        if (Strings.notEmpty(progressEntryViewModel.getImageId())) {
            return ImageServiceUtil.getImageThumbnailUri(context2, imageService2, progressEntryViewModel.getImageId());
        }
        return progressEntryViewModel.getImageLocalFilepath();
    }

    public static boolean hasAtLeastOneVisibleEntry(List<ProgressEntryViewModel> list) {
        for (ProgressEntryViewModel isShowInList : list) {
            if (isShowInList.isShowInList()) {
                return true;
            }
        }
        return false;
    }
}
