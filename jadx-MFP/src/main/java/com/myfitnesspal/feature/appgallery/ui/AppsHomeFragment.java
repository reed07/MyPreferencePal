package com.myfitnesspal.feature.appgallery.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.MenuItemCompat.OnActionExpandListener;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.event.NavigateToPartnerAppDetailsEvent;
import com.myfitnesspal.feature.appgallery.model.AppItemModel;
import com.myfitnesspal.feature.appgallery.model.AppsHomeViewModel;
import com.myfitnesspal.feature.appgallery.model.AppsHomeViewModel.Property;
import com.myfitnesspal.feature.appgallery.model.FeaturedAppItemModel;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.ui.fragment.MfpFragment;
import com.myfitnesspal.shared.ui.view.LinearLayoutAdapterView;
import com.myfitnesspal.shared.ui.view.LinearLayoutListAdapterView;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.MapUtil;
import com.uacf.core.util.TextViewUtils;
import com.uacf.core.util.ViewUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;

public class AppsHomeFragment extends MfpFragment implements OnViewInstantatedListener {
    private static final int ALL_APPS_INDEX = 0;
    private static final int CONNECTED_APPS_INDEX = 1;
    private static final String EXTRA_CATEGORY_ID = "extra_category_id";
    public static final int NUMBER_OF_PAGER_VIEWS = 2;
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    private AppItemListAdapter<AppItemModel> allAppsAdapter;
    @Inject
    Lazy<AppGalleryService> appGalleryService;
    /* access modifiers changed from: private */
    public int category;
    private Button categoryButton;
    private AppItemListAdapter<AppItemModel> connectedAppsAdapter;
    /* access modifiers changed from: private */
    public AppItemListAdapter<FeaturedAppItemModel> featuredAdapter;
    private LinearLayoutListAdapterView featuredLayout;
    private View headerContent;
    private View headerView;
    /* access modifiers changed from: private */
    public String searchTerm;
    @BindView(2131362804)
    TabLayout tabLayout;
    /* access modifiers changed from: private */
    public AppsHomeViewModel viewModel;
    @BindView(2131364187)
    ViewPager viewPager;

    public int getPageCount() {
        return 2;
    }

    public static AppsHomeFragment newInstance(Intent intent) {
        boolean z = ExtrasUtils.getBoolean(intent, Extras.HARDWARE_TRACKERS_ONLY);
        Bundle bundle = new Bundle();
        bundle.putInt(Extras.APPS_DEVICES_FILTER_CATEGORY, z ? 1 : 0);
        AppsHomeFragment appsHomeFragment = new AppsHomeFragment();
        appsHomeFragment.setArguments(bundle);
        return appsHomeFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        component().inject(this);
        this.category = BundleUtils.getInt(getArguments(), Extras.APPS_DEVICES_FILTER_CATEGORY, 0);
        initViewModel(this.category);
    }

