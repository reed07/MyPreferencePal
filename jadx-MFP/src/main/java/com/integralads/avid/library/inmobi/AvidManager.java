package com.integralads.avid.library.inmobi;

import android.app.Activity;
import android.content.Context;
import com.integralads.avid.library.inmobi.AvidLoader.AvidLoaderListener;
import com.integralads.avid.library.inmobi.AvidStateWatcher.AvidStateWatcherListener;
import com.integralads.avid.library.inmobi.activity.AvidActivityStack;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistryListener;
import com.integralads.avid.library.inmobi.session.AbstractAvidAdSession;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;

public class AvidManager implements AvidLoaderListener, AvidStateWatcherListener, AvidAdSessionRegistryListener {
    private static AvidManager avidManagerInstance = new AvidManager();
    private static Context context;

    public static AvidManager getInstance() {
        return avidManagerInstance;
    }

    public void init(Context context2) {
        if (context == null) {
            context = context2.getApplicationContext();
            AvidStateWatcher.getInstance().init(context);
            AvidAdSessionRegistry.getInstance().setListener(this);
            AvidJSONUtil.init(context);
        }
    }

    public void registerAvidAdSession(AbstractAvidAdSession abstractAvidAdSession, InternalAvidAdSession internalAvidAdSession) {
        AvidAdSessionRegistry.getInstance().registerAvidAdSession(abstractAvidAdSession, internalAvidAdSession);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String str) {
        return AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionById(str);
    }

    public void registerActivity(Activity activity) {
        AvidActivityStack.getInstance().addActivity(activity);
    }

    private void start() {
        AvidStateWatcher.getInstance().setStateWatcherListener(this);
        AvidStateWatcher.getInstance().start();
        if (AvidStateWatcher.getInstance().isActive()) {
            AvidTreeWalker.getInstance().start();
        }
    }

    private void stop() {
        AvidActivityStack.getInstance().cleanup();
        AvidTreeWalker.getInstance().stop();
        AvidStateWatcher.getInstance().stop();
        AvidLoader.getInstance().unregisterAvidLoader();
        context = null;
    }

    private boolean isActive() {
        return !AvidAdSessionRegistry.getInstance().isEmpty();
    }

    private void notifyAvidReady() {
        AvidAdSessionRegistry.getInstance().setListener(null);
        for (InternalAvidAdSession avidBridgeManager : AvidAdSessionRegistry.getInstance().getInternalAvidAdSessions()) {
            avidBridgeManager.getAvidBridgeManager().onAvidJsReady();
        }
        AvidAdSessionRegistry.getInstance().setListener(this);
    }

    public void onAvidLoaded() {
        if (isActive()) {
            notifyAvidReady();
            if (AvidAdSessionRegistry.getInstance().hasActiveSessions()) {
                start();
            }
        }
    }

    public void onAppStateChanged(boolean z) {
        if (z) {
            AvidTreeWalker.getInstance().start();
        } else {
            AvidTreeWalker.getInstance().pause();
        }
    }

    public void registryHasSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry) {
        if (!avidAdSessionRegistry.isEmpty() && !AvidBridge.isAvidJsReady()) {
            AvidLoader.getInstance().setListener(this);
            AvidLoader.getInstance().registerAvidLoader(context);
        }
    }

    public void registryHasActiveSessionsChanged(AvidAdSessionRegistry avidAdSessionRegistry) {
        if (!avidAdSessionRegistry.hasActiveSessions() || !AvidBridge.isAvidJsReady()) {
            stop();
        } else {
            start();
        }
    }
}
