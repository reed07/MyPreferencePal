package com.myfitnesspal.shared.ui.dialog.impl;

import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.event.DeletePhotoEvent;
import com.myfitnesspal.shared.event.ShareProgressPhotoEvent;
import com.myfitnesspal.shared.event.StartCameraEvent;
import com.myfitnesspal.shared.event.StartMediaChooserEvent;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.DialogListTextResImageItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class ImageChooserDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_OPTION_ICON_ID = "extra_option_icon_id";
    private static final String EXTRA_OPTION_STRING_ID = "extra_option_res_id";
    private static final String SHOW_DELETE_PHOTO_OPTION = "show_remove_image_option";
    private static final String TITLE_RES_ID = "title_res_id";
    @Inject
    PackageManager packageManager;

    public static ImageChooserDialogFragment newInstance(int i) {
        return newInstance(i, false);
    }

    public static ImageChooserDialogFragment newInstance(int i, boolean z) {
        return newInstance(i, 0, 0, z);
    }

    public static ImageChooserDialogFragment newInstance(int i, int i2, int i3, boolean z) {
        ImageChooserDialogFragment imageChooserDialogFragment = new ImageChooserDialogFragment();
        Bundle bundle = new Bundle();
        if (i != 0) {
            bundle.putInt(TITLE_RES_ID, i);
        }
        if (!(i2 == 0 || i3 == 0)) {
            bundle.putInt(EXTRA_OPTION_STRING_ID, i2);
            bundle.putInt(EXTRA_OPTION_ICON_ID, i3);
        }
        bundle.putBoolean(SHOW_DELETE_PHOTO_OPTION, z);
        imageChooserDialogFragment.setArguments(bundle);
        return imageChooserDialogFragment;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        component().inject(this);
        ArrayList arrayList = new ArrayList();
        int i = BundleUtils.getInt(getArguments(), TITLE_RES_ID);
        int i2 = BundleUtils.getInt(getArguments(), EXTRA_OPTION_STRING_ID);
        int i3 = BundleUtils.getInt(getArguments(), EXTRA_OPTION_ICON_ID);
        boolean z = BundleUtils.getBoolean(getArguments(), SHOW_DELETE_PHOTO_OPTION);
        if (this.packageManager.hasSystemFeature("android.hardware.camera")) {
            arrayList.add(new DialogListTextResImageItem(R.string.photoOptionBtn1, R.drawable.ic_photo_camera_black_24dp));
        }
        arrayList.add(new DialogListTextResImageItem(R.string.photoOptionBtn2, R.drawable.ic_collections_black_24dp));
        if (i2 > 0 && i3 > 0) {
            arrayList.add(new DialogListTextResImageItem(i2, i3));
        }
        if (z) {
            arrayList.add(new DialogListTextResImageItem(R.string.progress_photos_entry_delete_photo, R.drawable.ic_delete_black_24dp));
        }
        return new MfpAlertDialogBuilder(getDialogContextThemeWrapper()).setTitle(i).setItems(arrayList, new OnItemClickListener(arrayList) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                ImageChooserDialogFragment.lambda$onCreateDialog$0(ImageChooserDialogFragment.this, this.f$1, adapterView, view, i, j);
            }
        }).create();
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(ImageChooserDialogFragment imageChooserDialogFragment, List list, AdapterView adapterView, View view, int i, long j) {
        switch (((DialogListTextResImageItem) list.get(i)).getTextResId()) {
            case R.string.add_progress_photo /*2131886268*/:
                imageChooserDialogFragment.messageBus.post(new ShareProgressPhotoEvent());
                return;
            case R.string.photoOptionBtn1 /*2131888690*/:
                imageChooserDialogFragment.messageBus.post(new StartCameraEvent());
                return;
            case R.string.photoOptionBtn2 /*2131888691*/:
                imageChooserDialogFragment.messageBus.post(new StartMediaChooserEvent());
                return;
            case R.string.progress_photos_entry_delete_photo /*2131888825*/:
                imageChooserDialogFragment.messageBus.post(new DeletePhotoEvent());
                return;
            default:
                return;
        }
    }
}
