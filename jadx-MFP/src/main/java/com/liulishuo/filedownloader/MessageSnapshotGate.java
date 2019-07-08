package com.liulishuo.filedownloader;

import com.liulishuo.filedownloader.BaseDownloadTask.IRunningTask;
import com.liulishuo.filedownloader.message.MessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshotFlow.MessageReceiver;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import java.util.List;

public class MessageSnapshotGate implements MessageReceiver {
    private boolean transmitMessage(List<IRunningTask> list, MessageSnapshot messageSnapshot) {
        boolean updateKeepAhead;
        if (list.size() > 1 && messageSnapshot.getStatus() == -3) {
            for (IRunningTask iRunningTask : list) {
                synchronized (iRunningTask.getPauseLock()) {
                    if (iRunningTask.getMessageHandler().updateMoreLikelyCompleted(messageSnapshot)) {
                        FileDownloadLog.d(this, "updateMoreLikelyCompleted", new Object[0]);
                        return true;
                    }
                }
            }
        }
        for (IRunningTask iRunningTask2 : list) {
            synchronized (iRunningTask2.getPauseLock()) {
                if (iRunningTask2.getMessageHandler().updateKeepFlow(messageSnapshot)) {
                    FileDownloadLog.d(this, "updateKeepFlow", new Object[0]);
                    return true;
                }
            }
        }
        if (-4 == messageSnapshot.getStatus()) {
            for (IRunningTask iRunningTask3 : list) {
                synchronized (iRunningTask3.getPauseLock()) {
                    if (iRunningTask3.getMessageHandler().updateSameFilePathTaskRunning(messageSnapshot)) {
                        FileDownloadLog.d(this, "updateSampleFilePathTaskRunning", new Object[0]);
                        return true;
                    }
                }
            }
        }
        if (list.size() != 1) {
            return false;
        }
        IRunningTask iRunningTask4 = (IRunningTask) list.get(0);
        synchronized (iRunningTask4.getPauseLock()) {
            FileDownloadLog.d(this, "updateKeepAhead", new Object[0]);
            updateKeepAhead = iRunningTask4.getMessageHandler().updateKeepAhead(messageSnapshot);
        }
        return updateKeepAhead;
    }

    public void receive(MessageSnapshot messageSnapshot) {
        synchronized (Integer.toString(messageSnapshot.getId()).intern()) {
            List<IRunningTask> receiveServiceTaskList = FileDownloadList.getImpl().getReceiveServiceTaskList(messageSnapshot.getId());
            if (receiveServiceTaskList.size() > 0) {
                BaseDownloadTask origin = ((IRunningTask) receiveServiceTaskList.get(0)).getOrigin();
                if (FileDownloadLog.NEED_LOG) {
                    FileDownloadLog.d(this, "~~~callback %s old[%s] new[%s] %d", Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(origin.getStatus()), Byte.valueOf(messageSnapshot.getStatus()), Integer.valueOf(receiveServiceTaskList.size()));
                }
                if (!transmitMessage(receiveServiceTaskList, messageSnapshot)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("The event isn't consumed, id:");
                    sb.append(messageSnapshot.getId());
                    sb.append(" status:");
                    sb.append(messageSnapshot.getStatus());
                    sb.append(" task-count:");
                    sb.append(receiveServiceTaskList.size());
                    StringBuilder sb2 = new StringBuilder(sb.toString());
                    for (IRunningTask iRunningTask : receiveServiceTaskList) {
                        sb2.append(" | ");
                        sb2.append(iRunningTask.getOrigin().getStatus());
                    }
                    FileDownloadLog.i(this, sb2.toString(), new Object[0]);
                }
            } else {
                FileDownloadLog.i(this, "Receive the event %d, but there isn't any running task in the upper layer", Byte.valueOf(messageSnapshot.getStatus()));
            }
        }
    }
}
