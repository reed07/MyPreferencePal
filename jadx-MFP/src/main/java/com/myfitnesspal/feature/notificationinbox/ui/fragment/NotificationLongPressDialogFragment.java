package com.myfitnesspal.feature.notificationinbox.ui.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import java.util.ArrayList;

public class NotificationLongPressDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_NOTIFICATION_ID = "extra_notification_id";
    private static final String EXTRA_NOTIFICATION_POSITION = "extra_notification_position";
    /* access modifiers changed from: private */
    public OnDeleteClickedListener listener;

    public interface OnDeleteClickedListener {
        void onDeleteClicked(int i);

        void onDeleteClicked(String str);
    }

    public static NotificationLongPressDialogFragment newInstance(String str) {
        NotificationLongPressDialogFragment notificationLongPressDialogFragment = new NotificationLongPressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_NOTIFICATION_ID, str);
        notificationLongPressDialogFragment.setArguments(bundle);
        return notificationLongPressDialogFragment;
    }

    public static NotificationLongPressDialogFragment newInstance(int i) {
        NotificationLongPressDialogFragment notificationLongPressDialogFragment = new NotificationLongPressDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA_NOTIFICATION_POSITION, i);
        notificationLongPressDialogFragment.setArguments(bundle);
        return notificationLongPressDialogFragment;
    }

    public void setOnDeleteClickedListener(OnDeleteClickedListener onDeleteClickedListener) {
        this.listener = onDeleteClickedListener;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        final String string = getArguments().getString(EXTRA_NOTIFICATION_ID);
        final int i = getArguments().getInt(EXTRA_NOTIFICATION_POSITION);
        arrayList.add(new DialogListTextItem(getContext().getString(R.string.delete_notification)));
        return new MfpAlertDialogBuilder(getActivity()).setItems(arrayList, new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (NotificationLongPressDialogFragment.this.listener != null) {
                    NotificationLongPressDialogFragment.this.listener.onDeleteClicked(string);
                    NotificationLongPressDialogFragment.this.listener.onDeleteClicked(i);
                }
            }
        }).setDismissOnItemClick(true).create();
    }
}
