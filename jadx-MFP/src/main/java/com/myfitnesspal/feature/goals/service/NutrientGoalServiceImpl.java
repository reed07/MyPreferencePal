package com.myfitnesspal.feature.goals.service;

import android.content.Context;
import android.os.Looper;
import android.os.Parcelable;
import com.myfitnesspal.feature.premium.service.PremiumFeature;
import com.myfitnesspal.feature.premium.service.PremiumService;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.api.ApiRequest;
import com.myfitnesspal.shared.api.ApiResponse;
import com.myfitnesspal.shared.api.request.MfpNutrientGoalPost;
import com.myfitnesspal.shared.api.v2.MfpV2Api;
import com.myfitnesspal.shared.constants.Constants.Uri;
import com.myfitnesspal.shared.constants.SharedConstants.Params;
import com.myfitnesspal.shared.db.adapter.NutrientGoalsServiceDbAdapter;
import com.myfitnesspal.shared.model.v2.MfpDailyGoal;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal;
import com.myfitnesspal.shared.model.v2.MfpNutrientGoal.API_RESPONSE_MAPPER;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.SyncV2ServiceBase;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.asyncservice.SimpleAsyncServiceBase.InvokeMode;
import com.uacf.core.constants.DateTime.Format;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ParcelableUtil;
import com.uacf.core.util.ReturningFunction2;
import com.uacf.core.util.Strings;
import com.uacf.sync.provider.sdk.model.SyncItem;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Provider;

public class NutrientGoalServiceImpl extends SyncV2ServiceBase<MfpNutrientGoal> implements NutrientGoalService {
    private static String API_DATE_FORAMT = Format.newIso8601DateFormat().toPattern();
    private static final int MAX_THREADS = 2;
    private static final String TAG = "NutrientGoalServiceImpl";
    private Provider<MfpV2Api> apiProvider;
    private NutrientGoalsServiceDbAdapter dbAdapter;
    private Lazy<NutrientGoalsUtil> nutrientGoalsUtil;
    /* access modifiers changed from: private */
    public Set<String> passiveBackgroundApiCalls = Collections.newSetFromMap(new ConcurrentHashMap());
    private Lazy<PremiumService> premiumService;
    private Lazy<Session> session;
    private MfpDailyGoal v2DefaultGoal;

    /* access modifiers changed from: protected */
    public int getMaxThreads() {
        return 2;
    }

    public String getSyncResourceName() {
        return "nutrient_goal";
    }

    /* access modifiers changed from: protected */
    public String getThreadName() {
        return TAG;
    }

    public NutrientGoalServiceImpl(Context context, Provider<MfpV2Api> provider, Lazy<Session> lazy, Lazy<PremiumService> lazy2, Lazy<NutrientGoalsUtil> lazy3) {
        this.apiProvider = provider;
        this.dbAdapter = new NutrientGoalsServiceDbAdapter(context, lazy);
        this.session = lazy;
        this.premiumService = lazy2;
        this.nutrientGoalsUtil = lazy3;
        updateDefaultGoalCache();
    }

    public Class<? extends Parcelable> getSyncItemClass() {
        return MfpNutrientGoal.class;
    }

    public void consumeSyncItems(List<SyncItem<MfpNutrientGoal>> list) {
        Enumerable.forEach((Collection<T>) list, (Function1<T>) new Function1<SyncItem<MfpNutrientGoal>>() {
            public void execute(SyncItem<MfpNutrientGoal> syncItem) {
                try {
                    NutrientGoalServiceImpl.this.writeNutrientGoalToDb((MfpNutrientGoal) syncItem.getItem());
                } catch (Exception e) {
                    Ln.d("nutrient_goals failed", e);
                }
            }
        });
    }

