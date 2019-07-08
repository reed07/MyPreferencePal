package com.liulishuo.filedownloader;

import java.util.List;

public interface IQueuesHandler {
    boolean contain(int i);

    void freezeAllSerialQueues();

    int serialQueueSize();

    void unFreezeSerialQueues(List<Integer> list);
}
