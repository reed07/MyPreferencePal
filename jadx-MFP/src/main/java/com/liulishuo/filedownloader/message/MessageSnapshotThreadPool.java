package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.message.MessageSnapshotFlow.MessageReceiver;
import com.liulishuo.filedownloader.util.FileDownloadExecutors;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

public class MessageSnapshotThreadPool {
    private final List<FlowSingleExecutor> executorList = new ArrayList();
    /* access modifiers changed from: private */
    public final MessageReceiver receiver;

    public class FlowSingleExecutor {
        /* access modifiers changed from: private */
        public final List<Integer> enQueueTaskIdList = new ArrayList();
        private final Executor mExecutor;

        public FlowSingleExecutor(int i) {
            StringBuilder sb = new StringBuilder();
            sb.append("Flow-");
            sb.append(i);
            this.mExecutor = FileDownloadExecutors.newDefaultThreadPool(1, sb.toString());
        }

        public void enqueue(int i) {
            this.enQueueTaskIdList.add(Integer.valueOf(i));
        }

        public void execute(final MessageSnapshot messageSnapshot) {
            this.mExecutor.execute(new Runnable() {
                public void run() {
                    MessageSnapshotThreadPool.this.receiver.receive(messageSnapshot);
                    FlowSingleExecutor.this.enQueueTaskIdList.remove(Integer.valueOf(messageSnapshot.getId()));
                }
            });
        }
    }

    MessageSnapshotThreadPool(int i, MessageReceiver messageReceiver) {
        this.receiver = messageReceiver;
        for (int i2 = 0; i2 < i; i2++) {
            this.executorList.add(new FlowSingleExecutor(i2));
        }
    }

    public void execute(MessageSnapshot messageSnapshot) {
        FlowSingleExecutor flowSingleExecutor = null;
        try {
            synchronized (this.executorList) {
                int id = messageSnapshot.getId();
                Iterator it = this.executorList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    FlowSingleExecutor flowSingleExecutor2 = (FlowSingleExecutor) it.next();
                    if (flowSingleExecutor2.enQueueTaskIdList.contains(Integer.valueOf(id))) {
                        flowSingleExecutor = flowSingleExecutor2;
                        break;
                    }
                }
                if (flowSingleExecutor == null) {
                    int i = 0;
                    Iterator it2 = this.executorList.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        FlowSingleExecutor flowSingleExecutor3 = (FlowSingleExecutor) it2.next();
                        if (flowSingleExecutor3.enQueueTaskIdList.size() <= 0) {
                            flowSingleExecutor = flowSingleExecutor3;
                            break;
                        } else if (i == 0 || flowSingleExecutor3.enQueueTaskIdList.size() < i) {
                            i = flowSingleExecutor3.enQueueTaskIdList.size();
                            flowSingleExecutor = flowSingleExecutor3;
                        }
                    }
                }
                flowSingleExecutor.enqueue(id);
            }
            flowSingleExecutor.execute(messageSnapshot);
        } catch (Throwable th) {
            flowSingleExecutor.execute(messageSnapshot);
            throw th;
        }
    }
}
