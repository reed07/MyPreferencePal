package io.uacf.rollouts.ui.debug;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import io.uacf.core.api.UacfApiException;
import io.uacf.rollouts.R;
import io.uacf.rollouts.sdk.UacfVariantSdk;
import io.uacf.rollouts.sdk.UacfVariantSdkFactory;
import io.uacf.rollouts.sdk.model.UacfVariant;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class UacfRolloutsDevToolActivity extends AppCompatActivity {
    private RolloutsAdapter adapter;
    private List<UacfVariant> currentVariants;
    private TextView emptyState;
    private ForceFetchRolloutsTask forceFetchRolloutsTask;
    /* access modifiers changed from: private */
    public boolean forceFetchStarted;
    /* access modifiers changed from: private */
    public ListView rolloutsListView;
    /* access modifiers changed from: private */
    public SwipeRefreshLayout swipeRefreshLayout;
    /* access modifiers changed from: private */
    public UacfVariantSdk variantSdk;

    private static class ForceFetchRolloutsTask extends AsyncTask<Void, Void, Boolean> {
        private WeakReference<UacfRolloutsDevToolActivity> activityWeakReference;

        private ForceFetchRolloutsTask(UacfRolloutsDevToolActivity uacfRolloutsDevToolActivity) {
            this.activityWeakReference = new WeakReference<>(uacfRolloutsDevToolActivity);
        }

        /* access modifiers changed from: protected */
        public Boolean doInBackground(Void... voidArr) {
            if (((UacfRolloutsDevToolActivity) this.activityWeakReference.get()).forceFetchStarted) {
                return Boolean.valueOf(false);
            }
            try {
                ((UacfRolloutsDevToolActivity) this.activityWeakReference.get()).variantSdk.forceFetchVariants();
                return Boolean.valueOf(true);
            } catch (UacfApiException e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Boolean bool) {
            ((UacfRolloutsDevToolActivity) this.activityWeakReference.get()).rolloutsListView.setVisibility(0);
            if (bool.booleanValue()) {
                ((UacfRolloutsDevToolActivity) this.activityWeakReference.get()).loadRolloutsList();
            }
            ((UacfRolloutsDevToolActivity) this.activityWeakReference.get()).forceFetchStarted = false;
            ((UacfRolloutsDevToolActivity) this.activityWeakReference.get()).swipeRefreshLayout.setRefreshing(false);
        }
    }

    private class RolloutViewHolder {
        private TextView rolloutName;
        private TextView rolloutVersion;
        private TextView trackEvent;
        private TextView varaintIndex;
        private TextView varaintName;

        private RolloutViewHolder(View view) {
            this.rolloutName = (TextView) view.findViewById(R.id.rollout_name);
            this.rolloutVersion = (TextView) view.findViewById(R.id.rollout_version);
            this.varaintName = (TextView) view.findViewById(R.id.variant_name);
            this.varaintIndex = (TextView) view.findViewById(R.id.variant_index);
            this.trackEvent = (TextView) view.findViewById(R.id.rollout_track_event);
        }

        public void setData(UacfVariant uacfVariant) {
            if (uacfVariant != null) {
                this.rolloutName.setText(uacfVariant.getRolloutName());
                this.rolloutVersion.setText(String.valueOf(uacfVariant.getRolloutVersion()));
                this.varaintName.setText(uacfVariant.getVariantName() == null ? "null" : uacfVariant.getVariantName());
                this.varaintIndex.setText(String.valueOf(uacfVariant.getVariantIndex()));
                this.trackEvent.setText(String.valueOf(uacfVariant.getTrackEvent()));
            }
        }
    }

    private class RolloutsAdapter extends ArrayAdapter<UacfVariant> {
        private List<UacfVariant> data;
        @LayoutRes
        private int resourceId;

        private RolloutsAdapter(Context context, @NonNull int i, @LayoutRes List<UacfVariant> list) {
            super(context, i);
            this.resourceId = i;
            this.data = list;
        }

        @NonNull
        public View getView(int i, View view, @NonNull ViewGroup viewGroup) {
            RolloutViewHolder rolloutViewHolder;
            if (view == null) {
                view = LayoutInflater.from(getContext()).inflate(this.resourceId, viewGroup, false);
                view.setClickable(false);
                view.setOnClickListener(null);
                rolloutViewHolder = new RolloutViewHolder(view);
                view.setTag(rolloutViewHolder);
            } else {
                rolloutViewHolder = (RolloutViewHolder) view.getTag();
            }
            rolloutViewHolder.setData(getItem(i));
            return view;
        }

        @Nullable
        public UacfVariant getItem(int i) {
            return (UacfVariant) this.data.get(i);
        }

        public int getCount() {
            return this.data.size();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_rollouts_dev_tool);
        setUpActionBar();
        this.variantSdk = new UacfVariantSdkFactory().newSdkInstance();
        this.rolloutsListView = (ListView) findViewById(R.id.rollouts_list_view);
        this.emptyState = (TextView) findViewById(R.id.rollouts_list_empty_state);
        this.swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.rollouts_list_swipe_container);
        this.swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            public void onRefresh() {
                UacfRolloutsDevToolActivity.this.startForceFetchTask();
            }
        });
        loadRolloutsList();
    }

    private void setUpActionBar() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle(R.string.rollouts_dev_tool);
        supportActionBar.setHomeButtonEnabled(true);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onResume() {
        super.onResume();
        if (this.forceFetchStarted) {
            startForceFetchTask();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.forceFetchStarted) {
            ForceFetchRolloutsTask forceFetchRolloutsTask2 = this.forceFetchRolloutsTask;
            if (forceFetchRolloutsTask2 != null) {
                forceFetchRolloutsTask2.cancel(true);
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: private */
    public void startForceFetchTask() {
        this.rolloutsListView.setVisibility(8);
        this.emptyState.setVisibility(8);
        this.forceFetchRolloutsTask = new ForceFetchRolloutsTask();
        this.forceFetchRolloutsTask.execute(new Void[0]);
    }

    /* access modifiers changed from: private */
    public void loadRolloutsList() {
        List variants = this.variantSdk.getVariants();
        updateList(variants);
        int i = 0;
        this.emptyState.setVisibility(variants.size() == 0 ? 0 : 8);
        ListView listView = this.rolloutsListView;
        if (variants.size() == 0) {
            i = 8;
        }
        listView.setVisibility(i);
    }

    private void updateList(List<UacfVariant> list) {
        if (this.currentVariants == null) {
            this.currentVariants = new ArrayList();
        }
        this.currentVariants.clear();
        this.currentVariants.addAll(list);
        RolloutsAdapter rolloutsAdapter = this.adapter;
        if (rolloutsAdapter == null) {
            RolloutsAdapter rolloutsAdapter2 = new RolloutsAdapter(this, R.layout.rollouts_list_item, this.currentVariants);
            this.adapter = rolloutsAdapter2;
            this.rolloutsListView.setAdapter(this.adapter);
            return;
        }
        rolloutsAdapter.notifyDataSetChanged();
    }
}
