package com.bumptech.glide.manager;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.request.Request;
import com.bumptech.glide.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

public class RequestTracker {
    private boolean isPaused;
    private final List<Request> pendingRequests = new ArrayList();
    private final Set<Request> requests = Collections.newSetFromMap(new WeakHashMap());

    public void runRequest(@NonNull Request request) {
        this.requests.add(request);
        if (!this.isPaused) {
            request.begin();
            return;
        }
        request.clear();
        if (Log.isLoggable("RequestTracker", 2)) {
            Log.v("RequestTracker", "Paused, delaying request");
        }
        this.pendingRequests.add(request);
    }

    public boolean clearRemoveAndRecycle(@Nullable Request request) {
        return clearRemoveAndMaybeRecycle(request, true);
    }

    private boolean clearRemoveAndMaybeRecycle(@Nullable Request request, boolean z) {
        boolean z2 = true;
        if (request == null) {
            return true;
        }
        boolean remove = this.requests.remove(request);
        if (!this.pendingRequests.remove(request) && !remove) {
            z2 = false;
        }
        if (z2) {
            request.clear();
            if (z) {
                request.recycle();
            }
        }
        return z2;
    }

    public void pauseRequests() {
        this.isPaused = true;
        for (Request request : Util.getSnapshot(this.requests)) {
            if (request.isRunning()) {
                request.clear();
                this.pendingRequests.add(request);
            }
        }
    }

    public void resumeRequests() {
        this.isPaused = false;
        for (Request request : Util.getSnapshot(this.requests)) {
            if (!request.isComplete() && !request.isRunning()) {
                request.begin();
            }
        }
        this.pendingRequests.clear();
    }

    public void clearRequests() {
        for (Request clearRemoveAndMaybeRecycle : Util.getSnapshot(this.requests)) {
            clearRemoveAndMaybeRecycle(clearRemoveAndMaybeRecycle, false);
        }
        this.pendingRequests.clear();
    }

    public void restartRequests() {
        for (Request request : Util.getSnapshot(this.requests)) {
            if (!request.isComplete() && !request.isCleared()) {
                request.clear();
                if (!this.isPaused) {
                    request.begin();
                } else {
                    this.pendingRequests.add(request);
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("{numRequests=");
        sb.append(this.requests.size());
        sb.append(", isPaused=");
        sb.append(this.isPaused);
        sb.append("}");
        return sb.toString();
    }
}
