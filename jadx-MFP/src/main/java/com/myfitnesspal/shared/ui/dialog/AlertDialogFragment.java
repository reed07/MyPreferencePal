package com.myfitnesspal.shared.ui.dialog;

import android.os.Bundle;
import android.view.View;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 \u00042\u000e\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0004B\u0005¢\u0006\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/shared/ui/dialog/AlertDialogFragment;", "Lcom/myfitnesspal/shared/ui/dialog/AlertDialogFragmentBase;", "", "()V", "Companion", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: AlertDialogFragment.kt */
public final class AlertDialogFragment extends AlertDialogFragmentBase<AlertDialogFragment, String> {
    public static final Companion Companion = new Companion(null);
    private HashMap _$_findViewCache;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0007¨\u0006\u0005"}, d2 = {"Lcom/myfitnesspal/shared/ui/dialog/AlertDialogFragment$Companion;", "", "()V", "newInstance", "Lcom/myfitnesspal/shared/ui/dialog/AlertDialogFragment;", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
    /* compiled from: AlertDialogFragment.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final AlertDialogFragment newInstance() {
            AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
            alertDialogFragment.setArguments(new Bundle());
            return alertDialogFragment;
        }
    }

    @JvmStatic
    @NotNull
    public static final AlertDialogFragment newInstance() {
        return Companion.newInstance();
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }
}
