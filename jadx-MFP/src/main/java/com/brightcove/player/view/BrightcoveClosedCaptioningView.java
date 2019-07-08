package com.brightcove.player.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.Html;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.brightcove.player.R;
import com.brightcove.player.captioning.BrightcoveCaptionFormat;
import com.brightcove.player.captioning.BrightcoveCaptionStyle;
import com.brightcove.player.captioning.BrightcoveClosedCaption;
import com.brightcove.player.captioning.BrightcoveClosedCaptioningTextView;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Component;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Emits;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventEmitter;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.event.RegisteringEventEmitter;
import com.brightcove.player.management.BrightcoveClosedCaptioningManager;
import com.brightcove.player.mediacontroller.ShowHideController;
import com.brightcove.player.model.Block;
import com.brightcove.player.model.CaptionsDocument;
import com.brightcove.player.model.Length;
import com.brightcove.player.model.Region;
import com.brightcove.player.model.Region.DisplayAlign;
import com.brightcove.player.model.Span;
import com.brightcove.player.model.StyledElement;
import com.brightcove.player.model.StyledElement.FontStyle;
import com.brightcove.player.model.StyledElement.FontWeight;
import com.brightcove.player.model.StyledElement.TextAlign;
import com.brightcove.player.model.StyledElement.TextDecoration;
import com.brightcove.player.model.StyledElement.Unit;
import com.brightcove.player.model.TTMLDocument;
import com.brightcove.player.model.WebVTTDocument;
import com.brightcove.player.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

