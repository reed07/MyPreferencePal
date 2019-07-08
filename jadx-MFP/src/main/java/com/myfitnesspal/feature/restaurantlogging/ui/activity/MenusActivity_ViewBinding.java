package com.myfitnesspal.feature.restaurantlogging.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.CustomSwipeableViewPager;

public class MenusActivity_ViewBinding implements Unbinder {
    private MenusActivity target;

    @UiThread
    public MenusActivity_ViewBinding(MenusActivity menusActivity) {
        this(menusActivity, menusActivity.getWindow().getDecorView());
    }

    @UiThread
    public MenusActivity_ViewBinding(MenusActivity menusActivity, View view) {
        this.target = menusActivity;
        menusActivity.viewPager = (CustomSwipeableViewPager) Utils.findRequiredViewAsType(view, R.id.content_pager, "field 'viewPager'", CustomSwipeableViewPager.class);
        menusActivity.tabLayout = (TabLayout) Utils.findRequiredViewAsType(view, R.id.tabs_container, "field 'tabLayout'", TabLayout.class);
        menusActivity.titleTextView = (TextView) Utils.findRequiredViewAsType(view, R.id.title, "field 'titleTextView'", TextView.class);
        menusActivity.menuSearchEditText = (EditText) Utils.findRequiredViewAsType(view, R.id.menu_item_input, "field 'menuSearchEditText'", EditText.class);
        menusActivity.emptyView = Utils.findRequiredView(view, R.id.empty_view, "field 'emptyView'");
        menusActivity.loadingMenus = Utils.findRequiredView(view, R.id.loading_menus, "field 'loadingMenus'");
        menusActivity.menuParentView = Utils.findRequiredView(view, R.id.menu_parent_layout, "field 'menuParentView'");
        menusActivity.emptyButton = Utils.findRequiredView(view, R.id.empty_menu_button, "field 'emptyButton'");
    }

    @CallSuper
    public void unbind() {
        MenusActivity menusActivity = this.target;
        if (menusActivity != null) {
            this.target = null;
            menusActivity.viewPager = null;
            menusActivity.tabLayout = null;
            menusActivity.titleTextView = null;
            menusActivity.menuSearchEditText = null;
            menusActivity.emptyView = null;
            menusActivity.loadingMenus = null;
            menusActivity.menuParentView = null;
            menusActivity.emptyButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
