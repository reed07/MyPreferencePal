package com.myfitnesspal.feature.premium.util;

public final class RemoteLoadMonitor {
    private LoadState loadState = LoadState.NotLoaded;
    private Loader loader;

    public enum LoadState {
        NotLoaded,
        Loading,
        Loaded
    }

    public interface Loader {
        void load();
    }

    public RemoteLoadMonitor(Loader loader2) {
        this.loader = loader2;
    }

    public synchronized void onStarted() {
        if (this.loadState != LoadState.Loaded) {
            this.loadState = LoadState.Loading;
        }
    }

    public synchronized boolean isLoadingOrLoaded() {
        return this.loadState != LoadState.NotLoaded;
    }

    public synchronized void onFinished(boolean z) {
        if (this.loadState != LoadState.Loaded) {
            if (z) {
                this.loadState = LoadState.Loaded;
                this.loader = null;
            } else {
                this.loadState = LoadState.NotLoaded;
            }
        }
    }

    public synchronized void loadOnce() {
        if (this.loader != null && this.loadState == LoadState.NotLoaded) {
            this.loader.load();
        }
    }
}
