package com.myfitnesspal.feature.images.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.ImportDevice;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextResImageItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChooseImageDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final DateFormat DATE_TITLE_FORMATTER = DateFormat.getDateInstance(2);
    private static final String EXTRA_DIALOG_TITLE = "extra_dialog_title";
    private static final String EXTRA_SHOW_REMOVE_PHOTO = "extra_show_remove_photo";
    private static final int ITEM_ID_CAMERA = 2;
    private static final int ITEM_ID_PICKER = 1;
    private static final int ITEM_ID_REMOVE = 3;
    private OnImportDeviceSelectedListener listener;
    private String title;

    public interface OnImportDeviceSelectedListener {
        void onImportDeviceSelected(ImportDevice importDevice);

        void onRemovePhoto();
    }

    public static ChooseImageDialogFragment newInstance(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_DIALOG_TITLE, str);
        bundle.putBoolean(EXTRA_SHOW_REMOVE_PHOTO, z);
        ChooseImageDialogFragment chooseImageDialogFragment = new ChooseImageDialogFragment();
        chooseImageDialogFragment.setArguments(bundle);
        return chooseImageDialogFragment;
    }

    public static ChooseImageDialogFragment newInstance(String str) {
        return newInstance(str, false);
    }

    public static ChooseImageDialogFragment newInstance(Date date) {
        return newInstance(DATE_TITLE_FORMATTER.format(date));
    }

    public void setOnImportDeviceSelectedListener(OnImportDeviceSelectedListener onImportDeviceSelectedListener) {
        this.listener = onImportDeviceSelectedListener;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.title = getArguments().getString(EXTRA_DIALOG_TITLE);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new DialogListTextResImageItem(R.string.progress_photos_import_photo, R.drawable.ic_collections_black_24dp, 1));
        arrayList.add(new DialogListTextResImageItem(R.string.progress_photos_take_photo, R.drawable.ic_photo_camera_black_24dp, 2));
        if (getArguments().getBoolean(EXTRA_SHOW_REMOVE_PHOTO)) {
            arrayList.add(new DialogListTextResImageItem(R.string.progress_photos_entry_delete_photo, R.drawable.ic_delete_black_24dp, 3));
        }
        return new MfpAlertDialogBuilder(getActivity()).setTitle((CharSequence) this.title).setItems(arrayList, new OnItemClickListener(arrayList) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                ChooseImageDialogFragment.this.onDialogItemClicked(((DialogListTextResImageItem) this.f$1.get(i)).getItemId());
            }
        }).setDismissOnItemClick(true).create();
    }

    /* access modifiers changed from: private */
    public void onDialogItemClicked(int i) {
        OnImportDeviceSelectedListener onImportDeviceSelectedListener = this.listener;
        if (onImportDeviceSelectedListener == null) {
            return;
        }
        if (i == 1) {
            onImportDeviceSelectedListener.onImportDeviceSelected(ImportDevice.Picker);
        } else if (i == 2) {
            onImportDeviceSelectedListener.onImportDeviceSelected(ImportDevice.Camera);
        } else if (i == 3) {
            onImportDeviceSelectedListener.onRemovePhoto();
        }
    }
}
