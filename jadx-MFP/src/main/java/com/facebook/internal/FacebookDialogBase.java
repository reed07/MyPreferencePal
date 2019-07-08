package com.facebook.internal;

import android.app.Activity;
import android.util.Log;
import com.brightcove.player.event.AbstractEvent;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookDialog;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.util.Iterator;
import java.util.List;

public abstract class FacebookDialogBase<CONTENT, RESULT> implements FacebookDialog<CONTENT, RESULT> {
    protected static final Object BASE_AUTOMATIC_MODE = new Object();
    private static final String TAG = "FacebookDialog";
    private final Activity activity;
    private final FragmentWrapper fragmentWrapper;
    private List<ModeHandler> modeHandlers;
    private int requestCode;

    protected abstract class ModeHandler {
        public abstract boolean canShow(CONTENT content, boolean z);

        public abstract AppCall createAppCall(CONTENT content);

        protected ModeHandler() {
        }

        public Object getMode() {
            return FacebookDialogBase.BASE_AUTOMATIC_MODE;
        }
    }

    /* access modifiers changed from: protected */
    public abstract AppCall createBaseAppCall();

    /* access modifiers changed from: protected */
    public abstract List<ModeHandler> getOrderedModeHandlers();

    /* access modifiers changed from: protected */
    public abstract void registerCallbackImpl(CallbackManagerImpl callbackManagerImpl, FacebookCallback<RESULT> facebookCallback);

    protected FacebookDialogBase(Activity activity2, int i) {
        Validate.notNull(activity2, AbstractEvent.ACTIVITY);
        this.activity = activity2;
        this.fragmentWrapper = null;
        this.requestCode = i;
    }

    protected FacebookDialogBase(FragmentWrapper fragmentWrapper2, int i) {
        Validate.notNull(fragmentWrapper2, "fragmentWrapper");
        this.fragmentWrapper = fragmentWrapper2;
        this.activity = null;
        this.requestCode = i;
        if (fragmentWrapper2.getActivity() == null) {
            throw new IllegalArgumentException("Cannot use a fragment that is not attached to an activity");
        }
    }

