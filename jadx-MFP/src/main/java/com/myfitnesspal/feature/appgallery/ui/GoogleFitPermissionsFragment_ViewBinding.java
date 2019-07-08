package com.myfitnesspal.feature.appgallery.ui;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;

public class GoogleFitPermissionsFragment_ViewBinding implements Unbinder {
    private GoogleFitPermissionsFragment target;

    @UiThread
    public GoogleFitPermissionsFragment_ViewBinding(GoogleFitPermissionsFragment googleFitPermissionsFragment, View view) {
        this.target = googleFitPermissionsFragment;
        googleFitPermissionsFragment.permissionsListView = (ListView) Utils.findRequiredViewAsType(view, R.id.permissionsView, "field 'permissionsListView'", ListView.class);
        googleFitPermissionsFragment.continueBtn = Utils.findRequiredView(view, R.id.continue_btn, "field 'continueBtn'");
    }

    @CallSuper
    public void unbind() {
        GoogleFitPermissionsFragment googleFitPermissionsFragment = this.target;
        if (googleFitPermissionsFragment != null) {
            this.target = null;
            googleFitPermissionsFragment.permissionsListView = null;
            googleFitPermissionsFragment.continueBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
