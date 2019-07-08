package com.myfitnesspal.feature.friends.ui.viewmodel;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.myfitnesspal.feature.friends.task.GroupFriendsTask;
import com.myfitnesspal.framework.mvvm.LoadableViewModel.State;
import com.myfitnesspal.framework.mvvm.RunnerViewModel;
import com.myfitnesspal.framework.mvvm.ViewModelPropertyId;
import com.myfitnesspal.framework.taskrunner.TaskDetails;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import com.uacf.taskrunner.Runner;
import java.util.List;
import java.util.Map;

public class FriendsListViewModel extends RunnerViewModel<List<MiniUserInfo>> {
    private Map<Character, List<MiniUserInfo>> friendsInfoMap;

    public interface Property extends com.myfitnesspal.framework.mvvm.LoadableViewModel.Property {
        public static final int FRIENDS_LIST = ViewModelPropertyId.next();
    }

    public FriendsListViewModel(Runner runner) {
        super(runner);
    }

    public void load(List<MiniUserInfo>... listArr) {
        setState(State.Loading);
        getRunner().run(new GroupFriendsTask(listArr[0]));
    }

    /* access modifiers changed from: protected */
    public void onTaskCompleted(@NonNull TaskDetails taskDetails) {
        if (!taskDetails.matches(getRunner(), GroupFriendsTask.class)) {
            return;
        }
        if (taskDetails.successful()) {
            this.friendsInfoMap = (Map) taskDetails.getResult();
            notifyPropertyChanged(Property.FRIENDS_LIST);
            setState(State.Loaded);
            return;
        }
        setState(State.Error);
    }

    @Nullable
    public Map<Character, List<MiniUserInfo>> friendsInfoMap() {
        return this.friendsInfoMap;
    }
}
