package com.myfitnesspal.feature.progress.service;

import com.myfitnesspal.feature.progress.ui.viewmodel.ArtifactViewModel;
import com.myfitnesspal.feature.progress.ui.viewmodel.GalleryImageViewModel;
import com.myfitnesspal.shared.model.ProgressEntryViewModel;
import com.myfitnesspal.shared.util.UnitsUtils.Length;
import com.myfitnesspal.shared.util.UnitsUtils.Weight;
import java.util.List;

public interface ProgressService {
    List<GalleryImageViewModel> getGalleryImagesForImportMode(String str);

    List<GalleryImageViewModel> getGalleryImagesForViewMode(String str);

    int getImageCountForMeasurementType(String str);

    List<ProgressEntryViewModel> getProgressReport(String str, int i, Weight weight, Length length);

    List<ArtifactViewModel> getShareProgressArtifactData(String str, String str2, boolean z);
}
