package com.myfitnesspal.android.databinding;

import android.databinding.DataBindingComponent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseIntArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.android.generated.callback.OnClickListener.Listener;
import com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingFlowViewModel;

public class OnboardingAnimatedFragmentBindingImpl extends OnboardingAnimatedFragmentBinding implements Listener {
    @Nullable
    private static final IncludedLayouts sIncludes = null;
    @Nullable
    private static final SparseIntArray sViewsWithIds = new SparseIntArray();
    @Nullable
    private final OnClickListener mCallback3;
    @Nullable
    private final OnClickListener mCallback4;
    private long mDirtyFlags;
    @NonNull
    private final LinearLayout mboundView0;
    @NonNull
    private final TextView mboundView5;

    /* access modifiers changed from: protected */
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    static {
        sViewsWithIds.put(R.id.screenHeader, 6);
    }

    public OnboardingAnimatedFragmentBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, mapBindings(dataBindingComponent, view, 7, sIncludes, sViewsWithIds));
    }

    private OnboardingAnimatedFragmentBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[4], (LottieAnimationView) objArr[1], (TextView) objArr[3], (TextView) objArr[6], (TextView) objArr[2]);
        this.mDirtyFlags = -1;
        this.btnOpenFeature.setTag(null);
        this.mboundView0 = objArr[0];
        this.mboundView0.setTag(null);
        this.mboundView5 = objArr[5];
        this.mboundView5.setTag(null);
        this.screenAnimation.setTag(null);
        this.screenDescription.setTag(null);
        this.screenTitle.setTag(null);
        setRootTag(view);
        this.mCallback4 = new com.myfitnesspal.android.generated.callback.OnClickListener(this, 2);
        this.mCallback3 = new com.myfitnesspal.android.generated.callback.OnClickListener(this, 1);
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
        if (3 != i) {
            return false;
        }
        setViewModel((PremiumOnboardingFlowViewModel) obj);
        return true;
    }

    public void setViewModel(@Nullable PremiumOnboardingFlowViewModel premiumOnboardingFlowViewModel) {
        this.mViewModel = premiumOnboardingFlowViewModel;
        synchronized (this) {
            this.mDirtyFlags |= 1;
        }
        notifyPropertyChanged(3);
        super.requestRebind();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void executeBindings() {
        /*
            r12 = this;
            monitor-enter(r12)
            long r0 = r12.mDirtyFlags     // Catch:{ all -> 0x005c }
            r2 = 0
            r12.mDirtyFlags = r2     // Catch:{ all -> 0x005c }
            monitor-exit(r12)     // Catch:{ all -> 0x005c }
            com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingFlowViewModel r4 = r12.mViewModel
            r5 = 3
            long r5 = r5 & r0
            r7 = 0
            r8 = 0
            int r9 = (r5 > r2 ? 1 : (r5 == r2 ? 0 : -1))
            if (r9 == 0) goto L_0x002e
            if (r4 == 0) goto L_0x001a
            com.myfitnesspal.feature.onboarding.model.OnboardingScreen r4 = r4.getScreen()
            goto L_0x001b
        L_0x001a:
            r4 = r7
        L_0x001b:
            if (r4 == 0) goto L_0x002e
            java.lang.String r7 = r4.getAnimation()
            int r8 = r4.getTitle()
            int r5 = r4.getBtnFeatureTitle()
            int r4 = r4.getDescription()
            goto L_0x0030
        L_0x002e:
            r4 = 0
            r5 = 0
        L_0x0030:
            r10 = 2
            long r0 = r0 & r10
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 == 0) goto L_0x0045
            android.widget.TextView r0 = r12.btnOpenFeature
            android.view.View$OnClickListener r1 = r12.mCallback3
            r0.setOnClickListener(r1)
            android.widget.TextView r0 = r12.mboundView5
            android.view.View$OnClickListener r1 = r12.mCallback4
            r0.setOnClickListener(r1)
        L_0x0045:
            if (r9 == 0) goto L_0x005b
            android.widget.TextView r0 = r12.btnOpenFeature
            r0.setText(r5)
            com.airbnb.lottie.LottieAnimationView r0 = r12.screenAnimation
            com.myfitnesspal.feature.onboarding.ui.viewmodel.PremiumOnboardingFlowViewModelKt.setAnimation(r0, r7)
            android.widget.TextView r0 = r12.screenDescription
            r0.setText(r4)
            android.widget.TextView r0 = r12.screenTitle
            r0.setText(r8)
        L_0x005b:
            return
        L_0x005c:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x005c }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.android.databinding.OnboardingAnimatedFragmentBindingImpl.executeBindings():void");
    }

    public final void _internalCallbackOnClick(int i, View view) {
        boolean z = true;
        switch (i) {
            case 1:
                PremiumOnboardingFlowViewModel premiumOnboardingFlowViewModel = this.mViewModel;
                if (premiumOnboardingFlowViewModel == null) {
                    z = false;
                }
                if (z) {
                    premiumOnboardingFlowViewModel.goToPremiumFeature();
                    return;
                }
                return;
            case 2:
                PremiumOnboardingFlowViewModel premiumOnboardingFlowViewModel2 = this.mViewModel;
                if (premiumOnboardingFlowViewModel2 == null) {
                    z = false;
                }
                if (z) {
                    premiumOnboardingFlowViewModel2.goToAllFeatures();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
