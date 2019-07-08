package io.requery.android;

import android.support.v4.content.AsyncTaskLoader;
import io.requery.query.Result;

public abstract class QueryLoader<E> extends AsyncTaskLoader<Result<E>> {
    private Result<E> result;

    /* access modifiers changed from: protected */
    public void onStartLoading() {
        Result<E> result2 = this.result;
        if (result2 != null) {
            deliverResult(result2);
        } else {
            forceLoad();
        }
    }

    /* access modifiers changed from: protected */
    public void onReset() {
        super.onReset();
        onStopLoading();
        Result<E> result2 = this.result;
        if (result2 != null) {
            result2.close();
            this.result = null;
        }
    }

    public void deliverResult(Result<E> result2) {
        if (isReset()) {
            Result<E> result3 = this.result;
            if (result3 != null) {
                result3.close();
            }
            return;
        }
        Result<E> result4 = this.result;
        this.result = result2;
        if (isStarted()) {
            super.deliverResult(this.result);
        }
        if (!(result4 == null || result4 == result2)) {
            result4.close();
        }
    }
}
