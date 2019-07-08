package com.myfitnesspal.feature.challenges.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.event.FriendListRefreshedEvent;
import com.myfitnesspal.feature.addfriends.service.ContactsOnMfpService;
import com.myfitnesspal.feature.addfriends.service.InviteSucceeded;
import com.myfitnesspal.feature.addfriends.ui.activity.AddFriendsBase;
import com.myfitnesspal.feature.addfriends.ui.view.AddFriendsItem;
import com.myfitnesspal.feature.challenges.model.NewInvitation;
import com.myfitnesspal.feature.challenges.service.ChallengesService;
import com.myfitnesspal.feature.challenges.service.ExecuteInviteFriendsTask;
import com.myfitnesspal.feature.challenges.service.ExecuteInviteFriendsTask.CompletedEvent;
import com.myfitnesspal.feature.challenges.service.FetchExistingFriendsTask;
import com.myfitnesspal.feature.challenges.ui.view.ChallengeInviteContactRowView;
import com.myfitnesspal.feature.challenges.util.ChallengesAnalyticsHelper;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.event.AlertEvent;
import com.myfitnesspal.shared.model.v1.EmailFriend;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.service.friends.FriendService;
import com.myfitnesspal.shared.ui.adapter.SeparatedListAdapter;
import com.myfitnesspal.shared.util.UniqueId;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.ListViewUtils;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;

public class InviteContactsToChallengeActivity extends AddFriendsBase<ContactsOnMfpService, EmailFriend> {
    private static final int ACTION_COMPLETE = UniqueId.next();
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    private String challengeId;
    private String challengeName;
    @Inject
    Lazy<ChallengesAnalyticsHelper> challengesAnalyticsHelper;
    @Inject
    Lazy<ChallengesService> challengesService;
    @Inject
    Lazy<ContactsOnMfpService> contactsService;
    private boolean dedupedExisting;
    private List<EmailFriend> existingFriends;
    @Inject
    Lazy<FriendService> friendService;
    private ArrayList<String> friendUserIdsInChallenge;
    private boolean fromInterstitial;

    /* access modifiers changed from: protected */
    public int getEmptyTextId() {
        return R.string.you_havent_added_any_friends_yet;
    }

    /* access modifiers changed from: protected */
    public int getMfpHeaderForMoreThanOneFriend() {
        return R.string.addfriends_header_contact_N;
    }

    /* access modifiers changed from: protected */
    public int getMfpHeaderForOneFriend() {
        return R.string.addfriends_header_contact_1;
    }

    /* access modifiers changed from: protected */
    public int getNonMfpHeader() {
        return R.string.addfriends_invite_contacts;
    }

    /* access modifiers changed from: protected */
    public void refresh() {
    }

    /* access modifiers changed from: protected */
    public boolean shouldDisplayNonMfpContacts() {
        return false;
    }

