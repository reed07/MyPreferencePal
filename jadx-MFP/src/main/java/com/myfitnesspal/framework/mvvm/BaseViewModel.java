package com.myfitnesspal.framework.mvvm;

import android.databinding.BaseObservable;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

public abstract class BaseViewModel extends BaseObservable {
    private static final String SALT = String.valueOf(System.currentTimeMillis());
    private static final AtomicLong nextViewModelId = new AtomicLong(Long.MIN_VALUE);
    private Set<Integer> changedProperties = new HashSet();
    private String uniqueId = null;

    public final String getId() {
        if (this.uniqueId == null) {
            if (nextViewModelId.get() == Long.MAX_VALUE) {
                nextViewModelId.set(Long.MIN_VALUE);
            }
            if (this.uniqueId == null) {
                this.uniqueId = String.format("%s-%s", new Object[]{SALT, Long.valueOf(nextViewModelId.incrementAndGet())});
            }
        }
        return this.uniqueId;
    }

    public void notifyPropertyChanged(int i) {
        this.changedProperties.add(Integer.valueOf(i));
        super.notifyPropertyChanged(i);
    }

    public final void onRebindView() {
        for (Integer intValue : this.changedProperties) {
            notifyPropertyChanged(intValue.intValue());
        }
    }
}
