package android.support.constraint;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build.VERSION;
import android.support.constraint.solver.Metrics;
import android.support.constraint.solver.widgets.Analyzer;
import android.support.constraint.solver.widgets.ConstraintAnchor.Type;
import android.support.constraint.solver.widgets.ConstraintWidget;
import android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour;
import android.support.constraint.solver.widgets.ConstraintWidgetContainer;
import android.support.constraint.solver.widgets.Guideline;
import android.support.constraint.solver.widgets.ResolutionAnchor;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.exoplayer2.C;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import java.util.ArrayList;
import java.util.HashMap;

public class ConstraintLayout extends ViewGroup {
    SparseArray<View> mChildrenByIds = new SparseArray<>();
    private ArrayList<ConstraintHelper> mConstraintHelpers = new ArrayList<>(4);
    private ConstraintSet mConstraintSet = null;
    private int mConstraintSetId = -1;
    private HashMap<String, Integer> mDesignIds = new HashMap<>();
    private boolean mDirtyHierarchy = true;
    private int mLastMeasureHeight = -1;
    int mLastMeasureHeightMode = 0;
    int mLastMeasureHeightSize = -1;
    private int mLastMeasureWidth = -1;
    int mLastMeasureWidthMode = 0;
    int mLastMeasureWidthSize = -1;
    ConstraintWidgetContainer mLayoutWidget = new ConstraintWidgetContainer();
    private int mMaxHeight = Integer.MAX_VALUE;
    private int mMaxWidth = Integer.MAX_VALUE;
    private Metrics mMetrics;
    private int mMinHeight = 0;
    private int mMinWidth = 0;
    private int mOptimizationLevel = 7;
    private final ArrayList<ConstraintWidget> mVariableDimensionsWidgets = new ArrayList<>(100);

    public static class LayoutParams extends MarginLayoutParams {
        public int baselineToBaseline = -1;
        public int bottomToBottom = -1;
        public int bottomToTop = -1;
        public float circleAngle = BitmapDescriptorFactory.HUE_RED;
        public int circleConstraint = -1;
        public int circleRadius = 0;
        public boolean constrainedHeight = false;
        public boolean constrainedWidth = false;
        public String dimensionRatio = null;
        int dimensionRatioSide = 1;
        float dimensionRatioValue = BitmapDescriptorFactory.HUE_RED;
        public int editorAbsoluteX = -1;
        public int editorAbsoluteY = -1;
        public int endToEnd = -1;
        public int endToStart = -1;
        public int goneBottomMargin = -1;
        public int goneEndMargin = -1;
        public int goneLeftMargin = -1;
        public int goneRightMargin = -1;
        public int goneStartMargin = -1;
        public int goneTopMargin = -1;
        public int guideBegin = -1;
        public int guideEnd = -1;
        public float guidePercent = -1.0f;
        public boolean helped = false;
        public float horizontalBias = 0.5f;
        public int horizontalChainStyle = 0;
        boolean horizontalDimensionFixed = true;
        public float horizontalWeight = -1.0f;
        boolean isGuideline = false;
        boolean isHelper = false;
        boolean isInPlaceholder = false;
        public int leftToLeft = -1;
        public int leftToRight = -1;
        public int matchConstraintDefaultHeight = 0;
        public int matchConstraintDefaultWidth = 0;
        public int matchConstraintMaxHeight = 0;
        public int matchConstraintMaxWidth = 0;
        public int matchConstraintMinHeight = 0;
        public int matchConstraintMinWidth = 0;
        public float matchConstraintPercentHeight = 1.0f;
        public float matchConstraintPercentWidth = 1.0f;
        boolean needsBaseline = false;
        public int orientation = -1;
        int resolveGoneLeftMargin = -1;
        int resolveGoneRightMargin = -1;
        int resolvedGuideBegin;
        int resolvedGuideEnd;
        float resolvedGuidePercent;
        float resolvedHorizontalBias = 0.5f;
        int resolvedLeftToLeft = -1;
        int resolvedLeftToRight = -1;
        int resolvedRightToLeft = -1;
        int resolvedRightToRight = -1;
        public int rightToLeft = -1;
        public int rightToRight = -1;
        public int startToEnd = -1;
        public int startToStart = -1;
        public int topToBottom = -1;
        public int topToTop = -1;
        public float verticalBias = 0.5f;
        public int verticalChainStyle = 0;
        boolean verticalDimensionFixed = true;
        public float verticalWeight = -1.0f;
        ConstraintWidget widget = new ConstraintWidget();

        private static class Table {
            public static final SparseIntArray map = new SparseIntArray();

            private Table() {
            }

