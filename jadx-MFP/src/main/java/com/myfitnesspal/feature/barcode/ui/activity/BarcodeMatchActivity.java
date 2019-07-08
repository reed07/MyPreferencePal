package com.myfitnesspal.feature.barcode.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.activity.AddFood;
import com.myfitnesspal.feature.barcode.constants.BarcodeMatchDisplayMode;
import com.myfitnesspal.feature.barcode.ui.adapter.BarcodeMatchListAdapter;
import com.myfitnesspal.feature.barcode.ui.viewmodel.BarcodeMatchViewModel;
import com.myfitnesspal.feature.barcode.ui.viewmodel.BarcodeMatchViewModel.Property;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorType;
import com.myfitnesspal.feature.meals.model.MealIngredientEditorBundleData;
import com.myfitnesspal.feature.meals.util.MealUtil;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.view.ClearableEditText;
import com.myfitnesspal.shared.ui.view.ClearableEditText.OnClearTextListener;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;

public class BarcodeMatchActivity extends MfpActivity {
    private static final String EXTRA_DISPLAY_MODE = "extra_display_mode";
    public static final String EXTRA_EXISTING_MATCHES = "extra_existing_matches";
    private static final String EXTRA_FOOD_EDITOR_TYPE = "extra_food_editor_type";
    private static final String EXTRA_START_MODE = "extra_start_mode";
    private static final int LOAD_NEXT_PAGE_THRESHOLD = 8;
    private static final int SHOW_KEYBOARD_DELAY_MILLIS = 250;
    /* access modifiers changed from: private */
    public BarcodeMatchListAdapter adapter;
    private String barcode;
    private Date date;
    /* access modifiers changed from: private */
    public BarcodeMatchDisplayMode displayMode;
    private FoodEditorType foodEditorType;
    private TextView footerButton;
    private Handler handler = new Handler();
    @BindView(2131362921)
    ListView listView;
    @Inject
    Lazy<MealUtil> mealHelperUtil;
    private String mealName;
    @BindView(2131363149)
    TextView noMatches;
    private String referrer;
    @BindView(2131363559)
    ClearableEditText searchEdit;
    @Inject
    Lazy<SearchService> searchService;
    private StartMode startMode;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    /* access modifiers changed from: private */
    public BarcodeMatchViewModel viewModel;

    public enum StartMode {
        NoMatches,
        BetterMatch
    }

    public static Intent createStartIntent(Context context, Intent intent, ArrayList<MfpFood> arrayList, String str, String str2, String str3, StartMode startMode2, Date date2, FoodEditorType foodEditorType2) {
        Intent intent2 = new Intent(context, BarcodeMatchActivity.class);
        intent2.putExtra(EXTRA_EXISTING_MATCHES, arrayList);
        intent2.putExtra(Extras.MEAL_NAME, str);
        intent2.putExtra("barcode", str2);
        intent2.putExtra("referrer", str3);
        intent2.putExtra(EXTRA_FOOD_EDITOR_TYPE, foodEditorType2);
        intent2.putExtra(EXTRA_START_MODE, startMode2);
        intent2.putExtra(FoodEditorActivity.EXTRA_ACTIVITY_TO_START, intent);
        intent2.putExtra("date", date2);
        return intent2;
    }