    public void setNutrientGoalAsync(final Function1<Boolean> function1, final Function1<List<Exception>> function12, final MfpNutrientGoal mfpNutrientGoal) {
        auto(new Runnable() {
            public void run() {
                try {
                    List access$100 = NutrientGoalServiceImpl.this.postNutrientGoalsOnCurrentThread(mfpNutrientGoal);
                    if (CollectionUtils.notEmpty((Collection<?>) access$100)) {
                        NutrientGoalServiceImpl.this.invokeOnMainThread(function12, access$100);
                    } else {
                        NutrientGoalServiceImpl.this.invokeOnMainThread(function1, Boolean.valueOf(true));
                    }
                } catch (Exception e) {
                    Ln.e("unexpected failure while syncing with backend! %s", e);
                    NutrientGoalServiceImpl.this.invokeOnMainThread(function12, null);
                }
            }
        });
    }

    public void setNutrientGoal(MfpNutrientGoal mfpNutrientGoal) throws Exception {
        List postNutrientGoalsOnCurrentThread = postNutrientGoalsOnCurrentThread(mfpNutrientGoal);
        if (CollectionUtils.notEmpty((Collection<?>) postNutrientGoalsOnCurrentThread)) {
            throw ((Exception) postNutrientGoalsOnCurrentThread.get(0));
        }
    }