            static {
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toLeftOf, 8);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_toRightOf, 9);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toLeftOf, 10);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_toRightOf, 11);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toTopOf, 12);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_toBottomOf, 13);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toTopOf, 14);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_toBottomOf, 15);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_toBaselineOf, 16);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircle, 2);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleRadius, 3);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintCircleAngle, 4);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteX, 49);
                map.append(R.styleable.ConstraintLayout_Layout_layout_editor_absoluteY, 50);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_begin, 5);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_end, 6);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintGuide_percent, 7);
                map.append(R.styleable.ConstraintLayout_Layout_android_orientation, 1);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toEndOf, 17);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintStart_toStartOf, 18);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toStartOf, 19);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintEnd_toEndOf, 20);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginLeft, 21);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginTop, 22);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginRight, 23);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginBottom, 24);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginStart, 25);
                map.append(R.styleable.ConstraintLayout_Layout_layout_goneMarginEnd, 26);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_bias, 29);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_bias, 30);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintDimensionRatio, 44);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_weight, 45);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_weight, 46);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHorizontal_chainStyle, 47);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintVertical_chainStyle, 48);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedWidth, 27);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constrainedHeight, 28);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_default, 31);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_default, 32);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_min, 33);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_max, 34);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintWidth_percent, 35);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_min, 36);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_max, 37);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintHeight_percent, 38);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintLeft_creator, 39);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintTop_creator, 40);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintRight_creator, 41);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBottom_creator, 42);
                map.append(R.styleable.ConstraintLayout_Layout_layout_constraintBaseline_creator, 43);
            }
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            int i;
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                switch (Table.map.get(index)) {
                    case 1:
                        this.orientation = obtainStyledAttributes.getInt(index, this.orientation);
                        break;
                    case 2:
                        this.circleConstraint = obtainStyledAttributes.getResourceId(index, this.circleConstraint);
                        if (this.circleConstraint != -1) {
                            break;
                        } else {
                            this.circleConstraint = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 3:
                        this.circleRadius = obtainStyledAttributes.getDimensionPixelSize(index, this.circleRadius);
                        break;
                    case 4:
                        this.circleAngle = obtainStyledAttributes.getFloat(index, this.circleAngle) % 360.0f;
                        float f = this.circleAngle;
                        if (f >= BitmapDescriptorFactory.HUE_RED) {
                            break;
                        } else {
                            this.circleAngle = (360.0f - f) % 360.0f;
                            break;
                        }
                    case 5:
                        this.guideBegin = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideBegin);
                        break;
                    case 6:
                        this.guideEnd = obtainStyledAttributes.getDimensionPixelOffset(index, this.guideEnd);
                        break;
                    case 7:
                        this.guidePercent = obtainStyledAttributes.getFloat(index, this.guidePercent);
                        break;
                    case 8:
                        this.leftToLeft = obtainStyledAttributes.getResourceId(index, this.leftToLeft);
                        if (this.leftToLeft != -1) {
                            break;
                        } else {
                            this.leftToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 9:
                        this.leftToRight = obtainStyledAttributes.getResourceId(index, this.leftToRight);
                        if (this.leftToRight != -1) {
                            break;
                        } else {
                            this.leftToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 10:
                        this.rightToLeft = obtainStyledAttributes.getResourceId(index, this.rightToLeft);
                        if (this.rightToLeft != -1) {
                            break;
                        } else {
                            this.rightToLeft = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 11:
                        this.rightToRight = obtainStyledAttributes.getResourceId(index, this.rightToRight);
                        if (this.rightToRight != -1) {
                            break;
                        } else {
                            this.rightToRight = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 12:
                        this.topToTop = obtainStyledAttributes.getResourceId(index, this.topToTop);
                        if (this.topToTop != -1) {
                            break;
                        } else {
                            this.topToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 13:
                        this.topToBottom = obtainStyledAttributes.getResourceId(index, this.topToBottom);
                        if (this.topToBottom != -1) {
                            break;
                        } else {
                            this.topToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 14:
                        this.bottomToTop = obtainStyledAttributes.getResourceId(index, this.bottomToTop);
                        if (this.bottomToTop != -1) {
                            break;
                        } else {
                            this.bottomToTop = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 15:
                        this.bottomToBottom = obtainStyledAttributes.getResourceId(index, this.bottomToBottom);
                        if (this.bottomToBottom != -1) {
                            break;
                        } else {
                            this.bottomToBottom = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 16:
                        this.baselineToBaseline = obtainStyledAttributes.getResourceId(index, this.baselineToBaseline);
                        if (this.baselineToBaseline != -1) {
                            break;
                        } else {
                            this.baselineToBaseline = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 17:
                        this.startToEnd = obtainStyledAttributes.getResourceId(index, this.startToEnd);
                        if (this.startToEnd != -1) {
                            break;
                        } else {
                            this.startToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 18:
                        this.startToStart = obtainStyledAttributes.getResourceId(index, this.startToStart);
                        if (this.startToStart != -1) {
                            break;
                        } else {
                            this.startToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 19:
                        this.endToStart = obtainStyledAttributes.getResourceId(index, this.endToStart);
                        if (this.endToStart != -1) {
                            break;
                        } else {
                            this.endToStart = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 20:
                        this.endToEnd = obtainStyledAttributes.getResourceId(index, this.endToEnd);
                        if (this.endToEnd != -1) {
                            break;
                        } else {
                            this.endToEnd = obtainStyledAttributes.getInt(index, -1);
                            break;
                        }
                    case 21:
                        this.goneLeftMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneLeftMargin);
                        break;
                    case 22:
                        this.goneTopMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneTopMargin);
                        break;
                    case 23:
                        this.goneRightMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneRightMargin);
                        break;
                    case 24:
                        this.goneBottomMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneBottomMargin);
                        break;
                    case 25:
                        this.goneStartMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneStartMargin);
                        break;
                    case 26:
                        this.goneEndMargin = obtainStyledAttributes.getDimensionPixelSize(index, this.goneEndMargin);
                        break;
                    case 27:
                        this.constrainedWidth = obtainStyledAttributes.getBoolean(index, this.constrainedWidth);
                        break;
                    case 28:
                        this.constrainedHeight = obtainStyledAttributes.getBoolean(index, this.constrainedHeight);
                        break;
                    case 29:
                        this.horizontalBias = obtainStyledAttributes.getFloat(index, this.horizontalBias);
                        break;
                    case 30:
                        this.verticalBias = obtainStyledAttributes.getFloat(index, this.verticalBias);
                        break;
                    case 31:
                        this.matchConstraintDefaultWidth = obtainStyledAttributes.getInt(index, 0);
                        if (this.matchConstraintDefaultWidth != 1) {
                            break;
                        } else {
                            Log.e("ConstraintLayout", "layout_constraintWidth_default=\"wrap\" is deprecated.\nUse layout_width=\"WRAP_CONTENT\" and layout_constrainedWidth=\"true\" instead.");
                            break;
                        }
                    case 32:
                        this.matchConstraintDefaultHeight = obtainStyledAttributes.getInt(index, 0);
                        if (this.matchConstraintDefaultHeight != 1) {
                            break;
                        } else {
                            Log.e("ConstraintLayout", "layout_constraintHeight_default=\"wrap\" is deprecated.\nUse layout_height=\"WRAP_CONTENT\" and layout_constrainedHeight=\"true\" instead.");
                            break;
                        }
                    case 33:
                        try {
                            this.matchConstraintMinWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinWidth);
                            break;
                        } catch (Exception unused) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinWidth) != -2) {
                                break;
                            } else {
                                this.matchConstraintMinWidth = -2;
                                break;
                            }
                        }
                    case 34:
                        try {
                            this.matchConstraintMaxWidth = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxWidth);
                            break;
                        } catch (Exception unused2) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxWidth) != -2) {
                                break;
                            } else {
                                this.matchConstraintMaxWidth = -2;
                                break;
                            }
                        }
                    case 35:
                        this.matchConstraintPercentWidth = Math.max(BitmapDescriptorFactory.HUE_RED, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentWidth));
                        break;
                    case 36:
                        try {
                            this.matchConstraintMinHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMinHeight);
                            break;
                        } catch (Exception unused3) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMinHeight) != -2) {
                                break;
                            } else {
                                this.matchConstraintMinHeight = -2;
                                break;
                            }
                        }
                    case 37:
                        try {
                            this.matchConstraintMaxHeight = obtainStyledAttributes.getDimensionPixelSize(index, this.matchConstraintMaxHeight);
                            break;
                        } catch (Exception unused4) {
                            if (obtainStyledAttributes.getInt(index, this.matchConstraintMaxHeight) != -2) {
                                break;
                            } else {
                                this.matchConstraintMaxHeight = -2;
                                break;
                            }
                        }
                    case 38:
                        this.matchConstraintPercentHeight = Math.max(BitmapDescriptorFactory.HUE_RED, obtainStyledAttributes.getFloat(index, this.matchConstraintPercentHeight));
                        break;
                    case 44:
                        this.dimensionRatio = obtainStyledAttributes.getString(index);
                        this.dimensionRatioValue = Float.NaN;
                        this.dimensionRatioSide = -1;
                        String str = this.dimensionRatio;
                        if (str == null) {
                            break;
                        } else {
                            int length = str.length();
                            int indexOf = this.dimensionRatio.indexOf(44);
                            if (indexOf <= 0 || indexOf >= length - 1) {
                                i = 0;
                            } else {
                                String substring = this.dimensionRatio.substring(0, indexOf);
                                if (substring.equalsIgnoreCase(Attributes.WEDNESDAY)) {
                                    this.dimensionRatioSide = 0;
                                } else if (substring.equalsIgnoreCase("H")) {
                                    this.dimensionRatioSide = 1;
                                }
                                i = indexOf + 1;
                            }
                            int indexOf2 = this.dimensionRatio.indexOf(58);
                            if (indexOf2 >= 0 && indexOf2 < length - 1) {
                                String substring2 = this.dimensionRatio.substring(i, indexOf2);
                                String substring3 = this.dimensionRatio.substring(indexOf2 + 1);
                                if (substring2.length() > 0 && substring3.length() > 0) {
                                    try {
                                        float parseFloat = Float.parseFloat(substring2);
                                        float parseFloat2 = Float.parseFloat(substring3);
                                        if (parseFloat > BitmapDescriptorFactory.HUE_RED && parseFloat2 > BitmapDescriptorFactory.HUE_RED) {
                                            if (this.dimensionRatioSide != 1) {
                                                this.dimensionRatioValue = Math.abs(parseFloat / parseFloat2);
                                                break;
                                            } else {
                                                this.dimensionRatioValue = Math.abs(parseFloat2 / parseFloat);
                                                break;
                                            }
                                        }
                                    } catch (NumberFormatException unused5) {
                                        break;
                                    }
                                }
                            } else {
                                String substring4 = this.dimensionRatio.substring(i);
                                if (substring4.length() <= 0) {
                                    break;
                                } else {
                                    this.dimensionRatioValue = Float.parseFloat(substring4);
                                    break;
                                }
                            }
                        }
                        break;
                    case 45:
                        this.horizontalWeight = obtainStyledAttributes.getFloat(index, this.horizontalWeight);
                        break;
                    case 46:
                        this.verticalWeight = obtainStyledAttributes.getFloat(index, this.verticalWeight);
                        break;
                    case 47:
                        this.horizontalChainStyle = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 48:
                        this.verticalChainStyle = obtainStyledAttributes.getInt(index, 0);
                        break;
                    case 49:
                        this.editorAbsoluteX = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteX);
                        break;
                    case 50:
                        this.editorAbsoluteY = obtainStyledAttributes.getDimensionPixelOffset(index, this.editorAbsoluteY);
                        break;
                }
            }
            obtainStyledAttributes.recycle();
            validate();
        }

        public void validate() {
            this.isGuideline = false;
            this.horizontalDimensionFixed = true;
            this.verticalDimensionFixed = true;
            if (this.width == -2 && this.constrainedWidth) {
                this.horizontalDimensionFixed = false;
                this.matchConstraintDefaultWidth = 1;
            }
            if (this.height == -2 && this.constrainedHeight) {
                this.verticalDimensionFixed = false;
                this.matchConstraintDefaultHeight = 1;
            }
            if (this.width == 0 || this.width == -1) {
                this.horizontalDimensionFixed = false;
                if (this.width == 0 && this.matchConstraintDefaultWidth == 1) {
                    this.width = -2;
                    this.constrainedWidth = true;
                }
            }
            if (this.height == 0 || this.height == -1) {
                this.verticalDimensionFixed = false;
                if (this.height == 0 && this.matchConstraintDefaultHeight == 1) {
                    this.height = -2;
                    this.constrainedHeight = true;
                }
            }
            if (this.guidePercent != -1.0f || this.guideBegin != -1 || this.guideEnd != -1) {
                this.isGuideline = true;
                this.horizontalDimensionFixed = true;
                this.verticalDimensionFixed = true;
                if (!(this.widget instanceof Guideline)) {
                    this.widget = new Guideline();
                }
                ((Guideline) this.widget).setOrientation(this.orientation);
            }
        }

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public LayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        @TargetApi(17)
        public void resolveLayoutDirection(int i) {
            int i2 = this.leftMargin;
            int i3 = this.rightMargin;
            super.resolveLayoutDirection(i);
            this.resolvedRightToLeft = -1;
            this.resolvedRightToRight = -1;
            this.resolvedLeftToLeft = -1;
            this.resolvedLeftToRight = -1;
            this.resolveGoneLeftMargin = -1;
            this.resolveGoneRightMargin = -1;
            this.resolveGoneLeftMargin = this.goneLeftMargin;
            this.resolveGoneRightMargin = this.goneRightMargin;
            this.resolvedHorizontalBias = this.horizontalBias;
            this.resolvedGuideBegin = this.guideBegin;
            this.resolvedGuideEnd = this.guideEnd;
            this.resolvedGuidePercent = this.guidePercent;
            boolean z = false;
            if (1 == getLayoutDirection()) {
                int i4 = this.startToEnd;
                if (i4 != -1) {
                    this.resolvedRightToLeft = i4;
                    z = true;
                } else {
                    int i5 = this.startToStart;
                    if (i5 != -1) {
                        this.resolvedRightToRight = i5;
                        z = true;
                    }
                }
                int i6 = this.endToStart;
                if (i6 != -1) {
                    this.resolvedLeftToRight = i6;
                    z = true;
                }
                int i7 = this.endToEnd;
                if (i7 != -1) {
                    this.resolvedLeftToLeft = i7;
                    z = true;
                }
                int i8 = this.goneStartMargin;
                if (i8 != -1) {
                    this.resolveGoneRightMargin = i8;
                }
                int i9 = this.goneEndMargin;
                if (i9 != -1) {
                    this.resolveGoneLeftMargin = i9;
                }
                if (z) {
                    this.resolvedHorizontalBias = 1.0f - this.horizontalBias;
                }
                if (this.isGuideline && this.orientation == 1) {
                    float f = this.guidePercent;
                    if (f != -1.0f) {
                        this.resolvedGuidePercent = 1.0f - f;
                        this.resolvedGuideBegin = -1;
                        this.resolvedGuideEnd = -1;
                    } else {
                        int i10 = this.guideBegin;
                        if (i10 != -1) {
                            this.resolvedGuideEnd = i10;
                            this.resolvedGuideBegin = -1;
                            this.resolvedGuidePercent = -1.0f;
                        } else {
                            int i11 = this.guideEnd;
                            if (i11 != -1) {
                                this.resolvedGuideBegin = i11;
                                this.resolvedGuideEnd = -1;
                                this.resolvedGuidePercent = -1.0f;
                            }
                        }
                    }
                }
            } else {
                int i12 = this.startToEnd;
                if (i12 != -1) {
                    this.resolvedLeftToRight = i12;
                }
                int i13 = this.startToStart;
                if (i13 != -1) {
                    this.resolvedLeftToLeft = i13;
                }
                int i14 = this.endToStart;
                if (i14 != -1) {
                    this.resolvedRightToLeft = i14;
                }
                int i15 = this.endToEnd;
                if (i15 != -1) {
                    this.resolvedRightToRight = i15;
                }
                int i16 = this.goneStartMargin;
                if (i16 != -1) {
                    this.resolveGoneLeftMargin = i16;
                }
                int i17 = this.goneEndMargin;
                if (i17 != -1) {
                    this.resolveGoneRightMargin = i17;
                }
            }
            if (this.endToStart == -1 && this.endToEnd == -1 && this.startToStart == -1 && this.startToEnd == -1) {
                int i18 = this.rightToLeft;
                if (i18 != -1) {
                    this.resolvedRightToLeft = i18;
                    if (this.rightMargin <= 0 && i3 > 0) {
                        this.rightMargin = i3;
                    }
                } else {
                    int i19 = this.rightToRight;
                    if (i19 != -1) {
                        this.resolvedRightToRight = i19;
                        if (this.rightMargin <= 0 && i3 > 0) {
                            this.rightMargin = i3;
                        }
                    }
                }
                int i20 = this.leftToLeft;
                if (i20 != -1) {
                    this.resolvedLeftToLeft = i20;
                    if (this.leftMargin <= 0 && i2 > 0) {
                        this.leftMargin = i2;
                        return;
                    }
                    return;
                }
                int i21 = this.leftToRight;
                if (i21 != -1) {
                    this.resolvedLeftToRight = i21;
                    if (this.leftMargin <= 0 && i2 > 0) {
                        this.leftMargin = i2;
                    }
                }
            }
        }
    }

    public boolean shouldDelayChildPressedState() {
        return false;
    }

    public void setDesignInformation(int i, Object obj, Object obj2) {
        if (i == 0 && (obj instanceof String) && (obj2 instanceof Integer)) {
            if (this.mDesignIds == null) {
                this.mDesignIds = new HashMap<>();
            }
            String str = (String) obj;
            int indexOf = str.indexOf("/");
            if (indexOf != -1) {
                str = str.substring(indexOf + 1);
            }
            this.mDesignIds.put(str, Integer.valueOf(((Integer) obj2).intValue()));
        }
    }

    public Object getDesignInformation(int i, Object obj) {
        if (i == 0 && (obj instanceof String)) {
            String str = (String) obj;
            HashMap<String, Integer> hashMap = this.mDesignIds;
            if (hashMap != null && hashMap.containsKey(str)) {
                return this.mDesignIds.get(str);
            }
        }
        return null;
    }

    public ConstraintLayout(Context context) {
        super(context);
        init(null);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(attributeSet);
    }

    public ConstraintLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(attributeSet);
    }

    public void setId(int i) {
        this.mChildrenByIds.remove(getId());
        super.setId(i);
        this.mChildrenByIds.put(getId(), this);
    }

    private void init(AttributeSet attributeSet) {
        this.mLayoutWidget.setCompanionWidget(this);
        this.mChildrenByIds.put(getId(), this);
        this.mConstraintSet = null;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.ConstraintLayout_Layout);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = obtainStyledAttributes.getIndex(i);
                if (index == R.styleable.ConstraintLayout_Layout_android_minWidth) {
                    this.mMinWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_minHeight) {
                    this.mMinHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMinHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxWidth) {
                    this.mMaxWidth = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxWidth);
                } else if (index == R.styleable.ConstraintLayout_Layout_android_maxHeight) {
                    this.mMaxHeight = obtainStyledAttributes.getDimensionPixelOffset(index, this.mMaxHeight);
                } else if (index == R.styleable.ConstraintLayout_Layout_layout_optimizationLevel) {
                    this.mOptimizationLevel = obtainStyledAttributes.getInt(index, this.mOptimizationLevel);
                } else if (index == R.styleable.ConstraintLayout_Layout_constraintSet) {
                    int resourceId = obtainStyledAttributes.getResourceId(index, 0);
                    try {
                        this.mConstraintSet = new ConstraintSet();
                        this.mConstraintSet.load(getContext(), resourceId);
                    } catch (NotFoundException unused) {
                        this.mConstraintSet = null;
                    }
                    this.mConstraintSetId = resourceId;
                }
            }
            obtainStyledAttributes.recycle();
        }
        this.mLayoutWidget.setOptimizationLevel(this.mOptimizationLevel);
    }

    public void addView(View view, int i, android.view.ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (VERSION.SDK_INT < 14) {
            onViewAdded(view);
        }
    }

    public void removeView(View view) {
        super.removeView(view);
        if (VERSION.SDK_INT < 14) {
            onViewRemoved(view);
        }
    }

    public void onViewAdded(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewAdded(view);
        }
        ConstraintWidget viewWidget = getViewWidget(view);
        if ((view instanceof Guideline) && !(viewWidget instanceof Guideline)) {
            LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
            layoutParams.widget = new Guideline();
            layoutParams.isGuideline = true;
            ((Guideline) layoutParams.widget).setOrientation(layoutParams.orientation);
        }
        if (view instanceof ConstraintHelper) {
            ConstraintHelper constraintHelper = (ConstraintHelper) view;
            constraintHelper.validateParams();
            ((LayoutParams) view.getLayoutParams()).isHelper = true;
            if (!this.mConstraintHelpers.contains(constraintHelper)) {
                this.mConstraintHelpers.add(constraintHelper);
            }
        }
        this.mChildrenByIds.put(view.getId(), view);
        this.mDirtyHierarchy = true;
    }

    public void onViewRemoved(View view) {
        if (VERSION.SDK_INT >= 14) {
            super.onViewRemoved(view);
        }
        this.mChildrenByIds.remove(view.getId());
        ConstraintWidget viewWidget = getViewWidget(view);
        this.mLayoutWidget.remove(viewWidget);
        this.mConstraintHelpers.remove(view);
        this.mVariableDimensionsWidgets.remove(viewWidget);
        this.mDirtyHierarchy = true;
    }

    public void setMinWidth(int i) {
        if (i != this.mMinWidth) {
            this.mMinWidth = i;
            requestLayout();
        }
    }

    public void setMinHeight(int i) {
        if (i != this.mMinHeight) {
            this.mMinHeight = i;
            requestLayout();
        }
    }

    public int getMinWidth() {
        return this.mMinWidth;
    }

    public int getMinHeight() {
        return this.mMinHeight;
    }

    public void setMaxWidth(int i) {
        if (i != this.mMaxWidth) {
            this.mMaxWidth = i;
            requestLayout();
        }
    }

    public void setMaxHeight(int i) {
        if (i != this.mMaxHeight) {
            this.mMaxHeight = i;
            requestLayout();
        }
    }

    public int getMaxWidth() {
        return this.mMaxWidth;
    }

    public int getMaxHeight() {
        return this.mMaxHeight;
    }

    private void updateHierarchy() {
        int childCount = getChildCount();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= childCount) {
                break;
            } else if (getChildAt(i).isLayoutRequested()) {
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            this.mVariableDimensionsWidgets.clear();
            setChildrenConstraints();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:125:0x01ef  */
    /* JADX WARNING: Removed duplicated region for block: B:126:0x01f8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setChildrenConstraints() {
        /*
            r26 = this;
            r0 = r26
            boolean r1 = r26.isInEditMode()
            int r2 = r26.getChildCount()
            r3 = 0
            r4 = -1
            if (r1 == 0) goto L_0x0048
            r5 = 0
        L_0x000f:
            if (r5 >= r2) goto L_0x0048
            android.view.View r6 = r0.getChildAt(r5)
            android.content.res.Resources r7 = r26.getResources()     // Catch:{ NotFoundException -> 0x0045 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0045 }
            java.lang.String r7 = r7.getResourceName(r8)     // Catch:{ NotFoundException -> 0x0045 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0045 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ NotFoundException -> 0x0045 }
            r0.setDesignInformation(r3, r7, r8)     // Catch:{ NotFoundException -> 0x0045 }
            r8 = 47
            int r8 = r7.indexOf(r8)     // Catch:{ NotFoundException -> 0x0045 }
            if (r8 == r4) goto L_0x003a
            int r8 = r8 + 1
            java.lang.String r7 = r7.substring(r8)     // Catch:{ NotFoundException -> 0x0045 }
        L_0x003a:
            int r6 = r6.getId()     // Catch:{ NotFoundException -> 0x0045 }
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r0.getTargetWidget(r6)     // Catch:{ NotFoundException -> 0x0045 }
            r6.setDebugName(r7)     // Catch:{ NotFoundException -> 0x0045 }
        L_0x0045:
            int r5 = r5 + 1
            goto L_0x000f
        L_0x0048:
            r5 = 0
        L_0x0049:
            if (r5 >= r2) goto L_0x005c
            android.view.View r6 = r0.getChildAt(r5)
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r0.getViewWidget(r6)
            if (r6 != 0) goto L_0x0056
            goto L_0x0059
        L_0x0056:
            r6.reset()
        L_0x0059:
            int r5 = r5 + 1
            goto L_0x0049
        L_0x005c:
            int r5 = r0.mConstraintSetId
            if (r5 == r4) goto L_0x007e
            r5 = 0
        L_0x0061:
            if (r5 >= r2) goto L_0x007e
            android.view.View r6 = r0.getChildAt(r5)
            int r7 = r6.getId()
            int r8 = r0.mConstraintSetId
            if (r7 != r8) goto L_0x007b
            boolean r7 = r6 instanceof android.support.constraint.Constraints
            if (r7 == 0) goto L_0x007b
            android.support.constraint.Constraints r6 = (android.support.constraint.Constraints) r6
            android.support.constraint.ConstraintSet r6 = r6.getConstraintSet()
            r0.mConstraintSet = r6
        L_0x007b:
            int r5 = r5 + 1
            goto L_0x0061
        L_0x007e:
            android.support.constraint.ConstraintSet r5 = r0.mConstraintSet
            if (r5 == 0) goto L_0x0085
            r5.applyToInternal(r0)
        L_0x0085:
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r5 = r0.mLayoutWidget
            r5.removeAllChildren()
            java.util.ArrayList<android.support.constraint.ConstraintHelper> r5 = r0.mConstraintHelpers
            int r5 = r5.size()
            if (r5 <= 0) goto L_0x00a3
            r6 = 0
        L_0x0093:
            if (r6 >= r5) goto L_0x00a3
            java.util.ArrayList<android.support.constraint.ConstraintHelper> r7 = r0.mConstraintHelpers
            java.lang.Object r7 = r7.get(r6)
            android.support.constraint.ConstraintHelper r7 = (android.support.constraint.ConstraintHelper) r7
            r7.updatePreLayout(r0)
            int r6 = r6 + 1
            goto L_0x0093
        L_0x00a3:
            r5 = 0
        L_0x00a4:
            if (r5 >= r2) goto L_0x00b6
            android.view.View r6 = r0.getChildAt(r5)
            boolean r7 = r6 instanceof android.support.constraint.Placeholder
            if (r7 == 0) goto L_0x00b3
            android.support.constraint.Placeholder r6 = (android.support.constraint.Placeholder) r6
            r6.updatePreLayout(r0)
        L_0x00b3:
            int r5 = r5 + 1
            goto L_0x00a4
        L_0x00b6:
            r5 = 0
        L_0x00b7:
            if (r5 >= r2) goto L_0x0412
            android.view.View r6 = r0.getChildAt(r5)
            android.support.constraint.solver.widgets.ConstraintWidget r13 = r0.getViewWidget(r6)
            if (r13 != 0) goto L_0x00c5
            goto L_0x040e
        L_0x00c5:
            android.view.ViewGroup$LayoutParams r7 = r6.getLayoutParams()
            r14 = r7
            android.support.constraint.ConstraintLayout$LayoutParams r14 = (android.support.constraint.ConstraintLayout.LayoutParams) r14
            r14.validate()
            boolean r7 = r14.helped
            if (r7 == 0) goto L_0x00d6
            r14.helped = r3
            goto L_0x0108
        L_0x00d6:
            if (r1 == 0) goto L_0x0108
            android.content.res.Resources r7 = r26.getResources()     // Catch:{ NotFoundException -> 0x0107 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0107 }
            java.lang.String r7 = r7.getResourceName(r8)     // Catch:{ NotFoundException -> 0x0107 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0107 }
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch:{ NotFoundException -> 0x0107 }
            r0.setDesignInformation(r3, r7, r8)     // Catch:{ NotFoundException -> 0x0107 }
            java.lang.String r8 = "id/"
            int r8 = r7.indexOf(r8)     // Catch:{ NotFoundException -> 0x0107 }
            int r8 = r8 + 3
            java.lang.String r7 = r7.substring(r8)     // Catch:{ NotFoundException -> 0x0107 }
            int r8 = r6.getId()     // Catch:{ NotFoundException -> 0x0107 }
            android.support.constraint.solver.widgets.ConstraintWidget r8 = r0.getTargetWidget(r8)     // Catch:{ NotFoundException -> 0x0107 }
            r8.setDebugName(r7)     // Catch:{ NotFoundException -> 0x0107 }
            goto L_0x0108
        L_0x0107:
        L_0x0108:
            int r7 = r6.getVisibility()
            r13.setVisibility(r7)
            boolean r7 = r14.isInPlaceholder
            if (r7 == 0) goto L_0x0118
            r7 = 8
            r13.setVisibility(r7)
        L_0x0118:
            r13.setCompanionWidget(r6)
            android.support.constraint.solver.widgets.ConstraintWidgetContainer r6 = r0.mLayoutWidget
            r6.add(r13)
            boolean r6 = r14.verticalDimensionFixed
            if (r6 == 0) goto L_0x0128
            boolean r6 = r14.horizontalDimensionFixed
            if (r6 != 0) goto L_0x012d
        L_0x0128:
            java.util.ArrayList<android.support.constraint.solver.widgets.ConstraintWidget> r6 = r0.mVariableDimensionsWidgets
            r6.add(r13)
        L_0x012d:
            boolean r6 = r14.isGuideline
            r7 = 17
            if (r6 == 0) goto L_0x015e
            android.support.constraint.solver.widgets.Guideline r13 = (android.support.constraint.solver.widgets.Guideline) r13
            int r6 = r14.resolvedGuideBegin
            int r8 = r14.resolvedGuideEnd
            float r9 = r14.resolvedGuidePercent
            int r10 = android.os.Build.VERSION.SDK_INT
            if (r10 >= r7) goto L_0x0145
            int r6 = r14.guideBegin
            int r8 = r14.guideEnd
            float r9 = r14.guidePercent
        L_0x0145:
            r7 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r7 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
            if (r7 == 0) goto L_0x0150
            r13.setGuidePercent(r9)
            goto L_0x040e
        L_0x0150:
            if (r6 == r4) goto L_0x0157
            r13.setGuideBegin(r6)
            goto L_0x040e
        L_0x0157:
            if (r8 == r4) goto L_0x040e
            r13.setGuideEnd(r8)
            goto L_0x040e
        L_0x015e:
            int r6 = r14.leftToLeft
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.leftToRight
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.rightToLeft
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.rightToRight
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.startToStart
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.startToEnd
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.endToStart
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.endToEnd
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.topToTop
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.topToBottom
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.bottomToTop
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.bottomToBottom
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.baselineToBaseline
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.editorAbsoluteX
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.editorAbsoluteY
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.circleConstraint
            if (r6 != r4) goto L_0x01a6
            int r6 = r14.width
            if (r6 == r4) goto L_0x01a6
            int r6 = r14.height
            if (r6 != r4) goto L_0x040e
        L_0x01a6:
            int r6 = r14.resolvedLeftToLeft
            int r8 = r14.resolvedLeftToRight
            int r9 = r14.resolvedRightToLeft
            int r10 = r14.resolvedRightToRight
            int r11 = r14.resolveGoneLeftMargin
            int r12 = r14.resolveGoneRightMargin
            float r15 = r14.resolvedHorizontalBias
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 >= r7) goto L_0x020c
            int r3 = r14.leftToLeft
            int r6 = r14.leftToRight
            int r9 = r14.rightToLeft
            int r10 = r14.rightToRight
            int r7 = r14.goneLeftMargin
            int r8 = r14.goneRightMargin
            float r15 = r14.horizontalBias
            if (r3 != r4) goto L_0x01e2
            if (r6 != r4) goto L_0x01e2
            int r11 = r14.startToStart
            if (r11 == r4) goto L_0x01d6
            int r3 = r14.startToStart
            r25 = r6
            r6 = r3
            r3 = r25
            goto L_0x01e7
        L_0x01d6:
            int r11 = r14.startToEnd
            if (r11 == r4) goto L_0x01e2
            int r6 = r14.startToEnd
            r25 = r6
            r6 = r3
            r3 = r25
            goto L_0x01e7
        L_0x01e2:
            r25 = r6
            r6 = r3
            r3 = r25
        L_0x01e7:
            if (r9 != r4) goto L_0x0205
            if (r10 != r4) goto L_0x0205
            int r11 = r14.endToStart
            if (r11 == r4) goto L_0x01f8
            int r9 = r14.endToStart
            r12 = r7
            r16 = r8
            r11 = r10
            r10 = r15
            r15 = r9
            goto L_0x0213
        L_0x01f8:
            int r11 = r14.endToEnd
            if (r11 == r4) goto L_0x0205
            int r10 = r14.endToEnd
            r12 = r7
            r16 = r8
            r11 = r10
            r10 = r15
            r15 = r9
            goto L_0x0213
        L_0x0205:
            r12 = r7
            r16 = r8
            r11 = r10
            r10 = r15
            r15 = r9
            goto L_0x0213
        L_0x020c:
            r3 = r8
            r16 = r12
            r12 = r11
            r11 = r10
            r10 = r15
            r15 = r9
        L_0x0213:
            int r7 = r14.circleConstraint
            if (r7 == r4) goto L_0x0228
            int r3 = r14.circleConstraint
            android.support.constraint.solver.widgets.ConstraintWidget r3 = r0.getTargetWidget(r3)
            if (r3 == 0) goto L_0x035c
            float r6 = r14.circleAngle
            int r7 = r14.circleRadius
            r13.connectCircularConstraint(r3, r6, r7)
            goto L_0x035c
        L_0x0228:
            if (r6 == r4) goto L_0x0245
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r6)
            if (r9 == 0) goto L_0x0240
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            int r6 = r14.leftMargin
            r7 = r13
            r17 = r10
            r10 = r3
            r3 = r11
            r11 = r6
            r7.immediateConnect(r8, r9, r10, r11, r12)
            goto L_0x0243
        L_0x0240:
            r17 = r10
            r3 = r11
        L_0x0243:
            r6 = r3
            goto L_0x025a
        L_0x0245:
            r17 = r10
            r6 = r11
            if (r3 == r4) goto L_0x025a
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x025a
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            int r11 = r14.leftMargin
            r7 = r13
            r7.immediateConnect(r8, r9, r10, r11, r12)
        L_0x025a:
            if (r15 == r4) goto L_0x026f
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r15)
            if (r9 == 0) goto L_0x0283
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            int r11 = r14.rightMargin
            r7 = r13
            r12 = r16
            r7.immediateConnect(r8, r9, r10, r11, r12)
            goto L_0x0283
        L_0x026f:
            if (r6 == r4) goto L_0x0283
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r6)
            if (r9 == 0) goto L_0x0283
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            int r11 = r14.rightMargin
            r7 = r13
            r12 = r16
            r7.immediateConnect(r8, r9, r10, r11, r12)
        L_0x0283:
            int r3 = r14.topToTop
            if (r3 == r4) goto L_0x029c
            int r3 = r14.topToTop
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x02b4
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            int r11 = r14.topMargin
            int r12 = r14.goneTopMargin
            r7 = r13
            r7.immediateConnect(r8, r9, r10, r11, r12)
            goto L_0x02b4
        L_0x029c:
            int r3 = r14.topToBottom
            if (r3 == r4) goto L_0x02b4
            int r3 = r14.topToBottom
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x02b4
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            int r11 = r14.topMargin
            int r12 = r14.goneTopMargin
            r7 = r13
            r7.immediateConnect(r8, r9, r10, r11, r12)
        L_0x02b4:
            int r3 = r14.bottomToTop
            if (r3 == r4) goto L_0x02cd
            int r3 = r14.bottomToTop
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x02e5
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            int r11 = r14.bottomMargin
            int r12 = r14.goneBottomMargin
            r7 = r13
            r7.immediateConnect(r8, r9, r10, r11, r12)
            goto L_0x02e5
        L_0x02cd:
            int r3 = r14.bottomToBottom
            if (r3 == r4) goto L_0x02e5
            int r3 = r14.bottomToBottom
            android.support.constraint.solver.widgets.ConstraintWidget r9 = r0.getTargetWidget(r3)
            if (r9 == 0) goto L_0x02e5
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r8 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r10 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            int r11 = r14.bottomMargin
            int r12 = r14.goneBottomMargin
            r7 = r13
            r7.immediateConnect(r8, r9, r10, r11, r12)
        L_0x02e5:
            int r3 = r14.baselineToBaseline
            if (r3 == r4) goto L_0x033b
            android.util.SparseArray<android.view.View> r3 = r0.mChildrenByIds
            int r6 = r14.baselineToBaseline
            java.lang.Object r3 = r3.get(r6)
            android.view.View r3 = (android.view.View) r3
            int r6 = r14.baselineToBaseline
            android.support.constraint.solver.widgets.ConstraintWidget r6 = r0.getTargetWidget(r6)
            if (r6 == 0) goto L_0x033b
            if (r3 == 0) goto L_0x033b
            android.view.ViewGroup$LayoutParams r7 = r3.getLayoutParams()
            boolean r7 = r7 instanceof android.support.constraint.ConstraintLayout.LayoutParams
            if (r7 == 0) goto L_0x033b
            android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
            android.support.constraint.ConstraintLayout$LayoutParams r3 = (android.support.constraint.ConstraintLayout.LayoutParams) r3
            r7 = 1
            r14.needsBaseline = r7
            r3.needsBaseline = r7
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BASELINE
            android.support.constraint.solver.widgets.ConstraintAnchor r18 = r13.getAnchor(r3)
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BASELINE
            android.support.constraint.solver.widgets.ConstraintAnchor r19 = r6.getAnchor(r3)
            r20 = 0
            r21 = -1
            android.support.constraint.solver.widgets.ConstraintAnchor$Strength r22 = android.support.constraint.solver.widgets.ConstraintAnchor.Strength.STRONG
            r23 = 0
            r24 = 1
            r18.connect(r19, r20, r21, r22, r23, r24)
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            r3.reset()
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            r3.reset()
        L_0x033b:
            r3 = 1056964608(0x3f000000, float:0.5)
            r6 = 0
            r15 = r17
            int r7 = (r15 > r6 ? 1 : (r15 == r6 ? 0 : -1))
            if (r7 < 0) goto L_0x034b
            int r7 = (r15 > r3 ? 1 : (r15 == r3 ? 0 : -1))
            if (r7 == 0) goto L_0x034b
            r13.setHorizontalBiasPercent(r15)
        L_0x034b:
            float r7 = r14.verticalBias
            int r6 = (r7 > r6 ? 1 : (r7 == r6 ? 0 : -1))
            if (r6 < 0) goto L_0x035c
            float r6 = r14.verticalBias
            int r3 = (r6 > r3 ? 1 : (r6 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x035c
            float r3 = r14.verticalBias
            r13.setVerticalBiasPercent(r3)
        L_0x035c:
            if (r1 == 0) goto L_0x036d
            int r3 = r14.editorAbsoluteX
            if (r3 != r4) goto L_0x0366
            int r3 = r14.editorAbsoluteY
            if (r3 == r4) goto L_0x036d
        L_0x0366:
            int r3 = r14.editorAbsoluteX
            int r6 = r14.editorAbsoluteY
            r13.setOrigin(r3, r6)
        L_0x036d:
            boolean r3 = r14.horizontalDimensionFixed
            if (r3 != 0) goto L_0x0399
            int r3 = r14.width
            if (r3 != r4) goto L_0x038f
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            r13.setHorizontalDimensionBehaviour(r3)
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.LEFT
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            int r6 = r14.leftMargin
            r3.mMargin = r6
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.RIGHT
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            int r6 = r14.rightMargin
            r3.mMargin = r6
            goto L_0x03a3
        L_0x038f:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r13.setHorizontalDimensionBehaviour(r3)
            r3 = 0
            r13.setWidth(r3)
            goto L_0x03a3
        L_0x0399:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r13.setHorizontalDimensionBehaviour(r3)
            int r3 = r14.width
            r13.setWidth(r3)
        L_0x03a3:
            boolean r3 = r14.verticalDimensionFixed
            if (r3 != 0) goto L_0x03d0
            int r3 = r14.height
            if (r3 != r4) goto L_0x03c6
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_PARENT
            r13.setVerticalDimensionBehaviour(r3)
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.TOP
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            int r6 = r14.topMargin
            r3.mMargin = r6
            android.support.constraint.solver.widgets.ConstraintAnchor$Type r3 = android.support.constraint.solver.widgets.ConstraintAnchor.Type.BOTTOM
            android.support.constraint.solver.widgets.ConstraintAnchor r3 = r13.getAnchor(r3)
            int r6 = r14.bottomMargin
            r3.mMargin = r6
            r3 = 0
            goto L_0x03db
        L_0x03c6:
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r3 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT
            r13.setVerticalDimensionBehaviour(r3)
            r3 = 0
            r13.setHeight(r3)
            goto L_0x03db
        L_0x03d0:
            r3 = 0
            android.support.constraint.solver.widgets.ConstraintWidget$DimensionBehaviour r6 = android.support.constraint.solver.widgets.ConstraintWidget.DimensionBehaviour.FIXED
            r13.setVerticalDimensionBehaviour(r6)
            int r6 = r14.height
            r13.setHeight(r6)
        L_0x03db:
            java.lang.String r6 = r14.dimensionRatio
            if (r6 == 0) goto L_0x03e4
            java.lang.String r6 = r14.dimensionRatio
            r13.setDimensionRatio(r6)
        L_0x03e4:
            float r6 = r14.horizontalWeight
            r13.setHorizontalWeight(r6)
            float r6 = r14.verticalWeight
            r13.setVerticalWeight(r6)
            int r6 = r14.horizontalChainStyle
            r13.setHorizontalChainStyle(r6)
            int r6 = r14.verticalChainStyle
            r13.setVerticalChainStyle(r6)
            int r6 = r14.matchConstraintDefaultWidth
            int r7 = r14.matchConstraintMinWidth
            int r8 = r14.matchConstraintMaxWidth
            float r9 = r14.matchConstraintPercentWidth
            r13.setHorizontalMatchStyle(r6, r7, r8, r9)
            int r6 = r14.matchConstraintDefaultHeight
            int r7 = r14.matchConstraintMinHeight
            int r8 = r14.matchConstraintMaxHeight
            float r9 = r14.matchConstraintPercentHeight
            r13.setVerticalMatchStyle(r6, r7, r8, r9)
        L_0x040e:
            int r5 = r5 + 1
            goto L_0x00b7
        L_0x0412:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.ConstraintLayout.setChildrenConstraints():void");
    }

    private final ConstraintWidget getTargetWidget(int i) {
        ConstraintWidget constraintWidget;
        if (i == 0) {
            return this.mLayoutWidget;
        }
        View view = (View) this.mChildrenByIds.get(i);
        if (view == null) {
            view = findViewById(i);
            if (!(view == null || view == this || view.getParent() != this)) {
                onViewAdded(view);
            }
        }
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            constraintWidget = null;
        } else {
            constraintWidget = ((LayoutParams) view.getLayoutParams()).widget;
        }
        return constraintWidget;
    }

    public final ConstraintWidget getViewWidget(View view) {
        ConstraintWidget constraintWidget;
        if (view == this) {
            return this.mLayoutWidget;
        }
        if (view == null) {
            constraintWidget = null;
        } else {
            constraintWidget = ((LayoutParams) view.getLayoutParams()).widget;
        }
        return constraintWidget;
    }

    private void internalMeasureChildren(int i, int i2) {
        boolean z;
        boolean z2;
        int i3;
        int i4;
        int i5 = i;
        int i6 = i2;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = getChildAt(i7);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = layoutParams.widget;
                if (!layoutParams.isGuideline && !layoutParams.isHelper) {
                    constraintWidget.setVisibility(childAt.getVisibility());
                    int i8 = layoutParams.width;
                    int i9 = layoutParams.height;
                    if (layoutParams.horizontalDimensionFixed || layoutParams.verticalDimensionFixed || (!layoutParams.horizontalDimensionFixed && layoutParams.matchConstraintDefaultWidth == 1) || layoutParams.width == -1 || (!layoutParams.verticalDimensionFixed && (layoutParams.matchConstraintDefaultHeight == 1 || layoutParams.height == -1))) {
                        if (i8 == 0) {
                            i3 = getChildMeasureSpec(i5, paddingLeft, -2);
                            z2 = true;
                        } else if (i8 == -1) {
                            i3 = getChildMeasureSpec(i5, paddingLeft, -1);
                            z2 = false;
                        } else {
                            z2 = i8 == -2;
                            i3 = getChildMeasureSpec(i5, paddingLeft, i8);
                        }
                        if (i9 == 0) {
                            i4 = getChildMeasureSpec(i6, paddingTop, -2);
                            z = true;
                        } else if (i9 == -1) {
                            i4 = getChildMeasureSpec(i6, paddingTop, -1);
                            z = false;
                        } else {
                            z = i9 == -2;
                            i4 = getChildMeasureSpec(i6, paddingTop, i9);
                        }
                        childAt.measure(i3, i4);
                        Metrics metrics = this.mMetrics;
                        if (metrics != null) {
                            metrics.measures++;
                        }
                        constraintWidget.setWidthWrapContent(i8 == -2);
                        constraintWidget.setHeightWrapContent(i9 == -2);
                        i8 = childAt.getMeasuredWidth();
                        i9 = childAt.getMeasuredHeight();
                    } else {
                        z2 = false;
                        z = false;
                    }
                    constraintWidget.setWidth(i8);
                    constraintWidget.setHeight(i9);
                    if (z2) {
                        constraintWidget.setWrapWidth(i8);
                    }
                    if (z) {
                        constraintWidget.setWrapHeight(i9);
                    }
                    if (layoutParams.needsBaseline) {
                        int baseline = childAt.getBaseline();
                        if (baseline != -1) {
                            constraintWidget.setBaselineDistance(baseline);
                        }
                    }
                }
            }
        }
    }

    private void updatePostMeasures() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            if (childAt instanceof Placeholder) {
                ((Placeholder) childAt).updatePostMeasure(this);
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i2 = 0; i2 < size; i2++) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i2)).updatePostMeasure(this);
            }
        }
    }

    private void internalMeasureDimensions(int i, int i2) {
        long j;
        int i3;
        int i4;
        long j2;
        int i5;
        int i6;
        boolean z;
        boolean z2;
        boolean z3;
        int i7;
        int i8;
        LayoutParams layoutParams;
        int i9;
        int i10;
        ConstraintLayout constraintLayout = this;
        int i11 = i;
        int i12 = i2;
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int childCount = getChildCount();
        int i13 = 0;
        while (true) {
            j = 1;
            i3 = 8;
            if (i13 >= childCount) {
                break;
            }
            View childAt = constraintLayout.getChildAt(i13);
            if (childAt.getVisibility() == 8) {
                i9 = paddingTop;
            } else {
                LayoutParams layoutParams2 = (LayoutParams) childAt.getLayoutParams();
                ConstraintWidget constraintWidget = layoutParams2.widget;
                if (layoutParams2.isGuideline) {
                    i9 = paddingTop;
                } else if (layoutParams2.isHelper) {
                    i9 = paddingTop;
                } else {
                    constraintWidget.setVisibility(childAt.getVisibility());
                    int i14 = layoutParams2.width;
                    int i15 = layoutParams2.height;
                    if (i14 == 0 || i15 == 0) {
                        i9 = paddingTop;
                        constraintWidget.getResolutionWidth().invalidate();
                        constraintWidget.getResolutionHeight().invalidate();
                    } else {
                        boolean z4 = i14 == -2;
                        int childMeasureSpec = getChildMeasureSpec(i11, paddingLeft, i14);
                        boolean z5 = i15 == -2;
                        childAt.measure(childMeasureSpec, getChildMeasureSpec(i12, paddingTop, i15));
                        Metrics metrics = constraintLayout.mMetrics;
                        if (metrics != null) {
                            i9 = paddingTop;
                            metrics.measures++;
                            i10 = -2;
                        } else {
                            i9 = paddingTop;
                            i10 = -2;
                        }
                        constraintWidget.setWidthWrapContent(i14 == i10);
                        constraintWidget.setHeightWrapContent(i15 == i10);
                        int measuredWidth = childAt.getMeasuredWidth();
                        int measuredHeight = childAt.getMeasuredHeight();
                        constraintWidget.setWidth(measuredWidth);
                        constraintWidget.setHeight(measuredHeight);
                        if (z4) {
                            constraintWidget.setWrapWidth(measuredWidth);
                        }
                        if (z5) {
                            constraintWidget.setWrapHeight(measuredHeight);
                        }
                        if (layoutParams2.needsBaseline) {
                            int baseline = childAt.getBaseline();
                            if (baseline != -1) {
                                constraintWidget.setBaselineDistance(baseline);
                            }
                        }
                        if (layoutParams2.horizontalDimensionFixed && layoutParams2.verticalDimensionFixed) {
                            constraintWidget.getResolutionWidth().resolve(measuredWidth);
                            constraintWidget.getResolutionHeight().resolve(measuredHeight);
                        }
                    }
                }
            }
            i13++;
            paddingTop = i9;
            i12 = i2;
        }
        int i16 = paddingTop;
        constraintLayout.mLayoutWidget.solveGraph();
        int i17 = 0;
        while (i17 < childCount) {
            View childAt2 = constraintLayout.getChildAt(i17);
            if (childAt2.getVisibility() == i3) {
                i4 = i17;
                i5 = childCount;
                j2 = j;
                int i18 = i2;
            } else {
                LayoutParams layoutParams3 = (LayoutParams) childAt2.getLayoutParams();
                ConstraintWidget constraintWidget2 = layoutParams3.widget;
                if (layoutParams3.isGuideline) {
                    i4 = i17;
                    i5 = childCount;
                    j2 = j;
                    int i19 = i2;
                } else if (layoutParams3.isHelper) {
                    i4 = i17;
                    i5 = childCount;
                    j2 = j;
                    int i20 = i2;
                } else {
                    constraintWidget2.setVisibility(childAt2.getVisibility());
                    int i21 = layoutParams3.width;
                    int i22 = layoutParams3.height;
                    if (i21 == 0 || i22 == 0) {
                        ResolutionAnchor resolutionNode = constraintWidget2.getAnchor(Type.LEFT).getResolutionNode();
                        ResolutionAnchor resolutionNode2 = constraintWidget2.getAnchor(Type.RIGHT).getResolutionNode();
                        boolean z6 = (constraintWidget2.getAnchor(Type.LEFT).getTarget() == null || constraintWidget2.getAnchor(Type.RIGHT).getTarget() == null) ? false : true;
                        ResolutionAnchor resolutionNode3 = constraintWidget2.getAnchor(Type.TOP).getResolutionNode();
                        ResolutionAnchor resolutionNode4 = constraintWidget2.getAnchor(Type.BOTTOM).getResolutionNode();
                        i5 = childCount;
                        boolean z7 = (constraintWidget2.getAnchor(Type.TOP).getTarget() == null || constraintWidget2.getAnchor(Type.BOTTOM).getTarget() == null) ? false : true;
                        if (i21 != 0 || i22 != 0 || !z6 || !z7) {
                            i4 = i17;
                            LayoutParams layoutParams4 = layoutParams3;
                            boolean z8 = constraintLayout.mLayoutWidget.getHorizontalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT;
                            boolean z9 = constraintLayout.mLayoutWidget.getVerticalDimensionBehaviour() != DimensionBehaviour.WRAP_CONTENT;
                            if (!z8) {
                                constraintWidget2.getResolutionWidth().invalidate();
                            }
                            if (!z9) {
                                constraintWidget2.getResolutionHeight().invalidate();
                            }
                            if (i21 == 0) {
                                if (!z8 || !constraintWidget2.isSpreadWidth() || !z6 || !resolutionNode.isResolved() || !resolutionNode2.isResolved()) {
                                    i6 = getChildMeasureSpec(i11, paddingLeft, -2);
                                    z = true;
                                    z8 = false;
                                } else {
                                    i21 = (int) (resolutionNode2.getResolvedValue() - resolutionNode.getResolvedValue());
                                    constraintWidget2.getResolutionWidth().resolve(i21);
                                    i6 = getChildMeasureSpec(i11, paddingLeft, i21);
                                    z = false;
                                }
                            } else if (i21 == -1) {
                                i6 = getChildMeasureSpec(i11, paddingLeft, -1);
                                z = false;
                            } else {
                                z = i21 == -2;
                                i6 = getChildMeasureSpec(i11, paddingLeft, i21);
                            }
                            if (i22 != 0) {
                                int i23 = i2;
                                if (i22 == -1) {
                                    z2 = z9;
                                    i7 = getChildMeasureSpec(i23, i16, -1);
                                    z3 = false;
                                } else {
                                    z3 = i22 == -2;
                                    z2 = z9;
                                    i7 = getChildMeasureSpec(i23, i16, i22);
                                }
                            } else if (!z9 || !constraintWidget2.isSpreadHeight() || !z7 || !resolutionNode3.isResolved() || !resolutionNode4.isResolved()) {
                                i7 = getChildMeasureSpec(i2, i16, -2);
                                z3 = true;
                                z2 = false;
                            } else {
                                i22 = (int) (resolutionNode4.getResolvedValue() - resolutionNode3.getResolvedValue());
                                constraintWidget2.getResolutionHeight().resolve(i22);
                                z2 = z9;
                                i7 = getChildMeasureSpec(i2, i16, i22);
                                z3 = false;
                            }
                            childAt2.measure(i6, i7);
                            constraintLayout = this;
                            Metrics metrics2 = constraintLayout.mMetrics;
                            if (metrics2 != null) {
                                j2 = 1;
                                metrics2.measures++;
                                i8 = -2;
                            } else {
                                j2 = 1;
                                i8 = -2;
                            }
                            constraintWidget2.setWidthWrapContent(i21 == i8);
                            constraintWidget2.setHeightWrapContent(i22 == i8);
                            int measuredWidth2 = childAt2.getMeasuredWidth();
                            int measuredHeight2 = childAt2.getMeasuredHeight();
                            constraintWidget2.setWidth(measuredWidth2);
                            constraintWidget2.setHeight(measuredHeight2);
                            if (z) {
                                constraintWidget2.setWrapWidth(measuredWidth2);
                            }
                            if (z3) {
                                constraintWidget2.setWrapHeight(measuredHeight2);
                            }
                            if (z8) {
                                constraintWidget2.getResolutionWidth().resolve(measuredWidth2);
                            } else {
                                constraintWidget2.getResolutionWidth().remove();
                            }
                            if (z2) {
                                constraintWidget2.getResolutionHeight().resolve(measuredHeight2);
                                layoutParams = layoutParams4;
                            } else {
                                constraintWidget2.getResolutionHeight().remove();
                                layoutParams = layoutParams4;
                            }
                            if (layoutParams.needsBaseline) {
                                int baseline2 = childAt2.getBaseline();
                                if (baseline2 != -1) {
                                    constraintWidget2.setBaselineDistance(baseline2);
                                }
                            }
                        } else {
                            i4 = i17;
                            int i24 = i2;
                            j2 = 1;
                        }
                    } else {
                        i4 = i17;
                        i5 = childCount;
                        j2 = j;
                        int i25 = i2;
                    }
                }
            }
            i17 = i4 + 1;
            childCount = i5;
            j = j2;
            i3 = 8;
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        boolean z;
        int i3;
        boolean z2;
        boolean z3;
        boolean z4;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9 = i;
        int i10 = i2;
        System.currentTimeMillis();
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        this.mLayoutWidget.setX(paddingLeft);
        this.mLayoutWidget.setY(paddingTop);
        this.mLayoutWidget.setMaxWidth(this.mMaxWidth);
        this.mLayoutWidget.setMaxHeight(this.mMaxHeight);
        if (VERSION.SDK_INT >= 17) {
            this.mLayoutWidget.setRtl(getLayoutDirection() == 1);
        }
        setSelfDimensionBehaviour(i, i2);
        int width = this.mLayoutWidget.getWidth();
        int height = this.mLayoutWidget.getHeight();
        if (this.mDirtyHierarchy) {
            this.mDirtyHierarchy = false;
            updateHierarchy();
            z = true;
        } else {
            z = false;
        }
        boolean z5 = (this.mOptimizationLevel & 8) == 8;
        if (z5) {
            this.mLayoutWidget.preOptimize();
            this.mLayoutWidget.optimizeForDimensions(width, height);
            internalMeasureDimensions(i, i2);
        } else {
            internalMeasureChildren(i, i2);
        }
        updatePostMeasures();
        if (getChildCount() > 0 && z) {
            Analyzer.determineGroups(this.mLayoutWidget);
        }
        if (this.mLayoutWidget.mGroupsWrapOptimized) {
            if (this.mLayoutWidget.mHorizontalWrapOptimized && mode == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedWidth < size) {
                    ConstraintWidgetContainer constraintWidgetContainer = this.mLayoutWidget;
                    constraintWidgetContainer.setWidth(constraintWidgetContainer.mWrapFixedWidth);
                }
                this.mLayoutWidget.setHorizontalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized && mode2 == Integer.MIN_VALUE) {
                if (this.mLayoutWidget.mWrapFixedHeight < size2) {
                    ConstraintWidgetContainer constraintWidgetContainer2 = this.mLayoutWidget;
                    constraintWidgetContainer2.setHeight(constraintWidgetContainer2.mWrapFixedHeight);
                }
                this.mLayoutWidget.setVerticalDimensionBehaviour(DimensionBehaviour.FIXED);
            }
        }
        if ((this.mOptimizationLevel & 32) == 32) {
            int width2 = this.mLayoutWidget.getWidth();
            int height2 = this.mLayoutWidget.getHeight();
            if (this.mLastMeasureWidth != width2 && mode == 1073741824) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, width2);
            }
            if (this.mLastMeasureHeight != height2 && mode2 == 1073741824) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, height2);
            }
            if (this.mLayoutWidget.mHorizontalWrapOptimized && this.mLayoutWidget.mWrapFixedWidth > size) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 0, size);
            }
            if (this.mLayoutWidget.mVerticalWrapOptimized && this.mLayoutWidget.mWrapFixedHeight > size2) {
                Analyzer.setPosition(this.mLayoutWidget.mWidgetGroups, 1, size2);
            }
        }
        if (getChildCount() > 0) {
            solveLinearSystem("First pass");
        }
        int size3 = this.mVariableDimensionsWidgets.size();
        int paddingBottom = paddingTop + getPaddingBottom();
        int paddingRight = paddingLeft + getPaddingRight();
        if (size3 > 0) {
            boolean z6 = this.mLayoutWidget.getHorizontalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT;
            boolean z7 = this.mLayoutWidget.getVerticalDimensionBehaviour() == DimensionBehaviour.WRAP_CONTENT;
            int max = Math.max(this.mLayoutWidget.getWidth(), this.mMinWidth);
            int max2 = Math.max(this.mLayoutWidget.getHeight(), this.mMinHeight);
            int i11 = 0;
            boolean z8 = false;
            int i12 = 0;
            while (i11 < size3) {
                ConstraintWidget constraintWidget = (ConstraintWidget) this.mVariableDimensionsWidgets.get(i11);
                int i13 = size3;
                View view = (View) constraintWidget.getCompanionWidget();
                if (view == null) {
                    i5 = width;
                    z4 = z8;
                    i4 = height;
                    i6 = i12;
                } else {
                    i4 = height;
                    LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
                    i5 = width;
                    if (layoutParams.isHelper) {
                        z4 = z8;
                        i6 = i12;
                    } else if (layoutParams.isGuideline) {
                        z4 = z8;
                        i6 = i12;
                    } else {
                        z4 = z8;
                        if (view.getVisibility() == 8) {
                            i6 = i12;
                        } else if (!z5 || !constraintWidget.getResolutionWidth().isResolved() || !constraintWidget.getResolutionHeight().isResolved()) {
                            if (layoutParams.width != -2 || !layoutParams.horizontalDimensionFixed) {
                                i7 = MeasureSpec.makeMeasureSpec(constraintWidget.getWidth(), 1073741824);
                            } else {
                                i7 = getChildMeasureSpec(i9, paddingRight, layoutParams.width);
                            }
                            if (layoutParams.height != -2 || !layoutParams.verticalDimensionFixed) {
                                i8 = MeasureSpec.makeMeasureSpec(constraintWidget.getHeight(), 1073741824);
                            } else {
                                i8 = getChildMeasureSpec(i10, paddingBottom, layoutParams.height);
                            }
                            view.measure(i7, i8);
                            Metrics metrics = this.mMetrics;
                            if (metrics != null) {
                                metrics.additionalMeasures++;
                            }
                            int measuredWidth = view.getMeasuredWidth();
                            int measuredHeight = view.getMeasuredHeight();
                            if (measuredWidth != constraintWidget.getWidth()) {
                                constraintWidget.setWidth(measuredWidth);
                                if (z5) {
                                    constraintWidget.getResolutionWidth().resolve(measuredWidth);
                                }
                                if (z6 && constraintWidget.getRight() > max) {
                                    max = Math.max(max, constraintWidget.getRight() + constraintWidget.getAnchor(Type.RIGHT).getMargin());
                                }
                                z4 = true;
                            }
                            if (measuredHeight != constraintWidget.getHeight()) {
                                constraintWidget.setHeight(measuredHeight);
                                if (z5) {
                                    constraintWidget.getResolutionHeight().resolve(measuredHeight);
                                }
                                if (z7 && constraintWidget.getBottom() > max2) {
                                    max2 = Math.max(max2, constraintWidget.getBottom() + constraintWidget.getAnchor(Type.BOTTOM).getMargin());
                                }
                                z4 = true;
                            }
                            if (layoutParams.needsBaseline) {
                                int baseline = view.getBaseline();
                                if (!(baseline == -1 || baseline == constraintWidget.getBaselineDistance())) {
                                    constraintWidget.setBaselineDistance(baseline);
                                    z4 = true;
                                }
                            }
                            if (VERSION.SDK_INT >= 11) {
                                i12 = combineMeasuredStates(i12, view.getMeasuredState());
                            } else {
                                int i14 = i12;
                            }
                            z8 = z4;
                            i11++;
                            width = i5;
                            size3 = i13;
                            height = i4;
                            i9 = i;
                        } else {
                            i6 = i12;
                        }
                    }
                }
                i12 = i6;
                z8 = z4;
                i11++;
                width = i5;
                size3 = i13;
                height = i4;
                i9 = i;
            }
            int i15 = size3;
            int i16 = width;
            int i17 = height;
            i3 = i12;
            if (z8) {
                this.mLayoutWidget.setWidth(i16);
                this.mLayoutWidget.setHeight(i17);
                if (z5) {
                    this.mLayoutWidget.solveGraph();
                }
                solveLinearSystem("2nd pass");
                if (this.mLayoutWidget.getWidth() < max) {
                    this.mLayoutWidget.setWidth(max);
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (this.mLayoutWidget.getHeight() < max2) {
                    this.mLayoutWidget.setHeight(max2);
                    z3 = true;
                } else {
                    z3 = z2;
                }
                if (z3) {
                    solveLinearSystem("3rd pass");
                }
            }
            int i18 = i15;
            for (int i19 = 0; i19 < i18; i19++) {
                ConstraintWidget constraintWidget2 = (ConstraintWidget) this.mVariableDimensionsWidgets.get(i19);
                View view2 = (View) constraintWidget2.getCompanionWidget();
                if (view2 != null) {
                    if (view2.getMeasuredWidth() != constraintWidget2.getWidth() || view2.getMeasuredHeight() != constraintWidget2.getHeight()) {
                        if (constraintWidget2.getVisibility() != 8) {
                            view2.measure(MeasureSpec.makeMeasureSpec(constraintWidget2.getWidth(), 1073741824), MeasureSpec.makeMeasureSpec(constraintWidget2.getHeight(), 1073741824));
                            Metrics metrics2 = this.mMetrics;
                            if (metrics2 != null) {
                                metrics2.additionalMeasures++;
                            }
                        }
                    }
                }
            }
        } else {
            i3 = 0;
        }
        int width3 = this.mLayoutWidget.getWidth() + paddingRight;
        int height3 = this.mLayoutWidget.getHeight() + paddingBottom;
        if (VERSION.SDK_INT >= 11) {
            int resolveSizeAndState = resolveSizeAndState(height3, i10, i3 << 16) & 16777215;
            int min = Math.min(this.mMaxWidth, resolveSizeAndState(width3, i, i3) & 16777215);
            int min2 = Math.min(this.mMaxHeight, resolveSizeAndState);
            if (this.mLayoutWidget.isWidthMeasuredTooSmall()) {
                min |= C.DEFAULT_MUXED_BUFFER_SIZE;
            }
            if (this.mLayoutWidget.isHeightMeasuredTooSmall()) {
                min2 |= C.DEFAULT_MUXED_BUFFER_SIZE;
            }
            setMeasuredDimension(min, min2);
            this.mLastMeasureWidth = min;
            this.mLastMeasureHeight = min2;
            return;
        }
        setMeasuredDimension(width3, height3);
        this.mLastMeasureWidth = width3;
        this.mLastMeasureHeight = height3;
    }

    private void setSelfDimensionBehaviour(int i, int i2) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        DimensionBehaviour dimensionBehaviour = DimensionBehaviour.FIXED;
        DimensionBehaviour dimensionBehaviour2 = DimensionBehaviour.FIXED;
        getLayoutParams();
        if (mode == Integer.MIN_VALUE) {
            dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
        } else if (mode != 0) {
            size = mode != 1073741824 ? 0 : Math.min(this.mMaxWidth, size) - paddingLeft;
        } else {
            dimensionBehaviour = DimensionBehaviour.WRAP_CONTENT;
            size = 0;
        }
        if (mode2 == Integer.MIN_VALUE) {
            dimensionBehaviour2 = DimensionBehaviour.WRAP_CONTENT;
        } else if (mode2 != 0) {
            size2 = mode2 != 1073741824 ? 0 : Math.min(this.mMaxHeight, size2) - paddingTop;
        } else {
            dimensionBehaviour2 = DimensionBehaviour.WRAP_CONTENT;
            size2 = 0;
        }
        this.mLayoutWidget.setMinWidth(0);
        this.mLayoutWidget.setMinHeight(0);
        this.mLayoutWidget.setHorizontalDimensionBehaviour(dimensionBehaviour);
        this.mLayoutWidget.setWidth(size);
        this.mLayoutWidget.setVerticalDimensionBehaviour(dimensionBehaviour2);
        this.mLayoutWidget.setHeight(size2);
        this.mLayoutWidget.setMinWidth((this.mMinWidth - getPaddingLeft()) - getPaddingRight());
        this.mLayoutWidget.setMinHeight((this.mMinHeight - getPaddingTop()) - getPaddingBottom());
    }

    /* access modifiers changed from: protected */
    public void solveLinearSystem(String str) {
        this.mLayoutWidget.layout();
        Metrics metrics = this.mMetrics;
        if (metrics != null) {
            metrics.resolutions++;
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int childCount = getChildCount();
        boolean isInEditMode = isInEditMode();
        for (int i5 = 0; i5 < childCount; i5++) {
            View childAt = getChildAt(i5);
            LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
            ConstraintWidget constraintWidget = layoutParams.widget;
            if ((childAt.getVisibility() != 8 || layoutParams.isGuideline || layoutParams.isHelper || isInEditMode) && !layoutParams.isInPlaceholder) {
                int drawX = constraintWidget.getDrawX();
                int drawY = constraintWidget.getDrawY();
                int width = constraintWidget.getWidth() + drawX;
                int height = constraintWidget.getHeight() + drawY;
                childAt.layout(drawX, drawY, width, height);
                if (childAt instanceof Placeholder) {
                    View content = ((Placeholder) childAt).getContent();
                    if (content != null) {
                        content.setVisibility(0);
                        content.layout(drawX, drawY, width, height);
                    }
                }
            }
        }
        int size = this.mConstraintHelpers.size();
        if (size > 0) {
            for (int i6 = 0; i6 < size; i6++) {
                ((ConstraintHelper) this.mConstraintHelpers.get(i6)).updatePostLayout(this);
            }
        }
    }

    public void setOptimizationLevel(int i) {
        this.mLayoutWidget.setOptimizationLevel(i);
    }

    public int getOptimizationLevel() {
        return this.mLayoutWidget.getOptimizationLevel();
    }

    public LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new LayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    /* access modifiers changed from: protected */
    public android.view.ViewGroup.LayoutParams generateLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return new LayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public boolean checkLayoutParams(android.view.ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    public void setConstraintSet(ConstraintSet constraintSet) {
        this.mConstraintSet = constraintSet;
    }

    public View getViewById(int i) {
        return (View) this.mChildrenByIds.get(i);
    }

    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (isInEditMode()) {
            int childCount = getChildCount();
            float width = (float) getWidth();
            float height = (float) getHeight();
            for (int i = 0; i < childCount; i++) {
                View childAt = getChildAt(i);
                if (childAt.getVisibility() != 8) {
                    Object tag = childAt.getTag();
                    if (tag != null && (tag instanceof String)) {
                        String[] split = ((String) tag).split(",");
                        if (split.length == 4) {
                            int parseInt = Integer.parseInt(split[0]);
                            int parseInt2 = Integer.parseInt(split[1]);
                            int i2 = (int) ((((float) parseInt) / 1080.0f) * width);
                            int i3 = (int) ((((float) parseInt2) / 1920.0f) * height);
                            int parseInt3 = (int) ((((float) Integer.parseInt(split[2])) / 1080.0f) * width);
                            int parseInt4 = (int) ((((float) Integer.parseInt(split[3])) / 1920.0f) * height);
                            Paint paint = new Paint();
                            paint.setColor(-65536);
                            float f = (float) i2;
                            float f2 = (float) (i2 + parseInt3);
                            Canvas canvas2 = canvas;
                            float f3 = (float) i3;
                            float f4 = f;
                            float f5 = f;
                            float f6 = f3;
                            Paint paint2 = paint;
                            float f7 = f2;
                            Paint paint3 = paint2;
                            canvas2.drawLine(f4, f6, f7, f3, paint3);
                            float f8 = (float) (i3 + parseInt4);
                            float f9 = f2;
                            float f10 = f8;
                            canvas2.drawLine(f9, f6, f7, f10, paint3);
                            float f11 = f8;
                            float f12 = f5;
                            canvas2.drawLine(f9, f11, f12, f10, paint3);
                            float f13 = f5;
                            canvas2.drawLine(f13, f11, f12, f3, paint3);
                            Paint paint4 = paint2;
                            paint4.setColor(-16711936);
                            Paint paint5 = paint4;
                            float f14 = f2;
                            Paint paint6 = paint5;
                            canvas2.drawLine(f13, f3, f14, f8, paint6);
                            canvas2.drawLine(f13, f8, f14, f3, paint6);
                        }
                    }
                }
            }
            return;
        }
    }

    public void requestLayout() {
        super.requestLayout();
        this.mDirtyHierarchy = true;
        this.mLastMeasureWidth = -1;
        this.mLastMeasureHeight = -1;
        this.mLastMeasureWidthSize = -1;
        this.mLastMeasureHeightSize = -1;
        this.mLastMeasureWidthMode = 0;
        this.mLastMeasureHeightMode = 0;
    }
}
