package com.myfitnesspal.shared.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;

public class MfpImageView extends AppCompatImageView {
    private Bitmap defaultBitmap = null;
    private int errorImageId;
    private Paint paint;
    private Drawable placeholderDrawable;
    private int placeholderImageId;
    private RequestListener<Drawable> requestListener = null;
    private boolean transformCircular = false;
    private String url;
    private boolean usePlaceholder = true;

    public MfpImageView(Context context) {
        super(context);
        init(null);
    }

    public MfpImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public MfpImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Bitmap bitmap = this.defaultBitmap;
        if (bitmap != null) {
            bitmap.recycle();
            this.defaultBitmap = null;
        }
    }

    private void init(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.mfp_UrlImageView);
            if (obtainStyledAttributes != null) {
                this.transformCircular = obtainStyledAttributes.getBoolean(0, false);
                this.placeholderDrawable = obtainStyledAttributes.getDrawable(1);
                obtainStyledAttributes.recycle();
            }
        }
        this.paint = new Paint();
        this.paint.setAntiAlias(true);
    }

    private String getUrl() {
        return this.url;
    }

    private int getPlaceholderImageId() {
        int i = this.placeholderImageId;
        return i > 0 ? i : R.drawable.ic_profile;
    }

    private Drawable getPlaceholderDrawable() {
        return this.placeholderDrawable;
    }

    public MfpImageView setPlaceholderImageId(int i) {
        this.placeholderImageId = i;
        this.placeholderDrawable = null;
        return this;
    }

    public MfpImageView setPlaceholderDrawable(Drawable drawable) {
        this.placeholderImageId = 0;
        this.placeholderDrawable = drawable;
        return this;
    }

    public MfpImageView setTransformCircular(boolean z) {
        this.transformCircular = z;
        return this;
    }

    public MfpImageView usePlaceholder(boolean z) {
        this.usePlaceholder = z;
        return this;
    }

    public void setErrorImageId(int i) {
        this.errorImageId = i;
    }

    public MfpImageView setUrl(String str) {
        if (Strings.isEmpty(str)) {
            str = null;
        }
        this.url = str;
        try {
            RequestBuilder load = Glide.with(getContext()).load(getUrl());
            RequestOptions centerCrop = new RequestOptions().centerCrop();
            if (this.usePlaceholder) {
                if (getPlaceholderDrawable() != null) {
                    centerCrop.placeholder(getPlaceholderDrawable());
                } else {
                    centerCrop.placeholder(getPlaceholderImageId());
                }
            }
            if (this.errorImageId != 0) {
                centerCrop.error(this.errorImageId);
            }
            if (this.requestListener != null) {
                load.listener(this.requestListener);
            }
            load.apply(centerCrop).into((ImageView) this);
        } catch (IllegalArgumentException e) {
            Ln.e(e, "Failed to set the image using Glide. Activity destroyed?", new Object[0]);
        }
        return this;
    }

    private void drawCircularImage(Canvas canvas, Bitmap bitmap) {
        this.paint.setShader(new BitmapShader(Bitmap.createScaledBitmap(bitmap, canvas.getWidth(), canvas.getHeight(), true), TileMode.CLAMP, TileMode.CLAMP));
        int width = canvas.getWidth() / 2;
        int height = canvas.getHeight() / 2;
        canvas.drawCircle((float) width, (float) height, (float) Math.min(width, height), this.paint);
    }

    private Bitmap getBitmap() {
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        Bitmap bitmap = this.defaultBitmap;
        if (bitmap == null || !(bitmap.getWidth() == getWidth() || this.defaultBitmap.getHeight() == getHeight())) {
            this.defaultBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(this.defaultBitmap);
        canvas.drawColor(0, Mode.CLEAR);
        if (getDrawable() != null) {
            getDrawable().draw(canvas);
        }
        return this.defaultBitmap;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.transformCircular) {
            Bitmap bitmap = getBitmap();
            if (bitmap != null) {
                drawCircularImage(canvas, bitmap);
                return;
            }
        }
        super.onDraw(canvas);
    }

    public MfpImageView setRequestListener(RequestListener<Drawable> requestListener2) {
        this.requestListener = requestListener2;
        return this;
    }
}
