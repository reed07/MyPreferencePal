package com.google.android.exoplayer2.ui;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat.Action;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.app.NotificationCompat.MediaStyle;
import android.support.v4.media.session.MediaSessionCompat.Token;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ControlDispatcher;
import com.google.android.exoplayer2.DefaultControlDispatcher;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.Player.EventListener;
import com.google.android.exoplayer2.Player.EventListener.CC;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Timeline.Window;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerNotificationManager.BitmapCallback;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.NotificationUtil;
import com.google.android.exoplayer2.util.Util;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public class PlayerNotificationManager {
    public static final String ACTION_FAST_FORWARD = "com.google.android.exoplayer.ffwd";
    public static final String ACTION_NEXT = "com.google.android.exoplayer.next";
    public static final String ACTION_PAUSE = "com.google.android.exoplayer.pause";
    public static final String ACTION_PLAY = "com.google.android.exoplayer.play";
    public static final String ACTION_PREVIOUS = "com.google.android.exoplayer.prev";
    public static final String ACTION_REWIND = "com.google.android.exoplayer.rewind";
    public static final String ACTION_STOP = "com.google.android.exoplayer.stop";
    public static final int DEFAULT_FAST_FORWARD_MS = 15000;
    public static final int DEFAULT_REWIND_MS = 5000;
    public static final String EXTRA_INSTANCE_ID = "INSTANCE_ID";
    private static final long MAX_POSITION_FOR_SEEK_TO_PREVIOUS = 3000;
    private static int instanceIdCounter;
    private int badgeIconType;
    private final String channelId;
    private int color;
    private boolean colorized;
    private final Context context;
    /* access modifiers changed from: private */
    public ControlDispatcher controlDispatcher;
    /* access modifiers changed from: private */
    public int currentNotificationTag;
    /* access modifiers changed from: private */
    @Nullable
    public final CustomActionReceiver customActionReceiver;
    /* access modifiers changed from: private */
    public final Map<String, Action> customActions;
    private int defaults;
    /* access modifiers changed from: private */
    public long fastForwardMs;
    /* access modifiers changed from: private */
    public final int instanceId;
    private final IntentFilter intentFilter;
    /* access modifiers changed from: private */
    public boolean isNotificationStarted;
    /* access modifiers changed from: private */
    public int lastPlaybackState;
    /* access modifiers changed from: private */
    public final Handler mainHandler;
    private final MediaDescriptionAdapter mediaDescriptionAdapter;
    @Nullable
    private Token mediaSessionToken;
    private final NotificationBroadcastReceiver notificationBroadcastReceiver;
    private final int notificationId;
    @Nullable
    private NotificationListener notificationListener;
    private final NotificationManagerCompat notificationManager;
    private boolean ongoing;
    private final Map<String, Action> playbackActions;
    /* access modifiers changed from: private */
    @Nullable
    public Player player;
    private final EventListener playerListener;
    private int priority;
    /* access modifiers changed from: private */
    public long rewindMs;
    @DrawableRes
    private int smallIconResourceId;
    @Nullable
    private String stopAction;
    @Nullable
    private PendingIntent stopPendingIntent;
    private boolean useChronometer;
    private boolean useNavigationActions;
    private boolean usePlayPauseActions;
    private int visibility;
    /* access modifiers changed from: private */
    public boolean wasPlayWhenReady;

    public final class BitmapCallback {
        private final int notificationTag;

        private BitmapCallback(int i) {
            this.notificationTag = i;
        }

        public void onBitmap(Bitmap bitmap) {
            if (bitmap != null) {
                PlayerNotificationManager.this.mainHandler.post(new Runnable(bitmap) {
                    private final /* synthetic */ Bitmap f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        BitmapCallback.lambda$onBitmap$0(BitmapCallback.this, this.f$1);
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$onBitmap$0(BitmapCallback bitmapCallback, Bitmap bitmap) {
            if (PlayerNotificationManager.this.player != null && bitmapCallback.notificationTag == PlayerNotificationManager.this.currentNotificationTag && PlayerNotificationManager.this.isNotificationStarted) {
                PlayerNotificationManager.this.updateNotification(bitmap);
            }
        }
    }

    public interface CustomActionReceiver {
        Map<String, Action> createCustomActions(Context context, int i);

        List<String> getCustomActions(Player player);

        void onCustomAction(Player player, String str, Intent intent);
    }

    public interface MediaDescriptionAdapter {

        /* renamed from: com.google.android.exoplayer2.ui.PlayerNotificationManager$MediaDescriptionAdapter$-CC reason: invalid class name */
        public final /* synthetic */ class CC {
            @Nullable
            public static String $default$getCurrentSubText(MediaDescriptionAdapter mediaDescriptionAdapter, Player player) {
                return null;
            }
        }

        @Nullable
        PendingIntent createCurrentContentIntent(Player player);

        @Nullable
        String getCurrentContentText(Player player);

        String getCurrentContentTitle(Player player);

        @Nullable
        Bitmap getCurrentLargeIcon(Player player, BitmapCallback bitmapCallback);

        @Nullable
        String getCurrentSubText(Player player);
    }

    private class NotificationBroadcastReceiver extends BroadcastReceiver {
        private final Window window = new Window();

        public NotificationBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Player access$100 = PlayerNotificationManager.this.player;
            if (access$100 != null && PlayerNotificationManager.this.isNotificationStarted && intent.getIntExtra(PlayerNotificationManager.EXTRA_INSTANCE_ID, PlayerNotificationManager.this.instanceId) == PlayerNotificationManager.this.instanceId) {
                String action = intent.getAction();
                if (PlayerNotificationManager.ACTION_PLAY.equals(action) || PlayerNotificationManager.ACTION_PAUSE.equals(action)) {
                    PlayerNotificationManager.this.controlDispatcher.dispatchSetPlayWhenReady(access$100, PlayerNotificationManager.ACTION_PLAY.equals(action));
                } else if (PlayerNotificationManager.ACTION_FAST_FORWARD.equals(action) || PlayerNotificationManager.ACTION_REWIND.equals(action)) {
                    PlayerNotificationManager.this.controlDispatcher.dispatchSeekTo(access$100, access$100.getCurrentWindowIndex(), access$100.getCurrentPosition() + (PlayerNotificationManager.ACTION_FAST_FORWARD.equals(action) ? PlayerNotificationManager.this.fastForwardMs : -PlayerNotificationManager.this.rewindMs));
                } else if (PlayerNotificationManager.ACTION_NEXT.equals(action)) {
                    int nextWindowIndex = access$100.getNextWindowIndex();
                    if (nextWindowIndex != -1) {
                        PlayerNotificationManager.this.controlDispatcher.dispatchSeekTo(access$100, nextWindowIndex, -9223372036854775807L);
                    }
                } else if (PlayerNotificationManager.ACTION_PREVIOUS.equals(action)) {
                    access$100.getCurrentTimeline().getWindow(access$100.getCurrentWindowIndex(), this.window);
                    int previousWindowIndex = access$100.getPreviousWindowIndex();
                    if (previousWindowIndex == -1 || (access$100.getCurrentPosition() > PlayerNotificationManager.MAX_POSITION_FOR_SEEK_TO_PREVIOUS && (!this.window.isDynamic || this.window.isSeekable))) {
                        PlayerNotificationManager.this.controlDispatcher.dispatchSeekTo(access$100, access$100.getCurrentWindowIndex(), -9223372036854775807L);
                    } else {
                        PlayerNotificationManager.this.controlDispatcher.dispatchSeekTo(access$100, previousWindowIndex, -9223372036854775807L);
                    }
                } else if (PlayerNotificationManager.ACTION_STOP.equals(action)) {
                    PlayerNotificationManager.this.controlDispatcher.dispatchStop(access$100, true);
                    PlayerNotificationManager.this.stopNotification();
                } else if (PlayerNotificationManager.this.customActionReceiver != null && PlayerNotificationManager.this.customActions.containsKey(action)) {
                    PlayerNotificationManager.this.customActionReceiver.onCustomAction(access$100, action, intent);
                }
            }
        }
    }

    public interface NotificationListener {
        void onNotificationCancelled(int i);

        void onNotificationStarted(int i, Notification notification);
    }

    private class PlayerListener implements EventListener {
        public /* synthetic */ void onLoadingChanged(boolean z) {
            CC.$default$onLoadingChanged(this, z);
        }

        public /* synthetic */ void onPlayerError(ExoPlaybackException exoPlaybackException) {
            CC.$default$onPlayerError(this, exoPlaybackException);
        }

        public /* synthetic */ void onSeekProcessed() {
            CC.$default$onSeekProcessed(this);
        }

        public /* synthetic */ void onShuffleModeEnabledChanged(boolean z) {
            CC.$default$onShuffleModeEnabledChanged(this, z);
        }

        public /* synthetic */ void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
            CC.$default$onTracksChanged(this, trackGroupArray, trackSelectionArray);
        }

        private PlayerListener() {
        }

        public void onPlayerStateChanged(boolean z, int i) {
            if (!((PlayerNotificationManager.this.wasPlayWhenReady == z || i == 1) && PlayerNotificationManager.this.lastPlaybackState == i)) {
                PlayerNotificationManager.this.startOrUpdateNotification();
            }
            PlayerNotificationManager.this.wasPlayWhenReady = z;
            PlayerNotificationManager.this.lastPlaybackState = i;
        }

        public void onTimelineChanged(Timeline timeline, @Nullable Object obj, int i) {
            if (PlayerNotificationManager.this.player != null && PlayerNotificationManager.this.player.getPlaybackState() != 1) {
                PlayerNotificationManager.this.startOrUpdateNotification();
            }
        }

        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
            if (PlayerNotificationManager.this.player != null && PlayerNotificationManager.this.player.getPlaybackState() != 1) {
                PlayerNotificationManager.this.startOrUpdateNotification();
            }
        }

        public void onPositionDiscontinuity(int i) {
            PlayerNotificationManager.this.startOrUpdateNotification();
        }

        public void onRepeatModeChanged(int i) {
            if (PlayerNotificationManager.this.player != null && PlayerNotificationManager.this.player.getPlaybackState() != 1) {
                PlayerNotificationManager.this.startOrUpdateNotification();
            }
        }
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Priority {
    }

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Visibility {
    }

    public static PlayerNotificationManager createWithNotificationChannel(Context context2, String str, @StringRes int i, int i2, MediaDescriptionAdapter mediaDescriptionAdapter2) {
        NotificationUtil.createNotificationChannel(context2, str, i, 2);
        return new PlayerNotificationManager(context2, str, i2, mediaDescriptionAdapter2);
    }

    public PlayerNotificationManager(Context context2, String str, int i, MediaDescriptionAdapter mediaDescriptionAdapter2) {
        this(context2, str, i, mediaDescriptionAdapter2, null);
    }

    public PlayerNotificationManager(Context context2, String str, int i, MediaDescriptionAdapter mediaDescriptionAdapter2, @Nullable CustomActionReceiver customActionReceiver2) {
        Map<String, Action> map;
        this.context = context2.getApplicationContext();
        this.channelId = str;
        this.notificationId = i;
        this.mediaDescriptionAdapter = mediaDescriptionAdapter2;
        this.customActionReceiver = customActionReceiver2;
        this.controlDispatcher = new DefaultControlDispatcher();
        int i2 = instanceIdCounter;
        instanceIdCounter = i2 + 1;
        this.instanceId = i2;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.notificationManager = NotificationManagerCompat.from(context2);
        this.playerListener = new PlayerListener();
        this.notificationBroadcastReceiver = new NotificationBroadcastReceiver();
        this.intentFilter = new IntentFilter();
        this.useNavigationActions = true;
        this.usePlayPauseActions = true;
        this.ongoing = true;
        this.colorized = true;
        this.useChronometer = true;
        this.color = 0;
        this.smallIconResourceId = R.drawable.exo_notification_small_icon;
        this.defaults = 0;
        this.priority = -1;
        this.fastForwardMs = 15000;
        this.rewindMs = DefaultRenderersFactory.DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
        this.stopAction = ACTION_STOP;
        this.badgeIconType = 1;
        this.visibility = 1;
        this.playbackActions = createPlaybackActions(context2, this.instanceId);
        for (String addAction : this.playbackActions.keySet()) {
            this.intentFilter.addAction(addAction);
        }
        if (customActionReceiver2 != null) {
            map = customActionReceiver2.createCustomActions(context2, this.instanceId);
        } else {
            map = Collections.emptyMap();
        }
        this.customActions = map;
        for (String addAction2 : this.customActions.keySet()) {
            this.intentFilter.addAction(addAction2);
        }
        this.stopPendingIntent = ((Action) Assertions.checkNotNull(this.playbackActions.get(ACTION_STOP))).actionIntent;
    }

    public final void setPlayer(@Nullable Player player2) {
        boolean z = false;
        Assertions.checkState(Looper.myLooper() == Looper.getMainLooper());
        if (player2 == null || player2.getApplicationLooper() == Looper.getMainLooper()) {
            z = true;
        }
        Assertions.checkArgument(z);
        Player player3 = this.player;
        if (player3 != player2) {
            if (player3 != null) {
                player3.removeListener(this.playerListener);
                if (player2 == null) {
                    stopNotification();
                }
            }
            this.player = player2;
            if (player2 != null) {
                this.wasPlayWhenReady = player2.getPlayWhenReady();
                this.lastPlaybackState = player2.getPlaybackState();
                player2.addListener(this.playerListener);
                if (this.lastPlaybackState != 1) {
                    startOrUpdateNotification();
                }
            }
        }
    }

    public final void setControlDispatcher(ControlDispatcher controlDispatcher2) {
        if (controlDispatcher2 == null) {
            controlDispatcher2 = new DefaultControlDispatcher();
        }
        this.controlDispatcher = controlDispatcher2;
    }

    public final void setNotificationListener(NotificationListener notificationListener2) {
        this.notificationListener = notificationListener2;
    }

    public final void setFastForwardIncrementMs(long j) {
        if (this.fastForwardMs != j) {
            this.fastForwardMs = j;
            invalidate();
        }
    }

    public final void setRewindIncrementMs(long j) {
        if (this.rewindMs != j) {
            this.rewindMs = j;
            invalidate();
        }
    }

    public final void setUseNavigationActions(boolean z) {
        if (this.useNavigationActions != z) {
            this.useNavigationActions = z;
            invalidate();
        }
    }

    public final void setUsePlayPauseActions(boolean z) {
        if (this.usePlayPauseActions != z) {
            this.usePlayPauseActions = z;
            invalidate();
        }
    }

    public final void setStopAction(@Nullable String str) {
        if (!Util.areEqual(str, this.stopAction)) {
            this.stopAction = str;
            if (ACTION_STOP.equals(str)) {
                this.stopPendingIntent = ((Action) Assertions.checkNotNull(this.playbackActions.get(ACTION_STOP))).actionIntent;
            } else if (str != null) {
                this.stopPendingIntent = ((Action) Assertions.checkNotNull(this.customActions.get(str))).actionIntent;
            } else {
                this.stopPendingIntent = null;
            }
            invalidate();
        }
    }

    public final void setMediaSessionToken(Token token) {
        if (!Util.areEqual(this.mediaSessionToken, token)) {
            this.mediaSessionToken = token;
            invalidate();
        }
    }

    public final void setBadgeIconType(int i) {
        if (this.badgeIconType != i) {
            switch (i) {
                case 0:
                case 1:
                case 2:
                    this.badgeIconType = i;
                    invalidate();
                    return;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public final void setColorized(boolean z) {
        if (this.colorized != z) {
            this.colorized = z;
            invalidate();
        }
    }

    public final void setDefaults(int i) {
        if (this.defaults != i) {
            this.defaults = i;
            invalidate();
        }
    }

    public final void setColor(int i) {
        if (this.color != i) {
            this.color = i;
            invalidate();
        }
    }

    public final void setOngoing(boolean z) {
        if (this.ongoing != z) {
            this.ongoing = z;
            invalidate();
        }
    }

    public final void setPriority(int i) {
        if (this.priority != i) {
            switch (i) {
                case -2:
                case -1:
                case 0:
                case 1:
                case 2:
                    this.priority = i;
                    invalidate();
                    return;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public final void setSmallIcon(@DrawableRes int i) {
        if (this.smallIconResourceId != i) {
            this.smallIconResourceId = i;
            invalidate();
        }
    }

    public final void setUseChronometer(boolean z) {
        if (this.useChronometer != z) {
            this.useChronometer = z;
            invalidate();
        }
    }

    public final void setVisibility(int i) {
        if (this.visibility != i) {
            switch (i) {
                case -1:
                case 0:
                case 1:
                    this.visibility = i;
                    invalidate();
                    return;
                default:
                    throw new IllegalStateException();
            }
        }
    }

    public void invalidate() {
        if (this.isNotificationStarted && this.player != null) {
            updateNotification(null);
        }
    }

    /* access modifiers changed from: private */
    @RequiresNonNull({"player"})
    public Notification updateNotification(@Nullable Bitmap bitmap) {
        Notification createNotification = createNotification(this.player, bitmap);
        this.notificationManager.notify(this.notificationId, createNotification);
        return createNotification;
    }

    /* access modifiers changed from: private */
    public void startOrUpdateNotification() {
        if (this.player != null) {
            Notification updateNotification = updateNotification(null);
            if (!this.isNotificationStarted) {
                this.isNotificationStarted = true;
                this.context.registerReceiver(this.notificationBroadcastReceiver, this.intentFilter);
                NotificationListener notificationListener2 = this.notificationListener;
                if (notificationListener2 != null) {
                    notificationListener2.onNotificationStarted(this.notificationId, updateNotification);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void stopNotification() {
        if (this.isNotificationStarted) {
            this.notificationManager.cancel(this.notificationId);
            this.isNotificationStarted = false;
            this.context.unregisterReceiver(this.notificationBroadcastReceiver);
            NotificationListener notificationListener2 = this.notificationListener;
            if (notificationListener2 != null) {
                notificationListener2.onNotificationCancelled(this.notificationId);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Notification createNotification(Player player2, @Nullable Bitmap bitmap) {
        Action action;
        Builder builder = new Builder(this.context, this.channelId);
        List actions = getActions(player2);
        for (int i = 0; i < actions.size(); i++) {
            String str = (String) actions.get(i);
            if (this.playbackActions.containsKey(str)) {
                action = (Action) this.playbackActions.get(str);
            } else {
                action = (Action) this.customActions.get(str);
            }
            if (action != null) {
                builder.addAction(action);
            }
        }
        MediaStyle mediaStyle = new MediaStyle();
        Token token = this.mediaSessionToken;
        if (token != null) {
            mediaStyle.setMediaSession(token);
        }
        mediaStyle.setShowActionsInCompactView(getActionIndicesForCompactView(actions, player2));
        boolean z = this.stopAction != null;
        mediaStyle.setShowCancelButton(z);
        if (z) {
            PendingIntent pendingIntent = this.stopPendingIntent;
            if (pendingIntent != null) {
                builder.setDeleteIntent(pendingIntent);
                mediaStyle.setCancelButtonIntent(this.stopPendingIntent);
            }
        }
        builder.setStyle(mediaStyle);
        builder.setBadgeIconType(this.badgeIconType).setOngoing(this.ongoing).setColor(this.color).setColorized(this.colorized).setSmallIcon(this.smallIconResourceId).setVisibility(this.visibility).setPriority(this.priority).setDefaults(this.defaults);
        if (!this.useChronometer || player2.isPlayingAd() || player2.isCurrentWindowDynamic() || !player2.getPlayWhenReady() || player2.getPlaybackState() != 3) {
            builder.setShowWhen(false).setUsesChronometer(false);
        } else {
            builder.setWhen(System.currentTimeMillis() - player2.getContentPosition()).setShowWhen(true).setUsesChronometer(true);
        }
        builder.setContentTitle(this.mediaDescriptionAdapter.getCurrentContentTitle(player2));
        builder.setContentText(this.mediaDescriptionAdapter.getCurrentContentText(player2));
        builder.setSubText(this.mediaDescriptionAdapter.getCurrentSubText(player2));
        if (bitmap == null) {
            MediaDescriptionAdapter mediaDescriptionAdapter2 = this.mediaDescriptionAdapter;
            int i2 = this.currentNotificationTag + 1;
            this.currentNotificationTag = i2;
            bitmap = mediaDescriptionAdapter2.getCurrentLargeIcon(player2, new BitmapCallback(i2));
        }
        if (bitmap != null) {
            builder.setLargeIcon(bitmap);
        }
        PendingIntent createCurrentContentIntent = this.mediaDescriptionAdapter.createCurrentContentIntent(player2);
        if (createCurrentContentIntent != null) {
            builder.setContentIntent(createCurrentContentIntent);
        }
        return builder.build();
    }

    /* access modifiers changed from: protected */
    public List<String> getActions(Player player2) {
        boolean isPlayingAd = player2.isPlayingAd();
        ArrayList arrayList = new ArrayList();
        if (!isPlayingAd) {
            if (this.useNavigationActions) {
                arrayList.add(ACTION_PREVIOUS);
            }
            if (this.rewindMs > 0) {
                arrayList.add(ACTION_REWIND);
            }
        }
        if (this.usePlayPauseActions) {
            if (player2.getPlayWhenReady()) {
                arrayList.add(ACTION_PAUSE);
            } else {
                arrayList.add(ACTION_PLAY);
            }
        }
        if (!isPlayingAd) {
            if (this.fastForwardMs > 0) {
                arrayList.add(ACTION_FAST_FORWARD);
            }
            if (this.useNavigationActions && player2.getNextWindowIndex() != -1) {
                arrayList.add(ACTION_NEXT);
            }
        }
        CustomActionReceiver customActionReceiver2 = this.customActionReceiver;
        if (customActionReceiver2 != null) {
            arrayList.addAll(customActionReceiver2.getCustomActions(player2));
        }
        if (ACTION_STOP.equals(this.stopAction)) {
            arrayList.add(this.stopAction);
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    public int[] getActionIndicesForCompactView(List<String> list, Player player2) {
        int indexOf = list.indexOf(ACTION_PAUSE);
        int indexOf2 = list.indexOf(ACTION_PLAY);
        if (indexOf != -1) {
            return new int[]{indexOf};
        } else if (indexOf2 == -1) {
            return new int[0];
        } else {
            return new int[]{indexOf2};
        }
    }

    private static Map<String, Action> createPlaybackActions(Context context2, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put(ACTION_PLAY, new Action(R.drawable.exo_notification_play, context2.getString(R.string.exo_controls_play_description), createBroadcastIntent(ACTION_PLAY, context2, i)));
        hashMap.put(ACTION_PAUSE, new Action(R.drawable.exo_notification_pause, context2.getString(R.string.exo_controls_pause_description), createBroadcastIntent(ACTION_PAUSE, context2, i)));
        hashMap.put(ACTION_STOP, new Action(R.drawable.exo_notification_stop, context2.getString(R.string.exo_controls_stop_description), createBroadcastIntent(ACTION_STOP, context2, i)));
        hashMap.put(ACTION_REWIND, new Action(R.drawable.exo_notification_rewind, context2.getString(R.string.exo_controls_rewind_description), createBroadcastIntent(ACTION_REWIND, context2, i)));
        hashMap.put(ACTION_FAST_FORWARD, new Action(R.drawable.exo_notification_fastforward, context2.getString(R.string.exo_controls_fastforward_description), createBroadcastIntent(ACTION_FAST_FORWARD, context2, i)));
        hashMap.put(ACTION_PREVIOUS, new Action(R.drawable.exo_notification_previous, context2.getString(R.string.exo_controls_previous_description), createBroadcastIntent(ACTION_PREVIOUS, context2, i)));
        hashMap.put(ACTION_NEXT, new Action(R.drawable.exo_notification_next, context2.getString(R.string.exo_controls_next_description), createBroadcastIntent(ACTION_NEXT, context2, i)));
        return hashMap;
    }

    private static PendingIntent createBroadcastIntent(String str, Context context2, int i) {
        Intent intent = new Intent(str).setPackage(context2.getPackageName());
        intent.putExtra(EXTRA_INSTANCE_ID, i);
        return PendingIntent.getBroadcast(context2, i, intent, C.ENCODING_PCM_MU_LAW);
    }
}
