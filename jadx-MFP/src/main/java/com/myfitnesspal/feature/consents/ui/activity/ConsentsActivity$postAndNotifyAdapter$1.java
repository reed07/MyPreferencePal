package com.myfitnesspal.feature.consents.ui.activity;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
/* compiled from: ConsentsActivity.kt */
final class ConsentsActivity$postAndNotifyAdapter$1 implements Runnable {
    final /* synthetic */ Adapter $adapter;
    final /* synthetic */ Handler $handler;
    final /* synthetic */ RecyclerView $recyclerView;
    final /* synthetic */ ConsentsActivity this$0;

    ConsentsActivity$postAndNotifyAdapter$1(ConsentsActivity consentsActivity, RecyclerView recyclerView, Adapter adapter, Handler handler) {
        this.this$0 = consentsActivity;
        this.$recyclerView = recyclerView;
        this.$adapter = adapter;
        this.$handler = handler;
    }

    public final void run() {
        if (!this.$recyclerView.isComputingLayout()) {
            this.$adapter.notifyDataSetChanged();
        } else {
            this.this$0.postAndNotifyAdapter(this.$handler, this.$recyclerView, this.$adapter);
        }
    }
}
