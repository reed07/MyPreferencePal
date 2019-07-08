package com.facebook.ads.internal.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.AttributeSet;
import android.view.View.MeasureSpec;
import com.facebook.ads.internal.view.d.b;
import com.facebook.ads.internal.view.d.c;
import com.facebook.ads.internal.w.b.x;

public class j extends c implements com.facebook.ads.internal.view.d.c.a {
    private final HScrollLinearLayoutManager c;
    private a d;
    private int e = -1;
    private int f = -1;
    private int g = 0;
    private int h = 0;

    public interface a {
        void a(int i, int i2);
    }

    public j(Context context) {
        super(context);
        this.c = new HScrollLinearLayoutManager(context, new b(), new com.facebook.ads.internal.view.d.a());
        a();
    }

    public j(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = new HScrollLinearLayoutManager(context, new b(), new com.facebook.ads.internal.view.d.a());
        a();
    }

    public j(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new HScrollLinearLayoutManager(context, new b(), new com.facebook.ads.internal.view.d.a());
        a();
    }

    private void a() {
        this.c.setOrientation(0);
        setLayoutManager(this.c);
        setSaveEnabled(false);
        setSnapDelegate(this);
    }

    public int a(int i) {
        int abs = Math.abs(i);
        if (abs <= this.a) {
            return 0;
        }
        int i2 = this.g;
        int i3 = 1;
        if (i2 != 0) {
            i3 = 1 + (abs / i2);
        }
        return i3;
    }

    /* access modifiers changed from: protected */
    public void a(int i, boolean z) {
        super.a(i, z);
        if (i != this.e || this.f != 0) {
            this.e = i;
            this.f = 0;
            a aVar = this.d;
            if (aVar != null) {
                aVar.a(this.e, this.f);
            }
        }
    }

    public int getChildSpacing() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        int i3;
        super.onMeasure(i, i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int r = com.facebook.ads.internal.r.a.q(getContext()) ? (((int) x.b) * com.facebook.ads.internal.r.a.r(getContext())) + paddingTop : Math.round(((float) getMeasuredWidth()) / 1.91f);
        int mode = MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            r = Math.min(MeasureSpec.getSize(i2), r);
        } else if (mode == 1073741824) {
            r = MeasureSpec.getSize(i2);
        }
        int i4 = r - paddingTop;
        if (!com.facebook.ads.internal.r.a.q(getContext())) {
            int i5 = this.h * 2;
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - i5;
            int itemCount = getAdapter().getItemCount();
            int i6 = 0;
            int i7 = Integer.MAX_VALUE;
            while (true) {
                if (i7 <= i4) {
                    i3 = i7;
                    break;
                }
                i6++;
                if (i6 >= itemCount) {
                    i3 = i4;
                    break;
                }
                i7 = (int) (((float) (measuredWidth - (i6 * i5))) / (((float) i6) + 0.333f));
            }
        } else {
            i3 = Math.min(c.a, i4);
        }
        setMeasuredDimension(getMeasuredWidth(), paddingTop + i3);
        if (!com.facebook.ads.internal.r.a.q(getContext())) {
            setChildWidth(i3 + (this.h * 2));
        }
    }

    public void setAdapter(@Nullable Adapter adapter) {
        this.c.a(adapter == null ? -1 : adapter.hashCode());
        super.setAdapter(adapter);
    }

    public void setChildSpacing(int i) {
        this.h = i;
    }

    public void setChildWidth(int i) {
        this.g = i;
        int measuredWidth = getMeasuredWidth();
        this.c.b((((measuredWidth - getPaddingLeft()) - getPaddingRight()) - this.g) / 2);
        this.c.a(((double) this.g) / ((double) measuredWidth));
    }

    public void setCurrentPosition(int i) {
        a(i, false);
    }

    public void setOnPageChangedListener(a aVar) {
        this.d = aVar;
    }
}
