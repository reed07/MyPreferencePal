package com.myfitnesspal.feature.search.ui.adapter.holder;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.images.service.ImageService;
import com.myfitnesspal.feature.images.service.ImageServiceUtil;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.ui.constants.FoodSearchTab;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.view.GlideHideProgressListener;
import com.myfitnesspal.shared.ui.view.ViewHolder;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.myfitnesspal.shared.util.NutritionUtils;
import com.uacf.core.util.Strings;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;

public class NormalItemViewHolder extends ViewHolder<DiaryEntryCellModel> {
    private static final RequestOptions IMAGE_REQUEST_OPTIONS = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).error(R.drawable.ic_meal_photos_placeholder).placeholder((int) R.drawable.ic_meal_photos_placeholder).centerCrop().dontAnimate();
    @BindView(2131363999)
    TextView caloriesTxt;
    private final Context context;
    @BindView(2131363809)
    TextView foodDescriptionTxt;
    @BindView(2131363810)
    TextView foodDetailsTxt;
    private final FoodSearchTab foodSearchTab;
    @BindView(2131362785)
    View imageContainer;
    private final GlideHideProgressListener imageLoadListener = new GlideHideProgressListener();
    private final Lazy<ImageService> imageService;
    private String imageUri;
    @BindView(2131362790)
    ImageView imageView;
    private final boolean isShowingPairedFoods;
    private final Lazy<MealUtil> mealHelperUtil;
    private final Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    @BindView(2131363082)
    CompoundButton multiSelectCheckBox;
    private final OnCheckedChangeListener onCheckedChangeListener;
    @BindView(2131363314)
    ProgressBar progress;
    private final boolean showImages;
    private final Lazy<UserEnergyService> userEnergyService;

    public NormalItemViewHolder(View view, Lazy<ImageService> lazy, Lazy<MultiAddFoodHelper> lazy2, Lazy<UserEnergyService> lazy3, Lazy<MealUtil> lazy4, OnCheckedChangeListener onCheckedChangeListener2, FoodSearchTab foodSearchTab2, boolean z, boolean z2) {
        super(view);
        ButterKnife.bind((Object) this, view);
        this.context = view.getContext().getApplicationContext();
        this.imageService = lazy;
        this.multiAddFoodHelper = lazy2;
        this.userEnergyService = lazy3;
        this.mealHelperUtil = lazy4;
        this.onCheckedChangeListener = onCheckedChangeListener2;
        this.foodSearchTab = foodSearchTab2;
        this.isShowingPairedFoods = z;
        this.showImages = z2;
    }

    public void setImage(MfpImage mfpImage) {
        if (mfpImage == null) {
            this.imageUri = null;
        } else {
            this.imageUri = ImageServiceUtil.getImageThumbnailUri(this.context, (ImageService) this.imageService.get(), mfpImage);
        }
    }

    public void setData(DiaryEntryCellModel diaryEntryCellModel, int i) {
        String str;
        boolean z = diaryEntryCellModel instanceof MealFood;
        if (!z || !this.showImages) {
            this.imageContainer.setVisibility(8);
        } else {
            loadImage((MealFood) diaryEntryCellModel);
        }
        boolean z2 = diaryEntryCellModel.isFoodEntry() && ((FoodEntry) diaryEntryCellModel).getFood().isVerified();
        TextView textView = this.foodDescriptionTxt;
        if (diaryEntryCellModel.isMealEntries()) {
            str = ((UserEnergyService) this.userEnergyService.get()).getMealEntriesTitle(diaryEntryCellModel);
        } else {
            str = diaryEntryCellModel.summaryLine1();
        }
        textView.setText(str);
        this.foodDescriptionTxt.setCompoundDrawablesWithIntrinsicBounds(null, null, z2 ? this.context.getResources().getDrawable(R.drawable.ic_verified_foods_small) : null, null);
        DiaryEntryCellModel itemWithSameId = ((MultiAddFoodHelper) this.multiAddFoodHelper.get()).getItemWithSameId(diaryEntryCellModel);
        boolean z3 = itemWithSameId != null;
        if (!z || this.foodSearchTab != FoodSearchTab.MEALS) {
            this.foodDetailsTxt.setText(((UserEnergyService) this.userEnergyService.get()).getDescription(z3 ? itemWithSameId : diaryEntryCellModel, false));
        } else {
            this.foodDetailsTxt.setText(NutritionUtils.getNutritionalMacrosDetails(this.context, ((MealUtil) this.mealHelperUtil.get()).getNutritionalContents((MealFood) (z3 ? itemWithSameId : diaryEntryCellModel))));
        }
        ViewUtils.setVisible(true, this.caloriesTxt);
        TextView textView2 = this.caloriesTxt;
        UserEnergyService userEnergyService2 = (UserEnergyService) this.userEnergyService.get();
        if (!z3) {
            itemWithSameId = diaryEntryCellModel;
        }
        textView2.setText(userEnergyService2.getDisplayableEnergy(itemWithSameId));
        this.multiSelectCheckBox.setTag(Tuple.create(diaryEntryCellModel, Integer.valueOf(i), this.parent));
        if (!this.isShowingPairedFoods) {
            ViewUtils.increaseHitRectBy(this.context.getResources().getDimensionPixelSize(R.dimen.food_search_checkbox_padding), this.multiSelectCheckBox);
        }
        this.multiSelectCheckBox.setOnCheckedChangeListener(null);
        this.multiSelectCheckBox.setChecked(z3);
        this.multiSelectCheckBox.setOnCheckedChangeListener(this.onCheckedChangeListener);
        ViewUtils.setVisible(((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn() || this.isShowingPairedFoods, this.multiSelectCheckBox);
    }

    private void loadImage(MealFood mealFood) {
        this.imageContainer.setVisibility(0);
        if (mealFood.isAutoGeneratedMeal()) {
            int color = this.context.getResources().getColor(R.color.meal_photo_placeholder_image_bg);
            this.imageView.setImageResource(R.drawable.ic_meal_repeat);
            this.imageView.setBackground(new ColorDrawable(color));
            this.imageLoadListener.setLoaded(this.progress, this.imageView);
            return;
        }
        this.imageView.setBackground(null);
        if (Strings.notEmpty(this.imageUri)) {
            this.imageLoadListener.setLoading(this.progress, this.imageView);
            Glide.with(this.context).load(this.imageUri).listener(this.imageLoadListener).apply(IMAGE_REQUEST_OPTIONS).into(this.imageView);
            return;
        }
        this.imageView.setImageResource(R.drawable.ic_meal_photos_placeholder);
        this.imageLoadListener.setLoaded(this.progress, this.imageView);
    }
}
