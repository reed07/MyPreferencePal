package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.common.base.Ascii;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@SuppressLint({"ViewConstructor"})
public final class MediaRenderView extends VideoView implements ActivityLifecycleCallbacks, OnCompletionListener, OnErrorListener, OnPreparedListener {
    /* access modifiers changed from: private */
    public static final String l = "MediaRenderView";
    public CustomMediaController a;
    public Bitmap b;
    public ViewGroup c;
    public a d;
    int e;
    boolean f;
    public String g;
    public String h;
    boolean i;
    int j;
    int k;
    private boolean m = false;
    private MediaPlayer n;
    private WeakReference<Activity> o;

    static class CustomMediaController extends MediaController {
        public CustomMediaController(Context context) {
            super(context);
        }

        public final void show(int i) {
            super.show(i);
            if (VERSION.SDK_INT < 19) {
                try {
                    Field declaredField = MediaController.class.getDeclaredField("mAnchor");
                    declaredField.setAccessible(true);
                    View view = (View) declaredField.get(this);
                    Field declaredField2 = MediaController.class.getDeclaredField("mDecor");
                    declaredField2.setAccessible(true);
                    View view2 = (View) declaredField2.get(this);
                    Field declaredField3 = MediaController.class.getDeclaredField("mDecorLayoutParams");
                    declaredField3.setAccessible(true);
                    LayoutParams layoutParams = (LayoutParams) declaredField3.get(this);
                    Field declaredField4 = MediaController.class.getDeclaredField("mWindowManager");
                    declaredField4.setAccessible(true);
                    WindowManager windowManager = (WindowManager) declaredField4.get(this);
                    int[] iArr = new int[2];
                    view.getLocationOnScreen(iArr);
                    view2.measure(MeasureSpec.makeMeasureSpec(view.getWidth(), Integer.MIN_VALUE), MeasureSpec.makeMeasureSpec(view.getHeight(), Integer.MIN_VALUE));
                    view2.setPadding(0, 0, 0, 0);
                    layoutParams.verticalMargin = BitmapDescriptorFactory.HUE_RED;
                    layoutParams.horizontalMargin = BitmapDescriptorFactory.HUE_RED;
                    layoutParams.width = view.getWidth();
                    layoutParams.gravity = 8388659;
                    layoutParams.x = iArr[0];
                    layoutParams.y = (iArr[1] + view.getHeight()) - view2.getMeasuredHeight();
                    windowManager.updateViewLayout(view2, layoutParams);
                } catch (Exception unused) {
                }
            }
        }
    }

    interface a {
        void a();

        void a(MediaRenderView mediaRenderView);
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public final void onActivityPaused(Activity activity) {
    }

    public final void onActivityResumed(Activity activity) {
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
    }

    public MediaRenderView(Activity activity) {
        super(activity);
        setZOrderOnTop(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        if (VERSION.SDK_INT < 28) {
            setDrawingCacheEnabled(true);
        }
        this.e = 100;
        this.j = -1;
        this.k = 0;
        this.f = false;
        this.o = new WeakReference<>(activity);
        com.inmobi.commons.a.a.a((Context) activity, (ActivityLifecycleCallbacks) this);
    }

    /* access modifiers changed from: protected */
    public final void onWindowVisibilityChanged(int i2) {
        super.onWindowVisibilityChanged(i2);
        StringBuilder sb = new StringBuilder(">>> onWindowVisibilityChanged (");
        sb.append(i2);
        sb.append(")");
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        super.onLayout(z, i2, i3, i4, i5);
        getHolder().setSizeFromLayout();
    }

    /* access modifiers changed from: protected */
    @TargetApi(16)
    public final void onVisibilityChanged(@NonNull View view, int i2) {
        super.onVisibilityChanged(view, i2);
        StringBuilder sb = new StringBuilder(">>> onVisibilityChanged (");
        sb.append(i2);
        sb.append(")");
        if (i2 == 0) {
            if (VERSION.SDK_INT >= 16) {
                Context b2 = com.inmobi.commons.a.a.b();
                if (b2 != null) {
                    setBackground(new BitmapDrawable(b2.getResources(), this.b));
                }
                return;
            }
            setBackgroundDrawable(new BitmapDrawable(this.b));
        }
    }

    public final boolean onError(MediaPlayer mediaPlayer, int i2, int i3) {
        StringBuilder sb = new StringBuilder(">>> onError (");
        sb.append(i2);
        sb.append(", ");
        sb.append(i3);
        sb.append(")");
        a();
        return false;
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        this.n = mediaPlayer;
        mediaPlayer.setOnVideoSizeChangedListener(new OnVideoSizeChangedListener() {
            public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
                MediaRenderView.l;
                if (MediaRenderView.this.a == null) {
                    MediaRenderView mediaRenderView = MediaRenderView.this;
                    mediaRenderView.a = new CustomMediaController(mediaRenderView.getContext());
                    MediaRenderView.this.a.setAnchorView(MediaRenderView.this);
                    MediaRenderView mediaRenderView2 = MediaRenderView.this;
                    mediaRenderView2.setMediaController(mediaRenderView2.a);
                    MediaRenderView.this.requestLayout();
                    MediaRenderView.this.requestFocus();
                }
            }
        });
        int i2 = this.k;
        if (i2 < getDuration()) {
            this.k = i2;
            seekTo(i2);
        }
        this.i = true;
        this.d.a();
        start();
    }

