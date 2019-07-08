package com.myfitnesspal.feature.friends.task;

import com.myfitnesspal.shared.model.v1.MiniUserInfo;
import java.util.Comparator;

/* renamed from: com.myfitnesspal.feature.friends.task.-$$Lambda$GroupFriendsTask$Q0vCsF1wdFopHwAINbLZZm4Ty7w reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$GroupFriendsTask$Q0vCsF1wdFopHwAINbLZZm4Ty7w implements Comparator {
    public static final /* synthetic */ $$Lambda$GroupFriendsTask$Q0vCsF1wdFopHwAINbLZZm4Ty7w INSTANCE = new $$Lambda$GroupFriendsTask$Q0vCsF1wdFopHwAINbLZZm4Ty7w();

    private /* synthetic */ $$Lambda$GroupFriendsTask$Q0vCsF1wdFopHwAINbLZZm4Ty7w() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((MiniUserInfo) obj).getUsername().toLowerCase().compareTo(((MiniUserInfo) obj2).getUsername().toLowerCase());
    }
}