@ListensFor(events = {"caption", "completed", "didLoadClosedCaptions", "progress", "toggleClosedCaptions", "didShowMediaControls", "didHideMediaControls", "selectClosedCaptionTrack", "didEnterPictureInPictureMode", "didExitPictureInPictureMode"})
@Emits(events = {"captionsAvailable"})
public class BrightcoveClosedCaptioningView extends FrameLayout implements Component {
    @Deprecated
    public static final int DEFAULT_HORIZONTAL_GRAVITY = 17;
    @Deprecated
    public static final int DEFAULT_VERTICAL_GRAVITY = 80;
    protected static final float SAFE_AREA = 0.05f;
    protected static final String TAG = "BrightcoveClosedCaptioningView";
    private EventListener captionListener = new EventListener() {
        @Default
        public void processEvent(Event event) {
            int i;
            if (BrightcoveClosedCaptioningView.this.isEnabled() && BrightcoveClosedCaptioningView.this.getMode() == ClosedCaptioningMode.ON) {
                if (BrightcoveClosedCaptioningView.this.currentCaptionBlocks != null && BrightcoveClosedCaptioningView.this.currentCaptionBlocks.size() > 0) {
                    for (int i2 = 0; i2 < BrightcoveClosedCaptioningView.this.currentCaptionBlocks.size(); i2++) {
                        LinearLayout linearLayout = (LinearLayout) BrightcoveClosedCaptioningView.this.currentCaptionBlocks.get(i2);
                        if (!(linearLayout == null || linearLayout.getParent() == null)) {
                            BrightcoveClosedCaptioningView.this.removeView(linearLayout);
                        }
                    }
                }
                Map<String, Object> map = event.properties;
                String str = (String) map.get("text");
                Object obj = map.get(AbstractEvent.PLAYHEAD_POSITION);
                if (obj == null) {
                    i = -1;
                } else {
                    i = ((Integer) obj).intValue();
                }
                if (!TextUtils.isEmpty(str) && i != -1) {
                    StringBuilder sb = (StringBuilder) BrightcoveClosedCaptioningView.this.currentCaptionBlockArray.get(i);
                    if (sb == null) {
                        BrightcoveClosedCaptioningView.this.currentCaptionBlockArray.clear();
                        sb = new StringBuilder();
                        BrightcoveClosedCaptioningView.this.currentCaptionBlockArray.put(i, sb);
                    } else {
                        sb.append("\n");
                    }
                    sb.append(str);
                    ViewGroup buildCaptionBlock = BrightcoveClosedCaptioningView.this.buildCaptionBlock(null, new BrightcoveClosedCaption(i, i, sb.toString()));
                    if (buildCaptionBlock != null) {
                        buildCaptionBlock = BrightcoveClosedCaptioningView.this.styleCaptions(buildCaptionBlock);
                        BrightcoveClosedCaptioningView.this.addView(buildCaptionBlock);
                        buildCaptionBlock.setVisibility(0);
                        buildCaptionBlock.invalidate();
                    }
                    BrightcoveClosedCaptioningView.this.currentCaptionBlocks.clear();
                    BrightcoveClosedCaptioningView.this.currentCaptionBlocks.add((LinearLayout) buildCaptionBlock);
                }
            }
        }
    };
    protected TreeMap<Integer, ArrayList<TimeMapEntry>> captionsPerSecondMap;
    /* access modifiers changed from: private */
    public int controlsHeight;
    protected SparseArray<StringBuilder> currentCaptionBlockArray = new SparseArray<>();
    protected ArrayList<LinearLayout> currentCaptionBlocks = new ArrayList<>();
    protected ClosedCaptioningMode currentMode = ClosedCaptioningMode.OFF;
    protected Span defaultStyle;
    protected String defaultStyleId;
    private EventListener didHideMediaControlsListener = new EventListener() {
        @Default
        public void processEvent(Event event) {
            BrightcoveClosedCaptioningView.this.controlsHeight = -1;
            BrightcoveClosedCaptioningView.this.updatePadding();
        }
    };
    protected EventListener didLoadCaptionsListener = new EventListener() {
        @Default
        public void processEvent(Event event) {
            TTMLDocument tTMLDocument = (TTMLDocument) event.properties.get(AbstractEvent.TTML_DOCUMENT);
            if (tTMLDocument != null) {
                BrightcoveClosedCaptioningView.this.prepareDFXPCaptions(tTMLDocument);
            } else {
                WebVTTDocument webVTTDocument = (WebVTTDocument) event.properties.get(AbstractEvent.WEBVTT_DOCUMENT);
                if (webVTTDocument != null) {
                    BrightcoveClosedCaptioningView.this.prepareWebVTTCaptions(webVTTDocument);
                } else {
                    Log.e(BrightcoveClosedCaptioningView.TAG, "Captions document was null. No captions to load.");
                    BrightcoveClosedCaptioningView.this.clear();
                }
            }
            BrightcoveClosedCaptioningView.this.updatePadding();
        }
    };
    private EventListener didShowMediaControlsListener = new EventListener() {
        @Default
        public void processEvent(Event event) {
            BrightcoveClosedCaptioningView.this.controlsHeight = event.getIntegerProperty(ShowHideController.CONTROLS_HEIGHT);
            BrightcoveClosedCaptioningView.this.updatePadding();
        }
    };
    protected EventEmitter eventEmitter;
    protected LayoutInflater inflater;
    private boolean keepCaptionsWithinVideoBounds = true;
    protected int lastProgressTime = 0;
    protected EventListener onSelectClosedCaptionListener = new EventListener() {
        public void processEvent(Event event) {
            Object obj = event.properties.get(AbstractEvent.CAPTION_URI);
            if ((obj instanceof Uri) && obj.toString().startsWith(BrightcoveCaptionFormat.BRIGHTCOVE_SCHEME)) {
                BrightcoveClosedCaptioningView.this.captionsPerSecondMap.clear();
                BrightcoveClosedCaptioningView.this.cancelProgressListener();
            }
        }
    };
    private EventListener pictureInPictureListener = new EventListener() {
        @Default
        public void processEvent(Event event) {
            BrightcoveClosedCaptioningView brightcoveClosedCaptioningView = BrightcoveClosedCaptioningView.this;
            brightcoveClosedCaptioningView.doUpdateCaption(brightcoveClosedCaptioningView.videoView.getCurrentPosition());
        }
    };
    protected EventListener progressListener = new EventListener() {
        @Default
        public void processEvent(Event event) {
            int integerProperty = event.getIntegerProperty(AbstractEvent.ORIGINAL_PLAYHEAD_POSITION);
            if (integerProperty == -1) {
                integerProperty = event.getIntegerProperty(AbstractEvent.PLAYHEAD_POSITION);
            }
            BrightcoveClosedCaptioningView.this.doUpdateCaption(integerProperty);
        }
    };
    protected int progressListenerToken = -1;
    private float safeAreaPercent = SAFE_AREA;
    protected EventListener toggleClosedCaptionsListener = new EventListener() {
        @Default
        public void processEvent(Event event) {
            if (((Boolean) event.properties.get(AbstractEvent.BOOLEAN)).booleanValue()) {
                BrightcoveClosedCaptioningView.this.setMode(ClosedCaptioningMode.ON);
                BrightcoveClosedCaptioningView.this.setVisibility(0);
                BrightcoveClosedCaptioningView.this.bringToFront();
                if (!BrightcoveClosedCaptioningView.this.captionsPerSecondMap.isEmpty()) {
                    BrightcoveClosedCaptioningView.this.registerProgressListener();
                    return;
                }
                return;
            }
            BrightcoveClosedCaptioningView.this.setMode(ClosedCaptioningMode.OFF);
            BrightcoveClosedCaptioningView.this.setVisibility(8);
            BrightcoveClosedCaptioningView.this.cancelProgressListener();
        }
    };
    protected BaseVideoView videoView;

