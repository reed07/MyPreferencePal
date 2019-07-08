package com.myfitnesspal.feature.progress.ui.fragment;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.progress.constants.SharingPermission;
import com.myfitnesspal.feature.progress.event.SharePermissionChangedEvent;
import com.myfitnesspal.feature.progress.ui.activity.LearnAboutPermissionActivity;
import com.myfitnesspal.shared.ui.dialog.SingleChoiceListDialogWithCustomAdapterAndNeutralButton;
import com.uacf.core.util.BundleUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharePermissionDialogFragment extends SingleChoiceListDialogWithCustomAdapterAndNeutralButton<SharingPermission> {
    private static final String EXTRA_SHARE_PERMISSION = "share_permission";
    /* access modifiers changed from: private */
    public static final ArrayList<SharingPermission> PERMISSIONS = new ArrayList<>(Arrays.asList(new SharingPermission[]{SharingPermission.Community, SharingPermission.FriendsOnly}));
    private SharingPermission sharingPermission;

    /* access modifiers changed from: protected */
    public int getNegativeButtonString() {
        return R.string.cancel;
    }

    /* access modifiers changed from: protected */
    public int getNeutralButtonString() {
        return R.string.button_learn_more;
    }

    /* access modifiers changed from: protected */
    public int getPositiveButtonString() {
        return R.string.button_set;
    }

    public static SharePermissionDialogFragment newInstance(SharingPermission sharingPermission2) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SHARE_PERMISSION, sharingPermission2);
        SharePermissionDialogFragment sharePermissionDialogFragment = new SharePermissionDialogFragment();
        sharePermissionDialogFragment.setArguments(bundle);
        return sharePermissionDialogFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.sharingPermission = (SharingPermission) BundleUtils.getSerializable(getArguments(), EXTRA_SHARE_PERMISSION, SharingPermission.Community, SharingPermission.class.getClassLoader());
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        getArguments().putSerializable(EXTRA_SHARE_PERMISSION, (Serializable) PERMISSIONS.get(getCurrentSelectedItemIndex()));
    }

    /* access modifiers changed from: protected */
    public String getTitle() {
        return getString(R.string.share_permissions);
    }

    /* access modifiers changed from: protected */
    public List<SharingPermission> getItems() {
        return PERMISSIONS;
    }

    /* access modifiers changed from: protected */
    public int getInitialSelectedItemIndex() {
        return PERMISSIONS.indexOf(this.sharingPermission);
    }

    /* access modifiers changed from: protected */
    public String getTextFromItem(SharingPermission sharingPermission2) {
        return getContext().getString(sharingPermission2.getStringId());
    }

    /* access modifiers changed from: protected */
    public void onOkClicked(SharingPermission sharingPermission2) {
        this.messageBus.post(new SharePermissionChangedEvent(sharingPermission2));
    }

    /* access modifiers changed from: protected */
    public OnClickListener getNeutralButtonOnClickListener() {
        return new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SharePermissionDialogFragment.this.messageBus.post(new SharePermissionChangedEvent((SharingPermission) SharePermissionDialogFragment.PERMISSIONS.get(SharePermissionDialogFragment.this.getCurrentSelectedItemIndex())));
                SharePermissionDialogFragment.this.getNavigationHelper().withIntent(LearnAboutPermissionActivity.newStartIntent(SharePermissionDialogFragment.this.getContext())).startActivity(188);
            }
        };
    }
}
