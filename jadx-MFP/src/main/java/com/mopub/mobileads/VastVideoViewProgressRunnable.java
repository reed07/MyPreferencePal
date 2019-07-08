package com.mopub.mobileads;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.mopub.common.ExternalViewabilitySession.VideoEvent;
import com.mopub.common.Preconditions;
import com.mopub.network.TrackingRequest;
import java.util.ArrayList;
import java.util.List;

public class VastVideoViewProgressRunnable extends RepeatingHandlerRunnable {
    @NonNull
    private final VastVideoConfig mVastVideoConfig;
    @NonNull
    private final VastVideoViewController mVideoViewController;

    public VastVideoViewProgressRunnable(@NonNull VastVideoViewController vastVideoViewController, @NonNull VastVideoConfig vastVideoConfig, @NonNull Handler handler) {
        super(handler);
        Preconditions.checkNotNull(vastVideoViewController);
        Preconditions.checkNotNull(vastVideoConfig);
        this.mVideoViewController = vastVideoViewController;
        this.mVastVideoConfig = vastVideoConfig;
        ArrayList arrayList = new ArrayList();
        arrayList.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_STARTED.name(), BitmapDescriptorFactory.HUE_RED));
        arrayList.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_IMPRESSED.name(), BitmapDescriptorFactory.HUE_RED));
        arrayList.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_VIDEO_FIRST_QUARTILE.name(), 0.25f));
        arrayList.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_VIDEO_MIDPOINT.name(), 0.5f));
        arrayList.add(new VastFractionalProgressTracker(MessageType.QUARTILE_EVENT, VideoEvent.AD_VIDEO_THIRD_QUARTILE.name(), 0.75f));
        this.mVastVideoConfig.addFractionalTrackers(arrayList);
    }

    public void doWork() {
        int duration = this.mVideoViewController.getDuration();
        int currentPosition = this.mVideoViewController.getCurrentPosition();
        this.mVideoViewController.updateProgressBar();
        if (duration > 0) {
            List<VastTracker> untriggeredTrackersBefore = this.mVastVideoConfig.getUntriggeredTrackersBefore(currentPosition, duration);
            if (!untriggeredTrackersBefore.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                for (VastTracker vastTracker : untriggeredTrackersBefore) {
                    if (vastTracker.getMessageType() == MessageType.TRACKING_URL) {
                        arrayList.add(vastTracker.getContent());
                    } else if (vastTracker.getMessageType() == MessageType.QUARTILE_EVENT) {
                        this.mVideoViewController.handleViewabilityQuartileEvent(vastTracker.getContent());
                    }
                    vastTracker.setTracked();
                }
                TrackingRequest.makeTrackingHttpRequest((Iterable<String>) new VastMacroHelper(arrayList).withAssetUri(this.mVideoViewController.getNetworkMediaFileUrl()).withContentPlayHead(Integer.valueOf(currentPosition)).getUris(), this.mVideoViewController.getContext());
            }
            this.mVideoViewController.handleIconDisplay(currentPosition);
        }
    }
}
