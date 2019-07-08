package io.requery.sql;

import io.requery.proxy.EntityProxy;
import io.requery.proxy.EntityStateEventListeners;
import io.requery.proxy.PostInsertListener;
import io.requery.proxy.PostLoadListener;
import io.requery.proxy.PostUpdateListener;
import io.requery.proxy.PreInsertListener;
import io.requery.proxy.PreUpdateListener;

class CompositeEntityListener<T> extends EntityStateEventListeners<T> {
    private boolean enableStateListeners;

    CompositeEntityListener() {
    }

    /* access modifiers changed from: 0000 */
    public void enableStateListeners(boolean z) {
        this.enableStateListeners = z;
    }

    /* access modifiers changed from: 0000 */
    public void preUpdate(T t, EntityProxy<? extends T> entityProxy) {
        if (this.enableStateListeners) {
            for (PreUpdateListener preUpdate : this.preUpdateListeners) {
                preUpdate.preUpdate(t);
            }
        }
        if (entityProxy != null) {
            entityProxy.preUpdate();
        }
    }

    /* access modifiers changed from: 0000 */
    public void postUpdate(T t, EntityProxy<? extends T> entityProxy) {
        if (this.enableStateListeners) {
            for (PostUpdateListener postUpdate : this.postUpdateListeners) {
                postUpdate.postUpdate(t);
            }
        }
        if (entityProxy != null) {
            entityProxy.postUpdate();
        }
    }

    /* access modifiers changed from: 0000 */
    public void preInsert(T t, EntityProxy<? extends T> entityProxy) {
        if (this.enableStateListeners) {
            for (PreInsertListener preInsert : this.preInsertListeners) {
                preInsert.preInsert(t);
            }
        }
        if (entityProxy != null) {
            entityProxy.preInsert();
        }
    }

    /* access modifiers changed from: 0000 */
    public void postInsert(T t, EntityProxy<? extends T> entityProxy) {
        if (this.enableStateListeners) {
            for (PostInsertListener postInsert : this.postInsertListeners) {
                postInsert.postInsert(t);
            }
        }
        if (entityProxy != null) {
            entityProxy.postInsert();
        }
    }

    /* access modifiers changed from: 0000 */
    public void postLoad(T t, EntityProxy<? extends T> entityProxy) {
        if (this.enableStateListeners) {
            for (PostLoadListener postLoad : this.postLoadListeners) {
                postLoad.postLoad(t);
            }
        }
        if (entityProxy != null) {
            entityProxy.postLoad();
        }
    }
}