    /* renamed from: com.brightcove.player.view.BrightcoveClosedCaptioningView$11 reason: invalid class name */
    static /* synthetic */ class AnonymousClass11 {
        static final /* synthetic */ int[] $SwitchMap$com$brightcove$player$model$StyledElement$FontStyle = new int[FontStyle.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$brightcove$player$model$StyledElement$FontWeight = new int[FontWeight.values().length];
        static final /* synthetic */ int[] $SwitchMap$com$brightcove$player$model$StyledElement$TextDecoration = new int[TextDecoration.values().length];

        static {
            try {
                $SwitchMap$com$brightcove$player$model$StyledElement$TextDecoration[TextDecoration.UNDERLINE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$brightcove$player$model$StyledElement$FontWeight[FontWeight.BOLD.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$brightcove$player$model$StyledElement$FontStyle[FontStyle.ITALIC.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public enum ClosedCaptioningMode {
        OFF,
        ON
    }

    protected class TimeMapEntry {
        public ViewGroup block;
        public BrightcoveClosedCaption closedCaption;

        public TimeMapEntry(BrightcoveClosedCaption brightcoveClosedCaption, ViewGroup viewGroup) {
            this.closedCaption = brightcoveClosedCaption;
            this.block = viewGroup;
        }
    }

    @Deprecated
    public void prepareLayout() {
    }

    public BrightcoveClosedCaptioningView(Context context) {
        super(context);
    }

    public BrightcoveClosedCaptioningView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BrightcoveClosedCaptioningView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setKeepCaptionsWithinVideoBounds(boolean z) {
        this.keepCaptionsWithinVideoBounds = z;
    }

    public int getSafeAreaMarginWidth() {
        LayoutParams layoutParams = getLayoutParams();
        int i = 0;
        int i2 = layoutParams != null ? layoutParams.width : 0;
        if (i2 <= 0) {
            i2 = this.videoView.getWidth();
            if (this.keepCaptionsWithinVideoBounds) {
                i = (i2 - this.videoView.getMeasuredVideoWidth()) / 2;
            }
        }
        return Math.round(((float) i2) * this.safeAreaPercent) + i;
    }

    public int getSafeAreaMarginHeight() {
        LayoutParams layoutParams = getLayoutParams();
        int i = 0;
        int i2 = layoutParams != null ? layoutParams.height : 0;
        if (i2 <= 0) {
            i2 = this.videoView.getHeight();
            if (this.keepCaptionsWithinVideoBounds) {
                i = (i2 - this.videoView.getMeasuredVideoHeight()) / 2;
            }
        }
        return Math.round(((float) i2) * this.safeAreaPercent) + i;
    }

    @Deprecated
    public void initialize(EventEmitter eventEmitter2) {
        initialize(eventEmitter2, null);
    }

    public void initialize(EventEmitter eventEmitter2, BaseVideoView baseVideoView) {
        this.eventEmitter = RegisteringEventEmitter.build(eventEmitter2, getClass());
        this.inflater = (LayoutInflater) getContext().getSystemService("layout_inflater");
        this.captionsPerSecondMap = new TreeMap<>();
        this.eventEmitter.on(EventType.DID_LOAD_CLOSED_CAPTIONS, this.didLoadCaptionsListener);
        this.eventEmitter.on(EventType.TOGGLE_CLOSED_CAPTIONS, this.toggleClosedCaptionsListener);
        this.eventEmitter.on(ShowHideController.DID_SHOW_MEDIA_CONTROLS, this.didShowMediaControlsListener);
        this.eventEmitter.on(ShowHideController.DID_HIDE_MEDIA_CONTROLS, this.didHideMediaControlsListener);
        this.eventEmitter.on("caption", this.captionListener);
        this.eventEmitter.on(EventType.SELECT_CLOSED_CAPTION_TRACK, this.onSelectClosedCaptionListener);
        this.eventEmitter.on(EventType.DID_ENTER_PICTURE_IN_PICTURE_MODE, this.pictureInPictureListener);
        this.eventEmitter.on(EventType.DID_EXIT_PICTURE_IN_PICTURE_MODE, this.pictureInPictureListener);
        this.eventEmitter.on("completed", new EventListener() {
            @Default
            public void processEvent(Event event) {
                if (BrightcoveClosedCaptioningView.this.currentCaptionBlocks != null && BrightcoveClosedCaptioningView.this.currentCaptionBlocks.size() > 0) {
                    for (int i = 0; i < BrightcoveClosedCaptioningView.this.currentCaptionBlocks.size(); i++) {
                        ((LinearLayout) BrightcoveClosedCaptioningView.this.currentCaptionBlocks.get(i)).setVisibility(8);
                        BrightcoveClosedCaptioningView.this.currentCaptionBlockArray.clear();
                    }
                }
            }
        });
        this.videoView = baseVideoView;
        if (this.videoView.getClosedCaptioningController().isCaptioningEnabled()) {
            this.currentMode = ClosedCaptioningMode.ON;
        } else {
            this.currentMode = ClosedCaptioningMode.OFF;
        }
        this.videoView.addOnLayoutChangeListener(new OnLayoutChangeListener() {
            public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                if (i != i5 || i3 != i7 || i2 != i6 || i4 != i8) {
                    BrightcoveClosedCaptioningView.this.updatePadding();
                }
            }
        });
    }

    public void clear() {
        setMode(ClosedCaptioningMode.OFF);
        cancelProgressListener();
        emitCaptionsAvailable(false);
        if (getChildCount() > 0) {
            removeAllViews();
        }
        TreeMap<Integer, ArrayList<TimeMapEntry>> treeMap = this.captionsPerSecondMap;
        if (treeMap != null && treeMap.size() > 0) {
            this.captionsPerSecondMap.clear();
        }
    }

    public void setMode(@NonNull ClosedCaptioningMode closedCaptioningMode) {
        this.currentMode = closedCaptioningMode;
    }

    @NonNull
    public ClosedCaptioningMode getMode() {
        return this.currentMode;
    }

    public ArrayList<LinearLayout> findCaptionsForPosition(int i) {
        ArrayList<LinearLayout> arrayList = new ArrayList<>();
        ArrayList arrayList2 = (ArrayList) this.captionsPerSecondMap.get(Integer.valueOf((int) Math.floor(((double) i) / 1000.0d)));
        if (arrayList2 != null) {
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                TimeMapEntry timeMapEntry = (TimeMapEntry) it.next();
                if (timeMapEntry != null && i >= timeMapEntry.closedCaption.getBeginTime() && i < timeMapEntry.closedCaption.getEndTime()) {
                    arrayList.add((LinearLayout) styleCaptions(timeMapEntry.block));
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public ViewGroup styleCaptions(ViewGroup viewGroup) {
        BrightcoveCaptionStyle style = BrightcoveClosedCaptioningManager.getInstance(getContext()).getStyle();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof BrightcoveClosedCaptioningTextView) {
                ((BrightcoveClosedCaptioningTextView) childAt).setStyle(style);
            } else if (childAt instanceof ViewGroup) {
                styleCaptions((ViewGroup) childAt);
            }
        }
        viewGroup.invalidate();
        viewGroup.requestLayout();
        return viewGroup;
    }

    public void prepareDFXPCaptions(TTMLDocument tTMLDocument) {
        Iterator it = tTMLDocument.getStyles().entrySet().iterator();
        if (it != null && it.hasNext()) {
            this.defaultStyle = new Span((StyledElement) ((Entry) it.next()).getValue());
            this.defaultStyleId = this.defaultStyle.getID();
        }
        List<BrightcoveClosedCaption> captions = tTMLDocument.getCaptions();
        if (captions == null || captions.size() <= 0) {
            Log.w(TAG, "No captions found, cannot prepare captions for this video");
            emitCaptionsAvailable(false);
            return;
        }
        if (this.videoView.getClosedCaptioningController().isCaptioningEnabled()) {
            setMode(ClosedCaptioningMode.ON);
            registerProgressListener();
        }
        emitCaptionsAvailable(true);
        if (this.captionsPerSecondMap.size() > 0) {
            for (Integer intValue : this.captionsPerSecondMap.keySet()) {
                ArrayList arrayList = (ArrayList) this.captionsPerSecondMap.get(Integer.valueOf(intValue.intValue()));
                if (arrayList.size() > 0) {
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        TimeMapEntry timeMapEntry = (TimeMapEntry) it2.next();
                        if (!(timeMapEntry == null || timeMapEntry.block == null)) {
                            removeView(timeMapEntry.block);
                        }
                    }
                }
            }
            this.captionsPerSecondMap.clear();
        }
        for (BrightcoveClosedCaption brightcoveClosedCaption : captions) {
            ViewGroup buildCaptionBlock = buildCaptionBlock(tTMLDocument, brightcoveClosedCaption);
            if (buildCaptionBlock.getChildCount() > 0) {
                Iterator it3 = brightcoveClosedCaption.getTimeRange().iterator();
                while (it3.hasNext()) {
                    Integer num = (Integer) it3.next();
                    if (this.captionsPerSecondMap.get(num) == null) {
                        this.captionsPerSecondMap.put(num, new ArrayList());
                    }
                    ((ArrayList) this.captionsPerSecondMap.get(num)).add(new TimeMapEntry(brightcoveClosedCaption, buildCaptionBlock));
                }
            }
        }
        refreshCaptions();
    }

    public void prepareWebVTTCaptions(WebVTTDocument webVTTDocument) {
        List<BrightcoveClosedCaption> captions = webVTTDocument.getCaptions();
        if (captions == null || captions.size() <= 0) {
            Log.w(TAG, "No captions found, cannot prepare captions for this video");
            emitCaptionsAvailable(false);
            return;
        }
        if (this.videoView.getClosedCaptioningController().isCaptioningEnabled()) {
            setMode(ClosedCaptioningMode.ON);
            registerProgressListener();
        }
        emitCaptionsAvailable(true);
        if (this.captionsPerSecondMap.size() > 0) {
            for (Integer intValue : this.captionsPerSecondMap.keySet()) {
                ArrayList arrayList = (ArrayList) this.captionsPerSecondMap.get(Integer.valueOf(intValue.intValue()));
                if (arrayList.size() > 0) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        TimeMapEntry timeMapEntry = (TimeMapEntry) it.next();
                        if (!(timeMapEntry == null || timeMapEntry.block == null)) {
                            removeView(timeMapEntry.block);
                        }
                    }
                }
            }
            this.captionsPerSecondMap.clear();
        }
        for (BrightcoveClosedCaption brightcoveClosedCaption : captions) {
            ViewGroup buildCaptionBlock = buildCaptionBlock(webVTTDocument, brightcoveClosedCaption);
            if (buildCaptionBlock.getChildCount() > 0) {
                Iterator it2 = brightcoveClosedCaption.getTimeRange().iterator();
                while (it2.hasNext()) {
                    Integer num = (Integer) it2.next();
                    if (this.captionsPerSecondMap.get(num) == null) {
                        this.captionsPerSecondMap.put(num, new ArrayList());
                    }
                    ((ArrayList) this.captionsPerSecondMap.get(num)).add(new TimeMapEntry(brightcoveClosedCaption, buildCaptionBlock));
                }
            }
        }
        refreshCaptions();
    }

    /* access modifiers changed from: protected */
    public int getCaptionTextAlignAsGravity(BrightcoveClosedCaption brightcoveClosedCaption) {
        if (brightcoveClosedCaption.getTextAlign() != null) {
            if (brightcoveClosedCaption.getTextAlign() == TextAlign.LEFT) {
                return 3;
            }
            if (brightcoveClosedCaption.getTextAlign() == TextAlign.START) {
                return 8388611;
            }
            if (brightcoveClosedCaption.getTextAlign() == TextAlign.RIGHT) {
                return 5;
            }
            if (brightcoveClosedCaption.getTextAlign() == TextAlign.END) {
                return 8388613;
            }
        }
        return 1;
    }

    /* access modifiers changed from: protected */
    public ViewGroup buildCaptionBlock(CaptionsDocument captionsDocument, BrightcoveClosedCaption brightcoveClosedCaption) {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(80);
        linearLayout.setBaselineAligned(false);
        linearLayout.setOrientation(1);
        BrightcoveClosedCaptioningTextView brightcoveClosedCaptioningTextView = new BrightcoveClosedCaptioningTextView(getContext());
        LinearLayout linearLayout2 = null;
        Region resolveRegionForBlock = captionsDocument != null ? resolveRegionForBlock((Block) brightcoveClosedCaption, captionsDocument) : null;
        if (resolveRegionForBlock != null) {
            linearLayout2 = applyHorizontal(resolveRegionForBlock, applyDisplayAlign(resolveRegionForBlock, brightcoveClosedCaptioningTextView), brightcoveClosedCaptioningTextView);
            applyVertical(resolveRegionForBlock, linearLayout, linearLayout2, brightcoveClosedCaptioningTextView);
        }
        if (linearLayout2 != null && linearLayout2.getParent() == null) {
            linearLayout.addView(linearLayout2);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout2.getLayoutParams();
            layoutParams.gravity = 80;
            layoutParams.height = -2;
            layoutParams.width = -1;
            linearLayout2.setLayoutParams(layoutParams);
        } else if (brightcoveClosedCaptioningTextView.getParent() == null) {
            linearLayout.addView(brightcoveClosedCaptioningTextView);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) brightcoveClosedCaptioningTextView.getLayoutParams();
            layoutParams2.gravity = 81;
            layoutParams2.height = -2;
            layoutParams2.width = -1;
            brightcoveClosedCaptioningTextView.setLayoutParams(layoutParams2);
        }
        applyText(captionsDocument, brightcoveClosedCaption, brightcoveClosedCaptioningTextView);
        brightcoveClosedCaptioningTextView.setGravity(getCaptionTextAlignAsGravity(brightcoveClosedCaption));
        return linearLayout;
    }

    private LinearLayout applyDisplayAlign(Region region, BrightcoveClosedCaptioningTextView brightcoveClosedCaptioningTextView) {
        DisplayAlign displayAlign = region.getDisplayAlign();
        if (displayAlign == null || displayAlign == DisplayAlign.CENTER) {
            return null;
        }
        int i = 17;
        if (displayAlign == DisplayAlign.BEFORE) {
            i = 8388611;
        } else if (displayAlign == DisplayAlign.AFTER) {
            i = 8388613;
        }
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setGravity(i);
        linearLayout.setBaselineAligned(false);
        linearLayout.addView(brightcoveClosedCaptioningTextView);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) brightcoveClosedCaptioningTextView.getLayoutParams();
        layoutParams.gravity = i;
        layoutParams.height = -2;
        layoutParams.width = -2;
        brightcoveClosedCaptioningTextView.setLayoutParams(layoutParams);
        return linearLayout;
    }

    private LinearLayout applyHorizontal(Region region, LinearLayout linearLayout, BrightcoveClosedCaptioningTextView brightcoveClosedCaptioningTextView) {
        Length originX = region.getOriginX();
        if (originX != null) {
            double value = originX.getValue();
            Unit unit = originX.getUnit();
            if (unit == Unit.PERCENT) {
                int i = (value > 50.0d ? 1 : (value == 50.0d ? 0 : -1));
                if (i != 0) {
                    int i2 = 17;
                    if (linearLayout == null) {
                        linearLayout = new LinearLayout(getContext());
                        linearLayout.setGravity(17);
                        linearLayout.setOrientation(0);
                        linearLayout.setBaselineAligned(false);
                        linearLayout.setWeightSum(100.0f);
                    }
                    View view = new View(getContext());
                    linearLayout.addView(view);
                    linearLayout.addView(brightcoveClosedCaptioningTextView);
                    View view2 = new View(getContext());
                    linearLayout.addView(view2);
                    if (value < 50.0d) {
                        i2 = 8388627;
                    } else if (i > 0) {
                        i2 = 8388629;
                    }
                    linearLayout.setGravity(i2);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
                    layoutParams.weight = (float) value;
                    layoutParams.height = 50;
                    layoutParams.width = 0;
                    view.setLayoutParams(layoutParams);
                    LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) brightcoveClosedCaptioningTextView.getLayoutParams();
                    layoutParams2.height = -2;
                    layoutParams2.width = -2;
                    layoutParams2.gravity = i2;
                    brightcoveClosedCaptioningTextView.setLayoutParams(layoutParams2);
                    LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) view2.getLayoutParams();
                    layoutParams3.weight = (float) (100.0d - value);
                    layoutParams3.height = 50;
                    layoutParams3.width = 0;
                    view2.setLayoutParams(layoutParams3);
                }
            } else {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("applyHorizontal: unhandled X length unit = ");
                sb.append(unit);
                Log.w(str, sb.toString());
            }
        }
        return linearLayout;
    }

