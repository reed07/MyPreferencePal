package com.myfitnesspal.feature.appgallery.ui;

import android.annotation.SuppressLint;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.model.GoogleFitPermissionsViewModel;
import com.myfitnesspal.feature.appgallery.model.GoogleFitPermissionsViewModel.Property;
import com.myfitnesspal.feature.appgallery.util.AppGalleryUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.event.GoogleFitConnectedEvent;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ListViewUtils;
import java.util.Collection;

public class GoogleFitPermissionsFragment extends MfpFragment {
    @BindView(2131362215)
    View continueBtn;
    @BindView(2131363261)
    ListView permissionsListView;
    /* access modifiers changed from: private */
    public GoogleFitPermissionsViewModel viewModel;

    public static class GoogleFitPermissionScopedConnectionEstablishedEvent {
    }

    public static GoogleFitPermissionsFragment newInstance() {
        return new GoogleFitPermissionsFragment();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.google_fit_connect_permissions, viewGroup, false);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        MaterialUtils.enableListViewNestedScrolling(this.permissionsListView);
        initViewModel();
        initAdapter();
        initListeners();
    }

    @SuppressLint({"RestrictedApi"})
    private void initAdapter() {
        this.permissionsListView.setAdapter(new GoogleFitPermissionsAdapter(getLayoutInflater(null), this.viewModel.getPermissionItems()));
    }

    private void initViewModel() {
        this.viewModel = (GoogleFitPermissionsViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (GoogleFitPermissionsViewModel) setViewModel(new GoogleFitPermissionsViewModel(getActivity()));
        }
    }

    private void initListeners() {
        this.permissionsListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                GoogleFitPermissionsFragment.this.viewModel.togglePermissionItemState(i);
            }
        });
        this.continueBtn.setEnabled(!CollectionUtils.isEmpty((Collection<?>) this.viewModel.getScopesFromPermissions()));
        this.continueBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MfpActivity mfpActivity = (MfpActivity) GoogleFitPermissionsFragment.this.getActivity();
                if (!AppGalleryUtil.isGoogleFitConnected(mfpActivity)) {
                    mfpActivity.getGoogleFitClient().setScopeAndConnect(GoogleFitPermissionsFragment.this.viewModel.getScopesFromPermissions());
                }
            }
        });
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        super.onViewModelPropertyChanged(observable, i);
        if (i == Property.PERMISSION_STATE_CHANGE) {
            ListViewUtils.notifyDataSetChanged(this.permissionsListView);
            this.continueBtn.setEnabled(!CollectionUtils.isEmpty((Collection<?>) this.viewModel.getScopesFromPermissions()));
        }
    }

    @Subscribe
    public void onFitConnectedEvent(GoogleFitConnectedEvent googleFitConnectedEvent) {
        getMessageBus().post(new GoogleFitPermissionScopedConnectionEstablishedEvent());
    }
}
