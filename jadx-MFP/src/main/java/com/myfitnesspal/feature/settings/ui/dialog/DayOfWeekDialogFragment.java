package com.myfitnesspal.feature.settings.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.event.DayOfWeekEvent;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.util.ArrayUtil;
import java.util.ArrayList;

public class DayOfWeekDialogFragment extends CustomLayoutBaseDialogFragment {
    public static DayOfWeekDialogFragment newInstance() {
        return new DayOfWeekDialogFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        String[] stringArray = getResources().getStringArray(R.array.day_of_week);
        ArrayList arrayList = new ArrayList(ArrayUtil.size(stringArray));
        for (String dialogListTextItem : stringArray) {
            arrayList.add(new DialogListTextItem(dialogListTextItem));
        }
        return new MfpAlertDialogBuilder(getActivity()).setTitle((int) R.string.day_of_the_week).setItems(arrayList, new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                DayOfWeekDialogFragment.this.messageBus.post(new DayOfWeekEvent(DateTimeUtils.getDayString(Integer.valueOf(i + 1))));
            }
        }).create();
    }
}
