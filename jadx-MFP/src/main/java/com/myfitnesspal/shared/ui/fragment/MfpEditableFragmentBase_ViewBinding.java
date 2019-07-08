package com.myfitnesspal.shared.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.ui.view.EmptyStateView;

public class MfpEditableFragmentBase_ViewBinding implements Unbinder {
    private MfpEditableFragmentBase target;

    @UiThread
    public MfpEditableFragmentBase_ViewBinding(MfpEditableFragmentBase mfpEditableFragmentBase, View view) {
        this.target = mfpEditableFragmentBase;
        mfpEditableFragmentBase.listView = (ListView) Utils.findRequiredViewAsType(view, 16908298, "field 'listView'", ListView.class);
        mfpEditableFragmentBase.emptyListState = (EmptyStateView) Utils.findRequiredViewAsType(view, R.id.empty_list_message, "field 'emptyListState'", EmptyStateView.class);
        mfpEditableFragmentBase.refreshLayout = (SwipeRefreshLayout) Utils.findOptionalViewAsType(view, R.id.refreshLayout, "field 'refreshLayout'", SwipeRefreshLayout.class);
        mfpEditableFragmentBase.addItemFooterButton = (Button) Utils.findRequiredViewAsType(view, R.id.btn_add_item, "field 'addItemFooterButton'", Button.class);
    }

    @CallSuper
    public void unbind() {
        MfpEditableFragmentBase mfpEditableFragmentBase = this.target;
        if (mfpEditableFragmentBase != null) {
            this.target = null;
            mfpEditableFragmentBase.listView = null;
            mfpEditableFragmentBase.emptyListState = null;
            mfpEditableFragmentBase.refreshLayout = null;
            mfpEditableFragmentBase.addItemFooterButton = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
