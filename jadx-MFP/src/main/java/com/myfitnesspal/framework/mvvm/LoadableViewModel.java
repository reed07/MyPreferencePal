package com.myfitnesspal.framework.mvvm;

import com.uacf.core.util.Ln;

public abstract class LoadableViewModel<T> extends BaseViewModel {
    private Object lastError;
    private State state = State.NotLoaded;

    public interface Property {
        public static final int LOAD_STATE = ViewModelPropertyId.next();
    }

    public enum State {
        NotLoaded,
        Loading,
        Loaded,
        Error
    }

    public abstract void load(T... tArr);

    public void loadIfNotLoaded(T... tArr) {
        State state2 = getState();
        if (state2 == State.NotLoaded || state2 == State.Error) {
            load(tArr);
        }
    }

    public boolean isBusy() {
        return getState() == State.Loading;
    }

    /* access modifiers changed from: protected */
    public void setState(State state2) {
        if (state2 != this.state) {
            if (state2 != State.Error) {
                this.lastError = null;
            }
            this.state = state2;
            notifyPropertyChanged(Property.LOAD_STATE);
        }
    }

    public boolean isLoaded() {
        return this.state == State.Loaded;
    }

    public boolean isLoading() {
        return this.state == State.Loading;
    }

    public boolean isLoadedOrError() {
        return this.state == State.Loaded || this.state == State.Error;
    }

    public State getState() {
        return this.state;
    }

    public Object getLastError() {
        return this.lastError;
    }

    /* access modifiers changed from: protected */
    public void setError(Object obj) {
        Ln.e(obj, new Object[0]);
        this.lastError = obj;
        setState(State.Error);
    }

    /* access modifiers changed from: protected */
    public void resetError(State state2) {
        this.lastError = null;
        setState(state2);
    }
}
