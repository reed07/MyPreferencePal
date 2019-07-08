package com.myfitnesspal.android.databinding;

import android.databinding.Bindable;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.alexainterstitial.ui.databinding.ContentTextCreator;

public abstract class ActivityAlexaInterstitialBinding extends ViewDataBinding {
    @NonNull
    public final ImageView alexaImage;
    @NonNull
    public final ImageView closeButton;
    @NonNull
    public final TextView dialogHint;
    @NonNull
    public final Button enableNowButton;
    @Nullable
    public final Guideline guideline;
    @NonNull
    public final TextView learnMore;
    @Bindable
    protected ContentTextCreator mTextCreator;
    @NonNull
    public final TextView mainText;
    @NonNull
    public final TextView subText;

    public abstract void setTextCreator(@Nullable ContentTextCreator contentTextCreator);

    protected ActivityAlexaInterstitialBinding(DataBindingComponent dataBindingComponent, View view, int i, ImageView imageView, ImageView imageView2, TextView textView, Button button, Guideline guideline2, TextView textView2, TextView textView3, TextView textView4) {
        super(dataBindingComponent, view, i);
        this.alexaImage = imageView;
        this.closeButton = imageView2;
        this.dialogHint = textView;
        this.enableNowButton = button;
        this.guideline = guideline2;
        this.learnMore = textView2;
        this.mainText = textView3;
        this.subText = textView4;
    }

    @Nullable
    public ContentTextCreator getTextCreator() {
        return this.mTextCreator;
    }

    @NonNull
    public static ActivityAlexaInterstitialBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAlexaInterstitialBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable DataBindingComponent dataBindingComponent) {
        return (ActivityAlexaInterstitialBinding) DataBindingUtil.inflate(layoutInflater, R.layout.activity_alexa_interstitial, viewGroup, z, dataBindingComponent);
    }

    @NonNull
    public static ActivityAlexaInterstitialBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ActivityAlexaInterstitialBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable DataBindingComponent dataBindingComponent) {
        return (ActivityAlexaInterstitialBinding) DataBindingUtil.inflate(layoutInflater, R.layout.activity_alexa_interstitial, null, false, dataBindingComponent);
    }

    public static ActivityAlexaInterstitialBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    public static ActivityAlexaInterstitialBinding bind(@NonNull View view, @Nullable DataBindingComponent dataBindingComponent) {
        return (ActivityAlexaInterstitialBinding) bind(dataBindingComponent, view, R.layout.activity_alexa_interstitial);
    }
}
