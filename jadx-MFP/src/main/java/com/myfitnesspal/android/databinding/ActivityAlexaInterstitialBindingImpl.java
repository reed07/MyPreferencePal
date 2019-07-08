package com.myfitnesspal.android.databinding;

import android.databinding.DataBindingComponent;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.alexainterstitial.ui.databinding.ContentTextCreator;

public class ActivityAlexaInterstitialBindingImpl extends ActivityAlexaInterstitialBinding {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    private long mDirtyFlags;
    @NonNull
    private final RelativeLayout mboundView0;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.close_button, 4);
        sViewsWithIds.put(R.id.alexa_image, 5);
        sViewsWithIds.put(R.id.enable_now_button, 6);
        sViewsWithIds.put(R.id.learn_more, 7);
    }

    public ActivityAlexaInterstitialBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 8, sIncludes, sViewsWithIds));
    }

    private ActivityAlexaInterstitialBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (ImageView) objArr[5], (ImageView) objArr[4], (TextView) objArr[1], (Button) objArr[6], null, (TextView) objArr[7], (TextView) objArr[2], (TextView) objArr[3]);
        this.mDirtyFlags = -1;
        this.dialogHint.setTag(null);
        this.mainText.setTag(null);
        this.mboundView0 = objArr[0];
        this.mboundView0.setTag(null);
        this.subText.setTag(null);
        View view2 = view;
        setRootTag(view);
        invalidateAll();
    }

    public void invalidateAll() {
        synchronized (this) {
            this.mDirtyFlags = 2;
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
        if (1 != i) {
            return false;
        }
        setTextCreator((ContentTextCreator) obj);
        return true;
    }

    public void setTextCreator(@Nullable ContentTextCreator contentTextCreator) {
        this.mTextCreator = contentTextCreator;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(1);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    public void executeBindings() {
        long j;
        CharSequence charSequence;
        CharSequence charSequence2;
        synchronized (this) {
            j = this.mDirtyFlags;
            this.mDirtyFlags = 0;
        }
        ContentTextCreator contentTextCreator = this.mTextCreator;
        String str = null;
        int i = ((j & 3) > 0 ? 1 : ((j & 3) == 0 ? 0 : -1));
        if (i == 0 || contentTextCreator == null) {
            charSequence2 = null;
            charSequence = null;
        } else {
            str = contentTextCreator.getSubText();
            charSequence2 = contentTextCreator.getMainText();
            charSequence = contentTextCreator.getHintText();
        }
        if (i != 0) {
            TextViewBindingAdapter.setText(this.dialogHint, charSequence);
            TextViewBindingAdapter.setText(this.mainText, charSequence2);
            TextViewBindingAdapter.setText(this.subText, str);
        }
    }
}
