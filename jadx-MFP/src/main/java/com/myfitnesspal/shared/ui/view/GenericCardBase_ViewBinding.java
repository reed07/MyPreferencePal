package com.myfitnesspal.shared.ui.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class GenericCardBase_ViewBinding implements Unbinder {
    private GenericCardBase target;

    @UiThread
    public GenericCardBase_ViewBinding(GenericCardBase genericCardBase) {
        this(genericCardBase, genericCardBase);
    }

    @UiThread
    public GenericCardBase_ViewBinding(GenericCardBase genericCardBase, View view) {
        this.target = genericCardBase;
        genericCardBase.title = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
        genericCardBase.button = (TextView) Utils.findRequiredViewAsType(view, R.id.button, "field 'button'", TextView.class);
        genericCardBase.headerAndContentContainer = Utils.findRequiredView(view, R.id.headerAndContent, "field 'headerAndContentContainer'");
        genericCardBase.buttonContainer = Utils.findRequiredView(view, R.id.buttonContainer, "field 'buttonContainer'");
        genericCardBase.contentContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.contentContainer, "field 'contentContainer'", FrameLayout.class);
        genericCardBase.leftBadgeContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.titleLeftBadgeContainer, "field 'leftBadgeContainer'", FrameLayout.class);
        genericCardBase.rightBadgeContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.titleRightBadgeContainer, "field 'rightBadgeContainer'", FrameLayout.class);
    }

    @CallSuper
    public void unbind() {
        GenericCardBase genericCardBase = this.target;
        if (genericCardBase != null) {
            this.target = null;
            genericCardBase.title = null;
            genericCardBase.button = null;
            genericCardBase.headerAndContentContainer = null;
            genericCardBase.buttonContainer = null;
            genericCardBase.contentContainer = null;
            genericCardBase.leftBadgeContainer = null;
            genericCardBase.rightBadgeContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