    /* JADX WARNING: type inference failed for: r14v2, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r14v3 */
    /* JADX WARNING: type inference failed for: r14v4, types: [android.view.View] */
    /* JADX WARNING: type inference failed for: r14v5 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void applyVertical(com.brightcove.player.model.Region r12, android.widget.LinearLayout r13, android.widget.LinearLayout r14, com.brightcove.player.captioning.BrightcoveClosedCaptioningTextView r15) {
        /*
            r11 = this;
            com.brightcove.player.model.Length r12 = r12.getOriginY()
            if (r12 == 0) goto L_0x0127
            double r0 = r12.getValue()
            com.brightcove.player.model.StyledElement$Unit r12 = r12.getUnit()
            com.brightcove.player.model.StyledElement$Unit r2 = com.brightcove.player.model.StyledElement.Unit.PERCENT
            r3 = 0
            r4 = 1120403456(0x42c80000, float:100.0)
            r5 = -2
            if (r12 != r2) goto L_0x009a
            if (r14 != 0) goto L_0x0019
            r14 = r15
        L_0x0019:
            r6 = 4632233691727265792(0x4049000000000000, double:50.0)
            r12 = 17
            int r15 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r15 == 0) goto L_0x0083
            r13.setWeightSum(r4)
            android.view.View r2 = new android.view.View
            android.content.Context r4 = r11.getContext()
            r2.<init>(r4)
            r13.addView(r2)
            r13.addView(r14)
            android.view.View r4 = new android.view.View
            android.content.Context r8 = r11.getContext()
            r4.<init>(r8)
            r13.addView(r4)
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 >= 0) goto L_0x0046
            r12 = 49
            goto L_0x004a
        L_0x0046:
            if (r15 <= 0) goto L_0x004a
            r12 = 81
        L_0x004a:
            r13.setGravity(r12)
            android.view.ViewGroup$LayoutParams r13 = r2.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r13 = (android.widget.LinearLayout.LayoutParams) r13
            float r15 = (float) r0
            r13.weight = r15
            r13.height = r3
            r15 = 50
            r13.width = r15
            r2.setLayoutParams(r13)
            android.view.ViewGroup$LayoutParams r13 = r14.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r13 = (android.widget.LinearLayout.LayoutParams) r13
            r13.height = r5
            r13.width = r5
            r13.gravity = r12
            r14.setLayoutParams(r13)
            android.view.ViewGroup$LayoutParams r12 = r4.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r12 = (android.widget.LinearLayout.LayoutParams) r12
            r13 = 4636737291354636288(0x4059000000000000, double:100.0)
            double r13 = r13 - r0
            float r13 = (float) r13
            r12.weight = r13
            r12.height = r3
            r12.width = r15
            r4.setLayoutParams(r12)
            goto L_0x0127
        L_0x0083:
            r13.setGravity(r12)
            r13.addView(r14)
            android.view.ViewGroup$LayoutParams r13 = r14.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r13 = (android.widget.LinearLayout.LayoutParams) r13
            r13.height = r5
            r13.width = r5
            r13.gravity = r12
            r14.setLayoutParams(r13)
            goto L_0x0127
        L_0x009a:
            com.brightcove.player.model.StyledElement$Unit r2 = com.brightcove.player.model.StyledElement.Unit.LINE
            if (r12 != r2) goto L_0x0111
            if (r14 != 0) goto L_0x00a1
            r14 = r15
        L_0x00a1:
            float r12 = r15.getTextSize()
            int r12 = (int) r12
            r13.setWeightSum(r4)
            android.view.View r15 = new android.view.View
            android.content.Context r2 = r11.getContext()
            r15.<init>(r2)
            r13.addView(r15)
            r13.addView(r14)
            android.view.View r2 = new android.view.View
            android.content.Context r6 = r11.getContext()
            r2.<init>(r6)
            r13.addView(r2)
            r6 = 0
            r8 = 0
            int r9 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r9 < 0) goto L_0x00d5
            r6 = 48
            double r9 = (double) r12
            double r9 = r9 * r0
            int r12 = (int) r9
            r4 = 0
            r8 = 1120403456(0x42c80000, float:100.0)
            goto L_0x00e1
        L_0x00d5:
            r6 = 80
            double r9 = (double) r12
            double r9 = r9 * r0
            double r0 = java.lang.Math.abs(r9)
            int r12 = (int) r0
            r3 = r12
            r12 = 0
        L_0x00e1:
            r13.setGravity(r6)
            android.view.ViewGroup$LayoutParams r13 = r15.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r13 = (android.widget.LinearLayout.LayoutParams) r13
            r13.weight = r4
            r13.height = r12
            r12 = -1
            r13.width = r12
            r15.setLayoutParams(r13)
            android.view.ViewGroup$LayoutParams r13 = r14.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r13 = (android.widget.LinearLayout.LayoutParams) r13
            r13.height = r5
            r13.width = r12
            r14.setLayoutParams(r13)
            android.view.ViewGroup$LayoutParams r13 = r2.getLayoutParams()
            android.widget.LinearLayout$LayoutParams r13 = (android.widget.LinearLayout.LayoutParams) r13
            r13.weight = r8
            r13.height = r3
            r13.width = r12
            r2.setLayoutParams(r13)
            goto L_0x0127
        L_0x0111:
            java.lang.String r13 = TAG
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            r14.<init>()
            java.lang.String r15 = "applyVertical: unhandled Y length unit = "
            r14.append(r15)
            r14.append(r12)
            java.lang.String r12 = r14.toString()
            android.util.Log.w(r13, r12)
        L_0x0127:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.view.BrightcoveClosedCaptioningView.applyVertical(com.brightcove.player.model.Region, android.widget.LinearLayout, android.widget.LinearLayout, com.brightcove.player.captioning.BrightcoveClosedCaptioningTextView):void");
    }

    private void applyText(CaptionsDocument captionsDocument, BrightcoveClosedCaption brightcoveClosedCaption, BrightcoveClosedCaptioningTextView brightcoveClosedCaptioningTextView) {
        if (captionsDocument instanceof TTMLDocument) {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (List list : brightcoveClosedCaption.getLines()) {
                if (list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        Span span = (Span) list.get(i);
                        if (brightcoveClosedCaption.getStyleName() == null || brightcoveClosedCaption.getStyleName().equals(this.defaultStyleId)) {
                            span.setFontSize(this.defaultStyle.getFontSize());
                            span.setFontStyle(this.defaultStyle.getFontStyle());
                            span.setColor(this.defaultStyle.getColor());
                            span.setBackgroundColor(this.defaultStyle.getBackgroundColor());
                        }
                        spannableStringBuilder.append(buildSpannableString(span));
                    }
                }
            }
            brightcoveClosedCaptioningTextView.setText(collapseWhitespace(spannableStringBuilder));
            return;
        }
        if (!(captionsDocument instanceof WebVTTDocument)) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Unrecognized document type: ");
            sb.append(captionsDocument);
            Log.w(str, sb.toString());
        }
        brightcoveClosedCaptioningTextView.setText(Html.fromHtml(brightcoveClosedCaption.getCaption()));
    }

    /* access modifiers changed from: protected */
    public void registerProgressListener() {
        if (this.progressListenerToken == -1) {
            this.progressListenerToken = this.eventEmitter.on("progress", this.progressListener);
        }
    }

