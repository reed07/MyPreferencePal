package com.myfitnesspal.feature.diary.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.event.CopyEntriesToDateEvent;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.squareup.otto.Bus;
import com.uacf.core.util.BundleUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Calendar;
import javax.inject.Inject;

public class DiaryQuickToolsDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String SECTION_NAME = "section_name";
    @Inject
    Lazy<Bus> bus;
    private OnItemClickListener copyToDateListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            Calendar instance = Calendar.getInstance();
            switch (i) {
                case 0:
                    instance.add(5, 2);
                    DiaryQuickToolsDialogFragment.this.actOnCopy(instance);
                    return;
                case 1:
                    instance.add(5, 1);
                    DiaryQuickToolsDialogFragment.this.actOnCopy(instance);
                    return;
                case 2:
                    DiaryQuickToolsDialogFragment.this.actOnCopy(instance);
                    return;
                case 3:
                    instance.add(5, -1);
                    DiaryQuickToolsDialogFragment.this.actOnCopy(instance);
                    return;
                case 4:
                    instance.add(5, -2);
                    DiaryQuickToolsDialogFragment.this.actOnCopy(instance);
                    return;
                default:
                    return;
            }
        }
    };

    public static DiaryQuickToolsDialogFragment newInstance() {
        return newInstance(null);
    }

    public static DiaryQuickToolsDialogFragment newInstance(String str) {
        Bundle bundle = new Bundle();
        bundle.putString(SECTION_NAME, str);
        DiaryQuickToolsDialogFragment diaryQuickToolsDialogFragment = new DiaryQuickToolsDialogFragment();
        diaryQuickToolsDialogFragment.setArguments(bundle);
        return diaryQuickToolsDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        String string = getString(R.string.copy_to_date_colon);
        Calendar instance = Calendar.getInstance();
        instance.add(5, 2);
        arrayList.add(new DialogListTextItem(DateTimeUtils.formatBrief(instance.getTime())));
        Calendar.getInstance().add(5, 1);
        arrayList.add(new DialogListTextItem(getString(R.string.tomorrow)));
        arrayList.add(new DialogListTextItem(getString(R.string.today)));
        Calendar.getInstance().add(5, -1);
        arrayList.add(new DialogListTextItem(getString(R.string.yesterday)));
        Calendar instance2 = Calendar.getInstance();
        instance2.add(5, -2);
        arrayList.add(new DialogListTextItem(DateTimeUtils.formatBrief(instance2.getTime())));
        return new MfpAlertDialogBuilder(getActivity()).setTitle((CharSequence) string).setItems(arrayList, this.copyToDateListener).create();
    }

    /* access modifiers changed from: private */
    public void actOnCopy(Calendar calendar) {
        ((Bus) this.bus.get()).post(new CopyEntriesToDateEvent(calendar.getTime(), BundleUtils.getString(getArguments(), SECTION_NAME)));
    }
}
