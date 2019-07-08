package com.myfitnesspal.feature.restaurantlogging.task;

import android.content.Context;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItem;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMultiAddMenuItem;
import com.myfitnesspal.feature.restaurantlogging.service.MenuService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;
import java.util.List;

public class CreateMenuItemMatchesTask extends EventedTaskBase<List<MfpMultiAddMenuItem>, ApiException> {
    private final List<MfpMenuItem> menuItemList;
    private final Lazy<MenuService> menuService;

    public static class CompletedEvent extends TaskEventBase<List<MfpMultiAddMenuItem>, ApiException> {
    }

    public CreateMenuItemMatchesTask(Lazy<MenuService> lazy, List<MfpMenuItem> list) {
        super(CompletedEvent.class);
        this.menuService = lazy;
        this.menuItemList = list;
    }

    /* access modifiers changed from: protected */
    public List<MfpMultiAddMenuItem> exec(Context context) throws ApiException {
        return ((MenuService) this.menuService.get()).createMenuItemMatches(this.menuItemList);
    }
}
