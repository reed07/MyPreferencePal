package com.myfitnesspal.feature.addfriends.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.addfriends.event.FriendListRefreshedEvent;
import com.myfitnesspal.feature.addfriends.service.ContactsOnMfpService;
import com.myfitnesspal.feature.addfriends.service.InviteSucceeded;
import com.myfitnesspal.feature.addfriends.ui.view.AddFriendsItem;
import com.myfitnesspal.feature.addfriends.ui.view.AddFriendsItemContacts;
import com.myfitnesspal.feature.permissions.PermissionsMixin;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.Analytics.Screens;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.model.v1.EmailFriend;
import com.myfitnesspal.shared.service.analytics.ActionTrackingService;
import com.myfitnesspal.shared.ui.dialog.AlertDialogFragmentBase.DialogNegativeListener;
import com.myfitnesspal.shared.ui.dialog.DialogListTextItem;
import com.myfitnesspal.shared.ui.dialog.MfpAlertDialogBuilder;
import com.myfitnesspal.shared.util.UniqueId;
import com.squareup.otto.Subscribe;
import com.uacf.core.util.BundleUtils;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

public class AddFriendsContacts extends AddFriendsBase<ContactsOnMfpService, EmailFriend> {
    private static final int ACTION_COMPLETE = UniqueId.next();
    @Inject
    Lazy<ActionTrackingService> actionTrackingService;
    @Inject
    Lazy<ContactsOnMfpService> contactsService;
    private boolean fromInterstitial;
    /* access modifiers changed from: private */
    public boolean showPermissionDenied;

    public String getAnalyticsScreenTag() {
        return Screens.ADD_FRIENDS_CONTACTS;
    }

    /* access modifiers changed from: protected */
    public int getEmptyTextId() {
        return R.string.addfriends_empty_contacts;
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

    public static Intent newStartIntent(Context context) {
        return new Intent(context, AddFriendsContacts.class);
    }

    public void onCreate(Bundle bundle) {
        component().inject(this);
        super.onCreate(bundle);
        setTitle(R.string.addfriends_title_contacts);
        this.fromInterstitial = ExtrasUtils.getBoolean(getIntent(), Extras.FROM_INTERSTITIAL);
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
        checkPermission();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        this.ignoreRefresh = true;
        super.onResume();
        if (this.showPermissionDenied) {
            showPermissionDeniedDialog();
        }
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
    public ContactsOnMfpService getService() {
        return (ContactsOnMfpService) this.contactsService.get();
    }

    /* access modifiers changed from: protected */
    public View getFriendView(EmailFriend emailFriend, View view) {
        if (!(view instanceof AddFriendsItemContacts)) {
            view = new AddFriendsItemContacts(this);
        }
        AddFriendsItemContacts addFriendsItemContacts = (AddFriendsItemContacts) view;
        addFriendsItemContacts.setFriend(emailFriend);
        return addFriendsItemContacts;
    }

    public boolean onInviteClicked(AddFriendsItem<EmailFriend> addFriendsItem, InviteSucceeded inviteSucceeded) {
        EmailFriend emailFriend = (EmailFriend) addFriendsItem.getFriend();
        if (!super.onInviteClicked(addFriendsItem, inviteSucceeded)) {
            if (emailFriend.hasMultipleEmailAddresses()) {
                Set<String> emailAddresses = emailFriend.getEmailAddresses();
                ArrayList arrayList = new ArrayList(CollectionUtils.size((Collection<?>) emailAddresses));
                for (String dialogListTextItem : emailAddresses) {
                    arrayList.add(new DialogListTextItem(dialogListTextItem));
                }
                new MfpAlertDialogBuilder(this).setItems(arrayList, new OnItemClickListener(emailFriend, arrayList, inviteSucceeded) {
                    private final /* synthetic */ EmailFriend f$1;
                    private final /* synthetic */ List f$2;
                    private final /* synthetic */ InviteSucceeded f$3;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                        this.f$3 = r4;
                    }

                    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                        AddFriendsContacts.this.sendInvite(this.f$1, ((DialogListTextItem) this.f$2.get(i)).getText(), this.f$3);
                    }
                }).setTitle((CharSequence) getString(R.string.addfriends_multiple_email_title, new Object[]{emailFriend.getName()})).setOnCancelListener(new OnCancelListener() {
                    public final void onCancel(DialogInterface dialogInterface) {
                        InviteSucceeded.this.execute(Boolean.valueOf(false));
                    }
                }).setNegativeButton((int) R.string.cancel, (OnClickListener) new OnClickListener() {
                    public final void onClick(DialogInterface dialogInterface, int i) {
                        InviteSucceeded.this.execute(Boolean.valueOf(false));
                    }
                }).show();
            } else {
                String firstEmailAddress = emailFriend.getFirstEmailAddress();
                if (Strings.notEmpty(firstEmailAddress)) {
                    sendInvite(emailFriend, firstEmailAddress, inviteSucceeded);
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void sendInvite(EmailFriend emailFriend, String str, InviteSucceeded inviteSucceeded) {
        getService().sendExternalInvites(Arrays.asList(new EmailFriend[]{emailFriend}), str, inviteSucceeded);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 18) {
            String string = ExtrasUtils.getString(intent, Extras.FRIEND_TO_INVITE, (String) null);
            if (Strings.notEmpty(string)) {
                updateNonMfpUserState(string, i2 == -1);
            }
        } else if (i == 205) {
            checkPermission();
        }
    }

    private void checkPermission() {
        ((PermissionsMixin) mixin(PermissionsMixin.class)).checkPermission("android.permission.READ_CONTACTS", new Function0() {
            public final Object invoke() {
                return AddFriendsContacts.lambda$checkPermission$3(AddFriendsContacts.this);
            }
        }, new Function0() {
            public final Object invoke() {
                return AddFriendsContacts.this.finish();
            }
        }, new Function0() {
            public final Object invoke() {
                return AddFriendsContacts.this.showPermissionDenied = true;
            }
        }, new Function0() {
            public final Object invoke() {
                return AddFriendsContacts.this.finish();
            }
        });
    }

    public static /* synthetic */ Unit lambda$checkPermission$3(AddFriendsContacts addFriendsContacts) {
        addFriendsContacts.ignoreRefresh = false;
        addFriendsContacts.showPermissionDenied = false;
        addFriendsContacts.beginRefresh();
        if (addFriendsContacts.fromInterstitial && addFriendsContacts.mfpFriends != null) {
            addFriendsContacts.sendFriendInterstitialDisplayedEventToAnalytics();
        }
        return null;
    }

    @Subscribe
    public void onFriendListRefreshedEvent(FriendListRefreshedEvent friendListRefreshedEvent) {
        if (this.fromInterstitial) {
            sendFriendInterstitialDisplayedEventToAnalytics();
        }
    }

    private void showPermissionDeniedDialog() {
        ((PermissionsMixin) mixin(PermissionsMixin.class)).showPermissionDeniedDialog(R.string.permission_settings_contacts, new DialogNegativeListener() {
            public final void onClick() {
                AddFriendsContacts.this.finish();
            }
        });
    }
}
