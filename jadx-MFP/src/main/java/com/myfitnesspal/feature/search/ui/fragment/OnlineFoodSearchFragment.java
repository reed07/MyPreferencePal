package com.myfitnesspal.feature.search.ui.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider.Factory;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.constraint.Group;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2;
import com.myfitnesspal.feature.diary.service.DiaryService;
import com.myfitnesspal.feature.diary.ui.activity.Diary;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorActivity;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorAnalyticsExtras;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras;
import com.myfitnesspal.feature.foodeditor.ui.activity.FoodEditorExtras.ActionType;
import com.myfitnesspal.feature.foodeditor.ui.mixin.impl.FoodEditorMixinBase;
import com.myfitnesspal.feature.registration.model.Resource;
import com.myfitnesspal.feature.registration.model.Resource.Error;
import com.myfitnesspal.feature.registration.model.Resource.Loading;
import com.myfitnesspal.feature.registration.model.Resource.Success;
import com.myfitnesspal.feature.restaurantlogging.model.MenusActivityBundleData;
import com.myfitnesspal.feature.restaurantlogging.model.Venue;
import com.myfitnesspal.feature.restaurantlogging.ui.activity.MenusActivity;
import com.myfitnesspal.feature.search.event.FoodItemSelectedEvent;
import com.myfitnesspal.feature.search.event.V2SearchReturnedNoResultsEvent;
import com.myfitnesspal.feature.search.event.V2SearchReturnedResultsEvent;
import com.myfitnesspal.feature.search.model.SearchResultType;
import com.myfitnesspal.feature.search.model.SearchSource;
import com.myfitnesspal.feature.search.model.SponsoredFood;
import com.myfitnesspal.feature.search.service.SearchService;
import com.myfitnesspal.feature.search.ui.activity.FoodSearchActivityV2;
import com.myfitnesspal.feature.search.ui.adapter.OnlineFoodSearchAdapter;
import com.myfitnesspal.feature.search.ui.viewmodel.FoodSearchViewModel;
import com.myfitnesspal.feature.search.ui.viewmodel.OnlineFoodSearchViewModel;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.RequestCodes;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.event.UpdateMultiAddStatusEvent;
import com.myfitnesspal.shared.extension.SpannableUtil;
import com.myfitnesspal.shared.model.FoodV2Logging;
import com.myfitnesspal.shared.model.FoodV2Logging.Builder;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapper;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.model.v2.MfpFoodSearchResult;
import com.myfitnesspal.shared.model.v2.SearchResultItem;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.install.CountryService;
import com.myfitnesspal.shared.service.userdata.UserEnergyService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.factory.VMFactory;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.navigation.NavigationHelper;
import com.myfitnesspal.shared.util.ConfigUtils;
import com.myfitnesspal.shared.util.MultiAddFoodHelper;
import com.myfitnesspal.shared.util.SnackbarBuilder;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.DeviceInfo;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.inject.Inject;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

public class OnlineFoodSearchFragment extends MfpFragment {
    private static final String PLUS_SIGN_PLACEHOLDER = "PLUS_SIGN_HERE";
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<CountryService> countryService;
    @Inject
    Lazy<DeviceInfo> deviceInfo;
    @Inject
    Lazy<DiaryService> diaryService;
    @Inject
    Lazy<FoodMapper> foodMapper;
    private OnlineFoodSearchAdapter foodSearchAdapter;
    private FoodSearchViewModel foodSearchViewModel;
    /* access modifiers changed from: private */
    public LinearLayoutManager layoutManager;
    @Inject
    Lazy<MultiAddFoodHelper> multiAddFoodHelper;
    private List<FoodV2Logging> multiAddFoodsToTrack;
    private FoodV2Logging multiAddFromAddFoodSummary;
    @BindView(2131363115)
    Group newDesignEmptyState;
    @BindView(2131363154)
    TextView newDesignNoResultsMessage;
    @BindView(2131363221)
    TextView newDesignOnlineSearchStatusView;
    @BindView(2131364137)
    CompoundButton newDesignVerifiedOnlyButton;
    @BindView(2131363155)
    View noResultsView;
    @BindView(2131363220)
    RecyclerView searchResultsRecyclerView;
    @Inject
    Lazy<SearchService> searchService;
    @Inject
    Lazy<UserEnergyService> userEnergyService;
    /* access modifiers changed from: private */
    public OnlineFoodSearchViewModel viewModel;
    @Inject
    VMFactory vmFactory;

