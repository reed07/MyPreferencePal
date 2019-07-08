package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.event.MealNameEvent;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import dagger.Lazy;
import java.util.ArrayList;
import javax.inject.Inject;

public class MealNamesDialogFragment extends CustomLayoutBaseDialogFragment {
    private OnMealSelectedListener listener;
    @Inject
    Lazy<LocalizedStringsUtil> localizedStringsUtil;
    @Inject
    Lazy<Session> session;
    @Inject
    Lazy<UserEnergyService> userEnergyService;

    public interface OnMealSelectedListener {
        void onMealSelected(String str);
    }

    /* access modifiers changed from: protected */
    public int getTitleStringResId() {
        return R.string.meals;
    }

    public static MealNamesDialogFragment newInstance() {
        return new MealNamesDialogFragment();
    }

    public void setListener(OnMealSelectedListener onMealSelectedListener) {
        this.listener = onMealSelectedListener;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        final MealNames mealNames = ((Session) this.session.get()).getUser().getMealNames();
        if (mealNames.notEmpty()) {
            for (String mealNameString : mealNames.getNames()) {
                arrayList.add(new DialogListTextItem(((LocalizedStringsUtil) this.localizedStringsUtil.get()).getMealNameString(mealNameString, (UserEnergyService) this.userEnergyService.get())));
            }
        }
        return new MfpAlertDialogBuilder(getActivity()).setTitle(getTitleStringResId()).setItems(arrayList, new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MealNamesDialogFragment.this.mealSelected(mealNames.nameForId((long) (i + 1)));
            }
        }).create();
    }

    /* access modifiers changed from: protected */
    public void mealSelected(String str) {
        OnMealSelectedListener onMealSelectedListener = this.listener;
        if (onMealSelectedListener != null) {
            onMealSelectedListener.onMealSelected(str);
        }
        this.messageBus.post(new MealNameEvent(str));
    }
}
