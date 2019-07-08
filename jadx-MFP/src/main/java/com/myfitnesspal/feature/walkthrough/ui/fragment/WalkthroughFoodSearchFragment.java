package com.myfitnesspal.feature.walkthrough.ui.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.event.V2SearchReturnedNoResultsEvent;
import com.myfitnesspal.feature.search.event.V2SearchReturnedResultsEvent;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment;
import com.myfitnesspal.feature.walkthrough.event.SkipLoggingWalkthroughEvent;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtil;
import com.myfitnesspal.feature.walkthrough.util.WalkthroughUtilImpl.WalkthroughType;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.ClearableEditText;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function2;
import com.uacf.core.util.ReturningFunction0;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.Collection;
import java.util.List;
import java.util.Stack;
import javax.inject.Inject;

public class WalkthroughFoodSearchFragment extends MfpFragment {
    @Nullable
    @BindView(2131362339)
    View divider;
    @BindView(2131364057)
    ClearableEditText editTxtFoodSearch;
    TextWatcher editTxtFoodSearchTextWatcher = new TextWatcher() {
        private String previousTerm = "";

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            WalkthroughFoodSearchFragment.this.setQueryTerm(Strings.trimmed((Object) editable));
            if (Strings.trimmed((Object) editable).length() == 1 && this.previousTerm.length() == 0) {
                WalkthroughFoodSearchFragment.this.walkthroughUtil.showDescription(true);
            } else if (Strings.trimmed((Object) editable).length() == 0 && this.previousTerm.length() == 1) {
                WalkthroughFoodSearchFragment.this.walkthroughUtil.showDescription(false);
            }
            this.previousTerm = Strings.trimmed((Object) editable);
        }
    };
    private Stack<Fragment> fragmentStack = new Stack<>();
    private boolean isSearchResultsWalkthroughShowing;
    private boolean isWalkThrough;
    @Inject
    protected LocalizedStringsUtil localizedStringsUtil;
    private String mealName;
    private String queryTerm;
    @BindView(2131362029)
    ImageButton scanButton;
    @BindView(2131363574)
    View searchResultsWalkThroughContainer;
    @Inject
    protected Lazy<SearchService> searchService;
    private Function2<View, WalkthroughType> skipClickedCallback = new Function2<View, WalkthroughType>() {
        public void execute(View view, WalkthroughType walkthroughType) throws RuntimeException {
            WalkthroughFoodSearchFragment walkthroughFoodSearchFragment = WalkthroughFoodSearchFragment.this;
            walkthroughFoodSearchFragment.postEvent(new SkipLoggingWalkthroughEvent((String) walkthroughFoodSearchFragment.skipEventNameFunc.execute()));
        }
    };
    ReturningFunction0<String> skipEventNameFunc = new ReturningFunction0<String>() {
        public String execute() {
            return Events.SPOTLIGHT_SEARCH_FIELD_SKIP;
        }
    };
    @Inject
    protected Lazy<UserEnergyService> userEnergyService;
    private View walkThrough;
    private boolean walkThroughInitialized;
    @Inject
    protected WalkthroughUtil walkthroughUtil;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.new_walkthrough_food_search, viewGroup, false);
    }

    public static Fragment newInstance(String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putString(Extras.MEAL_NAME, str);
        bundle.putBoolean(Extras.IS_WALK_THROUGH, z);
        WalkthroughFoodSearchFragment walkthroughFoodSearchFragment = new WalkthroughFoodSearchFragment();
        walkthroughFoodSearchFragment.setArguments(bundle);
        return walkthroughFoodSearchFragment;
    }

    @SuppressLint({"RestrictedApi"})
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (getArguments() != null) {
            this.mealName = BundleUtils.getString(getArguments(), Extras.MEAL_NAME);
            this.isWalkThrough = BundleUtils.getBoolean(getArguments(), Extras.IS_WALK_THROUGH, false);
            getActivity().setTitle(this.localizedStringsUtil.getMealNameString(this.mealName, (UserEnergyService) this.userEnergyService.get()));
        }
        init();
        setListeners();
        ViewUtils.setVisible(false, this.scanButton);
        ViewUtils.setVisible(false, this.divider);
    }

    public void onStop() {
        super.onStop();
        getImmHelper().hideSoftInput();
    }

    private void init() {
        if (!this.walkThroughInitialized) {
            initializeWalkThrough();
            getAnalyticsService().reportEvent(Events.SPOTLIGHT_SEARCH_FIELD_SEE);
        }
        if (Strings.notEmpty(this.queryTerm)) {
            showSearchResultsWalkThrough();
        }
    }

    private void setListeners() {
        this.editTxtFoodSearch.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (WalkthroughFoodSearchFragment.this.hasResumed()) {
                    WalkthroughFoodSearchFragment.this.determineProperSearch();
                }
                return true;
            }
        });
        this.editTxtFoodSearch.setTextWatcherListener(this.editTxtFoodSearchTextWatcher);
    }

    /* access modifiers changed from: private */
    public void determineProperSearch() {
        getImmHelper().hideSoftInput((View) this.editTxtFoodSearch);
        performV2Search(this.queryTerm);
    }

    private void performV2Search(String str) {
        List fragments = getChildFragmentManager().getFragments();
        if (!CollectionUtils.notEmpty((Collection<?>) fragments) || !(fragments.get(0) instanceof OnlineFoodSearchFragment)) {
            FragmentTransaction beginTransaction = getChildFragmentManager().beginTransaction();
            beginTransaction.add((int) R.id.search_fragment_container, (Fragment) this.fragmentStack.push(OnlineFoodSearchFragment.newInstance(new OnlineFoodSearchFragment.Extras().setQuery(str).setMealName(this.mealName).setInWalkthrough(true))));
            beginTransaction.addToBackStack(null);
            beginTransaction.commit();
        } else {
            ((OnlineFoodSearchFragment) fragments.get(0)).performSearch(str);
        }
        ViewUtils.setVisible(false, this.walkThrough);
    }

    private void showSearchResultsWalkThrough() {
        if (isEnabled()) {
            showSearchResultsWalkthrough();
        }
    }

    private void showSearchResultsWalkthrough() {
        this.walkthroughUtil.showWalkthrough(this.searchResultsWalkThroughContainer, WalkthroughType.PickFood, this.skipClickedCallback);
    }

    private void hideSearchResultWalkThrough(boolean z) {
        if (isEnabled() && ViewUtils.isVisible(this.searchResultsWalkThroughContainer)) {
            if (z) {
                this.walkthroughUtil.hide(this.searchResultsWalkThroughContainer);
            } else {
                ViewUtils.setVisible(false, this.searchResultsWalkThroughContainer);
            }
            this.isSearchResultsWalkthroughShowing = false;
        }
    }

    /* access modifiers changed from: protected */
    public void initializeWalkThrough() {
        if (this.isWalkThrough) {
            this.walkThrough = ViewUtils.findById(getView(), R.id.search_walkthrough_container);
            this.walkthroughUtil.showWalkthrough(this.walkThrough, WalkthroughType.EnterQuery, this.skipClickedCallback);
            this.walkThroughInitialized = true;
        }
    }

    /* access modifiers changed from: private */
    public void setQueryTerm(String str) {
        this.queryTerm = str;
    }

    @Subscribe
    public void onV2SearchReturnedNoResultsEvent(V2SearchReturnedNoResultsEvent v2SearchReturnedNoResultsEvent) {
        getAnalyticsService().reportEvent(Events.FOODSEARCH_ZERO_RESULTS_FOUND);
        hideSearchResultWalkThrough(false);
        this.walkthroughUtil.showWalkthrough(this.walkThrough, WalkthroughType.NoResultsFound, this.skipClickedCallback);
        getAnalyticsService().reportEvent(Events.SPOTLIGHT_ZERO_RESULTS_SEE);
    }

    @Subscribe
    public void onV2SearchReturnedResultsEvent(V2SearchReturnedResultsEvent v2SearchReturnedResultsEvent) {
        if (!this.isSearchResultsWalkthroughShowing) {
            hideEnterSearchQueryWalkThrough();
            showSearchResultsWalkThrough();
        }
    }

    private void hideEnterSearchQueryWalkThrough() {
        ViewUtils.setVisible(false, ViewUtils.findById(getView(), R.id.search_walkthrough_container));
    }
}
