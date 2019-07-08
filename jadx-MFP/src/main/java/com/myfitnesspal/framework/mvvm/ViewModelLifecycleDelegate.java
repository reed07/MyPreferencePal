package com.myfitnesspal.framework.mvvm;

import android.databinding.Observable.OnPropertyChangedCallback;
import android.os.Bundle;
import com.uacf.core.util.BundleUtils;
import com.uacf.taskrunner.Runner;

public class ViewModelLifecycleDelegate {
    private static final String VIEW_MODEL_ID;
    private ViewModelComponent component;
    private boolean notifyParentOfCacheMiss;
    private ViewModelParent parent;
    private RunnerViewModel runnerViewModel;
    private BaseViewModel viewModel;
    private ViewModelLifecycle viewModelLifecycle;

    private enum CreateResult {
        NoModel,
        CacheHit,
        CacheMiss
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(BaseViewModel.class.getCanonicalName());
        sb.append(".ViewModelId");
        VIEW_MODEL_ID = sb.toString();
    }

    public ViewModelLifecycleDelegate(ViewModelComponent viewModelComponent) {
        this(viewModelComponent, null);
    }

    public ViewModelLifecycleDelegate(ViewModelComponent viewModelComponent, ViewModelParent viewModelParent) {
        this.component = viewModelComponent;
        this.parent = viewModelParent;
    }

    public BaseViewModel getViewModel() {
        return this.viewModel;
    }

    public BaseViewModel setViewModel(BaseViewModel baseViewModel, OnPropertyChangedCallback onPropertyChangedCallback, Runner runner) {
        BaseViewModel baseViewModel2 = this.viewModel;
        if (baseViewModel != baseViewModel2) {
            ViewModelUtil.updateCallbacks(baseViewModel2, baseViewModel, onPropertyChangedCallback);
            BaseViewModel baseViewModel3 = this.viewModel;
            this.viewModel = baseViewModel;
            this.runnerViewModel = baseViewModel instanceof RunnerViewModel ? (RunnerViewModel) baseViewModel : null;
            this.viewModelLifecycle = baseViewModel instanceof ViewModelLifecycle ? (ViewModelLifecycle) baseViewModel : null;
            checkAttachToRunner(runner);
            this.component.onViewModelChanged(baseViewModel3, this.viewModel);
        }
        if (this.viewModel == null) {
            this.runnerViewModel = null;
            this.viewModelLifecycle = null;
        }
        return this.viewModel;
    }

    public void onCreate(Bundle bundle, ViewModelCache viewModelCache, OnPropertyChangedCallback onPropertyChangedCallback, Runner runner) {
        CreateResult createResult = CreateResult.NoModel;
        if (bundle != null && bundle.containsKey(VIEW_MODEL_ID)) {
            BaseViewModel baseViewModel = viewModelCache.get(BundleUtils.getString(bundle, VIEW_MODEL_ID));
            if (baseViewModel != null) {
                setViewModel(baseViewModel, onPropertyChangedCallback, runner);
                createResult = CreateResult.CacheHit;
            } else {
                createResult = CreateResult.CacheMiss;
            }
        }
        switch (createResult) {
            case CacheMiss:
                this.component.onViewModelReset();
                this.notifyParentOfCacheMiss = true;
                return;
            case CacheHit:
                this.component.onViewModelRestored(this.viewModel);
                this.component.onViewModelChanged(null, this.viewModel);
                return;
            default:
                return;
        }
    }

    public void onResume() {
        ViewModelLifecycle viewModelLifecycle2 = this.viewModelLifecycle;
        if (viewModelLifecycle2 != null) {
            viewModelLifecycle2.onResume();
        }
        if (this.notifyParentOfCacheMiss) {
            this.notifyParentOfCacheMiss = false;
            ViewModelParent viewModelParent = this.parent;
            if (viewModelParent != null) {
                viewModelParent.onChildViewModelReset(this.component);
            }
        }
    }

    public void onPause() {
        ViewModelLifecycle viewModelLifecycle2 = this.viewModelLifecycle;
        if (viewModelLifecycle2 != null) {
            viewModelLifecycle2.onPause();
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        BaseViewModel baseViewModel = this.viewModel;
        if (baseViewModel != null) {
            bundle.putString(VIEW_MODEL_ID, baseViewModel.getId());
        }
    }

    public void onDestroy(Runner runner, ViewModelCache viewModelCache) {
        if (this.viewModel != null) {
            RunnerViewModel runnerViewModel2 = this.runnerViewModel;
            if (runnerViewModel2 != null) {
                runnerViewModel2.detach(runner);
            }
            viewModelCache.put(this.viewModel);
            setViewModel(null, null, null);
            this.runnerViewModel = null;
            this.viewModelLifecycle = null;
        }
    }

    private void checkAttachToRunner(Runner runner) {
        RunnerViewModel runnerViewModel2 = this.runnerViewModel;
        if (runnerViewModel2 != null && runner != null && !runnerViewModel2.isAttachedTo(runner)) {
            this.runnerViewModel.attach(runner);
        }
    }
}
