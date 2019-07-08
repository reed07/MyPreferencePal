package io.requery.sql;

import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

class LoggingListener<T> implements EntityStateListener<T>, StatementListener {
    private final Level level;
    private final Logger log;

    public LoggingListener() {
        this(Logger.getLogger("requery"), Level.INFO);
    }

    public LoggingListener(Logger logger, Level level2) {
        this.log = logger;
        this.level = level2;
    }

    public void postLoad(T t) {
        this.log.log(this.level, "postLoad {0}", t);
    }

    public void postInsert(T t) {
        this.log.log(this.level, "postInsert {0}", t);
    }

    public void postUpdate(T t) {
        this.log.log(this.level, "postUpdate {0}", t);
    }

    public void preInsert(T t) {
        this.log.log(this.level, "preInsert {0}", t);
    }

    public void preUpdate(T t) {
        this.log.log(this.level, "preUpdate {0}", t);
    }

    public void beforeExecuteUpdate(Statement statement, String str, BoundParameters boundParameters) {
        if (boundParameters == null || boundParameters.isEmpty()) {
            this.log.log(this.level, "beforeExecuteUpdate {0} sql:\n{1}", new Object[]{statement, str});
            return;
        }
        this.log.log(this.level, "beforeExecuteUpdate {0} sql:\n{1} \n({2})", new Object[]{statement, str, boundParameters});
    }

    public void afterExecuteUpdate(Statement statement, int i) {
        this.log.log(this.level, "afterExecuteUpdate {0}", new Object[]{Integer.valueOf(i)});
    }

    public void beforeExecuteQuery(Statement statement, String str, BoundParameters boundParameters) {
        if (boundParameters == null || boundParameters.isEmpty()) {
            this.log.log(this.level, "beforeExecuteQuery {0} sql:\n{1}", new Object[]{statement, str});
            return;
        }
        this.log.log(this.level, "beforeExecuteQuery {0} sql:\n{1} \n({2})", new Object[]{statement, str, boundParameters});
    }

    public void afterExecuteQuery(Statement statement) {
        this.log.log(this.level, "afterExecuteQuery");
    }
}
