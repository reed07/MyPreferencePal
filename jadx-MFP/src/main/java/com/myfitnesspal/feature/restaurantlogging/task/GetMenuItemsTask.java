package com.myfitnesspal.feature.restaurantlogging.task;

import android.content.Context;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.service.MenuService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;
import java.util.List;

public class GetMenuItemsTask extends EventedTaskBase<List<MfpMenuItem>, ApiException> {
    private final String menuId;
    private final List<String> menuItemIds;
    private final Lazy<MenuService> menuService;

    public static class CompletedEvent extends TaskEventBase<List<MfpMenuItem>, ApiException> {
    }

    public GetMenuItemsTask(String str, List<String> list, Lazy<MenuService> lazy) {
        super(CompletedEvent.class);
        this.menuId = str;
        this.menuItemIds = list;
        this.menuService = lazy;
    }

    /* access modifiers changed from: protected */
    public List<MfpMenuItem> exec(Context context) throws ApiException {
        return ((MenuService) this.menuService.get()).getMenuItemsForMenuIdAndItemIds(this.menuId, this.menuItemIds);
    }
}
