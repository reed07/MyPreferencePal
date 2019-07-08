package com.myfitnesspal.feature.restaurantlogging.ui.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.restaurantlogging.listener.OnMenuItemActionListener;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenu;
import com.myfitnesspal.feature.restaurantlogging.ui.view.MenusListView;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.ViewUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuPagerAdapter extends PagerAdapter {
    public static final String MENU_TAB_LIST_TAG = "menu_tab_list_tag";
    private final OnMenuItemActionListener itemActionListener;
    private final LayoutInflater layoutInflater;
    private final List<MfpMenu> menus = new ArrayList();
    private final Map<Integer, ListView> positionToListViewMap = new HashMap();

    public int getItemPosition(Object obj) {
        return -2;
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public MenuPagerAdapter(Activity activity, OnMenuItemActionListener onMenuItemActionListener) {
        this.itemActionListener = onMenuItemActionListener;
        this.layoutInflater = LayoutInflater.from(activity);
    }

    public void clearCurrentAndAddNewMenus(List<MfpMenu> list) {
        this.menus.clear();
        this.menus.addAll(list);
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.menus.size();
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View inflate = this.layoutInflater.inflate(R.layout.menu_page, viewGroup, false);
        MenusListView menusListView = (MenusListView) ViewUtils.findById(inflate, R.id.menu_list_view);
        menusListView.setMenu((MfpMenu) this.menus.get(i));
        menusListView.setItemActionListener(this.itemActionListener);
        viewGroup.addView(inflate);
        inflate.setTag(getPageTag(i));
        this.positionToListViewMap.put(Integer.valueOf(i), menusListView);
        MaterialUtils.enableListViewNestedScrolling(menusListView);
        return inflate;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public void notifyListViews() {
        for (ListView notifyDataSetChanged : this.positionToListViewMap.values()) {
            ListViewUtils.notifyDataSetChanged(notifyDataSetChanged);
        }
    }

    public CharSequence getPageTitle(int i) {
        return ((MfpMenu) this.menus.get(i)).getName();
    }

    public static String getPageTag(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(MENU_TAB_LIST_TAG);
        sb.append(i);
        return sb.toString();
    }
}