    /* access modifiers changed from: private */
    public int writeNutrientGoalToDb(MfpNutrientGoal mfpNutrientGoal) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(mfpNutrientGoal);
        return writeNutrientGoalToDb((List<MfpNutrientGoal>) arrayList);
    }

    private int writeNutrientGoalToDb(List<MfpNutrientGoal> list) {
        int nutrientGoals = this.dbAdapter.setNutrientGoals(list);
        updateDefaultGoalCache();
        return nutrientGoals;
    }

    public MfpDailyGoal getDailyGoalForDayOfWeekSync(Date date) {
        MfpDailyGoal mfpDailyGoalFromDB = getMfpDailyGoalFromDB(date);
        if (mfpDailyGoalFromDB == null) {
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                getDailyGoalFromApiInBackground(date);
                return null;
            }
            Ln.e("no nutrient goal for specific date nutrient_goals table, will try via api", new Object[0]);
            try {
                pullNutrientGoalsFromApiOnCurrentThread(date, null);
                MfpNutrientGoal mfpNutrientGoalFromDB = getMfpNutrientGoalFromDB(date);
                if (mfpNutrientGoalFromDB != null) {
                    mfpDailyGoalFromDB = getMfpDailyGoalForDayOfWeek(mfpNutrientGoalFromDB, DateTimeUtils.getDayOfTheWeek(date));
                }
            } catch (Exception e) {
                Ln.e("unexpected failure while syncing with backend! %s", e);
            }
        }
        return mfpDailyGoalFromDB;
    }

    public void getDailyGoalForDate(final Function1<MfpDailyGoal> function1, final Function1<List<Exception>> function12, final Date date) {
        getNutrientGoal(new Function1<MfpNutrientGoal>() {
            public void execute(MfpNutrientGoal mfpNutrientGoal) {
                MfpDailyGoal mfpDailyGoalForDayOfWeek = NutrientGoalServiceImpl.this.getMfpDailyGoalForDayOfWeek(mfpNutrientGoal, DateTimeUtils.getDayOfTheWeek(date));
                if (mfpDailyGoalForDayOfWeek != null) {
                    NutrientGoalServiceImpl.this.invokeOnMainThread(function1, mfpDailyGoalForDayOfWeek);
                    return;
                }
                Ln.e("failed to get daily goal for date from table", new Object[0]);
                NutrientGoalServiceImpl.this.invokeOnMainThread(function12, null);
            }
        }, new Function1<List<Exception>>() {
            public void execute(List<Exception> list) {
                Ln.e("failed to get daily goal for date from table", new Object[0]);
                NutrientGoalServiceImpl.this.invokeOnMainThread(function12, list);
            }
        }, date, InvokeMode.Auto);
    }

    public void getDailyGoalForDayOfWeek(Function1<MfpDailyGoal> function1, Function1<List<Exception>> function12, Date date) {
        getDailyGoalForDayOfWeek(function1, function12, DateTimeUtils.getDayOfTheWeek(date), InvokeMode.Auto);
    }

    /* access modifiers changed from: private */
    public void getNutrientGoalsViaApi(final Function1<MfpNutrientGoal> function1, final Function1<List<Exception>> function12, final Date date) {
        if (date == null) {
            date = Calendar.getInstance().getTime();
        }
        auto(new Runnable() {
            public void run() {
                try {
                    NutrientGoalServiceImpl.this.pullNutrientGoalsFromApiOnCurrentThread();
                    MfpNutrientGoal mfpNutrientGoalFromDB = NutrientGoalServiceImpl.this.getMfpNutrientGoalFromDB(date);
                    if (mfpNutrientGoalFromDB != null) {
                        NutrientGoalServiceImpl.this.invokeOnMainThread(function1, mfpNutrientGoalFromDB);
                    } else {
                        NutrientGoalServiceImpl.this.invokeOnMainThread(function12, null);
                    }
                } catch (ApiException e) {
                    try {
                        NutrientGoalServiceImpl.this.invokeOnMainThread(function12, Arrays.asList(new Exception[]{e}));
                    } catch (Exception e2) {
                        Ln.e("unexpected failure while syncing with backend! %s", e2);
                        NutrientGoalServiceImpl.this.invokeOnMainThread(function12, null);
                    }
                }
            }
        });
    }

    public void getNutrientGoal(Function1<MfpNutrientGoal> function1, Function1<List<Exception>> function12) {
        getNutrientGoal(function1, function12, Calendar.getInstance().getTime());
    }

    public void getNutrientGoal(Function1<MfpNutrientGoal> function1, Function1<List<Exception>> function12, Date date) {
        getNutrientGoal(function1, function12, date, InvokeMode.Auto);
    }

    public MfpNutrientGoal getNutrientGoal(Date date) throws Exception {
        return getNutrientGoalOnCurrentThread(date);
    }

    public MfpDailyGoal getCachedDefaultGoal() {
        MfpDailyGoal mfpDailyGoal = this.v2DefaultGoal;
        if (mfpDailyGoal != null) {
            return (MfpDailyGoal) ParcelableUtil.clone(mfpDailyGoal, MfpDailyGoal.CREATOR);
        }
        Ln.e("warning! getCachedDefaultGoal() not available for V2! falling back to V1", new Object[0]);
        return ((Session) this.session.get()).getUser().getUserV1NutrientGoals().createMfpDailyGoalFromV1Values();
    }

    private void getDailyGoalFromApiInBackground(final Date date) {
        final String format = String.format("%d-%d-%d", new Object[]{Integer.valueOf(date.getYear()), Integer.valueOf(date.getMonth()), Integer.valueOf(date.getDay())});
        if (!this.passiveBackgroundApiCalls.contains(format)) {
            this.passiveBackgroundApiCalls.add(format);
            async(new Runnable() {
                public void run() {
                    try {
                        NutrientGoalServiceImpl.this.pullNutrientGoalsFromApiOnCurrentThread(date, null);
                    } catch (ApiException unused) {
                    } catch (Throwable th) {
                        NutrientGoalServiceImpl.this.passiveBackgroundApiCalls.remove(format);
                        throw th;
                    }
                    NutrientGoalServiceImpl.this.passiveBackgroundApiCalls.remove(format);
                }
            });
        }
    }

    private void getNutrientGoal(Function1<MfpNutrientGoal> function1, Function1<List<Exception>> function12, InvokeMode invokeMode) {
        getNutrientGoal(function1, function12, Calendar.getInstance().getTime(), invokeMode);
    }

    private void getNutrientGoal(final Function1<MfpNutrientGoal> function1, final Function1<List<Exception>> function12, final Date date, InvokeMode invokeMode) {
        invoke(new Runnable() {
            public void run() {
                try {
                    MfpNutrientGoal mfpNutrientGoalFromDB = NutrientGoalServiceImpl.this.getMfpNutrientGoalFromDB(date);
                    if (mfpNutrientGoalFromDB != null) {
                        NutrientGoalServiceImpl.this.invokeOnMainThread(function1, mfpNutrientGoalFromDB);
                        return;
                    }
                    Ln.e("no nutrient goal for specific date nutrient_goals table, will try via api", new Object[0]);
                    NutrientGoalServiceImpl.this.getNutrientGoalsViaApi(function1, function12, date);
                } catch (Exception e) {
                    Ln.e("unexpected failure while query nutrient_goals table %s", e);
                    NutrientGoalServiceImpl.this.invokeOnMainThread(function12, null);
                }
            }
        }, invokeMode);
    }

    private MfpNutrientGoal getNutrientGoalOnCurrentThread(Date date) throws Exception {
        MfpNutrientGoal mfpNutrientGoalFromDB = getMfpNutrientGoalFromDB(date);
        if (mfpNutrientGoalFromDB == null) {
            Ln.e("no nutrient goal for specific date nutrient_goals table, will try via api", new Object[0]);
            pullNutrientGoalsFromApiOnCurrentThread();
            mfpNutrientGoalFromDB = getMfpNutrientGoalFromDB(date);
        }
        if (mfpNutrientGoalFromDB != null) {
            return mfpNutrientGoalFromDB;
        }
        throw new Exception("unable to fetch nutrient goal from DB or backend!");
    }

    private void getDailyGoalForDayOfWeek(final Function1<MfpDailyGoal> function1, final Function1<List<Exception>> function12, final String str, InvokeMode invokeMode) {
        getNutrientGoal((Function1<MfpNutrientGoal>) new Function1<MfpNutrientGoal>() {
            public void execute(MfpNutrientGoal mfpNutrientGoal) throws RuntimeException {
                MfpDailyGoal mfpDailyGoalForDayOfWeek = NutrientGoalServiceImpl.this.getMfpDailyGoalForDayOfWeek(mfpNutrientGoal, str);
                if (mfpDailyGoalForDayOfWeek != null) {
                    NutrientGoalServiceImpl.this.invokeOnMainThread(function1, mfpDailyGoalForDayOfWeek);
                    return;
                }
                Ln.e("failed to get daily goal for a day of the week from table", new Object[0]);
                NutrientGoalServiceImpl.this.invokeOnMainThread(function12, null);
            }
        }, (Function1<List<Exception>>) new Function1<List<Exception>>() {
            public void execute(List<Exception> list) throws RuntimeException {
                Ln.e("failed to get daily goal for a day of the week from table", new Object[0]);
                NutrientGoalServiceImpl.this.invokeOnMainThread(function12, list);
            }
        }, invokeMode);
    }

    /* access modifiers changed from: private */
    public void pullNutrientGoalsFromApiOnCurrentThread() throws ApiException {
        pullNutrientGoalsFromApiOnCurrentThread(null, null);
    }

    /* access modifiers changed from: private */
    public void pullNutrientGoalsFromApiOnCurrentThread(Date date, Date date2) throws ApiException {
        if (((Session) this.session.get()).getUser().isLoggedIn()) {
            Object[] objArr = null;
            if (Strings.notEmpty((Object) date) && Strings.notEmpty((Object) date2)) {
                objArr = new Object[]{Params.FROM, DateTimeUtils.format(API_DATE_FORAMT, date), "to", DateTimeUtils.format(API_DATE_FORAMT, date2)};
            } else if (Strings.notEmpty((Object) date)) {
                objArr = new Object[]{"date", DateTimeUtils.format(API_DATE_FORAMT, date)};
            }
            List items = ((ApiResponse) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withOutputType(API_RESPONSE_MAPPER.class)).get(Uri.NUTRIENT_GOALS, objArr)).getItems();
            if (CollectionUtils.notEmpty((Collection<?>) items)) {
                this.dbAdapter.setNutrientGoals(items);
                updateDefaultGoalCache();
            }
        }
    }

    /* access modifiers changed from: private */
    public List<Exception> postNutrientGoalsOnCurrentThread(MfpNutrientGoal mfpNutrientGoal) {
        ArrayList arrayList = new ArrayList();
        if (!((Session) this.session.get()).getUser().isLoggedIn() || mfpNutrientGoal == null) {
            arrayList.add(new Exception("trying to POST empty nutrient goal or user not logged in"));
            Ln.e("failed to POST nutrient goal.", new Object[0]);
            return arrayList;
        }
        try {
            MfpNutrientGoal mfpNutrientGoal2 = (MfpNutrientGoal) ((ApiResponse) ((MfpV2Api) ((MfpV2Api) ((MfpV2Api) this.apiProvider.get()).withJsonBody(new ApiRequest(new MfpNutrientGoalPost(replaceDailyGoalIfUserNotPremium(mfpNutrientGoal))))).withOutputType(API_RESPONSE_MAPPER.class)).post(Uri.NUTRIENT_GOALS, new Object[0])).getItem();
            if (mfpNutrientGoal2 != null) {
                this.dbAdapter.setNutrientGoal(mfpNutrientGoal2);
                updateDefaultGoalCache();
            }
        } catch (ApiException e) {
            arrayList.add(e);
            Ln.e("failed to POST nutrient goal.", new Object[0]);
        }
        return arrayList;
    }

    private MfpNutrientGoal replaceDailyGoalIfUserNotPremium(MfpNutrientGoal mfpNutrientGoal) {
        if (((PremiumService) this.premiumService.get()).isFeatureSubscribed(PremiumFeature.CustomDailyGoals)) {
            return mfpNutrientGoal;
        }
        return ((NutrientGoalsUtil) this.nutrientGoalsUtil.get()).transformDailyGoals(mfpNutrientGoal, new ReturningFunction2<MfpDailyGoal, MfpDailyGoal, MfpDailyGoal>() {
            public MfpDailyGoal execute(MfpDailyGoal mfpDailyGoal, MfpDailyGoal mfpDailyGoal2) throws RuntimeException {
                MfpDailyGoal mfpDailyGoal3 = (MfpDailyGoal) ParcelableUtil.clone(mfpDailyGoal, MfpDailyGoal.CREATOR);
                mfpDailyGoal3.setDayOfWeek(mfpDailyGoal2.getDayOfWeek());
                mfpDailyGoal3.setGroupId(mfpDailyGoal2.getGroupId());
                return mfpDailyGoal3;
            }
        });
    }

    public MfpDailyGoal getMfpDailyGoalForDayOfWeek(MfpNutrientGoal mfpNutrientGoal, String str) {
        if (mfpNutrientGoal != null && CollectionUtils.notEmpty((Collection<?>) mfpNutrientGoal.getDailyGoals())) {
            for (MfpDailyGoal mfpDailyGoal : mfpNutrientGoal.getDailyGoals()) {
                if (Strings.equalsIgnoreCase(mfpDailyGoal.getDayOfWeek(), str)) {
                    return mfpDailyGoal;
                }
            }
        }
        return null;
    }

    public MfpNutrientGoal getMfpNutrientGoalFromDB(Date date) {
        return this.dbAdapter.getNutrientGoal(date);
    }

    public MfpDailyGoal getMfpDailyGoalFromDB(Date date) {
        return getMfpDailyGoalForDayOfWeek(getMfpNutrientGoalFromDB(date), DateTimeUtils.getDayOfTheWeek(date));
    }

    private void updateDefaultGoalCache() {
        MfpNutrientGoal mfpNutrientGoalFromDB = getMfpNutrientGoalFromDB(new Date());
        if (mfpNutrientGoalFromDB != null && mfpNutrientGoalFromDB.getDefaultGoal() != null) {
            this.v2DefaultGoal = mfpNutrientGoalFromDB.getDefaultGoal();
            ((Session) this.session.get()).getUser().getUserV1NutrientGoals().updateDefaultGoal(mfpNutrientGoalFromDB);
        }
    }
}
