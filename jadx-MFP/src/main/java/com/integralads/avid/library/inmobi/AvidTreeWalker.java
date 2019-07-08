package com.integralads.avid.library.inmobi;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.view.View;
import com.integralads.avid.library.inmobi.processing.AvidProcessorFactory;
import com.integralads.avid.library.inmobi.processing.IAvidNodeProcessor;
import com.integralads.avid.library.inmobi.processing.IAvidNodeProcessor.IAvidViewWalker;
import com.integralads.avid.library.inmobi.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.integralads.avid.library.inmobi.utils.AvidTimestamp;
import com.integralads.avid.library.inmobi.utils.AvidViewUtil;
import com.integralads.avid.library.inmobi.walking.AvidAdViewCache;
import com.integralads.avid.library.inmobi.walking.AvidStatePublisher;
import com.integralads.avid.library.inmobi.walking.ViewType;
import com.integralads.avid.library.inmobi.walking.async.AvidAsyncTaskQueue;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class AvidTreeWalker implements IAvidViewWalker {
    private static AvidTreeWalker avidTreeWalker = new AvidTreeWalker();
    /* access modifiers changed from: private */
    public static TreeWalkerHandler handler;
    /* access modifiers changed from: private */
    public static final Runnable viewTreeUpdaterRunnable = new Runnable() {
        public final void run() {
            if (AvidTreeWalker.handler != null) {
                AvidTreeWalker.handler.sendEmptyMessage(0);
                AvidTreeWalker.handler.postDelayed(AvidTreeWalker.viewTreeUpdaterRunnable, 200);
            }
        }
    };
    private AvidAdViewCache adViewCache = new AvidAdViewCache(AvidAdSessionRegistry.getInstance());
    private double endTime;
    private int objectsCount;
    private AvidProcessorFactory processorFactory = new AvidProcessorFactory();
    private double startTime;
    private AvidStatePublisher statePublisher = new AvidStatePublisher(AvidAdSessionRegistry.getInstance(), new AvidAsyncTaskQueue());
    private List<AvidTreeWalkerTimeLogger> timeLoggers = new ArrayList();

    public interface AvidTreeWalkerTimeLogger {
        void onTreeProcessed(int i, long j);
    }

    private static class TreeWalkerHandler extends Handler {
        private TreeWalkerHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            AvidTreeWalker.getInstance().updateTreeState();
        }
    }

    public static AvidTreeWalker getInstance() {
        return avidTreeWalker;
    }

    public void start() {
        startTreeWalkerUpdater();
        updateTreeState();
    }

    public void stop() {
        pause();
        this.timeLoggers.clear();
        this.statePublisher.cleanupCache();
    }

    public void pause() {
        stopTreeWalkerUpdater();
    }

    /* access modifiers changed from: private */
    public void updateTreeState() {
        beforeWalk();
        doWalk();
        afterWalk();
    }

    private void beforeWalk() {
        this.objectsCount = 0;
        this.startTime = AvidTimestamp.getCurrentTime();
    }

    private void afterWalk() {
        this.endTime = AvidTimestamp.getCurrentTime();
        notifyTimeLogger((long) (this.endTime - this.startTime));
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void doWalk() {
        this.adViewCache.prepare();
        double currentTime = AvidTimestamp.getCurrentTime();
        IAvidNodeProcessor rootProcessor = this.processorFactory.getRootProcessor();
        if (this.adViewCache.getHiddenSessionIds().size() > 0) {
            this.statePublisher.publishEmptyState(rootProcessor.getState(null), this.adViewCache.getHiddenSessionIds(), currentTime);
        }
        if (this.adViewCache.getVisibleSessionIds().size() > 0) {
            JSONObject state = rootProcessor.getState(null);
            walkViewChildren(null, rootProcessor, state, ViewType.ROOT_VIEW);
            AvidJSONUtil.fixStateFrame(state);
            this.statePublisher.publishState(state, this.adViewCache.getVisibleSessionIds(), currentTime);
        } else {
            this.statePublisher.cleanupCache();
        }
        this.adViewCache.cleanup();
    }

    public void walkView(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject) {
        if (AvidViewUtil.isViewVisible(view)) {
            ViewType viewType = this.adViewCache.getViewType(view);
            if (viewType != ViewType.UNDERLYING_VIEW) {
                JSONObject state = iAvidNodeProcessor.getState(view);
                AvidJSONUtil.addChildState(jSONObject, state);
                if (!handleAdView(view, state)) {
                    checkFriendlyObstruction(view, state);
                    walkViewChildren(view, iAvidNodeProcessor, state, viewType);
                }
                this.objectsCount++;
            }
        }
    }

    private void walkViewChildren(View view, IAvidNodeProcessor iAvidNodeProcessor, JSONObject jSONObject, ViewType viewType) {
        iAvidNodeProcessor.iterateChildren(view, jSONObject, this, viewType == ViewType.ROOT_VIEW);
    }

    private boolean handleAdView(View view, JSONObject jSONObject) {
        String sessionId = this.adViewCache.getSessionId(view);
        if (sessionId == null) {
            return false;
        }
        AvidJSONUtil.addAvidId(jSONObject, sessionId);
        this.adViewCache.onAdViewProcessed();
        return true;
    }

    private void checkFriendlyObstruction(View view, JSONObject jSONObject) {
        ArrayList friendlySessionIds = this.adViewCache.getFriendlySessionIds(view);
        if (friendlySessionIds != null) {
            AvidJSONUtil.addFriendlyObstruction(jSONObject, friendlySessionIds);
        }
    }

    private void notifyTimeLogger(long j) {
        if (this.timeLoggers.size() > 0) {
            for (AvidTreeWalkerTimeLogger onTreeProcessed : this.timeLoggers) {
                onTreeProcessed.onTreeProcessed(this.objectsCount, j);
            }
        }
    }

    private void startTreeWalkerUpdater() {
        if (handler == null) {
            TreeWalkerHandler treeWalkerHandler = new TreeWalkerHandler();
            handler = treeWalkerHandler;
            treeWalkerHandler.postDelayed(viewTreeUpdaterRunnable, 200);
        }
    }

    private void stopTreeWalkerUpdater() {
        TreeWalkerHandler treeWalkerHandler = handler;
        if (treeWalkerHandler != null) {
            treeWalkerHandler.removeCallbacks(viewTreeUpdaterRunnable);
            handler = null;
        }
    }
}
