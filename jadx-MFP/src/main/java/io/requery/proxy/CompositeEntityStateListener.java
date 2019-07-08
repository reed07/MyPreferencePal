package io.requery.proxy;

class CompositeEntityStateListener<T> extends EntityStateEventListeners<T> implements EntityStateListener {
    private final T entity;

    CompositeEntityStateListener(T t) {
        this.entity = t;
    }

    public void preUpdate() {
        for (PreUpdateListener preUpdate : this.preUpdateListeners) {
            preUpdate.preUpdate(this.entity);
        }
    }

    public void postUpdate() {
        for (PostUpdateListener postUpdate : this.postUpdateListeners) {
            postUpdate.postUpdate(this.entity);
        }
    }

    public void preInsert() {
        for (PreInsertListener preInsert : this.preInsertListeners) {
            preInsert.preInsert(this.entity);
        }
    }

    public void postInsert() {
        for (PostInsertListener postInsert : this.postInsertListeners) {
            postInsert.postInsert(this.entity);
        }
    }

    public void postLoad() {
        for (PostLoadListener postLoad : this.postLoadListeners) {
            postLoad.postLoad(this.entity);
        }
    }
}
