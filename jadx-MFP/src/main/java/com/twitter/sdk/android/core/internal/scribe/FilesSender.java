package com.twitter.sdk.android.core.internal.scribe;

import java.io.File;
import java.util.List;

public interface FilesSender {
    boolean send(List<File> list);
}
