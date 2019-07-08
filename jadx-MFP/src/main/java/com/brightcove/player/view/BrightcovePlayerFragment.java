package com.brightcove.player.view;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.brightcove.player.event.AbstractEvent;
import com.brightcove.player.event.Default;
import com.brightcove.player.event.Event;
import com.brightcove.player.event.EventListener;
import com.brightcove.player.event.EventLogger;
import com.brightcove.player.event.EventType;
import com.brightcove.player.event.ListensFor;
import com.brightcove.player.pictureinpicture.PictureInPictureManager;
import com.brightcove.player.util.LifecycleUtil;

@ListensFor(events = {"fragmentSaveInstanceState"})
@TargetApi(11)
public class BrightcovePlayerFragment extends Fragment {
    public static final String TAG = "BrightcovePlayerFragment";
    protected BaseVideoView brightcoveVideoView;
    private EventLogger eventLogger;
    private LifecycleUtil lifecycleUtil;

    public BrightcoveVideoView getBrightcoveVideoView() {
        BaseVideoView baseVideoView = this.brightcoveVideoView;
        if (baseVideoView instanceof BrightcoveVideoView) {
            return (BrightcoveVideoView) baseVideoView;
        }
        return null;
    }

    public BaseVideoView getBaseVideoView() {
        return this.brightcoveVideoView;
    }

    public void fullScreen() {
        if (!this.brightcoveVideoView.isFullScreen()) {
            this.brightcoveVideoView.getEventEmitter().emit(EventType.ENTER_FULL_SCREEN);
        } else {
            Log.e(TAG, "Event emitter is not defined or the video view is already in full screen mode.");
        }
    }

    public void normalScreen() {
        if (this.brightcoveVideoView.isFullScreen()) {
            this.brightcoveVideoView.getEventEmitter().emit(EventType.EXIT_FULL_SCREEN);
        } else {
            Log.e(TAG, "Event emitter is not defined or the video view is not in full screen mode!");
        }
    }

    public void onActivityCreated(Bundle bundle) {
        Log.v(TAG, "onActivityCreated");
        super.onActivityCreated(bundle);
        this.lifecycleUtil.onActivityCreated(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.v(TAG, "onCreateView");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        BaseVideoView baseVideoView = this.brightcoveVideoView;
        if (baseVideoView != null) {
            this.lifecycleUtil = new LifecycleUtil(baseVideoView);
            this.lifecycleUtil.onCreateView(bundle, this);
            this.eventLogger = new EventLogger(this.brightcoveVideoView.getEventEmitter(), true, getClass().getSimpleName());
            return onCreateView;
        }
        throw new IllegalStateException("brightcoveVideoView must be assigned before calling onCreateView().");
    }

    public void onStart() {
        Log.v(TAG, "onStart");
        super.onStart();
        this.lifecycleUtil.fragmentOnStart();
    }

    public void onPause() {
        Log.v(TAG, "onPause");
        super.onPause();
        this.lifecycleUtil.fragmentOnPause();
    }

    public void onResume() {
        Log.v(TAG, "onResume");
        super.onResume();
        this.brightcoveVideoView.getEventEmitter().on(EventType.CHANGE_ORIENTATION, new EventListener() {
            public void processEvent(Event event) {
                BrightcovePlayerFragment.this.getActivity().setRequestedOrientation(event.getIntegerProperty(AbstractEvent.REQUESTED_ORIENTATION));
            }
        });
        this.lifecycleUtil.fragmentOnResume();
    }

    public void onDestroy() {
        Log.v(TAG, "onDestroy");
        super.onDestroy();
        this.lifecycleUtil.fragmentOnDestroy();
    }

    public void onDestroyView() {
        Log.v(TAG, "onDestroyView");
        super.onDestroyView();
        this.lifecycleUtil.onDestroyView();
    }

    public void onDetach() {
        Log.v(TAG, "onDetach");
        super.onDetach();
        this.lifecycleUtil.onDetach();
    }

    public void onStop() {
        Log.v(TAG, "onStop");
        super.onStop();
        this.lifecycleUtil.fragmentOnStop();
    }

    public void onSaveInstanceState(final Bundle bundle) {
        Log.v(TAG, "onSaveInstanceState");
        this.brightcoveVideoView.getEventEmitter().on(EventType.FRAGMENT_SAVE_INSTANCE_STATE, new EventListener() {
            @Default
            public void processEvent(Event event) {
                BrightcovePlayerFragment.super.onSaveInstanceState(bundle);
            }
        });
        this.lifecycleUtil.fragmentOnSaveInstanceState(bundle);
    }

    @TargetApi(17)
    public void onViewStateRestored(Bundle bundle) {
        Log.v(TAG, "onViewStateRestored");
        super.onViewStateRestored(bundle);
        this.lifecycleUtil.onViewStateRestored(bundle);
    }

    public void onPictureInPictureModeChanged(boolean z, Configuration configuration) {
        super.onPictureInPictureModeChanged(z, configuration);
        PictureInPictureManager.getInstance().onPictureInPictureModeChanged(z, configuration);
    }
}
