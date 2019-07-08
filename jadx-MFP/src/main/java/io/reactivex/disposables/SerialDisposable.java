package io.reactivex.disposables;

import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class SerialDisposable implements Disposable {
    final AtomicReference<Disposable> resource = new AtomicReference<>();

    public void dispose() {
        DisposableHelper.dispose(this.resource);
    }

    public boolean isDisposed() {
        return DisposableHelper.isDisposed((Disposable) this.resource.get());
    }
}
