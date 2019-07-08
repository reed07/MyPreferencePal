package com.myfitnesspal.android.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.model.ChangePasswordViewModel;

public abstract class ChangePasswordBinding extends ViewDataBinding {
    @NonNull
    public final Button changePassword;
    @NonNull
    public final ProgressBar changingProgressBar;
    @NonNull
    public final TextView learnMore;
    @Bindable
    protected ChangePasswordViewModel mViewmodel;
    @NonNull
    public final TextInputEditText newPassword;
    @NonNull
    public final TextInputLayout newPasswordInputLayout;
    @NonNull
    public final TextInputEditText retypePassword;
    @NonNull
    public final TextInputLayout retypePasswordInputLayout;

    public abstract void setViewmodel(@Nullable ChangePasswordViewModel changePasswordViewModel);

    protected ChangePasswordBinding(DataBindingComponent dataBindingComponent, View view, int i, Button button, ProgressBar progressBar, TextView textView, TextInputEditText textInputEditText, TextInputLayout textInputLayout, TextInputEditText textInputEditText2, TextInputLayout textInputLayout2) {
        super(dataBindingComponent, view, i);
        this.changePassword = button;
        this.changingProgressBar = progressBar;
        this.learnMore = textView;
        this.newPassword = textInputEditText;
        this.newPasswordInputLayout = textInputLayout;
        this.retypePassword = textInputEditText2;
        this.retypePasswordInputLayout = textInputLayout2;
    }

    @Nullable
    public ChangePasswordViewModel getViewmodel() {
        return this.mViewmodel;
    }

    @NonNull
    public static ChangePasswordBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ChangePasswordBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (ChangePasswordBinding) DataBindingUtil.inflate(layoutInflater, R.layout.change_password, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static ChangePasswordBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ChangePasswordBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return (ChangePasswordBinding) DataBindingUtil.inflate(layoutInflater, R.layout.change_password, null, false, dataBindingComponent);
    }

    public static ChangePasswordBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ChangePasswordBinding bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        return (ChangePasswordBinding) bind(dataBindingComponent, view, R.layout.change_password);
    }
}
