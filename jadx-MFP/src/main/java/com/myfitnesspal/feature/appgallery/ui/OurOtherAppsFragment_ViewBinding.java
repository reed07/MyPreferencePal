package com.myfitnesspal.feature.appgallery.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;

public class OurOtherAppsFragment_ViewBinding implements Unbinder {
    private OurOtherAppsFragment target;

    @UiThread
    public OurOtherAppsFragment_ViewBinding(OurOtherAppsFragment ourOtherAppsFragment, View view) {
        this.target = ourOtherAppsFragment;
        ourOtherAppsFragment.listView = (LinearLayoutListAdapterView) Utils.findRequiredViewAsType(view, R.id.appsListView, "field 'listView'", LinearLayoutListAdapterView.class);
        ourOtherAppsFragment.header = Utils.findRequiredView(view, R.id.header, "field 'header'");
        ourOtherAppsFragment.titleText = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'titleText'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        OurOtherAppsFragment ourOtherAppsFragment = this.target;
        if (ourOtherAppsFragment != null) {
            this.target = null;
            ourOtherAppsFragment.listView = null;
            ourOtherAppsFragment.header = null;
            ourOtherAppsFragment.titleText = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
