package com.liulishuo.filedownloader.message;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.download.DownloadStatusCallback.ProcessParams;
import com.liulishuo.filedownloader.message.BlockCompleteMessage.BlockCompleteMessageImpl;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.CompletedFlowDirectlySnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.CompletedSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.ConnectedMessageSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.ErrorMessageSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.ProgressMessageSnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.WarnFlowDirectlySnapshot;
import com.liulishuo.filedownloader.message.LargeMessageSnapshot.WarnMessageSnapshot;
import com.liulishuo.filedownloader.message.MessageSnapshot.StartedMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.PausedSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.PendingMessageSnapshot;
import com.liulishuo.filedownloader.message.SmallMessageSnapshot.RetryMessageSnapshot;
import com.liulishuo.filedownloader.model.FileDownloadModel;
import com.liulishuo.filedownloader.util.FileDownloadLog;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import java.io.File;

public class MessageSnapshotTaker {
    public static MessageSnapshot catchCanReusedOldFile(int i, File file, boolean z) {
        long length = file.length();
        if (length > 2147483647L) {
            if (z) {
                return new CompletedFlowDirectlySnapshot(i, true, length);
            }
            return new CompletedSnapshot(i, true, length);
        } else if (z) {
            return new SmallMessageSnapshot.CompletedFlowDirectlySnapshot(i, true, (int) length);
        } else {
            return new SmallMessageSnapshot.CompletedSnapshot(i, true, (int) length);
        }
    }

    public static MessageSnapshot catchWarn(int i, long j, long j2, boolean z) {
        if (j2 > 2147483647L) {
            if (z) {
                WarnFlowDirectlySnapshot warnFlowDirectlySnapshot = new WarnFlowDirectlySnapshot(i, j, j2);
                return warnFlowDirectlySnapshot;
            }
            WarnMessageSnapshot warnMessageSnapshot = new WarnMessageSnapshot(i, j, j2);
            return warnMessageSnapshot;
        } else if (z) {
            return new SmallMessageSnapshot.WarnFlowDirectlySnapshot(i, (int) j, (int) j2);
        } else {
            return new SmallMessageSnapshot.WarnMessageSnapshot(i, (int) j, (int) j2);
        }
    }

    public static MessageSnapshot catchException(int i, long j, Throwable th) {
        if (j > 2147483647L) {
            return new ErrorMessageSnapshot(i, j, th);
        }
        return new SmallMessageSnapshot.ErrorMessageSnapshot(i, (int) j, th);
    }

    public static MessageSnapshot catchPause(BaseDownloadTask baseDownloadTask) {
        if (!baseDownloadTask.isLargeFile()) {
            return new PausedSnapshot(baseDownloadTask.getId(), baseDownloadTask.getSmallFileSoFarBytes(), baseDownloadTask.getSmallFileTotalBytes());
        }
        LargeMessageSnapshot.PausedSnapshot pausedSnapshot = new LargeMessageSnapshot.PausedSnapshot(baseDownloadTask.getId(), baseDownloadTask.getLargeFileSoFarBytes(), baseDownloadTask.getLargeFileTotalBytes());
        return pausedSnapshot;
    }

    public static MessageSnapshot takeBlockCompleted(MessageSnapshot messageSnapshot) {
        if (messageSnapshot.getStatus() == -3) {
            return new BlockCompleteMessageImpl(messageSnapshot);
        }
        throw new IllegalStateException(FileDownloadUtils.formatString("take block completed snapshot, must has already be completed. %d %d", Integer.valueOf(messageSnapshot.getId()), Byte.valueOf(messageSnapshot.getStatus())));
    }

    public static MessageSnapshot take(byte b, FileDownloadModel fileDownloadModel, ProcessParams processParams) {
        IllegalStateException illegalStateException;
        int id = fileDownloadModel.getId();
        if (b != -4) {
            switch (b) {
                case -3:
                    if (fileDownloadModel.isLargeFile()) {
                        return new CompletedSnapshot(id, false, fileDownloadModel.getTotal());
                    }
                    return new SmallMessageSnapshot.CompletedSnapshot(id, false, (int) fileDownloadModel.getTotal());
                case -1:
                    if (fileDownloadModel.isLargeFile()) {
                        return new ErrorMessageSnapshot(id, fileDownloadModel.getSoFar(), processParams.getException());
                    }
                    return new SmallMessageSnapshot.ErrorMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), processParams.getException());
                case 1:
                    if (!fileDownloadModel.isLargeFile()) {
                        return new PendingMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), (int) fileDownloadModel.getTotal());
                    }
                    LargeMessageSnapshot.PendingMessageSnapshot pendingMessageSnapshot = new LargeMessageSnapshot.PendingMessageSnapshot(id, fileDownloadModel.getSoFar(), fileDownloadModel.getTotal());
                    return pendingMessageSnapshot;
                case 2:
                    String filename = fileDownloadModel.isPathAsDirectory() ? fileDownloadModel.getFilename() : null;
                    if (fileDownloadModel.isLargeFile()) {
                        ConnectedMessageSnapshot connectedMessageSnapshot = new ConnectedMessageSnapshot(id, processParams.isResuming(), fileDownloadModel.getTotal(), fileDownloadModel.getETag(), filename);
                        return connectedMessageSnapshot;
                    }
                    SmallMessageSnapshot.ConnectedMessageSnapshot connectedMessageSnapshot2 = new SmallMessageSnapshot.ConnectedMessageSnapshot(id, processParams.isResuming(), (int) fileDownloadModel.getTotal(), fileDownloadModel.getETag(), filename);
                    return connectedMessageSnapshot2;
                case 3:
                    if (fileDownloadModel.isLargeFile()) {
                        return new ProgressMessageSnapshot(id, fileDownloadModel.getSoFar());
                    }
                    return new SmallMessageSnapshot.ProgressMessageSnapshot(id, (int) fileDownloadModel.getSoFar());
                case 5:
                    if (!fileDownloadModel.isLargeFile()) {
                        return new RetryMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), processParams.getException(), processParams.getRetryingTimes());
                    }
                    LargeMessageSnapshot.RetryMessageSnapshot retryMessageSnapshot = new LargeMessageSnapshot.RetryMessageSnapshot(id, fileDownloadModel.getSoFar(), processParams.getException(), processParams.getRetryingTimes());
                    return retryMessageSnapshot;
                case 6:
                    return new StartedMessageSnapshot(id);
                default:
                    String formatString = FileDownloadUtils.formatString("it can't takes a snapshot for the task(%s) when its status is %d,", fileDownloadModel, Byte.valueOf(b));
                    FileDownloadLog.w(MessageSnapshotTaker.class, "it can't takes a snapshot for the task(%s) when its status is %d,", fileDownloadModel, Byte.valueOf(b));
                    if (processParams.getException() != null) {
                        illegalStateException = new IllegalStateException(formatString, processParams.getException());
                    } else {
                        illegalStateException = new IllegalStateException(formatString);
                    }
                    if (fileDownloadModel.isLargeFile()) {
                        return new ErrorMessageSnapshot(id, fileDownloadModel.getSoFar(), illegalStateException);
                    }
                    return new SmallMessageSnapshot.ErrorMessageSnapshot(id, (int) fileDownloadModel.getSoFar(), illegalStateException);
            }
        } else {
            throw new IllegalStateException(FileDownloadUtils.formatString("please use #catchWarn instead %d", Integer.valueOf(id)));
        }
    }
}
