package com.myfitnesspal.shared.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.myfitnesspal.feature.search.ui.adapter.MultiSelectEnabledAdapter;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ViewUtils;
import java.util.LinkedHashMap;
import java.util.Map;

public class SeparatedListAdapter extends BaseAdapter {
    public static final int TYPE_SECTION_HEADER = 0;
    public final ArrayAdapter<String> headers;
    public final Map<String, Adapter> sections = new LinkedHashMap();

    private class HeaderAdapter extends ArrayAdapter<String> {
        private static final int TYPE_ID = 1010101;
        private Context context;
        private int resource;
        private int textViewResourceId;

        public int getItemViewType(int i) {
            return TYPE_ID;
        }

        public HeaderAdapter(Context context2, int i, int i2) {
            super(context2, i, i2);
            this.context = context2;
            this.resource = i;
            this.textViewResourceId = i2;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            String str = (String) getItem(i);
            if (view == null || view.getTag() == null || !(view.getTag() == null || ((Integer) view.getTag()).intValue() == getItemViewType(i))) {
                view = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(this.resource, viewGroup, false);
            }
            view.setTag(Integer.valueOf(getItemViewType(i)));
            ((TextView) ViewUtils.findById(view, this.textViewResourceId)).setText(str);
            return view;
        }
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public SeparatedListAdapter(Context context, int i, int i2) {
        this.headers = new HeaderAdapter(context, i, i2);
    }

    public void addSection(String str, Adapter adapter) {
        this.headers.add(str);
        this.sections.put(str, adapter);
    }

    public void clear() {
        this.sections.clear();
        this.headers.clear();
    }

    public Object getItem(int i) {
        for (Object next : this.sections.keySet()) {
            Adapter adapter = (Adapter) this.sections.get(next);
            int count = adapter.getCount() + 1;
            if (i == 0) {
                return next;
            }
            if (i < count) {
                return adapter.getItem(i - 1);
            }
            i -= count;
        }
        return null;
    }

    public int getCount() {
        int i = 0;
        for (Adapter count : this.sections.values()) {
            i += count.getCount() + 1;
        }
        return i;
    }

    public int getViewTypeCount() {
        int i = 1;
        for (Adapter viewTypeCount : this.sections.values()) {
            i += viewTypeCount.getViewTypeCount();
        }
        return i;
    }

    public int getItemViewType(int i) {
        try {
            int i2 = 1;
            for (Object obj : this.sections.keySet()) {
                Adapter adapter = (Adapter) this.sections.get(obj);
                int count = adapter.getCount() + 1;
                if (i == 0) {
                    return 0;
                }
                if (i < count) {
                    return i2 + adapter.getItemViewType(i - 1);
                }
                i -= count;
                i2 += adapter.getViewTypeCount();
            }
            return -1;
        } catch (Exception e) {
            Ln.e(e);
            return 0;
        }
    }

    public boolean isEnabled(int i) {
        return getItemViewType(i) != 0;
    }

    public void setMultiSelectMode(boolean z) {
        for (Adapter adapter : this.sections.values()) {
            if (adapter instanceof MultiSelectEnabledAdapter) {
                ((MultiSelectEnabledAdapter) adapter).setMultiSelectMode(z);
            }
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = 0;
        try {
            for (Object obj : this.sections.keySet()) {
                Adapter adapter = (Adapter) this.sections.get(obj);
                if (!adapter.isEmpty()) {
                    int count = adapter.getCount() + 1;
                    if (i == 0 && !adapter.isEmpty()) {
                        return this.headers.getView(i2, view, viewGroup);
                    }
                    if (i < count) {
                        return adapter.getView(i - 1, view, viewGroup);
                    }
                    i -= count;
                }
                i2++;
            }
            return null;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }
}
