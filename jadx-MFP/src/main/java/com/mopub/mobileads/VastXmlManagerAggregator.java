package com.mopub.mobileads;

import android.content.Context;
import android.graphics.Point;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.util.MimeTypes;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Dips;
import com.mopub.network.Networking;
import com.mopub.network.TrackingRequest;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class VastXmlManagerAggregator extends AsyncTask<String, Void, VastVideoConfig> {
    public static final String ADS_BY_AD_SLOT_ID = "adsBy";
    public static final String SOCIAL_ACTIONS_AD_SLOT_ID = "socialActions";
    private static final List<String> VIDEO_MIME_TYPES = Arrays.asList(new String[]{MimeTypes.VIDEO_MP4, MimeTypes.VIDEO_H263});
    @NonNull
    private final Context mContext;
    private final int mScreenAreaDp;
    private final double mScreenAspectRatio;
    private int mTimesFollowedVastRedirect;
    @NonNull
    private final WeakReference<VastXmlManagerAggregatorListener> mVastXmlManagerAggregatorListener;

    enum CompanionOrientation {
        LANDSCAPE,
        PORTRAIT
    }

    interface VastXmlManagerAggregatorListener {
        void onAggregationComplete(@Nullable VastVideoConfig vastVideoConfig);
    }

    VastXmlManagerAggregator(@NonNull VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener, double d, int i, @NonNull Context context) {
        Preconditions.checkNotNull(vastXmlManagerAggregatorListener);
        Preconditions.checkNotNull(context);
        this.mVastXmlManagerAggregatorListener = new WeakReference<>(vastXmlManagerAggregatorListener);
        this.mScreenAspectRatio = d;
        this.mScreenAreaDp = i;
        this.mContext = context.getApplicationContext();
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        Networking.getUserAgent(this.mContext);
    }

    /* access modifiers changed from: protected */
    public VastVideoConfig doInBackground(@Nullable String... strArr) {
        if (strArr == null || strArr.length == 0 || strArr[0] == null) {
            return null;
        }
        try {
            return evaluateVastXmlManager(strArr[0], new ArrayList());
        } catch (Exception e) {
            MoPubLog.d("Unable to generate VastVideoConfig.", e);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(@Nullable VastVideoConfig vastVideoConfig) {
        VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener = (VastXmlManagerAggregatorListener) this.mVastXmlManagerAggregatorListener.get();
        if (vastXmlManagerAggregatorListener != null) {
            vastXmlManagerAggregatorListener.onAggregationComplete(vastVideoConfig);
        }
    }

    /* access modifiers changed from: protected */
    public void onCancelled() {
        VastXmlManagerAggregatorListener vastXmlManagerAggregatorListener = (VastXmlManagerAggregatorListener) this.mVastXmlManagerAggregatorListener.get();
        if (vastXmlManagerAggregatorListener != null) {
            vastXmlManagerAggregatorListener.onAggregationComplete(null);
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @VisibleForTesting
    public VastVideoConfig evaluateVastXmlManager(@NonNull String str, @NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(str, "vastXml cannot be null");
        Preconditions.checkNotNull(list, "errorTrackers cannot be null");
        VastXmlManager vastXmlManager = new VastXmlManager();
        try {
            vastXmlManager.parseVastXml(str);
            List<VastAdXmlManager> adXmlManagers = vastXmlManager.getAdXmlManagers();
            if (fireErrorTrackerIfNoAds(adXmlManagers, vastXmlManager, this.mContext)) {
                return null;
            }
            for (VastAdXmlManager vastAdXmlManager : adXmlManagers) {
                if (isValidSequenceNumber(vastAdXmlManager.getSequence())) {
                    VastInLineXmlManager inLineXmlManager = vastAdXmlManager.getInLineXmlManager();
                    if (inLineXmlManager != null) {
                        VastVideoConfig evaluateInLineXmlManager = evaluateInLineXmlManager(inLineXmlManager, list);
                        if (evaluateInLineXmlManager != null) {
                            populateMoPubCustomElements(vastXmlManager, evaluateInLineXmlManager);
                            return evaluateInLineXmlManager;
                        }
                    }
                    VastWrapperXmlManager wrapperXmlManager = vastAdXmlManager.getWrapperXmlManager();
                    if (wrapperXmlManager != null) {
                        ArrayList arrayList = new ArrayList(list);
                        arrayList.addAll(wrapperXmlManager.getErrorTrackers());
                        String evaluateWrapperRedirect = evaluateWrapperRedirect(wrapperXmlManager, arrayList);
                        if (evaluateWrapperRedirect == null) {
                            continue;
                        } else {
                            VastVideoConfig evaluateVastXmlManager = evaluateVastXmlManager(evaluateWrapperRedirect, arrayList);
                            if (evaluateVastXmlManager != null) {
                                evaluateVastXmlManager.addImpressionTrackers(wrapperXmlManager.getImpressionTrackers());
                                for (VastLinearXmlManager populateLinearTrackersAndIcon : wrapperXmlManager.getLinearXmlManagers()) {
                                    populateLinearTrackersAndIcon(populateLinearTrackersAndIcon, evaluateVastXmlManager);
                                }
                                populateVideoViewabilityTracker(wrapperXmlManager, evaluateVastXmlManager);
                                populateViewabilityMetadata(wrapperXmlManager, evaluateVastXmlManager);
                                List<VastCompanionAdXmlManager> companionAdXmlManagers = wrapperXmlManager.getCompanionAdXmlManagers();
                                if (!evaluateVastXmlManager.hasCompanionAd()) {
                                    evaluateVastXmlManager.setVastCompanionAd(getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.LANDSCAPE), getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.PORTRAIT));
                                } else {
                                    VastCompanionAdConfig vastCompanionAd = evaluateVastXmlManager.getVastCompanionAd(2);
                                    VastCompanionAdConfig vastCompanionAd2 = evaluateVastXmlManager.getVastCompanionAd(1);
                                    if (!(vastCompanionAd == null || vastCompanionAd2 == null)) {
                                        for (VastCompanionAdXmlManager vastCompanionAdXmlManager : companionAdXmlManagers) {
                                            if (!vastCompanionAdXmlManager.hasResources()) {
                                                vastCompanionAd.addClickTrackers(vastCompanionAdXmlManager.getClickTrackers());
                                                vastCompanionAd.addCreativeViewTrackers(vastCompanionAdXmlManager.getCompanionCreativeViewTrackers());
                                                vastCompanionAd2.addClickTrackers(vastCompanionAdXmlManager.getClickTrackers());
                                                vastCompanionAd2.addCreativeViewTrackers(vastCompanionAdXmlManager.getCompanionCreativeViewTrackers());
                                            }
                                        }
                                    }
                                }
                                if (evaluateVastXmlManager.getSocialActionsCompanionAds().isEmpty()) {
                                    evaluateVastXmlManager.setSocialActionsCompanionAds(getSocialActionsCompanionAds(companionAdXmlManagers));
                                }
                                populateMoPubCustomElements(vastXmlManager, evaluateVastXmlManager);
                                return evaluateVastXmlManager;
                            }
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        } catch (Exception e) {
            MoPubLog.d("Failed to parse VAST XML", e);
            TrackingRequest.makeVastTrackingHttpRequest(list, VastErrorCode.XML_PARSING_ERROR, null, null, this.mContext);
            return null;
        }
    }

    @Nullable
    private VastVideoConfig evaluateInLineXmlManager(@NonNull VastInLineXmlManager vastInLineXmlManager, @NonNull List<VastTracker> list) {
        Preconditions.checkNotNull(vastInLineXmlManager);
        Preconditions.checkNotNull(list);
        for (VastLinearXmlManager vastLinearXmlManager : vastInLineXmlManager.getLinearXmlManagers()) {
            String bestMediaFileUrl = getBestMediaFileUrl(vastLinearXmlManager.getMediaXmlManagers());
            if (bestMediaFileUrl != null) {
                VastVideoConfig vastVideoConfig = new VastVideoConfig();
                vastVideoConfig.addImpressionTrackers(vastInLineXmlManager.getImpressionTrackers());
                populateLinearTrackersAndIcon(vastLinearXmlManager, vastVideoConfig);
                vastVideoConfig.setClickThroughUrl(vastLinearXmlManager.getClickThroughUrl());
                vastVideoConfig.setNetworkMediaFileUrl(bestMediaFileUrl);
                List companionAdXmlManagers = vastInLineXmlManager.getCompanionAdXmlManagers();
                vastVideoConfig.setVastCompanionAd(getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.LANDSCAPE), getBestCompanionAd(companionAdXmlManagers, CompanionOrientation.PORTRAIT));
                vastVideoConfig.setSocialActionsCompanionAds(getSocialActionsCompanionAds(companionAdXmlManagers));
                list.addAll(vastInLineXmlManager.getErrorTrackers());
                vastVideoConfig.addErrorTrackers(list);
                populateVideoViewabilityTracker(vastInLineXmlManager, vastVideoConfig);
                populateViewabilityMetadata(vastInLineXmlManager, vastVideoConfig);
                return vastVideoConfig;
            }
        }
        return null;
    }

    private void populateVideoViewabilityTracker(@NonNull VastBaseInLineWrapperXmlManager vastBaseInLineWrapperXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastBaseInLineWrapperXmlManager);
        Preconditions.checkNotNull(vastVideoConfig);
        if (vastVideoConfig.getVideoViewabilityTracker() == null) {
            VastExtensionParentXmlManager vastExtensionParentXmlManager = vastBaseInLineWrapperXmlManager.getVastExtensionParentXmlManager();
            if (vastExtensionParentXmlManager != null) {
                Iterator it = vastExtensionParentXmlManager.getVastExtensionXmlManagers().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    VastExtensionXmlManager vastExtensionXmlManager = (VastExtensionXmlManager) it.next();
                    if ("MoPub".equals(vastExtensionXmlManager.getType())) {
                        vastVideoConfig.setVideoViewabilityTracker(vastExtensionXmlManager.getVideoViewabilityTracker());
                        break;
                    }
                }
            }
        }
    }

    private void populateViewabilityMetadata(@NonNull VastBaseInLineWrapperXmlManager vastBaseInLineWrapperXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        VastExtensionParentXmlManager vastExtensionParentXmlManager = vastBaseInLineWrapperXmlManager.getVastExtensionParentXmlManager();
        if (vastExtensionParentXmlManager != null) {
            for (VastExtensionXmlManager vastExtensionXmlManager : vastExtensionParentXmlManager.getVastExtensionXmlManagers()) {
                if (vastExtensionXmlManager != null) {
                    vastVideoConfig.addAvidJavascriptResources(vastExtensionXmlManager.getAvidJavaScriptResources());
                    vastVideoConfig.addMoatImpressionPixels(vastExtensionXmlManager.getMoatImpressionPixels());
                }
            }
        }
    }

    @Nullable
    private String evaluateWrapperRedirect(@NonNull VastWrapperXmlManager vastWrapperXmlManager, @NonNull List<VastTracker> list) {
        String vastAdTagURI = vastWrapperXmlManager.getVastAdTagURI();
        String str = null;
        if (vastAdTagURI == null) {
            return null;
        }
        try {
            str = followVastRedirect(vastAdTagURI);
        } catch (Exception e) {
            MoPubLog.d("Failed to follow VAST redirect", e);
            if (!list.isEmpty()) {
                TrackingRequest.makeVastTrackingHttpRequest(list, VastErrorCode.WRAPPER_TIMEOUT, null, null, this.mContext);
            }
        }
        return str;
    }

    private void populateLinearTrackersAndIcon(@NonNull VastLinearXmlManager vastLinearXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastLinearXmlManager, "linearXmlManager cannot be null");
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        vastVideoConfig.addAbsoluteTrackers(vastLinearXmlManager.getAbsoluteProgressTrackers());
        vastVideoConfig.addFractionalTrackers(vastLinearXmlManager.getFractionalProgressTrackers());
        vastVideoConfig.addPauseTrackers(vastLinearXmlManager.getPauseTrackers());
        vastVideoConfig.addResumeTrackers(vastLinearXmlManager.getResumeTrackers());
        vastVideoConfig.addCompleteTrackers(vastLinearXmlManager.getVideoCompleteTrackers());
        vastVideoConfig.addCloseTrackers(vastLinearXmlManager.getVideoCloseTrackers());
        vastVideoConfig.addSkipTrackers(vastLinearXmlManager.getVideoSkipTrackers());
        vastVideoConfig.addClickTrackers(vastLinearXmlManager.getClickTrackers());
        if (vastVideoConfig.getSkipOffsetString() == null) {
            vastVideoConfig.setSkipOffset(vastLinearXmlManager.getSkipOffset());
        }
        if (vastVideoConfig.getVastIconConfig() == null) {
            vastVideoConfig.setVastIconConfig(getBestIcon(vastLinearXmlManager.getIconXmlManagers()));
        }
    }

    private void populateMoPubCustomElements(@NonNull VastXmlManager vastXmlManager, @NonNull VastVideoConfig vastVideoConfig) {
        Preconditions.checkNotNull(vastXmlManager, "xmlManager cannot be null");
        Preconditions.checkNotNull(vastVideoConfig, "vastVideoConfig cannot be null");
        vastVideoConfig.addImpressionTrackers(vastXmlManager.getMoPubImpressionTrackers());
        if (vastVideoConfig.getCustomCtaText() == null) {
            vastVideoConfig.setCustomCtaText(vastXmlManager.getCustomCtaText());
        }
        if (vastVideoConfig.getCustomSkipText() == null) {
            vastVideoConfig.setCustomSkipText(vastXmlManager.getCustomSkipText());
        }
        if (vastVideoConfig.getCustomCloseIconUrl() == null) {
            vastVideoConfig.setCustomCloseIconUrl(vastXmlManager.getCustomCloseIconUrl());
        }
        if (!vastVideoConfig.isCustomForceOrientationSet()) {
            vastVideoConfig.setCustomForceOrientation(vastXmlManager.getCustomForceOrientation());
        }
    }

    private boolean fireErrorTrackerIfNoAds(@NonNull List<VastAdXmlManager> list, @NonNull VastXmlManager vastXmlManager, @NonNull Context context) {
        if (!list.isEmpty() || vastXmlManager.getErrorTracker() == null) {
            return false;
        }
        TrackingRequest.makeVastTrackingHttpRequest(Collections.singletonList(vastXmlManager.getErrorTracker()), this.mTimesFollowedVastRedirect > 0 ? VastErrorCode.NO_ADS_VAST_RESPONSE : VastErrorCode.UNDEFINED_ERROR, null, null, context);
        return true;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @VisibleForTesting
    public String getBestMediaFileUrl(@NonNull List<VastMediaXmlManager> list) {
        Preconditions.checkNotNull(list, "managers cannot be null");
        Iterator it = new ArrayList(list).iterator();
        double d = Double.POSITIVE_INFINITY;
        String str = null;
        while (it.hasNext()) {
            VastMediaXmlManager vastMediaXmlManager = (VastMediaXmlManager) it.next();
            String type = vastMediaXmlManager.getType();
            String mediaUrl = vastMediaXmlManager.getMediaUrl();
            if (!VIDEO_MIME_TYPES.contains(type) || mediaUrl == null) {
                it.remove();
            } else {
                Integer width = vastMediaXmlManager.getWidth();
                Integer height = vastMediaXmlManager.getHeight();
                if (width != null && width.intValue() > 0 && height != null && height.intValue() > 0) {
                    double calculateFitness = calculateFitness(width.intValue(), height.intValue());
                    if (calculateFitness < d) {
                        d = calculateFitness;
                        str = mediaUrl;
                    }
                }
            }
        }
        return str;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @VisibleForTesting
    public VastCompanionAdConfig getBestCompanionAd(@NonNull List<VastCompanionAdXmlManager> list, @NonNull CompanionOrientation companionOrientation) {
        int i;
        ArrayList<VastCompanionAdXmlManager> arrayList;
        Type[] typeArr;
        VastResource vastResource;
        int i2;
        ArrayList arrayList2;
        Type[] typeArr2;
        double d;
        List<VastCompanionAdXmlManager> list2 = list;
        CompanionOrientation companionOrientation2 = companionOrientation;
        Preconditions.checkNotNull(list2, "managers cannot be null");
        Preconditions.checkNotNull(companionOrientation2, "orientation cannot be null");
        ArrayList arrayList3 = new ArrayList(list2);
        Type[] values = Type.values();
        int length = values.length;
        int i3 = 0;
        double d2 = Double.POSITIVE_INFINITY;
        VastCompanionAdXmlManager vastCompanionAdXmlManager = null;
        VastResource vastResource2 = null;
        Point point = null;
        while (true) {
            if (i3 >= i) {
                vastResource = vastResource2;
                break;
            }
            Type type = typeArr[i3];
            for (VastCompanionAdXmlManager vastCompanionAdXmlManager2 : arrayList) {
                Integer width = vastCompanionAdXmlManager2.getWidth();
                Integer height = vastCompanionAdXmlManager2.getHeight();
                if (width != null) {
                    typeArr2 = typeArr;
                    if (width.intValue() >= 300 && height != null) {
                        if (height.intValue() < 250) {
                            arrayList2 = arrayList;
                            i2 = i;
                        } else {
                            Point scaledDimensions = getScaledDimensions(width.intValue(), height.intValue(), type, companionOrientation2);
                            arrayList2 = arrayList;
                            i2 = i;
                            VastResource fromVastResourceXmlManager = VastResource.fromVastResourceXmlManager(vastCompanionAdXmlManager2.getResourceXmlManager(), type, scaledDimensions.x, scaledDimensions.y);
                            if (fromVastResourceXmlManager != null) {
                                if (CompanionOrientation.PORTRAIT == companionOrientation2) {
                                    d = calculateFitness(height.intValue(), width.intValue());
                                } else {
                                    d = calculateFitness(width.intValue(), height.intValue());
                                }
                                if (d < d2) {
                                    point = scaledDimensions;
                                    vastResource2 = fromVastResourceXmlManager;
                                    d2 = d;
                                    vastCompanionAdXmlManager = vastCompanionAdXmlManager2;
                                }
                                typeArr = typeArr2;
                                arrayList = arrayList2;
                                i = i2;
                            }
                        }
                        typeArr = typeArr2;
                        arrayList = arrayList2;
                        i = i2;
                    }
                } else {
                    typeArr2 = typeArr;
                }
                arrayList2 = arrayList;
                i2 = i;
                typeArr = typeArr2;
                arrayList = arrayList2;
                i = i2;
            }
            Type[] typeArr3 = typeArr;
            ArrayList arrayList4 = arrayList;
            int i4 = i;
            if (vastCompanionAdXmlManager != null) {
                vastResource = vastResource2;
                break;
            }
            i3++;
            values = typeArr3;
            arrayList3 = arrayList4;
            length = i4;
        }
        if (vastCompanionAdXmlManager == null) {
            return null;
        }
        VastCompanionAdConfig vastCompanionAdConfig = new VastCompanionAdConfig(point.x, point.y, vastResource, vastCompanionAdXmlManager.getClickThroughUrl(), vastCompanionAdXmlManager.getClickTrackers(), vastCompanionAdXmlManager.getCompanionCreativeViewTrackers());
        return vastCompanionAdConfig;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @VisibleForTesting
    public Map<String, VastCompanionAdConfig> getSocialActionsCompanionAds(@NonNull List<VastCompanionAdXmlManager> list) {
        Preconditions.checkNotNull(list, "managers cannot be null");
        HashMap hashMap = new HashMap();
        for (VastCompanionAdXmlManager vastCompanionAdXmlManager : list) {
            Integer width = vastCompanionAdXmlManager.getWidth();
            Integer height = vastCompanionAdXmlManager.getHeight();
            if (!(width == null || height == null)) {
                String adSlotId = vastCompanionAdXmlManager.getAdSlotId();
                if (!ADS_BY_AD_SLOT_ID.equals(adSlotId) ? !(!SOCIAL_ACTIONS_AD_SLOT_ID.equals(adSlotId) || width.intValue() < 50 || width.intValue() > 150 || height.intValue() < 10 || height.intValue() > 50) : !(width.intValue() < 25 || width.intValue() > 75 || height.intValue() < 10 || height.intValue() > 50)) {
                    VastResource fromVastResourceXmlManager = VastResource.fromVastResourceXmlManager(vastCompanionAdXmlManager.getResourceXmlManager(), Type.HTML_RESOURCE, width.intValue(), height.intValue());
                    if (fromVastResourceXmlManager != null) {
                        VastCompanionAdConfig vastCompanionAdConfig = new VastCompanionAdConfig(width.intValue(), height.intValue(), fromVastResourceXmlManager, vastCompanionAdXmlManager.getClickThroughUrl(), vastCompanionAdXmlManager.getClickTrackers(), vastCompanionAdXmlManager.getCompanionCreativeViewTrackers());
                        hashMap.put(adSlotId, vastCompanionAdConfig);
                    }
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    @VisibleForTesting
    public Point getScaledDimensions(int i, int i2, Type type, CompanionOrientation companionOrientation) {
        int i3;
        int i4;
        Point point = new Point(i, i2);
        Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        int width = defaultDisplay.getWidth();
        int height = defaultDisplay.getHeight();
        int dipsToIntPixels = Dips.dipsToIntPixels((float) i, this.mContext);
        int dipsToIntPixels2 = Dips.dipsToIntPixels((float) i2, this.mContext);
        if (CompanionOrientation.LANDSCAPE == companionOrientation) {
            i3 = Math.max(width, height);
            i4 = Math.min(width, height);
        } else {
            i3 = Math.min(width, height);
            i4 = Math.max(width, height);
        }
        if (dipsToIntPixels <= i3 - 16 && dipsToIntPixels2 <= i4 - 16) {
            return point;
        }
        Point point2 = new Point();
        if (Type.HTML_RESOURCE == type) {
            point2.x = Math.min(i3, dipsToIntPixels);
            point2.y = Math.min(i4, dipsToIntPixels2);
        } else {
            float f = (float) dipsToIntPixels;
            float f2 = f / ((float) i3);
            float f3 = (float) dipsToIntPixels2;
            float f4 = f3 / ((float) i4);
            if (f2 >= f4) {
                point2.x = i3;
                point2.y = (int) (f3 / f2);
            } else {
                point2.x = (int) (f / f4);
                point2.y = i4;
            }
        }
        point2.x -= 16;
        point2.y -= 16;
        if (point2.x < 0 || point2.y < 0) {
            return point;
        }
        point2.x = Dips.pixelsToIntDips((float) point2.x, this.mContext);
        point2.y = Dips.pixelsToIntDips((float) point2.y, this.mContext);
        return point2;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    @VisibleForTesting
    public VastIconConfig getBestIcon(@NonNull List<VastIconXmlManager> list) {
        Type[] values;
        List<VastIconXmlManager> list2 = list;
        Preconditions.checkNotNull(list2, "managers cannot be null");
        ArrayList<VastIconXmlManager> arrayList = new ArrayList<>(list2);
        for (Type type : Type.values()) {
            for (VastIconXmlManager vastIconXmlManager : arrayList) {
                Integer width = vastIconXmlManager.getWidth();
                Integer height = vastIconXmlManager.getHeight();
                if (width != null && width.intValue() > 0 && width.intValue() <= 300 && height != null && height.intValue() > 0 && height.intValue() <= 300) {
                    VastResource fromVastResourceXmlManager = VastResource.fromVastResourceXmlManager(vastIconXmlManager.getResourceXmlManager(), type, width.intValue(), height.intValue());
                    if (fromVastResourceXmlManager != null) {
                        VastIconConfig vastIconConfig = new VastIconConfig(vastIconXmlManager.getWidth().intValue(), vastIconXmlManager.getHeight().intValue(), vastIconXmlManager.getOffsetMS(), vastIconXmlManager.getDurationMS(), fromVastResourceXmlManager, vastIconXmlManager.getClickTrackingUris(), vastIconXmlManager.getClickThroughUri(), vastIconXmlManager.getViewTrackingUris());
                        return vastIconConfig;
                    }
                }
            }
        }
        return null;
    }

    private double calculateFitness(int i, int i2) {
        return (Math.abs(Math.log((((double) i) / ((double) i2)) / this.mScreenAspectRatio)) * 70.0d) + (Math.abs(Math.log(((double) (i * i2)) / ((double) this.mScreenAreaDp))) * 30.0d);
    }

    static boolean isValidSequenceNumber(@Nullable String str) {
        boolean z = true;
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            if (Integer.parseInt(str) >= 2) {
                z = false;
            }
            return z;
        } catch (NumberFormatException unused) {
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0037  */
    @android.support.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String followVastRedirect(@android.support.annotation.NonNull java.lang.String r5) throws java.io.IOException {
        /*
            r4 = this;
            com.mopub.common.Preconditions.checkNotNull(r5)
            int r0 = r4.mTimesFollowedVastRedirect
            r1 = 0
            r2 = 10
            if (r0 >= r2) goto L_0x003b
            int r0 = r0 + 1
            r4.mTimesFollowedVastRedirect = r0
            java.net.HttpURLConnection r5 = com.mopub.common.MoPubHttpUrlConnection.getHttpUrlConnection(r5)     // Catch:{ all -> 0x002f }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ all -> 0x002d }
            java.io.InputStream r2 = r5.getInputStream()     // Catch:{ all -> 0x002d }
            r0.<init>(r2)     // Catch:{ all -> 0x002d }
            java.lang.String r1 = com.mopub.common.util.Strings.fromStream(r0)     // Catch:{ all -> 0x0028 }
            com.mopub.common.util.Streams.closeStream(r0)
            if (r5 == 0) goto L_0x0027
            r5.disconnect()
        L_0x0027:
            return r1
        L_0x0028:
            r1 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L_0x0032
        L_0x002d:
            r0 = move-exception
            goto L_0x0032
        L_0x002f:
            r5 = move-exception
            r0 = r5
            r5 = r1
        L_0x0032:
            com.mopub.common.util.Streams.closeStream(r1)
            if (r5 == 0) goto L_0x003a
            r5.disconnect()
        L_0x003a:
            throw r0
        L_0x003b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.mobileads.VastXmlManagerAggregator.followVastRedirect(java.lang.String):java.lang.String");
    }
}
