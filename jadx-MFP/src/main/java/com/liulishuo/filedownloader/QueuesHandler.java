package com.liulishuo.filedownloader;

import android.os.Handler;
import android.util.SparseArray;
import java.util.List;

class QueuesHandler implements IQueuesHandler {
    private final SparseArray<Handler> mRunningSerialMap = new SparseArray<>();

    QueuesHandler() {
    }

    public void freezeAllSerialQueues() {
        for (int i = 0; i < this.mRunningSerialMap.size(); i++) {
            freezeSerialHandler((Handler) this.mRunningSerialMap.get(this.mRunningSerialMap.keyAt(i)));
        }
    }

    public void unFreezeSerialQueues(List<Integer> list) {
        for (Integer intValue : list) {
            unFreezeSerialHandler((Handler) this.mRunningSerialMap.get(intValue.intValue()));
        }
    }

    public int serialQueueSize() {
        return this.mRunningSerialMap.size();
    }

    public boolean contain(int i) {
        return this.mRunningSerialMap.get(i) != null;
    }

    private void freezeSerialHandler(Handler handler) {
        handler.sendEmptyMessage(2);
    }

    private void unFreezeSerialHandler(Handler handler) {
        handler.sendEmptyMessage(3);
    }
}
