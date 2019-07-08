package com.brightcove.player.edge;

import com.brightcove.player.model.Playlist;

public abstract class PlaylistListener extends ErrorListener {
    public abstract void onPlaylist(Playlist playlist);
}
