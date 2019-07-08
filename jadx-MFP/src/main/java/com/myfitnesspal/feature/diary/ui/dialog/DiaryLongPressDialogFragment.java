package com.myfitnesspal.feature.diary.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.diary.event.DeleteEntryEvent;
import com.myfitnesspal.feature.diary.event.ShowMealsDialogEvent;
import com.myfitnesspal.shared.constants.Constants.ABTest.MaterialAccidentalDelete201603;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.analytics.AnalyticsService;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextResImageItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.squareup.otto.Bus;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.ArrayList;
import javax.inject.Inject;

public class DiaryLongPressDialogFragment extends CustomLayoutBaseDialogFragment {
    @Inject
    Lazy<AnalyticsService> analyticsService;
    @Inject
    Lazy<Bus> bus;
    @Inject
    Lazy<ConfigService> configService;
    /* access modifiers changed from: private */
    public int deleteLocation = 0;
    /* access modifiers changed from: private */
    public int moveLocation = 1;

    public static DiaryLongPressDialogFragment newInstance(boolean z, boolean z2) {
        DiaryLongPressDialogFragment diaryLongPressDialogFragment = new DiaryLongPressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(Extras.IS_FOOD_ENTRY, z);
        bundle.putBoolean(Extras.IS_LANDSCAPE, z2);
        diaryLongPressDialogFragment.setArguments(bundle);
        return diaryLongPressDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        if (((ConfigService) this.configService.get()).isVariantEnabled(MaterialAccidentalDelete201603.NAME)) {
            this.deleteLocation = 1;
            this.moveLocation = 0;
        }
        FragmentActivity activity = getActivity();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DialogListTextResImageItem(R.string.deleteEntry, R.drawable.ic_delete_black_24dp));
        final boolean z = getArguments().getBoolean(Extras.IS_FOOD_ENTRY, false);
        if (z) {
            arrayList.add(this.moveLocation, new DialogListTextResImageItem(R.string.move_to, R.drawable.ic_move_black_24dp));
        }
        return new MfpAlertDialogBuilder(activity).setTitle((int) R.string.diary).setItems(arrayList, new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                Ln.d(Integer.valueOf(i), new Object[0]);
                Bus bus = (Bus) DiaryLongPressDialogFragment.this.bus.get();
                if (i == DiaryLongPressDialogFragment.this.deleteLocation || !z) {
                    ((AnalyticsService) DiaryLongPressDialogFragment.this.analyticsService.get()).reportEvent(Events.DIARY_DELETEBTN_LONGPRESS);
                    bus.post(new DeleteEntryEvent());
                } else if (i == DiaryLongPressDialogFragment.this.moveLocation) {
                    bus.post(new ShowMealsDialogEvent());
                    DiaryLongPressDialogFragment.this.dismiss();
                }
            }
        }).setDismissOnItemClick(false).create();
    }
}
