package com.brightcove.player.mediacontroller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import com.brightcove.player.R;
import com.brightcove.player.view.BaseVideoView;
import com.brightcove.player.view.BaseVideoView.OnVideoViewSizeChangedListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BrightcoveControlBar extends LinearLayout {
    public static final int AUDIO_TRACKS = R.styleable.BrightcoveMediaController_brightcove_audio_tracks;
    public static final int AUDIO_TRACKS_IMAGE = R.styleable.BrightcoveMediaController_brightcove_audio_tracks_image;
    public static final int CLOSE = R.styleable.BrightcoveMediaController_brightcove_close;
    public static final int CLOSED_CAPTIONS = R.styleable.BrightcoveMediaController_brightcove_closed_captions;
    public static final int CLOSED_CAPTIONS_IMAGE = R.styleable.BrightcoveMediaController_brightcove_closed_captions_image;
    public static final int CLOSE_IMAGE = R.styleable.BrightcoveMediaController_brightcove_close_image;
    public static final int ENTER_FULL_SCREEN_IMAGE = R.styleable.BrightcoveMediaController_brightcove_enter_full_screen_image;
    public static final int EXIT_FULL_SCREEN_IMAGE = R.styleable.BrightcoveMediaController_brightcove_exit_full_screen_image;
    public static final int FAST_FORWARD = R.styleable.BrightcoveMediaController_brightcove_fast_forward;
    public static final int FULL_SCREEN = R.styleable.BrightcoveMediaController_brightcove_full_screen;
    public static final int LIVE = R.styleable.BrightcoveMediaController_brightcove_live;
    public static final int PAUSE_IMAGE = R.styleable.BrightcoveMediaController_brightcove_pause_image;
    public static final int PICTURE_IN_PICTURE = R.styleable.BrightcoveMediaController_brightcove_picture_in_picture;
    public static final int PICTURE_IN_PICTURE_OFF_IMAGE = R.styleable.BrightcoveMediaController_brightcove_picture_in_picture_off_image;
    public static final int PICTURE_IN_PICTURE_ON_IMAGE = R.styleable.BrightcoveMediaController_brightcove_picture_in_picture_on_image;
    public static final int PLAY = R.styleable.BrightcoveMediaController_brightcove_play;
    public static final int PLAYER_OPTIONS = R.styleable.BrightcoveMediaController_brightcove_player_options;
    public static final int PLAYER_OPTIONS_IMAGE = R.styleable.BrightcoveMediaController_brightcove_player_options_image;
    public static final int PLAY_IMAGE = R.styleable.BrightcoveMediaController_brightcove_play_image;
    public static final int REWIND = R.styleable.BrightcoveMediaController_brightcove_rewind;
    public static final int REWIND_IMAGE = R.styleable.BrightcoveMediaController_brightcove_pause_image;
    public static final int SEEK_BAR = R.styleable.BrightcoveMediaController_brightcove_seekbar;
    private static final String TAG = BrightcoveControlBar.class.getSimpleName();
    public static final int VR_MODE = R.styleable.BrightcoveMediaController_brightcove_vr_mode;
    public static final int VR_MODE_IMAGE = R.styleable.BrightcoveMediaController_brightcove_vr_mode_image;
    private boolean align;
    private List<TypedValue> attributeValues;
    private SparseArray<Drawable> imageMap;
    private BaseVideoView videoView;

    public BrightcoveControlBar(Context context) {
        super(context);
        this.attributeValues = new ArrayList();
        this.imageMap = new SparseArray<>();
    }

    public BrightcoveControlBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    @SuppressLint({"NewApi"})
    public BrightcoveControlBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.attributeValues = new ArrayList();
        this.imageMap = new SparseArray<>();
        if (!isInEditMode()) {
            init(context, attributeSet, i);
        }
    }

    public boolean getBooleanResource(int i) {
        boolean z = true;
        if (i <= -1 || i >= this.attributeValues.size()) {
            Log.e(TAG, String.format(Locale.getDefault(), "The index %1$d is not valid.", new Object[]{Integer.valueOf(i)}));
            return false;
        }
        TypedValue typedValue = (TypedValue) this.attributeValues.get(i);
        if (typedValue == null || typedValue.type != 18) {
            return false;
        }
        if (typedValue.data == 0) {
            z = false;
        }
        return z;
    }

    public int getColorResource(int i, int i2) {
        if (i <= -1 || i >= this.attributeValues.size()) {
            Log.e(TAG, String.format(Locale.getDefault(), "The index %1$d is not valid.", new Object[]{Integer.valueOf(i)}));
            return i2;
        }
        TypedValue typedValue = (TypedValue) this.attributeValues.get(i);
        if (typedValue == null || typedValue.type != 28) {
            return i2;
        }
        return typedValue.data;
    }

    public Drawable getImage(int i) {
        return (Drawable) this.imageMap.get(i);
    }

    public int getResourceId(int i) {
        if (i <= -1 || i >= this.attributeValues.size()) {
            Log.e(TAG, String.format(Locale.getDefault(), "The index %1$d is not valid.", new Object[]{Integer.valueOf(i)}));
            return -1;
        }
        TypedValue typedValue = (TypedValue) this.attributeValues.get(i);
        if (typedValue == null || typedValue.type != 1) {
            return -1;
        }
        return typedValue.data;
    }

    public float getFloatResource(int i, float f) {
        if (i <= -1 || i >= this.attributeValues.size()) {
            Log.e(TAG, String.format(Locale.getDefault(), "The index %1$d is not valid.", new Object[]{Integer.valueOf(i)}));
            return f;
        }
        TypedValue typedValue = (TypedValue) this.attributeValues.get(i);
        if (typedValue == null || typedValue.type != 4) {
            return f;
        }
        return typedValue.getFloat();
    }

    public int getIntegerResource(int i, int i2) {
        if (i <= -1 || i >= this.attributeValues.size()) {
            Log.e(TAG, String.format(Locale.getDefault(), "The index %1$d is not valid.", new Object[]{Integer.valueOf(i)}));
            return i2;
        }
        TypedValue typedValue = (TypedValue) this.attributeValues.get(i);
        if (typedValue == null) {
            return i2;
        }
        if (typedValue.type == 16 || typedValue.type == 17) {
            return typedValue.data;
        }
        return i2;
    }

    public String getStringResource(int i, String str) {
        if (i <= -1 || i >= this.attributeValues.size()) {
            Log.e(TAG, String.format(Locale.getDefault(), "The index %1$d is not valid.", new Object[]{Integer.valueOf(i)}));
            return str;
        }
        TypedValue typedValue = (TypedValue) this.attributeValues.get(i);
        if (typedValue == null || typedValue.type != 3) {
            return str;
        }
        return (String) typedValue.string;
    }

    /* access modifiers changed from: 0000 */
    @SuppressLint({"NewApi"})
    public void setVideoView(BaseVideoView baseVideoView) {
        this.videoView = baseVideoView;
        baseVideoView.addOnVideoViewSizeChangedListener(new OnVideoViewSizeChangedListener() {
            public void onVideoViewSizeChange(int i, int i2, int i3, int i4) {
                BrightcoveControlBar.this.updateMargins();
            }
        });
        if (VERSION.SDK_INT >= 11) {
            baseVideoView.getStillView().addOnLayoutChangeListener(new OnLayoutChangeListener() {
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    BrightcoveControlBar.this.updateMargins();
                }
            });
        }
        updateMargins();
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        updateMargins();
    }

    /* access modifiers changed from: private */
    public void updateMargins() {
        BaseVideoView baseVideoView = this.videoView;
        if (baseVideoView != null && this.align) {
            int height = (baseVideoView.getHeight() - this.videoView.getMeasuredVideoHeight()) / 2;
            if (height == 0) {
                height = (this.videoView.getHeight() - this.videoView.getStillView().getMeasuredHeight()) / 2;
            }
            LayoutParams layoutParams = (LayoutParams) getLayoutParams();
            if (height != layoutParams.bottomMargin) {
                layoutParams.setMargins(0, 0, 0, height);
                requestLayout();
            }
        }
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        String str = TAG;
        Locale locale = Locale.getDefault();
        String str2 = "The attribute set contains %1$d attributes.";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(attributeSet == null ? -1 : attributeSet.getAttributeCount());
        Log.d(str, String.format(locale, str2, objArr));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.BrightcoveMediaController, i, R.style.BrightcoveControlBar);
        for (int i2 = 0; i2 < obtainStyledAttributes.length(); i2++) {
            TypedValue typedValue = new TypedValue();
            obtainStyledAttributes.getValue(i2, typedValue);
            this.attributeValues.add(typedValue);
            if ((i2 == R.styleable.BrightcoveMediaController_brightcove_chrome_cast_image || i2 == R.styleable.BrightcoveMediaController_brightcove_audio_tracks_image || i2 == R.styleable.BrightcoveMediaController_brightcove_closed_captions_image || i2 == R.styleable.BrightcoveMediaController_brightcove_enter_full_screen_image || i2 == R.styleable.BrightcoveMediaController_brightcove_exit_full_screen_image || i2 == R.styleable.BrightcoveMediaController_brightcove_pause_image || i2 == R.styleable.BrightcoveMediaController_brightcove_play_image || i2 == R.styleable.BrightcoveMediaController_brightcove_rewind_image || i2 == R.styleable.BrightcoveMediaController_brightcove_picture_in_picture_on_image || i2 == R.styleable.BrightcoveMediaController_brightcove_vr_mode_image) && typedValue.type == 3) {
                Drawable drawable = obtainStyledAttributes.getDrawable(i2);
                if (drawable != null) {
                    this.imageMap.put(i2, drawable);
                }
            }
            if (i2 == R.styleable.BrightcoveMediaController_brightcove_align) {
                setAlign(obtainStyledAttributes.getBoolean(i2, true));
            }
        }
        obtainStyledAttributes.recycle();
    }

    public void setAlign(boolean z) {
        this.align = z;
    }
}