    public static Intent createStartIntent(Context context, Intent intent, ArrayList<MfpFood> arrayList, String str, String str2, String str3, StartMode startMode2) {
        return createStartIntent(context, intent, arrayList, str, str2, str3, startMode2, null, null);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setResult(0);
        Intent intent = getIntent();
        this.mealName = ExtrasUtils.getString(intent, Extras.MEAL_NAME, (String) null);
        this.barcode = ExtrasUtils.getString(intent, "barcode", (String) null);
        this.referrer = ExtrasUtils.getString(intent, "referrer", (String) null);
        this.date = (Date) BundleUtils.getSerializable("date", null, Date.class.getClassLoader(), intent.getExtras());
        this.foodEditorType = (FoodEditorType) BundleUtils.getSerializable(intent.getExtras(), EXTRA_FOOD_EDITOR_TYPE, null, FoodEditorType.class.getClassLoader());
        this.startMode = (StartMode) BundleUtils.getSerializable(intent.getExtras(), EXTRA_START_MODE, StartMode.NoMatches, StartMode.class.getClassLoader());
        setContentView((int) R.layout.barcode_match_activity);
        initListViewFooter();
        initViewModelAndDisplayMode(getIntent().getExtras(), bundle);
        initListeners();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        getImmHelper().hideSoftInput();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putSerializable(EXTRA_DISPLAY_MODE, this.displayMode);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 != -1) {
            return;
        }
        if (i == 53 || i == 183) {
            MfpFood mfpFood = (MfpFood) BundleUtils.getParcelable(intent.getExtras(), "food", MfpFood.class.getClassLoader());
            FoodEntry foodEntry = (FoodEntry) BundleUtils.getParcelable(intent.getExtras(), "food_entry", FoodEntry.class.getClassLoader());
            if (this.foodEditorType == FoodEditorType.MealIngredient && foodEntry != null) {
                setFoodResultAndFinish(mfpFood, foodEntry);
            } else if (this.foodEditorType != null) {
                if (mfpFood == null) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("BarcodeMatchActivity food is null; request code: ");
                    sb.append(Strings.toString(Integer.valueOf(i)));
                    sb.append(", referrer: ");
                    sb.append(Strings.toString(this.referrer));
                    sb.append(", start mode: ");
                    sb.append(Strings.toString(this.startMode));
                    sb.append(", barcode: ");
                    sb.append(Strings.toString(this.barcode));
                    Ln.e(sb.toString(), new Object[0]);
                    finish();
                }
                navigateToFoodEditor(mfpFood);
            } else {
                setFoodResultAndFinish(mfpFood, ((MealUtil) this.mealHelperUtil.get()).foodEntryFromMfpFood(mfpFood));
            }
        } else if (i == 194) {
            MfpFood mfpFood2 = (MfpFood) BundleUtils.getParcelable(intent.getExtras(), "food", MfpFood.class.getClassLoader());
            setFoodResultAndFinish(mfpFood2, ((MealUtil) this.mealHelperUtil.get()).foodEntryFromMfpFood(mfpFood2));
        } else if (i == 200) {
            setResult(-1, intent);
            finish();
        }
    }

    public boolean backPressed() {
        if (this.displayMode != BarcodeMatchDisplayMode.Search || this.viewModel.getMatches().size() <= 0) {
            return false;
        }
        setDisplayMode(BarcodeMatchDisplayMode.MultiMatch);
        return true;
    }

    public void onUpPressed() {
        if (!backPressed()) {
            super.onUpPressed();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && this.viewModel.getSearchResults().size() == 0 && this.displayMode == BarcodeMatchDisplayMode.Search) {
            focusSearchAndShowKeyboard();
        }
    }

    private void setDisplayMode(BarcodeMatchDisplayMode barcodeMatchDisplayMode) {
        this.displayMode = barcodeMatchDisplayMode;
        BarcodeMatchDisplayMode barcodeMatchDisplayMode2 = BarcodeMatchDisplayMode.MultiMatch;
        int i = R.string.barcode_button_find_better_match;
        if (barcodeMatchDisplayMode == barcodeMatchDisplayMode2) {
            setTitle(R.string.barcode_scanner_find_a_match_title);
            this.footerButton.setText(R.string.barcode_button_find_better_match);
            ViewUtils.setGone(this.searchEdit);
            getImmHelper().hideSoftInput();
        } else {
            if (this.startMode != StartMode.BetterMatch) {
                i = R.string.barcode_scanner_no_match_found_title;
            }
            setTitle(i);
            this.footerButton.setText(R.string.new_food);
            ViewUtils.setVisible(this.searchEdit);
            focusSearchAndShowKeyboard();
        }
        this.adapter.setDisplayMode(barcodeMatchDisplayMode);
    }

    private void initViewModelAndDisplayMode(Bundle bundle, Bundle bundle2) {
        this.viewModel = (BarcodeMatchViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (BarcodeMatchViewModel) setViewModel(new BarcodeMatchViewModel(getRunner(), this.searchService, BundleUtils.getParcelableArrayList(bundle, EXTRA_EXISTING_MATCHES, MfpFood.class.getClassLoader())));
        }
        this.adapter = new BarcodeMatchListAdapter(this, this.userEnergyService, this.viewModel, this.displayMode);
        this.displayMode = this.viewModel.getMatches().size() > 0 ? BarcodeMatchDisplayMode.MultiMatch : BarcodeMatchDisplayMode.Search;
        this.displayMode = (BarcodeMatchDisplayMode) BundleUtils.getSerializable(bundle2, EXTRA_DISPLAY_MODE, this.displayMode, BarcodeMatchDisplayMode.class.getClassLoader());
        setDisplayMode(this.displayMode);
        this.listView.setAdapter(this.adapter);
        this.viewModel.onRebindView();
    }

    private void focusSearchAndShowKeyboard() {
        if (this.viewModel.getSearchResults().size() == 0) {
            this.handler.postDelayed(new Runnable() {
                public void run() {
                    BarcodeMatchActivity.this.searchEdit.requestFocus();
                    BarcodeMatchActivity.this.getImmHelper().showSoftInput(BarcodeMatchActivity.this.searchEdit);
                }
            }, 250);
        }
    }

    private void initListViewFooter() {
        if (this.listView.getFooterViewsCount() == 0) {
            View inflate = getLayoutInflater().inflate(R.layout.barcode_match_footer_view, this.listView, false);
            this.footerButton = (TextView) inflate.findViewById(R.id.footerButton);
            this.listView.addFooterView(inflate, null, false);
        }
    }

    private void initListeners() {
        this.searchEdit.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 3) {
                    return false;
                }
                BarcodeMatchActivity.this.search();
                return true;
            }
        });
        this.searchEdit.setOnClearTextListener(new OnClearTextListener() {
            public final void onTextCleared() {
                BarcodeMatchActivity.lambda$initListeners$0(BarcodeMatchActivity.this);
            }
        });
        this.searchEdit.setOnKeyListener(new OnKeyListener() {
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return BarcodeMatchActivity.lambda$initListeners$1(BarcodeMatchActivity.this, view, i, keyEvent);
            }
        });
        this.footerButton.setOnClickListener(new OnClickListener() {
            public final void onClick(View view) {
                BarcodeMatchActivity.lambda$initListeners$2(BarcodeMatchActivity.this, view);
            }
        });
        this.listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                BarcodeMatchActivity.this.selectItemAndFinish(i);
            }
        });
        this.listView.setOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(AbsListView absListView, int i) {
            }

            public void onScroll(AbsListView absListView, int i, int i2, int i3) {
                if (BarcodeMatchActivity.this.displayMode == BarcodeMatchDisplayMode.Search && i + i2 > BarcodeMatchActivity.this.adapter.getCount() - 8) {
                    BarcodeMatchActivity.this.viewModel.loadNextPage();
                }
            }
        });
    }

    public static /* synthetic */ void lambda$initListeners$0(BarcodeMatchActivity barcodeMatchActivity) {
        if (barcodeMatchActivity.displayMode == BarcodeMatchDisplayMode.Search) {
            barcodeMatchActivity.viewModel.clearSearchResults();
        }
    }

    public static /* synthetic */ boolean lambda$initListeners$1(BarcodeMatchActivity barcodeMatchActivity, View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 66) {
            return false;
        }
        barcodeMatchActivity.search();
        return true;
    }

    public static /* synthetic */ void lambda$initListeners$2(BarcodeMatchActivity barcodeMatchActivity, View view) {
        if (barcodeMatchActivity.displayMode == BarcodeMatchDisplayMode.MultiMatch) {
            barcodeMatchActivity.setDisplayMode(BarcodeMatchDisplayMode.Search);
        } else {
            barcodeMatchActivity.getNavigationHelper().withExtra("referrer", barcodeMatchActivity.referrer).withExtra("barcode", barcodeMatchActivity.barcode).withExtra(Extras.REQUIRE_MACROS, true).withExtra(Extras.MEAL_NAME, barcodeMatchActivity.mealName).withExtra(AddFood.EXTRA_CREATE_DIARY_ENTRY, false).withExtra(Extras.GO_TO_DIARY, false).withIntent(AddFood.newStartIntent(barcodeMatchActivity, true)).startActivity(53);
        }
    }

    /* access modifiers changed from: private */
    public void selectItemAndFinish(int i) {
        MfpFood mfpFood = (MfpFood) this.adapter.getItem(i);
        if (this.foodEditorType != null) {
            navigateToFoodEditor(mfpFood);
        } else {
            setFoodResultAndFinish(mfpFood, null);
        }
    }

    private void navigateToFoodEditor(MfpFood mfpFood) {
        Intent intent = (Intent) BundleUtils.getParcelable(getIntent().getExtras(), FoodEditorActivity.EXTRA_ACTIVITY_TO_START, Intent.class.getClassLoader());
        Date date2 = this.date;
        if (date2 == null) {
            date2 = new Date();
        }
        Date date3 = date2;
        if (this.foodEditorType == FoodEditorType.DiaryFood) {
            startActivityForResult(FoodEditorActivity.newDiaryFoodItemEditorIntent(this, intent, mfpFood, date3, this.mealName, this.barcode, SearchSource.BARCODE_MISS, this.referrer), RequestCodes.FOOD_EDITOR);
        } else if (this.foodEditorType == FoodEditorType.MealIngredient) {
            MealIngredientEditorBundleData mealIngredientEditorBundleData = new MealIngredientEditorBundleData(mfpFood, date3, this.mealName, this.barcode, this.referrer);
            startActivityForResult(FoodEditorActivity.newMealIngredientEditorIntent(this, intent, mealIngredientEditorBundleData), RequestCodes.FOOD_EDITOR);
        } else if (this.foodEditorType == FoodEditorType.BarcodeMultiAddFood) {
            startActivityForResult(FoodEditorActivity.newBarcodeMultiAddFoodItemEditorIntent(this, intent, mfpFood, date3, this.mealName, this.barcode, this.referrer), RequestCodes.BARCODE_FOOD_MULTI_ADD_EDITOR);
        } else if (this.foodEditorType == FoodEditorType.RecipeIngredient) {
            startActivityForResult(FoodEditorActivity.newSelectRecipeIngredientIntent(this, mfpFood), 200);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("foodEditorType ");
            sb.append(this.foodEditorType);
            sb.append(" is invalid");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private void setFoodResultAndFinish(MfpFood mfpFood, FoodEntry foodEntry) {
        Intent intent = new Intent();
        intent.putExtra("food", mfpFood);
        intent.putExtra("food_entry", foodEntry);
        intent.putExtra("barcode", this.barcode);
        intent.putExtra("referrer", this.referrer);
        setResult(-1, intent);
        finish();
    }

    /* access modifiers changed from: private */
    public void search() {
        String trim = this.searchEdit.getText().toString().trim();
        if (Strings.notEmpty(trim)) {
            getImmHelper().hideSoftInput();
            this.viewModel.load(trim);
        }
    }

    private void showNoMatchesIfEmpty() {
        boolean z = this.adapter.getCount() == 0 && !this.viewModel.isLoading();
        this.noMatches.setText(R.string.noMatchFound);
        ViewUtils.setVisible(z, this.noMatches);
    }

    private void resetToDefaultState() {
        this.noMatches.setText(R.string.barcode_scanner_help_us_improve);
        ViewUtils.setVisible(true, this.noMatches);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.LOAD_STATE) {
            setBusy(this.viewModel.isLoading());
        } else if (i == Property.SEARCH_RESULTS) {
            this.adapter.notifyDataSetChanged();
            showNoMatchesIfEmpty();
        } else if (i == Property.SEARCH_RESULTS_RESET) {
            this.adapter.notifyDataSetChanged();
            resetToDefaultState();
        }
    }
}
