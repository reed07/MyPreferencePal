package com.myfitnesspal.feature.meals.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.extension.ViewExtKt;
import com.myfitnesspal.shared.model.v1.FoodPermission.Permission;
import com.myfitnesspal.shared.ui.dialog.CustomLayoutBaseDialogFragment;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;
import java.util.Arrays;
import java.util.List;

public class MealFoodPermissionSelectionDialogFragment extends CustomLayoutBaseDialogFragment {
    private static final String EXTRA_SELECTED_PERMISSION_ORDINAL = "selected_permission_ordinal";
    private static final List<Permission> PERMISSIONS = Arrays.asList(new Permission[]{Permission.Public, Permission.Friends, Permission.Private});
    private OnMealFoodPermissionSelectedListener onMealFoodPermissionSelectedListener;
    /* access modifiers changed from: private */
    public Permission selectedPermission;

    public interface OnMealFoodPermissionSelectedListener {
        void onMealFoodPermissionSelected(Permission permission);
    }

    public static MealFoodPermissionSelectionDialogFragment newInstance(Permission permission) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(EXTRA_SELECTED_PERMISSION_ORDINAL, Integer.valueOf(permission.ordinal()));
        MealFoodPermissionSelectionDialogFragment mealFoodPermissionSelectionDialogFragment = new MealFoodPermissionSelectionDialogFragment();
        mealFoodPermissionSelectionDialogFragment.setArguments(bundle);
        return mealFoodPermissionSelectionDialogFragment;
    }

    public void setOnMealFoodPermissionSelectedListener(OnMealFoodPermissionSelectedListener onMealFoodPermissionSelectedListener2) {
        this.onMealFoodPermissionSelectedListener = onMealFoodPermissionSelectedListener2;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreateDialog(bundle);
        this.selectedPermission = Permission.values()[BundleUtils.getInt(EXTRA_SELECTED_PERMISSION_ORDINAL, Integer.valueOf(0), bundle, getArguments()).intValue()];
        ContextThemeWrapper dialogContextThemeWrapper = getDialogContextThemeWrapper();
        View inflate = LayoutInflater.from(dialogContextThemeWrapper).inflate(R.layout.list_dialog, null);
        ListView listView = (ListView) inflate.findViewById(R.id.listViewList);
        final ContextThemeWrapper contextThemeWrapper = dialogContextThemeWrapper;
        AnonymousClass1 r0 = new ArrayAdapter<Permission>(dialogContextThemeWrapper, R.layout.two_line_radio_button, R.id.text1, PERMISSIONS) {
            @NonNull
            public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
                boolean z = false;
                if (view == null) {
                    view = LayoutInflater.from(contextThemeWrapper).inflate(R.layout.two_line_radio_button, viewGroup, false);
                }
                TextView textView = (TextView) ViewUtils.findById(view, R.id.text2);
                CompoundButton compoundButton = (CompoundButton) ViewUtils.findById(view, R.id.radio1);
                Permission permission = (Permission) getItem(i);
                ((TextView) ViewUtils.findById(view, R.id.text1)).setText(permission.getNameResId());
                textView.setText(permission.getInfoResId());
                if (permission == MealFoodPermissionSelectionDialogFragment.this.selectedPermission) {
                    z = true;
                }
                compoundButton.setChecked(z);
                compoundButton.setOnClickListener(ViewExtKt.createAlertDialogRadioButtonClickListener((AdapterView) viewGroup, view, i, getItemId(i)));
                return view;
            }
        };
        listView.setAdapter(r0);
        listView.setOnItemClickListener(new OnItemClickListener(r0) {
            private final /* synthetic */ ArrayAdapter f$1;

            {
                this.f$1 = r2;
            }

            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                MealFoodPermissionSelectionDialogFragment.lambda$onCreateDialog$0(MealFoodPermissionSelectionDialogFragment.this, this.f$1, adapterView, view, i, j);
            }
        });
        return new MfpAlertDialogBuilder(dialogContextThemeWrapper).setTitle((int) R.string.sharing_preferences).setView(inflate).setPositiveButton((int) R.string.ok, (OnClickListener) new OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                MealFoodPermissionSelectionDialogFragment.this.onMealFoodPermissionSelectedListener.onMealFoodPermissionSelected(MealFoodPermissionSelectionDialogFragment.this.selectedPermission);
            }
        }).setNegativeButton((int) R.string.cancel, (OnClickListener) null).create();
    }

    public static /* synthetic */ void lambda$onCreateDialog$0(MealFoodPermissionSelectionDialogFragment mealFoodPermissionSelectionDialogFragment, ArrayAdapter arrayAdapter, AdapterView adapterView, View view, int i, long j) {
        mealFoodPermissionSelectionDialogFragment.selectedPermission = (Permission) PERMISSIONS.get(i);
        arrayAdapter.notifyDataSetChanged();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(EXTRA_SELECTED_PERMISSION_ORDINAL, this.selectedPermission.ordinal());
    }
}