    public final void setPlaybackData(String str) {
        this.h = a(str);
        this.g = "anonymous";
        if (this.b == null) {
            this.b = Bitmap.createBitmap(24, 24, Config.ARGB_8888);
            this.b = b(this.h);
        }
    }

    public final void start() {
        if (!this.m) {
            super.start();
        }
    }

    public final void pause() {
        super.pause();
    }

    public final void a() {
        stopPlayback();
        ViewGroup viewGroup = this.c;
        if (viewGroup != null) {
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.c);
            }
            ViewGroup viewGroup3 = (ViewGroup) getParent();
            if (viewGroup3 != null) {
                viewGroup3.removeView(this);
            }
            setBackgroundColor(0);
            this.c = null;
        }
        super.setMediaController(null);
        this.a = null;
        a aVar = this.d;
        if (aVar != null) {
            aVar.a(this);
        }
    }

    public final ViewGroup getViewContainer() {
        return this.c;
    }

    public final void setViewContainer(ViewGroup viewGroup) {
        this.c = viewGroup;
    }

    public final void setListener(a aVar) {
        this.d = aVar;
    }

    public static String a(String str) {
        String str2 = "";
        byte[] bytes = str.getBytes();
        StringBuilder sb = new StringBuilder();
        for (byte b2 : bytes) {
            if ((b2 & 128) > 0) {
                sb.append("%");
                char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
                sb.append(new String(new char[]{cArr[(b2 >> 4) & 15], cArr[b2 & Ascii.SI]}));
            } else {
                sb.append((char) b2);
            }
        }
        try {
            return new String(sb.toString().getBytes(), "ISO-8859-1");
        } catch (UnsupportedEncodingException unused) {
            return str2;
        }
    }

    public static Bitmap b(String str) {
        try {
            return (Bitmap) Class.forName("android.media.ThumbnailUtils").getDeclaredMethod("createVideoThumbnail", new Class[]{String.class, Integer.TYPE}).invoke(null, new Object[]{str, Integer.valueOf(1)});
        } catch (ClassNotFoundException unused) {
            return null;
        } catch (InvocationTargetException unused2) {
            return null;
        } catch (NoSuchMethodException unused3) {
            return null;
        } catch (IllegalAccessException unused4) {
            return null;
        }
    }

    public final void onActivityStarted(Activity activity) {
        if (this.o.get() != null && ((Activity) this.o.get()).equals(activity)) {
            this.m = false;
            start();
        }
    }

    public final void onActivityStopped(Activity activity) {
        Activity activity2 = (Activity) this.o.get();
        if (activity2 != null && activity2.equals(activity)) {
            this.m = true;
            if (getCurrentPosition() != 0) {
                this.k = getCurrentPosition();
            }
            pause();
        }
    }

    public final void onActivityDestroyed(Activity activity) {
        activity.getApplication().unregisterActivityLifecycleCallbacks(this);
    }
}
