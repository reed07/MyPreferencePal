package com.myfitnesspal.feature.search.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.EmptyStateView;

public class LocalFoodSearchFragment_ViewBinding implements Unbinder {
    private LocalFoodSearchFragment target;

    @UiThread
    public LocalFoodSearchFragment_ViewBinding(LocalFoodSearchFragment localFoodSearchFragment, View view) {
        this.target = localFoodSearchFragment;
        localFoodSearchFragment.createNewContainer = view.findViewById(R.id.create_new_container);
        localFoodSearchFragment.emptyStateView = (EmptyStateView) Utils.findRequiredViewAsType(view, R.id.empty_state, "field 'emptyStateView'", EmptyStateView.class);
        localFoodSearchFragment.createNewButton = (Button) Utils.findOptionalViewAsType(view, R.id.create_new_btn, "field 'createNewButton'", Button.class);
        localFoodSearchFragment.listView = (ListView) Utils.findRequiredViewAsType(view, R.id.list_food_search, "field 'listView'", ListView.class);
    }

    @CallSuper
    public void unbind() {
        LocalFoodSearchFragment localFoodSearchFragment = this.target;
        if (localFoodSearchFragment != null) {
            this.target = null;
            localFoodSearchFragment.createNewContainer = null;
            localFoodSearchFragment.emptyStateView = null;
            localFoodSearchFragment.createNewButton = null;
            localFoodSearchFragment.listView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
