package io.requery.sql;

import io.requery.proxy.PostDeleteListener;
import io.requery.proxy.PostInsertListener;
import io.requery.proxy.PostLoadListener;
import io.requery.proxy.PostUpdateListener;
import io.requery.proxy.PreDeleteListener;
import io.requery.proxy.PreInsertListener;
import io.requery.proxy.PreUpdateListener;

public interface EntityStateListener<T> extends PostDeleteListener<T>, PostInsertListener<T>, PostLoadListener<T>, PostUpdateListener<T>, PreDeleteListener<T>, PreInsertListener<T>, PreUpdateListener<T> {
}
