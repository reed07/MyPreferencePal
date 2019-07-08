package com.google.android.exoplayer2.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.brightcove.player.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerLibraryInfo;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlaybackPreparer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.Player.EventListener.CC;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Period;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.TimeBar.OnScrubListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.RepeatModeUtil;
import com.google.android.exoplayer2.util.Util;
import com.myfitnesspal.shared.constants.Constants.Database.Statements;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Locale;

public class PlayerControlView extends FrameLayout {
    public static final int DEFAULT_FAST_FORWARD_MS = 15000;
    public static final int DEFAULT_REPEAT_TOGGLE_MODES = 0;
    public static final int DEFAULT_REWIND_MS = 5000;
    public static final int DEFAULT_SHOW_TIMEOUT_MS = 5000;
    private static final long MAX_POSITION_FOR_SEEK_TO_PREVIOUS = 3000;
    public static final int MAX_WINDOWS_FOR_MULTI_WINDOW_TIME_BAR = 100;
    private long[] adGroupTimesMs;
    private final ComponentListener componentListener;
    /* access modifiers changed from: private */
    public ControlDispatcher controlDispatcher;
    private final TextView durationView;
    private long[] extraAdGroupTimesMs;
    private boolean[] extraPlayedAdGroups;
    /* access modifiers changed from: private */
    public final View fastForwardButton;
    private int fastForwardMs;
    /* access modifiers changed from: private */
    public final StringBuilder formatBuilder;
    /* access modifiers changed from: private */
    public final Formatter formatter;
    private final Runnable hideAction;
    private long hideAtMs;
    private boolean isAttachedToWindow;
    private boolean multiWindowTimeBar;
    /* access modifiers changed from: private */
    public final View nextButton;
    /* access modifiers changed from: private */
    public final View pauseButton;
    private final Period period;
    /* access modifiers changed from: private */
    public final View playButton;
    /* access modifiers changed from: private */
    @Nullable
    public PlaybackPreparer playbackPreparer;
    private boolean[] playedAdGroups;
    /* access modifiers changed from: private */
    public Player player;
    /* access modifiers changed from: private */
    public final TextView positionView;
    /* access modifiers changed from: private */
    public final View previousButton;
    private final String repeatAllButtonContentDescription;
    private final Drawable repeatAllButtonDrawable;
    private final String repeatOffButtonContentDescription;
    private final Drawable repeatOffButtonDrawable;
    private final String repeatOneButtonContentDescription;
    private final Drawable repeatOneButtonDrawable;
    /* access modifiers changed from: private */
    public final ImageView repeatToggleButton;
    /* access modifiers changed from: private */
    public int repeatToggleModes;
    /* access modifiers changed from: private */
    public final View rewindButton;
    private int rewindMs;
    /* access modifiers changed from: private */
    public boolean scrubbing;
    private boolean showMultiWindowTimeBar;
    private boolean showShuffleButton;
    private int showTimeoutMs;
    /* access modifiers changed from: private */
    public final View shuffleButton;
    private final TimeBar timeBar;
    private final Runnable updateProgressAction;
    private VisibilityListener visibilityListener;
    private final Window window;

    private final class ComponentListener implements OnClickListener, EventListener, OnScrubListener {
        public /* synthetic */ void onLoadingChanged(boolean z) {
            CC.$default$onLoadingChanged(this, z);
        }

        public /* synthetic */ void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            CC.$default$onPlaybackParametersChanged(this, playbackParameters);
        }

        public /* synthetic */ void onPlayerError(ExoPlaybackException exoPlaybackException) {
            CC.$default$onPlayerError(this, exoPlaybackException);
        }

        public /* synthetic */ void onSeekProcessed() {
            CC.$default$onSeekProcessed(this);
        }

