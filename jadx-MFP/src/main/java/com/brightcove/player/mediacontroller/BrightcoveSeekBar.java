package com.brightcove.player.mediacontroller;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.SeekBar;
import com.brightcove.player.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

public class BrightcoveSeekBar extends SeekBar {
    private static final String TAG = "BrightcoveSeekBar";
    private int markerHeight;
    private SortedSet<Integer> markers;
    private Paint paint;
    private boolean shouldOverdrawThumb;

    public BrightcoveSeekBar(Context context) {
        this(context, null);
    }

    public BrightcoveSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842875);
    }

    public BrightcoveSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.markers = new TreeSet();
        this.paint = new Paint();
        this.shouldOverdrawThumb = true;
        if (!isInEditMode() && BrightcoveMediaController.checkTvMode(context)) {
            this.markerHeight = (int) getResources().getDimension(R.dimen.player_seekbar_stroke_width);
        }
    }

    public void addMarker(int i) {
        this.markers.add(Integer.valueOf(i));
        invalidate();
    }

    public List<Integer> getMarkers() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.markers);
        Log.d(TAG, String.format(Locale.getDefault(), "The markers are: {%s}.", new Object[]{arrayList}));
        return arrayList;
    }

    public void clearMarkers() {
        this.markers.clear();
    }

    public void removeMarker(Integer num) {
        this.markers.remove(num);
    }

    public String getMarkerColor() {
        return String.valueOf(this.paint.getColor());
    }

    public float getMarkerWidth() {
        return this.paint.getStrokeWidth();
    }

    public void setMarkerColor(int i) {
        this.paint.setColor(i);
    }

    public void setMarkerWidth(float f) {
        this.paint.setStrokeWidth(f);
    }

    public int getMarkerHeight() {
        return this.markerHeight;
    }

    public void setMarkerHeight(int i) {
        this.markerHeight = i;
    }

    public void setShouldMarkerOverdrawThumb(boolean z) {
        this.shouldOverdrawThumb = z;
    }

    /* access modifiers changed from: protected */
    public synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMarkers(canvas);
    }

    @TargetApi(16)
    private void drawMarkers(Canvas canvas) {
        if (this.markers.size() > 0) {
            Rect bounds = getProgressDrawable().getBounds();
            float width = (float) bounds.width();
            float f = (float) bounds.top;
            float height = ((float) bounds.height()) + f;
            int i = this.markerHeight;
            if (i != 0) {
                f = ((f + height) - ((float) i)) / 2.0f;
                height = ((float) i) + f;
            }
            for (Integer floatValue : this.markers) {
                float paddingLeft = ((float) bounds.left) + ((float) getPaddingLeft()) + ((floatValue.floatValue() / ((float) getMax())) * width);
                if (!this.shouldOverdrawThumb && VERSION.SDK_INT >= 16) {
                    Rect copyBounds = getThumb().copyBounds();
                    copyBounds.right -= getThumbOffset();
                    if (copyBounds.contains((int) paddingLeft, ((int) (f + height)) / 2)) {
                    }
                }
                canvas.drawLine(paddingLeft, f, paddingLeft, height, this.paint);
            }
        }
    }
}
