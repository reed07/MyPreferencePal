package com.myfitnesspal.feature.friends.ui.viewmodel;

import com.myfitnesspal.feature.friends.task.FetchFriendsListTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.mapper.impl.MiniUserInfoMapper;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.uacf.taskrunner.Runner;
import dagger.Lazy;
import java.util.List;

public class FriendsViewModel extends RunnerViewModel<Void> {
    private final Lazy<FriendService> friendService;
    private List<MiniUserInfo> friendsList;
    private final Lazy<MiniUserInfoMapper> miniUserInfoMapper;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int FRIENDS_LIST = ViewModelPropertyId.next();
    }

    public FriendsViewModel(Runner runner, Lazy<FriendService> lazy, Lazy<MiniUserInfoMapper> lazy2) {
        super(runner);
        this.friendService = lazy;
        this.miniUserInfoMapper = lazy2;
    }

    public void load(Void... voidArr) {
        setState(State.Loading);
        getRunner().run(new FetchFriendsListTask(this.friendService, this.miniUserInfoMapper));
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(TaskDetails taskDetails) {
        if (!taskDetails.matches(getRunner(), FetchFriendsListTask.class)) {
            return;
        }
        if (taskDetails.successful()) {
            this.friendsList = (List) taskDetails.getResult();
            notifyPropertyChanged(Property.FRIENDS_LIST);
            setState(State.Loaded);
            return;
        }
        setState(State.Error);
    }

    public List<MiniUserInfo> getFriendsList() {
        return this.friendsList;
    }
}
