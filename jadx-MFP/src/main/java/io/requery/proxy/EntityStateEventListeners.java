package io.requery.proxy;

import java.util.LinkedHashSet;
import java.util.Set;

public class EntityStateEventListeners<T> implements EntityStateEventListenable<T> {
    protected final Set<PostDeleteListener<T>> postDeleteListeners = new LinkedHashSet();
    protected final Set<PostInsertListener<T>> postInsertListeners = new LinkedHashSet();
    protected final Set<PostLoadListener<T>> postLoadListeners = new LinkedHashSet();
    protected final Set<PostUpdateListener<T>> postUpdateListeners = new LinkedHashSet();
    protected final Set<PreDeleteListener<T>> preDeleteListeners = new LinkedHashSet();
    protected final Set<PreInsertListener<T>> preInsertListeners = new LinkedHashSet();
    protected final Set<PreUpdateListener<T>> preUpdateListeners = new LinkedHashSet();

    protected EntityStateEventListeners() {
    }

    public void addPreInsertListener(PreInsertListener<T> preInsertListener) {
        this.preInsertListeners.add(preInsertListener);
    }

    public void addPreDeleteListener(PreDeleteListener<T> preDeleteListener) {
        this.preDeleteListeners.add(preDeleteListener);
    }

    public void addPreUpdateListener(PreUpdateListener<T> preUpdateListener) {
        this.preUpdateListeners.add(preUpdateListener);
    }

    public void addPostInsertListener(PostInsertListener<T> postInsertListener) {
        this.postInsertListeners.add(postInsertListener);
    }

    public void addPostDeleteListener(PostDeleteListener<T> postDeleteListener) {
        this.postDeleteListeners.add(postDeleteListener);
    }

    public void addPostUpdateListener(PostUpdateListener<T> postUpdateListener) {
        this.postUpdateListeners.add(postUpdateListener);
    }

    public void addPostLoadListener(PostLoadListener<T> postLoadListener) {
        this.postLoadListeners.add(postLoadListener);
    }
}
