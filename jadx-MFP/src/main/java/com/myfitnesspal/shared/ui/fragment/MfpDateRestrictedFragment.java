package com.myfitnesspal.shared.ui.fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.myfitnesspal.shared.event.DialogCalendarEvent;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.service.syncv2.SyncType;
import com.myfitnesspal.shared.ui.activity.BusEventHandler;
import com.myfitnesspal.shared.ui.fragment.impl.DatePickerFragment;
import com.myfitnesspal.shared.util.CalendarUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.sync.engine.UacfScheduleFinishedInfo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public abstract class MfpDateRestrictedFragment extends MfpFragment {
    public static final String CURRENT_ITEM = "current_item";
    public static final String DATE_LIST = "date_list";
    protected Date activeDate;
    /* access modifiers changed from: protected */
    @Nullable
    @BindView(2131362213)
    public ViewPager contentPager;
    protected PagerAdapter contentPagerAdapter;
    @Nullable
    @BindView(2131361991)
    protected TextView date;
    /* access modifiers changed from: protected */
    public List<Calendar> dateList = new ArrayList();
    /* access modifiers changed from: protected */
    public int mCurrentSelection;
    @Nullable
    @BindView(2131361999)
    protected View next;
    protected OnPageChangeListener onContentPageChangeListener = new OnPageChangeListener() {
        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            MfpDateRestrictedFragment mfpDateRestrictedFragment = MfpDateRestrictedFragment.this;
            mfpDateRestrictedFragment.setActiveDateAndUpdateUI((Calendar) mfpDateRestrictedFragment.dateList.get(i));
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                int currentItem = MfpDateRestrictedFragment.this.contentPager.getCurrentItem();
                if (currentItem <= 2) {
                    MfpDateRestrictedFragment.this.addListOfDatesBefore();
                } else if (currentItem >= MfpDateRestrictedFragment.this.dateList.size() - 2) {
                    MfpDateRestrictedFragment.this.addListOfDatesAfter();
                }
            }
        }
    };
    private int persistedSelection = -1;
    @Nullable
    @BindView(2131362002)
    protected View previous;

    private static class BusEventHelper implements BusEventHandler {
        private final MfpDateRestrictedFragment parent;

        public BusEventHelper(MfpDateRestrictedFragment mfpDateRestrictedFragment) {
            this.parent = mfpDateRestrictedFragment;
        }

        @Subscribe
        public void onSyncFinished(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
            this.parent.onSyncFinished(uacfScheduleFinishedInfo);
        }

        @Subscribe
        public void onDialogCalendarEvent(DialogCalendarEvent dialogCalendarEvent) {
            this.parent.onDialogCalendarEvent(dialogCalendarEvent);
        }
    }

    public class ContentPagerAdapter extends PagerAdapter {
        public int getItemPosition(Object obj) {
            return -2;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public ContentPagerAdapter() {
        }

        public int getCount() {
            return MfpDateRestrictedFragment.this.getCount();
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            MfpDateRestrictedFragment.this.destroyPageItem(viewGroup, i, obj);
            viewGroup.removeView((View) obj);
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View renderPageView = MfpDateRestrictedFragment.this.renderPageView(viewGroup, i);
            viewGroup.addView(renderPageView);
            return renderPageView;
        }
    }

    /* access modifiers changed from: protected */
    public void destroyPageItem(ViewGroup viewGroup, int i, Object obj) {
    }

    public abstract boolean isWeekly();

    /* access modifiers changed from: protected */
    public abstract void loadDate(Calendar calendar);

    public abstract void onContentPagerCreated();

    /* access modifiers changed from: protected */
    public abstract View renderPageView(ViewGroup viewGroup, int i);

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (bundle == null || !bundle.containsKey(DATE_LIST)) {
            initialiseDates();
        } else {
            getPositionAndDatesFromPersistedArray(bundle);
        }
        setDateActionListeners();
        ViewPager viewPager = this.contentPager;
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(this.onContentPageChangeListener);
        }
    }

    public void addBusEventHandlers(List<BusEventHandler> list) {
        super.addBusEventHandlers(list);
        list.add(new BusEventHelper(this));
    }

    public void onResume() {
        super.onResume();
        ViewPager viewPager = this.contentPager;
        if (viewPager != null) {
            setActiveDateAndUpdateUI((Calendar) this.dateList.get(viewPager.getCurrentItem()));
        }
    }

    private void initialiseDates() {
        this.dateList = new ArrayList(11);
        Calendar instance = Calendar.getInstance();
        Date date2 = this.activeDate;
        if (date2 == null) {
            date2 = getCurrentUser().getActiveDate();
        }
        instance.setTime(date2);
        refreshDatesList(instance);
    }

    private void getPositionAndDatesFromPersistedArray(Bundle bundle) {
        long[] longArray = bundle.getLongArray(DATE_LIST);
        ArrayList arrayList = new ArrayList();
        for (long j : longArray) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(j);
            arrayList.add(instance);
        }
        this.dateList = arrayList;
        this.persistedSelection = bundle.getInt(CURRENT_ITEM);
    }

    private void writeDateListAndPositionToArray(Bundle bundle) {
        long[] jArr = new long[this.dateList.size()];
        int i = 0;
        int i2 = 0;
        for (Calendar timeInMillis : this.dateList) {
            int i3 = i2 + 1;
            jArr[i2] = timeInMillis.getTimeInMillis();
            i2 = i3;
        }
        bundle.putLongArray(DATE_LIST, jArr);
        String str = CURRENT_ITEM;
        ViewPager viewPager = this.contentPager;
        if (viewPager != null) {
            i = viewPager.getCurrentItem();
        }
        bundle.putInt(str, i);
    }

    private User getCurrentUser() {
        return getSession().getUser();
    }

    /* access modifiers changed from: protected */
    public void refreshDatesList(Calendar calendar) {
        this.dateList.clear();
        this.dateList.addAll(CalendarUtils.fetchDates(0, calendar, 11, isWeekly()));
    }

    private void setDateActionListeners() {
        View view = this.previous;
        if (view != null) {
            view.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MfpDateRestrictedFragment.this.goToPreviousPage();
                }
            });
        }
        View view2 = this.next;
        if (view2 != null) {
            view2.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MfpDateRestrictedFragment.this.goToNextPage();
                }
            });
        }
        TextView textView = this.date;
        if (textView != null) {
            textView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    MfpDateRestrictedFragment.this.showDateDialog(null);
                }
            });
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        recreateDateDialog();
    }

    private void recreateDateDialog() {
        DialogFragment dialogFragment = (DialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag(DatePickerFragment.class.getName());
        if (dialogFragment != null) {
            dialogFragment.dismiss();
            showDateDialog(((DatePickerFragment) dialogFragment).getSelectedDate());
        }
    }

    /* access modifiers changed from: private */
    public void showDateDialog(Calendar calendar) {
        if (calendar == null) {
            calendar = (Calendar) this.dateList.get(this.contentPager.getCurrentItem());
        }
        DatePickerFragment.newInstance(calendar).show(getActivity().getSupportFragmentManager(), DatePickerFragment.class.getName());
    }

    public void onDialogCalendarEvent(DialogCalendarEvent dialogCalendarEvent) {
        refreshContentToDate(dialogCalendarEvent.getCalendar());
    }

    /* access modifiers changed from: protected */
    public void refreshContentToDate(Calendar calendar) {
        refreshDatesList(calendar);
        this.contentPagerAdapter.notifyDataSetChanged();
        setContentPage(5, false);
        setActiveDateAndUpdateUI(calendar);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        writeDateListAndPositionToArray(bundle);
    }

    /* access modifiers changed from: private */
    public void addListOfDatesBefore() {
        int currentItem = this.contentPager.getCurrentItem();
        List<Calendar> list = this.dateList;
        list.addAll(0, CalendarUtils.fetchDates(1, (Calendar) list.get(0), 5, isWeekly()));
        this.contentPagerAdapter.notifyDataSetChanged();
        setContentPage(currentItem + 5, false);
    }

    /* access modifiers changed from: private */
    public void addListOfDatesAfter() {
        List<Calendar> list = this.dateList;
        list.addAll(CalendarUtils.fetchDates(2, (Calendar) list.get(list.size() - 1), 5, isWeekly()));
        this.contentPagerAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void goToNextPage() {
        setContentPage(this.contentPager.getCurrentItem() + 1);
    }

    /* access modifiers changed from: protected */
    public void goToPreviousPage() {
        setContentPage(this.contentPager.getCurrentItem() - 1);
    }

    /* access modifiers changed from: protected */
    public void setContentPage(int i) {
        setContentPage(i, true);
    }

    /* access modifiers changed from: protected */
    public void setContentPage(int i, boolean z) {
        this.mCurrentSelection = i;
        this.contentPager.setCurrentItem(i, z);
    }

    /* access modifiers changed from: protected */
    public void setContentPageToDefaultOrPersisted() {
        int i = this.persistedSelection;
        if (i == -1) {
            i = 5;
        }
        setContentPage(i, false);
        this.persistedSelection = -1;
    }

    /* access modifiers changed from: protected */
    public Calendar getCurrentVisibleDate() {
        return (Calendar) this.dateList.get(this.contentPager.getCurrentItem());
    }

    /* access modifiers changed from: protected */
    public void setActiveDateAndUpdateUI(Calendar calendar) {
        getSession().getUser().setActiveDate(calendar.getTime());
        loadDate(calendar);
    }

    /* access modifiers changed from: protected */
    public int getCount() {
        return CollectionUtils.size((Collection<?>) this.dateList);
    }

    /* access modifiers changed from: protected */
    public void onSyncFinished(UacfScheduleFinishedInfo uacfScheduleFinishedInfo) {
        if (this.contentPagerAdapter != null && uacfScheduleFinishedInfo.getScheduleGroup() == SyncType.Incremental) {
            this.contentPagerAdapter.notifyDataSetChanged();
        }
    }
}
