package com.myfitnesspal.feature.foodeditor.ui.mixin.impl;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras;
import com.myfitnesspal.feature.foodeditor.ui.mixin.EditorMixinBase.OnItemSavedListener;
import com.myfitnesspal.feature.foodeditor.viewmodel.SponsoredFoodEditorViewModel;
import com.myfitnesspal.feature.foodeditor.viewmodel.SponsoredFoodViewModelFactory;
import com.myfitnesspal.feature.registration.model.Resource;
import com.myfitnesspal.feature.registration.model.Resource.Error;
import com.myfitnesspal.feature.registration.model.Resource.Loading;
import com.myfitnesspal.feature.registration.model.Resource.Success;
import com.myfitnesspal.feature.search.model.SponsoredFoodBannerAd;
import com.myfitnesspal.feature.search.model.SponsoredFoodSearchAd;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.view.MfpImageView;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ViewUtils;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001BA\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0016H\u0002J\b\u0010\u001a\u001a\u00020\u0016H\u0002J\b\u0010\u001b\u001a\u00020\u0016H\u0016J\b\u0010\u001c\u001a\u00020\u0016H\u0016J\u0012\u0010\u001c\u001a\u00020\u00162\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\b\u0010\u001f\u001a\u00020\u0016H\u0002J\b\u0010 \u001a\u00020\u0016H\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/myfitnesspal/feature/foodeditor/ui/mixin/impl/SponsoredFoodMixin;", "Lcom/myfitnesspal/feature/foodeditor/ui/mixin/impl/FoodEditorMixin;", "activity", "Lcom/myfitnesspal/shared/ui/activity/MfpActivity;", "onItemSavedListener", "Lcom/myfitnesspal/feature/foodeditor/ui/mixin/EditorMixinBase$OnItemSavedListener;", "intent", "Landroid/content/Intent;", "savedState", "Landroid/os/Bundle;", "parentView", "Landroid/view/View;", "foodEditorExtras", "Lcom/myfitnesspal/feature/foodeditor/ui/activity/FoodEditorExtras;", "(Lcom/myfitnesspal/shared/ui/activity/MfpActivity;Lcom/myfitnesspal/feature/foodeditor/ui/mixin/EditorMixinBase$OnItemSavedListener;Landroid/content/Intent;Landroid/os/Bundle;Landroid/view/View;Lcom/myfitnesspal/feature/foodeditor/ui/activity/FoodEditorExtras;)V", "sponsoredFoodBanner", "Landroid/support/constraint/ConstraintLayout;", "sponsoredInfo", "Lcom/myfitnesspal/feature/search/model/SponsoredFoodSearchAd;", "viewModel", "Lcom/myfitnesspal/feature/foodeditor/viewmodel/SponsoredFoodEditorViewModel;", "bindAdToView", "", "ad", "Lcom/myfitnesspal/feature/search/model/SponsoredFoodBannerAd;", "fetchBannerAd", "hideProgressBar", "renderView", "saveItemToTarget", "food", "Lcom/myfitnesspal/shared/model/v2/MfpFood;", "showSponsoredFoodBanner", "showSponsoredFoodDetails", "app_googleRelease"}, k = 1, mv = {1, 1, 13})
/* compiled from: SponsoredFoodMixin.kt */
public final class SponsoredFoodMixin extends FoodEditorMixin {
    /* access modifiers changed from: private */
    public final ConstraintLayout sponsoredFoodBanner;
    /* access modifiers changed from: private */
    public final SponsoredFoodSearchAd sponsoredInfo;
    /* access modifiers changed from: private */
    public final SponsoredFoodEditorViewModel viewModel;

