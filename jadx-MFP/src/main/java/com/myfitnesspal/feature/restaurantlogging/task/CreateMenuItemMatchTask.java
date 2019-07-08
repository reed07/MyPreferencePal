package com.myfitnesspal.feature.restaurantlogging.task;

import android.content.Context;
import com.myfitnesspal.feature.restaurantlogging.model.MfpMenuItemMatch;
import com.myfitnesspal.feature.restaurantlogging.service.MenuService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;

public class CreateMenuItemMatchTask extends EventedTaskBase<MfpMenuItemMatch, ApiException> {
    private final MfpMenuItemMatch match;
    private final String menuId;
    private final String menuItemId;
    private final Lazy<MenuService> menuService;

    public static class CompletedEvent extends TaskEventBase<MfpMenuItemMatch, ApiException> {
    }

    public CreateMenuItemMatchTask(Lazy<MenuService> lazy, MfpMenuItemMatch mfpMenuItemMatch, String str, String str2) {
        super(CompletedEvent.class);
        this.menuService = lazy;
        this.match = mfpMenuItemMatch;
        this.menuId = str;
        this.menuItemId = str2;
    }

    /* access modifiers changed from: protected */
    public MfpMenuItemMatch exec(Context context) throws ApiException {
        return ((MenuService) this.menuService.get()).createMenuItemMatch(this.match, this.menuId, this.menuItemId);
    }
}
