package io.requery.android;

import android.util.Log;
import io.requery.proxy.PostDeleteListener;
import io.requery.proxy.PostInsertListener;
import io.requery.proxy.PostLoadListener;
import io.requery.proxy.PostUpdateListener;
import io.requery.proxy.PreDeleteListener;
import io.requery.proxy.PreInsertListener;
import io.requery.proxy.PreUpdateListener;
import io.requery.sql.BoundParameters;
import io.requery.sql.StatementListener;
import java.sql.Statement;

public class LoggingListener implements PostDeleteListener<Object>, PostInsertListener<Object>, PostLoadListener<Object>, PostUpdateListener<Object>, PreDeleteListener<Object>, PreInsertListener<Object>, PreUpdateListener<Object>, StatementListener {
    private final String tag;

    public LoggingListener() {
        this("requery");
    }

    public LoggingListener(String str) {
        this.tag = str;
    }

    public void postLoad(Object obj) {
        Log.i(this.tag, String.format("postLoad %s", new Object[]{obj}));
    }

    public void postInsert(Object obj) {
        Log.i(this.tag, String.format("postInsert %s", new Object[]{obj}));
    }

    public void postUpdate(Object obj) {
        Log.i(this.tag, String.format("postUpdate %s", new Object[]{obj}));
    }

    public void preInsert(Object obj) {
        Log.i(this.tag, String.format("preInsert %s", new Object[]{obj}));
    }

    public void preUpdate(Object obj) {
        Log.i(this.tag, String.format("preUpdate %s", new Object[]{obj}));
    }

    public void beforeExecuteUpdate(Statement statement, String str, BoundParameters boundParameters) {
        Log.i(this.tag, String.format("beforeExecuteUpdate sql: %s", new Object[]{str}));
    }

    public void afterExecuteUpdate(Statement statement, int i) {
        Log.i(this.tag, String.format("afterExecuteUpdate %d", new Object[]{Integer.valueOf(i)}));
    }

    public void beforeExecuteQuery(Statement statement, String str, BoundParameters boundParameters) {
        Log.i(this.tag, String.format("beforeExecuteQuery sql: %s", new Object[]{str}));
    }

    public void afterExecuteQuery(Statement statement) {
        Log.i(this.tag, "afterExecuteQuery");
    }
}
