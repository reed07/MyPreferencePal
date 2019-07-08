package com.myfitnesspal.shared.ui.tooltip;

import android.app.Activity;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.brightcove.player.event.AbstractEvent;
import com.myfitnesspal.shared.ui.tooltip.FeatureHighlightView.Listener;
import com.uacf.core.util.Ln;
import java.util.Queue;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 !2\u00020\u0001:\u0001!Bp\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012%\b\u0002\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\n\u0012\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011\u0012\u0010\b\u0002\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011¢\u0006\u0002\u0010\u0013J\u0006\u0010\u001d\u001a\u00020\u000fJ\b\u0010\u001e\u001a\u00020\u000fH\u0002J\b\u0010\u001f\u001a\u00020\u000fH\u0002J\b\u0010\u0007\u001a\u00020\u000fH\u0002J\u0006\u0010 \u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000R+\u0010\t\u001a\u001f\u0012\u0013\u0012\u00110\u000b¢\u0006\f\b\f\u0012\b\b\r\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u00020\u000f\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u000e¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightSequence;", "", "activity", "Landroid/app/Activity;", "highlights", "Ljava/util/Queue;", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlight;", "showOverlay", "", "onHighlightShow", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "index", "", "onSequenceFinished", "Lkotlin/Function0;", "onSequenceCancelled", "(Landroid/app/Activity;Ljava/util/Queue;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "currentStep", "featureHighlightListener", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView$Listener;", "featureHighlightView", "Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightView;", "overlayView", "Landroid/view/View;", "windowManager", "Landroid/view/WindowManager;", "dismiss", "removeOverlay", "showNext", "start", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: FeatureHighlightSequence.kt */
public final class FeatureHighlightSequence {
    private static final int COLOR_OVERLAY = -1996488704;
    public static final Companion Companion = new Companion(null);
    private final Activity activity;
    private int currentStep;
    private final Listener featureHighlightListener;
    /* access modifiers changed from: private */
    public FeatureHighlightView featureHighlightView;
    private final Queue<FeatureHighlight> highlights;
    private Function1<? super Integer, Unit> onHighlightShow;
    /* access modifiers changed from: private */
    public Function0<Unit> onSequenceCancelled;
    private Function0<Unit> onSequenceFinished;
    /* access modifiers changed from: private */
    public View overlayView;
    private final boolean showOverlay;
    /* access modifiers changed from: private */
    public WindowManager windowManager;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/shared/ui/tooltip/FeatureHighlightSequence$Companion;", "", "()V", "COLOR_OVERLAY", "", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: FeatureHighlightSequence.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public FeatureHighlightSequence(@NotNull Activity activity2, @NotNull Queue<FeatureHighlight> queue, boolean z, @Nullable Function1<? super Integer, Unit> function1, @Nullable Function0<Unit> function0, @Nullable Function0<Unit> function02) {
        Intrinsics.checkParameterIsNotNull(activity2, AbstractEvent.ACTIVITY);
        Intrinsics.checkParameterIsNotNull(queue, "highlights");
        this.activity = activity2;
        this.highlights = queue;
        this.showOverlay = z;
        this.onHighlightShow = function1;
        this.onSequenceFinished = function0;
        this.onSequenceCancelled = function02;
        this.featureHighlightListener = new FeatureHighlightSequence$featureHighlightListener$1(this);
        this.currentStep = -1;
    }

    public /* synthetic */ FeatureHighlightSequence(Activity activity2, Queue queue, boolean z, Function1 function1, Function0 function0, Function0 function02, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(activity2, queue, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : function1, (i & 16) != 0 ? null : function0, (i & 32) != 0 ? null : function02);
    }

    public final void start() {
        if (this.showOverlay) {
            showOverlay();
        }
        showNext();
    }

    public final void dismiss() {
        removeOverlay();
    }

    /* access modifiers changed from: private */
    public final void showNext() {
        FeatureHighlightView featureHighlightView2 = this.featureHighlightView;
        if (featureHighlightView2 != null) {
            featureHighlightView2.dismiss();
        }
        FeatureHighlight featureHighlight = (FeatureHighlight) this.highlights.poll();
        if (featureHighlight != null) {
            FeatureHighlightView.Companion.showFor(this.activity, featureHighlight, this.featureHighlightListener);
            Function1<? super Integer, Unit> function1 = this.onHighlightShow;
            if (function1 != null) {
                this.currentStep++;
                Unit unit = (Unit) function1.invoke(Integer.valueOf(this.currentStep));
                return;
            }
            return;
        }
        removeOverlay();
        Function0<Unit> function0 = this.onSequenceFinished;
        if (function0 != null) {
            Unit unit2 = (Unit) function0.invoke();
        }
    }

    private final void showOverlay() {
        Object systemService = this.activity.getSystemService("window");
        if (systemService != null) {
            this.windowManager = (WindowManager) systemService;
            View view = new View(this.activity);
            view.setBackgroundColor(-1996488704);
            this.overlayView = view;
            LayoutParams layoutParams = new LayoutParams();
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.x = 0;
            layoutParams.y = 0;
            layoutParams.gravity = 8388659;
            layoutParams.type = 2;
            layoutParams.format = 1;
            layoutParams.flags = 0;
            WindowManager windowManager2 = this.windowManager;
            if (windowManager2 != null) {
                windowManager2.addView(this.overlayView, layoutParams);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
    }

    private final void removeOverlay() {
        View view = this.overlayView;
        if (view != null && view.isAttachedToWindow()) {
            try {
                WindowManager windowManager2 = this.windowManager;
                if (windowManager2 != null) {
                    windowManager2.removeView(this.overlayView);
                }
            } catch (IllegalArgumentException e) {
                Ln.e(e);
            }
        }
    }
}