    public final void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback) {
        if (callbackManager instanceof CallbackManagerImpl) {
            registerCallbackImpl((CallbackManagerImpl) callbackManager, facebookCallback);
            return;
        }
        throw new FacebookException("Unexpected CallbackManager, please use the provided Factory.");
    }

    public final void registerCallback(CallbackManager callbackManager, FacebookCallback<RESULT> facebookCallback, int i) {
        setRequestCode(i);
        registerCallback(callbackManager, facebookCallback);
    }

    /* access modifiers changed from: protected */
    public void setRequestCode(int i) {
        if (!FacebookSdk.isFacebookRequestCode(i)) {
            this.requestCode = i;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Request code ");
        sb.append(i);
        sb.append(" cannot be within the range reserved by the Facebook SDK.");
        throw new IllegalArgumentException(sb.toString());
    }

    public int getRequestCode() {
        return this.requestCode;
    }

    public boolean canShow(CONTENT content) {
        return canShowImpl(content, BASE_AUTOMATIC_MODE);
    }

    /* access modifiers changed from: protected */
    public boolean canShowImpl(CONTENT content, Object obj) {
        boolean z = obj == BASE_AUTOMATIC_MODE;
        for (ModeHandler modeHandler : cachedModeHandlers()) {
            if ((z || Utility.areObjectsEqual(modeHandler.getMode(), obj)) && modeHandler.canShow(content, false)) {
                return true;
            }
        }
        return false;
    }

    public void show(CONTENT content) {
        showImpl(content, BASE_AUTOMATIC_MODE);
    }

    /* access modifiers changed from: protected */
    public void showImpl(CONTENT content, Object obj) {
        AppCall createAppCallForMode = createAppCallForMode(content, obj);
        if (createAppCallForMode != null) {
            FragmentWrapper fragmentWrapper2 = this.fragmentWrapper;
            if (fragmentWrapper2 != null) {
                DialogPresenter.present(createAppCallForMode, fragmentWrapper2);
            } else {
                DialogPresenter.present(createAppCallForMode, this.activity);
            }
        } else {
            String str = "No code path should ever result in a null appCall";
            Log.e(TAG, str);
            if (FacebookSdk.isDebugEnabled()) {
                throw new IllegalStateException(str);
            }
        }
    }

    /* access modifiers changed from: protected */
    public Activity getActivityContext() {
        Activity activity2 = this.activity;
        if (activity2 != null) {
            return activity2;
        }
        FragmentWrapper fragmentWrapper2 = this.fragmentWrapper;
        if (fragmentWrapper2 != null) {
            return fragmentWrapper2.getActivity();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void startActivityForResult(android.content.Intent r3, int r4) {
        /*
            r2 = this;
            android.app.Activity r0 = r2.activity
            if (r0 == 0) goto L_0x0008
            r0.startActivityForResult(r3, r4)
            goto L_0x002d
        L_0x0008:
            com.facebook.internal.FragmentWrapper r0 = r2.fragmentWrapper
            if (r0 == 0) goto L_0x0032
            android.app.Fragment r0 = r0.getNativeFragment()
            if (r0 == 0) goto L_0x001c
            com.facebook.internal.FragmentWrapper r0 = r2.fragmentWrapper
            android.app.Fragment r0 = r0.getNativeFragment()
            r0.startActivityForResult(r3, r4)
            goto L_0x002d
        L_0x001c:
            com.facebook.internal.FragmentWrapper r0 = r2.fragmentWrapper
            android.support.v4.app.Fragment r0 = r0.getSupportFragment()
            if (r0 == 0) goto L_0x002f
            com.facebook.internal.FragmentWrapper r0 = r2.fragmentWrapper
            android.support.v4.app.Fragment r0 = r0.getSupportFragment()
            r0.startActivityForResult(r3, r4)
        L_0x002d:
            r3 = 0
            goto L_0x0034
        L_0x002f:
            java.lang.String r3 = "Failed to find Activity or Fragment to startActivityForResult "
            goto L_0x0034
        L_0x0032:
            java.lang.String r3 = "Failed to find Activity or Fragment to startActivityForResult "
        L_0x0034:
            if (r3 == 0) goto L_0x0044
            com.facebook.LoggingBehavior r4 = com.facebook.LoggingBehavior.DEVELOPER_ERRORS
            r0 = 6
            java.lang.Class r1 = r2.getClass()
            java.lang.String r1 = r1.getName()
            com.facebook.internal.Logger.log(r4, r0, r1, r3)
        L_0x0044:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.internal.FacebookDialogBase.startActivityForResult(android.content.Intent, int):void");
    }

    private AppCall createAppCallForMode(CONTENT content, Object obj) {
        boolean z = obj == BASE_AUTOMATIC_MODE;
        AppCall appCall = null;
        Iterator it = cachedModeHandlers().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ModeHandler modeHandler = (ModeHandler) it.next();
            if ((z || Utility.areObjectsEqual(modeHandler.getMode(), obj)) && modeHandler.canShow(content, true)) {
                try {
                    appCall = modeHandler.createAppCall(content);
                    break;
                } catch (FacebookException e) {
                    appCall = createBaseAppCall();
                    DialogPresenter.setupAppCallForValidationError(appCall, e);
                }
            }
        }
        if (appCall != null) {
            return appCall;
        }
        AppCall createBaseAppCall = createBaseAppCall();
        DialogPresenter.setupAppCallForCannotShowError(createBaseAppCall);
        return createBaseAppCall;
    }

    private List<ModeHandler> cachedModeHandlers() {
        if (this.modeHandlers == null) {
            this.modeHandlers = getOrderedModeHandlers();
        }
        return this.modeHandlers;
    }
}
