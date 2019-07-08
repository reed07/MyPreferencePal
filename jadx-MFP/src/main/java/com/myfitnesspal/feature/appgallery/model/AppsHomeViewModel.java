package com.myfitnesspal.feature.appgallery.model;

import android.content.Context;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.appgallery.service.AppGalleryService;
import com.myfitnesspal.feature.appgallery.service.QueryAppListTask;
import com.myfitnesspal.feature.appgallery.util.AppGalleryUtil;
import com.myfitnesspal.feature.appgallery.util.AppStateUtil;
import com.myfitnesspal.feature.externalsync.impl.googlefit.client.GoogleFitClient;
import com.myfitnesspal.feature.externalsync.impl.googlefit.mixin.GoogleFitMixin;
import com.myfitnesspal.feature.externalsync.impl.shealth.mixin.SHealthMixin;
import com.myfitnesspal.feature.externalsync.impl.shealth.service.SHealthConnection;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelLifecycle;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.v2.MfpPlatformApp;
import com.myfitnesspal.shared.model.v2.MfpPlatformAppOptions.AppType;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class AppsHomeViewModel extends RunnerViewModel<Void> implements ViewModelLifecycle {
    public static final int NO_CATEGORY = 0;
    private List<MfpPlatformApp> all = new ArrayList();
    private Lazy<AppGalleryService> appGalleryService;
    private List<String> categories = new ArrayList();
    private int categoryId = 0;
    public final List<AppItemModel> connected = new ArrayList();
    private Context context;
    public final List<FeaturedAppItemModel> featured = new ArrayList();
    private String filter = "";
    public final List<AppItemModel> filtered = new ArrayList();
    private final GoogleFitClient googleFit;
    private final SHealthConnection shealth;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int ALL_LIST = ViewModelPropertyId.next();
        public static final int CATEGORY = ViewModelPropertyId.next();
        public static final int CONNECTED_LIST = ViewModelPropertyId.next();
        public static final int FEATURED_LIST = ViewModelPropertyId.next();
        public static final int FILTER = ViewModelPropertyId.next();
        public static final int FILTERED_LIST = ViewModelPropertyId.next();
    }

    public void onPause() {
    }

    public AppsHomeViewModel(MfpActivity mfpActivity, Runner runner, Lazy<AppGalleryService> lazy) {
        super(runner);
        this.googleFit = ((GoogleFitMixin) mfpActivity.mixin(GoogleFitMixin.class)).getClient();
        this.shealth = ((SHealthMixin) mfpActivity.mixin(SHealthMixin.class)).getConnection();
        this.context = mfpActivity.getApplicationContext();
        this.appGalleryService = lazy;
    }

    public void onResume() {
        refreshConnected();
    }

    public void load(Void... voidArr) {
        setState(State.Loading);
        new QueryAppListTask("all", this.appGalleryService).run(getRunner());
        new QueryAppListTask(AppType.FEATURED, this.appGalleryService).run(getRunner());
    }

    public void resetCategory() {
        setCategoryAndFilter(0);
    }

    public void setCategoryAndFilter(int i) {
        this.categoryId = i;
        filter();
        notifyPropertyChanged(Property.CATEGORY);
    }

    public void setCategory(int i) {
        this.categoryId = i;
    }

    public void setFilter(String str) {
        setCategoryAndFilter(0);
        if (!Strings.equals(this.filter, str)) {
            this.filter = str;
            filter();
            notifyPropertyChanged(Property.FILTER);
        }
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public String getCategory(int i) {
        return (String) this.categories.get(i);
    }

    public String getFilter() {
        return this.filter;
    }

    public boolean isFiltered() {
        return Strings.notEmpty(this.filter);
    }

    public boolean isBusy() {
        return getState() == State.Loading;
    }

    public String getCategoryName() {
        int i = this.categoryId;
        if (i == 0) {
            return this.context.getString(R.string.all_apps);
        }
        return (String) this.categories.get(i);
    }

    private List<AppItemModel> filterConnected(List<MfpPlatformApp> list) {
        ArrayList arrayList = new ArrayList();
        for (MfpPlatformApp mfpPlatformApp : list) {
            if (mfpPlatformApp.hasUserConnected() || AppGalleryUtil.isGoogleFitAndConnected(mfpPlatformApp, this.googleFit) || AppGalleryUtil.isSHealthAndConnected(mfpPlatformApp, this.shealth)) {
                arrayList.add(new AppItemModel(mfpPlatformApp));
            }
        }
        Collections.sort(arrayList, new Comparator<AppItemModel>() {
            public int compare(AppItemModel appItemModel, AppItemModel appItemModel2) {
                return Strings.toString(appItemModel.getName()).compareTo(Strings.toString(appItemModel2.getName()));
            }
        });
        return arrayList;
    }

    private List<AppItemModel> filterByCategoryAndFilter(List<MfpPlatformApp> list, int i, String str) {
        ArrayList arrayList = new ArrayList();
        if (CollectionUtils.notEmpty((Collection<?>) list) && CollectionUtils.notEmpty((Collection<?>) this.categories)) {
            String str2 = (String) this.categories.get(i);
            if (CollectionUtils.notEmpty((Collection<?>) list)) {
                for (MfpPlatformApp mfpPlatformApp : list) {
                    if (i == 0 || mfpPlatformApp.getAppCategories().contains(str2)) {
                        String lowerCase = Strings.toString(mfpPlatformApp.getName()).toLowerCase();
                        if (Strings.isEmpty(str) || lowerCase.contains(str)) {
                            arrayList.add(new AppItemModel(mfpPlatformApp));
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    private static List<FeaturedAppItemModel> createFeaturedList(List<MfpPlatformApp> list) {
        return Enumerable.select((Collection<T>) list, (ReturningFunction1<U, T>) new ReturningFunction1<FeaturedAppItemModel, MfpPlatformApp>() {
            public FeaturedAppItemModel execute(MfpPlatformApp mfpPlatformApp) throws RuntimeException {
                return new FeaturedAppItemModel(mfpPlatformApp);
            }
        });
    }

    private static <T> void set(List<T> list, List<T> list2) {
        list.clear();
        list.addAll(list2);
    }

    private void refreshConnected() {
        set(this.connected, filterConnected(this.all));
        notifyPropertyChanged(Property.CONNECTED_LIST);
    }

    private void filter() {
        set(this.filtered, filterByCategoryAndFilter(this.all, this.categoryId, this.filter));
        notifyPropertyChanged(Property.FILTERED_LIST);
    }

    private List<MfpPlatformApp> filterNonConnectableDevices(List<MfpPlatformApp> list) {
        return (List) Enumerable.where((Collection<T>) list, (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, MfpPlatformApp>() {
            public Boolean execute(MfpPlatformApp mfpPlatformApp) throws RuntimeException {
                return Boolean.valueOf(AppStateUtil.isConnectable(mfpPlatformApp) || AppGalleryUtil.isGoogleFit(mfpPlatformApp) || AppGalleryUtil.isSHealth(mfpPlatformApp));
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner()) && QueryAppListTask.matches(taskDetails)) {
            if (taskDetails.getFailure() == null) {
                List filterNonConnectableDevices = filterNonConnectableDevices((List) taskDetails.getResult());
                if (taskDetails.getTaskName().contains("all")) {
                    this.all = filterNonConnectableDevices(filterNonConnectableDevices);
                    createCategoryList(this.all);
                    filter();
                    refreshConnected();
                    notifyPropertyChanged(Property.ALL_LIST);
                } else if (taskDetails.getTaskName().contains(AppType.FEATURED)) {
                    set(this.featured, createFeaturedList(filterNonConnectableDevices));
                    notifyPropertyChanged(Property.FEATURED_LIST);
                }
                if (CollectionUtils.notEmpty((Collection<?>) this.all) && CollectionUtils.notEmpty((Collection<?>) this.featured)) {
                    setState(State.Loaded);
                    return;
                }
                return;
            }
            setError(taskDetails.getFailure());
        }
    }

    private void createCategoryList(List<MfpPlatformApp> list) {
        this.categories = new ArrayList();
        this.categories.add(this.context.getString(R.string.all_apps));
        for (MfpPlatformApp appCategories : list) {
            Iterator it = appCategories.getAppCategories().iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!this.categories.contains(str)) {
                    this.categories.add(str);
                }
            }
        }
    }
}
