package android.support.design.resources;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StyleRes;
import android.support.annotation.VisibleForTesting;
import android.support.design.R;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.content.res.ResourcesCompat.FontCallback;
import android.text.TextPaint;
import android.util.Log;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

@RestrictTo
public class TextAppearance {
    private static final String TAG = "TextAppearance";
    private static final int TYPEFACE_MONOSPACE = 3;
    private static final int TYPEFACE_SANS = 1;
    private static final int TYPEFACE_SERIF = 2;
    /* access modifiers changed from: private */
    @Nullable
    public Typeface font;
    @Nullable
    public final String fontFamily;
    @FontRes
    private final int fontFamilyResourceId;
    /* access modifiers changed from: private */
    public boolean fontResolved = false;
    @Nullable
    public final ColorStateList shadowColor;
    public final float shadowDx;
    public final float shadowDy;
    public final float shadowRadius;
    public final boolean textAllCaps;
    @Nullable
    public final ColorStateList textColor;
    @Nullable
    public final ColorStateList textColorHint;
    @Nullable
    public final ColorStateList textColorLink;
    public final float textSize;
    public final int textStyle;
    public final int typeface;

    public TextAppearance(Context context, @StyleRes int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.TextAppearance);
        this.textSize = obtainStyledAttributes.getDimension(R.styleable.TextAppearance_android_textSize, BitmapDescriptorFactory.HUE_RED);
        this.textColor = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColor);
        this.textColorHint = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColorHint);
        this.textColorLink = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.TextAppearance_android_textColorLink);
        this.textStyle = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_textStyle, 0);
        this.typeface = obtainStyledAttributes.getInt(R.styleable.TextAppearance_android_typeface, 1);
        int indexWithValue = MaterialResources.getIndexWithValue(obtainStyledAttributes, R.styleable.TextAppearance_fontFamily, R.styleable.TextAppearance_android_fontFamily);
        this.fontFamilyResourceId = obtainStyledAttributes.getResourceId(indexWithValue, 0);
        this.fontFamily = obtainStyledAttributes.getString(indexWithValue);
        this.textAllCaps = obtainStyledAttributes.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        this.shadowColor = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.TextAppearance_android_shadowColor);
        this.shadowDx = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDx, BitmapDescriptorFactory.HUE_RED);
        this.shadowDy = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowDy, BitmapDescriptorFactory.HUE_RED);
        this.shadowRadius = obtainStyledAttributes.getFloat(R.styleable.TextAppearance_android_shadowRadius, BitmapDescriptorFactory.HUE_RED);
        obtainStyledAttributes.recycle();
    }

    @VisibleForTesting
    @NonNull
    public Typeface getFont(Context context) {
        if (this.fontResolved) {
            return this.font;
        }
        if (!context.isRestricted()) {
            try {
                this.font = ResourcesCompat.getFont(context, this.fontFamilyResourceId);
                if (this.font != null) {
                    this.font = Typeface.create(this.font, this.textStyle);
                }
            } catch (NotFoundException | UnsupportedOperationException unused) {
            } catch (Exception e) {
                String str = TAG;
                StringBuilder sb = new StringBuilder();
                sb.append("Error loading font ");
                sb.append(this.fontFamily);
                Log.d(str, sb.toString(), e);
            }
        }
        createFallbackTypeface();
        this.fontResolved = true;
        return this.font;
    }

    public void getFontAsync(Context context, final TextPaint textPaint, @NonNull final FontCallback fontCallback) {
        if (this.fontResolved) {
            updateTextPaintMeasureState(textPaint, this.font);
            return;
        }
        createFallbackTypeface();
        if (context.isRestricted()) {
            this.fontResolved = true;
            updateTextPaintMeasureState(textPaint, this.font);
            return;
        }
        try {
            ResourcesCompat.getFont(context, this.fontFamilyResourceId, new FontCallback() {
                public void onFontRetrieved(@NonNull Typeface typeface) {
                    TextAppearance textAppearance = TextAppearance.this;
                    textAppearance.font = Typeface.create(typeface, textAppearance.textStyle);
                    TextAppearance.this.updateTextPaintMeasureState(textPaint, typeface);
                    TextAppearance.this.fontResolved = true;
                    fontCallback.onFontRetrieved(typeface);
                }

                public void onFontRetrievalFailed(int i) {
                    TextAppearance.this.createFallbackTypeface();
                    TextAppearance.this.fontResolved = true;
                    fontCallback.onFontRetrievalFailed(i);
                }
            }, null);
        } catch (NotFoundException | UnsupportedOperationException unused) {
        } catch (Exception e) {
            String str = TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("Error loading font ");
            sb.append(this.fontFamily);
            Log.d(str, sb.toString(), e);
        }
    }

    /* access modifiers changed from: private */
    public void createFallbackTypeface() {
        if (this.font == null) {
            this.font = Typeface.create(this.fontFamily, this.textStyle);
        }
        if (this.font == null) {
            switch (this.typeface) {
                case 1:
                    this.font = Typeface.SANS_SERIF;
                    break;
                case 2:
                    this.font = Typeface.SERIF;
                    break;
                case 3:
                    this.font = Typeface.MONOSPACE;
                    break;
                default:
                    this.font = Typeface.DEFAULT;
                    break;
            }
            Typeface typeface2 = this.font;
            if (typeface2 != null) {
                this.font = Typeface.create(typeface2, this.textStyle);
            }
        }
    }

    public void updateDrawState(Context context, TextPaint textPaint, FontCallback fontCallback) {
        updateMeasureState(context, textPaint, fontCallback);
        ColorStateList colorStateList = this.textColor;
        textPaint.setColor(colorStateList != null ? colorStateList.getColorForState(textPaint.drawableState, this.textColor.getDefaultColor()) : -16777216);
        float f = this.shadowRadius;
        float f2 = this.shadowDx;
        float f3 = this.shadowDy;
        ColorStateList colorStateList2 = this.shadowColor;
        textPaint.setShadowLayer(f, f2, f3, colorStateList2 != null ? colorStateList2.getColorForState(textPaint.drawableState, this.shadowColor.getDefaultColor()) : 0);
    }

    public void updateMeasureState(Context context, TextPaint textPaint, @Nullable FontCallback fontCallback) {
        if (TextAppearanceConfig.shouldLoadFontSynchronously()) {
            updateTextPaintMeasureState(textPaint, getFont(context));
            return;
        }
        getFontAsync(context, textPaint, fontCallback);
        if (!this.fontResolved) {
            updateTextPaintMeasureState(textPaint, this.font);
        }
    }

    public void updateTextPaintMeasureState(@NonNull TextPaint textPaint, @NonNull Typeface typeface2) {
        textPaint.setTypeface(typeface2);
        int i = (~typeface2.getStyle()) & this.textStyle;
        textPaint.setFakeBoldText((i & 1) != 0);
        textPaint.setTextSkewX((i & 2) != 0 ? -0.25f : BitmapDescriptorFactory.HUE_RED);
        textPaint.setTextSize(this.textSize);
    }
}
