package com.myfitnesspal.feature.goals.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.goals.ui.adapter.ActivityItem;
import com.myfitnesspal.feature.goals.ui.adapter.ActivityItemAdapter;
import com.myfitnesspal.shared.constants.Constants.Exercise.ActivityLevel;
import com.myfitnesspal.shared.event.ActivityLevelDialogEvent;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Iterator;

public class ActivityLevelDialogFragment extends CustomLayoutBaseDialogFragment {
    ActivityItemAdapter activityItemAdapter;
    ArrayList<ActivityItem> activityLevel;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public static ActivityLevelDialogFragment newInstance() {
        return new ActivityLevelDialogFragment();
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        View inflate = LayoutInflater.from(dialogContextThemeWrapper).inflate(R.layout.list_dialog, null);
        ListView listView = (ListView) inflate.findViewById(R.id.listViewList);
        listView.setChoiceMode(1);
        this.activityLevel = new ArrayList<>();
        String lifestyleName = getSession().getUser().getProfile().getLifestyleName();
        Resources resources = dialogContextThemeWrapper.getResources();
        this.activityLevel.add(new ActivityItem(ActivityLevel.SEDENTARY, resources.getString(R.string.not_very_active), resources.getString(R.string.sedentary), Strings.equals(lifestyleName, ActivityLevel.SEDENTARY)));
        this.activityLevel.add(new ActivityItem(ActivityLevel.LIGHTLY_ACTIVE, resources.getString(R.string.lightActivetxt), resources.getString(R.string.lightlyActive), Strings.equals(lifestyleName, ActivityLevel.LIGHTLY_ACTIVE)));
        this.activityLevel.add(new ActivityItem(ActivityLevel.ACTIVE, resources.getString(R.string.activetxt), resources.getString(R.string.active), Strings.equals(lifestyleName, ActivityLevel.ACTIVE)));
        this.activityLevel.add(new ActivityItem(ActivityLevel.VERY_ACTIVE, resources.getString(R.string.veryActivetxt), resources.getString(R.string.veryActive), Strings.equals(lifestyleName, ActivityLevel.VERY_ACTIVE)));
        this.activityItemAdapter = new ActivityItemAdapter(dialogContextThemeWrapper, R.layout.two_line_radio_button, this.activityLevel);
        listView.setAdapter(this.activityItemAdapter);
        Iterator it = this.activityLevel.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((ActivityItem) it.next()).getState()) {
                    listView.setItemChecked(0, true);
                    break;
                }
            } else {
                break;
            }
        }
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ActivityLevelDialogFragment.this.refreshUI(i);
            }
        });
        return new MfpAlertDialogBuilder(dialogContextThemeWrapper).setTitle((int) R.string.activityLevelTxt).setView(inflate).setPositiveButton((int) R.string.setBtn, (OnClickListener) new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ActivityLevelDialogFragment.this.doSave();
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).create();
    }

    /* access modifiers changed from: private */
    public void refreshUI(int i) {
        Iterator it = this.activityLevel.iterator();
        int i2 = 0;
        while (it.hasNext()) {
            ((ActivityItem) it.next()).setState(i2 == i);
            i2++;
        }
        this.activityItemAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void doSave() {
        Iterator it = this.activityLevel.iterator();
        while (it.hasNext()) {
            ActivityItem activityItem = (ActivityItem) it.next();
            if (activityItem.getState()) {
                this.messageBus.post(new ActivityLevelDialogEvent(activityItem));
                return;
            }
        }
    }
}