    public static class Extras implements Parcelable {
        public static final Creator<Extras> CREATOR = new Creator<Extras>() {
            public Extras createFromParcel(Parcel parcel) {
                return new Extras(parcel);
            }

            public Extras[] newArray(int i) {
                return new Extras[i];
            }
        };
        private String barcode;
        private String flowId;
        private boolean isInMealFoodCreationFlow;
        private boolean isInWalkthrough;
        private String listType;
        private String mealFoodCreationFlowId;
        private String mealName;
        private String query;
        private boolean shouldDisableMultiAdd;
        private SearchSource source;
        private Trigger trigger;

        public int describeContents() {
            return 0;
        }

        public Extras() {
        }

        private Extras(Parcel parcel) {
            this.query = parcel.readString();
            this.mealName = parcel.readString();
            boolean z = true;
            this.isInMealFoodCreationFlow = parcel.readByte() != 0;
            this.flowId = parcel.readString();
            this.mealFoodCreationFlowId = parcel.readString();
            this.barcode = parcel.readString();
            this.source = (SearchSource) parcel.readSerializable();
            this.listType = parcel.readString();
            this.shouldDisableMultiAdd = parcel.readByte() != 0;
            if (parcel.readByte() == 0) {
                z = false;
            }
            this.isInWalkthrough = z;
            this.trigger = (Trigger) parcel.readSerializable();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.query);
            parcel.writeString(this.mealName);
            parcel.writeByte(this.isInMealFoodCreationFlow ? (byte) 1 : 0);
            parcel.writeString(this.flowId);
            parcel.writeString(this.mealFoodCreationFlowId);
            parcel.writeString(this.barcode);
            parcel.writeSerializable(this.source);
            parcel.writeString(this.listType);
            parcel.writeByte(this.shouldDisableMultiAdd ? (byte) 1 : 0);
            parcel.writeByte(this.isInWalkthrough ? (byte) 1 : 0);
            parcel.writeSerializable(this.trigger);
        }

        public String getQuery() {
            return this.query;
        }

        public Extras setQuery(String str) {
            this.query = str;
            return this;
        }

        public String getMealName() {
            return this.mealName;
        }

        public Extras setMealName(String str) {
            this.mealName = str;
            return this;
        }

        public boolean isInMealFoodCreationFlow() {
            return this.isInMealFoodCreationFlow;
        }

        public Extras setInMealFoodCreationFlow(boolean z) {
            this.isInMealFoodCreationFlow = z;
            return this;
        }

        public String getMealFoodCreationFlowId() {
            return this.mealFoodCreationFlowId;
        }

        public Extras setMealFoodCreationFlowId(String str) {
            this.mealFoodCreationFlowId = str;
            return this;
        }

        public String getFlowId() {
            return this.flowId;
        }

        public Extras setFlowId(String str) {
            this.flowId = str;
            return this;
        }

        public String getBarcode() {
            return this.barcode;
        }

        public Extras setBarcode(String str) {
            this.barcode = str;
            return this;
        }

        public SearchSource getSource() {
            return this.source;
        }

        public Extras setSource(SearchSource searchSource) {
            this.source = searchSource;
            return this;
        }

        public String getListType() {
            return this.listType;
        }

        public Extras setListType(String str) {
            this.listType = str;
            return this;
        }

        public boolean isShouldDisableMultiAdd() {
            return this.shouldDisableMultiAdd;
        }

        public Extras setShouldDisableMultiAdd(boolean z) {
            this.shouldDisableMultiAdd = z;
            return this;
        }

        public boolean isInWalkthrough() {
            return this.isInWalkthrough;
        }

        public Extras setInWalkthrough(boolean z) {
            this.isInWalkthrough = z;
            return this;
        }

        public Trigger getTrigger() {
            return this.trigger;
        }

