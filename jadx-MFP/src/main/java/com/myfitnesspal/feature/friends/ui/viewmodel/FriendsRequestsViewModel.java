package com.myfitnesspal.feature.friends.ui.viewmodel;

import android.util.LongSparseArray;
import com.myfitnesspal.feature.friends.task.AcceptFriendRequestTask;
import com.myfitnesspal.feature.friends.task.DeclineFriendRequestTask;
import com.myfitnesspal.feature.friends.task.FetchFriendRequestsTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.v15.FriendRequestObject;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;

public class FriendsRequestsViewModel extends RunnerViewModel<String> {
    private List<FriendRequestObject> friendRequests;
    private final Lazy<FriendService> friendService;
    private OnFriendRequestCompletedListener requestActionCompletedListener;
    private LongSparseArray<Long> taskIdToRequestUid = new LongSparseArray<>();

    public interface OnFriendRequestCompletedListener {
        void onFailure(RequestType requestType, long j, Throwable th);

        void onSuccess(RequestType requestType, long j);
    }

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int FRIEND_REQUEST_LIST = ViewModelPropertyId.next();
    }

    public enum RequestType {
        Accept,
        Decline
    }

    public FriendsRequestsViewModel(Runner runner, Lazy<FriendService> lazy) {
        super(runner);
        this.friendService = lazy;
    }

    public void load(String... strArr) {
        setState(State.Loading);
        getRunner().run(new FetchFriendRequestsTask((FriendService) this.friendService.get()));
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (taskDetails.matches(getRunner(), FetchFriendRequestsTask.class)) {
            if (taskDetails.successful()) {
                this.friendRequests = (List) taskDetails.getResult();
                notifyPropertyChanged(Property.FRIEND_REQUEST_LIST);
                setState(State.Loaded);
                return;
            }
            setState(State.Error);
        } else if (taskDetails.matches(getRunner(), AcceptFriendRequestTask.class)) {
            long findRequestIdByTaskId = findRequestIdByTaskId(taskDetails.getTaskId());
            if (this.requestActionCompletedListener == null) {
                return;
            }
            if (taskDetails.successful()) {
                this.requestActionCompletedListener.onSuccess(RequestType.Accept, findRequestIdByTaskId);
            } else {
                this.requestActionCompletedListener.onFailure(RequestType.Accept, findRequestIdByTaskId, taskDetails.getFailure());
            }
        } else if (taskDetails.matches(getRunner(), DeclineFriendRequestTask.class)) {
            long findRequestIdByTaskId2 = findRequestIdByTaskId(taskDetails.getTaskId());
            if (this.requestActionCompletedListener == null) {
                return;
            }
            if (taskDetails.successful()) {
                this.requestActionCompletedListener.onSuccess(RequestType.Decline, findRequestIdByTaskId2);
            } else {
                this.requestActionCompletedListener.onFailure(RequestType.Decline, findRequestIdByTaskId2, taskDetails.getFailure());
            }
        }
    }

    public void acceptFriendRequest(long j, String str) {
        this.taskIdToRequestUid.put(getRunner().run(new AcceptFriendRequestTask((FriendService) this.friendService.get(), j, str)), Long.valueOf(j));
    }

    public void declineFriendRequest(long j, String str) {
        this.taskIdToRequestUid.put(getRunner().run(new DeclineFriendRequestTask((FriendService) this.friendService.get(), j, str)), Long.valueOf(j));
    }

    private long findRequestIdByTaskId(long j) {
        Long l = (Long) this.taskIdToRequestUid.get(j);
        if (l == null) {
            return -1;
        }
        this.taskIdToRequestUid.remove(j);
        return l.longValue();
    }

    public List<FriendRequestObject> getFriendRequests() {
        return new ArrayList(this.friendRequests);
    }

    public void setRequestActionCompletedListener(OnFriendRequestCompletedListener onFriendRequestCompletedListener) {
        this.requestActionCompletedListener = onFriendRequestCompletedListener;
    }
}
