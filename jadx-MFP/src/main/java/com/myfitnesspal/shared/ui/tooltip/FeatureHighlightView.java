package com.myfitnesspal.shared.ui.tooltip;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewManager;
import android.view.ViewParent;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ScrollView;
import com.brightcove.player.event.AbstractEvent;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.myfitnesspal.shared.extension.ViewExtKt;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlight.Gravity;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlight.HighlightShape;
import com.uacf.core.util.Ln;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 F2\u00020\u0001:\u0002FGB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u00101\u001a\u000202H\u0002J\b\u00103\u001a\u000202H\u0002J\b\u00104\u001a\u000202H\u0002J\u0006\u00105\u001a\u000202J\u0012\u00106\u001a\u0002022\b\u00107\u001a\u0004\u0018\u000108H\u0014J\u001a\u00109\u001a\u00020)2\u0006\u0010:\u001a\u00020\u001b2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u0012\u0010=\u001a\u00020)2\b\u0010;\u001a\u0004\u0018\u00010>H\u0016J\b\u0010?\u001a\u000202H\u0002J\b\u0010@\u001a\u00020)H\u0002J\b\u0010A\u001a\u000202H\u0002J\f\u0010B\u001a\u00020)*\u00020\u0001H\u0002J\u001d\u0010C\u001a\u000202*\u00020\u00012\u000e\b\u0004\u0010D\u001a\b\u0012\u0004\u0012\u0002020EH\bR\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020)X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010+\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0017X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\u0004\u0018\u00010.*\u00020\u00018BX\u0004¢\u0006\u0006\u001a\u0004\b/\u00100¨\u0006H"}, d2 = {"Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView;", "Landroid/view/View;", "context", "Landroid/content/Context;", "parent", "Landroid/view/ViewManager;", "featureHighlight", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight;", "listener", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$Listener;", "(Landroid/content/Context;Landroid/view/ViewManager;Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight;Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$Listener;)V", "buttonHitRectPadding", "", "buttonPadding", "contentBackgroundPaint", "Landroid/graphics/Paint;", "contentBounds", "Landroid/graphics/Rect;", "contentMargin", "contentPadding", "descriptionLayout", "Landroid/text/StaticLayout;", "descriptionPaint", "Landroid/text/TextPaint;", "featureBounds", "featurePaint", "lastTouchX", "", "lastTouchY", "maxContentWidth", "negativeButtonBounds", "negativeButtonLayout", "negativeButtonPaint", "pointerHeight", "pointerMargin", "pointerPath", "Landroid/graphics/Path;", "positiveButtonBounds", "positiveButtonLayout", "positiveButtonPaint", "shouldHighlightOnTop", "", "textPadding", "titleLayout", "titlePaint", "parentScrollView", "Landroid/widget/ScrollView;", "getParentScrollView", "(Landroid/view/View;)Landroid/widget/ScrollView;", "calculatePointerPath", "", "calculateTextBounds", "calculateViewBounds", "dismiss", "onDraw", "canvas", "Landroid/graphics/Canvas;", "onKeyUp", "keyCode", "event", "Landroid/view/KeyEvent;", "onTouchEvent", "Landroid/view/MotionEvent;", "removeSelf", "shouldDisplayContentOnTop", "updateTextLayouts", "isVisibleInScrollView", "onLaidOut", "function", "Lkotlin/Function0;", "Companion", "Listener", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FeatureHighlightView.kt */
public final class FeatureHighlightView extends View {
    public static final float BUTTON_PADDING_DP = 24.0f;
    public static final float CONTENT_MARGIN_DP = 8.0f;
    public static final float CONTENT_PADDING_DP = 16.0f;
    public static final float CONTENT_SHADOW_RADIUS_DP = 12.0f;
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static final String FONT_PATH_ROBOTO_MEDIUM = "fonts/Roboto-Medium.ttf";
    public static final float MAX_CONTENT_WIDTH_DP = 280.0f;
    public static final float POINTER_HEIGHT_DP = 8.0f;
    public static final float POINTER_MARGIN_DP = 40.0f;
    public static final int SCROLL_BOTTOM_GAP_PX = 75;
    public static final int SHADOW_COLOR = 1275068416;
    public static final float SHADOW_Y_OFFSET = 12.0f;
    public static final float TEXT_PADDING_DP = 8.0f;
    private HashMap _$_findViewCache;
    private final float buttonHitRectPadding = (this.buttonPadding / ((float) 2));
    private final float buttonPadding = ViewExtKt.dp((View) this, 24.0f);
    private final Paint contentBackgroundPaint = new Paint();
    /* access modifiers changed from: private */
    public Rect contentBounds = new Rect();
    private final float contentMargin = ViewExtKt.dp((View) this, 8.0f);
    private final float contentPadding = ViewExtKt.dp((View) this, 16.0f);
    private StaticLayout descriptionLayout;
    private final TextPaint descriptionPaint = new TextPaint();
    /* access modifiers changed from: private */
    public final Rect featureBounds = new Rect();
    /* access modifiers changed from: private */
    public final FeatureHighlight featureHighlight;
    private final Paint featurePaint = new Paint();
    /* access modifiers changed from: private */
    public int lastTouchX;
    /* access modifiers changed from: private */
    public int lastTouchY;
    /* access modifiers changed from: private */
    public final Listener listener;
    private final int maxContentWidth = ((int) ViewExtKt.dp((View) this, 280.0f));
    /* access modifiers changed from: private */
    public final Rect negativeButtonBounds = new Rect();
    private StaticLayout negativeButtonLayout;
    private final TextPaint negativeButtonPaint = new TextPaint();
    private final ViewManager parent;
    private final int pointerHeight = ((int) ViewExtKt.dp((View) this, 8.0f));
    private final int pointerMargin = ((int) ViewExtKt.dp((View) this, 40.0f));
    private final Path pointerPath = new Path();
    /* access modifiers changed from: private */
    public final Rect positiveButtonBounds = new Rect();
    private StaticLayout positiveButtonLayout;
    private final TextPaint positiveButtonPaint = new TextPaint();
    private boolean shouldHighlightOnTop;
    private final float textPadding = ViewExtKt.dp((View) this, 8.0f);
    private StaticLayout titleLayout;
    private final TextPaint titlePaint = new TextPaint();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eXT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$Companion;", "", "()V", "BUTTON_PADDING_DP", "", "CONTENT_MARGIN_DP", "CONTENT_PADDING_DP", "CONTENT_SHADOW_RADIUS_DP", "FONT_PATH_ROBOTO_MEDIUM", "", "MAX_CONTENT_WIDTH_DP", "POINTER_HEIGHT_DP", "POINTER_MARGIN_DP", "SCROLL_BOTTOM_GAP_PX", "", "SHADOW_COLOR", "SHADOW_Y_OFFSET", "TEXT_PADDING_DP", "showFor", "", "activity", "Landroid/app/Activity;", "featureHighlight", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight;", "listener", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$Listener;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FeatureHighlightView.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void showFor(@NotNull Activity activity, @NotNull FeatureHighlight featureHighlight, @NotNull Listener listener) {
            Intrinsics.checkParameterIsNotNull(activity, AbstractEvent.ACTIVITY);
            Intrinsics.checkParameterIsNotNull(featureHighlight, "featureHighlight");
            Intrinsics.checkParameterIsNotNull(listener, CastExtraArgs.LISTENER);
            Object systemService = activity.getSystemService("window");
            if (systemService != null) {
                WindowManager windowManager = (WindowManager) systemService;
                LayoutParams layoutParams = new LayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -1;
                layoutParams.x = 0;
                layoutParams.y = 0;
                layoutParams.gravity = 8388659;
                layoutParams.type = 2;
                layoutParams.format = 1;
                layoutParams.flags = 0;
                windowManager.addView(new FeatureHighlightView(activity, windowManager, featureHighlight, listener), layoutParams);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
        }

        public final void showFor(@NotNull Activity activity, @NotNull FeatureHighlight featureHighlight) {
            Intrinsics.checkParameterIsNotNull(activity, AbstractEvent.ACTIVITY);
            Intrinsics.checkParameterIsNotNull(featureHighlight, "featureHighlight");
            showFor(activity, featureHighlight, new Listener());
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\r\u001a\u00020\u0004H\u0016J\u0010\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u000f"}, d2 = {"Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$Listener;", "", "()V", "onBackPressed", "", "view", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView;", "onContentClick", "onFeatureAbsent", "onFeatureClick", "onNegativeButtonClick", "onOutsideClick", "onPositiveButtonClick", "onViewDismissed", "onViewShow", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FeatureHighlightView.kt */
    public static class Listener {
        public void onContentClick(@NotNull FeatureHighlightView featureHighlightView) {
            Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        }

        public void onFeatureAbsent(@NotNull FeatureHighlightView featureHighlightView) {
            Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        }

        public void onOutsideClick(@NotNull FeatureHighlightView featureHighlightView) {
            Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        }

        public void onViewDismissed() {
        }

        public void onViewShow(@NotNull FeatureHighlightView featureHighlightView) {
            Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
        }

        public void onPositiveButtonClick(@NotNull FeatureHighlightView featureHighlightView) {
            Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
            featureHighlightView.dismiss();
        }

        public void onNegativeButtonClick(@NotNull FeatureHighlightView featureHighlightView) {
            Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
            featureHighlightView.dismiss();
        }

        public void onFeatureClick(@NotNull FeatureHighlightView featureHighlightView) {
            Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
            featureHighlightView.dismiss();
        }

        public void onBackPressed(@NotNull FeatureHighlightView featureHighlightView) {
            Intrinsics.checkParameterIsNotNull(featureHighlightView, Promotion.ACTION_VIEW);
            featureHighlightView.dismiss();
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public FeatureHighlightView(@NotNull Context context, @NotNull ViewManager viewManager, @NotNull FeatureHighlight featureHighlight2, @NotNull Listener listener2) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(viewManager, "parent");
        Intrinsics.checkParameterIsNotNull(featureHighlight2, "featureHighlight");
        Intrinsics.checkParameterIsNotNull(listener2, CastExtraArgs.LISTENER);
        super(context);
        this.parent = viewManager;
        this.featureHighlight = featureHighlight2;
        this.listener = listener2;
        Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), FONT_PATH_ROBOTO_MEDIUM);
        this.featurePaint.setAntiAlias(true);
        this.featurePaint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
        this.contentBackgroundPaint.setAntiAlias(true);
        this.contentBackgroundPaint.setColor(-1);
        this.contentBackgroundPaint.setStyle(Style.FILL_AND_STROKE);
        this.contentBackgroundPaint.setShadowLayer(ViewExtKt.dp((View) this, 12.0f), BitmapDescriptorFactory.HUE_RED, ViewExtKt.dp((View) this, 12.0f), SHADOW_COLOR);
        this.titlePaint.setAntiAlias(true);
        this.titlePaint.setTextSize(ViewExtKt.sp(this, (float) this.featureHighlight.getTitleTextSize()));
        this.titlePaint.setColor(-16777216);
        this.titlePaint.setTypeface(createFromAsset);
        this.descriptionPaint.setAntiAlias(true);
        this.descriptionPaint.setTextSize(ViewExtKt.sp(this, (float) this.featureHighlight.getDescriptionTextSize()));
        this.descriptionPaint.setColor(this.featureHighlight.getTextColor());
        this.descriptionPaint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 0));
        this.positiveButtonPaint.setAntiAlias(true);
        this.positiveButtonPaint.setTextSize(ViewExtKt.sp(this, (float) this.featureHighlight.getButtonTextSize()));
        this.positiveButtonPaint.setColor(this.featureHighlight.getPositiveButtonTextColor());
        this.positiveButtonPaint.setTypeface(createFromAsset);
        this.negativeButtonPaint.setAntiAlias(true);
        this.negativeButtonPaint.setTextSize(ViewExtKt.sp(this, (float) this.featureHighlight.getButtonTextSize()));
        this.negativeButtonPaint.setColor(this.featureHighlight.getNegativeButtonTextColor());
        this.negativeButtonPaint.setTypeface(createFromAsset);
        setFocusableInTouchMode(true);
        setClickable(true);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeatureHighlightView this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(View view) {
                if (!this.this$0.positiveButtonBounds.isEmpty() && this.this$0.positiveButtonBounds.contains(this.this$0.lastTouchX, this.this$0.lastTouchY)) {
                    this.this$0.listener.onPositiveButtonClick(this.this$0);
                } else if (!this.this$0.negativeButtonBounds.isEmpty() && this.this$0.negativeButtonBounds.contains(this.this$0.lastTouchX, this.this$0.lastTouchY)) {
                    this.this$0.listener.onNegativeButtonClick(this.this$0);
                } else if (!this.this$0.contentBounds.isEmpty() && this.this$0.contentBounds.contains(this.this$0.lastTouchX, this.this$0.lastTouchY)) {
                    this.this$0.listener.onContentClick(this.this$0);
                } else if (this.this$0.featureBounds.isEmpty() || !this.this$0.featureBounds.contains(this.this$0.lastTouchX, this.this$0.lastTouchY)) {
                    this.this$0.listener.onOutsideClick(this.this$0);
                } else {
                    this.this$0.listener.onFeatureClick(this.this$0);
                }
            }
        });
        getViewTreeObserver().addOnGlobalLayoutListener(new FeatureHighlightView$$special$$inlined$onLaidOut$1(this));
    }

    /* access modifiers changed from: protected */
    public void onDraw(@Nullable Canvas canvas) {
        if (this.featureHighlight.getView().getVisibility() != 0) {
            removeSelf();
            this.listener.onFeatureAbsent(this);
        } else if (canvas == null) {
            invalidate();
        } else {
            IntRef intRef = new IntRef();
            canvas.clipRect(0, 0, getWidth(), getHeight());
            canvas.drawColor(this.featureHighlight.getDimColor());
            if (this.featureHighlight.getShouldHighlightFeatureView()) {
                HighlightShape highlightShape = this.featureHighlight.getHighlightShape();
                if (highlightShape != null) {
                    switch (highlightShape) {
                        case CIRCLE:
                            canvas.drawCircle(this.featureBounds.exactCenterX(), this.featureBounds.exactCenterY(), (float) (this.featureBounds.height() / 2), this.featurePaint);
                            break;
                        case RECT:
                            canvas.drawRect(this.featureBounds, this.featurePaint);
                            break;
                    }
                } else {
                    canvas.drawRect(this.featureBounds, this.featurePaint);
                }
            }
            canvas.drawPath(this.pointerPath, this.contentBackgroundPaint);
            canvas.drawRect((float) this.contentBounds.left, (float) this.contentBounds.top, (float) this.contentBounds.right, (float) this.contentBounds.bottom, this.contentBackgroundPaint);
            intRef.element = canvas.save();
            StaticLayout staticLayout = this.titleLayout;
            if (staticLayout != null) {
                canvas.translate(((float) this.contentBounds.left) + this.contentPadding, ((float) this.contentBounds.top) + this.contentPadding);
                staticLayout.draw(canvas);
            }
            StaticLayout staticLayout2 = this.descriptionLayout;
            if (staticLayout2 != null) {
                canvas.restoreToCount(intRef.element);
                intRef.element = canvas.save();
                float f = ((float) this.contentBounds.left) + this.contentPadding;
                float f2 = ((float) this.contentBounds.top) + this.contentPadding;
                StaticLayout staticLayout3 = this.titleLayout;
                canvas.translate(f, f2 + (staticLayout3 != null ? ((float) staticLayout3.getHeight()) + this.textPadding : BitmapDescriptorFactory.HUE_RED));
                staticLayout2.draw(canvas);
            }
            StaticLayout staticLayout4 = this.positiveButtonLayout;
            if (staticLayout4 != null) {
                canvas.restoreToCount(intRef.element);
                intRef.element = canvas.save();
                canvas.translate(((float) this.positiveButtonBounds.left) + this.buttonHitRectPadding, ((float) this.positiveButtonBounds.top) + this.buttonHitRectPadding);
                staticLayout4.draw(canvas);
            }
            StaticLayout staticLayout5 = this.negativeButtonLayout;
            if (staticLayout5 != null) {
                canvas.restoreToCount(intRef.element);
                canvas.translate(((float) this.negativeButtonBounds.left) + this.buttonHitRectPadding, ((float) this.negativeButtonBounds.top) + this.buttonHitRectPadding);
                staticLayout5.draw(canvas);
            }
            this.listener.onViewShow(this);
        }
    }

    public boolean onKeyUp(int i, @Nullable KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        dismiss();
        this.listener.onBackPressed(this);
        return true;
    }

    public boolean onTouchEvent(@Nullable MotionEvent motionEvent) {
        int i = 0;
        this.lastTouchX = motionEvent != null ? (int) motionEvent.getX() : 0;
        if (motionEvent != null) {
            i = (int) motionEvent.getY();
        }
        this.lastTouchY = i;
        return super.onTouchEvent(motionEvent);
    }

    public final void dismiss() {
        removeSelf();
        this.listener.onViewDismissed();
    }

    private final void removeSelf() {
        try {
            this.parent.removeView(this);
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* access modifiers changed from: private */
    public final void calculateViewBounds() {
        int[] iArr = new int[2];
        this.featureHighlight.getView().getLocationOnScreen(iArr);
        this.featureBounds.set(new Rect(iArr[0], iArr[1], iArr[0] + this.featureHighlight.getView().getWidth(), iArr[1] + this.featureHighlight.getView().getHeight()));
        int[] iArr2 = new int[2];
        Rect rect = this.featureBounds;
        rect.set(rect);
        getLocationOnScreen(iArr2);
        this.featureBounds.offset(-iArr2[0], -iArr2[1]);
        this.shouldHighlightOnTop = shouldDisplayContentOnTop();
        updateTextLayouts();
        calculateTextBounds();
        requestFocus();
    }

    private final void updateTextLayouts() {
        int min = (int) (((float) Math.min(getWidth(), this.maxContentWidth)) - ((this.contentPadding + this.contentMargin) * ((float) 2)));
        String title = this.featureHighlight.getTitle();
        if (title != null) {
            StaticLayout staticLayout = new StaticLayout(title, this.titlePaint, min, Alignment.ALIGN_NORMAL, 1.0f, 5.0f, false);
            this.titleLayout = staticLayout;
        }
        StaticLayout staticLayout2 = new StaticLayout(this.featureHighlight.getDescription(), this.descriptionPaint, min, Alignment.ALIGN_NORMAL, 1.2f, 5.0f, false);
        this.descriptionLayout = staticLayout2;
        if (this.featureHighlight.getPositiveButtonText() != null) {
            StaticLayout staticLayout3 = new StaticLayout(this.featureHighlight.getPositiveButtonText(), this.positiveButtonPaint, (int) this.positiveButtonPaint.measureText(this.featureHighlight.getPositiveButtonText()), Alignment.ALIGN_NORMAL, 1.0f, BitmapDescriptorFactory.HUE_RED, false);
            this.positiveButtonLayout = staticLayout3;
        }
        if (this.featureHighlight.getNegativeButtonText() != null) {
            StaticLayout staticLayout4 = new StaticLayout(this.featureHighlight.getNegativeButtonText(), this.negativeButtonPaint, (int) this.negativeButtonPaint.measureText(this.featureHighlight.getNegativeButtonText()), Alignment.ALIGN_NORMAL, 1.0f, BitmapDescriptorFactory.HUE_RED, false);
            this.negativeButtonLayout = staticLayout4;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:45:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x008e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void calculateTextBounds() {
        /*
            r10 = this;
            android.text.StaticLayout r0 = r10.descriptionLayout
            r1 = 0
            if (r0 == 0) goto L_0x000a
            int r0 = r0.getWidth()
            goto L_0x000b
        L_0x000a:
            r0 = 0
        L_0x000b:
            float r2 = r10.contentPadding
            int r2 = (int) r2
            int r2 = r2 * 2
            int r0 = r0 + r2
            android.text.StaticLayout r2 = r10.titleLayout
            if (r2 == 0) goto L_0x001a
            int r2 = r2.getHeight()
            goto L_0x001b
        L_0x001a:
            r2 = 0
        L_0x001b:
            android.text.StaticLayout r3 = r10.descriptionLayout
            if (r3 == 0) goto L_0x0024
            int r3 = r3.getHeight()
            goto L_0x0025
        L_0x0024:
            r3 = 0
        L_0x0025:
            int r2 = r2 + r3
            float r3 = r10.contentPadding
            int r3 = (int) r3
            int r3 = r3 * 2
            int r2 = r2 + r3
            android.text.StaticLayout r3 = r10.positiveButtonLayout
            if (r3 == 0) goto L_0x0040
            int r3 = r3.getWidth()
            android.text.StaticLayout r4 = r10.negativeButtonLayout
            if (r4 == 0) goto L_0x003d
            int r4 = r4.getWidth()
            goto L_0x003e
        L_0x003d:
            r4 = 0
        L_0x003e:
            int r3 = r3 + r4
            goto L_0x0041
        L_0x0040:
            r3 = 0
        L_0x0041:
            android.text.StaticLayout r4 = r10.positiveButtonLayout
            if (r4 != 0) goto L_0x0049
            android.text.StaticLayout r4 = r10.negativeButtonLayout
            if (r4 == 0) goto L_0x0091
        L_0x0049:
            android.text.StaticLayout r4 = r10.descriptionLayout
            if (r4 == 0) goto L_0x0052
            int r4 = r4.getWidth()
            goto L_0x0053
        L_0x0052:
            r4 = 0
        L_0x0053:
            if (r3 <= r4) goto L_0x0071
            android.text.StaticLayout r4 = r10.positiveButtonLayout
            if (r4 == 0) goto L_0x005e
            int r4 = r4.getHeight()
            goto L_0x005f
        L_0x005e:
            r4 = 0
        L_0x005f:
            android.text.StaticLayout r5 = r10.negativeButtonLayout
            if (r5 == 0) goto L_0x0068
            int r5 = r5.getHeight()
            goto L_0x0069
        L_0x0068:
            r5 = 0
        L_0x0069:
            int r4 = r4 + r5
            float r5 = r10.buttonPadding
            int r5 = (int) r5
            int r5 = r5 * 2
            int r4 = r4 + r5
            goto L_0x0090
        L_0x0071:
            float r4 = r10.buttonPadding
            int r4 = (int) r4
            android.text.StaticLayout r5 = r10.positiveButtonLayout
            if (r5 == 0) goto L_0x0081
        L_0x0078:
            int r5 = r5.getHeight()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            goto L_0x0087
        L_0x0081:
            android.text.StaticLayout r5 = r10.negativeButtonLayout
            if (r5 == 0) goto L_0x0086
            goto L_0x0078
        L_0x0086:
            r5 = 0
        L_0x0087:
            if (r5 == 0) goto L_0x008e
            int r5 = r5.intValue()
            goto L_0x008f
        L_0x008e:
            r5 = 0
        L_0x008f:
            int r4 = r4 + r5
        L_0x0090:
            int r2 = r2 + r4
        L_0x0091:
            android.graphics.Rect r4 = r10.featureBounds
            int r4 = r4.centerX()
            int r5 = r0 / 2
            int r4 = r4 - r5
            android.graphics.Rect r5 = r10.featureBounds
            int r5 = r5.width()
            if (r5 <= r0) goto L_0x00c8
            com.myfitnesspal.shared.ui.tooltip.FeatureHighlight r5 = r10.featureHighlight
            com.myfitnesspal.shared.ui.tooltip.FeatureHighlight$Gravity r5 = r5.getContentGravity()
            int[] r6 = com.myfitnesspal.shared.ui.tooltip.FeatureHighlightView.WhenMappings.$EnumSwitchMapping$1
            int r5 = r5.ordinal()
            r5 = r6[r5]
            switch(r5) {
                case 1: goto L_0x00c4;
                case 2: goto L_0x00b9;
                case 3: goto L_0x00e0;
                default: goto L_0x00b3;
            }
        L_0x00b3:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L_0x00b9:
            int r4 = r10.getWidth()
            int r4 = r4 - r0
            float r4 = (float) r4
            float r5 = r10.contentMargin
            float r4 = r4 - r5
            int r4 = (int) r4
            goto L_0x00e0
        L_0x00c4:
            float r4 = r10.contentMargin
            int r4 = (int) r4
            goto L_0x00e0
        L_0x00c8:
            if (r4 >= 0) goto L_0x00ce
            float r4 = r10.contentMargin
            int r4 = (int) r4
            goto L_0x00e0
        L_0x00ce:
            int r5 = r4 + r0
            int r6 = r10.getWidth()
            if (r5 <= r6) goto L_0x00e0
            int r4 = r10.getWidth()
            int r4 = r4 - r0
            float r4 = (float) r4
            float r5 = r10.contentMargin
            float r4 = r4 - r5
            int r4 = (int) r4
        L_0x00e0:
            int r0 = r0 + r4
            boolean r5 = r10.shouldHighlightOnTop
            if (r5 == 0) goto L_0x00f0
            android.graphics.Rect r5 = r10.featureBounds
            int r5 = r5.top
            float r6 = r10.contentMargin
            int r6 = (int) r6
            int r5 = r5 - r6
            int r2 = r5 - r2
            goto L_0x00fd
        L_0x00f0:
            android.graphics.Rect r5 = r10.featureBounds
            int r5 = r5.bottom
            float r5 = (float) r5
            float r6 = r10.contentMargin
            float r5 = r5 + r6
            int r5 = (int) r5
            int r2 = r2 + r5
            r9 = r5
            r5 = r2
            r2 = r9
        L_0x00fd:
            android.graphics.Rect r6 = r10.contentBounds
            r6.set(r4, r2, r0, r5)
            r10.calculatePointerPath()
            android.text.StaticLayout r0 = r10.positiveButtonLayout
            if (r0 == 0) goto L_0x0150
            android.graphics.Rect r2 = r10.contentBounds
            int r2 = r2.top
            float r4 = r10.contentPadding
            int r4 = (int) r4
            int r2 = r2 + r4
            android.text.StaticLayout r4 = r10.titleLayout
            if (r4 == 0) goto L_0x011a
            int r4 = r4.getHeight()
            goto L_0x011b
        L_0x011a:
            r4 = 0
        L_0x011b:
            int r2 = r2 + r4
            android.text.StaticLayout r4 = r10.descriptionLayout
            if (r4 == 0) goto L_0x0125
            int r4 = r4.getHeight()
            goto L_0x0126
        L_0x0125:
            r4 = 0
        L_0x0126:
            int r2 = r2 + r4
            float r4 = r10.buttonPadding
            int r4 = (int) r4
            int r2 = r2 + r4
            android.graphics.Rect r4 = r10.contentBounds
            int r4 = r4.right
            float r5 = r10.contentPadding
            int r5 = (int) r5
            int r4 = r4 - r5
            android.graphics.Rect r5 = r10.positiveButtonBounds
            int r6 = r0.getWidth()
            int r6 = r4 - r6
            float r7 = r10.buttonHitRectPadding
            int r8 = (int) r7
            int r6 = r6 - r8
            int r8 = (int) r7
            int r8 = r2 - r8
            int r7 = (int) r7
            int r4 = r4 + r7
            int r0 = r0.getHeight()
            int r2 = r2 + r0
            float r0 = r10.buttonHitRectPadding
            int r0 = (int) r0
            int r2 = r2 + r0
            r5.set(r6, r8, r4, r2)
        L_0x0150:
            android.text.StaticLayout r0 = r10.negativeButtonLayout
            if (r0 == 0) goto L_0x01d1
            android.graphics.Rect r2 = r10.contentBounds
            int r2 = r2.top
            float r4 = r10.contentPadding
            int r4 = (int) r4
            int r2 = r2 + r4
            android.text.StaticLayout r4 = r10.titleLayout
            if (r4 == 0) goto L_0x016a
            int r4 = r4.getHeight()
            float r4 = (float) r4
            float r5 = r10.textPadding
            float r4 = r4 + r5
            int r4 = (int) r4
            goto L_0x016b
        L_0x016a:
            r4 = 0
        L_0x016b:
            int r2 = r2 + r4
            android.text.StaticLayout r4 = r10.descriptionLayout
            if (r4 == 0) goto L_0x0175
            int r4 = r4.getHeight()
            goto L_0x0176
        L_0x0175:
            r4 = 0
        L_0x0176:
            int r2 = r2 + r4
            float r4 = r10.buttonPadding
            int r4 = (int) r4
            int r2 = r2 + r4
            android.graphics.Rect r4 = r10.contentBounds
            int r4 = r4.right
            float r5 = r10.contentPadding
            int r5 = (int) r5
            int r4 = r4 - r5
            android.text.StaticLayout r5 = r10.positiveButtonLayout
            if (r5 == 0) goto L_0x0190
            int r5 = r5.getWidth()
            float r6 = r10.buttonPadding
            int r6 = (int) r6
            int r5 = r5 + r6
            goto L_0x0191
        L_0x0190:
            r5 = 0
        L_0x0191:
            int r4 = r4 - r5
            android.text.StaticLayout r5 = r10.descriptionLayout
            if (r5 == 0) goto L_0x019b
            int r5 = r5.getWidth()
            goto L_0x019c
        L_0x019b:
            r5 = 0
        L_0x019c:
            if (r3 <= r5) goto L_0x01b4
            android.text.StaticLayout r3 = r10.positiveButtonLayout
            if (r3 == 0) goto L_0x01aa
            int r1 = r3.getHeight()
            float r3 = r10.buttonPadding
            int r3 = (int) r3
            int r1 = r1 + r3
        L_0x01aa:
            int r2 = r2 + r1
            android.graphics.Rect r1 = r10.contentBounds
            int r1 = r1.right
            float r3 = r10.contentPadding
            int r3 = (int) r3
            int r4 = r1 - r3
        L_0x01b4:
            android.graphics.Rect r1 = r10.negativeButtonBounds
            int r3 = r0.getWidth()
            int r3 = r4 - r3
            float r5 = r10.buttonHitRectPadding
            int r6 = (int) r5
            int r3 = r3 - r6
            int r6 = (int) r5
            int r6 = r2 - r6
            int r5 = (int) r5
            int r4 = r4 + r5
            int r0 = r0.getHeight()
            int r2 = r2 + r0
            float r0 = r10.buttonHitRectPadding
            int r0 = (int) r0
            int r2 = r2 + r0
            r1.set(r3, r6, r4, r2)
        L_0x01d1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.ui.tooltip.FeatureHighlightView.calculateTextBounds():void");
    }

    private final void calculatePointerPath() {
        Point point = new Point();
        int i = this.shouldHighlightOnTop ? this.contentBounds.bottom + this.pointerHeight : this.contentBounds.top - this.pointerHeight;
        int i2 = this.shouldHighlightOnTop ? -1 : 1;
        Gravity pointerGravity = this.featureHighlight.getPointerGravity();
        if (pointerGravity != null) {
            switch (pointerGravity) {
                case LEFT:
                    point.set(this.contentBounds.left + this.pointerMargin, i);
                    break;
                case RIGHT:
                    point.set(this.contentBounds.right - this.pointerMargin, i);
                    break;
                case CENTER:
                    point.set(this.contentBounds.centerX(), i);
                    break;
            }
        } else {
            point.set(this.featureBounds.centerX(), i);
        }
        this.pointerPath.reset();
        this.pointerPath.moveTo((float) point.x, (float) point.y);
        this.pointerPath.lineTo(((float) point.x) + ((float) this.pointerHeight), ((float) point.y) + ((float) (this.pointerHeight * i2)));
        this.pointerPath.lineTo(((float) point.x) - ((float) this.pointerHeight), ((float) point.y) + ((float) (i2 * this.pointerHeight)));
        this.pointerPath.lineTo((float) point.x, (float) point.y);
        this.pointerPath.close();
    }

    private final boolean shouldDisplayContentOnTop() {
        return this.featureBounds.top > getHeight() - this.featureBounds.bottom;
    }

    private final void onLaidOut(@NotNull View view, Function0<Unit> function0) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new FeatureHighlightView$onLaidOut$1(function0));
    }

    /* access modifiers changed from: private */
    public final boolean isVisibleInScrollView(@NotNull View view) {
        boolean z = true;
        if (getParentScrollView(view) == null) {
            return true;
        }
        Rect rect = new Rect();
        ScrollView parentScrollView = getParentScrollView(view);
        if (parentScrollView != null) {
            parentScrollView.getDrawingRect(rect);
        }
        float y = view.getY();
        float height = ((float) view.getHeight()) + y;
        if (((float) rect.top) >= y || ((float) rect.bottom) <= height) {
            z = false;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public final ScrollView getParentScrollView(@NotNull View view) {
        ViewParent parent2 = view.getParent();
        while (!(parent2 instanceof ScrollView)) {
            if (parent2 != null) {
                parent2 = parent2.getParent();
                if (parent2 == null) {
                }
            }
            return null;
        }
        return (ScrollView) parent2;
    }
}