    /* access modifiers changed from: protected */
    public void cancelProgressListener() {
        int i = this.progressListenerToken;
        if (i != -1) {
            this.eventEmitter.off("progress", i);
            this.progressListenerToken = -1;
        }
    }

    /* access modifiers changed from: protected */
    public void emitCaptionsAvailable(boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put(AbstractEvent.BOOLEAN, Boolean.valueOf(z));
        this.eventEmitter.emit(EventType.CAPTIONS_AVAILABLE, hashMap);
    }

    /* access modifiers changed from: protected */
    public CharSequence collapseWhitespace(CharSequence charSequence) {
        return StringUtil.replaceAll(charSequence, new String[]{"\n", "\r", "\t", "  "}, new CharSequence[]{" ", " ", " ", " "});
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:9:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.brightcove.player.model.Region resolveRegionForBlock(com.brightcove.player.model.Block r4, com.brightcove.player.model.TTMLDocument r5) {
        /*
            r3 = this;
            com.brightcove.player.model.Block r0 = r5.getBody()
            if (r0 == 0) goto L_0x001f
            com.brightcove.player.model.Block r0 = r5.getBody()
            java.lang.String r0 = r0.getRegion()
            java.util.Map r1 = r5.getRegions()
            boolean r2 = com.brightcove.player.util.StringUtil.isEmpty(r0)
            if (r2 != 0) goto L_0x001f
            java.lang.Object r0 = r1.get(r0)
            com.brightcove.player.model.Region r0 = (com.brightcove.player.model.Region) r0
            goto L_0x0020
        L_0x001f:
            r0 = 0
        L_0x0020:
            if (r0 != 0) goto L_0x0026
            com.brightcove.player.model.Region r0 = r3.resolveRegionForBlock(r4, r5)
        L_0x0026:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.brightcove.player.view.BrightcoveClosedCaptioningView.resolveRegionForBlock(com.brightcove.player.model.Block, com.brightcove.player.model.TTMLDocument):com.brightcove.player.model.Region");
    }

    /* access modifiers changed from: protected */
    public Region resolveRegionForBlock(Block block, CaptionsDocument captionsDocument) {
        String region = block.getRegion();
        Map regions = captionsDocument.getRegions();
        if (!StringUtil.isEmpty(region)) {
            return (Region) regions.get(region);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public CharSequence buildSpannableString(Span span) {
        if (StringUtil.isEmpty(span.getText())) {
            return "";
        }
        SpannableString spannableString = new SpannableString(span.getText());
        int length = span.getText().length();
        if (span.getFontStyle() != null && AnonymousClass11.$SwitchMap$com$brightcove$player$model$StyledElement$FontStyle[span.getFontStyle().ordinal()] == 1) {
            spannableString.setSpan(new StyleSpan(R.style.caption_text_italic), 0, length, 33);
        }
        if (span.getFontWeight() != null && AnonymousClass11.$SwitchMap$com$brightcove$player$model$StyledElement$FontWeight[span.getFontWeight().ordinal()] == 1) {
            spannableString.setSpan(new StyleSpan(R.style.caption_text_bold), 0, length, 33);
        }
        if (span.getTextDecoration() != null && AnonymousClass11.$SwitchMap$com$brightcove$player$model$StyledElement$TextDecoration[span.getTextDecoration().ordinal()] == 1) {
            spannableString.setSpan(new UnderlineSpan(), 0, length, 33);
        }
        if (!StringUtil.isEmpty(span.getColor())) {
            try {
                spannableString.setSpan(new ForegroundColorSpan(parseColor(span.getColor())), 0, length, 33);
            } catch (IllegalArgumentException unused) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Invalid color: ");
                sb.append(span.getColor());
                Log.w(str, sb.toString());
            }
        }
        if (!StringUtil.isEmpty(span.getBackgroundColor())) {
            try {
                spannableString.setSpan(new BackgroundColorSpan(parseColor(span.getBackgroundColor())), 0, length, 33);
            } catch (IllegalArgumentException unused2) {
                String str2 = TAG;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Invalid color: ");
                sb2.append(span.getColor());
                Log.w(str2, sb2.toString());
            }
        }
        if (span.getFontSize() != null) {
            Length fontSize = span.getFontSize();
            if (fontSize != null) {
                spannableString.setSpan(new AbsoluteSizeSpan((int) fontSize.getValue()), 0, length, 33);
            }
        }
        spannableString.setSpan(new RelativeSizeSpan(1.0f), 0, length, 33);
        return spannableString;
    }

    private static int parseColor(String str) {
        if (str.charAt(0) == '#' && str.length() == 4) {
            return Integer.parseInt(str.substring(1), 16) | -16777216;
        }
        return Color.parseColor(str);
    }

    /* access modifiers changed from: private */
    public void doUpdateCaption(int i) {
        if (isEnabled() && getMode() == ClosedCaptioningMode.ON) {
            this.lastProgressTime = i;
            ArrayList<LinearLayout> arrayList = this.currentCaptionBlocks;
            if (arrayList != null && arrayList.size() > 0) {
                for (int i2 = 0; i2 < this.currentCaptionBlocks.size(); i2++) {
                    LinearLayout linearLayout = (LinearLayout) this.currentCaptionBlocks.get(i2);
                    if (!(linearLayout == null || linearLayout.getParent() == null)) {
                        removeView(linearLayout);
                    }
                }
            }
            if (i != -1) {
                this.currentCaptionBlocks = findCaptionsForPosition(i);
                ArrayList<LinearLayout> arrayList2 = this.currentCaptionBlocks;
                if (arrayList2 != null && arrayList2.size() > 0) {
                    for (int i3 = 0; i3 < this.currentCaptionBlocks.size(); i3++) {
                        LinearLayout linearLayout2 = (LinearLayout) this.currentCaptionBlocks.get(i3);
                        if (linearLayout2 != null) {
                            addView(linearLayout2);
                        }
                    }
                }
                requestLayout();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updatePadding() {
        int safeAreaMarginWidth = getSafeAreaMarginWidth();
        int safeAreaMarginHeight = getSafeAreaMarginHeight();
        int i = this.controlsHeight;
        if (i != -1) {
            setPadding(safeAreaMarginWidth, safeAreaMarginHeight, safeAreaMarginWidth, i + safeAreaMarginHeight);
        } else {
            setPadding(safeAreaMarginWidth, safeAreaMarginHeight, safeAreaMarginWidth, safeAreaMarginHeight);
        }
    }

    public void refreshCaptions() {
        int i = this.lastProgressTime;
        if (i != -1) {
            doUpdateCaption(i);
        }
    }

    public void refreshCaptions(int i) {
        if (i != -1) {
            doUpdateCaption(i);
        }
    }

    public void setSafeAreaPercent(float f) {
        this.safeAreaPercent = f;
    }
}
