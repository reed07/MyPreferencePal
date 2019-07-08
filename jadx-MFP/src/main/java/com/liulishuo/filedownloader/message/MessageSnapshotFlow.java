package com.liulishuo.filedownloader.message;

public class MessageSnapshotFlow {
    private volatile MessageSnapshotThreadPool flowThreadPool;
    private volatile MessageReceiver receiver;

    public static final class HolderClass {
        /* access modifiers changed from: private */
        public static final MessageSnapshotFlow INSTANCE = new MessageSnapshotFlow();
    }

    public interface MessageReceiver {
        void receive(MessageSnapshot messageSnapshot);
    }

    public static MessageSnapshotFlow getImpl() {
        return HolderClass.INSTANCE;
    }

    public void setReceiver(MessageReceiver messageReceiver) {
        this.receiver = messageReceiver;
        if (messageReceiver == null) {
            this.flowThreadPool = null;
        } else {
            this.flowThreadPool = new MessageSnapshotThreadPool(5, messageReceiver);
        }
    }

    public void inflow(MessageSnapshot messageSnapshot) {
        if (messageSnapshot instanceof IFlowDirectly) {
            if (this.receiver != null) {
                this.receiver.receive(messageSnapshot);
            }
        } else if (this.flowThreadPool != null) {
            this.flowThreadPool.execute(messageSnapshot);
        }
    }
}
