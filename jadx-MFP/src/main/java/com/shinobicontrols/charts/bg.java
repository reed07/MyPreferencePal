package com.shinobicontrols.charts;

import android.graphics.Rect;
import android.view.ViewGroup.MarginLayoutParams;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.shinobicontrols.charts.Axis.Position;

abstract class bg {
    private static bg a = new bg() {
        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, MarginLayoutParams marginLayoutParams, int i, int i2, float f) {
            rect.offset(0, (int) (((float) (marginLayoutParams.bottomMargin + (i2 / 2))) + (f / 2.0f)));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
            rect.bottom -= i2;
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position, int i) {
            if (position == Position.NORMAL) {
                return i;
            }
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return (int) Math.max(BitmapDescriptorFactory.HUE_RED, Math.max(BitmapDescriptorFactory.HUE_RED, (((float) (i / 2)) - (f / 2.0f)) - ((float) a(position, i2))) - ((float) marginLayoutParams.bottomMargin));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
            rect.left += i;
            rect.top += i2;
            rect.right -= i3;
            rect.bottom -= i4;
        }
    };
    private static bg b = new bg() {
        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return i;
        }

        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
            rect.bottom -= i2;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
            rect.left += i;
            rect.right -= i3;
        }
    };
    private static bg c = new bg() {
        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
        }

        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
            rect.left += i;
            rect.top += i2;
            rect.right -= i3;
            rect.bottom -= i4;
        }
    };
    private static bg d = new bg() {
        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, MarginLayoutParams marginLayoutParams, int i, int i2, float f) {
            rect.offset(-((int) (((float) (marginLayoutParams.leftMargin + (i / 2))) + (f / 2.0f))), 0);
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
            rect.left += i;
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position, int i) {
            if (position == Position.NORMAL) {
                return i;
            }
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return (int) Math.max(BitmapDescriptorFactory.HUE_RED, Math.max(BitmapDescriptorFactory.HUE_RED, (((float) (i / 2)) - (f / 2.0f)) - ((float) a(position, i2))) - ((float) marginLayoutParams.leftMargin));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
            rect.left += i;
            rect.top += i2;
            rect.right -= i3;
            rect.bottom -= i4;
        }
    };
    private static bg e = new bg() {
        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return i;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
            rect.left += i;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
            rect.top += i2;
            rect.bottom -= i4;
        }
    };
    private static bg f = new bg() {
        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position, int i) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
        }

        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
            rect.setEmpty();
        }
    };
    private static bg g = new bg() {
        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, MarginLayoutParams marginLayoutParams, int i, int i2, float f) {
            rect.offset((int) (((float) (marginLayoutParams.rightMargin + (i / 2))) + (f / 2.0f)), 0);
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
            rect.right -= i;
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position, int i) {
            if (position == Position.REVERSE) {
                return i;
            }
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return (int) Math.max(BitmapDescriptorFactory.HUE_RED, Math.max(BitmapDescriptorFactory.HUE_RED, (((float) (i / 2)) - (f / 2.0f)) - ((float) a(position, i2))) - ((float) marginLayoutParams.rightMargin));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
            rect.left += i;
            rect.top += i2;
            rect.right -= i3;
            rect.bottom -= i4;
        }
    };
    private static bg h = new bg() {
        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return i;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
            rect.right -= i;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
            rect.top += i2;
            rect.bottom -= i4;
        }
    };
    private static bg i = new bg() {
        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, MarginLayoutParams marginLayoutParams, int i, int i2, float f) {
            rect.offset(0, -((int) (((float) (marginLayoutParams.topMargin + (i2 / 2))) + (f / 2.0f))));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
            rect.top += i2;
        }

        /* access modifiers changed from: 0000 */
        public int a(Position position, int i) {
            if (position == Position.REVERSE) {
                return i;
            }
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return (int) Math.max(BitmapDescriptorFactory.HUE_RED, Math.max(BitmapDescriptorFactory.HUE_RED, (((float) (i / 2)) - (f / 2.0f)) - ((float) a(position, i2))) - ((float) marginLayoutParams.topMargin));
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
            rect.left += i;
            rect.top += i2;
            rect.right -= i3;
            rect.bottom -= i4;
        }
    };
    private static bg j = new bg() {
        /* access modifiers changed from: 0000 */
        public int a(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return i;
        }

        /* access modifiers changed from: 0000 */
        public int b(int i, float f, Position position, int i2, MarginLayoutParams marginLayoutParams) {
            return 0;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2) {
            rect.top += i2;
        }

        /* access modifiers changed from: 0000 */
        public void a(Rect rect, int i, int i2, int i3, int i4) {
            rect.left += i;
            rect.right -= i3;
        }
    };

    /* access modifiers changed from: 0000 */
    public abstract int a(int i2, float f2, Position position, int i3, MarginLayoutParams marginLayoutParams);

    /* access modifiers changed from: 0000 */
    public int a(Position position, int i2) {
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public abstract void a(Rect rect, int i2, int i3);

    /* access modifiers changed from: 0000 */
    public abstract void a(Rect rect, int i2, int i3, int i4, int i5);

    /* access modifiers changed from: 0000 */
    public void a(Rect rect, MarginLayoutParams marginLayoutParams, int i2, int i3, float f2) {
    }

    /* access modifiers changed from: 0000 */
    public abstract int b(int i2, float f2, Position position, int i3, MarginLayoutParams marginLayoutParams);

    bg() {
    }

    static bg a(Legend legend) {
        bg bgVar;
        if (legend == null || legend.getPosition() == null || legend.getPlacement() == null) {
            return f;
        }
        switch (legend.getPosition()) {
            case TOP_LEFT:
            case MIDDLE_LEFT:
            case BOTTOM_LEFT:
                switch (legend.getPlacement()) {
                    case OUTSIDE_PLOT_AREA:
                        bgVar = e;
                        break;
                    case ON_PLOT_AREA_BORDER:
                        bgVar = d;
                        break;
                    case INSIDE_PLOT_AREA:
                        bgVar = c;
                        break;
                    default:
                        StringBuilder sb = new StringBuilder();
                        sb.append("Legend Placement invalid:");
                        sb.append(legend.getPlacement());
                        throw new AssertionError(sb.toString());
                }
            case TOP_RIGHT:
            case MIDDLE_RIGHT:
            case BOTTOM_RIGHT:
                switch (legend.getPlacement()) {
                    case OUTSIDE_PLOT_AREA:
                        bgVar = h;
                        break;
                    case ON_PLOT_AREA_BORDER:
                        bgVar = g;
                        break;
                    case INSIDE_PLOT_AREA:
                        bgVar = c;
                        break;
                    default:
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Legend Placement invalid:");
                        sb2.append(legend.getPlacement());
                        throw new AssertionError(sb2.toString());
                }
            case TOP_CENTER:
                switch (legend.getPlacement()) {
                    case OUTSIDE_PLOT_AREA:
                        bgVar = j;
                        break;
                    case ON_PLOT_AREA_BORDER:
                        bgVar = i;
                        break;
                    case INSIDE_PLOT_AREA:
                        bgVar = c;
                        break;
                    default:
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Legend Placement invalid:");
                        sb3.append(legend.getPlacement());
                        throw new AssertionError(sb3.toString());
                }
            case BOTTOM_CENTER:
                switch (legend.getPlacement()) {
                    case OUTSIDE_PLOT_AREA:
                        bgVar = b;
                        break;
                    case ON_PLOT_AREA_BORDER:
                        bgVar = a;
                        break;
                    case INSIDE_PLOT_AREA:
                        bgVar = c;
                        break;
                    default:
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("Legend Placement invalid:");
                        sb4.append(legend.getPlacement());
                        throw new AssertionError(sb4.toString());
                }
            default:
                StringBuilder sb5 = new StringBuilder();
                sb5.append("Legend Position invalid:");
                sb5.append(legend.getPosition());
                throw new AssertionError(sb5.toString());
        }
        return bgVar;
    }
}
