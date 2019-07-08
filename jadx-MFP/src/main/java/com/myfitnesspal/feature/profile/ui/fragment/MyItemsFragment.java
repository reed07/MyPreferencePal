package com.myfitnesspal.feature.profile.ui.fragment;

import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.activity.AddFood;
import com.myfitnesspal.feature.diary.service.MealAnalyticsHelper;
import com.myfitnesspal.feature.exercise.ui.activity.NewCardio;
import com.myfitnesspal.feature.exercise.ui.activity.NewStrength;
import com.myfitnesspal.feature.exercise.ui.fragment.ExerciseList;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.feature.profile.analytics.MeAnalytics;
import com.myfitnesspal.feature.profile.ui.activity.MeActivity;
import com.myfitnesspal.feature.profile.ui.viewmodel.MyItemsViewModel;
import com.myfitnesspal.feature.profile.util.ProfileViewUtil;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods;
import com.myfitnesspal.feature.recipes.ui.activity.RecipesAndFoods.TabType;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.Property;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import dagger.Lazy;
import javax.inject.Inject;

public class MyItemsFragment extends MfpFragment {
    /* access modifiers changed from: private */
    public MeAnalytics analytics = new MeAnalytics();
    @BindView(2131362090)
    ViewGroup cardioRow;
    @BindView(2131362643)
    ViewGroup foodsRow;
    @BindView(2131363046)
    View mealsRecipesFoodsCard;
    @BindView(2131363045)
    ViewGroup mealsRow;
    @Inject
    Lazy<PremiumService> premium;
    @BindView(2131363422)
    ViewGroup recipesRow;
    @BindView(2131362987)
    View root;
    @BindView(2131363739)
    ViewGroup strengthRow;
    private MyItemsViewModel viewModel;

    public static MyItemsFragment newInstance() {
        return new MyItemsFragment();
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        initViewModel();
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        return layoutInflater.inflate(R.layout.my_items_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.viewModel.getState() != State.Loaded) {
            this.root.setVisibility(8);
        }
        bindListeners();
        bindUi();
    }

    public void onResume() {
        super.onResume();
        updateTopPadding();
        this.viewModel.load(new Void[0]);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.LOAD_STATE && this.viewModel.isLoaded()) {
            bindUi();
            this.root.setVisibility(0);
        }
    }

    private void initViewModel() {
        this.viewModel = (MyItemsViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (MyItemsViewModel) setViewModel(new MyItemsViewModel(getContext(), getRunner()));
        }
    }

    /* access modifiers changed from: private */
    public void navigateTo(Intent intent) {
        getNavigationHelper().withIntent(intent).startActivity();
    }

    private void bindListeners() {
        this.mealsRow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowTapped("meals");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(RecipesAndFoods.newStartIntent(myItemsFragment.getActivity(), TabType.Meals));
            }
        });
        this.mealsRow.findViewById(R.id.create).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowCreateTapped("meals");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(FoodEditorActivity.newMealItemEditorIntent(myItemsFragment.getActivity(), MeActivity.newStartIntent(MyItemsFragment.this.getContext(), 1), null, null, MealAnalyticsHelper.VALUE_MY_ITEMS));
            }
        });
        this.recipesRow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowTapped("recipes");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(RecipesAndFoods.newStartIntent(myItemsFragment.getActivity(), TabType.Recipes));
            }
        });
        this.recipesRow.findViewById(R.id.create).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowCreateTapped("recipes");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(RecipesAndFoods.newStartIntentForRecipeCreation(myItemsFragment.getActivity()));
            }
        });
        this.foodsRow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowTapped("foods");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(RecipesAndFoods.newStartIntent(myItemsFragment.getActivity(), TabType.Foods));
            }
        });
        this.foodsRow.findViewById(R.id.create).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowCreateTapped("foods");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(AddFood.newStartIntent(myItemsFragment.getActivity()).putExtra(AddFood.EXTRA_CREATE_DIARY_ENTRY, false));
            }
        });
        this.cardioRow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowTapped("cardio");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(ExerciseList.newStartIntent(myItemsFragment.getActivity(), 0));
            }
        });
        this.cardioRow.findViewById(R.id.create).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowCreateTapped("cardio");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(NewCardio.newStartIntent(myItemsFragment.getActivity(), false));
            }
        });
        this.strengthRow.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowTapped("strength");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(ExerciseList.newStartIntent(myItemsFragment.getActivity(), 1));
            }
        });
        this.strengthRow.findViewById(R.id.create).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                MyItemsFragment.this.analytics.reportItemRowCreateTapped("strength");
                MyItemsFragment myItemsFragment = MyItemsFragment.this;
                myItemsFragment.navigateTo(NewStrength.newStartIntent(myItemsFragment.getActivity(), false));
            }
        });
    }

    private void bindUi() {
        bindRow(this.mealsRow, this.viewModel.getMealCount(), R.drawable.ic_me_meals);
        bindRow(this.recipesRow, this.viewModel.getRecipesCount(), R.drawable.ic_me_recipes);
        bindRowNoDivider(this.foodsRow, this.viewModel.getFoodCount(), R.drawable.ic_me_foods);
        bindRow(this.cardioRow, this.viewModel.getCardioCount(), R.drawable.ic_me_cardio);
        bindRowNoDivider(this.strengthRow, this.viewModel.getStrengthCount(), R.drawable.ic_me_strength);
        updateTopPadding();
    }

    private void updateTopPadding() {
        ProfileViewUtil.addTopPaddingIfAdVisible((PremiumService) this.premium.get(), this.mealsRecipesFoodsCard, R.dimen.news_feed_card_marginTop);
    }

    private void bindRowNoDivider(ViewGroup viewGroup, Spanned spanned, int i) {
        bindRow(viewGroup, spanned, i);
        viewGroup.findViewById(R.id.divider).setVisibility(8);
    }

    private void bindRow(ViewGroup viewGroup, Spanned spanned, int i) {
        ImageView imageView = (ImageView) viewGroup.findViewById(R.id.icon);
        TextView textView = (TextView) viewGroup.findViewById(R.id.text);
        viewGroup.findViewById(R.id.divider).setVisibility(0);
        imageView.setImageResource(i);
        textView.setText(spanned);
    }
}
