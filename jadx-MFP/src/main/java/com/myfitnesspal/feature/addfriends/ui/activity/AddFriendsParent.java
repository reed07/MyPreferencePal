package com.myfitnesspal.feature.addfriends.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.BindView;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.friends.ui.activity.InviteFriendActivity;
import com.myfitnesspal.shared.constants.Constants.Extras;
import com.myfitnesspal.shared.constants.Constants.Facebook.Login;
import com.myfitnesspal.shared.constants.Constants.Facebook.Permissions;
import com.myfitnesspal.shared.service.facebook.FacebookService;
import com.myfitnesspal.shared.ui.activity.MfpActivity;
import com.myfitnesspal.shared.ui.mixin.LegacyAlertMixin;
import com.myfitnesspal.shared.util.OrientationUtils;
import com.uacf.core.util.ExtrasUtils;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Function2;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.Arrays;
import java.util.Collection;
import javax.inject.Inject;

public class AddFriendsParent extends MfpActivity implements OnClickListener {
    @BindView(2131361871)
    View btnContacts;
    @BindView(2131361870)
    View btnEmail;
    @BindView(2131361872)
    View btnFb;
    private CallbackManager facebookCallbackManager;
    @Inject
    Lazy<FacebookService> facebookService;
    private boolean isFacebookRedirect;

    public static Intent newStartIntent(Context context) {
        return new Intent(context, AddFriendsParent.class);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.add_friends_parent);
        component().inject(this);
        this.facebookCallbackManager = Factory.create();
        this.isFacebookRedirect = ExtrasUtils.getBoolean(getIntent(), Extras.FACEBOOK_REDIRECT, false);
        setTitle(R.string.addfriends_title);
        setupItem(this.btnFb, R.drawable.ic_fb, R.string.addfriends_parent_facebook_title, R.string.addfriends_parent_facebook_subtitle);
        setupItem(this.btnContacts, R.drawable.ic_contacts, R.string.addfriends_parent_contacts_title, R.string.addfriends_parent_contacts_subtitle);
        setupItem(this.btnEmail, R.drawable.ic_email, R.string.addfriends_parent_email_title, R.string.addfriends_parent_email_subtitle);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.isFacebookRedirect) {
            checkFacebookLoggingStatusAndNavigateToAddFacebookFriends();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.facebookCallbackManager.onActivityResult(i, i2, intent);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    private void setupItem(View view, int i, int i2, int i3) {
        view.findViewById(R.id.image).setBackgroundResource(i);
        ((TextView) view.findViewById(R.id.text_title)).setText(i2);
        ((TextView) view.findViewById(R.id.text_subtitle)).setText(i3);
        view.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.btnFb) {
            checkFacebookLoggingStatusAndNavigateToAddFacebookFriends();
        } else if (view == this.btnContacts) {
            getNavigationHelper().withIntent(AddFriendsContacts.newStartIntent(this)).startActivity();
        } else if (view == this.btnEmail) {
            getNavigationHelper().withIntent(InviteFriendActivity.newStartIntent(this)).startActivity(18);
        }
    }

    private void checkFacebookLoggingStatusAndNavigateToAddFacebookFriends() {
        if (!(AccessToken.getCurrentAccessToken() != null)) {
            invokeFacebookLogin();
        } else if (AccessToken.getCurrentAccessToken().getPermissions().contains(Permissions.USER_FRIENDS)) {
            setBusy(false);
            getNavigationHelper().withIntent(AddFriendsFacebook.newStartIntent(this)).startActivity();
        } else {
            requestFacebookFriendsPermission();
        }
    }

    /* access modifiers changed from: private */
    public void connectFacebook() {
        setBusy(true);
        ((FacebookService) this.facebookService.get()).connect(this, new Function1() {
            public final void execute(Object obj) {
                AddFriendsParent.lambda$connectFacebook$0(AddFriendsParent.this, (String) obj);
            }
        }, new Function2() {
            public final void execute(Object obj, Object obj2) {
                AddFriendsParent.lambda$connectFacebook$1(AddFriendsParent.this, (Integer) obj, (String) obj2);
            }
        });
    }

    public static /* synthetic */ void lambda$connectFacebook$0(AddFriendsParent addFriendsParent, String str) throws RuntimeException {
        addFriendsParent.setBusy(false);
        addFriendsParent.getNavigationHelper().withIntent(AddFriendsFacebook.newStartIntent(addFriendsParent)).startActivity();
    }

    public static /* synthetic */ void lambda$connectFacebook$1(AddFriendsParent addFriendsParent, Integer num, String str) throws RuntimeException {
        String str2;
        if (num.intValue() != 2000) {
            if (!Strings.notEmpty(str)) {
                str = addFriendsParent.getString(R.string.failAssociateFacebookUser);
            }
            str2 = addFriendsParent.getString(R.string.dismiss);
        } else {
            str = addFriendsParent.getString(R.string.underage_facebook_connect);
            str2 = addFriendsParent.getString(R.string.ok);
        }
        addFriendsParent.setBusy(false);
        ((LegacyAlertMixin) addFriendsParent.mixin(LegacyAlertMixin.class)).showAlertDialogWithTitle(addFriendsParent.getString(R.string.error), str, str2);
    }

    private void requestFacebookFriendsPermission() {
        LoginManager.getInstance().registerCallback(this.facebookCallbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                OrientationUtils.unlockOrientation(AddFriendsParent.this.getActivity());
                AddFriendsParent.this.getNavigationHelper().withIntent(AddFriendsFacebook.newStartIntent(AddFriendsParent.this)).startActivity();
            }

            public void onCancel() {
                Ln.d("CANCELLED", new Object[0]);
                OrientationUtils.unlockOrientation(AddFriendsParent.this.getActivity());
                AddFriendsParent.this.setBusy(false);
            }

            public void onError(FacebookException facebookException) {
                StringBuilder sb = new StringBuilder();
                sb.append("ERROR: %s");
                sb.append(facebookException.toString());
                Ln.d(sb.toString(), new Object[0]);
                OrientationUtils.unlockOrientation(AddFriendsParent.this.getActivity());
                AddFriendsParent.this.setBusy(false);
            }
        });
        setBusy(true);
        OrientationUtils.lockOrientation(getActivity());
        LoginManager.getInstance().logInWithReadPermissions((Activity) this, (Collection<String>) Arrays.asList(new String[]{Permissions.USER_FRIENDS}));
    }

    private void invokeFacebookLogin() {
        LoginManager.getInstance().registerCallback(this.facebookCallbackManager, new FacebookCallback<LoginResult>() {
            public void onSuccess(LoginResult loginResult) {
                Ln.v("invokeFacebookLogin success", new Object[0]);
                OrientationUtils.unlockOrientation(AddFriendsParent.this.getActivity());
                AddFriendsParent.this.connectFacebook();
            }

            public void onCancel() {
                Ln.v("invokeFacebookLogin cancelled", new Object[0]);
                OrientationUtils.unlockOrientation(AddFriendsParent.this.getActivity());
                AddFriendsParent.this.setBusy(false);
            }

            public void onError(FacebookException facebookException) {
                Ln.v("invokeFacebookLogin error", facebookException);
                OrientationUtils.unlockOrientation(AddFriendsParent.this.getActivity());
                AddFriendsParent.this.setBusy(false);
            }
        });
        OrientationUtils.lockOrientation(getActivity());
        LoginManager.getInstance().logInWithReadPermissions((Activity) this, Login.PERMISSIONS_PLUS_FRIENDS);
    }
}
