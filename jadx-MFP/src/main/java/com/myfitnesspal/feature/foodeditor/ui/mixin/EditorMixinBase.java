package com.myfitnesspal.feature.foodeditor.ui.mixin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import com.myfitnesspal.android.R;
import com.myfitnesspal.app.MyFitnessPalApp;
import com.myfitnesspal.framework.mixin.RunnerLifecycleMixin;
import com.myfitnesspal.shared.injection.component.ApplicationComponent;
import com.myfitnesspal.shared.model.FoodEditorItem;
import com.myfitnesspal.shared.ui.component.MfpUiComponentInterface;
import java.util.Map;

public abstract class EditorMixinBase<T extends FoodEditorItem> extends RunnerLifecycleMixin {
    protected static final int ADD_ITEM = 1001;

    public interface OnItemSavedListener {
        void onItemSaveFailed(int i, Bundle bundle);

        void onItemSaved(int i, Bundle bundle);

        void onItemSavedIgnoreStartIntent(int i, Bundle bundle);

        void onItemSavedOverrideStartIntent(int i, Bundle bundle, Intent intent);
    }

    public boolean backPressed() {
        return false;
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    public Map<String, String> getAnalyticsScreenAttributes() {
        return null;
    }

    public String getAnalyticsScreenTag() {
        return null;
    }

    public void onPause() {
    }

    public boolean onRebindDialogFragment(DialogFragment dialogFragment, String str) {
        return false;
    }

    public void onResume() {
    }

    public void onSaveInstanceState(Bundle bundle) {
    }

    public boolean processActivityResult(int i, int i2, Intent intent) {
        return false;
    }

    public abstract void renderView();

    public abstract void saveItemToTarget();

    public abstract void saveItemToTarget(T t);

    public EditorMixinBase(MfpUiComponentInterface mfpUiComponentInterface) {
        super(mfpUiComponentInterface);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        processActivityResult(i, i2, intent);
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItemCompat.setShowAsAction(menu.add(0, 1001, 1, R.string.add).setIcon(R.drawable.ic_check_white_24dp), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 1001) {
            return false;
        }
        saveItemToTarget();
        return true;
    }

    /* access modifiers changed from: protected */
    @NonNull
    public MyFitnessPalApp application() {
        return MyFitnessPalApp.getInstance();
    }

    /* access modifiers changed from: protected */
    @NonNull
    public ApplicationComponent component() {
        return application().component();
    }
}
