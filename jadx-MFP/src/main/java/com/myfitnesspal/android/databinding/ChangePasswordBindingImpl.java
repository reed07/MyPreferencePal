package com.myfitnesspal.android.databinding;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.DataBindingComponent;
import android.databinding.InverseBindingListener;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.TextViewBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter.AfterTextChanged;
import android.databinding.adapters.TextViewBindingAdapter.BeforeTextChanged;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.generated.callback.OnClickListener.Listener;
import com.myfitnesspal.android.generated.callback.OnTextChanged;
import com.myfitnesspal.feature.settings.model.ChangePasswordViewModel;
import com.myfitnesspal.feature.settings.model.ChangePasswordViewModelKt;

public class ChangePasswordBindingImpl extends ChangePasswordBinding implements Listener, OnTextChanged.Listener {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final TextViewBindingAdapter.OnTextChanged mCallback5;
    @Nullable
    private final TextViewBindingAdapter.OnTextChanged mCallback6;
    @Nullable
    private final OnClickListener mCallback7;
    private long mDirtyFlags;
    @NonNull
    private final ScrollView mboundView0;

    static {
        sViewsWithIds.put(R.id.newPasswordInputLayout, 5);
        sViewsWithIds.put(R.id.retypePasswordInputLayout, 6);
        sViewsWithIds.put(R.id.changingProgressBar, 7);
    }

    public ChangePasswordBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private ChangePasswordBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (Button) objArr[4], (ProgressBar) objArr[7], (TextView) objArr[1], (TextInputEditText) objArr[2], (TextInputLayout) objArr[5], (TextInputEditText) objArr[3], (TextInputLayout) objArr[6]);
        this.mDirtyFlags = -1;
        this.changePassword.setTag(null);
        this.learnMore.setTag(null);
        this.mboundView0 = objArr[0];
        this.mboundView0.setTag(null);
        this.newPassword.setTag(null);
        this.retypePassword.setTag(null);
        setRootTag(view);
        this.mCallback7 = new com.myfitnesspal.android.generated.callback.OnClickListener(this, 3);
        this.mCallback6 = new OnTextChanged(this, 2);
        this.mCallback5 = new OnTextChanged(this, 1);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 8;
        }
        requestRebind();
    }

    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.mDirtyFlags != 0) {
                return true;
            }
            return false;
        }
    }

    public boolean setVariable(int i, @Nullable Object obj) {
        if (2 != i) {
            return false;
        }
        setViewmodel((ChangePasswordViewModel) obj);
        return true;
    }

    public void setViewmodel(@Nullable ChangePasswordViewModel changePasswordViewModel) {
        this.mViewmodel = changePasswordViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 4;
        }
        notifyPropertyChanged(2);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        switch (i) {
            case 0:
                return onChangeViewmodelEnabledToChange((MutableLiveData) obj, i2);
            case 1:
                return onChangeViewmodelForcedStatus((MutableLiveData) obj, i2);
            default:
                return false;
        }
    }

    private boolean onChangeViewmodelEnabledToChange(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        return true;
    }

    private boolean onChangeViewmodelForcedStatus(MutableLiveData<Boolean> mutableLiveData, int i) {
        if (i != 0) {
            return false;
        }
        synchronized (this) {
            this.mDirtyFlags |= 2;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        int i;
        boolean z;
        OnFocusChangeListener onFocusChangeListener;
        OnFocusChangeListener onFocusChangeListener2;
        long j2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ChangePasswordViewModel changePasswordViewModel = this.mViewmodel;
        if ((15 & j) != 0) {
            if ((j & 12) == 0 || changePasswordViewModel == null) {
                onFocusChangeListener2 = null;
                onFocusChangeListener = null;
            } else {
                onFocusChangeListener2 = changePasswordViewModel.getRetypeOnFocusChangeListener();
                onFocusChangeListener = changePasswordViewModel.getNewOnFocusChangeListener();
            }
            if ((j & 13) != 0) {
                MutableLiveData enabledToChange = changePasswordViewModel != null ? changePasswordViewModel.getEnabledToChange() : null;
                updateLiveDataRegistration(0, enabledToChange);
                z = ViewDataBinding.safeUnbox(enabledToChange != null ? (Boolean) enabledToChange.getValue() : null);
            } else {
                z = false;
            }
            int i2 = ((j & 14) > 0 ? 1 : ((j & 14) == 0 ? 0 : -1));
            if (i2 != 0) {
                MutableLiveData forcedStatus = changePasswordViewModel != null ? changePasswordViewModel.getForcedStatus() : null;
                updateLiveDataRegistration(1, forcedStatus);
                boolean safeUnbox = ViewDataBinding.safeUnbox(forcedStatus != null ? (Boolean) forcedStatus.getValue() : null);
                if (i2 != 0) {
                    j = safeUnbox ? j | 32 : j | 16;
                }
                i = safeUnbox ? 0 : 8;
            } else {
                i = 0;
            }
        } else {
            onFocusChangeListener2 = null;
            onFocusChangeListener = null;
            z = false;
            i = 0;
        }
        if ((j & 13) != 0) {
            this.changePassword.setEnabled(z);
        }
        if ((8 & j) != 0) {
            this.changePassword.setOnClickListener(this.mCallback7);
            BeforeTextChanged beforeTextChanged = null;
            AfterTextChanged afterTextChanged = null;
            InverseBindingListener inverseBindingListener = null;
            TextViewBindingAdapter.setTextWatcher(this.newPassword, beforeTextChanged, this.mCallback5, afterTextChanged, inverseBindingListener);
            TextViewBindingAdapter.setTextWatcher(this.retypePassword, beforeTextChanged, this.mCallback6, afterTextChanged, inverseBindingListener);
        }
        if ((14 & j) != 0) {
            this.learnMore.setVisibility(i);
            j2 = 12;
        } else {
            j2 = 12;
        }
        if ((j & j2) != 0) {
            ChangePasswordViewModelKt.bindFocusChange(this.newPassword, onFocusChangeListener);
            ChangePasswordViewModelKt.bindFocusChange(this.retypePassword, onFocusChangeListener2);
        }
    }

    public final void _internalCallbackOnClick(int i, View view) {
        ChangePasswordViewModel changePasswordViewModel = this.mViewmodel;
        if (changePasswordViewModel != null) {
            changePasswordViewModel.changePassword();
        }
    }

    public final void _internalCallbackOnTextChanged(int i, CharSequence charSequence, int i2, int i3, int i4) {
        boolean z = true;
        switch (i) {
            case 1:
                ChangePasswordViewModel changePasswordViewModel = this.mViewmodel;
                if (changePasswordViewModel == null) {
                    z = false;
                }
                if (z) {
                    changePasswordViewModel.newPasswordTextChanged(charSequence);
                    return;
                }
                return;
            case 2:
                ChangePasswordViewModel changePasswordViewModel2 = this.mViewmodel;
                if (changePasswordViewModel2 == null) {
                    z = false;
                }
                if (z) {
                    changePasswordViewModel2.retypePasswordTextChanged(charSequence);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
