package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioRendererEventListener;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaCrypto;
import com.google.android.exoplayer2.mediacodec.MediaCodecSelector;
import com.google.android.exoplayer2.metadata.MetadataOutput;
import com.google.android.exoplayer2.metadata.MetadataRenderer;
import com.google.android.exoplayer2.text.TextOutput;
import com.google.android.exoplayer2.text.TextRenderer;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.video.MediaCodecVideoRenderer;
import com.google.android.exoplayer2.video.VideoRendererEventListener;
import com.google.android.exoplayer2.video.spherical.CameraMotionRenderer;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class DefaultRenderersFactory implements RenderersFactory {
    public static final long DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS = 5000;
    public static final int EXTENSION_RENDERER_MODE_OFF = 0;
    public static final int EXTENSION_RENDERER_MODE_ON = 1;
    public static final int EXTENSION_RENDERER_MODE_PREFER = 2;
    protected static final int MAX_DROPPED_VIDEO_FRAME_COUNT_TO_NOTIFY = 50;
    private static final String TAG = "DefaultRenderersFactory";
    private long allowedVideoJoiningTimeMs;
    private final Context context;
    @Nullable
    private DrmSessionManager<FrameworkMediaCrypto> drmSessionManager;
    private int extensionRendererMode;
    private MediaCodecSelector mediaCodecSelector;
    private boolean playClearSamplesWithoutKeys;

    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface ExtensionRendererMode {
    }

    /* access modifiers changed from: protected */
    public void buildMiscellaneousRenderers(Context context2, Handler handler, int i, ArrayList<Renderer> arrayList) {
    }

    public DefaultRenderersFactory(Context context2) {
        this.context = context2;
        this.extensionRendererMode = 0;
        this.allowedVideoJoiningTimeMs = DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS;
        this.mediaCodecSelector = MediaCodecSelector.DEFAULT;
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2) {
        this(context2, drmSessionManager2, 0);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, int i) {
        this(context2, i, (long) DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, int i) {
        this(context2, drmSessionManager2, i, DEFAULT_ALLOWED_VIDEO_JOINING_TIME_MS);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, int i, long j) {
        this(context2, null, i, j);
    }

    @Deprecated
    public DefaultRenderersFactory(Context context2, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, int i, long j) {
        this.context = context2;
        this.extensionRendererMode = i;
        this.allowedVideoJoiningTimeMs = j;
        this.drmSessionManager = drmSessionManager2;
        this.mediaCodecSelector = MediaCodecSelector.DEFAULT;
    }

    public DefaultRenderersFactory setExtensionRendererMode(int i) {
        this.extensionRendererMode = i;
        return this;
    }

    public DefaultRenderersFactory setPlayClearSamplesWithoutKeys(boolean z) {
        this.playClearSamplesWithoutKeys = z;
        return this;
    }

    public DefaultRenderersFactory setMediaCodecSelector(MediaCodecSelector mediaCodecSelector2) {
        this.mediaCodecSelector = mediaCodecSelector2;
        return this;
    }

    public DefaultRenderersFactory setAllowedVideoJoiningTimeMs(long j) {
        this.allowedVideoJoiningTimeMs = j;
        return this;
    }

    public Renderer[] createRenderers(Handler handler, VideoRendererEventListener videoRendererEventListener, AudioRendererEventListener audioRendererEventListener, TextOutput textOutput, MetadataOutput metadataOutput, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2) {
        DrmSessionManager<FrameworkMediaCrypto> drmSessionManager3 = drmSessionManager2 == null ? this.drmSessionManager : drmSessionManager2;
        ArrayList arrayList = new ArrayList();
        DrmSessionManager<FrameworkMediaCrypto> drmSessionManager4 = drmSessionManager3;
        buildVideoRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, drmSessionManager4, this.playClearSamplesWithoutKeys, handler, videoRendererEventListener, this.allowedVideoJoiningTimeMs, arrayList);
        buildAudioRenderers(this.context, this.extensionRendererMode, this.mediaCodecSelector, drmSessionManager4, this.playClearSamplesWithoutKeys, buildAudioProcessors(), handler, audioRendererEventListener, arrayList);
        ArrayList arrayList2 = arrayList;
        buildTextRenderers(this.context, textOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildMetadataRenderers(this.context, metadataOutput, handler.getLooper(), this.extensionRendererMode, arrayList2);
        buildCameraMotionRenderers(this.context, this.extensionRendererMode, arrayList);
        Handler handler2 = handler;
        buildMiscellaneousRenderers(this.context, handler, this.extensionRendererMode, arrayList);
        return (Renderer[]) arrayList.toArray(new Renderer[arrayList.size()]);
    }

    /* access modifiers changed from: protected */
    public void buildVideoRenderers(Context context2, int i, MediaCodecSelector mediaCodecSelector2, @Nullable DrmSessionManager<FrameworkMediaCrypto> drmSessionManager2, boolean z, Handler handler, VideoRendererEventListener videoRendererEventListener, long j, ArrayList<Renderer> arrayList) {
        int i2 = i;
        ArrayList<Renderer> arrayList2 = arrayList;
        MediaCodecVideoRenderer mediaCodecVideoRenderer = new MediaCodecVideoRenderer(context2, mediaCodecSelector2, j, drmSessionManager2, z, handler, videoRendererEventListener, 50);
        arrayList2.add(mediaCodecVideoRenderer);
        if (i2 != 0) {
            int size = arrayList.size();
            if (i2 == 2) {
                size--;
            }
            try {
                arrayList2.add(size, (Renderer) Class.forName("com.google.android.exoplayer2.ext.vp9.LibvpxVideoRenderer").getConstructor(new Class[]{Boolean.TYPE, Long.TYPE, Handler.class, VideoRendererEventListener.class, Integer.TYPE}).newInstance(new Object[]{Boolean.valueOf(true), Long.valueOf(j), handler, videoRendererEventListener, Integer.valueOf(50)}));
                Log.i(TAG, "Loaded LibvpxVideoRenderer.");
            } catch (ClassNotFoundException unused) {
            } catch (Exception e) {
                throw new RuntimeException("Error instantiating VP9 extension", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x005e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0066, code lost:
        throw new java.lang.RuntimeException("Error instantiating Opus extension", r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x009b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x00a3, code lost:
        throw new java.lang.RuntimeException("Error instantiating FLAC extension", r0);
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x005e A[ExcHandler: Exception (r0v7 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:7:0x002d] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x009b A[ExcHandler: Exception (r0v6 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:19:0x006a] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void buildAudioRenderers(android.content.Context r13, int r14, com.google.android.exoplayer2.mediacodec.MediaCodecSelector r15, @android.support.annotation.Nullable com.google.android.exoplayer2.drm.DrmSessionManager<com.google.android.exoplayer2.drm.FrameworkMediaCrypto> r16, boolean r17, com.google.android.exoplayer2.audio.AudioProcessor[] r18, android.os.Handler r19, com.google.android.exoplayer2.audio.AudioRendererEventListener r20, java.util.ArrayList<com.google.android.exoplayer2.Renderer> r21) {
        /*
            r12 = this;
            r0 = r14
            r10 = r21
            com.google.android.exoplayer2.audio.MediaCodecAudioRenderer r11 = new com.google.android.exoplayer2.audio.MediaCodecAudioRenderer
            com.google.android.exoplayer2.audio.AudioCapabilities r8 = com.google.android.exoplayer2.audio.AudioCapabilities.getCapabilities(r13)
            r1 = r11
            r2 = r13
            r3 = r15
            r4 = r16
            r5 = r17
            r6 = r19
            r7 = r20
            r9 = r18
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            r10.add(r11)
            if (r0 != 0) goto L_0x001f
            return
        L_0x001f:
            int r1 = r21.size()
            r2 = 2
            if (r0 != r2) goto L_0x0028
            int r1 = r1 + -1
        L_0x0028:
            r0 = 0
            r3 = 3
            r4 = 1
            java.lang.String r5 = "com.google.android.exoplayer2.ext.opus.LibopusAudioRenderer"
            java.lang.Class r5 = java.lang.Class.forName(r5)     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            java.lang.Class<android.os.Handler> r7 = android.os.Handler.class
            r6[r0] = r7     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioRendererEventListener> r7 = com.google.android.exoplayer2.audio.AudioRendererEventListener.class
            r6[r4] = r7     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioProcessor[]> r7 = com.google.android.exoplayer2.audio.AudioProcessor[].class
            r6[r2] = r7     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            java.lang.reflect.Constructor r5 = r5.getConstructor(r6)     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            java.lang.Object[] r6 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            r6[r0] = r19     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            r6[r4] = r20     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            r6[r2] = r18     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            java.lang.Object r5 = r5.newInstance(r6)     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            com.google.android.exoplayer2.Renderer r5 = (com.google.android.exoplayer2.Renderer) r5     // Catch:{ ClassNotFoundException -> 0x0067, Exception -> 0x005e }
            int r6 = r1 + 1
            r10.add(r1, r5)     // Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
            java.lang.String r1 = "DefaultRenderersFactory"
            java.lang.String r5 = "Loaded LibopusAudioRenderer."
            com.google.android.exoplayer2.util.Log.i(r1, r5)     // Catch:{ ClassNotFoundException -> 0x0068, Exception -> 0x005e }
            goto L_0x0068
        L_0x005e:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating Opus extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x0067:
            r6 = r1
        L_0x0068:
            java.lang.String r1 = "com.google.android.exoplayer2.ext.flac.LibflacAudioRenderer"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            java.lang.Class<android.os.Handler> r7 = android.os.Handler.class
            r5[r0] = r7     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioRendererEventListener> r7 = com.google.android.exoplayer2.audio.AudioRendererEventListener.class
            r5[r4] = r7     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioProcessor[]> r7 = com.google.android.exoplayer2.audio.AudioProcessor[].class
            r5[r2] = r7     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r5)     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            r5[r0] = r19     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            r5[r4] = r20     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            r5[r2] = r18     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            java.lang.Object r1 = r1.newInstance(r5)     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            com.google.android.exoplayer2.Renderer r1 = (com.google.android.exoplayer2.Renderer) r1     // Catch:{ ClassNotFoundException -> 0x00a4, Exception -> 0x009b }
            int r5 = r6 + 1
            r10.add(r6, r1)     // Catch:{ ClassNotFoundException -> 0x00a5, Exception -> 0x009b }
            java.lang.String r1 = "DefaultRenderersFactory"
            java.lang.String r6 = "Loaded LibflacAudioRenderer."
            com.google.android.exoplayer2.util.Log.i(r1, r6)     // Catch:{ ClassNotFoundException -> 0x00a5, Exception -> 0x009b }
            goto L_0x00a5
        L_0x009b:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating FLAC extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00a4:
            r5 = r6
        L_0x00a5:
            java.lang.String r1 = "com.google.android.exoplayer2.ext.ffmpeg.FfmpegAudioRenderer"
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            java.lang.Class[] r6 = new java.lang.Class[r3]     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            java.lang.Class<android.os.Handler> r7 = android.os.Handler.class
            r6[r0] = r7     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioRendererEventListener> r7 = com.google.android.exoplayer2.audio.AudioRendererEventListener.class
            r6[r4] = r7     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            java.lang.Class<com.google.android.exoplayer2.audio.AudioProcessor[]> r7 = com.google.android.exoplayer2.audio.AudioProcessor[].class
            r6[r2] = r7     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            java.lang.reflect.Constructor r1 = r1.getConstructor(r6)     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            r3[r0] = r19     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            r3[r4] = r20     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            r3[r2] = r18     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            java.lang.Object r0 = r1.newInstance(r3)     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            com.google.android.exoplayer2.Renderer r0 = (com.google.android.exoplayer2.Renderer) r0     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            r10.add(r5, r0)     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            java.lang.String r0 = "DefaultRenderersFactory"
            java.lang.String r1 = "Loaded FfmpegAudioRenderer."
            com.google.android.exoplayer2.util.Log.i(r0, r1)     // Catch:{ ClassNotFoundException -> 0x00df, Exception -> 0x00d6 }
            goto L_0x00df
        L_0x00d6:
            r0 = move-exception
            java.lang.RuntimeException r1 = new java.lang.RuntimeException
            java.lang.String r2 = "Error instantiating FFmpeg extension"
            r1.<init>(r2, r0)
            throw r1
        L_0x00df:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.DefaultRenderersFactory.buildAudioRenderers(android.content.Context, int, com.google.android.exoplayer2.mediacodec.MediaCodecSelector, com.google.android.exoplayer2.drm.DrmSessionManager, boolean, com.google.android.exoplayer2.audio.AudioProcessor[], android.os.Handler, com.google.android.exoplayer2.audio.AudioRendererEventListener, java.util.ArrayList):void");
    }

    /* access modifiers changed from: protected */
    public void buildTextRenderers(Context context2, TextOutput textOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new TextRenderer(textOutput, looper));
    }

    /* access modifiers changed from: protected */
    public void buildMetadataRenderers(Context context2, MetadataOutput metadataOutput, Looper looper, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new MetadataRenderer(metadataOutput, looper));
    }

    /* access modifiers changed from: protected */
    public void buildCameraMotionRenderers(Context context2, int i, ArrayList<Renderer> arrayList) {
        arrayList.add(new CameraMotionRenderer());
    }

    /* access modifiers changed from: protected */
    public AudioProcessor[] buildAudioProcessors() {
        return new AudioProcessor[0];
    }
}
