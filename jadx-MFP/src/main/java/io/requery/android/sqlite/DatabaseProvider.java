package io.requery.android.sqlite;

import android.annotation.TargetApi;
import io.requery.sql.ConnectionProvider;

@TargetApi(19)
public interface DatabaseProvider<T> extends ConnectionProvider, AutoCloseable {
}
