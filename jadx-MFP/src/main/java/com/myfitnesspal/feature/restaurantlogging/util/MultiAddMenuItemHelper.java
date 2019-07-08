package com.myfitnesspal.feature.restaurantlogging.util;

import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiAddMenuItemHelper {
    public static final int MAX_MENU_ITEM_LIMIT = 20;
    private final Map<String, MfpMenuItem> idToMenuItemMap = new HashMap();
    private boolean isEnabled;

    public void enable() {
        this.isEnabled = true;
    }

    public void disable() {
        this.idToMenuItemMap.clear();
        this.isEnabled = false;
    }

    public boolean isEnabled() {
        return this.isEnabled;
    }

    public boolean hasReachedLimit() {
        return this.idToMenuItemMap.size() == 20;
    }

    public void addItem(MfpMenuItem mfpMenuItem) {
        this.idToMenuItemMap.put(mfpMenuItem.getId(), mfpMenuItem);
    }

    public void removeItem(MfpMenuItem mfpMenuItem) {
        this.idToMenuItemMap.remove(mfpMenuItem.getId());
    }

    public boolean containsItem(MfpMenuItem mfpMenuItem) {
        return this.idToMenuItemMap.containsKey(mfpMenuItem.getId());
    }

    public int getItemCount() {
        return this.idToMenuItemMap.size();
    }

    public List<MfpMenuItem> getSelectedItems() {
        return new ArrayList(this.idToMenuItemMap.values());
    }
}
