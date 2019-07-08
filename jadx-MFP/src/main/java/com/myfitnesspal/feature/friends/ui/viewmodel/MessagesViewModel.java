package com.myfitnesspal.feature.friends.ui.viewmodel;

import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.feature.friends.task.FetchMessagesTask;
import com.myfitnesspal.feature.friends.ui.activity.MessagesActivity.Type;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import com.uacf.core.exception.UacfNotImplementedException;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Strings;
import com.uacf.taskrunner.Runner;
import com.uacf.taskrunner.Runner.DedupeMode;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MessagesViewModel extends RunnerViewModel<Void> {
    private static final int MESSAGES_LIMIT = 200;
    private static final String TASK_NAME = "messages_task";
    private final Lazy<MessageService> messageService;
    private List<InboxMessage> messages = new ArrayList();
    private Type messagesType;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int MESSAGES_FETCHED = ViewModelPropertyId.next();
        public static final int MESSAGES_FETCH_FAILED = ViewModelPropertyId.next();
    }

    public MessagesViewModel(Runner runner, Lazy<MessageService> lazy, Type type) {
        super(runner);
        this.messageService = lazy;
        this.messagesType = type;
    }

    public void setTypeAndReloadIfNecessary(Type type) {
        if (type != this.messagesType) {
            this.messagesType = type;
            this.messages.clear();
            notifyPropertyChanged(Property.MESSAGES_FETCHED);
            loadInternal();
        }
    }

    public void load(Void... voidArr) {
        if (getState() != State.Loading) {
            loadInternal();
        }
    }

    public List<InboxMessage> getMessages() {
        return this.messages;
    }

    public boolean hasMessages() {
        return CollectionUtils.notEmpty((Collection<?>) this.messages);
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.isFrom(getRunner()) && Strings.equals(taskDetails.getTaskName(), TASK_NAME)) {
            setState(State.Loaded);
            if (taskDetails.successful()) {
                List<InboxMessage> list = (List) taskDetails.getResult();
                if (list == null) {
                    list = new ArrayList<>();
                }
                this.messages = list;
                notifyPropertyChanged(Property.MESSAGES_FETCHED);
            } else {
                notifyPropertyChanged(Property.MESSAGES_FETCH_FAILED);
            }
        }
    }

    private void loadInternal() {
        FetchMessagesTask fetchMessagesTask;
        setState(State.Loading);
        switch (this.messagesType) {
            case ReceivedMessages:
                fetchMessagesTask = FetchMessagesTask.newInstanceForReceivedMessages(this.messageService, 200);
                break;
            case SentMessages:
                fetchMessagesTask = FetchMessagesTask.newInstanceForSentMessages(this.messageService, 200);
                break;
            default:
                throw new UacfNotImplementedException();
        }
        getRunner().run(TASK_NAME, fetchMessagesTask, DedupeMode.CancelExisting);
    }
}
