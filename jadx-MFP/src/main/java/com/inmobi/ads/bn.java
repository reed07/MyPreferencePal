package com.inmobi.ads;

import android.os.Handler;
import android.os.Message;
import com.inmobi.commons.core.a.a;
import com.inmobi.rendering.RenderView;
import java.lang.ref.WeakReference;

/* compiled from: RenderTimeoutHandler */
final class bn extends Handler {
    private WeakReference<i> a;

    bn(i iVar) {
        this.a = new WeakReference<>(iVar);
    }

    public final void handleMessage(Message message) {
        i iVar = (i) this.a.get();
        if (iVar != null && message.what == 0) {
            try {
                if (iVar instanceof ac) {
                    ac acVar = (ac) iVar;
                    if (acVar.u != null) {
                        acVar.u.stopLoading();
                        return;
                    }
                }
                RenderView renderView = (RenderView) iVar.j();
                if (renderView == null) {
                    iVar.D();
                    return;
                }
                renderView.stopLoading();
                iVar.D();
            } catch (Exception e) {
                a.a().a(new com.inmobi.commons.core.e.a(e));
            } finally {
                iVar.D();
            }
        }
    }
}
