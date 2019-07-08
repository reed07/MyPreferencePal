package com.myfitnesspal.feature.addfriends.ui.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import butterknife.BindView;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.event.FriendListRefreshedEvent;
import com.myfitnesspal.feature.addfriends.service.FriendOnMfpService;
import com.myfitnesspal.feature.addfriends.service.FriendOnMfpService.MfpCheckListener;
import com.myfitnesspal.feature.addfriends.service.InviteSucceeded;
import com.myfitnesspal.feature.addfriends.ui.view.AddFriendsItem;
import com.myfitnesspal.feature.addfriends.ui.view.AddFriendsItem.InviteClickedListener;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.model.v1.Friend;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.adapter.SeparatedListAdapter;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public abstract class AddFriendsBase<TService extends FriendOnMfpService, TFriend extends Friend> extends MfpActivity implements InviteClickedListener<TFriend> {
    static final int ALREADY_FRIEND_CODE = 256;
    @BindView(16908292)
    TextView empty;
    protected boolean ignoreRefresh;
    protected List<String> justJoinedFbFriendsNames;
    @BindView(16908298)
    protected ListView list;
    protected List<TFriend> mfpFriends;
    protected List<TFriend> nonMfpFriends;
    @BindView(2131363768)
    ViewSwitcher switcher;

    protected static class FriendAdapter<TFriendType extends Friend> extends ArrayAdapter<TFriendType> {
        public boolean areAllItemsEnabled() {
            return true;
        }

        public boolean isEnabled(int i) {
            return false;
        }

        public FriendAdapter(AddFriendsBase addFriendsBase, List<TFriendType> list) {
            super(addFriendsBase, 17367043, list.toArray((Friend[]) Array.newInstance(((Friend) list.get(0)).getClass(), list.size())));
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            AddFriendsBase addFriendsBase = (AddFriendsBase) getContext();
            AddFriendsItem addFriendsItem = (AddFriendsItem) addFriendsBase.getFriendView((Friend) getItem(i), view);
            addFriendsItem.setListener(addFriendsBase);
            return addFriendsItem;
        }
    }

    /* access modifiers changed from: protected */
    public abstract int getEmptyTextId();

    /* access modifiers changed from: protected */
    public abstract View getFriendView(TFriend tfriend, View view);

    /* access modifiers changed from: protected */
    public abstract int getMfpHeaderForMoreThanOneFriend();

    /* access modifiers changed from: protected */
    public abstract int getMfpHeaderForOneFriend();

    /* access modifiers changed from: protected */
    public abstract int getNonMfpHeader();

    /* access modifiers changed from: protected */
    public abstract TService getService();

    /* access modifiers changed from: protected */
    public boolean shouldDisplayNonMfpContacts() {
        return true;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.add_friends);
        getService().setActivityContext(this);
        getService().clearState();
        this.empty.setText(getEmptyTextId());
        this.list.setEmptyView(this.empty);
    }

    /* access modifiers changed from: protected */
    public void refresh() {
        FriendOnMfpService service = getService();
        service.setListener(new MfpCheckListener<TFriend>() {
            public void onMfpCheckComplete(List<TFriend> list, List<TFriend> list2) {
                String str = "onMfpCheckComplete, found %s friends";
                Object[] objArr = new Object[1];
                objArr[0] = Integer.valueOf(list != null ? list.size() : 0);
                Ln.d(str, objArr);
                AddFriendsBase.this.onRefreshComplete(list, list2);
            }

            public void onMfpCheckFailed(int i, String str) {
                CharSequence charSequence;
                String str2;
                if (i != 2000) {
                    if (!Strings.notEmpty(str)) {
                        str = AddFriendsBase.this.getString(R.string.failAssociateFacebookUser);
                    }
                    charSequence = AddFriendsBase.this.getString(R.string.dismiss);
                    str2 = str;
                } else {
                    String string = AddFriendsBase.this.getString(R.string.underage_facebook_connect);
                    charSequence = AddFriendsBase.this.getString(R.string.ok);
                    str2 = string;
                }
                ((LegacyAlertMixin) AddFriendsBase.this.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitleAndListeners(AddFriendsBase.this.getString(R.string.error), str2, charSequence, new OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        AddFriendsBase.this.finish();
                    }
                }, null, null);
                AddFriendsBase.this.onFinishedProcessing();
            }
        });
        service.loadMfpFriends();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.ignoreRefresh) {
            beginRefresh();
        }
        this.ignoreRefresh = false;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 18) {
            this.ignoreRefresh = true;
        }
        super.onActivityResult(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void beginRefresh() {
        onStartedProcessing();
        if (this.mfpFriends == null && this.nonMfpFriends == null) {
            refresh();
        } else {
            onRefreshComplete(this.mfpFriends, this.nonMfpFriends);
        }
    }

    public boolean onInviteClicked(AddFriendsItem<TFriend> addFriendsItem, InviteSucceeded inviteSucceeded) {
        final Friend friend = addFriendsItem.getFriend();
        if (friend.isOnMfp()) {
            getService().addFriend(Collections.singletonList(friend), getSession().getUser().getUsername(), new Function2<Integer, String>() {
                public void execute(Integer num, String str) {
                    AddFriendsBase.this.getActionTrackingService().registerAndReportEvent(Events.FRIEND_INVITE_SENT);
                    AddFriendsBase.this.updateMfpUserState(friend.getMfpUsername(), num.intValue() == 0 || num.intValue() == 256);
                }
            });
            return true;
        }
        inviteSucceeded.execute(Boolean.valueOf(false));
        return false;
    }

    /* access modifiers changed from: private */
    public void updateMfpUserState(String str, boolean z) {
        BaseAdapter baseAdapter = (BaseAdapter) this.list.getAdapter();
        for (int i = 0; i < baseAdapter.getCount(); i++) {
            Object item = baseAdapter.getItem(i);
            if (item instanceof Friend) {
                Friend friend = (Friend) item;
                if (Strings.equals(friend.getMfpUsername(), str)) {
                    friend.setHasBeenInvited(z);
                    baseAdapter.notifyDataSetChanged();
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateNonMfpUserState(String str, boolean z) {
        if (shouldDisplayNonMfpContacts()) {
            BaseAdapter baseAdapter = (BaseAdapter) this.list.getAdapter();
            for (int i = 0; i < baseAdapter.getCount(); i++) {
                Object item = baseAdapter.getItem(i);
                if (item instanceof Friend) {
                    Friend friend = (Friend) item;
                    if (friend.matches(str)) {
                        friend.setHasBeenInvited(z);
                        baseAdapter.notifyDataSetChanged();
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onRefreshComplete(List<TFriend> list2, List<TFriend> list3) {
        this.mfpFriends = list2;
        this.nonMfpFriends = list3;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (CollectionUtils.notEmpty((Collection<?>) this.justJoinedFbFriendsNames) && CollectionUtils.notEmpty((Collection<?>) list2)) {
            HashMap hashMap = new HashMap(list2.size());
            for (TFriend tfriend : list2) {
                if (tfriend != null) {
                    hashMap.put(tfriend.getMfpUsername(), tfriend);
                }
            }
            for (String str : this.justJoinedFbFriendsNames) {
                if (hashMap.containsKey(str)) {
                    Friend friend = (Friend) hashMap.get(str);
                    arrayList.add(friend);
                    arrayList2.add(friend);
                }
            }
            list2.removeAll(arrayList2);
        }
        SeparatedListAdapter separatedListAdapter = new SeparatedListAdapter(this, R.layout.add_friends_header, R.id.text);
        if (CollectionUtils.notEmpty((Collection<?>) arrayList)) {
            int size = arrayList.size();
            separatedListAdapter.addSection(getResources().getQuantityString(R.plurals.friend_just_joined, size, new Object[]{Integer.valueOf(size)}), new FriendAdapter(this, arrayList));
        }
        if (CollectionUtils.notEmpty((Collection<?>) list2)) {
            separatedListAdapter.addSection(getString(list2.size() > 1 ? getMfpHeaderForMoreThanOneFriend() : getMfpHeaderForOneFriend(), new Object[]{Integer.valueOf(list2.size())}), new FriendAdapter(this, list2));
        }
        if (shouldDisplayNonMfpContacts() && CollectionUtils.notEmpty((Collection<?>) list3) && getIntent().getFlags() != 268435456) {
            separatedListAdapter.addSection(getString(getNonMfpHeader()), new FriendAdapter(this, list3));
        }
        this.list.setAdapter(separatedListAdapter);
        postEvent(new FriendListRefreshedEvent());
        onFinishedProcessing();
    }

    /* access modifiers changed from: protected */
    public void onStartedProcessing() {
        this.switcher.setDisplayedChild(1);
    }

    /* access modifiers changed from: protected */
    public void onFinishedProcessing() {
        this.switcher.setDisplayedChild(0);
    }
}
