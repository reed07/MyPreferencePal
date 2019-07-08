package com.shinobicontrols.charts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

abstract class SupportChartFragmentBase extends Fragment {
    private v a;

    /* access modifiers changed from: 0000 */
    public abstract v a();

    SupportChartFragmentBase() {
    }

    public ShinobiChart getShinobiChart() {
        return this.a;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.a = a();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.a;
    }

    public void onDestroyView() {
        super.onDestroyView();
        ((ViewGroup) this.a.getParent()).removeView(this.a);
    }

    public void onResume() {
        super.onResume();
        v vVar = this.a;
        if (vVar != null) {
            vVar.c();
        }
    }

    public void onPause() {
        super.onPause();
        v vVar = this.a;
        if (vVar != null) {
            vVar.d();
        }
    }
}
