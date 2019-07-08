package com.myfitnesspal.feature.progress.ui.viewmodel;

import android.content.Context;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.progress.constants.GalleryDataMode;
import com.myfitnesspal.feature.progress.service.ProgressService;
import com.myfitnesspal.feature.progress.task.GetGalleryImagesTask;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.constants.Constants.Measurement;
import com.myfitnesspal.shared.ui.fragment.impl.HorizontalGalleryFragment.ImageMetadataProvider;
import com.uacf.core.util.CollectionUtils;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class GalleryScrollerViewModel extends RunnerViewModel<Void> implements ImageMetadataProvider {
    private final Context context;
    private final GalleryDataMode dataMode;
    private List<GalleryImageViewModel> headerImages = null;
    private Lazy<ImageService> imageService;
    private List<GalleryImageViewModel> images = null;
    private Lazy<ProgressService> progressService;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int IMAGE_LIST = ViewModelPropertyId.next();
    }

    public GalleryScrollerViewModel(Context context2, Lazy<ImageService> lazy, Lazy<ProgressService> lazy2, GalleryDataMode galleryDataMode, Runner runner) {
        super(runner);
        this.context = context2.getApplicationContext();
        this.imageService = lazy;
        this.progressService = lazy2;
        this.dataMode = galleryDataMode;
    }

    public void load(Void... voidArr) {
        new GetGalleryImagesTask(this.progressService, Measurement.WEIGHT, this.dataMode).run(getRunner());
    }

    public String getUri(int i) {
        GalleryImageViewModel galleryImageViewModel = get(i);
        if (galleryImageViewModel.hasImageAssociation()) {
            return galleryImageViewModel.getThumbnailUri(this.context, this.imageService);
        }
        return null;
    }

    public int getCount() {
        return CollectionUtils.size((Collection<?>) this.images) + CollectionUtils.size((Collection<?>) this.headerImages);
    }

    public Date getDate(int i) {
        return get(i).getRawDate();
    }

    public GalleryImageViewModel get(int i) {
        int size = CollectionUtils.size((Collection<?>) this.headerImages);
        if (i < size) {
            return (GalleryImageViewModel) this.headerImages.get(i);
        }
        return (GalleryImageViewModel) this.images.get(i - size);
    }

    public int getIndexOf(long j) {
        if (this.images != null) {
            for (int i = 0; i < this.images.size(); i++) {
                if (get(i).getImageAssociationLocalId() == j) {
                    return i;
                }
            }
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.matches(getRunner(), GetGalleryImagesTask.class)) {
            this.images = (List) taskDetails.getResult();
            notifyPropertyChanged(Property.IMAGE_LIST);
        }
    }

    public void addHeaderImages(List<GalleryImageViewModel> list) {
        if (CollectionUtils.notEmpty((Collection<?>) list)) {
            this.headerImages = list;
        }
    }
}
