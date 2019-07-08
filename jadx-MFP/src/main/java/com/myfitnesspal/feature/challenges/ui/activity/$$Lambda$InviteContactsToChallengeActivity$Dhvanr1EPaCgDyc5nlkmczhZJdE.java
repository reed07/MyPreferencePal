package com.myfitnesspal.feature.challenges.ui.activity;

import com.myfitnesspal.shared.model.v1.EmailFriend;
import java.util.Comparator;

/* renamed from: com.myfitnesspal.feature.challenges.ui.activity.-$$Lambda$InviteContactsToChallengeActivity$Dhvanr1EPaCgDyc5nlkmczhZJdE reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$InviteContactsToChallengeActivity$Dhvanr1EPaCgDyc5nlkmczhZJdE implements Comparator {
    public static final /* synthetic */ $$Lambda$InviteContactsToChallengeActivity$Dhvanr1EPaCgDyc5nlkmczhZJdE INSTANCE = new $$Lambda$InviteContactsToChallengeActivity$Dhvanr1EPaCgDyc5nlkmczhZJdE();

    private /* synthetic */ $$Lambda$InviteContactsToChallengeActivity$Dhvanr1EPaCgDyc5nlkmczhZJdE() {
    }

    public final int compare(Object obj, Object obj2) {
        return ((EmailFriend) obj).getMfpUsername().compareTo(((EmailFriend) obj2).getMfpUsername());
    }
}
