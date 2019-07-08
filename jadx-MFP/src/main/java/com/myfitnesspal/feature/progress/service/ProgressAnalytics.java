package com.myfitnesspal.feature.progress.service;

import com.myfitnesspal.feature.progress.constants.ArtifactType;
import com.myfitnesspal.feature.progress.constants.GalleryViewMode;
import com.myfitnesspal.feature.progress.constants.ProgressEntryPoint;
import com.myfitnesspal.feature.progress.constants.ShareTarget;
import com.myfitnesspal.feature.progress.constants.SharingPermission;
import com.myfitnesspal.feature.progress.service.ProgressCongratsService.MessageType;
import java.util.Date;

public interface ProgressAnalytics {

    public enum TapTarget {
        ProgressShare("progress_share_tap"),
        GallerySinglePane("progress_gallery_single_pane_toggle"),
        GallerySplitPane("progress_gallery_double_pane_toggle");
        
        private String eventName;

        private TapTarget(String str) {
            this.eventName = str;
        }

        public String getEventName() {
            return this.eventName;
        }
    }

    void reportArtifactScreenNextTapped(ArtifactType artifactType, GalleryViewMode galleryViewMode, boolean z);

    void reportArtifactScreenViewed();

    void reportGalleryImportPhotoCompleted();

    void reportGalleryImportPhotoStarted();

    void reportGalleryShareTapped(GalleryViewMode galleryViewMode);

    void reportImageReportedEvent(String str, String str2, String str3, String str4, String str5);

    void reportLegacyWeightEntrySaved();

    void reportPostPrivacyPermissionViewed();

    void reportPostPrivacyPermissions(SharingPermission sharingPermission);

    void reportProgressImportPhotoCompleted();

    void reportProgressImportPhotoStarted();

    void reportProgressPhotoView();

    void reportProgressPhotosIntroInterstitialPositive();

    void reportProgressPhotosIntroInterstitialView();

    void reportProgressPhotosShareCompletedNewsfeed(String str, String str2, String str3, boolean z);

    void reportProgressPhotosShareStartedNewsfeed(String str, String str2, String str3, boolean z);

    void reportProgressScreenSinceStartingWeightGraph(boolean z);

    void reportProgressShareScreenViewed();

    void reportShareProgressArtifactView(ArtifactType artifactType);

    void reportShareProgressInterstitialNegative(MessageType messageType, int i, int i2);

    void reportShareProgressInterstitialPositive(MessageType messageType, int i, int i2);

    void reportShareProgressInterstitialView(MessageType messageType, int i, int i2);

    void reportShareProgressMessageToggle(boolean z);

    void reportShareProgressShareStarted(ShareTarget shareTarget, ArtifactType artifactType, boolean z, GalleryViewMode galleryViewMode, boolean z2);

    void reportShareProgressShareStarted(ShareTarget shareTarget, String str, boolean z, String str2, boolean z2);

    void reportViewTapped(TapTarget tapTarget);

    void reportWeightEntryCameraPhotoTaken();

    void reportWeightEntryCameraView();

    void reportWeightEntryPhotoImported(boolean z, Date date);

    void reportWeightEntrySaved(ProgressEntryPoint progressEntryPoint, boolean z);

    void reportWeightEntrySaved(ProgressEntryPoint progressEntryPoint, boolean z, boolean z2, boolean z3);

    void reportWeightEntryScreenDismiss();

    void reportWeightEntryScreenView(ProgressEntryPoint progressEntryPoint);
}
