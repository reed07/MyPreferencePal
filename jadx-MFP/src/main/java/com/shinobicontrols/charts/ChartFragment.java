package com.shinobicontrols.charts;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(12)
public class ChartFragment extends ChartFragmentBase {
    public final ShinobiChart getShinobiChart() {
        return super.getShinobiChart();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    /* access modifiers changed from: 0000 */
    public v a() {
        return new ce(getActivity());
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public final void onDestroyView() {
        super.onDestroyView();
    }
}
