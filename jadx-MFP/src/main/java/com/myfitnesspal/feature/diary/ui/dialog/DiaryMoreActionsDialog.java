package com.myfitnesspal.feature.diary.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.event.MoreItemEventSelected;
import com.myfitnesspal.feature.diary.ui.item.MoreItem;
import com.myfitnesspal.shared.constants.Constants.Dialogs;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Report;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class DiaryMoreActionsDialog extends CustomLayoutBaseDialogFragment {
    private static final String ARE_FOOD_ENTRIES_PRESENT = "areFoodEntriesPresent";
    @Inject
    Lazy<ConfigService> configService;

    public static DiaryMoreActionsDialog newInstance(String str, List<FoodEntry> list) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.MEAL_NAME, str);
        bundle.putBoolean(ARE_FOOD_ENTRIES_PRESENT, CollectionUtils.notEmpty((Collection<?>) list));
        DiaryMoreActionsDialog diaryMoreActionsDialog = new DiaryMoreActionsDialog();
        diaryMoreActionsDialog.setArguments(bundle);
        return diaryMoreActionsDialog;
    }

    private List<MoreItem> createModel() {
        ArrayList arrayList = new ArrayList();
        if (BundleUtils.getBoolean(getArguments(), ARE_FOOD_ENTRIES_PRESENT)) {
            arrayList.add(new MoreItem(R.string.save_meal, Dialogs.SAVE_MEAL));
            arrayList.add(new MoreItem(R.string.copy_meal, Dialogs.COPY_MEAL));
        }
        if (Strings.equals(BundleUtils.getString(getArguments(), Extras.MEAL_NAME), Report.WATER_CONSUMPTION)) {
            arrayList.add(new MoreItem(R.string.change_water_unit, Dialogs.CHANGE_UNIT));
        } else if (!Strings.equals(BundleUtils.getString(getArguments(), Extras.MEAL_NAME), Report.WATER_CONSUMPTION)) {
            arrayList.add(new MoreItem(R.string.quick_add, Dialogs.QUICK_ADD));
        }
        if (ConfigUtils.isAppNavUpdateDiaryEnabled((ConfigService) this.configService.get())) {
            arrayList.add(new MoreItem(R.string.menu_reminders, Dialogs.REMINDERS));
        }
        return arrayList;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        component().inject(this);
        final List createModel = createModel();
        return new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setItems(createModel, new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i >= createModel.size()) {
                    Ln.d("Could not find selected item!", new Object[0]);
                } else {
                    DiaryMoreActionsDialog.this.postMoreItemEventSelected((MoreItem) createModel.get(i));
                }
            }
        }).create();
    }

    /* access modifiers changed from: protected */
    public void postMoreItemEventSelected(MoreItem moreItem) {
        this.messageBus.post(new MoreItemEventSelected(moreItem.getId(), BundleUtils.getString(getArguments(), Extras.MEAL_NAME)));
    }
}