    public SponsoredFoodMixin(@Nullable MfpActivity mfpActivity, @Nullable OnItemSavedListener onItemSavedListener, @Nullable Intent intent, @Nullable Bundle bundle, @Nullable View view, @Nullable FoodEditorExtras foodEditorExtras) {
        super(mfpActivity, onItemSavedListener, intent, bundle, view, foodEditorExtras);
        View inflate = LayoutInflater.from(mfpActivity).inflate(R.layout.header_sponsored_food, this.headerContainer, false);
        if (inflate != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) inflate;
            ((MfpImageView) constraintLayout.findViewById(R.id.sponsoredFoodBannerImage)).setPlaceholderImageId(R.drawable.meal_photo_default_bg);
            this.sponsoredFoodBanner = constraintLayout;
            this.sponsoredInfo = (SponsoredFoodSearchAd) BundleUtils.getParcelable(intent != null ? intent.getExtras() : null, FoodEditorActivity.EXTRA_SPONSORED_FOOD_AD, SponsoredFoodMixin$sponsoredInfo$1.INSTANCE.getClass().getClassLoader());
            if (mfpActivity != null) {
                FragmentActivity fragmentActivity = mfpActivity;
                Activity activity = getActivity();
                Intrinsics.checkExpressionValueIsNotNull(activity, "getActivity()");
                Application application = activity.getApplication();
                Intrinsics.checkExpressionValueIsNotNull(application, "getActivity().application");
                ViewModel viewModel2 = ViewModelProviders.of(fragmentActivity, (Factory) new SponsoredFoodViewModelFactory(application, this.sponsoredInfo)).get(SponsoredFoodEditorViewModel.class);
                Intrinsics.checkExpressionValueIsNotNull(viewModel2, "ViewModelProviders.of(ac…torViewModel::class.java)");
                this.viewModel = (SponsoredFoodEditorViewModel) viewModel2;
                this.viewModel.getSponsoredBannerAd().observe(fragmentActivity, new Observer<Resource<? extends SponsoredFoodBannerAd>>(this) {
                    final /* synthetic */ SponsoredFoodMixin this$0;

                    {
                        this.this$0 = r1;
                    }

                    public final void onChanged(@Nullable Resource<SponsoredFoodBannerAd> resource) {
                        ProgressBar progressBar = (ProgressBar) this.this$0.sponsoredFoodBanner.findViewById(R.id.sponsoredFoodBannerProgressBar);
                        Intrinsics.checkExpressionValueIsNotNull(progressBar, "sponsoredFoodBanner.sponsoredFoodBannerProgressBar");
                        progressBar.setVisibility(resource instanceof Loading ? 0 : 8);
                        if (resource instanceof Error) {
                            this.this$0.hideProgressBar();
                        } else if (resource instanceof Success) {
                            this.this$0.bindAdToView((SponsoredFoodBannerAd) ((Success) resource).getData());
                        }
                    }
                });
                setNutritionDetailsMode(NutritionDetailsMode.Open);
                ((MfpImageView) this.sponsoredFoodBanner.findViewById(R.id.sponsoredFoodBannerImage)).setRequestListener(new RequestListener<Drawable>(this) {
                    final /* synthetic */ SponsoredFoodMixin this$0;

                    {
                        this.this$0 = r1;
                    }

                    public boolean onLoadFailed(@Nullable GlideException glideException, @Nullable Object obj, @Nullable Target<Drawable> target, boolean z) {
                        this.this$0.hideProgressBar();
                        return false;
                    }

                    public boolean onResourceReady(@Nullable Drawable drawable, @Nullable Object obj, @Nullable Target<Drawable> target, @Nullable DataSource dataSource, boolean z) {
                        this.this$0.hideProgressBar();
                        return false;
                    }
                });
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.support.v4.app.FragmentActivity");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.support.constraint.ConstraintLayout");
    }

    public void renderView() {
        super.renderView();
        View view = this.foodDescRowView;
        Intrinsics.checkExpressionValueIsNotNull(view, "foodDescRowView");
        view.setVisibility(8);
        ViewGroup viewGroup = this.sponsoredFoodRow;
        Intrinsics.checkExpressionValueIsNotNull(viewGroup, "sponsoredFoodRow");
        viewGroup.setVisibility(0);
        showSponsoredFoodDetails();
    }

    public void saveItemToTarget() {
        saveItemToTarget(getFood());
    }

    public void saveItemToTarget(@Nullable MfpFood mfpFood) {
        FoodEditorAnalyticsExtras foodEditorAnalyticsExtras;
        saveFoodToTarget(mfpFood, this.selectedServingSizeIndex, this.selectedServingAmount, getPairedFoodEntries());
        if (this.foodEditorExtras != null) {
            FoodEditorExtras foodEditorExtras = this.foodEditorExtras;
            Intrinsics.checkExpressionValueIsNotNull(foodEditorExtras, "foodEditorExtras");
            foodEditorAnalyticsExtras = foodEditorExtras.getFoodEditorAnalyticsExtras();
        } else {
            foodEditorAnalyticsExtras = null;
        }
        MfpFood mfpFood2 = mfpFood;
        getAnalytics().reportSponsoredFoodLogged(mfpFood2, getMealName(), getBarcode(), getSource(), getDate(), this.selectedServingSizeIndex, foodEditorAnalyticsExtras, this.searchVersion);
        Bundle bundle = new Bundle();
        bundle.putParcelable("food", mfpFood);
        onItemSaved(mfpFood, -1, bundle);
    }

