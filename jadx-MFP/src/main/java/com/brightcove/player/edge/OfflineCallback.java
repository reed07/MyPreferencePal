package com.brightcove.player.edge;

public interface OfflineCallback<R> {
    void onFailure(Throwable th);

    void onSuccess(R r);
}
