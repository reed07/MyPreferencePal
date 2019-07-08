package com.myfitnesspal.feature.restaurantlogging.util;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.widget.ListView;
import com.google.android.gms.common.ConnectionResult;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.restaurantlogging.event.UnableToFetchMenuItemInfoEvent;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuListItem;
import com.myfitnesspal.feature.restaurantlogging.service.MenuService;
import com.myfitnesspal.feature.restaurantlogging.task.GetMenuItemsTask;
import com.myfitnesspal.feature.restaurantlogging.task.GetMenuItemsTask.CompletedEvent;
import com.myfitnesspal.feature.restaurantlogging.ui.adapter.MenuAdapter;
import com.myfitnesspal.feature.restaurantlogging.ui.adapter.MenuPagerAdapter;
import com.myfitnesspal.feature.restaurantlogging.ui.view.MenusListView;
import com.squareup.otto.Bus;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple2;
import com.uacf.core.util.ViewUtils;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MenuItemFetchHelper {
    private static final String FETCHED_ALL_MENU_ITEMS_INFO = "fetched_all_menu_items_info";
    private static final int MAX_ITEMS_REQUEST = 15;
    private static final int[] RETRY_BACKOFF_TIMERS = {500, ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED, 3000};
    private static final String RETRY_COUNT = "retry_count";
    private static final Set<String> completedMenus = new HashSet();
    private static final Map<String, MfpMenuItem> pendingMenuItemsMap = new HashMap();
    private static final Map<String, MfpMenuItem> requestedMenuItemsMap = new HashMap();
    private final Lazy<Bus> bus;
    private final ViewPager contentPager;
    private final Runnable fetchMenuItemInfoRunnable = new Runnable() {
        public final void run() {
            MenuItemFetchHelper.this.clearPendingMenuItemsAndFetchInfo();
        }
    };
    private boolean fetchedAllMenuItemsInfo;
    private final Lazy<MenuService> menuService;
    private int retryCount;
    private Handler retryHandler;
    private final Runner runner;

    public MenuItemFetchHelper(ViewPager viewPager, Runner runner2, Lazy<MenuService> lazy, Lazy<Bus> lazy2) {
        this.contentPager = viewPager;
        this.runner = runner2;
        this.menuService = lazy;
        this.bus = lazy2;
    }

    public void saveInstanceState(Bundle bundle) {
        bundle.putBoolean(FETCHED_ALL_MENU_ITEMS_INFO, this.fetchedAllMenuItemsInfo);
        bundle.putInt(RETRY_COUNT, this.retryCount);
    }

    public void restoreInstanceState(Bundle bundle) {
        if (bundle != null) {
            this.fetchedAllMenuItemsInfo = bundle.getBoolean(FETCHED_ALL_MENU_ITEMS_INFO);
            this.retryCount = bundle.getInt(RETRY_COUNT);
        }
    }

    public void fetchMenuItemInfo() {
        clearPendingMenuItemFetches();
        if (!this.fetchedAllMenuItemsInfo) {
            MenusListView currentListView = getCurrentListView();
            if (currentListView != null) {
                int firstVisiblePosition = currentListView.getFirstVisiblePosition();
                MenuAdapter menuAdapter = (MenuAdapter) ListViewUtils.getAdapter(currentListView, MenuAdapter.class);
                if (menuAdapter != null) {
                    boolean isDisplayingSingleMenu = currentListView.isDisplayingSingleMenu();
                    if (!isDisplayingSingleMenu || !completedMenus.contains(currentListView.getMfpMenu().getId())) {
                        Tuple2 newRequestList = getNewRequestList(menuAdapter.getItems(), firstVisiblePosition);
                        List list = (List) newRequestList.getItem2();
                        String str = (String) newRequestList.getItem1();
                        if (list.isEmpty()) {
                            if (isDisplayingSingleMenu) {
                                completedMenus.add(str);
                            } else {
                                this.fetchedAllMenuItemsInfo = !currentListView.isFiltering();
                            }
                            return;
                        }
                        requestMenuItemInfo(list, str);
                    }
                }
            }
        }
    }

    public void destroy() {
        clearPendingMenuItemFetches();
        requestedMenuItemsMap.clear();
        pendingMenuItemsMap.clear();
        completedMenus.clear();
    }

    private void clearPendingMenuItemFetches() {
        Handler handler = this.retryHandler;
        if (handler != null) {
            handler.removeCallbacks(this.fetchMenuItemInfoRunnable);
        }
    }

    public void getMenuItemsTaskCompletedEvent(CompletedEvent completedEvent) {
        if (completedEvent.getFailure() == null) {
            this.retryCount = 0;
            for (MfpMenuItem mfpMenuItem : (List) completedEvent.getResult()) {
                String id = mfpMenuItem.getId();
                MfpMenuItem mfpMenuItem2 = (MfpMenuItem) pendingMenuItemsMap.get(id);
                if (mfpMenuItem2 != null) {
                    mfpMenuItem2.setMfpMenuItem(mfpMenuItem);
                    requestedMenuItemsMap.put(id, mfpMenuItem2);
                }
            }
            ListViewUtils.notifyDataSetChanged((ListView) getCurrentListView());
            clearPendingMenuItemsAndFetchInfo();
        } else {
            int i = this.retryCount;
            int[] iArr = RETRY_BACKOFF_TIMERS;
            if (i >= iArr.length) {
                ((Bus) this.bus.get()).post(new UnableToFetchMenuItemInfoEvent());
                return;
            }
            this.retryCount = i + 1;
            int i2 = iArr[i];
            if (this.retryHandler == null) {
                this.retryHandler = new Handler();
            }
            this.retryHandler.postDelayed(this.fetchMenuItemInfoRunnable, (long) i2);
        }
    }

    /* access modifiers changed from: private */
    public void clearPendingMenuItemsAndFetchInfo() {
        pendingMenuItemsMap.clear();
        fetchMenuItemInfo();
    }

    private MenusListView getCurrentListView() {
        ViewPager viewPager = this.contentPager;
        return (MenusListView) ViewUtils.findById(viewPager.findViewWithTag(MenuPagerAdapter.getPageTag(viewPager.getCurrentItem())), R.id.menu_list_view);
    }

    private Tuple2<String, List<MfpMenuItem>> getNewRequestList(List<MfpMenuListItem> list, int i) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        String addValidItemsToList = addValidItemsToList(list, arrayList, i, size, null);
        if (!didReachLimit(arrayList)) {
            addValidItemsToList(list, arrayList, 0, Math.min(i, size), addValidItemsToList);
        }
        return Tuple.create(addValidItemsToList, arrayList);
    }

    private void requestMenuItemInfo(List<MfpMenuItem> list, String str) {
        ArrayList arrayList = new ArrayList();
        for (MfpMenuItem mfpMenuItem : list) {
            String id = mfpMenuItem.getId();
            pendingMenuItemsMap.put(id, mfpMenuItem);
            arrayList.add(id);
        }
        new GetMenuItemsTask(str, arrayList, this.menuService).run(this.runner);
    }

    private boolean shouldAddItemToNewRequestList(MfpMenuListItem mfpMenuListItem) {
        boolean z = false;
        if (!(mfpMenuListItem instanceof MfpMenuItem)) {
            return false;
        }
        String id = ((MfpMenuItem) mfpMenuListItem).getId();
        if (!requestedMenuItemsMap.containsKey(id) && !pendingMenuItemsMap.containsKey(id)) {
            z = true;
        }
        return z;
    }

    private String addValidItemsToList(List<MfpMenuListItem> list, List<MfpMenuItem> list2, int i, int i2, String str) {
        while (i < i2) {
            MfpMenuListItem mfpMenuListItem = (MfpMenuListItem) list.get(i);
            if (shouldAddItemToNewRequestList(mfpMenuListItem)) {
                MfpMenuItem mfpMenuItem = (MfpMenuItem) mfpMenuListItem;
                String menuId = mfpMenuItem.getMenuId();
                if (str == null || Strings.equals(str, menuId)) {
                    if (str == null) {
                        str = menuId;
                    }
                    list2.add(mfpMenuItem);
                    if (didReachLimit(list2)) {
                        break;
                    }
                }
            }
            i++;
        }
        return str;
    }

    private boolean didReachLimit(List<MfpMenuItem> list) {
        return list.size() >= 15;
    }
}