    private final void showSponsoredFoodDetails() {
        if (this.sponsoredInfo == null) {
            ViewUtils.setGone(this.sponsoredFoodRow);
            return;
        }
        ViewGroup viewGroup = this.sponsoredFoodRow;
        if (viewGroup != null) {
            View view = viewGroup;
            ((MfpImageView) view.findViewById(R.id.sponsoredItemImageView)).setUrl(this.sponsoredInfo.getProductThumbnailImagePath());
            TextView textView = (TextView) view.findViewById(R.id.sponsoredItemTitleView);
            Intrinsics.checkExpressionValueIsNotNull(textView, "it.sponsoredItemTitleView");
            textView.setText(this.sponsoredInfo.getProductName());
            TextView textView2 = (TextView) view.findViewById(R.id.sponsoredItemDescriptionView);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "it.sponsoredItemDescriptionView");
            textView2.setText(this.sponsoredInfo.getProductTagline());
            TextView textView3 = (TextView) view.findViewById(R.id.sponsoredItemPortionView);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "it.sponsoredItemPortionView");
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            StringBuilder sb = new StringBuilder();
            sb.append(this.sponsoredInfo.getSponsoredCallout());
            sb.append(" • ");
            sb.append(this.sponsoredInfo.getFoodDescription());
            sb.append(", ");
            sb.append(this.sponsoredInfo.getMeasurement());
            Object[] objArr = new Object[0];
            String format = String.format(sb.toString(), Arrays.copyOf(objArr, objArr.length));
            Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
            textView3.setText(format);
        }
        showSponsoredFoodBanner();
    }

    private final void showSponsoredFoodBanner() {
        if (this.sponsoredInfo != null) {
            this.headerContainer.addView(this.sponsoredFoodBanner);
            fetchBannerAd();
        }
    }

    private final void fetchBannerAd() {
        ProgressBar progressBar = (ProgressBar) this.sponsoredFoodBanner.findViewById(R.id.sponsoredFoodBannerProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "sponsoredFoodBanner.sponsoredFoodBannerProgressBar");
        progressBar.setVisibility(0);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        if (r1 != null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0023, code lost:
        if (r0 != null) goto L_0x0028;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void bindAdToView(com.myfitnesspal.feature.search.model.SponsoredFoodBannerAd r5) {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0006
            r4.hideProgressBar()
            return
        L_0x0006:
            android.support.constraint.ConstraintLayout r0 = r4.sponsoredFoodBanner
            android.view.View r0 = (android.view.View) r0
            int r1 = com.myfitnesspal.android.R.id.sponsoredFoodBannerImage
            android.view.View r0 = r0.findViewById(r1)
            com.myfitnesspal.shared.ui.view.MfpImageView r0 = (com.myfitnesspal.shared.ui.view.MfpImageView) r0
            java.lang.String r1 = r5.getImageUrl()
            r0.setUrl(r1)
            com.myfitnesspal.shared.model.v2.MfpFood r0 = r4.getFood()
            if (r0 == 0) goto L_0x0026
            java.lang.String r0 = r0.getId()
            if (r0 == 0) goto L_0x0026
            goto L_0x0028
        L_0x0026:
            java.lang.String r0 = ""
        L_0x0028:
            com.myfitnesspal.shared.model.v2.MfpFood r1 = r4.getFood()
            if (r1 == 0) goto L_0x0035
            java.lang.String r1 = r1.getVersion()
            if (r1 == 0) goto L_0x0035
            goto L_0x0037
        L_0x0035:
            java.lang.String r1 = ""
        L_0x0037:
            java.lang.String r2 = r5.getClickUrl()
            if (r2 == 0) goto L_0x0049
            android.support.constraint.ConstraintLayout r2 = r4.sponsoredFoodBanner
            com.myfitnesspal.feature.foodeditor.ui.mixin.impl.SponsoredFoodMixin$bindAdToView$1 r3 = new com.myfitnesspal.feature.foodeditor.ui.mixin.impl.SponsoredFoodMixin$bindAdToView$1
            r3.<init>(r4, r0, r1, r5)
            android.view.View$OnClickListener r3 = (android.view.View.OnClickListener) r3
            r2.setOnClickListener(r3)
        L_0x0049:
            com.myfitnesspal.feature.foodeditor.ui.service.FoodEditorAnalytics r5 = r4.getAnalytics()
            com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras r2 = r4.foodEditorExtras
            java.lang.String r3 = "foodEditorExtras"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
            java.lang.String r2 = r2.getFlowId()
            com.myfitnesspal.feature.search.model.SearchSource r3 = com.myfitnesspal.feature.search.model.SearchSource.ONLINE
            java.lang.String r3 = r3.getTitle()
            r5.reportSponsoredFoodBannerDisplayed(r2, r0, r1, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.feature.foodeditor.ui.mixin.impl.SponsoredFoodMixin.bindAdToView(com.myfitnesspal.feature.search.model.SponsoredFoodBannerAd):void");
    }

    /* access modifiers changed from: private */
    public final void hideProgressBar() {
        ProgressBar progressBar = (ProgressBar) this.sponsoredFoodBanner.findViewById(R.id.sponsoredFoodBannerProgressBar);
        Intrinsics.checkExpressionValueIsNotNull(progressBar, "sponsoredFoodBanner.sponsoredFoodBannerProgressBar");
        progressBar.setVisibility(8);
    }
}
