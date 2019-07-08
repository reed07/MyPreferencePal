package com.myfitnesspal.feature.appgallery.model;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.SyncScopes;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.GoogleFitScope;
import com.myfitnesspal.feature.settings.ui.view.CheckableItem;
import com.myfitnesspal.framework.mvvm.BaseViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GoogleFitPermissionsViewModel extends BaseViewModel {
    private static final int ACTIVITY_PERMISSION = 0;
    private static final int BODY_PERMISSION = 1;
    private static final int NUTRIENT_PERMISSION = 2;
    private final Map<Integer, CheckableItem> permissionItems = new LinkedHashMap();

    public interface Property {
        public static final int PERMISSION_STATE_CHANGE = ViewModelPropertyId.next();
    }

    public GoogleFitPermissionsViewModel(Context context) {
        String[] stringArray = context.getResources().getStringArray(R.array.google_fit_permissions);
        for (int i = 0; i < stringArray.length; i++) {
            this.permissionItems.put(Integer.valueOf(i), new CheckableItem(stringArray[i], true));
        }
    }

    public Map<Integer, CheckableItem> getPermissionItems() {
        return this.permissionItems;
    }

    public void togglePermissionItemState(int i) {
        if (this.permissionItems.containsKey(Integer.valueOf(i))) {
            CheckableItem checkableItem = (CheckableItem) this.permissionItems.get(Integer.valueOf(i));
            checkableItem.setState(!checkableItem.getState());
            this.permissionItems.put(Integer.valueOf(i), checkableItem);
            notifyPropertyChanged(Property.PERMISSION_STATE_CHANGE);
        }
    }

    public List<GoogleFitScope> getScopesFromPermissions() {
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.permissionItems.keySet()) {
            if (((CheckableItem) this.permissionItems.get(num)).getState()) {
                GoogleFitScope scopeFromPermission = getScopeFromPermission(num.intValue());
                if (scopeFromPermission != null) {
                    arrayList.add(scopeFromPermission);
                }
            }
        }
        return arrayList;
    }

    private GoogleFitScope getScopeFromPermission(int i) {
        switch (i) {
            case 0:
                return new GoogleFitScope(SyncScopes.FITNESS_ACTIVITY_READ_WRITE);
            case 1:
                return new GoogleFitScope(SyncScopes.FITNESS_BODY_READ_WRITE);
            case 2:
                return new GoogleFitScope(SyncScopes.FITNESS_NUTRITION_READ_WRITE);
            default:
                return null;
        }
    }
}
