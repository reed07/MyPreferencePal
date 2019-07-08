package com.google.android.exoplayer2.audio;

import android.os.Handler;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AudioRendererEventListener.EventDispatcher;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.util.Assertions;

public interface AudioRendererEventListener {

    /* renamed from: com.google.android.exoplayer2.audio.AudioRendererEventListener$-CC reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$onAudioDecoderInitialized(AudioRendererEventListener audioRendererEventListener, String str, long j, long j2) {
        }

        public static void $default$onAudioDisabled(AudioRendererEventListener audioRendererEventListener, DecoderCounters decoderCounters) {
        }

        public static void $default$onAudioEnabled(AudioRendererEventListener audioRendererEventListener, DecoderCounters decoderCounters) {
        }

        public static void $default$onAudioInputFormatChanged(AudioRendererEventListener audioRendererEventListener, Format format) {
        }

        public static void $default$onAudioSessionId(AudioRendererEventListener audioRendererEventListener, int i) {
        }

        public static void $default$onAudioSinkUnderrun(AudioRendererEventListener audioRendererEventListener, int i, long j, long j2) {
        }
    }

    public static final class EventDispatcher {
        @Nullable
        private final Handler handler;
        @Nullable
        private final AudioRendererEventListener listener;

        public EventDispatcher(@Nullable Handler handler2, @Nullable AudioRendererEventListener audioRendererEventListener) {
            this.handler = audioRendererEventListener != null ? (Handler) Assertions.checkNotNull(handler2) : null;
            this.listener = audioRendererEventListener;
        }

        public void enabled(DecoderCounters decoderCounters) {
            if (this.listener != null) {
                this.handler.post(new Runnable(decoderCounters) {
                    private final /* synthetic */ DecoderCounters f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onAudioEnabled(this.f$1);
                    }
                });
            }
        }

        public void decoderInitialized(String str, long j, long j2) {
            if (this.listener != null) {
                Handler handler2 = this.handler;
                $$Lambda$AudioRendererEventListener$EventDispatcher$F29t8_xYSK7h_6CpLRlp2y2yb1E r1 = new Runnable(str, j, j2) {
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ long f$2;
                    private final /* synthetic */ long f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r5;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onAudioDecoderInitialized(this.f$1, this.f$2, this.f$3);
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
                        EventDispatcher.this.listener.onAudioInputFormatChanged(this.f$1);
                    }
                });
            }
        }

        public void audioTrackUnderrun(int i, long j, long j2) {
            if (this.listener != null) {
                Handler handler2 = this.handler;
                $$Lambda$AudioRendererEventListener$EventDispatcher$oPQKly422CpX1mqIU2N6d76OGxk r1 = new Runnable(i, j, j2) {
                    private final /* synthetic */ int f$1;
                    private final /* synthetic */ long f$2;
                    private final /* synthetic */ long f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r5;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onAudioSinkUnderrun(this.f$1, this.f$2, this.f$3);
                    }
                };
                handler2.post(r1);
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
                        EventDispatcher.lambda$disabled$4(EventDispatcher.this, this.f$1);
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$disabled$4(EventDispatcher eventDispatcher, DecoderCounters decoderCounters) {
            decoderCounters.ensureUpdated();
            eventDispatcher.listener.onAudioDisabled(decoderCounters);
        }

        public void audioSessionId(int i) {
            if (this.listener != null) {
                this.handler.post(new Runnable(i) {
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void run() {
                        EventDispatcher.this.listener.onAudioSessionId(this.f$1);
                    }
                });
            }
        }
    }

    void onAudioDecoderInitialized(String str, long j, long j2);

    void onAudioDisabled(DecoderCounters decoderCounters);

    void onAudioEnabled(DecoderCounters decoderCounters);

    void onAudioInputFormatChanged(Format format);

    void onAudioSessionId(int i);

    void onAudioSinkUnderrun(int i, long j, long j2);
}
