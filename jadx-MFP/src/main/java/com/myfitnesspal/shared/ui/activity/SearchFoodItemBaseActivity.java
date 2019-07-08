package com.myfitnesspal.shared.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.search.event.FoodItemSelectedEvent;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment;
import com.myfitnesspal.feature.search.ui.fragment.OnlineFoodSearchFragment.Extras;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.Debouncer;
import com.uacf.core.util.Strings;
import java.util.List;

public abstract class SearchFoodItemBaseActivity extends MfpActivity {
    private static final int DEBOUNCER_DELAY = 200;
    private static final String SEARCH_FRAGMENT_TAG = "search_fragment";
    @BindView(2131362814)
    protected EditText inputText;
    private Debouncer<String> searchForMatchesDebouncer = new Debouncer<String>(200) {
        /* access modifiers changed from: protected */
        public void onDebounced(String str) {
            SearchFoodItemBaseActivity.this.searchForMatches(str);
        }
    };

    private class BusEventHelper implements BusEventHandler {
        private BusEventHelper() {
        }

        @Subscribe
        public void onFoodItemSelectedEvent(FoodItemSelectedEvent foodItemSelectedEvent) {
            SearchFoodItemBaseActivity.this.foodSelected(foodItemSelectedEvent);
        }
    }

    /* access modifiers changed from: protected */
    public abstract void foodSelected(FoodItemSelectedEvent foodItemSelectedEvent);

    /* access modifiers changed from: protected */
    @LayoutRes
    public abstract int getLayoutResId();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getLayoutResId());
        component().inject((MfpActivity) this);
        setListeners();
    }

    public void addBusEventHandlers(List<BusEventHandler> list) {
        super.addBusEventHandlers(list);
        list.add(new BusEventHelper());
    }

    private void setListeners() {
        this.inputText.setOnEditorActionListener(new OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return SearchFoodItemBaseActivity.lambda$setListeners$0(SearchFoodItemBaseActivity.this, textView, i, keyEvent);
            }
        });
        this.inputText.setSelectAllOnFocus(true);
    }

    public static /* synthetic */ boolean lambda$setListeners$0(SearchFoodItemBaseActivity searchFoodItemBaseActivity, TextView textView, int i, KeyEvent keyEvent) {
        searchFoodItemBaseActivity.searchForMatchesDebouncer.call(Strings.toString(textView.getText()));
        searchFoodItemBaseActivity.getImmHelper().hideSoftInput((View) searchFoodItemBaseActivity.inputText);
        return true;
    }

    /* access modifiers changed from: protected */
    public void searchForMatches(String str) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        OnlineFoodSearchFragment onlineFoodSearchFragment = (OnlineFoodSearchFragment) supportFragmentManager.findFragmentByTag(SEARCH_FRAGMENT_TAG);
        if (onlineFoodSearchFragment == null) {
            OnlineFoodSearchFragment newInstance = OnlineFoodSearchFragment.newInstance(new Extras().setQuery(str).setShouldDisableMultiAdd(true));
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.add(R.id.container, newInstance, SEARCH_FRAGMENT_TAG);
            beginTransaction.commit();
            return;
        }
        onlineFoodSearchFragment.performSearch(str);
    }
}