        public Extras setTrigger(Trigger trigger2) {
            this.trigger = trigger2;
            return this;
        }
    }

    public enum Trigger {
        MANUAL("manual"),
        NO_HISTORY_RESULTS("no_history_results"),
        TAP_FROM_HISTORY("tap_from_history");
        
        public final String anltName;

        private Trigger(String str) {
            this.anltName = str;
        }
    }

    public static OnlineFoodSearchFragment newInstance(Extras extras) {
        Bundle bundle = new Bundle();
        OnlineFoodSearchFragment onlineFoodSearchFragment = new OnlineFoodSearchFragment();
        bundle.putParcelable("extras", extras);
        onlineFoodSearchFragment.setArguments(bundle);
        return onlineFoodSearchFragment;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        this.viewModel = (OnlineFoodSearchViewModel) ViewModelProviders.of((Fragment) this, (Factory) this.vmFactory).get(OnlineFoodSearchViewModel.class);
        this.viewModel.setExtras((Extras) BundleUtils.getParcelable(getArguments(), "extras", Extras.class.getClassLoader()));
        this.viewModel.getSearchResults().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                OnlineFoodSearchFragment.lambda$onCreate$0(OnlineFoodSearchFragment.this, (Resource) obj);
            }
        });
        this.viewModel.getNextPage().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                OnlineFoodSearchFragment.lambda$onCreate$1(OnlineFoodSearchFragment.this, (Resource) obj);
            }
        });
        this.viewModel.getSponsoredCategory().observe(this, new Observer() {
            public final void onChanged(Object obj) {
                OnlineFoodSearchFragment.lambda$onCreate$2(OnlineFoodSearchFragment.this, (String) obj);
            }
        });
        if (this.viewModel.isVerifiedOnlyAvailable()) {
            this.viewModel.isVerifiedFoodsOnly().observe(this, new Observer() {
                public final void onChanged(Object obj) {
                    OnlineFoodSearchFragment.lambda$onCreate$4(OnlineFoodSearchFragment.this, (Boolean) obj);
                }
            });
        }
        if (getActivity() != null) {
            this.foodSearchViewModel = (FoodSearchViewModel) ViewModelProviders.of(getActivity(), (Factory) this.vmFactory).get(FoodSearchViewModel.class);
            this.foodSearchViewModel.isMultiAddEnabled().observe(this, new Observer() {
                public final void onChanged(Object obj) {
                    OnlineFoodSearchFragment.this.toggleMultiAdd(((Boolean) obj).booleanValue());
                }
            });
        }
    }

    public static /* synthetic */ void lambda$onCreate$0(OnlineFoodSearchFragment onlineFoodSearchFragment, Resource resource) {
        boolean z = resource instanceof Loading;
        onlineFoodSearchFragment.setBusy(z);
        if (z) {
            onlineFoodSearchFragment.foodSearchAdapter.clear();
            onlineFoodSearchFragment.newDesignOnlineSearchStatusView.setText(R.string.searching);
            onlineFoodSearchFragment.newDesignVerifiedOnlyButton.setEnabled(false);
        } else {
            onlineFoodSearchFragment.newDesignOnlineSearchStatusView.setText(R.string.search_results);
            onlineFoodSearchFragment.newDesignVerifiedOnlyButton.setEnabled(true);
        }
        if (resource instanceof Success) {
            List list = (List) ((Success) resource).getData();
            if (CollectionUtils.isEmpty((Collection<?>) list)) {
                onlineFoodSearchFragment.updateSearchResultVisibility(false);
                onlineFoodSearchFragment.getMessageBus().post(new V2SearchReturnedNoResultsEvent());
                return;
            }
            onlineFoodSearchFragment.updateSearchResultVisibility(true);
            onlineFoodSearchFragment.foodSearchAdapter.setItems(list);
            onlineFoodSearchFragment.foodSearchAdapter.notifyDataSetChanged();
            onlineFoodSearchFragment.getMessageBus().post(new V2SearchReturnedResultsEvent());
        } else if (resource instanceof Error) {
            new SnackbarBuilder(onlineFoodSearchFragment.searchResultsRecyclerView).setMessage((int) R.string.network_problem_searching).setDuration(0).show();
        }
    }

    public static /* synthetic */ void lambda$onCreate$1(OnlineFoodSearchFragment onlineFoodSearchFragment, Resource resource) {
        onlineFoodSearchFragment.foodSearchAdapter.setFetchingNextPage(resource instanceof Loading);
        if (resource instanceof Error) {
            onlineFoodSearchFragment.postEvent(new AlertEvent(((Error) resource).getThrowable().getMessage()).asToast());
        }
    }

    public static /* synthetic */ void lambda$onCreate$2(OnlineFoodSearchFragment onlineFoodSearchFragment, String str) {
        MfpActivity mfpActivity = (MfpActivity) onlineFoodSearchFragment.getActivity();
        if (!Strings.isEmpty(str) && mfpActivity != null) {
            mfpActivity.forceLoadBannerAdWithCategory(str);
        }
    }

    public static /* synthetic */ void lambda$onCreate$4(OnlineFoodSearchFragment onlineFoodSearchFragment, Boolean bool) {
        if (bool != null) {
            onlineFoodSearchFragment.expandAppBarIfPresent();
            onlineFoodSearchFragment.newDesignVerifiedOnlyButton.setOnCheckedChangeListener(null);
            onlineFoodSearchFragment.newDesignVerifiedOnlyButton.setChecked(bool.booleanValue());
            onlineFoodSearchFragment.newDesignVerifiedOnlyButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    OnlineFoodSearchFragment.lambda$null$3(OnlineFoodSearchFragment.this, compoundButton, z);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$null$3(OnlineFoodSearchFragment onlineFoodSearchFragment, CompoundButton compoundButton, boolean z) {
        OnlineFoodSearchViewModel onlineFoodSearchViewModel = onlineFoodSearchFragment.viewModel;
        onlineFoodSearchViewModel.search(onlineFoodSearchViewModel.getQuery(), onlineFoodSearchFragment.viewModel.getSearchTrigger(), z);
    }

    public View onCreateView(@NonNull LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_online_food_search, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        init();
        return inflate;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i != 54) {
                if (i == 196) {
                    this.foodSearchAdapter.notifyDataSetChanged();
                }
            } else if (this.viewModel.getSearchResults().getValue() instanceof Success) {
                List<MfpFoodSearchResult> list = (List) ((Success) this.viewModel.getSearchResults().getValue()).getData();
                if (list != null) {
                    Bundle extras = intent.getExtras();
                    MfpFood mfpFood = (MfpFood) BundleUtils.getParcelable(extras, com.myfitnesspal.shared.constants.Constants.Extras.MFP_FOOD, MfpFood.class.getClassLoader());
                    if (mfpFood != null) {
                        for (MfpFoodSearchResult mfpFoodSearchResult : list) {
                            SearchResultItem searchResultItem = mfpFoodSearchResult.getSearchResultItem();
                            if ((searchResultItem instanceof MfpFood) && Strings.equals(((MfpFood) searchResultItem).getId(), mfpFood.getId())) {
                                mfpFoodSearchResult.setSearchResultItem(mfpFood);
                            }
                        }
                        if (getMultiAddFoodHelper().isMultiAddModeOn()) {
                            int i3 = BundleUtils.getInt(extras, "serving_size_index");
                            FoodV2Logging foodV2Logging = new FoodV2Logging(this.viewModel.getQuery(), mfpFood.getId(), mfpFood.getVersion(), BundleUtils.getInt(extras, "position"), i3, mfpFood.getVerified(), SearchSource.ONLINE.getTitle());
                            this.multiAddFromAddFoodSummary = foodV2Logging;
                            postEventAfterResume(new UpdateMultiAddStatusEvent());
                        }
                        this.foodSearchAdapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        this.viewModel.reportOnlineSearchSummary();
    }

    public void performSearch(String str) {
        performSearch(str, null);
    }

    public void performSearch(String str, Trigger trigger) {
        OnlineFoodSearchViewModel onlineFoodSearchViewModel = this.viewModel;
        if (onlineFoodSearchViewModel != null) {
            onlineFoodSearchViewModel.search(str, trigger);
        }
    }

    private void init() {
        int i = 8;
        if (this.viewModel.getAreSearchImprovementsLive()) {
            this.newDesignOnlineSearchStatusView.setVisibility(0);
            CompoundButton compoundButton = this.newDesignVerifiedOnlyButton;
            if (this.viewModel.isVerifiedOnlyAvailable()) {
                i = 0;
            }
            compoundButton.setVisibility(i);
        } else {
            this.newDesignOnlineSearchStatusView.setVisibility(8);
            this.newDesignVerifiedOnlyButton.setVisibility(8);
        }
        OnlineFoodSearchAdapter onlineFoodSearchAdapter = new OnlineFoodSearchAdapter((MfpActivity) getActivity(), new ArrayList(), (UserEnergyService) this.userEnergyService.get(), (MultiAddFoodHelper) this.multiAddFoodHelper.get(), (FoodMapper) this.foodMapper.get(), !this.viewModel.getAreSearchImprovementsLive());
        this.foodSearchAdapter = onlineFoodSearchAdapter;
        this.foodSearchAdapter.setOnFoodClick(new Function2() {
            public final Object invoke(Object obj, Object obj2) {
                return OnlineFoodSearchFragment.lambda$init$5(OnlineFoodSearchFragment.this, (MfpFood) obj, (Integer) obj2);
            }
        });
        this.foodSearchAdapter.setOnSponsoredFoodClick(new Function2() {
            public final Object invoke(Object obj, Object obj2) {
                return OnlineFoodSearchFragment.lambda$init$6(OnlineFoodSearchFragment.this, (SponsoredFood) obj, (Integer) obj2);
            }
        });
        this.foodSearchAdapter.setOnVenueClick(new Function2() {
            public final Object invoke(Object obj, Object obj2) {
                return OnlineFoodSearchFragment.lambda$init$7(OnlineFoodSearchFragment.this, (Venue) obj, (Integer) obj2);
            }
        });
        this.foodSearchAdapter.setOnCheckedChange(new Function3() {
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return OnlineFoodSearchFragment.lambda$init$8(OnlineFoodSearchFragment.this, (MfpFood) obj, (Integer) obj2, (Boolean) obj3);
            }
        });
        toggleListMultiAddMode(((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn());
        this.layoutManager = new LinearLayoutManager(getContext(), 1, false);
        this.searchResultsRecyclerView.setLayoutManager(this.layoutManager);
        this.searchResultsRecyclerView.setAdapter(this.foodSearchAdapter);
        this.searchResultsRecyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                OnlineFoodSearchFragment.this.getImmHelper().hideSoftInput();
            }

            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                int childCount = OnlineFoodSearchFragment.this.layoutManager.getChildCount();
                int itemCount = OnlineFoodSearchFragment.this.layoutManager.getItemCount();
                int findFirstVisibleItemPosition = OnlineFoodSearchFragment.this.layoutManager.findFirstVisibleItemPosition();
                if (childCount + findFirstVisibleItemPosition >= itemCount && findFirstVisibleItemPosition >= 0) {
                    OnlineFoodSearchFragment.this.viewModel.fetchNextPage();
                }
            }
        });
        if (!this.viewModel.getAreSearchImprovementsLive()) {
            return;
        }
        if (getActivity() instanceof FoodSearchActivityV2) {
            SpannableUtil.setImageSpan(this.newDesignNoResultsMessage, getString(R.string.online_search_empty_message), new Pair(PLUS_SIGN_PLACEHOLDER, Integer.valueOf(R.drawable.ic_plus_18dp)));
            return;
        }
        this.newDesignNoResultsMessage.setText(R.string.no_menus_available);
    }

    public static /* synthetic */ Unit lambda$init$5(OnlineFoodSearchFragment onlineFoodSearchFragment, MfpFood mfpFood, Integer num) {
        onlineFoodSearchFragment.viewModel.trackClickInSearchSummary(num.intValue());
        if (onlineFoodSearchFragment.viewModel.getFlowId() == null) {
            onlineFoodSearchFragment.postEvent(new FoodItemSelectedEvent(onlineFoodSearchFragment.viewModel.getQuery(), mfpFood, num.intValue(), onlineFoodSearchFragment.viewModel.getMealName()));
        } else {
            onlineFoodSearchFragment.switchToFoodSummaryViewForFood(mfpFood, 1.0f, num.intValue());
        }
        return null;
    }

    public static /* synthetic */ Unit lambda$init$6(OnlineFoodSearchFragment onlineFoodSearchFragment, SponsoredFood sponsoredFood, Integer num) {
        Intent intent;
        onlineFoodSearchFragment.viewModel.reportSponsoredAdPressed();
        onlineFoodSearchFragment.viewModel.trackClickInSearchSummary(num.intValue());
        onlineFoodSearchFragment.viewModel.reportFoodLookupEvent(sponsoredFood, num.intValue());
        FoodEditorExtras foodEditorAnalyticsExtras = new FoodEditorExtras().setActionType(ActionType.Create).setSupportPairedFoods(false).setMealName(onlineFoodSearchFragment.viewModel.getMealName()).setGetSuggestedServings(true).setDate(onlineFoodSearchFragment.getSession().getUser().getActiveDate().getTime()).setMultiAddOn(((MultiAddFoodHelper) onlineFoodSearchFragment.multiAddFoodHelper.get()).isMultiAddModeOn()).setScreenTitle(onlineFoodSearchFragment.getString(R.string.addFood)).setFlowId(onlineFoodSearchFragment.viewModel.getFlowId()).setFoodEditorAnalyticsExtras(new FoodEditorAnalyticsExtras().setSearchQuery(onlineFoodSearchFragment.viewModel.getQuery()).setResultsListPosition(num.intValue()));
        if (onlineFoodSearchFragment.viewModel.isMealFoodCreationFlow()) {
            intent = null;
        } else {
            intent = Diary.newStartIntentWithReferrerAndForceHomeOnBack(onlineFoodSearchFragment.getContext(), com.myfitnesspal.shared.constants.Constants.Extras.REFERRER_DIARY_JUST_LOGGED);
        }
        onlineFoodSearchFragment.getNavigationHelper().withExtra("request_id", onlineFoodSearchFragment.viewModel.getSearchRequestId()).withExtra(FoodEditorMixinBase.EXTRA_SEARCH_VERSION, onlineFoodSearchFragment.getSearchVersion()).withIntent(FoodEditorActivity.newSponsoredFoodIntent(onlineFoodSearchFragment.getContext(), intent, sponsoredFood, onlineFoodSearchFragment.getSession().getUser().getActiveDate(), onlineFoodSearchFragment.viewModel.getMealName(), onlineFoodSearchFragment.viewModel.getSource(), com.myfitnesspal.shared.constants.Constants.Extras.REFERRER_ONLINE_FOOD_SEARCH_FRAGMENT, onlineFoodSearchFragment.viewModel.isMealFoodCreationFlow(), foodEditorAnalyticsExtras, sponsoredFood.getSponsoredFoodAd())).startActivity(foodEditorAnalyticsExtras.isMultiAddOn() ? RequestCodes.FOOD_MULTI_ADD_EDITOR : 54);
        return null;
    }

    public static /* synthetic */ Unit lambda$init$7(OnlineFoodSearchFragment onlineFoodSearchFragment, Venue venue, Integer num) {
        onlineFoodSearchFragment.viewModel.trackClickInSearchSummary(num.intValue());
        onlineFoodSearchFragment.viewModel.reportFoodLookupEvent(venue, num.intValue());
        onlineFoodSearchFragment.getMultiAddFoodHelper().disable();
        MenusActivityBundleData menusActivityBundleData = new MenusActivityBundleData(venue, onlineFoodSearchFragment.viewModel.getMealName(), onlineFoodSearchFragment.getSession().getUser().getActiveDate(), onlineFoodSearchFragment.viewModel.getFlowId(), "food_search", onlineFoodSearchFragment.viewModel.isMealFoodCreationFlow());
        onlineFoodSearchFragment.getNavigationHelper().withIntent(MenusActivity.newStartIntent(onlineFoodSearchFragment.getActivity(), menusActivityBundleData)).startActivity(RequestCodes.MENUS);
        return null;
    }

    public static /* synthetic */ Unit lambda$init$8(OnlineFoodSearchFragment onlineFoodSearchFragment, MfpFood mfpFood, Integer num, Boolean bool) {
        try {
            onlineFoodSearchFragment.toggleMultiAddForFood(mfpFood, num.intValue(), bool.booleanValue());
        } catch (Exception e) {
            Ln.e(e);
        }
        return null;
    }

    private void switchToFoodSummaryViewForFood(MfpFood mfpFood, float f, int i) {
        Intent intent;
        MfpFood mfpFood2 = mfpFood;
        int i2 = i;
        int shiftPositionIfAdPresent = this.viewModel.shiftPositionIfAdPresent(i2);
        float f2 = f;
        if (((double) f2) <= 0.0d) {
            f2 = 1.0f;
        }
        if (mfpFood2 != null) {
            this.viewModel.reportFoodLookupEvent(mfpFood, i2);
            this.viewModel.reportFoodPressed();
            int i3 = 54;
            if (ConfigUtils.isNewAddFoodFlowOn(getConfigService())) {
                FoodEditorExtras foodEditorAnalyticsExtras = new FoodEditorExtras().setActionType(ActionType.Create).setSupportPairedFoods(false).setMealName(this.viewModel.getMealName()).setGetSuggestedServings(true).setDate(getSession().getUser().getActiveDate().getTime()).setMultiAddOn(((MultiAddFoodHelper) this.multiAddFoodHelper.get()).isMultiAddModeOn()).setScreenTitle(getString(R.string.addFood)).setFoodEditorAnalyticsExtras(new FoodEditorAnalyticsExtras().setSearchQuery(this.viewModel.getQuery()).setResultsListPosition(shiftPositionIfAdPresent).setPositionWithAd(i2));
                if (this.viewModel.isMealFoodCreationFlow()) {
                    intent = null;
                } else {
                    intent = Diary.newStartIntentWithReferrerAndForceHomeOnBack(getContext(), com.myfitnesspal.shared.constants.Constants.Extras.REFERRER_DIARY_JUST_LOGGED);
                }
                NavigationHelper withIntent = getNavigationHelper().withExtra("request_id", this.viewModel.getSearchRequestId()).withExtra(FoodEditorMixinBase.EXTRA_SEARCH_VERSION, getSearchVersion()).withIntent(FoodEditorActivity.newDiaryFoodItemEditorIntent(getContext(), intent, mfpFood, getSession().getUser().getActiveDate(), this.viewModel.getMealName(), this.viewModel.getBarcode(), SearchSource.ONLINE, com.myfitnesspal.shared.constants.Constants.Extras.REFERRER_ONLINE_FOOD_SEARCH_FRAGMENT, this.viewModel.isMealFoodCreationFlow(), foodEditorAnalyticsExtras));
                if (foodEditorAnalyticsExtras.isMultiAddOn()) {
                    i3 = RequestCodes.FOOD_MULTI_ADD_EDITOR;
                }
                withIntent.startActivity(i3);
            } else {
                getNavigationHelper().withIntent(AddFoodSummaryViewV2.newStartIntent(getActivity(), (com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.Extras) ((com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.Extras) ((com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.Extras) ((com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.Extras) ((com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.Extras) ((com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.Extras) ((com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.Extras) ((com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.Extras) new com.myfitnesspal.feature.addentry.ui.activity.AddFoodSummaryViewV2.Extras().setFood(mfpFood).setRequestId(this.viewModel.getSearchRequestId()).setSearchVersion(getSearchVersion()).setMealName(this.viewModel.getMealName())).setTitle(getString(R.string.addFood))).setServings(f2)).setDate(getSession().getUser().getActiveDate())).setQuery(this.viewModel.getQuery())).setPosition(i2)).setSource("online_search")).setBarcode(this.viewModel.getBarcode()).setMealFoodCreationFlow(this.viewModel.isMealFoodCreationFlow()))).startActivity(54);
            }
            return;
        }
        postEvent(new AlertEvent(getActivity().getString(R.string.unknown_error)));
    }

    private void toggleMultiAddForFood(MfpFood mfpFood, int i, boolean z) {
        Food mapFromMfpFood = ((FoodMapper) this.foodMapper.get()).mapFromMfpFood(mfpFood, getSession().getUser());
        if (mapFromMfpFood != null) {
            boolean z2 = false;
            MultiAddFoodHelper multiAddFoodHelper2 = getMultiAddFoodHelper();
            if (multiAddFoodHelper2.isMultiAddModeOn()) {
                FoodV2Logging build = Builder.fromMfpFood(mfpFood).searchTerm(this.viewModel.getQuery()).index(this.viewModel.shiftPositionIfAdPresent(i)).indexWithAd(i).source(SearchSource.ONLINE.getTitle()).type(mfpFood instanceof SponsoredFood ? SearchResultType.FOOD_AD : SearchResultType.FOOD).requestId(this.viewModel.getSearchRequestId()).build();
                if (z) {
                    multiAddFoodHelper2.addAndLogItem(mapFromMfpFood, build);
                    ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.LOGGED, "yes");
                } else {
                    multiAddFoodHelper2.removeItemAndLog(mapFromMfpFood, build);
                    if (!multiAddFoodHelper2.containsOnlineItemWithTerm(this.viewModel.getQuery())) {
                        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.ONLINE_SEARCH_SUMMARY, Attributes.LOGGED, "no");
                    }
                }
                z2 = true;
            }
            if (z2) {
                if (z) {
                    FoodV2Logging foodV2Logging = this.multiAddFromAddFoodSummary;
                    if (foodV2Logging == null || !foodV2Logging.matchesMfpFood(mfpFood)) {
                        addV2FoodToMultiAdd(mfpFood, -1, i);
                    } else {
                        addV2FoodToMultiAdd(mfpFood, this.multiAddFromAddFoodSummary.getServingSizeIndex(), i);
                    }
                } else {
                    removeV2FoodFromMultiAdd(mfpFood);
                }
                this.foodSearchAdapter.notifyDataSetChanged();
                postEvent(new UpdateMultiAddStatusEvent());
            }
        }
    }

    private MultiAddFoodHelper getMultiAddFoodHelper() {
        return (MultiAddFoodHelper) this.multiAddFoodHelper.get();
    }

    private void addV2FoodToMultiAdd(MfpFood mfpFood, int i, int i2) {
        boolean z;
        boolean z2;
        MfpFood mfpFood2 = mfpFood;
        int i3 = i;
        if (CollectionUtils.isEmpty((Collection<?>) this.multiAddFoodsToTrack)) {
            this.multiAddFoodsToTrack = new ArrayList();
        }
        Iterator it = this.multiAddFoodsToTrack.iterator();
        while (true) {
            z = true;
            if (!it.hasNext()) {
                z2 = false;
                z = false;
                break;
            }
            FoodV2Logging foodV2Logging = (FoodV2Logging) it.next();
            if (foodV2Logging.matchesMfpFood(mfpFood2)) {
                z2 = (foodV2Logging.getServingSizeIndex() == i3 || i3 == -1) ? false : true;
            }
        }
        if (!z || z2) {
            if (z2) {
                removeV2FoodFromMultiAdd(mfpFood);
            }
            int i4 = i3 == -1 ? 0 : i3;
            List<FoodV2Logging> list = this.multiAddFoodsToTrack;
            FoodV2Logging foodV2Logging2 = new FoodV2Logging(this.viewModel.getQuery(), mfpFood.getId(), mfpFood.getVersion(), this.viewModel.shiftPositionIfAdPresent(i2), i4, mfpFood.getVerified(), SearchSource.ONLINE.getTitle(), mfpFood2 instanceof SponsoredFood ? SearchResultType.FOOD_AD : SearchResultType.FOOD, i2);
            list.add(foodV2Logging2);
        }
    }

    private void removeV2FoodFromMultiAdd(MfpFood mfpFood) {
        int i = -1;
        for (FoodV2Logging foodV2Logging : this.multiAddFoodsToTrack) {
            if (foodV2Logging.matchesMfpFood(mfpFood)) {
                i = this.multiAddFoodsToTrack.indexOf(foodV2Logging);
            }
        }
        if (i != -1) {
            this.multiAddFoodsToTrack.remove(i);
        }
    }

    /* access modifiers changed from: private */
    public void toggleMultiAdd(boolean z) {
        toggleListMultiAddMode(z);
        if (z) {
            this.multiAddFoodsToTrack = new ArrayList();
        }
    }

    private void toggleListMultiAddMode(boolean z) {
        if (!shouldDisableMultiAdd()) {
            this.foodSearchAdapter.setMultiAddEnabled(z);
            this.foodSearchAdapter.notifyDataSetChanged();
        }
    }

    private boolean shouldDisableMultiAdd() {
        return Strings.notEmpty(this.viewModel.getBarcode()) || this.viewModel.getShouldDisableMultiAdd();
    }

    private void updateSearchResultVisibility(boolean z) {
        ViewUtils.setVisible(z, this.searchResultsRecyclerView);
        if (this.viewModel.getAreSearchImprovementsLive()) {
            ViewUtils.setVisible(!z, 8, this.newDesignEmptyState);
            if (this.viewModel.isVerifiedOnlyAvailable() && !this.newDesignVerifiedOnlyButton.isChecked()) {
                ViewUtils.setVisible(z, 8, this.newDesignVerifiedOnlyButton);
                return;
            }
            return;
        }
        ViewUtils.setVisible(!z, 8, this.noResultsView);
    }

    private int getSearchVersion() {
        return getActivity() instanceof FoodSearchActivityV2 ? 2 : 1;
    }

    private void expandAppBarIfPresent() {
        if (getActivity() != null) {
            AppBarLayout appBarLayout = (AppBarLayout) getActivity().findViewById(R.id.appBarLayout);
            if (appBarLayout != null) {
                appBarLayout.setExpanded(true, true);
            }
        }
    }
}
