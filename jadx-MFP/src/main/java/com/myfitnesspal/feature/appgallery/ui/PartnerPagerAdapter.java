package com.myfitnesspal.feature.appgallery.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.util.MaterialUtils;

public class PartnerPagerAdapter extends PagerAdapter {
    private OnViewInstantatedListener listener;
    private Context mContext;

    interface OnViewInstantatedListener {
        int getPageCount();

        String getPageTitle(int i);

        void onViewInstantiated(View view, int i);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public PartnerPagerAdapter(Context context, OnViewInstantatedListener onViewInstantatedListener) {
        this.mContext = context;
        this.listener = onViewInstantatedListener;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ListView listView = (ListView) LayoutInflater.from(this.mContext).inflate(R.layout.app_gallery_list_view, viewGroup, false);
        this.listener.onViewInstantiated(listView, i);
        MaterialUtils.enableListViewNestedScrolling(listView);
        viewGroup.addView(listView);
        return listView;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public int getCount() {
        return this.listener.getPageCount();
    }

    public CharSequence getPageTitle(int i) {
        return this.listener.getPageTitle(i);
    }
}
