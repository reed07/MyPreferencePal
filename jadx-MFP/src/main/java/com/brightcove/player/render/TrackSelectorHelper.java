package com.brightcove.player.render;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.brightcove.player.captioning.BrightcoveCaptionFormat;
import com.brightcove.player.display.ExoPlayerVideoDisplayComponent;
import com.brightcove.player.edge.OfflineStoreManager;
import com.brightcove.player.model.DeliveryType;
import com.brightcove.player.util.MediaSourceUtil;
import com.brightcove.player.util.Objects;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector.ParametersBuilder;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector.SelectionOverride;
import com.google.android.exoplayer2.trackselection.MappingTrackSelector.MappedTrackInfo;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrackSelectorHelper {
    private final Map<Integer, String> mAudioTracksMap = new LinkedHashMap();
    private final ExoPlayer player;
    private TrackSelectionArray trackSelections;
    private final DefaultTrackSelector trackSelector;

    public TrackSelectorHelper(@NonNull ExoPlayer exoPlayer, @NonNull DefaultTrackSelector defaultTrackSelector) {
        this.player = (ExoPlayer) Objects.requireNonNull(exoPlayer, "Exoplayer cannot be null");
        this.trackSelector = (DefaultTrackSelector) Objects.requireNonNull(defaultTrackSelector, "TrackSelector cannot be null");
    }

    @NonNull
    public List<Format> getAvailableFormatList(int i) {
        int i2;
        if (this.trackSelector.getCurrentMappedTrackInfo() == null) {
            return Collections.emptyList();
        }
        TrackGroupArray trackGroups = this.trackSelector.getCurrentMappedTrackInfo().getTrackGroups(getRendererIndex(i));
        if (trackGroups == null) {
            i2 = 0;
        } else {
            i2 = trackGroups.length;
        }
        if (i2 == 0) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < i2; i3++) {
            TrackGroup trackGroup = trackGroups.get(i3);
            if (trackGroup != null && trackGroup.length > 0) {
                arrayList.add(trackGroup.getFormat(0));
            }
        }
        return arrayList;
    }

    public void selectCaption(@NonNull BrightcoveCaptionFormat brightcoveCaptionFormat) {
        List availableFormatList = getAvailableFormatList(3);
        if (!availableFormatList.isEmpty()) {
            int i = 0;
            while (i < availableFormatList.size()) {
                Format format = (Format) availableFormatList.get(i);
                String str = format.sampleMimeType == null ? "" : format.sampleMimeType;
                if (!brightcoveCaptionFormat.language().equals(str.contains("608") ? ExoPlayerVideoDisplayComponent.resourceBundle.getString(ExoPlayerVideoDisplayComponent.UNKNOWN_CC) : format.language) || !brightcoveCaptionFormat.type().equals(str)) {
                    i++;
                } else {
                    int rendererIndex = getRendererIndex(3);
                    ParametersBuilder buildUponParameters = this.trackSelector.buildUponParameters();
                    buildUponParameters.setRendererDisabled(rendererIndex, false);
                    SelectionOverride selectionOverride = new SelectionOverride(i, 0);
                    MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
                    if (currentMappedTrackInfo != null) {
                        buildUponParameters.setSelectionOverride(rendererIndex, currentMappedTrackInfo.getTrackGroups(rendererIndex), selectionOverride);
                    }
                    this.trackSelector.setParameters(buildUponParameters);
                    return;
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:16:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void selectAudio(@android.support.annotation.NonNull java.lang.String r5) {
        /*
            r4 = this;
            java.util.Map<java.lang.Integer, java.lang.String> r0 = r4.mAudioTracksMap
            boolean r0 = r0.containsValue(r5)
            r1 = -1
            if (r0 == 0) goto L_0x0032
            java.util.Map<java.lang.Integer, java.lang.String> r0 = r4.mAudioTracksMap
            java.util.Set r0 = r0.keySet()
            java.util.Iterator r0 = r0.iterator()
        L_0x0013:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x0032
            java.lang.Object r2 = r0.next()
            java.lang.Integer r2 = (java.lang.Integer) r2
            java.util.Map<java.lang.Integer, java.lang.String> r3 = r4.mAudioTracksMap
            java.lang.Object r3 = r3.get(r2)
            java.lang.String r3 = (java.lang.String) r3
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x0013
            int r5 = r2.intValue()
            goto L_0x0033
        L_0x0032:
            r5 = -1
        L_0x0033:
            if (r5 == r1) goto L_0x005d
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector$SelectionOverride r0 = new com.google.android.exoplayer2.trackselection.DefaultTrackSelector$SelectionOverride
            r1 = 1
            int[] r2 = new int[r1]
            r3 = 0
            r2[r3] = r3
            r0.<init>(r5, r2)
            int r5 = r4.getRendererIndex(r1)
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector r1 = r4.trackSelector
            com.google.android.exoplayer2.trackselection.MappingTrackSelector$MappedTrackInfo r1 = r1.getCurrentMappedTrackInfo()
            if (r1 == 0) goto L_0x005d
            com.google.android.exoplayer2.source.TrackGroupArray r1 = r1.getTrackGroups(r5)
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector r2 = r4.trackSelector
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector$ParametersBuilder r3 = r2.buildUponParameters()
            com.google.android.exoplayer2.trackselection.DefaultTrackSelector$ParametersBuilder r5 = r3.setSelectionOverride(r5, r1, r0)
            r2.setParameters(r5)
        L_0x005d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.render.TrackSelectorHelper.selectAudio(java.lang.String):void");
    }

    public String getSelectedAudioLanguage() {
        int rendererIndex = getRendererIndex(1);
        MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        SelectionOverride selectionOverride = currentMappedTrackInfo != null ? this.trackSelector.getParameters().getSelectionOverride(rendererIndex, currentMappedTrackInfo.getTrackGroups(rendererIndex)) : null;
        int i = 0;
        if (selectionOverride != null) {
            i = selectionOverride.groupIndex;
        } else {
            TrackSelectionArray trackSelectionArray = this.trackSelections;
            if (trackSelectionArray != null) {
                TrackSelection trackSelection = trackSelectionArray.get(rendererIndex);
                if (trackSelection != null) {
                    i = trackSelection.getSelectedIndex();
                }
            }
        }
        return this.mAudioTracksMap.isEmpty() ? "" : (String) this.mAudioTracksMap.get(Integer.valueOf(i));
    }

    public void updateTracksSelectionArray(@Nullable TrackSelectionArray trackSelectionArray) {
        this.trackSelections = trackSelectionArray;
    }

    public void enableTrack(int i) {
        ParametersBuilder buildUponParameters = this.trackSelector.buildUponParameters();
        int rendererIndex = getRendererIndex(i);
        buildUponParameters.setRendererDisabled(rendererIndex, false);
        MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        if (currentMappedTrackInfo != null) {
            buildUponParameters.setSelectionOverride(rendererIndex, currentMappedTrackInfo.getTrackGroups(rendererIndex), new SelectionOverride(0, 0));
        }
        this.trackSelector.setParameters(buildUponParameters);
    }

    public void disableTrack(int i) {
        DefaultTrackSelector defaultTrackSelector = this.trackSelector;
        defaultTrackSelector.setParameters(defaultTrackSelector.buildUponParameters().setRendererDisabled(i, true));
    }

    @NonNull
    public Map<Integer, String> getAudioTracksIndexMap(Context context, DeliveryType deliveryType, boolean z) {
        int i;
        this.mAudioTracksMap.clear();
        if (this.trackSelector.getCurrentMappedTrackInfo() == null) {
            return Collections.emptyMap();
        }
        TrackGroupArray trackGroups = this.trackSelector.getCurrentMappedTrackInfo().getTrackGroups(getRendererIndex(1));
        if (trackGroups == null) {
            i = 0;
        } else {
            i = trackGroups.length;
        }
        if (i == 0) {
            return Collections.emptyMap();
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (int i2 = 0; i2 < i; i2++) {
            TrackGroup trackGroup = trackGroups.get(i2);
            if (trackGroup != null && trackGroup.length > 0) {
                Format format = null;
                if (z) {
                    int i3 = 0;
                    while (true) {
                        if (i3 >= trackGroup.length) {
                            break;
                        }
                        Format format2 = trackGroup.getFormat(i3);
                        if (isFormatOffline(context, format2)) {
                            format = format2;
                            break;
                        }
                        i3++;
                    }
                } else {
                    format = trackGroup.getFormat(0);
                }
                if (format != null) {
                    linkedHashMap2.put(Integer.valueOf(i2), format);
                    linkedHashMap.put(Integer.valueOf(i2), getAudioString(format, deliveryType, false));
                }
            }
        }
        if (linkedHashMap.size() > 1) {
            ArrayList arrayList = new ArrayList(linkedHashMap.keySet());
            ArrayList arrayList2 = new ArrayList(linkedHashMap.values());
            int i4 = 0;
            while (i4 < arrayList2.size()) {
                String str = (String) arrayList2.get(i4);
                int i5 = i4 + 1;
                boolean z2 = false;
                for (int i6 = i5; i6 < arrayList2.size(); i6++) {
                    if (str.compareTo((String) arrayList2.get(i6)) == 0) {
                        int intValue = ((Integer) arrayList.get(i6)).intValue();
                        linkedHashMap.put(Integer.valueOf(intValue), getAudioString((Format) linkedHashMap2.get(Integer.valueOf(intValue)), deliveryType, true));
                        z2 = true;
                    }
                }
                if (z2) {
                    int intValue2 = ((Integer) arrayList.get(i4)).intValue();
                    linkedHashMap.put(Integer.valueOf(intValue2), getAudioString((Format) linkedHashMap2.get(Integer.valueOf(intValue2)), deliveryType, true));
                }
                i4 = i5;
            }
        }
        this.mAudioTracksMap.putAll(linkedHashMap);
        return linkedHashMap;
    }

    private String getAudioString(Format format, DeliveryType deliveryType, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (deliveryType == DeliveryType.HLS) {
            sb.append(format.label);
        } else {
            sb.append(format.language);
            if (z) {
                sb.append(" (");
                sb.append(MediaSourceUtil.getBrightcoveRoleValue(format.selectionFlags));
                sb.append(")");
            }
        }
        return sb.toString();
    }

    public int getRendererIndex(int i) {
        MappedTrackInfo currentMappedTrackInfo = this.trackSelector.getCurrentMappedTrackInfo();
        if (currentMappedTrackInfo != null) {
            for (int i2 = 0; i2 < currentMappedTrackInfo.getRendererCount(); i2++) {
                if (this.player.getRendererType(i2) == i) {
                    return i2;
                }
            }
        }
        return -1;
    }

    @NonNull
    public List<Format> findOfflineFormatList(@NonNull Context context, @NonNull List<Format> list) {
        ArrayList arrayList = new ArrayList();
        for (Format format : list) {
            if (isFormatOffline(context, format)) {
                arrayList.add(format);
            }
        }
        return arrayList;
    }

    private boolean isFormatOffline(@NonNull Context context, @NonNull Format format) {
        OfflineStoreManager instance = OfflineStoreManager.getInstance(context);
        String findRenditionUrl = MediaSourceUtil.findRenditionUrl(this.player.getCurrentManifest(), MediaSourceUtil.findTrackType(format), format);
        return !TextUtils.isEmpty(findRenditionUrl) && instance.findOfflineAssetUri(Uri.parse(findRenditionUrl)) != null;
    }
}
