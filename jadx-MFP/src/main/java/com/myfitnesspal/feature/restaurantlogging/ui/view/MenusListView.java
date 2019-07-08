package com.myfitnesspal.feature.restaurantlogging.ui.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Filter;
import android.widget.Filter.FilterResults;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.feature.restaurantlogging.listener.OnMenuItemActionListener;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenu;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuListItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuSection;
import com.myfitnesspal.feature.restaurantlogging.service.RestaurantLoggingSettingsService;
import com.myfitnesspal.feature.restaurantlogging.ui.adapter.MenuAdapter;
import com.myfitnesspal.feature.restaurantlogging.util.MultiAddMenuItemHelper;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class MenusListView extends ListView implements OnItemClickListener {
    private DisplayState currentDisplayState;
    private String filterString;
    /* access modifiers changed from: private */
    public OnMenuItemActionListener itemActionListener;
    /* access modifiers changed from: private */
    public MenuAdapter menuAdapter;
    private MenuItemFilter menuItemFilter;
    /* access modifiers changed from: private */
    public List<MfpMenuListItem> menuItems;
    private MfpMenu mfpMenu;
    @Inject
    Lazy<MultiAddMenuItemHelper> multiAddMenuItemHelper;
    @Inject
    Lazy<RestaurantLoggingSettingsService> restaurantLoggingSettingsService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    private enum DisplayState {
        SingleMenu,
        MultipleMenus
    }

    private class MenuItemFilter extends Filter {
        private MenuItemFilter() {
        }

        /* access modifiers changed from: protected */
        public FilterResults performFiltering(CharSequence charSequence) {
            if (MenusListView.this.menuAdapter == null) {
                return null;
            }
            List<MfpMenuListItem> access$300 = MenusListView.this.menuItems;
            if (CollectionUtils.isEmpty((Collection<?>) access$300)) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            if (Strings.isEmpty((Object) charSequence)) {
                arrayList.addAll(access$300);
            } else {
                String lowerCase = charSequence.toString().toLowerCase();
                for (MfpMenuListItem mfpMenuListItem : access$300) {
                    if (mfpMenuListItem instanceof MfpMenuItem) {
                        MfpMenuItem mfpMenuItem = (MfpMenuItem) mfpMenuListItem;
                        if (mfpMenuItem.getName().toLowerCase().contains(lowerCase)) {
                            arrayList.add(mfpMenuItem);
                        }
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.count = arrayList.size();
            filterResults.values = arrayList;
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, FilterResults filterResults) {
            if (filterResults != null && MenusListView.this.menuAdapter != null) {
                MenusListView.this.menuAdapter.setItems((List) filterResults.values);
            }
        }
    }

    public MenusListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public MenusListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public MenusListView(Context context) {
        super(context);
        init();
    }

    private void init() {
        MyFitnessPalApp.getInstance().component().inject(this);
        this.menuAdapter = new MenuAdapter(getContext(), this.userEnergyService, this.multiAddMenuItemHelper);
        this.menuItemFilter = new MenuItemFilter();
        this.menuItems = new ArrayList();
        setAdapter(this.menuAdapter);
        setOnItemClickListener(this);
    }

    public void setMenu(MfpMenu mfpMenu2) {
        setItemListAndState(getListFromMenu(mfpMenu2, true), DisplayState.SingleMenu, mfpMenu2);
    }

    public void setMenus(List<MfpMenu> list) {
        ArrayList arrayList = new ArrayList();
        for (MfpMenu listFromMenu : list) {
            arrayList.addAll(getListFromMenu(listFromMenu, false));
        }
        setItemListAndState(arrayList, DisplayState.MultipleMenus, null);
    }

    public void setItemActionListener(OnMenuItemActionListener onMenuItemActionListener) {
        this.itemActionListener = onMenuItemActionListener;
        this.menuAdapter.setItemActionListener(onMenuItemActionListener);
    }

    private void setItemListAndState(List<MfpMenuListItem> list, DisplayState displayState, MfpMenu mfpMenu2) {
        this.menuItems.clear();
        this.menuItems.addAll(list);
        this.menuAdapter.setItems(list);
        this.currentDisplayState = displayState;
        setData(mfpMenu2);
    }

    private void setData(MfpMenu mfpMenu2) {
        this.mfpMenu = mfpMenu2;
    }

    private List<MfpMenuListItem> getListFromMenu(MfpMenu mfpMenu2, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (MfpMenuSection mfpMenuSection : mfpMenu2.getSections()) {
            if (z && !TextUtils.isEmpty(mfpMenuSection.getName())) {
                arrayList.add(mfpMenuSection);
            }
            List<MfpMenuItem> items = mfpMenuSection.getItems();
            if (!CollectionUtils.isEmpty((Collection<?>) items)) {
                for (MfpMenuItem add : items) {
                    arrayList.add(add);
                }
            }
        }
        return arrayList;
    }

    public MfpMenu getMfpMenu() {
        return this.mfpMenu;
    }

    public boolean isDisplayingSingleMenu() {
        return this.currentDisplayState == DisplayState.SingleMenu;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        MfpMenuListItem mfpMenuListItem = (MfpMenuListItem) this.menuAdapter.getItem(i);
        if (mfpMenuListItem instanceof MfpMenuItem) {
            MfpMenuItem mfpMenuItem = (MfpMenuItem) mfpMenuListItem;
            List matches = mfpMenuItem.getMatches();
            if (matches != null) {
                if (!matches.isEmpty() || !checkShowNoMatchDialog(mfpMenuItem)) {
                    this.itemActionListener.onItemClicked(mfpMenuItem);
                }
            }
        }
    }

    private boolean checkShowNoMatchDialog(final MfpMenuItem mfpMenuItem) {
        if (((RestaurantLoggingSettingsService) this.restaurantLoggingSettingsService.get()).wasNoMatchDialogDisplayed()) {
            return false;
        }
        new MfpAlertDialogBuilder(getContext()).setTitle((int) R.string.no_match_dialog_header).setMessage((int) R.string.no_match_dialog_body).setPositiveButton((int) R.string.choose_match, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MenusListView.this.itemActionListener.onItemClicked(mfpMenuItem);
            }
        }).setNegativeButton((int) R.string.dismiss, (OnClickListener) null).setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                ((RestaurantLoggingSettingsService) MenusListView.this.restaurantLoggingSettingsService.get()).setNoMatchDialogDisplayed(true);
            }
        }).setCancelable(false).setCanceledOnTouchOutside(false).show();
        return true;
    }

    public void setFilterString(String str) {
        this.filterString = str;
        this.menuItemFilter.filter(str);
    }

    public boolean isFiltering() {
        return Strings.notEmpty(this.filterString);
    }
}
