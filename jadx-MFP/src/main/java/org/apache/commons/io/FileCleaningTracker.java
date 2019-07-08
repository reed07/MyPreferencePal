package org.apache.commons.io;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class FileCleaningTracker {
    final List<String> deleteFailures = Collections.synchronizedList(new ArrayList());
    volatile boolean exitWhenFinished = false;
    ReferenceQueue<Object> q = new ReferenceQueue<>();
    final Collection<Object> trackers = Collections.synchronizedSet(new HashSet());
}