    public View onCreateView(LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.partner_gallery_fragment, viewGroup, false);
        ButterKnife.bind((Object) this, inflate);
        this.viewModel.onRebindView();
        return inflate;
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        setHasOptionsMenu(true);
        FragmentActivity activity = getActivity();
        MaterialUtils.removeDefaultToolbarElevation(activity);
        MaterialUtils.enableAppBarScrollIfLollipop(activity);
        if (bundle != null) {
            this.category = BundleUtils.getInt(bundle, EXTRA_CATEGORY_ID);
        }
        initUI();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt(EXTRA_CATEGORY_ID, this.category);
    }

    private void initViewModel(int i) {
        this.viewModel = (AppsHomeViewModel) getViewModel();
        if (this.viewModel == null) {
            this.viewModel = (AppsHomeViewModel) setViewModel(new AppsHomeViewModel((MfpActivity) getActivity(), getRunner(), this.appGalleryService));
        }
        this.viewModel.setCategory(i);
        this.viewModel.loadIfNotLoaded(new Void[0]);
    }

    private void initUI() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(R.string.all));
        arrayList.add(Integer.valueOf(R.string.connected));
        this.viewPager.setAdapter(new PartnerPagerAdapter(getContext(), this));
        this.tabLayout.setupWithViewPager(this.viewPager);
    }

    public void onPrepareOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.partner_gallery_search, menu);
        MenuItem findItem = menu.findItem(R.id.action_search);
        MenuItemCompat.setOnActionExpandListener(findItem, new OnActionExpandListener() {
            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                return true;
            }

            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                ((ActionTrackingService) AppsHomeFragment.this.actionTrackingService.get()).registerAndReportEvent(Events.TAP_SEARCH_ICON_PARTNERGALLERY);
                return true;
            }
        });
        initializeSearchView(findItem);
        super.onPrepareOptionsMenu(menu);
    }

    private void initializeSearchView(MenuItem menuItem) {
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconifiedByDefault(true);
        StringBuilder sb = new StringBuilder();
        sb.append("<font color=#ffffff>");
        sb.append(getString(R.string.searchForAnApp));
        sb.append("</font>");
        searchView.setQueryHint(Html.fromHtml(sb.toString()));
        MenuItemCompat.setOnActionExpandListener(menuItem, new OnActionExpandListener() {
            public boolean onMenuItemActionExpand(MenuItem menuItem) {
                AppsHomeFragment.this.switchSearchView(true);
                AppsHomeFragment.this.viewModel.resetCategory();
                return true;
            }

            public boolean onMenuItemActionCollapse(MenuItem menuItem) {
                AppsHomeFragment.this.searchTerm = null;
                AppsHomeFragment.this.viewModel.setFilter("");
                AppsHomeFragment.this.switchSearchView(false);
                return true;
            }
        });
        searchView.setOnQueryTextListener(new OnQueryTextListener() {
            public boolean onQueryTextSubmit(String str) {
                return true;
            }

            public boolean onQueryTextChange(String str) {
                AppsHomeFragment.this.searchTerm = str;
                AppsHomeFragment.this.viewModel.setFilter(str);
                return true;
            }
        });
        String str = this.searchTerm;
        if (str != null) {
            searchView.setIconified(false);
            menuItem.expandActionView();
            searchView.setQuery(str, false);
        }
    }

    private void attachAppsEventListeners(ListView listView, int i) {
        listView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (AppsHomeFragment.this.viewModel.isFiltered()) {
                    ((ActionTrackingService) AppsHomeFragment.this.actionTrackingService.get()).registerAndReportEvent(Events.TAP_SEARCH_RESULT_PARTNERGALLERY);
                }
                AppsHomeFragment.this.getMessageBus().post(new NavigateToPartnerAppDetailsEvent(((AppItemModel) adapterView.getItemAtPosition(i)).getModel()));
            }
        });
        if (i == 0) {
            this.featuredLayout.setOnItemClickListener(new LinearLayoutAdapterView.OnItemClickListener() {
                public void onClick(View view, int i) {
                    MfpPlatformApp model = ((FeaturedAppItemModel) AppsHomeFragment.this.featuredAdapter.getItem(i)).getModel();
                    ((ActionTrackingService) AppsHomeFragment.this.actionTrackingService.get()).registerAppendAndReportEvent(Events.TAP_FEATURED_PARTNERGALLERY, MapUtil.createMap(Attributes.APP_NAME, model.getName()));
                    AppsHomeFragment.this.getMessageBus().post(new NavigateToPartnerAppDetailsEvent(model));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void switchSearchView(boolean z) {
        this.viewPager.setCurrentItem(0);
        ViewUtils.setVisible(!z, this.headerContent);
        ViewUtils.setVisible(!z, this.categoryButton);
    }

    private void setupFilter() {
        this.categoryButton.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1) {
                    ((ActionTrackingService) AppsHomeFragment.this.actionTrackingService.get()).registerAndReportEvent(Events.VIEW_CATEGORY_SELECTOR_PARTNERGALLERY);
                    List<String> categories = AppsHomeFragment.this.viewModel.getCategories();
                    ArrayList arrayList = new ArrayList(CollectionUtils.size((Collection<?>) categories));
                    for (String dialogListTextItem : categories) {
                        arrayList.add(new DialogListTextItem(dialogListTextItem));
                    }
                    new MfpAlertDialogBuilder(AppsHomeFragment.this.getActivity()).setTitle((int) R.string.categories).setItems(arrayList, new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                            AppsHomeFragment.this.category = i;
                            String category = AppsHomeFragment.this.viewModel.getCategory(i);
                            ((ActionTrackingService) AppsHomeFragment.this.actionTrackingService.get()).registerAppendAndReportEvent(Events.SELECT_CATEGORY_PARTNERGALLERY, MapUtil.createMap(Attributes.CATEGORY_NAME, category));
                            AppsHomeFragment.this.viewModel.setCategoryAndFilter(i);
                        }
                    }).show();
                }
                return true;
            }
        });
    }

    @SuppressLint({"RestrictedApi"})
    private void instantiateViews(int i, ListView listView) {
        LayoutInflater from = LayoutInflater.from(getContext());
        if (i == 0) {
            if (this.headerView == null) {
                this.headerView = View.inflate(getActivity(), R.layout.partner_gallery_list_header, null);
                this.headerContent = ViewUtils.findById(this.headerView, R.id.featuredView);
                this.categoryButton = (Button) ViewUtils.findById(this.headerView, R.id.btnCategories);
                AppsHomeViewModel appsHomeViewModel = this.viewModel;
                if (appsHomeViewModel != null && CollectionUtils.notEmpty((Collection<?>) appsHomeViewModel.getCategories())) {
                    this.categoryButton.setText(this.viewModel.getCategory(this.category));
                }
                this.featuredLayout = (LinearLayoutListAdapterView) ViewUtils.findById(this.headerView, R.id.featuredLayout);
            }
            listView.addHeaderView(this.headerView);
            setupFilter();
            this.allAppsAdapter = new AppItemListAdapter<>(from, this.viewModel.filtered, R.layout.app_gallery_item);
            this.featuredAdapter = new AppItemListAdapter<>(from, this.viewModel.featured, R.layout.featured_app_gallery_item);
            listView.setAdapter(this.allAppsAdapter);
            this.featuredLayout.setAdapter(this.featuredAdapter);
            return;
        }
        this.connectedAppsAdapter = new AppItemListAdapter<>(from, this.viewModel.connected, R.layout.app_gallery_item);
        listView.setAdapter(this.connectedAppsAdapter);
    }

    public void onViewModelPropertyChanged(Observable observable, int i) {
        if (i == Property.CATEGORY) {
            TextViewUtils.setText(this.categoryButton, this.viewModel.getCategoryName());
        } else if (i == Property.LOAD_STATE) {
            setBusy(this.viewModel.isBusy());
            if (this.viewModel.getState() == State.Error) {
                getMessageBus().post(new AlertEvent(getString(R.string.failed_to_load_app_data)));
            }
            if (this.viewModel.getState() == State.Loaded) {
                TextViewUtils.setText(this.categoryButton, this.viewModel.getCategoryName());
            }
        } else if (i == Property.CONNECTED_LIST) {
            AppItemListAdapter<AppItemModel> appItemListAdapter = this.connectedAppsAdapter;
            if (appItemListAdapter != null) {
                appItemListAdapter.notifyDataSetChanged();
            }
        } else if (i == Property.FEATURED_LIST) {
            AppItemListAdapter<FeaturedAppItemModel> appItemListAdapter2 = this.featuredAdapter;
            if (appItemListAdapter2 != null) {
                appItemListAdapter2.notifyDataSetChanged();
            }
        } else if (i == Property.FILTERED_LIST) {
            AppItemListAdapter<AppItemModel> appItemListAdapter3 = this.allAppsAdapter;
            if (appItemListAdapter3 != null) {
                appItemListAdapter3.notifyDataSetChanged();
            }
        } else if (i == Property.ALL_LIST) {
            TextViewUtils.setText(this.categoryButton, this.viewModel.getCategoryName());
        }
    }

    public void onViewInstantiated(View view, int i) {
        ListView listView = (ListView) view;
        instantiateViews(i, listView);
        attachAppsEventListeners(listView, i);
    }

    public String getPageTitle(int i) {
        switch (i) {
            case 0:
                return getString(R.string.all);
            case 1:
                return getString(R.string.connected);
            default:
                return null;
        }
    }
}
