package com.myfitnesspal.shared.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import butterknife.BindView;
import com.myfitnesspal.feature.drawer.event.DrawerCloseEvent;
import com.myfitnesspal.feature.drawer.event.PreDrawerOpenEvent;
import com.myfitnesspal.shared.ui.fragment.MfpEditableFragmentBase;
import com.myfitnesspal.shared.util.MaterialUtils;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class MfpPagedEditableActivity extends MfpActivity {
    protected EditablePagerAdapter adapter;
    @BindView(2131362804)
    TabLayout indicator;
    @BindView(2131363233)
    ViewPager pager;

    public static abstract class EditablePagerAdapter extends FragmentPagerAdapter {
        private final Context context;
        private List<FragmentEntry> fragments;

        public static class FragmentEntry {
            /* access modifiers changed from: private */
            public final MfpEditableFragmentBase fragment;
            /* access modifiers changed from: private */
            public final int stringId;
            /* access modifiers changed from: private */
            public String title;

            public FragmentEntry(MfpEditableFragmentBase mfpEditableFragmentBase, int i) {
                this.fragment = mfpEditableFragmentBase;
                this.stringId = i;
                this.title = null;
            }

            public FragmentEntry(MfpEditableFragmentBase mfpEditableFragmentBase, String str) {
                this.fragment = mfpEditableFragmentBase;
                this.stringId = 0;
                this.title = str;
            }

            public MfpEditableFragmentBase getFragment() {
                return this.fragment;
            }
        }

        /* access modifiers changed from: protected */
        public abstract List<FragmentEntry> createFragmentList();

        public EditablePagerAdapter(FragmentManager fragmentManager, Context context2) {
            super(fragmentManager);
            this.context = context2;
        }

        public List<FragmentEntry> getFragments() {
            if (this.fragments == null) {
                this.fragments = new ArrayList(createFragmentList());
            }
            return this.fragments;
        }

        public MfpEditableFragmentBase getItem(int i) {
            return ((FragmentEntry) getFragments().get(i)).fragment;
        }

        public int getCount() {
            return CollectionUtils.size((Collection<?>) getFragments());
        }

        public CharSequence getPageTitle(int i) {
            FragmentEntry fragmentEntry = (FragmentEntry) getFragments().get(i);
            if (Strings.notEmpty(fragmentEntry.title)) {
                return fragmentEntry.title;
            }
            return fragmentEntry.stringId > 0 ? this.context.getString(fragmentEntry.stringId) : "????";
        }
    }

    /* access modifiers changed from: protected */
    public abstract int getContentViewId();

    /* access modifiers changed from: protected */
    public abstract EditablePagerAdapter recreateAdapter();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(getContentViewId());
        MaterialUtils.removeDefaultToolbarElevation(this);
        this.adapter = recreateAdapter();
        this.pager.setAdapter(this.adapter);
        this.indicator.setupWithViewPager(this.pager);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        for (int i = 0; i < this.adapter.getCount(); i++) {
            this.adapter.getItem(i).refresh();
        }
    }

    public ViewPager getPager() {
        return this.pager;
    }

    @Subscribe
    public void onPreDrawerOpenEvent(PreDrawerOpenEvent preDrawerOpenEvent) {
        for (int i = 0; i < this.adapter.getCount(); i++) {
            this.adapter.getItem(i).hideMenuItems(true);
        }
        supportInvalidateOptionsMenu();
    }

    @Subscribe
    public void onPostDrawerCloseEvent(DrawerCloseEvent drawerCloseEvent) {
        for (int i = 0; i < this.adapter.getCount(); i++) {
            this.adapter.getItem(i).hideMenuItems(false);
        }
        supportInvalidateOptionsMenu();
    }
}
