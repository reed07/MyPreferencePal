package com.brightcove.player.mediacontroller.buttons;

import android.view.KeyEvent;

public interface RemoteControlKeyState {
    boolean onBack(KeyEvent keyEvent);

    boolean onDpadCenter(KeyEvent keyEvent);

    boolean onDpadDown(KeyEvent keyEvent);

    boolean onDpadLeft(KeyEvent keyEvent);

    boolean onDpadRight(KeyEvent keyEvent);

    boolean onDpadUp(KeyEvent keyEvent);

    boolean onFastForward(KeyEvent keyEvent);

    boolean onHome(KeyEvent keyEvent);

    boolean onMenu(KeyEvent keyEvent);

    boolean onPause(KeyEvent keyEvent);

    boolean onPlay(KeyEvent keyEvent);

    boolean onPlayPause(KeyEvent keyEvent);

    boolean onRewind(KeyEvent keyEvent);

    boolean onSkipBackward(KeyEvent keyEvent);

    boolean onSkipForward(KeyEvent keyEvent);
}
