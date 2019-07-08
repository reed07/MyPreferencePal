package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.video.VideoRendererEventListener.EventDispatcher;

public interface VideoRendererEventListener {

    /* renamed from: com.google.android.exoplayer2.video.VideoRendererEventListener$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onDroppedFrames(VideoRendererEventListener videoRendererEventListener, int i, long j) {
        }

        public static void $default$onRenderedFirstFrame(@Nullable VideoRendererEventListener videoRendererEventListener, Surface surface) {
        }

        public static void $default$onVideoDecoderInitialized(VideoRendererEventListener videoRendererEventListener, String str, long j, long j2) {
        }

        public static void $default$onVideoDisabled(VideoRendererEventListener videoRendererEventListener, DecoderCounters decoderCounters) {
        }

        public static void $default$onVideoEnabled(VideoRendererEventListener videoRendererEventListener, DecoderCounters decoderCounters) {
        }

        public static void $default$onVideoInputFormatChanged(VideoRendererEventListener videoRendererEventListener, Format format) {
        }

        public static void $default$onVideoSizeChanged(VideoRendererEventListener videoRendererEventListener, int i, int i2, int i3, float f) {
        }
    }

    public static final class EventDispatcher {
        @Nullable
        private final Handler handler;
        @Nullable
        private final VideoRendererEventListener listener;

        public EventDispatcher(@Nullable Handler handler2, @Nullable VideoRendererEventListener videoRendererEventListener) {
            this.handler = videoRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = videoRendererEventListener;
        }

        public void enabled(DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable(decoderCounters) {
                    private final /* synthetic */ DecoderCounters f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onVideoEnabled(this.f$1);
                    }
                });
            }
        }

        public void decoderInitialized(String str, long j, long j2) {
            if (this.listener != null) {
                Handler handler2 = this.handler;
                $$Lambda$VideoRendererEventListener$EventDispatcher$Y232CA7hogfrRJjYu2VeUSxg0VQ r1 = new Runnable(str, j, j2) {
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ long f$2;
                    private final /* synthetic */ long f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r5;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onVideoDecoderInitialized(this.f$1, this.f$2, this.f$3);
                    }
                };
                handler2.post(r1);
            }
        }

        public void inputFormatChanged(Format format) {
            if (this.listener != null) {
                this.handler.post(new Runnable(format) {
                    private final /* synthetic */ Format f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onVideoInputFormatChanged(this.f$1);
                    }
                });
            }
        }

        public void droppedFrames(int i, long j) {
            if (this.listener != null) {
                this.handler.post(new Runnable(i, j) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ long f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onDroppedFrames(this.f$1, this.f$2);
                    }
                });
            }
        }

        public void videoSizeChanged(int i, int i2, int i3, float f) {
            if (this.listener != null) {
                Handler handler2 = this.handler;
                $$Lambda$VideoRendererEventListener$EventDispatcher$TaBV3X3b5lKElsQ7tczViKAyQ3w r1 = new Runnable(i, i2, i3, f) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ int f$2;
                    private final /* synthetic */ int f$3;
                    private final /* synthetic */ float f$4;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                        this.f$4 = r5;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onVideoSizeChanged(this.f$1, this.f$2, this.f$3, this.f$4);
                    }
                };
                handler2.post(r1);
            }
        }

        public void renderedFirstFrame(@Nullable Surface surface) {
            if (this.listener != null) {
                this.handler.post(new Runnable(surface) {
                    private final /* synthetic */ Surface f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onRenderedFirstFrame(this.f$1);
                    }
                });
            }
        }

        public void disabled(DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable(decoderCounters) {
                    private final /* synthetic */ DecoderCounters f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        EventDispatcher.lambda$disabled$6(EventDispatcher.this, this.f$1);
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$disabled$6(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            eventDispatcher.listener.onVideoDisabled(decoderCounters);
        }
    }

    void onDroppedFrames(int i, long j);

    void onRenderedFirstFrame(@Nullable Surface surface);

    void onVideoDecoderInitialized(String str, long j, long j2);

    void onVideoDisabled(DecoderCounters decoderCounters);

    void onVideoEnabled(DecoderCounters decoderCounters);

    void onVideoInputFormatChanged(Format format);

    void onVideoSizeChanged(int i, int i2, int i3, float f);
}