    public static Intent newStartIntent(Context context, String str, String str2, ArrayList<String> arrayList) {
        Intent intent = new Intent(context, InviteContactsToChallengeActivity.class);
        intent.putExtra("challenge_id", str);
        intent.putExtra("challenge_name", str2);
        intent.putExtra(Extras.FRIENDS_IN_CHALLENGE, arrayList);
        return intent;
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        this.challengeId = getIntent().getStringExtra("challenge_id");
        this.challengeName = ExtrasUtils.getString(getIntent(), "challenge_name");
        this.friendUserIdsInChallenge = ExtrasUtils.getStringArrayList(getIntent(), Extras.FRIENDS_IN_CHALLENGE);
        this.fromInterstitial = ExtrasUtils.getBoolean(getIntent(), Extras.FROM_INTERSTITIAL);
        setTitle(R.string.invite_friends_to_challenge);
        if (bundle != null) {
            ArrayList parcelableArrayAsList = BundleUtils.getParcelableArrayAsList(bundle, Extras.NON_MFP_FRIENDS, EmailFriend.class.getClassLoader());
            if (CollectionUtils.notEmpty((Collection<?>) parcelableArrayAsList)) {
                this.nonMfpFriends = parcelableArrayAsList;
            }
            ArrayList parcelableArrayAsList2 = BundleUtils.getParcelableArrayAsList(bundle, Extras.MFP_FRIENDS, EmailFriend.class.getClassLoader());
            if (CollectionUtils.notEmpty((Collection<?>) parcelableArrayAsList2)) {
                this.mfpFriends = parcelableArrayAsList2;
            }
        }
        refreshExistingFriends();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (CollectionUtils.notEmpty((Collection<?>) this.mfpFriends)) {
            bundle.putParcelableArray(Extras.MFP_FRIENDS, (Parcelable[]) this.mfpFriends.toArray(new EmailFriend[this.mfpFriends.size()]));
        }
        if (this.nonMfpFriends != null && this.nonMfpFriends.size() > 0) {
            bundle.putParcelableArray(Extras.NON_MFP_FRIENDS, (Parcelable[]) this.nonMfpFriends.toArray(new EmailFriend[this.nonMfpFriends.size()]));
        }
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        if (!this.fromInterstitial) {
            return false;
        }
        MenuItemCompat.setShowAsAction(menu.add(0, ACTION_COMPLETE, 1, R.string.ok).setIcon(R.drawable.ic_check_white_24dp), 2);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != ACTION_COMPLETE) {
            return super.onOptionsItemSelected(menuItem);
        }
        ((ActionTrackingService) this.actionTrackingService.get()).registerAndReportEvent(Events.FRIEND_LIST_SKIPPED);
        finish();
        return true;
    }

    private void sendFriendInterstitialDisplayedEventToAnalytics() {
        if (this.mfpFriends != null) {
            ((ActionTrackingService) this.actionTrackingService.get()).registerEvent(Events.FRIEND_LIST_DISPLAYED);
            ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.FRIEND_LIST_DISPLAYED, "count", Strings.toString(Integer.valueOf(this.mfpFriends.size())));
            ((ActionTrackingService) this.actionTrackingService.get()).reportEventToAnalytics(Events.FRIEND_LIST_DISPLAYED);
            return;
        }
        ((ActionTrackingService) this.actionTrackingService.get()).registerEvent(Events.FRIEND_LIST_DISPLAYED);
        ((ActionTrackingService) this.actionTrackingService.get()).appendToEvent(Events.FRIEND_LIST_DISPLAYED, "count", "0");
        ((ActionTrackingService) this.actionTrackingService.get()).reportEventToAnalytics(Events.FRIEND_LIST_DISPLAYED);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 18) {
            String string = ExtrasUtils.getString(intent, Extras.FRIEND_TO_INVITE, (String) null);
            if (Strings.notEmpty(string)) {
                updateNonMfpUserState(string, i2 == -1);
            }
        }
    }

    private void refreshExistingFriends() {
        onStartedProcessing();
        new FetchExistingFriendsTask((FriendService) this.friendService.get()).run(getRunner());
    }

    /* access modifiers changed from: protected */
    public ContactsOnMfpService getService() {
        return (ContactsOnMfpService) this.contactsService.get();
    }

    public boolean onInviteClicked(AddFriendsItem<EmailFriend> addFriendsItem, InviteSucceeded inviteSucceeded) {
        sendInvite((EmailFriend) addFriendsItem.getFriend());
        ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportInviteFriendsScreenInviteEvent(this.challengeName, this.challengeId);
        return true;
    }

    /* access modifiers changed from: protected */
    public void sendInvite(EmailFriend emailFriend) {
        new ExecuteInviteFriendsTask(emailFriend, this.challengesService, this.challengeId, new NewInvitation(emailFriend.getId())).run(getRunner());
    }

    @Subscribe
    public void onExecuteInviteFriendsTaskEvent(CompletedEvent completedEvent) {
        if (completedEvent.getRunnerId() != getRunner().getId() || completedEvent.successful()) {
            setInvitedStatus(completedEvent.getEmailFriend(), true);
            return;
        }
        postEvent(new AlertEvent(getString(R.string.friend_invite_failed)));
        setInvitedStatus(completedEvent.getEmailFriend(), false);
    }

    private void setInvitedStatus(EmailFriend emailFriend, boolean z) {
        EmailFriend emailFriend2 = (EmailFriend) Enumerable.firstOrDefault(this.existingFriends, new ReturningFunction1() {
            public final Object execute(Object obj) {
                return Boolean.valueOf(Strings.equals(EmailFriend.this.getMfpUsername(), ((EmailFriend) obj).getMfpUsername()));
            }
        });
        if (emailFriend2 != null) {
            emailFriend2.setHasBeenInvited(z);
            ListViewUtils.notifyDataSetChanged(this.list);
        }
    }

    /* access modifiers changed from: protected */
    public void onRefreshComplete(List<EmailFriend> list, List<EmailFriend> list2) {
        this.mfpFriends = list != null ? list : new ArrayList<>();
        this.nonMfpFriends = list2;
        List<EmailFriend> list3 = this.existingFriends;
        if (list3 != null) {
            if (!this.dedupedExisting) {
                this.mfpFriends = dedupe(list3, list);
                this.dedupedExisting = true;
            }
            SeparatedListAdapter separatedListAdapter = new SeparatedListAdapter(this, R.layout.add_friends_header, R.id.text);
            List existingFriendsNotInChallenge = getExistingFriendsNotInChallenge();
            if (CollectionUtils.notEmpty((Collection<?>) existingFriendsNotInChallenge)) {
                Collections.sort(existingFriendsNotInChallenge, $$Lambda$InviteContactsToChallengeActivity$Dhvanr1EPaCgDyc5nlkmczhZJdE.INSTANCE);
                separatedListAdapter.addSection(getString(R.string.invite_friend_existing_friends_title), new FriendAdapter(this, existingFriendsNotInChallenge));
            }
            this.list.setAdapter(separatedListAdapter);
            postEvent(new FriendListRefreshedEvent());
            onFinishedProcessing();
            ((ChallengesAnalyticsHelper) this.challengesAnalyticsHelper.get()).reportInviteFriendsScreenViewedEvent(this.challengeName, CollectionUtils.size((Collection<?>) existingFriendsNotInChallenge), this.challengeId);
        }
    }

    private List<EmailFriend> getExistingFriendsNotInChallenge() {
        if (!CollectionUtils.notEmpty((Collection<?>) this.friendUserIdsInChallenge)) {
            return this.existingFriends;
        }
        ArrayList arrayList = new ArrayList();
        Set createExistingFriendsSet = createExistingFriendsSet();
        for (EmailFriend emailFriend : this.existingFriends) {
            if (!createExistingFriendsSet.contains(emailFriend.getMfpUsername())) {
                arrayList.add(emailFriend);
            }
        }
        return arrayList;
    }

    private Set<String> createExistingFriendsSet() {
        HashSet hashSet = new HashSet();
        Iterator it = this.friendUserIdsInChallenge.iterator();
        while (it.hasNext()) {
            hashSet.add((String) it.next());
        }
        return hashSet;
    }

    /* access modifiers changed from: protected */
    public View getFriendView(EmailFriend emailFriend, View view) {
        if (!(view instanceof ChallengeInviteContactRowView)) {
            view = new ChallengeInviteContactRowView(this);
        }
        ChallengeInviteContactRowView challengeInviteContactRowView = (ChallengeInviteContactRowView) view;
        challengeInviteContactRowView.setFriend(emailFriend);
        return challengeInviteContactRowView;
    }

    @Subscribe
    public void onFetchFriendsCompleted(FetchExistingFriendsTask.CompletedEvent completedEvent) {
        this.existingFriends = completedEvent.successful() ? (List) completedEvent.getResult() : new ArrayList<>();
        onRefreshComplete(this.mfpFriends, this.nonMfpFriends);
    }

    @Subscribe
    public void onFriendListRefreshedEvent(FriendListRefreshedEvent friendListRefreshedEvent) {
        if (this.fromInterstitial) {
            sendFriendInterstitialDisplayedEventToAnalytics();
        }
    }

    private static List<EmailFriend> dedupe(List<EmailFriend> list, List<EmailFriend> list2) {
        if (CollectionUtils.isEmpty((Collection<?>) list)) {
            return list2;
        }
        HashSet hashSet = new HashSet();
        for (EmailFriend mfpUsername : list) {
            hashSet.add(mfpUsername.getMfpUsername());
        }
        ArrayList arrayList = new ArrayList();
        if (CollectionUtils.notEmpty((Collection<?>) list2)) {
            for (EmailFriend emailFriend : list2) {
                if (!hashSet.contains(emailFriend.getMfpUsername())) {
                    arrayList.add(emailFriend);
                }
            }
        }
        return arrayList;
    }
}
