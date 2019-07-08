package android.support.v7.preference;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.preference.PreferenceGroup.PreferencePositionCallback;
import android.support.v7.preference.PreferenceManager.PreferenceComparisonCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.Callback;
import android.support.v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

@RestrictTo
public class PreferenceGroupAdapter extends Adapter<PreferenceViewHolder> implements OnPreferenceChangeInternalListener, PreferencePositionCallback {
    private Handler mHandler;
    private PreferenceGroup mPreferenceGroup;
    private CollapsiblePreferenceGroupController mPreferenceGroupController;
    private List<PreferenceLayout> mPreferenceLayouts;
    private List<Preference> mPreferenceList;
    private List<Preference> mPreferenceListInternal;
    private Runnable mSyncRunnable;
    private PreferenceLayout mTempPreferenceLayout;

    private static class PreferenceLayout {
        String mName;
        int mResId;
        int mWidgetResId;

        PreferenceLayout() {
        }

        PreferenceLayout(PreferenceLayout preferenceLayout) {
            this.mResId = preferenceLayout.mResId;
            this.mWidgetResId = preferenceLayout.mWidgetResId;
            this.mName = preferenceLayout.mName;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof PreferenceLayout)) {
                return false;
            }
            PreferenceLayout preferenceLayout = (PreferenceLayout) obj;
            if (this.mResId == preferenceLayout.mResId && this.mWidgetResId == preferenceLayout.mWidgetResId && TextUtils.equals(this.mName, preferenceLayout.mName)) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return ((((527 + this.mResId) * 31) + this.mWidgetResId) * 31) + this.mName.hashCode();
        }
    }

    public PreferenceGroupAdapter(PreferenceGroup preferenceGroup) {
        this(preferenceGroup, new Handler());
    }

    private PreferenceGroupAdapter(PreferenceGroup preferenceGroup, Handler handler) {
        this.mTempPreferenceLayout = new PreferenceLayout();
        this.mSyncRunnable = new Runnable() {
            public void run() {
                PreferenceGroupAdapter.this.syncMyPreferences();
            }
        };
        this.mPreferenceGroup = preferenceGroup;
        this.mHandler = handler;
        this.mPreferenceGroupController = new CollapsiblePreferenceGroupController(preferenceGroup, this);
        this.mPreferenceGroup.setOnPreferenceChangeInternalListener(this);
        this.mPreferenceList = new ArrayList();
        this.mPreferenceListInternal = new ArrayList();
        this.mPreferenceLayouts = new ArrayList();
        PreferenceGroup preferenceGroup2 = this.mPreferenceGroup;
        if (preferenceGroup2 instanceof PreferenceScreen) {
            setHasStableIds(((PreferenceScreen) preferenceGroup2).shouldUseGeneratedIds());
        } else {
            setHasStableIds(true);
        }
        syncMyPreferences();
    }

    @VisibleForTesting
    static PreferenceGroupAdapter createInstanceWithCustomHandler(PreferenceGroup preferenceGroup, Handler handler) {
        return new PreferenceGroupAdapter(preferenceGroup, handler);
    }

    /* access modifiers changed from: 0000 */
    public void syncMyPreferences() {
        for (Preference onPreferenceChangeInternalListener : this.mPreferenceListInternal) {
            onPreferenceChangeInternalListener.setOnPreferenceChangeInternalListener(null);
        }
        ArrayList<Preference> arrayList = new ArrayList<>(this.mPreferenceListInternal.size());
        flattenPreferenceGroup(arrayList, this.mPreferenceGroup);
        final List<Preference> createVisiblePreferencesList = this.mPreferenceGroupController.createVisiblePreferencesList(this.mPreferenceGroup);
        final List<Preference> list = this.mPreferenceList;
        this.mPreferenceList = createVisiblePreferencesList;
        this.mPreferenceListInternal = arrayList;
        PreferenceManager preferenceManager = this.mPreferenceGroup.getPreferenceManager();
        if (preferenceManager == null || preferenceManager.getPreferenceComparisonCallback() == null) {
            notifyDataSetChanged();
        } else {
            final PreferenceComparisonCallback preferenceComparisonCallback = preferenceManager.getPreferenceComparisonCallback();
            DiffUtil.calculateDiff(new Callback() {
                public int getOldListSize() {
                    return list.size();
                }

                public int getNewListSize() {
                    return createVisiblePreferencesList.size();
                }

                public boolean areItemsTheSame(int i, int i2) {
                    return preferenceComparisonCallback.arePreferenceItemsTheSame((Preference) list.get(i), (Preference) createVisiblePreferencesList.get(i2));
                }

                public boolean areContentsTheSame(int i, int i2) {
                    return preferenceComparisonCallback.arePreferenceContentsTheSame((Preference) list.get(i), (Preference) createVisiblePreferencesList.get(i2));
                }
            }).dispatchUpdatesTo((Adapter) this);
        }
        for (Preference clearWasDetached : arrayList) {
            clearWasDetached.clearWasDetached();
        }
    }

    private void flattenPreferenceGroup(List<Preference> list, PreferenceGroup preferenceGroup) {
        preferenceGroup.sortPreferences();
        int preferenceCount = preferenceGroup.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            Preference preference = preferenceGroup.getPreference(i);
            list.add(preference);
            addPreferenceClassName(preference);
            if (preference instanceof PreferenceGroup) {
                PreferenceGroup preferenceGroup2 = (PreferenceGroup) preference;
                if (preferenceGroup2.isOnSameScreenAsChildren()) {
                    flattenPreferenceGroup(list, preferenceGroup2);
                }
            }
            preference.setOnPreferenceChangeInternalListener(this);
        }
    }

    private PreferenceLayout createPreferenceLayout(Preference preference, PreferenceLayout preferenceLayout) {
        if (preferenceLayout == null) {
            preferenceLayout = new PreferenceLayout();
        }
        preferenceLayout.mName = preference.getClass().getName();
        preferenceLayout.mResId = preference.getLayoutResource();
        preferenceLayout.mWidgetResId = preference.getWidgetLayoutResource();
        return preferenceLayout;
    }

    private void addPreferenceClassName(Preference preference) {
        PreferenceLayout createPreferenceLayout = createPreferenceLayout(preference, null);
        if (!this.mPreferenceLayouts.contains(createPreferenceLayout)) {
            this.mPreferenceLayouts.add(createPreferenceLayout);
        }
    }

    public int getItemCount() {
        return this.mPreferenceList.size();
    }

    public Preference getItem(int i) {
        if (i < 0 || i >= getItemCount()) {
            return null;
        }
        return (Preference) this.mPreferenceList.get(i);
    }

    public long getItemId(int i) {
        if (!hasStableIds()) {
            return -1;
        }
        return getItem(i).getId();
    }

    public void onPreferenceChange(Preference preference) {
        int indexOf = this.mPreferenceList.indexOf(preference);
        if (indexOf != -1) {
            notifyItemChanged(indexOf, preference);
        }
    }

    public void onPreferenceHierarchyChange(Preference preference) {
        this.mHandler.removeCallbacks(this.mSyncRunnable);
        this.mHandler.post(this.mSyncRunnable);
    }

    public void onPreferenceVisibilityChange(Preference preference) {
        if (this.mPreferenceListInternal.contains(preference) && !this.mPreferenceGroupController.onPreferenceVisibilityChange(preference)) {
            if (preference.isVisible()) {
                int i = -1;
                for (Preference preference2 : this.mPreferenceListInternal) {
                    if (preference.equals(preference2)) {
                        break;
                    } else if (preference2.isVisible()) {
                        i++;
                    }
                }
                int i2 = i + 1;
                this.mPreferenceList.add(i2, preference);
                notifyItemInserted(i2);
            } else {
                int size = this.mPreferenceList.size();
                int i3 = 0;
                while (i3 < size && !preference.equals(this.mPreferenceList.get(i3))) {
                    if (i3 != size - 1) {
                        i3++;
                    } else {
                        return;
                    }
                }
                this.mPreferenceList.remove(i3);
                notifyItemRemoved(i3);
            }
        }
    }

    public int getItemViewType(int i) {
        this.mTempPreferenceLayout = createPreferenceLayout(getItem(i), this.mTempPreferenceLayout);
        int indexOf = this.mPreferenceLayouts.indexOf(this.mTempPreferenceLayout);
        if (indexOf != -1) {
            return indexOf;
        }
        int size = this.mPreferenceLayouts.size();
        this.mPreferenceLayouts.add(new PreferenceLayout(this.mTempPreferenceLayout));
        return size;
    }

    public PreferenceViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        PreferenceLayout preferenceLayout = (PreferenceLayout) this.mPreferenceLayouts.get(i);
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        TypedArray obtainStyledAttributes = viewGroup.getContext().obtainStyledAttributes(null, R.styleable.BackgroundStyle);
        Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.BackgroundStyle_android_selectableItemBackground);
        if (drawable == null) {
            drawable = ContextCompat.getDrawable(viewGroup.getContext(), 17301602);
        }
        obtainStyledAttributes.recycle();
        View inflate = from.inflate(preferenceLayout.mResId, viewGroup, false);
        if (inflate.getBackground() == null) {
            ViewCompat.setBackground(inflate, drawable);
        }
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(16908312);
        if (viewGroup2 != null) {
            if (preferenceLayout.mWidgetResId != 0) {
                from.inflate(preferenceLayout.mWidgetResId, viewGroup2);
            } else {
                viewGroup2.setVisibility(8);
            }
        }
        return new PreferenceViewHolder(inflate);
    }

    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder, int i) {
        getItem(i).onBindViewHolder(preferenceViewHolder);
    }

    public int getPreferenceAdapterPosition(String str) {
        int size = this.mPreferenceList.size();
        for (int i = 0; i < size; i++) {
            if (TextUtils.equals(str, ((Preference) this.mPreferenceList.get(i)).getKey())) {
                return i;
            }
        }
        return -1;
    }

    public int getPreferenceAdapterPosition(Preference preference) {
        int size = this.mPreferenceList.size();
        for (int i = 0; i < size; i++) {
            Preference preference2 = (Preference) this.mPreferenceList.get(i);
            if (preference2 != null && preference2.equals(preference)) {
                return i;
            }
        }
        return -1;
    }
}
