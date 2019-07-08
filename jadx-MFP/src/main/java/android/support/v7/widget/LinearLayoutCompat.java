package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.RestrictTo;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
    public static final int HORIZONTAL = 0;
    private static final int INDEX_BOTTOM = 2;
    private static final int INDEX_CENTER_VERTICAL = 0;
    private static final int INDEX_FILL = 3;
    private static final int INDEX_TOP = 1;
    public static final int SHOW_DIVIDER_BEGINNING = 1;
    public static final int SHOW_DIVIDER_END = 4;
    public static final int SHOW_DIVIDER_MIDDLE = 2;
    public static final int SHOW_DIVIDER_NONE = 0;
    public static final int VERTICAL = 1;
    private static final int VERTICAL_GRAVITY_COUNT = 4;
    private boolean mBaselineAligned;
    private int mBaselineAlignedChildIndex;
    private int mBaselineChildTop;
    private Drawable mDivider;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDividerWidth;
    private int mGravity;
    private int[] mMaxAscent;
    private int[] mMaxDescent;
    private int mOrientation;
    private int mShowDividers;
    private int mTotalLength;
    private boolean mUseLargestChild;
    private float mWeightSum;

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface DividerMode {
    }

    public static class LayoutParams extends MarginLayoutParams {
        public int gravity;
        public float weight;

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.gravity = -1;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LinearLayoutCompat_Layout);
            this.weight = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, BitmapDescriptorFactory.HUE_RED);
            this.gravity = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
            obtainStyledAttributes.recycle();
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.gravity = -1;
            this.weight = BitmapDescriptorFactory.HUE_RED;
        }

        public LayoutParams(int i, int i2, float f) {
            super(i, i2);
            this.gravity = -1;
            this.weight = f;
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
        }

        public LayoutParams(MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.gravity = -1;
        }

        public LayoutParams(LayoutParams layoutParams) {
            super(layoutParams);
            this.gravity = -1;
            this.weight = layoutParams.weight;
            this.gravity = layoutParams.gravity;
        }
    }

    @RestrictTo
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationMode {
    }

    /* access modifiers changed from: 0000 */
    public int getChildrenSkipCount(View view, int i) {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int getLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int getNextLocationOffset(View view) {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int measureNullChild(int i) {
        return 0;
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public LinearLayoutCompat(Context context) {
        this(context, null);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutCompat(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mBaselineAligned = true;
        this.mBaselineAlignedChildIndex = -1;
        this.mBaselineChildTop = 0;
        this.mGravity = 8388659;
        TintTypedArray obtainStyledAttributes = TintTypedArray.obtainStyledAttributes(context, attributeSet, R.styleable.LinearLayoutCompat, i, 0);
        int i2 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
        if (i2 >= 0) {
            setOrientation(i2);
        }
        int i3 = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
        if (i3 >= 0) {
            setGravity(i3);
        }
        boolean z = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
        if (!z) {
            setBaselineAligned(z);
        }
        this.mWeightSum = obtainStyledAttributes.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0f);
        this.mBaselineAlignedChildIndex = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
        this.mUseLargestChild = obtainStyledAttributes.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
        setDividerDrawable(obtainStyledAttributes.getDrawable(R.styleable.LinearLayoutCompat_divider));
        this.mShowDividers = obtainStyledAttributes.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
        this.mDividerPadding = obtainStyledAttributes.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
        obtainStyledAttributes.recycle();
    }

    public void setShowDividers(int i) {
        if (i != this.mShowDividers) {
            requestLayout();
        }
        this.mShowDividers = i;
    }

    public int getShowDividers() {
        return this.mShowDividers;
    }

    public Drawable getDividerDrawable() {
        return this.mDivider;
    }

    public void setDividerDrawable(Drawable drawable) {
        if (drawable != this.mDivider) {
            this.mDivider = drawable;
            boolean z = false;
            if (drawable != null) {
                this.mDividerWidth = drawable.getIntrinsicWidth();
                this.mDividerHeight = drawable.getIntrinsicHeight();
            } else {
                this.mDividerWidth = 0;
                this.mDividerHeight = 0;
            }
            if (drawable == null) {
                z = true;
            }
            setWillNotDraw(z);
            requestLayout();
        }
    }

    public void setDividerPadding(int i) {
        this.mDividerPadding = i;
    }

    public int getDividerPadding() {
        return this.mDividerPadding;
    }

    @RestrictTo
    public int getDividerWidth() {
        return this.mDividerWidth;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        if (this.mDivider != null) {
            if (this.mOrientation == 1) {
                drawDividersVertical(canvas);
            } else {
                drawDividersHorizontal(canvas);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void drawDividersVertical(Canvas canvas) {
        int i;
        int virtualChildCount = getVirtualChildCount();
        for (int i2 = 0; i2 < virtualChildCount; i2++) {
            View virtualChildAt = getVirtualChildAt(i2);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i2))) {
                drawHorizontalDivider(canvas, (virtualChildAt.getTop() - ((LayoutParams) virtualChildAt.getLayoutParams()).topMargin) - this.mDividerHeight);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 == null) {
                i = (getHeight() - getPaddingBottom()) - this.mDividerHeight;
            } else {
                i = virtualChildAt2.getBottom() + ((LayoutParams) virtualChildAt2.getLayoutParams()).bottomMargin;
            }
            drawHorizontalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: 0000 */
    public void drawDividersHorizontal(Canvas canvas) {
        int i;
        int i2;
        int virtualChildCount = getVirtualChildCount();
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(this);
        for (int i3 = 0; i3 < virtualChildCount; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (!(virtualChildAt == null || virtualChildAt.getVisibility() == 8 || !hasDividerBeforeChildAt(i3))) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (isLayoutRtl) {
                    i2 = virtualChildAt.getRight() + layoutParams.rightMargin;
                } else {
                    i2 = (virtualChildAt.getLeft() - layoutParams.leftMargin) - this.mDividerWidth;
                }
                drawVerticalDivider(canvas, i2);
            }
        }
        if (hasDividerBeforeChildAt(virtualChildCount)) {
            View virtualChildAt2 = getVirtualChildAt(virtualChildCount - 1);
            if (virtualChildAt2 != null) {
                LayoutParams layoutParams2 = (LayoutParams) virtualChildAt2.getLayoutParams();
                if (isLayoutRtl) {
                    i = (virtualChildAt2.getLeft() - layoutParams2.leftMargin) - this.mDividerWidth;
                } else {
                    i = virtualChildAt2.getRight() + layoutParams2.rightMargin;
                }
            } else if (isLayoutRtl) {
                i = getPaddingLeft();
            } else {
                i = (getWidth() - getPaddingRight()) - this.mDividerWidth;
            }
            drawVerticalDivider(canvas, i);
        }
    }

    /* access modifiers changed from: 0000 */
    public void drawHorizontalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, i, (getWidth() - getPaddingRight()) - this.mDividerPadding, this.mDividerHeight + i);
        this.mDivider.draw(canvas);
    }

    /* access modifiers changed from: 0000 */
    public void drawVerticalDivider(Canvas canvas, int i) {
        this.mDivider.setBounds(i, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + i, (getHeight() - getPaddingBottom()) - this.mDividerPadding);
        this.mDivider.draw(canvas);
    }

    public boolean isBaselineAligned() {
        return this.mBaselineAligned;
    }

    public void setBaselineAligned(boolean z) {
        this.mBaselineAligned = z;
    }

    public boolean isMeasureWithLargestChildEnabled() {
        return this.mUseLargestChild;
    }

    public void setMeasureWithLargestChildEnabled(boolean z) {
        this.mUseLargestChild = z;
    }

    public int getBaseline() {
        if (this.mBaselineAlignedChildIndex < 0) {
            return super.getBaseline();
        }
        int childCount = getChildCount();
        int i = this.mBaselineAlignedChildIndex;
        if (childCount > i) {
            View childAt = getChildAt(i);
            int baseline = childAt.getBaseline();
            if (baseline != -1) {
                int i2 = this.mBaselineChildTop;
                if (this.mOrientation == 1) {
                    int i3 = this.mGravity & 112;
                    if (i3 != 48) {
                        if (i3 == 16) {
                            i2 += ((((getBottom() - getTop()) - getPaddingTop()) - getPaddingBottom()) - this.mTotalLength) / 2;
                        } else if (i3 == 80) {
                            i2 = ((getBottom() - getTop()) - getPaddingBottom()) - this.mTotalLength;
                        }
                    }
                }
                return i2 + ((LayoutParams) childAt.getLayoutParams()).topMargin + baseline;
            } else if (this.mBaselineAlignedChildIndex == 0) {
                return -1;
            } else {
                throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline.");
            }
        } else {
            throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds.");
        }
    }

    public int getBaselineAlignedChildIndex() {
        return this.mBaselineAlignedChildIndex;
    }

    public void setBaselineAlignedChildIndex(int i) {
        if (i < 0 || i >= getChildCount()) {
            StringBuilder sb = new StringBuilder();
            sb.append("base aligned child index out of range (0, ");
            sb.append(getChildCount());
            sb.append(")");
            throw new IllegalArgumentException(sb.toString());
        }
        this.mBaselineAlignedChildIndex = i;
    }

    /* access modifiers changed from: 0000 */
    public View getVirtualChildAt(int i) {
        return getChildAt(i);
    }

    /* access modifiers changed from: 0000 */
    public int getVirtualChildCount() {
        return getChildCount();
    }

    public float getWeightSum() {
        return this.mWeightSum;
    }

    public void setWeightSum(float f) {
        this.mWeightSum = Math.max(BitmapDescriptorFactory.HUE_RED, f);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        if (this.mOrientation == 1) {
            measureVertical(i, i2);
        } else {
            measureHorizontal(i, i2);
        }
    }

    /* access modifiers changed from: protected */
    @RestrictTo
    public boolean hasDividerBeforeChildAt(int i) {
        boolean z = false;
        if (i == 0) {
            if ((this.mShowDividers & 1) != 0) {
                z = true;
            }
            return z;
        } else if (i == getChildCount()) {
            if ((this.mShowDividers & 4) != 0) {
                z = true;
            }
            return z;
        } else if ((this.mShowDividers & 2) == 0) {
            return false;
        } else {
            int i2 = i - 1;
            while (true) {
                if (i2 < 0) {
                    break;
                } else if (getChildAt(i2).getVisibility() != 8) {
                    z = true;
                    break;
                } else {
                    i2--;
                }
            }
            return z;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:140:0x0333  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureVertical(int r34, int r35) {
        /*
            r33 = this;
            r7 = r33
            r8 = r34
            r9 = r35
            r10 = 0
            r7.mTotalLength = r10
            int r11 = r33.getVirtualChildCount()
            int r12 = android.view.View.MeasureSpec.getMode(r34)
            int r13 = android.view.View.MeasureSpec.getMode(r35)
            int r14 = r7.mBaselineAlignedChildIndex
            boolean r15 = r7.mUseLargestChild
            r16 = 0
            r17 = 1
            r0 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r18 = 0
            r19 = 1
            r20 = 0
        L_0x002a:
            r10 = 8
            r22 = r4
            if (r6 >= r11) goto L_0x01a0
            android.view.View r4 = r7.getVirtualChildAt(r6)
            if (r4 != 0) goto L_0x0047
            int r4 = r7.mTotalLength
            int r10 = r7.measureNullChild(r6)
            int r4 = r4 + r10
            r7.mTotalLength = r4
            r23 = r11
            r4 = r22
            r22 = r13
            goto L_0x0194
        L_0x0047:
            r24 = r1
            int r1 = r4.getVisibility()
            if (r1 != r10) goto L_0x005e
            int r1 = r7.getChildrenSkipCount(r4, r6)
            int r6 = r6 + r1
            r23 = r11
            r4 = r22
            r1 = r24
            r22 = r13
            goto L_0x0194
        L_0x005e:
            boolean r1 = r7.hasDividerBeforeChildAt(r6)
            if (r1 == 0) goto L_0x006b
            int r1 = r7.mTotalLength
            int r10 = r7.mDividerHeight
            int r1 = r1 + r10
            r7.mTotalLength = r1
        L_0x006b:
            android.view.ViewGroup$LayoutParams r1 = r4.getLayoutParams()
            r10 = r1
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r10 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r10
            float r1 = r10.weight
            float r25 = r0 + r1
            r1 = 1073741824(0x40000000, float:2.0)
            if (r13 != r1) goto L_0x00a9
            int r0 = r10.height
            if (r0 != 0) goto L_0x00a9
            float r0 = r10.weight
            int r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r0 <= 0) goto L_0x00a9
            int r0 = r7.mTotalLength
            int r1 = r10.topMargin
            int r1 = r1 + r0
            r26 = r2
            int r2 = r10.bottomMargin
            int r1 = r1 + r2
            int r0 = java.lang.Math.max(r0, r1)
            r7.mTotalLength = r0
            r0 = r3
            r3 = r4
            r31 = r5
            r23 = r11
            r8 = r24
            r30 = r26
            r18 = 1
            r11 = r6
            r32 = r22
            r22 = r13
            r13 = r32
            goto L_0x011b
        L_0x00a9:
            r26 = r2
            int r0 = r10.height
            if (r0 != 0) goto L_0x00ba
            float r0 = r10.weight
            int r0 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r0 <= 0) goto L_0x00ba
            r0 = -2
            r10.height = r0
            r2 = 0
            goto L_0x00bc
        L_0x00ba:
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x00bc:
            r27 = 0
            int r0 = (r25 > r16 ? 1 : (r25 == r16 ? 0 : -1))
            if (r0 != 0) goto L_0x00c7
            int r0 = r7.mTotalLength
            r28 = r0
            goto L_0x00c9
        L_0x00c7:
            r28 = 0
        L_0x00c9:
            r0 = r33
            r8 = r24
            r23 = 1073741824(0x40000000, float:2.0)
            r1 = r4
            r29 = r2
            r30 = r26
            r2 = r6
            r9 = r3
            r3 = r34
            r24 = r4
            r23 = r11
            r11 = 1073741824(0x40000000, float:2.0)
            r32 = r22
            r22 = r13
            r13 = r32
            r4 = r27
            r31 = r5
            r5 = r35
            r11 = r6
            r6 = r28
            r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6)
            r0 = r29
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r0 == r1) goto L_0x00f8
            r10.height = r0
        L_0x00f8:
            int r0 = r24.getMeasuredHeight()
            int r1 = r7.mTotalLength
            int r2 = r1 + r0
            int r3 = r10.topMargin
            int r2 = r2 + r3
            int r3 = r10.bottomMargin
            int r2 = r2 + r3
            r3 = r24
            int r4 = r7.getNextLocationOffset(r3)
            int r2 = r2 + r4
            int r1 = java.lang.Math.max(r1, r2)
            r7.mTotalLength = r1
            if (r15 == 0) goto L_0x011a
            int r0 = java.lang.Math.max(r0, r9)
            goto L_0x011b
        L_0x011a:
            r0 = r9
        L_0x011b:
            if (r14 < 0) goto L_0x0125
            int r6 = r11 + 1
            if (r14 != r6) goto L_0x0125
            int r1 = r7.mTotalLength
            r7.mBaselineChildTop = r1
        L_0x0125:
            if (r11 >= r14) goto L_0x0136
            float r1 = r10.weight
            int r1 = (r1 > r16 ? 1 : (r1 == r16 ? 0 : -1))
            if (r1 > 0) goto L_0x012e
            goto L_0x0136
        L_0x012e:
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            java.lang.String r1 = "A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex."
            r0.<init>(r1)
            throw r0
        L_0x0136:
            r1 = 1073741824(0x40000000, float:2.0)
            if (r12 == r1) goto L_0x0143
            int r1 = r10.width
            r2 = -1
            if (r1 != r2) goto L_0x0143
            r1 = 1
            r20 = 1
            goto L_0x0144
        L_0x0143:
            r1 = 0
        L_0x0144:
            int r2 = r10.leftMargin
            int r4 = r10.rightMargin
            int r2 = r2 + r4
            int r4 = r3.getMeasuredWidth()
            int r4 = r4 + r2
            r5 = r30
            int r5 = java.lang.Math.max(r5, r4)
            int r6 = r3.getMeasuredState()
            int r6 = android.view.View.combineMeasuredStates(r8, r6)
            if (r19 == 0) goto L_0x0165
            int r8 = r10.width
            r9 = -1
            if (r8 != r9) goto L_0x0165
            r8 = 1
            goto L_0x0166
        L_0x0165:
            r8 = 0
        L_0x0166:
            float r9 = r10.weight
            int r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r9 <= 0) goto L_0x0178
            if (r1 == 0) goto L_0x016f
            goto L_0x0170
        L_0x016f:
            r2 = r4
        L_0x0170:
            int r4 = java.lang.Math.max(r13, r2)
            r13 = r4
            r1 = r31
            goto L_0x0182
        L_0x0178:
            if (r1 == 0) goto L_0x017b
            goto L_0x017c
        L_0x017b:
            r2 = r4
        L_0x017c:
            r1 = r31
            int r1 = java.lang.Math.max(r1, r2)
        L_0x0182:
            int r2 = r7.getChildrenSkipCount(r3, r11)
            int r2 = r2 + r11
            r3 = r0
            r19 = r8
            r4 = r13
            r0 = r25
            r32 = r5
            r5 = r1
            r1 = r6
            r6 = r2
            r2 = r32
        L_0x0194:
            int r6 = r6 + 1
            r13 = r22
            r11 = r23
            r8 = r34
            r9 = r35
            goto L_0x002a
        L_0x01a0:
            r8 = r1
            r9 = r3
            r1 = r5
            r23 = r11
            r5 = r2
            r32 = r22
            r22 = r13
            r13 = r32
            int r2 = r7.mTotalLength
            if (r2 <= 0) goto L_0x01c0
            r2 = r23
            boolean r3 = r7.hasDividerBeforeChildAt(r2)
            if (r3 == 0) goto L_0x01c2
            int r3 = r7.mTotalLength
            int r4 = r7.mDividerHeight
            int r3 = r3 + r4
            r7.mTotalLength = r3
            goto L_0x01c2
        L_0x01c0:
            r2 = r23
        L_0x01c2:
            if (r15 == 0) goto L_0x0211
            r3 = r22
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r3 == r4) goto L_0x01cc
            if (r3 != 0) goto L_0x0213
        L_0x01cc:
            r4 = 0
            r7.mTotalLength = r4
            r4 = 0
        L_0x01d0:
            if (r4 >= r2) goto L_0x0213
            android.view.View r6 = r7.getVirtualChildAt(r4)
            if (r6 != 0) goto L_0x01e2
            int r6 = r7.mTotalLength
            int r11 = r7.measureNullChild(r4)
            int r6 = r6 + r11
            r7.mTotalLength = r6
            goto L_0x020c
        L_0x01e2:
            int r11 = r6.getVisibility()
            if (r11 != r10) goto L_0x01ee
            int r6 = r7.getChildrenSkipCount(r6, r4)
            int r4 = r4 + r6
            goto L_0x020c
        L_0x01ee:
            android.view.ViewGroup$LayoutParams r11 = r6.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r11 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r11
            int r14 = r7.mTotalLength
            int r21 = r14 + r9
            int r10 = r11.topMargin
            int r21 = r21 + r10
            int r10 = r11.bottomMargin
            int r21 = r21 + r10
            int r6 = r7.getNextLocationOffset(r6)
            int r6 = r21 + r6
            int r6 = java.lang.Math.max(r14, r6)
            r7.mTotalLength = r6
        L_0x020c:
            int r4 = r4 + 1
            r10 = 8
            goto L_0x01d0
        L_0x0211:
            r3 = r22
        L_0x0213:
            int r4 = r7.mTotalLength
            int r6 = r33.getPaddingTop()
            int r10 = r33.getPaddingBottom()
            int r6 = r6 + r10
            int r4 = r4 + r6
            r7.mTotalLength = r4
            int r4 = r7.mTotalLength
            int r6 = r33.getSuggestedMinimumHeight()
            int r4 = java.lang.Math.max(r4, r6)
            r10 = r9
            r6 = r35
            r9 = 0
            int r4 = android.view.View.resolveSizeAndState(r4, r6, r9)
            r9 = 16777215(0xffffff, float:2.3509886E-38)
            r9 = r9 & r4
            int r11 = r7.mTotalLength
            int r9 = r9 - r11
            if (r18 != 0) goto L_0x0284
            if (r9 == 0) goto L_0x0243
            int r11 = (r0 > r16 ? 1 : (r0 == r16 ? 0 : -1))
            if (r11 <= 0) goto L_0x0243
            goto L_0x0284
        L_0x0243:
            int r0 = java.lang.Math.max(r1, r13)
            if (r15 == 0) goto L_0x027f
            r1 = 1073741824(0x40000000, float:2.0)
            if (r3 == r1) goto L_0x027f
            r1 = 0
        L_0x024e:
            if (r1 >= r2) goto L_0x027f
            android.view.View r3 = r7.getVirtualChildAt(r1)
            if (r3 == 0) goto L_0x027c
            int r9 = r3.getVisibility()
            r11 = 8
            if (r9 != r11) goto L_0x025f
            goto L_0x027c
        L_0x025f:
            android.view.ViewGroup$LayoutParams r9 = r3.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r9 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r9
            float r9 = r9.weight
            int r9 = (r9 > r16 ? 1 : (r9 == r16 ? 0 : -1))
            if (r9 <= 0) goto L_0x027c
            int r9 = r3.getMeasuredWidth()
            r11 = 1073741824(0x40000000, float:2.0)
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r11)
            int r13 = android.view.View.MeasureSpec.makeMeasureSpec(r10, r11)
            r3.measure(r9, r13)
        L_0x027c:
            int r1 = r1 + 1
            goto L_0x024e
        L_0x027f:
            r1 = r8
            r11 = r34
            goto L_0x0377
        L_0x0284:
            float r10 = r7.mWeightSum
            int r11 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r11 <= 0) goto L_0x028b
            r0 = r10
        L_0x028b:
            r10 = 0
            r7.mTotalLength = r10
            r11 = r0
            r0 = 0
            r32 = r8
            r8 = r1
            r1 = r32
        L_0x0295:
            if (r0 >= r2) goto L_0x0366
            android.view.View r13 = r7.getVirtualChildAt(r0)
            int r14 = r13.getVisibility()
            r15 = 8
            if (r14 != r15) goto L_0x02a9
            r21 = r11
            r11 = r34
            goto L_0x035f
        L_0x02a9:
            android.view.ViewGroup$LayoutParams r14 = r13.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r14 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r14
            float r10 = r14.weight
            int r18 = (r10 > r16 ? 1 : (r10 == r16 ? 0 : -1))
            if (r18 <= 0) goto L_0x030a
            float r15 = (float) r9
            float r15 = r15 * r10
            float r15 = r15 / r11
            int r15 = (int) r15
            float r11 = r11 - r10
            int r9 = r9 - r15
            int r10 = r33.getPaddingLeft()
            int r18 = r33.getPaddingRight()
            int r10 = r10 + r18
            r18 = r9
            int r9 = r14.leftMargin
            int r10 = r10 + r9
            int r9 = r14.rightMargin
            int r10 = r10 + r9
            int r9 = r14.width
            r21 = r11
            r11 = r34
            int r9 = getChildMeasureSpec(r11, r10, r9)
            int r10 = r14.height
            if (r10 != 0) goto L_0x02ed
            r10 = 1073741824(0x40000000, float:2.0)
            if (r3 == r10) goto L_0x02e1
            goto L_0x02ef
        L_0x02e1:
            if (r15 <= 0) goto L_0x02e4
            goto L_0x02e5
        L_0x02e4:
            r15 = 0
        L_0x02e5:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10)
            r13.measure(r9, r15)
            goto L_0x02ff
        L_0x02ed:
            r10 = 1073741824(0x40000000, float:2.0)
        L_0x02ef:
            int r23 = r13.getMeasuredHeight()
            int r15 = r23 + r15
            if (r15 >= 0) goto L_0x02f8
            r15 = 0
        L_0x02f8:
            int r15 = android.view.View.MeasureSpec.makeMeasureSpec(r15, r10)
            r13.measure(r9, r15)
        L_0x02ff:
            int r9 = r13.getMeasuredState()
            r9 = r9 & -256(0xffffffffffffff00, float:NaN)
            int r1 = android.view.View.combineMeasuredStates(r1, r9)
            goto L_0x0311
        L_0x030a:
            r10 = r11
            r11 = r34
            r18 = r9
            r21 = r10
        L_0x0311:
            int r9 = r14.leftMargin
            int r10 = r14.rightMargin
            int r9 = r9 + r10
            int r10 = r13.getMeasuredWidth()
            int r10 = r10 + r9
            int r5 = java.lang.Math.max(r5, r10)
            r15 = 1073741824(0x40000000, float:2.0)
            if (r12 == r15) goto L_0x032c
            int r15 = r14.width
            r23 = r1
            r1 = -1
            if (r15 != r1) goto L_0x032f
            r15 = 1
            goto L_0x0330
        L_0x032c:
            r23 = r1
            r1 = -1
        L_0x032f:
            r15 = 0
        L_0x0330:
            if (r15 == 0) goto L_0x0333
            goto L_0x0334
        L_0x0333:
            r9 = r10
        L_0x0334:
            int r8 = java.lang.Math.max(r8, r9)
            if (r19 == 0) goto L_0x0340
            int r9 = r14.width
            if (r9 != r1) goto L_0x0340
            r9 = 1
            goto L_0x0341
        L_0x0340:
            r9 = 0
        L_0x0341:
            int r10 = r7.mTotalLength
            int r15 = r13.getMeasuredHeight()
            int r15 = r15 + r10
            int r1 = r14.topMargin
            int r15 = r15 + r1
            int r1 = r14.bottomMargin
            int r15 = r15 + r1
            int r1 = r7.getNextLocationOffset(r13)
            int r15 = r15 + r1
            int r1 = java.lang.Math.max(r10, r15)
            r7.mTotalLength = r1
            r19 = r9
            r9 = r18
            r1 = r23
        L_0x035f:
            int r0 = r0 + 1
            r11 = r21
            r10 = 0
            goto L_0x0295
        L_0x0366:
            r11 = r34
            int r0 = r7.mTotalLength
            int r3 = r33.getPaddingTop()
            int r9 = r33.getPaddingBottom()
            int r3 = r3 + r9
            int r0 = r0 + r3
            r7.mTotalLength = r0
            r0 = r8
        L_0x0377:
            if (r19 != 0) goto L_0x037e
            r3 = 1073741824(0x40000000, float:2.0)
            if (r12 == r3) goto L_0x037e
            goto L_0x037f
        L_0x037e:
            r0 = r5
        L_0x037f:
            int r3 = r33.getPaddingLeft()
            int r5 = r33.getPaddingRight()
            int r3 = r3 + r5
            int r0 = r0 + r3
            int r3 = r33.getSuggestedMinimumWidth()
            int r0 = java.lang.Math.max(r0, r3)
            int r0 = android.view.View.resolveSizeAndState(r0, r11, r1)
            r7.setMeasuredDimension(r0, r4)
            if (r20 == 0) goto L_0x039d
            r7.forceUniformWidth(r2, r6)
        L_0x039d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureVertical(int, int):void");
    }

    private void forceUniformWidth(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    int i4 = layoutParams.height;
                    layoutParams.height = virtualChildAt.getMeasuredHeight();
                    measureChildWithMargins(virtualChildAt, makeMeasureSpec, 0, i2, 0);
                    layoutParams.height = i4;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:185:0x046b  */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0499  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0173  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x0193  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x01c5  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x01cd  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01db  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x01e8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void measureHorizontal(int r39, int r40) {
        /*
            r38 = this;
            r7 = r38
            r8 = r39
            r9 = r40
            r10 = 0
            r7.mTotalLength = r10
            int r11 = r38.getVirtualChildCount()
            int r12 = android.view.View.MeasureSpec.getMode(r39)
            int r13 = android.view.View.MeasureSpec.getMode(r40)
            int[] r0 = r7.mMaxAscent
            r14 = 4
            if (r0 == 0) goto L_0x001e
            int[] r0 = r7.mMaxDescent
            if (r0 != 0) goto L_0x0026
        L_0x001e:
            int[] r0 = new int[r14]
            r7.mMaxAscent = r0
            int[] r0 = new int[r14]
            r7.mMaxDescent = r0
        L_0x0026:
            int[] r15 = r7.mMaxAscent
            int[] r6 = r7.mMaxDescent
            r16 = 3
            r5 = -1
            r15[r16] = r5
            r17 = 2
            r15[r17] = r5
            r18 = 1
            r15[r18] = r5
            r15[r10] = r5
            r6[r16] = r5
            r6[r17] = r5
            r6[r18] = r5
            r6[r10] = r5
            boolean r4 = r7.mBaselineAligned
            boolean r3 = r7.mUseLargestChild
            r2 = 1073741824(0x40000000, float:2.0)
            if (r12 != r2) goto L_0x004c
            r19 = 1
            goto L_0x004e
        L_0x004c:
            r19 = 0
        L_0x004e:
            r20 = 0
            r0 = 0
            r1 = 0
            r14 = 0
            r21 = 0
            r22 = 0
            r23 = 0
            r24 = 0
            r26 = 0
            r27 = 1
            r28 = 0
        L_0x0061:
            r29 = r6
            r5 = 8
            if (r1 >= r11) goto L_0x0215
            android.view.View r6 = r7.getVirtualChildAt(r1)
            if (r6 != 0) goto L_0x007c
            int r5 = r7.mTotalLength
            int r6 = r7.measureNullChild(r1)
            int r5 = r5 + r6
            r7.mTotalLength = r5
            r33 = r3
            r37 = r4
            goto L_0x0205
        L_0x007c:
            int r10 = r6.getVisibility()
            if (r10 != r5) goto L_0x008d
            int r5 = r7.getChildrenSkipCount(r6, r1)
            int r1 = r1 + r5
            r33 = r3
            r37 = r4
            goto L_0x0205
        L_0x008d:
            boolean r5 = r7.hasDividerBeforeChildAt(r1)
            if (r5 == 0) goto L_0x009a
            int r5 = r7.mTotalLength
            int r10 = r7.mDividerWidth
            int r5 = r5 + r10
            r7.mTotalLength = r5
        L_0x009a:
            android.view.ViewGroup$LayoutParams r5 = r6.getLayoutParams()
            r10 = r5
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r10 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r10
            float r5 = r10.weight
            float r32 = r0 + r5
            if (r12 != r2) goto L_0x00f0
            int r0 = r10.width
            if (r0 != 0) goto L_0x00f0
            float r0 = r10.weight
            int r0 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r0 <= 0) goto L_0x00f0
            if (r19 == 0) goto L_0x00be
            int r0 = r7.mTotalLength
            int r5 = r10.leftMargin
            int r2 = r10.rightMargin
            int r5 = r5 + r2
            int r0 = r0 + r5
            r7.mTotalLength = r0
            goto L_0x00cc
        L_0x00be:
            int r0 = r7.mTotalLength
            int r2 = r10.leftMargin
            int r2 = r2 + r0
            int r5 = r10.rightMargin
            int r2 = r2 + r5
            int r0 = java.lang.Math.max(r0, r2)
            r7.mTotalLength = r0
        L_0x00cc:
            if (r4 == 0) goto L_0x00e1
            r0 = 0
            int r2 = android.view.View.MeasureSpec.makeMeasureSpec(r0, r0)
            r6.measure(r2, r2)
            r35 = r1
            r33 = r3
            r37 = r4
            r3 = r6
            r31 = -2
            goto L_0x016f
        L_0x00e1:
            r35 = r1
            r33 = r3
            r37 = r4
            r3 = r6
            r1 = 1073741824(0x40000000, float:2.0)
            r24 = 1
            r31 = -2
            goto L_0x0171
        L_0x00f0:
            int r0 = r10.width
            if (r0 != 0) goto L_0x00ff
            float r0 = r10.weight
            int r0 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r0 <= 0) goto L_0x00ff
            r5 = -2
            r10.width = r5
            r2 = 0
            goto L_0x0102
        L_0x00ff:
            r5 = -2
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
        L_0x0102:
            int r0 = (r32 > r20 ? 1 : (r32 == r20 ? 0 : -1))
            if (r0 != 0) goto L_0x010b
            int r0 = r7.mTotalLength
            r30 = r0
            goto L_0x010d
        L_0x010b:
            r30 = 0
        L_0x010d:
            r34 = 0
            r0 = r38
            r35 = r1
            r1 = r6
            r36 = r2
            r2 = r35
            r33 = r3
            r3 = r39
            r37 = r4
            r4 = r30
            r9 = -1
            r30 = -2
            r5 = r40
            r30 = r6
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r31 = -2
            r6 = r34
            r0.measureChildBeforeLayout(r1, r2, r3, r4, r5, r6)
            r0 = r36
            if (r0 == r9) goto L_0x0136
            r10.width = r0
        L_0x0136:
            int r0 = r30.getMeasuredWidth()
            if (r19 == 0) goto L_0x014f
            int r1 = r7.mTotalLength
            int r2 = r10.leftMargin
            int r2 = r2 + r0
            int r3 = r10.rightMargin
            int r2 = r2 + r3
            r3 = r30
            int r4 = r7.getNextLocationOffset(r3)
            int r2 = r2 + r4
            int r1 = r1 + r2
            r7.mTotalLength = r1
            goto L_0x0166
        L_0x014f:
            r3 = r30
            int r1 = r7.mTotalLength
            int r2 = r1 + r0
            int r4 = r10.leftMargin
            int r2 = r2 + r4
            int r4 = r10.rightMargin
            int r2 = r2 + r4
            int r4 = r7.getNextLocationOffset(r3)
            int r2 = r2 + r4
            int r1 = java.lang.Math.max(r1, r2)
            r7.mTotalLength = r1
        L_0x0166:
            if (r33 == 0) goto L_0x016f
            int r14 = java.lang.Math.max(r0, r14)
            r1 = 1073741824(0x40000000, float:2.0)
            goto L_0x0171
        L_0x016f:
            r1 = 1073741824(0x40000000, float:2.0)
        L_0x0171:
            if (r13 == r1) goto L_0x017c
            int r0 = r10.height
            r2 = -1
            if (r0 != r2) goto L_0x017c
            r0 = 1
            r28 = 1
            goto L_0x017d
        L_0x017c:
            r0 = 0
        L_0x017d:
            int r2 = r10.topMargin
            int r4 = r10.bottomMargin
            int r2 = r2 + r4
            int r4 = r3.getMeasuredHeight()
            int r4 = r4 + r2
            int r5 = r3.getMeasuredState()
            r6 = r26
            int r5 = android.view.View.combineMeasuredStates(r6, r5)
            if (r37 == 0) goto L_0x01c5
            int r6 = r3.getBaseline()
            r9 = -1
            if (r6 == r9) goto L_0x01c2
            int r9 = r10.gravity
            if (r9 >= 0) goto L_0x01a1
            int r9 = r7.mGravity
            goto L_0x01a3
        L_0x01a1:
            int r9 = r10.gravity
        L_0x01a3:
            r9 = r9 & 112(0x70, float:1.57E-43)
            r25 = 4
            int r9 = r9 >> 4
            r9 = r9 & -2
            int r9 = r9 >> 1
            r1 = r15[r9]
            int r1 = java.lang.Math.max(r1, r6)
            r15[r9] = r1
            r1 = r29[r9]
            int r6 = r4 - r6
            int r1 = java.lang.Math.max(r1, r6)
            r29[r9] = r1
            r1 = r21
            goto L_0x01c7
        L_0x01c2:
            r1 = r21
            goto L_0x01c7
        L_0x01c5:
            r1 = r21
        L_0x01c7:
            int r1 = java.lang.Math.max(r1, r4)
            if (r27 == 0) goto L_0x01d4
            int r6 = r10.height
            r9 = -1
            if (r6 != r9) goto L_0x01d4
            r6 = 1
            goto L_0x01d5
        L_0x01d4:
            r6 = 0
        L_0x01d5:
            float r9 = r10.weight
            int r9 = (r9 > r20 ? 1 : (r9 == r20 ? 0 : -1))
            if (r9 <= 0) goto L_0x01e8
            if (r0 == 0) goto L_0x01de
            goto L_0x01df
        L_0x01de:
            r2 = r4
        L_0x01df:
            r10 = r23
            int r23 = java.lang.Math.max(r10, r2)
            r10 = r35
            goto L_0x01f7
        L_0x01e8:
            r10 = r23
            if (r0 == 0) goto L_0x01ed
            r4 = r2
        L_0x01ed:
            r2 = r22
            int r22 = java.lang.Math.max(r2, r4)
            r23 = r10
            r10 = r35
        L_0x01f7:
            int r0 = r7.getChildrenSkipCount(r3, r10)
            int r0 = r0 + r10
            r21 = r1
            r26 = r5
            r27 = r6
            r1 = r0
            r0 = r32
        L_0x0205:
            int r1 = r1 + 1
            r6 = r29
            r3 = r33
            r4 = r37
            r2 = 1073741824(0x40000000, float:2.0)
            r5 = -1
            r9 = r40
            r10 = 0
            goto L_0x0061
        L_0x0215:
            r33 = r3
            r37 = r4
            r1 = r21
            r2 = r22
            r10 = r23
            r6 = r26
            r9 = -2147483648(0xffffffff80000000, float:-0.0)
            r31 = -2
            int r3 = r7.mTotalLength
            if (r3 <= 0) goto L_0x0236
            boolean r3 = r7.hasDividerBeforeChildAt(r11)
            if (r3 == 0) goto L_0x0236
            int r3 = r7.mTotalLength
            int r4 = r7.mDividerWidth
            int r3 = r3 + r4
            r7.mTotalLength = r3
        L_0x0236:
            r3 = r15[r18]
            r4 = -1
            if (r3 != r4) goto L_0x024c
            r3 = 0
            r5 = r15[r3]
            if (r5 != r4) goto L_0x024c
            r3 = r15[r17]
            if (r3 != r4) goto L_0x024c
            r3 = r15[r16]
            if (r3 == r4) goto L_0x0249
            goto L_0x024c
        L_0x0249:
            r23 = r6
            goto L_0x027d
        L_0x024c:
            r3 = r15[r16]
            r4 = 0
            r5 = r15[r4]
            r9 = r15[r18]
            r4 = r15[r17]
            int r4 = java.lang.Math.max(r9, r4)
            int r4 = java.lang.Math.max(r5, r4)
            int r3 = java.lang.Math.max(r3, r4)
            r4 = r29[r16]
            r5 = 0
            r9 = r29[r5]
            r5 = r29[r18]
            r23 = r6
            r6 = r29[r17]
            int r5 = java.lang.Math.max(r5, r6)
            int r5 = java.lang.Math.max(r9, r5)
            int r4 = java.lang.Math.max(r4, r5)
            int r3 = r3 + r4
            int r1 = java.lang.Math.max(r1, r3)
        L_0x027d:
            if (r33 == 0) goto L_0x02e7
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r12 == r3) goto L_0x0285
            if (r12 != 0) goto L_0x02e7
        L_0x0285:
            r3 = 0
            r7.mTotalLength = r3
            r3 = 0
        L_0x0289:
            if (r3 >= r11) goto L_0x02e4
            android.view.View r4 = r7.getVirtualChildAt(r3)
            if (r4 != 0) goto L_0x029d
            int r4 = r7.mTotalLength
            int r5 = r7.measureNullChild(r3)
            int r4 = r4 + r5
            r7.mTotalLength = r4
            r22 = r1
            goto L_0x02df
        L_0x029d:
            int r5 = r4.getVisibility()
            r6 = 8
            if (r5 != r6) goto L_0x02ad
            int r4 = r7.getChildrenSkipCount(r4, r3)
            int r3 = r3 + r4
            r22 = r1
            goto L_0x02df
        L_0x02ad:
            android.view.ViewGroup$LayoutParams r5 = r4.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5
            if (r19 == 0) goto L_0x02c8
            int r6 = r7.mTotalLength
            int r9 = r5.leftMargin
            int r9 = r9 + r14
            int r5 = r5.rightMargin
            int r9 = r9 + r5
            int r4 = r7.getNextLocationOffset(r4)
            int r9 = r9 + r4
            int r6 = r6 + r9
            r7.mTotalLength = r6
            r22 = r1
            goto L_0x02df
        L_0x02c8:
            int r6 = r7.mTotalLength
            int r9 = r6 + r14
            r22 = r1
            int r1 = r5.leftMargin
            int r9 = r9 + r1
            int r1 = r5.rightMargin
            int r9 = r9 + r1
            int r1 = r7.getNextLocationOffset(r4)
            int r9 = r9 + r1
            int r1 = java.lang.Math.max(r6, r9)
            r7.mTotalLength = r1
        L_0x02df:
            int r3 = r3 + 1
            r1 = r22
            goto L_0x0289
        L_0x02e4:
            r22 = r1
            goto L_0x02e9
        L_0x02e7:
            r22 = r1
        L_0x02e9:
            int r1 = r7.mTotalLength
            int r3 = r38.getPaddingLeft()
            int r4 = r38.getPaddingRight()
            int r3 = r3 + r4
            int r1 = r1 + r3
            r7.mTotalLength = r1
            int r1 = r7.mTotalLength
            int r3 = r38.getSuggestedMinimumWidth()
            int r1 = java.lang.Math.max(r1, r3)
            r3 = 0
            int r1 = android.view.View.resolveSizeAndState(r1, r8, r3)
            r3 = 16777215(0xffffff, float:2.3509886E-38)
            r3 = r3 & r1
            int r4 = r7.mTotalLength
            int r3 = r3 - r4
            if (r24 != 0) goto L_0x035a
            if (r3 == 0) goto L_0x0316
            int r5 = (r0 > r20 ? 1 : (r0 == r20 ? 0 : -1))
            if (r5 <= 0) goto L_0x0316
            goto L_0x035a
        L_0x0316:
            int r0 = java.lang.Math.max(r2, r10)
            if (r33 == 0) goto L_0x0352
            r2 = 1073741824(0x40000000, float:2.0)
            if (r12 == r2) goto L_0x0352
            r2 = 0
        L_0x0321:
            if (r2 >= r11) goto L_0x0352
            android.view.View r3 = r7.getVirtualChildAt(r2)
            if (r3 == 0) goto L_0x034f
            int r5 = r3.getVisibility()
            r6 = 8
            if (r5 != r6) goto L_0x0332
            goto L_0x034f
        L_0x0332:
            android.view.ViewGroup$LayoutParams r5 = r3.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5
            float r5 = r5.weight
            int r5 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r5 <= 0) goto L_0x034f
            r5 = 1073741824(0x40000000, float:2.0)
            int r6 = android.view.View.MeasureSpec.makeMeasureSpec(r14, r5)
            int r9 = r3.getMeasuredHeight()
            int r9 = android.view.View.MeasureSpec.makeMeasureSpec(r9, r5)
            r3.measure(r6, r9)
        L_0x034f:
            int r2 = r2 + 1
            goto L_0x0321
        L_0x0352:
            r26 = r11
            r2 = r22
            r3 = r40
            goto L_0x050b
        L_0x035a:
            float r5 = r7.mWeightSum
            int r6 = (r5 > r20 ? 1 : (r5 == r20 ? 0 : -1))
            if (r6 <= 0) goto L_0x0361
            r0 = r5
        L_0x0361:
            r5 = -1
            r15[r16] = r5
            r15[r17] = r5
            r15[r18] = r5
            r6 = 0
            r15[r6] = r5
            r29[r16] = r5
            r29[r17] = r5
            r29[r18] = r5
            r29[r6] = r5
            r7.mTotalLength = r6
            r10 = r2
            r9 = r23
            r6 = -1
            r2 = r0
            r0 = 0
        L_0x037b:
            if (r0 >= r11) goto L_0x04b2
            android.view.View r14 = r7.getVirtualChildAt(r0)
            if (r14 == 0) goto L_0x04a1
            int r5 = r14.getVisibility()
            r4 = 8
            if (r5 != r4) goto L_0x0394
            r4 = r3
            r26 = r11
            r3 = r40
            r24 = 4
            goto L_0x04a8
        L_0x0394:
            android.view.ViewGroup$LayoutParams r5 = r14.getLayoutParams()
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r5 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r5
            float r4 = r5.weight
            int r23 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1))
            if (r23 <= 0) goto L_0x03fd
            float r8 = (float) r3
            float r8 = r8 * r4
            float r8 = r8 / r2
            int r8 = (int) r8
            float r2 = r2 - r4
            int r3 = r3 - r8
            int r4 = r38.getPaddingTop()
            int r23 = r38.getPaddingBottom()
            int r4 = r4 + r23
            r23 = r2
            int r2 = r5.topMargin
            int r4 = r4 + r2
            int r2 = r5.bottomMargin
            int r4 = r4 + r2
            int r2 = r5.height
            r24 = r3
            r26 = r11
            r3 = r40
            r11 = -1
            int r2 = getChildMeasureSpec(r3, r4, r2)
            int r4 = r5.width
            if (r4 != 0) goto L_0x03db
            r4 = 1073741824(0x40000000, float:2.0)
            if (r12 == r4) goto L_0x03cf
            goto L_0x03dd
        L_0x03cf:
            if (r8 <= 0) goto L_0x03d2
            goto L_0x03d3
        L_0x03d2:
            r8 = 0
        L_0x03d3:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r4)
            r14.measure(r8, r2)
            goto L_0x03ed
        L_0x03db:
            r4 = 1073741824(0x40000000, float:2.0)
        L_0x03dd:
            int r30 = r14.getMeasuredWidth()
            int r8 = r30 + r8
            if (r8 >= 0) goto L_0x03e6
            r8 = 0
        L_0x03e6:
            int r8 = android.view.View.MeasureSpec.makeMeasureSpec(r8, r4)
            r14.measure(r8, r2)
        L_0x03ed:
            int r2 = r14.getMeasuredState()
            r4 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2 = r2 & r4
            int r9 = android.view.View.combineMeasuredStates(r9, r2)
            r2 = r23
            r4 = r24
            goto L_0x0403
        L_0x03fd:
            r4 = r3
            r26 = r11
            r3 = r40
            r11 = -1
        L_0x0403:
            if (r19 == 0) goto L_0x0422
            int r8 = r7.mTotalLength
            int r23 = r14.getMeasuredWidth()
            int r11 = r5.leftMargin
            int r23 = r23 + r11
            int r11 = r5.rightMargin
            int r23 = r23 + r11
            int r11 = r7.getNextLocationOffset(r14)
            int r23 = r23 + r11
            int r8 = r8 + r23
            r7.mTotalLength = r8
            r23 = r2
            r2 = 1073741824(0x40000000, float:2.0)
            goto L_0x043e
        L_0x0422:
            int r8 = r7.mTotalLength
            int r11 = r14.getMeasuredWidth()
            int r11 = r11 + r8
            r23 = r2
            int r2 = r5.leftMargin
            int r11 = r11 + r2
            int r2 = r5.rightMargin
            int r11 = r11 + r2
            int r2 = r7.getNextLocationOffset(r14)
            int r11 = r11 + r2
            int r2 = java.lang.Math.max(r8, r11)
            r7.mTotalLength = r2
            r2 = 1073741824(0x40000000, float:2.0)
        L_0x043e:
            if (r13 == r2) goto L_0x0447
            int r2 = r5.height
            r8 = -1
            if (r2 != r8) goto L_0x0447
            r2 = 1
            goto L_0x0448
        L_0x0447:
            r2 = 0
        L_0x0448:
            int r8 = r5.topMargin
            int r11 = r5.bottomMargin
            int r8 = r8 + r11
            int r11 = r14.getMeasuredHeight()
            int r11 = r11 + r8
            int r6 = java.lang.Math.max(r6, r11)
            if (r2 == 0) goto L_0x0459
            goto L_0x045a
        L_0x0459:
            r8 = r11
        L_0x045a:
            int r2 = java.lang.Math.max(r10, r8)
            if (r27 == 0) goto L_0x0467
            int r8 = r5.height
            r10 = -1
            if (r8 != r10) goto L_0x0468
            r8 = 1
            goto L_0x0469
        L_0x0467:
            r10 = -1
        L_0x0468:
            r8 = 0
        L_0x0469:
            if (r37 == 0) goto L_0x0499
            int r14 = r14.getBaseline()
            if (r14 == r10) goto L_0x0496
            int r10 = r5.gravity
            if (r10 >= 0) goto L_0x0478
            int r5 = r7.mGravity
            goto L_0x047a
        L_0x0478:
            int r5 = r5.gravity
        L_0x047a:
            r5 = r5 & 112(0x70, float:1.57E-43)
            r24 = 4
            int r5 = r5 >> 4
            r5 = r5 & -2
            int r5 = r5 >> 1
            r10 = r15[r5]
            int r10 = java.lang.Math.max(r10, r14)
            r15[r5] = r10
            r10 = r29[r5]
            int r11 = r11 - r14
            int r10 = java.lang.Math.max(r10, r11)
            r29[r5] = r10
            goto L_0x049b
        L_0x0496:
            r24 = 4
            goto L_0x049b
        L_0x0499:
            r24 = 4
        L_0x049b:
            r10 = r2
            r27 = r8
            r2 = r23
            goto L_0x04a8
        L_0x04a1:
            r4 = r3
            r26 = r11
            r3 = r40
            r24 = 4
        L_0x04a8:
            int r0 = r0 + 1
            r3 = r4
            r11 = r26
            r5 = -1
            r8 = r39
            goto L_0x037b
        L_0x04b2:
            r26 = r11
            r3 = r40
            int r0 = r7.mTotalLength
            int r2 = r38.getPaddingLeft()
            int r4 = r38.getPaddingRight()
            int r2 = r2 + r4
            int r0 = r0 + r2
            r7.mTotalLength = r0
            r0 = r15[r18]
            r2 = -1
            if (r0 != r2) goto L_0x04d9
            r0 = 0
            r4 = r15[r0]
            if (r4 != r2) goto L_0x04d9
            r0 = r15[r17]
            if (r0 != r2) goto L_0x04d9
            r0 = r15[r16]
            if (r0 == r2) goto L_0x04d7
            goto L_0x04d9
        L_0x04d7:
            r0 = r6
            goto L_0x0507
        L_0x04d9:
            r0 = r15[r16]
            r2 = 0
            r4 = r15[r2]
            r5 = r15[r18]
            r8 = r15[r17]
            int r5 = java.lang.Math.max(r5, r8)
            int r4 = java.lang.Math.max(r4, r5)
            int r0 = java.lang.Math.max(r0, r4)
            r4 = r29[r16]
            r2 = r29[r2]
            r5 = r29[r18]
            r8 = r29[r17]
            int r5 = java.lang.Math.max(r5, r8)
            int r2 = java.lang.Math.max(r2, r5)
            int r2 = java.lang.Math.max(r4, r2)
            int r0 = r0 + r2
            int r0 = java.lang.Math.max(r6, r0)
        L_0x0507:
            r2 = r0
            r23 = r9
            r0 = r10
        L_0x050b:
            if (r27 != 0) goto L_0x0512
            r4 = 1073741824(0x40000000, float:2.0)
            if (r13 == r4) goto L_0x0512
            goto L_0x0513
        L_0x0512:
            r0 = r2
        L_0x0513:
            int r2 = r38.getPaddingTop()
            int r4 = r38.getPaddingBottom()
            int r2 = r2 + r4
            int r0 = r0 + r2
            int r2 = r38.getSuggestedMinimumHeight()
            int r0 = java.lang.Math.max(r0, r2)
            r2 = -16777216(0xffffffffff000000, float:-1.7014118E38)
            r2 = r23 & r2
            r1 = r1 | r2
            int r2 = r23 << 16
            int r0 = android.view.View.resolveSizeAndState(r0, r3, r2)
            r7.setMeasuredDimension(r1, r0)
            if (r28 == 0) goto L_0x053c
            r1 = r26
            r0 = r39
            r7.forceUniformHeight(r1, r0)
        L_0x053c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.measureHorizontal(int, int):void");
    }

    private void forceUniformHeight(int i, int i2) {
        int makeMeasureSpec = MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
        for (int i3 = 0; i3 < i; i3++) {
            View virtualChildAt = getVirtualChildAt(i3);
            if (virtualChildAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                if (layoutParams.height == -1) {
                    int i4 = layoutParams.width;
                    layoutParams.width = virtualChildAt.getMeasuredWidth();
                    measureChildWithMargins(virtualChildAt, i2, 0, makeMeasureSpec, 0);
                    layoutParams.width = i4;
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void measureChildBeforeLayout(View view, int i, int i2, int i3, int i4, int i5) {
        measureChildWithMargins(view, i2, i3, i4, i5);
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mOrientation == 1) {
            layoutVertical(i, i2, i3, i4);
        } else {
            layoutHorizontal(i, i2, i3, i4);
        }
    }

    /* access modifiers changed from: 0000 */
    public void layoutVertical(int i, int i2, int i3, int i4) {
        int i5;
        int i6;
        int paddingLeft = getPaddingLeft();
        int i7 = i3 - i;
        int paddingRight = i7 - getPaddingRight();
        int paddingRight2 = (i7 - paddingLeft) - getPaddingRight();
        int virtualChildCount = getVirtualChildCount();
        int i8 = this.mGravity;
        int i9 = i8 & 112;
        int i10 = i8 & 8388615;
        if (i9 == 16) {
            i5 = getPaddingTop() + (((i4 - i2) - this.mTotalLength) / 2);
        } else if (i9 != 80) {
            i5 = getPaddingTop();
        } else {
            i5 = ((getPaddingTop() + i4) - i2) - this.mTotalLength;
        }
        int i11 = 0;
        while (i11 < virtualChildCount) {
            View virtualChildAt = getVirtualChildAt(i11);
            if (virtualChildAt == null) {
                i5 += measureNullChild(i11);
            } else if (virtualChildAt.getVisibility() != 8) {
                int measuredWidth = virtualChildAt.getMeasuredWidth();
                int measuredHeight = virtualChildAt.getMeasuredHeight();
                LayoutParams layoutParams = (LayoutParams) virtualChildAt.getLayoutParams();
                int i12 = layoutParams.gravity;
                if (i12 < 0) {
                    i12 = i10;
                }
                int absoluteGravity = GravityCompat.getAbsoluteGravity(i12, ViewCompat.getLayoutDirection(this)) & 7;
                if (absoluteGravity == 1) {
                    i6 = ((((paddingRight2 - measuredWidth) / 2) + paddingLeft) + layoutParams.leftMargin) - layoutParams.rightMargin;
                } else if (absoluteGravity != 5) {
                    i6 = layoutParams.leftMargin + paddingLeft;
                } else {
                    i6 = (paddingRight - measuredWidth) - layoutParams.rightMargin;
                }
                if (hasDividerBeforeChildAt(i11)) {
                    i5 += this.mDividerHeight;
                }
                int i13 = i5 + layoutParams.topMargin;
                LayoutParams layoutParams2 = layoutParams;
                setChildFrame(virtualChildAt, i6, i13 + getLocationOffset(virtualChildAt), measuredWidth, measuredHeight);
                i11 += getChildrenSkipCount(virtualChildAt, i11);
                i5 = i13 + measuredHeight + layoutParams2.bottomMargin + getNextLocationOffset(virtualChildAt);
            }
            i11++;
        }
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b3  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void layoutHorizontal(int r25, int r26, int r27, int r28) {
        /*
            r24 = this;
            r6 = r24
            boolean r0 = android.support.v7.widget.ViewUtils.isLayoutRtl(r24)
            int r7 = r24.getPaddingTop()
            int r1 = r28 - r26
            int r2 = r24.getPaddingBottom()
            int r8 = r1 - r2
            int r1 = r1 - r7
            int r2 = r24.getPaddingBottom()
            int r9 = r1 - r2
            int r10 = r24.getVirtualChildCount()
            int r1 = r6.mGravity
            r2 = 8388615(0x800007, float:1.1754953E-38)
            r2 = r2 & r1
            r11 = r1 & 112(0x70, float:1.57E-43)
            boolean r12 = r6.mBaselineAligned
            int[] r13 = r6.mMaxAscent
            int[] r14 = r6.mMaxDescent
            int r1 = android.support.v4.view.ViewCompat.getLayoutDirection(r24)
            int r1 = android.support.v4.view.GravityCompat.getAbsoluteGravity(r2, r1)
            r15 = 2
            r5 = 1
            if (r1 == r5) goto L_0x004b
            r2 = 5
            if (r1 == r2) goto L_0x003f
            int r1 = r24.getPaddingLeft()
            goto L_0x0056
        L_0x003f:
            int r1 = r24.getPaddingLeft()
            int r1 = r1 + r27
            int r1 = r1 - r25
            int r2 = r6.mTotalLength
            int r1 = r1 - r2
            goto L_0x0056
        L_0x004b:
            int r1 = r24.getPaddingLeft()
            int r2 = r27 - r25
            int r3 = r6.mTotalLength
            int r2 = r2 - r3
            int r2 = r2 / r15
            int r1 = r1 + r2
        L_0x0056:
            r2 = 0
            if (r0 == 0) goto L_0x0060
            int r0 = r10 + -1
            r16 = r0
            r17 = -1
            goto L_0x0064
        L_0x0060:
            r16 = 0
            r17 = 1
        L_0x0064:
            r3 = 0
        L_0x0065:
            if (r3 >= r10) goto L_0x0156
            int r0 = r17 * r3
            int r2 = r16 + r0
            android.view.View r0 = r6.getVirtualChildAt(r2)
            if (r0 != 0) goto L_0x0082
            int r0 = r6.measureNullChild(r2)
            int r1 = r1 + r0
            r22 = r7
            r19 = r10
            r20 = r11
            r21 = 1
            r23 = -1
            goto L_0x014a
        L_0x0082:
            int r5 = r0.getVisibility()
            r15 = 8
            if (r5 == r15) goto L_0x013e
            int r15 = r0.getMeasuredWidth()
            int r5 = r0.getMeasuredHeight()
            android.view.ViewGroup$LayoutParams r18 = r0.getLayoutParams()
            r4 = r18
            android.support.v7.widget.LinearLayoutCompat$LayoutParams r4 = (android.support.v7.widget.LinearLayoutCompat.LayoutParams) r4
            if (r12 == 0) goto L_0x00aa
            r18 = r3
            int r3 = r4.height
            r19 = r10
            r10 = -1
            if (r3 == r10) goto L_0x00ae
            int r3 = r0.getBaseline()
            goto L_0x00af
        L_0x00aa:
            r18 = r3
            r19 = r10
        L_0x00ae:
            r3 = -1
        L_0x00af:
            int r10 = r4.gravity
            if (r10 >= 0) goto L_0x00b4
            r10 = r11
        L_0x00b4:
            r10 = r10 & 112(0x70, float:1.57E-43)
            r20 = r11
            r11 = 16
            if (r10 == r11) goto L_0x00fa
            r11 = 48
            if (r10 == r11) goto L_0x00e6
            r11 = 80
            if (r10 == r11) goto L_0x00c9
            r3 = r7
            r11 = -1
            r21 = 1
            goto L_0x0108
        L_0x00c9:
            int r10 = r8 - r5
            int r11 = r4.bottomMargin
            int r10 = r10 - r11
            r11 = -1
            if (r3 == r11) goto L_0x00e2
            int r21 = r0.getMeasuredHeight()
            int r21 = r21 - r3
            r3 = 2
            r22 = r14[r3]
            int r22 = r22 - r21
            int r10 = r10 - r22
            r3 = r10
            r21 = 1
            goto L_0x0108
        L_0x00e2:
            r3 = r10
            r21 = 1
            goto L_0x0108
        L_0x00e6:
            r11 = -1
            int r10 = r4.topMargin
            int r10 = r10 + r7
            if (r3 == r11) goto L_0x00f6
            r21 = 1
            r22 = r13[r21]
            int r22 = r22 - r3
            int r10 = r10 + r22
            r3 = r10
            goto L_0x0108
        L_0x00f6:
            r21 = 1
            r3 = r10
            goto L_0x0108
        L_0x00fa:
            r11 = -1
            r21 = 1
            int r3 = r9 - r5
            r10 = 2
            int r3 = r3 / r10
            int r3 = r3 + r7
            int r10 = r4.topMargin
            int r3 = r3 + r10
            int r10 = r4.bottomMargin
            int r3 = r3 - r10
        L_0x0108:
            boolean r10 = r6.hasDividerBeforeChildAt(r2)
            if (r10 == 0) goto L_0x0111
            int r10 = r6.mDividerWidth
            int r1 = r1 + r10
        L_0x0111:
            int r10 = r4.leftMargin
            int r10 = r10 + r1
            int r1 = r6.getLocationOffset(r0)
            int r22 = r10 + r1
            r1 = r0
            r0 = r24
            r25 = r1
            r11 = r2
            r2 = r22
            r22 = r7
            r23 = -1
            r7 = r4
            r4 = r15
            r0.setChildFrame(r1, r2, r3, r4, r5)
            int r0 = r7.rightMargin
            int r15 = r15 + r0
            r0 = r25
            int r1 = r6.getNextLocationOffset(r0)
            int r15 = r15 + r1
            int r10 = r10 + r15
            int r0 = r6.getChildrenSkipCount(r0, r11)
            int r3 = r18 + r0
            r1 = r10
            goto L_0x014a
        L_0x013e:
            r18 = r3
            r22 = r7
            r19 = r10
            r20 = r11
            r21 = 1
            r23 = -1
        L_0x014a:
            int r3 = r3 + 1
            r10 = r19
            r11 = r20
            r7 = r22
            r5 = 1
            r15 = 2
            goto L_0x0065
        L_0x0156:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutCompat.layoutHorizontal(int, int, int, int):void");
    }

    private void setChildFrame(View view, int i, int i2, int i3, int i4) {
        view.layout(i, i2, i3 + i, i4 + i2);
    }

    public void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            requestLayout();
        }
    }

    public int getOrientation() {
        return this.mOrientation;
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((8388615 & i) == 0) {
                i |= 8388611;
            }
            if ((i & 112) == 0) {
                i |= 48;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    public int getGravity() {
        return this.mGravity;
    }

    public void setHorizontalGravity(int i) {
        int i2 = i & 8388615;
        int i3 = this.mGravity;
        if ((8388615 & i3) != i2) {
            this.mGravity = i2 | (-8388616 & i3);
            requestLayout();
        }
    }

    public void setVerticalGravity(int i) {
        int i2 = i & 112;
        int i3 = this.mGravity;
        if ((i3 & 112) != i2) {
            this.mGravity = i2 | (i3 & -113);
            requestLayout();
        }
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        int i = this.mOrientation;
        if (i == 0) {
            return new LayoutParams(-2, -2);
        }
        if (i == 1) {
            return new LayoutParams(-1, -2);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
    }
}