        public /* synthetic */ void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            CC.$default$onTracksChanged(this, trackGroupArray, trackSelectionArray);
        }

        private ComponentListener() {
        }

        public void onScrubStart(TimeBar timeBar, long j) {
            PlayerControlView.this.scrubbing = true;
        }

        public void onScrubMove(TimeBar timeBar, long j) {
            if (PlayerControlView.this.positionView != null) {
                PlayerControlView.this.positionView.setText(Util.getStringForTime(PlayerControlView.this.formatBuilder, PlayerControlView.this.formatter, j));
            }
        }

        public void onScrubStop(TimeBar timeBar, long j, boolean z) {
            PlayerControlView.this.scrubbing = false;
            if (!z && PlayerControlView.this.player != null) {
                PlayerControlView.this.seekToTimeBarPosition(j);
            }
        }

        public void onPlayerStateChanged(boolean z, int i) {
            PlayerControlView.this.updatePlayPauseButton();
            PlayerControlView.this.updateProgress();
        }

        public void onRepeatModeChanged(int i) {
            PlayerControlView.this.updateRepeatModeButton();
            PlayerControlView.this.updateNavigation();
        }

        public void onShuffleModeEnabledChanged(boolean z) {
            PlayerControlView.this.updateShuffleButton();
            PlayerControlView.this.updateNavigation();
        }

        public void onPositionDiscontinuity(int i) {
            PlayerControlView.this.updateNavigation();
            PlayerControlView.this.updateProgress();
        }

        public void onTimelineChanged(Timeline timeline, @Nullable Object obj, int i) {
            PlayerControlView.this.updateNavigation();
            PlayerControlView.this.updateTimeBarMode();
            PlayerControlView.this.updateProgress();
        }

        public void onClick(View view) {
            if (PlayerControlView.this.player == null) {
                return;
            }
            if (PlayerControlView.this.nextButton == view) {
                PlayerControlView.this.next();
            } else if (PlayerControlView.this.previousButton == view) {
                PlayerControlView.this.previous();
            } else if (PlayerControlView.this.fastForwardButton == view) {
                PlayerControlView.this.fastForward();
            } else if (PlayerControlView.this.rewindButton == view) {
                PlayerControlView.this.rewind();
            } else if (PlayerControlView.this.playButton == view) {
                if (PlayerControlView.this.player.getPlaybackState() == 1) {
                    if (PlayerControlView.this.playbackPreparer != null) {
                        PlayerControlView.this.playbackPreparer.preparePlayback();
                    }
                } else if (PlayerControlView.this.player.getPlaybackState() == 4) {
                    PlayerControlView.this.controlDispatcher.dispatchSeekTo(PlayerControlView.this.player, PlayerControlView.this.player.getCurrentWindowIndex(), -9223372036854775807L);
                }
                PlayerControlView.this.controlDispatcher.dispatchSetPlayWhenReady(PlayerControlView.this.player, true);
            } else if (PlayerControlView.this.pauseButton == view) {
                PlayerControlView.this.controlDispatcher.dispatchSetPlayWhenReady(PlayerControlView.this.player, false);
            } else if (PlayerControlView.this.repeatToggleButton == view) {
                PlayerControlView.this.controlDispatcher.dispatchSetRepeatMode(PlayerControlView.this.player, RepeatModeUtil.getNextRepeatMode(PlayerControlView.this.player.getRepeatMode(), PlayerControlView.this.repeatToggleModes));
            } else if (PlayerControlView.this.shuffleButton == view) {
                PlayerControlView.this.controlDispatcher.dispatchSetShuffleModeEnabled(PlayerControlView.this.player, true ^ PlayerControlView.this.player.getShuffleModeEnabled());
            }
        }
    }

    public interface VisibilityListener {
        void onVisibilityChange(int i);
    }

    @SuppressLint({"InlinedApi"})
    private static boolean isHandledMediaKey(int i) {
        return i == 90 || i == 89 || i == 85 || i == 126 || i == 127 || i == 87 || i == 88;
    }

    static {
        ExoPlayerLibraryInfo.registerModule("goog.exo.ui");
    }

    public PlayerControlView(Context context) {
        this(context, null);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, attributeSet);
    }

    public PlayerControlView(Context context, AttributeSet attributeSet, int i, AttributeSet attributeSet2) {
        super(context, attributeSet, i);
        int i2 = R.layout.exo_player_control_view;
        this.rewindMs = 5000;
        this.fastForwardMs = 15000;
        this.showTimeoutMs = 5000;
        this.repeatToggleModes = 0;
        this.hideAtMs = -9223372036854775807L;
        this.showShuffleButton = false;
        if (attributeSet2 != null) {
            TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet2, R.styleable.PlayerControlView, 0, 0);
            try {
                this.rewindMs = obtainStyledAttributes.getInt(R.styleable.PlayerControlView_rewind_increment, this.rewindMs);
                this.fastForwardMs = obtainStyledAttributes.getInt(R.styleable.PlayerControlView_fastforward_increment, this.fastForwardMs);
                this.showTimeoutMs = obtainStyledAttributes.getInt(R.styleable.PlayerControlView_show_timeout, this.showTimeoutMs);
                i2 = obtainStyledAttributes.getResourceId(R.styleable.PlayerControlView_controller_layout_id, i2);
                this.repeatToggleModes = getRepeatToggleModes(obtainStyledAttributes, this.repeatToggleModes);
                this.showShuffleButton = obtainStyledAttributes.getBoolean(R.styleable.PlayerControlView_show_shuffle_button, this.showShuffleButton);
            } finally {
                obtainStyledAttributes.recycle();
            }
        }
        this.period = new Period();
        this.window = new Window();
        this.formatBuilder = new StringBuilder();
        this.formatter = new Formatter(this.formatBuilder, Locale.getDefault());
        this.adGroupTimesMs = new long[0];
        this.playedAdGroups = new boolean[0];
        this.extraAdGroupTimesMs = new long[0];
        this.extraPlayedAdGroups = new boolean[0];
        this.componentListener = new ComponentListener();
        this.controlDispatcher = new DefaultControlDispatcher();
        this.updateProgressAction = new Runnable() {
            public final void run() {
                PlayerControlView.this.updateProgress();
            }
        };
        this.hideAction = new Runnable() {
            public final void run() {
                PlayerControlView.this.hide();
            }
        };
        LayoutInflater.from(context).inflate(i2, this);
        setDescendantFocusability(C.DASH_ROLE_SUB_FLAG);
        this.durationView = (TextView) findViewById(R.id.exo_duration);
        this.positionView = (TextView) findViewById(R.id.exo_position);
        this.timeBar = (TimeBar) findViewById(R.id.exo_progress);
        TimeBar timeBar2 = this.timeBar;
        if (timeBar2 != null) {
            timeBar2.addListener(this.componentListener);
        }
        this.playButton = findViewById(R.id.exo_play);
        View view = this.playButton;
        if (view != null) {
            view.setOnClickListener(this.componentListener);
        }
        this.pauseButton = findViewById(R.id.exo_pause);
        View view2 = this.pauseButton;
        if (view2 != null) {
            view2.setOnClickListener(this.componentListener);
        }
        this.previousButton = findViewById(R.id.exo_prev);
        View view3 = this.previousButton;
        if (view3 != null) {
            view3.setOnClickListener(this.componentListener);
        }
        this.nextButton = findViewById(R.id.exo_next);
        View view4 = this.nextButton;
        if (view4 != null) {
            view4.setOnClickListener(this.componentListener);
        }
        this.rewindButton = findViewById(R.id.exo_rew);
        View view5 = this.rewindButton;
        if (view5 != null) {
            view5.setOnClickListener(this.componentListener);
        }
        this.fastForwardButton = findViewById(R.id.exo_ffwd);
        View view6 = this.fastForwardButton;
        if (view6 != null) {
            view6.setOnClickListener(this.componentListener);
        }
        this.repeatToggleButton = (ImageView) findViewById(R.id.exo_repeat_toggle);
        ImageView imageView = this.repeatToggleButton;
        if (imageView != null) {
            imageView.setOnClickListener(this.componentListener);
        }
        this.shuffleButton = findViewById(R.id.exo_shuffle);
        View view7 = this.shuffleButton;
        if (view7 != null) {
            view7.setOnClickListener(this.componentListener);
        }
        Resources resources = context.getResources();
        this.repeatOffButtonDrawable = resources.getDrawable(R.drawable.exo_controls_repeat_off);
        this.repeatOneButtonDrawable = resources.getDrawable(R.drawable.exo_controls_repeat_one);
        this.repeatAllButtonDrawable = resources.getDrawable(R.drawable.exo_controls_repeat_all);
        this.repeatOffButtonContentDescription = resources.getString(R.string.exo_controls_repeat_off_description);
        this.repeatOneButtonContentDescription = resources.getString(R.string.exo_controls_repeat_one_description);
        this.repeatAllButtonContentDescription = resources.getString(R.string.exo_controls_repeat_all_description);
    }

    private static int getRepeatToggleModes(TypedArray typedArray, int i) {
        return typedArray.getInt(R.styleable.PlayerControlView_repeat_toggle_modes, i);
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(@Nullable Player player2) {
        boolean z = true;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (!(player2 == null || player2.getApplicationLooper() == Looper.getMainLooper())) {
            z = false;
        }
        Assertions.checkArgument(z);
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.removeListener(this.componentListener);
            }
            this.player = player2;
            if (player2 != null) {
                player2.addListener(this.componentListener);
            }
            updateAll();
        }
    }

    public void setShowMultiWindowTimeBar(boolean z) {
        this.showMultiWindowTimeBar = z;
        updateTimeBarMode();
    }

    public void setExtraAdGroupMarkers(@Nullable long[] jArr, @Nullable boolean[] zArr) {
        boolean z = false;
        if (jArr == null) {
            this.extraAdGroupTimesMs = new long[0];
            this.extraPlayedAdGroups = new boolean[0];
        } else {
            if (jArr.length == zArr.length) {
                z = true;
            }
            Assertions.checkArgument(z);
            this.extraAdGroupTimesMs = jArr;
            this.extraPlayedAdGroups = zArr;
        }
        updateProgress();
    }

    public void setVisibilityListener(VisibilityListener visibilityListener2) {
        this.visibilityListener = visibilityListener2;
    }

    public void setPlaybackPreparer(@Nullable PlaybackPreparer playbackPreparer2) {
        this.playbackPreparer = playbackPreparer2;
    }

    public void setControlDispatcher(@Nullable ControlDispatcher controlDispatcher2) {
        if (controlDispatcher2 == null) {
            controlDispatcher2 = new DefaultControlDispatcher();
        }
        this.controlDispatcher = controlDispatcher2;
    }

    public void setRewindIncrementMs(int i) {
        this.rewindMs = i;
        updateNavigation();
    }

    public void setFastForwardIncrementMs(int i) {
        this.fastForwardMs = i;
        updateNavigation();
    }

    public int getShowTimeoutMs() {
        return this.showTimeoutMs;
    }

    public void setShowTimeoutMs(int i) {
        this.showTimeoutMs = i;
        if (isVisible()) {
            hideAfterTimeout();
        }
    }

    public int getRepeatToggleModes() {
        return this.repeatToggleModes;
    }

    public void setRepeatToggleModes(int i) {
        this.repeatToggleModes = i;
        Player player2 = this.player;
        if (player2 != null) {
            int repeatMode = player2.getRepeatMode();
            if (i == 0 && repeatMode != 0) {
                this.controlDispatcher.dispatchSetRepeatMode(this.player, 0);
            } else if (i == 1 && repeatMode == 2) {
                this.controlDispatcher.dispatchSetRepeatMode(this.player, 1);
            } else if (i == 2 && repeatMode == 1) {
                this.controlDispatcher.dispatchSetRepeatMode(this.player, 2);
            }
        }
        updateRepeatModeButton();
    }

    public boolean getShowShuffleButton() {
        return this.showShuffleButton;
    }

    public void setShowShuffleButton(boolean z) {
        this.showShuffleButton = z;
        updateShuffleButton();
    }

    public void show() {
        if (!isVisible()) {
            setVisibility(0);
            VisibilityListener visibilityListener2 = this.visibilityListener;
            if (visibilityListener2 != null) {
                visibilityListener2.onVisibilityChange(getVisibility());
            }
            updateAll();
            requestPlayPauseFocus();
        }
        hideAfterTimeout();
    }

    public void hide() {
        if (isVisible()) {
            setVisibility(8);
            VisibilityListener visibilityListener2 = this.visibilityListener;
            if (visibilityListener2 != null) {
                visibilityListener2.onVisibilityChange(getVisibility());
            }
            removeCallbacks(this.updateProgressAction);
            removeCallbacks(this.hideAction);
            this.hideAtMs = -9223372036854775807L;
        }
    }

    public boolean isVisible() {
        return getVisibility() == 0;
    }

    private void hideAfterTimeout() {
        removeCallbacks(this.hideAction);
        if (this.showTimeoutMs > 0) {
            long uptimeMillis = SystemClock.uptimeMillis();
            int i = this.showTimeoutMs;
            this.hideAtMs = uptimeMillis + ((long) i);
            if (this.isAttachedToWindow) {
                postDelayed(this.hideAction, (long) i);
                return;
            }
            return;
        }
        this.hideAtMs = -9223372036854775807L;
    }

    private void updateAll() {
        updatePlayPauseButton();
        updateNavigation();
        updateRepeatModeButton();
        updateShuffleButton();
        updateProgress();
    }

    /* access modifiers changed from: private */
    public void updatePlayPauseButton() {
        boolean z;
        if (isVisible() && this.isAttachedToWindow) {
            boolean isPlaying = isPlaying();
            View view = this.playButton;
            int i = 8;
            boolean z2 = true;
            if (view != null) {
                z = (isPlaying && view.isFocused()) | false;
                this.playButton.setVisibility(isPlaying ? 8 : 0);
            } else {
                z = false;
            }
            View view2 = this.pauseButton;
            if (view2 != null) {
                if (isPlaying || !view2.isFocused()) {
                    z2 = false;
                }
                z |= z2;
                View view3 = this.pauseButton;
                if (isPlaying) {
                    i = 0;
                }
                view3.setVisibility(i);
            }
            if (z) {
                requestPlayPauseFocus();
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateNavigation() {
        boolean z;
        boolean z2;
        boolean z3;
        if (isVisible() && this.isAttachedToWindow) {
            Player player2 = this.player;
            Timeline currentTimeline = player2 != null ? player2.getCurrentTimeline() : null;
            boolean z4 = true;
            if (!(currentTimeline != null && !currentTimeline.isEmpty()) || this.player.isPlayingAd()) {
                z3 = false;
                z2 = false;
                z = false;
            } else {
                currentTimeline.getWindow(this.player.getCurrentWindowIndex(), this.window);
                z3 = this.window.isSeekable;
                z2 = z3 || !this.window.isDynamic || this.player.hasPrevious();
                z = this.window.isDynamic || this.player.hasNext();
            }
            setButtonEnabled(z2, this.previousButton);
            setButtonEnabled(z, this.nextButton);
            setButtonEnabled(this.fastForwardMs > 0 && z3, this.fastForwardButton);
            if (this.rewindMs <= 0 || !z3) {
                z4 = false;
            }
            setButtonEnabled(z4, this.rewindButton);
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setEnabled(z3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateRepeatModeButton() {
        if (isVisible() && this.isAttachedToWindow) {
            ImageView imageView = this.repeatToggleButton;
            if (imageView != null) {
                if (this.repeatToggleModes == 0) {
                    imageView.setVisibility(8);
                } else if (this.player == null) {
                    setButtonEnabled(false, imageView);
                } else {
                    setButtonEnabled(true, imageView);
                    switch (this.player.getRepeatMode()) {
                        case 0:
                            this.repeatToggleButton.setImageDrawable(this.repeatOffButtonDrawable);
                            this.repeatToggleButton.setContentDescription(this.repeatOffButtonContentDescription);
                            break;
                        case 1:
                            this.repeatToggleButton.setImageDrawable(this.repeatOneButtonDrawable);
                            this.repeatToggleButton.setContentDescription(this.repeatOneButtonContentDescription);
                            break;
                        case 2:
                            this.repeatToggleButton.setImageDrawable(this.repeatAllButtonDrawable);
                            this.repeatToggleButton.setContentDescription(this.repeatAllButtonContentDescription);
                            break;
                    }
                    this.repeatToggleButton.setVisibility(0);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateShuffleButton() {
        if (isVisible() && this.isAttachedToWindow) {
            View view = this.shuffleButton;
            if (view != null) {
                if (!this.showShuffleButton) {
                    view.setVisibility(8);
                } else {
                    Player player2 = this.player;
                    if (player2 == null) {
                        setButtonEnabled(false, view);
                    } else {
                        view.setAlpha(player2.getShuffleModeEnabled() ? 1.0f : 0.3f);
                        this.shuffleButton.setEnabled(true);
                        this.shuffleButton.setVisibility(0);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateTimeBarMode() {
        Player player2 = this.player;
        if (player2 != null) {
            this.multiWindowTimeBar = this.showMultiWindowTimeBar && canShowMultiWindowTimeBar(player2.getCurrentTimeline(), this.window);
        }
    }

    /* access modifiers changed from: private */
    public void updateProgress() {
        long j;
        long j2;
        long j3;
        long j4;
        int i;
        long j5;
        int i2;
        long j6;
        if (isVisible() && this.isAttachedToWindow) {
            Player player2 = this.player;
            boolean z = true;
            if (player2 != null) {
                Timeline currentTimeline = player2.getCurrentTimeline();
                if (!currentTimeline.isEmpty()) {
                    int currentWindowIndex = this.player.getCurrentWindowIndex();
                    int i3 = this.multiWindowTimeBar ? 0 : currentWindowIndex;
                    int windowCount = this.multiWindowTimeBar ? currentTimeline.getWindowCount() - 1 : currentWindowIndex;
                    j5 = 0;
                    i = 0;
                    j4 = 0;
                    while (true) {
                        if (i3 > windowCount) {
                            break;
                        }
                        if (i3 == currentWindowIndex) {
                            j4 = com.google.android.exoplayer2.C.usToMs(j5);
                        }
                        currentTimeline.getWindow(i3, this.window);
                        if (this.window.durationUs == -9223372036854775807L) {
                            Assertions.checkState(this.multiWindowTimeBar ^ z);
                            break;
                        }
                        int i4 = this.window.firstPeriodIndex;
                        while (i4 <= this.window.lastPeriodIndex) {
                            currentTimeline.getPeriod(i4, this.period);
                            int adGroupCount = this.period.getAdGroupCount();
                            int i5 = i;
                            int i6 = 0;
                            while (i6 < adGroupCount) {
                                long adGroupTimeUs = this.period.getAdGroupTimeUs(i6);
                                if (adGroupTimeUs != Long.MIN_VALUE) {
                                    j6 = adGroupTimeUs;
                                } else if (this.period.durationUs == -9223372036854775807L) {
                                    i2 = currentWindowIndex;
                                    i6++;
                                    currentWindowIndex = i2;
                                } else {
                                    j6 = this.period.durationUs;
                                }
                                long positionInWindowUs = j6 + this.period.getPositionInWindowUs();
                                if (positionInWindowUs >= 0) {
                                    i2 = currentWindowIndex;
                                    if (positionInWindowUs <= this.window.durationUs) {
                                        long[] jArr = this.adGroupTimesMs;
                                        if (i5 == jArr.length) {
                                            int length = jArr.length == 0 ? 1 : jArr.length * 2;
                                            this.adGroupTimesMs = Arrays.copyOf(this.adGroupTimesMs, length);
                                            this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, length);
                                        }
                                        this.adGroupTimesMs[i5] = com.google.android.exoplayer2.C.usToMs(positionInWindowUs + j5);
                                        this.playedAdGroups[i5] = this.period.hasPlayedAdGroup(i6);
                                        i5++;
                                    }
                                } else {
                                    i2 = currentWindowIndex;
                                }
                                i6++;
                                currentWindowIndex = i2;
                            }
                            int i7 = currentWindowIndex;
                            i4++;
                            i = i5;
                        }
                        int i8 = currentWindowIndex;
                        j5 += this.window.durationUs;
                        i3++;
                        z = true;
                    }
                } else {
                    j5 = 0;
                    j4 = 0;
                    i = 0;
                }
                j3 = com.google.android.exoplayer2.C.usToMs(j5);
                j2 = this.player.getContentPosition() + j4;
                j = this.player.getContentBufferedPosition() + j4;
                if (this.timeBar != null) {
                    int length2 = this.extraAdGroupTimesMs.length;
                    int i9 = i + length2;
                    long[] jArr2 = this.adGroupTimesMs;
                    if (i9 > jArr2.length) {
                        this.adGroupTimesMs = Arrays.copyOf(jArr2, i9);
                        this.playedAdGroups = Arrays.copyOf(this.playedAdGroups, i9);
                    }
                    System.arraycopy(this.extraAdGroupTimesMs, 0, this.adGroupTimesMs, i, length2);
                    System.arraycopy(this.extraPlayedAdGroups, 0, this.playedAdGroups, i, length2);
                    this.timeBar.setAdGroupTimesMs(this.adGroupTimesMs, this.playedAdGroups, i9);
                }
            } else {
                j3 = 0;
                j2 = 0;
                j = 0;
            }
            TextView textView = this.durationView;
            if (textView != null) {
                textView.setText(Util.getStringForTime(this.formatBuilder, this.formatter, j3));
            }
            TextView textView2 = this.positionView;
            if (textView2 != null && !this.scrubbing) {
                textView2.setText(Util.getStringForTime(this.formatBuilder, this.formatter, j2));
            }
            TimeBar timeBar2 = this.timeBar;
            if (timeBar2 != null) {
                timeBar2.setPosition(j2);
                this.timeBar.setBufferedPosition(j);
                this.timeBar.setDuration(j3);
            }
            removeCallbacks(this.updateProgressAction);
            Player player3 = this.player;
            int playbackState = player3 == null ? 1 : player3.getPlaybackState();
            if (!(playbackState == 1 || playbackState == 4)) {
                long j7 = 1000;
                if (this.player.getPlayWhenReady() && playbackState == 3) {
                    float f = this.player.getPlaybackParameters().speed;
                    if (f > 0.1f) {
                        if (f <= 5.0f) {
                            long max = (long) (1000 / Math.max(1, Math.round(1.0f / f)));
                            long j8 = max - (j2 % max);
                            if (j8 < max / 5) {
                                j8 += max;
                            }
                            if (f != 1.0f) {
                                j8 = (long) (((float) j8) / f);
                            }
                            j7 = j8;
                        } else {
                            j7 = 200;
                        }
                    }
                }
                postDelayed(this.updateProgressAction, j7);
            }
        }
    }

    private void requestPlayPauseFocus() {
        boolean isPlaying = isPlaying();
        if (!isPlaying) {
            View view = this.playButton;
            if (view != null) {
                view.requestFocus();
                return;
            }
        }
        if (isPlaying) {
            View view2 = this.pauseButton;
            if (view2 != null) {
                view2.requestFocus();
            }
        }
    }

    private void setButtonEnabled(boolean z, View view) {
        if (view != null) {
            view.setEnabled(z);
            view.setAlpha(z ? 1.0f : 0.3f);
            view.setVisibility(0);
        }
    }

    /* access modifiers changed from: private */
    public void previous() {
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (!currentTimeline.isEmpty() && !this.player.isPlayingAd()) {
            currentTimeline.getWindow(this.player.getCurrentWindowIndex(), this.window);
            int previousWindowIndex = this.player.getPreviousWindowIndex();
            if (previousWindowIndex == -1 || (this.player.getCurrentPosition() > MAX_POSITION_FOR_SEEK_TO_PREVIOUS && (!this.window.isDynamic || this.window.isSeekable))) {
                seekTo(0);
            } else {
                seekTo(previousWindowIndex, -9223372036854775807L);
            }
        }
    }

    /* access modifiers changed from: private */
    public void next() {
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (!currentTimeline.isEmpty() && !this.player.isPlayingAd()) {
            int currentWindowIndex = this.player.getCurrentWindowIndex();
            int nextWindowIndex = this.player.getNextWindowIndex();
            if (nextWindowIndex != -1) {
                seekTo(nextWindowIndex, -9223372036854775807L);
            } else if (currentTimeline.getWindow(currentWindowIndex, this.window).isDynamic) {
                seekTo(currentWindowIndex, -9223372036854775807L);
            }
        }
    }

    /* access modifiers changed from: private */
    public void rewind() {
        if (this.rewindMs > 0) {
            seekTo(Math.max(this.player.getCurrentPosition() - ((long) this.rewindMs), 0));
        }
    }

    /* access modifiers changed from: private */
    public void fastForward() {
        if (this.fastForwardMs > 0) {
            long duration = this.player.getDuration();
            long currentPosition = this.player.getCurrentPosition() + ((long) this.fastForwardMs);
            if (duration != -9223372036854775807L) {
                currentPosition = Math.min(currentPosition, duration);
            }
            seekTo(currentPosition);
        }
    }

    private void seekTo(long j) {
        seekTo(this.player.getCurrentWindowIndex(), j);
    }

    private void seekTo(int i, long j) {
        if (!this.controlDispatcher.dispatchSeekTo(this.player, i, j)) {
            updateProgress();
        }
    }

    /* access modifiers changed from: private */
    public void seekToTimeBarPosition(long j) {
        int i;
        Timeline currentTimeline = this.player.getCurrentTimeline();
        if (this.multiWindowTimeBar && !currentTimeline.isEmpty()) {
            int windowCount = currentTimeline.getWindowCount();
            i = 0;
            while (true) {
                long durationMs = currentTimeline.getWindow(i, this.window).getDurationMs();
                if (j < durationMs) {
                    break;
                } else if (i == windowCount - 1) {
                    j = durationMs;
                    break;
                } else {
                    j -= durationMs;
                    i++;
                }
            }
        } else {
            i = this.player.getCurrentWindowIndex();
        }
        seekTo(i, j);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachedToWindow = true;
        long j = this.hideAtMs;
        if (j != -9223372036854775807L) {
            long uptimeMillis = j - SystemClock.uptimeMillis();
            if (uptimeMillis <= 0) {
                hide();
            } else {
                postDelayed(this.hideAction, uptimeMillis);
            }
        } else if (isVisible()) {
            hideAfterTimeout();
        }
        updateAll();
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        removeCallbacks(this.updateProgressAction);
        removeCallbacks(this.hideAction);
    }

    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            removeCallbacks(this.hideAction);
        } else if (motionEvent.getAction() == 1) {
            hideAfterTimeout();
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return dispatchMediaKeyEvent(keyEvent) || super.dispatchKeyEvent(keyEvent);
    }

    public boolean dispatchMediaKeyEvent(KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (this.player == null || !isHandledMediaKey(keyCode)) {
            return false;
        }
        if (keyEvent.getAction() == 0) {
            if (keyCode != 90) {
                if (keyCode != 89) {
                    if (keyEvent.getRepeatCount() == 0) {
                        switch (keyCode) {
                            case 85:
                                ControlDispatcher controlDispatcher2 = this.controlDispatcher;
                                Player player2 = this.player;
                                controlDispatcher2.dispatchSetPlayWhenReady(player2, !player2.getPlayWhenReady());
                                break;
                            case 87:
                                next();
                                break;
                            case 88:
                                previous();
                                break;
                            case 126:
                                this.controlDispatcher.dispatchSetPlayWhenReady(this.player, true);
                                break;
                            case Statements.GetOwnedFoodIdsDateDescending /*127*/:
                                this.controlDispatcher.dispatchSetPlayWhenReady(this.player, false);
                                break;
                        }
                    }
                } else {
                    rewind();
                }
            } else {
                fastForward();
            }
        }
        return true;
    }

    private boolean isPlaying() {
        Player player2 = this.player;
        if (player2 == null || player2.getPlaybackState() == 4 || this.player.getPlaybackState() == 1 || !this.player.getPlayWhenReady()) {
            return false;
        }
        return true;
    }

    private static boolean canShowMultiWindowTimeBar(Timeline timeline, Window window2) {
        if (timeline.getWindowCount() > 100) {
            return false;
        }
        int windowCount = timeline.getWindowCount();
        for (int i = 0; i < windowCount; i++) {
            if (timeline.getWindow(i, window2).durationUs == -9223372036854775807L) {
                return false;
            }
        }
        return true;
    }
}
