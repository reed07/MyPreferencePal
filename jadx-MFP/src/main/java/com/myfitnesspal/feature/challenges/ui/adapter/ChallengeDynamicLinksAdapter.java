package com.myfitnesspal.feature.challenges.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.challenges.model.ChallengeDynamicTab;
import com.uacf.core.util.ViewUtils;
import java.util.List;

public class ChallengeDynamicLinksAdapter extends BaseAdapter {
    private List<ChallengeDynamicTab> dynamicLinks;
    private LayoutInflater inflater;
    private int layoutId;

    static class ViewHolder {
        @BindView(2131363954)
        TextView name;
        @BindView(2131363599)
        View separator;

        public ViewHolder(View view) {
            ButterKnife.bind((Object) this, view);
        }

        /* access modifiers changed from: 0000 */
        public void bind(ChallengeDynamicTab challengeDynamicTab) {
            ViewUtils.setVisible(true, this.separator);
            this.name.setText(challengeDynamicTab.getTitle());
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        @UiThread
        public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
            this.target = viewHolder;
            viewHolder.name = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_dynamic_link, "field 'name'", TextView.class);
            viewHolder.separator = Utils.findRequiredView(view, R.id.separator, "field 'separator'");
        }

        @CallSuper
        public void unbind() {
            ViewHolder viewHolder = this.target;
            if (viewHolder != null) {
                this.target = null;
                viewHolder.name = null;
                viewHolder.separator = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public ChallengeDynamicLinksAdapter(LayoutInflater layoutInflater, List<ChallengeDynamicTab> list, int i) {
        this.inflater = layoutInflater;
        this.dynamicLinks = list;
        this.layoutId = i;
    }

    public int getCount() {
        return this.dynamicLinks.size();
    }

    public Object getItem(int i) {
        return this.dynamicLinks.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.inflater.inflate(this.layoutId, viewGroup, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.bind((ChallengeDynamicTab) this.dynamicLinks.get(i));
        return view;
    }
}
