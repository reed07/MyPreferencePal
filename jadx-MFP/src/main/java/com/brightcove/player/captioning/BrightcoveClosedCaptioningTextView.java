package com.brightcove.player.captioning;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.brightcove.player.R;
import com.brightcove.player.pictureinpicture.PictureInPictureManager;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class BrightcoveClosedCaptioningTextView extends TextView {
    public static final String TAG = "BrightcoveClosedCaptioningTextView";
    private int edgeAttribute = 0;

    public BrightcoveClosedCaptioningTextView(Context context) {
        super(context);
        initCaptionsTextView();
    }

    public BrightcoveClosedCaptioningTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initCaptionsTextView();
    }

    public BrightcoveClosedCaptioningTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initCaptionsTextView();
    }

    /* access modifiers changed from: protected */
    public void initCaptionsTextView() {
        setSingleLine(false);
    }

    public void setStyle(BrightcoveCaptionStyle brightcoveCaptionStyle) {
        int edgeColor = brightcoveCaptionStyle.edgeColor();
        this.edgeAttribute = brightcoveCaptionStyle.edgeType();
        setTypeface(Typeface.create(brightcoveCaptionStyle.typeface(), 0));
        int round = Math.round((((float) getResources().getDisplayMetrics().densityDpi) * 2.0f) / 160.0f);
        int i = this.edgeAttribute;
        if (i == 0) {
            setShadowLayer(BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, 0);
        } else if (i == 3) {
            float f = (float) round;
            setShadowLayer(f, f / 2.0f, BitmapDescriptorFactory.HUE_RED, edgeColor);
        } else if (i == 4) {
            setShadowLayer((float) round, ((float) (-round)) / 2.0f, BitmapDescriptorFactory.HUE_RED, edgeColor);
        } else if (i == 1) {
            setShadowLayer((float) round, BitmapDescriptorFactory.HUE_RED, BitmapDescriptorFactory.HUE_RED, edgeColor);
        } else if (i == 2) {
            float f2 = (float) round;
            setShadowLayer(f2, f2, f2, edgeColor);
        }
        if (!getText().equals("")) {
            int opacityColor = getOpacityColor(brightcoveCaptionStyle.windowColor(), brightcoveCaptionStyle.windowOpacity());
            ViewParent parent = getParent();
            if (parent == null || ((ViewGroup) parent).getId() != R.id.caption_block) {
                if (this.edgeAttribute == 1) {
                    opacityColor = Color.argb(Color.alpha(opacityColor) / 5, Color.red(opacityColor), Color.green(opacityColor), Color.blue(opacityColor));
                }
                setBackgroundColor(opacityColor);
            } else {
                ((LinearLayout) parent).setBackgroundColor(opacityColor);
            }
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            SpannableString spannableString = new SpannableString(getText());
            int length = getText().length();
            int opacityColor2 = getOpacityColor(brightcoveCaptionStyle.foregroundColor(), brightcoveCaptionStyle.foregroundOpacity());
            int opacityColor3 = getOpacityColor(brightcoveCaptionStyle.backgroundColor(), brightcoveCaptionStyle.backgroundOpacity());
            if (this.edgeAttribute == 1) {
                opacityColor3 = Color.argb(Color.alpha(opacityColor3) / 4, Color.red(opacityColor3), Color.green(opacityColor3), Color.blue(opacityColor3));
            }
            spannableString.setSpan(new ForegroundColorSpan(opacityColor2), 0, length, 33);
            spannableString.setSpan(new BackgroundColorSpan(opacityColor3), 0, length, 33);
            Float valueOf = Float.valueOf(24.0f);
            Float valueOf2 = Float.valueOf(Float.parseFloat(brightcoveCaptionStyle.fontSize()));
            PictureInPictureManager instance = PictureInPictureManager.getInstance();
            if (instance.isInPictureInPictureMode()) {
                valueOf2 = Float.valueOf(valueOf2.floatValue() * instance.getBrightcovePictureInPictureParams().getClosedCaptionsReductionScaleFactor());
            }
            if (valueOf2.floatValue() > BitmapDescriptorFactory.HUE_RED && valueOf.floatValue() > BitmapDescriptorFactory.HUE_RED) {
                setTextSize(valueOf2.floatValue() * valueOf.floatValue());
            }
            spannableStringBuilder.append(spannableString);
            setText(spannableStringBuilder);
        }
    }

    private int getOpacityColor(int i, int i2) {
        return Color.argb(Color.alpha(i2), Color.red(i), Color.green(i), Color.blue(i));
    }

    public void onDraw(Canvas canvas) {
        if (this.edgeAttribute == 1) {
            for (int i = 0; i < 4; i++) {
                super.onDraw(canvas);
            }
        }
        super.onDraw(canvas);
    }
}
