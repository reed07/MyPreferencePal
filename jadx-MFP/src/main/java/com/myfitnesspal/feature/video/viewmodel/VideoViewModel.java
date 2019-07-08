package com.myfitnesspal.feature.video.viewmodel;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.amazon.device.ads.DTBAdResponse;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.model.CuePoint;
import com.brightcove.player.model.CuePoint.PositionType;
import com.brightcove.player.model.Playlist;
import com.brightcove.player.model.Video;
import com.myfitnesspal.feature.settings.model.VideoAdsParams;
import com.myfitnesspal.feature.video.model.VideoGalleryAdapterItem;
import com.myfitnesspal.feature.video.model.VideoGalleryDetailsItem;
import com.myfitnesspal.feature.video.model.VideoGalleryItem;
import com.myfitnesspal.feature.video.task.AmazonAdTask;
import com.myfitnesspal.feature.video.task.FetchVideoTask;
import com.myfitnesspal.feature.video.task.FetchVideosTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.util.ConnectivityUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VideoViewModel extends RunnerViewModel<VideoGalleryAdapterItem> {
    private static final long AMAZON_AD_LOAD_TIME_INTERVAL = TimeUnit.SECONDS.toMillis(1);
    private static final long AMAZON_AD_LOAD_TIME_TOTAL = TimeUnit.SECONDS.toMillis(1);
    private static final String AMAZON_CUSTOM_PARAMS_PLACEHOLDER = "{AMAZON_CUSTOM_PARAMS}";
    private static final String DESCRIPTION_URL_PLACEHOLDER = "{DESCRIPTION_URL}";
    private static final String DFP_UNIT_PLACEHOLDER = "{DFP_AD_UNIT}";
    private static final String IMA_TAGS_DELIMETER = ",";
    private static final long LOAD_TIME_INTERVAL = TimeUnit.SECONDS.toMillis(10);
    private static final long LOAD_TIME_TOTAL = TimeUnit.SECONDS.toMillis(10);
    private static final String URL_ENCODING = "utf-8";
    private static final String VIDEO_ID_PLACEHOLDER = "{VIDEO_ID}";
    private static final String VIDEO_IMA_TAGS_PLACEHOLDER = "{VIDEO_IMA_TAGS}";
    private static final int VIDEO_INVALID_INDEX = -1;
    private static final String VIDEO_LINKS_KEY = "link";
    private static final String VIDEO_TAGS_KEY = "tags";
    private static final String VIDEO_URL_KEY = "url";
    @Nullable
    private Video activeVideo;
    private int activeVideoIndex = -1;
    @NonNull
    private Map<String, String> adsMap = new HashMap();
    @Nullable
    private CountDownTimer amazonAdLoadTimer;
    @Nullable
    private String amazonSlotUuid;
    @Nullable
    private String amazonTargeting;
    @Nullable
    private EventEmitter eventEmitter;
    @Nullable
    private CountDownTimer fetchPlaylistLoadTimer;
    private boolean isInRewind = false;
    private boolean isOnline = true;
    @NonNull
    private List<VideoGalleryAdapterItem> items = new ArrayList();
    @Nullable
    private final String playlistId;
    @Nullable
    private String sponsorName;
    @Nullable
    private VideoAdsParams videoAdsParams;
    @Nullable
    private String videoFranchise;
    @NonNull
    private final String videoId;
    @Nullable
    private String videoThumbnail;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int VIDEO_CHANGED = ViewModelPropertyId.next();
        public static final int VIDEO_FAILED_LOAD = ViewModelPropertyId.next();
        public static final int VIDEO_LOADED = ViewModelPropertyId.next();
    }

    public VideoViewModel(@Nullable Runner runner, @NonNull String str, @Nullable String str2, @Nullable String str3) {
        super(runner);
        this.videoId = str;
        this.playlistId = str2;
        this.videoThumbnail = str3;
    }

    public void load(VideoGalleryAdapterItem... videoGalleryAdapterItemArr) {
        this.isOnline = ConnectivityUtil.isOnline();
        if (this.eventEmitter == null || !this.isOnline) {
            setState(State.Error);
            notifyPropertyChanged(Property.VIDEO_FAILED_LOAD);
            return;
        }
        setState(State.Loading);
        VideoAdsParams videoAdsParams2 = this.videoAdsParams;
        String amazonAppId = videoAdsParams2 != null ? videoAdsParams2.getAmazonAppId() : null;
        String str = this.amazonSlotUuid;
        if (TextUtils.isEmpty(amazonAppId) || TextUtils.isEmpty(str)) {
            Ln.w("%s %s", AmazonAdTask.TAG, "Loading video without Amazon video ad");
            loadVideo();
        } else {
            Ln.w("%s %s", AmazonAdTask.TAG, "Preparing Amazon video ad task");
            getRunner().run(AmazonAdTask.NAME, new AmazonAdTask(AmazonAdTask.DEFAULT_AD_WIDTH, AmazonAdTask.DEFAULT_AD_HEIGHT, amazonAppId, str));
            AnonymousClass1 r6 = new CountDownTimer(AMAZON_AD_LOAD_TIME_TOTAL, AMAZON_AD_LOAD_TIME_INTERVAL) {
                public void onTick(long j) {
                }

                public void onFinish() {
                    if (VideoViewModel.this.isAttached()) {
                        VideoViewModel.this.getRunner().cancel(AmazonAdTask.NAME);
                        Ln.w("%s %s", AmazonAdTask.TAG, "Amazon video ad task was canceled in case of long request time");
                        VideoViewModel.this.loadVideo();
                    }
                }
            };
            this.amazonAdLoadTimer = r6.start();
        }
    }

    public void setEventEmitter(@NonNull EventEmitter eventEmitter2) {
        this.eventEmitter = eventEmitter2;
    }

    public void setAdParams(@NonNull VideoAdsParams videoAdsParams2, @NonNull String str) {
        this.videoAdsParams = videoAdsParams2;
        this.amazonSlotUuid = str;
    }

    @Nullable
    public Video getActiveVideo() {
        return this.activeVideo;
    }

    public int getIndexOfCurrentlySelectedVideo() {
        return this.activeVideoIndex;
    }

    @Nullable
    public String getVideoThumbnail() {
        return this.videoThumbnail;
    }

    public void setInRewind(boolean z) {
        this.isInRewind = z;
    }

    public boolean isInRewind() {
        return this.isInRewind;
    }

    @NonNull
    public Map<String, String> getAdsMap() {
        return this.adsMap;
    }

    public int getCount() {
        return this.items.size();
    }

    public boolean isOnline() {
        return this.isOnline;
    }

    @Nullable
    public String getVideoFranchise() {
        return this.videoFranchise;
    }

    @Nullable
    public String getSponsorName() {
        return this.sponsorName;
    }

    public VideoGalleryAdapterItem getVideoGalleryItem(int i) {
        return (VideoGalleryAdapterItem) this.items.get(i);
    }

    public void onVideoChanged(@NonNull Video video, int i) {
        VideoGalleryAdapterItem videoGalleryAdapterItem = this.items.size() > 0 ? (VideoGalleryAdapterItem) this.items.get(0) : null;
        if (videoGalleryAdapterItem instanceof VideoGalleryDetailsItem) {
            ((VideoGalleryDetailsItem) videoGalleryAdapterItem).setActiveVideo(video.getId());
        }
        this.items.remove(i);
        Video video2 = this.activeVideo;
        if (video2 != null) {
            this.items.add(new VideoGalleryItem(video2));
        }
        this.activeVideoIndex = i;
        this.activeVideo = video;
        this.isInRewind = false;
        this.videoThumbnail = Strings.toString(this.activeVideo.getStillImageUri());
        notifyPropertyChanged(Property.VIDEO_CHANGED);
    }

    @Nullable
    public Video getVideoAt(int i) {
        if (i >= 1 && i < CollectionUtils.size((Collection<?>) this.items)) {
            VideoGalleryAdapterItem videoGalleryItem = getVideoGalleryItem(i);
            if (videoGalleryItem instanceof VideoGalleryItem) {
                return ((VideoGalleryItem) videoGalleryItem).getVideo();
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(@NonNull TaskDetails taskDetails) {
        if (taskDetails.matches(getRunner(), AmazonAdTask.class)) {
            CountDownTimer countDownTimer = this.amazonAdLoadTimer;
            if (countDownTimer != null) {
                countDownTimer.cancel();
            }
            this.amazonTargeting = buildAmazonTargeting((DTBAdResponse) taskDetails.getResult());
            Ln.w("%s %s", AmazonAdTask.TAG, "Amazon video ad task was completed");
            loadVideo();
        } else if (taskDetails.matches(getRunner(), FetchVideoTask.class)) {
            if (taskDetails.successful()) {
                Video video = (Video) taskDetails.getResult();
                addVideoToAdsMap(video);
                this.activeVideo = video;
            }
            notifyPropertyChanged(Property.VIDEO_LOADED);
        } else if (taskDetails.matches(getRunner(), FetchVideosTask.class)) {
            CountDownTimer countDownTimer2 = this.fetchPlaylistLoadTimer;
            if (countDownTimer2 != null) {
                countDownTimer2.cancel();
            }
            if (taskDetails.successful()) {
                Playlist playlist = (Playlist) taskDetails.getResult();
                if (playlist != null) {
                    VideoGalleryDetailsItem videoGalleryDetailsItem = new VideoGalleryDetailsItem(playlist, this.videoId);
                    this.videoFranchise = videoGalleryDetailsItem.getPlaylistName();
                    this.sponsorName = videoGalleryDetailsItem.getStringProperty("sponsor");
                    this.items.add(videoGalleryDetailsItem);
                    for (int i = 0; i < videoGalleryDetailsItem.getVideos().size(); i++) {
                        Video video2 = (Video) videoGalleryDetailsItem.getVideos().get(i);
                        addVideoToAdsMap(video2);
                        if (Strings.equals(video2.getId(), this.videoId)) {
                            this.activeVideoIndex = i;
                        } else {
                            this.items.add(new VideoGalleryItem(video2));
                        }
                    }
                }
                setState(State.Loaded);
            }
        }
    }

    @Nullable
    private String buildAmazonTargeting(@Nullable DTBAdResponse dTBAdResponse) {
        if (dTBAdResponse == null) {
            return null;
        }
        Map defaultVideoAdsRequestCustomParams = dTBAdResponse.getDefaultVideoAdsRequestCustomParams();
        String str = "";
        for (String str2 : defaultVideoAdsRequestCustomParams.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(str2);
            sb.append("=");
            sb.append((String) defaultVideoAdsRequestCustomParams.get(str2));
            sb.append("&");
            str = sb.toString();
        }
        return str.substring(0, str.length() - 1).replaceAll("=", "%3D").replaceAll("&", "%26").replaceAll(IMA_TAGS_DELIMETER, "%2C");
    }

    /* access modifiers changed from: private */
    public void loadVideo() {
        getRunner().run(new FetchVideoTask(this.eventEmitter, this.videoId));
        fetchPlaylist();
    }

    private void addVideoToAdsMap(@NonNull Video video) {
        if (this.videoAdsParams != null) {
            Map properties = video.getProperties();
            String str = this.amazonTargeting;
            video.getCuePoints().add(new CuePoint(PositionType.BEFORE, "ad", (Map<String, Object>) new HashMap<String,Object>()));
            StringBuilder sb = new StringBuilder(this.videoAdsParams.getImaTagTemplate());
            fillImaLinkPlaceholder(sb, DFP_UNIT_PLACEHOLDER, this.videoAdsParams.getDfpAdUnit());
            HashMap hashMap = (HashMap) properties.get("link");
            fillImaLinkPlaceholder(sb, DESCRIPTION_URL_PLACEHOLDER, (hashMap == null || !(hashMap.get("url") instanceof String)) ? null : encodeUrlParameter((String) hashMap.get("url")));
            fillImaLinkPlaceholder(sb, VIDEO_ID_PLACEHOLDER, video.getId());
            fillImaLinkPlaceholder(sb, AMAZON_CUSTOM_PARAMS_PLACEHOLDER, str);
            if (properties.containsKey(VIDEO_TAGS_KEY)) {
                ArrayList<String> arrayList = (ArrayList) properties.get(VIDEO_TAGS_KEY);
                ArrayList arrayList2 = new ArrayList();
                if (CollectionUtils.notEmpty((Collection<?>) arrayList)) {
                    for (String encodeUrlParameter : arrayList) {
                        String encodeUrlParameter2 = encodeUrlParameter(encodeUrlParameter);
                        if (encodeUrlParameter2 != null) {
                            arrayList2.add(encodeUrlParameter2);
                        }
                    }
                }
                fillImaLinkPlaceholder(sb, VIDEO_IMA_TAGS_PLACEHOLDER, Strings.join(IMA_TAGS_DELIMETER, (Collection<T>) arrayList2));
            }
            this.adsMap.put(video.getId(), sb.toString());
        }
    }

    private void fillImaLinkPlaceholder(@NonNull StringBuilder sb, @NonNull String str, @Nullable String str2) {
        if (str2 == null) {
            str2 = "";
        }
        int indexOf = sb.indexOf(str);
        if (indexOf >= 0) {
            sb.replace(indexOf, str.length() + indexOf, str2);
        }
    }

    @Nullable
    private String encodeUrlParameter(@NonNull String str) {
        try {
            return URLEncoder.encode(str, "utf-8");
        } catch (Exception unused) {
            return null;
        }
    }

    private void fetchPlaylist() {
        if (!Strings.notEmpty(this.playlistId)) {
            setState(State.Error);
        } else if (!getRunner().running(FetchVideosTask.NAME)) {
            getRunner().run(FetchVideosTask.NAME, new FetchVideosTask(this.eventEmitter, this.playlistId));
            AnonymousClass2 r5 = new CountDownTimer(LOAD_TIME_TOTAL, LOAD_TIME_INTERVAL) {
                public void onTick(long j) {
                }

                public void onFinish() {
                    if (VideoViewModel.this.getRunner() != null) {
                        VideoViewModel.this.getRunner().cancel(FetchVideosTask.NAME);
                    }
                    VideoViewModel.this.setState(State.Error);
                }
            };
            this.fetchPlaylistLoadTimer = r5.start();
        }
    }
}
