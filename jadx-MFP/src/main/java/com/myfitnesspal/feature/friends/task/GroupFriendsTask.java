package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GroupFriendsTask extends EventedTaskBase<Map<Character, List<MiniUserInfo>>, Exception> {
    private final List<MiniUserInfo> userInfos;

    public GroupFriendsTask(List<MiniUserInfo> list) {
        this.userInfos = list;
    }

    /* access modifiers changed from: protected */
    public Map<Character, List<MiniUserInfo>> exec(Context context) throws Exception {
        return groupFriendsByFirstLetter(this.userInfos);
    }

    private Map<Character, List<MiniUserInfo>> groupFriendsByFirstLetter(List<MiniUserInfo> list) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Collections.sort(list, $$Lambda$GroupFriendsTask$Q0vCsF1wdFopHwAINbLZZm4Ty7w.INSTANCE);
        for (MiniUserInfo miniUserInfo : list) {
            Character valueOf = Character.valueOf(Character.toUpperCase(miniUserInfo.getUsername().charAt(0)));
            List list2 = (List) linkedHashMap.get(valueOf);
            if (list2 == null) {
                list2 = new ArrayList();
                linkedHashMap.put(valueOf, list2);
            }
            list2.add(miniUserInfo);
        }
        return linkedHashMap;
    }
}
