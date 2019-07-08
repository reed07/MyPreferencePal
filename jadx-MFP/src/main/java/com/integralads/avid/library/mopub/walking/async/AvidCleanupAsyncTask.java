package com.integralads.avid.library.mopub.walking.async;

import com.integralads.avid.library.mopub.walking.async.AvidAsyncTask.StateProvider;

public class AvidCleanupAsyncTask extends AvidAsyncTask {
    public AvidCleanupAsyncTask(StateProvider stateProvider) {
        super(stateProvider);
    }

    /* access modifiers changed from: protected */
    public String doInBackground(Object... objArr) {
        this.stateProvider.setPreviousState(null);
        return null;
    }
}
