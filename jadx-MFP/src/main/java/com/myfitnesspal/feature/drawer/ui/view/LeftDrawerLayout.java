package com.myfitnesspal.feature.drawer.ui.view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.view.View;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.drawer.event.DrawerCloseEvent;
import com.myfitnesspal.feature.drawer.event.PreDrawerOpenEvent;
import com.squareup.otto.Bus;
import javax.inject.Inject;

public class LeftDrawerLayout extends DrawerLayout {
    @Inject
    Bus bus;

    public LeftDrawerLayout(Context context) {
        super(context);
        inject();
    }

    public LeftDrawerLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inject();
    }

    public LeftDrawerLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inject();
    }

    private void inject() {
        MyFitnessPalApp.getInstance().component().inject(this);
    }

    public void openDrawer(View view) {
        this.bus.post(new PreDrawerOpenEvent());
        super.openDrawer(view);
    }

    public void closeDrawers() {
        super.closeDrawers();
        postDrawerClosedEvent();
    }

    public void closeDrawer(View view) {
        super.closeDrawer(view);
        postDrawerClosedEvent();
    }

    private void postDrawerClosedEvent() {
        this.bus.post(new DrawerCloseEvent());
    }
}
