package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.util.FileDownloadUtils;

public interface BlockCompleteMessage {

    public static class BlockCompleteMessageImpl extends MessageSnapshot implements BlockCompleteMessage {
        private final MessageSnapshot mCompletedSnapshot;

        public byte getStatus() {
            return 4;
        }

        public BlockCompleteMessageImpl(MessageSnapshot messageSnapshot) {
            super(messageSnapshot.getId());
            if (messageSnapshot.getStatus() == -3) {
                this.mCompletedSnapshot = messageSnapshot;
            } else {
                throw new IllegalArgumentException(FileDownloadUtils.formatString("can't create the block complete message for id[%d], status[%d]", Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus())));
            }
        }

        public MessageSnapshot transmitToCompleted() {
            return this.mCompletedSnapshot;
        }
    }

    MessageSnapshot transmitToCompleted();
}
