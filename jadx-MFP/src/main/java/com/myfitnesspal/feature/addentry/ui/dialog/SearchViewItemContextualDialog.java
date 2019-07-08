package com.myfitnesspal.feature.addentry.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.service.foods.FoodService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextResItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import java.util.ArrayList;
import javax.inject.Inject;

public class SearchViewItemContextualDialog extends CustomLayoutBaseDialogFragment {
    @Inject
    FoodService foodService;

    public static SearchViewItemContextualDialog newInstance(long j, Food food, boolean z) {
        SearchViewItemContextualDialog searchViewItemContextualDialog = new SearchViewItemContextualDialog();
        Bundle bundle = new Bundle();
        bundle.putLong("food_master_id", food.getMasterDatabaseId());
        bundle.putString("food_uid", food.getUid());
        bundle.putBoolean(Extras.FOOD_HAS_MASTER_ID, food.hasMasterDatabaseId());
        bundle.putLong(Extras.FOOD_ORIGINAL_MASTER_ID, food.getOriginalMasterId());
        bundle.putString(Extras.FOOD_ORIGINAL_UID, food.getOriginalUid());
        bundle.putBoolean(Extras.HIDE_WHEN_DELETED, z);
        bundle.putLong("food_local_id", food.getLocalId());
        bundle.putLong("meal_id", j);
        bundle.putLong(Extras.FOOD_ORIGINAL_ID, food.getOriginalId());
        searchViewItemContextualDialog.setArguments(bundle);
        return searchViewItemContextualDialog;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        component().inject(this);
        Bundle arguments = getArguments();
        long j = arguments.getLong("food_master_id");
        String string = arguments.getString("food_uid");
        boolean z = arguments.getBoolean(Extras.FOOD_HAS_MASTER_ID);
        long j2 = arguments.getLong(Extras.FOOD_ORIGINAL_MASTER_ID);
        String string2 = arguments.getString(Extras.FOOD_ORIGINAL_UID);
        boolean z2 = arguments.getBoolean(Extras.HIDE_WHEN_DELETED);
        long j3 = arguments.getLong("food_local_id");
        long j4 = arguments.getLong("meal_id");
        long j5 = arguments.getLong(Extras.FOOD_ORIGINAL_ID);
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new DialogListTextResItem(R.string.delete));
        MfpAlertDialogBuilder mfpAlertDialogBuilder = new MfpAlertDialogBuilder(dialogContextThemeWrapper);
        $$Lambda$SearchViewItemContextualDialog$8PP6IqbYq47mAw5erOErvXzG5Nw r20 = r1;
        ArrayList arrayList2 = arrayList;
        MfpAlertDialogBuilder mfpAlertDialogBuilder2 = mfpAlertDialogBuilder;
        $$Lambda$SearchViewItemContextualDialog$8PP6IqbYq47mAw5erOErvXzG5Nw r1 = new OnItemClickListener(z2, j3, z, j, string, j2, string2, j5, j4) {
            private final /* synthetic */ boolean f$1;
            private final /* synthetic */ long f$2;
            private final /* synthetic */ boolean f$3;
            private final /* synthetic */ long f$4;
            private final /* synthetic */ String f$5;
            private final /* synthetic */ long f$6;
            private final /* synthetic */ String f$7;
            private final /* synthetic */ long f$8;
            private final /* synthetic */ long f$9;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r5;
                this.f$4 = r6;
                this.f$5 = r8;
                this.f$6 = r9;
                this.f$7 = r11;
                this.f$8 = r12;
                this.f$9 = r14;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                SearchViewItemContextualDialog.lambda$onCreateDialog$0(SearchViewItemContextualDialog.this, this.f$1, this.f$2, this.f$3, this.f$4, this.f$5, this.f$6, this.f$7, this.f$8, this.f$9, adapterView, view, i, j);
            }
        };
        return mfpAlertDialogBuilder2.setItems(arrayList2, r20).create();
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(SearchViewItemContextualDialog searchViewItemContextualDialog, boolean z, long j, boolean z2, long j2, String str, long j3, String str2, long j4, long j5, AdapterView adapterView, View view, int i, long j6) {
        SearchViewItemContextualDialog searchViewItemContextualDialog2 = searchViewItemContextualDialog;
        if (i != 0) {
            return;
        }
        if (!z) {
            searchViewItemContextualDialog2.foodService.deleteFood(j, z2, j2, str, j3, str2, true, true);
        } else {
            searchViewItemContextualDialog2.foodService.hideFood(j2, str, j, j4, j3, str2, j5);
        }
    }
}
